package com.gpcsoft.serve.hotel.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.bizplatform.base.common.util.StringUtils;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.serve.hotel.domain.Hotel;
import com.gpcsoft.serve.hotel.domain.RecommendHotel;
import com.gpcsoft.serve.hotel.enumeration.HotelEnum;
import com.gpcsoft.serve.hotel.service.HotelService;
import com.gpcsoft.serve.hotel.service.RecommendHotelService;
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.service.AttachmentService;

/**
  * @gpcsoft.view value="hotelShowListView"
  *  url="view/serve/hotel/showhotel/show_hotel_list.jsp"
  * @gpcsoft.view value="hotelShowForListView"
  *  url="view/serve/hotel/showhotel/hotel_list_div_l.jsp"
  * @gpcsoft.view value="hotelShowInfoView"
  *  url="view/serve/hotel/showhotel/show_hotel_detail.jsp"
  *  
  * @gpcsoft.view value="hotelRecommendListView"
  *  url="view/serve/hotel/showhotel/hotel_recommend_list.jsp"
  * @gpcsoft.view value="recommendHotelIndexView"
  *  url="view/srplatform/portal/include/recommend_hotel_list.jsp"
  *  
  */
@Controller//标识为控制器
@Scope("request")
@RequestMapping("/HotelShowController.do")//页面请求路径,可修改
public class HotelShowController extends AnnotationMultiController<GpcBaseObject> {

	@Autowired(required=true) @Qualifier("hotelServiceImpl")
	private HotelService hotelService;

	@Autowired(required=true) @Qualifier("recommendHotelServiceImpl")
	private RecommendHotelService recommendHotelService;
	
	@Autowired(required=true) @Qualifier("attachmentServiceImpl")
	private AttachmentService attachmentService;

	/** 
     * Description :  跳转到酒店展示的二级页面，也就是列表页面
     * Create Date: 2010-7-27上午10:02:32 by liangxj  Modified Date: 2010-7-27上午10:02:32 by liangxj
     * @param   categoryId 品目id categoryCode 品目编号
     * @return  
     * @Exception   
     */
    @SuppressWarnings("unchecked")
	@RequestMapping(params = "method=toHotelList")
    public ModelAndView toHotelList(HttpServletRequest request) throws Exception {
    	Map<String, Object> model = new HashMap<String, Object>();
    	Map<String, Object> paramsMap = new HashMap<String, Object>();//paramsMap这个是查询条件
    	
    	/** 如果从详情页面跳转过来，需要过滤具体的星级和地区；keyWord,districtLevel来自与首页的参数 */
    	String keyWord = StringUtils.ascii2Native(request.getParameter("keyWord"));
    	String districtLevel = request.getParameter("districtLevel");
    	String starLevel = request.getParameter("starLevel");
    	String districtId = request.getParameter("districtId");
    	
    	paramsMap.put("districtLevel", districtLevel);
    	paramsMap.put("keyWord",StringUtils.replaceIngoreStr(keyWord));
    	paramsMap.put("starLevel", starLevel);
    	paramsMap.put("districtId", districtId);
    	
    	/** 取酒店信息*/
    	Page<Hotel> page = prePage(request);//预分页,算出当前页和大小等	
    	Page<Hotel> pageData = hotelService.getHotelListForShow(page,paramsMap);
    	endPage(model, pageData, request);
    	model.put("PAGERESULT",pageData);
    	
    	/** 取酒店星级信息*/
    	List<String[]> starLevelList = hotelService.getStarLevelListShow();
    	
    	for (Object obj : starLevelList) {
    		Object[] aType = (Object[])obj;
			for (String type : enumService.getEnum(HotelEnum.STAR)) {
				String[] temp = type.split(":");
				if(((String)aType[0]).equals(temp[0])) {
					aType[1] = temp[1];
				}
			}
		}
    	model.put("starLevelList", starLevelList);
    	
    	/** 取区域信息*/
    	Short level = 0;
    	if(districtLevel != null && !districtLevel.equals("")) {
    		level = new Short(districtLevel);
    	}
    	
    	List<String[]> listDistrict =  hotelService.getDistrictListShowByStarLevel(null,starLevel,level);
    	model.put("districtList", listDistrict);
    	
    	/** 回设高亮参数或首页参数 */
    	model.put("districtLevel", districtLevel);
    	model.put("starLevel", starLevel);
    	model.put("districtId", districtId);
    	model.put("keyWord",StringUtils.returnIngoreStr(keyWord));
    	
        return new ModelAndView("hotelShowListView",model);
    }
    
    
	/** 
	 * Description :  获取酒店展示的列表数据或大图显示数据
	 * Create Date: 2010-7-27上午11:46:10 by liangxj  Modified Date: 2010-7-27上午11:46:10 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=getHotelForListAndPic")   
	public ModelAndView getHotelForListAndPic(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> paramsMap = new HashMap<String, Object>();//paramsMap这个是查询条件
		
		//酒店星级、区域
    	paramsMap.put("starLevel", request.getParameter("starLevel"));
    	paramsMap.put("districtId", request.getParameter("districtId"));
    	
    	//高级搜索条件
    	paramsMap.put("startDate", request.getParameter("startDate"));//成立时间
    	paramsMap.put("endDate", request.getParameter("endDate"));
    	
    	paramsMap.put("guestRoomType", request.getParameter("guestRoomType"));  //客房类型
    	paramsMap.put("funFacilities", request.getParameter("funFacilities"));  //娱乐设施
    	paramsMap.put("serviceItems", request.getParameter("serviceItems"));//服务项目
    	paramsMap.put("creditCardType", request.getParameter("creditCardType"));//接受信用卡类型
    	paramsMap.put("meetingRoomType", request.getParameter("meetingRoomType"));//会议室信息
    	
    	String keyWord = request.getParameter("keyWord");//关键字
    	paramsMap.put("keyWord", StringUtils.replaceIngoreStr(StringUtils.ascii2Native(keyWord)));
    	
    	//排序
    	paramsMap.put("evalSort", request.getParameter("evalSort"));
    	paramsMap.put("starSort", request.getParameter("starSort"));
    	
    	/** 取酒店信息*/
    	Page<Hotel> page = prePage(request);//预分页,算出当前页和大小等	
    	Page<Hotel> pageData = hotelService.getHotelListForShow(page,paramsMap);
    	endPage(model, pageData, request);
    	model.put("PAGERESULT",pageData);
    	
