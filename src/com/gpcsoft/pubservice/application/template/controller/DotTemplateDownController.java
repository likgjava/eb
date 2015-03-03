package com.gpcsoft.pubservice.application.template.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.pubservice.application.template.domain.DotTemplateDown;
import com.gpcsoft.pubservice.application.template.service.DotTemplateDownService;

@Controller
@Scope("request")
@SessionAttributes(types={DotTemplateDown.class})
@RequestMapping("/DotTemplateDownController.do")
public class DotTemplateDownController extends AnnotationMultiController<DotTemplateDown> {

	@SuppressWarnings("unused")
	@Autowired(required=true) @Qualifier("dotTemplateDownServiceImpl")
	private DotTemplateDownService dotTemplateDownService;
	
}
