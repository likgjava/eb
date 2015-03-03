package com.gpcsoft.goods.goodsprice.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.ListOrderedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.utils.StringUtils;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.goods.goods.domain.Goods;
import com.gpcsoft.goods.goods.service.GoodsService;
import com.gpcsoft.goods.goodsprice.domain.GoodsPrice;
import com.gpcsoft.goods.goodsprice.service.GoodsOptFitPriceService;
import com.gpcsoft.goods.goodsprice.service.GoodsPriceProcessService;
import com.gpcsoft.goods.goodsprice.service.GoodsPriceService;
import com.gpcsoft.srplatform.common.utils.xml.MultiSeriesXmlBuilder;

/**
  * @gpcsoft.view value="goodsOptionalFittingView"
  *  url="view/goods/showgoods/goods_optional_fitting.jsp"
  *  
  * @gpcsoft.view value="toPriceChartByDistrictView"
  *  url="view/goods/goodsprice/goods_sell_qty_chart_div.jsp"
  *  
  * @gpcsoft.view value="toOptPriceChooseListView"
  *  url="view/goods/showgoods/goods_optional_fitting_div.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={Goods.class})
@RequestMapping("/GoodsPriceShowController.do")//页面请求路径,可修改
public class GoodsPriceShowController extends AnnotationMultiController<Goods> {

	@Autowired(required=true) @Qualifier("goodsServiceImpl")
	private GoodsService goodsService;
	
	@Autowired(required=true) @Qualifier("goodsPriceServiceImpl")
	private GoodsPriceService goodsPriceService;
	
	@Autowired(required=true) @Qualifier("goodsPriceProcessServiceImpl")
	private GoodsPriceProcessService goodsPriceProcessService;
	
	@Autowired(required=true) @Qualifier("goodsOptFitPriceServiceImpl")
	private GoodsOptFitPriceService goodsOptFitPriceService;
	
	/** 
	 * Description : 获得商品的行情 
	 * Create Date: 2010-9-16下午04:11:54 by guoyr  Modified Date: 2010-9-16下午04:11:54 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getGoodsPriceList")
	public ModelAndView getGoodsPriceList(HttpServletRequest request) throws Exception {
		Map<String,Object> model = new HashMap<String,Object>();
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("objId", request.getParameter("objId"));
		param.put("districtId", request.getParameter("districtId"));
		model.put("goodsPriceList", goodsPriceService.getGoodsPriceList(param));
		
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	/** 
	 * Description : 根据行情获得选配价格 
	 * Create Date: 2010-9-17下午03:30:39 by guoyr  Modified Date: 2010-9-17下午03:30:39 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toGoodsOptionalFittingView")
	public ModelAndView toGoodsOptionalFittingView(String goodsId,String goodsPriceId) throws Exception {
		Map<String,Object> model = new HashMap<String,Object>();
		Goods goods = goodsService.get(goodsId);
		model.put("goods", goods);
		GoodsPrice goodsPrice =  goodsPriceService.get(goodsPriceId);
		model.put("goodsPrice", goodsPrice);
		return new ModelAndView("goodsOptionalFittingView", model);
	}
	

	
	/** 
	 * Description :  跳转到地区行情趋势图形
	 * Create Date: 2010-11-4上午10:09:03 by yucy  Modified Date: 2010-11-4上午10:09:03 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toPriceChartByDistrictView")
	public ModelAndView toPriceChartByDistrictView(HttpServletRequest request) throws Exception{
		Map<String,Object> model = new HashMap<String, Object>();
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("goodsId", request.getParameter("goodsId"));
		List<Object[]> priceList =  goodsPriceService.getAllPriceAndValue(param);
		model.put("priceList", priceList);
		return new ModelAndView ("toPriceChartByDistrictView",model);
	}

	/** 
	 * Description :  
	 * Create Date: 2010-11-29下午04:33:47 by yucy  Modified Date: 2010-11-29下午04:33:47 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getGoodsSellChartXml")
	public void getGoodsSellChartXml(HttpServletRequest request,String goodsId,HttpServletResponse response) throws Exception{
		
		Map<String,Object> param = new HashMap<String, Object>();
		
		param.put("goodsId", request.getParameter("goodsId"));
		List<ListOrderedMap> data = goodsPriceService.getGoodsSalesChart(param);
		
		MultiSeriesXmlBuilder builder = new MultiSeriesXmlBuilder();
		
		builder.setCaption("商品销量和销售额统计表");
		builder.setNamePosition(0);
		builder.setValuePosition(1);
		builder.setSeriesName(new String[]{"销量","销售额"});
		builder.setDataPosition(new int[]{1,2});
		builder.setChartRightMargin("10");
		builder.setChartTopMargin("50");
		builder.setChartBottomMargin("10");
		
		builder.setFormatNumberScale("0");
		builder.setLabelDisplay("");
		builder.setBgAlpha("10,10");
		builder.setAlternateHGridAlpha("5");
		builder.setDivLineIsDashed("1");
		builder.setLineThickness(new String[]{"","3"});
		builder.setParentYAxis(new String[]{"P","S"});
		
		response.getWriter().write(builder.buildXml(data));
		response.getWriter().close();
	}
	
	/** 
	 * Description :  
	 * Create Date: 2010-11-29下午06:19:48 by yucy  Modified Date: 2010-11-29下午06:19:48 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getGoodsPriceChartXml")
	public void getGoodsPriceChartXml(HttpServletRequest request,String goodsId,HttpServletResponse response) throws Exception{
		Map<String,Object> param = new HashMap<String, Object>();
		
		param.put("goodsId", request.getParameter("goodsId"));
		
		//取得热门区间 
		param.put("tagsType",OrganizationEnum.PRICE_TAGS);
		
		List<Object[]> districtArrayList  = null;
		//如果传进来地区参数
		if(StringUtils.hasLength(request.getParameter("districtId"))){
			param.put("districtId", request.getParameter("districtId"));
			districtArrayList =  goodsPriceService.getHotTagAndVelueByDistrict(param);
		}else{
			districtArrayList =  goodsPriceService.getHotTagAndVelue(param);
		}
		if(districtArrayList.size()>0){
			String districtIds = "";
			String seriesName = "";
			int[] position = new int[districtArrayList.size()];
			for (int i=0;i<districtArrayList.size();i++ ) {
				String id = (String)districtArrayList.get(i)[0];
				String name = (String)districtArrayList.get(i)[1];
				if(districtIds.equals("")){
					districtIds += id;
					seriesName+= 	name;
				}else{
					districtIds += ","+id;
					seriesName+= ","+name;
				}
				position[i] = i+1;
			}
			param.put("districtIds", districtIds.split(","));
			List<ListOrderedMap> result = goodsPriceProcessService.getPriceChartDate(param);
			MultiSeriesXmlBuilder builder = new MultiSeriesXmlBuilder();
			
			builder.setCaption("商品行情统计表");
			builder.setNamePosition(0);
			builder.setValuePosition(1);
			builder.setSeriesName(seriesName.split(","));
			builder.setDataPosition(position);
			builder.setChartRightMargin("25");
			builder.setChartTopMargin("50");
			builder.setChartBottomMargin("10");
			
			builder.setFormatNumberScale("0");
			builder.setLabelDisplay("");
			builder.setBgAlpha("10,10");
			builder.setAlternateHGridAlpha("5");
			builder.setDivLineIsDashed("1");
			
			response.getWriter().write(builder.buildXml(result));
			response.getWriter().close();
		}
	}
	
	/** 
	 * Description :  跳转到行情展示列表
	 * Create Date: 2010-10-7下午05:21:09 by yucy  Modified Date: 2010-10-7下午05:21:09 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toGoodsPriceShowList")   
	public ModelAndView toGoodsPriceShowList(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("tagsType",OrganizationEnum.PRICE_TAGS);
		params.put("goodsId",request.getParameter("goodsId"));
		
		//取得热门标签和相应的值
		List<Object[]> hotTagsList =  goodsPriceService.getHotTagAndVelue(params);
		model.put("hotTagsList", hotTagsList);
		
		//取行情
		params.put("objId", request.getParameter("goodsId"));
		params.put("districtId",hotTagsList!=null && hotTagsList.size()>0 ?hotTagsList.get(0)[0]: null );
		
		return new ModelAndView("toGoodsPriceShowListView",model);
	}
	
	/** 
	 * Description :  跳转到选配行情选择列表
	 * Create Date: 2011-12-8下午11:33:32 by yucy  Modified Date: 2011-12-8下午11:33:32 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toOptPriceChooseListView")   
	public ModelAndView toOptPriceChooseListView(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> params = new HashMap<String, Object>();
		//取行情
		params.put("objId", request.getParameter("goodsId"));
		params.put("districtId",request.getParameter("districtId") );
		List<String> goodsPriceIds = new ArrayList<String>();
		for(GoodsPrice goodsPrice : goodsPriceService.getGoodsPriceList(params)){
			goodsPriceIds.add(goodsPrice.getObjId());
		}
		//根据行情取选配行情集合
		params.put("goodsPriceIds",goodsPriceIds);
		if( goodsPriceIds!=null&& goodsPriceIds.size()>0 ){
			model.put("goodsOptFitPriceList", goodsOptFitPriceService.getGoodsOptFitPriceListByGoodsPriceId(params));
		}
		return new ModelAndView("toOptPriceChooseListView",model);
	}
}
