package com.gpcsoft.goods.goods.controller;

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
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.goods.goods.domain.GoodsBrand;
import com.gpcsoft.goods.goods.domain.GoodsBrandChange;
import com.gpcsoft.goods.goods.enumeration.GoodsEnum;
import com.gpcsoft.goods.goods.service.GoodsBrandChangeService;
import com.gpcsoft.goods.goods.service.GoodsBrandService;
import com.gpcsoft.goods.goodsclass.domain.GoodsClass;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.auth.manager.RoleManager;

/**
  * @gpcsoft.view value="goodsBrandFormView"
  *  url="view/goods/jsp/goods/goods/goodsBrandForm.jsp"
  *  
  * @gpcsoft.view value="toSaveBrandSuccessView"
  *  url="view/goods/goods/brandmng/brand_submit_result.jsp"
  *  
  * @gpcsoft.view value="goodsBrandTreeFormView"
  *  url="view/goods/jsp/goods/goods/goodsBrandTreeForm.jsp"
  *  
  * @gpcsoft.view value="goodsBrandListView"
  *  url="view/goods/jsp/goods/goods/goodsBrandList.jsp"
  *  
  * @gpcsoft.view value="goodsBrandManageView" 
  *  url="view/goods/goods/brandmng/brand_list_manage.jsp"
  *  
  * @gpcsoft.view value="brandSaleManageView"
  *  url="view/goods/goods/sellmng/brand_sale_management.jsp"
  *  
  * @gpcsoft.view value="brandAuditListView"
  *  url="view/goods/goods/brandmng/brand_audit_list.jsp"
  *  
  * @gpcsoft.view value="brandCreateView"
  *  url="view/goods/goods/brandmng/brand_add.jsp"
  *  
  * @gpcsoft.view value="brandUpdateView"
  *  url="view/goods/goods/brandmng/brand_modify.jsp"
  *  
  * @gpcsoft.view value="auditGoodsBrandView"
  *  url="view/goods/goods/brandmng/brand_audit.jsp"
  *  
  * @gpcsoft.view value="goodsBrandDetailView"
  *  url="view/goods/goods/brandmng/brand_detail_view.jsp"
  * 
  * @gpcsoft.view value="chooseClassByCategoryView"
  *  url="view/goods/goods/brandmng/choose_class_by_category.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={GoodsBrand.class,User.class})
@RequestMapping("/GoodsBrandController.do")//页面请求路径,可修改
public class GoodsBrandController extends AnnotationMultiController<GoodsBrand> {

	@Autowired(required=true) @Qualifier("goodsBrandServiceImpl")
	private GoodsBrandService goodsBrandService;
	
	@Autowired(required=true) @Qualifier("roleManagerImpl")
	private RoleManager roleManagerImpl;
	
	@Autowired(required=true) @Qualifier("goodsBrandChangeServiceImpl")
	private GoodsBrandChangeService goodsBrandChangeService;
	
	/** 
	 * Description :  检查品牌名称
	 * Create Date: 2010-8-3下午08:18:16 by yucy  Modified Date: 2010-8-3下午08:18:16 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=checkBrandName")
	public ModelAndView checkBrandName(HttpServletRequest request) throws Exception {
		
		String brandName = request.getParameter("brandName");
		String objId = request.getParameter("objId");
		String goodsClassId = request.getParameter("goodsClassId");

		Map<String,Object> result = goodsBrandService.checkBrandName(brandName, goodsClassId, objId);
		
		return new ModelAndView(Constants.JSON_VIEW, result);	
    }
	
	/** 
	 * Description :  跳转至修改或新增页面
	 * Create Date: 2010-7-26上午11:48:51 by yucy  Modified Date: 2010-7-26上午11:48:51 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toCreateOrUpdateView")   
	public ModelAndView toCreateOrUpdateView(String objId ,HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();  
		GoodsBrand goodsBrand = null;
		String viewName = null;
		
		if (StringUtils.hasLength(objId)) {
			goodsBrand = goodsBrandService.get(objId);
			
			//取变更信息
			if( GoodsEnum.USE_VALID.equals(goodsBrand.getUseStatus()) ){
				List<GoodsBrandChange> goodsBrandChangeList =  goodsBrandChangeService.getGoodsBrandChange(goodsBrand.getObjId(),null,null);
				model.put("goodsBrandChangeList", goodsBrandChangeList);
			}
			viewName = "brandUpdateView";
		}else{
			goodsBrand = new GoodsBrand(); 
			viewName = "brandCreateView";
		}
		
		//判断是否管理员或商品库管理员
		if(roleManagerImpl.isHasThisRole(AuthenticationHelper.getCurrentUser(true).getObjId(), OrganizationEnum.ROLE_MANAGER+","+OrganizationEnum.ROLE_GOODS_MANAGER)){
			model.put("isManager", true);
			model.put("blongOrg", new OrgInfo());
		}else{
			model.put("isManager", false);
			model.put("blongOrg", (OrgInfo)AuthenticationHelper.getCurrentUser(true).getOrgInfo());
		}
		
		
		model.put("goodsBrand", goodsBrand);
		return new ModelAndView(viewName, model);
	}
	
	/** 
	 * Description :  跳转到新增或修改成功页面
	 * Create Date: 2011-5-6下午04:49:51 by yucy  Modified Date: 2011-5-6下午04:49:51 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toGoodsBrandSuccess")   
	public ModelAndView toGoodsBrandSuccess(HttpServletRequest request ,String objId) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();  
		if(StringUtils.hasLength(objId)){
			model.put("goodsBrand", goodsBrandService.get(objId));
		}
		return new ModelAndView("toSaveBrandSuccessView", model);
	}
	
	/** 
	 * Description :  跳转至审核页面
	 * Create Date: 2010-7-26上午11:48:51 by yucy  Modified Date: 2010-7-26上午11:48:51 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toAuditGoodsBrandView")   
	public ModelAndView toAuditGoodsBrandView(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();  
		String objId = request.getParameter("objId");
		GoodsBrand goodsBrand = goodsBrandService.get(objId);
		
		String goodsClassId = "";
		for(GoodsClass goodsClass : goodsBrand.getGoodsClasses()){
			goodsClassId += ( StringUtils.hasLength(goodsClassId)?",":"" ) + goodsClass.getObjId();
		}
		
		model.put("brandNameCheck", goodsBrandService.checkBrandName(goodsBrand.getBrandName(), goodsClassId, objId).get("checkType"));
		model.put("englishNameCheck",goodsBrandService.checkBrandName(goodsBrand.getEnglishName(), goodsClassId, objId).get("checkType"));

		model.put("goodsBrand", goodsBrand);
		
		//取变更信息
		if( GoodsEnum.USE_VALID.equals(goodsBrand.getUseStatus()) ){
			
			GoodsBrandChange changeClass = null;
			GoodsBrandChange brandName = null;
			GoodsBrandChange englishName = null;
			
			List<GoodsBrandChange> goodsBrandChangeList =  goodsBrandChangeService.getGoodsBrandChange(goodsBrand.getObjId(),null,GoodsEnum.AWAIT_EXAM);
			model.put("goodsBrandChangeList", goodsBrandChangeList);
			for (GoodsBrandChange goodsBrandChange : goodsBrandChangeList) {
				if("goodsClass".equals(goodsBrandChange.getModifyType())){
					model.put("changeClass", changeClass = goodsBrandChange);
				}
				if("brandName".equals(goodsBrandChange.getModifyType())){
					model.put("brandName", brandName = goodsBrandChange);
				}
				if("englishName".equals(goodsBrandChange.getModifyType())){
					model.put("englishName", englishName = goodsBrandChange);
				}
			}
			
			//变更的名称也要检查唯一性
			if(brandName!=null){
				model.put("brandNameCheck", goodsBrandService.checkBrandName(brandName.getNewValue(),changeClass!=null?changeClass.getNewValue().split("##")[1] : goodsClassId, objId).get("checkType"));
			}
			
			//变更的名称也要检查唯一性
			if(englishName!=null){
				model.put("englishNameCheck", goodsBrandService.checkBrandName(englishName.getNewValue(),changeClass!=null?changeClass.getNewValue().split("##")[1] : goodsClassId, objId).get("checkType"));
			}
		}
		
		return new ModelAndView("auditGoodsBrandView", model);
	}
	
	/** 
	 * Description :  跳转至查看页面
	 * Create Date: 2010-7-26上午11:48:51 by yucy  Modified Date: 2010-7-26上午11:48:51 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toGoodsBrandDetailView")   
	public ModelAndView toGoodsBrandDetailView(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();  
		GoodsBrand goodsBrand = goodsBrandService.get(request.getParameter("objId"));
		
		Map<String, Object> param = new HashMap<String, Object>();  
		param.put("objId", request.getParameter("objId"));
		param.put("useStatus", goodsBrand.getUseStatus());
		param.put("currentId", goodsBrand.getCurrentId());
		param.put("createTime", goodsBrand.getCreateTime());

		model.put("goodsBrand", goodsBrand);
		
		//取变更历史
		model.put("goodsBrandChangeList", goodsBrandChangeService.getGoodsBrandChange(goodsBrand.getObjId(),null,GoodsEnum.PASS_EXAM));

		return new ModelAndView("goodsBrandDetailView", model);
	}
	
    /** 
     * Description :  保存品牌
     * Create Date: 2010-8-3下午08:47:03 by yucy  Modified Date: 2010-8-3下午08:47:03 by yucy
     * @param   
     * @return  
     * @Exception   
     */
    @RequestMapping(params = "method=saveGoodsBrand")
    public ModelAndView saveGoodsBrand(HttpServletRequest request,GoodsBrand goodsBrand, SessionStatus status) throws Exception {
    	Boolean result = false;
        Map<String, Object> model = new HashMap<String, Object>();
     	Map<String, Object> param = new HashMap<String, Object>();
       
     	String classBrandString = request.getParameter("classBrandString");
     	String saveType = request.getParameter("saveType");
        param.put("classBrandString", classBrandString);
        param.put("saveType", saveType);
        
        //新增
        if(null==goodsBrand.getObjId()||"".equals(goodsBrand.getObjId())){
        	result = goodsBrandService.createGoodsBrand(goodsBrand,param);
        }
        //修改
        else if(GoodsEnum.USE_TEMP.equals(goodsBrand.getUseStatus()) && StringUtils.hasLength(goodsBrand.getObjId())){
        	result = goodsBrandService.updateGoodsBrand(goodsBrand,param);
        }
        //变更
        else if(GoodsEnum.USE_VALID.equals(goodsBrand.getUseStatus()) && StringUtils.hasLength(goodsBrand.getObjId())){
        	
        	param.put("changeClass.objId", request.getParameter("changeClass.objId"));
        	param.put("changeClass.name", request.getParameter("changeClass.name"));
        	param.put("changeName", request.getParameter("changeName"));
        	param.put("changeEnglish", request.getParameter("changeEnglish"));

        	result = goodsBrandService.saveChangeGoodsBrand(goodsBrand,param);
        }
        model.put("goodsBrand", goodsBrand);
        status.setComplete();
        
        if(result){
        	model.put(Constants.JSON_RESULT,"success");
        }
        return new ModelAndView(Constants.JSON_VIEW, model);
    }
    
    /** 
     * Description :  审核品牌
     * Create Date: 2010-8-3下午08:47:03 by yucy  Modified Date: 2010-8-3下午08:47:03 by yucy
     * @param   
     * @return  
     * @Exception   
     */
    @RequestMapping(params = "method=updateBrandAuditStatus")
    public ModelAndView updateBrandAuditStatus(HttpServletRequest request,GoodsBrand goodsBrand, SessionStatus status) throws Exception {
    	Boolean result = false;
        Map<String, Object> model = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
        
        String objId = request.getParameter("objId");
        String auditStatus = request.getParameter("auditStatus");
        param.put("objId", objId);
        param.put("auditStatus", auditStatus);
        param.put("goodsBrand", goodsBrand);
        
        result = goodsBrandService.updateBrandAuditStatus(param);
        if(result){
        	model.put(Constants.JSON_RESULT, "操作成功!");
        }
        return new ModelAndView(Constants.JSON_VIEW, model);
    }
    
    /** 
     * Description :  审核变更
     * Create Date: 2010-8-3下午08:47:03 by yucy  Modified Date: 2010-8-3下午08:47:03 by yucy
     * @param   
     * @return  
     * @Exception   
     */
    @RequestMapping(params = "method=updateChangeAuditStatus")
    public ModelAndView updateChangeAuditStatus(HttpServletRequest request,String brandId,String auditType, SessionStatus status) throws Exception {
    	Boolean result = false;
        Map<String, Object> model = new HashMap<String, Object>();
        
        String auditStatus = "pass".equals(auditType)? GoodsEnum.PASS_EXAM : GoodsEnum.NO_PASS_EXAM;
        
        result =  goodsBrandService.auditChange(brandId,auditStatus);
        if(result){
        	model.put(Constants.JSON_RESULT, "操作成功!");
        }
        return new ModelAndView(Constants.JSON_VIEW, model);
    }
    
    /** 
     * Description :  启卖/禁卖
     * Create Date: 2010-8-3下午08:47:03 by yucy  Modified Date: 2010-8-3下午08:47:03 by yucy
     * @param   
     * @return  
     * @Exception   
     */
    @RequestMapping(params = "method=updateSellStatus")
    public ModelAndView updateSellStatus(HttpServletRequest request, SessionStatus status) throws Exception {
    	Boolean result = false;
        Map<String, Object> model = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
        String objId = request.getParameter("objId");
        String sellStatus = request.getParameter("sellStatus");
        param.put("objId", objId);
        param.put("sellStatus", sellStatus);
        result = goodsBrandService.updateSellStatus(param);
        if(result){
        	model.put(Constants.JSON_RESULT, "操作成功!");
        }
        return new ModelAndView(Constants.JSON_VIEW, model);
    }
    
    
    /** 
     * Description :  删除品牌
     * Create Date: 2010-8-6上午09:22:22 by yucy  Modified Date: 2010-8-6上午09:22:22 by yucy
     * @param   
     * @return  
     * @Exception   
     */
    @RequestMapping(params = "method=deleteBrand")
    public ModelAndView deleteBrand(HttpServletRequest request) throws Exception{
    	Boolean result = false;
    	Map<String ,Object> model = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
        String objId = request.getParameter("objId");
        param.put("objId", objId);
    	result = goodsBrandService.deleteBrand(param);
    	if(result){
    		model.put(Constants.JSON_RESULT, "操作成功!");
    	}
        return new ModelAndView(Constants.JSON_VIEW, model);
    	
    }
    
    /** 
     * Description :  报废品牌(有效品牌)
     * Create Date: 2010-8-6上午09:22:22 by yucy  Modified Date: 2010-8-6上午09:22:22 by yucy
     * @param   
     * @return  
     * @Exception   
     */
    @RequestMapping(params = "method=updateDestroy")
    public ModelAndView updateDestroy(HttpServletRequest request) throws Exception{
    	Boolean result = false;
    	Map<String ,Object> model = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
        String objId = request.getParameter("objId");
        param.put("objId", objId);
    	result = goodsBrandService.updateDestroy(param);
    	if(result){
    		model.put(Constants.JSON_RESULT, "操作成功!");
    	}
        return new ModelAndView(Constants.JSON_VIEW, model);
    	
    }
    
	/** 
	 * Description :  获取指定维护商的商品品牌(过滤报废禁卖的品牌)
	 * Create Date: 2010-8-20上午11:44:13 by sunl  Modified Date: 2010-8-20上午11:44:13 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getAssignedGoodsBrand")
	public ModelAndView getAssignedGoodsBrand(HttpServletRequest request) throws Exception {
		List<GoodsBrand> goodsBrandList=new ArrayList<GoodsBrand>();
		goodsBrandList = goodsBrandService.getAssignedGoodsBrand();
    	Map<String,Object> model = new HashMap<String,Object>();
    	model.put("result", goodsBrandList);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	/** 
	 * Description : 获得供应商可维护的所有品牌  
	 * Create Date: 2010-9-9下午03:52:29 by guoyr  Modified Date: 2010-9-9下午03:52:29 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getAllGoodsBrandByOrgId")
	public ModelAndView getAllGoodsBrandByOrgId() throws Exception {
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("result", goodsBrandService.getAllGoodsBrandByOrgId());
		return new ModelAndView(Constants.JSON_VIEW, model);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected QueryObject onFind(QueryObject query, HttpServletRequest request)throws Exception {
		//判断是否管理员
		if( !"true".equals(request.getParameter("showAll")) && !roleManagerImpl.isHasThisRole(AuthenticationHelper.getCurrentUser(true).getObjId(), OrganizationEnum.ROLE_MANAGER+","+OrganizationEnum.ROLE_GOODS_MANAGER)){
			query.getQueryParam().and(new QueryParam("belongsId",AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId()));
		}
		return query;
	}
	
	/** 
	 * Description :  跳转到根据品目(经营范围)选择商品分类
	 * Create Date: 2011-5-5上午09:50:02 by yucy  Modified Date: 2011-5-5上午09:50:02 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toChooseClassByCategory")
	public ModelAndView toChooseClassByCategory(HttpServletRequest request) throws Exception{
		Map<String,Object> model = new HashMap<String,Object>();
		
		//判断是否管理员或商品库管理员
		if(roleManagerImpl.isHasThisRole(AuthenticationHelper.getCurrentUser(true).getObjId(), OrganizationEnum.ROLE_MANAGER+","+OrganizationEnum.ROLE_GOODS_MANAGER)){
			model.put("isManager", true);
			model.put("blongOrg", new OrgInfo());
		}else{
			model.put("isManager", false);
			model.put("blongOrg", (OrgInfo)AuthenticationHelper.getCurrentUser(true).getOrgInfo());
		}
		
		return new ModelAndView("chooseClassByCategoryView", model);
	}
	
}
