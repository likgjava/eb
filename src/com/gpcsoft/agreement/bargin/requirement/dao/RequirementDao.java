package com.gpcsoft.agreement.bargin.requirement.dao;

import java.util.List;
import java.util.Map;

import com.gpcsoft.agreement.bargin.requirement.domain.Requirement;
import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.core.utils.Page;

public interface RequirementDao extends BaseGenericDao<Requirement> {

	/** 
	 * Description :  更新状态(可能批量)
	 * Create Date: 2011-3-25上午10:59:51 by yucy  Modified Date: 2011-3-25上午10:59:51 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Boolean updateStatus(String objIds, Map<String, Object> param) throws Exception;

	/** 
	 * Description :  取得需求信息
	 * Create Date: 2011-3-30上午10:04:03 by yucy  Modified Date: 2011-3-30上午10:04:03 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Page<Requirement> getRequirementListForShow(Page<Requirement> page,Map<String, Object> paramsMap) throws Exception;

	/** 
	 * Description :  根据品目获得品目的展示信息集合
	 * Create Date: 2010-7-27下午06:12:58 by yucy  Modified Date: 2010-7-27下午06:12:58 by yucy
	 * @param   categoryId 品目id，categoryCode 品目编号, isLeaf 是否只取叶子节点
	 * @return  
	 * @Exception   
	 */
	List<String[]> getCategoryListShowByCategory(String categoryId,String categoryCode, Boolean isLeaf, String keyWord) throws Exception;

	/** 
	 * Description :  根据品目获得区域信息展示信息集合
	 * Create Date: 2010-8-9下午03:25:57 by yucy  Modified Date: 2010-8-9下午03:25:57 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<String[]> getDistrictListForShow(String districtId, String categoryCode,Short level, String keyWord) throws Exception;

	/** 
	 * Description :  根据id 获得需求的集合
	 * Create Date: 2011-4-6下午02:27:52 by yucy  Modified Date: 2011-4-6下午02:27:52 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<Requirement> getRequirementList(String objIds) throws Exception;
}
