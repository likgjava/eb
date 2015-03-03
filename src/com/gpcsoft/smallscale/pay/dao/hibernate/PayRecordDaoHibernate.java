package com.gpcsoft.smallscale.pay.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.smallscale.pay.dao.PayRecordDao;
import com.gpcsoft.smallscale.pay.domain.PayRecord;

@Repository
public class PayRecordDaoHibernate extends BaseGenericDaoHibernate<PayRecord> implements PayRecordDao {

}
