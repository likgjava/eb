package com.gpcsoft.epp.negotationrecord.service.impl;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.epp.negotationrecord.domain.NegotationRecordItem;
import com.gpcsoft.epp.negotationrecord.manager.NegotationRecordItemManager;
import com.gpcsoft.epp.negotationrecord.service.NegotationRecordItemService;

@Service 
public class NegotationRecordItemServiceImpl extends BaseGenericServiceImpl<NegotationRecordItem> implements NegotationRecordItemService {

	@Autowired(required=true) @Qualifier("negotationRecordItemManagerImpl")
	private NegotationRecordItemManager negotationRecordItemManager;

}
