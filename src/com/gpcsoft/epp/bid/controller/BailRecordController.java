package com.gpcsoft.epp.bid.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.bid.domain.BailRecord;
import com.gpcsoft.epp.bid.domain.BailStatusCNEnum;
import com.gpcsoft.epp.bid.domain.BailStatusEnum;
import com.gpcsoft.epp.bid.service.BailRecordService;
import com.gpcsoft.epp.common.domain.AuditStatusEnum;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.domain.ProjectRule;
import com.gpcsoft.epp.project.service.ProjectService;
import com.gpcsoft.epp.signuprecord.domain.SignUprecord;
import com.gpcsoft.epp.signuprecord.service.SignUprecordService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.auth.service.AttachmentService;
import com.gpcsoft.srplatform.common.utils.AttachmentUtil;

/**
 * @gpcsoft.view value="bailRecordFormView"
 *               url="view/es/planform/bid/bailRecordForm.jsp"
 * @gpcsoft.view value="bailRecordTreeFormView"
 *               url="view/es/planform/bid/bailRecordTreeForm.jsp"
 * @gpcsoft.view value="bailRecordListView"
 *               url="view/es/planform/bid/bailRecordList.jsp"
 * @gpcsoft.view value="bailRecordDetailView"
 *               url="view/es/planform/bid/bailRecordDetail.jsp"
 * @gpcsoft.view value="toapplyRecord"
 *               url="view/es/planform/bid/applyRecord.jsp"
 * @gpcsoft.view value="toapplyRecordForPack"
 *               url="view/es/planform/bid/applyRecordForPack.jsp"
 * @gpcsoft.view value="toAddBailRecord"
 *               url="view/es/planform/bid/floatAddBailRecordForm.jsp"
 * @gpcsoft.view value="toUpdateBailRecord"
 *               url="view/es/planform/bid/floatUpdateBailRecordForm.jsp"
 * @gpcsoft.view value="toBailRecordListForSupplier"
 *  url="view/es/planform/bid/bailRecordListForSupplierList.jsp"
 * @gpcsoft.view value="toBailRecordForSupplierDetailForm"
 *  url="view/es/planform/bid/bailRecordDetails.jsp"
 * @gpcsoft.view value="toBailRecordForSupplierForm"
 *  url="view/es/planform/bid/bailRecordFormForSupplier.jsp"
 * @gpcsoft.view value="toBailRecordListForAgency"
 *  url="view/es/planform/bid/bailRecordListForAgent.jsp"
 * @gpcsoft.view value="toViewBailRecordList"
 *  url="view/es/planform/bid/bailRecordViewList.jsp"
 * @gpcsoft.view value="toConfirmBailRecordForm"
 *  url="view/es/planform/bid/bailRecordForConfirm.jsp"
 */
@Controller
@Scope("request")// 标识为控制器
@SessionAttributes(types = { BailRecord.class })
@RequestMapping("/BailRecordController.do")// 页面请求路径,可修改
public class BailRecordController extends AnnotationMultiController<BailRecord> {

	@Autowired(required = true)
	@Qualifier("bailRecordServiceImpl")
	private BailRecordService bailRecordService;

	@Autowired(required = true)
	@Qualifier("signUprecordServiceImpl")
	private SignUprecordService signUprecordService;

	@Autowired(required = true)
	@Qualifier("attachmentServiceImpl")
	private AttachmentService attachmentService;

	@Autowired(required = true)
	@Qualifier("projectServiceImpl")
	private ProjectService projectService;

