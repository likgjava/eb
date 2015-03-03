package com.gpcsoft.epp.resproject.controller;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.resproject.domain.ResProjectItem;

@Controller 
@Scope("request")
@RequestMapping("/ResProjectItemController.do")
public class ResProjectItemController extends AnnotationMultiController<ResProjectItem> {


	
}
