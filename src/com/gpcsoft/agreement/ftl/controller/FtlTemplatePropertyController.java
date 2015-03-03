package com.gpcsoft.agreement.ftl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gpcsoft.agreement.ftl.domain.FtlTemplateProperty;
import com.gpcsoft.agreement.ftl.service.FtlTemplatePropertyService;
import com.gpcsoft.core.web.controller.AnnotationMultiController;

/**
  * @gpcsoft.view value="ftlTemplatePropertyFormView"
  *  url="view/agreement/ftl/ftl_template_property_form.jsp"
  * @gpcsoft.view value="ftlTemplatePropertyListView"
  *  url="view/agreement/ftl/ftl_template_property_list.jsp"
  * @gpcsoft.view value="ftlTemplatePropertyDetailView"
  *  url="view/agreement/ftl/ftl_template_property_detail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={FtlTemplateProperty.class})
@RequestMapping("/FtlTemplatePropertyController.do")//页面请求路径,可修改
public class FtlTemplatePropertyController extends AnnotationMultiController<FtlTemplateProperty> {

	@SuppressWarnings("unused")
	@Autowired(required=true) @Qualifier("ftlTemplatePropertyServiceImpl")
	private FtlTemplatePropertyService ftlTemplatePropertyService;

}
