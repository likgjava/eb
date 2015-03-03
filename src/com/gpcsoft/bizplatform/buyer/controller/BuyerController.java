package com.gpcsoft.bizplatform.buyer.controller;

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
import com.gpcsoft.bizplatform.buyer.domain.Buyer;
import com.gpcsoft.bizplatform.buyer.service.BuyerService;
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
  * @gpcsoft.view value="buyerModifyFormView"
  *  url="view/bizplatform/buyers/buyers/buyer_modify.jsp"
  *  
  * @gpcsoft.view value="toBuyerRegisterPageView_step1"
  *  url="view/bizplatform/buyers/registration/buyer_step1.jsp"
  *  
  * @gpcsoft.view value="toBuyerRegisterPageView_step2"
  *  url="view/bizplatform/buyers/registration/buyer_register.jsp"
  *  
  * @gpcsoft.view value="toBuyerRegisterPageView_success"
  *  url="view/bizplatform/buyers/registration/buyer_finish.jsp"  
  * @gpcsoft.view value="toBuyerRegisterGatePassView_step1"
  *  url="view/bizplatform/buyers/registration/buyer_user_step1.jsp" 
  *  
  * @gpcsoft.view value="toBuyerRegisterGatePassView_step2"
  *  url="view/bizplatform/buyers/registration/buyer_user_register.jsp"  
  *  
  * @gpcsoft.view value="toBuyerRegisterGatePassView_success"
  *  url="view/bizplatform/buyers/registration/buyer_user_finish.jsp"
  *  
  * @gpcsoft.view value="toBuyerRoleView"
  *  url="view/bizplatform/buyers/buyers/buyer_view.jsp"
  *  
  * @gpcsoft.view value="toBuyerApplyView"
  *  url="view/bizplatform/buyers/buyers/buyer_apply.jsp"
  *  
  * @gpcsoft.view value="toBuyerAuditView"
  *  url="view/bizplatform/buyers/buyers/buyer_audit.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={Buyer.class})
@RequestMapping("/BuyerController.do")//页面请求路径,可修改
public class BuyerController extends AnnotationMultiController<Buyer> {

	@Autowired(required=true) @Qualifier("buyerServiceImpl")
	private BuyerService buyerService;
	@Autowired(required=true) @Qualifier("roleManagerImpl")
	private RoleManager roleManagerImpl;
	
