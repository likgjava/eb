package com.gpcsoft.goods.goods.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.core.Constants;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.goods.goods.domain.Goods;
import com.gpcsoft.goods.goods.domain.GoodsAccessories;
import com.gpcsoft.goods.goods.service.GoodsAccessoriesService;
import com.gpcsoft.goods.goods.service.GoodsService;

/**
  * @gpcsoft.view value="goodsAccessoriesFormView"
  *  url="view/goods/goods/goodsmng/goodsAccessories_form.jsp"
  * @gpcsoft.view value="goodsAccessoriesListView"
  *  url="view/goods/goods/goodsmng/goodsAccessories_list.jsp"
  * @gpcsoft.view value="goodsAccessoriesDetailView"
  *  url="view/goods/goods/goodsmng/goodsAccessories_detail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={GoodsAccessories.class})
@RequestMapping("/GoodsAccessoriesController.do")//页面请求路径,可修改
public class GoodsAccessoriesController extends AnnotationMultiController<GoodsAccessories> {

	@Autowired(required=true) @Qualifier("goodsAccessoriesServiceImpl")
	private GoodsAccessoriesService goodsAccessoriesService;
	
	@Autowired(required=true) @Qualifier("goodsServiceImpl")
	private GoodsService goodsService;

	/** 
     * Description :  添加或修改零配件
     * Create Date: 2010-8-11下午04:40:52 by sunl  Modified Date: 2010-8-11下午04:40:52 by sunl
     * @param   
     * @return  
     * @Exception   
     */
	@Override
	public ModelAndView toCreateOrUpdate(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   
		
		String objId = request.getParameter("objId");//零配件ID
		String goodsId = request.getParameter("goodsId");//主商品ID
		
		request.getSession().removeAttribute("accessory");
		
		if(StringUtils.hasLength(objId)) {
			GoodsAccessories accessory = goodsAccessoriesService.get(objId);
			model.put("accessory",accessory);
		}else {
			Goods goods = goodsService.get(goodsId);
			model.put("productName", goods.getProductName());
			model.put("goodsId", goods.getObjId());
		}
		
		return new ModelAndView("goodsAccessoriesFormView", model);
	}
	
	/** 
	 * Description :  开启或禁用零配件
	 * Create Date: 2010-8-2上午11:41:27 by sunl  Modified Date: 2010-8-2上午11:41:27 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=updateDisableOrEnable")  
	public ModelAndView updateDisableOrEnable(String objId,String isOff,HttpServletRequest request,SessionStatus status) throws Exception {
		GoodsAccessories accessory = goodsAccessoriesService.get(objId);
		accessory.setIsOff(isOff);
		goodsAccessoriesService.save(accessory);
		
	    status.setComplete();
	    return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/** 
	 * Description :  跳转到零配件详情页面
	 * Create Date: 2011-1-7下午04:01:37 by likg  Modified Date: 2011-1-7下午04:01:37 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toGoodsAccessDetail")
	public ModelAndView toGoodsAccessDetail(String goodsAccessId) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		GoodsAccessories goodsAccess = goodsAccessoriesService.get(goodsAccessId);
		model.put("goodsAccess", goodsAccess);
		
		return new ModelAndView("goodsAccessoriesDetailView", model);
	}
}
