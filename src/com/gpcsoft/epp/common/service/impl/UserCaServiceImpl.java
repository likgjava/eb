package com.gpcsoft.epp.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.epp.common.manager.UserCaManager;
import com.gpcsoft.epp.common.service.UserCaService;
import com.gpcsoft.srplatform.auth.domain.User;

@Service 
public class UserCaServiceImpl extends BaseGenericServiceImpl implements UserCaService{
	
	@Autowired(required=true) @Qualifier("userCaManagerImpl")
	private UserCaManager userCaManager;
	
	/** 
	 * Description :  验证用户的证书信息
	 * Create Date: 2010-8-18上午10:27:14 by yangx  Modified Date: 2010-8-18上午10:27:14 by yangx
	 * @param   user 	用户对象
	 * @param   usrCaSn 证书信息
	 * @return  
	 * @Exception   
	 */
	public boolean checkCA(User user,String usrCaSn){
		return userCaManager.checkCA(user, usrCaSn);
	}
}
