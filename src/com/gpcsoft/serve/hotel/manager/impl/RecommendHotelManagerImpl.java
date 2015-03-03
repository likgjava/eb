package com.gpcsoft.serve.hotel.manager.impl;

import org.springframework.stereotype.Repository;

import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.serve.hotel.domain.RecommendHotel;
import com.gpcsoft.serve.hotel.manager.RecommendHotelManager;

@Repository
public class RecommendHotelManagerImpl extends BaseManagerImpl<RecommendHotel> implements RecommendHotelManager {

//	@Autowired(required=true)  @Qualifier("recommendHotelDaoHibernate")
//	private RecommendHotelDao recommendHotelDaoHibernate;

}
