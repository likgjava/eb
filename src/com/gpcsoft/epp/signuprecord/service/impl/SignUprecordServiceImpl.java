package com.gpcsoft.epp.signuprecord.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.organization.manager.OrgInfoManager;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryObjectBase;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.BeanUtils;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.common.domain.AuditStatusEnum;
import com.gpcsoft.epp.common.domain.EsExceptionEnum;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.common.service.UserApiService;
import com.gpcsoft.epp.project.domain.ProjProcessStatusEnum;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.manager.ProjectManager;
import com.gpcsoft.epp.purchasedoc.domain.PurchaseDoc;
import com.gpcsoft.epp.purchasedoc.manager.impl.PurchaseDocManagerImpl;
import com.gpcsoft.epp.signuprecord.dao.SignUprecordDao;
import com.gpcsoft.epp.signuprecord.domain.SignUpMRespone;
import com.gpcsoft.epp.signuprecord.domain.SignUpRespone;
import com.gpcsoft.epp.signuprecord.domain.SignUprecord;
import com.gpcsoft.epp.signuprecord.manager.SignUpMResponeManager;
import com.gpcsoft.epp.signuprecord.manager.SignUpResponeManager;
import com.gpcsoft.epp.signuprecord.manager.SignUprecordManager;
import com.gpcsoft.epp.signuprecord.service.SignUprecordService;
import com.gpcsoft.epp.worktask.domain.CompletedWorkTaskResultEnum;
import com.gpcsoft.epp.worktask.domain.CompletedWorkTaskTypeEnum;
import com.gpcsoft.epp.worktask.domain.CompletedWorkTaskURLEnum;
import com.gpcsoft.epp.worktask.manager.CompletedWorkTaskManager;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.plugin.wflow.domain.WorkFlowModel;
import com.gpcsoft.pubservice.application.message.domain.Message;
import com.gpcsoft.pubservice.application.message.enumeration.EnumMessage;
import com.gpcsoft.pubservice.application.message.manager.MessageManager;
import com.gpcsoft.pubservice.utils.MessageUtil;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.auth.manager.EmployeeManager;
import com.gpcsoft.srplatform.common.assist.service.FreeMarkerService;
import com.gpcsoft.srplatform.mail.domain.EmailMessage;
import com.gpcsoft.srplatform.mail.service.MailService;

@Service 
public class SignUprecordServiceImpl extends BaseGenericServiceImpl<SignUprecord> implements SignUprecordService {

	@Autowired(required=true) @Qualifier("signUprecordManagerImpl")
	public SignUprecordManager signUprecordManager;
	
	@Autowired(required=true) @Qualifier("projectManagerImpl")
	public ProjectManager projectManager;
	
	@Autowired(required=true) @Qualifier("completedWorkTaskManagerImpl")
	public CompletedWorkTaskManager completedWorkTaskManager;
	
	@Autowired(required=true) @Qualifier("signUpResponeManagerImpl")
	public SignUpResponeManager signUpResponeManager;
	
	@Autowired(required=true) @Qualifier("signUpMResponeManagerImpl")
	public SignUpMResponeManager signUpMResponeManager;
	
	@Autowired(required=true)  @Qualifier("signUprecordDaoHibernate")
	public SignUprecordDao signUprecordDaoHibernate;

	@Autowired(required=true) @Qualifier("freeMarkerServiceImpl")
	public FreeMarkerService freeMarkerService;
	
	@Autowired(required=true) @Qualifier("messageManagerImpl")
	public MessageManager messageManager;
	
	@Autowired(required=true) @Qualifier("attachmentMailServiceImpl")
	public MailService mailService;

	@Autowired(required=true) @Qualifier("userApiServiceImpl")
	public UserApiService userApiService;
	
	
	@Autowired(required=true) @Qualifier("orgInfoManagerImpl")
	private OrgInfoManager orgInfoManager;
	
	@Autowired(required=true) @Qualifier("employeeManagerImpl")
	private EmployeeManager employeeManager;
	
	@Autowired(required=true) @Qualifier("purchaseDocManagerImpl")
	private PurchaseDocManagerImpl purchaseDocManager;
	
