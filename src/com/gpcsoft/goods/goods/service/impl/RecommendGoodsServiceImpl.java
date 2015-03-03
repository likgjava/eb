package com.gpcsoft.goods.goods.service.impl;

import java.util.Date;
import java.util.Map;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.goods.goods.manager.RecommendGoodsManager;
import com.gpcsoft.goods.goods.service.RecommendGoodsService;
import com.gpcsoft.goods.goods.dao.RecommendGoodsDao;
import com.gpcsoft.goods.goods.domain.Goods;
import com.gpcsoft.goods.goods.domain.RecommendGoods;

@Service 
public class RecommendGoodsServiceImpl extends BaseGenericServiceImpl<RecommendGoods> implements RecommendGoodsService 
{
	@Autowired(required=true) @Qualifier("recommendGoodsManagerImpl")
	private RecommendGoodsManager recommendGoodsManager;

	@Autowired(required=true)  @Qualifier("recommendGoodsDaoHibernate")
	private RecommendGoodsDao recommendGoodsDaoHibernate;

	/** 
	 * Description :  推荐商品
	 * Create Date: 2010-10-11 下午 16:30:16 by likg  Modified Date: 2010-10-11 下午 16:30:16 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void recommendGoods(String goodsIds, String recommendReason) throws Exception
	{
		RecommendGoods recommendGoodsPattern = new RecommendGoods();
		recommendGoodsPattern.setReason(recommendReason);
		recommendGoodsPattern.setAuditStatus("00");
		recommendGoodsPattern.setUseStatus(false);
		recommendGoodsPattern.setCreateTime(new Date());
		recommendGoodsPattern.setSort(0L);
		
		recommendGoodsDaoHibernate.recommendGoods(goodsIds.split(","), recommendGoodsPattern);
	}

	/** 
	 * Description :  获取所有的未推荐商品
	 * Create Date: 2010-10-13 下午 16:30:16 by likg  Modified Date: 2010-10-13 下午 16:30:16 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Page<Goods> listNoRecommendGoods(Map<String,Object> param, Page<Goods> page) throws Exception {
		return recommendGoodsDaoHibernate.listNoRecommendGoods(param, page);
	}

	/** 
	 * Description :  修改推荐商品的排序序号
	 * Create Date: 2010-10-15上午10:43:42 by likg  Modified Date: 2010-10-15上午10:43:42 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void updateSort(String sourceObjId, boolean isToUp) throws Exception 
	{
		RecommendGoods sourceRecommendGoods = recommendGoodsManager.get(sourceObjId);
		recommendGoodsDaoHibernate.updateSort(sourceRecommendGoods.getSort(), isToUp);
	}

	/** 
	 * Description :  获得推荐商品
	 * Create Date: 2010-11-5下午05:28:09 by likg  Modified Date: 2010-11-5下午05:28:09 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Page<RecommendGoods> getRecommendGoods(Page<RecommendGoods> page, Map<String, Object> paramsMap) throws Exception {
		return recommendGoodsDaoHibernate.getRecommendGoods(page, paramsMap);
	}
}
