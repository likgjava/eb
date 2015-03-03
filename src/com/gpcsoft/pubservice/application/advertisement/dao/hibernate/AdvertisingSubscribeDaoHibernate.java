package com.gpcsoft.pubservice.application.advertisement.dao.hibernate;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.gpcsoft.bizplatform.base.common.util.StringUtils;
import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.pubservice.application.advertisement.dao.AdvertisingSubscribeDao;
import com.gpcsoft.pubservice.application.advertisement.domain.AdvertisingSubscribe;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

@Repository
public class AdvertisingSubscribeDaoHibernate extends BaseGenericDaoHibernate<AdvertisingSubscribe> implements AdvertisingSubscribeDao {

	/**
	 * 更改使用状态或审核状态
	 */
	@SuppressWarnings("unchecked")
	public void modifyUseORAuditStatus(final Map<String, Object> paramMap) throws Exception {
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
					String adverSubscribeIds = (String) paramMap.get("adverSubscribeIds");
					String Hql = " update AdvertisingSubscribe ad set ad.";
					Hql = Hql + (String) paramMap.get("field");
					Hql = Hql + " = :estatus where ad.objId ";
					if("" != adverSubscribeIds && adverSubscribeIds != null){
						Hql = Hql + "in ("+adverSubscribeIds+")";
					}else{
						Hql = Hql + "= :objId ";
					}
					Query query = session.createQuery(Hql);
					query.setParameter("estatus", paramMap.get("estatus"));
					if("" == adverSubscribeIds || adverSubscribeIds==null){
						query.setParameter("objId",(String)paramMap.get("objId") );
					}
					
					return query.executeUpdate();
				}
		});
	}

	/**
	 * 根据广告位Id获取广告订阅对象列表
	 */
	@SuppressWarnings("unchecked")
	public List<AdvertisingSubscribe> getAdverSubscribeByPositionId(final Map<String, Object> paramsMap) throws Exception {
		return (List<AdvertisingSubscribe>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				StringBuilder hql = new StringBuilder();
				hql.append("from AdvertisingSubscribe ad where ad.advertisingPosition.objId = :adverPositionId");
				if(StringUtils.hasLength((String)paramsMap.get("useStatus"))){
					if("!=".equals((String)paramsMap.get("useStatusOperType"))){
						//取得非报废广告
						hql.append(" and ad.useStatus != :useStatus");
					}else{
						//取得有效广告
						hql.append(" and ad.useStatus = :useStatus");
					}
				}
				if(StringUtils.hasLength((String)paramsMap.get("auditStatus"))){
					hql.append(" and ad.auditStatus = :auditStatus");
				}
				hql.append(" order by ad.sort,ad.verifyTime,ad.createTime ");
				Query query = session.createQuery(hql.toString());
				query.setParameter("adverPositionId", paramsMap.get("adverPositionId"));
				if(StringUtils.hasLength((String)paramsMap.get("useStatus"))){
					query.setParameter("useStatus", paramsMap.get("useStatus"));
				}
				if(StringUtils.hasLength((String)paramsMap.get("auditStatus"))){
					query.setParameter("auditStatus", paramsMap.get("auditStatus"));
				}
				return query.list();
			}
		});
	}

	/**
	 * 更改广告跑马灯排序
	 */
	@SuppressWarnings("unchecked")
	public void updateSort(final Long sort, final String positionId, final boolean isToUp) throws Exception {
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException 
			{
				StringBuilder hql = new StringBuilder();
				hql.append("from AdvertisingSubscribe ad where 1=1 and ad.advertisingPosition.objId='").append(positionId).append("'");
				hql.append(" and ad.useStatus = '01' and ad.auditStatus = '02'");
				
				//过滤条件和排序条件
				if(isToUp){
					hql.append(" and ad.sort <= ").append(sort);
					hql.append(" order by ad.sort desc ");
				}else{
					hql.append(" and ad.sort >= ").append(sort);
					hql.append(" order by ad.sort asc ");
				}
				
				Query query = session.createQuery(hql.toString());
				query.setFirstResult(0).setMaxResults(2);
		
				List<AdvertisingSubscribe> sdvertisingSubscribeList = query.list();
				if(sdvertisingSubscribeList!=null && sdvertisingSubscribeList.size()==2){
					AdvertisingSubscribe sourceRecommendGoods = sdvertisingSubscribeList.get(0);
					AdvertisingSubscribe targetRecommendGoods = sdvertisingSubscribeList.get(1);
					
					Long tempSort = sourceRecommendGoods.getSort();
					sourceRecommendGoods.setSort(targetRecommendGoods.getSort());
					targetRecommendGoods.setSort(tempSort);
				}
				return null;
			}	
		});
	}

	/**
	 * 返回广告最大排序号 
	 */
	public Long adverSubscribeMaxSort() throws Exception {
		return (Long) getHibernateTemplate().find("select max(s.sort) from AdvertisingSubscribe s").get(0);
	}
}
