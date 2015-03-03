package com.gpcsoft.agreement.bargin.buyresult.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.agreement.bargin.buyresult.dao.BuyWinnerDaoXygh;
import com.gpcsoft.agreement.bargin.buyresult.service.BuyWinnerServiceXygh;
import com.gpcsoft.epp.buyresult.service.impl.BuyWinnerServiceImpl;

@Service 
public class BuyWinnerServiceXyghImpl extends BuyWinnerServiceImpl implements BuyWinnerServiceXygh{

	@SuppressWarnings("unused")
	@Autowired(required=true) @Qualifier("buyWinnerDaoXyghHibernate")
	private BuyWinnerDaoXygh buyWinnerDaoXyghHibernate;
	

}
