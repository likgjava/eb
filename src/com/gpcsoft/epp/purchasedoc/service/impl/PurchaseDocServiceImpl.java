package com.gpcsoft.epp.purchasedoc.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.bizplatform.agency.domain.Agency;
import com.gpcsoft.bizplatform.agency.manager.AgencyManager;
import com.gpcsoft.core.context.FrameMessageResource;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.FileUtil;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.core.utils.StringUtils;
import com.gpcsoft.epp.common.domain.AuditStatusEnum;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.common.domain.DataItem;
import com.gpcsoft.epp.common.domain.EbuyMethodEnum;
import com.gpcsoft.epp.common.domain.EsExceptionEnum;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.common.service.UserApiService;
import com.gpcsoft.epp.common.utils.FileUtils;
import com.gpcsoft.epp.eval.service.CongruousFactorService;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.manager.ProjectMTaskPlanManager;
import com.gpcsoft.epp.project.manager.ProjectManager;
import com.gpcsoft.epp.projrule.domain.ProjProcessRule;
import com.gpcsoft.epp.projrule.manager.ProjProcessRuleManager;
import com.gpcsoft.epp.purchasedoc.dao.PurchaseDocDao;
import com.gpcsoft.epp.purchasedoc.domain.PurchaseDoc;
import com.gpcsoft.epp.purchasedoc.domain.PurchaseDocAtt;
import com.gpcsoft.epp.purchasedoc.domain.PurchaseDocEnum;
import com.gpcsoft.epp.purchasedoc.manager.PurchaseDocAttManager;
import com.gpcsoft.epp.purchasedoc.manager.PurchaseDocManager;
import com.gpcsoft.epp.purchasedoc.service.PurchaseDocService;
import com.gpcsoft.epp.signuprecord.domain.SignUprecord;
import com.gpcsoft.epp.taskplan.domain.TaskPlanSub;
import com.gpcsoft.epp.webService.ueSystem.bulletin.dto.PurchaseFileDTO;
import com.gpcsoft.epp.worktask.domain.CompletedWorkTaskResultEnum;
import com.gpcsoft.epp.worktask.domain.CompletedWorkTaskTypeEnum;
import com.gpcsoft.epp.worktask.manager.CompletedWorkTaskManager;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.plugin.wflow.domain.WorkFlowModel;
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.auth.manager.AttachmentManager;
import com.gpcsoft.srplatform.auth.service.AttachmentService;
import com.gpcsoft.srplatform.sysconfig.domain.SysConfigEnum;

@Service 
public class PurchaseDocServiceImpl extends BaseGenericServiceImpl<PurchaseDoc> implements PurchaseDocService {

	@Autowired(required=true) @Qualifier("purchaseDocManagerImpl")
	private PurchaseDocManager purchaseDocManager;

	@Autowired(required=true) @Qualifier("purchaseDocAttManagerImpl")
	private PurchaseDocAttManager purchaseDocAttManager;
	
	@Autowired(required=true) @Qualifier("projProcessRuleManagerImpl")
	private ProjProcessRuleManager projProcessRuleManager;
	
	@Autowired(required=true) @Qualifier("projectManagerImpl")
	private ProjectManager projectManager;
	
	@Autowired(required=true) @Qualifier("projectMTaskPlanManagerImpl")
	private ProjectMTaskPlanManager projectMTaskPlanManager;
		
	@Autowired(required=true)  @Qualifier("purchaseDocDaoHibernate")
	private PurchaseDocDao purchaseDocDaoHibernate;
	
	@Autowired(required=true) @Qualifier("completedWorkTaskManagerImpl")
	private CompletedWorkTaskManager completedWorkTaskManager;
	
	@Autowired(required=true) @Qualifier("congruousFactorServiceImpl")
	private CongruousFactorService congruousFactorService;	
	
	@Autowired(required=true) @Qualifier("userApiServiceImpl")
	private UserApiService userApiService;
	
	@Autowired(required=true) @Qualifier("agencyManagerImpl")
	private AgencyManager agencyManager;
	
	@Autowired(required=false)@Qualifier("attachmentManagerImpl")
	private AttachmentManager attachmentManager;
	
	@Autowired(required = true)
	@Qualifier("attachmentServiceImpl")
	private AttachmentService attachmentService;
	
	@Autowired(required = true)
	@Qualifier("frameMessageResource")
	private FrameMessageResource messageSource;
	
	/** 
	 * Description :  根据文件Id获取文件
	 * Create Date: 2010-9-7下午04:58:24 by yangx  Modified Date: 2010-9-7下午04:58:24 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public PurchaseDoc getPurchaseDocByObjId(String objId){
		logger.debug("\n PurchaseDocServiceImpl||getPurchaseDocByObjId\n");
		return purchaseDocManager.get(objId);
	}
	
	/** 
	 * Description :  根据项目Id、招标文件Id获取未录入购买记录的且报名的供应商
	 * Create Date: 2010-10-28下午02:17:47 by yangx  Modified Date: 2010-10-28下午02:17:47 by yangx
	 * @param   projectId：项目Id,purchaseDocId：招标文件Id
	 * @return  List<SignUprecord>
	 * @Exception   
	 */
	public List<SignUprecord> getSupplierByProjectIdAndPurchaseDocId(String projectId,String purchaseDocId){
		logger.debug("\n=PurchaseDocServiceImpl||getSupplierByProjectIdAndPurchaseDocId\n");
		return purchaseDocDaoHibernate.getSupplierByProjectIdAndPurchaseDocId(projectId,purchaseDocId);
	}
	
	/**
	 * 
	 * Description : 根据项目主键得到采购文件 
	 * Create Date: 2010-5-19下午03:48:57 by liuke  Modified Date: 2010-5-19下午03:48:57 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public PurchaseDoc getPurchaseDocByProjectId(String projectId) {
		logger.debug("\n PurchaseDocServiceImpl||getPurchaseDocByProjectId\n");
		return purchaseDocManager.getPurchaseDocByProjectId(projectId);
	}
    
	/** 
	 * Description :  保存采购文件
	 * Create Date: 2010-9-6下午02:33:40 by yangx  Modified Date: 2010-9-6下午02:33:40 by yangx
	 * @param   purchaseDoc：招标文件对象
	 * @return  PurchaseDoc
	 * @Exception   
	 */
	public PurchaseDoc savePurchaseDoc(PurchaseDoc purchaseDoc){
		logger.debug("\n PurchaseDocServiceImpl||savePurchaseDoc\n");
		if (purchaseDoc!=null&&purchaseDoc.getObjId()!=null) {//修改
			ProjProcessRule projProcessRule= projProcessRuleManager.getProjProcessRuleByProjectIdAndCode(purchaseDoc.getProject().getObjId(),SysConfigEnum.NEEDBUYERACKPURCHASEDOC);
			if (projProcessRule!=null&&"false".equals(projProcessRule.getResValue())) {
				purchaseDoc.setAuditStatus(PurchaseDocEnum.PURCHASEOFFICE_WAIT);
				purchaseDoc.setUseStatus(AuditStatusEnum.AUDIT_PASS);//有效状态
			}else{
				purchaseDoc.setAuditStatus(PurchaseDocEnum.FORAUDIT);
			}
			purchaseDocManager.save(purchaseDoc);
			if(CommonEnum.USER_STATUS_TEMP.equals(purchaseDoc.getUseStatus())){
				completedWorkTaskManager.createCompTaskByPassivity("实用工具制作采购文件",CompletedWorkTaskTypeEnum.WORKTASK_TYPE_PURCHASEDOC,null,purchaseDoc.getObjId(),purchaseDoc.getProject().getObjId(),"",CompletedWorkTaskResultEnum.WORKTASK_SAVE);
			}else{
				completedWorkTaskManager.createCompTaskByPassivity("制作采购文件",CompletedWorkTaskTypeEnum.WORKTASK_TYPE_PURCHASEDOC,null,purchaseDoc.getObjId(),purchaseDoc.getProject().getObjId(),"",CompletedWorkTaskResultEnum.WORKTASK_SAVE);
			}
		}else{//新增
			//冗余招标文件价格到项目里 edit by wangcl
			Project p = (Project)this.get(purchaseDoc.getProject().getObjId(),Project.class);
			//招标项目里的招标文件价格同步到招标文件对象里  edit by liuy
			if(purchaseDoc.getFilePrice() == null){
				if(p.getPurDocPrice()==null){
					purchaseDoc.setFilePrice("0");
				}else{
					purchaseDoc.setFilePrice(String.valueOf(p.getPurDocPrice()));
				}
			} 
			purchaseDocManager.save(purchaseDoc);
			completedWorkTaskManager.createCompTaskByPassivity("提交采购文件",CompletedWorkTaskTypeEnum.WORKTASK_TYPE_PURCHASEDOC,null,purchaseDoc.getObjId(),purchaseDoc.getProject().getObjId(),"",CompletedWorkTaskResultEnum.WORKTASK_SAVE);
		}
		User user=AuthenticationHelper.getCurrentUser();
		//从外部接口过来时没有用户
		if(user != null) {
			purchaseDoc.setUser(user);
		}
		Project project = projectManager.get(purchaseDoc.getProject().getObjId());
		if(purchaseDoc.getFilePrice()!=null){
			project.setPurDocPrice(new BigDecimal(purchaseDoc.getFilePrice()));
		}
		projectManager.save(project);
		String parentBizId = project.getObjId();
		if(project.getParentId()!= null ){
			parentBizId = project.getParentId();
		}
		purchaseDoc.setParentBizId(parentBizId);
		purchaseDoc.setWorkflowAuditStatus(WorkFlowModel.AUDIT_STATUS_PASS);
		return purchaseDoc;
	}
	