	/** 
	 * Description :  更改报名状态为支付状态
	 * Create Date: 2011-7-21下午01:43:12 by sunl  Modified Date: 2011-7-21下午01:43:12 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean updatePayStatus(String docId,String empId, String status) throws Exception {
		//根据采购文件id获取采购文件
		PurchaseDoc doc = purchaseDocManager.get(docId);
		
		//根据采购文件获取项目ID
		String projId = doc.getProject().getObjId();
		
		//获取供应商ID
		OrgInfo supplier = orgInfoManager.getOrgInfoByCompany(employeeManager.get(empId).getCompany().getObjId());
		String supplierId = supplier == null ? "" : supplier.getObjId();
		String supplierName = supplier == null ? "" : supplier.getOrgName();
		
		//更新报名状态
		boolean result = signUprecordDaoHibernate.updatePayStatus(projId, supplierId, status);
		
		//发站内信
		Map<String, Object> templateMap = new HashMap<String, Object>();
		templateMap.put("projName", doc.getProject().getProjName());
		templateMap.put("supplierName", supplierName);
		
		//获取经办人发送站内信
		Set<User> jbusers = doc.getProject().getManager().getUsers();
		String userids[]= new String[jbusers.size()];
		int i=0;
		for (User user : jbusers) {
			userids[i] = user.getObjId();
			i++;
		}
		
		MessageUtil.sendMessageSystem(userids,"招标文件费用已支付","",messageSource.getMessage(EnumMessage.DOC_PAY),templateMap,false);
		
		return result;
	}
	
	/**
	 * 根据项目ID和供应商ID查询供应商的报名信息
	 */
	public SignUprecord getSignUprecordBySupplierId(String projectId, User supplierUser) {
		logger.debug("\nes SignUprecordServiceImpl||getSignUprecordBySupplierId\n");
		QueryObject<SignUprecord> queryObject = new QueryObjectBase<SignUprecord>();
		queryObject.setEntityClass(SignUprecord.class);
		queryObject.getQueryParam().and(new QueryParam("project.objId",QueryParam.OPERATOR_EQ,projectId));
		queryObject.getQueryParam().and(new QueryParam("supplier.objId",QueryParam.OPERATOR_EQ,supplierUser.getOrgInfo().getObjId()));
		return signUprecordManager.getSignUprecord(queryObject);
		
	}

	/** 
	 * Description :  根据项目ID查询供应商的报名信息[采购人]
	 * Create Date: 2010-6-29下午04:15:56 by yangx  Modified Date: 2010-6-29下午04:15:56 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 * @Modifier zhouzhanghe(改为调用Hibernate的方法)
	 * @Modified Date: 2011-7-14 16:18
	 */
	public List<SignUprecord> getSignupRecordList(String projectId){
		logger.debug("\n SignUprecordServiceImpl||getSignupRecordList\n");
		/*QueryObject<SignUprecord> queryObject = new QueryObjectBase<SignUprecord>();
		queryObject.setEntityClass(SignUprecord.class);
		queryObject.getQueryParam().and(new QueryParam("project.objId",QueryParam.OPERATOR_EQ,projectId));
		return signUprecordManager.findByQuery(queryObject);*/
		return signUprecordDaoHibernate.getSignUprecordByprojectId(projectId);
	}

