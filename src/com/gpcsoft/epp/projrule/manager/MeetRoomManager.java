package com.gpcsoft.epp.projrule.manager;

import java.util.List;

import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.epp.projrule.domain.BidRoom;
import com.gpcsoft.epp.projrule.domain.MeetRoom;

public interface MeetRoomManager extends BaseManager<MeetRoom> {

	 /**
	  * 
	  * Description :得到全部预定时间  
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
	 public BidRoom getBidRoomByRoomIdAndTime(String roomId, String startTime,String timeBucket);
	 
	 /**
	  * Description:根据项目ID获取BidRoom
	  * @param projectId
	  * @return
	  * Created at 2011-4-25 15:32 by zhouzhanghe
	  */
	 public List<BidRoom> getBidRoomByProjectId(String projectId);
	 
	 /** 
	  * FuncName : getBidRoomByProjectIdAndRoomType
	  * Description :  根据
	  * Create Date: 2011-10-24下午05:35:10 by yangx  
	  * Modified Date: 2011-10-24下午05:35:10 by yangx
	  * @param   projectId：项目Id,type：房间类型
	  * @return  BidRoom
	  * @Exception   
	  */
	public BidRoom getBidRoomByProjectIdAndRoomType(String projectId,String type);
}