	/** 
	 * Description :  保存澄清文件
	 * Create Date: 2010-9-6下午02:33:40 by yangx  Modified Date: 2010-9-6下午02:33:40 by yangx
	 * @param   purchaseDoc：招标文件对象
	 * @return  PurchaseDoc
	 * @Exception   
	 */
	public PurchaseDoc saveClarificationDoc(PurchaseDoc purchaseDoc){
		logger.debug("\n PurchaseDocServiceImpl||saveClarificationDoc\n");
		purchaseDocManager.save(purchaseDoc);
		User user=AuthenticationHelper.getCurrentUser();
		purchaseDoc.setUser(user);
		purchaseDoc.setParentBizId(purchaseDoc.getProject().getObjId());
		return purchaseDoc;
	}
	/** 
	 * Description :  修改：保存澄清文件
	 * Create Date: 2010-9-6下午02:33:40 by yangx  Modified Date: 2010-9-6下午02:33:40 by yangx
	 * @param   purchaseDoc：招标文件对象
	 * @return  PurchaseDoc
	 * @Exception   
	 */
	public PurchaseDoc updateSaveClarificationDoc(PurchaseDoc purchaseDoc){
		logger.debug("\n PurchaseDocServiceImpl||updateSaveClarificationDoc\n");
		purchaseDocManager.save(purchaseDoc);
		User user=AuthenticationHelper.getCurrentUser();
		purchaseDoc.setUser(user);
		purchaseDoc.setParentBizId(purchaseDoc.getProject().getObjId());
		return purchaseDoc;
	}
	/** 
	 * Description :  提交澄清文件
	 * Create Date: 2010-9-6下午02:33:40 by yangx  Modified Date: 2010-9-6下午02:33:40 by yangx
	 * @param   purchaseDoc：招标文件对象
	 * @return  PurchaseDoc
	 */
	public PurchaseDoc saveSubmitClarificationDoc(PurchaseDoc purchaseDoc){
		logger.debug("\n PurchaseDocServiceImpl||saveSubmitClarificationDoc\n");
		purchaseDocManager.save(purchaseDoc);
		User user=AuthenticationHelper.getCurrentUser();
		purchaseDoc.setUser(user);
		purchaseDoc.setParentBizId(purchaseDoc.getProject().getObjId());
		purchaseDoc.setWorkflowAuditStatus(WorkFlowModel.AUDIT_STATUS_PASS);
		return purchaseDoc;
	}

	/** 
	 * Description :  修改：提交澄清文件
	 * Create Date: 2010-9-6下午02:33:40 by yangx  Modified Date: 2010-9-6下午02:33:40 by yangx
	 * @param   purchaseDoc：招标文件对象
	 * @return  PurchaseDoc
	 */
	public PurchaseDoc updateSubmitClarificationDoc(PurchaseDoc purchaseDoc){
		logger.debug("\n PurchaseDocServiceImpl||updateSubmitClarificationDoc\n");
		purchaseDocManager.save(purchaseDoc);
		User user=AuthenticationHelper.getCurrentUser();
		purchaseDoc.setUser(user);
		purchaseDoc.setParentBizId(purchaseDoc.getProject().getObjId());
		purchaseDoc.setWorkflowAuditStatus(WorkFlowModel.AUDIT_STATUS_PASS);
		return purchaseDoc;
	}
	/** 
	 * Description :  修改采购文件
	 * Create Date: 2010-9-6下午02:33:40 by yangx  Modified Date: 2010-9-6下午02:33:40 by yangx
	 * @param   purchaseDoc：招标文件对象
	 * @return  PurchaseDoc
	 */
//	public PurchaseDoc updatePurchaseDoc(PurchaseDoc purchaseDoc){
//		logger.debug("\n PurchaseDocServiceImpl||updatePurchaseDoc\n");
//		ProjProcessRule projProcessRule= projProcessRuleManager.getProjProcessRuleByProjectIdAndCode(purchaseDoc.getProject().getObjId(),SysConfigEnum.NEEDBUYERACKPURCHASEDOC);
//		if (projProcessRule!=null&&"false".equals(projProcessRule.getResValue())) {//业务规则不需要采购人确认招标文件
//				purchaseDoc.setAuditStatus(PurchaseDocEnum.PURCHASEOFFICE_WAIT);//采购办审核
//		}else{
//			purchaseDoc.setAuditStatus(PurchaseDocEnum.FORAUDIT);//00待采购人确认   01 采购人确认通过  02  采购办审核通过 03 监察局审核通过
//		}
//		purchaseDocManager.save(purchaseDoc);
//		User user=AuthenticationHelper.getCurrentUser();
//		purchaseDoc.setUser(user);
//		Project project = projectManager.get(purchaseDoc.getProject().getObjId());
//		String parentBizId = project.getObjId();
//		if(project.getParentId()!= null ){
//			parentBizId = project.getParentId();
//		}
//		purchaseDoc.setParentBizId(parentBizId);
//		completedWorkTaskManager.createCompTaskByPassivity("修改采购文件",CompletedWorkTaskTypeEnum.WORKTASK_TYPE_PURCHASEDOC,null,purchaseDoc.getObjId(),parentBizId,"",CompletedWorkTaskResultEnum.WORKTASK_SAVE);
//		return purchaseDoc;
//	}
	
