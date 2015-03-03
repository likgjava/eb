package com.gpcsoft.agreement.bargin.buyresult.dao.hibernate;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Service;

import com.gpcsoft.agreement.bargin.bidding.domain.BiddingRecordItem;
import com.gpcsoft.agreement.bargin.buyresult.dao.BuyResultDaoXygh;
import com.gpcsoft.core.utils.StringUtils;
import com.gpcsoft.epp.buyresult.dao.hibernate.BuyResultDaoHibernate;
import com.gpcsoft.epp.buyresult.domain.BuyResult;

@Service 
public class BuyResultDaoXyghHibernate extends BuyResultDaoHibernate implements BuyResultDaoXygh {

	/** 
	 * Description :  供应商轮次报价数据
	 * Create Date: 2010-10-14下午03:23:27 by yucy  Modified Date: 2010-10-14下午03:23:27 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> getSupplierBargainByTurn( final Map<String, Object> param) throws Exception {
		return (Map<String, Object>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
					StringBuffer Hql  = new StringBuffer();
					Hql.append(" select distinct br.bargainTurn.objId ,br.bargainTurn.turnNo " );
					Hql.append(" from BiddingRecord br " );
					Hql.append(" where br.project.objId = :projectId " );
					Hql.append(" and br.supplier.objId = :supplierId " );
					
					if(StringUtils.hasLength((String)param.get("turnId"))){
						Hql.append(" and br.bargainTurn.objId = :turnId " );
					}
					Hql.append(" order by br.bargainTurn.turnNo desc ") ;
					Query query = session.createQuery(Hql.toString());
					query.setParameter("projectId", param.get("projectId"));
					query.setParameter("supplierId", param.get("supplierId"));
					
					if(StringUtils.hasLength((String)param.get("turnId"))){
						query.setParameter("turnId", param.get("turnId"));
					}
					
					List<Object[]> tabResult = query.list();
					
					String HqlSub = " select br.objId, br.goodsTotal ,br.barginTime" +
							" from BiddingRecord br " +
							" where br.project.objId = :projectId "+
							" and br.supplier.objId = :supplierId "+
							(StringUtils.hasLength((String)param.get("turnId"))||tabResult.size()>0?" and br.bargainTurn.objId = :turnId ":"");
					Query query1 = session.createQuery(HqlSub);
					if(StringUtils.hasLength((String)param.get("turnId"))||tabResult.size()>0){
						query1.setParameter("turnId", tabResult.size()>0?tabResult.get(0)[0]:null);
					}
					query1.setParameter("projectId", param.get("projectId"));
					query1.setParameter("supplierId", param.get("supplierId"));
					List<Object[]> firstResult = query1.list();

					Map<String, Object> result = new HashMap<String, Object>();
					result.put("tabResult", tabResult);
					result.put("firstResult", firstResult);
					return result;
				}
		});
	}

	@SuppressWarnings("unchecked")
	public BuyResult getBuyResultByProject(final Map<String, Object> param) throws Exception {
		return (BuyResult) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
					String hql = " from BuyResult br where br.project.objId = :projectId " ;
					Query query = session.createQuery(hql);
					query.setParameter("projectId", (String)param.get("projectId"));
					List<BuyResult> result =  query.list();
					return result.size()>0? query.list().get(0):null;
				}
		});
	}

	/** 
	 * Description :  获得供应商单轮报价的数据
	 * Create Date: 2010-10-18上午10:54:18 by yucy  Modified Date: 2010-10-18上午10:54:18 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getSingleTurnRecordDate(final Map<String, Object> param) throws Exception {
		return ( List<Object[]>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
					String HqlSub = " select br.objId, br.goodsTotal ,br.barginTime" +
							" from BiddingRecord br " +
							" where br.bargainTurn.objId = :turnId "+
							" and br.project.objId = :projectId " +
							" and br.supplier.objId = :supplierId " ;
					Query query = session.createQuery(HqlSub);
					query.setParameter("turnId", param.get("turnId"));
					query.setParameter("projectId", param.get("projectId"));
					query.setParameter("supplierId", param.get("supplierId"));
					return query.list();
				}
		});
	}

	/** 
	 * Description :  取得报价记录明细by报价记录
	 * Create Date: 2010-10-18上午11:27:24 by yucy  Modified Date: 2010-10-18上午11:27:24 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<BiddingRecordItem> getRecordItemsByRecord(final Map<String, Object> param) throws Exception {
		return ( List<BiddingRecordItem>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)throws HibernateException, SQLException {
					String Hql = " from BiddingRecordItem bri where bri.biddingRecord.objId = :recordId " ;
					Query query = session.createQuery(Hql);
					query.setParameter("recordId", param.get("recordId"));
					return query.list();
				}
		});
	}
}
