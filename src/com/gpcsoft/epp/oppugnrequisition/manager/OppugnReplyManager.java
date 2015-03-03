package com.gpcsoft.epp.oppugnrequisition.manager;

import java.util.List;

import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.epp.oppugnrequisition.domain.OppugnReply;

public interface OppugnReplyManager extends BaseManager<OppugnReply> {

	/** 
	 * Description :  根据质疑获取回复
	 * Create Date: 2010-6-24下午06:44:39 by sunl  Modified Date: 2010-6-24下午06:44:39 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<OppugnReply> getReplyByOppId(String oppId) throws Exception;
}
