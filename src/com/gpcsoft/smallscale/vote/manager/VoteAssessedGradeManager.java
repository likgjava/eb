package com.gpcsoft.smallscale.vote.manager;

import java.util.List;

import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.smallscale.vote.domain.VoteAssessedGrade;

public interface VoteAssessedGradeManager extends BaseManager<VoteAssessedGrade> {
	
	/**
	 * Description :  查询评分结果(voteAssessedId为投票评选表Id)
	 * Create Date: 2011-2-24下午05:31:43 by zhaojf  Modified Date: 2011-2-24下午05:31:43 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<VoteAssessedGrade> findVoteAssessedGradeList(String voteAssessedId);
	
	/**
	 * Description :  删除评分值(objId为投票评选表Id)
	 * Create Date: 2011-2-25上午11:34:04 by zhaojf  Modified Date: 2011-2-25上午11:34:04 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public void removeGradeByAssessedId(String objId);

}
