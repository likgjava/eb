package com.gpcsoft.smallscale.business.dao.hibernate;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.smallscale.business.dao.ForumReplyDao;
import com.gpcsoft.smallscale.business.domain.ForumReply;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

@Repository
public class ForumReplyDaoHibernate extends BaseGenericDaoHibernate<ForumReply> implements ForumReplyDao {

	/** 
	 * Description : 更改帖子的显示状态(true：显示,false：不显示)
	 * Create Date: 2011-2-25下午15:30:27 by zhongq  Modified Date: 2011-2-25下午15:30:27 by zhongq
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Boolean isUnfurlTopicReply( final Map<String, Object> param){
		return (Boolean) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
					String Hql = " update ForumReply fr set fr.isShow = :isShow where fr.objId = :objId ";
					Query query = session.createQuery(Hql);
					query.setBoolean("isShow", ("true".equals((String)param.get("isShow"))?false:true));
					query.setParameter("objId",(String)param.get("objId"));
					return query.executeUpdate()>0?true:false;
				}
		});
	}

	/**
	 * 获取回帖列表，及回帖机构信息、回帖机构发帖状况
	 */
	@SuppressWarnings("unchecked")
	public Page<Object> getTopicReplyListForShow(final Page<Object> page,final Map<String, Object> paramsMap) {
		return (Page<Object>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				
				String topicId = (String) paramsMap.get("topicId");//主题Id
				
				String preSql = "select org.org_info_id,org.org_logo,"+
										"org.org_name,"+
										"org.create_time as orgTime,"+
										"efr.reply_id,"+
										"efr.create_time as efrTime,"+
										"efr.content,"+
										"'1',"+
										"'2',"+
										"efr.attachment,"+
										"org.BUYER_ID,"+
										"org.SUPPLIER_ID,"+
										"'' "+
										"from ecp_forum_reply efr,org_info org "+
										"where efr.org_info_id = org.org_info_id "+
										"and efr.topic_id = '"+topicId+
										"' and efr.is_show = 1 "+
										"order by efrTime desc";
				
				Query query = session.createSQLQuery(preSql);
				query.setFirstResult(page.getStart()).setMaxResults(page.getPageSize());
				
				List topicReplyShowList = query.list();
				
				//设置发布机构的发帖数、精英主题
				for(int i=0;i<topicReplyShowList.size();i++){
					Object[] topicShow = (Object[]) topicReplyShowList.get(i);
					
					//topicShow[7]为发布机构的发帖数
					String hqlSpecail=" select count(*) from ForumReply fr,ForumTopic ft where fr.topic.objId = ft.objId and fr.orginfo.objId = :orgInfoId and fr.isShow = '1' and ft.isShow = '1'";
					query = session.createQuery(hqlSpecail);
					query.setString("orgInfoId",(String) topicShow[0] );
					topicShow[7] = query.list().get(0);
					
					// topicShow[8]为发布机构发布的精英主题
					String hqlRecommended=" select count(*) from ForumTopic ft where ft.orginfo.objId = :orgInfoId and ft.isElite = :isElite and ft.isShow = '1'";
					query = session.createQuery(hqlRecommended);
					query.setString("orgInfoId",(String) topicShow[0]);
					query.setBoolean("isElite", true);
					topicShow[8] = query.list().get(0);
					
					//topicShow[12]为回帖附件名称
					if(topicShow[9] != null && !topicShow[9].equals("")){
						String hqlAttachment = " select att.viewName from Attachment att where att.objId = :attachmentId";
						query = session.createQuery(hqlAttachment);
						query.setString("attachmentId", (String) topicShow[9]);
						topicShow[12] = query.list().get(0);
					}
				}
				
				page.setData(topicReplyShowList);
				
				//查询总数
				String sql = "select count(efr.reply_id) from ecp_forum_reply efr where efr.topic_id = '"+topicId+"' and efr.is_show = 1";
				query = session.createSQLQuery(sql);
				String ss = query.list().get(0).toString();
				page.setTotalRowNum(Long.valueOf(ss));
				return page;
			}
		});
	}
}
