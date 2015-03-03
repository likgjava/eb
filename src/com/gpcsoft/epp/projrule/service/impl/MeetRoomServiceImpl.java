package com.gpcsoft.epp.projrule.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Service;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.DateUtil;
import com.gpcsoft.epp.common.domain.SeparationEnum;
import com.gpcsoft.epp.expertrule.manager.TakeManager;
import com.gpcsoft.epp.project.domain.ProjProcessStatusEnum;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.domain.ProjectRule;
import com.gpcsoft.epp.project.manager.ProjectManager;
import com.gpcsoft.epp.projrule.dao.FacilitiesDao;
import com.gpcsoft.epp.projrule.dao.MeetRoomDao;
import com.gpcsoft.epp.projrule.domain.BidRoom;
import com.gpcsoft.epp.projrule.domain.Facilities;
import com.gpcsoft.epp.projrule.domain.MeetRoom;
import com.gpcsoft.epp.projrule.domain.RoomTypeEnum;
import com.gpcsoft.epp.projrule.manager.MeetRoomManager;
import com.gpcsoft.epp.projrule.service.MeetRoomService;
import com.gpcsoft.eprocurement.taker.provider.CodePO;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.User;

@Service 
public class MeetRoomServiceImpl extends BaseGenericServiceImpl<MeetRoom> implements MeetRoomService {

	@Autowired(required=true) @Qualifier("meetRoomManagerImpl")
	private MeetRoomManager meetRoomManager;
    
	@Autowired(required=true) @Qualifier("projectManagerImpl")
	private ProjectManager projectManager;
	
	@Autowired(required=true)  @Qualifier("takeManagerImpl")
	private TakeManager takeManager;
	
	@Autowired(required=true)  @Qualifier("meetRoomDaoHibernate")
	private MeetRoomDao meetRoomDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("facilitiesDaoHibernate")
	private FacilitiesDao facilitiesDaoHibernate;
	/**
	 * 
	 * Description :得到标评室列表  
	 * Create Date: 2010-5-4下午02:18:19 by liuke  Modified Date: 2010-5-4下午02:18:19 by liuke
	 * @param   
	 * @return  List
	 * @Exception
	 */
	public List<MeetRoom> saveAndGetBidMeetRooms() {
		logger.debug("\nMeetRoomServiceImpl||saveAndGetBidMeetRooms\n");
		List<MeetRoom> list = meetRoomDaoHibernate.getMeetRoomListByRoomType(RoomTypeEnum.BID_ROOM);
		if (list==null||list.size()<1) {
			list = new ArrayList<MeetRoom>();
			List<CodePO> roomList = takeManager.getInfoForFile(SeparationEnum.ROOM);//得到评审室
			for (CodePO codePO:roomList) {
				MeetRoom room = new MeetRoom();
				room.setMeetRoomName(codePO.getColumn_name());
				room.setMeetRoomAddress(codePO.getColumn_name());
				room.setRoomType(RoomTypeEnum.BID_ROOM);
				meetRoomManager.save(room);//保存评标室
				list.add(room);
			}
		}
		return list;
	}
    
	 /**
	  * 
	  * Description : 增加临时评标室 
	  * Create Date: 2010-5-4下午03:32:10 by liuke Modified Date: 2010-5-4下午03:32:10 by liuke
	  * @param   MeetRoom meetroom
	  * @return  void
	  * @Exception
	  */
	public MeetRoom saveTemporaryRoom(MeetRoom meetroom) {
		logger.debug("\nMeetRoomServiceImpl||saveTemporaryRoom\n");
		return meetRoomManager.save(meetroom);
	}
    
	 /**
	  * 
	  * Description :得到全部预定时间  
	  * Create Date: 2010-5-5下午01:39:12 by liuke  Modified Date: 2010-5-5下午01:39:12 by liuke
	  * @param   
	  * @return  List
	  * @Exception
	  */
	public List getPresetTimeByDate(String startDate,String endDate) {
		logger.debug("\nMeetRoomServiceImpl||getPresetTimeByDate\n");
		List list = meetRoomManager.getPresetTimeByDate(startDate,endDate);
		return list;
	}
	 /**
	  * 
	  * Description :  通过主键得到标评室信息 
	  * Create Date: 2010-5-5下午05:44:22 by liuke  Modified Date: 2010-5-5下午05:44:22 by liuke
	  * @param   String objId
	  * @return  List
	  * @Exception
	  */
	public List getMeetRoomsByObjId(String objId) {
		logger.debug("\nMeetRoomServiceImpl||getMeetRoomsByObjId\n");
		List list = meetRoomManager.getMeetRoomsByObjId(objId);
		return list;
	}
     

