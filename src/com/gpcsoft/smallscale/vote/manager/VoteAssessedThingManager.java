package com.gpcsoft.smallscale.vote.manager;

import java.util.List;

import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.smallscale.vote.domain.VoteAssessedThing;

public interface VoteAssessedThingManager extends BaseManager<VoteAssessedThing> {
	
	/**
	 * Description :  获取主题下的评选单位
	 * Create Date: 2011-2-21下午02:59:48 by zhaojf  Modified Date: 2011-2-21下午02:59:48 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<VoteAssessedThing> findAssessedThingOfTemplate(String templateId);

}
