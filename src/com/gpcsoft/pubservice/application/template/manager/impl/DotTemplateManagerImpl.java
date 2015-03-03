package com.gpcsoft.pubservice.application.template.manager.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.pubservice.application.template.dao.DotTemplateDao;
import com.gpcsoft.pubservice.application.template.manager.DotTemplateManager;
import com.gpcsoft.pubservice.application.template.domain.DotTemplate;

@Repository
public class DotTemplateManagerImpl extends BaseManagerImpl<DotTemplate> implements DotTemplateManager {

	@Autowired(required=true)  @Qualifier("dotTemplateDaoHibernate")
	private DotTemplateDao dotTemplateDaoHibernate;

	/** 
	 * Description :  根据参数获取范本列表集合
	 * Create Date: 2011-8-3上午08:34:58 by likg  Modified Date: 2011-8-3上午08:34:58 by likg
	 * @param   categoryCode:品目编号  districtId:区域Code  templateType:范本类型  orgInfoId:机构Id
	 * @return  
	 * @Exception   
	 */
	public List<DotTemplate> getTemplateListByParam(Map<String, Object> param) throws Exception {
		return dotTemplateDaoHibernate.getTemplateListByParam(param);
	}

	/** 
	 * Description :  获取范本列表数据为前台展示使用
	 * Create Date: 2011-8-3下午01:04:52 by likg  Modified Date: 2011-8-3下午01:04:52 by likg
	 * @param   page:分页数据  param(categoryCode:品目编号  districtId:区域Code  templateType:范本类型)
	 * @return  
	 * @Exception   
	 */
	public Page<DotTemplate> getTemplateListForShow(Page<DotTemplate> page, Map<String, Object> param) throws Exception {
		return dotTemplateDaoHibernate.getTemplateListForShow(page, param);
	}

}
