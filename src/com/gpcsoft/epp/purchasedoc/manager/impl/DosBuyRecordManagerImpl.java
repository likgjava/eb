package com.gpcsoft.epp.purchasedoc.manager.impl;


import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.epp.common.domain.EsExceptionEnum;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.project.dao.ProjectDao;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.purchasedoc.dao.DosBuyRecordDao;
import com.gpcsoft.epp.purchasedoc.dao.PurchaseDocDao;
import com.gpcsoft.epp.purchasedoc.domain.DosBuyRecord;
import com.gpcsoft.epp.purchasedoc.domain.DosBuyTypeEnum;
import com.gpcsoft.epp.purchasedoc.domain.PurchaseDoc;
import com.gpcsoft.epp.purchasedoc.domain.PurchaseDocEnum;
import com.gpcsoft.epp.purchasedoc.manager.DosBuyRecordManager;
import com.gpcsoft.srplatform.auth.domain.User;

@Repository
public class DosBuyRecordManagerImpl extends BaseManagerImpl<DosBuyRecord> implements DosBuyRecordManager {

	@Autowired(required=true)  @Qualifier("dosBuyRecordDaoHibernate")
	private DosBuyRecordDao dosBuyRecordDaoHibernate;
    
	@Autowired(required=true)  @Qualifier("projectDaoHibernate")
	private ProjectDao projectDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("purchaseDocDaoHibernate")
	private PurchaseDocDao purchaseDocDaoHibernate;
	
	/**
	 * 
	 * Description :通过供应商主键判断是否购买采购文件  
	 * Create Date: 2010-5-27下午04:52:06 by liuke  Modified Date: 2010-5-27下午04:52:06 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public boolean IsBuyPurchaseDoc(String projectId, String subprojectId ,User user) {
		StringBuffer exception = new StringBuffer();
		Boolean checkValue = true;//判断是否有不满足条件的数据
		if (null==projectId ||"".equals(projectId)) {//项目不存在
			exception.append(messageSource.getMessage(EsExceptionEnum.PROJECT_ISNOTEXISTED));
		} else {
			Project project = projectDaoHibernate.get(projectId);
			PurchaseDoc purchaseDoc = purchaseDocDaoHibernate.getPurchaseDocByProjectIdAndFileType(project.getObjId(), PurchaseDocEnum.PURCHASEDOC);
			if(null==purchaseDoc){
				purchaseDoc = purchaseDocDaoHibernate.getPurchaseDocByProjectIdAndFileType(project.getObjId(), PurchaseDocEnum.INQPDOC);
			}
			if (null==purchaseDoc){//招标文件不存在
				exception.append(messageSource.getMessage(EsExceptionEnum.DOC_IS_NONE));
			}else if(null!=((OrgInfo)user.getOrgInfo()).getSupplierId()&&!"".equals(((OrgInfo)user.getOrgInfo()).getSupplierId())){//当前用户是供应商
				DosBuyRecord dosBuyRecord = dosBuyRecordDaoHibernate.getDosBuyRecordByDosId(purchaseDoc.getObjId(),user.getOrgInfo().getObjId());
				if(null==dosBuyRecord){//没有录入购买记录
					exception.append(messageSource.getMessage(EsExceptionEnum.DOCBUYRECORD_IS_NONE));
				}else{
				    if(DosBuyTypeEnum.ONLY_APPLICATION.equals(dosBuyRecord.getDocBuyStatus())) {//没有支付购买文件
				    	exception.append(messageSource.getMessage(EsExceptionEnum.DOCBUYRECORD_IS_NOTPAY));
				    }
				}
			}
			
		}
		if(exception.length()>0)checkValue=false;//若有异常信息，表示有异常，需要把判断置为false;
		if (!checkValue) {
			throw new EsException(exception.toString());//若有影响项目操作的数据，则抛出异常
		}
		return checkValue;
	}
	
	
	
	/**
	 * 
	 * Description :  采购文件购买记录对供应商参与招标项目的影响
	 * Create Date: 2010-8-11上午09:30:37 by shenjianzhuang  Modified Date: 2010-8-11上午09:30:37 by shenjianzhuang
	 * @return
	 * @return  boolean
	 * @Exception 
	 */
	public boolean checkDocBuyStatusToProject(String projectId, String supplierId)
	{
		StringBuffer exception = new StringBuffer();
		Boolean checkValue = true;//判断是否有不满足条件的数据
		if (null==projectId ||"".equals(projectId)) {//项目不存在
			exception.append(messageSource.getMessage(EsExceptionEnum.PROJECT_ISNOTEXISTED));
		} else {
			PurchaseDoc purchaseDoc = purchaseDocDaoHibernate.getPurchaseDocByProjectId(projectId);//通过项目Id获取采购文件
			DosBuyRecord dosBuyRecord = dosBuyRecordDaoHibernate.getDosBuyRecordByDosId(purchaseDoc.getObjId(), supplierId);//通过采购文件Id和供应商Id获取采购文件购买记录
			if (null==purchaseDoc){
				exception.append(messageSource.getMessage(EsExceptionEnum.DOC_IS_NONE));//招标文件不存在
			}else {
				if(null==dosBuyRecord){
					exception.append(messageSource.getMessage(EsExceptionEnum.DOCBUYRECORD_IS_NONE));//招标文件购买记录不存在
				}else{
				    if(DosBuyTypeEnum.ONLY_APPLICATION.equals(dosBuyRecord.getDocBuyStatus())) {
				    	exception.append(messageSource.getMessage(EsExceptionEnum.DOCBUYRECORD_IS_NOTPAY));//没有支付购买采购文件
				    }
				}
			}
		}
		if(exception.length()>0)checkValue=false;//若有异常信息，表示有异常，需要把判断置为false;
		if (!checkValue) {
			throw new EsException(exception.toString());//若有影响项目操作的数据，则抛出异常
		}
	    return checkValue;
	}



	public List<DosBuyRecord> getByProjectId(String projectId) {
		return dosBuyRecordDaoHibernate.getByProjectId(projectId);
	}
	
	/** 
	 * FuncName : getDosBuyRecordBySupplierId
	 * Description :  根据供应商Id获取标书费购买记录
	 * Create Date: 2011-9-22下午11:38:18 by yangx  
	 * Modified Date: 2011-9-22下午11:38:18 by yangx
	 * @param   supplierId：供应商Id
	 * @return  DosBuyRecord
	 * @Exception   
	 */
	public List<DosBuyRecord> getDosBuyRecordBySupplierId(String supplierId,String projectId){
		List<DosBuyRecord> dosBuyRecordList = dosBuyRecordDaoHibernate.getDosBuyRecordBySupplierId(supplierId,projectId);
		return dosBuyRecordList;
	}
}
