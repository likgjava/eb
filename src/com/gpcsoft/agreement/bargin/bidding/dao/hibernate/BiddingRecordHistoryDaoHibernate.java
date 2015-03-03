package com.gpcsoft.agreement.bargin.bidding.dao.hibernate;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.agreement.bargin.bidding.dao.BiddingRecordHistoryDao;
import com.gpcsoft.agreement.bargin.bidding.domain.BiddingRecordHistory;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.utils.StringUtils;

@Repository
public class BiddingRecordHistoryDaoHibernate extends BaseGenericDaoHibernate<BiddingRecordHistory> implements BiddingRecordHistoryDao {

	/** 
	 * Description : 根据detail获得历史
	 * Create Date: 2011-5-30上午09:29:31 by yucy  Modified Date: 2011-5-30上午09:29:31 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getHistoryInfoByDetail(final String detailId) throws Exception {
		return (List<Object[]>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String sql2 = " select ard.record_detail_id," +
								" to_char(arh.bargin_time ,'yyyy-MM-dd HH24:mi:ss') ," +
								" arh.goods_total ," +
								" ard.is_choose, " +
								" g.product_name || ard.service_content," +
								" ard.bargin_file" +
								" ,arh.RECORD_HISTORY_ID,g.spec,g.goods_id"+
								" from eps_agree_record_history arh"+
								" left join eps_agree_record_detail ard on ard.record_detail_id = arh.record_detail_id"+
								" left join goods g on g.goods_id = ard.goods_id"+
								" where ard.record_detail_id = '"+detailId+"'"+
								" order by ard.record_detail_id, arh.goods_total ";
				Query query1 = session.createSQLQuery(sql2);
				//历史
				List<Object[]> listHistory= query1.list();
				
				return listHistory;
			}
		});
	}
	/** 
	 * Description :  根据DetialId获取历史报价  
	 * Create Date: 2011-5-27下午04:51:21 by sunl  Modified Date: 2011-5-27下午04:51:21 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getHistoryByDetail(final String bargainDetailId) throws Exception {
		return (List<Object[]>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				StringBuilder sql = new StringBuilder();
				sql.append(" select g.product_name || d.service_content ,");
				sql.append(" to_char(h.bargin_time,'yyyy-MM-dd hh24:mi:ss') bargaintime,h.goods_price,h.goods_total ");
				sql.append(" from eps_agree_record_history h ");
				sql.append(" left join eps_agree_record_detail d on h.record_detail_id=d.record_detail_id ");
				sql.append(" left join goods g on g.goods_id=d.goods_id ");
				sql.append(" where 1=1 ");
				if(StringUtils.hasLength(bargainDetailId)) {
					sql.append(" and h.record_detail_id=:bargainDetailId");
				}
				sql.append(" order by bargaintime desc ");
				Query query = session.createSQLQuery(sql.toString());
				if(StringUtils.hasLength(bargainDetailId)) {
					query.setString("bargainDetailId", bargainDetailId);
				}
				return query.list();
			}
		});
	}
}
