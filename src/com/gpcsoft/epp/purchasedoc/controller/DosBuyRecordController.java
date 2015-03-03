package com.gpcsoft.epp.purchasedoc.controller;

import java.util.ArrayList;
import java.util.Date;
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
import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.common.domain.AuditStatusEnum;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.project.domain.ProjPlanStatusEnum;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.service.ProjectService;
import com.gpcsoft.epp.projrule.domain.ProjProcessRule;
import com.gpcsoft.epp.projrule.service.ProjProcessRuleService;
import com.gpcsoft.epp.purchasedoc.domain.DosBuyRecord;
import com.gpcsoft.epp.purchasedoc.service.DosBuyRecordService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.auth.service.AttachmentService;
import com.gpcsoft.srplatform.common.utils.AttachmentUtil;
import com.gpcsoft.srplatform.sysconfig.domain.SysConfigEnum;

/**
 * @gpcsoft.view value="BuyRecordListView"
 *  url="view/es/planform/purchasedoc/dosBuyRecordList.jsp"
 *  @gpcsoft.view value="BuyRecordFormView"
 *  url="view/es/planform/purchasedoc/dosBuyRecordForm.jsp"
 *  @gpcsoft.view value="docDownLoadView"
 *  url="view/es/planform/purchasedoc/docDownLoad.jsp"
 *  @gpcsoft.view value="BuyRecordDedtailView"
 *  url="view/es/planform/purchasedoc/dosBuyRecordDetail.jsp"
 *  @gpcsoft.view value="toDosBuyRecordListForTenderfeeView"
 *  url="view/es/planform/purchasedoc/dosBuyRecordListForTenderfee.jsp"
 *  @gpcsoft.view value="toViewDosBuyRecordList"
 *  url="view/es/planform/purchasedoc/dosBuyRecordViewList.jsp"
 *  @gpcsoft.view value="toDosBuyRecordListForSupplierView"
 *  url="view/es/planform/purchasedoc/dosBuyRecordFormForSupplier.jsp"
 *  @gpcsoft.view value="toDosBuyRecordListForSupplierList"
 *  url="view/es/planform/purchasedoc/dosBuyRecordListForSupplierList.jsp"
 *  @gpcsoft.view value="toconfirmDosBuyRecordForm"
 *  url="view/es/planform/purchasedoc/dosBuyRecordDetailForConfirm.jsp"
 * 
*/
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={DosBuyRecord.class})
@RequestMapping("/DosBuyRecordController.do")//页面请求路径,可修改
@SuppressWarnings("unchecked")
public class DosBuyRecordController extends AnnotationMultiController<DosBuyRecord> {

	@Autowired(required=true) @Qualifier("dosBuyRecordServiceImpl")
	private DosBuyRecordService dosBuyRecordService;
    
	@Autowired(required=true) @Qualifier("projectServiceImpl")
	private ProjectService projectService;
	
	@Autowired(required=true) @Qualifier("projProcessRuleServiceImpl")
	private ProjProcessRuleService projProcessRuleService;
	
