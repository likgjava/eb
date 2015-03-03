package com.gpcsoft.epp.signuprecord.dao;

import java.util.List;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.epp.signuprecord.domain.SignUpMRespone;
import com.gpcsoft.srplatform.auth.domain.User;

public interface SignUpMResponeDao extends BaseGenericDao<SignUpMRespone> {
	/**
	 * 
	 * Description : 根据项目得到投标响应列表 
	 * Create Date: 2010-8-13下午03:25:30 by liuke  Modified Date: 2010-8-13下午03:25:30 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List getSignUpResponeFromTableByProjectId(String projectId,OrgInfo supplier);
}
