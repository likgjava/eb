package com.gpcsoft.smallscale.vote.service;
import java.util.List;
import java.util.Map;

import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.smallscale.vote.domain.VoteAssessed;

public interface VoteAssessedService extends BaseGenericService<VoteAssessed> {
	
	/**
	 * Description :  展示投票指标
	 * Create Date: 2011-2-24上午11:02:09 by zhaojf  Modified Date: 2011-2-24上午11:02:09 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public Map<String, Object> findUnfurlPointerOfTemplate(String templateId,String voteAssessedThingId);
	
	/**
	 * Description :  投票数据统计展示
	 * Create Date: 2011-3-1下午02:26:43 by zhaojf  Modified Date: 2011-3-1下午02:26:43 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public Map<String,Object> voteStatisticUnfurl (String templateId,String voteAssessedThingId);
	
	/**
	 * Description :  查看投票评选
	 * Create Date: 2011-2-24下午05:40:40 by zhaojf  Modified Date: 2011-2-24下午05:40:40 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public Map<String, Object> viewVoteAssessed(String objId);
	
	/**
	 * Description :  保存投票记录表
	 * Create Date: 2011-2-24上午11:10:52 by zhaojf  Modified Date: 2011-2-24上午11:10:52 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public VoteAssessed saveVoteAssessed(VoteAssessed voteAssessed);
	
	/**
	 * Description : 保存投票评分
	 * Create Date: 2011-2-24下午04:24:01 by zhaojf  Modified Date: 2011-2-24下午04:24:01 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public void saveVoteAssessedGrade(String assessedId, String[] idsResult);
	
	/**
	 * Description :  删除投票评选,并删除评分值
	 * Create Date: 2011-2-25上午11:34:04 by zhaojf  Modified Date: 2011-2-25上午11:34:04 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public void removeVoteAssessed(String objId);

	/**
	 * Description :  验证当前登录用户是否对该主题下的此单位进行过评分
	 * Create Date: 2011-2-25下午03:25:31 by zhaojf  Modified Date: 2011-2-25下午03:25:31 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public boolean isUnique(String templateId,String voteAssessedThingId,String isSingleVote);

	/**
	 * Description :  统计参与单位的投票数
	 * Create Date: 2011-2-28下午04:47:47 by zhaojf  Modified Date: 2011-2-28下午04:47:47 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<Object> findAssessedThingVoteCount(String templateId);

	/**
	 * Description :  评论展示列表(单个参选对象的评论列表)
	 * Create Date: 2011-4-28下午02:22:10 by zhaojf  Modified Date: 2011-4-28下午02:22:10 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public Page<VoteAssessed> getVoteAssessedCommentShow(Page<VoteAssessed> page,Map<String, Object> paramsMap);
	
	/**
	 * Description :  投票结果列表
	 * Create Date: 2011-4-28下午05:02:37 by zhaojf  Modified Date: 2011-4-28下午05:02:37 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<Object> getVoteResultShow(Map<String, Object> paramMap);
}
