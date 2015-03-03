package com.gpcsoft.pubservice.common.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.web.controller.AnnotationMultiController;

/** 
  *  Comments: <strong>IndexViewController</strong>            		
  *	 <br/>	协议供货首页	        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   srplatform                    					          
  *  <br/>Module ID: 权限平台     		
  *  <br/>Create Date：2010-7-8 下午02:30:59 by sunl    					                            
  *  <br/>Modified Date:  2010-7-8 下午02:30:59 by sunl                                   
  *<p>@since 0.5
  *   @version: 0.5 
  */ 
/**
 * @gpcsoft.view value="registrationView"
 *  url="view/pubservice/common/registration.jsp"
 */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={GpcBaseObject.class})
@RequestMapping("/RegistrationController.do")//页面请求路径,可修改
public class RegistrationController extends AnnotationMultiController<GpcBaseObject> {
	
	/** 
	 * Description :  跳转到注册页面（所有注册类型入口）
	 * Create Date: 2010-7-8上午10:43:37 by sunl  Modified Date: 2010-7-8上午10:43:37 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toRegistration")   
	public ModelAndView toRegistration(HttpServletRequest request) throws Exception { 
		ModelAndView mv = new ModelAndView(ClassUtils.getShortNameAsProperty(this.getPersistClass()), referenceData(request));
		mv.setViewName("registrationView");
		return mv;
	}
}
