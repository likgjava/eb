package com.gpcsoft.goods.goods.service;

import java.util.Map;

import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.goods.goods.domain.GoodsEvaluate;

public interface GoodsEvaluateService extends BaseGenericService<GoodsEvaluate> {

	/** 
	 * Description :  保存商品评价
	 * Create Date: 2010-8-17下午02:17:46 by yucy  Modified Date: 2010-8-17下午02:17:46 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Map<String, Object> saveGoodsEvaluate(Map<String, Object> param) throws Exception;

}
