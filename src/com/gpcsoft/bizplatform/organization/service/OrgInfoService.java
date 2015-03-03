package com.gpcsoft.bizplatform.organization.service;

import java.util.List;
import java.util.Map;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.srplatform.auth.domain.Company;


public interface OrgInfoService extends BaseGenericService<OrgInfo> {
	
		/** 
	 * Description :  获得代理机构下拉列表
	 * Create Date: 2010-9-26下午10:11:49 by sunl  Modified Date: 2010-9-26下午10:11:49 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<OrgInfo> getAgencies() throws Exception;
	
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
     * Description : 修改机构信息，如果机构已经通过审核，
     * 				 则创建新的数据，并创建扩展信息，否则更新原有信息
     * 				 如果是创建新数据，则currentId的值需同步为原有数据的id
     * Create Date: 2010-7-22下午05:09:57 by sunl  Modified Date: 2010-7-22下午05:09:57 by sunl
     * @param   baseObject 可以传入多个扩展信息，包括Supplier、agency、buyer
     * @return  
     * @Exception   
     */
    public String saveModifyOrgInfo(OrgInfo orgInfo,String saveType,GpcBaseObject... baseObject)throws Exception;
    
	/** 
	 * Description :  开启或禁用机构账号,并更新机构下用户状态
	 * Create Date: 2010-8-2上午11:39:32 by sunl  Modified Date: 2010-8-2上午11:39:32 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void updateDisableOrEnable(String orgInfoId,String isOff) throws Exception;

	/** 
	 * Description :  注册信息的审核（包含管理员做的新增机构）
	 * 				  如果通过,则将useStatus变为01、auditStatus变为03、生效时间为当前时间
	 * 				  不通过，则将auditStatus变为04
	 * 				  需同步扩展信息的审核状态，同步company的审核状态
	 * Create Date: 2010-7-21下午07:14:38 by sunl  Modified Date: 2010-7-21下午07:14:38 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void auditOrgBaseInfoForReg(OrgInfo orgInfo,Map<String,Object> params) throws Exception;
	
	/** 
	 * Description :  变更信息的审核
	 * 				如果通过,则将当前数据C的useStatus变为01、auditStatus变为03、生效时间为当前时间，currentId置空，
	 * 				同步将objId为currentId的数据B的useStatus变为02，失效时间为当前时间。
	 * 				同步将所有currentId为B的数据A的currentId变为当前数据C的objId
	 * 				不通过，则将auditStatus变为04
	 * 				需同步扩展信息的审核状态，同步company的审核状态
	 * Create Date: 2010-7-21下午07:14:38 by sunl  Modified Date: 2010-7-21下午07:14:38 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void auditOrgBaseInfoForMod(OrgInfo orgInfo) throws Exception;
	
	/** 
	 * Description :  申请信息的审核
	 * 				  更新扩展信息的状态
	 * Create Date: 2010-7-28下午04:55:24 by sunl  Modified Date: 2010-7-28下午04:55:24 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void auditOrgBaseInfoForApply(OrgInfo orgInfo) throws Exception;
	
	/** 
	 * Description :  普通审核（处理用户提交产生的信息进行审核，只进行审核不进行其他操作）
	 * Create Date: 2010-7-21下午07:14:38 by sunl  Modified Date: 2010-7-21下午07:14:38 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void auditOrgInfo(OrgInfo orgInfo) throws Exception;
	
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
	public Map<String,Object> checkOrgName(Map<String,Object> param) throws Exception;
	
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
	 * Description :  查询是否通过审核
	 * Create Date: 2010-11-10下午06:41:44 by yucy  Modified Date: 2010-11-10下午06:41:44 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean getIsAuditPass(Map<String, Object> param) throws Exception;
	
	/**
	 * 同步扩展信息状态
	 * Description :  
	 * Create Date: 2010-11-10下午01:26:57 by sunl  Modified Date: 2010-11-10下午01:26:57 by sunl
	 * @param   
	 * @return  
	 * @Exception
	 */
	public void updateExtendInfoStatus(OrgInfo orgInfo) throws Exception;
	
	/** 
     * Description : 维护企业基本信息
     * Create Date: 2010-7-22下午05:09:57 by sunl  Modified Date: 2010-7-22下午05:09:57 by sunl
     * @param 
     * @return  
     * @Exception   
     */
	public String saveEntBaseInfo(OrgInfo orgInfo,String saveType) throws Exception;
	
	/** 
	 * Description :  根据供应商名称查询供应商列表
	 * Create Date: 2010-11-22下午03:40:51 by guoyr  Modified Date: 2010-11-22下午03:40:51 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<OrgInfo> getAllOrgInfoByOrgName(String orgName);
	
	
	/** 
	 * Description :  根据当前用户返回机构状态
	 * Create Date: 2011-4-12下午02:07:42 by sunl  Modified Date: 2011-4-12下午02:07:42 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Map<String,Object> getCurrentOrgStatus() throws Exception;
	
	/** 
	 * Description :  根据当前组织机构id获得orgInfo
	 * Create Date: 2011-9-22上午11:33:04 by liangxj  Modified Date: 2011-9-22上午11:33:04 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public OrgInfo getOrgInfoByCompany(String companyId) throws Exception;
	
	/** 
	 * Description :  插入/更新auth_orgnizaiton数据
	 * Create Date: 2011-10-25上午11:54:17 by sunl  Modified Date: 2011-10-25上午11:54:17 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Integer saveCompany(Company company, String saveType) throws Exception;
	
	/** 
	 * Description :  插入/更新org_info数据
	 * Create Date: 2011-10-25上午11:54:17 by sunl  Modified Date: 2011-10-25上午11:54:17 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Integer saveOrgInfo(OrgInfo orgInfo, String saveType) throws Exception;
}
