package com.gpcsoft.bizplatform.buyer.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.bizplatform.buyer.dao.BuyerDao;
import com.gpcsoft.bizplatform.buyer.domain.Buyer;
import com.gpcsoft.bizplatform.buyer.manager.BuyerManager;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.core.utils.BeanUtils;
@Repository
public class BuyerManagerImpl extends BaseManagerImpl<Buyer> implements BuyerManager {

	@Autowired(required=true)  @Qualifier("buyerDaoHibernate")
	private BuyerDao buyerDaoHibernate;

	/** 
	 * Description : 更新采购人审核状态  
	 * Create Date: 2010-7-28下午02:50:44 by sunl  Modified Date: 2010-7-28下午02:50:44 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Integer updateBuyerStatus(String buyerId,OrgInfo orgInfo) throws Exception {
		return buyerDaoHibernate.updateBuyerStatus(buyerId, orgInfo);
	}
	
	/** 
	 * Description :  保存或更新采购人扩展信息
	 * 				  传入参数为buyerId和表单对象buyer
	 * Create Date: 2010-7-26下午09:03:47 by sunl  Modified Date: 2010-7-26下午09:03:47 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Buyer saveBuyer(boolean isSave,Buyer buyer) throws Exception {
		if(isSave){//保存
			Buyer oldBuyer = buyerDaoHibernate.get(buyer.getObjId());
			Buyer newBuyer = new Buyer();
			//将数据库旧值拷贝到新对象内,将表单新值拷贝到新对象内
			BeanUtils.copyPropertiesFilterEmpty(newBuyer,oldBuyer);
			BeanUtils.copyPropertiesFilterEmpty(newBuyer,buyer);
			newBuyer.setObjId(null);
			newBuyer.setAuditStatus(OrganizationEnum.AWAIT_EXAM);//新采购人更新为待审核状态
			newBuyer = buyerDaoHibernate.save(newBuyer);
			return newBuyer;
		}else{//更新
			Buyer oldBuyer = buyerDaoHibernate.get(buyer.getObjId());
			BeanUtils.copyPropertiesFilterEmpty(oldBuyer,buyer);
			return oldBuyer;
		}
	}
}
