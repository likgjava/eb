package com.gpcsoft.smallscale.vote.dao;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.smallscale.vote.domain.VoteAssessedGrade;

public interface VoteAssessedGradeDao extends BaseGenericDao<VoteAssessedGrade> {

	/**
	 * Description :  删除评分值(objId为投票评选表Id)
	 * Create Date: 2011-2-25上午11:34:04 by zhaojf  Modified Date: 2011-2-25上午11:34:04 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public void removeGradeByAssessedId(String objId);
}
