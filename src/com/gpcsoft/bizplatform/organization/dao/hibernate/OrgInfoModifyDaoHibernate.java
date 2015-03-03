package com.gpcsoft.bizplatform.organization.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.gpcsoft.bizplatform.organization.dao.OrgInfoModifyDao;
import com.gpcsoft.bizplatform.organization.domain.OrgInfoModify;
import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;

@Repository
public class OrgInfoModifyDaoHibernate extends BaseGenericDaoHibernate<OrgInfoModify> implements OrgInfoModifyDao {

	/** 
	 * Description :  根据机构ID获取变更的记录
	 * Create Date: 2010-7-28上午09:39:54 by sunl  Modified Date: 2010-7-28上午09:39:54 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<OrgInfoModify> getOrgInfoModifyList(String orgId) throws Exception {
		return this.findbyHql("from OrgInfoModify m where m.orgInfo.objId=?", new Object[]{orgId});
	}
	
	/** 
	 * Description :  获取变更待审核的机构
	 * Create Date: 2010-7-28上午09:39:54 by sunl  Modified Date: 2010-7-28上午09:39:54 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<OrgInfoModify> getOrgInfoAuditList(String orgId) throws Exception {
		StringBuilder hql = new StringBuilder();
		hql.append("from OrgInfoModify t left join fetch t.orgInfo where 1=1 and t.auditStatus='");
		hql.append(OrganizationEnum.AWAIT_EXAM).append("'");
		if(StringUtils.hasLength(orgId)) {
			hql.append(" and t.orgInfo.objId =:orgId");
		}
		Query query = this.getSession().createQuery(hql.toString());
		if(StringUtils.hasLength(orgId)) {
			query.setString("orgId", orgId);
		}
		return query.list();
	}
}
