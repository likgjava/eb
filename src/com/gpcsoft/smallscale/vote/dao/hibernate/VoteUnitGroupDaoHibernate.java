package com.gpcsoft.smallscale.vote.dao.hibernate;

import java.sql.SQLException;
import java.util.List;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.smallscale.vote.dao.VoteUnitGroupDao;
import com.gpcsoft.smallscale.vote.domain.VoteUnitGroup;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

@Repository
public class VoteUnitGroupDaoHibernate extends BaseGenericDaoHibernate<VoteUnitGroup> implements VoteUnitGroupDao {

	/**
	 * 删除主题下的评选组
	 */
	@SuppressWarnings("unchecked")
	public void removeVoteUnitGroupOfTemplate(final String templateId) {
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				StringBuilder hql = new StringBuilder();
				hql.append("delete from VoteUnitGroup ug where ug.voteTemplate.objId = :templateObjId");
				Query query = session.createQuery(hql.toString());
				query.setString("templateObjId", templateId);
				return query.executeUpdate();
			}
		});
	}

	/**
	 * 根据主题Id(templateId)获取评选组列表
	 */
	@SuppressWarnings("unchecked")
	public List<VoteUnitGroup> getVoteUnitGroupListByTemplateId(final String templateId) {
		return (List<VoteUnitGroup>) getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				StringBuilder hql = new StringBuilder();
				hql.append("from VoteUnitGroup ug where ug.voteTemplate.objId = :templateId order by ug.createTime");
				Query query = session.createQuery(hql.toString());
				query.setString("templateId", templateId);
				return query.list();
			}
		});
	}
}
