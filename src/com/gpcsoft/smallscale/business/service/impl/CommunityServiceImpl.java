package com.gpcsoft.smallscale.business.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.smallscale.business.dao.CommunityDao;
import com.gpcsoft.smallscale.business.domain.Community;
import com.gpcsoft.smallscale.business.manager.CommunityManager;
import com.gpcsoft.smallscale.business.service.CommunityService;

@Service 
public class CommunityServiceImpl extends BaseGenericServiceImpl<Community> implements CommunityService {

	@SuppressWarnings("unused")
	@Autowired(required=true) @Qualifier("communityManagerImpl")
	private CommunityManager communityManager;
	
	@Autowired(required=true) @Qualifier("communityDaoHibernate")
	private CommunityDao communityDao;

	/** 
	 * Description :  删除(检查是否可删)
	 * Create Date: 2010-12-8上午11:02:07 by yucy  Modified Date: 2010-12-8上午11:02:07 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Integer delCommunity(String[] communityIdArray) throws Exception {
		Integer flag = 0;
		//检查可删性
		if(true){//待扩展是否可删方法
			//删除
			flag = communityDao.delCommunity(communityIdArray);
		}
		return flag;
	}

	/** 
	 * Description :  最新的特色的推荐的
	 * Create Date: 2010-12-11上午10:50:37 by yucy  Modified Date: 2010-12-11上午10:50:37 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Map<String, Object> getNewAndSpecialAndRecommended(Map<String, Object> param) throws Exception {
		return communityDao.getNewAndSpecialAndRecommended(param);
	}
	
	/** 
	 * Description :  根据参数获得社区的展示信息列表
	 * Create Date: 2011-2-16下午05:50:15 by liangxj  Modified Date: 2011-2-16下午05:50:15 by liangxj
	 * @param   page 分页信息 paramsMap 查询条件：是否推荐、是否特色，排序默认为时间倒序
	 * @return  
	 * @Exception   
	 */
	public Page<Community> getCommunityListForShow(Page<Community> page,Map<String, Object> paramsMap) throws Exception {
		return communityDao.getCommunityListForShow(page,paramsMap);
	}
}
