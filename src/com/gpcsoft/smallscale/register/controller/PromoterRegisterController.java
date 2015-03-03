package com.gpcsoft.smallscale.register.controller;


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

import com.gpcsoft.bizplatform.base.common.util.StringUtils;
import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.smallscale.point.domain.Promoter;
import com.gpcsoft.smallscale.point.domain.PromoterInfo;
import com.gpcsoft.smallscale.point.service.PromoterService;
import com.gpcsoft.smallscale.register.service.PromoterRegisterService;
import com.gpcsoft.srplatform.auth.domain.Company;
import com.gpcsoft.srplatform.auth.domain.Employee;
import com.gpcsoft.srplatform.auth.domain.User;

/**
  * @gpcsoft.view value="toPromoterRegisterPageView_step1"
  *  url="view/smallscale/registration/promoter_step1.jsp"
  *  
  * @gpcsoft.view value="toPromoterRegisterPageView_step2"
  *  url="view/smallscale/registration/promoter_register.jsp"
  *  
  * @gpcsoft.view value="toPromoterRegisterPageView_success"
  *  url="view/smallscale/registration/promoter_finish.jsp" 
  *  
  * @gpcsoft.view value="toRegistPromoterView"
  *  url="view/smallscale/promoter/promoter.jsp" 
  *  
  * @gpcsoft.view value="toBePromoterView"
  *  url="view/smallscale/promoter/to_be_promoter.jsp" 
  *  
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={Promoter.class})
@RequestMapping("/PromoterRegisterController.do")//页面请求路径,可修改
public class PromoterRegisterController extends AnnotationMultiController<Promoter> {


	@Autowired(required=true) @Qualifier("promoterRegisterServiceImpl")
	private PromoterRegisterService promoterRegisterService;
	
	@Autowired(required = true)
	@Qualifier("promoterServiceImpl")
	private PromoterService promoterService;
	
	/** 
	 * Description :  推广人注册
	 * @param company 公司信息
	 * @param employee 员工信息
	 * @param user 用户信息
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=savePromoterOfRegister")
	public ModelAndView savePromoterOfRegister(Employee employee,User user,PromoterInfo promoterInfo,HttpServletRequest request,SessionStatus status) throws Exception{
		Company company = null;		 
		promoterRegisterService.saveOfRegisterPromoter(company, employee, user,promoterInfo);
		status.setComplete();
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		//设置isAttribute的值，登录方法中不是从parameter中读取，而是从attribute中读取
		model.put("isAttribute", true);
		
		String j_rand = request.getSession().getAttribute("j_rand") == null ?"":request.getSession().getAttribute("j_rand").toString();
		model.put("j_username", user.getUsName());
		model.put("j_password", user.getPassword());
		model.put("j_rand", j_rand);
		
		return new ModelAndView("reglogin",model);
	}

	/** 
	 * Description : 跳转到推广人注册页面	
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toRegisterPage")
	public ModelAndView toRegisterPage(HttpServletRequest request,String step) throws Exception {
		ModelAndView mv = new ModelAndView(ClassUtils.getShortNameAsProperty(this.getPersistClass()), referenceData(request));
		if(StringUtils.hasLength(step)){
			if(step.equals("1")){//阅读条款
				mv.setViewName("toPromoterRegisterPageView_step1");
			}
			if(step.equals("2")){//填写信息
				mv.setViewName("toPromoterRegisterPageView_step2");
			}
			if(step.equals("success")){//注册成功
				mv.setViewName("toPromoterRegisterPageView_success");
			}
		}
        return mv;
	}
	
	

	/** 
	 * Description :  申请推广人
	 * Create Date: 2010-11-19上午10:47:29 by liangxj  Modified Date: 2010-11-19上午10:47:29 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toRegistPromoter")
	public ModelAndView toRegistPromoter(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		//获取当前用户的信息
		User user = AuthenticationHelper.getCurrentUser();
		if(user != null){
			boolean isPromoter = promoterService.isHasThisRole(user.getObjId(), OrganizationEnum.ROLE_PROMOTER);
			model.put("isPromoter", isPromoter);
		}
		model.put("user", user);
		
		//处理响应视图
		String viewName = request.getParameter("viewName");
		if(!StringUtils.hasLength(viewName)) {
			viewName = "toRegistPromoterView";
		}
		
		return new ModelAndView(viewName,model);		
	}

}
