package com.gpcsoft.serve.hotel.service;
import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.serve.hotel.domain.MeetingRoom;

public interface MeetingRoomService extends BaseGenericService<MeetingRoom> {

	/** 
	 * Description :  保存会议室信息
	 * Create Date: 2010-12-7上午10:28:21 by guoyr  Modified Date: 2010-12-7上午10:28:21 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public MeetingRoom saveMeetingRoom(MeetingRoom meetingRoom)throws Exception;
	
	/** 
	 * Description :  验证会议室编号的唯一性
	 * Create Date: 2010-12-8下午04:13:18 by guoyr  Modified Date: 2010-12-8下午04:13:18 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public boolean isUnique(String objId,String hotelId,String meetingRoomCode)throws Exception;
	
	/** 
	 * Description : 将会议室的类型和人数冗余到酒店里，便于检索 
	 * Create Date: 2010-12-9下午04:17:44 by guoyr  Modified Date: 2010-12-9下午04:17:44 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void updateMeetingRoomType(String hotelId)throws Exception;
}
