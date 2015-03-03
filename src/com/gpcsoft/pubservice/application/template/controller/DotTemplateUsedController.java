package com.gpcsoft.pubservice.application.template.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.pubservice.application.template.domain.DotTemplateUsed;
import com.gpcsoft.pubservice.application.template.service.DotTemplateUsedService;

@Controller
@Scope("request")
@SessionAttributes(types={DotTemplateUsed.class})
@RequestMapping("/DotTemplateUsedController.do")
public class DotTemplateUsedController extends AnnotationMultiController<DotTemplateUsed> {

	@SuppressWarnings("unused")
	@Autowired(required=true) @Qualifier("dotTemplateUsedServiceImpl")
	private DotTemplateUsedService dotTemplateUsedService;

}
