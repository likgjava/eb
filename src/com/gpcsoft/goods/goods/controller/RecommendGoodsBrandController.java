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
import com.gpcsoft.core.utils.HqlResultConvertUtils;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.goods.goods.domain.GoodsBrand;
import com.gpcsoft.goods.goods.domain.RecommendGoodsBrand;
import com.gpcsoft.goods.goods.service.RecommendGoodsBrandService;

@Controller
@Scope("request")
@SessionAttributes(types={RecommendGoodsBrand.class})
@RequestMapping("/RecommendGoodsBrandController.do")
public class RecommendGoodsBrandController extends AnnotationMultiController<RecommendGoodsBrand> {

	@Autowired(required=true) @Qualifier("recommendGoodsBrandServiceImpl")
	private RecommendGoodsBrandService recommendGoodsBrandService;

	/** 
	 * Description :  保存推荐的商品品牌
	 * Create Date: 2011-5-16下午04:09:30 by likg  Modified Date: 2011-5-16下午04:09:30 by likg
	 * @param   goodsBrandIds:商品品牌Id以逗号分割
	 * @param   recommendReason:推荐理由
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=saveRecommendGoodsBrand")
	public ModelAndView saveRecommendGoodsBrand(String goodsBrandIds, String recommendReason, HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		//保存推荐的商品品牌
		recommendGoodsBrandService.saveRecommendGoodsBrand(goodsBrandIds, recommendReason);
		
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  获取所有未推荐的商品品牌
	 * Create Date: 2011-5-16下午04:11:57 by likg  Modified Date: 2011-5-16下午04:11:57 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params="method=listNoRecommendGoodsBrand")
	public ModelAndView listNoRecommendGoodsBrand(HttpServletRequest request) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		Map<String, Object> model = new HashMap<String, Object>();
		
		//从页面接受的参数
		String brandCode = request.getParameter("goodsBrand.brandCode"); //品牌编号
		String brandName = request.getParameter("goodsBrand.brandName"); //品牌名称
		String order = request.getParameter("order"); //排序字段
		String order_flag = request.getParameter("order_flag"); //排序方式
		
		param.put("brandCode", brandCode);
		param.put("brandName", brandName);
		param.put("order", order);
		param.put("order_flag", order_flag);
		
		//获取未推荐的商品品牌
		Page<GoodsBrand> page = prePage(request);
	    Page<GoodsBrand> pageData = recommendGoodsBrandService.listNoRecommendGoodsBrand(param, page);
	    String queryColumns = makeQueryColumns(request); //接收前台的指定列名
		pageData.setData(HqlResultConvertUtils.hqlResultConvert(pageData.getData(), queryColumns.split(","), null, GoodsBrand.class, getEnumColumns()));
		endPage(model, pageData, request);
	    
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  修改推荐商品品牌的排序序号
	 * Create Date: 2011-5-16下午04:20:32 by likg  Modified Date: 2011-5-16下午04:20:32 by likg
	 * @param   objId:要排序的推荐商品品牌的Id	isToUp:排序方向
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=updateSort")
	public ModelAndView updateSort(String objId, boolean isToUp, HttpServletRequest request) throws Exception {
		
		//修改推荐商品品牌的排序序号
		recommendGoodsBrandService.updateSort(objId, isToUp);
		
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
}
