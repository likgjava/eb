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
import com.gpcsoft.smallscale.business.dao.ForumTopicDao;
import com.gpcsoft.smallscale.business.domain.ForumTopic;

@Repository
public class ForumTopicDaoHibernate extends BaseGenericDaoHibernate<ForumTopic> implements ForumTopicDao {
	
	/**
	 * 是否显示主题
	 */
	@SuppressWarnings("unchecked")
	public Boolean updateShowStatus( final Map<String, Object> param) throws Exception {
		return (Boolean) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
					String Hql = " update ForumTopic ft set ft.isShow = :showType where ft.objId = :forumTopicrId ";
					Query query = session.createQuery(Hql);
					query.setBoolean("showType", ("true".equals((String)param.get("showType"))?false:true));
					query.setParameter("forumTopicrId",(String)param.get("forumTopicrId") );
					return query.executeUpdate()>0?true:false;
				}
		});
	}
	
	/**
	 * 是否置顶(isTop),是否显示(isShow),是否精华(isElite)
	 */
	@SuppressWarnings("unchecked")
	public Boolean updateTopOrShowOrElite( final Map<String, Object> param) throws Exception {
		return (Boolean) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
					String Hql = " update ForumTopic ft set ft.";
					Hql = Hql + (String) param.get("setType");
					Hql = Hql + " = :isStatusVale  where ft.objId = :forumTopicrId ";
					Query query = session.createQuery(Hql);
					query.setBoolean("isStatusVale", ("true".equals((String)param.get("isStatusVale"))?false:true));
					query.setParameter("forumTopicrId",(String)param.get("forumTopicrId") );
					return query.executeUpdate()>0?true:false;
				}
		});
	}

	/**
	 * 删除帖子主题,并删除此主题下的所有的回复帖子
	 */
	@SuppressWarnings("unchecked")
	public void removeTopicAndReply(final String objId) {
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				StringBuilder hql = new StringBuilder();
				hql.append("delete from ForumReply fr where fr.topic.objId = :forumTopicId");
				Query query = session.createQuery(hql.toString());
				query.setString("forumTopicId", objId);
				return query.executeUpdate();
			}
		});
	}

	/**
	 * 主题的回复数、发布机构的发帖数、发布机构发布的精英主题
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> getReplyAndOrgReplyAndOrgTop(final ForumTopic forumTopic){
		return (Map<String, Object>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Map<String, Object> result = new HashMap<String, Object>();
				
				//主题的回复数
				String hqlNew=" select count(*) from ForumReply fr where fr.topic.objId = :topicId and fr.isShow = '1'";
				Query query = session.createQuery(hqlNew);
				query.setString("topicId", forumTopic.getObjId());
				result.put("replyTopicCount", query.list().get(0).toString());
				
				//发布机构的发帖数
				String hqlSpecail=" select count(*) from ForumReply fr,ForumTopic ft where fr.topic.objId = ft.objId and fr.orginfo.objId = :orgInfoId and fr.isShow = '1' and ft.isShow = '1'";
				query = session.createQuery(hqlSpecail);
				query.setString("orgInfoId", forumTopic.getOrginfo().getObjId());
				result.put("orgReplyNum", query.list().get(0).toString());
				
				//发布机构发布的精英主题
				String hqlRecommended=" select count(*) from ForumTopic ft where ft.orginfo.objId = :orgInfoId and ft.isElite = :isElite and ft.isShow = '1'";
				query = session.createQuery(hqlRecommended);
				query.setString("orgInfoId", forumTopic.getOrginfo().getObjId());
				query.setBoolean("isElite", true);
				result.put("orgTopNum", query.list().get(0).toString());
				
				return result;
		}});
	}

	/** 
	 * Description :  获取社区主题列表    商圈最新的主题
	 * Create Date: 2011-3-21上午11:52:31 by likg  Modified Date: 2011-3-21上午11:52:31 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Page<ForumTopic> getForumTopicList(final Page<ForumTopic> page, final Map<String, Object> paramsMap) throws Exception {
		return (Page<ForumTopic>) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String communityId = (String) paramsMap.get("communityId"); //社区id
				String isElite = (String) paramsMap.get("isElite"); //精华主题
				String order = (String) paramsMap.get("order");//排序
				
				String queryhql = "from ForumTopic f left join fetch f.orginfo o where 1=1 ";
				String queryCountStr = "select count(f.objId) from ForumTopic f where 1=1 ";
				String orderhql = " order by f.createTime desc";
				StringBuilder hql = new StringBuilder();
				
				//过滤条件
				hql.append(" and f.isShow = '1' ");
				
				if(StringUtils.hasLength(isElite) && "true".equals(isElite)){//主题为精华主题
					hql.append("and f.isElite = '1'");
				}
				
				if(StringUtils.hasLength(communityId)) {
					hql.append(" and f.community.objId = '").append(communityId).append("' ");
				}
				
				if(StringUtils.hasLength(order) && "order".equals(order)){//添加排序字段
					hql.append(" order by f.isElite desc,f.isTop desc,f.createTime desc");
				}
				
				
				Query query = session.createQuery(queryhql + hql + orderhql);
				query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());
				List<ForumTopic> bulletinList = query.list();
				page.setData(bulletinList);
				
				query = session.createQuery(queryCountStr + hql);
				page.setTotalRowNum((Long) query.list().get(0));
				
				return page;
			}
		});
	}

}
