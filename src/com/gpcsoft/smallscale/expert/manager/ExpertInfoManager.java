package com.gpcsoft.smallscale.expert.manager;

import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.smallscale.expert.domain.ExpertInfo;
import com.gpcsoft.srplatform.auth.domain.Employee;
import com.gpcsoft.srplatform.auth.domain.User;

public interface ExpertInfoManager extends BaseManager<ExpertInfo> {

	/** 
	 * Description :  保存专家注册信息【用户信息，员工信息】
	 * Create Date: 2010-11-29下午05:19:38 by likg  Modified Date: 2010-11-29下午05:19:38 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	void saveExpertInfoOfRegister(User user, Employee employee) throws Exception;

}
