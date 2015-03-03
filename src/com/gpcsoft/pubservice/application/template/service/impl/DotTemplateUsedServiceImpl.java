package com.gpcsoft.pubservice.application.template.service.impl;

import java.util.List;
import java.util.Map;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.gpcsoft.pubservice.application.template.manager.DotTemplateUsedManager;
import com.gpcsoft.pubservice.application.template.service.DotTemplateUsedService;
import com.gpcsoft.pubservice.application.template.domain.DotTemplateUsed;

@Service 
public class DotTemplateUsedServiceImpl extends BaseGenericServiceImpl<DotTemplateUsed> implements DotTemplateUsedService {

	@Autowired(required=true) @Qualifier("dotTemplateUsedManagerImpl")
	private DotTemplateUsedManager dotTemplateUsedManager;

	/** 
	 * Description :  根据参数获取范本使用记录的集合
	 * Create Date: 2011-8-3上午08:34:58 by likg  Modified Date: 2011-8-3上午08:34:58 by likg
	 * @param   templateId:范本Id  projectCode:项目编号  orgInfoId:机构Id
	 * @return  
	 * @Exception   
	 */
	public List<DotTemplateUsed> getTemplateUsedListByParam(Map<String, Object> param) throws Exception {
		return dotTemplateUsedManager.getTemplateUsedListByParam(param);
	}

	/** 
	 * Description :  获取指定范本的使用次数
	 * Create Date: 2011-8-3上午08:34:58 by likg  Modified Date: 2011-8-3上午08:34:58 by likg
	 * @param   templateId:范本Id
	 * @return  
	 * @Exception   
	 */
	public Long getUsedNum(String templateId) throws Exception {
		return dotTemplateUsedManager.getUsedNum(templateId);
	}

}
