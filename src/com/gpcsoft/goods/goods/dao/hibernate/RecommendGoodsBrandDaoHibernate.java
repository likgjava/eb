package com.gpcsoft.goods.goods.dao.hibernate;

import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.bizplatform.base.common.util.StringUtils;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.goods.goods.dao.RecommendGoodsBrandDao;
import com.gpcsoft.goods.goods.domain.GoodsBrand;
import com.gpcsoft.goods.goods.domain.RecommendGoodsBrand;
import com.gpcsoft.goods.goods.enumeration.GoodsEnum;

@Repository
public class RecommendGoodsBrandDaoHibernate extends BaseGenericDaoHibernate<RecommendGoodsBrand> implements RecommendGoodsBrandDao {

	/** 
	 * Description :  保存推荐的商品品牌
	 * Create Date: 2011-5-16下午04:29:43 by likg  Modified Date: 2011-5-16下午04:29:43 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public void saveRecommendGoodsBrand(final String goodsBrandIds, final RecommendGoodsBrand recommendGoodsBrand) throws Exception {
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				//获得已推荐商品品牌的最小排序序号
				Query queryMinSort = session.createQuery("select min(rb.sort) from RecommendGoodsBrand rb");
				Long minSort = (Long) queryMinSort.uniqueResult();
				if(minSort == null) {
					minSort = 0L;
				}
				
				StringBuilder sql = new StringBuilder();
				long mm = new Date().getTime();
				sql.append("insert into RECOMMEND_GOODS_BRAND (ID,GOODS_BRAND_ID,RECOMMENDER,REASON,CREATE_TIME,SORT) ");
				sql.append("select b.GOODS_BRAND_ID || :mm, b.GOODS_BRAND_ID, :recommender, :reason, :createTime, :sort - rownum ");
				sql.append("from GOODS_BRAND b ");
				sql.append("where b.GOODS_BRAND_ID in (:goodsBrandIds)");
				
				Query query = session.createSQLQuery(sql.toString());
				query.setParameterList("goodsBrandIds", goodsBrandIds.split(","));
				query.setParameter("mm", mm);
				query.setParameter("recommender", recommendGoodsBrand.getRecommender());
				query.setParameter("reason", recommendGoodsBrand.getReason());
				query.setParameter("createTime", recommendGoodsBrand.getCreateTime());
				query.setParameter("sort", minSort);
				
				query.executeUpdate();
				return true;
			}
		});
	}
	
	/** 
	 * Description :  获取所有未推荐的商品品牌
	 * Create Date: 2011-5-16下午04:11:57 by likg  Modified Date: 2011-5-16下午04:11:57 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Page<GoodsBrand> listNoRecommendGoodsBrand(final Map<String, Object> param, final Page<GoodsBrand> page) throws Exception {
		return (Page<GoodsBrand>) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String brandCode = (String) param.get("brandCode");
				String brandName = (String) param.get("brandName");
				String order = (String) param.get("order");
				String order_flag = (String) param.get("order_flag");
				StringBuilder hql = new StringBuilder();
				
				hql.append(" from GoodsBrand b where b.objId not in (select rb.goodsBrand.objId from RecommendGoodsBrand rb )");
				
				//品牌状态过滤条件
				hql.append(" and b.useStatus = '").append(GoodsEnum.USE_VALID).append("'");
				hql.append(" and b.auditStatus = '").append(GoodsEnum.PASS_EXAM).append("'");
				hql.append(" and b.sellStatus = '").append(GoodsEnum.SELL_START).append("'");
				
				//查询过滤条件
				hql.append(" and b.brandCode like '%" + brandCode + "%'");
				hql.append(" and b.brandName like '%" + brandName + "%'");
				
				//排序
				String orderHql = " order by b.createTime desc ";
				if(StringUtils.hasLength(order)) {
					StringBuilder multiOrder = new StringBuilder();
					if(order.indexOf("brandCode") != -1){
						multiOrder.append("b.brandCode, ");
					}
					if(order.indexOf("brandName") != -1){
						multiOrder.append("b.brandName, ");
					}
					hql.append(" order by ").append(multiOrder.toString().substring(0,multiOrder.toString().length()-2));
					hql.append("true".equals(order_flag) ? " desc ":" asc ");
				} else {
					hql.append(orderHql);
				}
				
				String queryColumn = "select b.objId,b.brandCode,b.brandName";
				Query queryCount = session.createQuery("select count(*) " + hql.toString());
				Query query = session.createQuery(queryColumn + hql.toString());
				query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());
				Integer totalCount = ((Long)queryCount.list().get(0)).intValue();
				
				return new Page<GoodsBrand>(page.getStart(), totalCount, page.getPageSize(), query.list());
			}
		});
	}
	
	/** 
	 * Description :  修改推荐商品品牌的排序序号
	 * Create Date: 2011-5-16下午04:20:32 by likg  Modified Date: 2011-5-16下午04:20:32 by likg
	 * @param   objId:要排序的推荐商品品牌的Id	isToUp:排序方向
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public void updateSort(final String objId, final boolean isToUp) throws Exception {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				//获取要排序的推荐商品品牌的序号
				RecommendGoodsBrand rb = get(objId);
				Long sort = rb.getSort();
				
				StringBuilder hql = new StringBuilder();
				hql.append("from RecommendGoodsBrand rb where 1=1 ");
				
				//上移
				if(isToUp) {
					hql.append(" and rb.sort < ").append(sort);
					hql.append(" order by rb.sort desc ");
				}
				//下移
				else {
					hql.append(" and rb.sort > ").append(sort);
					hql.append(" order by rb.sort asc ");
				}
				
				//交换两对象的序号
				Query query = session.createQuery(hql.toString());
				query.setFirstResult(0).setMaxResults(1);
				RecommendGoodsBrand trb = (RecommendGoodsBrand) query.uniqueResult();
				rb.setSort(trb.getSort());
				trb.setSort(sort);
				
				return null;
			}	
		});
	}
	
	/** 
	 * Description :  获得推荐商品品牌
	 * Create Date: 2011-5-16下午04:32:47 by likg  Modified Date: 2011-5-16下午04:32:47 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Page<RecommendGoodsBrand> getRecommendGoodsBrand(final Page<RecommendGoodsBrand> page, final Map<String, Object> paramsMap) throws Exception {
		return (Page<RecommendGoodsBrand>) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				//查询推荐商品品牌信息
				String preHql = "from RecommendGoodsBrand rb join fetch rb.goodsBrand b where 1=1 ";
				StringBuilder hql = new StringBuilder();
				
				//排序条件
				StringBuilder orderHql = new StringBuilder();
				orderHql.append(" order by rb.sort ");
				
				Query query = session.createQuery(preHql + hql + orderHql);
				query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());
				page.setData(query.list());
				
				//查询总数
				preHql = "select count(rb.objId) from RecommendGoodsBrand rb join rb.goodsBrand b where 1=1 ";
				query = session.createQuery(preHql + hql);
				page.setTotalRowNum((Long) query.list().get(0));
				
				return page;
		}});
	}

}
