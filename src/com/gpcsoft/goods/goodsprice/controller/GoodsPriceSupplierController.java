package com.gpcsoft.goods.goodsprice.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.goods.goodsprice.domain.GoodsPriceSupplier;
import com.gpcsoft.goods.goodsprice.service.GoodsPriceSupplierService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.pubservice.application.service.service.ServiceSubscribeService;

/**
  * @gpcsoft.view value="goodsPriceSupplierFormView"
  *  url="view/agreement/GoodsPriceSupplier/goodsPriceSupplierForm.jsp"
  * @gpcsoft.view value="goodsPriceSupplierTreeFormView"
  *  url="view/agreement/GoodsPriceSupplier/goodsPriceSupplierTreeForm.jsp"
  * @gpcsoft.view value="goodsPriceSupplierListView"
  *  url="view/agreement/GoodsPriceSupplier/goodsPriceSupplierList.jsp"
  * @gpcsoft.view value="goodsPriceSupplierDetailView"
  *  url="view/agreement/GoodsPriceSupplier/goodsPriceSupplierDetail.jsp"
  *  @gpcsoft.view value="toGoodsListForSupplierView"
  *  url="view/goods/goodsprice/s_price_goods_list.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={GoodsPriceSupplier.class})
@RequestMapping("/GoodsPriceSupplierController.do")//页面请求路径,可修改
public class GoodsPriceSupplierController extends AnnotationMultiController<GoodsPriceSupplier> {

	@Autowired(required=true) @Qualifier("goodsPriceSupplierServiceImpl")
	private GoodsPriceSupplierService goodsPriceSupplierService;
	
	@Autowired(required=true)  @Qualifier("serviceSubscribeServiceImpl")
	private ServiceSubscribeService serviceSubscribeService;
	
	/** 
	 * Description :  显示供应商行情/禁止显示供应商行情
	 * Create Date: 2010-4-15下午01:27:15 by yucy  Modified Date: 2010-4-15下午01:27:15 by yucy
	 * @param   
	 * @return  
	 * @throws Exception 
	 * @Exception   
	 */
	@RequestMapping(params="method=updateShowStatus")
	public ModelAndView updateShowStatus(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		
		param.put("goodsPriceSupplierId", request.getParameter("goodsPriceSupplierId"));
		param.put("showType", request.getParameter("showType"));

		Boolean relsut = goodsPriceSupplierService.updateShowStatus(param);
		if(relsut){
			model.put(Constants.SUCCESS, true);
		}else{
			model.put(Constants.FAILURE, true);
		}
		return new ModelAndView(Constants.JSON_VIEW,model);
	}

	/** 
	 * Description :  判断供应商是否具有商圈会员角色
	 * Create Date: 2010-7-28上午09:39:54 by sunl  Modified Date: 2010-7-28上午09:39:54 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=isMember")
    public ModelAndView isMember(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		Boolean result = serviceSubscribeService.isShangQuanHuiYuan(request.getParameter("orgInfoId")==null?"":request.getParameter("orgInfoId"));
		
		model.put("isMember", result);
		
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	/** 
	 * Description :跳转供应商的报价的商品列表
	 * Create Date: 2010-4-29上午10:12:13 by yucy  Modified Date: 2010-4-29上午10:12:13 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toGoodsListForSupplier")   
	public ModelAndView toGoodsListForSupplier(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   
		
		//获取当前机构的id
		OrgInfo currentOrg = (OrgInfo)AuthenticationHelper.getCurrentUser(true).getOrgInfo();
		model.put("currentOrgId", currentOrg.getObjId());
		
		return new ModelAndView("toGoodsListForSupplierView", model);
    }
}
