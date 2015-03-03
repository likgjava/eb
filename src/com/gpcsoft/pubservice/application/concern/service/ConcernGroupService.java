package com.gpcsoft.pubservice.application.concern.service;
import java.util.List;

import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.pubservice.application.concern.domain.ConcernGroup;

public interface ConcernGroupService extends BaseGenericService<ConcernGroup> {

	/** 
	 * Description : 保存关注分组 
	 * Create Date: 2010-11-3下午05:56:54 by guoyr  Modified Date: 2010-11-3下午05:56:54 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public ConcernGroup saveConcernGroup (ConcernGroup concernGroup);
	/** 
	 * Description : 修改关注分组 
	 * Create Date: 2010-11-3下午05:56:54 by guoyr  Modified Date: 2010-11-3下午05:56:54 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean updateConcernGroup (ConcernGroup concernGroup);
	
	/** 
	 * Description :获得供应商名采购人的分组列表 
	 * Create Date: 2010-11-5下午02:39:31 by guoyr  Modified Date: 2010-11-5下午02:39:31 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<ConcernGroup> getConcerngListByGroupType(String groupType);
	
	/** 
	 * Description :获得分组的最大排序号  
	 * Create Date: 2010-11-5下午08:23:38 by guoyr  Modified Date: 2010-11-5下午08:23:38 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Integer getMaxConcernGroupSort(String groupType);
}
