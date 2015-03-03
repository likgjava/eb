package com.gpcsoft.goods.goods.controller;


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

import com.gpcsoft.core.Constants;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.goods.goods.domain.GoodsEvaluate;
import com.gpcsoft.goods.goods.service.GoodsEvaluateService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;

/**
  * @gpcsoft.view value="goodsEvaluateFormView"
  *  url="view/goods/goods/goodsevaluatemng/goods_evaluate_form.jsp"
  * @gpcsoft.view value="goodsEvaluateListView"
  *  url="view/goods/goods/goodsevaluatemng/goods_evaluate_list.jsp"
  * @gpcsoft.view value="goodsEvaluateDetailView"
  *  url="view/goods/goods/goodsevaluatemng/goods_evaluate_detail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={GoodsEvaluate.class})
@RequestMapping("/GoodsEvaluateController.do")//页面请求路径,可修改
public class GoodsEvaluateController extends AnnotationMultiController<GoodsEvaluate> {

	@Autowired(required=true) @Qualifier("goodsEvaluateServiceImpl")
	private GoodsEvaluateService goodsEvaluateService;
	
	/** 
	 * Description :  保存商品评价
	 * Create Date: 2010-8-17下午02:31:39 by yucy  Modified Date: 2010-8-17下午02:31:39 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params= "method=saveGoodsEvaluate")
	public ModelAndView saveGoodsEvaluate(HttpServletRequest request) throws Exception{
		Map<String, Object> param = new HashMap<String, Object>();
		Map<String, Object> model = new HashMap<String, Object>();
		
		param.put("goodsId", request.getParameter("goodsId"));
		param.put("level", request.getParameter("level"));
		param.put("remark", request.getParameter("remark"));
		param.put("title", request.getParameter("title"));
		param.put("userId",AuthenticationHelper.getCurrentUser(true).getObjId());
		model = goodsEvaluateService.saveGoodsEvaluate(param);
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	
	/** 
	 * Description :  跳转到新增评价页面
	 * Create Date: 2010-9-9下午04:48:53 by zhangyd  Modified Date: 2010-9-9下午04:48:53 by zhangyd
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params= "method=toGoodsEvaluateAdd")
	public ModelAndView toGoodsEvaluateAdd(HttpServletRequest request,String objId) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		GoodsEvaluate goodsEvaluate = goodsEvaluateService.get(objId);
		model.put("goods", goodsEvaluate.getGoods());
		return new ModelAndView("goodsEvaluateFormView",model);
	}
	
	/** 
	 * Description :  跳转到商品评价详细页面
	 * Create Date: 2010-9-9下午04:29:59 by zhangyd  Modified Date: 2010-9-9下午04:29:59 by zhangyd
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params= "method=toGoodsEvaluateDetail")
	public ModelAndView toGoodsEvaluateDetail(HttpServletRequest request,String objId) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		GoodsEvaluate goodsEvaluate = goodsEvaluateService.get(objId);
		model.put("goodsEvaluate", goodsEvaluate);
		return new ModelAndView("goodsEvaluateDetailView",model);
	}
}
