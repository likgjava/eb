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

import com.gpcsoft.bizplatform.base.common.util.StringUtils;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.utils.JsonUtils;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.goods.goodsclass.domain.GoodsClass;
import com.gpcsoft.goods.goodsclass.domain.GoodsClassParamType;
import com.gpcsoft.goods.goodsclass.service.GoodsClassParamTypeService;

/**
  * @gpcsoft.view value="goodsClassParamTypeFormView"
  *  url="view/goods/goodsclass/goodsclassparamtype_edit.jsp"
  * @gpcsoft.view value="goodsClassParamTypeTreeFormView"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={GoodsClassParamType.class, GoodsClass.class})
@RequestMapping("/GoodsClassParamTypeController.do")//页面请求路径,可修改
public class GoodsClassParamTypeController extends AnnotationMultiController<GoodsClassParamType> {

	@Autowired(required=true) @Qualifier("goodsClassParamTypeServiceImpl")
	private GoodsClassParamTypeService goodsClassParamTypeService;

    /** 
     * Description : 保存商品参数分类的信息 
     * Create Date: 2010-8-3下午08:46:51 by guoyr  Modified Date: 2010-8-3下午08:46:51 by guoyr
     * @param   
     * @return  
     * @Exception   
     */
    @RequestMapping(params = "method=saveGoodsClassParamType")
    public ModelAndView saveGoodsClassParamType(HttpServletRequest request, String goodsClassParamTypeJson, SessionStatus status) throws Exception {
    	Map <String, Object> model = new HashMap<String, Object> ();
    	GoodsClassParamType goodsClassParamType = JsonUtils.json2Bean(JsonUtils.getJSONString(goodsClassParamTypeJson),GoodsClassParamType.class);
    	goodsClassParamTypeService.saveGoodsClassParamType(goodsClassParamType);
		
		model.put("objId", goodsClassParamType.getObjId());
		model.put("parentId", null != goodsClassParamType.getParent() ? goodsClassParamType.getParent().getObjId() : "");
		model.put("path", goodsClassParamType.getPath());
		status.setComplete();
		
		return new ModelAndView(Constants.JSON_VIEW, model);
    }
    
    @Override
	public ModelAndView toCreateOrUpdate(HttpServletRequest request)
			throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   
		if(null != request.getParameter("objId") && !"".equals(request.getParameter("objId"))) {
			GoodsClassParamType goodsClassParamType  = goodsClassParamTypeService.get(request.getParameter("objId"));
			model.put("goodsClassParamType", goodsClassParamType);
		}else {
			model.put("goodsClassParamType", new GoodsClassParamType());
		}
		
		return new ModelAndView("goodsClassParamTypeFormView", model);
	}
    
    /** 
	 * Description : 上下移动商品参数时修改原行和目标行的排序
	 * Create Date: 2010-8-2下午06:00:04 by guoyr  Modified Date: 2010-8-2下午06:00:04 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=updateSort")
	public ModelAndView updateSort(HttpServletRequest request) {
		String sourceObjId = request.getParameter("sourceObjId");
		String targetObjId = request.getParameter("targetObjId");
		goodsClassParamTypeService.updateSort(sourceObjId, targetObjId);
		return new ModelAndView(Constants.JSON_VIEW);
	}

	/** 
	 * Description : 删除商品参数分类，并判断是否新修改父结点为叶子结点 
	 * Create Date: 2010-8-3下午02:47:14 by guoyr  Modified Date: 2010-8-3下午02:47:14 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=removeGoodsClassParamType")
	public ModelAndView removeGoodsClassParamType(HttpServletRequest request) {
		String objId = request.getParameter("objId");
		
		goodsClassParamTypeService.removeGoodsClassParamType(objId);
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/** 
	 * Description : 判断在同一商品分类下的商品参数在父节点下是不是唯一  
	 * Create Date: 2010-11-25上午10:59:50 by guoyr  Modified Date: 2010-11-25上午10:59:50 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=isUnique")
	public ModelAndView isUnique(HttpServletRequest request, String goodsClassId, String paramName, String objId, String parentId ) {
		Map <String, Object> model = new HashMap<String, Object> ();
		paramName = StringUtils.ascii2Native(paramName);
		model.put("isUnique", goodsClassParamTypeService.isUnique(goodsClassId, paramName, objId, parentId));
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  批量导入商品分类参数
	 * Create Date: 2011-5-27下午04:55:07 by likg  Modified Date: 2011-5-27下午04:55:07 by likg
	 * @param   goodsClassId:需要添加参数的商品分类Id	goodsClassIds:选择该商品分类的参数
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveGoodsClassParamTypeBatch")
	public ModelAndView saveGoodsClassParamTypeBatch(String goodsClassId, String goodsClassIds, HttpServletRequest request) throws Exception {
		Map <String, Object> model = new HashMap<String, Object>();
		Map <String, Object> param = new HashMap<String, Object>();
		
		param.put("goodsClassId", goodsClassId);
		param.put("goodsClassIds", goodsClassIds);
		goodsClassParamTypeService.saveGoodsClassParamTypeBatch(param);
		
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
}
