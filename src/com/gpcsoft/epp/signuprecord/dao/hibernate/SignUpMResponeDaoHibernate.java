package com.gpcsoft.epp.signuprecord.dao.hibernate;

import java.util.List;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.epp.signuprecord.dao.SignUpMResponeDao;
import com.gpcsoft.epp.signuprecord.domain.SignUpMRespone;
import com.gpcsoft.epp.signuprecord.domain.SignUpRespone;
import com.gpcsoft.srplatform.auth.domain.User;

import org.springframework.stereotype.Repository;

@Repository
public class SignUpMResponeDaoHibernate extends BaseGenericDaoHibernate<SignUpMRespone> implements SignUpMResponeDao {
    
	/**
	 * 
	 * Description : 根据项目得到投标响应列表 
	 * Create Date: 2010-8-13下午03:25:30 by liuke  Modified Date: 2010-8-13下午03:25:30 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List getSignUpResponeFromTableByProjectId(String projectId, OrgInfo supplier) {
		List list = this.findbyHql("select smr.signUpRespone from SignUpMRespone smr where smr.signUprecord.project.objId=? and smr.signUprecord.supplier.objId=?", projectId,supplier.getObjId());
		return list;
	}

}
