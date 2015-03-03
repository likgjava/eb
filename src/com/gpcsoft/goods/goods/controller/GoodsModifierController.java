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

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.extra.json.FrameJsonView;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.goods.goods.domain.GoodsModifier;
import com.gpcsoft.goods.goods.service.GoodsModifierService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.auth.manager.RoleManager;

/**
  * @gpcsoft.view value="goodsModifierFormView"
  *  url="view/goods/goods/goodsModifierForm.jsp"
  * @gpcsoft.view value="goodsModifierTreeFormView"
  *  url="view/goods/goods/goodsModifierTreeForm.jsp"
  * @gpcsoft.view value="goodsModifierListView"
  *  url="view/goods/goods/goodsModifierList.jsp"
  * @gpcsoft.view value="goodsModifierDetailView"
  *  url="view/goods/goods/goodsModifierDetail.jsp"
  * @gpcsoft.view value="brandAssignmodifierList"
  *  url="view/goods/goods/assignmodifier/brand_list_assignmodifier.jsp"
  * @gpcsoft.view value="brandListAssignModifierView"
  *  url="view/goods/goods/assignmodifier/brand_list_assignmodifier.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={GoodsModifier.class})
@RequestMapping("/GoodsModifierController.do")//页面请求路径,可修改
public class GoodsModifierController extends AnnotationMultiController<GoodsModifier> {

	@Autowired(required=true) @Qualifier("goodsModifierServiceImpl")
	private GoodsModifierService goodsModifierService;
	
	@Autowired(required=true) @Qualifier("roleManagerImpl")
	private RoleManager roleManagerImpl;

	/** 
	 * Description : 跳转到指定维护供应商列表页面 
	 * Create Date: 2010-11-9下午02:44:14 by guoyr  Modified Date: 2010-11-9下午02:44:14 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toBrandListAssignModifier")   
	public ModelAndView toBrandListAssignModifier(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();  
		
		//判断是否管理员
		if(roleManagerImpl.isHasThisRole(AuthenticationHelper.getCurrentUser(true).getObjId(), OrganizationEnum.ROLE_MANAGER)){
			model.put("isManager", true);
			model.put("blongOrg", new OrgInfo());
		}else{
			model.put("isManager", false);
			model.put("blongOrg", (OrgInfo)AuthenticationHelper.getCurrentUser(true).getOrgInfo());
		}
		return new ModelAndView("brandListAssignModifierView", model);	
	};
	
	/** 
	 * Description : 保存商品的维护商   
	 * Create Date: 2010-9-9下午05:53:17 by guoyr  Modified Date: 2010-9-9下午05:53:17 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveGoodsModifier")
    public ModelAndView saveGoodsModifier(String goodsClassIds, String goodsBrandId, String supplierId, SessionStatus status) throws Exception {
		goodsModifierService.saveGoodsModifier(goodsClassIds, goodsBrandId, supplierId);
		 return new ModelAndView(Constants.JSON_VIEW );
	}
	
	/** 
	 * Description : 修改商品的维护商，如果该分类对应的商品的品牌有变更，则把变更以前的也更改   
	 * Create Date: 2010-9-9下午05:53:33 by guoyr  Modified Date: 2010-9-9下午05:53:33 by guoyr
	 * @param goodsClassId 商品的分类  
	 * @param goodsBrandId 商品的品牌
	 * @param supplierId 商品的维护的供应商
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=updateGoodsModifier")
	public ModelAndView updateGoodsModifier(String objId, String goodsClassId, String goodsBrandId, String supplierId){
		goodsModifierService.updateGoodsModifier(objId,goodsClassId, goodsBrandId, supplierId);
		 return new ModelAndView(Constants.JSON_VIEW );
	}
	
	/**
     * Description : 查看该品牌下面的维护商列表的信息。
     * Create Date: Mar 27, 2010 7:59:14 PM by liujf  Modified Date: Mar 27, 2010 7:59:14 PM by liujf
     * @param   
     * @return  
     * @Exception
     */
    @SuppressWarnings("unchecked")
	@RequestMapping(params = "method=getGoodsModifiersListByBrandId")
    public ModelAndView getGoodsModifiersListByBrandId(String brandId, HttpServletRequest request) throws Exception {
        Page page = prePage(request);
        
        Map<String, Object> model = new HashMap<String, Object>();
        
        Page pageData = goodsModifierService.getGoodsModifiersListByBrandId(brandId, page);
        endPage(model, pageData, request);
        //model.put("rows", goodsBrandList);
        model.put(FrameJsonView.INCLUDED_PROPERTIES, new String[]{"supplier"});//输出的结果当中包含的GpcObject     Collection属性
        
        return new ModelAndView(Constants.JSON_VIEW, model);
    }

	/**
     * Description : 查看该品牌下面的维护商列表的信息。
     * Create Date: Mar 27, 2010 7:59:14 PM by liujf  Modified Date: Mar 27, 2010 7:59:14 PM by liujf
     * @param   
     * @return  
     * @Exception
     */
    @SuppressWarnings("unchecked")
	@RequestMapping(params = "method=getNonGoodsModifierOrgInfo")
    public ModelAndView getNonGoodsModifierOrgInfo(String brandId, HttpServletRequest request) throws Exception {
        Page page = prePage(request);
        
        Map<String, Object> model = new HashMap<String, Object>();
        
        Page pageData = goodsModifierService.getNonGoodsModifierOrgInfo(brandId, new Long(page.getPageNum()).intValue(), page.getPageSize());
        endPage(model, pageData, request);
        //model.put("rows", goodsBrandList);
//        model.put(FrameJsonView.INCLUDED_PROPERTIES, new String[]{"supplier"});//输出的结果当中包含的GpcObject     Collection属性
        
        return new ModelAndView(Constants.JSON_VIEW, model);
    }

	/**
     * Description : 给指定的品牌的某个供应商指定其所维护的商品分类。
     * Create Date: Mar 31, 2010 15:31:32 PM by liujf  Modified Date: Mar 31, 2010 15:31:32 PM by liujf
     * @param   
     * @return  
     * @Exception
     */
	@RequestMapping(params = "method=specifyGoodsClass")
    public ModelAndView specifyGoodsClass(String goodsBrandId, String orgId, String goodsClassIds) throws Exception {
        String [] goodsClassIDsArray = null;
        if (goodsClassIds != null || !"".equals(goodsClassIds)) {
            if (goodsClassIds != null) {
            	goodsClassIDsArray = goodsClassIds.split(",");
            }
        }
        goodsModifierService.update4SpecifyGoodsClass(goodsBrandId, orgId, goodsClassIDsArray);

        Map<String, Object> model = new HashMap<String, Object>();
        
        return new ModelAndView(Constants.JSON_VIEW, model);
    }

	/**
     * Description : 删除该维护商在该品牌下面的所有信息。
     * Create Date: Mar 31, 2010 16:17:01 PM by liujf  Modified Date: Mar 31, 2010 16:17:01 PM by liujf
     * @param   
     * @return  
     * @Exception
     */
	@RequestMapping(params = "method=removeGoodsModifierBrandClass")
    public ModelAndView removeGoodsModifierBrandClass(String[] orgId, String goodsBrandId) throws Exception {
        goodsModifierService.removeGoodsModifierBrandClass(orgId, goodsBrandId);

        Map<String, Object> model = new HashMap<String, Object>();
        
        return new ModelAndView(Constants.JSON_VIEW, model);
    }
	/**
     * Description : 获取用户指定的品牌
     * Create Date: Mar 31, 2010 16:17:01 PM by xiaogh  Modified Date: Mar 31, 2010 16:17:01 PM by xiaogh
     * @param   
     * @return  
     * @Exception
     */
	@RequestMapping(params = "method=getLoginUserBrands")
    public ModelAndView getLoginUserBrands() throws Exception {
        Map<String, Object> model = new HashMap<String, Object>();
		User user = AuthenticationHelper.getCurrentUser(true);
		StringBuffer brands = goodsModifierService.getBrandOfUser(user);
		String returnMessage = "";
		if(brands == null){
			returnMessage = "该用户没用机构。";
		}else{
			 returnMessage = "seccessed";
			 model.put("brands", brands);
		}
        model.put(Constants.JSON_RESULT, returnMessage);
        return new ModelAndView(Constants.JSON_VIEW, model);
    }
	
	/** 
	 * Description:  查看品牌维护商的信息.
	 * Create Date: 2010-3-11下午03:57:33 by bish
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=getGoodsModifierBySupplierId")  
	public ModelAndView getGoodsModifierBySupplierId(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Page<GoodsModifier> page = prePage(request);
	    Page<GoodsModifier> pageData = goodsModifierService.getGoodsModifierBySupplierId(new Long(request.getParameter("page")).intValue(), page.getPageSize());
	    endPage(model, pageData,request);
	    model.put(FrameJsonView.INCLUDED_PROPERTIES, new String[]{"goodsBrand"});
	    return new ModelAndView(Constants.JSON_VIEW, model);
    }
	
	
	/** 
	 * Description :  指定维护商品品牌列表
	 * Create Date: 2010-8-2下午06:13:59 by sunl  Modified Date: 2010-8-2下午06:13:59 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=getGoodsBrandBySupplierId")  
	public ModelAndView getGoodsBrandBySupplierId(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Page<GoodsModifier> page = prePage(request);
	    Page<GoodsModifier> pageData = goodsModifierService.getGoodsBrandBySupplierId(page,request);
	    endPage(model, pageData,request);
	    return new ModelAndView(Constants.JSON_VIEW, model);
    }
	
	/** 
	 * Description : 获得还没有指定维护供应商的商品 
	 * Create Date: 2010-8-5下午04:27:02 by guoyr  Modified Date: 2010-8-5下午04:27:02 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=getUnSpecifiedGoods")  
	public ModelAndView getUnSpecifiedGoods(HttpServletRequest request) throws Exception {
		String goodsClassName = request.getParameter("goodsClass.goodsClassName");
		String goodsBrandName = request.getParameter("goodsBrand.brandName");
		String order = request.getParameter("order")==null?"":request.getParameter("order");//排序
		String order_flag = request.getParameter("order_flag")==null?"":request.getParameter("order_flag");//排序
		Map<String, Object> model = new HashMap<String, Object>();
		Page<GoodsModifier> page = prePage(request);
	    Page<GoodsModifier> pageData = goodsModifierService.getUnSpecifiedGoods(page, goodsClassName, goodsBrandName, order, order_flag);
	    endPage(model, pageData,request);
	    return new ModelAndView(Constants.JSON_VIEW, model);
	}

	
}
