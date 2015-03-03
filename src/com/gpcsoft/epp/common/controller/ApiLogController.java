package com.gpcsoft.epp.common.controller;

import java.util.HashMap;
import java.util.Map;

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
import com.gpcsoft.epp.common.domain.ApiLog;
import com.gpcsoft.epp.common.service.ApiLogService;

/**
  * @gpcsoft.view value="apiLogFormView"
  *  url="view/es/planform/common/apiLogForm.jsp"
  * @gpcsoft.view value="apiLogTreeFormView"
  *  url="view/es/planform/common/apiLogTreeForm.jsp"
  * @gpcsoft.view value="apiLogListView"
  *  url="view/es/planform/common/apiLogList.jsp"
  * @gpcsoft.view value="apiLogDetailView"
  *  url="view/es/planform/common/apiLogDetail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={ApiLog.class})
@RequestMapping("/ApiLogController.do")//页面请求路径,可修改
public class ApiLogController extends AnnotationMultiController<ApiLog> {

	@Autowired(required=true) @Qualifier("apiLogServiceImpl")
	private ApiLogService apiLogService;
	
	/** 
	 * Description :  根据日志记录Id[公告Id]发布公告
	 * Create Date: 2010-9-15上午10:34:47 by yangx  Modified Date: 2010-9-15上午10:34:47 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params="method=toSendBulletin")
	public ModelAndView toSendBulletin(HttpServletRequest request)throws Exception {
		String bizId = request.getParameter("bizId");
		String trueOrFalse = apiLogService.saveSendBulletin(bizId);
		Map model = new HashMap();
		model.put("trueOrFalse",trueOrFalse);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
}
