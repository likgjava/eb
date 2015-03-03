package com.gpcsoft.pubservice.application.message.manager.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.pubservice.application.message.dao.ComplainDao;
import com.gpcsoft.pubservice.application.message.manager.ComplainManager;
import com.gpcsoft.pubservice.application.message.domain.Complain;

@Repository
public class ComplainManagerImpl extends BaseManagerImpl<Complain> implements ComplainManager {

	@Autowired(required=true)  @Qualifier("complainDaoHibernate")
	private ComplainDao complainDaoHibernate;

	/** 
	 * Description :  查询投诉列表
	 * Create Date: 2011-7-11下午04:40:14 by yucy  Modified Date: 2011-7-11下午04:40:14 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Page<Complain> getComplainList(Page<Complain> page, Map<String, Object> paramsMap) throws Exception {
		return complainDaoHibernate.getComplainList(page, paramsMap);
	}

}
