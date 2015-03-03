package com.gpcsoft.agreement.management.dao;

import java.util.List;
import java.util.Map;

import com.gpcsoft.agreement.management.domain.Area;
import com.gpcsoft.core.dao.BaseGenericDao;

public interface AreaDao extends BaseGenericDao<Area> {

	/** 
	 * Description : 删除区间 
	 * Create Date: 2010-4-17下午06:00:50 by yucy  Modified Date: 2010-4-17下午06:00:50 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Integer removeArea(String areaId);

	/** 
	 * Description :  取到当前的Area综合信息(它的父id,可否修改,可否删除等)
	 * Create Date: 2010-5-20上午11:04:06 by yucy  Modified Date: 2010-5-20上午11:04:06 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Map<String, Object> getAreaInfo(Map<String, Object> params);

	/** 
	 * Description :  
	 * Create Date: 2010-5-24上午01:22:26 by yucy  Modified Date: 2010-5-24上午01:22:26 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Object getAreaTreeById(Map<String, Object> params);

	/** 
	 * Description :  根据是否有效取到区间和区域信息
	 * Create Date: 2011-12-1下午02:24:38 by yucy  Modified Date: 2011-12-1下午02:24:38 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<Object[]> getAreaList(String isValid) throws Exception;

	/** 
	 * Description :  保存区间（批量）
	 * Create Date: 2011-12-2上午11:39:01 by yucy  Modified Date: 2011-12-2上午11:39:01 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Boolean saveAreaBatch(Map<String, Object> param) throws Exception;

}
