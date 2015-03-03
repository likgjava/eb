package com.gpcsoft.goods.goodsprice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.core.Constants;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.goods.goodsprice.domain.GoodsGiftPrice;
import com.gpcsoft.goods.goodsprice.domain.GoodsPrice;
import com.gpcsoft.goods.goodsprice.service.GoodsGiftPriceService;
import com.gpcsoft.goods.goodsprice.service.GoodsPriceService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;

/**
  * @gpcsoft.view value="goodsGiftManageView"
  *  url="view/goods/goodsprice/goods_gift_manage.jsp"
  *  
  * @gpcsoft.view value="goodsGiftPriceListDivView"
  *  url="view/goods/showgoods/goods_gift_price_list_div.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={GoodsGiftPrice.class})
@RequestMapping("/GoodsGiftPriceController.do")//页面请求路径,可修改
public class GoodsGiftPriceController extends AnnotationMultiController<GoodsGiftPrice> {

	@Autowired(required=true) @Qualifier("goodsGiftPriceServiceImpl")
	private GoodsGiftPriceService goodsGiftPriceService;
	
	@Autowired(required=true) @Qualifier("goodsPriceServiceImpl")
	private GoodsPriceService goodsPriceService;
	
	/** 
	 * Description :  跳转到管理商品优惠礼包页面
	 * Create Date: 2011-1-10上午08:55:21 by likg  Modified Date: 2011-1-10上午08:55:21 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toGoodsGiftManageView")
	public ModelAndView toGoodsGiftManageView(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		
		param.put("goodsPriceId", request.getParameter("goodsPriceId"));
		param.put("goodsPriceSupplierId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId());
		
		List<GoodsGiftPrice> goodsGiftPriceList = goodsGiftPriceService.getGoodsGiftPriceList(param);
		model.put("goodsGiftPriceList", goodsGiftPriceList);
		
		return new ModelAndView("goodsGiftManageView", model);
	}
	
	/** 
	 * Description :  保存和修改礼包价格信息
	 * Create Date: 2011-1-10下午02:43:49 by likg  Modified Date: 2011-1-10下午02:43:49 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveGoodsGiftPrice")
	public ModelAndView saveGoodsGiftPrice(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		
		param.put("goodsGiftPrice", request.getParameter("goodsGiftPrice"));
		goodsGiftPriceService.saveGoodsGiftPrice(param);
		
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  加载商品礼包行情列表视图
	 * Create Date: 2011-1-13下午02:00:47 by likg  Modified Date: 2011-1-13下午02:00:47 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=loadGoodsGiftPriceListView")
	public ModelAndView loadGoodsGiftPriceListView(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		
		param.put("goodsPriceId", request.getParameter("goodsPriceId"));
		param.put("goodsPriceSupplierId", request.getParameter("goodsPriceSupplierId"));
		param.put("maxSize", request.getParameter("maxSize"));
		
		//商品礼包行情集合
		List<GoodsGiftPrice> goodsGiftPriceList = goodsGiftPriceService.getGoodsGiftPriceList(param);
		model.put("goodsGiftPriceList", goodsGiftPriceList);
		
		//商品信息
		if(goodsGiftPriceList!=null && goodsGiftPriceList.size()>0){
			model.put("goods", goodsGiftPriceList.get(0).getGoodsGift().getGoods());
		}
		
		//商品行情
		GoodsPrice goodsPrice = goodsPriceService.get(request.getParameter("goodsPriceId"));
		model.put("goodsPrice", goodsPrice);
		model.put("goodsGiftDiv", request.getParameter("goodsGiftDiv")); //商品礼包所属的DIV
		
		return new ModelAndView("goodsGiftPriceListDivView", model);
	}

}
