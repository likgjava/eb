package com.gpcsoft.agreement.bargin.project.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gpcsoft.agreement.bargin.project.domain.RequireTaskItem;
import com.gpcsoft.core.web.controller.AnnotationMultiController;

/**
  * @gpcsoft.view value="requireTaskItemFormView"
  *  url="view/order/project/requireTaskItemForm.jsp"
  * @gpcsoft.view value="requireTaskItemTreeFormView"
  *  url="view/order/project/requireTaskItemTreeForm.jsp"
  * @gpcsoft.view value="requireTaskItemListView"
  *  url="view/order/project/requireTaskItemList.jsp"
  * @gpcsoft.view value="requireTaskItemDetailView"
  *  url="view/order/project/requireTaskItemDetail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={RequireTaskItem.class})
@RequestMapping("/RequireTaskItemController.do")//页面请求路径,可修改
public class RequireTaskItemController extends AnnotationMultiController<RequireTaskItem> {

}
