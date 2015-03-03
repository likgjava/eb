package com.gpcsoft.pubservice.application.message.dao.hibernate;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.pubservice.application.message.dao.MessageDao;
import com.gpcsoft.pubservice.application.message.domain.Message;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

@Repository
public class MessageDaoHibernate extends BaseGenericDaoHibernate<Message> implements MessageDao {

	/** 
	 * Description :  获得指定用户的【未读的和未提示的】站内信的数量
	 * Create Date: 2010-9-30上午08:18:16 by likg  Modified Date: 2010-9-30上午08:18:16 by likg
	 * @param   userId:接收人Id
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Long> getMessageNum(final String userId, final Map<String, Object> param) throws Exception {
		Map<String, Long> messageNumMap = new HashMap<String, Long>();
		
		//查询未读和未提示的站内信数量
		String notReadHql = "select count(*) from Message m where m.receiver = ? and m.isRead = false and m.isSave = false and (m.sender != ? or m.sender is null)";
		String notTipHql = "select count(*) from Message m where m.receiver = ? and m.isRead = false and m.isSave = false and m.isTip = false and (m.sender != ? or m.sender is null)";
		
		List notReadList = this.getHibernateTemplate().find(notReadHql, userId, userId);
		List notTipList = this.getHibernateTemplate().find(notTipHql, userId, userId);
		
		if(notReadList!=null && notTipList!=null){
			messageNumMap.put("notReadMessageNum", (Long)notReadList.get(0));
			messageNumMap.put("notTipMessageNum", (Long)notTipList.get(0));
		}else{
			messageNumMap.put("notReadMessageNum", 0L);
			messageNumMap.put("notTipMessageNum", 0L);
		}
		
		return messageNumMap;
	}

	/** 
	 * Description :  向指定的多个用户批量发送站内信
	 * Create Date: 2010-9-30上午08:18:16 by likg  Modified Date: 2010-9-30上午08:18:16 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public void saveMessageBatch(final Message message, final String[] receiverObjIds, final long time) throws Exception {
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException 
			{
				StringBuilder sql = new StringBuilder();
				sql.append("insert into EPS_PUB_MESSAGE (MSG_ID,TITLE,CONTENT,TYPE,SENDER,SENDER_NAME,SEND_TIME,RECEIVER,RECEIVER_NAME,IS_READ,IS_SAVE,CREDATE) ");
				sql.append("select u.usr_id || :mm, :title, :content, :type, :sender, :sender_name, :send_time, u.usr_id, u.usr_name, :is_read, :is_save, :credate ");
				sql.append("from AUTH_USER u ");
				sql.append("where u.usr_id in (");
				for(String str : receiverObjIds)
				{
					if(str != null)
					{
						sql.append("'" + str + "',");
					}
				}
				sql.deleteCharAt(sql.length()-1);
				sql.append(")");
				
				Query query = session.createSQLQuery(sql.toString());
				
				query.setParameter("mm", time);
				query.setParameter("title", message.getTitle());
				query.setParameter("content", message.getContent());
				query.setParameter("type", message.getType());
				query.setParameter("sender", message.getSender());
				query.setParameter("sender_name", message.getSenderName());
				query.setParameter("send_time", message.getSendTime());
				query.setParameter("is_read", message.getIsRead());
				query.setParameter("is_save", message.getIsSave());
				query.setParameter("credate", message.getCreateTime());
				
				query.executeUpdate();
				return true;
			}
		});
	}

	/** 
	 * Description :  获得指定用户的未提示的站内信
	 * Create Date: 2010-11-19上午09:13:32 by likg  Modified Date: 2010-11-19上午09:13:32 by likg
	 * @param   userId:接收人Id
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List<Message> getNotTipMessage(final String userId) throws Exception {
		return (List<Message>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String hql = "from Message m " +
						" where m.receiver = :receiverId " +
						" and m.isSave = false " +
						" and m.isTip = false " +
						" and (m.sender != :receiverId or m.sender is null)" +
						" order by m.createTime desc";
				Query query = session.createQuery(hql);
				query.setParameter("receiverId", userId);
				
				return query.list();
			}
		});
	}
	
	/** 
	 * Description :  修改指定用户的未读的站内信的提示状态
	 * Create Date: 2010-11-19上午09:13:32 by likg  Modified Date: 2010-11-19上午09:13:32 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public void updateMessageTipStatus(final String receiverId) throws Exception {
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				String hql = "update Message m set m.isTip = true where m.receiver = :receiverId and m.isTip = false";
				Query query = session.createQuery(hql);
				query.setParameter("receiverId", receiverId);
				query.executeUpdate();
				
				return null;
			}
		});
	}
}
