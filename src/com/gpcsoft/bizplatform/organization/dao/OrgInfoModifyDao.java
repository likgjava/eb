package com.gpcsoft.bizplatform.organization.dao;

import java.util.List;

import com.gpcsoft.bizplatform.organization.domain.OrgInfoModify;
import com.gpcsoft.core.dao.BaseGenericDao;

public interface OrgInfoModifyDao extends BaseGenericDao<OrgInfoModify> {

	/** 
	 * Description :  根据机构ID获取变更的记录
	 * Create Date: 2010-7-28上午09:39:54 by sunl  Modified Date: 2010-7-28上午09:39:54 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<OrgInfoModify> getOrgInfoModifyList(String orgId) throws Exception;
	
	/** 
	 * Description :  获取变更的机构
	 * Create Date: 2010-7-28上午09:39:54 by sunl  Modified Date: 2010-7-28上午09:39:54 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<OrgInfoModify> getOrgInfoAuditList(String orgId) throws Exception;
}
