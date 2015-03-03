package com.gpcsoft.epp.projreview.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.epp.bid.domain.BidPackage;
import com.gpcsoft.epp.bid.manager.BidPackageManager;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.domain.ProjectRule;
import com.gpcsoft.epp.project.manager.ProjectManager;
import com.gpcsoft.epp.projreview.dao.OpenBidRecordDao;
import com.gpcsoft.epp.projreview.domain.OpenBid;
import com.gpcsoft.epp.projreview.domain.OpenBidRecord;
import com.gpcsoft.epp.projreview.manager.OpenBidManager;
import com.gpcsoft.epp.projreview.manager.OpenBidRecordManager;
import com.gpcsoft.epp.projreview.service.OpenBidRecordService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.User;

@Service 
public class OpenBidRecordServiceImpl extends BaseGenericServiceImpl<OpenBidRecord> implements OpenBidRecordService {

	@Autowired(required=true) @Qualifier("openBidRecordManagerImpl")
	private OpenBidRecordManager openBidRecordManager;

	@Autowired(required=true) @Qualifier("openBidManagerImpl")
	private OpenBidManager openBidManager;
	
	@Autowired(required=true) @Qualifier("projectManagerImpl")
	private ProjectManager projectManager;
	
	@Autowired(required=true)  @Qualifier("openBidRecordDaoHibernate")
	private OpenBidRecordDao openBidRecordDaoHibernate;
	
	@Autowired(required=true) @Qualifier("bidPackageManagerImpl")
	private BidPackageManager bidPackageManager;
	
	/**
	 * FuncName:getopenBidRecordListByPackId
	 * Description:根据包件ID得到开标记录列表
	 * @param   packId:包组主键
	 * @return  List
	 * @author liuke
	 * @Create Date: 2010-6-20下午04:09:37 
	 * @Modifier   liuke
	 * @Modified Date: 2010-6-20下午04:09:37 
	 */
	@SuppressWarnings("unchecked")
	public List getopenBidRecordListByPackId(String packId) {
		logger.debug("\nes=OpenBidRecordServiceImpl||getopenBidRecordListByPackId\n");
		return openBidRecordManager.getOpenBidRecordByPackId(packId);
	}
	
	/** 
	  * FuncName:getSubProjectByProjectId
	  * Description:根据项目主键获取对应的所有包组信息
	  * @param projectId:项目主键
	  * @return List<Project>
	  * @author Administrator
	  * @Create Date: 2010-5-21下午02:25:13   
	  * @Modifier Administrators
	  * @Modified Date: 2010-5-21下午02:25:13 
	  */
	public List<Project> getSubProjectByProjectId(String projectId)throws Exception {
		logger.debug("\n OpenBidRecordServiceImpl||getSubProjectByProjectId\n");
		return projectManager.getSubProjectByQueryObject(projectId);
	}
	
