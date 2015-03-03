package com.gpcsoft.epp.negotationrecord.manager.impl;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.epp.negotationrecord.dao.NegotationRecordDao;
import com.gpcsoft.epp.negotationrecord.domain.NegotationRecord;
import com.gpcsoft.epp.negotationrecord.manager.NegotationRecordManager;

@Repository
public class NegotationRecordManagerImpl extends BaseManagerImpl<NegotationRecord> implements NegotationRecordManager {

	@Autowired(required=true)  @Qualifier("negotationRecordDaoHibernate")
	private NegotationRecordDao negotationRecordDaoHibernate;

}
