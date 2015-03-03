package com.gpcsoft.smallscale.chart.dao.hibernate;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.agreement.bargin.bidding.domain.BiddingRecordDetail;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.utils.StringUtils;
import com.gpcsoft.epp.buyresult.domain.BuyWinner;
import com.gpcsoft.epp.common.domain.EbuyMethodEnum;
import com.gpcsoft.smallscale.chart.dao.ChartDao;


@Repository
public class ChartDaoHibernate extends BaseGenericDaoHibernate<GpcBaseObject> implements ChartDao {

	/** 
	 * Description :  获取每月的节省金额
	 * Create Date: 2011-7-11上午10:01:30 by likg  Modified Date: 2011-7-11上午10:01:30 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getSaveAmountByMonth(final Map<String, Object> param) throws Exception {
		return (List<Object[]>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String buyerId = (String) param.get("buyerId"); //采购人Id
				String year = (String) param.get("year"); //年份
				
				String sql = "" + 
				"select month, sum(save) from (" +
					" select TO_NUMBER(TO_CHAR(o.CRE_TIME,'mm')) month, " +
					" case when oi.GOODS_ID is null then (ri.GOODS_PRICE-oi.GOODS_PRICE)*oi.GOODS_QTY else (oi.MARKET_PRICE-oi.GOODS_PRICE)*oi.GOODS_QTY end save " + 
					" from EPS_AGREEMENT_ORDER o " +
					" join EPS_AGREEMENT_ORDER_ITEM oi on o.ORDER_ID = oi.ORDER_ID" +
					" and oi.MARKET_PRICE != 0" +
					" and o.BUYER_ID = :buyerId" +
					" and TO_CHAR(o.CRE_TIME,'yyyy') = :year" +
					" left join EPS_AGREEMENT_REQUIRE_ITEM ri on oi.REQUIRE_DTL_ID = ri.REQUIRE_DTL_ID" + 
				")" +
				" group by month" +
				" order by month asc";
				
				Query query = session.createSQLQuery(sql);
				query.setParameter("buyerId", buyerId);
				query.setParameter("year", year);
				
				return query.list();
			}
		});
	}

	/** 
	 * Description :  获取每月的预算额、实际额、节省额、采购量
	 * Create Date: 2011-7-12上午11:22:50 by likg  Modified Date: 2011-7-12上午11:22:50 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getPlanActualSaveQtyByMonth(final Map<String, Object> param) throws Exception {
		return (List<Object[]>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String year = (String) param.get("year"); //年份
				String buyerId = (String) param.get("buyerId"); //采购人Id
				String supplierId = (String) param.get("supplierId"); //供应商Id
				
				//过滤条件
				String filterSql = "";
				if(StringUtils.hasLength(buyerId)) {
					filterSql = "and o.BUYER_ID = :buyerId";
				} else {
					filterSql = "and o.SUPPLIER_ID = :supplierId";
				}
				
				String sql = "" + 
				"select month, sum(plan), sum(actual), sum(save), count(month) from (" +
					" select TO_NUMBER(TO_CHAR(o.CRE_TIME,'mm')) month, " +
					" case when oi.GOODS_ID is null then ri.GOODS_PRICE*oi.GOODS_QTY else oi.MARKET_PRICE*oi.GOODS_QTY end plan, " + 
					" oi.GOODS_PRICE*oi.GOODS_QTY actual, " + 
					" case when oi.GOODS_ID is null then (ri.GOODS_PRICE-oi.GOODS_PRICE)*oi.GOODS_QTY else (oi.MARKET_PRICE-oi.GOODS_PRICE)*oi.GOODS_QTY end save " + 
					" from EPS_AGREEMENT_ORDER o " +
					" join EPS_AGREEMENT_ORDER_ITEM oi on o.ORDER_ID = oi.ORDER_ID" +
					" and oi.MARKET_PRICE != 0" +
					filterSql +
					" and TO_CHAR(o.CRE_TIME,'yyyy') = :year" +
					" left join EPS_AGREEMENT_REQUIRE_ITEM ri on oi.REQUIRE_DTL_ID = ri.REQUIRE_DTL_ID" + 
				")" +
				" group by month" +
				" order by month asc";
				
				Query query = session.createSQLQuery(sql);
				query.setParameter("year", year);
				if(StringUtils.hasLength(buyerId)) {
					query.setParameter("buyerId", buyerId);
				} else {
					query.setParameter("supplierId", supplierId);
				}
				
				return query.list();
			}
		});
	}

	/** 
	 * Description :  获取采购人或供应商产生过交易的采购品目
	 * Create Date: 2011-7-12下午03:08:10 by likg  Modified Date: 2011-7-12下午03:08:10 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getPurCategoryList(final Map<String, Object> param) throws Exception {
		return (List<Object[]>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String buyerId = (String) param.get("buyerId"); //采购人Id
				String supplierId = (String) param.get("supplierId"); //供应商Id
				
				//过滤条件
				String filterSql = "";
				if(StringUtils.hasLength(buyerId)) {
					filterSql = "and o.BUYER_ID = :buyerId";
				} else {
					filterSql = "and o.SUPPLIER_ID = :supplierId";
				}
				
				//获取交易过的采购品目Id
				List<String> purCategoryIds = null;
				String sql = "" + 
					" select distinct case when oi.GOODS_ID is null then ri.PUR_CATEGORY_ID else g.PUR_CATEGORY_ID end " + 
					" from EPS_AGREEMENT_ORDER o " +
					" join EPS_AGREEMENT_ORDER_ITEM oi on o.ORDER_ID = oi.ORDER_ID" +
					" and oi.MARKET_PRICE != 0" +
					filterSql +
					" left join GOODS g on g.GOODS_ID = oi.GOODS_ID" + 
					" left join EPS_AGREEMENT_REQUIRE_ITEM ri on oi.REQUIRE_DTL_ID = ri.REQUIRE_DTL_ID";
				
				Query query = session.createSQLQuery(sql);
				if(StringUtils.hasLength(buyerId)) {
					query.setParameter("buyerId", buyerId);
				} else {
					query.setParameter("supplierId", supplierId);
				}
				purCategoryIds = query.list();
				
				//获取采购品目信息
				List<Object[]> result = null;
				if(purCategoryIds!=null && purCategoryIds.size()>0) {
					sql = "select p.id, p.category_Name from PURCATALOG_CATEGORY p where p.ID in (:ids)";
					query = session.createSQLQuery(sql);
					query.setParameterList("ids", purCategoryIds);
					result = query.list();
				}
				
				return result;
			}
		});
	}

	/** 
	 * Description :  获取指定品目的每月的（采购人：采购额、采购量、节省额）（供应商：销售额、销售量）
	 * Create Date: 2011-7-12下午03:55:51 by likg  Modified Date: 2011-7-12下午03:55:51 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getPurcategoryAmountQtySaveByMonth(final Map<String, Object> param) throws Exception {
		return (List<Object[]>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String purCategoryId = (String) param.get("purCategoryId"); //采购品目Id
				String supplierId = (String) param.get("supplierId"); //供应商Id
				String buyerId = (String) param.get("buyerId"); //采购人Id
				String year = (String) param.get("year"); //年份
				
				//过滤条件
				String filterSql = "";
				if(StringUtils.hasLength(buyerId)) {
					filterSql = "and o.BUYER_ID = :buyerId";
				} else {
					filterSql = "and o.SUPPLIER_ID = :supplierId";
				}
				
				String sql = "" + 
				"select month, sum(actual), count(month), sum(save) from (" +
					" select TO_NUMBER(TO_CHAR(o.CRE_TIME,'mm')) month, " +
					" oi.GOODS_PRICE*oi.GOODS_QTY actual, " + 
					" case when oi.GOODS_ID is null then (ri.GOODS_PRICE-oi.GOODS_PRICE)*oi.GOODS_QTY else (oi.MARKET_PRICE-oi.GOODS_PRICE)*oi.GOODS_QTY end save " + 
					" from EPS_AGREEMENT_ORDER o " +
					" join EPS_AGREEMENT_ORDER_ITEM oi on o.ORDER_ID = oi.ORDER_ID" +
					" and oi.MARKET_PRICE != 0" +
					filterSql +
					" and TO_CHAR(o.CRE_TIME,'yyyy') = :year" +
					" left join GOODS g on g.GOODS_ID = oi.GOODS_ID" + 
					" left join EPS_AGREEMENT_REQUIRE_ITEM ri on oi.REQUIRE_DTL_ID = ri.REQUIRE_DTL_ID" + 
					" where g.PUR_CATEGORY_ID = :purCategoryId or ri.PUR_CATEGORY_ID = :purCategoryId" +
				")" +
				" group by month" +
				" order by month asc";
				
				Query query = session.createSQLQuery(sql);
				query.setParameter("purCategoryId", purCategoryId);
				query.setParameter("year", year);
				if(StringUtils.hasLength(buyerId)) {
					query.setParameter("buyerId", buyerId);
				} else {
					query.setParameter("supplierId", supplierId);
				}
				
				return query.list();
			}
		});
	}

	/** 
	 * Description :  获取所有品目的年度（采购人：采购额、采购量）（供应商：销售额、销售量）
	 * Create Date: 2011-7-12下午03:55:51 by likg  Modified Date: 2011-7-12下午03:55:51 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getAllPurcategoryAmountQtyByYear(final Map<String, Object> param) throws Exception {
		return (List<Object[]>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String supplierId = (String) param.get("supplierId"); //供应商Id
				String buyerId = (String) param.get("buyerId"); //采购人Id
				String year = (String) param.get("year"); //年份
				
				//过滤条件
				String filterSql = "";
				if(StringUtils.hasLength(buyerId)) {
					filterSql = "and o.BUYER_ID = :buyerId";
				} else {
					filterSql = "and o.SUPPLIER_ID = :supplierId";
				}
				
				String sql = "" + 
				"select t.pid, max(p.category_Name), sum(t.actual), count(t.pid) from (" +
					" select case when oi.GOODS_ID is null then ri.PUR_CATEGORY_ID else g.PUR_CATEGORY_ID end pid, " +
					" oi.GOODS_PRICE*oi.GOODS_QTY actual " + 
					" from EPS_AGREEMENT_ORDER o " +
					" join EPS_AGREEMENT_ORDER_ITEM oi on o.ORDER_ID = oi.ORDER_ID" +
					" and oi.MARKET_PRICE != 0" +
					filterSql +
					" and TO_CHAR(o.CRE_TIME,'yyyy') = :year" +
					" left join GOODS g on g.GOODS_ID = oi.GOODS_ID" + 
					" left join EPS_AGREEMENT_REQUIRE_ITEM ri on oi.REQUIRE_DTL_ID = ri.REQUIRE_DTL_ID" + 
				") t, PURCATALOG_CATEGORY p" +
				" where p.ID = t.pid" +
				" group by t.pid";
				
				Query query = session.createSQLQuery(sql);
				query.setParameter("year", year);
				if(StringUtils.hasLength(buyerId)) {
					query.setParameter("buyerId", buyerId);
				} else {
					query.setParameter("supplierId", supplierId);
				}
				
				return query.list();
			}
		});
	}

	/** 
	 * Description :  获取指定采购方式每月的采购额、采购量
	 * Create Date: 2011-7-12下午03:55:51 by likg  Modified Date: 2011-7-12下午03:55:51 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getEbuymethodAmountQtyByMonth(final Map<String, Object> param) throws Exception {
		return (List<Object[]>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String buyerId = (String) param.get("buyerId"); //采购人Id
				String year = (String) param.get("year"); //年份
				String ebuyMethod = (String) param.get("ebuyMethod"); //采购方式
				
				String filterSql = "";
				if("00".equals(ebuyMethod)) { //直接订购
					filterSql = " and o.PROJECT_ID is null";
				} else if("01".equals(ebuyMethod)) { //议价
					filterSql = " and p.TENDERMETHOD = '" + EbuyMethodEnum.TALK + "'";
				} else if("02".equals(ebuyMethod)) { //竞价
					filterSql = " and p.TENDERMETHOD = '" + EbuyMethodEnum.COMPETITION + "'";
				}
				
				String sql = "" + 
				"select month, sum(amount), count(month) from (" +
					" select TO_NUMBER(TO_CHAR(o.CRE_TIME,'mm')) month, o.GOODS_TOTAL amount" +
					" from EPS_AGREEMENT_ORDER o " +
					" left join ECP_TENDER_PROJECT p on o.PROJECT_ID = p.TENDERID" +
					" where o.BUYER_ID = :buyerId" +
					" and TO_CHAR(o.CRE_TIME,'yyyy') = :year" +
					filterSql +
				")" +
				" group by month" +
				" order by month asc";
				
				Query query = session.createSQLQuery(sql);
				query.setParameter("buyerId", buyerId);
				query.setParameter("year", year);
				
				return query.list();
			}
		});
	}

	/** 
	 * Description :  获取各采购方式的年度采购额、采购量
	 * Create Date: 2011-7-12下午03:55:51 by likg  Modified Date: 2011-7-12下午03:55:51 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getAllEbuymethodAmountQtyByYear(final Map<String, Object> param) throws Exception {
		return (List<Object[]>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String buyerId = (String) param.get("buyerId"); //采购人Id
				String year = (String) param.get("year"); //年份
				
				String sql = "" + 
				"select ebuymethod, nvl(sum(amount), 0), nvl(count(ebuymethod), 0) from (" +
					" select case when o.PROJECT_ID is null then 0 when p.TENDERMETHOD = '" + EbuyMethodEnum.TALK + "' then 1 when p.TENDERMETHOD = '" + EbuyMethodEnum.COMPETITION + "' then 2 else 3 end ebuymethod, " +
					" o.GOODS_TOTAL amount" +
					" from EPS_AGREEMENT_ORDER o " +
					" left join ECP_TENDER_PROJECT p on o.PROJECT_ID = p.TENDERID" +
					" where o.BUYER_ID = :buyerId" +
					" and TO_CHAR(o.CRE_TIME,'yyyy') = :year" +
				")" +
				" where ebuymethod != 3" +
				" group by ebuymethod" +
				" order by ebuymethod asc";
				
				Query query = session.createSQLQuery(sql);
				query.setParameter("buyerId", buyerId);
				query.setParameter("year", year);
				
				return query.list();
			}
		});
	}

	/**
	 * Description :  获取所有商品时间段内（供应商：销售额、销售量）
	 * Create Date: 2011-7-28下午03:07:11 by zhaojf  Modified Date: 2011-7-28下午03:07:11 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getAllDurGoodsAmountQtyByYear(final Map<String, Object> param) throws Exception {
		return (List<Object[]>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String supplierId = (String) param.get("supplierId"); //供应商Id
				String startDate = (String) param.get("startDate"); //开始时间
				String endDate = (String) param.get("endDate"); //截止时间
				String dateUrl = "";
				if(!"".equals(startDate) && !"".equals(endDate)){
					dateUrl = dateUrl + " and o.s_confirm_date >= to_date('"+startDate+"', 'YYYY-mm-dd')";
					dateUrl = dateUrl + " and o.s_confirm_date <= to_date('"+endDate+"', 'YYYY-mm-dd')";
				}
				if("".equals(startDate) && "".equals(endDate)){
					dateUrl = "";
				}
				if(!"".equals(startDate) && "".equals(endDate)){
					dateUrl = dateUrl + " and o.s_confirm_date >= to_date('"+startDate+"', 'YYYY-mm-dd')";
				}
				if("".equals(startDate) && !"".equals(endDate)){
					dateUrl = dateUrl + " and o.s_confirm_date <= to_date('"+endDate+"', 'YYYY-mm-dd')";
				}

				//过滤条件
				String sql = "" + 
				"select g.product_name, ot.* from goods g,(select tot.goods_id, sum(tot.goods_qty), sum(tot.goods_total) "+
				"from (select t.goods_id, t.supplier_id, t.goods_qty, t.goods_total"+
				" from eps_agreement_order_item t, eps_agreement_order o where t.order_id = o.order_id"+
				" and o.use_status = '01' and t.supplier_id = :supplierId"+dateUrl+
				") tot group by tot.goods_id) ot where g.goods_id = ot.goods_id";
				
				Query query = session.createSQLQuery(sql);
				query.setParameter("supplierId", supplierId);
				
				return query.list();
			}
		});
	}

	/**
	 * 获取商品在项目里的报价(根据参数商品Id、供货商Id、当前用户Id)
	 */
	@SuppressWarnings("unchecked")
	public List<BiddingRecordDetail> getAllGoodsBiddingByProjectData(final Map<String, Object> param) throws Exception {
		return (List<BiddingRecordDetail>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String goodsId = (String) param.get("goodsId");//商品Id
				String supplierId = (String) param.get("supplierId");//供货商Id
				String currentUserId = (String) param.get("currentUserId");//当前用户Id
				String hql = "from BiddingRecordDetail b where b.createUser.objId = :currentUserId and b.goods.objId = :goodsId and b.supplier.objId = :supplierId";
				hql = hql + " order by b.project.createTime asc";
				
				Query query = session.createQuery(hql);
				query.setParameter("currentUserId", currentUserId);
				query.setParameter("goodsId", goodsId);
				query.setParameter("supplierId", supplierId);
				
				return query.list();
			}
		});
	}

	/**
	 * 获取一段时间段内的中标情况数据(供应商)
	 */
	@SuppressWarnings("unchecked")
	public List<BuyWinner> getAllBiddingWinnerRecordsByYearData(final Map<String, Object> param) throws Exception {
		return (List<BuyWinner>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String supplierId = (String) param.get("supplierId"); //供应商Id
				String start_date = (String) param.get("start_date"); //季度开始时间
				String end_date = (String) param.get("end_date"); //季度结束时间
				
				String hql = "select w from BuyWinner w,BuyResult r where w.buyResult.objId = r.objId and w.selllerId = :supplierId ";
				hql = hql + "and r.createTime >= to_date('"+start_date+"','YYYY-mm-dd') ";
				hql = hql + "and r.createTime <= to_date('"+end_date+"','YYYY-mm-dd') ";
				Query query = session.createQuery(hql);
				query.setParameter("supplierId", supplierId);
				
				return query.list();
			}
		});
	}
}
