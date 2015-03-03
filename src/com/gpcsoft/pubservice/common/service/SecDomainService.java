package com.gpcsoft.pubservice.common.service;
import com.gpcsoft.core.service.BaseGenericService;

@SuppressWarnings("unchecked")
public interface SecDomainService extends BaseGenericService{

	/** 
	 * Description : 刷新内存中的二级域名
	 * Create Date: 2011-10-11下午04:38:56 by yucy  Modified Date: 2011-10-11下午04:38:56 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	void refreshSecondDomain() throws Exception;

}