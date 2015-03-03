package com.gpcsoft.bizplatform.suppliers.manager;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.suppliers.domain.Supplier;
import com.gpcsoft.core.manager.BaseManager;

public interface SupplierManager extends BaseManager<Supplier> {
	

	/** 
	 * Description :  保存或更新供应商扩展信息
	 * 				  传入参数为supplierId和表单对象supplier
	 * Create Date: 2010-7-26下午09:03:47 by sunl  Modified Date: 2010-7-26下午09:03:47 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Supplier saveSupplier(boolean isSave,Supplier supplier) throws Exception;
	
	
	/** 
	 * Description :  更新供应商的审核状态
	 * Create Date: 2010-7-28下午02:29:47 by sunl  Modified Date: 2010-7-28下午02:29:47 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Integer updateSupplierStatus(String supplierId,OrgInfo orgInfo) throws Exception;
}
