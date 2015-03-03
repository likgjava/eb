package com.gpcsoft.epp.negotationrecord.manager.impl;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.epp.negotationrecord.dao.NegotationRecordItemDao;
import com.gpcsoft.epp.negotationrecord.domain.NegotationRecordItem;
import com.gpcsoft.epp.negotationrecord.manager.NegotationRecordItemManager;

@Repository
public class NegotationRecordItemManagerImpl extends BaseManagerImpl<NegotationRecordItem> implements NegotationRecordItemManager {

	@Autowired(required=true)  @Qualifier("negotationRecordItemDaoHibernate")
	private NegotationRecordItemDao negotationRecordItemDaoHibernate;

}
