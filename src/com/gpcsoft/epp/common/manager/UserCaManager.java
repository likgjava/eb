package com.gpcsoft.epp.common.manager;

import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.srplatform.auth.domain.User;

public interface UserCaManager extends BaseManager{

	/** 
	 * Description :  验证用户的证书信息
	 * Create Date: 2010-8-18上午10:27:14 by yangx  Modified Date: 2010-8-18上午10:27:14 by yangx
	 * @param   user 	用户对象
	 * @param   usrCaSn 证书信息
	 * @return  
	 * @Exception   
	 */
	public boolean checkCA(User user,String usrCaSn);
}
