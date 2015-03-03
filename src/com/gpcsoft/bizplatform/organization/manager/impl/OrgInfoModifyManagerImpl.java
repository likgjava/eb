package com.gpcsoft.bizplatform.organization.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.bizplatform.organization.dao.OrgInfoModifyDao;
import com.gpcsoft.bizplatform.organization.domain.OrgInfoModify;
import com.gpcsoft.bizplatform.organization.manager.OrgInfoModifyManager;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;

@Repository
public class OrgInfoModifyManagerImpl extends BaseManagerImpl<OrgInfoModify> implements OrgInfoModifyManager {

	@Autowired(required=true)  @Qualifier("orgInfoModifyDaoHibernate")
	private OrgInfoModifyDao orgInfoModifyDaoHibernate;

	/** 
	 * Description :  根据机构ID获取变更的记录
	 * Create Date: 2010-7-28上午09:39:54 by sunl  Modified Date: 2010-7-28上午09:39:54 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<OrgInfoModify> getOrgInfoModifyList(String orgId) throws Exception {
		return orgInfoModifyDaoHibernate.getOrgInfoModifyList(orgId);
	}
	
	/** 
	 * Description :  获取变更的机构
	 * Create Date: 2010-7-28上午09:39:54 by sunl  Modified Date: 2010-7-28上午09:39:54 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<OrgInfoModify> getOrgInfoAuditList(String orgId) throws Exception {
		return orgInfoModifyDaoHibernate.getOrgInfoAuditList(orgId);
	}
}
