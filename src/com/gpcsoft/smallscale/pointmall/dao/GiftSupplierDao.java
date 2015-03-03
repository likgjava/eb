package com.gpcsoft.smallscale.pointmall.dao;

import java.util.Date;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.smallscale.pointmall.domain.GiftSupplier;

public interface GiftSupplierDao extends BaseGenericDao<GiftSupplier> {
	
	/**
	 * Description :   判断礼品供货商是否唯一
	 * Create Date: 2011-1-12下午03:54:07 by zhaojf  Modified Date: 2011-1-12下午03:54:07 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public boolean isUnique(String giftSupplierName);
	
	
	/**
	 * Description :  判断此供货商是否还包含有礼品
	 * Create Date: 2011-1-12下午04:16:20 by zhaojf  Modified Date: 2011-1-12下午04:16:20 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public boolean isHasGiftByGiftSupplier(String objId);
	
	
	/**
	 * Description :  禁用或启用
	 * Create Date: 2011-1-12下午05:25:19 by zhaojf  Modified Date: 2011-1-12下午05:25:19 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public void modifyIsUsedStatus(String objId,boolean isUsed);
	
	/**
	 * Description :  修改礼品供货商
	 * Create Date: 2011-1-13上午10:12:05 by zhaojf  Modified Date: 2011-1-13上午10:12:05 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public void modifyGiftSupplier(String objId,Date startTime,Date endTime);

}
