package com.gpcsoft.epp.signuprecord.dao;

import java.util.List;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.epp.signuprecord.domain.SignUpCondFactor;

public interface SignUpCondFactorDao extends BaseGenericDao<SignUpCondFactor> {

    /**
     * 
     * Description : 根据项目主键得到符合性要求对象 
     * Create Date: 2010-8-10下午01:46:47 by liuke  Modified Date: 2010-8-10下午01:46:47 by liuke
     * @param   
     * @return  
     * @Exception
     */
	public List<SignUpCondFactor> getSignUpCondFactorListByProjectId(String projectId);
}
