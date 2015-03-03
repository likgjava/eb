package com.gpcsoft.goods.goods.dao;

import java.util.Map;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.goods.goods.domain.GoodsBrand;
import com.gpcsoft.goods.goods.domain.RecommendGoodsBrand;

public interface RecommendGoodsBrandDao extends BaseGenericDao<RecommendGoodsBrand> {

	/** 
	 * Description :  保存推荐的商品品牌
	 * Create Date: 2011-5-16下午04:29:43 by likg  Modified Date: 2011-5-16下午04:29:43 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	void saveRecommendGoodsBrand(String goodsBrandIds, RecommendGoodsBrand recommendGoodsBrand) throws Exception;

	/** 
	 * Description :  获取所有未推荐的商品品牌
	 * Create Date: 2011-5-16下午04:11:57 by likg  Modified Date: 2011-5-16下午04:11:57 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Page<GoodsBrand> listNoRecommendGoodsBrand(Map<String, Object> param, Page<GoodsBrand> page) throws Exception;

	/** 
	 * Description :  修改推荐商品品牌的排序序号
	 * Create Date: 2011-5-16下午04:20:32 by likg  Modified Date: 2011-5-16下午04:20:32 by likg
	 * @param   objId:要排序的推荐商品品牌的Id	isToUp:排序方向
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
