package com.gpcsoft.epp.projrule.dao;

import java.util.List;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.epp.projrule.domain.BidRoom;
import com.gpcsoft.epp.projrule.domain.Facilities;
import com.gpcsoft.epp.projrule.domain.MeetRoom;

public interface MeetRoomDao extends BaseGenericDao<MeetRoom> {
     
     /**
      * 
      * Description :根据标评室得到预定时间  
      * Create Date: 2010-5-4下午04:40:01 by liuke  Modified Date: 2010-5-4下午04:40:01 by liuke
      * @param   
      * @return  List
      * @Exception
      */
     public List getPresetTimeByMeetRoomId(String roomId); 
     
     /**
      * 
      * Description : 得到全部预定时间
      * Create Date: 2010-5-5下午01:36:40 by liuke  Modified Date: 2010-5-5下午01:36:40 by liuke
      * @param   
      * @return  List
      * @Exception
      */
     public List getPresetTimeByDate(String startDate,String endDate);
     
     
     
     /**
      * 
      * Description : 通过主键得到标评室信息
      * Create Date: 2010-5-5下午04:28:32 by liuke  Modified Date: 2010-5-5下午04:28:32 by liuke
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
	  * Description : 通过包组Id删除原有的预定时间记录 
	  * Create Date: 2010-5-29下午01:30:52 by liuke  Modified Date: 2010-5-29下午01:30:52 by liuke
	  * @param   
	  * @return  
	  * @Exception
	  */
     public void removePresetTimeByProjPackId(String projPackId,String roomType);
     
     
     /**
	  * 
	  * Description :根据标评室ID和预定时间得到项目信息  
	  * Create Date: 2010-5-31下午02:48:35 by liuke  Modified Date: 2010-5-31下午02:48:35 by liuke
	  * @param   
	  * @return  
	  * @Exception
	  */
     public BidRoom getBidRoomByRoomIdAndTime(String roomId, String startTime,
				String timeBucket);
     
     
     
     
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
	  * Description :根据评标室ID删除预定时间信息  
	  * Create Date: 2010-11-12上午11:20:02 by liuke  Modified Date: 2010-11-12上午11:20:02 by liuke
	  * @param   
	  * @return  
	  * @Exception
	  */
	 public void removePresetTimeByMeetRoomId(String meetRoomId);
	 
	 
	 /**
	  * 
	  * FuncName: getMeetRoomListByRoomType
	  * Description :  根据房间类型查询标评室
	  * @param 
	  * @param roomType
	  * @return List<MeetRoom>
	  * @author: liuke
	  * @Create Date:2011-1-17  上午10:28:49
	  * @Modifier: liuke
	  * @Modified Date:2011-1-17  上午10:28:49
	  */
	 public  List<MeetRoom> getMeetRoomListByRoomType(String roomType);
	 
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
	 * FuncName : getBidRoomByProjectIdAndRoomType
	 * Description :  根据
	 * Create Date: 2011-10-24下午05:35:10 by yangx  
	 * Modified Date: 2011-10-24下午05:35:10 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List getBidRoomByProjectIdAndRoomType(String projectId,String type);
	

}
