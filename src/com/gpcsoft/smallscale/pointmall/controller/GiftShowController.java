package com.gpcsoft.smallscale.pointmall.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.bizplatform.base.common.util.StringUtils;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.goods.goods.service.GoodsService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.smallscale.point.enumeration.SmallscaleEnum;
import com.gpcsoft.smallscale.pointmall.domain.Gift;
import com.gpcsoft.smallscale.pointmall.domain.GiftSeries;
import com.gpcsoft.smallscale.pointmall.service.GiftSeriesService;
import com.gpcsoft.smallscale.pointmall.service.GiftService;
import com.gpcsoft.smallscale.pointmall.service.RealGiftRecordService;
import com.gpcsoft.smallscale.pointmall.service.VirtualGiftRecordService;
import com.gpcsoft.srplatform.auth.domain.User;

/**
  *
  * @gpcsoft.view value="giftShowIndexView"
  *  url="view/smallscale/pointmall/showgift/show_gift_index.jsp"
  *  
  * @gpcsoft.view value="giftShowListView"
  *  url="view/smallscale/pointmall/showgift/show_gift_list.jsp"
  *  
  * @gpcsoft.view value="giftShowLeftSeriesView"
  *  url="view/smallscale/pointmall/showgift/gift_series_list.jsp"
  *  
  * @gpcsoft.view value="giftShowInfoView"
  *  url="view/smallscale/pointmall/showgift/show_gift_detail.jsp"
  *  
  * @gpcsoft.view value="giftRecommendView"
  *  url="view/smallscale/pointmall/showgift/gift_recommend_list.jsp"
  *  
  * @gpcsoft.view value="loadSeriesAndReallyGiftView"
  *  url="view/smallscale/pointmall/showgift/show_series_gift_load.jsp"
  *  
  * @gpcsoft.view value="giftShowForListView"
  *  url="view/smallscale/pointmall/showgift/gift_list_div_l.jsp"
  *  
  * @gpcsoft.view value="giftShowForPicView"
  *  url="view/smallscale/pointmall/showgift/gift_list_div_p.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@RequestMapping("/GiftShowController.do")//页面请求路径,可修改
public class GiftShowController extends AnnotationMultiController<GpcBaseObject> {

	@Autowired(required=true) @Qualifier("giftServiceImpl")
	private GiftService giftService;
	
	@Autowired(required=true) @Qualifier("goodsServiceImpl")
	private GoodsService goodsService;
	
	@Autowired(required=true) @Qualifier("giftSeriesServiceImpl")
	private GiftSeriesService giftSeriesService;
	
	@Autowired(required=true) @Qualifier("realGiftRecordServiceImpl")
	private RealGiftRecordService realGiftRecordService;
	
	@Autowired(required=true) @Qualifier("virtualGiftRecordServiceImpl")
	private VirtualGiftRecordService virtualGiftRecordService;
	
    /** 
     * Description :  跳转到礼物展示首页
     * Create Date: 2011-1-9下午08:28:52 by yucy  Modified Date: 2011-1-9下午08:28:52 by yucy
     * @param   
     * @return  
     * @Exception   
     */
	@RequestMapping(params = "method=toGiftIndex")
    public ModelAndView toGiftIndex(HttpServletRequest request, HttpSession session) throws Exception {
    	Map<String, Object> model = new HashMap<String, Object>();
    	Map<String, Object> paramsMap = new HashMap<String, Object>();//paramsMap这个是查询条件
    	
    	String keyWord = StringUtils.ascii2Native(request.getParameter("keyWords"));

    	if(com.gpcsoft.core.utils.StringUtils.hasLength(keyWord)){
    		toGiftList(request);//跳转到列表
    	}
    	
    	//查询虚拟的礼品
    	paramsMap.put("resultNumber",5);
    	paramsMap.put("giftType", SmallscaleEnum.VIRTUAL_OBJECTS);
    	List<Gift> virtualGiftList = giftService.getGiftList(paramsMap);
    	model.put("virtualGiftList",virtualGiftList);
    	
    	//查询实际礼品和系列
    	paramsMap.remove("giftType");
    	paramsMap.put("giftType", SmallscaleEnum.REALLY_OBJECTS);
    	List<GiftSeries> giftSeriesFirstList =  giftSeriesService.getSeriesList(paramsMap);//一级系列
    	model.put("giftSeriesFirstList",giftSeriesFirstList);
    	paramsMap.put("giftSeries", giftSeriesFirstList.size()>0?giftSeriesFirstList.get(0):null);
    	paramsMap.remove("resultNumber");
    	List<Map<String,Object>> seriesAndReallyGift =  giftService.getSeriesAndReallyGift(paramsMap);//二级系列和礼品
    	model.put("seriesAndReallyGift",seriesAndReallyGift);
    	
    	//获得用户角色
		User user=AuthenticationHelper.getCurrentUser();
		if(user != null) {
			model.put("roleStr",session.getAttribute("roleStr"));
		}
    	
        return new ModelAndView("giftShowIndexView",model);
    }
	
    /** 
     * Description :  跳转到礼物展示的二级页面，也就是列表页面
     * Create Date: 2011-1-9下午08:28:52 by yucy  Modified Date: 2011-1-9下午08:28:52 by yucy
     * @param   
     * @return  
     * @Exception   
     */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=toGiftList")
    public ModelAndView toGiftList(HttpServletRequest request) throws Exception {
    	Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> paramsMap = new HashMap<String, Object>();//paramsMap这个是查询条件
    	
    	String seriesId = StringUtils.ascii2Native(request.getParameter("seriesId"));
    	String keyWord = StringUtils.ascii2Native(request.getParameter("keyWord"));
    	String giftType = StringUtils.ascii2Native(request.getParameter("giftType"));

    	
    	/** 判断如果没有分类编号或者关键字，则跳转至空页面，加载商品分类列表*/
    	if((seriesId != null && !"".equals(seriesId)) || (keyWord != null && !"".equals(keyWord))||(giftType != null && !"".equals(giftType))) {
	    	paramsMap.put("seriesId", seriesId);
	    	paramsMap.put("keyWord",StringUtils.replaceIngoreStr(keyWord));
	    	paramsMap.put("giftType",giftType);
	    	
	    	/** 取商品信息*/
	    	Page<Gift> page = prePage(request);//预分页,算出当前页和大小等	
	    	Page<Gift> pageData = giftService.getGiftListForShow(page,paramsMap);
	    	endPage(model, pageData, request);
	    	model.put("PAGERESULT",pageData);
	    	
	    	/** 回设高亮参数或首页参数 */
	    	model.put("seriesId", seriesId);
	    	model.put("keyWord",StringUtils.returnIngoreStr(keyWord));
    	}
        return new ModelAndView("giftShowListView",model);
    }
	

	/** 
	 * Description : 获取礼品展示的列表数据或大图显示数据 
	 * Create Date: 2011-1-11下午05:26:57 by yucy  Modified Date: 2011-1-11下午05:26:57 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=getGiftForListAndPic")   
	public ModelAndView getGiftForListAndPic(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> paramsMap = new HashMap<String, Object>();//paramsMap这个是查询条件
		
    	String seriesId = StringUtils.ascii2Native(request.getParameter("seriesId"));
    	String keyWord = StringUtils.ascii2Native(request.getParameter("keyWord"));
    	String giftType = StringUtils.ascii2Native(request.getParameter("giftType"));
    	
    	//排序
    	String sort = request.getParameter("order");
    	if(sort != null) {
    		paramsMap.put("order", sort.replace("_", " "));
    	}
    	
    	String style = request.getParameter("style");  //显示样式
    	
    	paramsMap.put("seriesId", seriesId);
    	paramsMap.put("keyWord",StringUtils.replaceIngoreStr(keyWord));
    	paramsMap.put("giftType",StringUtils.replaceIngoreStr(giftType));

    	/** 取礼品信息*/
    	Page<Gift> page = prePage(request);//预分页,算出当前页和大小等	
    	Page<Gift> pageData = giftService.getGiftListForShow(page,paramsMap);
    	endPage(model, pageData, request);
    	model.put("PAGERESULT",pageData);

    	//如果样式是style，则大图显示商品
    	String view = "giftShowForListView";
    	if("pic".equals(style))
    		view = "giftShowForPicView";
    	
		return new ModelAndView(view,model);
    }
	
    /** 
     * Description :  切换根分类
     * Create Date: 2011-1-10下午10:14:15 by yucy  Modified Date: 2011-1-10下午10:14:15 by yucy
     * @param   
     * @return  
     * @Exception   
     */
	@RequestMapping(params = "method=toChangeRootSeries")
    public ModelAndView toChangeRootSeries(HttpServletRequest request) throws Exception {
    	Map<String, Object> model = new HashMap<String, Object>();
    	Map<String, Object> paramsMap = new HashMap<String, Object>();//paramsMap这个是查询条件
    	
    	paramsMap.put("giftSeries", giftSeriesService.get(request.getParameter("giftSeriesId")));
    	paramsMap.put("giftType", SmallscaleEnum.REALLY_OBJECTS);
    	
    	List<Map<String,Object>> seriesAndReallyGift =  giftService.getSeriesAndReallyGift(paramsMap);//二级系列和礼品
    	
    	model.put("seriesAndReallyGift",seriesAndReallyGift);

        return new ModelAndView("loadSeriesAndReallyGiftView",model);
    }
	
	/** 
	 * Description :   获得礼品的详情
	 * Create Date: 2011-1-10下午02:38:46 by yucy  Modified Date: 2011-1-10下午02:38:46 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getGiftInfo")
	public ModelAndView getGiftInfo(String objId,String viewName,Boolean isShowPic,HttpServletRequest request) throws Exception {
		Map<String,Object> model = new HashMap<String,Object>();
		
		Map<String,Object> paramsMap = new HashMap<String,Object>();
		paramsMap.put("objId", objId);
		
		//查询礼品
		Gift gift = giftService.getGiftAllInfo(paramsMap);
		
		if(gift.getGoods()!=null){//如果礼品关联商品
			gift.setGoods(goodsService.getGoodsAllInfo(gift.getGoods().getObjId()));
		}
		
		model.put("gift", gift);
		
		model.put("realGiftRecordList",realGiftRecordService.getRecordListByGiftId(objId));
		
		model.put("virtualGiftRecordList",virtualGiftRecordService.getVirtualListByGiftId(objId));

    	//详细页面特定显示的tab
    	model.put("tabId", request.getParameter("tabId"));
    	
		return new ModelAndView("giftShowInfoView", model);
	}
	

	
	/** 
	 * Description :  跳转到礼品的系列列表
	 * Create Date: 2011-1-11上午09:24:46 by yucy  Modified Date: 2011-1-11上午09:24:46 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getGiftSeriesList")
	public ModelAndView getGiftSeriesList(HttpServletRequest request)throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
    	Map<String, Object> paramsMap = new HashMap<String, Object>();
    	paramsMap.put("giftType", SmallscaleEnum.REALLY_OBJECTS);
    	List<GiftSeries> giftSeriesFirstList =  giftSeriesService.getSeriesList(paramsMap);//一级系列
    	model.put("giftSeriesFirstList", giftSeriesFirstList);
		return new ModelAndView("giftShowLeftSeriesView", model);
	}
	
	/** 
	 * Description : 获得推荐礼品
	 * Create Date: 2010-8-20上午11:58:07 by liangxj  Modified Date: 2010-11-5上午11:58:07 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getRecommendGift")
	public ModelAndView getRecommendGift(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> paramsMap = new HashMap<String, Object>();//paramsMap这个是查询条件
		paramsMap.put("isRecommended", true);//是推荐商品
		paramsMap.put("resultNumber", 8);//推荐取8条
		List<Gift> recommendList = giftService.getGiftList(paramsMap);
		model.put("recommendList", recommendList);
		return new ModelAndView("giftRecommendView", model);
	} 
}