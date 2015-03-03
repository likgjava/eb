package com.gpcsoft.epp.common.manager.impl;

import org.springframework.stereotype.Repository;

import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.epp.common.domain.EsExceptionEnum;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.common.manager.UserCaManager;
import com.gpcsoft.srplatform.auth.domain.User;

@Repository
public class UserCaManagerImpl extends BaseManagerImpl implements UserCaManager{

	/** 
	 * Description :  验证用户的证书信息
	 * Create Date: 2010-8-18上午10:27:14 by yangx  Modified Date: 2010-8-18上午10:27:14 by yangx
	 * @param   user 	用户对象
	 * @param   usrCaSn 证书信息
	 * @return  
	 * @Exception   
	 */
	public boolean checkCA(User user,String usrCaSn){
		boolean flag = false;
		if (usrCaSn!=null&&!"".equals(usrCaSn)) {//判断是否有验证信息
			if (user!=null&&user.getUsrCaSn()!=null&&usrCaSn.equals(user.getUsrCaSn())) {//判断绑定信息与当前插入密钥的信息是否匹配
				flag = true;
			}else {
				throw new EsException(messageSource.getMessage(EsExceptionEnum.CASN_NOT_MATCH));
			}
		}else {
			throw new EsException(messageSource.getMessage(EsExceptionEnum.CASN_NOT_INSERT));
		}
		return flag;
	}
}
