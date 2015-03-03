package com.gpcsoft.pubservice.application.message.dao;

import java.util.Map;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.pubservice.application.message.domain.Complain;

public interface ComplainDao extends BaseGenericDao<Complain> {
	
	/** 
	 * Description :  获取投诉举报列表
	 * Create Date: 2011-7-11下午07:46:19 by yucy  Modified Date: 2011-7-11下午07:46:19 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Page<Complain> getComplainList(Page<Complain> page, Map<String, Object> param) throws Exception;

}
