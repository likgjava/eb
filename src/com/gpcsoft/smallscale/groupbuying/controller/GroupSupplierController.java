package com.gpcsoft.smallscale.groupbuying.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.smallscale.groupbuying.domain.GroupSupplier;

/**
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={GroupSupplier.class})
@RequestMapping("/GroupSupplierController.do")//页面请求路径,可修改
public class GroupSupplierController extends AnnotationMultiController<GroupSupplier> {

//	@Autowired(required=true) @Qualifier("groupSupplierServiceImpl")
//	private GroupSupplierService groupSupplierService;

}
