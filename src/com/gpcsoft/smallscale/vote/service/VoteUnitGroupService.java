package com.gpcsoft.smallscale.vote.service;
import java.util.List;

import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.smallscale.vote.domain.VoteUnitGroup;

public interface VoteUnitGroupService extends BaseGenericService<VoteUnitGroup> {
	/**
	 * Description :  根据主题Id(templateId)获取评选组列表
	 * Create Date: 2011-4-26下午02:20:39 by zhaojf  Modified Date: 2011-4-26下午02:20:39 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<VoteUnitGroup> getVoteUnitGroupListByTemplateId(String templateId);

}
