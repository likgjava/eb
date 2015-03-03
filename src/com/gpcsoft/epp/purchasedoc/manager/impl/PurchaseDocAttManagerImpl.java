package com.gpcsoft.epp.purchasedoc.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.epp.purchasedoc.dao.PurchaseDocAttDao;
import com.gpcsoft.epp.purchasedoc.domain.PurchaseDocAtt;
import com.gpcsoft.epp.purchasedoc.manager.PurchaseDocAttManager;

@Repository
public class PurchaseDocAttManagerImpl extends BaseManagerImpl<PurchaseDocAtt> implements PurchaseDocAttManager {

	@Autowired(required=true)  @Qualifier("purchaseDocAttDaoHibernate")
	private PurchaseDocAttDao purchaseDocAttDaoHibernate;


	/** 
	 * FuncName : getPurchaseDocAttByPurchaseDocId
	 * Description :  根据招标文件Id获取附件对象
	 * Create Date: 2011-9-21上午11:41:44 by yangx  Modified Date: 2011-9-21上午11:41:44 by yangx
	 * @param   purchaseDocId：招标文件Id 
	 * @return  List<PurchaseDocAtt>
	 * @Exception   
	 */
	public List<PurchaseDocAtt> getPurchaseDocAttByPurchaseDocId(String purchaseDocId){
		return purchaseDocAttDaoHibernate.getPurchaseDocAttByPurchaseDocId(purchaseDocId);
	}
}
