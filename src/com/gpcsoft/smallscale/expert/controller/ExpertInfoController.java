package com.gpcsoft.smallscale.expert.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.bizplatform.base.common.util.StringUtils;
import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.smallscale.expert.domain.ExpertInfo;
import com.gpcsoft.smallscale.expert.service.ExpertInfoService;
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.domain.Employee;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.auth.manager.RoleManager;
import com.gpcsoft.srplatform.auth.service.AttachmentService;
import com.gpcsoft.srplatform.baseData.domain.District;
import com.gpcsoft.srplatform.baseData.service.DistrictService;
import com.gpcsoft.srplatform.common.utils.AttachmentUtil;

/**
  * @gpcsoft.view value="toExpertRegisterPageView_step1"
  *  url="view/smallscale/registration/expert_step1.jsp"
  *  
  * @gpcsoft.view value="toExpertRegisterPageView_step2"
  *  url="view/smallscale/registration/expert_register.jsp"
  *  
  * @gpcsoft.view value="toExpertRegisterPageView_success"
  *  url="view/smallscale/registration/expert_finish.jsp" 
  *  
  * @gpcsoft.view value="toExpertAllInfoView"
  *  url="view/smallscale/expert/expert_info_detail.jsp"
  *  
  * @gpcsoft.view value="toExpertInfoModifyView"
  *  url="view/smallscale/expert/expert_info_modify.jsp"
  *  
  * @gpcsoft.view value="expertInfoModifyTipView"
  *  url="view/smallscale/expert/expert_info_modify_tip.jsp"
  *  
  * @gpcsoft.view value="toAuditExpertInfoView"
  *  url="view/smallscale/expert/expert_info_audit.jsp"
  *  
  * @gpcsoft.view value="applyExpertView"
  *  url="view/smallscale/expert/expert_apply.jsp"
  *  
  * @gpcsoft.view value="applyExpertTipView"
  *  url="view/smallscale/expert/expert_apply_tip.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={ExpertInfo.class})
@RequestMapping("/ExpertInfoController.do")//页面请求路径,可修改
public class ExpertInfoController extends AnnotationMultiController<ExpertInfo> {

	@Autowired(required=true) @Qualifier("expertInfoServiceImpl")
	private ExpertInfoService expertInfoService;
	
	@Autowired(required=true) @Qualifier("attachmentServiceImpl")
	private AttachmentService attachmentService;
	
	@Autowired(required=true) @Qualifier("districtServiceImpl")
	private DistrictService districtService;
	
	@Autowired(required=true) @Qualifier("roleManagerImpl")
	private RoleManager roleManagerImpl;
	
	/** 
	 * Description :  根据主键，获得专家的详细信息【基本信息，教育经历信息，培训经历信息，任职经历信息，职称管理信息】，并跳转到专家详细信息页面
	 * Create Date: 2010-11-26上午09:45:22 by likg  Modified Date: 2010-11-26上午09:45:22 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getExpertAllInfo")
	public ModelAndView getExpertAllInfo(String expertId) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>(); 
		
		ExpertInfo expertInfo = expertInfoService.getExpertAllInfo(expertId);
		model.put("expertInfo", expertInfo);
		
		return new ModelAndView("toExpertAllInfoView", model);
	}
	
	/** 
	 * Description :  保存专家信息【基本信息，用户信息，员工信息】
	 * Create Date: 2010-11-26下午01:20:42 by likg  Modified Date: 2010-11-26下午01:20:42 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveExpertInfo")
	public ModelAndView saveExpertInfo(ExpertInfo expertInfo, User user, Employee employee,HttpServletRequest request,SessionStatus status) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		//保存专家信息
		expertInfoService.saveExpertInfo(expertInfo, user, employee);
		status.setComplete();
		
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  修改专家信息【基本信息，用户信息，员工信息】
	 * Create Date: 2010-11-29上午08:59:07 by likg  Modified Date: 2010-11-29上午08:59:07 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=updateExpertInfo")
	public ModelAndView updateExpertInfo(ExpertInfo expertInfo, Employee employee, String saveType, HttpServletRequest request,SessionStatus status) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		//修改专家信息
		employee.setObjId(request.getParameter("employee.objId"));
		expertInfoService.updateExpertInfo(expertInfo, employee, saveType);
		status.setComplete();
		
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  开启或禁用专家
	 * Create Date: 2010-11-26上午11:04:09 by likg  Modified Date: 2010-11-26上午11:04:09 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=updateDisableOrEnable")
	public ModelAndView updateDisableOrEnable(String expertId, String isOff, SessionStatus status) throws Exception {
		expertInfoService.updateDisableOrEnable(expertId, isOff);
	    status.setComplete();
	    return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/** 
	 * Description :  跳转到专家信息的修改页面
	 * Create Date: 2010-11-26下午04:20:30 by likg  Modified Date: 2010-11-26下午04:20:30 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toExpertInfoModifyView")
	public ModelAndView toExpertInfoModifyView(String expertId, String viewName) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>(); 
		
		ExpertInfo expertInfo = null;
		if(StringUtils.hasLength(expertId)) {
			expertInfo = expertInfoService.get(expertId);
		} else {
			expertInfo = expertInfoService.getUserExpertInfo(AuthenticationHelper.getCurrentUser(true).getObjId());
		}
		
		if("expertInfoModifyTipView".equals(viewName)){
			model.put("expertInfo", expertInfo);
			return new ModelAndView(viewName, model);
		}
		
		//获取所有【province-->city-->town】数据
		List<District> province = districtService.findTopDistricts();
		List<District> city = new ArrayList<District>();
		List<District> town = new ArrayList<District>();
		if(expertInfo.getDistrict() != null) {
			city = districtService.getSubDistricts(expertInfo.getDistrict().getParent().getParent().getObjId());
			town = districtService.getSubDistricts(expertInfo.getDistrict().getParent().getObjId());
		}
		
		//判断当前用户角色
		User user = AuthenticationHelper.getCurrentUser(true);
		if(roleManagerImpl.isHasThisRole(user.getObjId(), OrganizationEnum.ROLE_MANAGER)){
			model.put("role_type", "3"); //运营管理员
		}else{
			model.put("role_type", "4");
		}
		
		model.put("expertInfo", expertInfo);
		model.put("province", province);
		model.put("city", city);
		model.put("town", town);
		
		return new ModelAndView("toExpertInfoModifyView", model);
	}

	/** 
	 * Description :  跳转到专家审核页面，获取专家信息
	 * Create Date: 2010-11-29下午01:32:43 by likg  Modified Date: 2010-11-29下午01:32:43 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toAuditExpertInfoView")
	public ModelAndView toAuditExpertInfoView(String expertId, HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		ExpertInfo expertInfo = expertInfoService.get(expertId);
		model.put("expertInfo", expertInfo);
		
		return new ModelAndView("toAuditExpertInfoView", model);
	}
	
	/** 
	 * Description :  审核专家信息
	 * Create Date: 2010-11-29下午02:05:48 by likg  Modified Date: 2010-11-29下午02:05:48 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=auditExpertInfo")
	public ModelAndView auditExpertInfo(ExpertInfo expertInfo, HttpServletRequest request, SessionStatus status) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		expertInfoService.auditExpertInfo(expertInfo);
		
		status.setComplete();
        return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  跳转到专家注册页面
	 * Create Date: 2010-11-29下午04:36:07 by likg  Modified Date: 2010-11-29下午04:36:07 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toRegisterPage")
	public ModelAndView toRegisterPage(HttpServletRequest request, String step) throws Exception {
		ModelAndView mv = new ModelAndView(ClassUtils.getShortNameAsProperty(this.getPersistClass()), referenceData(request));
		if(StringUtils.hasLength(step)){
			if(step.equals("1")){//阅读条款
				mv.setViewName("toExpertRegisterPageView_step1");
			} else if(step.equals("2")){//填写信息
				Map<String, Object> model = new HashMap<String, Object>();
				model.put("expertInfo",  new ExpertInfo());//清空专家对象信息
				mv.getModelMap().addAllAttributes(model);
				mv.setViewName("toExpertRegisterPageView_step2");
			} else if(step.equals("success")){//注册成功
				mv.setViewName("toExpertRegisterPageView_success");
			}
		}
		
        return mv;
	}
	
	/** 
	 * Description :  保存专家注册信息【专家信息，用户信息，员工信息】
	 * Create Date: 2010-11-29下午05:06:50 by likg  Modified Date: 2010-11-29下午05:06:50 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveExpertInfoOfRegister")
	public ModelAndView saveExpertInfoOfRegister(ExpertInfo expertInfo, User user, Employee employee,HttpServletRequest request, SessionStatus status) throws Exception{		 
		expertInfoService.saveExpertInfoOfRegister(expertInfo, user, employee);
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
	 * Description :  跳转到申请为专家的页面
	 * Create Date: 2010-12-21下午05:06:50 by likg  Modified Date: 2010-12-21下午05:06:50 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toApplyExpertView")
	public ModelAndView toApplyExpertView(String type) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		String viewName = "applyExpertView";
		
		User user = AuthenticationHelper.getCurrentUser(true);
		ExpertInfo expertInfo = expertInfoService.getUserExpertInfo(user.getObjId());
		
		if(StringUtils.hasLength(expertInfo.getObjId())){
			//不是重新申请
			if(!"reapply".equals(type)){
				viewName = "applyExpertTipView";
			}
		}
		
		//获取所有【province-->city-->town】数据
		List<District> province = districtService.findTopDistricts();
		List<District> city = new ArrayList<District>();
		List<District> town = new ArrayList<District>();
		if(expertInfo.getDistrict() != null) {
			city = districtService.getSubDistricts(expertInfo.getDistrict().getParent().getParent().getObjId());
			town = districtService.getSubDistricts(expertInfo.getDistrict().getParent().getObjId());
		}
		
		model.put("province", province);
		model.put("city", city);
		model.put("town", town);
		model.put("expertInfo", expertInfo);
		model.put("user", user);
		
		return new ModelAndView(viewName, model);
	}
	
	/** 
	 * Description :  保存专家申请信息
	 * Create Date: 2010-12-21下午05:06:50 by likg  Modified Date: 2010-12-21下午05:06:50 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveExpertInfoOfApply")
	public ModelAndView saveExpertInfoOfApply(ExpertInfo expertInfo, HttpServletRequest request, SessionStatus status) throws Exception{
		Map<String, String> model = new HashMap<String, String>(); 
		String returnMessage = "success";
		
		//处理专家照片
		try {
			CommonsMultipartFile file = null;
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			file = (CommonsMultipartFile)multipartRequest.getFile("pictureFile");
			if(file!=null && file.getSize()!=0) {
				Object o = AttachmentUtil.uploadFile(request, "pictureFile");
				if(o instanceof GpcBaseObject) {
					Attachment attachment = (Attachment) o;
					attachmentService.saveAttachment(attachment);
					expertInfo.setPhoto(attachment.getObjId());
				}
			}
		} catch (Exception e) {
			returnMessage = e.getMessage();
		}
		
		//图片上传成功
		if("success".equals(returnMessage)){
			User user = AuthenticationHelper.getCurrentUser(true);
			expertInfoService.saveExpertInfoOfApply(expertInfo, user);
		}
		
		model.put(Constants.JSON_RESULT,StringUtils.string2ASCII(returnMessage));
		
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
}
