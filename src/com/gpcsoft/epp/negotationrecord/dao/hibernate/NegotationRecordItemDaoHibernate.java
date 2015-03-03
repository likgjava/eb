package com.gpcsoft.epp.negotationrecord.dao.hibernate;

import java.util.List;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.epp.negotationrecord.dao.NegotationRecordItemDao;
import com.gpcsoft.epp.negotationrecord.domain.NegotationRecordItem;

import org.springframework.stereotype.Repository;

@Repository
public class NegotationRecordItemDaoHibernate extends BaseGenericDaoHibernate<NegotationRecordItem> implements NegotationRecordItemDao {
	/**
	 * 
	 * Description :根据谈判记录查找谈判记录明细 
	 * Create Date: 2010-11-3下午08:24:32 by liuke  Modified Date: 2010-11-3下午08:24:32 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<NegotationRecordItem> getNegotationRecordItembyNegotationRecordId(
			String negotationRecordId) {
	    List<NegotationRecordItem> list = this.findbyHql("from NegotationRecordItem t where t.negotationRecord.objId = ?", negotationRecordId);
		return list;
	}
	
	
}
