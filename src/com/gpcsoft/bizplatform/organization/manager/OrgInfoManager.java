package com.gpcsoft.bizplatform.organization.manager;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.srplatform.auth.domain.Company;
import com.gpcsoft.srplatform.auth.domain.Employee;
import com.gpcsoft.srplatform.auth.domain.User;

public interface OrgInfoManager extends BaseManager<OrgInfo> {

	/** 
	 * Description :  将代理机构、供应商、主管单位、监管机构相同的机构信息、用户信息、账号信息等保存
	 * Create Date: 2010-7-21下午03:28:16 by sunl  Modified Date: 2010-7-21下午03:28:16 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public OrgInfo saveSimilar(Company company,Employee employee,OrgInfo orgInfo,User user) throws Exception;
	
	/** 
	 * Description :  将代理机构、主管单位、监管机构相同的机构信息等保存
	 * Create Date: 2010-11-15上午08:31:50 by likg  Modified Date: 2010-11-15上午08:31:50 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	OrgInfo saveSimilar(Company company, Employee employee, OrgInfo orgInfo) throws Exception;

	/** 
	 * Description :  取得机构信息
	 * Create Date: 2010-8-2下午09:58:16 by yucy  Modified Date: 2010-8-2下午09:58:16 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public OrgInfo getQualityOrgInfo(String objId) throws Exception;
	
	/** 
	 * Description :  开启或禁用机构下用户
	 * Create Date: 2010-8-2上午11:39:32 by sunl  Modified Date: 2010-8-2上午11:39:32 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void disableOrEnableUser(String companyId,String userIsLocked) throws Exception;
	
	/**
	 * Description :  根据机构名称获取此机构信息
	 * Create Date: 2011-1-13上午11:14:53 by zhaojf  Modified Date: 2011-1-13上午11:14:53 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public OrgInfo getOrgInfoByOrgName(String orgName) throws Exception;
	
	/** 
	 * Description :  根据当前组织机构id获得orgInfo
	 * Create Date: 2011-9-22上午11:33:04 by liangxj  Modified Date: 2011-9-22上午11:33:04 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public OrgInfo getOrgInfoByCompany(String companyId) throws Exception;
	
	/** 
	 * Description :  根据当前组织机构code获得orgInfo
	 * Create Date: 2011-12-9下午02:30:10 by yucy  Modified Date: 2011-12-9下午02:30:10 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public OrgInfo getOrgInfoByOrgCode(String orgCode) throws Exception;
}
