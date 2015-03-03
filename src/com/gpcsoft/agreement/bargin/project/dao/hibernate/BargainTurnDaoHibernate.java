package com.gpcsoft.agreement.bargin.project.dao.hibernate;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.agreement.bargin.project.dao.BargainTurnDao;
import com.gpcsoft.agreement.bargin.project.domain.BargainTurn;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.utils.DateUtil;
import com.gpcsoft.srplatform.common.utils.unifysql.UnifyDBSqlFactory;

@Repository
public class BargainTurnDaoHibernate extends BaseGenericDaoHibernate<BargainTurn> implements BargainTurnDao {
	
	/** 
	 * Description :  根据项目ID获取轮次信息
	 * Create Date: 2010-9-28上午11:15:50 by sunl  Modified Date: 2010-9-28上午11:15:50 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<BargainTurn> getBargainTurnByProjId(String projId) throws Exception {
		StringBuilder hql = new StringBuilder();
		hql.append("from BargainTurn r left join fetch r.project where r.project.objId =:objId order by r.turnNo");
		
		Query query = this.getSession().createQuery(hql.toString());
		query.setString("objId", projId);
		
		return query.list();
	}
	
	/** 
	 * Description :  根据项目ID获取当前时间所属轮次
	 * Create Date: 2010-9-28上午11:15:50 by sunl  Modified Date: 2010-9-28上午11:15:50 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public BargainTurn getCurrentBargainTurn(final String projId) throws Exception {
		return (BargainTurn) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException { 
				StringBuilder hql = new StringBuilder();
				hql.append(" from BargainTurn t where 1=1 ");
				hql.append(" and t.project.objId =:projId ");
				hql.append(" and t.startTime < ").append(UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2DateTime(DateUtil.getCurDateTime()));
				hql.append(" and t.endTime > ").append(UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2DateTime(DateUtil.getCurDateTime()));
				
				Query query = session.createQuery(hql.toString());
				query.setString("projId", projId);
				List res = query.list();
				if(res != null && res.size() > 0){
					return query.list().get(0);
				} else {
					return null;
				}
		}});
	}
	
	/** 
	 * Description :  根据项目ID获取下一个轮次
	 * Create Date: 2011-7-4下午06:26:27 by likg  Modified Date: 2011-7-4下午06:26:27 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public BargainTurn getNextBargainTurn(final String projId) throws Exception {
		return (BargainTurn) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException { 
				StringBuilder hql = new StringBuilder();
				hql.append(" from BargainTurn t where 1=1 ");
				hql.append(" and t.project.objId =:projId ");
				hql.append(" and t.startTime > ").append(UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2DateTime(DateUtil.getCurDateTime()));
				hql.append(" order by t.startTime asc ");
				
				Query query = session.createQuery(hql.toString());
				query.setString("projId", projId);
				List res = query.list();
				if(res != null && res.size() > 0) {
					return query.list().get(0);
				} else {
					return null;
				}
			}
		});
	}
	
	/** 
	 * Description :  根据项目ID删除轮次信息
	 * Create Date: 2010-9-28上午11:15:50 by sunl  Modified Date: 2010-9-28上午11:15:50 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Integer removeBargainTurnByProjId(final String projId) throws Exception {
		return (Integer) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String hql="delete from BargainTurn b where b.project.objId =:objId";
				Query query = session.createQuery(hql);
				query.setString("objId", projId);
				return query.executeUpdate();
		}});
	}
	
	/** 
	 * Description :  根据轮次ID获取降幅
	 * Create Date: 2010-9-28上午11:15:50 by sunl  Modified Date: 2010-9-28上午11:15:50 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Object[] getTotalCut(String turnId) throws Exception {
		StringBuilder hql = new StringBuilder();
		hql.append("select t.totalCutType,t.totalCut from BargainTurn t where t.objId = ?");
		List list = this.findbyHql(hql.toString(), turnId);
		Object[] obj = (Object[])list.get(0);
		return obj;
	}
}
