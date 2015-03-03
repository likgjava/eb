package com.gpcsoft.serve.hotel.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.serve.hotel.dao.GuestRoomPriceDao;
import com.gpcsoft.serve.hotel.domain.GuestRoomPrice;
import com.gpcsoft.serve.hotel.service.GuestRoomPriceService;

@Service 
public class GuestRoomPriceServiceImpl extends BaseGenericServiceImpl<GuestRoomPrice> implements GuestRoomPriceService {
//	@Autowired(required=true) @Qualifier("guestRoomPriceManagerImpl")
//	private GuestRoomPriceManager guestRoomPriceManager;
	
	@Autowired(required=true)  @Qualifier("guestRoomPriceDaoHibernate")
	private GuestRoomPriceDao guestRoomPriceDaoHibernate;

	/** 
	 * Description :  获取客房当天的协议价
	 * Create Date: 2010-12-13下午03:54:09 by likg  Modified Date: 2010-12-13下午03:54:09 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public BigDecimal getTodayAgreePrice(String guestRoomId) throws Exception {
		return guestRoomPriceDaoHibernate.getTodayAgreePrice(guestRoomId);
	}

}