	/**
	 * Description : 获取查询的保证金记录表 
	 * Create Date: 2010-8-4下午05:54:38 by shenjianzhuang
	 * Modified Date: 2010-8-4下午05:54:38 by shenjianzhuang
	 * @param request
	 * @return ModelAndView
	 */
	@RequestMapping(params = "method=toBailRecordList")
	public ModelAndView toBailRecordList(HttpServletRequest request)
			throws Exception {
		logger.debug("\nes BailRecordController||toBailRecordList\n");
		String renderTime = request.getParameter("renderTime");
		String renderTime2 = request.getParameter("renderTime2");
		String returnedTime = request.getParameter("returnedTime");
		String returnedTime2 = request.getParameter("returnedTime2");
		Page<BailRecord> page = prePage(request);
		Map<String, Object> model = new HashMap<String, Object>();
		Page<BailRecord> pageData = (Page<BailRecord>) bailRecordService
				.getSelectedBailRecord(page, renderTime, renderTime2,
						returnedTime, returnedTime2);
		endPage(model, pageData, request);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}

	/**
	 * Description : 跳到供应商录入保证金列表页面 
	 * Create Date: 2010-9-1上午10:07:56 by shenjianzhuang
	 * Modified Date: 2010-9-1上午10:07:56 by shenjianzhuang
	 * @param request:请求
	 * @return ModelAndView
	 * @Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=toapplyRecord")
	public ModelAndView toapplyRecord(HttpServletRequest request)throws Exception {
		logger.debug("\nes BailRecordController||toapplyRecord\n");
		String projectId = request.getParameter("projectId");
		/* 查询项目的包组放入subPrjList */
		ProjectRule pule = projectService.getProjectRuleByProjectId(projectId);
		List<Project> subPrjList = projectService
				.getSubProjectByProjectId(projectId);
		if (pule.getIsDividePack() == false) {
			subPrjList = new ArrayList<Project>();
			subPrjList.add(projectService.get(projectId));// 查询项目放入subPrjList
		}
		/* 查询所有包组的报名记录 */
		Map<Project, List> projectAndSignUpRecordMap = new HashMap<Project, List>();
		for (Project p : subPrjList) {
			List<SignUprecord> signUprecordList = signUprecordService
					.getSignUprecordByprojectId(p.getObjId());

			/* 计算报名记录的保证金缴纳状态 */
			for (SignUprecord signUprecord : signUprecordList) {
				BailRecord bailRecord = bailRecordService
						.getBailRecordBySignUprecord(signUprecord.getObjId());
				if (bailRecord == null)
					signUprecord.setBailStatus(BailStatusEnum.NOT_INPUT);
				else
					signUprecord.setBailStatus(bailRecord.getBailStatus());
			}
			projectAndSignUpRecordMap.put(p, signUprecordList);
		}
		Map model = new HashMap();
		model.put("projectAndSignUpRecordMap", projectAndSignUpRecordMap);
		return new ModelAndView("toapplyRecord", model);
	}
	
	
	@RequestMapping(params = "method=toapplyRecordForPack")
	public ModelAndView toapplyRecordForPack(HttpServletRequest request)throws Exception {
		logger.debug("\nes BailRecordController||toapplyRecord\n");
		String projectId = request.getParameter("projectId");
		/* 查询所有包组的报名记录 */
		List<SignUprecord> signUprecordList = signUprecordService.getSignUprecordByprojectId(projectId);
		/* 计算报名记录的保证金缴纳状态 */
		for (SignUprecord signUprecord : signUprecordList) {
			BailRecord bailRecord = bailRecordService.getBailRecordBySignUprecord(signUprecord.getObjId());
			if (bailRecord == null)
				signUprecord.setBailStatus(BailStatusEnum.NOT_INPUT);
			else
				signUprecord.setBailStatus(bailRecord.getBailStatus());	
		}
		Map model = new HashMap();
		model.put("signUprecordList", signUprecordList);
		return new ModelAndView("toapplyRecordForPack", model);
	}
	/**
	 * Description : 跳转到相应供应商的保证金录入界面 
	 * Create Date: 2010-9-1上午10:07:56 by shenjianzhuang
	 * Modified Date: 2010-9-1上午10:07:56 by shenjianzhuang
	 * @param request:请求,s:session状态,bailRecord:保证金对象;
	 * @return ModelAndView
	 * @Exception
	 */
	@RequestMapping(params = "method=toAddorUpdateBailRecord")
	public ModelAndView toAddorUpdateBailRecord(HttpServletRequest request)
			throws Exception {
		logger.debug("\nes BailRecordController||toAddorUpdateBailRecord\n");
		String signUprecordId = request.getParameter("signUprecordId");
		String url = "toAddBailRecord";// 默认跳转到新增保证金记录的界面
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("objId", "");
		BailRecord bailRecord = bailRecordService
				.getBailRecordBySignUprecord(signUprecordId);
		if (bailRecord != null) {
			if (bailRecord.getRenderAtt() != null) {
				List<Attachment> attachmentList = attachmentService
						.getAttachmentsByRealID(bailRecord.getRenderAtt());
				if (null != attachmentList && !attachmentList.isEmpty()) {
					model.put("attrId1", attachmentList.get(0).getObjId());
					model.put("attrName1", attachmentList.get(0).getViewName());
				}
			}
			if (bailRecord.getReturnedAtt() != null) {
				List<Attachment> attachmentList = attachmentService
						.getAttachmentsByRealID(bailRecord.getReturnedAtt());
				if (null != attachmentList && !attachmentList.isEmpty()) {
					model.put("attrId2", attachmentList.get(0).getObjId());
					model.put("attrName2", attachmentList.get(0).getViewName());
				}
			}
			model.put("objId", bailRecord.getObjId());
			url = "toUpdateBailRecord";// 跳转到修改保证金记录的界面
		}else{
			bailRecord = new BailRecord();
			SignUprecord signUprecord = signUprecordService.getSignUprecordByobjId(signUprecordId);
			bailRecord.setBallMoney(signUprecord.getProject().getBail());
		}
		model.put("bailRecord", bailRecord);
		model.put("signUprecordId", signUprecordId);
		return new ModelAndView(url, model);
	}

	/**
	 * Description : 新增相应供应商的保证金记录 
	 * Create Date: 2010-9-1上午10:07:56 by shenjianzhuang
	 * Modified Date: 2010-9-1上午10:07:56 by shenjianzhuang
	 * @param bailRecord:保证金对象;
	 * @return ModelAndView
	 * @exception
	 */
	@RequestMapping(params = "method=addBailRecord")
	public ModelAndView addBailRecord(HttpServletRequest request,
			SessionStatus s, BailRecord bailRecord) throws Exception {
		logger.debug("\nes BailRecordController||addBailRecord\n");
		String signUprecordId = request.getParameter("signUprecordId");
		SignUprecord signUprecord = signUprecordService.getSignUprecordByobjId(signUprecordId);
		BailRecord bailRecords = bailRecord;
		Boolean isUploadFile1 = new Boolean(request.getParameter("isUploadFile1"));// 判断是否上传附件
		if (isUploadFile1) {
			Object o1 = AttachmentUtil.uploadFile(request, "attachFile1");
			if (o1 instanceof GpcBaseObject) {
				Attachment attachment = (Attachment) o1;
				attachmentService.saveAttachment((Attachment) o1);
				bailRecords.setRenderAtt(attachment.getRelationId());
			}
		}
		Boolean isUploadFile2 = new Boolean(request.getParameter("isUploadFile2"));// 判断是否上传附件
		if (isUploadFile2) {
			Object o2 = AttachmentUtil.uploadFile(request, "attachFile2");
			if (o2 instanceof GpcBaseObject) {
				Attachment attachment = (Attachment) o2;
				attachmentService.saveAttachment((Attachment) o2);
				bailRecords.setReturnedAtt(attachment.getRelationId());
			}
		}
		bailRecords.setSignUprecord(signUprecord);
		bailRecords.setProjId(signUprecord.getProject().getObjId());
		
		bailRecordService.saveBailRecord(bailRecords);
		s.setComplete();
		Map<String, Object> model = new HashMap<String, Object>();
		return new ModelAndView(Constants.JSON_VIEW, model);
	}

	/**
	 * Description : 修改相应供应商的保证金记录 
	 * Create Date: 2010-9-1上午10:07:56 by shenjianzhuang
	 * Modified Date: 2010-9-1上午10:07:56 by shenjianzhuang
	 * @param request:请求,s:session状态,bailRecord:保证金对象;
	 * @return void
	 * @Exception
	 */
	@RequestMapping(params = "method=updateBailRecord")
	public ModelAndView updateBailRecord(HttpServletRequest request,
			SessionStatus s, BailRecord bailRecord) throws Exception {
		logger.debug("\nes BailRecordController||updateBailRecord\n");
		Boolean isUploadFile1 = new Boolean(request
				.getParameter("isUploadFile1"));// 判断是否上传附件
		Boolean isUploadFile2 = new Boolean(request
				.getParameter("isUploadFile2"));// 判断是否上传附件
		BailRecord bailRecords = bailRecordService.getBailRecord(bailRecord
				.getObjId());// 获取已有的保证金对象
		bailRecords.setBailStatus(bailRecord.getBailStatus());
		bailRecords.setBallMoney(bailRecord.getBallMoney());
		bailRecords.setCreateTime(bailRecord.getCreateTime());
		bailRecords.setReturnedTime(bailRecord.getReturnedTime());
		if (isUploadFile1) {
			Object o1 = AttachmentUtil.uploadFile(request, "attachFile1");
			if (o1 instanceof GpcBaseObject) {
				Attachment attachment = (Attachment) o1;
				attachmentService.saveAttachment((Attachment) o1);
				bailRecords.setRenderAtt(attachment.getRelationId());
			}
		}
		if (isUploadFile2) {
			Object o2 = AttachmentUtil.uploadFile(request, "attachFile2");
			if (o2 instanceof GpcBaseObject) {
				Attachment attachment = (Attachment) o2;
				attachmentService.saveAttachment((Attachment) o2);
				bailRecords.setReturnedAtt(attachment.getRelationId());
			}
		}
		bailRecordService.saveBailRecord(bailRecords);
		s.setComplete();
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	
	/** 
	 * FuncName : toBailRecordListForSupplier
	 * Description :  供应商跳转到录入保证金列表页面
	 * Create Date: 2011-9-26下午10:51:14 by yangx  
	 * Modified Date: 2011-9-26下午10:51:14 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toBailRecordListForSupplier")
	public ModelAndView toBailRecordListForSupplier(HttpServletRequest request)throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		String projectId = request.getParameter("projectId");
		ProjectRule projRule = projectService.getProjectRuleByProjectId(projectId);//项目规则
		if (projRule.getIsDividePack()) {//分包
			List<Project> subPrjList = projectService.getSubProjectByProjectId(projectId);
			model.put("projectList",subPrjList);
		}else{//未分包
			List<Project> projList = new ArrayList<Project>();
			Project proj = projectService.getProjectByObjId(projectId);
			projList.add(proj);
			model.put("projectList",projList);
		}
		return new ModelAndView("toBailRecordListForSupplier", model);
	}
	
	
	/** 
	 * FuncName : toBailRecordForSupplier
	 * Description :  供应商跳转到录入保证金表单页面
	 * Create Date: 2011-9-26下午10:51:36 by yangx  
	 * Modified Date: 2011-9-26下午10:51:36 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toBailRecordForSupplier")
	public ModelAndView toBailRecordForSupplier(HttpServletRequest request)throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		String projectId = request.getParameter("projectId");
		User user = AuthenticationHelper.getCurrentUser(true);
		OrgInfo orgInfo = (OrgInfo)user.getOrgInfo();
		List<BailRecord> bailRecordList = bailRecordService.getBailRecordByProjectAndSupplierId(projectId,orgInfo.getObjId());
		BailRecord bailRecord =null;
		String returnUrl = "toBailRecordForSupplierForm";
		if (bailRecordList!=null&&bailRecordList.size()>0) {//存在报名记录
			bailRecord = bailRecordList.get(0);
			if (bailRecord!=null&&!AuditStatusEnum.AUDIT_NO_PASS.equals(bailRecord.getAuditStatus())) {
				returnUrl = "toBailRecordForSupplierDetailForm";
			}
		}
		model.put("bailRecord",bailRecord);
		model.put("projectId",projectId);
		return new ModelAndView(returnUrl, model);
	}

	
	/** 
	 * FuncName : saveBailRecordForSupplier
	 * Description :  供应商保存保证金记录
	 * Create Date: 2011-9-26下午11:14:12 by yangx  
	 * Modified Date: 2011-9-26下午11:14:12 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveBailRecordForSupplier")
	public ModelAndView saveBailRecordForSupplier(HttpServletRequest request,BailRecord bailRecord,SessionStatus stauts)throws Exception {
		Map model = new HashMap();
		String projectId = request.getParameter("projectId");
		User user = AuthenticationHelper.getCurrentUser(true);
		OrgInfo orgInfo = (OrgInfo)user.getOrgInfo();
		try {
			Boolean attachRelaId1 = new Boolean(request.getParameter("attachRelaId1"));// 判断是否上传缴纳证明附件
			if (attachRelaId1) {//上传缴纳证明附件
				Object o1 = AttachmentUtil.uploadFile(request, "attachRelaIdBak");
				if (o1 instanceof GpcBaseObject) {
					Attachment attachment = (Attachment) o1;
					attachmentService.saveAttachment((Attachment) o1);
					bailRecord.setRenderAtt(attachment.getRelationId());
				}
			}
		} catch (Exception e) {
			model.put("result", e.getMessage());
			model.put("failure", true);
		}
		SignUprecord signUprecord = signUprecordService.getSignUprecordByprojectAndSupplierId(projectId, orgInfo.getObjId());
		Project project = projectService.getProjectByObjId(projectId);
		bailRecord.setSignUprecord(signUprecord);
		bailRecord.setUseStatus(CommonEnum.USER_STATUS_TEMP);
		bailRecord.setAuditStatus(AuditStatusEnum.WAIT_AUDIT);
		bailRecord.setBailStatus(BailStatusCNEnum.NO_RECEIVE);
		bailRecord.setSupplyerId(orgInfo.getObjId());
		bailRecord.setSupplyerName(orgInfo.getOrgName());
		bailRecord.setProjId(project.getObjId());
		bailRecord.setProjName(project.getProjName());
		bailRecord.setCreateUser(user);
		bailRecordService.saveBailRecord(bailRecord);
		stauts.setComplete();
		return new ModelAndView(Constants.JSON_VIEW,model);
	}

	
	/** 
	 * FuncName : toViewBailRecordList
	 * Description :  跳转到查看保证金页面
	 * Create Date: 2011-11-15下午03:40:45 by yangx  
	 * Modified Date: 2011-11-15下午03:40:45 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=toViewBailRecordList")
	public ModelAndView toViewBailRecordList(HttpServletRequest request)throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		String projectId = request.getParameter("projectId");
		ProjectRule projRule = projectService.getProjectRuleByProjectId(projectId);//项目规则
		if (projRule.getIsDividePack()) {//分包
			List<Project> subPrjList = projectService.getSubProjectByProjectId(projectId);
			for (Project subProject:subPrjList) {
				List<BailRecord> bailRecordList = bailRecordService.getBailRecordByProjectId(subProject.getObjId());
				subProject.setList(bailRecordList);
			}
			model.put("projectList",subPrjList);
		}else{//未分包
			List<Project> projList = new ArrayList<Project>();
			Project proj = projectService.getProjectByObjId(projectId);
			List<BailRecord> bailRecordList = bailRecordService.getBailRecordByProjectId(proj.getObjId());
			proj.setList(bailRecordList);
			projList.add(proj);
			model.put("projectList",projList);
		}
		model.put("userId", AuthenticationHelper.getCurrentUser(true).getObjId());
		return new ModelAndView("toViewBailRecordList", model);
	}
	
	/** 
	 * FuncName : toBailRecordListForAgency
	 * Description :  代理机构跳转到确认保证金列表页面
	 * Create Date: 2011-9-27下午02:26:56 by yangx  
	 * Modified Date: 2011-9-27下午02:26:56 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=toBailRecordListForAgency")
	public ModelAndView toBailRecordListForAgency(HttpServletRequest request)throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		String projectId = request.getParameter("projectId");
		ProjectRule projRule = projectService.getProjectRuleByProjectId(projectId);//项目规则
		if (projRule.getIsDividePack()) {//分包
			List<Project> subPrjList = projectService.getSubProjectByProjectId(projectId);
			for (Project subProject:subPrjList) {
				List<BailRecord> bailRecordList = bailRecordService.getBailRecordByProjectId(subProject.getObjId());
				subProject.setList(bailRecordList);
				model.put("bailRecordList",bailRecordList);
			}
			model.put("projectList",subPrjList);
		}else{//未分包
			List<Project> projList = new ArrayList<Project>();
			Project proj = projectService.getProjectByObjId(projectId);
			List<BailRecord> bailRecordList = bailRecordService.getBailRecordByProjectId(proj.getObjId());
			proj.setList(bailRecordList);
			projList.add(proj);
			model.put("bailRecordList",bailRecordList);
			model.put("projectList",projList);
		}
		model.put("userId", AuthenticationHelper.getCurrentUser(true).getObjId());
		return new ModelAndView("toBailRecordListForAgency", model);
	}

	
	/** 
	 * FuncName : toViewBailRecord
	 * Description :  根据保证金Id跳转到查看页面
	 * Create Date: 2011-9-27下午04:28:55 by yangx  
	 * Modified Date: 2011-9-27下午04:28:55 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=toViewBailRecord")
	public ModelAndView toViewBailRecord(HttpServletRequest request)throws Exception {
		String bailRecordId = request.getParameter("bailRecordId");
		Map<String,Object> model = new HashMap<String,Object>();
		BailRecord bailRecord = bailRecordService.get(bailRecordId);
		model.put("bailRecord",bailRecord);
		model.put("fromDljg","YES");
		return new ModelAndView("toBailRecordForSupplierDetailForm", model);
	}
	
	
	/** 
	 * FuncName : removeBailRecord
	 * Description :  删除保证金
	 * Create Date: 2011-9-27下午04:34:40 by yangx  
	 * Modified Date: 2011-9-27下午04:34:40 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=removeBailRecord")
	public ModelAndView removeBailRecord(HttpServletRequest request)throws Exception {
		String bailRecordId = request.getParameter("bailRecordId");
		bailRecordService.removeBailRecordByIds(bailRecordId);
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	
	/** 
	 * FuncName : toBailRecordForm
	 * Description :  代理机构跳转到确认录入保证金页面
	 * Create Date: 2011-9-27下午04:43:13 by yangx  
	 * Modified Date: 2011-9-27下午04:43:13 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=toConfirmBailRecordForm")
	public ModelAndView toConfirmBailRecordForm(HttpServletRequest request)throws Exception {
		String bailRecordId = request.getParameter("bailRecordId");
		Map<String,Object> model = new HashMap<String,Object>();
		BailRecord bailRecord = bailRecordService.get(bailRecordId);
		String returnUrl ="toBailRecordForSupplierDetailForm";
		if (bailRecord!=null&&AuditStatusEnum.WAIT_AUDIT.equals(bailRecord.getAuditStatus())) {
			returnUrl ="toConfirmBailRecordForm";
		}
		model.put("bailRecord",bailRecord);
		model.put("fromDljg","YES");
		return new ModelAndView(returnUrl,model);
	}

	
	/** 
	 * FuncName : saveConfirmBailRecord
	 * Description :  保存确认保证金记录
	 * Create Date: 2011-9-27下午05:09:00 by yangx  
	 * Modified Date: 2011-9-27下午05:09:00 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=saveConfirmBailRecord")
	public ModelAndView saveConfirmBailRecord(HttpServletRequest request)throws Exception {
		String bailRecordId = request.getParameter("bailRecordId");
		String opinion = request.getParameter("opinion");
		String useStatus = request.getParameter("useStatus");
		String bailStatus = request.getParameter("bailStatus");
		String auditStatus = request.getParameter("auditStatus");
		BailRecord bailRecord = bailRecordService.get(bailRecordId);
		bailRecord.setUseStatus(useStatus);
		bailRecord.setOpinion(opinion);
		bailRecord.setBailStatus(bailStatus);
		bailRecord.setAuditStatus(auditStatus);
		bailRecordService.saveConfirmBailRecord(bailRecord);
		return new ModelAndView(Constants.JSON_VIEW);
	}

	
	/** 
	 * FuncName : saveBailRecordForAgent
	 * Description :  保存补录结果
	 * Create Date: 2011-9-27下午05:26:36 by yangx  
	 * Modified Date: 2011-9-27下午05:26:36 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=saveBailRecordForAgent")
	public ModelAndView saveBailRecordForAgent(HttpServletRequest request,BailRecord bailRecord,SessionStatus stauts)throws Exception {
		String projectId = request.getParameter("projectId");
		User user = AuthenticationHelper.getCurrentUser(true);
		Boolean attachRelaId1 = new Boolean(request.getParameter("attachRelaId1"));// 判断是否上传缴纳证明附件
		if (attachRelaId1) {//上传缴纳证明附件
			Object o1 = AttachmentUtil.uploadFile(request, "attachRelaIdBak");
			if (o1 instanceof GpcBaseObject) {
				Attachment attachment = (Attachment) o1;
				attachmentService.saveAttachment((Attachment) o1);
				bailRecord.setRenderAtt(attachment.getRelationId());
			}
		}
		SignUprecord signUprecord = signUprecordService.getSignUprecordByprojectAndSupplierId(projectId,bailRecord.getSupplyerId());
		Project project = projectService.getProjectByObjId(projectId);
		bailRecord.setSignUprecord(signUprecord);
		bailRecord.setUseStatus(CommonEnum.USER_STATUS_FORMAL);
		bailRecord.setAuditStatus(AuditStatusEnum.AUDIT_PASS);
		bailRecord.setBailStatus(BailStatusCNEnum.ALREADY_RECEIVE);
		bailRecord.setProjId(project.getObjId());
		bailRecord.setProjName(project.getProjName());
		bailRecord.setCreateUser(user);
		bailRecordService.saveBailRecord(bailRecord);
		stauts.setComplete();//因为session会影响到页面取值，所以这里清除一下。
	    return new ModelAndView(Constants.JSON_VIEW);
	}
	
	
	/** 
	 * FuncName : finshConfirmBailRecord
	 * Description :  完成保证金录入
	 * Create Date: 2011-11-18下午01:13:06 by yangx  
	 * Modified Date: 2011-11-18下午01:13:06 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=finshConfirmBailRecord")
	public ModelAndView finshConfirmBailRecord(HttpServletRequest request)throws Exception{
		String projectId = request.getParameter("projectId");
		bailRecordService.finishConfirmBailRecord(projectId);
		return new ModelAndView(Constants.JSON_VIEW);	
	}
	
}
