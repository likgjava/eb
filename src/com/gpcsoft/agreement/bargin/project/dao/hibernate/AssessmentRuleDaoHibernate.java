package com.gpcsoft.agreement.bargin.project.dao.hibernate;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.agreement.bargin.project.dao.AssessmentRuleDao;
import com.gpcsoft.agreement.bargin.project.domain.AssessmentRule;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;

@Repository
public class AssessmentRuleDaoHibernate extends BaseGenericDaoHibernate<AssessmentRule> implements AssessmentRuleDao {

	/** 
	 * Description : 获取规则集合根据品目
	 * Create Date: 2011-8-25下午03:31:33 by yucy  Modified Date: 2011-8-25下午03:31:33 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<AssessmentRule> getAssessmentRuleByCategoryIds(final Map<String, Object> param ) throws Exception {
		return (List<AssessmentRule>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				List<String> categoryIds = (List<String>)param.get("categoryIds");
				String sql = "select ar.* from EPS_ASSESSMENT_RULE ar where 1=2 ";
				if(categoryIds!=null){
					for(String categoryId: categoryIds){
						sql += "or ar.category_id like '%,"+categoryId+"%' or ar.category_id like '%"+categoryId+",%' or ar.category_id = '"+categoryId+"'";
					}
				}
				SQLQuery sqlQuery = session.createSQLQuery(sql);
				sqlQuery.addEntity(AssessmentRule.class);
				return sqlQuery.list();
			}
		});
	}
}
