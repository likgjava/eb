package com.gpcsoft.epp.signuprecord.service.impl;

import java.util.List;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.epp.signuprecord.dao.SignUpCondFactorDao;
import com.gpcsoft.epp.signuprecord.domain.SignUpCondFactor;
import com.gpcsoft.epp.signuprecord.service.SignUpCondFactorService;

@Service 
public class SignUpCondFactorServiceImpl extends BaseGenericServiceImpl<SignUpCondFactor> implements SignUpCondFactorService {
	@Autowired(required=true)  @Qualifier("signUpCondFactorDaoHibernate")
	private SignUpCondFactorDao signUpCondFactorDaoHibernate;
    /**
     * 
     * Description : 根据项目主键得到符合性要求对象 
     * Create Date: 2010-8-10下午01:46:47 by liuke  Modified Date: 2010-8-10下午01:46:47 by liuke
     * @param   
     * @return  
     * @Exception
     */
	public List<SignUpCondFactor> getSignUpCondFactorListByProjectId(
			String projectId) {
		logger.debug("\nes SignUpCondFactorServiceImpl||getSignUpCondFactorListByProjectId\n");
		return signUpCondFactorDaoHibernate.getSignUpCondFactorListByProjectId(projectId);
	}
	/**
	 * 
	 * Description :根据主键获得符合性要求对象   
	 * Create Date: 2010-9-7上午09:23:28 by liuke  Modified Date: 2010-9-7上午09:23:28 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public SignUpCondFactor getSignUpCondFactorByObjId(String objId) {
		logger.debug("\nes SignUpCondFactorServiceImpl||getSignUpCondFactorByObjId\n");
		return signUpCondFactorDaoHibernate.get(objId);
	}
	
	
	/**
	 * 
	 * Description :根据主键删除报名指标 
	 * Create Date: 2010-9-7上午10:07:00 by liuke  Modified Date: 2010-9-7上午10:07:00 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public void removeSignUpCondFactorByObjId(String objId) {
		logger.debug("\nes SignUpCondFactorServiceImpl||removeSignUpCondFactorByObjId\n");
		signUpCondFactorDaoHibernate.remove(objId, SignUpCondFactor.class);
	}
	
	/**
	 * 
	 * Description :保存报名指标   
	 * Create Date: 2010-9-7上午10:11:50 by liuke  Modified Date: 2010-9-7上午10:11:50 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public SignUpCondFactor saveSignUpCondFactor(
			SignUpCondFactor signUpCondFactor) {
		 logger.debug("\nes SignUpCondFactorServiceImpl||saveSignUpCondFactor\n");
		 signUpCondFactorDaoHibernate.save(signUpCondFactor);
		 return signUpCondFactor;
	}

}
