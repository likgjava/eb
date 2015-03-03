package com.gpcsoft.goods.goods.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.bizplatform.base.purcategory.domain.PurCategory;
import com.gpcsoft.bizplatform.base.purcategory.service.PurCategoryService;
import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.utils.HqlResultConvertUtils;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.goods.goods.domain.Goods;
import com.gpcsoft.goods.goods.domain.GoodsBrand;
import com.gpcsoft.goods.goods.domain.GoodsChange;
import com.gpcsoft.goods.goods.enumeration.GoodsEnum;
import com.gpcsoft.goods.goods.service.GoodsBrandService;
import com.gpcsoft.goods.goods.service.GoodsChangeService;
import com.gpcsoft.goods.goods.service.GoodsService;
import com.gpcsoft.goods.goodsclass.domain.GoodsClass;
import com.gpcsoft.goods.goodsclass.service.GoodsClassService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.pubservice.common.enumeration.CommonEnum;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.auth.manager.RoleManager;

/**
  * @gpcsoft.view value="goodsChangeFormView"
  *  url="view/goods/goods/goodsmng/goods_change.jsp"
  *  
  * @gpcsoft.view value="goodsChangeAuditListView"
  *  url="view/goods/goods/goodsmng/goods_change_audit_list.jsp"
  *  
*  @gpcsoft.view value="toAuditChangeGoodsView"
*  url="view/goods/goods/goodsmng/goods_change_audit.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={GoodsChange.class})
@RequestMapping("/GoodsChangeController.do")//页面请求路径,可修改
public class GoodsChangeController extends AnnotationMultiController<GoodsChange> {

	@Autowired(required=true) @Qualifier("goodsServiceImpl")
	private GoodsService goodsService;
	@Autowired(required=true) @Qualifier("purCategoryServiceImpl")
	private PurCategoryService purCategoryService;
	@Autowired(required=true) @Qualifier("goodsClassServiceImpl")
	private GoodsClassService goodsClassService;
	@Autowired(required=true) @Qualifier("goodsBrandServiceImpl")
	private GoodsBrandService goodsBrandService;
	@Autowired(required=true) @Qualifier("goodsChangeServiceImpl")
	private GoodsChangeService goodsChangeService;
	@Autowired(required=true) @Qualifier("roleManagerImpl")
	private RoleManager roleManagerImpl;
	
	/** 
	 * Description :  跳转到商品变更
	 * Create Date: 2010-7-19下午06:29:00 by sunl  Modified Date: 2010-7-19下午06:29:00 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toGoodsChange")
    public ModelAndView toGoodsChange(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		String objId = request.getParameter("objId");
		String viewName = "goodsChangeFormView";
		
		if(StringUtils.hasLength(request.getParameter("viewName"))) {
			viewName = request.getParameter("viewName");
		}
		
		if(StringUtils.hasLength(objId)) {
			Goods goods  = goodsService.get(objId);
			model.put("oldGoods", goods);
		}
		
		//获取变更商品信息
		model.put("goodsChangeList", goodsChangeService.getGoodsAuditList(objId));
		
		return new ModelAndView(viewName, model);
	}
	
	/** 
	 * Description :  跳转到商品变更审核列表
	 * Create Date: 2010-7-19下午06:29:00 by sunl  Modified Date: 2010-7-19下午06:29:00 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=toGoodsChangeAuditList")
    public ModelAndView toGoodsChangeAuditList(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> params = new HashMap<String, Object>();

		String productName = request.getParameter("productName");
		params.put("productName", productName);
		
		//获取变更商品信息
		Page page = prePage(request);//预分页,算出当前页和大小等		
		Page pageData = (Page) goodsChangeService.getGoodsAuditListPage(page,params);
		
		String queryColumns = makeQueryColumns(request);//接收前台的指定列名
		String alias=request.getParameter("alias");
		pageData.setData(HqlResultConvertUtils.hqlResultConvert(pageData.getData(), queryColumns.split(","), alias==null?null:alias.split(","), Goods.class, getEnumColumns()));
		endPage(model, pageData, request);
		
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	/** 
	 * Description :  待审核变更商品列表
	 * Create Date: 2010-7-19下午06:29:00 by sunl  Modified Date: 2010-7-19下午06:29:00 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toAuditChangeGoodsList")
    public ModelAndView toAuditChangeGoodsList(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		List<GoodsChange> goodsChangeList = goodsChangeService.getGoodsAuditList(null);
		
		model.put("modifyAuditList", goodsChangeList);
		
		return new ModelAndView("goodsChangeAuditListView", model);
	}
	
	/** 
	 * Description :  跳转到审核变更商品
	 * Create Date: 2010-7-19下午06:29:00 by sunl  Modified Date: 2010-7-19下午06:29:00 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toAuditChangeGoods")
    public ModelAndView toAuditChangeGoods(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		String goodsId = request.getParameter("goodsId");
		
		List<GoodsChange> modifyAudit = goodsChangeService.getGoodsAuditList(goodsId);
		
		model.put("modifyAudit", modifyAudit);
		model.put("goodsId", goodsId);
		
		return new ModelAndView("toAuditChangeGoodsView", model);
	}
	
	/** 
	 * Description :  保存商品变更信息
	 * Create Date: 2010-7-19下午06:29:00 by sunl  Modified Date: 2010-7-19下午06:29:00 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveChange")
    public ModelAndView saveChange(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		String changeGoodsStr = request.getParameter("changeGoodsStr");
		
		JSONArray jsonArray = JSONArray.fromObject(changeGoodsStr);
		JSONObject[] jsonbject=(JSONObject[]) jsonArray.toArray(new JSONObject[]{});
		List<GoodsChange> goodsChangeList = new ArrayList<GoodsChange>();
		Goods goods = null;
		
		if(jsonbject!=null) {
			for(JSONObject obj:jsonbject){
				GoodsChange goodsChange = (GoodsChange)JSONObject.toBean(obj, GoodsChange.class);
				goodsChange.setCreateUser(AuthenticationHelper.getCurrentUser(true));
				goodsChange.setCreateTime(new Date());
				
				//如果manager变更，省去审核通过
				User user = AuthenticationHelper.getCurrentUser(true);
				if(roleManagerImpl.isHasThisRole(user.getObjId(), OrganizationEnum.ROLE_MANAGER)){
					goodsChange.setAuditStatus(CommonEnum.PASS_EXAM);//审核通过
					goods = goodsService.get(goodsChange.getGoods().getObjId());
					if("purCategory".equals(goodsChange.getModifyType())) {
						PurCategory newcategory = purCategoryService.get(goodsChange.getNewValueId());
						goods.setPurCategory(newcategory);
					} else if("goodsClass".equals(goodsChange.getModifyType())) {
						GoodsClass newclass = goodsClassService.get(goodsChange.getNewValueId());
						goods.setGoodsClass(newclass);
					} else if("goodsBrand".equals(goodsChange.getModifyType())) {
						GoodsBrand newbrand = goodsBrandService.get(goodsChange.getNewValueId());
						goods.setGoodsBrand(newbrand);
					}
				}
				
				goodsChangeList.add(goodsChange);
			}
		}
		goodsChangeService.save(goodsChangeList);
		if(goods != null) {
			goodsService.save(goods);
		}
		
		return new ModelAndView(Constants.JSON_VIEW,model);
    }
	
	/** 
	 * Description :  审核变更商品
	 * Create Date: 2010-7-19下午06:29:00 by sunl  Modified Date: 2010-7-19下午06:29:00 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=auditGoodsChange")
    public ModelAndView auditGoodsChange(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		String goodsId = request.getParameter("goodsId");
		String status = request.getParameter("status");
		
		Goods goods = goodsService.get(goodsId);
		
		List<GoodsChange> modifyAudit = goodsChangeService.getGoodsAuditList(goodsId);
		for (GoodsChange goodsChange : modifyAudit) {
			goodsChange.setAuditStatus(status);
			goodsChange.setVerifyUser(AuthenticationHelper.getCurrentUser(true));
			goodsChange.setVerifyTime(new Date());
			goodsChange.setOpinion(request.getParameter("opinion"));
			
			//审核通过
			if(GoodsEnum.PASS_EXAM.equals(status)) {
				if("purCategory".equals(goodsChange.getModifyType())) {
					PurCategory newcategory = purCategoryService.get(goodsChange.getNewValueId());
					goods.setPurCategory(newcategory);
				} else if("goodsClass".equals(goodsChange.getModifyType())) {
					GoodsClass newclass = goodsClassService.get(goodsChange.getNewValueId());
					goods.setGoodsClass(newclass);
				} else if("goodsBrand".equals(goodsChange.getModifyType())) {
					GoodsBrand newbrand = goodsBrandService.get(goodsChange.getNewValueId());
					goods.setGoodsBrand(newbrand);
				}
			}
		}
		goodsChangeService.save(modifyAudit);
		goodsService.save(goods);
		
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
}