	@Autowired(required = true) @Qualifier("attachmentServiceImpl")
	private AttachmentService attachmentService;
	/** 
	 * FuncName : toDosBuyRecordForm
	 * Description :  录入标书费购买记录
	 * Create Date: 2011-9-22上午11:30:43 by yangx  
	 * Modified Date: 2011-9-22上午11:30:43 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=toDosBuyRecordForm")
	public ModelAndView toDosBuyRecordForm(HttpServletRequest request)throws Exception {
		logger.debug("\n=DosBuyRecordController||toDosBuyRecordForm\n");
		String projectId = request.getParameter("projectId");
		String isSubProject = request.getParameter("isSubProject");
		DosBuyRecord dosBuyRecord = new DosBuyRecord();
		dosBuyRecord.setTenderId(projectId);
		dosBuyRecord.setIsSubProject(new Boolean(isSubProject));
		Map model = new HashMap();
		model.put("dosBuyRecord", dosBuyRecord);
		return new ModelAndView("BuyRecordFormView", model);	
	}
	
	
	/** 
	 * FuncName : saveDosBuyRecord
	 * Description :  保存标书费购买记录
	 * Create Date: 2011-9-22下午01:31:30 by yangx  
	 * Modified Date: 2011-9-22下午01:31:30 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=saveDosBuyRecord")
	public ModelAndView saveDosBuyRecord(HttpServletRequest request,DosBuyRecord dosBuyRecord,SessionStatus stauts)throws Exception {
		logger.debug("\n=DosBuyRecordController||saveDosBuyRecord\n");
		String projectId = request.getParameter("tenderId");
		Boolean attachRelaId1 = new Boolean(request.getParameter("attachRelaId1"));// 判断是否上传缴纳证明附件
		Boolean invoiceFile1 = new Boolean(request.getParameter("invoiceFile1"));// 判断是否上传发票附件
		if (attachRelaId1) {//上传缴纳证明附件
			Object o1 = AttachmentUtil.uploadFile(request, "attachRelaIdBak");
			if (o1 instanceof GpcBaseObject) {
				Attachment attachment = (Attachment) o1;
				attachmentService.saveAttachment((Attachment) o1);
				dosBuyRecord.setDocBuyFile(attachment.getRelationId());
			}
		}
		if (invoiceFile1) {//上传发票附件
			Object o2 = AttachmentUtil.uploadFile(request, "invoiceFileBak");
			if (o2 instanceof GpcBaseObject) {
				Attachment attachment = (Attachment) o2;
				attachmentService.saveAttachment((Attachment) o2);
				dosBuyRecord.setInvoiceFile(attachment.getRelationId());
			}
		}
		dosBuyRecord.setCreateTime(new Date());
		dosBuyRecord.setAuditStatus(AuditStatusEnum.AUDIT_PASS);
		dosBuyRecord.setUseStatus(CommonEnum.USER_STATUS_FORMAL);
		dosBuyRecord.setDocBuyStatus(DosBuyRecord.STATUS_PAID);
		dosBuyRecordService.saveDosBuyRecord(dosBuyRecord,projectId);
		stauts.setComplete();//因为session会影响到页面取值，所以这里清除一下。
	    return new ModelAndView(Constants.JSON_VIEW);	
	}
	
	/** 
	 * FuncName : toUpdateDosBuyRecordForm
	 * Description :  跳转到修改标书费购买记录页面
	 * Create Date: 2011-9-22下午04:12:36 by yangx  
	 * Modified Date: 2011-9-22下午04:12:36 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=toUpdateDosBuyRecordForm")
	public ModelAndView toUpdateDosBuyRecordForm(HttpServletRequest request)throws Exception {
		logger.debug("\n=DosBuyRecordController||toUpdateDosBuyRecordForm\n");
		String dosBuyRecordId = request.getParameter("dosBuyRecordId");
		Map model = new HashMap();
		model.put("dosBuyRecord", dosBuyRecordService.getDosBuyRecordByObjId(dosBuyRecordId));
		return new ModelAndView("BuyRecordFormView", model);	
	}
	
	
	/** 
	 * FuncName : toDosBuyRecordDetail
	 * Description :  跳转到查看标书费购买记录页面
	 * Create Date: 2011-9-22下午02:50:44 by yangx  
	 * Modified Date: 2011-9-22下午02:50:44 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=toDosBuyRecordDetail")
	public ModelAndView toDosBuyRecordDetail(HttpServletRequest request)throws Exception {
		logger.debug("\n=DosBuyRecordController||toDosBuyRecordDetail\n");
		String dosBuyRecordId = request.getParameter("dosBuyRecordId");
		Map model = new HashMap();
		List<DosBuyRecord> dosBuyRecordList = new ArrayList<DosBuyRecord>();
		DosBuyRecord dosBuyRecord = dosBuyRecordService.getDosBuyRecordByObjId(dosBuyRecordId);
		dosBuyRecordList.add(dosBuyRecord);
		model.put("dosBuyRecord", dosBuyRecord);
		model.put("dosBuyRecordList", dosBuyRecordList);
		model.put("fromDljg","YES");
		return new ModelAndView("BuyRecordDedtailView", model);	
	}
	
	/**
	 * Funcname:getAttachmentByobjId
	 * Description:得到附件对象
	 * Create Date: 2010-6-19下午02:38:07 
	 * @author liuke  
	 * Modified Date: 2010-6-19下午02:38:07 
	 * @author liuke
	 * @param   request
	 * @return  ModelAndView
	 * @Exception
	 */
	@RequestMapping(params="method=getAttachmentByobjId")
	public ModelAndView getAttachmentByobjId(HttpServletRequest request)throws Exception {
		logger.debug("\n=DosBuyRecordController||getAttachmentByobjId\n");
		AttachmentService attachmentService = (AttachmentService)FrameBeanFactory.getBean("attachmentServiceImpl");
		String attachmentId = request.getParameter("attachmentId");
		List attachmentList=new ArrayList();
		attachmentList = attachmentService.getAttachmentsByRealID(attachmentId);
		Map model = new HashMap();
		model.put("attachment", attachmentList.get(0));
	    return new ModelAndView(Constants.JSON_VIEW, model);	
	}
	