	/**
	 * 
	 * Description :[采购办]得到待审核采购文件列表 
	 * Create Date: 2010-6-21上午11:32:33 by liuke  Modified Date: 2010-6-21上午11:32:33 by liuke
	 * Modified at 2011-4-26 11:44 by zhouzhanghe(去掉过滤条件)
	 * @param   
	 * @return  
	 * @Exception
	 */
	public Page<PurchaseDoc> getPurchaseDocByUser(Page<PurchaseDoc> page,
			QueryObject queryObject,User user,String fileType) {
		logger.debug("\n PurchaseDocServiceImpl||getPurchaseDocByUser\n");
		queryObject.getQueryParam().and(new QueryParam("fileType",QueryParam.OPERATOR_EQ,fileType));
		queryObject.getQueryParam().and(new QueryParam("auditStatus",QueryParam.OPERATOR_EQ,PurchaseDocEnum.PURCHASEOFFICE_WAIT));
		queryObject.getQueryProjections().setOrderProperty("createTime");
		queryObject.getQueryProjections().setDescFlag(true);
		return this.findByQuery(queryObject, true, page);
	}
	/**
	 * 
	 * Description : [采购人]根据申报书明细，得到采购人的待确认采购文件列表 []
	 * Create Date: 2010-7-6下午05:34:00 by liuke  Modified Date: 2010-7-6下午05:34:00 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public Page getPurchaseDocByBuyer(Page<PurchaseDoc> page,
			User user,String fileType) {
		logger.debug("\n PurchaseDocServiceImpl||getPurchaseDocByBuyer\n");
		Page pages = this.purchaseDocDaoHibernate.getPurchaseDocByBuyer(page, user,fileType);
		return pages;
	}

	/** 
	 * Description :  根据查询条件统计对应的采购文件数量
	 * Create Date: 2010-7-7下午03:38:09 by Administrator  Modified Date: 2010-7-7下午03:38:09 by Administrator
	 * @param queryObject[buyerId:采购人ID;auditStatus:审核状态]
	 * @return
	 */
	public BigDecimal getPurDocTotalByQueryObject(QueryObject queryObject) {
		logger.debug("\n PurchaseDocServiceImpl||getPurDocTotalByQueryObject\n");
		return purchaseDocManager.getPurDocTotalByQueryObject(queryObject);
	}
     
	
	/**
	 * 
	 * Description : 通过项目查找项目总金额
	 * Create Date: 2010-7-10下午12:31:09 by liuke  Modified Date: 2010-7-10下午12:31:09 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public Double getTotalPriceByProject(String projectId) {
		logger.debug("\n PurchaseDocServiceImpl||getTotalPriceByProject\n");
		//根据项目获得该项目的申报书条目列表
		Double sumPrice = 0.0;
		List taskPalnSubList = projectMTaskPlanManager.getAllTaskPlanSubByProject(projectId);
		for(int i=0;i<taskPalnSubList.size();i++)
		{
			TaskPlanSub taskPlanSub = (TaskPlanSub) taskPalnSubList.get(i);
			sumPrice += taskPlanSub.getTotalPrice().doubleValue();
		}
		return sumPrice;
	}
    
	/**
	 * 
	 * Description :得到更多采购文件信息  
	 * Create Date: 2010-7-13上午10:42:20 by liuke  Modified Date: 2010-7-13上午10:42:20 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public Page<PurchaseDoc> searchPurchaseDocsByQueryObject(
			Page<PurchaseDoc> page, QueryObject queryObject) {
		logger.debug("\n PurchaseDocServiceImpl||searchPurchaseDocsByQueryObject\n");
		return purchaseDocManager.searchPurchaseDocsByQueryObject(page, queryObject);
	}

	
	/**
	 * 
	 * Description :确认采购文件
	 * Create Date: 2010-8-23下午05:35:31 by liuke  Modified Date: 2010-8-23下午05:35:31 by liuke
	 * @param   ids：招标文件Ids,opinion：意见,auditStatus：是否通过标志,workFlowTaskId：工作流Id
	 * @return  PurchaseDoc
	 * @Exception
	 */
	public PurchaseDoc saveConfirmPurchaseDoc(String ids,String opinion,String auditStatus,String workFlowTaskId) {
		logger.debug("\n PurchaseDocServiceImpl||saveConfirmPurchaseDoc\n");
		String bizIds = "";
		PurchaseDoc purchaseDoc = null;
		String[] objIds = ids.split(",");
		for (String objId : objIds) {
			purchaseDoc = this.purchaseDocManager.get(objId);
			String completedWorkTaskResult = "";
			if(null!=auditStatus &&"Y".equals(auditStatus)) {  //采购人确认通过
				purchaseDoc.setWorkflowAuditStatus(WorkFlowModel.AUDIT_STATUS_PASS);
				completedWorkTaskResult = CompletedWorkTaskResultEnum.WORKTASK_PASSAUDIT;
			} else if(null!=auditStatus &&"N".equals(auditStatus)) { //采购人确认不通过
				purchaseDoc.setWorkflowAuditStatus(WorkFlowModel.AUDIT_STATUS_NO_PASS);
				completedWorkTaskResult = CompletedWorkTaskResultEnum.WORKTASK_NOPASSAUDIT;
			}
			purchaseDoc.setWorkFlowTaskId(workFlowTaskId);
			purchaseDocManager.save(purchaseDoc);
			Project project = projectManager.get(purchaseDoc.getProject().getObjId());
			String parentBizId = project.getObjId();
			if(project.getParentId()!= null ){
				parentBizId = project.getParentId();
			}
			completedWorkTaskManager.createCompTaskByPassivity("采购人确认采购文件",CompletedWorkTaskTypeEnum.WORKTASK_TYPE_PURCHASEDOC,opinion,purchaseDoc.getObjId(),parentBizId,"",completedWorkTaskResult);
			bizIds = bizIds+parentBizId+",";
		}
		purchaseDoc.setUser(AuthenticationHelper.getCurrentUser());
		purchaseDoc.setParentBizId(bizIds.substring(0, bizIds.length()-1));
		return purchaseDoc;
	}
	
	/** 
	 * Description :  审核文件
	 * Create Date: 2010-9-6下午04:09:37 by yangx  Modified Date: 2010-9-6下午04:09:37 by yangx
	 * @param   ids：招标文件Ids,opinion：意见,auditStatus：是否通过标志,workFlowTaskId：工作流Id
	 * @return  PurchaseDoc
	 * @Exception   
	 */
	public PurchaseDoc auditPurchaseDoc(String ids,User auditor,String opinion,String auditStatus,String workFlowTaskId){
		logger.debug("\n PurchaseDocServiceImpl||auditPurchaseDoc\n");
		String bizIds = "";
		PurchaseDoc purchaseDoc = null;
		String[] objIds = ids.split(",");
		for (String objId : objIds) {
			purchaseDoc = this.purchaseDocManager.get(objId);
			String completedWorkTaskResult = "";
			if(null!=auditStatus &&"Y".equals(auditStatus)) {  //采购人确认通过
				completedWorkTaskResult = CompletedWorkTaskResultEnum.WORKTASK_PASSAUDIT;
				purchaseDoc.setWorkflowAuditStatus(WorkFlowModel.AUDIT_STATUS_PASS);
			} else if(null!=auditStatus &&"N".equals(auditStatus)) { //采购人确认不通过
				completedWorkTaskResult = CompletedWorkTaskResultEnum.WORKTASK_NOPASSAUDIT;
				purchaseDoc.setWorkflowAuditStatus(WorkFlowModel.AUDIT_STATUS_NO_PASS);
			}
			purchaseDoc.setWorkFlowTaskId(workFlowTaskId);
			purchaseDocManager.save(purchaseDoc);
			Project project = projectManager.get(purchaseDoc.getProject().getObjId());
			String parentBizId = project.getObjId();
			if(project.getParentId()!= null ){
				parentBizId = project.getParentId();
			}
			completedWorkTaskManager.createCompTaskByPassivity("采购办审核采购文件",CompletedWorkTaskTypeEnum.WORKTASK_TYPE_PURCHASEDOC,opinion,purchaseDoc.getObjId(),parentBizId,"",completedWorkTaskResult);
			bizIds = bizIds+parentBizId+",";
		}
		purchaseDoc.setAuditStatus(auditStatus);
		purchaseDoc.setUser(auditor);
		purchaseDoc.setParentBizId(bizIds.substring(0, bizIds.length()-1));
		return purchaseDoc;
	}
	
