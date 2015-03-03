package com.gpcsoft.agreement.bargin.project.dao.hibernate;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.agreement.bargin.project.dao.ProjectTaskItemDao;
import com.gpcsoft.agreement.bargin.project.domain.ProjectTaskItem;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;

@Repository
public class ProjectTaskItemDaoHibernate extends BaseGenericDaoHibernate<ProjectTaskItem> implements ProjectTaskItemDao {

	/** 
	 * Description :  根据项目Id删除项目与任务书的关联数据
	 * Create Date: 2011-12-7上午10:20:16 by likg  Modified Date: 2011-12-7上午10:20:16 by likg
	 * @param   projectId：项目Id
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public void deleteByProjectId(final String projectId) throws Exception {
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String hql = "delete from ProjectTaskItem pt where pt.project.objId = :projectId";
				Query query = session.createQuery(hql);
				query.setParameter("projectId", projectId);
				query.executeUpdate();
				return null;
			}
		});
	}
}
