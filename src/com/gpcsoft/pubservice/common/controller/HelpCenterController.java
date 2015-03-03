package com.gpcsoft.pubservice.common.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.web.controller.AnnotationMultiController;

/** 
  *  Comments: <strong>IndexViewController</strong>            		
  *	 <br/>	协议供货首页	        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   srplatform                    					          
  *  <br/>Module ID: 权限平台     		
  *  <br/>Create Date：2010-4-19 下午02:30:59 by wangsw    					                            
  *  <br/>Modified Date:  2010-4-19 下午02:30:59 by wangsw                                   
  *<p>@since 0.5
  *   @version: 0.5 
  */ 
/**
 * @gpcsoft.view value="helpCenterView"
 *  url="view/pubservice/help/help_index.jsp"
 */
@Controller//标识为控制器
@Scope("request")
@RequestMapping("/HelpCenterController.do")//页面请求路径,可修改
public class HelpCenterController extends AnnotationMultiController<GpcBaseObject> {

	/** 
	 * Description :  跳转到帮助中心页面
	 * Create Date: 2011-2-24下午04:34:36 by sunl  Modified Date: 2011-2-24下午04:34:36 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toHelpCenter")   
	public ModelAndView toHelpCenter(HttpServletRequest request)throws Exception { 
		
		return new ModelAndView("helpCenterView");
	}
}
