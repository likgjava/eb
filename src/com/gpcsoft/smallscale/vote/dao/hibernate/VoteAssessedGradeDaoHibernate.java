package com.gpcsoft.smallscale.vote.dao.hibernate;

import java.sql.SQLException;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.smallscale.vote.dao.VoteAssessedGradeDao;
import com.gpcsoft.smallscale.vote.domain.VoteAssessedGrade;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

@Repository
public class VoteAssessedGradeDaoHibernate extends BaseGenericDaoHibernate<VoteAssessedGrade> implements VoteAssessedGradeDao {

	/**
	 * 删除评分值(objId为投票评选表Id)
	 */
	@SuppressWarnings("unchecked")
	public void removeGradeByAssessedId(final String objId) {
		getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session) throws HibernateException, SQLException {
				StringBuilder hql = new StringBuilder();
				hql.append("delete from VoteAssessedGrade vg where vg.voteAssessed.objId = :objId");
				Query query = session.createQuery(hql.toString());
				query.setString("objId", objId);
				return query.executeUpdate();
			}
		});
	}
}
