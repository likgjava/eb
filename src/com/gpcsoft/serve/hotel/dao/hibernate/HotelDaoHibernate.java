package com.gpcsoft.serve.hotel.dao.hibernate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.gpcsoft.bizplatform.suppliers.domain.Supplier;
import com.gpcsoft.cms.util.StringUtils;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.serve.hotel.dao.HotelDao;
import com.gpcsoft.serve.hotel.domain.Hotel;
import com.gpcsoft.serve.hotel.enumeration.HotelEnum;
import com.gpcsoft.srplatform.common.utils.unifysql.UnifyDBSqlFactory;

@Repository
public class HotelDaoHibernate extends BaseGenericDaoHibernate<Hotel> implements HotelDao {

	/** 
	 * Description :  验证同一机构下酒店名称的唯一性
	 * Create Date: 2010-12-8下午04:13:18 by guoyr  Modified Date: 2010-12-8下午04:13:18 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public boolean isUnique(final String objId,final String orgInfoId,final String hotelName){
			return (Boolean) getHibernateTemplate().execute(new HibernateCallback(){
				public Object doInHibernate(Session session)
						throws HibernateException, SQLException {	
					StringBuffer sb= new StringBuffer("select "+UnifyDBSqlFactory.getIUnifyDBSqlImpl().isNull("count(objId)","0")+" from Hotel  where orgInfo.objId =:orgInfoId");
					sb.append(" and hotelName =:hotelName");
					if(StringUtils.hasLength(objId)){
						sb.append(" and objId <>:objId");
					}
					Query query = session.createQuery(sb.toString());
					query.setString("orgInfoId", orgInfoId);
					query.setString("hotelName", hotelName);
					if(StringUtils.hasLength(objId)){
						query.setString("objId", objId);
					}
					List<Long>  list =  query.list();
					boolean flag = true;
					if(list.get(0) > 0){
						flag = false;
					}
					return flag;
				}
				
			});
		}
	
	/** 
	 * Description : 审核酒店信息
	 * Create Date: 2010-12-9上午10:50:08 by guoyr  Modified Date: 2010-12-9上午10:50:08 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Integer updateHotelAuditStatus(final String objId, final String auditStatus,final String useStatus){
		return (Integer)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				StringBuilder hql = new StringBuilder();
				Query query = null;
				if(StringUtils.hasLength(objId)){
					hql.append("update Hotel  set auditStatus =:auditStatus, useStatus =:useStatus where objId =:objId");
					query = session.createQuery(hql.toString());
					query.setParameter("auditStatus", auditStatus);
					query.setParameter("useStatus", useStatus);
					query.setParameter("objId", objId);
				}
				return query.executeUpdate();
			}
		});
	}
	
	/** 
	 * Description :  获得酒店星级展示数据，包含酒店数量
	 * Create Date: 2010-12-8下午04:36:35 by liangxj  Modified Date: 2010-12-8下午04:36:35 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<String[]> getStarLevelListShow() throws Exception {
		return (List<String[]>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				
				String hql = "select a.star ,0, count(a.objId) from Hotel a " +
						"where a.useStatus =:useStatus and a.isOff =:isOff " +
						"group by a.star order by a.star";
				
				Query query = session.createQuery(hql);
				
				query.setString("useStatus", HotelEnum.USE_VALID);
				query.setString("isOff", HotelEnum.ENABLE);
				
				return query.list();
		}});
	}
	
	/** 
	 * Description :  获得酒店的展示数据
	 * Create Date: 2010-12-8下午04:37:40 by liangxj  Modified Date: 2010-12-8下午04:37:40 by liangxj
	 * @param   page 分页信息 paramsMap 查询条件：包含酒店级别、区域、客房信息、会议室信息、排序信息
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Page<Hotel> getHotelListForShow(final Page<Hotel> page,final Map<String, Object> paramsMap) throws Exception {
		return (Page<Hotel>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String starLevel = (String) paramsMap.get("starLevel"); //酒店星级
				String districtId = (String) paramsMap.get("districtId"); //区域id
				String starSort = (String) paramsMap.get("starSort"); //酒店星级排序
				String evalSort = (String) paramsMap.get("evalSort"); //评价排序
				String keyWord= (String) paramsMap.get("keyWord"); //关键字
				
				//查询酒店信息
				String preHql = "from Hotel as s left join fetch s.district " +
									"where s.auditStatus='"+HotelEnum.PASS_EXAM+"' and s.useStatus = '"+HotelEnum.USE_VALID+"' and s.isOff = '"+HotelEnum.ENABLE+"' ";
				
				StringBuilder hql = new StringBuilder();
				
				//星级过滤
				if(StringUtils.hasLength(starLevel)){
					hql.append(" and s.star = '").append(starLevel).append("' ");
				}
				
				//区域过滤
				if(StringUtils.hasLength(districtId)){
					hql.append(" and " +
							"(s.district.objId = '").append(districtId).append("' " +
							"or s.district.parent.objId = '").append(districtId).append("' " +
							"or s.district.parent.parent.objId = '").append(districtId).append("') ");
				}
				
				//高级搜索条件
				StringBuilder highparam = new StringBuilder();
				String temp;
				if(StringUtils.hasLength((String)paramsMap.get("startDate"))){//成立时间
					highparam.append(" and s.startTime >= ").append(UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2Date((String)paramsMap.get("startDate")));
				}
				if(StringUtils.hasLength((String)paramsMap.get("endDate"))){
					highparam.append(" and s.startTime <= ").append(UnifyDBSqlFactory.getIUnifyDBSqlImpl().valueString2Date((String)paramsMap.get("endDate")));
				}
				if(StringUtils.hasLength((String)paramsMap.get("guestRoomType"))){ //客房类型
					temp = (String)paramsMap.get("guestRoomType");
					highparam.append(" and ( 1=1 ");
					for (String t : temp.split(",")) {
						highparam.append("and s.guestRoomType like '%").append(t).append("%' ");
					}	
					highparam.append(")");
				}
				if(StringUtils.hasLength((String)paramsMap.get("funFacilities"))){ //娱乐设施
					temp = (String)paramsMap.get("funFacilities");
					highparam.append(" and ( 1=1 ");
					for (String t : temp.split(",")) {
						highparam.append("and s.funFacilities like '%").append(t).append("%' ");
					}	
					highparam.append(")");
				}
				if(StringUtils.hasLength((String)paramsMap.get("serviceItems"))){ //服务项目
					temp = (String)paramsMap.get("serviceItems");
					highparam.append(" and ( 1=1 ");
					for (String t : temp.split(",")) {
						highparam.append("and s.serviceItems like '%").append(t).append("%' ");
					}	
					highparam.append(")");
				}
				if(StringUtils.hasLength((String)paramsMap.get("creditCardType"))){ //可接受信用卡类型
					temp = (String)paramsMap.get("creditCardType");
					highparam.append(" and ( 1=1 ");
					for (String t : temp.split(",")) {
						highparam.append("and s.creditCardType like '%").append(t).append("%' ");
					}	
					highparam.append(")");
				}
				if(StringUtils.hasLength((String)paramsMap.get("meetingRoomType"))){ //会议室类型，格式是： 类型-人数范围
					highparam.append("and s.meetingRoomType like '%").append((String)paramsMap.get("meetingRoomType")).append("%' ");
				}
				
				
				//关键字(酒店名称、酒店地址、酒店机构)
				if(StringUtils.hasLength(keyWord)){
					highparam.append(" and (s.hotelName like '%").append(keyWord).append("%'");
					highparam.append(" or s.hotelAddress like '%").append(keyWord).append("%'");
					highparam.append(" or s.orgInfo.orgName like '%").append(keyWord).append("%')");
				}
				
				hql.append(highparam.toString());
				
				//排序条件
				StringBuilder orderHql = new StringBuilder();
				if(StringUtils.hasLength(evalSort) || StringUtils.hasLength(starSort)){
					orderHql.append("order by ");
					if(StringUtils.hasLength(starSort)) {
						orderHql.append("s.star ").append(starSort).append(",");
					}
					if(StringUtils.hasLength(evalSort)) {
						orderHql.append("s.star ").append(evalSort);
					}
					
					if(orderHql.toString().endsWith(",")) {
						orderHql.delete(orderHql.length()-1, orderHql.length());
					}
				}
				
				Query query = session.createQuery(preHql + hql + orderHql);
				query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());
		
				List<Supplier> supplierList=query.list();
				page.setData(supplierList);
				
				//查询总数
				preHql = "select count(s.objId) from Hotel as s " +
									"where s.auditStatus='"+HotelEnum.PASS_EXAM+"' and s.useStatus = '"+HotelEnum.USE_VALID+"' and s.isOff = '"+HotelEnum.ENABLE+"'" ;
				query = session.createQuery(preHql + hql);
				page.setTotalRowNum((Long) query.list().get(0));
				return page;
		}});
	}
	
	/** 
	 * Description :  根据酒店的星级获得酒店的区域信息
	 * Create Date: 2010-12-8下午04:39:48 by liangxj  Modified Date: 2010-12-8下午04:39:48 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<String[]> getDistrictListShowByStarLevel(final String districtId,final String starLevel,final Short districtLevel) throws Exception {
		return (List<String[]>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				Integer dLevelLength = districtLevel*2 + 2; //区域长度
				
				StringBuilder sql = new StringBuilder();
				sql.append("select rpad(t.did,6,'0'), d.district_name, t.num ");
				sql.append("from BASE_DISTRICT d, ( ");
					sql.append("select substr(h.district_id,1,").append(dLevelLength).append(") did, count(h.hotel_id) num ");
					sql.append("from SERVE_HOTEL h ");
					sql.append("where h.STAR like :starLevel ");
					sql.append("and h.use_status =:useStatus and h.is_off =:isOff and h.audit_status =:auditStatus ");
					sql.append("group by substr(h.district_id,1,").append(dLevelLength).append(")) t ");
				sql.append("where d.district_id =  rpad(t.did,6,'0') ");
				
				//过滤指定区域
				if(StringUtils.hasLength(districtId)) {
					sql.append(" and d.district_id in (:districtId) ");
				}
				
				//排序条件
				sql.append(" order by d.district_name");
				
				Query query = session.createSQLQuery(sql.toString());
				
				//过滤酒店状态
				query.setString("useStatus", HotelEnum.USE_VALID);
				query.setString("isOff", HotelEnum.ENABLE);
				query.setString("auditStatus", HotelEnum.PASS_EXAM);
				
				//过滤酒店星级
				if(StringUtils.hasLength(starLevel)) {
					query.setString("starLevel", "%"+starLevel+"%");
				} else {
					query.setString("starLevel", "%");
				}
				
				//过滤指定区域
				if(StringUtils.hasLength(districtId)) {
					query.setParameterList("districtId", districtId.split(","));
				}
				
				return query.list();
			}
		});
	}
	
	/** 
	 * Description :  根据主键，获得酒店的详细信息，包括基本信息、客房信息、会议室信息等
	 * Create Date: 2010-12-10下午04:31:14 by liangxj  Modified Date: 2010-12-10下午04:31:14 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Hotel getHotelAllInfo(final String objId) throws Exception {
		List<Hotel> list = getHotelAllInfoList(objId);
		if(list != null && list.size() > 0)
			return list.get(0);
		return null;
	}
	
	/** 
	 * Description :  根据主键，获得酒店的详细信息，包括基本信息、客房信息、会议室信息等
	 * Create Date: 2010-12-10下午04:31:14 by liangxj  Modified Date: 2010-12-10下午04:31:14 by liangxj
	 * @param   objIds以逗号分隔
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<Hotel> getHotelAllInfoList(final String objIds) throws Exception {
		return (List<Hotel>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				
				//查询供应商信息
				String hql = "from Hotel as s left join fetch s.district as d " +
									"left join fetch s.guestRooms as qr " +
									"left join fetch s.meetingRooms as mr " +
								"where s.objId in (:objId) ";
				
				Query query = session.createQuery(hql);
				query.setParameterList("objId", objIds.split(","));
				
				Set<Hotel> hotelSet = new HashSet<Hotel>(query.list());
				List<Hotel> hotelList = new ArrayList<Hotel>();
				hotelList.addAll(hotelSet);
				return hotelList;
		}});
	}
}
