package com.gpcsoft.serve.hotel.manager.impl;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.serve.hotel.dao.HotelEvaluateDao;
import com.gpcsoft.serve.hotel.manager.HotelEvaluateManager;
import com.gpcsoft.serve.hotel.domain.HotelEvaluate;

@Repository
public class HotelEvaluateManagerImpl extends BaseManagerImpl<HotelEvaluate> implements HotelEvaluateManager {

	@SuppressWarnings("unused")
	@Autowired(required=true)  @Qualifier("hotelEvaluateDaoHibernate")
	private HotelEvaluateDao hotelEvaluateDaoHibernate;

}
