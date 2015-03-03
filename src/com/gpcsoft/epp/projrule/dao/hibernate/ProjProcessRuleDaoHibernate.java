package com.gpcsoft.epp.projrule.dao.hibernate;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.epp.projrule.dao.ProjProcessRuleDao;
import com.gpcsoft.epp.projrule.domain.ProjProcessRule;

@Repository
public class ProjProcessRuleDaoHibernate extends BaseGenericDaoHibernate<ProjProcessRule> implements ProjProcessRuleDao {
	/** 
	 * Description :  根据项目Id删除规则
	 * Create Date: 2010-10-18下午04:56:32 by yangx  Modified Date: 2010-10-18下午04:56:32 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public void removeProjProcessRuleByProjectId(final String projectId){
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
				String hql = "delete ProjProcessRule where project.objId='"+projectId+"'";
				return session.createQuery(hql.toString()).executeUpdate();
			}});
	}
	/** 
	 * Description :  根据项目Id,规则编号获取项目规则
	 * Create Date: 2010-11-2下午03:52:59 by yangx  Modified Date: 2010-11-2下午03:52:59 by yangx
	 * @param   projectId：项目Id,code：规则编号
	 * @return  ProjProcessRule
	 * @Exception   
	 */
	public ProjProcessRule getProjProcessRuleByProjectIdAndCode(String projectId,String code){
		StringBuffer hql = new StringBuffer();
		hql.append("From ProjProcessRule t where t.project.objId='"+projectId+"' and t.code='"+code+"'");
		List<ProjProcessRule> projProcessRule = this.findbyHql(hql.toString());
		return (projProcessRule!=null&&projProcessRule.size()>0)?projProcessRule.get(0):null;
	}
}
