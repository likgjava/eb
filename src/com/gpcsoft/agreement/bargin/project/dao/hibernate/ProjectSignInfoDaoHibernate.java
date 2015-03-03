package com.gpcsoft.agreement.bargin.project.dao.hibernate;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.agreement.bargin.project.dao.ProjectSignInfoDao;
import com.gpcsoft.agreement.bargin.project.domain.ProjectSignInfo;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;

@Repository
public class ProjectSignInfoDaoHibernate extends BaseGenericDaoHibernate<ProjectSignInfo> implements ProjectSignInfoDao {

	/** 
	 * Description :  取得信息（by projectID）
	 * Create Date: 2010-12-2上午11:34:00 by yucy  Modified Date: 2010-12-2上午11:34:00 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public ProjectSignInfo getSignInfoByProjectId(final String projectId) throws Exception {
		return (ProjectSignInfo) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
					String Hql = " from ProjectSignInfo where project.objId = :projectId ";
					Query query = session.createQuery(Hql);
					query.setParameter("projectId",projectId );
					List list = query.list();
					return list.size()>0?list.get(0):null;
			}
		});
	}
	
	/** 
	 * Description :  根据项目ID删除扩展信息
	 * Create Date: 2010-9-28上午11:15:50 by sunl  Modified Date: 2010-9-28上午11:15:50 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Integer removeSignInfoByProjId(final String projId) throws Exception {
		return (Integer)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				StringBuilder hql = new StringBuilder();
				hql.append("delete from ProjectSignInfo t where t.project.objId=:objId");
				Query query = session.createQuery(hql.toString());
				query.setString("objId", projId);
				return query.executeUpdate();
		}});
	}
}
