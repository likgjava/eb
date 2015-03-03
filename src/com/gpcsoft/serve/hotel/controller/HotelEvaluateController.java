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
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.serve.hotel.domain.HotelEvaluate;
import com.gpcsoft.serve.hotel.service.HotelEvaluateService;

/**
  * @gpcsoft.view value="hotelEvaluateFormView"
  *  url="view//serve/hotelEvaluateForm.jsp"
  * @gpcsoft.view value="hotelEvaluateTreeFormView"
  *  url="view//serve/hotelEvaluateTreeForm.jsp"
  * @gpcsoft.view value="hotelEvaluateListView"
  *  url="view//serve/hotelEvaluateList.jsp"
  * @gpcsoft.view value="hotelEvaluateDetailView"
  *  url="view//serve/hotelEvaluateDetail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={HotelEvaluate.class})
@RequestMapping("/HotelEvaluateController.do")//页面请求路径,可修改
public class HotelEvaluateController extends AnnotationMultiController<HotelEvaluate> {

	@Autowired(required=true) @Qualifier("hotelEvaluateServiceImpl")
	private HotelEvaluateService hotelEvaluateService;

	/** 
	 * Description :  保存酒店评价
	 * Create Date: 2010-8-17下午02:31:39 by yucy  Modified Date: 2010-8-17下午02:31:39 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params= "method=saveHotelEvaluate")
	public ModelAndView saveHotelEvaluate(HttpServletRequest request) throws Exception{
		Map<String, Object> param = new HashMap<String, Object>();
		Map<String, Object> model = new HashMap<String, Object>();
		
		param.put("hotelId", request.getParameter("hotelId"));
		param.put("level", request.getParameter("level"));
		param.put("rateName", request.getParameter("rateName"));
		param.put("remark", request.getParameter("remark"));
		param.put("title", request.getParameter("title"));
		param.put("userId",AuthenticationHelper.getCurrentUser(true).getObjId());
		model = hotelEvaluateService.saveHotelEvaluate(param);
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
}
