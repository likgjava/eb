package com.gpcsoft.goods.goods.manager;


import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.goods.goods.domain.GoodsClassBrand;

public interface GoodsClassBrandManager extends BaseManager<GoodsClassBrand> {
	
	/** 
	 * Description :  根据商品品牌删除中间表
	 * Create Date: 2010-8-5上午11:08:36 by yucy  Modified Date: 2010-8-5上午11:08:36 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean deleteGoodsClassBrandByBrandId(String brandIds) throws Exception;
	
}
