package com.gpcsoft.goods.goods.dao;


import java.util.Map;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.goods.goods.domain.GoodsEvaluate;

public interface GoodsEvaluateDao extends BaseGenericDao<GoodsEvaluate> {

	
	/** 
	 * Description :  是否已评价
	 * Create Date: 2010-8-17下午02:17:46 by yucy  Modified Date: 2010-8-17下午02:17:46 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Long hasGoodsEvaluate(Map<String, Object> param) throws Exception;

}
