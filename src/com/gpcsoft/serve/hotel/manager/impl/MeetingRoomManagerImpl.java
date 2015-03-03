package com.gpcsoft.serve.hotel.manager.impl;

import org.springframework.stereotype.Repository;

import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.serve.hotel.domain.MeetingRoom;
import com.gpcsoft.serve.hotel.manager.MeetingRoomManager;

@Repository
public class MeetingRoomManagerImpl extends BaseManagerImpl<MeetingRoom> implements MeetingRoomManager {

//	@Autowired(required=true)  @Qualifier("meetingRoomDaoHibernate")
//	private MeetingRoomDao meetingRoomDaoHibernate;

}