	/** 
	 * Description :  获取供应商报名信息列表 
	 * Create Date: 2010-6-23下午03:27:02 by yangx  Modified Date: 2010-6-23下午03:27:02 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Page<SignUprecord> searchSignUprecordByQueryObject(Page<SignUprecord> page,QueryObject queryObject){
		logger.debug("\nes SignUprecordServiceImpl||searchSignUprecordByQueryObject\n");
		return signUprecordManager.searchSignUprecordByQueryObject(page,queryObject);
	}
	/** 
	 * Description :  根据查询条件统计对应的报名信息数据
	 * Create Date: 2010-7-10上午10:57:34 by yangx  Modified Date: 2010-7-10上午10:57:34 by yangx
	 * @param   queryObject[auditStatus:审核状态;managerID:项目负责人]
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public BigDecimal getSignUprecordTotalByQueryObject(QueryObject queryObject){
		logger.debug("\nes SignUprecordServiceImpl||getSignUprecordTotalByQueryObject\n");
		return signUprecordManager.getSignUprecordTotalByQueryObject(queryObject);
	}
	
	/**
	 * 
	 * Description :保存供应商报名和报名响应 
	 * Create Date: 2010-8-13上午11:52:12 by liuke  Modified Date: 2010-8-13上午11:52:12 by liuke
	 * @param   
	 * @return  
	 * @throws Exception 
	 * @Exception
	 */
	public SignUprecord saveSignUprecordAndSignUpRespone(SignUprecord signUprecord,List<SignUpRespone> signUpResponeList,String subPrjIds) throws Exception {
		logger.debug("\nes SignUprecordServiceImpl||saveSignUprecordAndSignUpRespone\n");
		String projectId = signUprecord.getProject().getObjId();//获得项目Id
		if(subPrjIds==null||"".equals(subPrjIds)){
			SignUprecord oldSignUprecord  = signUprecordDaoHibernate.getSignUprecordByprojectIdAndSupplierId(projectId, signUprecord.getSupplier().getObjId());
			if(oldSignUprecord!=null){ //如果已经有报名记录，则修改已有的记录
//				BeanUtils.copyPropertiesFilterEmpty(oldSignUprecord, signUprecord);
				org.apache.commons.beanutils.BeanUtils.copyProperties(oldSignUprecord, signUprecord);
				signUprecordManager.save(oldSignUprecord);
			}else{
				signUprecordManager.save(signUprecord);
			}
		
			if(signUpResponeList.size()>0){
				for(SignUpRespone signUpRespone:signUpResponeList){
					String signUpResponeId = signUpRespone.getObjId();
					if("".equals(signUpResponeId)){
						signUpRespone.setObjId(null);
					}
					signUpResponeManager.save(signUpRespone); //保存报名响应对象
					if(null==signUpResponeId||"".equals(signUpResponeId)){//保存中间表对象
						SignUpMRespone  signupmrespone = new SignUpMRespone();
						signupmrespone.setSignUprecord(signUprecord);
						signupmrespone.setSignUpRespone(signUpRespone);
						signupmrespone.setProject(signUprecord.getProject());
						signUpMResponeManager.save(signupmrespone);
					}
					
					
				}
			}
		}else{
			String[]subPrjId = subPrjIds.split(",");
			for (int i = 0; i < subPrjId.length; i++) {
				SignUprecord  signUprecord2  = new SignUprecord();
//				BeanUtils.copyPropertiesFilterEmpty(signUprecord2, signUprecord);
				org.apache.commons.beanutils.BeanUtils.copyProperties(signUprecord2, signUprecord);
				signUprecord2.setObjId(null);
				Project subPrj = projectManager.get(subPrjId[i]);
				projectId = subPrj.getParentId();//获取项目Id
				signUprecord2.setProject(subPrj);
				SignUprecord oldSignUprecord  = signUprecordDaoHibernate.getSignUprecordByprojectIdAndSupplierId(subPrj.getObjId(), signUprecord2.getSupplier().getObjId());
				if(oldSignUprecord!=null){ //如果已经有报名记录，则修改已有的记录
					BeanUtils.copyPropertiesFilterEmpty(oldSignUprecord, signUprecord2);
					signUprecordManager.save(oldSignUprecord);
				}else{
					signUprecordManager.save(signUprecord2);
				}
				if(signUpResponeList.size()>0){
					for(SignUpRespone signUpRespone:signUpResponeList){
						String signUpResponeId = signUpRespone.getObjId();
						if("".equals(signUpResponeId)){
							signUpRespone.setObjId(null);
						}
						signUpResponeManager.save(signUpRespone); //保存报名响应对象
						if(null==signUpResponeId||"".equals(signUpResponeId)){//保存中间表对象
							SignUpMRespone  signupmrespone = new SignUpMRespone();
							signupmrespone.setSignUprecord(signUprecord2);
							signupmrespone.setSignUpRespone(signUpRespone);
							signupmrespone.setProject(signUprecord.getProject());
							signUpMResponeManager.save(signupmrespone);
						}
					}
				}
			}
		}
		/** 保存项目实施状态 */
		//projectManager.saveProjProcessStatus(signUprecord.getProject().getObjId(), ProjProcessStatusEnum.SUPPLIERS_APPLY); //已将改变状态操作移至决策类里  noted by zhouzhanghe at 2011.3.10 14:40
		/** 保存已办任务 */
		String taskName="";
		String viewResultURL = "";
		String processResult = "";
		String opinion=null;
		if(signUprecord.getAuditStatus()!=null&&(AuditStatusEnum.WAIT_AUDIT).equals(signUprecord.getAuditStatus())){
			taskName="保存投标人报名";
			viewResultURL = CompletedWorkTaskURLEnum.WORKTASK_URL_SIGNUPRECORD_SAVE;
			processResult = CompletedWorkTaskResultEnum.WORKTASK_SAVE;
		}else if(signUprecord.getAuditStatus()!=null&&(AuditStatusEnum.AUDIT_PASS).equals(signUprecord.getAuditStatus())){
			taskName="审核投标人报名";
			opinion=signUprecord.getOpinion();
			viewResultURL = CompletedWorkTaskURLEnum.WORKTASK_URL_SIGNUPRECORD_AUDIT;
			processResult = CompletedWorkTaskResultEnum.WORKTASK_PASSAUDIT;
		}else if(signUprecord.getAuditStatus()!=null&&(AuditStatusEnum.AUDIT_NO_PASS).equals(signUprecord.getAuditStatus())){
			taskName="审核投标人报名";
			opinion=signUprecord.getOpinion();
			viewResultURL = CompletedWorkTaskURLEnum.WORKTASK_URL_SIGNUPRECORD_AUDIT;
			processResult = CompletedWorkTaskResultEnum.WORKTASK_NOPASSAUDIT;
		}
		completedWorkTaskManager.createCompTaskByPassivity(taskName,CompletedWorkTaskTypeEnum.WORKTASK_TYPE_SIGNUPRECORD,opinion,signUprecord.getObjId(),signUprecord.getProject().getObjId(),viewResultURL,processResult);
		signUprecord.setParentBizId(projectId);
		if(signUprecord.getUser()==null){
			signUprecord.setUser(AuthenticationHelper.getCurrentUser());
		}
		signUprecord.setWorkflowAuditStatus(WorkFlowModel.AUDIT_STATUS_PASS);
		return signUprecord;
	}

