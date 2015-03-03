package com.gpcsoft.goods.goodsprice.dao.hibernate;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.bizplatform.base.common.util.StringUtils;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.goods.goodsprice.dao.GoodsGiftPriceDao;
import com.gpcsoft.goods.goodsprice.domain.GoodsGiftPrice;

@Repository
public class GoodsGiftPriceDaoHibernate extends BaseGenericDaoHibernate<GoodsGiftPrice> implements GoodsGiftPriceDao {

	/** 
	 * Description :  获取礼包价格列表
	 * Create Date: 2011-1-10下午12:43:37 by likg  Modified Date: 2011-1-10下午12:43:37 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<GoodsGiftPrice> getGoodsGiftPriceList(final Map<String, Object> param) throws Exception {
		return (List<GoodsGiftPrice>) getHibernateTemplate().execute(new HibernateCallback()
		{
			public Object doInHibernate(Session session) throws HibernateException, SQLException 
			{
				Object goodsPriceId = param.get("goodsPriceId");
				Object goodsPriceSupplierId = param.get("goodsPriceSupplierId");
				Object maxSize = param.get("maxSize");
				
				StringBuilder hql = new StringBuilder();
				hql.append("from GoodsGiftPrice ggp join fetch ggp.goodsGift gp join fetch gp.goods g where 1=1 ");
				
				if(goodsPriceId!=null && StringUtils.hasLength(goodsPriceId.toString())) {
					hql.append(" and ggp.goodsPrice.objId = '").append(goodsPriceId).append("' ");
				}
				
				if(goodsPriceSupplierId!=null && StringUtils.hasLength(goodsPriceSupplierId.toString())) {
					hql.append(" and ggp.goodsPriceSupplier.supplier.objId = '").append(goodsPriceSupplierId).append("' ");
				}
				
				Query query = session.createQuery(hql.toString());
				
				//控制查询总记录数
				if(maxSize!=null && StringUtils.hasLength(maxSize.toString())) {
					query.setFirstResult(0).setMaxResults(Integer.parseInt(maxSize.toString()));
				}
				
				return query.list();
			}
		});
	}

}