	/** 
	 * Description :  采购人注册
	 * Create Date: 2010-7-9上午09:46:51 by sunl  Modified Date: 2010-7-9上午09:46:51 by sunl
	 * @param company 公司信息
	 * @param employee 员工信息
	 * @param orgInfo 机构信息
	 * @param user 用户信息
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveBuyerOfRegister")
	public ModelAndView saveBuyerOfRegister(Buyer buyer,Company company,Employee employee,OrgInfo orgInfo,User user,HttpServletRequest request,SessionStatus status,String promoterUID) throws Exception{
		buyer = buyerService.saveBuyerOfRegister(buyer, company, employee, orgInfo, user,promoterUID);
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
	 * Description :  审核采购人信息
	 * Create Date: 2010-7-28下午02:12:00 by sunl  Modified Date: 2010-7-28下午02:12:00 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=auditBuyer")
	public ModelAndView auditBuyer(String buyerId,String auditStatus,HttpServletRequest request,SessionStatus status) throws Exception {
		Buyer buyer = buyerService.get(buyerId);
		buyer.setAuditStatus(auditStatus);
		buyerService.save(buyer);
		
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/** 
	 * Description :  取消机构角色申请
	 * Create Date: 2010-7-27上午10:14:06 by sunl  Modified Date: 2010-7-27上午10:14:06 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=updateCancelApply")
	public ModelAndView updateCancelApply(String buyerId,HttpServletRequest request,SessionStatus status) throws Exception{
		buyerService.updateCancelApply(buyerId);
		status.setComplete();
		Map<String, Object> model = new HashMap<String, Object>();
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description : 跳转到采购人注册页面
	 * Create Date: 2010-7-9上午09:41:57 by sunl  Modified Date: 2010-7-9上午09:41:57 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toRegisterPage")
	public ModelAndView toRegisterPage(HttpServletRequest request,String step) throws Exception {
		ModelAndView mv = new ModelAndView(ClassUtils.getShortNameAsProperty(this.getPersistClass()), referenceData(request));
		if(StringUtils.hasLength(step)){
			if(step.equals("1")){//阅读条款
				mv.setViewName("toBuyerRegisterPageView_step1");
			}
			if(step.equals("2")){//填写信息
				mv.setViewName("toBuyerRegisterPageView_step2");
			}
			if(step.equals("success")){//注册成功
				mv.setViewName("toBuyerRegisterPageView_success");
			}
		}
        return mv;
	}	

	
	/** 
	 * Description : 保存机构申请中的采购人信息
	 * Create Date: 2010-7-26上午10:27:27 by sunl  Modified Date: 2010-7-26上午10:27:27 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveApplyBuyer")
	public ModelAndView saveApplyBuyer(Buyer buyer,HttpServletRequest request,SessionStatus status) throws Exception{
		 String orgInfoId = request.getParameter("orgInfoId");
		 buyerService.saveApplyBuyer(buyer,orgInfoId);
		 status.setComplete();
		 Map<String, Object> model = new HashMap<String, Object>();
		 return new ModelAndView(Constants.JSON_VIEW, model);
	}

	/** 
	 * Description :  机构申请页面，获取采购人角色信息
	 * Create Date: 2010-7-26下午06:29:00 by sunl  Modified Date: 2010-7-26下午06:29:00 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toBuyerRole")
    public ModelAndView toBuyerRole(HttpServletRequest request,String buyerId) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		String viewStr = "";
		Buyer buyer = null;
		if(buyerId == null){
			viewStr = "toBuyerApplyView";
		}else{
			viewStr = "toBuyerRoleView";
			buyer = buyerService.get(buyerId);
			
			//判断用户是否可取消申请
			if(roleManagerImpl.isHasThisRole(AuthenticationHelper.getCurrentUser(true).getObjId(), OrganizationEnum.ROLE_BUYER)) {
				model.put("hasBuyerRole",true);
			}
		}
		model.put("buyer", buyer);
		model.put("viewType", request.getParameter("viewType")==null?"":request.getParameter("viewType"));
		
		return new ModelAndView(viewStr, model);
    }
	
	/** 
	 * Description :  跳转到审核页面
	 * Create Date: 2010-7-26下午06:29:00 by sunl  Modified Date: 2010-7-26下午06:29:00 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toBuyerAudit")
    public ModelAndView toBuyerAudit(HttpServletRequest request,String buyerId) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Buyer buyer = buyerService.get(buyerId);
		model.put("buyer", buyer);
		
		return new ModelAndView("toBuyerAuditView", model);
    }
	
	/** 
	 * Description :  跳转到维护采购人详细信息页面
	 * Create Date: 2010-7-22下午02:50:25 by sunl  Modified Date: 2010-7-22下午02:50:25 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toModifyBuyer")
	public ModelAndView toModifyBuyer(String buyerId) throws Exception {
		 Map<String, Object> model = new HashMap<String, Object>();
		 Buyer buyer = null;
		 if(StringUtils.hasLength(buyerId)) {
			 buyer = buyerService.get(buyerId);
		 }else {
			 buyer = new Buyer();
		 }
		 model.put("buyer", buyer);
		 return new ModelAndView("buyerModifyFormView", model);
	}
	
	/**
	 * Description :  跳转到采购人注册页面(后来修改的)
	 * Create Date: 2010-11-15上午06:23:21 by zhaojf  Modified Date: 2010-11-15上午06:23:21 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params = "method=toRegisterGatePass")
	public ModelAndView toRegisterGatePass(HttpServletRequest request,String step) throws Exception {
		ModelAndView mv = new ModelAndView(ClassUtils.getShortNameAsProperty(this.getPersistClass()), referenceData(request));
		if(StringUtils.hasLength(step)){
			if(step.equals("1")){//阅读条款
				mv.setViewName("toBuyerRegisterGatePassView_step1");
			}
			if(step.equals("2")){//填写信息
				mv.setViewName("toBuyerRegisterGatePassView_step2");
			}
			if(step.equals("success")){//注册成功
				mv.setViewName("toBuyerRegisterGatePassView_success");
			}
		}		
		return mv;
	}
}
