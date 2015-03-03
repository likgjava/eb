package com.gpcsoft.pubservice.application.message.dao.hibernate;

import java.sql.SQLException;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.pubservice.application.message.dao.NoteDao;
import com.gpcsoft.pubservice.application.message.domain.Note;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

@Repository
public class NoteDaoHibernate extends BaseGenericDaoHibernate<Note> implements NoteDao {

	/**
	 * Description :  设置指定objId 留言/咨询的阅读状态为指定的状态readStatus
	 * Create Date: 2010-10-22上午05:52:12 by zhaojf  Modified Date: 2010-10-22上午05:52:12 by zhaojf
	 * @param   objId 主键, readStatus指定的阅读状态(0：未读, 1：已读)
	 * @return  
	 * @Exception
	 */
	@SuppressWarnings("unchecked")
	public void setReadStatusIsRead(final String objId, final String readStatus) throws Exception {
		this.getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(final Session session) throws HibernateException, SQLException {
				StringBuilder sql = new StringBuilder();
				sql.append("update Note note set note.isRead = :readStatus where note.objId = :objId" );
				
				Query query = session.createQuery(sql.toString());
				query.setString("readStatus", readStatus);
				query.setString("objId", objId);
				
				query.executeUpdate();
				
				return true;
			}
			
		});
	}

}