	/** 
	 * Description :  审核澄清文件
	 * Create Date: 2010-9-6下午04:09:37 by yangx  Modified Date: 2010-9-6下午04:09:37 by yangx
	 * @param   ids：招标文件Ids,opinion：意见,auditStatus：是否通过标志,workFlowTaskId：工作流Id
	 * @return  PurchaseDoc 
	 * @Exception   
	 */
	public PurchaseDoc auditClarificationDoc(String ids,String opinion,String auditStatus,String workFlowTaskId) {
		logger.debug("\n PurchaseDocServiceImpl||auditClarificationDoc\n");
		String bizIds = "";
		PurchaseDoc purchaseDoc = null;
		String[] objIds = ids.split(",");
		for (String objId : objIds) {
			purchaseDoc = this.purchaseDocManager.get(objId);
			if(null!=auditStatus &&"Y".equals(auditStatus)) {  //审核澄清文件通过
				purchaseDoc.setAuditStatus(PurchaseDocEnum.PURCHASEOFFICEPASS);
				purchaseDoc.setWorkflowAuditStatus(WorkFlowModel.AUDIT_STATUS_PASS);
			} else if(null!=auditStatus &&"N".equals(auditStatus)) { //审核澄清文件不通过
				purchaseDoc.setAuditStatus(PurchaseDocEnum.RETURNBACK);  //设置状态为退回状态
				purchaseDoc.setWorkflowAuditStatus(WorkFlowModel.AUDIT_STATUS_NO_PASS);
			}
			purchaseDoc.setWorkFlowTaskId(workFlowTaskId);
			purchaseDocManager.save(purchaseDoc);
			Project project = projectManager.get(purchaseDoc.getProject().getObjId());
			String parentBizId = project.getObjId();
			if(project.getParentId()!= null ){
				parentBizId = project.getParentId();
			}
			bizIds = bizIds+parentBizId+",";
		}
		purchaseDoc.setUser(AuthenticationHelper.getCurrentUser());
		purchaseDoc.setParentBizId(bizIds.substring(0, bizIds.length()-1));
		return purchaseDoc;
	}
	
	/** 
	 * Description :  确认澄清文件
	 * Create Date: 2010-9-6下午04:09:37 by yangx  Modified Date: 2010-9-6下午04:09:37 by yangx
	 * @param   ids：招标文件Ids,opinion：意见,auditStatus：是否通过标志,workFlowTaskId：工作流Id
	 * @return  PurchaseDoc 
	 * @Exception   
	 */
	public PurchaseDoc saveConfirmClarification(String ids,String opinion,String auditStatus,String workFlowTaskId) {
		logger.debug("\n PurchaseDocServiceImpl||saveConfirmClarification\n");
		String bizIds = "";
		PurchaseDoc purchaseDoc = null;
		String[] objIds = ids.split(",");
		for (String objId : objIds) {
			purchaseDoc = this.purchaseDocManager.get(objId);
			Double sumPrice = this.getTotalPriceByProject(purchaseDoc.getProject().getObjId());
			if(null!=auditStatus &&"Y".equals(auditStatus)) {  //采购人确认通过
				if(PurchaseDocEnum.FORAUDIT.equals(purchaseDoc.getAuditStatus())){//表示采购人确认操作
					if(sumPrice>=(PurchaseDocEnum.SUMPRICE)) { //项目金额大于50万元,监察局审核
						purchaseDoc.setAuditStatus(PurchaseDocEnum.SUPERVISALOFFICE_WAIT);
					}else {   //跳过监察局审核，直接由采购办审核
						purchaseDoc.setAuditStatus(PurchaseDocEnum.PURCHASEOFFICE_WAIT);
					}
					purchaseDoc.setWorkflowAuditStatus(WorkFlowModel.AUDIT_STATUS_PASS);
				} else if(PurchaseDocEnum.SUPERVISALOFFICE_WAIT.equals(purchaseDoc.getAuditStatus())) {//监察局审核
					purchaseDoc.setAuditStatus(PurchaseDocEnum.PURCHASEOFFICE_WAIT);
					purchaseDoc.setWorkflowAuditStatus(WorkFlowModel.AUDIT_STATUS_PASS);
				} else if(PurchaseDocEnum.PURCHASEOFFICE_WAIT.equals(purchaseDoc.getAuditStatus())) {//采购办审核
					purchaseDoc.setAuditStatus(PurchaseDocEnum.PURCHASEOFFICEPASS);
					purchaseDoc.setWorkflowAuditStatus(WorkFlowModel.AUDIT_STATUS_PASS);
				}
			} else if(null!=auditStatus &&"N".equals(auditStatus)) { //采购人确认不通过
				purchaseDoc.setAuditStatus(PurchaseDocEnum.RETURNBACK);  //设置状态为退回状态
				purchaseDoc.setWorkflowAuditStatus(WorkFlowModel.AUDIT_STATUS_NO_PASS);
			}
			purchaseDoc.setWorkFlowTaskId(workFlowTaskId);
			purchaseDocManager.save(purchaseDoc);
			Project project = projectManager.get(purchaseDoc.getProject().getObjId());
			String parentBizId = project.getObjId();
			if(project.getParentId()!= null ){
				parentBizId = project.getParentId();
			}
			bizIds = bizIds+parentBizId+",";
		}
		purchaseDoc.setUser(AuthenticationHelper.getCurrentUser());
		purchaseDoc.setParentBizId(bizIds.substring(0, bizIds.length()-1));
		return purchaseDoc;
	}
	/**
	 * 
	 * Description :根据项目Id和文件类型获取文件
	 * Create Date: 2010-9-2下午04:21:49 by shenjianzhuang  Modified Date: 2010-9-2下午04:21:49 by shenjianzhuang
	 * @param projectId
	 * @param fileType
	 * @return
	 * @return  PurchaseDoc
	 * @Exception 
	 */	 
	public PurchaseDoc getPurchaseDocByProjectIdAndFileType(String projectId,String fileType) {
		logger.debug("\n PurchaseDocServiceImpl||getPurchaseDocByProjectIdAndFileType\n");
		return purchaseDocDaoHibernate.getPurchaseDocByProjectIdAndFileType(projectId, fileType);
	}

	/** 
	 * Description :  监察局审核
	 * Create Date: 2010-9-3下午02:01:28 by liuy  Modified Date: 2010-9-3下午02:01:28 by liuy
	 * @param   ids：招标文件Ids,opinion：意见,auditStatus：是否通过标志,workFlowTaskId：工作流Id
	 * @return  PurchaseDoc 
	 * @throws Exception
	 */
	