	/**
	 * 
	 * Description : 抽取一定数量的供应商 
	 * Create Date: 2010-8-17上午11:11:32 by liuke  Modified Date: 2010-8-17上午11:11:32 by liuke
	 * @param   String num 抽取供应商个数  List<OrgInfo> supplierList 所有供应商列表
	 * @return  
	 * @Exception
	 */
	public List<OrgInfo> getSupplierList(List<OrgInfo> supplierList, String num) {
		logger.debug("\nes SignUprecordServiceImpl||getSupplierList\n");
		if(null==num||"".equals(num)){
			num = "0";
		}
		int number = Integer.valueOf(num);
		/** 判断集合、抽取个数是否为空 */
		if(supplierList==null||supplierList.size()<1||number<1)return null;
		Random rd = new Random();
		List<OrgInfo> listOrgInfo = new ArrayList<OrgInfo>();
		if(1==number){//如果只抽取一个供应商
			listOrgInfo.add(supplierList.get(rd.nextInt(supplierList.size())));
			return listOrgInfo;
		}
		listOrgInfo.add(supplierList.get(rd.nextInt(supplierList.size())));
		for(;true;){
			OrgInfo  orgInfo = supplierList.get(rd.nextInt(supplierList.size()));//随机抽取一个供应商
			boolean flag = true;  //设置一个标识是否没有重复
			for(int i=0;i<listOrgInfo.size();i++){
				if(orgInfo.getObjId().equals(listOrgInfo.get(i).getObjId())){//如果有重复，设置标识为false
					flag=false;
				}
			}
			if(flag){//如果没有重复，添加入列表
				listOrgInfo.add(orgInfo);
			}
			if(listOrgInfo.size()>=number||listOrgInfo.size()==supplierList.size()){//如果集合中元素数量大于number,跳出循环
				break;
			}
		}
		return listOrgInfo;
	}

