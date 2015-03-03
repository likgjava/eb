package com.gpcsoft.epp.common.dao.hibernate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.epp.common.dao.FactorDeDao;
import com.gpcsoft.epp.common.domain.FactorDe;

@Repository
public class FactorDeDaoHibernate extends BaseGenericDaoHibernate<FactorDe> implements FactorDeDao {
	
	/**
	 * @Description: 根据指标ID获取所有指标
	 * @param factorIds
	 * @return List<FactorDe>
	 * @throws Exception
	 * @Create Date 2010-8-11 上午10:36:49 By wanghz
	 */
	public List<FactorDe> getAllByFactorDeIds(final String[] factorIds){
		return (List<FactorDe>)getHibernateTemplate().execute(new HibernateCallback(){
			public List<FactorDe> doInHibernate(Session session)throws HibernateException, SQLException {
				StringBuffer hql = new StringBuffer();
				hql.append("from FactorDe as factor where factor.objId in ( ");
				for(String s:factorIds){
					hql.append("?,");
				}
				hql = new StringBuffer(hql.toString().substring(0,hql.toString().length()-1));
				hql.append(" ) ");
				
				Query query = session.createQuery(hql.toString());
				for(int i=0;i<factorIds.length;i++){
					query.setString(i, factorIds[i]);
				}
				List<FactorDe> factorDeList = query.list();
				if(null == factorDeList || factorDeList.size()<1){
					factorDeList = new ArrayList<FactorDe>(0);
				}
				return factorDeList;
		}});
	}
}
