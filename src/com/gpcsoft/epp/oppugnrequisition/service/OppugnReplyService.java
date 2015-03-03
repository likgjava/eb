package com.gpcsoft.epp.oppugnrequisition.service;
import java.util.List;

import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.epp.oppugnrequisition.domain.OppugnReply;

public interface OppugnReplyService extends BaseGenericService<OppugnReply> {
	
	/** 
	 * Description :  根据质疑获取回复
	 * Create Date: 2010-6-24下午06:44:39 by sunl  Modified Date: 2010-6-24下午06:44:39 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<OppugnReply> getReplyByOppId(String oppId) throws Exception;
	/**
	 * 
	 * Description :  保存回复对象
	 * Create Date: 2010-9-7上午09:41:39 by shenjianzhuang  Modified Date: 2010-9-7上午09:41:39 by shenjianzhuang
	 * @param oppugnReply
	 * @return
	 * @return  OppugnReply
	 * @Exception 
	 */	
	public OppugnReply saveOppugnReply(OppugnReply oppugnReply);
}
