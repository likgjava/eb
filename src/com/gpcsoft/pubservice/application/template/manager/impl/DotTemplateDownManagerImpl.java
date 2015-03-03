package com.gpcsoft.pubservice.application.template.manager.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.pubservice.application.template.dao.DotTemplateDownDao;
import com.gpcsoft.pubservice.application.template.manager.DotTemplateDownManager;
import com.gpcsoft.pubservice.application.template.domain.DotTemplateDown;

@Repository
public class DotTemplateDownManagerImpl extends BaseManagerImpl<DotTemplateDown> implements DotTemplateDownManager {

	@Autowired(required=true)  @Qualifier("dotTemplateDownDaoHibernate")
	private DotTemplateDownDao dotTemplateDownDaoHibernate;

	/** 
	 * Description :  根据参数获取范本下载记录的集合
	 * Create Date: 2011-8-3上午08:34:58 by likg  Modified Date: 2011-8-3上午08:34:58 by likg
	 * @param   templateId:范本Id  orgInfoId:机构Id
	 * @return  
	 * @Exception   
	 */
	public List<DotTemplateDown> getTemplateDownListByParam(Map<String, Object> param) throws Exception {
		return dotTemplateDownDaoHibernate.getTemplateDownListByParam(param);
	}
	
	/** 
	 * Description :  获取指定范本的下载次数
	 * Create Date: 2011-8-3上午08:34:58 by likg  Modified Date: 2011-8-3上午08:34:58 by likg
	 * @param   templateId:范本Id
	 * @return  
	 * @Exception   
	 */
	public Long getDownNum(String templateId) throws Exception {
		return dotTemplateDownDaoHibernate.getDownNum(templateId);
	}

}
