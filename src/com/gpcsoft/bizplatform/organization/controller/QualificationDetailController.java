package com.gpcsoft.bizplatform.organization.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gpcsoft.bizplatform.organization.domain.QualificationDetail;
import com.gpcsoft.bizplatform.organization.service.QualificationDetailService;
import com.gpcsoft.core.web.controller.AnnotationMultiController;

/**
  * @gpcsoft.view value="qualificationDetailFormView"
  *  url="view//qualication/qualificationDetailForm.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={QualificationDetail.class})
@RequestMapping("/QualificationDetailController.do")//页面请求路径,可修改
public class QualificationDetailController extends AnnotationMultiController<QualificationDetail> {

	@SuppressWarnings("unused")
	@Autowired(required=true) @Qualifier("qualificationDetailServiceImpl")
	private QualificationDetailService qualificationDetailService;

}
