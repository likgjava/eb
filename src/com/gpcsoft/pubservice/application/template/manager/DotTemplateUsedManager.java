package com.gpcsoft.pubservice.application.template.manager;

import java.util.List;
import java.util.Map;

import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.pubservice.application.template.domain.DotTemplateUsed;

public interface DotTemplateUsedManager extends BaseManager<DotTemplateUsed> {

	/** 
	 * Description :  根据参数获取范本使用记录的集合
	 * Create Date: 2011-8-3上午08:34:58 by likg  Modified Date: 2011-8-3上午08:34:58 by likg
	 * @param   templateId:范本Id  projectCode:项目编号  orgInfoId:机构Id
	 * @return  
	 * @Exception   
	 */
	List<DotTemplateUsed> getTemplateUsedListByParam(Map<String, Object> param) throws Exception;
	
	/** 
	 * Description :  获取指定范本的使用次数
	 * Create Date: 2011-8-3上午08:34:58 by likg  Modified Date: 2011-8-3上午08:34:58 by likg
	 * @param   templateId:范本Id
	 * @return  
	 * @Exception   
	 */
	Long getUsedNum(String templateId) throws Exception;

}
