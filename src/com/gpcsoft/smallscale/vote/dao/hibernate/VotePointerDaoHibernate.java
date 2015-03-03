package com.gpcsoft.smallscale.vote.dao.hibernate;

import java.sql.SQLException;
import java.util.List;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.smallscale.vote.dao.VotePointerDao;
import com.gpcsoft.smallscale.vote.domain.VotePointer;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

@Repository
public class VotePointerDaoHibernate extends BaseGenericDaoHibernate<VotePointer> implements VotePointerDao {

	/**
	 * Description :  获取主题下的指标
	 * Create Date: 2011-2-21下午02:44:22 by zhaojf  Modified Date: 2011-2-21下午02:44:22 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@SuppressWarnings("unchecked")
	public List<VotePointer> findPointOfTemplate(final String templateId,final String pointerFactor) {
		return (List<VotePointer>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				StringBuilder hql = new StringBuilder();
				if(pointerFactor == null){
					hql.append(" select vp from VotePointer vp where vp.voteTemplate.objId = :templateObjId order by vp.pointerCode asc");
				}
				if(pointerFactor != null && pointerFactor.equals("unvalid")){
					hql.append("select vp from VotePointer vp where vp.voteTemplate.objId = :templateObjId and pointerFactor = '0'");
				}
				if(pointerFactor != null && pointerFactor.equals("valid")){
					hql.append("select vp from VotePointer vp where vp.voteTemplate.objId = :templateObjId and pointerFactor <> '0'");
				}
				Query query = session.createQuery(hql.toString());
				query.setString("templateObjId", templateId);
				return query.list();
			}
		});
	}

	/**
	 * Description :  删除主题下的指标
	 * Create Date: 2011-2-22下午02:20:37 by zhaojf  Modified Date: 2011-2-22下午02:20:37 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@SuppressWarnings("unchecked")
	public void removeVotePointOfTemplate(final String templateId) {
		 getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				StringBuilder hql = new StringBuilder();
				hql.append("delete from VotePointer vp where vp.voteTemplate.objId = :templateObjId");
				Query query = session.createQuery(hql.toString());
				query.setString("templateObjId", templateId);
				return query.executeUpdate();
			}
		});
	}

	/**
	 * 根据编号获取子指标
	 */
	@SuppressWarnings("unchecked")
	public List<VotePointer> findPointerOfTemplateByCode(final String pointerCode) {
		return (List<VotePointer>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				StringBuilder hql = new StringBuilder();
				hql.append(" select vp from VotePointer vp where vp.pointerCode like '");
				hql.append(pointerCode);
				hql.append("%' and vp.pointerFactor <> '0' order by vp.pointerCode asc");
				Query query = session.createQuery(hql.toString());
				return query.list();
			}
		});
	}
}
