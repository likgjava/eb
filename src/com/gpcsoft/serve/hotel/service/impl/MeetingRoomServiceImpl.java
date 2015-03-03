package com.gpcsoft.serve.hotel.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.serve.hotel.dao.MeetingRoomDao;
import com.gpcsoft.serve.hotel.domain.MeetingRoom;
import com.gpcsoft.serve.hotel.service.MeetingRoomService;

@Service 
public class MeetingRoomServiceImpl extends BaseGenericServiceImpl<MeetingRoom> implements MeetingRoomService {

//	@Autowired(required=true) @Qualifier("meetingRoomManagerImpl")
//	private MeetingRoomManager meetingRoomManager;

	@Autowired(required=true)  @Qualifier("meetingRoomDaoHibernate")
	private MeetingRoomDao meetingRoomDaoHibernate;
	/** 
	 * Description :  保存会议室信息
	 * Create Date: 2010-12-7上午10:28:21 by guoyr  Modified Date: 2010-12-7上午10:28:21 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public MeetingRoom saveMeetingRoom(MeetingRoom meetingRoom)throws Exception{
		meetingRoomDaoHibernate.save(meetingRoom);
		meetingRoomDaoHibernate.updateMeetingRoomType(meetingRoom.getHotel().getObjId());
		return meetingRoom;
	}
	
	/** 
	 * Description :  验证会议室编号的唯一性
	 * Create Date: 2010-12-8下午04:13:18 by guoyr  Modified Date: 2010-12-8下午04:13:18 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public boolean isUnique(String objId,String hotelId,String meetingRoomCode)throws Exception{
		return meetingRoomDaoHibernate.isUnique(objId, hotelId, meetingRoomCode);
	}
	
	/** 
	 * Description : 将会议室的类型和人数冗余到酒店里，便于检索 
	 * Create Date: 2010-12-9下午04:17:44 by guoyr  Modified Date: 2010-12-9下午04:17:44 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void updateMeetingRoomType(String hotelId)throws Exception{
		meetingRoomDaoHibernate.updateMeetingRoomType(hotelId);
	}

}
