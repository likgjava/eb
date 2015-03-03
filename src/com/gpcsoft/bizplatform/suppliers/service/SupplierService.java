package com.gpcsoft.bizplatform.suppliers.service;
import java.util.List;
import java.util.Map;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.suppliers.domain.Supplier;
import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.srplatform.auth.domain.Company;
import com.gpcsoft.srplatform.auth.domain.Employee;
import com.gpcsoft.srplatform.auth.domain.User;

public interface SupplierService extends BaseGenericService<Supplier> {
	/** 
	 * Description :  保存供应商角色申请
	 * 				  保存供应商,机构里的supplierId更新为新保存的supplierId
	 * Create Date: 2010-7-27上午10:15:01 by sunl  Modified Date: 2010-7-27上午10:15:01 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Supplier saveApplySupplier(Supplier supplier,String orgInfoId) throws Exception;
	
	/** 
	 * Description :  供应商注册
	 * Create Date: 2010-7-7下午03:34:30 by sunl  Modified Date: 2010-7-7下午03:34:30 by sunl
	 * @param company 公司信息
	 * @param employee 员工信息
	 * @param orgInfo 机构信息
	 * @param user 用户信息
	 * @return  
	 * @Exception   
	 */
	public Supplier saveSupplierOfRegister(Supplier supplier,Company company,Employee employee,OrgInfo orgInfo,User user) throws Exception;
	
	/** 
	 * Description :  根据参数获得供应商的展示信息列表，及时加载OrgInfor信息
	 * Create Date: 2010-8-9下午01:44:06 by liangxj  Modified Date: 2010-8-9下午01:44:06 by liangxj
	 * @param   page 分页信息 paramsMap 查询条件：包含品目信息、区域信息、排序信息
	 * @return  
	 * @Exception   
	 */
	public Page<Supplier> getSupplierListForShow(Page<Supplier> page,Map<String, Object> paramsMap) throws Exception ;
	
	/** 
	 * Description :  根据品目获得包含供应商数量的区域信息
	 * Create Date: 2010-8-9下午03:25:57 by liangxj  Modified Date: 2010-8-9下午03:25:57 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<String[]> getDistrictListForShow(String districtId,String categoryCode,Short districtLevel,String keyWord) throws Exception;
	
	/** 
	 * Description :  根据品目获得品目的展示信息集合
	 * Create Date: 2010-7-27下午06:12:58 by liangxj  Modified Date: 2010-7-27下午06:12:58 by liangxj
	 * @param   categoryId 品目id，categoryCode 品目编号, isLeaf 是否只取叶子节点
	 * @return  
	 * @Exception   
	 */
	public List<String[]> getCategoryListShowByCategory(String categoryId,String categoryCode,Boolean isLeaf,String keyWord) throws Exception;
	
	/** 
	 * Description :  根据主键，获得供应商的详细信息，包括基本信息、扩展信息、评价、案例、资质等
	 * Create Date: 2010-8-4下午04:31:42 by liangxj  Modified Date: 2010-8-4下午04:31:42 by liangxj
	 * @param objIds以逗号分隔
	 * @return  
	 * @Exception   
	 */
	public List<Supplier> getSupplierAllInfoList(String objIds) throws Exception;
	
	/** 
	 * Description :  根据主键，获得供应商的详细信息，包括基本信息、扩展信息、评价、案例、资质等
	 * Create Date: 2010-8-4下午04:31:42 by liangxj  Modified Date: 2010-8-4下午04:31:42 by liangxj
	 * @param 
	 * @return  
	 * @Exception   
	 */
	public Supplier getSupplierAllInfo(String objId) throws Exception;
	
	/** 
	 * Description :  取消机构角色申请
	 * 				  删除supplier，并将orginfo的supplier信息置空
	 * Create Date: 2010-7-27上午10:15:01 by sunl  Modified Date: 2010-7-27上午10:15:01 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void updateCancelApply(String supplierId) throws Exception;

	/** 
	 * Description :  保存供应商信息（只包括机构信息）
	 * Create Date: 2010-11-12下午08:29:16 by likg  Modified Date: 2010-11-12下午08:29:16 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	void saveSimpleSupplier(Supplier supplier, OrgInfo orgInfo) throws Exception;
}
