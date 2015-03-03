package com.gpcsoft.agreement.bargin.project.service;

import java.util.List;
import java.util.Map;

import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.project.domain.Project;

public interface ProjectShowService extends BaseGenericService<Project> {
	
	/** 
	 * Description :  获取采购金额范围列表（供列表展示使用）
	 * Create Date: 2011-8-16上午08:51:14 by likg  Modified Date: 2011-8-16上午08:51:14 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<String[]> getAmountRangeListForShow(Map<String, Object> paramsMap) throws Exception;

	/** 
	 * Description :  获取采购区域列表（供列表展示使用）
	 * Create Date: 2011-8-15上午10:50:29 by likg  Modified Date: 2011-8-15上午10:50:29 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<Object[]> getDistrictListForShow(Map<String, Object> param) throws Exception;
	
	/** 
	 * Description :  获取采购品目列表（供列表展示使用）
	 * Create Date: 2011-8-15上午10:50:29 by likg  Modified Date: 2011-8-15上午10:50:29 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<Object[]> getPurCategoryListForShow(Map<String, Object> param) throws Exception;
	
	/** 
	 * Description :  获取项目列表（供列表展示使用）
	 * Create Date: 2011-8-15下午01:16:20 by likg  Modified Date: 2011-8-15下午01:16:20 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Page<Project> getProjectListForShow(Page<Project> page, Map<String, Object> paramsMap) throws Exception;
	
	/** 
	 * Description :  获取招标项目列表数据
	 * Create Date: 2011-8-16下午07:56:23 by likg  Modified Date: 2011-8-16下午07:56:23 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Page<Project> getBiddingProjectList(Page<Project> page, Map<String, Object> paramsMap) throws Exception;
	
	/** 
	 * Description :  获取采购项目列表数据
	 * Create Date: 2011-8-16下午07:56:23 by likg  Modified Date: 2011-8-16下午07:56:23 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Page<Project> getBargainProjectList(Page<Project> page, Map<String, Object> paramsMap) throws Exception;

}
