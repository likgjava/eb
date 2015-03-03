package com.gpcsoft.agreement.bargin.project.dao;

import java.util.Map;

import com.gpcsoft.agreement.bargin.project.domain.ProjectQueryDto;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.project.dao.ProjectDao;

public interface BargainProjectDao extends ProjectDao  {

	/** 
	 * Description :  查找被评价对象
	 * Create Date: 2010-10-28下午04:12:52 by yucy  Modified Date: 2010-10-28下午04:12:52 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Map<String, Object> getEvaluateObjectSupplier(String buyersId,String projectId) throws Exception;

	/** 
	 * Description :  项目查询数据(采购人)
	 * Create Date: 2011-6-29下午02:14:33 by yucy  Modified Date: 2011-6-29下午02:14:33 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Page<Object> getProjectList(Page<Object> page,Map<String, Object> param) throws Exception;
	
	/** 
	 * Description :  项目查询数据(供应商)
	 * Create Date: 2011-6-29下午02:14:33 by yucy  Modified Date: 2011-6-29下午02:14:33 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Page<Object> getMyProjectList(Page<Object> page,Map<String, Object> param) throws Exception;
	
	/**
	 * Description :  我与客户的交易项目记录
	 * Create Date: 2011-7-7下午03:50:20 by zhaojf  Modified Date: 2011-7-7下午03:50:20 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public Page<ProjectQueryDto> getExchangeProjectRecord(Page<ProjectQueryDto> page, Map<String, Object> param) throws Exception;
}
