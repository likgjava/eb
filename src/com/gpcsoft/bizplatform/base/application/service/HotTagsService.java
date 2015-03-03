package com.gpcsoft.bizplatform.base.application.service;
import java.util.List;
import java.util.Map;

import com.gpcsoft.bizplatform.base.application.domain.HotTags;
import com.gpcsoft.core.service.BaseGenericService;

public interface HotTagsService extends BaseGenericService<HotTags> {

	
	/** 
	 * Description :  取得热门标签
	 * Create Date: 2010-10-7下午05:29:16 by yucy  Modified Date: 2010-10-7下午05:29:16 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<HotTags> getHotTagsList(Map<String, Object> params) throws Exception;
}
