package com.gpcsoft.smallscale.vote.dao;

import java.util.List;
import java.util.Map;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.smallscale.vote.domain.VoteAssessed;

public interface VoteAssessedDao extends BaseGenericDao<VoteAssessed> {
	
	/**
	 * Description :  验证当前登录用户是否对该主题下的此单位进行过评分
	 * Create Date: 2011-2-25下午03:28:38 by zhaojf  Modified Date: 2011-2-25下午03:28:38 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public boolean isUnique(String templateId, String voteAssessedThingId,String userId,String isSingleVote);
	
	/**
	 * Description :  根据评选单位Id,计算该单位各指标的平均值(templateId主题Id,voteAssessedThingId评选单位Id)
	 * Create Date: 2011-3-1下午04:37:52 by zhaojf  Modified Date: 2011-3-1下午04:37:52 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<Object> countAvgGradeOfPointer(String templateId,String voteAssessedThingId);
	
	/**
	 * Description :  统计参与单位的投票数
	 * Create Date: 2011-3-2下午02:53:14 by zhaojf  Modified Date: 2011-3-2下午02:53:14 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<Object> countVoteAssessedNum(String templateId,String voteAssessedThingId);
	
	/**
	 * Description :  根据模板Id,删除指标分值记录
	 * Create Date: 2011-3-3下午04:37:26 by zhaojf  Modified Date: 2011-3-3下午04:37:26 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public void removeAssessedAndGradeByTemplateId(String templateId);

	/**
	 * Description :  根据模板Id,删除投票评分记录
	 * Create Date: 2011-3-3下午04:56:19 by zhaojf  Modified Date: 2011-3-3下午04:56:19 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public void removeAssessedByTemplateId(String templateId);
	
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
