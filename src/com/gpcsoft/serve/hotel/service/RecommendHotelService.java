package com.gpcsoft.serve.hotel.service;
import java.util.Map;

import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.serve.hotel.domain.Hotel;
import com.gpcsoft.serve.hotel.domain.RecommendHotel;

public interface RecommendHotelService extends BaseGenericService<RecommendHotel> {

	/** 
	 * Description :  获得未推荐的酒店
	 * Create Date: 2010-12-9上午10:58:36 by likg  Modified Date: 2010-12-9上午10:58:36 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Page<Hotel> getNoRecommendHotelList(Map<String, Object> param, Page<Hotel> page) throws Exception ;

	/** 
	 * Description :  推荐酒店
	 * Create Date: 2010-12-9上午11:50:38 by likg  Modified Date: 2010-12-9上午11:50:38 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	void recommendHotel(String hotelIds, String recommendReason) throws Exception ;

	/** 
	 * Description :  修改推荐酒店的排序序号
	 * Create Date: 2010-12-9下午01:05:51 by likg  Modified Date: 2010-12-9下午01:05:51 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	void updateSort(String sourceObjId, boolean isToUp) throws Exception;
	
	/** 
	 * Description :  获得推荐酒店
	 * Create Date: 2010-12-10下午12:45:16 by likg  Modified Date: 2010-12-10下午12:45:16 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Page<RecommendHotel> getRecommendHotel(Page<RecommendHotel> page, Map<String, Object> paramsMap) throws Exception;

}
