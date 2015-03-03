package com.gpcsoft.bizplatform.agency.manager;

import com.gpcsoft.bizplatform.agency.domain.Agency;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.manager.BaseManager;

public interface AgencyManager extends BaseManager<Agency>   {

	/** 
	 * Description : 更新代理机构审核状态  
	 * Create Date: 2010-7-28下午02:50:44 by sunl  Modified Date: 2010-7-28下午02:50:44 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Integer updateAgencyStatus(String agencyId,OrgInfo orgInfo) throws Exception;
	
	/** 
	 * Description :  保存或更新代理机构扩展信息
	 * 				  传入参数为agencyId和表单对象agency
	 * Create Date: 2010-7-26下午09:03:47 by sunl  Modified Date: 2010-7-26下午09:03:47 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Agency saveAgency(boolean isSave,Agency agency) throws Exception;
}
