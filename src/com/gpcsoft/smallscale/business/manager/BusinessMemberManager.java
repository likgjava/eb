package com.gpcsoft.smallscale.business.manager;

import java.util.List;

import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.smallscale.business.domain.BusinessMember;

public interface BusinessMemberManager extends BaseManager<BusinessMember> {

	/** 
	 * Description :  根据companyId，获得最新的orgInfo。
	 * Create Date: 2010-7-21下午05:38:03 by sunl  Modified Date: 2010-7-21下午05:38:03 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<BusinessMember> getBusinessMemberList(String orgInfoId) throws Exception;
	
	/** 
	 * Description :  商圈会员角色定时任务(如果商圈会员到期，自动删除角色)
	 * Create Date: 2010-7-21下午05:38:03 by sunl  Modified Date: 2010-7-21下午05:38:03 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void update_businessmember_orgrole() throws Exception;
}
