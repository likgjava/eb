package com.gpcsoft.epp.taskplan.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.taskplan.domain.TaskPlanMSub;

@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={TaskPlanMSub.class})
@RequestMapping("/TaskPlanSearchController.do")//页面请求路径,可修改
public class TaskPlanSearchController extends AnnotationMultiController<TaskPlanMSub>  {
		
	
	
}