	public PurchaseDoc saveForJcjAudit(String ids,String opinion,String auditStatus,String workFlowTaskId) {
		logger.debug("\n PurchaseDocServiceImpl||saveForJcjAudit\n");
		String bizIds = "";
		PurchaseDoc purchaseDoc = null;
		String[] objIds = ids.split(",");
		for (String objId : objIds) {
			purchaseDoc = this.purchaseDocManager.get(objId);
			String completedWorkTaskResult = "";
			if(null!=auditStatus &&"Y".equals(auditStatus)) {  //监察局审核通过
				completedWorkTaskResult = CompletedWorkTaskResultEnum.WORKTASK_PASSAUDIT;
				purchaseDoc.setAuditStatus(PurchaseDocEnum.PURCHASEOFFICE_WAIT);
				purchaseDoc.setWorkflowAuditStatus(WorkFlowModel.AUDIT_STATUS_PASS);
			} else if(null!=auditStatus &&"N".equals(auditStatus)) { //监察局审核不通过
				completedWorkTaskResult = CompletedWorkTaskResultEnum.WORKTASK_NOPASSAUDIT;
				purchaseDoc.setAuditStatus(PurchaseDocEnum.RETURNBACK);  //设置状态为退回状态
				purchaseDoc.setWorkflowAuditStatus(WorkFlowModel.AUDIT_STATUS_NO_PASS);
			}
			purchaseDoc.setWorkFlowTaskId(workFlowTaskId);
			purchaseDocManager.save(purchaseDoc);
			Project project = projectManager.get(purchaseDoc.getProject().getObjId());
			String parentBizId = project.getObjId();
			if(project.getParentId()!= null ){
				parentBizId = project.getParentId();
			}
			completedWorkTaskManager.createCompTaskByPassivity("监察局审核采购文件",CompletedWorkTaskTypeEnum.WORKTASK_TYPE_PURCHASEDOC,opinion,purchaseDoc.getObjId(),parentBizId,"",completedWorkTaskResult);
			bizIds = bizIds+parentBizId+",";
		}
		purchaseDoc.setUser(AuthenticationHelper.getCurrentUser());
		purchaseDoc.setParentBizId(bizIds.substring(0, bizIds.length()-1));
		return purchaseDoc;
	}
	
	
	/** 
	 * Description :  监察局审核澄清文件
	 * Create Date: 2010-9-7上午10:40:23 by yangx  Modified Date: 2010-9-7上午10:40:23 by yangx
	 * @param   ids：招标文件Ids,opinion：意见,auditStatus：是否通过标志,workFlowTaskId：工作流Id
	 * @return  PurchaseDoc 
	 * @Exception   
	 */
	public PurchaseDoc saveClarificationForJcjAudit(String ids,String opinion,String auditStatus,String workFlowTaskId) {
		logger.debug("\n PurchaseDocServiceImpl||saveClarificationForJcjAudit\n");
		String bizIds = "";
		PurchaseDoc purchaseDoc = null;
		String[] objIds = ids.split(",");
		for (String objId : objIds) {
			purchaseDoc = this.purchaseDocManager.get(objId);
			if(null!=auditStatus &&"Y".equals(auditStatus)) {  //采购人确认通过
				purchaseDoc.setAuditStatus(PurchaseDocEnum.PURCHASEOFFICE_WAIT);
				purchaseDoc.setWorkflowAuditStatus(WorkFlowModel.AUDIT_STATUS_PASS);
			} else if(null!=auditStatus &&"N".equals(auditStatus)) { //采购人确认不通过
				purchaseDoc.setAuditStatus(PurchaseDocEnum.RETURNBACK);  //设置状态为退回状态
				purchaseDoc.setWorkflowAuditStatus(WorkFlowModel.AUDIT_STATUS_NO_PASS);
			}
			purchaseDoc.setWorkFlowTaskId(workFlowTaskId);
			purchaseDocManager.save(purchaseDoc);
			Project project = projectManager.get(purchaseDoc.getProject().getObjId());
			String parentBizId = project.getObjId();
			if(project.getParentId()!= null ){
				parentBizId = project.getParentId();
			}
			bizIds = bizIds+parentBizId+",";
		}
		purchaseDoc.setUser(AuthenticationHelper.getCurrentUser());
		purchaseDoc.setParentBizId(bizIds.substring(0, bizIds.length()-1));
		return purchaseDoc;
	}
	/** 
	 * Description :  招标文件论证结论
	 * Create Date: 2010-9-3下午08:09:58 by liuy  Modified Date: 2010-9-3下午08:09:58 by liuy
	 * @param purchaseDocs
	 * @return
	 */
	public PurchaseDoc saveProofOpinion(PurchaseDoc purchaseDocs) {
		logger.debug("\n PurchaseDocServiceImpl||saveProofOpinion\n");
		this.save(purchaseDocs);
		User user=AuthenticationHelper.getCurrentUser();
		purchaseDocs.setUser(user);
		Project project = projectManager.get(purchaseDocs.getProject().getObjId());
		String parentBizId = project.getObjId();
		if(project.getParentId()!= null ){
			parentBizId = project.getParentId();
		}
		purchaseDocs.setParentBizId(parentBizId);
		completedWorkTaskManager.createCompTaskByPassivity("录入论证结果",CompletedWorkTaskTypeEnum.WORKTASK_TYPE_PROOFOPINION,purchaseDocs.getProofOpinion(),purchaseDocs.getObjId(),parentBizId,"",CompletedWorkTaskResultEnum.WORKTASK_SAVE);
		return purchaseDocs;
	}

	 /**
     * 
     * Description : 根据项目获得询价文件 
     * Create Date: 2010-9-8上午10:51:39 by liuke  Modified Date: 2010-9-8上午10:51:39 by liuke
     * @param   
     * @return  
     * @Exception
     */
	public PurchaseDoc getInqpDocByProjectId(String projectId) {
		logger.debug("\n PurchaseDocServiceImpl||getInqpDocByProjectId\n");
		return purchaseDocDaoHibernate.getPurchaseDocByProjectIdAndFileType(projectId, PurchaseDocEnum.INQPDOC);
	}

	/**
	 * 
	 * Description :根据主键获得询价文件  
	 * Create Date: 2010-9-8上午11:10:35 by liuke  Modified Date: 2010-9-8上午11:10:35 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public PurchaseDoc getInqpDocByObjId(String objId) {
		logger.debug("\n PurchaseDocServiceImpl||getInqpDocByObjId\n");
		return purchaseDocDaoHibernate.get(objId);
	}

	/**
	 * 
	 * Description :保存询价文件  
	 * Create Date: 2010-9-8上午11:23:31 by liuke  Modified Date: 2010-9-8上午11:23:31 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public PurchaseDoc saveInqpDoc(PurchaseDoc purchaseDoc) {
		logger.debug("\n PurchaseDocServiceImpl||saveInqpDoc\n");
		ProjProcessRule projProcessRule= projProcessRuleManager.getProjProcessRuleByProjectIdAndCode(purchaseDoc.getProject().getObjId(),SysConfigEnum.NEEDBUYERACKPURCHASEDOC);
		if (projProcessRule!=null&&"false".equals(projProcessRule.getResValue())) {
			purchaseDoc.setAuditStatus(PurchaseDocEnum.PURCHASEOFFICE_WAIT);
			purchaseDoc.setUseStatus(AuditStatusEnum.AUDIT_PASS);//有效状态
		}else{
			purchaseDoc.setAuditStatus(PurchaseDocEnum.FORAUDIT);
		}
		purchaseDocDaoHibernate.save(purchaseDoc);
		if(purchaseDoc.getUser()==null){
			User user=AuthenticationHelper.getCurrentUser();
			purchaseDoc.setUser(user);
		}
		
		Project project = projectManager.get(purchaseDoc.getProject().getObjId());
		String parentBizId = project.getObjId();
		if(project.getParentId()!= null ){
			parentBizId = project.getParentId();
		}
		purchaseDoc.setParentBizId(parentBizId);
		completedWorkTaskManager.createCompTaskByPassivity("提交询价文件",CompletedWorkTaskTypeEnum.WORKTASK_TYPE_PURCHASEDOC,null,purchaseDoc.getObjId(),parentBizId,"",CompletedWorkTaskResultEnum.WORKTASK_SAVE);
		purchaseDoc.setWorkflowAuditStatus(WorkFlowModel.AUDIT_STATUS_PASS);
		return purchaseDoc;
	}

	/**
	 * 
	 * Description : 确认询价文件 
	 * Create Date: 2010-9-8下午03:37:12 by liuke  Modified Date: 2010-9-8下午03:37:12 by liuke
	 * Modified Date: 2011-3-11 15:38 by zhouzhanghe
	 * @param   ids：招标文件Ids,opinion：意见,auditStatus：是否通过标志,workFlowTaskId：工作流Id
	 * @return  PurchaseDoc 
	 * @Exception
	 */
	public PurchaseDoc saveConfirmInqpDoc(String ids,String opinion,String auditStatus,String workFlowTaskId) {
		logger.debug("\n PurchaseDocServiceImpl||saveConfirmInqpDoc\n");
		String bizIds = "";
		PurchaseDoc purchaseDoc = null;
		String[] objIds = ids.split(",");
		for (String objId : objIds) {
			purchaseDoc = this.purchaseDocManager.get(objId);
			String completedWorkTaskResult = "";
			if(null!=auditStatus &&"Y".equals(auditStatus)) {  //采购人确认通过
				completedWorkTaskResult = CompletedWorkTaskResultEnum.WORKTASK_PASSAUDIT;
				purchaseDoc.setWorkflowAuditStatus(WorkFlowModel.AUDIT_STATUS_PASS);
			} else if(null!=auditStatus &&"N".equals(auditStatus)) { //采购人确认不通过
				completedWorkTaskResult = CompletedWorkTaskResultEnum.WORKTASK_NOPASSAUDIT;
				purchaseDoc.setWorkflowAuditStatus(WorkFlowModel.AUDIT_STATUS_NO_PASS);
			}
			purchaseDoc.setWorkFlowTaskId(workFlowTaskId);
			purchaseDocManager.save(purchaseDoc);
			Project project = projectManager.get(purchaseDoc.getProject().getObjId());
			String parentBizId = project.getObjId();
			if(project.getParentId()!= null ){
				parentBizId = project.getParentId();
			}
			completedWorkTaskManager.createCompTaskByPassivity("采购人确认询价文件",CompletedWorkTaskTypeEnum.WORKTASK_TYPE_PURCHASEDOC,opinion,purchaseDoc.getObjId(),parentBizId,"",completedWorkTaskResult);
			bizIds = bizIds+parentBizId+",";
		}
		purchaseDoc.setUser(AuthenticationHelper.getCurrentUser());
		purchaseDoc.setParentBizId(bizIds.substring(0, bizIds.length()-1));
		return purchaseDoc;
	}
	
