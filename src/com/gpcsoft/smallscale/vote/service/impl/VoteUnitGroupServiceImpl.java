package com.gpcsoft.smallscale.vote.service.impl;

import java.util.List;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.gpcsoft.smallscale.vote.manager.VoteUnitGroupManager;
import com.gpcsoft.smallscale.vote.service.VoteUnitGroupService;
import com.gpcsoft.smallscale.vote.dao.VoteUnitGroupDao;
import com.gpcsoft.smallscale.vote.domain.VoteUnitGroup;

@Service 
public class VoteUnitGroupServiceImpl extends BaseGenericServiceImpl<VoteUnitGroup> implements VoteUnitGroupService {

	@SuppressWarnings("unused")
	@Autowired(required=true) @Qualifier("voteUnitGroupManagerImpl")
	private VoteUnitGroupManager voteUnitGroupManager;
	
	@Autowired(required=true)  @Qualifier("voteUnitGroupDaoHibernate")
	private VoteUnitGroupDao voteUnitGroupDaoHibernate;

	/**
	 * 根据主题Id(templateId)获取评选组列表
	 */
	public List<VoteUnitGroup> getVoteUnitGroupListByTemplateId(String templateId) {
		return voteUnitGroupDaoHibernate.getVoteUnitGroupListByTemplateId(templateId);
	}

}
