package com.gpcsoft.agreement.bargin.requirement.dao;

import java.util.List;
import java.util.Map;

import com.gpcsoft.agreement.bargin.requirement.domain.RequirementReg;
import com.gpcsoft.core.dao.BaseGenericDao;

public interface RequirementRegDao extends BaseGenericDao<RequirementReg> {

	/** 
	 * Description :  检查是否报名过
	 * Create Date: 2011-4-1下午03:47:00 by yucy  Modified Date: 2011-4-1下午03:47:00 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Boolean checkRequirementReged(Map<String, Object> param) throws Exception;

	/** 
	 * Description :  更新需求报名的状态
	 * Create Date: 2011-3-11下午05:05:28 by yucy  Modified Date: 2011-3-11下午05:05:28 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Boolean updateStatus(String objIds,Map<String, Object> param ) throws Exception;

	/** 
	 * Description :  获得报名信息
	 * Create Date: 2011-4-6下午04:22:36 by yucy  Modified Date: 2011-4-6下午04:22:36 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<RequirementReg> getRequirementRegList(Map<String, Object> map) throws Exception;

	/** 
	 * Description :  校验报名身份证号码的唯一性
	 * Create Date: 2010-11-8下午03:06:40 by yucy  Modified Date: 2010-11-8下午03:06:40 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Boolean checkedIdCard(Map<String, Object> param) throws Exception;

}
