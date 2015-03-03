package com.gpcsoft.pubservice.application.concern.dao.hibernate;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.utils.StringUtils;
import com.gpcsoft.pubservice.application.concern.dao.ConcernDao;
import com.gpcsoft.pubservice.application.concern.domain.Concern;
import com.gpcsoft.pubservice.application.concern.enumeration.ConcernEnum;

@Repository
public class ConcernDaoHibernate extends BaseGenericDaoHibernate<Concern> implements ConcernDao {

	/** 
	 * Description : 修改关对象的所属分组和名单类型 
	 * Create Date: 2010-11-5下午05:18:42 by guoyr  Modified Date: 2010-11-5下午05:18:42 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Integer updateConcern(final String objId, final String concernGroupId, final String listType){
		
		return (Integer) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {	
				String hql="update Concern c set c.concernGroup.objId =:concernGroupId , c.listType =:listType where c.objId =:objId";
				Query query = session.createQuery(hql);
				query.setString("concernGroupId", concernGroupId);
				query.setString("listType", listType);
				query.setString("objId", objId);
				return query.executeUpdate();
			}
		});
	}

	/**
	 * 移至黑名单、移出黑名单(移至我的客户)
	 */
	@SuppressWarnings("unchecked")
	public void transferInOrOutHacker(final String objId, final String listType) {
		 getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {	
				StringBuffer hql = new StringBuffer();
				hql.append("update Concern c set c.listType = :listType where c.objId = :objId");
				Query query = session.createQuery(hql.toString());
				query.setString("listType", listType);
				query.setString("objId", objId);
				return query.executeUpdate();
			}
		});
	}

	/**
	 * 条件判断
	 */
	@SuppressWarnings("unchecked")
	public boolean isExistClient(final Map<String, Object> paramMap) {
		return (Boolean) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String belongsUserId = (String) paramMap.get("belongsUserId");
				String clientId = (String) paramMap.get("clientId");
				String hql="from Concern n where n.belongsUser.objId = :belongsUserId and n.orgInfo.objId = :clientId";
				Query query = session.createQuery(hql);
				query.setString("belongsUserId", belongsUserId);
				query.setString("clientId", clientId);
				return query.list().size()>0?true:false;
			}
		});
	}

	/** 
	 * Description :  判断机构是否为我机构的黑名单客户
	 * Create Date: 2011-7-20下午02:01:23 by likg  Modified Date: 2011-7-20下午02:01:23 by likg
	 * @param   myOrgId:我的机构id	blackOrgId:被检查的机构id
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public boolean isInBlacklist(final String myOrgId, final String blackOrgId) throws Exception {
		return (Boolean) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				StringBuilder sql = new StringBuilder();
				sql.append("select c.CONCERN_ID from ECP_PUB_CONCERN c join ECP_PUB_CONCERN_GROUP cg on c.CONCERN_GROUP = cg.CONCERN_GROUP_ID");
				sql.append(" where c.LIST_TYPE = :listType");
				sql.append(" and cg.BELONGS_ORG = :myOrgId and c.ORGINFO = :blackOrgId");
				Query query = session.createSQLQuery(sql.toString());
				query.setString("listType", ConcernEnum.HACKER_LIST);
				query.setString("myOrgId", myOrgId);
				query.setString("blackOrgId", blackOrgId);
				
				return query.list().size() > 0;
			}
		});
	}

	/** 
	 * Description :  获取指定机构或指定用户的黑名单客户的机构id
	 * Create Date: 2011-7-20下午07:30:27 by likg  Modified Date: 2011-7-20下午07:30:27 by likg
	 * @param   myOrgId:指定机构id	myUserId:指定用户id
	 * @return  List<String>:黑名单客户的机构id
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<String> getMyBlacklist(final String myOrgId, final String myUserId) throws Exception {
		return (List<String>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				StringBuilder sql = new StringBuilder();
				sql.append("select c.ORGINFO from ECP_PUB_CONCERN c");
				sql.append(" join ECP_PUB_CONCERN_GROUP cg on c.CONCERN_GROUP = cg.CONCERN_GROUP_ID");
				sql.append(" where c.LIST_TYPE = :listType");
				
				//机构过滤
				if(StringUtils.hasLength(myOrgId)) {
					sql.append(" and cg.BELONGS_ORG = '").append(myOrgId).append("' ");
				}
				//用户过滤
				if(StringUtils.hasLength(myUserId)) {
					sql.append(" and cg.BELONGS_USER = '").append(myUserId).append("' ");
				}
				
				Query query = session.createSQLQuery(sql.toString());
				query.setString("listType", ConcernEnum.HACKER_LIST);
				return query.list();
			}
		});
	}

}
