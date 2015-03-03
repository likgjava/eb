package com.gpcsoft.epp.oppugnrequisition.dao;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.oppugnrequisition.domain.OppugnReply;
import com.gpcsoft.epp.oppugnrequisition.domain.OppugnRequisition;

public interface OppugnRequisitionDao extends BaseGenericDao<OppugnRequisition> {

	/**
	 * Description :  保存质疑答复
	 * Create Date: 2010-5-24上午12:56:41 by yemx  Modified Date: 2010-5-24上午12:56:41 by yemx
	 * @param   
	 * @return  
	 * @Exception
	 */
	public void saveOppugnReply(OppugnReply oppugnReply);
	
	/** 
	 * Description :  采购人查询供应商质疑
	 * Create Date: 2010-8-1下午04:55:05 by yangx  Modified Date: 2010-8-1下午04:55:05 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Page<OppugnRequisition> getOppugnRequisitionByQO(QueryObject queryObject,Page page);
}
