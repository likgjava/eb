package com.gpcsoft.smallscale.pointmall.service;
import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.smallscale.pointmall.domain.GiftSupplier;

public interface GiftSupplierService extends BaseGenericService<GiftSupplier> {
	
	/**
	 * Description :  保存礼品供货商
	 * Create Date: 2011-1-12上午11:41:42 by zhaojf  Modified Date: 2011-1-12上午11:41:42 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public void saveGiftSupplier(GiftSupplier giftSupplier,String saveType) throws Exception;
	
	
	/**
	 * Description :  判断礼品供货商是否唯一
	 * Create Date: 2011-1-12下午03:51:37 by zhaojf  Modified Date: 2011-1-12下午03:51:37 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public boolean isUnique(String giftSupplierName) throws Exception;
	
	
	/**
	 * Description :  删除
	 * Create Date: 2011-1-12下午04:11:10 by zhaojf  Modified Date: 2011-1-12下午04:11:10 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public String removeGiftSupplier(String objId) throws Exception;
	
	
	/**
	 * Description :  禁用或启用
	 * Create Date: 2011-1-12下午05:22:37 by zhaojf  Modified Date: 2011-1-12下午05:22:37 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public void modifyIsUsedStatus(String objId,boolean isUsed) throws Exception;
	
	
	/**
	 * Description :  获取被禁用的礼品供货商Id(多个用逗号分开)
	 * Create Date: 2011-1-20下午04:42:43 by zhaojf  Modified Date: 2011-1-20下午04:42:43 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public String getObjIdsOfNotUsed() throws Exception;

}
