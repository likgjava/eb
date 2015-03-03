package com.gpcsoft.goods.goods.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.core.Constants;
import com.gpcsoft.core.utils.HqlResultConvertUtils;
import com.gpcsoft.core.utils.JsonUtils;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.goods.goods.domain.GoodsAccessoriesPrice;
import com.gpcsoft.goods.goods.service.GoodsAccessoriesPriceService;
import com.gpcsoft.goods.goodsprice.domain.GoodsPrice;
import com.gpcsoft.goods.goodsprice.service.GoodsPriceService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.User;

/**
  * @gpcsoft.view value="goodsAccessPriceListDivView"
  *  url="view/goods/showgoods/goods_access_price_list_div.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={GoodsAccessoriesPrice.class})
@RequestMapping("/GoodsAccessoriesPriceController.do")//页面请求路径,可修改
public class GoodsAccessoriesPriceController extends AnnotationMultiController<GoodsAccessoriesPrice> {

	@Autowired(required=true) @Qualifier("goodsAccessoriesPriceServiceImpl")
	private GoodsAccessoriesPriceService goodsAccessoriesPriceService;

	@Autowired(required=true) @Qualifier("goodsPriceServiceImpl")
	private GoodsPriceService goodsPriceService;
	
	/** 
	 * Description :  保存零配件价格
	 * Create Date: 2010-10-22下午06:47:15 by sunl  Modified Date: 2010-10-22下午06:47:15 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveAccPrice")   
	public ModelAndView saveAccPrice(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		String accPriceStr = request.getParameter("accPriceStr");
			
		JSONArray accPriceArray = JSONArray.fromObject(accPriceStr);
		List<GoodsAccessoriesPrice> accPriceList = new ArrayList<GoodsAccessoriesPrice>();
		GoodsAccessoriesPrice accPrice = new GoodsAccessoriesPrice();
		User user = AuthenticationHelper.getCurrentUser(true);
		for(Object obj : accPriceArray) {
			accPrice = (GoodsAccessoriesPrice)JsonUtils.json2Bean(((JSONObject)obj).toString(), GoodsAccessoriesPrice.class);
			accPrice.setCreateTime(new Date());
			accPrice.setCreateUser(user);
			if(null != accPrice.getAccessoryPrice()) {
				accPriceList.add(accPrice);
			}
		}
		
		goodsAccessoriesPriceService.save(accPriceList);
			
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	/** 
	 * Description :  零配件价格列表
	 * Create Date: 2010-10-22下午06:47:15 by sunl  Modified Date: 2010-10-22下午06:47:15 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=listAccPrice")   
	public ModelAndView listAccPrice(HttpServletRequest request) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		Map<String, Object> model = new HashMap<String, Object>();
		
		param.put("goodsId", request.getParameter("goodsId"));
		param.put("goodsPriceId", request.getParameter("goodsPriceId"));
		
		Page page = prePage(request);//预分页,算出当前页和大小等		
		Page pageData = (Page) goodsAccessoriesPriceService.listAccPrice(page,param);
		
		String queryColumns = makeQueryColumns(request);//接收前台的指定列名
		String alias=request.getParameter("alias");
		pageData.setData(HqlResultConvertUtils.hqlResultConvert(pageData.getData(), queryColumns.split(","), alias==null?null:alias.split(","), this.getPersistClass(), getEnumColumns()));
		
		endPage(model, pageData, request);
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	/** 
	 * Description :  加载商品零配件行情列表视图
	 * Create Date: 2011-1-13下午02:00:47 by likg  Modified Date: 2011-1-13下午02:00:47 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=loadGoodsAccessPriceList")
	public ModelAndView loadGoodsAccessPriceList(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		
		param.put("goodsPriceId", request.getParameter("goodsPriceId"));
		param.put("goodsPriceSupplierId", request.getParameter("goodsPriceSupplierId"));
		param.put("maxSize", request.getParameter("maxSize"));
		
		//商品礼包行情集合
		List<GoodsAccessoriesPrice> goodsAccessPriceList = goodsAccessoriesPriceService.getGoodsAccessPriceList(param);
		model.put("goodsAccessPriceList", goodsAccessPriceList);
		
		//商品信息
		if(goodsAccessPriceList!=null && goodsAccessPriceList.size()>0){
			model.put("goods", goodsAccessPriceList.get(0).getGoodsAccessories().getGoods());
		}
		
		//商品行情
		GoodsPrice goodsPrice = goodsPriceService.get(request.getParameter("goodsPriceId"));
		model.put("goodsPrice", goodsPrice);
		model.put("goodsAccessDiv", request.getParameter("goodsGiftDiv")); //商品礼包所属的DIV
		
		return new ModelAndView("goodsAccessPriceListDivView", model);
	}
}
