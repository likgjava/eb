package com.gpcsoft.pubservice.application.template.service;
import java.util.List;
import java.util.Map;

import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.pubservice.application.template.domain.DotTemplate;

public interface DotTemplateService extends BaseGenericService<DotTemplate> {

	/** 
	 * Description :  保存范本信息
	 * Create Date: 2011-8-1上午11:44:37 by likg  Modified Date: 2011-8-1上午11:44:37 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	void saveDotTemplate(DotTemplate dotTemplate) throws Exception;
	
	/** 
	 * Description :  根据参数获取范本列表集合
	 * Create Date: 2011-8-3上午08:34:58 by likg  Modified Date: 2011-8-3上午08:34:58 by likg
	 * @param   categoryCode:品目编号  districtCode:区域Code  templateType:范本类型  orgInfoId:机构Id
	 * @return  
	 * @Exception   
	 */
	List<DotTemplate> getTemplateListByParam(Map<String, Object> param) throws Exception;
	
	
	/** 
	 * Description :  获取各品目的范本数
	 * Create Date: 2011-8-3上午10:48:21 by likg  Modified Date: 2011-8-3上午10:48:21 by likg
	 * @param   param(categoryCode:品目编号  districtCode:区域Code  templateType:范本类型)
	 * @return  List([品目编号，品目名称，范本数量])
	 * @Exception   
	 */
	List<Object[]> getTemplateNumByCategory(Map<String, Object> param) throws Exception;
	
	/** 
	 * Description :  获取各区域的范本数（省级区域）
	 * Create Date: 2011-8-3上午10:48:21 by likg  Modified Date: 2011-8-3上午10:48:21 by likg
	 * @param   param(categoryCode:品目编号  districtCode:区域Code  templateType:范本类型)
	 * @return  List([区域编号，区域名称，范本数量])
	 * @Exception   
	 */
	List<Object[]> getTemplateNumByDistrict(Map<String, Object> param) throws Exception;
	
	/** 
	 * Description :  获取范本列表数据为前台展示使用
	 * Create Date: 2011-8-3下午01:04:52 by likg  Modified Date: 2011-8-3下午01:04:52 by likg
	 * @param   page:分页数据  param(categoryCode:品目编号  districtCode:区域Code  templateType:范本类型)
	 * @return  
	 * @Exception   
	 */
	Page<DotTemplate> getTemplateListForShow(Page<DotTemplate> page, Map<String, Object> param) throws Exception;

}
