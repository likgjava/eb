package com.gpcsoft.goods.goodsprice.dao.hibernate;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.goods.goodsprice.dao.GoodsPriceProcessDao;
import com.gpcsoft.goods.goodsprice.domain.GoodsPriceProcess;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

@Repository
public class GoodsPriceProcessDaoHibernate extends BaseGenericDaoHibernate<GoodsPriceProcess> implements GoodsPriceProcessDao {

	/** 
	 * Description :  获取行情图形
	 * Create Date: 2010-10-12下午02:23:46 by yucy  Modified Date: 2010-10-12下午02:23:46 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getPriceChartDate(final Map<String, Object> param) throws Exception {
		return (List<Object[]>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
					String sql = "select pgp.year||'-'||pgp.month, pgp.district_id,avg(pgp.amount) from ecp_process_goods_price pgp,goods_price_supplier gps " +
								" where  pgp.supplier_id = gps.goods_price_supplier_id and"+
								" gps.goods_id = :goodsId " +
								" and pgp.district_id in (:districtIds )"+
								" and ( to_date(pgp.year||pgp.month,'yyyyMM') between to_date(:startTime,'yyyyMM') and  to_date(:endTime,'yyyyMM') )" +
								" group by pgp.year||'-'||pgp.month, pgp.district_id";
					Query query = session.createSQLQuery(sql);
					
					query.setParameterList("districtIds",(String[])param.get("districtIds"));
					query.setParameter("goodsId",(String)param.get("goodsId"));
					query.setParameter("startTime",(String)param.get("startTime"));
					query.setParameter("endTime",(String)param.get("endTime"));
					
					return query.list();
				}
		});
	}

}
