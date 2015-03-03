package com.gpcsoft.bizplatform.base.purcatalog.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.bizplatform.base.purcatalog.domain.PurCatalogProcType;
import com.gpcsoft.bizplatform.base.purcatalog.service.PurCatalogProcTypeService;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.web.controller.AnnotationMultiController;

/**
  * @gpcsoft.view value="purCatalogProcTypeFormView"
  *  url="view//com.gpcsoft.bizplatform.base.purcatalog.domain/pu_catalo_pro_type_form.jsp"
  * @gpcsoft.view value="purCatalogProcTypeTreeFormView"
  *  url="view//com.gpcsoft.bizplatform.base.purcatalog.domain/pu_catalo_pro_type_tree_form.jsp"
  * @gpcsoft.view value="purCatalogProcTypeListView"
  *  url="view//com.gpcsoft.bizplatform.base.purcatalog.domain/pu_catalo_pro_type_list.jsp"
  * @gpcsoft.view value="purCatalogProcTypeDetailView"
  *  url="view//com.gpcsoft.bizplatform.base.purcatalog.domain/pu_catalo_pro_type_detail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={PurCatalogProcType.class})
@RequestMapping("/PurCatalogProcTypeController.do")//页面请求路径,可修改
public class PurCatalogProcTypeController extends AnnotationMultiController<PurCatalogProcType> {

	@Autowired(required=true) @Qualifier("purCatalogProcTypeServiceImpl")
	private PurCatalogProcTypeService purCatalogProcTypeService;
	
	
	/**
	 * Description : 删除采购方式明细对象 Create Date: 2010-8-10下午04:09:34 by yucy Modified
	 * Date: 2010-8-10下午04:09:34 by yucy
	 * @param
	 * @return
	 * @Exception
	 */
	@RequestMapping(params = "method=deletePurCatalogProcType")
	public ModelAndView deletePurCatalogProcType(String objId) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Boolean result = purCatalogProcTypeService.deletePurCatalogProcType(objId);
		if (result) {
			model.put(Constants.JSON_RESULT, "操作成功！");
		} else {
			model.put(Constants.JSON_RESULT, "操作失败！");
		}
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	

}
