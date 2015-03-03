package com.gpcsoft.epp.buyresult.manager.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.epp.buyresult.dao.BuyWinnerDao;
import com.gpcsoft.epp.buyresult.domain.BuyWinner;
import com.gpcsoft.epp.buyresult.manager.BuyWinnerManager;

@Repository
public class BuyWinnerManagerImpl extends BaseManagerImpl<BuyWinner> implements BuyWinnerManager {

	@Autowired(required=true)  @Qualifier("buyWinnerDaoHibernate")
	private BuyWinnerDao buyWinnerDaoHibernate;

	public List<BuyWinner> getListByResultId(String resultId) {
		return buyWinnerDaoHibernate.findbyHql("select t from BuyWinner t where t.buyResult.objId=?", resultId);
	}

}
