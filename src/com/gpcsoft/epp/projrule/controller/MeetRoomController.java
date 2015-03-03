package com.gpcsoft.epp.projrule.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.core.Constants;
import com.gpcsoft.core.utils.JsonUtils;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.common.domain.EsExceptionEnum;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.projrule.domain.BidRoom;
import com.gpcsoft.epp.projrule.domain.Facilities;
import com.gpcsoft.epp.projrule.domain.MeetRoom;
import com.gpcsoft.epp.projrule.domain.RoomTypeEnum;
import com.gpcsoft.epp.projrule.service.MeetRoomService;

/**
  * @gpcsoft.view value="meetRoomFormView"
  *  url="view/es/planform/projrule/meetRoomForm.jsp"
  * @gpcsoft.view value="meetRoomTreeFormView"
  *  url="view/es/planform/projrule/meetRoomTreeForm.jsp"
  * @gpcsoft.view value="meetRoomListView"
  *  url="view/es/planform/projrule/meetRoomList.jsp"
  * @gpcsoft.view value="meetRoomDetailView"
  *  url="view/es/planform/projrule/meetRoomDetail.jsp"
  *  @gpcsoft.view value="toBidRoomsList"
  *  url="view/es/planform/projrule/bidRoomsList.jsp"
  *  @gpcsoft.view value="toMeetRoom2"
  *  url="view/es/planform/projrule/meetRoomForm2.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={MeetRoom.class})
@RequestMapping("/MeetRoomController.do")//页面请求路径,可修改
@SuppressWarnings("unchecked")
public class MeetRoomController extends AnnotationMultiController<MeetRoom> {

	@Autowired(required=true) @Qualifier("meetRoomServiceImpl")
	private MeetRoomService meetRoomService;

	/**
	 * Description :得到所有标评室信息方法  
	 * Create Date: 2010-5-4下午02:11:51 by liuke  Modified Date: 2010-5-4下午02:11:51 by liuke
	 * @param   HttpServletRequest request
	 * @return  ModelAndView
	 * @Exception
	 */
	@RequestMapping(params = "method=getBidMeetRoom")
	public ModelAndView getBidMeetRoom(HttpServletRequest request) throws Exception {
		logger.debug("\nMeetRoomController||getBidMeetRoom\n");
		List list = meetRoomService.saveAndGetBidMeetRooms();
		Map model = new HashMap();
		model.put("meetRooms", list);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/**
	 * Description :得到所有开标室信息方法  
	 * Create Date: 2010-5-4下午02:11:51 by liuke  Modified Date: 2010-5-4下午02:11:51 by liuke
	 * @param   HttpServletRequest request
	 * @return  ModelAndView
	 * @Exception
	 */
	@RequestMapping(params = "method=getOpenBidMeetRoom")
	public ModelAndView getOpenBidMeetRoom(HttpServletRequest request) throws Exception {
		logger.debug("\nMeetRoomController||getOpenBidMeetRoom\n");
		List list = meetRoomService.saveAndGetOpenBidMeetRooms();
		Map model = new HashMap();
		model.put("meetRooms", list);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	
	/**
	 * FuncName: getEvalBidMeetRoom
	 * Description :得到所有评标室信息方法  
	 * @param 
	 * @param request
	 * @return
	 * @throws Exception ModelAndView
	 * @author: shenjz
	 * @Create Date:2011-11-3  上午10:07:45
	 * @Modifier: shenjz
	 * @Modified Date:2011-11-3  上午10:07:45
	 */
	@RequestMapping(params = "method=getEvalBidMeetRoom")
	public ModelAndView getEvalBidMeetRoom(HttpServletRequest request) throws Exception {
		logger.debug("\nMeetRoomController||getOpenBidMeetRoom\n");
		List list = meetRoomService.saveAndGetEvalBidMeetRooms();
		Map model = new HashMap();
		model.put("meetRooms", list);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/**
	 * Description :  增加评标室
	 * Create Date: 2010-5-4下午03:33:34 by liuke  Modified Date: 2010-5-4下午03:33:34 by liuke
	 * @param   HttpServletRequest request
	 * @return  ModelAndView
	 * @Exception
	 */
	@RequestMapping(params = "method=addTemporaryRoom")
    public ModelAndView addTemporaryRoom(HttpServletRequest request,MeetRoom mr) throws Exception {
		logger.debug("\nMeetRoomController||addTemporaryRoom\n");
		if(mr.getObjId()!=null){
			mr.setObjId(null);
		}
		meetRoomService.saveTemporaryRoom(mr);
	    Map model = new HashMap();
	    return new ModelAndView(Constants.JSON_VIEW, model);
    }
	
	/**
	 * 
	 * Description :  根据标评室得到预定时间
	 * Create Date: 2010-5-4下午04:51:42 by liuke  Modified Date: 2010-5-4下午04:51:42 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params = "method=getPresetTime")
	public ModelAndView getPresetTime(HttpServletRequest request)throws Exception { 
		logger.debug("\nMeetRoomController||getPresetTime\n");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		startTime+=" 00:00:00";
		endTime+=" 23:59:59";
		List list =  meetRoomService.getPresetTimeByDate(startTime,endTime);
		Map model = new HashMap();
		model.put("presetTime", list);
        return new ModelAndView(Constants.JSON_VIEW, model);	  
		
	}
	
	/**
	 * 
	 * Description :  根据评标室名称和时间得到预定时间对象
	 * Create Date: 2010-5-5下午04:23:29 by liuke  Modified Date: 2010-5-5下午04:23:29 by liuke
	 * @param   HttpServletRequest request
	 * @return  ModelAndView
	 * @Exception
	 */
	@RequestMapping(params = "method=getMeetRoomsByObjId")
	public ModelAndView getMeetRoomsByObjId(HttpServletRequest request)throws Exception {
		logger.debug("\nMeetRoomController||getMeetRoomsByObjId\n");
		String meetroomId = request.getParameter("meetroomId");
		List list =  meetRoomService.getMeetRoomsByObjId(meetroomId);
		 MeetRoom mr = null;
		 if(list.size()>0) {
			 mr=(MeetRoom) list.get(0);
		 }
		 Map model = new HashMap();
		 model.put("meetRoom", mr);
		 return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/**
	 * 
	 * Description :  保存预定时间对象
	 * Create Date: 2010-5-7上午11:21:52 by liuke  Modified Date: 2010-5-7上午11:21:52 by liuke
	 * @param   HttpServletRequest request,BidRoom bidroom
	 * @return  ModelAndView
	 * @Exception
	 */
	@RequestMapping(params ="method=savePresetTime")
	public ModelAndView savePresetTime(HttpServletRequest request,BidRoom bidroom)throws Exception {
		logger.debug("\nMeetRoomController||savePresetTime\n");
		BidRoom bid = bidroom;
		String meetroomId = request.getParameter("rid");
		String projPackId = request.getParameter("projPackId");
		if(null!=projPackId&&!projPackId.equals("")) {
			meetRoomService.removePresetTimeByProjPackId(projPackId,RoomTypeEnum.BID_ROOM);
		}
		MeetRoom mt = (MeetRoom) meetRoomService.getMeetRoomsByObjId(meetroomId).get(0);
		bid.setMeetRoom(mt);
		Project project = new Project();
		project.setObjId(projPackId);
		bid.setProject(project);
		meetRoomService.savePresetTime(bid);
		Map model = new HashMap();
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	
	
	/**
	 * 
	 * Description :  保存预定开标室时间对象
	 * Create Date: 2010-5-7上午11:21:52 by liuke  Modified Date: 2010-5-7上午11:21:52 by liuke
	 * @param   HttpServletRequest request,BidRoom bidroom
	 * @return  ModelAndView
	 * @Exception
	 */
	@RequestMapping(params ="method=saveOpenBidPresetTime")
	public ModelAndView saveOpenBidPresetTime(HttpServletRequest request,BidRoom bidroom)throws Exception {
		logger.debug("\nMeetRoomController||savePresetTime\n");
		BidRoom bid = bidroom;
		String meetroomId = request.getParameter("rid");
		String projPackId = request.getParameter("projPackId");
		if(null!=projPackId&&!projPackId.equals("")) {
			meetRoomService.removePresetTimeByProjPackId(projPackId,RoomTypeEnum.OPEN_BID_ROOM);
		}
		MeetRoom mt = (MeetRoom) meetRoomService.getMeetRoomsByObjId(meetroomId).get(0);
		bid.setMeetRoom(mt);
		Project project = new Project();
		project.setObjId(projPackId);
		bid.setProject(project);
		meetRoomService.saveOpenBidPresetTime(bid);
		Map model = new HashMap();
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/**
	 * Description :通过标评室主键和预定时间得到项目信息  
	 * Create Date: 2010-5-31下午02:29:26 by liuke  Modified Date: 2010-5-31下午02:29:26 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params ="method=getProjectByRoomIdAndTime")
	public ModelAndView getProjectByRoomIdAndTime(HttpServletRequest request)throws Exception {
		logger.debug("\nMeetRoomController||getProjectByRoomIdAndTime\n");
		String roomId = request.getParameter("meetroomId");
		String startTime = request.getParameter("time");
		String timeBucket = request.getParameter("timeBucket");
		Project ProjPack =  meetRoomService.getProjectByRoomIdAndTime(roomId, startTime, timeBucket);
		if(null==ProjPack){
			ProjPack = new Project();
		}
		Map model = new HashMap();
		model.put("ProjPack", ProjPack);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/**
	 * Description:通过预定时间主键删除预定时间
	 * Create Date: 2010-5-31下午02:29:26 by liuke  Modified Date: 2010-5-31下午02:29:26 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params ="method=removePresetTimeByojbId")
	public ModelAndView removePresetTimeByojbId(HttpServletRequest request)throws Exception{
		logger.debug("\nMeetRoomController||removePresetTimeByojbId\n");
		String objId = request.getParameter("objId");
		Map model = new HashMap();
		meetRoomService.removePresetTimeByojbId(objId);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/**
	 * Description:删除评标室及预订信息
	 * Create Date: 2010-11-12下午02:29:26 by liuke  Modified Date: 2011-11-11下午02:29:26 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params ="method=removeMeetRoomByojbId")
	public ModelAndView removeMeetRoomByojbId(HttpServletRequest request)throws Exception{
		logger.debug("\nes MeetRoomController||removeMeetRoomByojbId\n");
		String objId = request.getParameter("objId");
		Map model = new HashMap();
		boolean  isCandelete = meetRoomService.checkPresetTimeByMeetingRoom(objId);
		if(!isCandelete){
			model.put("result", messageSource.getMessage(EsExceptionEnum.MEETROOM_NOT_DELETE));
			model.put("refreshPage", "false");  //是否刷新页面
		}else{
			meetRoomService.removeMeetRoomByojbId(objId);
			model.put("refreshPage", "true");   //是否刷新页面
		}
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	/**
	 * FuncName: getPresetTimeByojbId
	 * Description : 根据预定时间主键得到预定时间对象
	 * @param 
	 * @param request
	 * @return
	 * @throws Exception ModelAndView
	 * @author: liuke
	 * @Create Date:2011-2-25  下午02:37:31
	 * @Modifier: liuke
	 * @Modified Date:2011-2-25  下午02:37:31
	 */
	@RequestMapping(params ="method=getPresetTimeByojbId")
	public ModelAndView getPresetTimeByojbId(HttpServletRequest request)throws Exception{
		logger.debug("\nMeetRoomController||getPresetTimeByojbId\n");
		String objId = request.getParameter("objId");
		Map model = new HashMap();
		BidRoom bidroom =  meetRoomService.getPresetTimeByojbId(objId);
		model.put("bidroom", bidroom);
		model.put("startTime", bidroom.getStartDate().toString().subSequence(0, 19));
		model.put("endTime", bidroom.getEndDate().toString().subSequence(0, 19));
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/**
	 * FuncName: getBidRooms
	 * Description :  查找到某评标室某时间段的预订信息
	 * @param 
	 * @param request
	 * @return
	 * @throws Exception ModelAndView
	 * @author: shenjz
	 * @Create Date:2011-6-20  上午11:31:08
	 * @Modifier: shenjz
	 * @Modified Date:2011-6-20  上午11:31:08
	 */
	@RequestMapping(params ="method=getBidRooms")
	public ModelAndView getBidRooms(HttpServletRequest request)throws Exception{
		logger.debug("\nMeetRoomController||getBidRooms\n");
		String meetroomId = request.getParameter("meetroomId");
		String startDateStr =request.getParameter("startDate");
		String endDateStr =request.getParameter("endDate");
		List<BidRoom> list = meetRoomService.getBidRooms(meetroomId, startDateStr, endDateStr);
		Map model = new HashMap();
		model.put("list", list);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/**
	 * FuncName: toBidRoomsList
	 * Description :  跳转到某评标室某时间段的预订页面
	 * @param 
	 * @param request
	 * @return
	 * @throws Exception ModelAndView
	 * @author: shenjz
	 * @Create Date:2011-6-20  下午04:45:02
	 * @Modifier: shenjz
	 * @Modified Date:2011-6-20  下午04:45:02
	 */
	@RequestMapping(params ="method=toBidRoomsList")
	public ModelAndView toBidRoomsList(HttpServletRequest request)throws Exception{
		logger.debug("\nMeetRoomController||getBidRooms\n");
		String meetroomId = request.getParameter("meetroomId");
		String startDateStr =request.getParameter("startDate");
		String endDateStr =request.getParameter("endDate");
		List<BidRoom> list = meetRoomService.getBidRooms(meetroomId, startDateStr, endDateStr);
		Map model = new HashMap();
		model.put("list", list);
		return new ModelAndView("toBidRoomsList", model);
	}
	
	/**
	 * FuncName: saveForIsOff
	 * Description :  保存开标室是否禁用
	 * @param 
	 * @param request
	 * @param bidroom
	 * @return
	 * @throws Exception ModelAndView
	 * @author: yangx
	 * @Create Date:2011-7-20  上午09:13:15
	 * @Modifier: chench
	 * @Modified Date:2011-7-20  上午09:13:15
	*/		 
	@RequestMapping(params ="method=saveForIsOff")
	public ModelAndView saveForIsOff(HttpServletRequest request,BidRoom bidroom)throws Exception {
		logger.debug("\nMeetRoomExtController||saveForIsOff\n");
		String meetroomId = request.getParameter("rid");
		String rtime = request.getParameter("rtime");
		meetRoomService.saveBidRoom(bidroom, meetroomId, rtime);
		return new ModelAndView(Constants.JSON_VIEW);
	}

	/**
	 * FuncName: getBidRoomsByMeetRoomId
	 * Description : 根据标评室Id查询禁用信息
	 * @param 
	 * @param request
	 * @return
	 * @throws Exception ModelAndView
	 * @author: yangx
	 * @Create Date:2011-7-20  上午09:13:48
	 * @Modifier: chench
	 * @Modified Date:2011-7-20  上午09:13:48
	*/		 
	@RequestMapping(params ="method=getBidRoomsByMeetRoomId")
	public ModelAndView getBidRoomsByMeetRoomId(HttpServletRequest request)throws Exception {
		logger.debug("\nMeetRoomExtController||getBidRoomsByMeetRoomId\n");
		String meetroomId = request.getParameter("meetroomId");
		String timeBucket = request.getParameter("timeBucket");
		String startTime = request.getParameter("rtime");
		BidRoom bidroom = meetRoomService.getBidRoomByRoomIdAndTime(meetroomId, startTime, timeBucket);
		Map model = new HashMap();
		model.put("bidRoom",bidroom);
		return new ModelAndView(Constants.JSON_VIEW,model);
	}

	/**
	 * FuncName: removeBidRoom
	 * Description :  启用开标室
	 * @param 
	 * @param request
	 * @return
	 * @throws Exception ModelAndView
	 * @author: yangx
	 * @Create Date:2011-7-20  上午09:15:23
	 * @Modifier: chench
	 * @Modified Date:2011-7-20  上午09:15:23
	*/		 
	@RequestMapping(params ="method=removeBidRoom")
	public ModelAndView removeBidRoom(HttpServletRequest request)throws Exception {
		logger.debug("\nMeetRoomExtController||removeBidRoom\n");
		String meetroomId = request.getParameter("meetroomId");
		String timeBucket = request.getParameter("timeBucket");
		String startTime = request.getParameter("rtime");
		BidRoom bidroom = meetRoomService.getBidRoomByRoomIdAndTime(meetroomId, startTime, timeBucket);
		if (bidroom!=null) {//开标室是否为NULL
			meetRoomService.remove(bidroom.getObjId(),BidRoom.class);
		}
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/**
	 * FuncName: saveTemporaryRoomAndFacilities
	 * Description : 创建评标室以及相关的设备
	 * @param 
	 * @param request
	 * @param mr
	 * @throws Exception ModelAndView
	 * @author: shenjz
	 * @Create Date:2011-11-3  下午02:04:41
	 * @Modifier: shenjz
	 * @Modified Date:2011-11-3  下午02:04:41
	 */
	@RequestMapping(params ="method=saveTemporaryRoomAndFacilities")
	public ModelAndView saveTemporaryRoomAndFacilities(HttpServletRequest request,String facilitiesStr,String meetRoomStr)throws Exception {
		MeetRoom mr = JsonUtils.json2Bean(meetRoomStr,MeetRoom.class);
		if(mr.getObjId()!=null){
			mr.setObjId(null);
		}
		meetRoomService.saveTemporaryRoom(mr);
		if (null != meetRoomStr && !"".equals(meetRoomStr)) {
			JSONArray reportPlanRelatingDetailArray = JSONArray.fromObject(facilitiesStr);
			if (null != reportPlanRelatingDetailArray && !reportPlanRelatingDetailArray.isEmpty()) {
				for (int i=0;i<reportPlanRelatingDetailArray.size();i++) {
					Facilities applyInfo = (Facilities)JSONObject.toBean(((JSONObject)reportPlanRelatingDetailArray.get(i)),Facilities.class);
					applyInfo.setMeetRoom(mr);
					meetRoomService.save(applyInfo, Facilities.class);
				}
			}
		}
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/**
	 * FuncName: toMeetRoom2
	 * Description :  创建或修改对象
	 * @param request
	 * @throws Exception ModelAndView
	 * @author: shenjz
	 * @Create Date:2011-11-3  下午07:31:29
	 * @Modifier: shenjz
	 * @Modified Date:2011-11-3  下午07:31:29
	 */
	@RequestMapping(params ="method=toMeetRoom2")
	public ModelAndView toMeetRoom2(HttpServletRequest request)throws Exception {
		Map model = new HashMap();
		String objId= request.getParameter("objId");
		List<Facilities> facilitiesList = meetRoomService.getFacilitiesList(objId);
		model.put("objId", objId);
		model.put("facilitiesList", facilitiesList);
		return new ModelAndView("toMeetRoom2",model);
	}
	
	/**
	 * FuncName: toMeetRoom2
	 * Description :  创建或修改对象
	 * @param request
	 * @throws Exception ModelAndView
	 * @author: shenjz
	 * @Create Date:2011-11-3  下午07:31:29
	 * @Modifier: shenjz
	 * @Modified Date:2011-11-3  下午07:31:29
	 */
	@RequestMapping(params ="method=updateTemporaryRoomAndFacilities")
	public ModelAndView updateTemporaryRoomAndFacilities(HttpServletRequest request,String facilitiesStr,String meetRoomStr)throws Exception {
		MeetRoom mr = JsonUtils.json2Bean(meetRoomStr,MeetRoom.class);
		meetRoomService.saveTemporaryRoom(mr);
		meetRoomService.deleteFacilitiesList(mr.getObjId());
		if (null != meetRoomStr && !"".equals(meetRoomStr)) {
			JSONArray reportPlanRelatingDetailArray = JSONArray.fromObject(facilitiesStr);
			if (null != reportPlanRelatingDetailArray && !reportPlanRelatingDetailArray.isEmpty()) {
				for (int i=0;i<reportPlanRelatingDetailArray.size();i++) {
					Facilities applyInfo = (Facilities)JSONObject.toBean(((JSONObject)reportPlanRelatingDetailArray.get(i)),Facilities.class);
					applyInfo.setMeetRoom(mr);
					meetRoomService.save(applyInfo, Facilities.class);
				}
			}
		}
		return new ModelAndView(Constants.JSON_VIEW);
	}
}
