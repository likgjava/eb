package com.gpcsoft.pubservice.application.template.manager;

import java.util.List;
import java.util.Map;

import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.pubservice.application.template.domain.DotTemplateDown;

public interface DotTemplateDownManager extends BaseManager<DotTemplateDown> {

	/** 
	 * Description :  根据参数获取范本下载记录的集合
	 * Create Date: 2011-8-3上午08:34:58 by likg  Modified Date: 2011-8-3上午08:34:58 by likg
	 * @param   templateId:范本Id  orgInfoId:机构Id
	 * @return  
	 * @Exception   
	 */
	List<DotTemplateDown> getTemplateDownListByParam(Map<String, Object> param) throws Exception;

	/** 
	 * Description :  获取指定范本的下载次数
	 * Create Date: 2011-8-3上午08:34:58 by likg  Modified Date: 2011-8-3上午08:34:58 by likg
	 * @param   templateId:范本Id
	 * @return  
	 * @Exception   
	 */
	Long getDownNum(String templateId) throws Exception;

}
