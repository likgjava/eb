package com.gpcsoft.bizplatform.agency.service;
import java.util.List;
import java.util.Map;

import com.gpcsoft.bizplatform.agency.domain.Agency;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.srplatform.auth.domain.Company;
import com.gpcsoft.srplatform.auth.domain.Employee;
import com.gpcsoft.srplatform.auth.domain.User;

public interface AgencyService extends BaseGenericService<Agency> {
	/** 
	 * Description :  根据orgInfoId获取agent
	 * Create Date: 2011-10-24下午06:23:23 by sunl  Modified Date: 2011-10-24下午06:23:23 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Agency getAgentByOrgInfoId(String orgInfoId)throws Exception;
	
	/** 
	 * Description :  保存代理机构角色申请
	 * 				  保存代理机构,机构里的agencyId更新为新保存的agencyId
	 * Create Date: 2010-7-27上午10:15:01 by sunl  Modified Date: 2010-7-27上午10:15:01 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Agency saveApplyAgency(Agency agency,String orgInfoId) throws Exception;
	
	/** 
	 * Description :  取消机构角色申请
	 * Create Date: 2010-7-27上午10:15:01 by sunl  Modified Date: 2010-7-27上午10:15:01 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void updateCancelApply(String agencyId) throws Exception;
	
	/** 
	 * Description :  代理机构注册
	 * Create Date: 2010-7-9下午03:38:36 by sunl  Modified Date: 2010-7-9下午03:38:36 by sunl
	 * @param company 公司信息
	 * @param employee 员工信息
	 * @param orgInfo 机构信息
	 * @param user 用户信息
	 * @return  
	 * @Exception   
	 */
	public Agency saveAgencyOfRegister(Agency agency, Company company,Employee employee, OrgInfo orgInfo, User user) throws Exception;

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
