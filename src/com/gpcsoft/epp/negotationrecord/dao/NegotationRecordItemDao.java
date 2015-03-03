package com.gpcsoft.epp.negotationrecord.dao;

import java.util.List;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.epp.negotationrecord.domain.NegotationRecordItem;

public interface NegotationRecordItemDao extends BaseGenericDao<NegotationRecordItem> {
	/**
	 * 
	 * Description :根据谈判记录查找谈判记录明细 
	 * Create Date: 2010-11-3下午08:24:32 by liuke  Modified Date: 2010-11-3下午08:24:32 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<NegotationRecordItem> getNegotationRecordItembyNegotationRecordId(String negotationRecordId);
}
