package com.gpcsoft.serve.hotel.dao.hibernate;

import java.sql.SQLException;
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
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.serve.hotel.dao.RecommendHotelDao;
import com.gpcsoft.serve.hotel.domain.Hotel;
import com.gpcsoft.serve.hotel.domain.RecommendHotel;
import com.gpcsoft.serve.hotel.enumeration.HotelEnum;

@Repository
public class RecommendHotelDaoHibernate extends BaseGenericDaoHibernate<RecommendHotel> implements RecommendHotelDao {

	/** 
	 * Description :  获得未推荐的酒店
	 * Create Date: 2010-12-9上午11:00:40 by likg  Modified Date: 2010-12-9上午11:00:40 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Page<Hotel> getNoRecommendHotelList(final Map<String, Object> param, final Page<Hotel> page) throws Exception {
		return (Page<Hotel>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				List<Hotel> noRecommendHotel = null;
				StringBuilder hql = new StringBuilder();
				
				hql.append(" from Hotel h where h.objId not in (select rh.hotel.objId from RecommendHotel rh )");
				
				//过滤条件
				hql.append(" and h.useStatus = '").append(HotelEnum.USE_VALID).append("'");
				hql.append(" and h.auditStatus = '").append(HotelEnum.PASS_EXAM).append("'");
				
				//查询过滤条件
				hql.append(" and h.hotelName like '%" + param.get("hotelName") + "%'");
				
				//排序条件
				String order = " order by h.createTime desc ";
				if(StringUtils.hasLength(param.get("order").toString())){
					String orderStr = param.get("order").toString();
					StringBuilder multiOrder = new StringBuilder();
					if(orderStr.indexOf("hotelName") != -1){
						multiOrder.append("h.hotelName, ");
					}
					if(orderStr.indexOf("hotelAddress") != -1){
						multiOrder.append("h.hotelAddress, ");
					}
					if(orderStr.indexOf("star") != -1){
						multiOrder.append("h.star, ");
					}
					hql.append(" order by ").append(multiOrder.toString().substring(0,multiOrder.toString().length()-2));//去掉空格
					hql.append(param.get("order_flag").toString().equals("true") ? " desc ":" asc ");
				}else{
					hql.append(order);
				}
				
				String queryColumn = "select h.objId,h.picture,h.hotelName,h.hotelAddress,h.star";
				Query queryCount = session.createQuery("select count(*) " + hql.toString());
				Query query = session.createQuery(queryColumn + hql.toString());
				query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());
				Integer totalCount = ((Long)queryCount.list().get(0)).intValue();
				noRecommendHotel = query.list();
				
				return new Page<Hotel>(page.getStart(), totalCount, page.getPageSize(), noRecommendHotel);
			}
		});
	}

	/** 
	 * Description :  推荐酒店
	 * Create Date: 2010-12-9下午12:00:24 by likg  Modified Date: 2010-12-9下午12:00:24 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public void recommendHotel(final String[] hotelIds, final RecommendHotel recommendHotelPattern) throws Exception {
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException  {
				//获得已推荐酒店的最大排序序号
				Query queryMaxSort = session.createQuery("select max(rh.sort) from RecommendHotel rh");
				Long maxSort = (Long) queryMaxSort.uniqueResult();
				if(maxSort == null) {
					maxSort = 0L;
				}
				
				StringBuilder sql = new StringBuilder();
				long mm = new Date().getTime();
				sql.append("insert into SERVE_RECOMMEND_HOTEL (RECOMMEND_HOTEL_ID,RECOMMENDER,REASON,AUDIT_STATUS,USE_STATUS,CREATE_TIME,HOTEL_ID,SORT) ");
				sql.append("select h.HOTEL_ID || :mm, :recommender, :reason, :audit_status, :use_status, :create_time, h.HOTEL_ID, :sort + rownum ");
				sql.append("from SERVE_HOTEL h ");
				sql.append("where h.HOTEL_ID in (");
				for(String str : hotelIds) {
					sql.append("'" + str + "',");
				}
				sql.deleteCharAt(sql.length()-1);
				sql.append(")");
				
				Query query = session.createSQLQuery(sql.toString());
				
				query.setParameter("mm", mm);
				query.setParameter("recommender", AuthenticationHelper.getCurrentUser(true).getObjId());
				query.setParameter("reason", recommendHotelPattern.getReason());
				query.setParameter("audit_status", recommendHotelPattern.getAuditStatus());
				query.setParameter("use_status", (recommendHotelPattern.getUseStatus()==true ? "1" : "0"));
				query.setParameter("create_time", recommendHotelPattern.getCreateTime());
				query.setParameter("sort", maxSort);
				
				query.executeUpdate();
				return true;
			}
		});
	}

	/** 
	 * Description :  获得推荐的酒店
	 * Create Date: 2010-12-10下午12:46:29 by likg  Modified Date: 2010-12-10下午12:46:29 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Page<RecommendHotel> getRecommendHotel(final Page<RecommendHotel> page, final Map<String, Object> paramsMap) throws Exception {
		return (Page<RecommendHotel>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException 
			{
				String queryhql = "from RecommendHotel rh join fetch rh.hotel h where 1=1 ";
				StringBuilder hql = new StringBuilder();
				
				//酒店级别
				String star = paramsMap.get("star").toString();
				if(StringUtils.hasLength(star)) {
					hql.append(" and h.star = '").append(star).append("'");
				}
				
				String sorthql = " order by rh.sort";
				Query query = session.createQuery(queryhql + hql.toString() + sorthql);
				query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());
				Query queryCount = session.createQuery("select count(rh.objId) from RecommendHotel rh");
				Integer totalCount = ((Long)queryCount.list().get(0)).intValue();
				
				return new Page<RecommendHotel>(page.getStart(), totalCount, page.getPageSize(), query.list());
			}
		});
	}
	
	/** 
	 * Description :  修改推荐酒店的排序序号
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
				hql.append("from RecommendHotel rh where 1=1 ");
				
				//过滤条件和排序条件
				if(isToUp){
					hql.append(" and rh.sort <= ").append(sort);
					hql.append(" order by rh.sort desc ");
				}else{
					hql.append(" and rh.sort >= ").append(sort);
					hql.append(" order by rh.sort asc ");
				}
				
				Query query = session.createQuery(hql.toString());
				query.setFirstResult(0).setMaxResults(2);
		
				List<RecommendHotel> hotelList = query.list();
				if(hotelList!=null && hotelList.size()==2){
					RecommendHotel sourceRecommendHotel = hotelList.get(0);
					RecommendHotel targetRecommendHotel = hotelList.get(1);
					
					Long tempSort = sourceRecommendHotel.getSort();
					sourceRecommendHotel.setSort(targetRecommendHotel.getSort());
					targetRecommendHotel.setSort(tempSort);
				}
				
				return null;
			}	
		});
	}

}
