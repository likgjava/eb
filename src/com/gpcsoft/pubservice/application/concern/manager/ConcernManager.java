package com.gpcsoft.pubservice.application.concern.manager;

import java.util.List;

import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.pubservice.application.concern.domain.Concern;

public interface ConcernManager extends BaseManager<Concern> {

	/** 
	 * Description : 修改关对象的所属分组和名单类型 
	 * Create Date: 2010-11-5下午05:18:42 by guoyr  Modified Date: 2010-11-5下午05:18:42 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Integer updateConcern(String objId, String concernGroupId, String listType);

	/** 
	 * Description :  判断机构是否为我机构的黑名单客户
	 * Create Date: 2011-7-20下午02:01:23 by likg  Modified Date: 2011-7-20下午02:01:23 by likg
	 * @param   myOrgId:我的机构id	blackOrgId:被检查的机构id
	 * @return  
	 * @Exception   
	 */
	public boolean isInBlacklist(String myOrgId, String blackOrgId) throws Exception;

	/** 
	 * Description :  获取指定机构或指定用户的黑名单客户的机构id
	 * Create Date: 2011-7-20下午07:30:27 by likg  Modified Date: 2011-7-20下午07:30:27 by likg
	 * @param   myOrgId:指定机构id	myUserId:指定用户id
	 * @return  List<String>:黑名单客户的机构id
	 * @Exception   
	 */
	public List<String> getMyBlacklist(String myOrgId, String myUserId) throws Exception;
}
