package com.gpcsoft.epp.purchasedoc.manager;

import java.util.List;

import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.epp.purchasedoc.domain.PurchaseDocAtt;

public interface PurchaseDocAttManager extends BaseManager<PurchaseDocAtt> {
	
	
	/** 
	 * FuncName : getPurchaseDocAttByPurchaseDocId
	 * Description :  根据招标文件Id获取附件对象
	 * Create Date: 2011-9-21上午11:41:44 by yangx  Modified Date: 2011-9-21上午11:41:44 by yangx
	 * @param   purchaseDocId：招标文件Id 
	 * @return  List<PurchaseDocAtt>
	 * @Exception   
	 */
	public List<PurchaseDocAtt> getPurchaseDocAttByPurchaseDocId(String purchaseDocId);	
}
