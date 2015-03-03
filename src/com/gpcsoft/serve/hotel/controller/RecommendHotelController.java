package com.gpcsoft.serve.hotel.controller;

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
import com.gpcsoft.serve.hotel.domain.Hotel;
import com.gpcsoft.serve.hotel.domain.RecommendHotel;
import com.gpcsoft.serve.hotel.service.RecommendHotelService;

@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={RecommendHotel.class})
@RequestMapping("/RecommendHotelController.do")//页面请求路径,可修改
public class RecommendHotelController extends AnnotationMultiController<RecommendHotel> {

	@Autowired(required=true) @Qualifier("recommendHotelServiceImpl")
	private RecommendHotelService recommendHotelService;

	/** 
	 * Description :  获取未推荐的酒店
	 * Create Date: 2010-12-9上午11:11:57 by likg  Modified Date: 2010-12-9上午11:11:57 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=getNoRecommendHotelList")
	@SuppressWarnings("unchecked")
	public ModelAndView getNoRecommendHotelList(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		
		param.put("hotelName", request.getParameter("hotelName")==null?"":request.getParameter("hotelName"));
		param.put("order", request.getParameter("order")==null?"":request.getParameter("order"));//排序字段
		param.put("order_flag", request.getParameter("order_flag")==null?"":request.getParameter("order_flag"));//排序方式
		
		Page page = prePage(request); //预分页,算出当前页和大小等
	    Page<Hotel> pageData = recommendHotelService.getNoRecommendHotelList(param, page);
	   
		String alias=request.getParameter("alias");
		String queryColumns = makeQueryColumns(request); //接收前台的指定列名
		pageData.setData(HqlResultConvertUtils.hqlResultConvert(pageData.getData(), queryColumns.split(","), alias==null?null:alias.split(","), Hotel.class, getEnumColumns()));
		
		endPage(model, pageData, request);
	    
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  推荐酒店
	 * Create Date: 2010-12-9上午11:50:38 by likg  Modified Date: 2010-12-9上午11:50:38 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=recommendHotel")
	public ModelAndView recommendHotel(String hotelIds, String recommendReason) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		recommendHotelService.recommendHotel(hotelIds, recommendReason);
		
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  修改推荐酒店的排序序号
	 * Create Date: 2010-12-9下午01:05:51 by likg  Modified Date: 2010-12-9下午01:05:51 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=updateSort")
	public ModelAndView updateSort(HttpServletRequest request) throws Exception
	{
		String sourceObjId = request.getParameter("sourceObjId");
		String isToUp = request.getParameter("isToUp");
		
		recommendHotelService.updateSort(sourceObjId, ("true".equals(isToUp) ? true : false));
		
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
}
