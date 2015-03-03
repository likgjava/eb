package com.gpcsoft.epp.buyresult.service.impl;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryObjectBase;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.epp.bulletin.domain.Bulletin;
import com.gpcsoft.epp.bulletin.domain.BulletinTypeEnum;
import com.gpcsoft.epp.bulletin.manager.BulletinManager;
import com.gpcsoft.epp.buyresult.domain.BuyResult;
import com.gpcsoft.epp.buyresult.domain.BuyWinner;
import com.gpcsoft.epp.buyresult.domain.ConfirmResultEnum;
import com.gpcsoft.epp.buyresult.domain.ResultTypeEnum;
import com.gpcsoft.epp.buyresult.manager.BuyResultManager;
import com.gpcsoft.epp.buyresult.manager.BuyWinnerManager;
import com.gpcsoft.epp.buyresult.service.BuyResultService;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.common.utils.FileUtils;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.manager.ProjectManager;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.User;

@Service 
public class BuyResultServiceImpl extends BaseGenericServiceImpl<BuyResult> implements BuyResultService {

	@Autowired(required=true) @Qualifier("buyResultManagerImpl")
	private BuyResultManager buyResultManager;
    
	@Autowired(required=true) @Qualifier("projectManagerImpl")
	private ProjectManager projectManager;
	
	@Autowired(required=true) @Qualifier("bulletinManagerImpl")
	private BulletinManager bulletinManager;
	
	@Autowired(required=true) @Qualifier("buyWinnerManagerImpl")
	private BuyWinnerManager buyWinnerManager;
	
	/** 
	  * Description :  根据项目ID获取对应的所有包组信息
	  * Create Date: 2010-5-21下午02:25:13 by Administrator  Modified Date: 2010-5-21下午02:25:13 by Administrator
	  * @param projectId:项目Id
	  * @return
	  * @throws Exception
	  */
	public List<Project> getSubProjectByProjectId(String projectId)throws Exception {
		logger.debug("\nes BuyResultServiceImpl||getSubProjectByProjectId\n");
		return projectManager.getSubProjectByQueryObject(projectId);
	}
	
	/** 
	  * Description :  根据项目ID获取对应的所有评标报告信息
	  * Create Date: 2010-5-21下午02:25:13 by Administrator  Modified Date: 2010-5-21下午02:25:13 by Administrator
	  * @param subProjId:包组Id
	  * @return
	  * @throws Exception
	  */
	public List<Bulletin> getbidEvaluationReportByProjectId(String subProjId) {
		logger.debug("\nes BuyResultServiceImpl||getbidEvaluationReportByProjectId\n");
		QueryObject<Bulletin> queryObject = new QueryObjectBase<Bulletin>();
		queryObject.setEntityClass(Bulletin.class);
		/** 公告类型为：评标报告 */
		queryObject.getQueryParam().and(new QueryParam("bullType",QueryParam.OPERATOR_EQ,BulletinTypeEnum.BID_EVALUATION_REPORT));
		queryObject.getQueryParam().and(new QueryParam("projectId",QueryParam.OPERATOR_EQ,subProjId));
		queryObject.getQueryParam().and(new QueryParam("useStatus",QueryParam.OPERATOR_EQ,CommonEnum.USER_STATUS_FORMAL));
		List<Bulletin> list = bulletinManager.getBulletinListByQueryObject(queryObject);
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Bulletin bulletin = (Bulletin) iterator.next();
			if(bulletin!=null&&bulletin.getContent()!=null){
				String filePath = messageSource.getMessage("uploadUrl");//创建附件对象
				/** 读取公告内容 */
				String bullContent = FileUtils.readFileByLines(filePath+bulletin.getContent().getPath()+bulletin.getContent().getFileName(), null);
				bulletin.setBullContents(bullContent);
			}
		}
		return list;
	}
	
	/**
	 * Description :保存成交结果与成交供应商  
	 * Create Date: 2010-6-26下午04:48:59 by lic  Modified Date: 2010-6-26下午04:48:59 by lic
	 * @param   
	 * @return  
	 * @Exception
	 */
	public BuyResult saveBuyResultAndBuyWinner(BuyResult buyResult, String suppliers,String allSupplierIds) {
		logger.debug("\nes BuyResultServiceImpl||saveBuyResultAndBuyWinner\n");
		String subProjId = buyResult.getSubProjId();
		BuyResult OldBuyResult = buyResultManager.getBuyResultBySubProjId(subProjId);
		if(null == OldBuyResult){ //如果没有记录就保存成交结果
			String[] supplierArray = suppliers.split(",");
			String[] allSupplierArray = allSupplierIds.split(",");
			if(allSupplierArray.length==0){
				buyResult.setBuyrResult(ConfirmResultEnum.NO_PARTICIPATOR);
			} else if(allSupplierArray.length<3) {
				buyResult.setBuyrResult(ConfirmResultEnum.NO_ENOUGH_PARTICIPATOR);
			} else if(supplierArray.length>0) {
				buyResult.setBuyrResult(ConfirmResultEnum.CONFIRM);
			} else {
				buyResult.setBuyrResult(ConfirmResultEnum.GIVEUP);
			}
			buyResultManager.save(buyResult);
			
			for(int i=0;i<allSupplierArray.length;i++) {
				BuyWinner buyWinner = new BuyWinner();
				buyWinner.setSelllerId(allSupplierArray[i]);
				buyWinner.setBuyResult(buyResult);
				OrgInfo supplier = (OrgInfo) this.get(allSupplierArray[i], OrgInfo.class);
				if(null!=supplier) {
					buyWinner.setSellerName(supplier.getOrgName());
				}
				    buyWinner.setResultType(ResultTypeEnum.DEAL_NO);
				 for(int j=0;j<supplierArray.length;j++) {
					 if(allSupplierArray[i].equals(supplierArray[j])) {
						 buyWinner.setResultType(ResultTypeEnum.DEAL_YES);
					 }
				 }
				buyWinnerManager.save(buyWinner);
			}
		}
		Project project = projectManager.get(buyResult.getProject().getObjId());
		String parentBizId = project.getObjId();
		if(project.getParentId()!= null ){
			parentBizId = project.getParentId();
		}
		User user = AuthenticationHelper.getCurrentUser();
		buyResult.setUser(user);
		buyResult.setParentBizId(parentBizId);
		return buyResult;
	}
	
	/**
	 * Description :根据项目主键得到成交结果 
	 * Create Date: 2010-6-26下午04:48:59 by liuke  Modified Date: 2010-6-26下午04:48:59 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public BuyResult getBuyResultBySubProjId(String subProjId) {
		logger.debug("\nes BuyResultServiceImpl||getBuyResultBySubProjId\n");
		return buyResultManager.getBuyResultBySubProjId(subProjId);
	}
	
	public List<BuyResult> getListByProjectId(String projectId) {
		logger.debug("\n BuyResultServiceImpl||getListByProjectId\n");
		return buyResultManager.getListByProjectId(projectId);
	}
	
	/** 
	 * FuncName : finishBuyResult
	 * Description :  完成定标
	 * Create Date: 2011-11-16下午02:52:34 by yangx  
	 * Modified Date: 2011-11-16下午02:52:34 by yangx
	 * @param   projectId：项目Id
	 * @return  BuyResult
	 */
	public BuyResult finishBuyResult(String projectId){
		BuyResult buyResult = new BuyResult();
		Project project = new Project();
		project.setObjId(projectId);
		buyResult.setProject(project);
		buyResult.setParentBizId(projectId);
		buyResult.setUser(AuthenticationHelper.getCurrentUser(true));
		return buyResult;
	}
}
