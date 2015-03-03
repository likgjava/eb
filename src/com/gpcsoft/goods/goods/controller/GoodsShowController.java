package com.gpcsoft.goods.goods.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.tools.ant.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.bizplatform.base.application.domain.HotTags;
import com.gpcsoft.bizplatform.base.application.service.HotTagsService;
import com.gpcsoft.bizplatform.base.common.util.StringUtils;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryObjectBase;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.utils.CollectionUtils;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.goods.goods.domain.Goods;
import com.gpcsoft.goods.goods.domain.GoodsParam;
import com.gpcsoft.goods.goods.domain.RecommendGoods;
import com.gpcsoft.goods.goods.domain.RecommendGoodsBrand;
import com.gpcsoft.goods.goods.service.GoodsBrandService;
import com.gpcsoft.goods.goods.service.GoodsClassBrandService;
import com.gpcsoft.goods.goods.service.GoodsService;
import com.gpcsoft.goods.goods.service.RecommendGoodsBrandService;
import com.gpcsoft.goods.goods.service.RecommendGoodsService;
import com.gpcsoft.goods.goodsclass.domain.GoodsClass;
import com.gpcsoft.goods.goodsclass.domain.GoodsClassParamType;
import com.gpcsoft.goods.goodsclass.service.GoodsClassParamTypeService;
import com.gpcsoft.goods.goodsclass.service.GoodsClassService;
import com.gpcsoft.goods.goodsprice.domain.GoodsPrice;
import com.gpcsoft.goods.goodsprice.service.AttentionPriceService;
import com.gpcsoft.goods.goodsprice.service.GoodsPriceService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.pubservice.application.service.service.ServiceSubscribeService;
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.auth.manager.RoleManager;
import com.gpcsoft.srplatform.auth.service.AttachmentService;

