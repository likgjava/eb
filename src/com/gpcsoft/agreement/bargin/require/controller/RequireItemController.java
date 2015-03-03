package com.gpcsoft.agreement.bargin.require.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gpcsoft.agreement.bargin.require.domain.RequireItem;
import com.gpcsoft.agreement.pub.domain.AgContract;
import com.gpcsoft.core.web.controller.AnnotationMultiController;

@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={AgContract.class})
@RequestMapping("/RequireItemController.do")//页面请求路径,可修改
public class RequireItemController extends AnnotationMultiController<RequireItem> {

}
