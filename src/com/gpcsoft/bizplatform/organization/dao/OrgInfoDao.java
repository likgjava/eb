package com.gpcsoft.bizplatform.organization.dao;

import java.util.List;
import java.util.Map;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.srplatform.auth.domain.Company;

public interface OrgInfoDao extends BaseGenericDao<OrgInfo> {
	/** 
	 * Description :  获得代理机构下拉列表
	 * Create Date: 2010-9-26下午10:11:49 by sunl  Modified Date: 2010-9-26下午10:11:49 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<String[]> getAgencies() throws Exception;
	
	/** 
	 * Description :  查询待审核的机构信息列表
	 * 				  查询条件为：auditStatus=01(待审核)
	 * 				  或者 supplierStatus=01 or buyersTatus=01 or agencyTatus=01)
	 * Create Date: 2010-7-28上午09:39:54 by sunl  Modified Date: 2010-7-28上午09:39:54 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Page<OrgInfo> listOrgInfoForAudit(Page<OrgInfo> page,Map<String,Object> param) throws Exception;

	/** 
	 * Description :  根据companyId，获得最新的orgInfo。
	 * 				  查询条件为根据companyId获取创建时间最新的orginfo信息 
	 * Create Date: 2010-7-21下午05:38:03 by sunl  Modified Date: 2010-7-21下午05:38:03 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public OrgInfo getLastedOrgInfo(String companyId,boolean isGetValid) throws Exception;
	
	
	/** 
	 * Description :  更新原来的orgInfo对象里的currentId为最新的orgInfoId
	 * 				  条件为所有companyId为传入参数值的orgInfo对象
	 * Create Date: 2010-7-23上午11:28:31 by sunl  Modified Date: 2010-7-23上午11:28:31 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Integer updateCurrentId(String orgInfoId, String newOrgInfoId) throws Exception;

	/** 
	 * Description :  根据组织机构的Id来查询orgInfo的信息
	 * Create Date: 2010-7-29下午06:16:11 by yucy  Modified Date: 2010-7-29下午06:16:11 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public OrgInfo getOrgInfoByCompanyId(String companyId) throws Exception;
	
	/** 
	 * Description :  开启或禁用机构下用户
	 * Create Date: 2010-8-2上午11:39:32 by sunl  Modified Date: 2010-8-2上午11:39:32 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void disableOrEnableUser(String companyId,String isOff) throws Exception;
	
	/** 
	 * Description :  获取机构历史信息
	 * Create Date: 2010-7-28下午02:12:00 by sunl  Modified Date: 2010-7-28下午02:12:00 by sunl
	 * @param   
	 * @return	
	 * @Exception   
	 */
	public List<OrgInfo> getOrgHistory(Map<String,Object> param) throws Exception;
	
	/**
	 * 
	 * Description :  验证机构代码和机构名称的唯一
	 * Create Date: 2010-8-24下午08:44:39 by sunl  Modified Date: 2010-8-24下午08:44:39 by sunl
	 * @param   
	 * @return  
	 * @Exception
	 */
	public Integer checkOrgUnique(Map<String,Object> param) throws Exception;
	
	/**
	 * 
	 * Description :  模糊检验机构名称
	 * Create Date: 2010-8-24下午08:44:39 by sunl  Modified Date: 2010-8-24下午08:44:39 by sunl
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<String> checkOrgName(Map<String,Object> param) throws Exception;
	
	/** 
	 * Description :  根据主键，获得机构的详细信息，包括供应商信息，采购人信息，代理机构信息
	 * 				  机构资质，成功案例，评价信息等
	 * Create Date: 2010-8-26下午06:50:25 by sunl  Modified Date: 2010-8-26下午06:50:25 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public OrgInfo getOrgAllInfo(String objIds) throws Exception;
	
	/** 
	 * Description : 根据机构Id 获得机构的管理员  
	 * Create Date: 2010-10-29下午02:47:21 by guoyr  Modified Date: 2010-10-29下午02:47:21 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public String[] getAllManagersByOrgInfoIds(String userType, String objIds) throws Exception;

	/** 
	 * Description :  查询是否通过审核
	 * Create Date: 2010-11-10下午06:41:44 by yucy  Modified Date: 2010-11-10下午06:41:44 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean getIsAuditPass(Map<String, Object> param) throws Exception;
	
	/** 
	 * Description :  根据供应商名称查询供应商列表
	 * Create Date: 2010-11-22下午03:40:51 by guoyr  Modified Date: 2010-11-22下午03:40:51 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<OrgInfo> getAllOrgInfoByOrgName(String orgName);
	
	/** 
	 * Description :  插入/更新auth_orgnizaiton数据
	 * Create Date: 2011-10-25上午11:54:17 by sunl  Modified Date: 2011-10-25上午11:54:17 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Integer saveCompany(Company company,String saveType) throws Exception;
	
	/** 
	 * Description :  插入/更新org_info数据
	 * Create Date: 2011-10-25上午11:54:17 by sunl  Modified Date: 2011-10-25上午11:54:17 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Integer saveOrgInfo(OrgInfo orgInfo,String saveType) throws Exception;

	/** 
	 * Description :  根据当前组织机构code获得orgInfo
	 * Create Date: 2011-12-9下午02:30:10 by yucy  Modified Date: 2011-12-9下午02:30:10 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public OrgInfo getOrgInfoByOrgCode(String orgCode) throws Exception;
}
