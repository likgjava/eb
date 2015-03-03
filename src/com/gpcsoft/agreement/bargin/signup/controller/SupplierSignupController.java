package com.gpcsoft.agreement.bargin.signup.controller;

import java.util.Date;
import java.util.HashMap;
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

import com.gpcsoft.agreement.bargin.signup.service.SignUprecordServiceXygh;
import com.gpcsoft.bizplatform.base.message.CustomerMessage;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.organization.service.OrgInfoService;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.dao.hibernate.query.QueryProjections;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.project.domain.ProjProcessStatusEnum;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.service.ProjectService;
import com.gpcsoft.epp.signuprecord.domain.SignUprecord;
import com.gpcsoft.epp.signuprecord.service.SignUprecordService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.pubservice.application.concern.service.ConcernService;
import com.gpcsoft.pubservice.utils.MessageUtil;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.common.assist.service.FreeMarkerService;

/**
  *  @gpcsoft.view value="toSupplierSignUpView"
  *  url="view/agreement/bargin/signup/a_supplier_signup_div.jsp"
  *  
  *  @gpcsoft.view value="toAuditSignUprecordStatusView"
  *  url="view/agreement/bargin/signup/a_supplier_signup_confirm.jsp"
  *  
  *  @gpcsoft.view value="toMySignUpProjectView"
  *  url="view/agreement/bargin/signup/a_my_signup_project.jsp"
  *  
  *	 @gpcsoft.view value="toMyProjectSupplierView"
  *  url="view/agreement/bargin/project/bargain/my_project_supplier.jsp"
  *   
  *  @gpcsoft.view value="toSupplierSignUpDetailView"
  *  url="view/agreement/bargin/signup/a_supplier_signup_view_div.jsp"
  */

@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={SignUprecord.class})
@RequestMapping("/SupplierSignupController.do")//页面请求路径,可修改
public class SupplierSignupController extends AnnotationMultiController<SignUprecord> {
	
	@Autowired(required=true) @Qualifier("signUprecordServiceImpl")
	private SignUprecordService signUprecordService;
	
	@Autowired(required=true) @Qualifier("projectServiceImpl")
	private ProjectService projectServiceImpl;
	
	@Autowired(required=true) @Qualifier("signUprecordServiceXyghImpl")
	private SignUprecordServiceXygh signUprecordServiceXygh;
	
	@Autowired(required=true) @Qualifier("orgInfoServiceImpl")
	private OrgInfoService orgInfoService;
	
	@Autowired(required=true) @Qualifier("concernServiceImpl")
	private ConcernService concernService;
	
	@Autowired(required=true) @Qualifier("freeMarkerServiceImpl")
	private FreeMarkerService freeMarkerService;
	
