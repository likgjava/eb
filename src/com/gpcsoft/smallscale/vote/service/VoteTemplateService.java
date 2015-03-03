package com.gpcsoft.smallscale.vote.service;
import java.util.List;
import java.util.Map;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.smallscale.vote.domain.VoteAssesseExpert;
import com.gpcsoft.smallscale.vote.domain.VoteAssessedThing;
import com.gpcsoft.smallscale.vote.domain.VotePointer;
import com.gpcsoft.smallscale.vote.domain.VoteTemplate;
import com.gpcsoft.smallscale.vote.domain.VoteUnitGroup;

public interface VoteTemplateService extends BaseGenericService<VoteTemplate> {
	
	/**
	 * Description :  生成主题编号
	 * Create Date: 2011-2-21上午11:07:26 by zhaojf  Modified Date: 2011-2-21上午11:07:26 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public String generateVoteTemplateCode();
	
	/**
	 * Description :  获取主题下的指标
	 * Create Date: 2011-2-21下午02:36:24 by zhaojf  Modified Date: 2011-2-21下午02:36:24 by zhaojf
	 * @param   objId主题id
	 * @return  
	 * @throws Exception 
	 * @Exception
	 */
	public List<VotePointer> findPointOfTemplate(String objId);
	
	/**
	 * Description :  获取主题下的评选单位
	 * Create Date: 2011-2-21下午02:38:14 by zhaojf  Modified Date: 2011-2-21下午02:38:14 by zhaojf
	 * @param   objId主题id
	 * @return  
	 * @throws Exception 
	 * @Exception
	 */
	public List<VoteAssessedThing> findAssessedThingOfTemplate(String objId);
	
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
	
	/**
	 * Description :  删除主题(确定删除时将同时删除主题下的指标、评选单位)
	 * Create Date: 2011-2-22下午02:16:17 by zhaojf  Modified Date: 2011-2-22下午02:16:17 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public void removeVoteTemplate(String objId);
	
	/**
	 * Description :  根据主题编号获取主题信息
	 * Create Date: 2011-2-22下午04:28:37 by zhaojf  Modified Date: 2011-2-22下午04:28:37 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public VoteTemplate getVoteTemplateByCode(String templateCode);

	/**
	 * Description :  主题统计(主题下的评选单位的参评人数和最终成绩)
	 * Create Date: 2011-3-3上午10:48:19 by zhaojf  Modified Date: 2011-3-3上午10:48:19 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<Object> toTemplateStatistic(String objId);
	
	/**
	 * Description :  获取主题下的评选组列表
	 * Create Date: 2011-4-25下午01:32:46 by zhaojf  Modified Date: 2011-4-25下午01:32:46 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public Page<VoteUnitGroup> getVoteUnitGroupList(Page<VoteUnitGroup> page, Map<String, Object> paramsMap);
	
}
