package com.gpcsoft.bizplatform.agency.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.bizplatform.agency.domain.Agency;
import com.gpcsoft.bizplatform.agency.service.AgencyService;
import com.gpcsoft.bizplatform.base.common.util.StringUtils;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.Company;
import com.gpcsoft.srplatform.auth.domain.Employee;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.auth.manager.RoleManager;

/**
  * @gpcsoft.view value="agencyModifyFormView"
  *  url="view/bizplatform/agency/agency/agency_modify.jsp"
  *  
  * @gpcsoft.view value="toAgencyRegisterPageView_step1"
  *  url="view/bizplatform/agency/registration/agency_step1.jsp"
  *  
  * @gpcsoft.view value="toAgencyRegisterPageView_step2"
  *  url="view/bizplatform/agency/registration/agency_register.jsp"
  *  
  * @gpcsoft.view value="toAgencyRegisterPageView_success"
  *  url="view/bizplatform/agency/registration/agency_finish.jsp"
  *  
  * @gpcsoft.view value="toAgencyRoleView"
  *  url="view/bizplatform/agency/agency/agency_view.jsp"
  *  
  * @gpcsoft.view value="toAgencyApplyView"
  *  url="view/bizplatform/agency/agency/agency_apply.jsp"
  *  
  * @gpcsoft.view value="toAgencyAuditView"
  *  url="view/bizplatform/agency/agency/agency_audit.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={Agency.class})
@RequestMapping("/AgencyController.do")//页面请求路径,可修改
public class AgencyController extends AnnotationMultiController<Agency> {

	@Autowired(required=true) @Qualifier("agencyServiceImpl")
	private AgencyService agencyService;	
	@Autowired(required=true) @Qualifier("roleManagerImpl")
	private RoleManager roleManagerImpl;
	
	/** 
	 * Description :  
	 * Create Date: 2010-7-27上午10:33:18 by sunl  Modified Date: 2010-7-27上午10:33:18 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toAgencyRole")
    public ModelAndView toAgencyRole(HttpServletRequest request,String agencyId) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		String viewStr = "";
		Agency agency = null;
		if(agencyId == null){
			viewStr = "toAgencyApplyView";
		}else{
			viewStr = "toAgencyRoleView";
			agency = agencyService.get(agencyId);
			
			//判断用户是否可取消申请
			if(roleManagerImpl.isHasThisRole(AuthenticationHelper.getCurrentUser(true).getObjId(), OrganizationEnum.ROLE_AGENCY)) {
				model.put("hasAgencyRole",true);
			}
		}
		model.put("agency", agency);
		model.put("viewType", request.getParameter("viewType")==null?"":request.getParameter("viewType"));
		
		return new ModelAndView(viewStr, model);
    }
	
	/** 
	 * Description :  审核代理机构信息
	 * Create Date: 2010-7-28下午02:12:00 by sunl  Modified Date: 2010-7-28下午02:12:00 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=auditAgency")
	public ModelAndView auditAgency(String agencyId,String auditStatus,HttpServletRequest request,SessionStatus status) throws Exception {
		Agency agency = agencyService.get(agencyId);
		agency.setAuditStatus(auditStatus);
		agencyService.save(agency);
		
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/** 
	 * Description :  跳转到审核页面
	 * Create Date: 2010-7-26下午06:29:00 by sunl  Modified Date: 2010-7-26下午06:29:00 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toAgencyAudit")
    public ModelAndView toAgencyAudit(HttpServletRequest request,String agencyId) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Agency agency = agencyService.get(agencyId);
		model.put("agency", agency);
		
		return new ModelAndView("toAgencyAuditView", model);
    }
	
	/** 
	 * Description :  保存代理及机构角色申请
	 * Create Date: 2010-7-27上午10:14:06 by sunl  Modified Date: 2010-7-27上午10:14:06 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveApplyAgency")
	public ModelAndView saveApplyAgency(Agency agency,HttpServletRequest request,SessionStatus status) throws Exception{
		 String orgInfoId=request.getParameter("orgInfoId");
		 agencyService.saveApplyAgency(agency,orgInfoId);
		 status.setComplete();
		 Map<String, Object> model = new HashMap<String, Object>();
		 return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  取消机构角色申请
	 * Create Date: 2010-7-27上午10:14:06 by sunl  Modified Date: 2010-7-27上午10:14:06 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=updateCancelApply")
	public ModelAndView updateCancelApply(String agencyId,HttpServletRequest request,SessionStatus status) throws Exception{
		 agencyService.updateCancelApply(agencyId);
		 status.setComplete();
		 Map<String, Object> model = new HashMap<String, Object>();
		 return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  跳转到代理机构注册页面
	 * Create Date: 2010-7-9下午01:54:41 by sunl  Modified Date: 2010-7-9下午01:54:41 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toRegisterPage")
	public ModelAndView toRegisterPage(HttpServletRequest request,String step) throws Exception {
		ModelAndView mv = new ModelAndView(ClassUtils.getShortNameAsProperty(this.getPersistClass()), referenceData(request));
		if(StringUtils.hasLength(step)){
			if(step.equals("1")){//to阅读条款
				mv.setViewName("toAgencyRegisterPageView_step1");
			}
			if(step.equals("2")){//to填写信息
				mv.setViewName("toAgencyRegisterPageView_step2");
			}
			if(step.equals("success")){//to注册成功
				mv.setViewName("toAgencyRegisterPageView_success");
			}
		}
        return mv;
	}
	
	/** 
	 * Description :  代理机构注册
	 * Create Date: 2010-7-9下午01:54:41 by sunl  Modified Date: 2010-7-9下午01:54:41 by sunl
	 * @param company 公司信息
	 * @param employee 员工信息
	 * @param orgInfo 机构信息
	 * @param user 用户信息
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveAgencyOfRegister")
	public ModelAndView saveAgencyOfRegister(Agency agency,Company company,Employee employee,OrgInfo orgInfo,User user,SessionStatus status) throws Exception{
	   	String returnMessage = "";
		try{
			agency = agencyService.saveAgencyOfRegister(agency, company, employee, orgInfo, user);
			returnMessage="success";
		}catch(Exception ce){
			returnMessage="faile";
		}
		status.setComplete();
		Map<String, String> model = new HashMap<String, String>(); 
		model.put(Constants.JSON_RESULT,returnMessage);
        return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  跳转到维护代理机构详细信息页面
	 * Create Date: 2010-7-22下午02:52:46 by sunl  Modified Date: 2010-7-22下午02:52:46 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toModifyAgency")
	public ModelAndView toModifyAgency(String agencyId) throws Exception {
		 Map<String, Object> model = new HashMap<String, Object>();
		 Agency agency = agencyService.get(agencyId);
		 model.put("agency", agency);
		 return new ModelAndView("agencyModifyFormView", model);
	}
}
