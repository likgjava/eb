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
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.bizplatform.base.common.util.StringUtils;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.goods.goods.domain.GoodsOptionalFitting;
import com.gpcsoft.goods.goods.service.GoodsOptionalFittingService;

/**
  * @gpcsoft.view value="goodsAddOptionalFittingView"
  *  url="view/goods/goods/goodsmng/goods_add_optionalitting.jsp"
  * @gpcsoft.view value="goodsOptionalFittingTreeFormView"
  *  url="view/goods/jsp/goods/goodsOptionalFittingTreeForm.jsp"
  * @gpcsoft.view value="goodsOptionalFittingListView"
  *  url="view/goods/jsp/goods/goodsOptionalFittingList.jsp"
  * @gpcsoft.view value="goodsOptionalFittingDetailView"
  *  url="view/goods/goods/goodsmng/goods_optionalitting_detail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={GoodsOptionalFitting.class})
@RequestMapping("/GoodsOptionalFittingController.do")//页面请求路径,可修改
public class GoodsOptionalFittingController extends AnnotationMultiController<GoodsOptionalFitting> {

	@Autowired(required=true) @Qualifier("goodsOptionalFittingServiceImpl")
	private GoodsOptionalFittingService goodsOptionalFittingService;
	
	/** 
	 * Description :  跳转到添加可选配置页面
	 * 				  传入参数为商品参数ID
	 * Create Date: 2010-8-6上午11:46:14 by sunl  Modified Date: 2010-8-6上午11:46:14 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@Override
	public ModelAndView toCreateOrUpdate(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>(); 
		model.put("goodsParamId", request.getParameter("goodsParamId"));
		if(!StringUtils.hasLength(request.getParameter("objId"))){
			model.put("goodsOptionalFitting", new GoodsOptionalFitting());
		}else{
			model.put("goodsOptionalFitting", goodsOptionalFittingService.get(request.getParameter("objId")));
		}
		return new ModelAndView("goodsAddOptionalFittingView", model);
	}
	
	/** 
	 * Description :  跳转到可选配置详细页面
	 * Create Date: 2010-8-6上午11:46:14 by sunl  Modified Date: 2010-8-6上午11:46:14 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	 @RequestMapping(params = "method=toOptionDetail")
	public ModelAndView toOptionDetail(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		GoodsOptionalFitting goodsOptionalFitting = new GoodsOptionalFitting();
		if(StringUtils.hasLength(request.getParameter("objId"))){
			goodsOptionalFitting = goodsOptionalFittingService.get(request.getParameter("objId"));
		}
		
		model.put("goodsOptionalFitting", goodsOptionalFitting);
		return new ModelAndView("goodsOptionalFittingDetailView", model);
	}
	 
	 /** 
      * Description :  禁卖商品
      * Create Date: 2010-8-10下午04:08:45 by sunl  Modified Date: 2010-8-10下午04:08:45 by sunl
      * @param   
      * @return  
      * @Exception   
     */
    @RequestMapping(params = "method=disableFitting")
    public ModelAndView disableFitting(String objId,SessionStatus status) throws Exception {
        Map<String, Object> model = new HashMap<String, Object>();
        goodsOptionalFittingService.disableFitting(objId);
        status.setComplete();
        return new ModelAndView(Constants.JSON_VIEW, model);
    }
}
