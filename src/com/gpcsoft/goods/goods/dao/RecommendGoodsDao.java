package com.gpcsoft.goods.goods.dao;

import java.util.Map;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.goods.goods.domain.Goods;
import com.gpcsoft.goods.goods.domain.RecommendGoods;

public interface RecommendGoodsDao extends BaseGenericDao<RecommendGoods> 
{
	/** 
	 * Description :  推荐指定的商品
	 * Create Date: 2010-10-11 下午 16:30:16 by likg  Modified Date: 2010-10-11 下午 16:30:16 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	void recommendGoods(String[] goodsIds, RecommendGoods recommendGoodsPattern) throws Exception;
	
	/** 
	 * Description :  获取所有的未推荐商品
	 * Create Date: 2010-10-13 下午 16:30:16 by likg  Modified Date: 2010-10-13 下午 16:30:16 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Page<Goods> listNoRecommendGoods(Map<String,Object> param, Page<Goods> page) throws Exception;
	
	/** 
	 * Description :  获得推荐商品
	 * Create Date: 2010-11-5下午05:30:58 by likg  Modified Date: 2010-11-5下午05:30:58 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Page<RecommendGoods> getRecommendGoods(Page<RecommendGoods> page, Map<String, Object> paramsMap) throws Exception;

	/** 
	 * Description :  修改推荐商品的排序序号
	 * Create Date: 2010-12-22上午10:43:42 by likg  Modified Date: 2010-12-22上午10:43:42 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	void updateSort(Long sort, boolean isToUp) throws Exception;
}
