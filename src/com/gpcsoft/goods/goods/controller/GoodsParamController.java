package com.gpcsoft.goods.goods.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.core.Constants;
import com.gpcsoft.core.extra.json.FrameJsonView;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.goods.goods.domain.Goods;
import com.gpcsoft.goods.goods.domain.GoodsParam;
import com.gpcsoft.goods.goods.service.GoodsParamService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.common.utils.CollectionUtil;

/**
  * @gpcsoft.view value="goodsParamFormView"
  *  url="view/goods/jsp/goods/goodsParamForm.jsp"
  * @gpcsoft.view value="goodsParamTreeFormView"
  *  url="view/goods/jsp/goods/goodsParamTreeForm.jsp"
  * @gpcsoft.view value="goodsParamListView"
  *  url="view/goods/jsp/goods/goodsParamList.jsp"
  * @gpcsoft.view value="goodsParamDetailView"
  *  url="view/goods/jsp/goods/goodsParamDetail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={GoodsParam.class})
@RequestMapping("/GoodsParamController.do")//页面请求路径,可修改
public class GoodsParamController extends AnnotationMultiController<GoodsParam> {

	@Autowired(required=true) @Qualifier("goodsParamServiceImpl")
	private GoodsParamService goodsParamService;
	
	/**
	 * 
	 * Description :  修改商品参数可选配置
     * Create Date: 2010-1-30下午04:05:15 by xiaogh  Modified Date: 2010-1-30下午04:05:15 by xiaogh
     * @param   goods
     * @return  Goods
     * @Exception
	 */
    @SuppressWarnings("unchecked")
    @RequestMapping(params = "method=modifyGoodsParam")
	public ModelAndView modifyGoodsParam(Goods goods) throws Exception{
    	 Map<String, String> model = new HashMap<String, String>(); 
	    	User creator = AuthenticationHelper.getCurrentUser(true);
		
		List<GoodsParam> goodsParams = CollectionUtil.covSetToList(goods.getGoodsParamSet());
		for(GoodsParam goodsParam : goodsParams){
			GoodsParam param = goodsParamService.get(goodsParam.getObjId());
			param.setModifier(creator);
			param.setModifyTime(new Date());
			param.setParamValue(param.getParamValue());
			goodsParamService.save(param);
		}
		 model.put(Constants.JSON_RESULT, "新增配件成功");
	     return new ModelAndView(Constants.JSON_VIEW, model);
    }
    /**
	 * 
	 * Description :  查询商品参数可选配置
     * Create Date: 2010-1-30下午04:05:15 by xiaogh  Modified Date: 2010-1-30下午04:05:15 by xiaogh
     * @param   paramId
     * @return  Goods
     * @Exception
	 */
    @RequestMapping(params = "method=getFitting")
	public ModelAndView getFitting(String paramId)throws Exception{   	
		Map<String, Object> model = new HashMap<String, Object>(); 
		GoodsParam goodsParam = goodsParamService.simpleGoodsParam(goodsParamService.get(paramId));
		 model.put(Constants.JSON_RESULT, goodsParam);
		 model.put(FrameJsonView.INCLUDED_PROPERTIES, new String[]{"goodsOptionalFittingSet"});
	     return new ModelAndView(Constants.JSON_VIEW, model);
	}
}
