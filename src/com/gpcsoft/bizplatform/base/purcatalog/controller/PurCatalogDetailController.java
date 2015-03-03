package com.gpcsoft.bizplatform.base.purcatalog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gpcsoft.bizplatform.base.purcatalog.domain.PurCatalogDetail;
import com.gpcsoft.bizplatform.base.purcatalog.service.PurCatalogDetailService;
import com.gpcsoft.core.web.controller.AnnotationMultiController;

/**
  * @gpcsoft.view value="purCatalogDetailFormView"
  *  url="view/base/purcatalog/base/purcatalog/pu_catalo_detail_form.jsp"
  * @gpcsoft.view value="purCatalogDetailTreeFormView"
  *  url="view/base/purcatalog/base/purcatalog/pu_catalo_detail_tree_form.jsp"
  * @gpcsoft.view value="purCatalogDetailListView"
  *  url="view/base/purcatalog/base/purcatalog/pu_catalo_detail_list.jsp"
  * @gpcsoft.view value="purCatalogDetailDetailView"
  *  url="view/base/purcatalog/base/purcatalog/pu_catalo_detail_detail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={PurCatalogDetail.class})
@RequestMapping("/PurCatalogDetailController.do")//页面请求路径,可修改
public class PurCatalogDetailController extends AnnotationMultiController<PurCatalogDetail> {

	@SuppressWarnings("unused")
	@Autowired(required=true) @Qualifier("purCatalogDetailServiceImpl")
	private PurCatalogDetailService purCatalogDetailService;

}