	 /**
	  * FuncName:saveOpenBidRecordBySubProjectId
	  * Description :根据包组进行开标  
	  * @param   projectId：项目Id,subProjectId：包组Id
	  * @return  Project
	  * @author 	 liuke
	  * @Create Date: 2010-9-17下午06:08:46 
	  * @Modifier   liuke
	  * @Modified Date: 2010-9-17下午06:08:46  
	  */
	public Project saveOpenBidRecordBySubProjectId(String projectId,String subProjectId)throws Exception {
		logger.debug("\n OpenBidRecordServiceImpl||saveOpenBidRecordBySubProjectId\n");
		Project project = projectManager.get(projectId);
		Project pack = projectManager.get(subProjectId);
		ProjectRule projectRule = projectManager.getProjectRuleByProjectId(projectId);//项目规则
		if(projectRule.getIsDividePack()==true){//如果项目已经分包
		List<BidPackage> bidPackageList = bidPackageManager.getAllByPackId(subProjectId);
		if(null != bidPackageList && bidPackageList.size()>0){
			// 保存开标
			OpenBid	openBid = new OpenBid();
			openBid.setProjId(pack.getParentId());
			openBid.setSubProjId(pack.getObjId());
			openBid.setProjName(pack.getProjName());
			openBid.setProjCode(pack.getProjCode());
			openBidManager.save(openBid);
			// 保存开标记录
			for(BidPackage bidPackage:bidPackageList){
				OpenBidRecord openBidRecord = new OpenBidRecord();
				openBidRecord.setOpenBId(openBid.getObjId());
				openBidRecord.setSubProjId(pack.getObjId());
				openBidRecord.setSellerName(bidPackage.getBid().getSupplierName());
				openBidRecord.setOpenBRStatus("01");
				openBidRecord.setBidId(bidPackage.getBid().getObjId());
				openBidRecord.setSupplier(bidPackage.getBid().getSupplier());
				openBidRecord.setQuoteSum(new BigDecimal(bidPackage.getQuotesum()));
				openBidRecord.setCreateTime(new java.util.Date());
				openBidRecord.setCreateUser(AuthenticationHelper.getCurrentUser());
				openBidRecordManager.save(openBidRecord);
			}
		}
		}else{//项目没分包
			List<BidPackage> bidPackageList = bidPackageManager.getAllByPackId(projectId);
			if(null != bidPackageList && bidPackageList.size()>0){
				// 保存开标
				OpenBid	openBid = new OpenBid();
				openBid.setProjId(projectId);
				openBid.setSubProjId(projectId);
				openBid.setProjName(project.getProjName());
				openBid.setProjCode(project.getProjCode());
				openBidManager.save(openBid);
				// 保存开标记录
				for(BidPackage bidPackage:bidPackageList){
					OpenBidRecord openBidRecord = new OpenBidRecord();
					openBidRecord.setOpenBId(openBid.getObjId());
					openBidRecord.setSubProjId(projectId);
					openBidRecord.setSellerName(bidPackage.getBid().getSupplierName());
					openBidRecord.setOpenBRStatus("01");
					openBidRecord.setBidId(bidPackage.getBid().getObjId());
					openBidRecord.setSupplier(bidPackage.getBid().getSupplier());
					openBidRecord.setQuoteSum(new BigDecimal(bidPackage.getQuotesum()));
					openBidRecord.setCreateTime(new java.util.Date());
					openBidRecord.setCreateUser(AuthenticationHelper.getCurrentUser());
					openBidRecordManager.save(openBidRecord);
				}
			}
		}
		String parentBizId = project.getObjId();
		if(project.getParentId()!= null ){
			parentBizId = project.getParentId();
		}
		project.setParentBizId(parentBizId);
		User user = AuthenticationHelper.getCurrentUser();
		project.setUser(user);
		return project;
	}
	
	/**
	 * FuncName:getAllOpenBidRecordListByProjectId
	 * Description :根据项目Id获得所有开标记录对象   
	 * @param   projectId:项目主键
	 * @return  List<OpenBidRecord>
	 * @author liuke
	 * @Create Date: 2010-10-9下午05:46:34 
	 * @Modifier   liuke
	 * @Modified Date: 2010-10-9下午05:46:34 
	 */
	public List<OpenBidRecord> getAllOpenBidRecordListByProjectId(String projectId) throws Exception {
		logger.debug("\n OpenBidRecordServiceImpl||getAllOpenBidRecordListByProjectId\n");
		return openBidRecordDaoHibernate.getAllOpenBidRecordByProjectId(projectId);
	}
	
	/**
	  * FuncName:getOpenBidRecordListByOpenBidIdAndSupplierId
	  * Description :根据开标Id和供应商ID获得开标记录对象  
	  * @param   openBidId:开标ID
	  * @param   supplierId:供应商ID
	  * @return  OpenBidRecord
	  * @author  shengn
	  * @Create  Date:2011-10-12 15:59  
	  */
	public OpenBidRecord getOpenBidRecordListByOpenBidIdAndSupplierId(String openBidId,String supplierId)throws Exception {
		logger.debug("\n OpenBidRecordServiceImpl||getOpenBidRecordListByOpenBidIdAndSupplierId\n");
		try {
			return openBidRecordDaoHibernate.getOpenBidRecordListByOpenBidIdAndSupplierId(openBidId,supplierId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("获取开标记录出错！");
		}
	}
}
