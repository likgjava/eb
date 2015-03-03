package com.gpcsoft.pubservice.application.service.dao;

import java.util.List;
import java.util.Map;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.pubservice.application.service.domain.ServiceGroup;

public interface ServiceGroupDao extends BaseGenericDao<ServiceGroup> {

	/** 
	 * Description :  获取搭配服务
	 * 根据会员级别进行过滤（会员级别为空的也应该被查询出来）
	 * Create Date: 2010-7-28上午09:39:54 by sunl  Modified Date: 2010-7-28上午09:39:54 by sunl
	 * @param   主服务ID
	 * @return  
	 * @Exception   
	 */
	public List<ServiceGroup> getServiceGroupList(Map<String,Object> params) throws Exception;
}
