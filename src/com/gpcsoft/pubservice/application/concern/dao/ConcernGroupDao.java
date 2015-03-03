package com.gpcsoft.pubservice.application.concern.dao;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.pubservice.application.concern.domain.ConcernGroup;

public interface ConcernGroupDao extends BaseGenericDao<ConcernGroup> {

	/** 
	 * Description : 获得默认分组  
	 * Create Date: 2010-11-4下午03:29:28 by guoyr  Modified Date: 2010-11-4下午03:29:28 by guoyr
	 * @param  groupType 默认分组的类型[01:供应商 02:采购人] 
	 * @return  
	 * @Exception   
	 */
	public ConcernGroup getDefaultConcernGroup(String belongsUser);
	
	/** 
	 * Description :获得分组的最大排序号  
	 * Create Date: 2010-11-5下午08:23:38 by guoyr  Modified Date: 2010-11-5下午08:23:38 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Integer getMaxConcernGroupSort(String groupType);
	
	/** 
	 * Description : 修改关注分组 
	 * Create Date: 2010-11-3下午05:56:54 by guoyr  Modified Date: 2010-11-3下午05:56:54 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean updateConcernGroup (ConcernGroup concernGroup);
}
