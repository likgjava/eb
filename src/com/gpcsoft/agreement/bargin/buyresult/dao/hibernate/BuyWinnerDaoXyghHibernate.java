package com.gpcsoft.agreement.bargin.buyresult.dao.hibernate;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Service;

import com.gpcsoft.agreement.bargin.buyresult.dao.BuyWinnerDaoXygh;
import com.gpcsoft.epp.buyresult.dao.hibernate.BuyWinnerDaoHibernate;
import com.gpcsoft.epp.buyresult.domain.BuyWinner;

@Service 
public class BuyWinnerDaoXyghHibernate extends BuyWinnerDaoHibernate implements BuyWinnerDaoXygh {

	/** 
	 * Description :  取得供应商该项目的成交结果
	 * Create Date: 2010-10-27上午11:53:28 by yucy  Modified Date: 2010-10-27上午11:53:28 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<BuyWinner> getBuyWinnerList( final Map<String, Object> param) throws Exception {
		return (List<BuyWinner>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
					String Hql = " select bw from BuyWinner bw where bw.buyResult.project.objId = :projectId and  bw.selllerId = :supplierId ";
					Query query = session.createQuery(Hql);
					query.setParameter("projectId",param.get("projectId") );
					query.setParameter("supplierId",param.get("supplierId") );
					return query.list();
				}
		});
	}

}
