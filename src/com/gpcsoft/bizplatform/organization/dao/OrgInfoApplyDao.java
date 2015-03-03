package com.gpcsoft.bizplatform.organization.dao;

import java.util.List;

import com.gpcsoft.bizplatform.organization.domain.OrgInfoApply;
import com.gpcsoft.core.dao.BaseGenericDao;

public interface OrgInfoApplyDao extends BaseGenericDao<OrgInfoApply> {

	/** 
	 * Description :  获取申请的机构角色
	 * Create Date: 2010-7-28上午09:39:54 by sunl  Modified Date: 2010-7-28上午09:39:54 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<OrgInfoApply> getApplyOrgList(String orgId,String applyType) throws Exception;
}
