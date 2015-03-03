package com.gpcsoft.epp.expertrule.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.expertrule.domain.ExpertRulePurchaseCategory;

/**
  * @gpcsoft.view value="expertRulePurchaseCategoryFormView"
  *  url="view/es/planform/expertrule/expertRulePurchaseCategoryForm.jsp"
  * @gpcsoft.view value="expertRulePurchaseCategoryTreeFormView"
  *  url="view/es/planform/expertrule/expertRulePurchaseCategoryTreeForm.jsp"
  * @gpcsoft.view value="expertRulePurchaseCategoryListView"
  *  url="view/es/planform/expertrule/expertRulePurchaseCategoryList.jsp"
  * @gpcsoft.view value="expertRulePurchaseCategoryDetailView"
  *  url="view/es/planform/expertrule/expertRulePurchaseCategoryDetail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={ExpertRulePurchaseCategory.class})
@RequestMapping("/ExpertRulePurchaseCategoryController.do")//页面请求路径,可修改
public class ExpertRulePurchaseCategoryController extends AnnotationMultiController<ExpertRulePurchaseCategory> {

}