	/** 
	 * FuncName : removeDosBuyRecordById
	 * Description :   删除标书费购买记录
	 * Create Date: 2011-9-22下午04:14:06 by yangx  
	 * Modified Date: 2011-9-22下午04:14:06 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=removeDosBuyRecordById")
	public ModelAndView removeDosBuyRecordById(HttpServletRequest request)throws EsException {
		logger.debug("\nes=DosBuyRecordController||removeDosBuyRecordById\n");
		String dosBuyRecordId = request.getParameter("dosBuyRecordId");
		dosBuyRecordService.removeDosBuyRecordByIds(dosBuyRecordId.split(","));
		return new ModelAndView(Constants.JSON_VIEW);	
	}
	
	
	/** 
	 * FuncName : toViewDosBuyRecordList
	 * Description :  跳转到查看标书费页面
	 * Create Date: 2011-11-15下午03:36:15 by yangx  
	 * Modified Date: 2011-11-15下午03:36:15 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=toViewDosBuyRecordList")
	public ModelAndView toViewDosBuyRecordList(HttpServletRequest request, String projectId)throws Exception {
		logger.debug("\nes DosBuyRecordController||toDosBuyRecordListForTenderfee\n");
		ProjProcessRule projProcessRule = projProcessRuleService.getProjProcessRuleByProjectIdAndCode(projectId,SysConfigEnum.MAKETENDER);
		Map<String,Object> model = new HashMap<String,Object>();
		if (projProcessRule!=null&&ProjPlanStatusEnum.IN_TRUE.equals(projProcessRule.getResValue())) {//是按照标段制作标书
			List<Project> subProjectList = projectService.getSubProjectByProjectId(projectId);
			for (Project subProject:subProjectList) {
				List<DosBuyRecord> signList = dosBuyRecordService.getByProjectId(subProject.getObjId());//购买记录
				subProject.setList(signList);
			}
			model.put("isSubProject", true);
			model.put("projectList", subProjectList);
		}else{
			List<Project> projectList = new ArrayList<Project>();
			List<DosBuyRecord> signList = dosBuyRecordService.getByProjectId(projectId);//购买记录
			Project project = projectService.getProjectByObjId(projectId);
			project.setList(signList);
			projectList.add(project);
			model.put("isSubProject", false);
			model.put("projectList", projectList);
		}
		model.put("userId", AuthenticationHelper.getCurrentUser(true).getObjId());
		return new ModelAndView("toViewDosBuyRecordList",model);
	}
	
	/**
	 * Funcname:toDosBuyRecordList
	 * Description :  跳转到收取标书费列表页面
	 * Create Date: 2010-9-20 上午09:10:00
	 * @author lixq  
	 * @param   request
	 * @return  ModelAndView
	 * @Exception
	 */
	@RequestMapping(params="method=toDosBuyRecordListForTenderfee")
	public ModelAndView toDosBuyRecordListForTenderfee(HttpServletRequest request, String projectId)throws Exception {
		logger.debug("\nes DosBuyRecordController||toDosBuyRecordListForTenderfee\n");
		ProjProcessRule projProcessRule = projProcessRuleService.getProjProcessRuleByProjectIdAndCode(projectId,SysConfigEnum.MAKETENDER);
		Map<String,Object> model = new HashMap<String,Object>();
		if (projProcessRule!=null&&ProjPlanStatusEnum.IN_TRUE.equals(projProcessRule.getResValue())) {//是按照标段制作标书
			List<Project> subProjectList = projectService.getSubProjectByProjectId(projectId);
			for (Project subProject:subProjectList) {
				List<DosBuyRecord> signList = dosBuyRecordService.getByProjectId(subProject.getObjId());//购买记录
				subProject.setList(signList);
			}
			model.put("isSubProject", true);
			model.put("projectList", subProjectList);
		}else{
			List<Project> projectList = new ArrayList<Project>();
			List<DosBuyRecord> signList = dosBuyRecordService.getByProjectId(projectId);//购买记录
			Project project = projectService.getProjectByObjId(projectId);
			project.setList(signList);
			projectList.add(project);
			model.put("isSubProject", false);
			model.put("projectList", projectList);
		}
		model.put("userId", AuthenticationHelper.getCurrentUser(true).getObjId());
		return new ModelAndView("toDosBuyRecordListForTenderfeeView",model);
	}
	
	
	/** 
	 * FuncName : toDosBuyRecordListForSupplierList
	 * Description :  跳转到供应商列表页面
	 * Create Date: 2011-9-26下午03:30:05 by yangx  
	 * Modified Date: 2011-9-26下午03:30:05 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=toDosBuyRecordListForSupplierList")
	public ModelAndView toDosBuyRecordListForSupplierList(HttpServletRequest request)throws Exception{
		Map<String,Object> model = new HashMap<String,Object>();
		String projectId = request.getParameter("projectId");
		ProjProcessRule projProcessRule = projProcessRuleService.getProjProcessRuleByProjectIdAndCode(projectId,SysConfigEnum.MAKETENDER);
		if (projProcessRule!=null&&ProjPlanStatusEnum.IN_TRUE.equals(projProcessRule.getResValue())) {//是按照标段制作标书
			List<Project> subProjectList = projectService.getSubProjectByProjectId(projectId);
			model.put("projectList", subProjectList);
			model.put("isSubProject", true);
		}else{
			List<Project> subProjectList = new ArrayList<Project>();
			Project project = projectService.getProjectByObjId(projectId);
			subProjectList.add(project);
			model.put("projectList", subProjectList);
			model.put("isSubProject", false);
		}
		return new ModelAndView("toDosBuyRecordListForSupplierList",model);
	}
	
	/** 
	 * FuncName : toDosBuyRecordListForSupplier
	 * Description :  跳转到供应商录入标书费界面
	 * Create Date: 2011-9-22下午11:22:16 by yangx  
	 * Modified Date: 2011-9-22下午11:22:16 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=toDosBuyRecordListForSupplier")
	public ModelAndView toDosBuyRecordListForSupplier(HttpServletRequest request)throws Exception{
		String projectId = request.getParameter("projectId");
		Map<String,Object> model = new HashMap<String,Object>();
		User user = AuthenticationHelper.getCurrentUser(true);
		OrgInfo orgInfo = (OrgInfo)user.getOrgInfo();
		String returnUrl = "toDosBuyRecordListForSupplierView";
		DosBuyRecord dosBuyRecord = new DosBuyRecord();
		List<DosBuyRecord> dosBuyRecordList = dosBuyRecordService.getDosBuyRecordBySupplierId(orgInfo.getObjId(), projectId);
		if (dosBuyRecordList!=null&&dosBuyRecordList.size()>0) {//存在标书费记录
			dosBuyRecord = dosBuyRecordList.get(0);
			if (dosBuyRecord!=null&&!AuditStatusEnum.AUDIT_NO_PASS.equals(dosBuyRecord.getAuditStatus())) {
				returnUrl = "BuyRecordDedtailView";//跳转到查看页面
			}
		}else{
			ProjProcessRule projProcessRule = projProcessRuleService.getProjProcessRuleByProjectIdAndCode(projectId,SysConfigEnum.MAKETENDER);
			if (projProcessRule!=null&&ProjPlanStatusEnum.IN_TRUE.equals(projProcessRule.getResValue())) {//是按照标段制作标书
				dosBuyRecord.setIsSubProject(true);
			}else{//按照项目制作标书
				dosBuyRecord.setIsSubProject(false);
			}
		}
		model.put("projectId",projectId);
		model.put("dosBuyRecord",dosBuyRecord);
		return new ModelAndView(returnUrl,model);
	}
	
	
	/** 
	 * FuncName : saveDosBuyRecordForSupplier
	 * Description :  供应商录入投标文件
	 * Create Date: 2011-9-26上午09:56:00 by yangx  
	 * Modified Date: 2011-9-26上午09:56:00 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=saveDosBuyRecordForSupplier")
	public ModelAndView saveDosBuyRecordForSupplier(HttpServletRequest request,DosBuyRecord dosBuyRecord,SessionStatus stauts)throws Exception {
		logger.debug("\n=DosBuyRecordController||saveDosBuyRecord\n");
		Map model = new HashMap();
		String projectIds = request.getParameter("tenderId");
		User user = AuthenticationHelper.getCurrentUser(true);
		OrgInfo orgInfo = (OrgInfo)user.getOrgInfo();
		try {
			Boolean attachRelaId1 = new Boolean(request.getParameter("attachRelaId1"));// 判断是否上传缴纳证明附件
			Boolean invoiceFile1 = new Boolean(request.getParameter("invoiceFile1"));// 判断是否上传发票附件
			if (attachRelaId1) {//上传缴纳证明附件
				Object o1 = AttachmentUtil.uploadFile(request, "attachRelaIdBak");
				if (o1 instanceof GpcBaseObject) {
					Attachment attachment = (Attachment) o1;
					attachmentService.saveAttachment((Attachment) o1);
					dosBuyRecord.setDocBuyFile(attachment.getRelationId());
				}
			}
			if (invoiceFile1) {//上传发票附件
				Object o2 = AttachmentUtil.uploadFile(request, "invoiceFileBak");
				if (o2 instanceof GpcBaseObject) {
					Attachment attachment = (Attachment) o2;
					attachmentService.saveAttachment((Attachment) o2);
					dosBuyRecord.setInvoiceFile(attachment.getRelationId());
				}
			}
		} catch (Exception e) {
			model.put("failure", true);
			model.put("result", e.getMessage());
		}
		dosBuyRecord.setSupplierId(orgInfo.getObjId());
		dosBuyRecord.setSupplierName(orgInfo.getOrgName());
		dosBuyRecord.setUseStatus(CommonEnum.USER_STATUS_TEMP);//临时
		dosBuyRecord.setAuditStatus(AuditStatusEnum.WAIT_AUDIT);//提交
		dosBuyRecord.setDocBuyStatus(DosBuyRecord.STATUS_APPLY);//申请
		dosBuyRecordService.saveDosBuyRecord(dosBuyRecord,projectIds);
		stauts.setComplete();//因为session会影响到页面取值，所以这里清除一下。
	    return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	
	/** 
	 * FuncName : toconfirmDosBuyRecordForm
	 * Description :  跳转到确认页面
	 * Create Date: 2011-9-26下午05:47:04 by yangx  
	 * Modified Date: 2011-9-26下午05:47:04 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=toConfirmDosBuyRecordForm")
	public ModelAndView toConfirmDosBuyRecordForm(HttpServletRequest request)throws Exception {
		String dosBuyRecordId = request.getParameter("dosBuyRecordId");
		Map<String,Object> model = new HashMap<String,Object>();
		DosBuyRecord dosBuyRecord = dosBuyRecordService.get(dosBuyRecordId);
		String returnUrl ="BuyRecordDedtailView";
		if (dosBuyRecord!=null&&AuditStatusEnum.WAIT_AUDIT.equals(dosBuyRecord.getAuditStatus())) {
			returnUrl ="toconfirmDosBuyRecordForm";
		}
		model.put("dosBuyRecord",dosBuyRecord);
		model.put("fromDljg","YES");
		return new ModelAndView(returnUrl,model);
	}

	
	/** 
	 * FuncName : saveConfirmDosBuyRecordForm
	 * Description :  保存确认购买记录
	 * Create Date: 2011-9-27下午05:03:27 by yangx  
	 * Modified Date: 2011-9-27下午05:03:27 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=saveConfirmDosBuyRecordForm")
	public ModelAndView saveConfirmDosBuyRecordForm(HttpServletRequest request)throws Exception {
		String dosBuyRecordId = request.getParameter("dosBuyRecordId");
		String opinion = request.getParameter("opinion");
		String useStatus = request.getParameter("useStatus");
		String auditStatus = request.getParameter("auditStatus");
		String docBuyStatus = request.getParameter("docBuyStatus");
		DosBuyRecord dosBuyRecord = dosBuyRecordService.get(dosBuyRecordId);
		dosBuyRecord.setUseStatus(useStatus);
		dosBuyRecord.setDocBuyStatus(docBuyStatus);
		dosBuyRecord.setAuditStatus(auditStatus);
		dosBuyRecord.setOpinion(opinion);
		dosBuyRecordService.saveConfirmDosBuyRecord(dosBuyRecord);
		return new ModelAndView(Constants.JSON_VIEW);	
	}
	
	
	/** 
	 * FuncName : finshConfirmDosBuyRecord
	 * Description :  完成标书费录入功能
	 * Create Date: 2011-11-16下午01:36:01 by yangx  
	 * Modified Date: 2011-11-16下午01:36:01 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=finshConfirmDosBuyRecord")
	public ModelAndView finshConfirmDosBuyRecord(HttpServletRequest request)throws Exception{
		String projectId = request.getParameter("projectId");
		dosBuyRecordService.finishConfirmDosBuyRecord(projectId);
		return new ModelAndView(Constants.JSON_VIEW);	
	}

}
