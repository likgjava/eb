package com.gpcsoft.serve.hotel.controller;

import java.math.BigDecimal;
import java.util.Date;
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
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.serve.hotel.domain.GuestRoomPrice;
import com.gpcsoft.serve.hotel.service.GuestRoomPriceService;

/**
  * @gpcsoft.view value="guestRoomPriceDetailView"
  *  url="view/serve/hotel/guest_room_price_detail.jsp"
  *  
  * @gpcsoft.view value="guestRoomPriceModifyView"
  *  url="view/serve/hotel/guest_room_price_modify.jsp"
  *  
  * @gpcsoft.view value="guestRoomPriceManageListView"
  *  url="view/serve/hotel/guest_room_price_manage_list.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={GuestRoomPrice.class})
@RequestMapping("/GuestRoomPriceController.do")//页面请求路径,可修改
public class GuestRoomPriceController extends AnnotationMultiController<GuestRoomPrice> {

	@Autowired(required=true) @Qualifier("guestRoomPriceServiceImpl")
	private GuestRoomPriceService guestRoomPriceService;
	
	/** 
	 * Description :  跳转到客房价格管理列表页面
	 * Create Date: 2010-12-8上午11:05:58 by likg  Modified Date: 2010-12-8上午11:05:58 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toGuestRoomPriceManageListView")
	public ModelAndView toGuestRoomPriceManageListView() throws Exception {
		Map<String, Object> model = new HashMap<String, Object>(); 
		
//		GuestRoom guestRoom = guestRoomService.get(guestRoomId);
//		model.put("guestRoom", guestRoom);
		OrgInfo orgInfo = (OrgInfo)AuthenticationHelper.getCurrentUser(true).getOrgInfo();
		String orgInfoId = orgInfo.getObjId();
		model.put("orgInfoId", orgInfoId);
		
		return new ModelAndView("guestRoomPriceManageListView", model);
	}

	/** 
	 * Description :  保存客房价格信息
	 * Create Date: 2010-12-7下午04:03:28 by likg  Modified Date: 2010-12-7下午04:03:28 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveGuestRoomPrice")
	public ModelAndView saveGuestRoomPrice(GuestRoomPrice guestRoomPrice, HttpServletRequest request, SessionStatus status) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>(); 
		
		guestRoomPrice.setCreateTime(new Date());
		guestRoomPrice.setCreateUser(AuthenticationHelper.getCurrentUser(true));
		guestRoomPriceService.save(guestRoomPrice);
		
		status.setComplete();
		
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  跳转到客房价格详细信息页面
	 * Create Date: 2010-12-7下午04:30:25 by likg  Modified Date: 2010-12-7下午04:30:25 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toGuestRoomPriceDetailView")
	public ModelAndView toGuestRoomPriceDetailView(String guestRoomPriceId) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>(); 
		
		GuestRoomPrice guestRoomPrice = guestRoomPriceService.get(guestRoomPriceId);
		model.put("guestRoomPrice", guestRoomPrice);
		
		return new ModelAndView("guestRoomPriceDetailView", model);
	}
	
	/** 
	 * Description :  跳转到客房价格信息的修改页面
	 * Create Date: 2010-12-7下午04:33:50 by likg  Modified Date: 2010-12-7下午04:33:50 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toGuestRoomPriceModifyView")
	public ModelAndView toGuestRoomPriceModifyView(String guestRoomPriceId) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>(); 
		
		GuestRoomPrice guestRoomPrice = guestRoomPriceService.get(guestRoomPriceId);
		model.put("guestRoomPrice", guestRoomPrice);
		
		return new ModelAndView("guestRoomPriceModifyView", model);
	}
	
	/** 
	 * Description :  获取客房当天的协议价
	 * Create Date: 2010-12-13下午03:50:47 by likg  Modified Date: 2010-12-13下午03:50:47 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getTodayAgreePrice")
	public ModelAndView getTodayAgreePrice(String guestRoomId) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>(); 
		
		BigDecimal todayAgreePrice = guestRoomPriceService.getTodayAgreePrice(guestRoomId);
		model.put("todayAgreePrice", todayAgreePrice);
		
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
}
