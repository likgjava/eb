package com.gpcsoft.pubservice.application.template.manager.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.pubservice.application.template.dao.DotTemplateUsedDao;
import com.gpcsoft.pubservice.application.template.manager.DotTemplateUsedManager;
import com.gpcsoft.pubservice.application.template.domain.DotTemplateUsed;

@Repository
public class DotTemplateUsedManagerImpl extends BaseManagerImpl<DotTemplateUsed> implements DotTemplateUsedManager {

	@Autowired(required=true)  @Qualifier("dotTemplateUsedDaoHibernate")
	private DotTemplateUsedDao dotTemplateUsedDaoHibernate;

	/** 
	 * Description :  根据参数获取范本使用记录的集合
	 * Create Date: 2011-8-3上午08:34:58 by likg  Modified Date: 2011-8-3上午08:34:58 by likg
	 * @param   templateId:范本Id  projectCode:项目编号  orgInfoId:机构Id
	 * @return  
	 * @Exception   
	 */
	public List<DotTemplateUsed> getTemplateUsedListByParam(Map<String, Object> param) throws Exception {
		return dotTemplateUsedDaoHibernate.getTemplateUsedListByParam(param);
	}

	/** 
	 * Description :  获取指定范本的使用次数
	 * Create Date: 2011-8-3上午08:34:58 by likg  Modified Date: 2011-8-3上午08:34:58 by likg
	 * @param   templateId:范本Id
	 * @return  
	 * @Exception   
	 */
	public Long getUsedNum(String templateId) throws Exception {
		return dotTemplateUsedDaoHibernate.getUsedNum(templateId);
	}

}
