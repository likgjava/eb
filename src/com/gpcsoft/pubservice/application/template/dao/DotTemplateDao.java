package com.gpcsoft.pubservice.application.template.dao;

import java.util.List;
import java.util.Map;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.pubservice.application.template.domain.DotTemplate;

public interface DotTemplateDao extends BaseGenericDao<DotTemplate> {

	/** 
	 * Description :  根据参数获取范本列表集合
	 * Create Date: 2011-8-3上午08:34:58 by likg  Modified Date: 2011-8-3上午08:34:58 by likg
	 * @param   categoryCode:品目编号  districtCode:区域Code  templateType:范本类型  orgInfoId:机构Id
	 * @return  
	 * @Exception   
	 */
	List<DotTemplate> getTemplateListByParam(Map<String, Object> param) throws Exception;

	/** 
	 * Description :  获取范本列表数据为前台展示使用
	 * Create Date: 2011-8-3下午01:04:52 by likg  Modified Date: 2011-8-3下午01:04:52 by likg
	 * @param   page:分页数据  param(categoryCode:品目编号  districtCode:区域Code  templateType:范本类型)
	 * @return  
	 * @Exception   
	 */
	Page<DotTemplate> getTemplateListForShow(Page<DotTemplate> page, Map<String, Object> param) throws Exception;

}
