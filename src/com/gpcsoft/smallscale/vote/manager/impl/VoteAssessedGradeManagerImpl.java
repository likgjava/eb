package com.gpcsoft.smallscale.vote.manager.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.smallscale.vote.dao.VoteAssessedGradeDao;
import com.gpcsoft.smallscale.vote.manager.VoteAssessedGradeManager;
import com.gpcsoft.smallscale.vote.domain.VoteAssessedGrade;

@Repository
public class VoteAssessedGradeManagerImpl extends BaseManagerImpl<VoteAssessedGrade> implements VoteAssessedGradeManager {

	@Autowired(required=true)  @Qualifier("voteAssessedGradeDaoHibernate")
	private VoteAssessedGradeDao voteAssessedGradeDaoHibernate;

	/**
	 * 查询评分结果(voteAssessedId为投票评选表Id)
	 */
	public List<VoteAssessedGrade> findVoteAssessedGradeList(
			String voteAssessedId) {
		List<VoteAssessedGrade> voteAssessedGradeList = null;
		String hql = "select vg from VoteAssessedGrade vg where vg.voteAssessed.objId = '"+voteAssessedId + "'";		
		voteAssessedGradeList = voteAssessedGradeDaoHibernate.findbyHql(hql);
		return voteAssessedGradeList;
	}

	/**
	 *  删除评分值(objId为投票评选表Id)
	 */
	public void removeGradeByAssessedId(String objId) {
		voteAssessedGradeDaoHibernate.removeGradeByAssessedId(objId);
	}

}
