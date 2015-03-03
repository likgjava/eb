package com.gpcsoft.serve.hotel.manager.impl;

import org.springframework.stereotype.Repository;

import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.serve.hotel.domain.GuestRoom;
import com.gpcsoft.serve.hotel.manager.GuestRoomManager;

@Repository
public class GuestRoomManagerImpl extends BaseManagerImpl<GuestRoom> implements GuestRoomManager {

//	@Autowired(required=true)  @Qualifier("guestRoomDaoHibernate")
//	private GuestRoomDao guestRoomDaoHibernate;

}
