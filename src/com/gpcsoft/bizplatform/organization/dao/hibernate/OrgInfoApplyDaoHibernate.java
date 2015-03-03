package com.gpcsoft.bizplatform.organization.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.gpcsoft.bizplatform.organization.dao.OrgInfoApplyDao;
import com.gpcsoft.bizplatform.organization.domain.OrgInfoApply;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;

@Repository
public class OrgInfoApplyDaoHibernate extends BaseGenericDaoHibernate<OrgInfoApply> implements OrgInfoApplyDao {

	/** 
	 * Description :  获取申请的机构角色
	 * Create Date: 2010-7-28上午09:39:54 by sunl  Modified Date: 2010-7-28上午09:39:54 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<OrgInfoApply> getApplyOrgList(String orgId,String applyType) throws Exception {
		StringBuilder hql = new StringBuilder();
		hql.append("from OrgInfoApply t left join fetch t.orgInfo where 1=1 ");
		if(StringUtils.hasLength(orgId)) {
			hql.append(" and t.orgInfo.objId =:orgId");
		}
		if(StringUtils.hasLength(applyType)) {
			hql.append(" and t.applyType =:applyType");
		}
		Query query = this.getSession().createQuery(hql.toString());
		if(StringUtils.hasLength(orgId)) {
			query.setString("orgId", orgId);
		}
		if(StringUtils.hasLength(applyType)) {
			query.setString("applyType", applyType);
		}
		return query.list();
	}
}
