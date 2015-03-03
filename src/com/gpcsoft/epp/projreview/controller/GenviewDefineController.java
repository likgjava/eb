package com.gpcsoft.epp.projreview.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.core.Constants;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.projreview.domain.GenviewDefine;
import com.gpcsoft.epp.projreview.service.GenviewDefineService;

/**
  * @gpcsoft.view value="genviewDefineFormView"
  *  url="view/es/planform/projreview/genviewDefineForm.jsp"
  * @gpcsoft.view value="genviewDefineTreeFormView"
  *  url="view/es/planform/projreview/genviewDefineTreeForm.jsp"
  * @gpcsoft.view value="genviewDefineListView"
  *  url="view/es/planform/projreview/genviewDefineList.jsp"
  * @gpcsoft.view value="genviewDefineDetailView"
  *  url="view/es/planform/projreview/genviewDefineDetail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={GenviewDefine.class})
@RequestMapping("/GenviewDefineController.do")//页面请求路径,可修改
public class GenviewDefineController extends AnnotationMultiController<GenviewDefine> {

	@Autowired(required=true) @Qualifier("genviewDefineServiceImpl")
	private GenviewDefineService genviewDefineService;
	
	/**
	 * FuncName:getOpenBidRecord
	 * Description :保存开标一览表指标  
	 * @param   request
	 * @return  ModelAndView
	 * @author liuke
	 * @Create Date: 2010-10-9上午10:29:03
	 * @Modifier  liuke 
	 * @Modified Date: 2010-10-9上午10:29:03 
	 */
	@RequestMapping(params = "method=saveGenviewDefine")
    public ModelAndView saveGenviewDefine(HttpServletRequest request)throws Exception {
		logger.debug("\nes GenviewDefineController||saveGenviewDefine\n");
    	String message= request.getParameter("message");
    	String projectId = request.getParameter("projectId");
    	genviewDefineService.saveGenviewDefine(message,projectId);
		return new ModelAndView(Constants.JSON_VIEW);
    }
}
