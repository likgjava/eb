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
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.bizplatform.base.common.util.StringUtils;
import com.gpcsoft.bizplatform.base.message.CustomerMessage;
import com.gpcsoft.bizplatform.base.purcategory.service.PurCategoryService;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.bizplatform.organization.service.OrgInfoService;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.utils.HqlResultConvertUtils;
import com.gpcsoft.core.utils.JsonUtils;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.goods.goods.domain.Goods;
import com.gpcsoft.goods.goods.domain.GoodsParam;
import com.gpcsoft.goods.goods.service.GoodsBrandService;
import com.gpcsoft.goods.goods.service.GoodsService;
import com.gpcsoft.goods.goodsclass.domain.GoodsClass;
import com.gpcsoft.goods.goodsclass.domain.GoodsClassParamType;
import com.gpcsoft.goods.goodsclass.service.GoodsClassCategoryService;
import com.gpcsoft.goods.goodsclass.service.GoodsClassParamTypeService;
import com.gpcsoft.goods.goodsclass.service.GoodsClassService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.auth.manager.RoleManager;
import com.gpcsoft.srplatform.auth.service.AttachmentService;
import com.gpcsoft.srplatform.common.utils.AttachmentUtil;

/**
  *  @gpcsoft.view value="goodsManageListView"
  *  url="view/goods/goods/goodsmng/goods_manage.jsp"
  *  
  *  @gpcsoft.view value="goodsAuditFormView"
  *  url="view/goods/goods/goodsmng/goods_audit.jsp"
  * 
  *  @gpcsoft.view value="goodsHistoryListView"
  *  url="view/goods/goods/goodsmng/goods_history_list.jsp"
  *  
  *  @gpcsoft.view value="goodsHistoryDetailView"
  *  url="view/goods/goods/goodsmng/goods_history_detail.jsp"
  * 
  * @gpcsoft.view value="manageOptionView"
  *  url="view/goods/goods/goodsmng/option_manage.jsp"
  *  
  * @gpcsoft.view value="goodsAddFormView_1"
  *  url="view/goods/goods/goodsmng/create_goods_1.jsp"
  *  
  * @gpcsoft.view value="goodsAddFormView_2"
  *  url="view/goods/goods/goodsmng/create_goods_2.jsp"
  *  
  * @gpcsoft.view value="goodsModifyFormView"
  *  url="view/goods/goods/goodsmng/update_goods.jsp"
  *  
  * @gpcsoft.view value="goodsDetailView"
  *  url="view/goods/goods/goodsmng/goods_detail.jsp"
  *  
  * @gpcsoft.view value="goodsTreeFormView"
  *  url="view/goods/jsp/goods/goodsTreeForm.jsp"
  *  
  * @gpcsoft.view value="goodsListView"
  *  url="view/goods/jsp/goods/goodsList.jsp"
  *  
  *  @gpcsoft.view value="accessoryAuditListView"
  *  url="view/goods/goods/fittingmng/accessory_audit_list.jsp"
  *  
  *  @gpcsoft.view value="goodsSubmitResultView"
  *  url="view/goods/goods/goodsmng/goods_submit_result.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={Goods.class})
@RequestMapping("/GoodsController.do")//页面请求路径,可修改
public class GoodsController extends AnnotationMultiController<Goods> {

	@Autowired(required=true) @Qualifier("goodsServiceImpl")
	private GoodsService goodsService;
	@Autowired(required=true) @Qualifier("purCategoryServiceImpl")
	private PurCategoryService purCategoryService;
	@Autowired(required=true) @Qualifier("goodsClassServiceImpl")
	private GoodsClassService goodsClassService;
	@Autowired(required=true) @Qualifier("goodsBrandServiceImpl")
	private GoodsBrandService goodsBrandService;
	@Autowired(required=true) @Qualifier("attachmentServiceImpl")
	private AttachmentService attachmentServiceImpl;
	@Autowired(required=true) @Qualifier("goodsClassParamTypeServiceImpl")
	private GoodsClassParamTypeService goodsClassParamTypeService;
	@Autowired(required=true) @Qualifier("roleManagerImpl")
	private RoleManager roleManagerImpl;
	@Autowired(required=true) @Qualifier("purCategoryServiceImpl")
	private PurCategoryService purCategoryServiceImpl;
	@Autowired(required=true) @Qualifier("goodsClassCategoryServiceImpl")
	private GoodsClassCategoryService goodsClassCategoryService;
	@Autowired(required=true) @Qualifier("orgInfoServiceImpl")
	private OrgInfoService orgInfoService;
	
	/** 
     * Description :  发布商品
     * Create Date: 2010-8-11下午04:40:52 by sunl  Modified Date: 2010-8-11下午04:40:52 by sunl
     * @param   
     * @return  
     * @Exception   
     */
	@RequestMapping(params = "method=toCreateGoods")
	public ModelAndView toCreateGoods(HttpServletRequest request) throws Exception {
		User user = AuthenticationHelper.getCurrentUser(true);
		
		String viewName = "";
		Map<String, Object> model = new HashMap<String, Object>();   
		
		//获取供应商状态
		Map<String,Object> resultMap = orgInfoService.getCurrentOrgStatus();
		model.put("currSuppStatus", resultMap.get("currSuppStatus").toString());
    	
		model.put("goods", new Goods());
		String categoryId = request.getParameter("categoryId");
		String classId = request.getParameter("classId");
		String brandId = request.getParameter("brandId");
		if(categoryId != null && classId != null && brandId != null){
			model.put("category", purCategoryService.get(categoryId));
			model.put("class", goodsClassService.get(classId));
			model.put("brand", goodsBrandService.get(brandId));
			//取出参数分类
			List<GoodsClassParamType> goodsClassParamList = goodsClassParamTypeService.getGoodsClassParamByClass(classId);
			model.put("goodsClassParamList", goodsClassParamList);
			
			//当前用户角色
	    	if(roleManagerImpl.isHasThisRole(user.getObjId(), "3")){
	    		model.put("currRoleType","3");
	    	}else{
	    		model.put("currRoleType","4");
	    	}
	    	
			viewName = "goodsAddFormView_2";
		}else{
			viewName = "goodsAddFormView_1";
		}
		return new ModelAndView(viewName, model);
	}
	
	/** 
	 * Description :  跳转到发布商品的结果页面
	 * Create Date: 2011-6-29下午04:27:45 by likg  Modified Date: 2011-6-29下午04:27:45 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toGoodsSubmitResultView")
	public ModelAndView toGoodsSubmitResultView(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		return new ModelAndView("goodsSubmitResultView", model);
	}
	
	/** 
     * Description :  修改商品
     * Create Date: 2010-8-11下午04:40:52 by sunl  Modified Date: 2010-8-11下午04:40:52 by sunl
     * @param   
     * @return  
     * @Exception   
     */
	@RequestMapping(params = "method=toUpdateGoods")
	public ModelAndView toUpdateGoods(HttpServletRequest request) throws Exception {
		User user = AuthenticationHelper.getCurrentUser(true);
		
		Map<String, Object> model = new HashMap<String, Object>();   
		
		//获取供应商状态
		Map<String,Object> resultMap = orgInfoService.getCurrentOrgStatus();
		model.put("currSuppStatus", resultMap.get("currSuppStatus").toString());
		
		//当前用户角色
    	if(roleManagerImpl.isHasThisRole(user.getObjId(), "3")){
    		model.put("currRoleType","3");
    	}else{
    		model.put("currRoleType","4");
    	}
    	
    	Goods goods  = goodsService.getGoodsAllInfo(request.getParameter("objId"));
		List<Object> goodsClassParamList = goodsService.getGoodsParamAndClassParam(goods.getObjId(),goods.getGoodsClass().getObjId());
		model.put("goodsClassParamList", goodsClassParamList);
		model.put("goods", goods);
		return new ModelAndView("goodsModifyFormView", model);
	}
	
	/** 
	 * Description :  商品唯一性校验
	 * 				  1 同分类、同品牌、有效的、商品名称不能重复
	 * 				  2 同分类、同品牌、有效的、商品型号不能重复
	 * Create Date: 2011-5-6下午06:50:51 by sunl  Modified Date: 2011-5-6下午06:50:51 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=checkGoodsUnique")
	public ModelAndView checkGoodsUnique(HttpServletRequest request) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		String objId = request.getParameter("objId");
		
		param.put("objId", objId);
		
		//商品品名和分类
		param.put("classId", request.getParameter("classId"));
		param.put("brandId", request.getParameter("brandId"));
		
		//商品名称和型号的校验
		param.put("productName", StringUtils.ascii2Native(request.getParameter("productName")));
		param.put("productCode", StringUtils.ascii2Native(request.getParameter("productCode")));
		
		
		Map<String,Object> result = goodsService.checkGoodsUnique(param);
		
		return new ModelAndView(Constants.JSON_VIEW, result);	
	}

    /** 
     * Description :  跳转到商品审核页面
     * Create Date: 2010-8-11下午04:40:52 by sunl  Modified Date: 2010-8-11下午04:40:52 by sunl
     * @param   
     * @return  
     * @Exception   
     */
    @RequestMapping(params = "method=toAuditGoods")
    public ModelAndView toAuditGoods(String objId,HttpServletRequest request) throws Exception {
    	Map<String, Object> model = new HashMap<String, Object>(); 
        Goods goodsAudit = goodsService.getGoodsAllInfo(objId);
        model.put("goodsAudit", goodsAudit);
        
        //获得历史商品信息
		Goods historyObject = null;
		if(StringUtils.hasLength(goodsAudit.getCurrentId())){
			historyObject = goodsService.get(goodsAudit.getCurrentId());
		}else{
			historyObject = new Goods();
		}
		model.put("historyObject", JsonUtils.getJSONString(historyObject, "Goods"));
        return new ModelAndView("goodsAuditFormView", model);
    }
    
    /** 
     * Description :  商品审核
     * Create Date: 2010-8-11下午04:40:52 by sunl  Modified Date: 2010-8-11下午04:40:52 by sunl
     * @param   
     * @return  
     * @Exception   
     */
    @RequestMapping(params = "method=auditGoods")
    public ModelAndView auditGoods(Goods goods,HttpServletRequest request,SessionStatus status) throws Exception {
    	//商品审核
    	goodsService.auditGoods(goods);
    	
    	status.setComplete();
    	return new ModelAndView(Constants.JSON_VIEW);
    }
	
	/** 
	 * Description :  跳转到添加或修改商品参数页面，根据商品Id获取商品参数
	 * Create Date: 2010-8-6上午11:03:38 by sunl  Modified Date: 2010-8-6上午11:03:38 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toAddOrModifyGoodsParam")
	public ModelAndView toAddOrModifyGoodsParam(String goodsId,HttpServletRequest request) throws Exception {
		String viewName = "manageOptionView";
		Map<String, Object> model = new HashMap<String, Object>(); 
		Goods goods = goodsService.getGoodsAllInfo(goodsId);
		model.put("goods",goods);
		return new ModelAndView(viewName, model);
	}
	
	/** 
	 * Description :  创建商品
	 * Create Date: 2010-8-3下午05:38:51 by sunl  Modified Date: 2010-8-3下午05:38:51 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=createGoods")
    public ModelAndView createGoods(String goodsString,HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>(); 
		
		//处理json转换时问题
		goodsString = getValidGoodsString(goodsString);
		
		//转换商品和商品参数集合
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("goodsParamSet", GoodsParam.class);
		Goods goods = JsonUtils.json2Bean(goodsString, Goods.class, map);

		//上传商品图片
		//String attachmentId = saveGoodsAttachment(request,"pictureFile");
		//goods.setPicture(attachmentId);
		
		//创建商品
		goodsService.createGoods(goods);
		
		//预览商品链接
		model.put("goodsid", goods.getObjId());
		
		return new ModelAndView(Constants.JSON_VIEW, model);
    }
	
	/** 
	 * Description :  修改商品
	 * Create Date: 2010-8-3下午05:38:51 by sunl  Modified Date: 2010-8-3下午05:38:51 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=updateGoods")
    public ModelAndView updateGoods(String goodsString,HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>(); 
		
		//处理json转换时问题
		goodsString = getValidGoodsString(goodsString);
		
		//转换商品和商品参数集合
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("goodsParamSet", GoodsParam.class);
		Goods goods = JsonUtils.json2Bean(goodsString, Goods.class, map);
		
		//上传商品图片
		//request.getAttribute("pictureFile");
		//request.getParameter("pictureFile");
		//String attachmentId = saveGoodsAttachment(request,"pictureFile");
		
		//若上传了新图片则修改否则不修改
		//if(StringUtils.hasLength(attachmentId)) {
		//	goods.setPicture(attachmentId);
		//}
		
		//修改商品
		goodsService.updateGoods(goods);
		
		return new ModelAndView(Constants.JSON_VIEW, model);
    }
	
	/** 
	 * Description :  获得商品历史列表
	 * Create Date: 2010-8-9上午10:31:54 by sunl  Modified Date: 2010-8-9上午10:31:54 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getGoodsHistory")
	public ModelAndView getGoodsHistory(HttpServletRequest request) throws Exception{
		String viewName = "goodsHistoryListView";
		Map<String, Object> model = new HashMap<String, Object>(); 		
		
		//如果是查询列表
		if(null == request.getParameter("type")){
			Map<String, Object> param = new HashMap<String, Object>();  
			param.put("id", request.getParameter("id"));
			List<Goods> goodsHistoryList = new ArrayList<Goods>();
			goodsHistoryList = goodsService.getGoodsHistory(param);
			model.put("goodsHistoryList",goodsHistoryList);
		}
		//如果是单条查询
		else if(null != request.getParameter("type") && "view".equals(request.getParameter("type"))){
			Goods goodsHistory = goodsService.getGoodsAllInfo(request.getParameter("id"));	
			model.put("goodsHistory",goodsHistory);
			viewName = "goodsHistoryDetailView";
		}
        return new ModelAndView(viewName, model);
	}
    
    /** 
     * Description :  启卖商品
     * Create Date: 2010-8-10下午04:08:45 by sunl  Modified Date: 2010-8-10下午04:08:45 by sunl
     * @param   
     * @return  
     * @Exception   
     */
    @RequestMapping(params = "method=enableGoods")
    public ModelAndView enableGoods(String objIds,SessionStatus status) throws Exception {
        Map<String, Object> model = new HashMap<String, Object>();
        goodsService.enableGoods(objIds);
        status.setComplete();
        model.put(Constants.JSON_RESULT,messageSource.getMessage(CustomerMessage.GOODS_START_SUCCESS));
        return new ModelAndView(Constants.JSON_VIEW, model);
    }
    
    /** 
     * Description :  禁卖商品
     * Create Date: 2010-8-10下午04:08:45 by sunl  Modified Date: 2010-8-10下午04:08:45 by sunl
     * @param   
     * @return  
     * @Exception   
     */
    @RequestMapping(params = "method=disableGoods")
    public ModelAndView disableGoods(String objIds,SessionStatus status) throws Exception {
        Map<String, Object> model = new HashMap<String, Object>();
        goodsService.disableGoods(objIds);
        status.setComplete();
        model.put(Constants.JSON_RESULT,messageSource.getMessage(CustomerMessage.GOODS_FORBIDDEN_SUCCESS));
        return new ModelAndView(Constants.JSON_VIEW, model);
    }
    
    /** 
     * Description :  报废商品
     * Create Date: 2010-8-10下午04:08:45 by sunl  Modified Date: 2010-8-10下午04:08:45 by sunl
     * @param   
     * @return  
     * @Exception   
     */
    @RequestMapping(params = "method=scrappedGoods")
    public ModelAndView scrappedGoods(String objIds,SessionStatus status) throws Exception {
        Map<String, Object> model = new HashMap<String, Object>();
        goodsService.scrappedGoods(objIds);
        status.setComplete();
        model.put(Constants.JSON_RESULT,messageSource.getMessage(CustomerMessage.GOODS_SCRAPPED_SUCCESS));
        return new ModelAndView(Constants.JSON_VIEW, model);
    }
    
    /** 
     * Description : 商品删除，接收逗号分隔的objId字串可进行批量删除
     * 				 如果是变更商品(currentId不为空)，则删除之后更新currentId
     * Create Date: 2010-8-10上午11:13:50 by sunl  Modified Date: 2010-8-10上午11:13:50 by sunl
     * @param   
     * @return  
     * @Exception   
     */
    @RequestMapping(params = "method=removeGoods")
    public ModelAndView removeGoods(String objIds,SessionStatus status) throws Exception {
        Map<String, Object> model = new HashMap<String, Object>();
        goodsService.removeGoods(objIds);
        status.setComplete();
        model.put(Constants.JSON_RESULT,"删除成功!");
        return new ModelAndView(Constants.JSON_VIEW, model);
    }
    
    /** 
     * Description : 根据经营范围获取所能维护的商品分类
     * Create Date: 2010-8-10上午11:13:50 by sunl  Modified Date: 2010-8-10上午11:13:50 by sunl
     * @param   
     * @return  
     * @Exception   
     */
    @RequestMapping(params = "method=getGoodsClassByOrg")
    public ModelAndView getGoodsClassByOrg(HttpServletRequest request) throws Exception {
    	OrgInfo currentOrg = (OrgInfo)AuthenticationHelper.getCurrentUser(true).getOrgInfo();
		
    	String bidRangeCodes = currentOrg.getBidForRange();
    	
    	List<Object[]> res = new ArrayList<Object[]>();
    	
    	if(StringUtils.hasLength(bidRangeCodes)) {
    		res = purCategoryServiceImpl.getCategoryByMainProducts(bidRangeCodes);
    	}
    	
    	String categorys = "";
    	for (Object[] obj : res) {
    		categorys += obj[0] + ",";
    	}
    	if(categorys.length() > 0) {
    		categorys = categorys.substring(0,categorys.length()-1);
    	}
    	
    	List<GoodsClass> goodsClass = new ArrayList<GoodsClass>();
    	if(StringUtils.hasLength(categorys)) {
    		goodsClass = goodsClassCategoryService.getClassListByCategory(categorys,null,true);
    	}
        
        Map<String, Object> model = new HashMap<String, Object>();
        
        //获取供应商状态
		Map<String,Object> resultMap = orgInfoService.getCurrentOrgStatus();
		model.put("currSuppStatus", resultMap.get("currSuppStatus").toString());
		
        model.put("goodsClass", goodsClass);
        model.put("cutab", request.getParameter("currentTab")==null?"":request.getParameter("currentTab"));
        return new ModelAndView("goodsManageListView",model);
    }
    
    /** 
	 * Description :  商品列表
	 * 				  查询条件为：指定维护商的维护商品
	 * Create Date: 2010-7-28上午09:39:54 by sunl  Modified Date: 2010-7-28上午09:39:54 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=listGoods")
    public ModelAndView listGoods(HttpServletRequest request) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		Map<String, Object> model = new HashMap<String, Object>();
		
		param.put("type", request.getParameter("type")==null?"":request.getParameter("type"));//li类型
		
		String goodsClassId = request.getParameter("goodsClassId");
		param.put("brandName", StringUtils.spaceToPercent(request.getParameter("brandName")));//商品品牌
		param.put("productName", StringUtils.spaceToPercent(request.getParameter("productName")));//商品名称
		param.put("productCode", StringUtils.spaceToPercent(request.getParameter("productCode")));//规格型号
		param.put("startDate", request.getParameter("startDate"));//创建时间
		param.put("endDate", request.getParameter("endDate"));//创建时间
		param.put("order", request.getParameter("order")==null?"":request.getParameter("order"));//排序
		param.put("order_flag", request.getParameter("order_flag")==null?"":request.getParameter("order_flag"));//排序
		
		//manager查询(或商品管理员)，指定列和供应商不一样
		User user = AuthenticationHelper.getCurrentUser(true);
		if(roleManagerImpl.isHasThisRole(user.getObjId(), OrganizationEnum.ROLE_MANAGER) ||
				roleManagerImpl.isHasThisRole(user.getObjId(), OrganizationEnum.ROLE_GOODS_MANAGER)){
			param.put("roleType", "manager");
		} 
		else {
			param.put("goodsClassId", goodsClassId);//商品分类
			param.put("roleType", "supplier");
		}
		
		Page page = prePage(request);//预分页,算出当前页和大小等		
		Page pageData = (Page) goodsService.listGoods(page,param);
		
		String queryColumns = makeQueryColumns(request);//接收前台的指定列名
		String alias=request.getParameter("alias");
		pageData.setData(HqlResultConvertUtils.hqlResultConvert(pageData.getData(), queryColumns.split(","), alias==null?null:alias.split(","), this.getPersistClass(), getEnumColumns()));
		
		endPage(model, pageData, request);
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	/** 
	 * Description :  数据迁移方法
	 * Create Date: 2010-8-3下午05:38:51 by sunl  Modified Date: 2010-8-3下午05:38:51 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=update_goods_transfer")
    public ModelAndView update_goods_transfer(HttpServletRequest request) throws Exception {
		goodsService.update_goods_transfer(null);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("result","成功");
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	/** 
	 * Description :  获取供应商的报价的商品列表数据
	 * Create Date: 2009-4-13上午10:53:40 by yucy  Modified Date: 2009-4-13上午10:53:40 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=getGoodsListForSupplier")   
	public ModelAndView getGoodsListForSupplier(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Page<Goods> page = prePage(request); //预分页,算出当前页和大小等		
		
		//参数封装
		Map<String, Object> param = new HashMap<String, Object>();
		
		//查询条件
		param.put("productName", StringUtils.spaceToPercent(request.getParameter("productName")));
		param.put("brandName", StringUtils.spaceToPercent(request.getParameter("goodsBrand.brandName")));
		param.put("className", StringUtils.spaceToPercent(request.getParameter("goodsClass.className")));
		param.put("orgInfo", AuthenticationHelper.getCurrentUser(true).getOrgInfo());
		
		//特殊条件
		//不过滤投标品目//request.getParameter("nobidForRange") 为 ‘true’时则不过滤品目
		param.put("nobidForRange", request.getParameter("nobidForRange"));
		param.put("categoryId", request.getParameter("categoryId"));
		
		//是否有有效行情
		param.put("validePrice", request.getParameter("validePrice"));
		
		//无有效行情
		param.put("noValidePrice", request.getParameter("noValidePrice"));

		//排序
		param.put("order", request.getParameter("order"));
		param.put("order_flag", request.getParameter("order_flag"));
		
		Page<Goods> pageData = (Page<Goods>) goodsService.getGoodsListBySupplierBidRange(page,param);
		endPage(model, pageData, request);
		return new ModelAndView(Constants.JSON_VIEW,model);
    }
	
	/**
	 * 处理商品图片
	 * Description :  
	 * Create Date: 2011-5-5下午06:04:59 by sunl  Modified Date: 2011-5-5下午06:04:59 by sunl
	 * @param   
	 * @return  
	 * @Exception
	 */
	public String saveGoodsAttachment(HttpServletRequest request,String requestFileName) throws Exception {
		String attachmentId = "";
		CommonsMultipartFile file = null;
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
	    file = (CommonsMultipartFile)multipartRequest.getFile(requestFileName);
		if(file != null && file.getSize() != 0){
			Object o=AttachmentUtil.uploadFile(request,requestFileName);
			if(o instanceof GpcBaseObject){
				Attachment attachment = (Attachment)o;
				attachmentServiceImpl.saveAttachment((Attachment)o);
				attachmentId = attachment.getObjId();
			}
		}
		
		return attachmentId;
	}
	
	/**
	 * 格式化json转换前字符串
	 * Description :  
	 * Create Date: 2011-5-5下午06:04:59 by sunl  Modified Date: 2011-5-5下午06:04:59 by sunl
	 * @param   
	 * @return  
	 * @Exception
	 */
	public String getValidGoodsString(String goodsString) throws Exception {
		if(StringUtils.hasLength(goodsString)) {
			String tem = goodsString.substring(goodsString.length()-2,goodsString.length());
			if("\"".equals(goodsString.substring(goodsString.lastIndexOf('"'),goodsString.length()))) {
				goodsString += "\"}";
			}
			if(!"\"}".equals(tem)) {
				goodsString += "\"}";
			}
			goodsString=goodsString.replaceAll("\"null\"","\"\"");
		}
		return goodsString;
	}
}
