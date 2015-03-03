package com.gpcsoft.smallscale.vote.service.impl;

import java.util.List;
import java.util.Map;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.gpcsoft.smallscale.vote.manager.VoteTemplateMediumManager;
import com.gpcsoft.smallscale.vote.service.VoteTemplateMediumService;
import com.gpcsoft.smallscale.vote.dao.VoteTemplateMediumDao;
import com.gpcsoft.smallscale.vote.domain.VoteTemplateMedium;
import com.gpcsoft.srplatform.auth.domain.Role;
import com.gpcsoft.srplatform.auth.manager.RoleManager;

@Service 
public class VoteTemplateMediumServiceImpl extends BaseGenericServiceImpl<VoteTemplateMedium> implements VoteTemplateMediumService {

	@SuppressWarnings("unused")
	@Autowired(required=true) @Qualifier("voteTemplateMediumManagerImpl")
	private VoteTemplateMediumManager voteTemplateMediumManager;
	
	@Autowired(required=true)  @Qualifier("voteTemplateMediumDaoHibernate")
	private VoteTemplateMediumDao voteTemplateMediumDao;
	
	@Autowired(required=true) @Qualifier("roleManagerImpl")
	private RoleManager roleManager;

	/**
	 * 从机构库里赛选媒体
	 */
	public Page<OrgInfo> getMediumOrgList(Page<OrgInfo> page,Map<String, Object> paramsMap) {
		Role mediumRole = roleManager.getRoleByType(OrganizationEnum.ROLE_MEDIUM);//媒体角色
		paramsMap.put("roleId", mediumRole.getObjId());
		return voteTemplateMediumDao.getMediumOrgList(page,paramsMap);
	}

	/**
	 * 媒体hql操作
	 */
	public boolean operateMediumParams(Map<String, Object> paramsMap) {
		return voteTemplateMediumDao.operateMediumParams(paramsMap);
	}

	/**
	 * 生成排序号
	 */
	public Integer generateMediumSort(Map<String, Object> paramsMap) {
		Integer mediumSort = 1;
		Integer maxSort = voteTemplateMediumDao.generateMediumSort(paramsMap);
		if(maxSort != null && maxSort > 0){
			mediumSort = maxSort + 1;
		}
		return mediumSort;
	}

	/**
	 * 合作媒体分页列表
	 */
	public Page<VoteTemplateMedium> getVoteTemplateMediumPage(Page<VoteTemplateMedium> page, Map<String, Object> paramsMap) {
		return voteTemplateMediumDao.getVoteTemplateMediumPage(page, paramsMap);
	}

	/**
	 * 合作媒体列表
	 */
	public List<VoteTemplateMedium> getVoteTemplateMediumList(Map<String, Object> paramsMap) {
		String voteTemplateId = (String) paramsMap.get("voteTemplateId");
		Integer rownum = (Integer) paramsMap.get("rownum");
		
		String hql = "from VoteTemplateMedium vtm where vtm.voteTemplate.objId='"+voteTemplateId+"'";
		if(rownum != null){
			hql = hql + " and rownum <="+rownum;
		}
		hql = hql+" order by vtm.mediumSort asc,vtm.createTime asc";
		List<VoteTemplateMedium> voteTemplateMediumList = voteTemplateMediumDao.findbyHql(hql);
		return voteTemplateMediumList;
	}

}