		return new ModelAndView("hotelShowForListView",model);
    }
	
	/** 
	 * Description :  根据酒店星级获得区域的展示信息集合
	 * Create Date: 2010-8-3上午11:44:58 by sunl  Modified Date: 2010-8-3上午11:44:58 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getDistrictListShowByStar")
	public ModelAndView getDistrictListShowByStar(String starLevel,String districtId,String districtLevel) throws Exception {
		Short level = 0;
    	if(districtLevel != null && !districtLevel.equals("")) {
    		level = new Short(districtLevel);
    	}
    	
    	List<String[]> district = hotelService.getDistrictListShowByStarLevel(districtId,starLevel,level);
		
    	Map<String,Object> model = new HashMap<String,Object>();
    	model.put("result", district);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  获取推荐酒店列表
	 * Create Date: 2010-12-10下午12:56:29 by likg  Modified Date: 2010-12-10下午12:56:29 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params="method=getRecommendHotel")
	public ModelAndView getRecommendHotel(HttpServletRequest request, String viewName) throws Exception
	{
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		
		//酒店星级
    	paramsMap.put("star", request.getParameter("star")==null ? "" : request.getParameter("star"));
		
		Page<RecommendHotel> page = prePage(request);
		Page<RecommendHotel> pageData = recommendHotelService.getRecommendHotel(page, paramsMap);
		
		model.put("recommendHotelList", pageData.getData());
		
		String viewStr = "hotelRecommendListView";
    	if(null != viewName) {
    		viewStr = viewName;
    	}
    	
		return new ModelAndView(viewStr, model);
	}
	
	/** 
	 * Description :  获得酒店的详细信息，包含客房、会议室、评价
	 * Create Date: 2010-8-4下午04:28:52 by liangxj  Modified Date: 2010-8-4下午04:28:52 by liangxj
	 * @param
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getHotelInfo")
	public ModelAndView getHotelInfo(String objId ,HttpServletRequest request) throws Exception {
		Map<String,Object> model = new HashMap<String,Object>();
		
		//查询酒店
		Hotel hotel = hotelService.getHotelAllInfo(objId);
		model.put("hotel", hotel);

		//获得酒店的附图
		if(hotel.getAdditionPicture() != null){
			List<Attachment> images = attachmentService.getAttachmentsByRealID(hotel.getAdditionPicture());
			model.put("images", images);
		}
		
		//导航参数
		model.put("districtId", request.getParameter("districtId"));
		
		if(request.getParameter("districtName")!=null&&!"".equals(request.getParameter("districtName"))){
			model.put("districtName", StringUtils.ascii2Native(request.getParameter("districtName")));
		}
		
		//详细页面特定显示的tab
    	model.put("tabId", request.getParameter("tabId"));
		
		return new ModelAndView("hotelShowInfoView", model);
	}
}
