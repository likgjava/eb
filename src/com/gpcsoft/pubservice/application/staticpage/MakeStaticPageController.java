package com.gpcsoft.pubservice.application.staticpage;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.core.Constants;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.web.controller.AnnotationMultiController;

@Controller
@Scope("request")
@SessionAttributes(types={GpcBaseObject.class})
@RequestMapping("/MakeStaticPageController.do")
public class MakeStaticPageController extends AnnotationMultiController<GpcBaseObject> {
	
	/** 
	 * Description :  生成静态首页页面
	 * Create Date: 2011-10-28下午05:33:15 by likg  Modified Date: 2011-10-28下午05:33:15 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=makeStaticPage")
	public ModelAndView makeStaticPage(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		MakeStaticPageUtil.makeStaticPage(request.getParameter("pageType"));
		
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
}