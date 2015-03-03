package com.gpcsoft.serve.hotel.manager.impl;

import org.springframework.stereotype.Repository;

import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.serve.hotel.domain.Hotel;
import com.gpcsoft.serve.hotel.manager.HotelManager;

@Repository
public class HotelManagerImpl extends BaseManagerImpl<Hotel> implements HotelManager {

//	@Autowired(required=true)  @Qualifier("hotelDaoHibernate")
//	private HotelDao hotelDaoHibernate;

}
