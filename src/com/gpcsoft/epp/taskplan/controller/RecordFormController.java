package com.gpcsoft.epp.taskplan.controller;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
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
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.common.domain.AuditStatusEnum;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.common.domain.EsExceptionEnum;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.common.service.UserApiService;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.domain.ProjectEnum;
import com.gpcsoft.epp.project.service.ProjectPlanService;
import com.gpcsoft.epp.project.service.ProjectService;
import com.gpcsoft.epp.taskplan.domain.ProjMember;
import com.gpcsoft.epp.taskplan.domain.RecordForm;
import com.gpcsoft.epp.taskplan.service.RecordFormService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.domain.Employee;
import com.gpcsoft.srplatform.auth.domain.ListBind;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.auth.service.AttachmentService;
import com.gpcsoft.srplatform.common.utils.AttachmentUtil;
import com.gpcsoft.srplatform.common.utils.SysInfo;

/**
  * @gpcsoft.view value="recordFormFormViewJz"
  *  url="view/es/planform/project/recordFormFormJz.jsp"
  * @gpcsoft.view value="recordFormFormViewCq"
  *  url="view/es/planform/project/recordFormFormCq.jsp"
  *  @gpcsoft.view value="recordFormFormViewTd"
  *  url="view/es/planform/project/recordFormFormTd.jsp"
  * @gpcsoft.view value="updateRecordFormFormViewJz"
  *  url="view/es/planform/project/updateRecordFormJz.jsp"
  * @gpcsoft.view value="updateRecordFormFormViewCq"
  *  url="view/es/planform/project/updateRecordFormCq.jsp"
  * @gpcsoft.view value="updateRecordFormFormViewTd"
  *  url="view/es/planform/project/updateRecordFormTd.jsp"
  * @gpcsoft.view value="recordFormListView"
  *  url="view/es/planform/taskplan/recordFormList.jsp"
  * @gpcsoft.view value="recordFormDetailView"
  *  url="view/es/planform/taskplan/recordFormDetail.jsp"
  * @gpcsoft.view value="updateRecordFormFormView"
  *  url="view/es/planform/project/updateRecordFormForm.jsp"
  * @gpcsoft.view value="recordFormFormView"
  *  url="view/es/planform/taskplan/recordFormForm.jsp"
  * @gpcsoft.view value="recordFormFormDetailView"
  *  url="view/es/planform/taskplan/recordFormForAudit.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={RecordForm.class})
@RequestMapping("/RecordFormController.do")//页面请求路径,可修改
public class RecordFormController extends AnnotationMultiController<RecordForm> {

	@Autowired(required=true) @Qualifier("recordFormServiceImpl")
	private RecordFormService recordFormService;
	
	@Autowired(required=true) @Qualifier("projectServiceImpl")
	private ProjectService projectService;	
	
	@Autowired(required=true) @Qualifier("userApiServiceImpl")
	private UserApiService userApiService;
	
	@Autowired(required=true) @Qualifier("projectPlanServiceImpl")
	private ProjectPlanService projectPlanService;
	
	@Autowired(required=true) @Qualifier("attachmentServiceImpl")
	private AttachmentService attachmentService;

	/**
	* FunName: toAddRecordForm
	* Description : 跳转到增加备案表页面
	* @param：request
	* @return ModelAndView
	* @Author: zhouzhanghe
	* @Create Date:  2011-6-21 14:59
	*/
	@SuppressWarnings("unchecked")
	@RequestMapping(params ="method=toAddRecordForm")
	public ModelAndView toAddRecordForm(HttpServletRequest request)throws Exception{
		Map model = new HashMap();
		
		RecordForm recordForm = new RecordForm();
		
		recordForm.setRecFormOrgName(((OrgInfo)AuthenticationHelper.getCurrentUser().getOrgInfo()).getOrgName());
		recordForm.setRecFormOrgId((OrgInfo)AuthenticationHelper.getCurrentUser().getOrgInfo());
		recordForm.setRecReserveUserName(AuthenticationHelper.getCurrentUser().getUsername());
		recordForm.setRecReserveUserId(AuthenticationHelper.getCurrentUser());
		
		model.put("recordForm", recordForm);
		return new ModelAndView("recordFormFormView",model);	
	}
	
	/**
	* FunName: addRecordForm
	* Description : 保存备案表
	* @param：request
	* @return ModelAndView
	* @Author: zhouzhanghe
	* @Create Date:  2011-6-21 14:59
	*/
	@SuppressWarnings("unchecked")
	@RequestMapping(params ="method=addRecordForm")
	public ModelAndView addRecordForm(HttpServletRequest request, RecordForm recordForm, ListBind list)throws Exception{
		Map model = new HashMap();
		
    	MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
    	
    	/*上传附件*/
		if(((CommonsMultipartFile) multipartRequest.getFile("tendererProve1")).getSize() != 0){
			Object o=AttachmentUtil.uploadFile(request,"tendererProve1");
			if(o instanceof GpcBaseObject){
				Attachment attachment1 = (Attachment)o;
				attachmentService.saveAttachment((Attachment)o);
				recordForm.setTendererProve(attachment1.getRelationId());
			}
		}
		if(((CommonsMultipartFile) multipartRequest.getFile("projApproval1")).getSize() != 0){
			Object o1=AttachmentUtil.uploadFile(request,"projApproval1");
			if(o1 instanceof GpcBaseObject){
				Attachment attachment2 = (Attachment)o1;
				attachmentService.saveAttachment((Attachment)o1);
				recordForm.setProjApproval(attachment2.getRelationId());
			}
		}
		if(((CommonsMultipartFile) multipartRequest.getFile("fundsProof1")).getSize() != 0){
			Object o2=AttachmentUtil.uploadFile(request,"fundsProof1");
			if(o2 instanceof GpcBaseObject){
				Attachment attachment3 = (Attachment)o2;
				attachmentService.saveAttachment((Attachment)o2);
				recordForm.setFundsProof(attachment3.getRelationId());
			}
		}
		if(((CommonsMultipartFile) multipartRequest.getFile("approveUnit1")).getSize() != 0){
			Object o2=AttachmentUtil.uploadFile(request,"approveUnit1");
			if(o2 instanceof GpcBaseObject){
				Attachment attachment3 = (Attachment)o2;
				attachmentService.saveAttachment((Attachment)o2);
				recordForm.setApproveUnit(attachment3.getRelationId());
			}
		}
		if(((CommonsMultipartFile) multipartRequest.getFile("designUnit1")).getSize() != 0){
			Object o2=AttachmentUtil.uploadFile(request,"designUnit1");
			if(o2 instanceof GpcBaseObject){
				Attachment attachment3 = (Attachment)o2;
				attachmentService.saveAttachment((Attachment)o2);
				recordForm.setDesignUnit(attachment3.getRelationId());
			}
		}
		if(((CommonsMultipartFile) multipartRequest.getFile("performProcedure1")).getSize() != 0){
			Object o2=AttachmentUtil.uploadFile(request,"performProcedure1");
			if(o2 instanceof GpcBaseObject){
				Attachment attachment3 = (Attachment)o2;
				attachmentService.saveAttachment((Attachment)o2);
				recordForm.setPerformProcedure(attachment3.getRelationId());
			}
		}

		
		/*建立保存对象双向关联*/
		if(list.getSets() != null && list.getSets().size() > 0)
			for(Iterator<ProjMember> itr = list.getSets().iterator(); itr.hasNext();)
				itr.next().setRecordForm(recordForm);
		recordForm.setProjMembers(list.getSets());
		
		recordFormService.save(recordForm);
		return new ModelAndView("jsonView",model);	
	}
	
	
	/** 
	 * FuncName : saveRecordForm
	 * Description :  保存程招标投标登记表
	 * Create Date: 2011-12-21下午04:33:32 by yangx  
	 * Modified Date: 2011-12-21下午04:33:32 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params ="method=saveRecordForm")
	public ModelAndView saveRecordForm(HttpServletRequest request, RecordForm recordForm,SessionStatus status)throws Exception{
		User user = AuthenticationHelper.getCurrentUser(true);
		OrgInfo orgInfo = (OrgInfo)user.getOrgInfo();
		recordForm.setRecFormOrgId(orgInfo);
		recordForm.setRecFormOrgName(orgInfo.getOrgName());
		recordForm.setCreateUser(AuthenticationHelper.getCurrentUser(true));
		recordForm.setAuditStatus(AuditStatusEnum.WAIT_AUDIT);
		recordFormService.saveRecordFormAndProject(recordForm);
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/**
	 * FuncName:saveReturnTaskplanByAgent
	 * Description :到达建筑工程招标备案书form页面
	 * @param    request
	 * @return  ModelAndView
	 * @author shenjz
	 * @Create Date: 2010-10-25下午03:39:17 
	 * @Modifier:shenjz
	 * @Modified Date: 2010-10-25下午03:39:17  
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params ="method=toCreateRecordFormJz")
	public ModelAndView toCreateRecordFormJz(HttpServletRequest request)throws Exception{
		 	Map model = new HashMap();
			String ebuyMethodCN = URLDecoder.decode(request.getParameter("ebuyMethodCN"), "UTF-8");
			String taskTypeCN = URLDecoder.decode(request.getParameter("taskTypeCN"), "UTF-8");
			model.put("projCode", projectService.createProjectCodeByQO(null, request.getParameter("ebuyMethod")));
			model.put("ebuyMethod",request.getParameter("ebuyMethod"));//采购方式
			model.put("ebuyMethodCN",ebuyMethodCN);//采购方式
			model.put("taskType",request.getParameter("taskType"));//类型
			model.put("taskTypeCN",taskTypeCN);//类型
			String taskPlanSubIds = request.getParameter("taskPlanSubIds");
			if (taskPlanSubIds!=null&&!"".equals(taskPlanSubIds)) {
				model.put("taskPlanSubIds",taskPlanSubIds);
			}else {
				model.put("taskPlanSubIds","-1");
			}
			User user = AuthenticationHelper.getCurrentUser();//获得当前人所在部门的所有员工
			List<Employee> empList = userApiService.getEmpListByCurUserOrgId(user.getEmp().getCompany().getObjId());
			int j = 0;
			for(int i=0;i<empList.size();i++){
				if(empList.get(i).getObjId().equals(user.getEmp().getObjId())){
					j=i;
				}
			}
			if(j!=0){//用以将当前登录人的信息放在集合第一位
				empList.set(j, empList.get(0));
				empList.set(0, user.getEmp());
			}
			model.put("empList",empList);
			return new ModelAndView("recordFormFormViewJz",model);	
	}
	
	/**
	 * FuncName: toUpdateRecordFormJz
	 * Description :  到达建筑工程确定底价form页面
	 * @param 
	 * @param request
	 * @return
	 * @throws Exception ModelAndView
	 * @author: shenjz
	 * @Create Date:2011-5-23  下午03:32:58
	 * @Modifier: shenjz
	 * @Modified Date:2011-5-23  下午03:32:58
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params ="method=toUpdateRecordFormJz")
	public ModelAndView toUpdateRecordFormJz(HttpServletRequest request)throws Exception{
		 	Map model = new HashMap();
		 	RecordForm recordForm = recordFormService.getRecordFormByPrjId(request.getParameter("projectId"));
		 	model.put("recordForm", recordForm);
			return new ModelAndView("updateRecordFormFormViewJz",model);	
	}
	
	/**
	 * FuncName: updateRecordFormJz
	 * Description :  确定建筑工程底价
	 * @param 
	 * @param request
	 * @return
	 * @throws Exception ModelAndView
	 * @author: shenjz
	 * @Create Date:2011-5-23  下午03:32:58
	 * @Modifier: shenjz
	 * @Modified Date:2011-5-23  下午03:32:58
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params ="method=updateRecordFormJz")
	public ModelAndView updateRecordFormJz(HttpServletRequest request)throws Exception{
		 	Map model = new HashMap();
		 	RecordForm recordForm = recordFormService.get(request.getParameter("objId"));
		 	recordForm.setReservePrice(request.getParameter("reservePrice"));
			recordFormService.updateRecordForm(recordForm);
			return new ModelAndView(Constants.JSON_VIEW,model);	
	}
	
	/**
	 * FuncName:saveReturnTaskplanByAgent
	 * Description :到达产权交易招标备案书form页面
	 * @param    request
	 * @return  ModelAndView
	 * @author shenjz
	 * @Create Date: 2010-10-25下午03:39:17 
	 * @Modifier:shenjz
	 * @Modified Date: 2010-10-25下午03:39:17  
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params ="method=toCreateRecordFormCq")
	public ModelAndView toCreateRecordFormCq(HttpServletRequest request)throws Exception{
		 	Map model = new HashMap();
			String ebuyMethodCN = URLDecoder.decode(request.getParameter("ebuyMethodCN"), "UTF-8");
			String taskTypeCN = URLDecoder.decode(request.getParameter("taskTypeCN"), "UTF-8");
			model.put("projCode", projectService.createProjectCodeByQO(null, request.getParameter("ebuyMethod")));
			model.put("ebuyMethod",request.getParameter("ebuyMethod"));//采购方式
			model.put("ebuyMethodCN",ebuyMethodCN);//采购方式
			model.put("taskType",request.getParameter("taskType"));//类型
			model.put("taskTypeCN",taskTypeCN);//类型
			String taskPlanSubIds = request.getParameter("taskPlanSubIds");
			if (taskPlanSubIds!=null&&!"".equals(taskPlanSubIds)) {
				model.put("taskPlanSubIds",taskPlanSubIds);
			}else {
				model.put("taskPlanSubIds","-1");
			}
			User user = AuthenticationHelper.getCurrentUser();//获得当前人所在部门的所有员工
			List<Employee> empList = userApiService.getEmpListByCurUserOrgId(user.getEmp().getCompany().getObjId());
			int j = 0;
			for(int i=0;i<empList.size();i++){
				if(empList.get(i).getObjId().equals(user.getEmp().getObjId())){
					j=i;
				}
			}
			if(j!=0){//用以将当前登录人的信息放在集合第一位
				empList.set(j, empList.get(0));
				empList.set(0, user.getEmp());
			}
			model.put("empList",empList);
			return new ModelAndView("recordFormFormViewCq",model);	
	}
	
	/**
	 * FuncName: toUpdateRecordFormCq
	 * Description :  到达产权交易确定底价form页面
	 * @param 
	 * @param request
	 * @return
	 * @throws Exception ModelAndView
	 * @author: shenjz
	 * @Create Date:2011-5-23  下午03:36:07
	 * @Modifier: shenjz
	 * @Modified Date:2011-5-23  下午03:36:07
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params ="method=toUpdateRecordFormCq")
	public ModelAndView toUpdateRecordFormCq(HttpServletRequest request)throws Exception{
		 	Map model = new HashMap();
		 	RecordForm recordForm = recordFormService.getRecordFormByPrjId(request.getParameter("projectId"));
		 	model.put("recordForm", recordForm);
			return new ModelAndView("updateRecordFormFormViewCq",model);	
	}
	
	/**
	 * FuncName: updateRecordFormCq
	 * Description :  确定产权交易底价
	 * @param 
	 * @param request
	 * @return
	 * @throws Exception ModelAndView
	 * @author: shenjz
	 * @Create Date:2011-5-23  下午03:32:58
	 * @Modifier: shenjz
	 * @Modified Date:2011-5-23  下午03:32:58
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params ="method=updateRecordFormCq")
	public ModelAndView updateRecordFormCq(HttpServletRequest request)throws Exception{
		 	Map model = new HashMap();
		 	RecordForm recordForm = recordFormService.get(request.getParameter("objId"));
		 	recordForm.setReservePrice(request.getParameter("reservePrice"));
			recordFormService.updateRecordForm(recordForm);
			return new ModelAndView(Constants.JSON_VIEW,model);	
	}
	/**
	 * FuncName:saveReturnTaskplanByAgent
	 * Description :到达土地交易招标备案书form页面
	 * @param    request
	 * @return  ModelAndView
	 * @author shenjz
	 * @Create Date: 2010-10-25下午03:39:17 
	 * @Modifier:shenjz
	 * @Modified Date: 2010-10-25下午03:39:17  
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params ="method=toCreateRecordFormTd")
	public ModelAndView toCreateRecordFormTd(HttpServletRequest request)throws Exception{
		 	Map model = new HashMap();
			String ebuyMethodCN = URLDecoder.decode(request.getParameter("ebuyMethodCN"), "UTF-8");
			String taskTypeCN = URLDecoder.decode(request.getParameter("taskTypeCN"), "UTF-8");
			model.put("projCode", projectService.createProjectCodeByQO(null, request.getParameter("ebuyMethod")));
			model.put("ebuyMethod",request.getParameter("ebuyMethod"));//采购方式
			model.put("ebuyMethodCN",ebuyMethodCN);//采购方式
			model.put("taskType",request.getParameter("taskType"));//类型
			model.put("taskTypeCN",taskTypeCN);//类型
			String taskPlanSubIds = request.getParameter("taskPlanSubIds");
			if (taskPlanSubIds!=null&&!"".equals(taskPlanSubIds)) {
				model.put("taskPlanSubIds",taskPlanSubIds);
			}else {
				model.put("taskPlanSubIds","-1");
			}
			User user = AuthenticationHelper.getCurrentUser();//获得当前人所在部门的所有员工
			List<Employee> empList = userApiService.getEmpListByCurUserOrgId(user.getEmp().getCompany().getObjId());
			int j = 0;
			for(int i=0;i<empList.size();i++){
				if(empList.get(i).getObjId().equals(user.getEmp().getObjId())){
					j=i;
				}
			}
			if(j!=0){//用以将当前登录人的信息放在集合第一位
				empList.set(j, empList.get(0));
				empList.set(0, user.getEmp());
			}
			model.put("empList",empList);
			return new ModelAndView("recordFormFormViewTd",model);	
	}
	
	/**
	 * FuncName: toUpdateRecordFormTd
	 * Description :  到达土地交易确定底价form页面
	 * @param 
	 * @param request
	 * @return
	 * @throws Exception ModelAndView
	 * @author: shenjz
	 * @Create Date:2011-5-23  下午03:36:31
	 * @Modifier: shenjz
	 * @Modified Date:2011-5-23  下午03:36:31
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params ="method=toUpdateRecordFormTd")
	public ModelAndView toUpdateRecordFormTd(HttpServletRequest request)throws Exception{
		 	Map model = new HashMap();
		 	RecordForm recordForm = recordFormService.getRecordFormByPrjId(request.getParameter("projectId"));
		 	model.put("recordForm", recordForm);
			return new ModelAndView("updateRecordFormFormViewTd",model);	
	}
	/**
	 * FuncName: updateRecordFormTd
	 * Description :  确定土地交易底价
	 * @param 
	 * @param request
	 * @return
	 * @throws Exception ModelAndView
	 * @author: shenjz
	 * @Create Date:2011-5-23  下午03:32:58
	 * @Modifier: shenjz
	 * @Modified Date:2011-5-23  下午03:32:58
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params ="method=updateRecordFormTd")
	public ModelAndView updateRecordFormTd(HttpServletRequest request)throws Exception{
		 	Map model = new HashMap();
		 	RecordForm recordForm = recordFormService.get(request.getParameter("objId"));
		 	recordForm.setReservePrice(request.getParameter("reservePrice"));
		 	recordFormService.updateRecordForm(recordForm);
			return new ModelAndView(Constants.JSON_VIEW,model);	
	}
	/**
	 * FuncName:saveRecordFormJz
	 * Description :保存建筑工程招标备案书+立项
	 * @param    request
	 * @return  ModelAndView
	 * @author shenjz
	 * @Create Date: 2010-10-25下午03:39:17 
	 * @Modifier:liuke
	 * @Modified Date: 2010-10-25下午03:39:17  
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params ="method=saveRecordFormJz")
	public ModelAndView saveRecordFormJz(HttpServletRequest request,RecordForm recordForm,Project project,String taskPlanSubIds,SessionStatus status)throws Exception{
		Map model = new HashMap();
		taskPlanSubIds = taskPlanSubIds.replace("-1,", "");
		taskPlanSubIds = taskPlanSubIds.replace("-1", "");
		if(taskPlanSubIds.length() <= 1){//如果没接收到申报书条目，需要提示
			throw new EsException(messageSource.getMessage(EsExceptionEnum.TASKPLANSUB_ISSELECT));
		}
		User user = AuthenticationHelper.getCurrentUser();
		OrgInfo orgInfo = (OrgInfo)user.getOrgInfo();
		project.setAgencies(orgInfo);
	    if(null!=project.getObjId()) {
	    	project.setObjId(null);//删除项目主键
	    }
	    project.setTenderType(ProjectEnum.JZGC);
	    project.setProjImplStatus("00");
	    project.setProjImplStatusCN("00");
	    project.setUseStatus(CommonEnum.USER_STATUS_TEMP);
	    project.setSysFlag(SysInfo.getInstance().getSysFlag());//添加系统标示 modify by yangx
	    projectService.saveECPJZGCProjectByTaskPlanSubs(project, taskPlanSubIds);
		projectPlanService.createProjectPlan(project.getObjId());//因为后续还有创建工作计划功能，故这里不用考虑事务问题
		//recordForm.setProject(project);
		//recordFormService.save(recordForm);
		status.setComplete();
		model.put("projectId", project.getObjId());
		return new ModelAndView(Constants.JSON_VIEW,model);	
	}
	
	/**
	 * FuncName:saveRecordFormTd
	 * Description :保存土地交易招标备案书+立项
	 * @param    request
	 * @return  ModelAndView
	 * @author shenjz
	 * @Create Date: 2010-10-25下午03:39:17 
	 * @Modifier:liuke
	 * @Modified Date: 2010-10-25下午03:39:17  
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params ="method=saveRecordFormTd")
	public ModelAndView saveRecordFormTd(HttpServletRequest request,RecordForm recordForm,Project project,String taskPlanSubIds,SessionStatus status)throws Exception{
		Map model = new HashMap();
		taskPlanSubIds = taskPlanSubIds.replace("-1,", "");
		taskPlanSubIds = taskPlanSubIds.replace("-1", "");
		if(taskPlanSubIds.length() <= 1){//如果没接收到申报书条目，需要提示
			throw new EsException(messageSource.getMessage(EsExceptionEnum.TASKPLANSUB_ISSELECT));
		}
		User user = AuthenticationHelper.getCurrentUser();
		OrgInfo orgInfo = (OrgInfo)user.getOrgInfo();
		project.setAgencies(orgInfo);
	    if(null!=project.getObjId()) {
	    	project.setObjId(null);//删除项目主键
	    }
	    project.setProjImplStatus("00");
	    project.setProjImplStatusCN("00");
	    project.setUseStatus(CommonEnum.USER_STATUS_TEMP);
	    project.setSysFlag(SysInfo.getInstance().getSysFlag());//添加系统标示 modify by yangx
	    try {
			Boolean isUploadFile1 = new Boolean(request.getParameter("isUploadFile1"));
			Boolean isUploadFile2 = new Boolean(request.getParameter("isUploadFile2"));
			Boolean isUploadFile3 = new Boolean(request.getParameter("isUploadFile3"));
			if(isUploadFile1){
				Object o=AttachmentUtil.uploadFile(request,"tendererProve1");
				if(o instanceof GpcBaseObject){
					Attachment attachment1 = (Attachment)o;
					attachmentService.saveAttachment((Attachment)o);
					recordForm.setTendererProve(attachment1.getRelationId());
				}
			}
			if(isUploadFile2){
				Object o1=AttachmentUtil.uploadFile(request,"projApproval1");
				if(o1 instanceof GpcBaseObject){
					Attachment attachment2 = (Attachment)o1;
					attachmentService.saveAttachment((Attachment)o1);
					recordForm.setProjApproval(attachment2.getRelationId());
				}
			}
			if(isUploadFile3){
				Object o2=AttachmentUtil.uploadFile(request,"fundsProof1");
				if(o2 instanceof GpcBaseObject){
					Attachment attachment3 = (Attachment)o2;
					attachmentService.saveAttachment((Attachment)o2);
					recordForm.setFundsProof(attachment3.getRelationId());
				}
			}
		} catch (Exception e) {
			model.put("failure", true);
			model.put("result", e.getMessage());
			return new ModelAndView(Constants.JSON_VIEW,model);	
		}
	    projectService.saveECPProjectByTaskPlanSubs(project, taskPlanSubIds);
		projectPlanService.createProjectPlan(project.getObjId());//因为后续还有创建工作计划功能，故这里不用考虑事务问题
		recordForm.setProject(project);
		recordFormService.save(recordForm);
		status.setComplete();
		model.put("projectId", project.getObjId());
		return new ModelAndView(Constants.JSON_VIEW,model);	
	}
	
	/**
	 * FuncName:saveRecordFormCq
	 * Description :保存产权交易招标备案书+立项
	 * @param    request
	 * @return  ModelAndView
	 * @author shenjz
	 * @Create Date: 2010-10-25下午03:39:17 
	 * @Modifier:liuke
	 * @Modified Date: 2010-10-25下午03:39:17  
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params ="method=saveRecordFormCq")
	public ModelAndView saveRecordFormCq(HttpServletRequest request,RecordForm recordForm,Project project,String taskPlanSubIds,SessionStatus status)throws Exception{
		Map model = new HashMap();
		taskPlanSubIds = taskPlanSubIds.replace("-1,", "");
		taskPlanSubIds = taskPlanSubIds.replace("-1", "");
		if(taskPlanSubIds.length() <= 1){//如果没接收到申报书条目，需要提示
			throw new EsException(messageSource.getMessage(EsExceptionEnum.TASKPLANSUB_ISSELECT));
		}
		User user = AuthenticationHelper.getCurrentUser();
		OrgInfo orgInfo = (OrgInfo)user.getOrgInfo();
		project.setAgencies(orgInfo);
	    if(null!=project.getObjId()) {
	    	project.setObjId(null);//删除项目主键
	    }
	    project.setProjImplStatus("00");
	    project.setProjImplStatusCN("00");
	    project.setUseStatus(CommonEnum.USER_STATUS_TEMP);
	    project.setSysFlag(SysInfo.getInstance().getSysFlag());//添加系统标示 modify by yangx
	    try {
			Boolean isUploadFile1 = new Boolean(request.getParameter("isUploadFile1"));
			Boolean isUploadFile2 = new Boolean(request.getParameter("isUploadFile2"));
			Boolean isUploadFile3 = new Boolean(request.getParameter("isUploadFile3"));
			if(isUploadFile1){
				Object o=AttachmentUtil.uploadFile(request,"tendererProve1");
				if(o instanceof GpcBaseObject){
					Attachment attachment1 = (Attachment)o;
					attachmentService.saveAttachment((Attachment)o);
					recordForm.setTendererProve(attachment1.getRelationId());
				}
			}
			if(isUploadFile2){
				Object o1=AttachmentUtil.uploadFile(request,"projApproval1");
				if(o1 instanceof GpcBaseObject){
					Attachment attachment2 = (Attachment)o1;
					attachmentService.saveAttachment((Attachment)o1);
					recordForm.setProjApproval(attachment2.getRelationId());
				}
			}
			if(isUploadFile3){
				Object o2=AttachmentUtil.uploadFile(request,"fundsProof1");
				if(o2 instanceof GpcBaseObject){
					Attachment attachment3 = (Attachment)o2;
					attachmentService.saveAttachment((Attachment)o2);
					recordForm.setFundsProof(attachment3.getRelationId());
				}
			}
		} catch (Exception e) {
			model.put("failure", true);
			model.put("result", e.getMessage());
			return new ModelAndView(Constants.JSON_VIEW,model);	
		}
	    projectService.saveECPProjectByTaskPlanSubs(project, taskPlanSubIds);
		projectPlanService.createProjectPlan(project.getObjId());//因为后续还有创建工作计划功能，故这里不用考虑事务问题
		recordForm.setProject(project);
		recordFormService.save(recordForm);
		status.setComplete();
		model.put("projectId", project.getObjId());
		return new ModelAndView(Constants.JSON_VIEW,model);	
	}
	/**
	 * FuncName: toUpdateRecordForm
	 * Description : 到达备案书form修改页面
	 * @param 
	 * @param request
	 * @return
	 * @throws Exception ModelAndView
	 * @author: shenjz
	 * @Create Date:2011-5-25  上午10:51:56
	 * @Modifier: shenjz
	 * @Modified Date:2011-5-25  上午10:51:56
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params ="method=toUpdateRecordForm")
	public ModelAndView toUpdateRecordForm(HttpServletRequest request)throws Exception{
		 	Map model = new HashMap();
		 	RecordForm recordForm = recordFormService.getRecordFormByPrjId(request.getParameter("projectId"));
		 	model.put("recordForm", recordForm);
		 	if(recordForm.getTendererProve()!=null){
				List<Attachment> attachmentList = attachmentService.getAttachmentsByRealID(recordForm.getTendererProve());
				if (null != attachmentList && !attachmentList.isEmpty()) {
					model.put("attrId1", attachmentList.get(0).getObjId());
					model.put("tendererProve", attachmentList.get(0).getViewName());
				}
			}
		 	if(recordForm.getProjApproval()!=null){
				List<Attachment> attachmentList = attachmentService.getAttachmentsByRealID(recordForm.getProjApproval());
				if (null != attachmentList && !attachmentList.isEmpty()) {
					model.put("attrId2", attachmentList.get(0).getObjId());
					model.put("projApproval", attachmentList.get(0).getViewName());
				}
			}
		 	if(recordForm.getFundsProof()!=null){
				List<Attachment> attachmentList = attachmentService.getAttachmentsByRealID(recordForm.getFundsProof());
				if (null != attachmentList && !attachmentList.isEmpty()) {
					model.put("attrId3", attachmentList.get(0).getObjId());
					model.put("fundsProof", attachmentList.get(0).getViewName());
				}
			}
			return new ModelAndView("updateRecordFormFormView",model);	
	}
	
	/**
	 * FuncName: toUpdateRecordForm
	 * Description : 到达备案书明细页面
	 * @param 
	 * @param request
	 * @return
	 * @throws Exception ModelAndView
	 * @author: shenjz
	 * @Create Date:2011-5-25  上午10:51:56
	 * @Modifier: shenjz
	 * @Modified Date:2011-5-25  上午10:51:56
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params ="method=toRecordFormDetail")
	public ModelAndView toRecordFormDetail(HttpServletRequest request)throws Exception{
		 	Map model = new HashMap();
		 	RecordForm recordForm = recordFormService.getRecordFormByPrjId(request.getParameter("projectId"));
		 	model.put("recordForm", recordForm);
		 	if(recordForm.getTendererProve()!=null){
				List<Attachment> attachmentList = attachmentService.getAttachmentsByRealID(recordForm.getTendererProve());
				if (null != attachmentList && !attachmentList.isEmpty()) {
					model.put("attrId1", attachmentList.get(0).getObjId());
					model.put("tendererProve", attachmentList.get(0).getViewName());
				}
			}
		 	if(recordForm.getProjApproval()!=null){
				List<Attachment> attachmentList = attachmentService.getAttachmentsByRealID(recordForm.getProjApproval());
				if (null != attachmentList && !attachmentList.isEmpty()) {
					model.put("attrId2", attachmentList.get(0).getObjId());
					model.put("projApproval", attachmentList.get(0).getViewName());
				}
			}
		 	if(recordForm.getFundsProof()!=null){
				List<Attachment> attachmentList = attachmentService.getAttachmentsByRealID(recordForm.getFundsProof());
				if (null != attachmentList && !attachmentList.isEmpty()) {
					model.put("attrId3", attachmentList.get(0).getObjId());
					model.put("fundsProof", attachmentList.get(0).getViewName());
				}
			}
			return new ModelAndView("recordFormDetailView",model);	
	}
	
	/**
	 * FuncName:saveRecordFormCq
	 * Description :保存产权交易招标备案书+立项
	 * @param    request
	 * @return  ModelAndView
	 * @author shenjz
	 * @Create Date: 2010-10-25下午03:39:17 
	 * @Modifier:liuke
	 * @Modified Date: 2010-10-25下午03:39:17  
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params ="method=updateRecordForm")
	public ModelAndView updateRecordForm(HttpServletRequest request,RecordForm recordForm,SessionStatus status)throws Exception{
		Map model = new HashMap();
		Boolean isUploadFile1 = new Boolean(request.getParameter("isUploadFile1"));
		Boolean isUploadFile2 = new Boolean(request.getParameter("isUploadFile2"));
		Boolean isUploadFile3 = new Boolean(request.getParameter("isUploadFile3"));
		if(isUploadFile1){
			Object o=AttachmentUtil.uploadFile(request,"tendererProve1");
			if(o instanceof GpcBaseObject){
				Attachment attachment1 = (Attachment)o;
				attachmentService.saveAttachment((Attachment)o);
				recordForm.setTendererProve(attachment1.getRelationId());
			}
		}
		if(isUploadFile2){
			Object o1=AttachmentUtil.uploadFile(request,"projApproval1");
			if(o1 instanceof GpcBaseObject){
				Attachment attachment2 = (Attachment)o1;
				attachmentService.saveAttachment((Attachment)o1);
				recordForm.setProjApproval(attachment2.getRelationId());
			}
		}
		if(isUploadFile3){
			Object o2=AttachmentUtil.uploadFile(request,"fundsProof1");
			if(o2 instanceof GpcBaseObject){
				Attachment attachment3 = (Attachment)o2;
				attachmentService.saveAttachment((Attachment)o2);
				recordForm.setFundsProof(attachment3.getRelationId());
			}
		}
		recordFormService.save(recordForm);
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW,model);	
	}
	
	/**
	* FunName: toAddRecordForm
	* Description : 删除备案记录
	* @param：request
	* @return ModelAndView
	* @Author: shengn
	* @Create Date:  2011-09-14 10:53
	*/
	@SuppressWarnings("unchecked")
	@RequestMapping(params ="method=toDelRecordForm")
	public ModelAndView toDelRecordForm(HttpServletRequest request)throws Exception{
		Map model = new HashMap();
		
		String objIds = request.getParameter("objIds")==null?"":request.getParameter("objIds");
		String[] ids = objIds.split(",");
		if(ids.length>0){
			recordFormService.remove(ids, RecordForm.class);	
		}
		model.put("result", "删除成功!");
		return new ModelAndView(Constants.JSON_VIEW,model);	
	}
	
	
	/** 
	 * FuncName : toShowRecordForm
	 * Description :  查看招标投标登记表
	 * Create Date: 2011-12-21下午11:12:58 by yangx  
	 * Modified Date: 2011-12-21下午11:12:58 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params ="method=toShowRecordForm")
	public ModelAndView toShowRecordForm(HttpServletRequest request)throws Exception{
		Map<String,Object> model = new HashMap<String,Object>();
		String recordFormId = request.getParameter("objId");
		RecordForm recordForm = recordFormService.get(recordFormId);
		model.put("recordForm",recordForm);
		return new ModelAndView("recordFormDetailView",model);	
	}
	
	
	/** 
	 * FuncName : toModifyRecordForm
	 * Description :  到达投标
	 * Create Date: 2011-12-21下午11:27:55 by yangx  
	 * Modified Date: 2011-12-21下午11:27:55 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params ="method=toModifyRecordForm")
	public ModelAndView toModifyRecordForm(HttpServletRequest request)throws Exception{
		Map<String,Object> model = new HashMap<String,Object>();
		String recordFormId = request.getParameter("objId");
		RecordForm recordForm = recordFormService.get(recordFormId);
		model.put("recordForm",recordForm);
		return new ModelAndView("recordFormFormView",model);	
	}
	
	/** 
	 * FuncName : toRecordFormAuditForm
	 * Description :  跳转审核页面
	 * Create Date: 2011-12-22下午05:55:44 by yangx  
	 * Modified Date: 2011-12-22下午05:55:44 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params ="method=toRecordFormAuditForm")
	public ModelAndView toRecordFormAuditForm(HttpServletRequest request)throws Exception{
		Map<String,Object> model = new HashMap<String,Object>();
		String recordFormId = request.getParameter("objId");
		RecordForm recordForm = recordFormService.get(recordFormId);
		model.put("recordForm",recordForm);
		return new ModelAndView("recordFormFormDetailView",model);
	}
	
	
	/** 
	 * FuncName : auditRecordForm
	 * Description :  审核招标投标登记表
	 * Create Date: 2011-12-22下午06:12:26 by yangx  
	 * Modified Date: 2011-12-22下午06:12:26 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params ="method=auditRecordForm")
	public ModelAndView auditRecordForm(HttpServletRequest request,SessionStatus status)throws Exception{
		String recordFormId = request.getParameter("objId");
		String auditStatus = request.getParameter("auditStatus");
		String opinion = request.getParameter("opinion");
		RecordForm recordForm = recordFormService.get(recordFormId);
		recordForm.setAuditStatus(auditStatus);
		recordFormService.saveRecordFormForAudit(recordForm,opinion);
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW);
	}
}
