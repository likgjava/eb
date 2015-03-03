package com.gpcsoft.serve.hotel.dao;

import java.util.Map;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.serve.hotel.domain.Hotel;
import com.gpcsoft.serve.hotel.domain.RecommendHotel;

public interface RecommendHotelDao extends BaseGenericDao<RecommendHotel> {

	/** 
	 * Description :  获得未推荐的酒店
	 * Create Date: 2010-12-9上午11:00:40 by likg  Modified Date: 2010-12-9上午11:00:40 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Page<Hotel> getNoRecommendHotelList(Map<String, Object> param, Page<Hotel> page) throws Exception;

	/** 
	 * Description :  推荐酒店
	 * Create Date: 2010-12-9下午12:00:24 by likg  Modified Date: 2010-12-9下午12:00:24 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	void recommendHotel(String[] hotelIds, RecommendHotel recommendHotelPattern) throws Exception;

	/** 
	 * Description :  获得推荐的酒店
	 * Create Date: 2010-12-10下午12:46:29 by likg  Modified Date: 2010-12-10下午12:46:29 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Page<RecommendHotel> getRecommendHotel(Page<RecommendHotel> page, Map<String, Object> paramsMap) throws Exception;
	
	/** 
	 * Description :  修改推荐酒店的排序序号
	 * Create Date: 2010-12-22上午10:43:42 by likg  Modified Date: 2010-12-22上午10:43:42 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	void updateSort(Long sort, boolean isToUp) throws Exception;

}
