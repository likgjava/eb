package com.gpcsoft.epp.oppugnrequisition.dao;

import java.util.List;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.epp.oppugnrequisition.domain.OppugnReply;

public interface OppugnReplyDao extends BaseGenericDao<OppugnReply> {
	/** 
	 * Description :  根据质疑获取回复
	 * Create Date: 2010-6-24下午06:44:39 by sunl  Modified Date: 2010-6-24下午06:44:39 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<OppugnReply> getReplyByOppId(String oppId) throws Exception;
}
