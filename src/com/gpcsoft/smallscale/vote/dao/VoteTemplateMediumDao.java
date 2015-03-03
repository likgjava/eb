package com.gpcsoft.smallscale.vote.dao;

import java.util.Map;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.smallscale.vote.domain.VoteTemplateMedium;

public interface VoteTemplateMediumDao extends BaseGenericDao<VoteTemplateMedium> {
	
	/**
	 * Description :  从机构库里赛选媒体
	 * Create Date: 2011-5-13下午01:32:14 by zhaojf  Modified Date: 2011-5-13下午01:32:14 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public Page<OrgInfo> getMediumOrgList(Page<OrgInfo> page,Map<String, Object> paramsMap);

	/**
	 * Description :  媒体hql操作
	 * Create Date: 2011-5-16下午04:25:55 by zhaojf  Modified Date: 2011-5-16下午04:25:55 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public boolean operateMediumParams(Map<String, Object> paramsMap); 
	
	/**
	 * Description :  生成排序号
	 * Create Date: 2011-5-16下午05:05:12 by zhaojf  Modified Date: 2011-5-16下午05:05:12 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public Integer generateMediumSort(Map<String, Object> paramsMap);
	
	/**
	 * Description :  合作媒体分页列表
	 * Create Date: 2011-5-17下午01:59:54 by zhaojf  Modified Date: 2011-5-17下午01:59:54 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public Page<VoteTemplateMedium> getVoteTemplateMediumPage(Page<VoteTemplateMedium> page,Map<String, Object> paramsMap);
}
