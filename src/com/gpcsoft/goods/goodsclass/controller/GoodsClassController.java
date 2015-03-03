package com.gpcsoft.goods.goodsclass.controller;

import java.util.ArrayList;
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
import com.gpcsoft.bizplatform.organization.service.OrgInfoService;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.dao.hibernate.query.QueryWebUtils;
import com.gpcsoft.core.extra.json.FrameJsonView;
import com.gpcsoft.core.tree.BaseTree;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.goods.goodsclass.domain.GoodsClass;
import com.gpcsoft.goods.goodsclass.domain.GoodsClassCategory;
import com.gpcsoft.goods.goodsclass.domain.GoodsClassParam;
import com.gpcsoft.goods.goodsclass.domain.GoodsClassParamType;
import com.gpcsoft.goods.goodsclass.service.GoodsClassCategoryService;
import com.gpcsoft.goods.goodsclass.service.GoodsClassService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;

/**
 * 
 * 
  * @gpcsoft.view value="goodsClassListView"
  *  url="view/goods/goodsclass/goodsclass_manage.jsp"
  *  
  *  
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={GoodsClass.class, GoodsClassParamType.class, GoodsClassParam.class, GoodsClassCategory.class})
@RequestMapping("/GoodsClassController.do")//页面请求路径,可修改
public class GoodsClassController extends AnnotationMultiController<GoodsClass> {

	@Autowired(required=true) @Qualifier("goodsClassServiceImpl")
	private GoodsClassService goodsClassService;
	
	@Autowired(required=true) @Qualifier("goodsClassCategoryServiceImpl")
	GoodsClassCategoryService goodsClassCategoryService;
	
	@Autowired(required=true) @Qualifier("orgInfoServiceImpl")
	OrgInfoService orgInfoServiceImpl;
	
	/** 
	 * Description :  跳转到维护页面
	 * Create Date: 2010-8-20下午04:59:45 by yucy  Modified Date: 2010-8-20下午04:59:45 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toGoodsClassList")
	public ModelAndView toGoodsClassList() throws Exception{
        Map<String, Object> model = new HashMap<String, Object>();
        GoodsClass goodsClass = new GoodsClass();
        model.put("goodsClass", goodsClass);
		return new ModelAndView("goodsClassListView", model);
	}
	
    /**
     * Description : 删除指定的商品分类。
     * Create Date: Jan 27, 2010 1:19:06 PM by liujf  Modified Date: Jan 27, 2010 1:19:06 PM by yucy
     * @param   
     * @return  
     * @Exception
     */
    @RequestMapping(params = "method=removeGoodsClass")
    public ModelAndView removeGoodsClass(String goodsClassId, SessionStatus status) throws Exception {
        Map<String, Object> model = new HashMap<String, Object>();
        goodsClassService.removeGoodsClass(goodsClassId);
        status.setComplete();
        return new ModelAndView(Constants.JSON_VIEW, model);
    }
    
    /** 
     * Description :   保存商品分类
     * Create Date: 2010-8-9下午02:17:31 by yucy  Modified Date: 2010-8-9下午02:17:31 by yucy
     * @param   
     * @return  
     * @Exception   
     */
    @RequestMapping(params = "method=saveGoodsClass")
    public ModelAndView saveGoodsClass(HttpServletRequest request, GoodsClass goodsClass, SessionStatus status) throws Exception {
        Map<String, Object> model = new HashMap<String, Object>();
        String purCategoryIds = request.getParameter("purCategoryIds");
        goodsClassService.saveGoodsClass(goodsClass,purCategoryIds);
        status.setComplete();
        model.put("objId", goodsClass.getObjId());   
        return new ModelAndView(Constants.JSON_VIEW, model);
    }
    
    /** 
     * Description :  获得分配的分类编号
     * Create Date: 2010-7-28下午05:17:05 by yucy  Modified Date: 2010-7-28下午05:17:05 by yucy
     * @param   
     * @return  
     * @Exception   
     */
    @RequestMapping(params = "method=getGoodsClassCode")
    public ModelAndView getGoodsClassCode(HttpServletRequest request) throws Exception {
        Map<String, Object> model = new HashMap<String, Object>();
        String parentClassId = request.getParameter("parentClassId");
        String goodsClassCode = goodsClassService.getGoodsClassCode(parentClassId);
        model.put("goodsClassCode", goodsClassCode);
        return new ModelAndView(Constants.JSON_VIEW, model);
    }
    
    /** 
     * Description :  查看商品分类的详细信息。
     * Create Date: 2010-8-9下午02:03:24 by yucy  Modified Date: 2010-8-9下午02:03:24 by yucy
     * @param   
     * @return  
     * @Exception   
     */
    @RequestMapping(params = "method=getGoodsClassDetail")
    public ModelAndView getGoodsClassDetail(String goodsClassId) throws Exception {
        Map<String, Object> model = new HashMap<String, Object>();
        
        if(StringUtils.hasLength(goodsClassId)) {
	        Map<String,Object> goodsClassInfoMap = goodsClassService.getGoodsClassDetail(goodsClassId);
	        GoodsClass goodsClass = (GoodsClass)goodsClassInfoMap.get("goodsClass");
	        model.put("goodsClass",goodsClass);
	        model.put("goodsClassInfoMap", goodsClassInfoMap);   
        }
        
        model.put(FrameJsonView.IS_IGNORE_NULL_VALUES, false);  //转换json时，不过滤空值
        model.put(FrameJsonView.INCLUDED_PROPERTIES, new String[]{"parentClazz", "paramTypeSet", "goodsClassParamSet"});
        model.put(FrameJsonView.EXCLUDED_PROPERTIES, new String[]{"creator", "modifier", "goodsModifierSet"});
        return new ModelAndView(Constants.JSON_VIEW, model);
    }
    
    /** 
     * Description :  根据品目取分类树根据机构投标品目过滤
     * Create Date: 2011-5-5上午11:14:03 by yucy  Modified Date: 2011-5-5上午11:14:03 by yucy
     * @param   
     * @return  
     * @Exception   
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(params = "method=getTreeByCategory")
	public ModelAndView getTreeByCategory(HttpServletRequest request) throws Exception {
    	Map<String, String> model=new HashMap<String, String>();
    	
		//设置查询条件
		QueryObject query=QueryWebUtils.getQuery(request, this.getPersistClass());
		
		//打开的节点id
		String  openId = request.getParameter("id");
		
		//操作人类型
		if( "supplier".equals(request.getParameter("orgType")) ){
			
			//取得机构
			String bidForRange = orgInfoServiceImpl.get(AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId()).getBidForRangeCode();
			
			List<GoodsClass> classlist = new ArrayList<GoodsClass>();
			
			if(StringUtils.hasLength(bidForRange)&& StringUtils.hasLength( bidForRange.split("@@@@")[0] ) ){
				String categoryId = bidForRange.split("@@@@")[0];
				classlist  = goodsClassCategoryService.getClassListByCategory( categoryId, null, false);
			}
			
			List<String > classIds  = new ArrayList<String>();
			
			for (GoodsClass goodsClass : classlist) {
				String classId = "";
				if( StringUtils.hasLength(openId) ){
					classId = goodsClass.getObjId().length()>openId.length()?goodsClass.getObjId().substring(0, openId.length()+2):"";
				}else{
					classId = goodsClass.getObjId().length()>1?goodsClass.getObjId().substring(0,1):"";//goodsClass.getObjId().length()>3?goodsClass.getObjId().substring(0,3):""; by yucy 原来是取第二级 但现在直接从第一级开始取
				}
				if(StringUtils.hasLength( classId ) && classIds.indexOf(classId)< 0 ){
					classIds.add(classId);
				}
			}
			query.getQueryParam().and(new QueryParam("objId" ,QueryParam.OPERATOR_IN ,classIds ));
		}
		
		//显示树结果数据
		String xmlTree=this.dHtmlTreeService.getTree((BaseTree)createCommand(), query, createDHtmlTree(request)).toString();
		model.put(Constants.STRING_RESULT, xmlTree);
    	return new ModelAndView(Constants.STRING_VIEW, model);
    }
    
}
