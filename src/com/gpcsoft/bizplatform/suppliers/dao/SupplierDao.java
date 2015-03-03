package com.gpcsoft.bizplatform.suppliers.dao;

import java.util.List;
import java.util.Map;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.suppliers.domain.Supplier;
import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.core.utils.Page;

public interface SupplierDao extends BaseGenericDao<Supplier> {

	/** 
	 * Description :  更新供应商的审核状态
	 * Create Date: 2010-7-28下午02:29:47 by sunl  Modified Date: 2010-7-28下午02:29:47 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Integer updateSupplierStatus(String supplierId,OrgInfo orgInfo) throws Exception;
	
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
	 * Description :  根据品目获得下级品目的展示信息集合
	 * Create Date: 2010-7-27下午06:12:58 by liangxj  Modified Date: 2010-7-27下午06:12:58 by liangxj
	 * @param   categoryId 品目id，categoryCode 品目编号, isLeaf 是否只取叶子节点
	 * @return  
	 * @Exception   
	 */
	public List<String[]> getCategoryListShowByCategory(String categoryId,String categoryCode,Boolean isLeaf,String keyWord) throws Exception;
	
	/** 
	 * Description :  根据主键，获得供应商的详细信息，包括基本信息、扩展信息、评价、案例、资质等
	 * Create Date: 2010-8-4下午04:31:42 by liangxj  Modified Date: 2010-8-4下午04:31:42 by liangxj
	 * @param 
	 * @return  
	 * @Exception   
	 */
	public Supplier getSupplierAllInfo(String objId) throws Exception;
	
	/** 
	 * Description :  根据主键，获得供应商的详细信息，包括基本信息、扩展信息、评价、案例、资质等
	 * Create Date: 2010-8-4下午04:31:42 by liangxj  Modified Date: 2010-8-4下午04:31:42 by liangxj
	 * @param objIds以逗号分隔
	 * @return  
	 * @Exception   
	 */
	public List<Supplier> getSupplierAllInfoList(String objIds) throws Exception;
	
	
	/** 
	 * Description :  根据orgInfoId获取供应商信息
	 * Create Date: 2010-8-17下午01:55:35 by sunl  Modified Date: 2010-8-17下午01:55:35 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Supplier getSupplierByOrgInfoId(String orgInfoId) throws Exception;
}
