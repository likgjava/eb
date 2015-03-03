package com.gpcsoft.bizplatform.base.purcategory.controller;

import java.util.HashMap;
import java.util.List;
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
import com.gpcsoft.bizplatform.base.purcategory.domain.PurCategory;
import com.gpcsoft.bizplatform.base.purcategory.service.PurCategoryService;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.web.controller.AnnotationMultiController;

/**
 * @gpcsoft.view value="purCategoryListView"
 * url="view/bizplatform/base/purcategory/purcategory_list.jsp"
 */
@Controller
// 标识为控制器
@Scope("request")
@SessionAttributes(types = { PurCategory.class })
@RequestMapping("/PurCategoryController.do")
// 页面请求路径,可修改
public class PurCategoryController extends AnnotationMultiController<PurCategory> {

	@Autowired(required = true)
	@Qualifier("purCategoryServiceImpl")
	private PurCategoryService purCategoryService;

	/** 
	 * Description :  保存商品参数，并生成拼音缩写 
	 * Create Date: 2010-8-4下午07:41:38 by guoyr  Modified Date: 2010-8-4下午07:41:38 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=savePurCategory")
    public ModelAndView savePurCategory(PurCategory purCategory, SessionStatus status) throws Exception {
    	Map <String, Object> model = new HashMap<String, Object> ();
    	purCategoryService.savePurCategory(purCategory);
		
		model.put("purCategory", purCategory);
		status.setComplete();
		
		return new ModelAndView(Constants.JSON_VIEW, model);
    }
	
	/** 
	 * Description : 删除品目，如果该品目被引用，则不充许删除  
	 * Create Date: 2010-8-4下午08:04:05 by guoyr  Modified Date: 2010-8-4下午08:04:05 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=removePurCategory")
    public ModelAndView removePurCategory(HttpServletRequest request) throws Exception {
		String objId = request.getParameter("objId");
		purCategoryService.removePurCategory(objId);
		return new ModelAndView(Constants.JSON_VIEW);
	}
	


	/**
	 * 
	 * Description : 返回所有采购品目 Create Date: 2010-1-15下午01:28:44 by xiaogh
	 * Modified Date: 2010-1-15下午01:28:44 by xiaogh
	 * 
	 * @param
	 * @return
	 * @Exception
	 */
	@RequestMapping(params = "method=getPurcategoryItems")
	public ModelAndView getPurcategoryItems() throws Exception {
		Map<String, List<PurCategory>> model = new HashMap<String, List<PurCategory>>();
		List<PurCategory> purCategoryArray = purCategoryService
				.getListPurCategory();
		model.put(Constants.JSON_RESULT, purCategoryArray);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  分级获得品目
	 * Create Date: 2011-3-18下午05:37:52 by yucy  Modified Date: 2011-3-18下午05:37:52 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getCategorysByLevel")
	public ModelAndView getCategorysByLevel(HttpServletRequest request) throws Exception {
    	Map <String, Object> model = new HashMap<String, Object> ();
    	Map <String, Object> param = new HashMap<String, Object> ();
    	
    	param.put("level", request.getParameter("level"));
    	param.put("parentId", request.getParameter("parentId"));
    	
		List<PurCategory> purCategoryArray = purCategoryService.getCategorysByLevel(param);
		model.put(Constants.JSON_RESULT, purCategoryArray);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description : 按搜索出品目列表
	 * Create Date: 2011-3-22下午02:29:43 by yucy  Modified Date: 2011-3-22下午02:29:43 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getCategorysByKeyWords")
	public ModelAndView getCategorysByKeyWords(HttpServletRequest request) throws Exception {
    	Map <String, Object> model = new HashMap<String, Object> ();
    	Map <String, Object> param = new HashMap<String, Object> ();
    	String keyWords = StringUtils.ascii2Native(request.getParameter("keyWords"));
    	param.put("keyWords", keyWords);
		List<PurCategory> purCategoryArray = purCategoryService.getCategorysByKeyWords(param);
		model.put("purCategoryArray", purCategoryArray);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  搜索返回值
	 * Create Date: 2011-3-22下午08:05:57 by yucy  Modified Date: 2011-3-22下午08:05:57 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getCategorysByParentId")
	public ModelAndView getCategorysByParentId(HttpServletRequest request) throws Exception {
    	Map <String, Object> model = new HashMap<String, Object>();
    	Map <String, Object> param = new HashMap<String, Object> ();
    	
    	String checkedValue = request.getParameter("checkedValue");
    	
    	//第一级品目
    	param.put("level", "second");
		//model.put("firstCategoryArray", purCategoryService.getCategorysByLevel(param));
		
    	if(StringUtils.hasLength(checkedValue)){
    		String [] checkedValueArray = checkedValue.split(",");
    		param.remove("level");
    		param.put("parentId", checkedValueArray[0]);
    		//第二级品目
    		model.put("secondCategoryArray", purCategoryService.getCategorysByLevel(param));
    		
    		if(StringUtils.hasLength(checkedValueArray[1])){
        		param.remove("parentId");
        		param.put("parentId", checkedValueArray[1]);
        		//第三级品目
        		model.put("thirdCategoryArray", purCategoryService.getCategorysByLevel(param));
    		}
    	}
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
}
