package com.gpcsoft.agreement.bargin.requirement.service;
import java.util.List;
import java.util.Map;

import com.gpcsoft.agreement.bargin.requirement.domain.Requirement;
import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.core.utils.Page;

public interface RequirementService extends BaseGenericService<Requirement> {

	/** 
	 * Description :  保存采购需求
	 * Create Date: 2011-3-24上午09:16:36 by yucy  Modified Date: 2011-3-24上午09:16:36 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Requirement saveRequirement(Requirement requirement ,String saveType) throws Exception;

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
	Page<Requirement> getRequirementListForShow(Page<Requirement> page, Map<String, Object> paramsMap) throws Exception;

	/** 
	 * Description :  根据品目获得品目的展示信息集合
	 * Create Date: 2010-7-27下午06:12:58 by yucy  Modified Date: 2010-7-27下午06:12:58 by yucy
	 * @param   categoryId 品目id，categoryCode 品目编号, isLeaf 是否只取叶子节点
	 * @return  
	 * @Exception   
	 */
	List<String[]> getCategoryListShowByCategory(String categoryId,String categoryCode,Boolean isLeaf,String keyWord) throws Exception;

	/** 
	 * Description :  根据品目获得区域信息展示信息集合
	 * Create Date: 2010-8-9下午03:25:57 by yucy  Modified Date: 2010-8-9下午03:25:57 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<String[]> getDistrictListForShow(String district_id, String categoryCode,Short level, String keyWord) throws Exception;

	/** 
	 * Description :  根据id 获得需求的集合
	 * Create Date: 2011-4-6下午02:27:52 by yucy  Modified Date: 2011-4-6下午02:27:52 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<Requirement> getRequirementList(String objIds) throws Exception;

	/** 
	 * Description :  推荐给朋友
	 * Create Date: 2011-4-8下午01:56:43 by yucy  Modified Date: 2011-4-8下午01:56:43 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Boolean shareRequirement(Map<String, Object> param) throws Exception;

}
