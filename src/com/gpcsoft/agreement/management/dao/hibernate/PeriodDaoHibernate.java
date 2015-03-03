package com.gpcsoft.agreement.management.dao.hibernate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.agreement.management.dao.PeriodDao;
import com.gpcsoft.agreement.management.domain.Period;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;

@Repository
public class PeriodDaoHibernate extends BaseGenericDaoHibernate<Period> implements PeriodDao {

	/* (non-Javadoc)
	 * @see com.gpcsoft.eps.agreement.management.dao.PeriodDao#delPeriod(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public Integer delPeriod(final String periods) {
		int result = 0;
		List list = (List) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String hql="from Agreement a where a.period.objId in (:objIds)";
				List periodlist = new ArrayList();
				String[] periodIds = periods.split(",");
				for(String periodId:periodIds){
					periodlist.add(periodId);
				}
				Query query = session.createQuery(hql);
				query.setParameterList("objIds", periodlist);   
				return query.list();
		}});
		
		if(list.size()<=0){
			result = (Integer) getHibernateTemplate().execute(new HibernateCallback(){
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {
					String hql="delete from Period p where p.objId in (:objIds)";
					List periodlist = new ArrayList();
					String[] periodIds = periods.split(",");
					for(String periodId:periodIds){
						periodlist.add(periodId);
					}
					Query query = session.createQuery(hql);
					query.setParameterList("objIds", periodlist);   
					return query.executeUpdate();
			}});
		}
		return result ;
	}

	/* (non-Javadoc)
	 * @see com.gpcsoft.eps.agreement.management.dao.PeriodDao#getAnnualSearchContent()
	 */
	@SuppressWarnings("unchecked")
	public List getAnnualSearchContent() {
		List list = (List) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String sql="Select Distinct pdate From (Select To_Char(p.Begin_Date, 'yyyy') pdate From Eps_Agreement_Period p Order By p.Begin_Date) Order By pdate";
				Query query = session.createSQLQuery(sql);
				return query.list();
		}});
		return list;
	}
}