	 /**
	 * 
	 * Description :根据项目得到报名信息  
	 * Create Date: 2010-9-1上午11:22:49 by shenjianzhuang  Modified Date: 2010-9-1上午11:22:49 by shenjianzhuang
	 * @param projectId
	 * @return
	 * @return  List<SignUprecord> 
	 * @Exception 
	 */
	public List<SignUprecord> getSignUprecordByprojectId(String projectId) {
		logger.debug("\nes SignUprecordServiceImpl||getSignUprecordByprojectId\n");
		return signUprecordDaoHibernate.getSignUprecordByprojectId(projectId);
	}

	
	/**
	 * 
	 * Description : 修改供应商报名和报名响应  
	 * Create Date: 2010-9-6下午02:44:42 by liuke  Modified Date: 2010-9-6下午02:44:42 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public SignUprecord updateSignUprecordAndSignUpRespone(SignUprecord signUprecord, List<SignUpRespone> signUpResponeList) {
		logger.debug("\nes SignUprecordServiceImpl||updateSignUprecordAndSignUpRespone\n");
		signUprecordManager.save(signUprecord);
		if(signUpResponeList.size()>0){
			for(SignUpRespone signUpRespone:signUpResponeList){
				String signUpResponeId = signUpRespone.getObjId();
				if ("".equals(signUpResponeId)) {
					signUpRespone.setObjId(null);
				}
				signUpResponeManager.save(signUpRespone); //保存报名响应对象
				if (null==signUpResponeId||"".equals(signUpResponeId)) {//保存中间表对象
					SignUpMRespone  signupmrespone = new SignUpMRespone();
					signupmrespone.setSignUprecord(signUprecord);
					signupmrespone.setSignUpRespone(signUpRespone);
					signupmrespone.setProject(signUprecord.getProject());
					signUpMResponeManager.save(signupmrespone);
				}
			}
		}
		/** 保存项目实施状态 */
		projectManager.saveProjProcessStatus(signUprecord.getProject().getObjId(), ProjProcessStatusEnum.SUPPLIERS_APPLY);
		/** 保存已办任务 */
		String taskName="";
		String viewResultURL = "";
		String processResult = "";
		String opinion=null;
		if(signUprecord.getAuditStatus()!=null&&(AuditStatusEnum.WAIT_AUDIT).equals(signUprecord.getAuditStatus())){
			taskName="保存供应商报名";
			viewResultURL = CompletedWorkTaskURLEnum.WORKTASK_URL_SIGNUPRECORD_SAVE;
			processResult = CompletedWorkTaskResultEnum.WORKTASK_SAVE;
			signUprecord.setWorkflowAuditStatus(WorkFlowModel.AUDIT_STATUS_PASS);
		}else if(signUprecord.getAuditStatus()!=null&&(AuditStatusEnum.AUDIT_PASS).equals(signUprecord.getAuditStatus())){
			taskName="审核供应商报名";
			opinion=signUprecord.getOpinion();
			viewResultURL = CompletedWorkTaskURLEnum.WORKTASK_URL_SIGNUPRECORD_AUDIT;
			processResult = CompletedWorkTaskResultEnum.WORKTASK_PASSAUDIT;
		}else if(signUprecord.getAuditStatus()!=null&&(AuditStatusEnum.AUDIT_NO_PASS).equals(signUprecord.getAuditStatus())){
			taskName="审核供应商报名";
			opinion=signUprecord.getOpinion();
			viewResultURL = CompletedWorkTaskURLEnum.WORKTASK_URL_SIGNUPRECORD_AUDIT;
			processResult = CompletedWorkTaskResultEnum.WORKTASK_NOPASSAUDIT;
		}
		completedWorkTaskManager.createCompTaskByPassivity(taskName,CompletedWorkTaskTypeEnum.WORKTASK_TYPE_SIGNUPRECORD,opinion,signUprecord.getObjId(),signUprecord.getProject().getObjId(),viewResultURL,processResult);
		signUprecord.setParentBizId(signUprecord.getProject().getObjId());
		signUprecord.setUser(AuthenticationHelper.getCurrentUser());
		return signUprecord;
	}

	
	/**
	 * 
	 * Description :根据主键获得报名信息对象 
	 * Create Date: 2010-9-7上午09:34:28 by liuke  Modified Date: 2010-9-7上午09:34:28 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public SignUprecord getSignUprecordByobjId(String objId) {
		logger.debug("\nes SignUprecordServiceImpl||getSignUprecordByobjId\n");
		return signUprecordDaoHibernate.get(objId);
	}

	/**
	 * 
	 * Description : 审核供应商报名
	 * Create Date: 2010-9-7上午09:40:47 by liuke  Modified Date: 2010-9-7上午09:40:47 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public SignUprecord auditSignUprecord(String objIds,String opinion,String auditStatus) {
		logger.debug("\nes SignUprecordServiceImpl||auditSignUprecord\n");
		/** modify by yangx 支持批量审核 */
		String temp = objIds.substring(0, objIds.length()-1);
		String newObjIds ="'" + temp.replace(",", "','")+"'";
		signUprecordDaoHibernate.saveAuditSignUprecord(newObjIds, auditStatus);
		String[] signUprecordIds = objIds.split(",");
		SignUprecord signUprecord = signUprecordManager.get(signUprecordIds[0]);//获得第一个报名对象，用于工作计划返回
		//signUprecordDaoHibernate.saveAuditSignUprecord(newObjIds, auditStatus);  //审核供应商报名 //已将改变状态操作移至决策类里  noted by zhouzhanghe at 2011.3.10 14:40
		
		/** 保存项目实施状态 */
		//projectManager.saveProjProcessStatus(signUprecord.getProject().getObjId(), ProjProcessStatusEnum.SUPPLIERS_APPLY); //已将改变状态操作移至决策类里  noted by zhouzhanghe at 2011.3.10 14:40
		/** 保存已办任务 */
		String taskName="";
		String viewResultURL = "";
		String processResult = "";
		if(signUprecord.getAuditStatus()!=null&&(AuditStatusEnum.WAIT_AUDIT).equals(signUprecord.getAuditStatus())){
			taskName="保存供应商报名";
			viewResultURL = CompletedWorkTaskURLEnum.WORKTASK_URL_SIGNUPRECORD_SAVE;
			processResult = CompletedWorkTaskResultEnum.WORKTASK_SAVE;
			signUprecord.setWorkflowAuditStatus(WorkFlowModel.AUDIT_STATUS_PASS);
		}else if(signUprecord.getAuditStatus()!=null&&(AuditStatusEnum.AUDIT_PASS).equals(signUprecord.getAuditStatus())){
			taskName="审核供应商报名";
			opinion=signUprecord.getOpinion();
			viewResultURL = CompletedWorkTaskURLEnum.WORKTASK_URL_SIGNUPRECORD_AUDIT;
			processResult = CompletedWorkTaskResultEnum.WORKTASK_PASSAUDIT;
			signUprecord.setWorkflowAuditStatus(WorkFlowModel.AUDIT_STATUS_PASS);
			try {
				String signids[]=objIds.split(",");
				String userids[]= new String[signids.length];
				String useremails[]= new String[signids.length];
				//拼装报名供应商的机构管理员用户ID
				for (int i = 0; i < signids.length; i++) {
					SignUprecord obj =  signUprecordManager.get(signids[i]);
					//审核报名供应商
					obj.setAuditStatus(auditStatus);
					userids[i] = obj.getSupplier().getUser().getObjId();
					useremails[i] = obj.getSupplier().getUser().getEmp().getEmail();
				}
				//报名审核通过发送站内信和邮件
				Map<String, Object> templateMap = new HashMap<String, Object>();
				templateMap.put("content", "在"+signUprecord.getProject().getProjName()+"采购项目中，您的报名信息已审核通过，您可以下载采购文件了！<a href=\"javascript:void(0);\" onclick=\"viewSignUpDetail('"+signUprecord.getObjId()+"')\">查看报名信息</a>");
				String  messageContent = freeMarkerService.getFreeMarkerContent(messageSource.getMessage(EnumMessage.SIGNUP_AUDIT_MESSAGE), templateMap);
				
		        EmailMessage emailEmessage = new EmailMessage();
		        emailEmessage.setTo(useremails);
		        emailEmessage.setSubject(signUprecord.getProject().getProjName());
		        emailEmessage.setContent(messageContent);
		        mailService.sendMessage(emailEmessage);
		        
		        //发站内信
				Message message = new Message();
				message.setTitle("<b>报名信息审核通过</b>");
				message.setContent(messageContent);
				message.setCreateTime(new Date());
				message.setIsSave(false);
				message.setType(EnumMessage.PRIVATE_MESSAGE);// 属于个人信件
				//给审核通过的机构管理员发站内信
				messageManager.saveMessageBatch(message, userids);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(signUprecord.getAuditStatus()!=null&&(AuditStatusEnum.AUDIT_NO_PASS).equals(signUprecord.getAuditStatus())){
			taskName="审核供应商报名";
			opinion=signUprecord.getOpinion();
			viewResultURL = CompletedWorkTaskURLEnum.WORKTASK_URL_SIGNUPRECORD_AUDIT;
			processResult = CompletedWorkTaskResultEnum.WORKTASK_NOPASSAUDIT;
			signUprecord.setWorkflowAuditStatus(WorkFlowModel.AUDIT_STATUS_NO_PASS);
		}
		for(String signUprecords:signUprecordIds){
			completedWorkTaskManager.createCompTaskByPassivity(taskName,CompletedWorkTaskTypeEnum.WORKTASK_TYPE_SIGNUPRECORD,opinion,signUprecords,signUprecord.getProject().getObjId(),viewResultURL,processResult);
		}
		
		
		User user=AuthenticationHelper.getCurrentUser();
		signUprecord.setUser(user);
		Project project = projectManager.get(signUprecord.getProject().getObjId());
		String parentBizId = project.getObjId();
		if(project.getParentId()!= null ){
			parentBizId = project.getParentId();
		}
		signUprecord.setUser(AuthenticationHelper.getCurrentUser());
		signUprecord.setParentBizId(parentBizId);
		return signUprecord;
	}

	
	/**
	 * 
	 * Description :根据项目和审核状态获得报名信息  
	 * Create Date: 2010-9-10下午01:43:22 by liuke  Modified Date: 2010-9-10下午01:43:22 by liuke
	 * @param   projectId：项目Id,auditStatus：审核状态
	 * @return  List<SignUprecord>
	 * @Exception
	 */
	public List<SignUprecord> getSignUprecordByprojectIdAndAuditStatus(
			String projectId) {
		logger.debug("\n SignUprecordServiceImpl||getSignUprecordByprojectIdAndAuditStatus\n");
		return signUprecordDaoHibernate.getSignUprecordByprojectIdAndAuditStatus(projectId);
	}

	/**
	 * Description :  得到审核通过的供应商报名信息列表
	 * Create Date: 2010-10-20上午10:12:55 by shenjianzhuang  Modified Date: 2010-10-20上午10:12:55 by shenjianzhuang
	 * @param projectId
	 * @return
	 * @return  List<SignUprecord>
	 * @Exception	
	 */		
	public List<SignUprecord> findSignupRecordList(String projectId){
		logger.debug("\n SignUprecordServiceImpl||findSignupRecordList\n");
		QueryObject<SignUprecord> queryObject = new QueryObjectBase<SignUprecord>();
		queryObject.setEntityClass(SignUprecord.class);
		queryObject.getQueryParam().and(new QueryParam("project.objId",QueryParam.OPERATOR_EQ,projectId));
		queryObject.getQueryParam().and(new QueryParam("auditStatus",QueryParam.OPERATOR_EQ,AuditStatusEnum.AUDIT_PASS));
		return signUprecordManager.findByQuery(queryObject);
	}
	


	/**
	 * Description :  删除供应商报名信息
	 * Create Date: 2010-10-20下午03:29:16 by shenjianzhuang  Modified Date: 2010-10-20下午03:29:16 by shenjianzhuang
	 * @param objId
	 * @return
	 * @return  SignUprecord
	 * @Exception
	 */		
	public SignUprecord deleteSignUprecord(String  objId) {
		logger.debug("\nes SignUprecordServiceImpl||deleteSignUprecord\n");
		signUprecordManager.remove(objId, SignUprecord.class);
		return null;
	}
	/**
	 * Description :  用于判断代理机构是否重复录入供应商
	 * Create Date: 2010-10-21上午09:44:37 by shenjianzhuang  Modified Date: 2010-10-21上午09:44:37 by shenjianzhuang
	 * @param objId
	 * @param objId2
	 * @return  Boolean
	 */	
	public Boolean checkSupllierInSignupRecord(String objId,String objId2){
		logger.debug("\nes SignUprecordServiceImpl||checkSupllierInSignupRecord\n");
		return signUprecordManager.checkSupllierInSignupRecord(objId, objId2);
	}
	/**
	 * 
	 * Description :保存供应商报名和报名响应 
	 * Create Date: 2010-8-13上午11:52:12 by shenjz  Modified Date: 2010-8-13上午11:52:12 by shenjz
	 * @param   
	 * @return  
	 * @Exception
	 */
	public SignUprecord saveSignUprecordAndSignUpResponeForAgency(SignUprecord signUprecord,List<SignUpRespone> signUpResponeList) {
		logger.debug("\nes SignUprecordServiceImpl||saveSignUprecordAndSignUpRespone\n");
		signUprecordManager.save(signUprecord);
		if(signUpResponeList.size()>0){
			for(SignUpRespone signUpRespone:signUpResponeList){
				String signUpResponeId = signUpRespone.getObjId();
				if("".equals(signUpResponeId)){
					signUpRespone.setObjId(null);
				}
				signUpResponeManager.save(signUpRespone); //保存报名响应对象
				if(null==signUpResponeId||"".equals(signUpResponeId)){//保存中间表对象
					SignUpMRespone  signupmrespone = new SignUpMRespone();
					signupmrespone.setSignUprecord(signUprecord);
					signupmrespone.setSignUpRespone(signUpRespone);
					signupmrespone.setProject(signUprecord.getProject());
					signUpMResponeManager.save(signupmrespone);
				}
			}
		}
		/** 保存项目实施状态 */
		projectManager.saveProjProcessStatus(signUprecord.getProject().getObjId(), ProjProcessStatusEnum.SUPPLIERS_APPLY);
		signUprecord.setParentBizId(signUprecord.getProject().getObjId());
		signUprecord.setUser(AuthenticationHelper.getCurrentUser());
		signUprecord.setWorkflowAuditStatus(WorkFlowModel.AUDIT_STATUS_PASS);
		return signUprecord;
	}
	/**
	 * FuncName: saveSignUprecordAndSignUpRespone1
	 * Description :  保存供应商报名和报名响应 (分包组的)
	 * @param 
	 * @param signUprecord
	 * @param signUpResponeList
	 * @return SignUprecord
	 * @author: shenjz
	 * @Create Date:2011-5-30  上午09:15:10
	 * @Modifier: shenjz
	 * @Modified Date:2011-5-30  上午09:15:10
	 */
	public SignUprecord saveSignUprecordForSubPrj(SignUprecord signUprecord,List<SignUpRespone> signUpResponeList,String subPrjIds) {
		logger.debug("\nes SignUprecordServiceImpl||saveSignUprecordForSubPrj\n");
		String[]subPrjId = subPrjIds.split(",");
		for (int i = 0; i < subPrjId.length; i++) {
			SignUprecord  signUprecord2  = signUprecord;
			Project subPrj = new Project();
			subPrj.setObjId(subPrjId[i]);
			signUprecord2.setProject(subPrj);
			signUprecordManager.save(signUprecord2);
			if(signUpResponeList.size()>0){
				for(SignUpRespone signUpRespone:signUpResponeList){
					String signUpResponeId = signUpRespone.getObjId();
					if("".equals(signUpResponeId)){
						signUpRespone.setObjId(null);
					}
					signUpResponeManager.save(signUpRespone); //保存报名响应对象
					if(null==signUpResponeId||"".equals(signUpResponeId)){//保存中间表对象
						SignUpMRespone  signupmrespone = new SignUpMRespone();
						signupmrespone.setSignUprecord(signUprecord2);
						signupmrespone.setSignUpRespone(signUpRespone);
						signupmrespone.setProject(signUprecord.getProject());
						signUpMResponeManager.save(signupmrespone);
					}
					
					
				}
			}
		}
		/** 保存项目实施状态 */
		//projectManager.saveProjProcessStatus(signUprecord.getProject().getObjId(), ProjProcessStatusEnum.SUPPLIERS_APPLY); //已将改变状态操作移至决策类里  noted by zhouzhanghe at 2011.3.10 14:40
		/** 保存已办任务 */
		String taskName="";
		String viewResultURL = "";
		String processResult = "";
		String opinion=null;
		if(signUprecord.getAuditStatus()!=null&&(AuditStatusEnum.WAIT_AUDIT).equals(signUprecord.getAuditStatus())){
			taskName="保存供应商报名";
			viewResultURL = CompletedWorkTaskURLEnum.WORKTASK_URL_SIGNUPRECORD_SAVE;
			processResult = CompletedWorkTaskResultEnum.WORKTASK_SAVE;
		}else if(signUprecord.getAuditStatus()!=null&&(AuditStatusEnum.AUDIT_PASS).equals(signUprecord.getAuditStatus())){
			taskName="审核供应商报名";
			opinion=signUprecord.getOpinion();
			viewResultURL = CompletedWorkTaskURLEnum.WORKTASK_URL_SIGNUPRECORD_AUDIT;
			processResult = CompletedWorkTaskResultEnum.WORKTASK_PASSAUDIT;
		}else if(signUprecord.getAuditStatus()!=null&&(AuditStatusEnum.AUDIT_NO_PASS).equals(signUprecord.getAuditStatus())){
			taskName="审核供应商报名";
			opinion=signUprecord.getOpinion();
			viewResultURL = CompletedWorkTaskURLEnum.WORKTASK_URL_SIGNUPRECORD_AUDIT;
			processResult = CompletedWorkTaskResultEnum.WORKTASK_NOPASSAUDIT;
		}
		completedWorkTaskManager.createCompTaskByPassivity(taskName,CompletedWorkTaskTypeEnum.WORKTASK_TYPE_SIGNUPRECORD,opinion,signUprecord.getObjId(),signUprecord.getProject().getObjId(),viewResultURL,processResult);
		signUprecord.setParentBizId(signUprecord.getProject().getObjId());
		signUprecord.setUser(AuthenticationHelper.getCurrentUser());
		signUprecord.setWorkflowAuditStatus(WorkFlowModel.AUDIT_STATUS_PASS);
		return signUprecord;
	}
	
	/** 
	 * Description :  根据查询条件统计对应的报名信息数据
	 * Create Date: 2010-7-10上午10:57:34 by yangx  Modified Date: 2010-7-10上午10:57:34 by yangx
	 * @param   queryObject[auditStatus:审核状态;managerID:项目负责人,projectID:项目ID]
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<SignUprecord> getSignUprecordListByQueryObject(QueryObject queryObject){
		return signUprecordDaoHibernate.getSignUprecordListByQueryObject(queryObject);
	}
	
	/**
	 * FuncName: getSignUprecordByprojectIdAndSupplierId
	 * Description :  根据项目和供应商得到报名信息 
	 * @param projectId
	 * @param supplierId
	 * @return SignUprecord
	 * @author: shenjz
	 * @Create Date:2011-6-2  上午10:17:29
	 * @Modifier: shenjz
	 * @Modified Date:2011-6-2  上午10:17:29
	 */
	public SignUprecord getSignUprecordByprojectIdAndSupplierId(String projectId, String supplierId) {
		return signUprecordDaoHibernate.getSignUprecordByprojectIdAndSupplierId(projectId, supplierId);
	}

	
	/**
	 * 
	 * FuncName: getSignUprecordByprojectAndSupplierId
	 * Description :  根据项目与供应商获取报名信息
	 * @param 
	 * @param projectId
	 * @param supplierId
	 * @return SignUprecord
	 * @author: liuke
	 * @Create Date:2011-8-2  下午05:02:40
	 * @Modifier: liuke
	 * @Modified Date:2011-8-2  下午05:02:40
	 */
	public SignUprecord getSignUprecordByprojectAndSupplierId(String projectId,
			String supplierId) {
		return signUprecordDaoHibernate.getSignUprecordByprojectAndSupplierId(projectId, supplierId);
	}
	
	/** 
	 * FuncName : checkSignupRecord
	 * Description :  完成供应商报名
	 * Create Date: 2011-8-8下午01:52:38 by yangx  Modified Date: 2011-8-8下午01:52:38 by yangx
	 * @param   projectId
	 * @return  SignUprecord
	 * @Exception   
	 */
	public SignUprecord checkSignupRecord(String projectId){
		SignUprecord signUprecord = new SignUprecord();
		List<SignUprecord> signUprecordList = signUprecordManager.getSignUprecordListByProjectId(projectId);
        if(signUprecordList!=null&&signUprecordList.size()>0){//判断是否有供应商报名
        	List<SignUprecord> waitAuditsignUprecordList = signUprecordDaoHibernate.getWaitAuditSignUprecordByProjectid(projectId);
              if (waitAuditsignUprecordList!=null&&waitAuditsignUprecordList.size()>0) {
            	  throw new EsException(messageSource.getMessage(EsExceptionEnum.SIGNUPRECORD_WAIT));
              }else{
            	signUprecord = signUprecordList.get(0);
          		signUprecord.setUser(AuthenticationHelper.getCurrentUser());
        		signUprecord.setParentBizId(projectId);
        		return signUprecord;
              }
        
        }else{
        	throw new EsException(messageSource.getMessage(EsExceptionEnum.SIGNUPRECORD_IS_NONE));
        }
	}
	/**
	 * FuncName: getSignUprecordNotEntryBailRecord
	 * Description :  得到未录入保证金的项目记录
	 * @param 
	 * @return List<SignUprecord>
	 * @author: liuke
	 * @Create Date:2011-8-10  上午10:29:46
	 * @Modifier: liuke
	 * @Modified Date:2011-8-10  上午10:29:46
	 */
	public List<Object> getSignUprecordNotEntryBailRecord() {
		return signUprecordDaoHibernate.getSignUprecordNotEntryBailRecord();
	}
 
	/** 
	 * FuncName : getSignUprecordBySupplierId
	 * Description :  根据供应商Id获取报名记录
	 * Create Date: 2011-9-27下午03:15:34 by yangx  
	 * Modified Date: 2011-9-27下午03:15:34 by yangx
	 * @param   supplierId：供应商Id
	 * @return  List<SignUprecord>
	 * @Exception   
	 */
	public List<SignUprecord> getSignUprecordBySupplierId(String supplierId){
		return signUprecordDaoHibernate.getSignUprecordBySupplierId(supplierId);
	}

	/**
	 * FuncName: getCountSignUpSupplierByProjectId
	 * Description :  统计报名供应商家数
	 * @param projectId
	 * @author: zhouzhanghe
	 * @Create Date:2011-8-4 11:47
	 */
	public int getCountSignUpSupplierByProjectId(String projectId) {
		return signUprecordDaoHibernate.getCountSignUpSupplierByProjectId(projectId);
	}
}
