package com.gpcsoft.bizplatform.buyer.service;
import java.util.List;
import java.util.Map;

import com.gpcsoft.bizplatform.buyer.domain.Buyer;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.srplatform.auth.domain.Company;
import com.gpcsoft.srplatform.auth.domain.Employee;
import com.gpcsoft.srplatform.auth.domain.User;

public interface BuyerService extends BaseGenericService<Buyer> {
	
	/** 
	 * Description :  根据orgInfoId获取Buyer
	 * Create Date: 2011-10-24下午06:23:23 by sunl  Modified Date: 2011-10-24下午06:23:23 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Buyer getBuyerByOrgInfoId(String orgInfoId)throws Exception;
	
	/** 
	 * Description :  保存采购人角色申请
	 * 				  保存采购人,更新机构状态为待审核，机构里的buyerId更新为新保存的buyerId
	 * Create Date: 2010-7-26下午07:34:02 by sunl  Modified Date: 2010-7-26下午07:34:02 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Buyer saveApplyBuyer(Buyer buyer,String orgInfoId)throws Exception;
	
	/** 
	 * Description : 采购人注册
     * Create Date: 2010-7-22上午08:47:12 by sunl  Modified Date: 2010-7-22上午08:47:12 by sunl
	 * @param company 公司信息
	 * @param employee 员工信息
	 * @param orgInfo 机构信息
	 * @param user 用户信息
	 * @return  
	 * @Exception   
	 */
	public Buyer saveBuyerOfRegister(Buyer buyer,Company company,Employee employee,OrgInfo orgInfo,User user,String promoterUID) throws Exception;
	
	
	/** 
	 * Description :   根据参数获得采购人机构的展示信息列表，及时加载OrgInfor信息
	 * Create Date: 2010-10-19上午11:46:16 by liangxj  Modified Date: 2010-10-19上午11:46:16 by liangxj
	 * @param   page 分页信息 paramsMap 查询条件：包含所属区域、企业类型、排序信息
	 * @return  
	 * @Exception   
	 */
	public Page<Buyer> getBuyerListForShow(Page<Buyer> page,Map<String, Object> paramsMap) throws Exception;
	
	/** 
	 * Description :  根据主键，获得采购人的详细信息，包括基本信息、扩展信息、评价等
	 * Create Date: 2010-10-19下午05:17:50 by liangxj  Modified Date: 2010-10-19下午05:17:50 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Buyer getBuyerAllInfo(String objId) throws Exception;
	
	/** 
	 * Description :  获得采购人企业类型的展示数据 
	 * Create Date: 2010-10-19下午08:17:07 by liangxj  Modified Date: 2010-10-19下午08:17:07 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<String[]> getUnitTypeListShow(String keyWord) throws Exception;
	
	/** 
	 * Description :  根据企业性质获得包含采购人数量的区域信息
	 * Create Date: 2010-10-19下午08:19:10 by liangxj  Modified Date: 2010-10-19下午08:19:10 by liangxj
	 * @param   districtId 区域id unitType 企业类型 districtLevel 区域级别
	 * @return  
	 * @Exception   
	 */
	public List<String[]> getDistrictListForShow(String districtId,String unitType,Short districtLevel,String keyWord) throws Exception;
	
	/** 
	 * Description :  取消机构角色申请
	 * 				  删除agency，并将orginfo的buyer信息置空
	 * Create Date: 2010-7-27上午10:15:01 by sunl  Modified Date: 2010-7-27上午10:15:01 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void updateCancelApply(String buyerId) throws Exception;

	/** 
	 * Description :  保存采购人对象（只包含机构信息）
	 * Create Date: 2010-11-12下午06:14:42 by likg  Modified Date: 2010-11-12下午06:14:42 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	void saveSimpleBuyer(Buyer buyer, OrgInfo orgInfo) throws Exception;
}
