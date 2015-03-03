package com.gpcsoft.epp.oppugnrequisition.manager;

import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.epp.oppugnrequisition.domain.OppugnReply;
import com.gpcsoft.epp.oppugnrequisition.domain.OppugnRequisition;

public interface OppugnRequisitionManager extends BaseManager<OppugnRequisition> {
	
	/**
	 * Description :  保存质疑
	 * Create Date: 2010-5-19上午09:36:16 by yemx  Modified Date: 2010-5-19上午09:36:16 by yemx
	 * @param   
	 * @return  
	 * @Exception
	 */
	public void saveOppugnRequisition(OppugnRequisition oppugnRequisition);
	
	/**
	 * Description :  保存质疑答复
	 * Create Date: 2010-5-23上午08:48:13 by lic  Modified Date: 2010-5-23上午08:48:13 by lic
	 * @param   
	 * @return  
	 * @Exception
	 */
	public void saveOppugnReply(OppugnReply oppugnReply);

	/**
	 * Description :  根据查询条件获取质疑
	 * Create Date: 2010-5-19上午11:19:58 by yemx  Modified Date: 2010-5-19上午11:19:58 by yemx
	 * @param   
	 * @return  
	 * @Exception
	 */
	public OppugnRequisition getOppugnRequisitionByQueryObject(QueryObject queryObject);
	
}
