package com.gpcsoft.smallscale.vote.dao;

import java.util.List;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.smallscale.vote.domain.VoteUnitGroup;

public interface VoteUnitGroupDao extends BaseGenericDao<VoteUnitGroup> {
	/**
	 * Description :  删除主题下的评选组
	 * Create Date: 2011-4-26上午10:41:35 by zhaojf  Modified Date: 2011-4-26上午10:41:35 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public void removeVoteUnitGroupOfTemplate(String templateId);
	
	/**
	 * Description :  根据主题Id(templateId)获取评选组列表
	 * Create Date: 2011-4-26下午02:20:39 by zhaojf  Modified Date: 2011-4-26下午02:20:39 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<VoteUnitGroup> getVoteUnitGroupListByTemplateId(String templateId);

}
