package com.gpcsoft.smallscale.business.dao.hibernate;

import java.sql.SQLException;
import java.util.HashMap;
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
import com.gpcsoft.smallscale.business.dao.CommunityDao;
import com.gpcsoft.smallscale.business.domain.Community;

@Repository
public class CommunityDaoHibernate extends BaseGenericDaoHibernate<Community> implements CommunityDao {

	/** 
	 * Description :  删除
	 * Create Date: 2010-12-8上午11:02:07 by yucy  Modified Date: 2010-12-8上午11:02:07 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Integer delCommunity(final String[] communityIdArray) throws Exception {
		return (Integer) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String hql="delete from Community c where c.objId in (:communityIds)";
				Query query = session.createQuery(hql);
				query.setParameterList("communityIds", communityIdArray);
				return query.executeUpdate();
		}});
	}

	/** 
	 * Description :  最新的特色的推荐的
	 * Create Date: 2010-12-11上午10:50:37 by yucy  Modified Date: 2010-12-11上午10:50:37 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> getNewAndSpecialAndRecommended( final Map<String, Object> param) throws Exception {
		return (Map<String, Object>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Map<String, Object> result = new HashMap<String, Object>();
				
				String hqlNew=" from Community where isDisplay = :isDisplay order by createTime desc ";
				Query query = session.createQuery(hqlNew);
				query.setBoolean("isDisplay", true);
				query.setMaxResults(16);
				result.put("newList", query.list());
				
				String hqlSpecail=" from Community where isSpecial = :isSpecial and isDisplay = :isDisplay ";
				query = session.createQuery(hqlSpecail);
				query.setBoolean("isSpecial", true);
				query.setBoolean("isDisplay", true);
				query.setMaxResults(16);
				result.put("specialList", query.list());
				
				String hqlRecommended=" from Community where isRecommended = :isRecommended and isDisplay = :isDisplay  ";
				query = session.createQuery(hqlRecommended);
				query.setBoolean("isRecommended", true);
				query.setBoolean("isDisplay", true);
				query.setMaxResults(16);
				result.put("recommendedList", query.list());
				
				return result;
		}});
	}
	
	/** 
	 * Description :  根据参数获得社区的展示信息列表
	 * Create Date: 2011-2-16下午05:50:15 by liangxj  Modified Date: 2011-2-16下午05:50:15 by liangxj
	 * @param   page 分页信息 paramsMap 查询条件：是否推荐、是否特色，排序默认为时间倒序
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Page<Community> getCommunityListForShow(final Page<Community> page,final Map<String, Object> paramsMap) throws Exception {
		return (Page<Community>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
				throws HibernateException, SQLException {
				String isSpecial  = (String) paramsMap.get("isSpecial"); //是否是特色
				String isRecommended = (String) paramsMap.get("isRecommended"); //是否是推荐
				
				String bidForRangeCode = (String) paramsMap.get("bidForRangeCode"); //投标品目code
				
				//String bidForRangeName = (String) paramsMap.get("bidForRangeName"); //投标品目name
				
				//查询商品信息
				String preHql= "select ct from Community as ct left join ct.orgInfo o left join o.company c " +
									"where ct.isDisplay = true ";
				StringBuilder hql = new StringBuilder();
				
				//是否是特色
				if(StringUtils.hasLength(isSpecial)) {
					hql.append(" and ct.isSpecial = ").append(isSpecial);
				}
				if(StringUtils.hasLength(isRecommended)){
					hql.append(" and ct.isRecommended = ").append(isRecommended);
				}
				
				//过滤communityName
				String communityName = (String) paramsMap.get("communityName");
				if(StringUtils.hasLength(communityName)){
					hql.append(" and ct.communityName like '%").append(communityName).append("%'");
				}
				
				//过滤品目
				if(StringUtils.hasLength(bidForRangeCode)){
					hql.append(" and (ct.tenderCategorys is null ");
					for(String bidForRangeCodeCell:  bidForRangeCode.split(",")){
						hql.append(" or ct.tenderCategorys like ").append("'%"+bidForRangeCodeCell+"%'");
					}
					hql.append(" )");
				}
				
				//过滤供应商已经有的 
				String orgNotIn = (String) paramsMap.get("orgNotIn"); //供应商已经有的
				String orgNotIn_Id = (String) paramsMap.get("orgNotIn_Id"); //供应商已经有的id
				if(StringUtils.hasLength(orgNotIn)){
					hql.append(" and (ct.orgInfo.objId != '" + orgNotIn_Id + "' or ct.orgInfo.objId is null ) and  ct.objId not in (select oc.community.objId from OrgCommunity oc where oc.orgInfo.objId = '"+orgNotIn_Id+"' )");
				}
				
				//过滤非公开的
				String isPublic = (String) paramsMap.get("isPublic"); //供应商已经有的id
				if(StringUtils.hasLength(isPublic)){
					hql.append(" and ct.isPublic ='1'");
				}
				
				//显示机构所有的的
				String company_path = (String) paramsMap.get("company_path"); //供应商已经有的id
				if(StringUtils.hasLength(company_path)){
					hql.append(" or (ct.orgInfo.objId is not null and (  c.path like '%"+company_path+"%' or '"+company_path+"' like '%'||c.path||'%' ) ) ");
				}
				
				//排序条件
				StringBuilder orderHql = new StringBuilder(" order by ct.createTime desc ");
				
				Query query = session.createQuery(preHql + hql + orderHql);
				query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());
		
				List<Community> goodsList=query.list();
				page.setData(goodsList);
				
				//查询总数
				preHql = "select count(ct.objId) from Community as ct left join ct.orgInfo o left join o.company c where ct.isDisplay = true ";
				query = session.createQuery(preHql + hql);
				page.setTotalRowNum((Long) query.list().get(0));
				return page;
		}});
	}
}
