package com.gpcsoft.agreement.bargin.bidding.dao.hibernate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.gpcsoft.agreement.bargin.bidding.dao.BiddingRecordDao;
import com.gpcsoft.agreement.bargin.bidding.domain.BiddingRecord;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;

@Repository
public class BiddingRecordDaoHibernate extends BaseGenericDaoHibernate<BiddingRecord> implements BiddingRecordDao {
	
	/** 
	 * Description :  根据项目ID删除报价信息
	 * Create Date: 2010-9-28上午11:15:50 by sunl  Modified Date: 2010-9-28上午11:15:50 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Integer removeBiddingRecordByProjId(final String projId) throws Exception {
		return (Integer)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				//删除报价条目
				StringBuilder hql2 = new StringBuilder();
				hql2.append("delete from BiddingRecordItem r where r.biddingRecord.objId in ");
				hql2.append("(select r.objId from BiddingRecord r where r.project.objId=:projId )");
				Query query2 = session.createQuery(hql2.toString());
				query2.setString("projId", projId);
				Integer result2 = query2.executeUpdate();
				
				//删除报价
				StringBuilder hql = new StringBuilder();
				hql.append("delete from BiddingRecord r where r.project.objId=:objId ");
				Query query = session.createQuery(hql.toString());
				query.setString("objId", projId);
				Integer result = query.executeUpdate();
				
				return result2 + result;
			}
		});
	}
	
	/** 
	 * Description :  根据项目ID获取报价信息（所有供应商最后一次报价报价，多信息）
	 * Create Date: 2010-9-28上午11:15:50 by sunl  Modified Date: 2010-9-28上午11:15:50 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<BiddingRecord> getBiddingRecordByProjectId_allInfo(String objId,String turnId) throws Exception {
		StringBuilder hql = new StringBuilder();
		hql.append("select b from BiddingRecord b left join fetch b.supplier ");
		hql.append(" left join fetch b.biddingRecordItemSet as bs ");
		hql.append(" left join fetch bs.requireItem as ri ");
		hql.append(" left join fetch bs.goods as rg ");
		
		hql.append(" where b.supplier.objId || ");
		hql.append(" to_char(b.barginTime,'yyyy-MM-dd HH24:mi:ss')");
		hql.append(" in ");
		
		hql.append(" ( select i.supplier.objId||max(to_char(i.barginTime,'yyyy-MM-dd HH24:mi:ss')) from BiddingRecord i ");
		hql.append(" where i.project.objId = ? and i.bargainTurn.objId = ? ");
		hql.append(" group by i.supplier.objId  ) ");
		
		List<BiddingRecord> biddingRecordList = this.findbyHql(hql.toString(), objId,turnId);
		Set<BiddingRecord> biddingRecordSet = new HashSet<BiddingRecord>(biddingRecordList);
		List<BiddingRecord> bidList = new ArrayList<BiddingRecord>();
		bidList.addAll(biddingRecordSet);
		return bidList;
	}
	
	/** 
	 * Description :  根据项目ID和供应商ID获取供应商的最后一次报价信息
	 * Create Date: 2010-9-28上午11:15:50 by sunl  Modified Date: 2010-9-28上午11:15:50 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public BiddingRecord getSupplierBiddingLastRecords(String projId,String supplierId,String turnId) throws Exception {
		StringBuilder hql = new StringBuilder();
		hql.append("from BiddingRecord r left join fetch r.biddingRecordItemSet where 1=1 ");
		hql.append("and r.project.objId = ? and r.supplier.objId = ? and r.bargainTurn.objId = ? ");
		hql.append("order by r.barginTime desc");
		
		BiddingRecord record = null;
		int size = ((List<BiddingRecord>)this.findbyHql(hql.toString(), projId,supplierId,turnId)).size();
		if(size == 0) {
			/*原则上,下一轮应取上一轮的最后一次报价,如果上一轮没有报价,则取该项目的最后一次报价*/
			List<BiddingRecord> tempList = (List<BiddingRecord>)this.findbyHql("from BiddingRecord r left join fetch r.biddingRecordItemSet where 1=1 " +
					"and r.project.objId = ? and r.supplier.objId = ? order by r.barginTime desc", 
					projId,supplierId);
			record = tempList.size()<1?null:tempList.get(0);
			if(record == null) {
				record = new BiddingRecord();
			}
		}else {
			record = ((List<BiddingRecord>)this.findbyHql(hql.toString(), projId,supplierId,turnId)).get(0);
		}
		
		return record;
	}
	
	/** 
	 * Description :  根据项目ID、供应商ID、轮次ID 获取供应商的报价历史
	 * Create Date: 2010-9-28上午11:15:50 by sunl  Modified Date: 2010-9-28上午11:15:50 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<BiddingRecord> getSupplierBiddingHistory(String projId,String supplierId,String turnId) throws Exception {
		StringBuilder hql = new StringBuilder();
		hql.append("select distinct r from BiddingRecord r left join fetch r.biddingRecordItemSet where 1=1 ");
		hql.append("and r.project.objId = ? and r.supplier.objId = ? ");
		if(turnId!=null && !"".equals(turnId))
		{
			hql.append("and r.bargainTurn.objId = '").append(turnId).append("' ");
		}
		hql.append("order by r.barginTime desc");
		
		return this.findbyHql(hql.toString(), projId,supplierId);
	}
	
	/** 
	 * Description :  根据项目ID获取最高、最低、平均报价
	 * Create Date: 2010-9-28上午11:15:50 by sunl  Modified Date: 2010-9-28上午11:15:50 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<Object> getMaxMinAvgPriceByProjId(final String objId) throws Exception {
		return (ArrayList<Object>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				StringBuilder sql = new StringBuilder();
				sql.append("select * from (select max(t.goods_total), min(t.goods_total),avg(t.goods_total) ");
				sql.append("from eps_agree_bargain_record t where t.project_id =:objId ),(");
				sql.append("select o.org_name maxS from eps_agree_bargain_record g,org_info o where o.org_info_id = g.suplier_id ");
				sql.append("and g.goods_total = (select max(goods_total) from eps_agree_bargain_record where project_id =:objId)");
				sql.append("),(select o.org_name minS from eps_agree_bargain_record g,org_info o where o.org_info_id = g.suplier_id ");
				sql.append("and g.goods_total = (select min(goods_total) from eps_agree_bargain_record where project_id =:objId))");
				
				Query query = session.createSQLQuery(sql.toString());
				query.setString("objId", objId);
				
				return query.list();
			}
		});
	}

	/** 
	 * Description :  取得每个供应商最低报价 (对象)
	 * Create Date: 2010-10-21下午09:23:44 by yucy  Modified Date: 2010-10-21下午09:23:44 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<BiddingRecord> getMinBiddingRecordByProjectId(final String projectId,final String turnId ,final String supplierId) throws Exception  {
		return (List<BiddingRecord>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
					StringBuilder hql = new StringBuilder();
					hql.append("select b from BiddingRecord b left join fetch b.supplier where b.supplier.objId||b.goodsTotal in ");
					hql.append("(select b1.supplier.objId||min(b1.goodsTotal) from BiddingRecord b1 where 1=1 ");
					if(StringUtils.hasLength(projectId)) {
						hql.append(" and b1.project.objId =:projId ");
					}
					if(StringUtils.hasLength(turnId)) {
						hql.append(" and b1.bargainTurn.objId =:turnId ");
					}
					if(StringUtils.hasLength(supplierId)){
						hql.append(" and b1.supplier.objId =:supplierId ");
					}
					hql.append("group by b1.supplier.objId)");
					
					hql.append(" and to_char(b.barginTime,'yyyy-MM-dd HH24:mi:ss') in (");
					hql.append(" select to_char(max(i.barginTime),'yyyy-MM-dd HH24:mi:ss') from BiddingRecord i ");
					hql.append(" where i.project.objId =:projId ");
					if(StringUtils.hasLength(turnId)) {
						hql.append(" and i.bargainTurn.objId =:turnId ");
					}
					hql.append(" group by i.supplier.objId,i.goodsTotal)");
					
					Query query = session.createQuery(hql.toString());
					if(StringUtils.hasLength(projectId)) {
						query.setParameter("projId", projectId);
					}
					if(StringUtils.hasLength(turnId)) {
						query.setParameter("turnId", turnId);
					}
					if(StringUtils.hasLength(supplierId)){
						query.setParameter("supplierId", supplierId);
					}
					return query.list();
				}
		});
	}
	
	/** 
	 * Description :  取得每个供应商最低议价报价 (对象)
	 * Create Date: 2010-10-21下午09:23:44 by yucy  Modified Date: 2010-10-21下午09:23:44 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<BiddingRecord> getMinBiddingRecordByTalkProjectId(final String projectId,final String turnId ,final String supplierId) throws Exception  {
		return (List<BiddingRecord>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
					StringBuilder hql = new StringBuilder();
					hql.append("select b from BiddingRecord b left join fetch b.supplier where b.supplier.objId||b.goodsTotal in ");
					hql.append("(select b1.supplier.objId||min(b1.goodsTotal) ");
					hql.append("from BiddingRecord b1 where 1=1 ");
					if(StringUtils.hasLength(projectId)) {
						hql.append(" and b1.project.objId =:projId ");
					}
					if(StringUtils.hasLength(turnId)) {
						hql.append(" and b1.bargainTurn.objId =:turnId ");
					}
					if(StringUtils.hasLength(supplierId)){
						hql.append(" and b1.supplier.objId =:supplierId ");
					}
					hql.append(" group by b1.supplier.objId) ");
					hql.append(" and to_char(b.barginTime,'yyyy-MM-dd HH24:mi:ss') in (");
					hql.append(" select to_char(max(i.barginTime),'yyyy-MM-dd HH24:mi:ss') from BiddingRecord i ");
					hql.append(" where i.project.objId =:projId ");
					if(StringUtils.hasLength(turnId)) {
						hql.append(" and i.bargainTurn.objId =:turnId ");
					}
					hql.append(" group by i.supplier.objId,i.goodsTotal)");
					Query query = session.createQuery(hql.toString());
					if(StringUtils.hasLength(projectId)) {
						query.setParameter("projId", projectId);
					}
					if(StringUtils.hasLength(turnId)) {
						query.setParameter("turnId", turnId);
					}
					if(StringUtils.hasLength(supplierId)){
						query.setParameter("supplierId", supplierId);
					}
					return query.list();
				}
		});
	}
	
	/**
	 * Description :  取得单个供应商项目的最低报价
	 * Create Date: 2010-10-22上午11:05:46 by yucy  Modified Date: 2010-10-22上午11:05:46 by yucy
	 * @param   
	 * @return  
	 * @Exception
	 */
	@SuppressWarnings("unchecked")
	public BiddingRecord getMinBiddingRecordByProjectAndSupplier(final Map<String, Object> param) throws Exception {
		return (BiddingRecord) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
					String Hql = " select br from BiddingRecord br" +
							" where br.supplier.objId = :supplierId and br.project.objId = :projectId" +
							"  order by br.goodsTotal ";
					Query query = session.createQuery(Hql);
					query.setParameter("projectId", param.get("projectId"));
					query.setParameter("supplierId", param.get("supplierId"));
					List list =  query.list();
					return list.size()>0 ? list.get(0) : null;
				}
		});
	}
	
	/** 
	 * Description :  根据轮次ID和项目ID获得报价排名(当前供应商)
	 * Create Date: 2010-9-28上午11:15:50 by sunl  Modified Date: 2010-9-28上午11:15:50 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> getSelfRanking(final String projId,final String turnId) throws Exception {
		return (Map<String, Object>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				StringBuilder sql = new StringBuilder();
				sql.append("select * from (select g.*,rownum from (select min(t.goods_total) gtotal,t.suplier_id from EPS_AGREE_BARGAIN_RECORD t ");
				sql.append("where t.project_id =:projId and t.bargain_turn_id =:turnId group by t.suplier_id order by gtotal asc) g) ");
				sql.append("where suplier_id =:supplierId");
				
				Query query = session.createSQLQuery(sql.toString());
				query.setString("projId", projId);
				query.setString("turnId", turnId);
				String supplierId = AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId();
				query.setString("supplierId", supplierId);
				
				Object[] obj;
				if(query.list() == null || query.list().size() == 0) {
					obj = null;
				}else{
					obj = (Object[])query.list().get(0);
				}
				Map<String, Object> res = new HashMap<String, Object>();
				res.put("selfRanking", obj == null ? "" : obj[2]);
				return res;
			}
		});
	}
	
	/** 
	 * Description :  获取轮次的最低报价
	 * Create Date: 2010-9-28上午11:15:50 by sunl  Modified Date: 2010-9-28上午11:15:50 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> getMinTurnBargainPrice(final String projId,final String turnId) throws Exception {
		return (Map<String, Object>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				StringBuilder sql = new StringBuilder();
				sql.append("select t.goods_total,o.org_name from EPS_AGREE_BARGAIN_RECORD t,org_info o ");
				sql.append("where t.project_id =:projId and ");
				sql.append("t.bargain_turn_id =:turnId ");
				sql.append("and o.org_info_id = t.suplier_id ");
				sql.append("order by t.goods_total asc");
				
				Query query = session.createSQLQuery(sql.toString());
				query.setString("projId", projId);
				query.setString("turnId", turnId);
				
				Object[] obj;
				if(query.list() == null || query.list().size() == 0) {
					obj = null;
				}else{
					obj = (Object[])query.list().get(0);
				}
				Map<String, Object> res = new HashMap<String, Object>();
				res.put("minTurnBargainPrice", obj);
				return res;
			}
		});
	}


}