	/**
	 * 
	 * Description :采购办审核询价文件  
	 * Create Date: 2010-9-8下午04:16:55 by liuke  Modified Date: 2010-9-8下午04:16:55 by liuke
	 * @param   ids：招标文件Ids,opinion：意见,auditStatus：是否通过标志,workFlowTaskId：工作流Id
	 * @return  PurchaseDoc 
	 * @Exception
	 */
	public PurchaseDoc auditInqpDoc(String ids,String opinion,String auditStatus,String workFlowTaskId) {
		logger.debug("\n PurchaseDocServiceImpl||auditInqpDoc\n");
		String bizIds = "";
		PurchaseDoc purchaseDoc = null;
		String[] objIds = ids.split(",");
		for (String objId : objIds) {
			purchaseDoc = this.purchaseDocManager.get(objId);
			String completedWorkTaskResult = "";
			if(null!=auditStatus &&"Y".equals(auditStatus)) {  //采购办审核询价通过
				completedWorkTaskResult = CompletedWorkTaskResultEnum.WORKTASK_PASSAUDIT;
				purchaseDoc.setWorkflowAuditStatus(WorkFlowModel.AUDIT_STATUS_PASS);
			} else if(null!=auditStatus &&"N".equals(auditStatus)) { //采购办审核询价不通过
				completedWorkTaskResult = CompletedWorkTaskResultEnum.WORKTASK_NOPASSAUDIT;
				purchaseDoc.setWorkflowAuditStatus(WorkFlowModel.AUDIT_STATUS_NO_PASS);
			}
			purchaseDoc.setWorkFlowTaskId(workFlowTaskId);
			purchaseDocManager.save(purchaseDoc);
			purchaseDoc.setAuditStatus(auditStatus);//将状态放入采购文件对象中,以便在决策类中使用added by zhouzhanghe at 2011.3.11 15:58
			Project project = projectManager.get(purchaseDoc.getProject().getObjId());
			String parentBizId = project.getObjId();
			if(project.getParentId()!= null ){
				parentBizId = project.getParentId();
			}
			completedWorkTaskManager.createCompTaskByPassivity("采购办审核询价文件",CompletedWorkTaskTypeEnum.WORKTASK_TYPE_PURCHASEDOC,opinion,purchaseDoc.getObjId(),parentBizId,"",completedWorkTaskResult);
			bizIds = bizIds+parentBizId+",";
		}
		purchaseDoc.setUser(AuthenticationHelper.getCurrentUser());
		purchaseDoc.setParentBizId(bizIds.substring(0, bizIds.length()-1));
		return purchaseDoc;
	}

