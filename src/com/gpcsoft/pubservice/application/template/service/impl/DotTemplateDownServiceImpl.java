package com.gpcsoft.pubservice.application.template.service.impl;

import java.util.List;
import java.util.Map;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.gpcsoft.pubservice.application.template.manager.DotTemplateDownManager;
import com.gpcsoft.pubservice.application.template.service.DotTemplateDownService;
import com.gpcsoft.pubservice.application.template.domain.DotTemplateDown;

@Service 
public class DotTemplateDownServiceImpl extends BaseGenericServiceImpl<DotTemplateDown> implements DotTemplateDownService {

	@Autowired(required=true) @Qualifier("dotTemplateDownManagerImpl")
	private DotTemplateDownManager dotTemplateDownManager;

	/** 
	 * Description :  根据参数获取范本下载记录的集合
	 * Create Date: 2011-8-3上午08:34:58 by likg  Modified Date: 2011-8-3上午08:34:58 by likg
	 * @param   templateId:范本Id  orgInfoId:机构Id
	 * @return  
	 * @Exception   
	 */
	public List<DotTemplateDown> getTemplateDownListByParam(Map<String, Object> param) throws Exception {
		return dotTemplateDownManager.getTemplateDownListByParam(param);
	}

	/** 
	 * Description :  获取指定范本的下载次数
	 * Create Date: 2011-8-3上午08:34:58 by likg  Modified Date: 2011-8-3上午08:34:58 by likg
	 * @param   templateId:范本Id
	 * @return  
	 * @Exception   
	 */
	public Long getDownNum(String templateId) throws Exception {
		return dotTemplateDownManager.getDownNum(templateId);
	}

}
