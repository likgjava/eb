package com.gpcsoft.smallscale.vote.dao;

import java.util.List;
import java.util.Map;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.smallscale.vote.domain.VoteAssesseExpert;
import com.gpcsoft.smallscale.vote.domain.VoteAssessedThing;
import com.gpcsoft.smallscale.vote.domain.VoteTemplate;
import com.gpcsoft.smallscale.vote.domain.VoteUnitGroup;

public interface VoteTemplateDao extends BaseGenericDao<VoteTemplate> {

	/**
	 * Description :  获取当前主题最大的编号
	 * Create Date: 2011-2-21上午11:27:20 by zhaojf  Modified Date: 2011-2-21上午11:27:20 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public String getMaxVoteTemplateCode();
	
	/**
	 * Description :  主题统计(主题下的评选单位的参评人数)
	 * Create Date: 2011-3-3上午10:59:34 by zhaojf  Modified Date: 2011-3-3上午10:59:34 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<Object> toTemplateStatistic(String objId);
	
	/**
	 * Description :  获取主题下的评选组
	 * Create Date: 2011-4-25下午01:32:46 by zhaojf  Modified Date: 2011-4-25下午01:32:46 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	Page<VoteUnitGroup> getVoteUnitGroupList(Page<VoteUnitGroup> page, Map<String, Object> paramsMap);
	
	/**
	 * Description :  主题下的参选对象列表
	 * Create Date: 2011-5-5下午04:29:33 by zhaojf  Modified Date: 2011-5-5下午04:29:33 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public Page<VoteAssessedThing> getVoteAssessedObjectList(Page<VoteAssessedThing> page,Map<String, Object> paramsMap);
	
	/**
	 * Description :  获取参选对象的所属机构
	 * Create Date: 2011-5-6上午11:06:56 by zhaojf  Modified Date: 2011-5-6上午11:06:56 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public Page<OrgInfo> getVoteBelongsOrgInfoList(Page<OrgInfo> page,Map<String, Object> paramsMap);
	
	/**
	 * Description :  获取主题下评审专家列表
	 * Create Date: 2011-5-9上午10:21:04 by zhaojf  Modified Date: 2011-5-9上午10:21:04 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public Page<VoteAssesseExpert> getVoteAssesseExpertList(Page<VoteAssesseExpert> page, Map<String, Object> paramsMap);
}
