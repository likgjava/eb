package com.gpcsoft.goods.goods.dao.hibernate;

import java.math.BigDecimal;
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
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.goods.goods.dao.GoodsAccessoriesPriceDao;
import com.gpcsoft.goods.goods.domain.GoodsAccessoriesPrice;

@Repository
public class GoodsAccessoriesPriceDaoHibernate extends BaseGenericDaoHibernate<GoodsAccessoriesPrice> implements GoodsAccessoriesPriceDao {

	/** 
	 * Description :  零配件价格列表
	 * Create Date: 2010-7-28上午09:39:54 by sunl  Modified Date: 2010-7-28上午09:39:54 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Page<GoodsAccessoriesPrice> listAccPrice(Page<GoodsAccessoriesPrice> page,Map<String,Object> param) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("from goods_accessories t left join ");
		sql.append("(select p.goods_accessories_id,p.accessories_price,p.goods_accessories_price_id from goods_accessories_price p where ");
		if(StringUtils.hasLength((String)param.get("goodsPriceId"))) {
			sql.append("p.goods_price_id = '").append((String)param.get("goodsPriceId")).append("') pp ");
		}
		sql.append("on t.GOODS_ACCESSORIES_ID = pp.goods_accessories_id ");
		sql.append("join goods g on g.goods_id = t.ACCESSORYGOODS_ID ");
		if(StringUtils.hasLength((String)param.get("goodsId"))) {
			sql.append("where t.goods_id = '").append((String)param.get("goodsId")).append("' and t.IS_OFF = '1' ");
		}
		sql.append("order by t.CREATE_TIME desc");
		
		String queryColumn = "";
		queryColumn = "select t.GOODS_ACCESSORIES_ID,g.product_name,t.accessory_qty,t.is_off,pp.accessories_price,pp.goods_accessories_price_id ";
			
		Query queryCount = this.getSession().createSQLQuery("select count(*) " + sql.toString());
		Query query = this.getSession().createSQLQuery(queryColumn + sql.toString());
		query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());
		Integer totalRow = ((BigDecimal)queryCount.list().get(0)).intValue();
		List list = query.list();
		Page<GoodsAccessoriesPrice> pageData = new Page<GoodsAccessoriesPrice>(page.getStart(),totalRow,page.getPageSize(),list );
		return pageData;	
	}
	
	/** 
	 * Description :  获取零配件价格列表
	 * Create Date: 2011-1-10下午12:43:37 by likg  Modified Date: 2011-1-10下午12:43:37 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<GoodsAccessoriesPrice> getGoodsAccessPriceList(final Map<String, Object> param) throws Exception {
		return (List<GoodsAccessoriesPrice>) getHibernateTemplate().execute(new HibernateCallback()
		{
			public Object doInHibernate(Session session) throws HibernateException, SQLException 
			{
				String goodsPriceId = (String)param.get("goodsPriceId");
				String goodsPriceSupplierId = (String)param.get("goodsPriceSupplierId");
				String maxSize = (String)param.get("maxSize");
				
				StringBuilder hql = new StringBuilder();
				hql.append("from GoodsAccessoriesPrice ggp join fetch ggp.goodsAccessories gp where 1=1 ");
				
				if(StringUtils.hasLength(goodsPriceId)) {
					hql.append(" and ggp.goodsPrice.objId =:goodsPriceId ");
				}
				if(StringUtils.hasLength(goodsPriceSupplierId)) {
					hql.append(" and ggp.supplier.supplier.objId =:supplierId ");
				}
				
				Query query = session.createQuery(hql.toString());
				if(StringUtils.hasLength(goodsPriceId)) {
					query.setString("goodsPriceId", goodsPriceId);
				}
				if(StringUtils.hasLength(goodsPriceSupplierId)) {
					query.setString("supplierId", goodsPriceSupplierId);
				}
				
				//控制查询总记录数
				if(maxSize!=null && StringUtils.hasLength(maxSize.toString())) {
					query.setFirstResult(0).setMaxResults(Integer.parseInt(maxSize.toString()));
				}
				
				return query.list();
			}
		});
	}
}
