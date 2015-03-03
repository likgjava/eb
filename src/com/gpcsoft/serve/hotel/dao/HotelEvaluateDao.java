package com.gpcsoft.serve.hotel.dao;

import java.util.Map;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.serve.hotel.domain.HotelEvaluate;

public interface HotelEvaluateDao extends BaseGenericDao<HotelEvaluate> {

	/** 
	 * Description :是否已经评价  
	 * Create Date: 2010-12-10上午10:41:47 by yucy  Modified Date: 2010-12-10上午10:41:47 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Long hasHotelEvaluate(Map<String, Object> param) throws Exception;

}
