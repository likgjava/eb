package com.gpcsoft.goods.goods.service;
import java.util.Map;

import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.goods.goods.domain.GoodsBrand;
import com.gpcsoft.goods.goods.domain.RecommendGoodsBrand;

public interface RecommendGoodsBrandService extends BaseGenericService<RecommendGoodsBrand> {
	
	/** 
	 * Description :  保存推荐的商品品牌
	 * Create Date: 2011-5-16下午04:29:43 by likg  Modified Date: 2011-5-16下午04:29:43 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	void saveRecommendGoodsBrand(String goodsBrandIds, String recommendReason) throws Exception;
	
	/** 
	 * Description :  获取所有的未推荐商品品牌
	 * Create Date: 2011-5-16下午04:30:45 by likg  Modified Date: 2011-5-16下午04:30:45 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Page<GoodsBrand> listNoRecommendGoodsBrand(Map<String, Object> param, Page<GoodsBrand> page) throws Exception;
	
	/** 
	 * Description :  修改推荐商品品牌的排序序号
	 * Create Date: 2011-5-16下午04:31:45 by likg  Modified Date: 2011-5-16下午04:31:45 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	void updateSort(String objId, boolean isToUp) throws Exception;
	
	/** 
	 * Description :  获得推荐商品品牌
	 * Create Date: 2011-5-16下午04:32:47 by likg  Modified Date: 2011-5-16下午04:32:47 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Page<RecommendGoodsBrand> getRecommendGoodsBrand(Page<RecommendGoodsBrand> page, Map<String, Object> paramsMap) throws Exception;
	
}
