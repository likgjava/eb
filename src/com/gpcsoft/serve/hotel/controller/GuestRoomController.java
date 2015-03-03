package com.gpcsoft.serve.hotel.controller;

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
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.core.Constants;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.serve.hotel.domain.GuestRoom;
import com.gpcsoft.serve.hotel.domain.Hotel;
import com.gpcsoft.serve.hotel.service.GuestRoomService;
import com.gpcsoft.serve.hotel.service.HotelService;
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.service.AttachmentService;
import com.gpcsoft.srplatform.common.utils.AttachmentUtil;

/**
 * @gpcsoft.view value="guestRoomListView"
 *  url="view/serve/hotel/guest_room_manage_list.jsp"
 *  
 * @gpcsoft.view value="guestRoomListAllView"
 *  url="view/serve/hotel/guest_room_manage_list_all.jsp"
 * 
 * @gpcsoft.view value="guestRoomDetailView"
 *  url="view/serve/hotel/guest_room_detail.jsp"
 *  
 * @gpcsoft.view value="guestRoomModifyView"
 *  url="view/serve/hotel/guest_room_modify.jsp"
 *  
 * @gpcsoft.view value="guestRoomPriceManageListView"
 *  url="view/serve/hotel/guest_room_price_manage_list.jsp"
 */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={GuestRoom.class})
@RequestMapping("/GuestRoomController.do")//页面请求路径,可修改
public class GuestRoomController extends AnnotationMultiController<GuestRoom> {

	@Autowired(required=true) @Qualifier("guestRoomServiceImpl")
	private GuestRoomService guestRoomService;
	
	@Autowired(required=true) @Qualifier("attachmentServiceImpl")
	private AttachmentService attachmentService;
	
	@Autowired(required=true) @Qualifier("hotelServiceImpl")
	private HotelService hotelService;
	
	/** 
	 * Description :  跳转到客房列表页面 
	 * Create Date: 2010-12-9下午04:43:00 by likg  Modified Date: 2010-12-9下午04:43:00 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toGuestRoomListView")   
	public ModelAndView toGuestRoomListView(HttpServletRequest request, String objId) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		String viewName = "guestRoomListView";
		
		String listType = request.getParameter("listType");
		if(listType!=null && "listAll".equals(listType)){
			viewName = "guestRoomListAllView";
			model.put("orgInfoId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId());
		}else{
			Hotel hotel = hotelService.get(objId);
			model.put("hotel", hotel);
		}

		return new ModelAndView(viewName, model);
    }
	
	/** 
	 * Description :  保存客房信息
	 * Create Date: 2010-12-7上午11:01:19 by likg  Modified Date: 2010-12-7上午11:01:19 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveGuestRoom")
	public ModelAndView saveGuestRoom(GuestRoom guestRoom, HttpServletRequest request,SessionStatus status) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>(); 
		
		//处理客房图片
		CommonsMultipartFile file = null;
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		file = (CommonsMultipartFile)multipartRequest.getFile("pictureFile");
		if(file!=null && file.getSize()!=0)
		{
			Object o = AttachmentUtil.uploadFile(request, "pictureFile");
			if(o instanceof GpcBaseObject)
			{
				Attachment attachment = (Attachment) o;
				attachmentService.saveAttachment(attachment);
				guestRoom.setPicture(attachment.getObjId());
			}
		}
		
		guestRoom.setCreateTime(new Date());
		guestRoom.setCreateUser(AuthenticationHelper.getCurrentUser(true));
		guestRoomService.save(guestRoom);
		
		status.setComplete();
		
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  跳转到客房详细信息页面
	 * Create Date: 2010-12-7下午01:53:12 by likg  Modified Date: 2010-12-7下午01:53:12 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toGuestRoomDetailView")
	public ModelAndView toGuestRoomDetailView(String guestRoomId) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>(); 
		
		GuestRoom guestRoom = guestRoomService.get(guestRoomId);
		model.put("guestRoom", guestRoom);
		
		return new ModelAndView("guestRoomDetailView", model);
	}
	
	/** 
	 * Description :  跳转到客房信息的修改页面
	 * Create Date: 2010-12-7下午02:06:28 by likg  Modified Date: 2010-12-7下午02:06:28 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toGuestRoomModifyView")
	public ModelAndView toGuestRoomModifyView(String guestRoomId) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>(); 
		
		GuestRoom guestRoom = guestRoomService.get(guestRoomId);
		model.put("guestRoom", guestRoom);
		
		return new ModelAndView("guestRoomModifyView", model);
	}
	
	/** 
	 * Description :  跳转到客房价格管理列表页面
	 * Create Date: 2010-12-8上午11:05:58 by likg  Modified Date: 2010-12-8上午11:05:58 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toGuestRoomPriceManageListView")
	public ModelAndView toGuestRoomPriceManageListView(String guestRoomId) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>(); 
		
		GuestRoom guestRoom = guestRoomService.get(guestRoomId);
		model.put("guestRoom", guestRoom);
		
		return new ModelAndView("guestRoomPriceManageListView", model);
	}
	
}
