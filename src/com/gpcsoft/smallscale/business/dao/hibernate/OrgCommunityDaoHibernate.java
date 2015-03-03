package com.gpcsoft.smallscale.business.dao.hibernate;

import java.sql.SQLException;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.smallscale.business.dao.OrgCommunityDao;
import com.gpcsoft.smallscale.business.domain.Community;
import com.gpcsoft.smallscale.business.domain.OrgCommunity;

@Repository
public class OrgCommunityDaoHibernate extends BaseGenericDaoHibernate<OrgCommunity> implements OrgCommunityDao {

	/** 
	 * Description :  加入社区
	 * Create Date: 2011-10-21上午11:16:28 by yucy  Modified Date: 2011-10-21上午11:16:28 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Boolean createOrgCommunity(final Map<String, Object> param) throws Exception {
		return (Boolean) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				//加入的id
				String[] joinIds = (String [])param.get("joinIds");
				//加入的机构id
				String orgId =  (String )param.get("orgId");
				if(joinIds!=null){
					for(String communityId : joinIds){
						if(session.createSQLQuery(" select o.org_community_id from ecp_business_org_community  o where o.org_info_id = '"+orgId+"' and o.community_id = '"+communityId+"' ").list().size()<=0){
							OrgInfo orgInfo = new OrgInfo();orgInfo.setObjId(orgId);
							Community community = new Community(); community.setObjId(communityId);
							OrgCommunity orgCommunity = new OrgCommunity();
							orgCommunity.setOrgInfo(orgInfo);
							orgCommunity.setCommunity(community);
							save(orgCommunity);
						}
					}
				}
				return true;
		}});
	}

}