	 /**
     * 
     * Description : 新增预定时间对象  
     * Create Date: 2010-5-6上午09:48:01 by liuke  Modified Date: 2010-5-6上午09:48:01 by liuke
     * @param   BidRoom bidroom
     * @return  void
     * @Exception
     */
	public BidRoom savePresetTime(BidRoom bidroom) {
		logger.debug("\nMeetRoomServiceImpl||savePresetTime\n");
		/** 保存项目实施状态 */
		projectManager.saveProjProcessStatus(bidroom.getProject().getObjId(), ProjProcessStatusEnum.BOOK_EVALUATION_ROOM);
		bidroom = meetRoomManager.savePresetTime(bidroom);
		ProjectRule projectRule = projectManager.getProjectRuleByProjectId(bidroom.getProject().getObjId());
		projectRule.setEvalSTime(bidroom.getStartDate());
		projectRule.setEvalETime(bidroom.getEndDate());
		User user = AuthenticationHelper.getCurrentUser();
		bidroom.setUser(user);
		Project project = projectManager.get(bidroom.getProject().getObjId());
		if(project.getParentId() != null){//若还有父级，则表示拿到的是包组，需要获得招标项目ID
			project = projectManager.get(bidroom.getProject().getObjId());
		}
		bidroom.setParentBizId(project.getObjId());
		return bidroom;
	}
     
	
	 /**
     * 
     * Description : 新增预定开标时间对象
     * Create Date: 2010-5-6上午09:48:01 by liuke  Modified Date: 2010-5-6上午09:48:01 by liuke
     * @param   BidRoom bidroom
     * @return  void
     * @Exception
     */
	public BidRoom saveOpenBidPresetTime(BidRoom bidroom) {
		logger.debug("\nMeetRoomServiceImpl||saveOpenBidPresetTime\n");
		/** 保存项目实施状态 */
		bidroom = meetRoomManager.savePresetTime(bidroom);
		User user = AuthenticationHelper.getCurrentUser();
		bidroom.setUser(user);
		Project project = projectManager.get(bidroom.getProject().getObjId());
		if(project.getParentId() != null){//若还有父级，则表示拿到的是包组，需要获得招标项目ID
			project = projectManager.get(bidroom.getProject().getObjId());
		}
		bidroom.setParentBizId(project.getObjId());
		return bidroom;
	}
	
	
	
	 /**
	  * 
	  * Description : 通过项目ID和标评室类型删除原有的预定时间记录 
	  * Create Date: 2010-5-29下午01:30:52 by liuke  Modified Date: 2010-5-29下午01:30:52 by liuke
	  * @param   
	  * @return  
	  * @Exception
	  */
	public void removePresetTimeByProjPackId(String projPackId,String roomType) {
		logger.debug("\nMeetRoomServiceImpl||removePresetTimeByProjPackId\n");
		meetRoomManager.removePresetTimeByProjPackId(projPackId,roomType);
	}
    
	 /**
	  * 
	  * Description :根据标评室ID和预定时间得到项目信息  
	  * Create Date: 2010-5-31下午02:48:35 by liuke  Modified Date: 2010-5-31下午02:48:35 by liuke
	  * @param   
	  * @return  
	  * @Exception
	  */
	public Project getProjectByRoomIdAndTime(String roomId, String startTime,
			String timeBucket) {
		logger.debug("\nMeetRoomServiceImpl||removePresetTimeByProjPackId\n");
		BidRoom bidroom = meetRoomManager.getBidRoomByRoomIdAndTime(roomId, startTime, timeBucket);
		if(null!=bidroom&&!bidroom.equals("")){
			Project project = projectManager.getProjectByTotal(bidroom.getProject().getObjId());
			return project;
		}
		return null;
	}
    
	
	/**
	  * 
	  * Description :通过预定时间主键删除预定时间
	  * Create Date: 2010-5-31下午02:48:35 by liuke  Modified Date: 2010-5-31下午02:48:35 by liuke
	  * @param   
	  * @return  
	  * @Exception
	  */
	public void removePresetTimeByojbId(String objId) {
		logger.debug("\nMeetRoomServiceImpl||removePresetTimeByojbId\n");
		meetRoomDaoHibernate.removePresetTimeByojbId(objId);
		
	}

