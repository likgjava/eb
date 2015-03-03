package com.gpcsoft.smallscale.business.dao;

import java.util.Map;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.smallscale.business.domain.Community;

public interface CommunityDao extends BaseGenericDao<Community> {

	/** 
	 * Description :  删除
	 * Create Date: 2010-12-8上午11:02:07 by yucy  Modified Date: 2010-12-8上午11:02:07 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Integer delCommunity(String[] communityIdArray)throws Exception;

	/** 
	 * Description :  最新的特色的推荐的
	 * Create Date: 2010-12-11上午10:50:37 by yucy  Modified Date: 2010-12-11上午10:50:37 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Map<String, Object> getNewAndSpecialAndRecommended(Map<String, Object> param)throws Exception;
	
	/** 
	 * Description :  根据参数获得社区的展示信息列表
	 * Create Date: 2011-2-16下午05:50:15 by liangxj  Modified Date: 2011-2-16下午05:50:15 by liangxj
	 * @param   page 分页信息 paramsMap 查询条件：是否推荐、是否特色，排序默认为时间倒序
	 * @return  
	 * @Exception   
	 */
	public Page<Community> getCommunityListForShow(Page<Community> page,Map<String, Object> paramsMap) throws Exception;

}
