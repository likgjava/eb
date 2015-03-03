package com.gpcsoft.goods.goods.dao.hibernate;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
import com.gpcsoft.goods.goods.dao.RecommendGoodsDao;
import com.gpcsoft.goods.goods.domain.Goods;
import com.gpcsoft.goods.goods.domain.RecommendGoods;
import com.gpcsoft.goods.goods.enumeration.GoodsEnum;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;

@Repository
public class RecommendGoodsDaoHibernate extends BaseGenericDaoHibernate<RecommendGoods> implements RecommendGoodsDao {
	
	/** 
	 * Description :  推荐指定的商品
	 * Create Date: 2010-10-11 下午 16:30:16 by likg  Modified Date: 2010-10-11 下午 16:30:16 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public void recommendGoods(final String[] goodsIds, final RecommendGoods recommendGoodsPattern) throws Exception 
	{
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException 
			{
				// 获得已推荐商品的最大排序序号
				Query queryMaxSort = session.createQuery("select max(rg.sort) from RecommendGoods rg");
				Long maxSort = (Long) queryMaxSort.uniqueResult();
				if(maxSort == null) {
					maxSort = 0L;
				}
				
				StringBuilder sql = new StringBuilder();
				long mm = new Date().getTime();
				sql.append("insert into RECOMMEND_GOODS (ID,RECOMMENDER,REASON,AUDIT_STATUS,USE_STATUS,CREATE_TIME,GOODS_ID,SORT) ");
				sql.append("select g.GOODS_ID || :mm, :recommender, :reason, :audit_status, :use_status, :create_time, g.GOODS_ID, :sort + rownum ");
				sql.append("from GOODS g ");
				sql.append("where g.GOODS_ID in (");
				for(String str : goodsIds)
				{
					sql.append("'" + str + "',");
				}
				sql.deleteCharAt(sql.length()-1);
				sql.append(")");
				
				Query query = session.createSQLQuery(sql.toString());
				
				query.setParameter("mm", mm);
				query.setParameter("recommender", AuthenticationHelper.getCurrentUser(true).getObjId());
				query.setParameter("reason", recommendGoodsPattern.getReason());
				query.setParameter("audit_status", recommendGoodsPattern.getAuditStatus());
				query.setParameter("use_status", (recommendGoodsPattern.getUseStatus()==true ? "1" : "0"));
				query.setParameter("create_time", recommendGoodsPattern.getCreateTime());
				query.setParameter("sort", maxSort);
				
				query.executeUpdate();
				return true;
			}
		});
	}

	/** 
	 * Description :  获取所有的未推荐商品
	 * Create Date: 2010-10-13 下午 16:30:16 by likg  Modified Date: 2010-10-13 下午 16:30:16 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Page<Goods> listNoRecommendGoods(final Map<String,Object> param, final Page<Goods> page) throws Exception {
		return (Page<Goods>) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String goodsClassCode = (String) param.get("goodsClassCode"); //商品分类编号
				String goodsBrandName = (String) param.get("goodsBrandName"); //商品品牌名称
				String productName = (String) param.get("productName"); //商品名称
				String order = (String) param.get("order"); //排序字段
				String order_flag = (String) param.get("order_flag"); //排序方式
				
				//查询语句
				StringBuilder hql = new StringBuilder();
				String queryColumn = "select g.objId,g.productName,g.productCode,g.goodsBrand.brandName,g.goodsClass.goodsClassName";
				hql.append(" from Goods g where g.objId not in (select rg.goods.objId from RecommendGoods rg )");
				
				//状态过滤条件
				hql.append(" and g.useStatus = '").append(GoodsEnum.USE_VALID).append("'");
				hql.append(" and g.auditStatus = '").append(GoodsEnum.PASS_EXAM).append("'");
				hql.append(" and g.sellStatus = '").append(GoodsEnum.SELL_START).append("'");
				
				//查询过滤条件
				if(StringUtils.hasLength(goodsClassCode)) {
					hql.append(" and g.goodsClass.goodsClassCode like '%").append(goodsClassCode).append("%' ");
				}
				if(StringUtils.hasLength(goodsBrandName)) {
					hql.append(" and g.goodsBrand.brandName like '%").append(goodsBrandName).append("%' ");
				}
				if(StringUtils.hasLength(productName)) {
					hql.append(" and g.productName like '%").append(productName).append("%' ");
				}
				
				//排序
				StringBuilder orderHql = new StringBuilder(" order by");
				if(StringUtils.hasLength(order)) {
					orderHql.append(" g.").append(order);
					orderHql.append("true".equalsIgnoreCase(order_flag) ? " desc " : " asc ");
				} else {
					orderHql.append(" g.createTime desc ");
				}
				
				//查询商品信息
				Query query = session.createQuery(queryColumn + hql + orderHql);
				query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());
				//查询总记录数
				Query queryCount = session.createQuery("select count(*) " + hql);
				Integer totalCount = ((Long)queryCount.list().get(0)).intValue();
				
				return new Page<Goods>(page.getStart(), totalCount, page.getPageSize(), query.list());
			}
		});
	}

	/** 
	 * Description :  获得推荐商品
	 * Create Date: 2010-11-5下午05:30:58 by likg  Modified Date: 2010-11-5下午05:30:58 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Page<RecommendGoods> getRecommendGoods(final Page<RecommendGoods> page, final Map<String, Object> paramsMap) throws Exception 
	{
		return (Page<RecommendGoods>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException 
			{
				String goodsClassId  = (String) paramsMap.get("goodsClassId"); //商品分类ID,以逗号分割
				String goodsClassCode = (String) paramsMap.get("goodsClassCode"); //商品分类Code
				String brandId  = (String) paramsMap.get("brandId"); //品牌ID,以逗号分割
				
				String keyWord = (String) paramsMap.get("keyWord");
				

//				//查询推荐商品信息
//				String preHql;
//				preHql = "from RecommendGoods rg join fetch rg.goods as gs join fetch gs.goodsClass gc where 1=1 ";
//				StringBuilder hql = new StringBuilder();
//				
//				//分类过滤
//				if(StringUtils.hasLength(goodsClassId)) 
//				{
//					hql.append(" and gs.goodsClass.objId in (").append(StringUtils.convertToQuoteString(goodsClassId)).append(") ");
//				}
//				if(StringUtils.hasLength(goodsClassCode))
//				{
//					hql.append(" and gs.goodsClass.goodsClassCode like '%").append(goodsClassCode).append("%' ");
//				}
//				
//				if(StringUtils.hasLength(brandId)){
//					hql.append(" and gs.goodsBrand.objId in (").append(StringUtils.convertToQuoteString(brandId)).append(") ");
//				}
//				
//				if(StringUtils.hasLength(keyWord)){
//					hql.append(" and ( instr(gs.productName ,'").append(keyWord).append("')>0 ");
//					hql.append(" or instr(gs.goodsBrand.brandName ,'").append(keyWord).append("')>0 ");
//					hql.append(" or instr(gs.goodsClass.goodsClassName ,'").append(keyWord).append("')>0 ) ");
//				}
//				
//				//排序条件
//				StringBuilder orderHql = new StringBuilder();
//				orderHql.append(" order by rg.createTime desc ");
//				
//				Query query = session.createQuery(preHql + hql + orderHql);
//				query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());
//		
//				List<RecommendGoods> goodsList = query.list();
//				page.setData(goodsList);
//				
//				//查询总数
//				preHql = "select count(rg.objId) from RecommendGoods rg join rg.goods as gs where 1=1 ";
//				query = session.createQuery(preHql + hql);
//				page.setTotalRowNum((Long) query.list().get(0));
//				return page;
				
				
				String column =  " select rg.id, rg.goods_id, g.product_name, g.product_code,g.picture_id, g.refer_price ,rg.reason";
				
				//连接表
				String table = " from recommend_goods rg, goods g ,goods_class c ,goods_brand b"+
					  " where rg.goods_id = g.goods_id  and c.goods_class_id = g.goods_class_id and g.goods_brand_id = b.goods_brand_id";
				
				//选择条件
				StringBuilder condition = new StringBuilder();
				//condition.append(" and rg.audit_status ='").append( GoodsEnum.PASS_EXAM ).append("'");
				//分类过滤
				if(StringUtils.hasLength(goodsClassId)){
					condition.append(" and g.goods_class_id in (").append(StringUtils.convertToQuoteString(goodsClassId)).append(") ");
				}
				if(StringUtils.hasLength(goodsClassCode)){
					condition.append(" and instr(g.goods_class_id ,'").append(goodsClassCode).append("')>0 ");
				}
				//品牌过滤
				if(StringUtils.hasLength(brandId)){
					condition.append(" and g.goods_brand_id in (").append(StringUtils.convertToQuoteString(brandId)).append(") ");
				}
				
				//关键字
				if(StringUtils.hasLength(keyWord)){
					condition.append(" and ( instr(g.product_name ,'").append(keyWord).append("')>0 ");
					condition.append(" or instr(b.brand_name ,'").append(keyWord).append("')>0 ");
					condition.append(" or instr(c.goods_class_name ,'").append(keyWord).append("')>0 ) ");
				}
				
				//排序条件
				String orderSql = " order by rg.sort asc ";
				
				Query query = session.createSQLQuery(column + table + condition.toString() + orderSql);
				query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());
				List<RecommendGoods> dataList = new ArrayList<RecommendGoods>();
				//拼装对象
				for(Object[] objects : (List<Object[]>)query.list()){
					RecommendGoods recommendGoods = new RecommendGoods();
					recommendGoods.setObjId((String)objects[0]);
					Goods goods = new Goods();
					goods.setObjId((String)objects[1]);
					goods.setProductName((String)objects[2]);
					goods.setProductCode((String)objects[3]);
					goods.setPicture((String)objects[4]);
					goods.setReferPrice((BigDecimal)objects[5]);
					recommendGoods.setReason((String)objects[6]);
					recommendGoods.setGoods(goods);
					dataList.add(recommendGoods);
				}
				page.setData(dataList);
				query = session.createSQLQuery("select count(rg.id) "+table + condition.toString());
				page.setTotalRowNum(((BigDecimal)query.uniqueResult()).longValue());
				return page;

				
		}});
	}

	/** 
	 * Description :  修改推荐商品的排序序号
	 * Create Date: 2010-12-22上午10:43:42 by likg  Modified Date: 2010-12-22上午10:43:42 by likg
	 * @param   
	 * @return 
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public void updateSort(final Long sort, final boolean isToUp) throws Exception {
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException 
			{
				StringBuilder hql = new StringBuilder();
				hql.append("from RecommendGoods rg where 1=1 ");
				
				//过滤条件和排序条件
				if(isToUp){
					hql.append(" and rg.sort <= ").append(sort);
					hql.append(" order by rg.sort desc ");
				}else{
					hql.append(" and rg.sort >= ").append(sort);
					hql.append(" order by rg.sort asc ");
				}
				
				Query query = session.createQuery(hql.toString());
				query.setFirstResult(0).setMaxResults(2);
		
				List<RecommendGoods> goodsList = query.list();
				if(goodsList!=null && goodsList.size()==2){
					RecommendGoods sourceRecommendGoods = goodsList.get(0);
					RecommendGoods targetRecommendGoods = goodsList.get(1);
					
					Long tempSort = sourceRecommendGoods.getSort();
					sourceRecommendGoods.setSort(targetRecommendGoods.getSort());
					targetRecommendGoods.setSort(tempSort);
				}
				
				return null;
			}	
		});
	}
}