	/** 
	 * Description :  跳转至供应商报名页面
	 * Create Date: 2010-10-13上午10:32:27 by yucy  Modified Date: 2010-10-13上午10:32:27 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toSupplierSignUp")
	public ModelAndView toSupplierSignUp(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>(); 
		User currentUser = AuthenticationHelper.getCurrentUser(true);
		SignUprecord signUprecord = new SignUprecord();
		signUprecord.setProject(projectServiceImpl.get(request.getParameter("projectId")));
		signUprecord.setSupplier(orgInfoService.getLastedOrgInfo(currentUser.getEmp().getCompany().getObjId(), true));
		signUprecord.setSupplierName(orgInfoService.getLastedOrgInfo(currentUser.getEmp().getCompany().getObjId(), true).getOrgName());
		signUprecord.setLinker(currentUser.getEmp().getName());
		signUprecord.setLinkerTel(currentUser.getEmp().getMobile());
		signUprecord.setIdCard(currentUser.getEmp().getIdCard());
		signUprecord.setAddress(currentUser.getEmp().getAddress());
		signUprecord.setZipCode(currentUser.getEmp().getZipCode());
		model.put("signUprecord", signUprecord);
		model.put("currentEmpId", currentUser.getEmp().getObjId());
		
		return new ModelAndView("toSupplierSignUpView",model);
	}
	
	/** 
	 * Description :  跳转至供应商报名详情页面
	 * Create Date: 2010-10-13上午10:32:27 by yucy  Modified Date: 2010-10-13上午10:32:27 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toSupplierSignUpDetail")
	public ModelAndView toSupplierSignUpDetail(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>(); 
		SignUprecord signUprecord = signUprecordService.get(request.getParameter("objId"));
		
		if(signUprecord==null){
			User user  = new User();
			OrgInfo orgInfo = new OrgInfo();
			orgInfo.setObjId(request.getParameter("objId"));
			user.setOrgInfo(orgInfo);
			signUprecord = signUprecordService.getSignUprecordBySupplierId(request.getParameter("projectId"),  user);
		}
		
		model.put("signUprecord", signUprecord);
		return new ModelAndView("toSupplierSignUpDetailView",model);
	}
	
	/** 
	 * Description :  获取报名数据
	 * Create Date: 2011-5-22下午07:41:50 by yucy  Modified Date: 2011-5-22下午07:41:50 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getSupplierSignUp")
	public ModelAndView getSupplierSignUp(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>(); 
		SignUprecord signUprecord = signUprecordService.get(request.getParameter("objId"));
		
		if(signUprecord==null){
			User user  = new User();
			OrgInfo orgInfo = new OrgInfo();
			orgInfo.setObjId(request.getParameter("objId"));
			user.setOrgInfo(orgInfo);
			signUprecord = signUprecordService.getSignUprecordBySupplierId(request.getParameter("projectId"),  user);
		}
		
		model.put("signUprecord", signUprecord);
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	/** 
	 * Description : 跳转到确认报名供应商
	 * Create Date: 2010-10-13上午10:32:27 by yucy  Modified Date: 2010-10-13上午10:32:27 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toAuditSignUprecordStatus")
	public ModelAndView toAuditSignUprecordStatus(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>(); 
		model.put("project", projectServiceImpl.get(request.getParameter("projectId")));
		return new ModelAndView("toAuditSignUprecordStatusView",model);
	}
	
	/** 
	 * Description :  跳转到我报名的项目
	 * Create Date: 2010-10-13下午11:29:42 by yucy  Modified Date: 2010-10-13下午11:29:42 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toMySignUpProject")
	public ModelAndView toMySignUpProject(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>(); 
		model.put("supplierId",orgInfoService.getLastedOrgInfo(AuthenticationHelper.getCurrentUser(true).getEmp().getCompany().getObjId(), true).getObjId());
		return new ModelAndView("toMySignUpProjectView",model);
	}
	
	/** 
	 * Description :  跳转到我的项目
	 * Create Date: 2010-10-22下午04:44:13 by yucy  Modified Date: 2010-10-22下午04:44:13 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toMyProjectSupplier")
	public ModelAndView toMyProjectSupplier(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>(); 
		model.put("supplierId",orgInfoService.getLastedOrgInfo(AuthenticationHelper.getCurrentUser(true).getEmp().getCompany().getObjId(), true).getObjId());
		model.put("ebuyMethod", request.getParameter("ebuyMethod"));
		return new ModelAndView("toMyProjectSupplierView",model);
	}
	
	/** 
	 * Description :  保存供应商报名信息
	 * Create Date: 2010-10-13上午10:32:27 by yucy  Modified Date: 2010-10-13上午10:32:27 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveSupplierSignUp")
	public ModelAndView saveSupplierSignUp(HttpServletRequest request,SignUprecord signUprecord,SessionStatus status) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>(); 
		Map<String, Object> params = new HashMap<String, Object>(); 
		
		signUprecord.setApplyDate(new Date());
		signUprecord.setApplyStatus(enumService.getValueByConstant("bizdata.applyStatus", "APPLY_STATUS_TYPE_YES"));//设置报名状态
		signUprecord.setAuditStatus(enumService.getValueByConstant("bizdata.auditStatus", "AWAIT_EXAM"));
		
		//判断是否新增一条联系人信息
		String type = request.getParameter("type");
		String empId = request.getParameter("currentEmpId");
		params.put("type", type);
		params.put("empId", empId);
		signUprecordServiceXygh.saveSignUprecordXygh(signUprecord,params);
		
		//判断该报名供应商是否为项目发布者的黑名单客户，如果是则向项目发布者发送站内信进行提醒
		Project project = projectServiceImpl.get(signUprecord.getProject().getObjId());
		OrgInfo blackOrgInfo = signUprecord.getSupplier();
		boolean isInBlacklist = concernService.isInBlacklist(project.getBuyersId(), blackOrgInfo.getObjId());
		if(isInBlacklist) {
			Map<String, Object> templateMap = new HashMap<String, Object>();
			templateMap.put("blackOrgName", blackOrgInfo.getOrgName());
			templateMap.put("projectName", project.getProjName());
			templateMap.put("projectId", project.getObjId());
			templateMap.put("contextPath", request.getContextPath());
			//根据模板生成站内信内容
			String contentsStr = "";
			try {
				contentsStr = freeMarkerService.getFreeMarkerContent(messageSource.getMessage(CustomerMessage.BLACKLIST_SIGNUP_MESSAGE_TEMPLATE), templateMap);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//发送站内信
			MessageUtil.sendMessageSystem(new String[]{project.getCreateUser().getObjId()}, 
					messageSource.getMessage(CustomerMessage.BLACKLIST_SIGNUP_MESSAGE_REMIND_TITLE),
					contentsStr, 
					null,
					null,
					false);
		}
		
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	/** 
	 * Description :  判断是否报过名
	 * Create Date: 2010-10-13下午10:09:02 by yucy  Modified Date: 2010-10-13下午10:09:02 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=ifHasSignUp")
	public ModelAndView ifHasSignUp(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>(); 
		Map<String, Object> param = new HashMap<String, Object>(); 

		OrgInfo currentOrg = orgInfoService.getLastedOrgInfo(AuthenticationHelper.getCurrentUser(true).getEmp().getCompany().getObjId(), true);
		param.put("orgId", currentOrg.getObjId());
		param.put("projectId", request.getParameter("projectId"));
		Boolean hasSinUp =  signUprecordServiceXygh.ifHasSignUp(param);
		
		if(hasSinUp){
			model.put(Constants.FAILURE, true);
			model.put(Constants.JSON_RESULT, "该项目您已经报名！");
		}else{
			model.put(Constants.SUCCESS, true);
		}
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	/** 
	 * Description :  确认报名供应商
	 * Create Date: 2010-10-13下午04:11:30 by yucy  Modified Date: 2010-10-13下午04:11:30 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=updateSignUprecordAuditStatus")
	public ModelAndView updateSignUprecordAuditStatus(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>(); 
		Map<String, Object> param = new HashMap<String, Object>(); 
		param.put("signIds", request.getParameter("signIds").split(","));
		if("pass".equals(request.getParameter("auditType"))){
			param.put("auditStatus", enumService.getValueByConstant("bizdata.auditStatus", "PASS_EXAM"));
		}else{
			param.put("auditStatus", enumService.getValueByConstant("bizdata.auditStatus", "NO_PASS_EXAM"));
		}
		signUprecordServiceXygh.updateSignUprecordAuditStatus(param);
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	/** 
	 * Description :  撤销供应商报名
	 * Create Date: 2010-10-14上午12:54:49 by yucy  Modified Date: 2010-10-14上午12:54:49 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=updateSignUprecordAppllyStatus")
	public ModelAndView updateSignUprecordAppllyStatus( HttpServletRequest request, SignUprecord signUprecord ) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>(); 
		Map<String, Object> param = new HashMap<String, Object>(); 
		
		param.put("signUprecord", signUprecord);
		signUprecord.setApplyStatus(enumService.getValueByConstant("bizdata.applyStatus", "APPLY_STATUS_TYPE_NO"));//撤销状态
		Boolean result = signUprecordServiceXygh.updateSignUprecordAppllyStatus(param);
		if(result){
			model.put(Constants.SUCCESS, true);
		}else{
			model.put(Constants.FAILURE, true);
		}
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	/** 
	 * Description :  取出待评价对象(采购人yong)
	 * Create Date: 2010-8-6下午03:20:46 by yucy  Modified Date: 2010-8-6下午03:20:46 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getEvaluateObjectSupplier")   
	public ModelAndView getEvaluateObjectSupplier(String projectId) throws Exception {
		Map<String, Object> model  = signUprecordServiceXygh.getEvaluateObjectSupplier(projectId);
	    return new ModelAndView(Constants.JSON_VIEW, model);
	}
	

	/** 
	 * Description :  供应商用户取出被投诉举报报名机构(其他供应商和项目采购人)
	 * Create Date: 2010-10-29下午01:53:25 by dongcl  Modified Date: 2010-10-29下午01:53:25 by dongcl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getComplainOrgInfo")   
	public ModelAndView getComplainOrgInfo(String projectId) throws Exception {
		Map<String, Object> model  = signUprecordServiceXygh.getComplainObjectSupplier(projectId);
	    return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  校验身份证号码的唯一性
	 * Create Date: 2010-11-8下午03:06:40 by yucy  Modified Date: 2010-11-8下午03:06:40 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=checkedIdCard")   
	public ModelAndView checkedIdCard(HttpServletRequest request) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>(); 
		param.put("cardId", request.getParameter("cardId"));
		param.put("projectId", request.getParameter("projectId"));

		Boolean result =  signUprecordServiceXygh.checkedIdCard(param);
		Map<String, Object> model  = new HashMap<String, Object>();
		if(result){
			model.put(Constants.SUCCESS, true);//校验成功  
		}else{
			model.put(Constants.FAILURE, true);//校验失败
		}
	    return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  拦截查询条件
	 * Create Date: 2010-11-8下午03:06:40 by yucy  Modified Date: 2010-11-8下午03:06:40 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected QueryObject onFind(QueryObject query, HttpServletRequest request) throws Exception {
		String status = request.getParameter("status")==null?"":request.getParameter("status");
		
		//竞价中项目(evalStartTime<nowDate<evalEndTime && projProcessStatus == 160)
		if("bargaining".equals(status)) {
			query.getQueryParam().and(new QueryParam("project.evalStartTime",QueryParam.OPERATOR_LT,new Date()));		
			query.getQueryParam().and(new QueryParam("project.evalEndTime",QueryParam.OPERATOR_GT,new Date()));		
			query.getQueryParam().and(new QueryParam("project.projProcessStatus",QueryParam.OPERATOR_EQ,ProjProcessStatusEnum.SUPPLIERS_BID));
			/*增加排序字段*/
			query.getQueryProjections().setOrderProperty("project.evalEndTime");
			query.getQueryProjections().setDescFlag(true); //按报价结束时间倒序
		}
		//即将开始竞价(evalStartTime > nowDate || (evalStartTime<nowDate<evalEndTime && useStatus=00) || useStatus=01 && projProcessStatus=20)
		else if("begaining".equals(status)) {
			QueryParam or = new QueryParam();
			
			QueryParam and = new QueryParam(); //(evalStartTime<nowDate<evalEndTime && useStatus=00)
			and.and(new QueryParam("project.evalStartTime",QueryParam.OPERATOR_LT,new Date()));		
			and.and(new QueryParam("project.evalEndTime",QueryParam.OPERATOR_GT,new Date()));	
			and.and(new QueryParam("project.useStatus",QueryParam.OPERATOR_EQ,CommonEnum.USER_STATUS_TEMP));
			or.or(and);
			
			QueryParam and1 = new QueryParam("project.evalStartTime",QueryParam.OPERATOR_GT,new Date());
			or.or(and1);
			
			QueryParam and2 = new QueryParam("project.useStatus",QueryParam.OPERATOR_EQ,CommonEnum.USER_STATUS_TEMP);//useStatus=00
			or.or(and2);
			
			QueryParam and3 = new QueryParam();  //(useStatus=01 && projProcessStatus=20)
			and3.and(new QueryParam("useStatus",QueryParam.OPERATOR_EQ,CommonEnum.USER_STATUS_FORMAL));
			and3.and(new QueryParam("projProcessStatus",QueryParam.OPERATOR_EQ,ProjProcessStatusEnum.DESIGNATED_PROJECT_RULE));
			
			query.getQueryParam().and(or);
			
			/*增加排序字段*/
			query.getQueryProjections().setOrderProperty("project.evalStartTime");
			query.getQueryProjections().setDescFlag(true); //按报价结束时间倒序
			
			
		}
		//竞价结束(evalEndTime < nowDate || projProcessStatus == 170 && useStatus=01(已提交))
		else if("over".equals(status)) {
			QueryParam or = new QueryParam();
			or.or(new QueryParam("project.evalEndTime",QueryParam.OPERATOR_LT,new Date()));
			or.or(new QueryParam("project.projProcessStatus",QueryParam.OPERATOR_EQ,ProjProcessStatusEnum.OPEN_BID));
			query.getQueryParam().and(or);
			
			query.getQueryParam().and(new QueryParam("project.useStatus",QueryParam.OPERATOR_EQ,CommonEnum.USER_STATUS_FORMAL));
			
			/*增加排序字段*/
			query.getQueryProjections().setOrderProperty("project.createTime");
			query.getQueryProjections().setDescFlag(true); //按报价结束时间倒序
		}
		
		query.getQueryParam().and(new QueryParam("project.useStatus",QueryParam.OPERATOR_NE,CommonEnum.USER_STATUS_CANCEL));
		
		//按创建人过滤
		query.getQueryParam().and(new QueryParam("supplier.objId",QueryParam.OPERATOR_EQ,AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId()));

		
		if(query.getQueryProjections()==null){
			query.setQueryProjections(new QueryProjections());
		}
		return query;
	}
	
}
