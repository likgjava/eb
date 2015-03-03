package com.gpcsoft.epp.projrule.service;
import java.util.List;

import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.projrule.domain.BidRoom;
import com.gpcsoft.epp.projrule.domain.Facilities;
import com.gpcsoft.epp.projrule.domain.MeetRoom;

public interface MeetRoomService extends BaseGenericService<MeetRoom> {

	/**
	 * 
	 * Description :得到标评室列表  
	 * Create Date: 2010-5-4下午02:18:19 by liuke  Modified Date: 2010-5-4下午02:18:19 by liuke
	 * @param   
	 * @return  List
	 * @Exception
	 */
	public List<MeetRoom> saveAndGetBidMeetRooms();
	
	
	/**
	 * 
	 * Description :得到开标室列表  
	 * Create Date: 2010-5-4下午02:18:19 by liuke  Modified Date: 2010-5-4下午02:18:19 by liuke
	 * @param   
	 * @return  List
	 * @Exception
	 */
	public List<MeetRoom> saveAndGetOpenBidMeetRooms();
	
	/**
	 * 
	 * Description :得到评标室列表  
	 * Create Date: 2010-5-4下午02:18:19 by liuke  Modified Date: 2010-5-4下午02:18:19 by liuke
	 * @param   
	 * @return  List
	 * @Exception
	 */
	public List<MeetRoom> saveAndGetEvalBidMeetRooms();
	
	 /**
	  * 
	  * Description : 增加临时评标室 
	  * Create Date: 2010-5-4下午03:32:10 by liuke Modified Date: 2010-5-4下午03:32:10 by liuke
	  * @param   MeetRoom meetroom
	  * @return  void
	  * @Exception
	  */
	 public MeetRoom saveTemporaryRoom(MeetRoom meetroom);
	 
	 
	 /**
	  * 
	  * Description :根据时间段得到预定信息
	  * Create Date: 2010-5-5下午01:39:12 by liuke  Modified Date: 2010-5-5下午01:39:12 by liuke
	  * @param   
	  * @return  List
	  * @Exception
	  */
	 public List getPresetTimeByDate(String startDate,String endDate);
	 
	 
	 
	 /**
	  * 
	  * Description :  通过主键得到标评室信息 
	  * Create Date: 2010-5-5下午05:44:22 by liuke  Modified Date: 2010-5-5下午05:44:22 by liuke
	  * @param   String objId
	  * @return  List
	  * @Exception
	  */
	 public List getMeetRoomsByObjId(String objId);
	 
	 
	 
	 /**
      * 
      * Description : 新增预定时间对象  
      * Create Date: 2010-5-6上午09:48:01 by liuke  Modified Date: 2010-5-6上午09:48:01 by liuke
      * @param   BidRoom bidroom
      * @return  void
      * @Exception
      */
	 public BidRoom savePresetTime(BidRoom bidroom);
	 
	 
	 /**
      * 
      * Description : 新增预定开标室时间对象  
      * Create Date: 2010-5-6上午09:48:01 by liuke  Modified Date: 2010-5-6上午09:48:01 by liuke
      * @param   BidRoom bidroom
      * @return  void
      * @Exception
      */
	 public BidRoom saveOpenBidPresetTime(BidRoom bidroom);
	 
	 /**
	  * 
	  * Description : 通过包组Id删除原有的预定时间记录 
	  * Create Date: 2010-5-29下午01:30:52 by liuke  Modified Date: 2010-5-29下午01:30:52 by liuke
	  * @param   
	  * @return  
	  * @Exception
	  */
	 public void removePresetTimeByProjPackId(String projPackId,String roomType);
	 
	 
	 /**
	  * 
	  * Description :  根据包组Id，得到项目信息
	  * Create Date: 2010-5-29下午02:18:30 by liuke  Modified Date: 2010-5-29下午02:18:30 by liuke
	  * @param   
	  * @return  
	  * @Exception
	  */
	 
	 
	 
	 /**
	  * 
	  * Description :根据标评室ID和预定时间得到项目信息  
	  * Create Date: 2010-5-31下午02:48:35 by liuke  Modified Date: 2010-5-31下午02:48:35 by liuke
	  * @param   
	  * @return  
	  * @Exception
	  */
	 public Project getProjectByRoomIdAndTime(String roomId,String startTime,String timeBucket);
	 
	/** 
	 * FuncName : getBidRoomByRoomIdAndTime
	 * Description :  根据标评室ID和预定时间得到项目信息  
	 * Create Date: 2011-8-17上午09:34:46 by yangx  Modified Date: 2011-8-17上午09:34:46 by yangx
	 * @param   roomId：房间ID,startTime：开始时间，timeBucket:会议室申请要求
	 * @return  
	 * @Exception   
	 */
	public BidRoom getBidRoomByRoomIdAndTime(String roomId, String startTime,String timeBucket);
	 
	 /**
	  * 
	  * Description :通过预定时间主键删除预定时间
	  * Create Date: 2010-5-31下午02:48:35 by liuke  Modified Date: 2010-5-31下午02:48:35 by liuke
	  * @param   
	  * @return  
	  * @Exception
	  */
	 public void removePresetTimeByojbId(String objId);

	 /**
	  * 
	  * Description :删除评标室及预订信息  
	  * Create Date: 2010-11-12上午11:06:18 by liuke  Modified Date: 2010-11-12上午11:06:18 by liuke
	  * @param   String objId 评标室主键
	  * @return  
	  * @Exception
	  */
	 public void  removeMeetRoomByojbId(String objId);
	 
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
	 public boolean checkPresetTimeByMeetingRoom(String roomId);
	 
	 
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
	 public BidRoom  getPresetTimeByojbId(String objId);
	 
	 /**
	  * Description:根据项目ID获取BidRoom
	  * @param projectId
	  * @return
	  * Created at 2011-4-25 15:32 by zhouzhanghe
	  */
	 public List<BidRoom> getBidRoomByProjectId(String projectId);
	
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
	public List<BidRoom> getBidRooms(String meetroomId, String startDate,String endDate);
	
	
	/** 
	 * FuncName : saveBidRoom
	 * Description :  保存开标室信息
	 * Create Date: 2011-8-17上午10:20:38 by yangx  Modified Date: 2011-8-17上午10:20:38 by yangx
	 * @param   bidRoom：开标室信息
	 * @return  BidRoom
	 * @Exception   
	 */
	public BidRoom saveBidRoom(BidRoom bidRoom,String meetroomId,String rtime)throws Exception;
	
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
	public MeetRoom saveTemporaryRoomAndFacilities(MeetRoom meetroom,List<Facilities> list);
	
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
	public List<Facilities> getFacilitiesList(String meetRoomId);
	
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
	public void deleteFacilitiesList(final String meetRoomId);
}
