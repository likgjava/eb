package com.gpcsoft.agreement.bargin.bidding.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.gpcsoft.agreement.bargin.bidding.dao.BiddingRecordItemDao;
import com.gpcsoft.agreement.bargin.bidding.domain.BiddingRecordItem;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;

@Repository
public class BiddingRecordItemDaoHibernate extends BaseGenericDaoHibernate<BiddingRecordItem> implements BiddingRecordItemDao {

}
