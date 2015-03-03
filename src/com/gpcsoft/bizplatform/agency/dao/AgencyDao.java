package com.gpcsoft.bizplatform.agency.dao;

import java.util.List;
import java.util.Map;

import com.gpcsoft.bizplatform.agency.domain.Agency;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.core.utils.Page;

public interface AgencyDao extends BaseGenericDao<Agency> {

	/** 
	 * Description :  根据orgInfoId获取agent
	 * Create Date: 2011-10-24下午06:23:23 by sunl  Modified Date: 2011-10-24下午06:23:23 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Agency getAgentByOrgInfoId(String orgInfoId)throws Exception;
	
	/** 
	 * Description : 更新代理机构审核状态  
	 * Create Date: 2010-7-28下午02:50:44 by sunl  Modified Date: 2010-7-28下午02:50:44 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Integer updateAgencyStatus(String agencyId,OrgInfo orgInfo) throws Exception;
	
	/** 
	 * Description :  根据主键，获得代理机构的详细信息，包括基本信息、扩展信息、评价、案例、资质等
	 * Create Date: 2010-8-4下午04:31:42 by liangxj  Modified Date: 2010-8-4下午04:31:42 by liangxj
	 * @param objIds以逗号分隔
	 * @return  
	 * @Exception   
	 */
	public List<Agency> getAgencyAllInfoList(String objIds) throws Exception;
	
	/** 
	 * Description :  根据主键，获得代理机构的详细信息，包括基本信息、扩展信息、评价、案例、资质等
	 * Create Date: 2010-8-4下午04:31:42 by liangxj  Modified Date: 2010-8-4下午04:31:42 by liangxj
	 * @param 
	 * @return  
	 * @Exception   
	 */
	public Agency getAgencyAllInfo(String objId) throws Exception;
	
	/** 
	 * Description :  根据参数获得代理机构的展示信息列表，及时加载OrgInfor信息
	 * Create Date: 2010-8-9下午01:44:06 by liangxj  Modified Date: 2010-8-9下午01:44:06 by liangxj
	 * @param   page 分页信息 paramsMap 查询条件：包含行政级别、代理机构类型、排序信息
	 * @return  
	 * @Exception   
	 */
	public Page<Agency> getAgencyListForShow(Page<Agency> page,Map<String, Object> paramsMap) throws Exception;
	
	/** 
	 * Description :  获得代理机构类型的展示数据
	 * Create Date: 2010-8-12下午02:54:53 by liangxj  Modified Date: 2010-8-12下午02:54:53 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<String[]> getAgentTypeListShow() throws Exception;
	
	/** 
	 * Description :  获得代理机构类型的展示数据
	 * Create Date: 2010-8-12下午02:54:53 by liangxj  Modified Date: 2010-8-12下午02:54:53 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<String[]> getAdminGrdListShow(String agentType) throws Exception;
}
