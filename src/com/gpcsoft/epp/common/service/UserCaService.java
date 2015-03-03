package com.gpcsoft.epp.common.service;

import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.srplatform.auth.domain.User;

public interface UserCaService extends BaseGenericService{
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
