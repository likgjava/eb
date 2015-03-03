package com.gpcsoft.bizplatform.suppliers.controller;

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
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.bizplatform.suppliers.domain.Supplier;
import com.gpcsoft.bizplatform.suppliers.service.SupplierService;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.Company;
import com.gpcsoft.srplatform.auth.domain.Employee;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.auth.manager.RoleManager;

/**
  * @gpcsoft.view value="supplierModifyFormView"
  *  url="view/bizplatform/suppliers/suppliers/supplier_modify.jsp"
  *  
  * @gpcsoft.view value="toSupplierRegisterPageView_step1"
  *  url="view/bizplatform/suppliers/registration/supplier_step1.jsp"
  *  
  * @gpcsoft.view value="toSupplierRegisterPageView_step2"
  *  url="view/bizplatform/suppliers/registration/supplier_register.jsp"
  *  
  * @gpcsoft.view value="toSupplierRegisterPageView_success"
  *  url="view/bizplatform/suppliers/registration/supplier_finish.jsp"
  *  
  *  
  * @gpcsoft.view value="toSupplierUserRegisterPageView_step1"
  *  url="view/bizplatform/suppliers/registration/supplier_user_step1.jsp"
  *  
  * @gpcsoft.view value="toSupplierUserRegisterPageView_step2"
  *  url="view/bizplatform/suppliers/registration/supplier_user_register.jsp"
  *  
  * @gpcsoft.view value="toSupplierUserRegisterPageView_success"
  *  url="view/bizplatform/suppliers/registration/supplier_user_finish.jsp"
  *  
  * @gpcsoft.view value="toSupplierRoleView"
  *  url="view/bizplatform/suppliers/suppliers/supplier_view.jsp"
  *  
  * @gpcsoft.view value="toSupplierApplyView"
  *  url="view/bizplatform/suppliers/suppliers/supplier_apply.jsp"
  *  
  * @gpcsoft.view value="toSupplierAuditView"
  *  url="view/bizplatform/suppliers/suppliers/supplier_audit.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={Supplier.class})
@RequestMapping("/SupplierController.do")//页面请求路径,可修改
public class SupplierController extends AnnotationMultiController<Supplier> {

	@Autowired(required=true) @Qualifier("supplierServiceImpl")
	private SupplierService supplierService;
	@Autowired(required=true) @Qualifier("roleManagerImpl")
	private RoleManager roleManagerImpl;
	
	/** 
	 * Description :  跳转到供应商注册页面
	 * Create Date: 2010-7-8下午04:33:27 by sunl  Modified Date: 2010-7-8下午04:33:27 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toRegisterPage")
	 public ModelAndView toRegisterPage( HttpServletRequest request,String step) throws Exception {
			ModelAndView mv = new ModelAndView(ClassUtils.getShortNameAsProperty(this.getPersistClass()), referenceData(request));
			if(StringUtils.hasLength(step)){
				if(step.equals("1")){//to阅读条款
					mv.setViewName("toSupplierRegisterPageView_step1");
				}
				if(step.equals("2")){//to填写信息
					mv.setViewName("toSupplierRegisterPageView_step2");
				}
				if(step.equals("success")){//to注册成功
					mv.setViewName("toSupplierRegisterPageView_success");
				}
			}
	        return mv;
	 }
	
	/** 
	 * Description :  跳转到供应商用户注册页面
	 * Create Date: 2010-7-8下午04:33:27 by sunl  Modified Date: 2010-7-8下午04:33:27 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toRegisterUserPage")
	 public ModelAndView toRegisterUserPage( HttpServletRequest request,String step) throws Exception {
			ModelAndView mv = new ModelAndView(ClassUtils.getShortNameAsProperty(this.getPersistClass()), referenceData(request));
			if(StringUtils.hasLength(step)){
				if(step.equals("1")){//to阅读条款
					mv.setViewName("toSupplierUserRegisterPageView_step1");
				}
				if(step.equals("2")){//to填写信息
					mv.setViewName("toSupplierUserRegisterPageView_step2");
				}
				if(step.equals("success")){//to注册成功
					mv.setViewName("toSupplierUserRegisterPageView_success");
				}
			}
	        return mv;
	 }

	/** 
	 * Description :  审核供应商信息
	 * Create Date: 2010-7-28下午02:12:00 by sunl  Modified Date: 2010-7-28下午02:12:00 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=auditSupplier")
	public ModelAndView auditSupplier(String supplierId,String auditStatus,HttpServletRequest request,SessionStatus status) throws Exception {
		Supplier supplier = supplierService.get(supplierId);
		supplier.setAuditStatus(auditStatus);
		supplierService.save(supplier);
		
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
	@RequestMapping(params = "method=toSupplierAudit")
    public ModelAndView toSupplierAudit(HttpServletRequest request,String supplierId) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Supplier supplier = supplierService.get(supplierId);
		model.put("supplier", supplier);
		
		return new ModelAndView("toSupplierAuditView", model);
    }
	
	/** 
	 * Description :  取消机构角色申请
	 * Create Date: 2010-7-27上午10:14:06 by sunl  Modified Date: 2010-7-27上午10:14:06 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=updateCancelApply")
	public ModelAndView updateCancelApply(String supplierId,HttpServletRequest request,SessionStatus status) throws Exception{
		supplierService.updateCancelApply(supplierId);
		status.setComplete();
		Map<String, Object> model = new HashMap<String, Object>();
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  保存供应商机构角色申请
	 * Create Date: 2010-7-27上午10:14:06 by sunl  Modified Date: 2010-7-27上午10:14:06 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveApplySupplier")
	public ModelAndView saveApplySupplier(Supplier supplier,HttpServletRequest request,SessionStatus status) throws Exception{
		 String orgInfoId=request.getParameter("orgInfoId");
		 supplierService.saveApplySupplier(supplier,orgInfoId);
		 status.setComplete();
		 Map<String, Object> model = new HashMap<String, Object>();
		 return new ModelAndView(Constants.JSON_VIEW, model);
	}

	/** 
	 * Description :  机构申请页面，获取供应商角色信息
	 * Create Date: 2010-7-26下午06:29:00 by sunl  Modified Date: 2010-7-26下午06:29:00 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toSupplierRole")
    public ModelAndView toSupplierRole(HttpServletRequest request,String supplierId) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		String viewStr = "";
		Supplier supplier = null;
		if(supplierId == null){
			viewStr = "toSupplierApplyView";
		}else{
			viewStr = "toSupplierRoleView";
			supplier = supplierService.get(supplierId);
			
			//判断用户是否可取消申请
			if(roleManagerImpl.isHasThisRole(AuthenticationHelper.getCurrentUser(true).getObjId(), OrganizationEnum.ROLE_SUPPLIER)) {
				model.put("hasSupplierRole",true);
			}
		}
		model.put("supplier", supplier);
		model.put("viewType", request.getParameter("viewType")==null?"":request.getParameter("viewType"));
		
		return new ModelAndView(viewStr, model);
    }
	
	/** 
	 * Description :  跳转到维护供应商详细信息页面
	 * Create Date: 2010-7-22下午01:25:10 by sunl  Modified Date: 2010-7-22下午01:25:10 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toModifySupplier")
	public ModelAndView toModifySupplier(String supplierId) throws Exception {
		 Map<String, Object> model = new HashMap<String, Object>();
		 Supplier supplier = supplierService.get(supplierId);
		 model.put("supplier", supplier);
		 return new ModelAndView("supplierModifyFormView", model);
	}
	
	/** 
	 * Description :  供应商注册
	 * Create Date: 2010-7-7下午03:34:30 by sunl  Modified Date: 2010-7-7下午03:34:30 by sunl
	 * @param company 公司信息
	 * @param employee 员工信息
	 * @param orgInfo 机构信息
	 * @param user 用户信息
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveSupplierOfRegister")
	public ModelAndView saveSupplierOfRegister(Supplier supplier,Company company,Employee employee,OrgInfo orgInfo,User user,HttpServletRequest request,SessionStatus status) throws Exception{
		supplier = supplierService.saveSupplierOfRegister(supplier, company, employee, orgInfo, user);
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
	
}