	/**
	 * 
	 * Description :修改询价文件  
	 * Create Date: 2010-9-8上午11:23:31 by liuke  Modified Date: 2010-9-8上午11:23:31 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public PurchaseDoc updateInqpDoc(PurchaseDoc purchaseDoc) {
		logger.debug("\n PurchaseDocServiceImpl||updateInqpDoc\n");
		purchaseDocDaoHibernate.save(purchaseDoc);
		User user=AuthenticationHelper.getCurrentUser();
		purchaseDoc.setUser(user);
		Project project = projectManager.get(purchaseDoc.getProject().getObjId());
		String parentBizId = project.getObjId();
		if(project.getParentId()!= null ){
			parentBizId = project.getParentId();
		}
		purchaseDoc.setParentBizId(parentBizId);
		completedWorkTaskManager.createCompTaskByPassivity("修改询价文件",CompletedWorkTaskTypeEnum.WORKTASK_TYPE_PURCHASEDOC,null,purchaseDoc.getObjId(),parentBizId,"",CompletedWorkTaskResultEnum.WORKTASK_SAVE);
		return purchaseDoc;
	}

	/**
	 * 
	 * Description :监察局审核询价文件  
	 * Create Date: 2010-9-8下午05:56:34 by liuke  Modified Date: 2010-9-8下午05:56:34 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public PurchaseDoc auditForJcjInqpDoc(PurchaseDoc purchaseDoc) {
		logger.debug("\n PurchaseDocServiceImpl||auditForJcjInqpDoc\n");
		purchaseDocDaoHibernate.save(purchaseDoc);
		User user=AuthenticationHelper.getCurrentUser();
		purchaseDoc.setUser(user);
		Project project = projectManager.get(purchaseDoc.getProject().getObjId());
		String parentBizId = project.getObjId();
		if(project.getParentId()!= null ){
			parentBizId = project.getParentId();
		}
		purchaseDoc.setParentBizId(parentBizId);
		String completedWorkTaskResult = "";
		if(null!=purchaseDoc.getConfirmStatus()&&"Y".equals(purchaseDoc.getConfirmStatus())){
			completedWorkTaskResult = CompletedWorkTaskResultEnum.WORKTASK_PASSAUDIT;
			purchaseDoc.setWorkflowAuditStatus(WorkFlowModel.AUDIT_STATUS_PASS);
		}else if(null!=purchaseDoc.getConfirmStatus()&&"N".equals(purchaseDoc.getConfirmStatus())){
			completedWorkTaskResult = CompletedWorkTaskResultEnum.WORKTASK_NOPASSAUDIT;
			purchaseDoc.setWorkflowAuditStatus(WorkFlowModel.AUDIT_STATUS_NO_PASS);
		}
		completedWorkTaskManager.createCompTaskByPassivity("监察局审核询价文件",CompletedWorkTaskTypeEnum.WORKTASK_TYPE_PURCHASEDOC,purchaseDoc.getOpinion(),purchaseDoc.getObjId(),parentBizId,"",completedWorkTaskResult);
		return purchaseDoc;
	}
	
	/**
	 * 
	 * Description :监察局审核询价文件 支持批量 
	 * Create Date: 2010-9-8下午05:56:34 by liuke  Modified Date: 2010-9-8下午05:56:34 by liuke
	 * @param   String ids 审核文件主键,String opinion 审核意见,String auditStatus 审核状态,String workFlowTaskId 工作流ID
	 * @return  PurchaseDoc 文件对象
	 * @Exception
	 */
	public PurchaseDoc auditForJcjInqpDoc(String ids,String opinion,String auditStatus,String workFlowTaskId) {
		logger.debug("\n PurchaseDocServiceImpl||auditForJcjInqpDoc\n");
		String bizIds = "";
		PurchaseDoc purchaseDoc = null;
		String[] objIds = ids.split(",");
		for (String objId : objIds) {
			purchaseDoc = this.purchaseDocManager.get(objId);
			String completedWorkTaskResult = "";
			Double sumPrice = this.getTotalPriceByProject(purchaseDoc.getProject().getObjId());
			if(null!=auditStatus &&"Y".equals(auditStatus)) {  //采购人确认通过
				completedWorkTaskResult = CompletedWorkTaskResultEnum.WORKTASK_PASSAUDIT;
				if(PurchaseDocEnum.FORAUDIT.equals(purchaseDoc.getAuditStatus())){//表示采购人确认操作
					if(sumPrice>=(PurchaseDocEnum.SUMPRICE)) { //项目金额大于50万元,监察局审核
						purchaseDoc.setAuditStatus(PurchaseDocEnum.SUPERVISALOFFICE_WAIT);
					}else {   //跳过监察局审核，直接由采购办审核
						purchaseDoc.setAuditStatus(PurchaseDocEnum.PURCHASEOFFICE_WAIT);
					}
					purchaseDoc.setWorkflowAuditStatus(WorkFlowModel.AUDIT_STATUS_PASS);
				} else if(PurchaseDocEnum.SUPERVISALOFFICE_WAIT.equals(purchaseDoc.getAuditStatus())) {//监察局审核
					purchaseDoc.setAuditStatus(PurchaseDocEnum.PURCHASEOFFICE_WAIT);
					purchaseDoc.setWorkflowAuditStatus(WorkFlowModel.AUDIT_STATUS_PASS);
				} else if(PurchaseDocEnum.PURCHASEOFFICE_WAIT.equals(purchaseDoc.getAuditStatus())) {//采购办审核
					purchaseDoc.setAuditStatus(PurchaseDocEnum.PURCHASEOFFICEPASS);
					purchaseDoc.setWorkflowAuditStatus(WorkFlowModel.AUDIT_STATUS_PASS);
				}
			} else if(null!=auditStatus &&"N".equals(auditStatus)) { //采购人确认不通过
				completedWorkTaskResult = CompletedWorkTaskResultEnum.WORKTASK_NOPASSAUDIT;
				purchaseDoc.setAuditStatus(PurchaseDocEnum.RETURNBACK);  //设置状态为退回状态
				purchaseDoc.setWorkflowAuditStatus(WorkFlowModel.AUDIT_STATUS_NO_PASS);
			}
			purchaseDoc.setWorkFlowTaskId(workFlowTaskId);
			purchaseDocManager.save(purchaseDoc);
			Project project = projectManager.get(purchaseDoc.getProject().getObjId());
			String parentBizId = project.getObjId();
			if(project.getParentId()!= null ){
				parentBizId = project.getParentId();
			}
			completedWorkTaskManager.createCompTaskByPassivity("监察局审核询价文件",CompletedWorkTaskTypeEnum.WORKTASK_TYPE_PURCHASEDOC,opinion,purchaseDoc.getObjId(),parentBizId,"",completedWorkTaskResult);
			bizIds = bizIds+parentBizId+",";
		}
		purchaseDoc.setUser(AuthenticationHelper.getCurrentUser());
		purchaseDoc.setParentBizId(bizIds.substring(0, bizIds.length()-1));
		return purchaseDoc;
	}


	/**
	 * FuncName: getPurchaseFileDTOByProjCode
	 * Description :根据项目Code获得PurchaseFileDTO对象
	 * @param 
	 * @param projCode
	 * @return PurchaseFileDTO
	 * @author: liuke
	 * @Create Date:2011-5-9  下午03:37:43
	 * @Modifier: liuke
	 * @Modified Date:2011-5-9  下午03:37:43
	 */
	public PurchaseFileDTO saveAndGetPurchaseFileDTOByProjCode(String projCode) throws Exception {
		return purchaseDocManager.saveAndGetPurchaseFileDTOByProjCode(projCode);
	}

	/**
	 * FuncName: saveUpdatePurchaseFile
	 * Description :用于远程保存采购文件
	 * @param projId 项目主键
	 * @param purchaseFileObjId 采购文件附件主键
	 * @param projectDataItemList 待更新的项目数据
	 * @param congruousFactorList 待更新的指标数据
	 * @author: zhouzhanghe
	 * @Create Date:2011-7-5 9:27
	 */
	public void saveUpdatePurchaseFile(String projId, String purchaseFileObjId, List<DataItem> projectDataItemList,
			List<Map> congruousFactorList) throws Exception {
		/*更新项目数据*/
		Project project = projectManager.get(projId);
		PurchaseDoc purchaseDoc = get(purchaseFileObjId);
		if(EbuyMethodEnum.INQUIRY.equals(project.getEbuyMethod())){  //如果是询价文件，则需要修改文件类型
			purchaseDoc.setFileType(PurchaseDocEnum.INQPDOC);
		}
		
		Agency agency = userApiService.getAgentMessageByObjId(project.getAgencies().getObjId());
		for(DataItem dataItem : projectDataItemList){
			if("dataItemName".equals(dataItem.getId())){//更新项目名称
				project.setProjName(dataItem.getValue());
			}else if("tenderFeeNumber".equals(dataItem.getId())){//更新标书费金额
				purchaseDoc.setFilePrice(dataItem.getValue());
			}else if("openBankOrg".equals(dataItem.getId())){//更新开户单位（缴纳）
				agency.setOpenBank(dataItem.getValue());
			}else if("openBankAccount".equals(dataItem.getId())){//更新账号（缴纳）
				agency.setOpenAccount(dataItem.getValue());
			}
		}
		projectManager.save(project);
		savePurchaseDoc(purchaseDoc);
		agencyManager.save(agency);
		
		/*更新指标数据*/
		congruousFactorService.saveCongruousFactor(congruousFactorList);
	}
	
	/**
	 * FuncName: getAttachment
	 * Description :  根据项目编号获取招标文件附件对象
	 * @param 
	 * @return Attachment
	 * @author: shenjz
	 * @Create Date:2011-8-18  下午02:55:44
	 * @Modifier: shenjz
	 * @Modified Date:2011-8-18  下午02:55:44
	 */
	public Attachment getPurchaseDocAttachment(String projCode)throws Exception{
		PurchaseDoc doc =  purchaseDocDaoHibernate.getPurchaseDocByProjCode(projCode);
		Attachment attachment = null;
		if(doc != null && doc.getObjId() != null){
//			QueryObject<Attachment> query = new QueryObjectBase<Attachment>();
//			query.setEntityClass(Attachment.class);
//			query.getQueryParam().and(new QueryParam("relationId",QueryParam.OPERATOR_EQ,doc.getAttachRelaId()));
//			List<Attachment> attachmentList = attachmentDaoHibernate.findByQuery(query);
			//modify by yangx attachmentManager中已经提供了该方法不需要在写
			List<Attachment> attachmentList = attachmentManager.getAttachmentsByRealID(doc.getAttachRelaId());
			attachment = attachmentList.get(0);
		}
		return attachment;
	}
	