	/**
	  * 
	  * Description :删除评标室及预订信息  
	  * Create Date: 2010-11-12上午11:06:18 by liuke  Modified Date: 2010-11-12上午11:06:18 by liuke
	  * @param   String objId 评标室主键
	  * @return  
	  * @Exception
	  */
	public void removeMeetRoomByojbId(String objId) {
		logger.debug("\nMeetRoomServiceImpl||removeMeetRoomByojbId\n");
		meetRoomDaoHibernate.removePresetTimeByMeetRoomId(objId);//删除预订信息
		meetRoomDaoHibernate.remove(objId, MeetRoom.class);//删除标评室
	}

	/**
	 * 
	 * Description :得到开标室列表  
	 * Create Date: 2010-5-4下午02:18:19 by liuke  Modified Date: 2010-5-4下午02:18:19 by liuke
	 * @param   
	 * @return  List
	 * @Exception
	 */
	public List<MeetRoom> saveAndGetOpenBidMeetRooms() {
		logger.debug("\nMeetRoomServiceImpl||saveAndGetOpenBidMeetRooms\n");
		List<MeetRoom> list = meetRoomDaoHibernate.getMeetRoomListByRoomType(RoomTypeEnum.OPEN_BID_ROOM);
		if (list==null||list.size()<1) {
			list = new ArrayList<MeetRoom>();
			List<CodePO> roomList = takeManager.getInfoForFile(SeparationEnum.ROOM);//得到评审室
			for (CodePO codePO:roomList) {
				MeetRoom room = new MeetRoom();
				room.setMeetRoomName(codePO.getColumn_name());
				room.setMeetRoomAddress(codePO.getColumn_name());
				room.setRoomType(RoomTypeEnum.OPEN_BID_ROOM);
				meetRoomManager.save(room);//保存开标室
				list.add(room);
			}
		}
		return list;
	}

	/**
	 * 
	 * Description :得到评标室列表  
	 * Create Date: 2010-5-4下午02:18:19 by liuke  Modified Date: 2010-5-4下午02:18:19 by liuke
	 * @param   
	 * @return  List
	 * @Exception
	 */
	public List<MeetRoom> saveAndGetEvalBidMeetRooms() {
		
		List<MeetRoom> list = meetRoomDaoHibernate.getMeetRoomListByRoomType(RoomTypeEnum.BID_ROOM);
		if (list==null||list.size()<1) {
			list = new ArrayList<MeetRoom>();
			List<CodePO> roomList = takeManager.getInfoForFile(SeparationEnum.ROOM);//得到评审室
			for (CodePO codePO:roomList) {
				MeetRoom room = new MeetRoom();
				room.setMeetRoomName(codePO.getColumn_name());
				room.setMeetRoomAddress(codePO.getColumn_name());
				room.setRoomType(RoomTypeEnum.BID_ROOM);
				meetRoomManager.save(room);//保存评标室
				list.add(room);
			}
		}
		return list;
	}
	
	 /**
	  * FuncName: checkPresetTimeByMeetingRoom
	  * Description :  根据评标室查询是否存在预定时间
	  * @param 
	  * @param roomId
	  * @return boolean
	  * @author: liuke
	  * @Create Date:2011-1-30  上午11:49:16
	  * @Modifier: liuke
	  * @Modified Date:2011-1-30  上午11:49:16
	  */
	public boolean checkPresetTimeByMeetingRoom(String roomId) {
	  List list =	meetRoomDaoHibernate.getPresetTimeByMeetRoomId(roomId);
	  if(list.isEmpty()){  
		  return true;    //如果该评标室有预定时间，返回false
	  }else{
		  return false;
	  }
	}

	 /**
	  * FuncName: getPresetTimeByojbId
	  * Description :根据预定时间主键得到预定时间对象
	  * @param 
	  * @param objId
	  * @return BidRoom
	  * @author: liuke
	  * @Create Date:2011-2-25  下午02:39:38
	  * @Modifier: liuke
	  * @Modified Date:2011-2-25  下午02:39:38
	  */
	public BidRoom getPresetTimeByojbId(String objId) {
		return (BidRoom) this.get(objId, BidRoom.class);
	}
	
