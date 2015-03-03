package com.gpcsoft.serve.hotel.dao.hibernate;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.serve.hotel.dao.GuestRoomDao;
import com.gpcsoft.serve.hotel.domain.GuestRoom;
import org.springframework.stereotype.Repository;

@Repository
public class GuestRoomDaoHibernate extends BaseGenericDaoHibernate<GuestRoom> implements GuestRoomDao {

}
