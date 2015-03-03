package com.gpcsoft.smallscale.vote.manager;

import java.util.List;

import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.smallscale.vote.domain.VoteTemplate;

public interface VoteTemplateManager extends BaseManager<VoteTemplate> {
	
	/**
	 * Description :  生成主题编号
	 * Create Date: 2011-2-21上午11:10:55 by zhaojf  Modified Date: 2011-2-21上午11:10:55 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public String generateVoteTemplateCode();
	
	/**
	 * Description :  主题统计(主题下的评选单位的参评人数)
	 * Create Date: 2011-3-3上午10:59:34 by zhaojf  Modified Date: 2011-3-3上午10:59:34 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<Object> toTemplateStatistic(String objId);
}
