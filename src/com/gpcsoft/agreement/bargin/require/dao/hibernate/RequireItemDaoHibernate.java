package com.gpcsoft.agreement.bargin.require.dao.hibernate;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.agreement.bargin.require.dao.RequireItemDao;
import com.gpcsoft.agreement.bargin.require.domain.RequireItem;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;

@Repository
public class RequireItemDaoHibernate extends BaseGenericDaoHibernate<RequireItem> implements RequireItemDao {
	/** 
	 * Description :  根据项目ID获取需求条目信息
	 * Create Date: 2010-9-28上午11:15:50 by sunl  Modified Date: 2010-9-28上午11:15:50 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<RequireItem> getRequireItemsByProjId(final String projId) throws Exception {
		return (List<RequireItem>)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				StringBuilder hql = new StringBuilder();
				hql.append("from RequireItem r left join fetch r.goods left join fetch r.goods.purCategory where r.project.objId =:objId");
				Query query = session.createQuery(hql.toString());
				query.setString("objId", projId);
				return query.list();
		}});
	}
	
	/** 
	 * Description :  根据项目ID获取需求条目个数
	 * Create Date: 2010-9-28上午11:15:50 by sunl  Modified Date: 2010-9-28上午11:15:50 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Integer getRequireItemCount(final String projId) throws Exception {
		return (Integer)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				StringBuilder sql = new StringBuilder();
				sql.append("select count(*) from eps_agreement_require_item r where r.project_id =:objId ");
				Query query = session.createSQLQuery(sql.toString());
				query.setString("objId", projId);
				
				if(query.list() != null && query.list().size() > 0) {
					return ((BigDecimal)query.list().get(0)).intValue();
				}else {
					return new Integer(0);
				}
		}});
	}
	
	/** 
	 * Description :  根据项目ID删除需求条目信息
	 * Create Date: 2010-9-28上午11:15:50 by sunl  Modified Date: 2010-9-28上午11:15:50 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Integer removeRequireItemByProjId(final String projId) throws Exception {
		return (Integer)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				StringBuilder hql = new StringBuilder();
				hql.append("delete from RequireItem t where t.project.objId=:objId");
				Query query = session.createQuery(hql.toString());
				query.setString("objId", projId);
				return query.executeUpdate();
		}});
	}
	
	/** 
	 * Description :  需求条目里商品数量
	 * Create Date: 2010-9-28上午11:15:50 by sunl  Modified Date: 2010-9-28上午11:15:50 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Integer isGoodsOrDesc(String projId) throws Exception {
		StringBuilder hql = new StringBuilder();
		hql.append("select count(*) from RequireItem r where r.project.objId = ? and r.goods.objId is not null");
		Object obj = this.findbyHql(hql.toString(), projId);
		
		return ((Long)((List)obj).get(0)).intValue();
	}
}
