package com.gpcsoft.goods.goodsclass.controller;

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

import com.gpcsoft.core.Constants;
import com.gpcsoft.core.utils.JsonUtils;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.goods.goodsclass.domain.GoodsClass;
import com.gpcsoft.goods.goodsclass.domain.GoodsClassParam;
import com.gpcsoft.goods.goodsclass.domain.GoodsClassParamType;
import com.gpcsoft.goods.goodsclass.service.GoodsClassParamService;

/**
 * 
  * @gpcsoft.view value="goodsClassParamFormView"
  *  url="view/goods/goodsclass/goodsclassparam_form.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={GoodsClassParam.class, GoodsClass.class, GoodsClassParamType.class})
@RequestMapping("/GoodsClassParamController.do")//页面请求路径,可修改
public class GoodsClassParamController extends AnnotationMultiController<GoodsClassParam> {

	@Autowired(required=true) @Qualifier("goodsClassParamServiceImpl")
	private GoodsClassParamService goodsClassParamService;

    
	
    /** 
     * Description :  保存商品分类参数的信息
     * Create Date: 2010-8-4上午10:35:23 by guoyr  Modified Date: 2010-8-4上午10:35:23 by guoyr
     * @param   
     * @return  
     * @Exception   
     */
    @RequestMapping(params = "method=saveGoodsClassParam")
    public ModelAndView saveGoodsClassParam(HttpServletRequest request, String goodsClassParamTypeJson, SessionStatus status) throws Exception {
    	Map <String, Object> model = new HashMap<String, Object> ();
    	GoodsClassParam goodsClassParam = JsonUtils.json2Bean(JsonUtils.getJSONString(goodsClassParamTypeJson),GoodsClassParam.class);
    	goodsClassParamService.saveGoodsClassParam(goodsClassParam);
		
		model.put("objId", goodsClassParam.getObjId());
		model.put("parentId", null != goodsClassParam.getParent() ? goodsClassParam.getParent().getObjId() : "");
		model.put("path", goodsClassParam.getPath());
		status.setComplete();
		
		return new ModelAndView(Constants.JSON_VIEW, model);
    }
    
    @Override
	public ModelAndView toCreateOrUpdate(HttpServletRequest request)
			throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   
		if(null != request.getParameter("objId") && !"".equals(request.getParameter("objId"))) {
			GoodsClassParam goodsClassParam  = goodsClassParamService.get(request.getParameter("objId"));
			model.put("goodsClassParam", goodsClassParam);
		}else {
			model.put("goodsClassParam", new GoodsClassParam());
		}
		
		return new ModelAndView("goodsClassParamFormView", model);
	}
    
    /** 
	 * Description : 删除商品参数，如果该参数被GoodsParam引用，则不充许删除，如果删除则判断是否新修改父结点为叶子结点 
	 * Create Date: 2010-8-3下午02:47:14 by guoyr  Modified Date: 2010-8-3下午02:47:14 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=removeGoodsClassParam")
	public ModelAndView removeGoodsClassParam(HttpServletRequest request) {
		String objId = request.getParameter("objId");
		
		goodsClassParamService.removeGoodsClassParam(objId);
		return new ModelAndView(Constants.JSON_VIEW);
	}

}
