package com.gpcsoft.serve.hotel.service;
import java.math.BigDecimal;

import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.serve.hotel.domain.GuestRoomPrice;

public interface GuestRoomPriceService extends BaseGenericService<GuestRoomPrice> {

	/** 
	 * Description :  获取客房当天的协议价
	 * Create Date: 2010-12-13下午03:54:09 by likg  Modified Date: 2010-12-13下午03:54:09 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	BigDecimal getTodayAgreePrice(String guestRoomId) throws Exception;

}
