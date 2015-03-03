package com.gpcsoft.serve.hotel.service.impl;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.serve.hotel.dao.RecommendHotelDao;
import com.gpcsoft.serve.hotel.domain.Hotel;
import com.gpcsoft.serve.hotel.domain.RecommendHotel;
import com.gpcsoft.serve.hotel.manager.RecommendHotelManager;
import com.gpcsoft.serve.hotel.service.RecommendHotelService;

@Service 
public class RecommendHotelServiceImpl extends BaseGenericServiceImpl<RecommendHotel> implements RecommendHotelService {

	@Autowired(required=true) @Qualifier("recommendHotelManagerImpl")
	private RecommendHotelManager recommendHotelManager;
	
	@Autowired(required=true)  @Qualifier("recommendHotelDaoHibernate")
	private RecommendHotelDao recommendHotelDaoHibernate;

	/** 
	 * Description :  获得未推荐的酒店
	 * Create Date: 2010-12-9上午10:58:36 by likg  Modified Date: 2010-12-9上午10:58:36 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Page<Hotel> getNoRecommendHotelList(Map<String, Object> param, Page<Hotel> page) throws Exception {
		return recommendHotelDaoHibernate.getNoRecommendHotelList(param, page);
	}

	/** 
	 * Description :  推荐酒店
	 * Create Date: 2010-12-9上午11:50:38 by likg  Modified Date: 2010-12-9上午11:50:38 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void recommendHotel(String hotelIds, String recommendReason) throws Exception {
		RecommendHotel recommendHotelPattern = new RecommendHotel();
		recommendHotelPattern.setReason(recommendReason);
		recommendHotelPattern.setAuditStatus("00");
		recommendHotelPattern.setUseStatus(false);
		recommendHotelPattern.setCreateTime(new Date());
		recommendHotelPattern.setSort(0L);
		
		recommendHotelDaoHibernate.recommendHotel(hotelIds.split(","), recommendHotelPattern);
	}

	/** 
	 * Description :  修改推荐酒店的排序序号
	 * Create Date: 2010-12-9下午01:05:51 by likg  Modified Date: 2010-12-9下午01:05:51 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void updateSort(String sourceObjId, boolean isToUp) throws Exception {
		RecommendHotel sourceRecommendHotel = recommendHotelManager.get(sourceObjId);
		recommendHotelDaoHibernate.updateSort(sourceRecommendHotel.getSort(), isToUp);
	}
	
	/** 
	 * Description :  获得推荐酒店
	 * Create Date: 2010-12-10下午12:45:16 by likg  Modified Date: 2010-12-10下午12:45:16 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Page<RecommendHotel> getRecommendHotel(Page<RecommendHotel> page, Map<String, Object> paramsMap) throws Exception 
	{
		return recommendHotelDaoHibernate.getRecommendHotel(page, paramsMap);
	}

}
