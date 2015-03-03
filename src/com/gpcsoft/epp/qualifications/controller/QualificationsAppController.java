package com.gpcsoft.epp.qualifications.controller;

import java.util.Map;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.ModelAndView;
import com.gpcsoft.core.Constants;
import com.gpcsoft.epp.qualifications.domain.QualificationsApp;
import com.gpcsoft.epp.qualifications.service.QualificationsAppService;

/**
  * @gpcsoft.view value="qualificationsAppFormView"
  *  url="view/es/planform/qualifications/qualificationsAppForm.jsp"
  * @gpcsoft.view value="qualificationsAppTreeFormView"
  *  url="view/es/planform/qualifications/qualificationsAppTreeForm.jsp"
  * @gpcsoft.view value="qualificationsAppListView"
  *  url="view/es/planform/qualifications/qualificationsAppList.jsp"
  * @gpcsoft.view value="qualificationsAppDetailView"
  *  url="view/es/planform/qualifications/qualificationsAppDetail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={QualificationsApp.class})
@RequestMapping("/QualificationsAppController.do")//页面请求路径,可修改
public class QualificationsAppController extends AnnotationMultiController<QualificationsApp> {

	@Autowired(required=true) @Qualifier("qualificationsAppServiceImpl")
	private QualificationsAppService qualificationsAppService;

}
