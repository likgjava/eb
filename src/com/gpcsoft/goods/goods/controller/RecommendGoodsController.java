package com.gpcsoft.goods.goods.controller;

import java.util.Map;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gpcsoft.core.utils.HqlResultConvertUtils;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.ModelAndView;
import com.gpcsoft.core.Constants;
import com.gpcsoft.goods.goods.service.RecommendGoodsService;
import com.gpcsoft.goods.goods.domain.Goods;
import com.gpcsoft.goods.goods.domain.RecommendGoods;

/**
  * @gpcsoft.view value="recommendGoodsView"
  *  url="view/goods/goods/recommend/recommend_goods.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={RecommendGoods.class})
@RequestMapping("/RecommendGoodsController.do")//页面请求路径,可修改
public class RecommendGoodsController extends AnnotationMultiController<RecommendGoods> 
{
	@Autowired(required=true) @Qualifier("recommendGoodsServiceImpl")
	private RecommendGoodsService recommendGoodsService;

	/** 
	 * Description :  跳转到商品推荐视图
	 * Create Date: 2010-10-9 下午 16:30:16 by likg  Modified Date: 2010-10-9 下午 16:30:16 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=toRecommendGoodsView")
	public ModelAndView toRecommendGoodsView() throws Exception
	{
		Map<String, Object> model = new HashMap<String, Object>();
		return new ModelAndView("recommendGoodsView", model);
	}
	
	/** 
	 * Description :  推荐商品
	 * Create Date: 2010-10-11 下午 16:30:16 by likg  Modified Date: 2010-10-11 下午 16:30:16 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=recommendGoods")
	public ModelAndView recommendGoods(String goodsIds, String recommendReason) throws Exception
	{
		Map<String, Object> model = new HashMap<String, Object>();
		
		recommendGoodsService.recommendGoods(goodsIds, recommendReason);
		model.put("success", true);
		
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  获取所有的未推荐商品
	 * Create Date: 2010-10-13 下午 16:30:16 by likg  Modified Date: 2010-10-13 下午 16:30:16 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=listNoRecommendGoods")
	@SuppressWarnings("unchecked")
	public ModelAndView listNoRecommendGoods(HttpServletRequest request) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		Map<String, Object> model = new HashMap<String, Object>();
		
		param.put("goodsClassCode", request.getParameter("goodsClassCode")); //商品分类编号
		param.put("goodsBrandName", request.getParameter("goodsBrandName")); //商品品牌名称
		param.put("productName", request.getParameter("productName")); //商品名称
		param.put("order", request.getParameter("order")); //排序字段
		param.put("order_flag", request.getParameter("order_flag")); //排序方式
		
		Page page = prePage(request); //预分页,算出当前页和大小等
	    Page<Goods> pageData = recommendGoodsService.listNoRecommendGoods(param, page);
	   
		String queryColumns = makeQueryColumns(request); //接收前台的指定列名
		pageData.setData(HqlResultConvertUtils.hqlResultConvert(pageData.getData(), queryColumns.split(","), null, Goods.class, getEnumColumns()));
		endPage(model, pageData, request);
	    
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  修改推荐商品的排序序号
	 * Create Date: 2010-10-15上午10:40:08 by likg  Modified Date: 2010-10-15上午10:40:08 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=updateSort")
	public ModelAndView updateSort(HttpServletRequest request) throws Exception
	{
		String sourceObjId = request.getParameter("sourceObjId");
		String isToUp = request.getParameter("isToUp");
		
		recommendGoodsService.updateSort(sourceObjId, ("true".equals(isToUp) ? true : false));
		
		return new ModelAndView(Constants.JSON_VIEW);
	}
}