	/** 
	 * FuncName : removeFile
	 * Description :  删除招标文件
	 * Create Date: 2011-9-21上午11:29:07 by yangx  Modified Date: 2011-9-21上午11:29:07 by yangx
	 * @param   fileId：文件Id
	 * @return  void
	 * @Exception   
	 */
	public void removePurchaseDocFile(String fileId,String attaPat)throws Exception{
		this.removePurchaseDocAtt(fileId, attaPat);//删除附件
		PurchaseDoc doc =  purchaseDocManager.get(fileId);//获取文件
		List<Attachment> attachmentList = attachmentManager.getAttachmentsByRealID(doc.getAttachRelaId());
		if (attachmentList!=null&&attachmentList.size()>0) {
			for (Attachment attachment:attachmentList) {
				 FileUtils.deleteAllFile(attaPat+attachment.getPath(), attachment.getFileName());//删除文件
				 attachmentManager.remove(attachment.getObjId(),Attachment.class);
//				 FileUtil.delete(filePath);//删除文件
			}
		}
		purchaseDocManager.remove(fileId,PurchaseDoc.class);
	}
	
	
	/** 
	 * FuncName : removePurchaseDocAtt
	 * Description :  删除招标文件附件
	 * Create Date: 2011-9-21上午11:38:53 by yangx  Modified Date: 2011-9-21上午11:38:53 by yangx
	 * @param   fileId：文件Id
	 * @return  
	 * @Exception   
	 */
	private void removePurchaseDocAtt(String fileId,String attaPat){
		List<PurchaseDocAtt> purchaseDocAttList = purchaseDocAttManager.getPurchaseDocAttByPurchaseDocId(fileId);
		if (purchaseDocAttList!=null&&purchaseDocAttList.size()>0) {
			for (PurchaseDocAtt purchaseDocAtt:purchaseDocAttList) {
				List<Attachment> attachmentList = attachmentManager.getAttachmentsByRealID(purchaseDocAtt.getAttRaId());
				if (attachmentList!=null&&attachmentList.size()>0) {
					for (Attachment attachment:attachmentList) {
						 String filePath=attaPat+attachment.getPath()+File.separator+attachment.getFileName();//换回绝对路径
						 FileUtil.delAllFile(filePath);//删除文件
						 attachmentManager.remove(attachment.getObjId(),Attachment.class);
					}
				}
				purchaseDocAttManager.remove(purchaseDocAtt.getObjId(),PurchaseDocAtt.class);//删除对象
			}
		}
	}

	/**
	 * 根据采购文件id返回文件物理地址和文件名
	 * @param returnFilePath 文件物理地址
	 * @param returnFileViewName 用于显示的文件名
	 * @param paramPurchaseDocId 采购文件主键
	 * @author zhouzhanghe
	 * @created date 2011-11-18 14:21
	 */
	public void getPurchaseDocFilePathAndNameByPurchaseDocId(StringBuilder returnFilePath, StringBuilder returnFileViewName, String paramPurchaseDocId) {
		if(StringUtils.empty(paramPurchaseDocId)){
			throw new EsException(EsExceptionEnum.ES_ERROR_NULLKEY);
		}
		PurchaseDoc purchaseDoc = get(paramPurchaseDocId);
		if(purchaseDoc == null){
			throw new EsException(EsExceptionEnum.ES_ERROR_NULLOBJECT);
		}
		if(StringUtils.empty(purchaseDoc.getAttachRelaId())){
			throw new EsException(EsExceptionEnum.ES_ERROR_NULLKEY);
		}
		// modify by shenjz at 2011-07-20 10:25,更改为通过业务对象获取到关联ID,再获取相应的附件对象
		Attachment attachment= null;
		if (null != purchaseDoc) {
			List<Attachment> attachmentList = attachmentService.getAttachmentsByRealID(purchaseDoc.getAttachRelaId());
			if(attachmentList != null && attachmentList.size() != 1){
				throw new EsException(EsExceptionEnum.ES_ERROR_EXPECTEDFORTHEONE);
			}
			attachment = attachmentList.get(0);
		}
		
		/*
		 * 本应该取attachment.getViewName值作为viewName的,但在存储时有问题,所以下面取文件名作为viewName
		 * returnFileViewName.replace(0, returnFileViewName.length(), attachment.getViewName());
		 * zhouzhanghe at 2011-11-18 15:31
		 * */
        String path = messageSource.getMessage("epp.projectAttaPath")+attachment.getPath()+File.separator;
        String filePath=messageSource.getMessage("epp.projectAttaPath")+attachment.getPath()+File.separator;//换回绝对路径
        String[] l = FileUtil.listFiles(path);
        for (int i = 0; i < l.length; i++) {
         	if(l[i].indexOf("BiddingDocument.xml")!=-1){
        		File file2 = new File(filePath+"BiddingDocument.xml");
        		SAXReader saxReader = new SAXReader();
        		Document document;
				try {
					document = saxReader.read(file2);
	        		Element element =  document.getRootElement();
	        		List<Element> sectionElements = element.elements("section");
	        		for (Iterator iterator = sectionElements.iterator(); iterator
	    					.hasNext();) {
	    				Element sectionElement = (Element) iterator.next();
	    				
	    				returnFileViewName.replace(0, returnFileViewName.length(), sectionElement.attribute("fileName").getText());
	    				/*如果docId本身带了扩展名*/
	    				if(sectionElement.attribute("docId").getText().indexOf(".") != -1)
	    					returnFilePath.replace(0, returnFilePath.length(), filePath.concat(sectionElement.attribute("docId").getText()));
	    				else
	    					returnFilePath.replace(0, returnFilePath.length(), filePath.concat(sectionElement.attribute("docId").getText().concat(".").concat(com.gpcsoft.epp.common.utils.FileUtils.getFileExtentionName(sectionElement.attribute("fileName").getText()))));
	    			}
				} catch (DocumentException e) {
					e.printStackTrace();
					break;
				}				
        		break;
         	}
         }
        //add by chenhj 20111208 修改招标文件文件，获取其中文名称
		if(returnFileViewName.length()>0){
			String exp=FileUtils.getFileExtentionName(returnFileViewName.toString());
			returnFileViewName=new StringBuilder();
			int ind=attachment.getViewName().indexOf(".");
			if(ind>-1){
				returnFileViewName.append(attachment.getViewName().substring(0, ind)).append(".").append(exp);
			}else{
				returnFileViewName.append(attachment.getViewName()).append(".").append(exp);
			}
		}
         if(returnFilePath == null || StringUtils.empty(returnFilePath.toString()))
	         for (int i = 0; i < l.length; i++) {
        		if(l[i].equals("PurchaseFile.doc")||l[i].equals("PurchaseFile.docx")||l[i].equals("PurchaseFile.pdf")){
        			returnFileViewName.replace(0, returnFileViewName.length(), l[i]);
        			returnFilePath.replace(0, returnFilePath.length(), l[i]);
   	           		break;
        		}else{
       	           	if(l[i].endsWith(".doc") || l[i].endsWith(".docx") || l[i].equals(".pdf") || l[i].endsWith("doc") || l[i].endsWith("docx") || l[i].equals("pdf")){
            			returnFileViewName.replace(0, returnFileViewName.length(), l[i]);
            			returnFilePath.replace(0, returnFilePath.length(), filePath.concat(returnFileViewName.toString()));
            			break;
       	           	}
        		}
	         }
	}
}