/**
  * @gpcsoft.view value="goodsDetailView"
  *  url="view/goods/goods/goodsmng/goods_detail.jsp"
  * @gpcsoft.view value="goodsShowListView"
  *  url="view/goods/showgoods/show_goods_list.jsp"
  * @gpcsoft.view value="goodsShowForListView"
  *  url="view/goods/showgoods/goods_list_div_l.jsp"
  * @gpcsoft.view value="goodsShowForPicView"
  *  url="view/goods/showgoods/goods_list_div_p.jsp"
  * @gpcsoft.view value="goodsShowInfoView"
  *  url="view/goods/showgoods/show_goods_detail.jsp"
  * @gpcsoft.view value="goodsCompareView"
  *  url="view/goods/showgoods/show_goods_compare.jsp"
  * @gpcsoft.view value="goodsRecommendView"
  *  url="view/goods/showgoods/goods_recommend_list.jsp"
  *  
  * @gpcsoft.view value="goodsShowByBrandListView"
  *  url="view/goods/showgoods/show_goods_list_by_brand.jsp"
  *  
  * @gpcsoft.view value="showGoodsIndexView"
  *  url="view/goods/showgoods/show_goods_index.jsp"
  *  
  * @gpcsoft.view value="goodsListByClassView"
  *  url="view/goods/showgoods/goods_list_by_class_div.jsp"
  *  
  * @gpcsoft.view value="goodsPreview"
  *  url="view/goods/goods/goodsmng/goods_preview.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@RequestMapping("/GoodsShowController.do")//页面请求路径,可修改
public class GoodsShowController extends AnnotationMultiController<GpcBaseObject> {

	@Autowired(required=true) @Qualifier("goodsServiceImpl")
	private GoodsService goodsService;
	
	@Autowired(required=true) @Qualifier("goodsClassServiceImpl")
	private GoodsClassService goodsClassService;
	
	@Autowired(required=true) @Qualifier("goodsClassParamTypeServiceImpl")
	private GoodsClassParamTypeService goodsClassParamTypeService;
	
	@Autowired(required=true) @Qualifier("attachmentServiceImpl")
	private AttachmentService attachmentService;
	
	@Autowired(required=true) @Qualifier("recommendGoodsServiceImpl")
	private RecommendGoodsService recommendGoodsService;
	
	@Autowired(required=true) @Qualifier("goodsPriceServiceImpl")
	private GoodsPriceService goodsPriceService;
	
	@Autowired(required=true) @Qualifier("roleManagerImpl")
	private RoleManager roleManagerImpl;
	
	@Autowired(required=true) @Qualifier("goodsClassBrandServiceImpl")
	private GoodsClassBrandService goodsClassBrandService;
	
	@Autowired(required=true) @Qualifier("goodsBrandServiceImpl")
	private GoodsBrandService goodsBrandService;
	
	@Autowired(required=true) @Qualifier("hotTagsServiceImpl")
	private HotTagsService hotTagsServiceService;
	
	@Autowired(required=true) @Qualifier("attentionPriceServiceImpl")
	private AttentionPriceService attentionPriceService;
	
	@Autowired(required=true)  @Qualifier("serviceSubscribeServiceImpl")
	private ServiceSubscribeService serviceSubscribeService;
	
	@Autowired(required=true) @Qualifier("recommendGoodsBrandServiceImpl")
	private RecommendGoodsBrandService recommendGoodsBrandService;
	
    /** 
     * Description :  跳转到商品展示的二级页面，也就是列表页面
     * Create Date: 2010-7-27上午10:02:32 by liangxj  Modified Date: 2010-7-27上午10:02:32 by liangxj
     * @param   categoryId 品目id categoryCode 品目编号
     * @return  
     * @Exception   
     */
    @SuppressWarnings("unchecked")
	@RequestMapping(params = "method=toGoodsList")
    public ModelAndView toGoodsList(HttpServletRequest request) throws Exception {
    	Map<String, Object> model = new HashMap<String, Object>();
    	Map<String, Object> paramsMap = new HashMap<String, Object>();//paramsMap这个是查询条件
    	
    	/** goodsClassCode和keyWord来自与首页的参数 */
    	String goodsClassCode = request.getParameter("goodsClassCode");
    	String keyWord = StringUtils.ascii2Native(request.getParameter("keyWord"));
    	//String keyWord = StringUtils.Base64Code(request.getParameter("keyWord"));//改为base64编码

    	/** 判断如果没有分类编号或者关键字，则跳转至空页面，加载商品分类列表*/
    	if((goodsClassCode != null && !"".equals(goodsClassCode)) || (keyWord != null && !"".equals(keyWord))) {
	    	paramsMap.put("goodsClassCode", goodsClassCode);
	    	paramsMap.put("keyWord",StringUtils.replaceIngoreStr(keyWord));
	    	
	    	/** 如果从详情页面跳转过来，需要过滤具体的分类和品牌*/
	    	String classId = "";
	    	String brandId = "";
	    	if("detail".equals(request.getParameter("type"))) {
	    		classId = request.getParameter("goodsClassId");
	    		brandId = request.getParameter("brandId");
	    		
	    		paramsMap.put("goodsClassId", classId);
	        	paramsMap.put("brandId", brandId);
	    	}
	    	
	    	/** 取商品分类信息*/
	    	if(goodsClassCode != null) {
	    		GoodsClass goodsClass = goodsClassService.getGoodsClassByCode(goodsClassCode);
	    		model.put("goodsClass",goodsClass);
	    	}
	    	
	    	/** 取商品信息*/
	    	Page<Goods> page = prePage(request);//预分页,算出当前页和大小等	
	    	paramsMap.put("order", "productDateIssued desc"); //按产品发布时间倒序排列
	    	Page<Goods> pageData = goodsService.getGoodsListForShow(page,paramsMap);
	    	endPage(model, pageData, request);
	    	model.put("PAGERESULT",pageData);
	    	
	    	/** 取商品分类信息*/
	    	List<String[]> listClass = goodsService.getClassListShowByClass("",goodsClassCode,true,keyWord);
	    	model.put("goodsClassList", listClass);
	    	
	    	/** 取品牌信息*/
	    	List<String[]> listBrand =  goodsService.getBrandsListShowByClass(classId,goodsClassCode,keyWord);
	    	model.put("goodsBrandList", listBrand);
	    	
	    	/** 回设高亮参数或首页参数 */
	    	model.put("goodsClassId", classId);
	    	model.put("brandId", brandId);
	    	model.put("keyWord",StringUtils.returnIngoreStr(keyWord));
    	}else {
    		//跳至选择分类页面
    		model.put("gclist", goodsClassService.findGoodsClassForHasGoods(null));
    		return new ModelAndView("goodsClassSortView", model);
		}
    	
        return new ModelAndView("goodsShowListView",model);
    }
    
    /** 
     * Description :  跳转到商品展示的二级页面(byBrand)
     * Create Date: 2011-1-18下午03:58:21 by yucy  Modified Date: 2011-1-18下午03:58:21 by yucy
     * @param   
     * @return  
     * @Exception   
     */
    @SuppressWarnings("unchecked")
	@RequestMapping(params = "method=toGoodsByBrandList")
    public ModelAndView toGoodsByBrandList(HttpServletRequest request) throws Exception {
    	Map<String, Object> model = new HashMap<String, Object>();
    	Map<String, Object> paramsMap = new HashMap<String, Object>();//paramsMap这个是查询条件
    	
    	/** brandId和keyWord来自与首页的参数 */
		String brandId = request.getParameter("brandId");
    	String keyWord = StringUtils.ascii2Native(request.getParameter("keyWord"));
    	
    	if(StringUtils.hasLength(brandId)){
    		model.put("brand",goodsBrandService.get(brandId));
    	}
    	
    	/** 判断如果没有品牌或者关键字，则跳转至空页面，加载商品分类列表*/
    	if((brandId != null && !"".equals(brandId)) || (keyWord != null && !"".equals(keyWord))) {
	    	paramsMap.put("brandId", brandId);
	    	paramsMap.put("keyWord",StringUtils.replaceIngoreStr(keyWord));
	    	
	    	/** 如果从详情页面跳转过来，需要过滤具体的分类和品牌*/
    		paramsMap.put("goodsClassId", request.getParameter("goodsClassId"));
        	paramsMap.put("brandId", brandId);
        	
        	/**根据品牌获得分类*/
        	List<GoodsClass> goodsClassList =  goodsClassBrandService.getClassListByBrand(brandId);
	    	model.put("goodsClassList",goodsClassList);
	    	
	    	/** 取商品信息*/
	    	Page<Goods> page = prePage(request);//预分页,算出当前页和大小等	
	    	paramsMap.put("order", "productDateIssued desc"); //按产品发布时间倒序排列
	    	Page<Goods> pageData = goodsService.getGoodsListForShow(page,paramsMap);
	    	endPage(model, pageData, request);
	    	model.put("PAGERESULT",pageData);
	    	
			//获取推荐商品信息
	    	Page<RecommendGoods> page2 = new Page(1L,  5L, 5, new ArrayList());
	    	Page<RecommendGoods> pageData2 = recommendGoodsService.getRecommendGoods(page2, paramsMap);
	    	model.put("recommendGoodsList", pageData2.getData());
	    	
	    	/** 回设高亮参数或首页参数 */
	    	model.put("brandId", brandId);
	    	model.put("keyWord",StringUtils.returnIngoreStr(keyWord));
    	}
    	
        return new ModelAndView("goodsShowByBrandListView",model);
    }
    
	/** 
	 * Description :  获取商品展示的列表数据或大图显示数据
	 * Create Date: 2010-7-27上午11:46:10 by liangxj  Modified Date: 2010-7-27上午11:46:10 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=getGoodsForListAndPic")   
	public ModelAndView getGoodsForListAndPic(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> paramsMap = new HashMap<String, Object>();//paramsMap这个是查询条件
		
		//商品分类、品牌
		paramsMap.put("goodsClassCode", request.getParameter("goodsClassCode"));
    	paramsMap.put("goodsClassId", request.getParameter("goodsClassId"));
    	paramsMap.put("brandId", request.getParameter("brandId"));
    	
    	//更多过滤信息
    	paramsMap.put("moreFilter", request.getParameter("moreFilter"));
    	String keyWord = request.getParameter("keyWord");
    	paramsMap.put("keyWord",StringUtils.replaceIngoreStr(StringUtils.ascii2Native(keyWord)));
    	paramsMap.put("price",request.getParameter("price"));
    	
    	//排序
    	String sort = request.getParameter("order");
    	if(sort != null) {
    		paramsMap.put("order", sort.replace("_", " "));
    	} else {
    		paramsMap.put("order", "productDateIssued desc"); //按产品发布时间倒序排列
    	}
    	
    	String style = request.getParameter("style");  //显示样式
    	
    	/** 取商品信息*/
    	Page<Goods> page = prePage(request);//预分页,算出当前页和大小等	
    	Page<Goods> pageData = goodsService.getGoodsListForShow(page,paramsMap);
    	endPage(model, pageData, request);
    	model.put("PAGERESULT",pageData);
    	model.put("goodsClassId",request.getParameter("goodsClassId"));

    	//如果样式是style，则大图显示商品
    	String view = "goodsShowForListView";
    	if("pic".equals(style))
    		view = "goodsShowForPicView";
    	
		return new ModelAndView(view,model);
    }
	
	/** 
	 * Description :  根据商品分类获得商品品牌的展示信息集合
	 * Create Date: 2010-8-3上午11:44:58 by sunl  Modified Date: 2010-8-3上午11:44:58 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getBrandsListShowByClass")
	public ModelAndView getBrandsListShowByClass(String classId,String goodsClassCode,HttpServletRequest request) throws Exception {
		String keyWord = StringUtils.ascii2Native(request.getParameter("keyWord"));
		
		List<String[]> goodsBrand = goodsService.getBrandsListShowByClass(classId,goodsClassCode,keyWord);
		
    	Map<String,Object> model = new HashMap<String,Object>();
    	model.put("result", goodsBrand);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description : 获得推荐商品 
	 * Create Date: 2010-8-20上午11:58:07 by liangxj  Modified Date: 2010-11-5上午11:58:07 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=getRecommendGoods")
	public ModelAndView getRecommendGoods(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> paramsMap = new HashMap<String, Object>();//paramsMap这个是查询条件
		
		/** goodsClassCode和keyWord来自与首页的参数 */
    	String keyWord = request.getParameter("keyWord");
    	paramsMap.put("goodsClassCode", request.getParameter("goodsClassCode"));
    	paramsMap.put("goodsClassId",request.getParameter("goodsClassId"));
    	paramsMap.put("brandId",request.getParameter("brandId"));
    	paramsMap.put("keyWord",StringUtils.replaceIngoreStr(StringUtils.ascii2Native(keyWord)));
    	
		//获取推荐商品信息
    	Page<RecommendGoods> page = prePage(request);//预分页,算出当前页和大小等	
    	Page<RecommendGoods> pageData = recommendGoodsService.getRecommendGoods(page, paramsMap);
    	model.put("recommendGoodsList", pageData.getData());
    	
		return new ModelAndView("goodsRecommendView", model);
	} 
	
	/** 
	 * Description :  获得商品的详细信息
	 * Create Date: 2010-8-4下午04:28:52 by liangxj  Modified Date: 2010-8-4下午04:28:52 by liangxj
	 * @param   isShowPic 是否显示商品附图
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getGoodsInfo")
	public ModelAndView getGoodsInfo(String objId,String viewName,Boolean isShowPic,HttpServletRequest request) throws Exception {
		Map<String,Object> model = new HashMap<String,Object>();
		
		//查询商品
		Goods goods = goodsService.getGoodsAllInfo(objId);
		model.put("goods", goods);
		
		//获得商品的附图
		if(isShowPic && goods.getAdditionPicture() != null){
			List<Attachment> images = attachmentService.getAttachmentsByRealID(goods.getAdditionPicture());
			model.put("images", images);
		}
    	
    	if(viewName == null) {
    		viewName = "goodsShowInfoView";
    	}
    	
    	/** 取商品分类信息*/
    	String classCode = request.getParameter("goodsClassCode");
    	if(classCode != null) {
    		
        	GoodsClass goodsClass = goodsClassService.getGoodsClassByCode(classCode);
        	model.put("goodsClass",goodsClass);
    	}
    	
    	//当前用户是否商圈会员
    	boolean isMember =  false ;
    	if(AuthenticationHelper.getCurrentUser() != null  && AuthenticationHelper.getCurrentUser().getOrgInfo() != null) {
    		isMember = serviceSubscribeService.isShangQuanHuiYuan(((OrgInfo)AuthenticationHelper.getCurrentUser(true).getOrgInfo()).getObjId());
    	}

    	model.put("isMember",isMember);
    	
    	//行情区间	by yucy (为解决内存溢出)
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("objId", objId);
		
		if(isMember)param.put("order","member_price");//如果是商圈会员则传递这个排序参数
		
		List<GoodsPrice> goodsPriceList = goodsPriceService.getGoodsPriceList(param);
		
		if(goodsPriceList.size()>0){
			model.put("minPrtcPrice",isMember?goodsPriceList.get(0).getMemberPrice():goodsPriceList.get(0).getPrtcPrice());
			model.put("maxPrtcPrice", isMember?goodsPriceList.get(goodsPriceList.size()-1).getMemberPrice():goodsPriceList.get(goodsPriceList.size()-1).getPrtcPrice());
		}
		
    	//详细页面特定显示的tab
    	model.put("tabId", request.getParameter("tabId"));
    	
    	model.put("attentionNumber", attentionPriceService.getAttentionNumberByGoodsId(objId));
    	
    	String viewFrom = request.getParameter("viewFrom");
    	model.put("viewFrom", viewFrom);
    	
    	//当前用户角色
    	if(AuthenticationHelper.getCurrentUser()!=null) {
	    	if(roleManagerImpl.isHasThisRole(AuthenticationHelper.getCurrentUser().getObjId(), "3")){
	    		model.put("roleType","3");
	    	}else{
	    		model.put("roleType","4");
	    	}
    	}

    	return new ModelAndView(viewName, model);
	}
	
	/** 
	 * Description :  获得商品的详细信息
	 * Create Date: 2010-8-4下午04:28:52 by liangxj  Modified Date: 2010-8-4下午04:28:52 by liangxj
	 * @param   ids以逗号分割
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=getCompareGoodsInfo")
	public ModelAndView getCompareGoodsInfo(String objIds) throws Exception {
		List<Goods> goodsList = goodsService.getGoodsAllInfoList(objIds);
		
		Map<String,Object> model = new HashMap<String,Object>();
		List<List<String>> result = new ArrayList<List<String>>();
		int colspan = goodsList.size() + 1;  //跨行合并列数
		DecimalFormat myformat = new  DecimalFormat("#####0.00");  
		
		//将商品的基本属性放入集合
		List<String> title = new ArrayList<String>();title.add("1");title.add("&nbsp;");;  //标题
		List<String> pic =  new ArrayList<String>();pic.add("1");pic.add("&nbsp;");  //图片
		List<String> properties =  new ArrayList<String>();properties.add(colspan+"");properties.add("基本属性");  //基本属性
		List<String> price = new ArrayList<String>();price.add("1");price.add("参考价");  //参考价
		List<String> brand = new ArrayList<String>();brand.add("1");brand.add("品牌");//品牌
		List<String> issue = new ArrayList<String>();issue.add("1");issue.add("发布时间");//计量单位
		List<String> unit = new ArrayList<String>();unit.add("1");unit.add("计量单位");//发布时间
		List<String> madeIn = new ArrayList<String>();madeIn.add("1");madeIn.add("产地");//产地
		List<String> factory = new ArrayList<String>();factory.add("1");factory.add("制造商");//制造商
		List<String> sell = new ArrayList<String>();sell.add("1");sell.add("是否可单独出售/是否特供产品");//是否可单独出售/是否特供
		List<String> energy = new ArrayList<String>();energy.add("1");energy.add("节能产品编号");//节能产品编号
		List<String> creation = new ArrayList<String>();creation.add("1");creation.add("自主创新认定产品编号");//自主创新认定编号
		List<String> environment = new ArrayList<String>();environment.add("1");environment.add("环境标志产品编号");//环境标志编号
		List<String> tech = new ArrayList<String>();tech.add("1");tech.add("含有密码技术产品编号");//含有密码技术产品编号
		
		List<String> removeId = new ArrayList<String>();removeId.add("1");removeId.add("移除全部"); //用来做移除比较
		
		List<ListOrderedMap> colData = new ArrayList<ListOrderedMap>();
		for (Goods goods : goodsList) {
			removeId.add(goods.getObjId());
			title.add(goods.getProductName() + "&nbsp;(" + goods.getProductCode() + ")");
			pic.add(goods.getPicture());
			price.add("￥ " + myformat.format(goods.getReferPrice()) + " 元");
			brand.add(goods.getGoodsBrand().getBrandName());
			issue.add(goods.getProductDateIssued()==null?"":DateUtils.format(goods.getProductDateIssued(),"yyyy年MM月dd日"));
			unit.add(goods.getMeasureUnit());
			madeIn.add(goods.getMadeIn());
			factory.add(goods.getFactory());
			sell.add(goods.getSoleToSellCN() + "/" + goods.getSpecialCN());
			creation.add(goods.getCreationCode());
			tech.add(goods.getCryptographyTechCode());
			environment.add(goods.getEnvironmentLabel());
			energy.add(goods.getEnergySavingProductNo());
			
			//存放交叉的列头
			ListOrderedMap goodsId = new ListOrderedMap();
			goodsId.put("0", goods.getObjId());
	        colData.add(goodsId);
		}
		result.add(title);
		result.add(pic);
		result.add(properties);
		result.add(price);
		result.add(brand);
		result.add(issue);
		result.add(unit);
		result.add(madeIn);
		result.add(factory);
		result.add(sell);
		result.add(creation);
		result.add(tech);
		result.add(environment);
		result.add(energy);
		
		//将商品的参数放入集合
		String classId = goodsList.get(0).getGoodsClass().getObjId();
		List<ListOrderedMap> resourceData = new ArrayList<ListOrderedMap>();
		List<GoodsClassParamType> goodsClassParamList = goodsClassParamTypeService.getGoodsClassParamByClass(classId);
		for (GoodsClassParamType goodsClassParam : goodsClassParamList) {
			for (Goods goods : goodsList) {
				Set<GoodsParam> params = goods.getGoodsParamSet();
				ListOrderedMap col = new ListOrderedMap();
				
				if(!goodsClassParam.getIsLeaf()) {
					col.put("0",colspan);
				}else {
					col.put("0", 1);
				}
				col.put("1", goodsClassParam.getParamName());  //参数名称
				col.put("2", goods.getObjId());  //商品id
				col.put("3", "");  //参数值
				
				for (GoodsParam goodsParam : params) {
					if(goodsParam.getGoodsClassParam().getObjId().equals(goodsClassParam.getObjId())) {
						col.remove("3");
						col.put("3", goodsParam.getParamValue());  //参数值
					}
				}
				resourceData.add(col);
			}
		}
		
		List<ListOrderedMap> paramList = CollectionUtils.crossList(resourceData,colData,2);
		for (ListOrderedMap listOrderedMap : paramList) {
			result.add(listOrderedMap.valueList());
		}
		
		result.add(removeId);
		
    	model.put("compareGoodsList", result);
		return new ModelAndView("goodsCompareView", model);
	}
	
	/** 
	 * Description :  跳转到商品库首页
	 * Create Date: 2011-2-18上午10:02:44 by likg  Modified Date: 2011-2-18上午10:02:44 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toShowGoodsIndexView")
	public ModelAndView toShowGoodsIndexView(HttpServletRequest request, HttpSession session) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> paramsMap = new HashMap<String, Object>(); //查询条件
		
		//获取商品分类，组装数据
    	QueryObject<GoodsClass> query = new QueryObjectBase<GoodsClass>();
    	query.setEntityClass(GoodsClass.class);
    	query.getQueryProjections().setOrderProperty("objId");
		query.getQueryProjections().setDescFlag(false);
    	List<GoodsClass> allList = goodsClassService.findByQuery(query);
    	List<GoodsClass> goodsClassList = new ArrayList<GoodsClass>();
		String currentId = null;
		GoodsClass currentGoodsClass = null;
		for(GoodsClass gc : allList) {
			//第四级
			if(gc.getObjId().length() == 7) {
				continue ;
			}
			//第一级
			else if(gc.getObjId().length() == 1) {
				goodsClassList.add(gc);
			}
			//是上一个的孩子
			else if(gc.getObjId().startsWith(currentId)) {
				gc.setParentClazz(currentGoodsClass);
				currentGoodsClass.getChildren().add(gc);
			}
			//是上一个的兄弟
			else if(gc.getObjId().length() == currentId.length()) {
				gc.setParentClazz(currentGoodsClass.getParentClazz());
				currentGoodsClass.getParentClazz().getChildren().add(gc);
			}
			//是上一个的叔叔
			else if(currentId.length() - gc.getObjId().length() == 2) {
				gc.setParentClazz(currentGoodsClass.getParentClazz().getParentClazz());
				currentGoodsClass.getParentClazz().getParentClazz().getChildren().add(gc);
			}
			currentId = gc.getObjId();
			currentGoodsClass = gc;
		}
		model.put("goodsClassList", goodsClassList);
		
		//获取推荐商品信息
    	Page<RecommendGoods> page1 = new Page<RecommendGoods>(1L, 32L, 32, new ArrayList<RecommendGoods>());
    	Page<RecommendGoods> pageData1 = recommendGoodsService.getRecommendGoods(page1, paramsMap);
    	model.put("recommendGoodsList", pageData1.getData());
    	
    	//获取推荐品牌
		Page<RecommendGoodsBrand> pageBrand = new Page<RecommendGoodsBrand>(1, 10, 10, new ArrayList<RecommendGoodsBrand>());
		paramsMap.clear();
		model.put("recommendBrandList", recommendGoodsBrandService.getRecommendGoodsBrand(pageBrand, paramsMap).getData());
		
    	//获得用户角色
		User user=AuthenticationHelper.getCurrentUser();
		if(user != null) {
			model.put("roleStr",session.getAttribute("roleStr"));
		}
		
		return new ModelAndView("showGoodsIndexView", model);
	}
	
	/** 
	 * Description :  跳转到按商品类别展示商品的视图
	 * Create Date: 2011-2-18下午05:10:19 by likg  Modified Date: 2011-2-18下午05:10:19 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toGoodsListByClassView")
	public ModelAndView toGoodsListByClassView(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		
		//通过商品分类热门标签获取商品分类
		param.put("tagsType", OrganizationEnum.GOODS_CLASS_TAGS);
		param.put("maxResults", 2);
		List<HotTags> hotTagList = hotTagsServiceService.getHotTagsList(param);
		model.put("hotTagList", hotTagList);
		
		//获取商品类别下的商品集合
		List<List<RecommendGoods>> goodsListList = new ArrayList<List<RecommendGoods>>();
		Page<RecommendGoods> page1 = new Page<RecommendGoods>(1L, 5L, 5, new ArrayList<RecommendGoods>());
		for(HotTags tag : hotTagList) {
			param.clear();
			param.put("goodsClassCode", tag.getTagsId());
			goodsListList.add(recommendGoodsService.getRecommendGoods(page1, param).getData());
		}
		model.put("goodsListList", goodsListList);
		
		return new ModelAndView("goodsListByClassView", model);
	}
}
