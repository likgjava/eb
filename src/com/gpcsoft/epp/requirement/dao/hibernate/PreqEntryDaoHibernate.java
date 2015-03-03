package com.gpcsoft.epp.requirement.dao.hibernate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.epp.requirement.dao.PreqEntryDao;
import com.gpcsoft.epp.requirement.domain.PreqEntry;

@Repository
public class PreqEntryDaoHibernate extends BaseGenericDaoHibernate<PreqEntry> implements PreqEntryDao {

	/** 
	 * Description :  根据申报书条目ID删除对应的需求条目ID
	 * Create Date: 2010-7-10下午04:55:41 by Administrator  Modified Date: 2010-7-10下午04:55:41 by Administrator
	 * @param taskPlanSubId
	 */
	@SuppressWarnings("unchecked")
	public void removeByTaskPlanSubId(final String taskPlanSubId) {
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {	
				String hql="delete from PreqEntry c where c.taskPlanSub.objId =:taskPlanSubId";
				Query query = session.createQuery(hql);
				query.setString("taskPlanSubId", taskPlanSubId);
				return query.executeUpdate();
			}
		});
	}
	
	/* (non-Javadoc)
	 * @see com.gpcsoft.epp.requirement.dao.PreqEntryDao#getAllByTaskPlanId(java.lang.String)
	 */
	public List<PreqEntry> getAllByTaskPlanId(String objId) throws Exception {
		String hql = "from PreqEntry as p where p.taskPlan.objId = ?";
		List<PreqEntry> preqEntryList = this.findbyHql(hql, objId);
		if(null == preqEntryList){
			preqEntryList = new ArrayList<PreqEntry>();
		}
		return preqEntryList;
	}

	/**
	 * 
	 * Description :根据项目获得所有需求条目  
	 * Create Date: 2010-11-3下午04:31:45 by liuke  Modified Date: 2010-11-3下午04:31:45 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 * @Modifier zhouzhanghe 
	 * @Modified Date: 2011-6-23 14:10 
	 */
	public List<PreqEntry> getPreqEntryByprojectId(String projectId) {
		StringBuffer hql = new StringBuffer();
		hql.append("from PreqEntry t where t.taskPlanSub.objId in (");
		hql.append("select pmt.taskPlanSub from ProjectMTaskPlan pmt where pmt.tproject.objId =? )");//pmt.taskPlanSub.objId改为pmt.taskPlanSub at 2011-6-23 15:08 by zhouzhanghe
		List<PreqEntry>  list = this.findbyHql(hql.toString(), projectId);
		return list;
	}

	/**
	 * 
	 * Description :根据包组获得所有需求条目  
	 * Create Date: 2010-11-3下午04:33:07 by liuke  Modified Date: 2010-11-3下午04:33:07 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<PreqEntry> getPreqEntryBySubProjectId(String subProjectId) {
		StringBuffer hql = new StringBuffer();
		hql.append("from PreqEntry t where t.taskPlanSub.objId in (");
		hql.append("select smt.taskPlanSub.objId from SubProjectMTaskPlanSub smt where smt.project.objId =? )");
		List<PreqEntry>  list = this.findbyHql(hql.toString(), subProjectId);
		return list;
	}

}
