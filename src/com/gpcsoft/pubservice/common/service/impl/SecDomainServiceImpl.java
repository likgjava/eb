package com.gpcsoft.pubservice.common.service.impl;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.pubservice.common.dao.SecDomainDao;
import com.gpcsoft.pubservice.common.service.SecDomainService;

@Service 
@SuppressWarnings("unchecked")
public class SecDomainServiceImpl extends BaseGenericServiceImpl implements SecDomainService,InitializingBean {
	
	private static Map<String ,Object> cache = new HashMap<String,Object>(); //配置值缓存

	@Autowired(required = true) @Qualifier("secDomainDaoHibernate")
	private SecDomainDao secDomainDao;
	
	/** 
	 * Description :  返回orgSite对应的OrgInfo
	 * Create Date: 2011-10-11下午12:00:13 by yucy  Modified Date: 2011-10-11下午12:00:13 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void afterPropertiesSet() throws Exception {
		cache = secDomainDao.getPassSecDomainInfo();
	}
	
	/** 
	 * Description :  返回orgSite对应的OrgInfo
	 * Create Date: 2011-10-11下午12:00:13 by yucy  Modified Date: 2011-10-11下午12:00:13 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public static OrgInfo getOrgInfoByPassSite(String orgSite) throws Exception{
		return (OrgInfo)cache.get(orgSite);
	}
	
	/** 
	 * Description :  返回所有的orgSite
	 * Create Date: 2011-10-11下午12:00:13 by yucy  Modified Date: 2011-10-11下午12:00:13 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public static Map<String ,Object> getOrgInfoByPassSiteAll() throws Exception{
		return cache;
	}

	/** 
	 * Description : 刷新内存中的二级域名
	 * Create Date: 2011-10-11下午04:38:56 by yucy  Modified Date: 2011-10-11下午04:38:56 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void refreshSecondDomain() throws Exception {
		cache = secDomainDao.getPassSecDomainInfo();
	}
}