	 /**
	  * Description:根据项目ID获取BidRoom
	  * @param projectId
	  * @return
	  * Created at 2011-4-25 15:32 by zhouzhanghe
	  */
	public List<BidRoom> getBidRoomByProjectId(String projectId) {
		return meetRoomManager.getBidRoomByProjectId(projectId);
	}
	/**
	 * FuncName: getBidRooms
	 * Description :  查找到某评标室某时间段的预订信息
	 * @param 
	 * @param meetroomId
	 * @param startDate
	 * @param endDate
	 * @return List<BidRoom>
	 * @author: shenjz
	 * @Create Date:2011-6-20  上午11:48:51
	 * @Modifier: shenjz
	 * @Modified Date:2011-6-20  上午11:48:51
	 */
	public List<BidRoom> getBidRooms(String meetroomId, String startDate,String endDate){
		return meetRoomDaoHibernate.getBidRooms(meetroomId, startDate, endDate);
	}
	
	/** 
	 * FuncName : getBidRoomByRoomIdAndTime
	 * Description :  根据标评室ID和预定时间得到项目信息  
	 * Create Date: 2011-8-17上午09:34:46 by yangx  Modified Date: 2011-8-17上午09:34:46 by yangx
	 * @param   roomId：房间ID,startTime：开始时间，timeBucket:会议室申请要求
	 * @return  
	 * @Exception   
	 */
	public BidRoom getBidRoomByRoomIdAndTime(String roomId, String startTime,String timeBucket){
		return meetRoomManager.getBidRoomByRoomIdAndTime(roomId, startTime, timeBucket);
	}
	
	/** 
	 * FuncName : saveBidRoom
	 * Description :  保存开标室信息
	 * Create Date: 2011-8-17上午10:20:38 by yangx  Modified Date: 2011-8-17上午10:20:38 by yangx
	 * @param   bidRoom：开标室信息
	 * @return  BidRoom
	 * @Exception   
	 */
	public BidRoom saveBidRoom(BidRoom bidRoom,String meetroomId,String rtime) throws Exception{
		BidRoom bidroom_ = meetRoomManager.getBidRoomByRoomIdAndTime(meetroomId, rtime, bidRoom.getTimeBucket());
		if (bidroom_!=null) {//修改
			bidroom_.setUseStatus(bidRoom.getUseStatus());
			bidroom_.setRemark(bidRoom.getRemark());
			meetRoomManager.save(bidroom_,BidRoom.class);
		}else{//新增
			MeetRoom mt = meetRoomManager.get(meetroomId);
			bidRoom.setMeetRoom(mt);
			bidRoom.setBidRoomAddress(mt.getMeetRoomAddress());
			bidRoom.setBidRoomName(mt.getMeetRoomName());
			bidRoom.setStartDate(DateUtil.parse(rtime));
			bidRoom.setEndDate(DateUtil.parse(rtime));
			meetRoomManager.save(bidRoom,BidRoom.class);
		}
		return (bidroom_==null)?bidRoom:bidroom_;
	}
	
	/**
	 * FuncName: saveTemporaryRoomAndFacilities
	 * Description :  创建评标室以及相关的设备
	 * @param 
	 * @param meetroom
	 * @return MeetRoom
	 * @author: shenjz
	 * @Create Date:2011-11-3  下午02:02:09
	 * @Modifier: shenjz
	 * @Modified Date:2011-11-3  下午02:02:09
	 */
	public MeetRoom saveTemporaryRoomAndFacilities(MeetRoom meetroom,List<Facilities> list) {
		return meetRoomManager.save(meetroom);
	}
	
	/**
	 * FuncName: saveTemporaryRoomAndFacilities
	 * Description :  获取评标室相关的设备
	 * @param 
	 * @param meetroom
	 * @return MeetRoom
	 * @author: shenjz
	 * @Create Date:2011-11-3  下午02:02:09
	 * @Modifier: shenjz
	 * @Modified Date:2011-11-3  下午02:02:09
	 */
	public List<Facilities> getFacilitiesList(String meetRoomId){
		return facilitiesDaoHibernate.getFacilitiesList(meetRoomId);
	}
	
	/**
	 * FuncName: getFacilitiesList
	 * Description :  删除一个评标室所有设备
	 * @param 
	 * @param meetRoomId
	 * @return List<Facilities>
	 * @author: shenjz
	 * @Create Date:2011-11-3  下午07:27:16
	 * @Modifier: shenjz
	 * @Modified Date:2011-11-3  下午07:27:16
	 */
	@SuppressWarnings("unchecked")
	public void deleteFacilitiesList(final String meetRoomId){
		facilitiesDaoHibernate.deleteFacilitiesList(meetRoomId);
	}
}
