package com.gpcsoft.goods.goods.service;
import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.goods.goods.domain.GoodsGift;

public interface GoodsGiftService extends BaseGenericService<GoodsGift> {

	/** 
	 * Description :  保存新增或修改的礼包信息
	 * Create Date: 2011-1-7下午02:30:08 by likg  Modified Date: 2011-1-7下午02:30:08 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	void saveGoodsGift(GoodsGift goodsGift) throws Exception;

}
