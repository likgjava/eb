package com.gpcsoft.goods.goodsprice.dao.hibernate;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.goods.goodsprice.dao.GoodsOptFitPriceDao;
import com.gpcsoft.goods.goodsprice.domain.GoodsOptFitPrice;

@Repository
public class GoodsOptFitPriceDaoHibernate extends BaseGenericDaoHibernate<GoodsOptFitPrice> implements GoodsOptFitPriceDao {

	/** 
	 * Description :  根据行情id取得选配行情
	 * Create Date: 2011-12-8上午12:22:50 by yucy  Modified Date: 2011-12-8上午12:22:50 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<GoodsOptFitPrice> getGoodsOptFitPriceListByGoodsPriceId(final  Map<String, Object> param) throws Exception {
		return (List<GoodsOptFitPrice>) getHibernateTemplate().execute(new HibernateCallback(){public Object doInHibernate(Session session) throws HibernateException, SQLException {
			List goodsPriceIds = (List)param.get("goodsPriceIds");
			if(goodsPriceIds!=null && goodsPriceIds.size()>0){
				String hql = " select gofp.* from goods_opt_fit_price  gofp , goods_optional_fitting gof  where gofp.goods_opt_fit_id = gof.goods_optional_fitting_id and gofp.goods_price_id in (:goodsPriceIds) order by gof.goods_param_id ,gofp.goods_opt_fit_id  ";
				SQLQuery query = session.createSQLQuery(hql);
				query.setParameterList("goodsPriceIds", goodsPriceIds );
				query.addEntity(GoodsOptFitPrice.class);
				return (List<GoodsOptFitPrice>)query.list();
			}else{
				return null;
			}
		}});
	}
}
