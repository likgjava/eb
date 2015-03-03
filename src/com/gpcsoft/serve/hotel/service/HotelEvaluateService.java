package com.gpcsoft.serve.hotel.service;
import java.util.Map;

import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.serve.hotel.domain.HotelEvaluate;

public interface HotelEvaluateService extends BaseGenericService<HotelEvaluate> {

	/** 
	 * Description :  保存酒店评价
	 * Create Date: 2010-12-10上午10:39:30 by yucy  Modified Date: 2010-12-10上午10:39:30 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Map<String, Object> saveHotelEvaluate(Map<String, Object> param) throws Exception;

}
