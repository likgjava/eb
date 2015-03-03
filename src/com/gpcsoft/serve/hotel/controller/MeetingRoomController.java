package com.gpcsoft.serve.hotel.controller;

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
import com.gpcsoft.core.utils.StringUtils;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.serve.hotel.domain.Hotel;
import com.gpcsoft.serve.hotel.domain.MeetingRoom;
import com.gpcsoft.serve.hotel.service.HotelService;
import com.gpcsoft.serve.hotel.service.MeetingRoomService;
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.service.AttachmentService;
import com.gpcsoft.srplatform.common.utils.AttachmentUtil;

/**
  * @gpcsoft.view value="meetingRoomFormView"
  *  url="view/serve/hotel/meeting_room_form.jsp"
  * @gpcsoft.view value="meetingRoomListView"
  *  url="view/serve/hotel/meeting_room_list.jsp"
  * @gpcsoft.view value="meetingRoomDetailView"
  *  url="view/serve/hotel/meeting_room_detail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={MeetingRoom.class})
@RequestMapping("/MeetingRoomController.do")//页面请求路径,可修改
public class MeetingRoomController extends AnnotationMultiController<MeetingRoom> {

	@Autowired(required=true) @Qualifier("meetingRoomServiceImpl")
	private MeetingRoomService meetingRoomService;
	
	@Autowired(required=true) @Qualifier("hotelServiceImpl")
	private HotelService hotelService;
	
	@Autowired(required=true) @Qualifier("attachmentServiceImpl")
	private AttachmentService attachmentServiceImpl;

	/** 
	 * Description : 跳转到会议室列表页面 
	 * Create Date: 2010-12-8下午02:24:32 by guoyr  Modified Date: 2010-12-8下午02:24:32 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toMeetingRoomListView")   
	public ModelAndView toMeetingRoomListView(HttpServletRequest request,String objId) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   

		Hotel hotel = hotelService.get(objId);
		model.put("hotel", hotel);
		
		model.put("orgInfoId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId());

		return new ModelAndView("meetingRoomListView", model);
    }

	/** 
	 * Description : 跳转到添加或修改会议室页面 
	 * Create Date: 2010-12-8下午02:24:32 by guoyr  Modified Date: 2010-12-8下午02:24:32 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@Override
	public ModelAndView toCreateOrUpdate(HttpServletRequest request)
			throws Exception {
		Map<String, Object> model = new HashMap<String, Object>(); 
		
		MeetingRoom meetingRoom = null;
		if(StringUtils.hasLength(request.getParameter("objId"))){
			meetingRoom = meetingRoomService.get(request.getParameter("objId"));
		}else {
			meetingRoom = new MeetingRoom();
		}
		model.put("meetingRoom", meetingRoom);
		
		return new ModelAndView("meetingRoomFormView", model);
	}
	
	 /** Description :  保存会议室信息
	 * Create Date: 2010-12-7上午10:28:21 by guoyr  Modified Date: 2010-12-7上午10:28:21 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveMeetingRoom")
	public ModelAndView saveMeetingRoom(HttpServletRequest request, MeetingRoom meetingRoom, SessionStatus status) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		//处理商品图片
		CommonsMultipartFile file = null;
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
	    file = (CommonsMultipartFile)multipartRequest.getFile("pictureFile");
		if(file != null && file.getSize() != 0){
			Object o=AttachmentUtil.uploadFile(request,"pictureFile");
			if(o instanceof GpcBaseObject){
				Attachment attachment = (Attachment)o;
				attachmentServiceImpl.saveAttachment((Attachment)o);
				meetingRoom.setPicture(attachment.getObjId());
			}
		}
		
		meetingRoomService.saveMeetingRoom(meetingRoom);
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** Description :  保存会议室信息
	 * Create Date: 2010-12-7上午10:28:21 by guoyr  Modified Date: 2010-12-7上午10:28:21 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=isUnique")
	public ModelAndView isUnique(String objId,String hotelId,String meetingRoomCode)throws Exception {
		Map<String, Boolean> model = new HashMap<String, Boolean>();
		model.put("isUnique", meetingRoomService.isUnique(objId, hotelId, meetingRoomCode));
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description : 查看会议室
	 * Create Date: 2010-12-8下午02:24:32 by guoyr  Modified Date: 2010-12-8下午02:24:32 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@Override
	public ModelAndView toShowView(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>(); 
		MeetingRoom meetingRoom = null;
		// 避免没有取到主键值而报错
		if(StringUtils.hasLength(request.getParameter("objId"))){
			meetingRoom = meetingRoomService.get(request.getParameter("objId"));
		}else {
			meetingRoom = new MeetingRoom();
		}
		model.put("meetingRoom", meetingRoom);
		
		return new ModelAndView("meetingRoomDetailView", model);
	}
	
	/** 
	 * Description : 删除会议室并将会议室的类型和人数冗余到酒店里，便于检索
	 * Create Date: 2010-12-9下午04:52:47 by guoyr  Modified Date: 2010-12-9下午04:52:47 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=removeMeetingRoom")
	public ModelAndView removeMeetingRoom(String objId,String hotelId) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>(); 
		meetingRoomService.remove(objId, MeetingRoom.class);
		
		// 删除会议室后将会议室的类型和人数冗余到酒店里
		if(StringUtils.hasLength(hotelId)){
			meetingRoomService.updateMeetingRoomType(hotelId);
		}
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
}
