package com.gpcsoft.pubservice.application.service.service;
import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.pubservice.application.service.domain.ServiceSubscribe;

public interface ShangquanhuiyuanPrivilegeService extends BaseGenericService<ServiceSubscribe> {

	/** 
	 * Description :  当前机构是否可以查看此机构的联系方式
	 * Create Date: 2011-2-12上午09:11:34 by yucy  Modified Date: 2011-2-12上午09:11:34 by yucy
	 * @param   currentOrgInfoId 当前机构ID
	 * @param   targetOrgInfoId  被查看机构ID
	 * @return  
	 * @Exception   
	 */
	public Boolean getIsShowContact(String currentOrgInfoId,String targetOrgInfoId)throws Exception;
}
