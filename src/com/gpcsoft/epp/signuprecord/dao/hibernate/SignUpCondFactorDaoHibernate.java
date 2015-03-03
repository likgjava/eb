package com.gpcsoft.epp.signuprecord.dao.hibernate;

import java.util.List;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.epp.signuprecord.dao.SignUpCondFactorDao;
import com.gpcsoft.epp.signuprecord.domain.SignUpCondFactor;

import org.springframework.stereotype.Repository;

@Repository
public class SignUpCondFactorDaoHibernate extends BaseGenericDaoHibernate<SignUpCondFactor> implements SignUpCondFactorDao {
    
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
		List<SignUpCondFactor> list = this.findbyHql("from SignUpCondFactor scf where scf.project.objId =? order by scf.createTime", projectId);
		return list;
	}

}
