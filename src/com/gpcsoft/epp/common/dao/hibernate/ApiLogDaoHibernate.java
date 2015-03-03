package com.gpcsoft.epp.common.dao.hibernate;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.epp.common.dao.ApiLogDao;
import com.gpcsoft.epp.common.domain.ApiLog;

@Repository
public class ApiLogDaoHibernate extends BaseGenericDaoHibernate<ApiLog> implements ApiLogDao{

	/** 
	 * Description :  根据业务Id、交换类型查询日志记录
	 * Create Date: 2010-9-13下午02:37:26 by yangx  Modified Date: 2010-9-13下午02:37:26 by yangx
	 * @param   bizId：业务Id,apiType：交换类型
	 * @return  
	 * @Exception   
	 */
	public ApiLog getApiLogByBizIdAndApiType(String bizId,String apiType){
		String hql = "From ApiLog t where t.bizId='"+bizId+"' and t.apiType='"+apiType+"'";
		List<ApiLog> logList = this.findbyHql(hql);
		return logList!=null&&logList.size()>0?logList.get(0):null;
	}
}
