package com.gpcsoft.bizplatform.base.purcatalog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gpcsoft.bizplatform.base.purcatalog.domain.PurCatalogDistrict;
import com.gpcsoft.bizplatform.base.purcatalog.service.PurCatalogDistrictService;
import com.gpcsoft.core.web.controller.AnnotationMultiController;

/**
  * @gpcsoft.view value="purCatalogDistrictFormView"
  *  url="view/base/purcatalog/base/purcatalog/pu_catalo_district_form.jsp"
  * @gpcsoft.view value="purCatalogDistrictTreeFormView"
  *  url="view/base/purcatalog/base/purcatalog/pu_catalo_district_tree_form.jsp"
  * @gpcsoft.view value="purCatalogDistrictListView"
  *  url="view/base/purcatalog/base/purcatalog/pu_catalo_district_list.jsp"
  * @gpcsoft.view value="purCatalogDistrictDetailView"
  *  url="view/base/purcatalog/base/purcatalog/pu_catalo_district_detail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={PurCatalogDistrict.class})
@RequestMapping("/PurCatalogDistrictController.do")//页面请求路径,可修改
public class PurCatalogDistrictController extends AnnotationMultiController<PurCatalogDistrict> {

	@SuppressWarnings("unused")
	@Autowired(required=true) @Qualifier("purCatalogDistrictServiceImpl")
	private PurCatalogDistrictService purCatalogDistrictService;

}
