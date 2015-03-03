package com.gpcsoft.bizplatform.suppliers.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.bizplatform.suppliers.dao.SupplierDao;
import com.gpcsoft.bizplatform.suppliers.domain.Supplier;
import com.gpcsoft.bizplatform.suppliers.manager.SupplierManager;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.core.utils.BeanUtils;

@Repository
public class SupplierManagerImpl extends BaseManagerImpl<Supplier> implements SupplierManager {

	@Autowired(required=true)  @Qualifier("supplierDaoHibernate")
	private SupplierDao supplierDaoHibernate;
	
	/** 
	 * Description :  保存或更新供应商扩展信息
	 * 				  传入参数为supplierId和表单对象supplier
	 * Create Date: 2010-7-26下午09:03:47 by sunl  Modified Date: 2010-7-26下午09:03:47 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Supplier saveSupplier(boolean isSave,Supplier supplier) throws Exception {
		if(isSave){//保存
			Supplier oldSupplier = supplierDaoHibernate.get(supplier.getObjId());
			Supplier newSupplier = new Supplier();
			//将数据库旧值拷贝到新对象内,将表单新值拷贝到新对象内
			BeanUtils.copyPropertiesFilterEmpty(newSupplier,oldSupplier);
			BeanUtils.copyPropertiesFilterEmpty(newSupplier,supplier);
			newSupplier.setObjId(null);
			newSupplier.setAuditStatus(OrganizationEnum.AWAIT_EXAM);//新供应商更新为待审核状态
			newSupplier = supplierDaoHibernate.save(newSupplier);
			return newSupplier;
		}else{//更新
			Supplier oldSupplier = supplierDaoHibernate.get(supplier.getObjId());
			BeanUtils.copyPropertiesFilterEmpty(oldSupplier,supplier);
			return oldSupplier;
		}
		
	}
	
	/** 
	 * Description :  更新供应商的审核状态
	 * Create Date: 2010-7-28下午02:29:47 by sunl  Modified Date: 2010-7-28下午02:29:47 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Integer updateSupplierStatus(String supplierId,OrgInfo orgInfo) throws Exception {
		return supplierDaoHibernate.updateSupplierStatus(supplierId, orgInfo);
	}
}
