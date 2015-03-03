package com.gpcsoft.smallscale.vote.manager;

import java.util.List;
import java.util.Map;

import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.smallscale.vote.domain.VoteAssessed;

public interface VoteAssessedManager extends BaseManager<VoteAssessed> {
	
	/**
	 * Description :  展示投票指标
	 * Create Date: 2011-2-24上午11:02:09 by zhaojf  Modified Date: 2011-2-24上午11:02:09 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public Map<String, Object> findUnfurlPointerOfTemplate(String templateId,String voteAssessedThingId);
	
	/**
	 * Description : 单一单位的票数数据统计展示
	 * Create Date: 2011-3-1下午02:26:43 by zhaojf  Modified Date: 2011-3-1下午02:26:43 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public Map<String,Object> voteStatisticUnfurl (String templateId,String voteAssessedThingId);
	
	/**
	 * Description :  验证当前登录用户是否对该主题下的此单位进行过评分
	 * Create Date: 2011-2-25下午03:28:38 by zhaojf  Modified Date: 2011-2-25下午03:28:38 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public boolean isUnique(String templateId, String voteAssessedThingId,String userId,String isSingleVote);
	
	/**
	 * Description :   统计参与单位的投票数
	 * Create Date: 2011-3-2下午02:47:39 by zhaojf  Modified Date: 2011-3-2下午02:47:39 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<Object> countVoteAssessedNum(String templateId);

}
