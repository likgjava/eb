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

import com.gpcsoft.bizplatform.base.common.util.StringUtils;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.goods.goods.domain.GoodsBrand;
import com.gpcsoft.goods.goods.domain.GoodsBrandChange;
import com.gpcsoft.goods.goods.service.GoodsBrandChangeService;

/**
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={GoodsBrandChange.class})
@RequestMapping("/GoodsBrandChangeController.do")//页面请求路径,可修改
public class GoodsBrandChangeController extends AnnotationMultiController<GoodsBrandChange> {

	@Autowired(required=true) @Qualifier("goodsBrandChangeServiceImpl")
	private GoodsBrandChangeService goodsBrandChangeService;
	
	/** 
	 * Description :  获取品牌的变更审核列表
	 * Create Date: 2011-5-9下午04:20:19 by yucy  Modified Date: 2011-5-9下午04:20:19 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=getBrandListToAuditchange")   
	public ModelAndView getBrandListToAuditchange(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Page<GoodsBrand> page = prePage(request); //预分页,算出当前页和大小等		
		
		//参数封装
		Map<String, Object> param = new HashMap<String, Object>();
		
		//查询条件
		param.put("brandName", StringUtils.spaceToPercent(request.getParameter("brandName")));
		param.put("brandCode", StringUtils.spaceToPercent(request.getParameter("brandCode")));
		param.put("englishName", StringUtils.spaceToPercent(request.getParameter("englishName")));
		param.put("goodsClassNames", StringUtils.spaceToPercent(request.getParameter("goodsClassNames")));
		
		//排序
		param.put("order", request.getParameter("order"));
		param.put("order_flag", request.getParameter("order_flag"));
		
		Page<GoodsBrand> pageData = (Page<GoodsBrand>) goodsBrandChangeService.getBrandListByBrandChange(page,param);
		endPage(model, pageData, request);
		return new ModelAndView(Constants.JSON_VIEW,model);
    }
	

	
}
