package com.gpcsoft.goods.goodsprice.dao;

import java.util.Map;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.goods.goodsprice.domain.AttentionPrice;

public interface AttentionPriceDao extends BaseGenericDao<AttentionPrice> {

	/** 
	 * Description :  取得当前关注的城市
	 * Create Date: 2010-11-4上午11:35:05 by yucy  Modified Date: 2010-11-4上午11:35:05 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Object[] getCurrentAttentionCity(Map<String, Object> param) throws Exception;

	/** 
	 * Description : 改变关注区域
	 * Create Date: 2010-11-4下午01:49:20 by yucy  Modified Date: 2010-11-4下午01:49:20 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Boolean changerAttentionCity(Map<String, Object> param) throws Exception;

	/** 
	 * Description :  是否关注过
	 * Create Date: 2010-11-17下午05:57:37 by yucy  Modified Date: 2010-11-17下午05:57:37 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Boolean hasAttention(Map<String, Object> param) throws Exception;

	/** 
	 * Description :  取得商品被关注的信息
	 * Create Date: 2011-2-28下午02:01:58 by yucy  Modified Date: 2011-2-28下午02:01:58 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Integer getAttentionNumberByGoodsId(String goodsId) throws Exception;

}
