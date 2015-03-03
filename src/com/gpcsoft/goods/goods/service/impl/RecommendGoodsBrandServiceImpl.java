package com.gpcsoft.goods.goods.service.impl;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.goods.goods.dao.RecommendGoodsBrandDao;
import com.gpcsoft.goods.goods.domain.GoodsBrand;
import com.gpcsoft.goods.goods.domain.RecommendGoodsBrand;
import com.gpcsoft.goods.goods.service.RecommendGoodsBrandService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;

@Service 
public class RecommendGoodsBrandServiceImpl extends BaseGenericServiceImpl<RecommendGoodsBrand> implements RecommendGoodsBrandService {

//	@Autowired(required=true) @Qualifier("recommendGoodsBrandManagerImpl")
//	private RecommendGoodsBrandManager recommendGoodsBrandManager;
	
	@Autowired(required=true)  @Qualifier("recommendGoodsBrandDaoHibernate")
	private RecommendGoodsBrandDao recommendGoodsBrandDaoHibernate;
	
	/** 
	 * Description :  保存推荐的商品品牌
	 * Create Date: 2011-5-16下午04:29:43 by likg  Modified Date: 2011-5-16下午04:29:43 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void saveRecommendGoodsBrand(String goodsBrandIds, String recommendReason) throws Exception {
		//推荐商品品牌模板
		RecommendGoodsBrand recommendGoodsBrand = new RecommendGoodsBrand();
		recommendGoodsBrand.setReason(recommendReason);
		recommendGoodsBrand.setRecommender(AuthenticationHelper.getCurrentUser(true).getObjId());
		recommendGoodsBrand.setCreateTime(new Date());
		recommendGoodsBrand.setSort(0L);
		
		recommendGoodsBrandDaoHibernate.saveRecommendGoodsBrand(goodsBrandIds, recommendGoodsBrand);
	}
	
	/** 
	 * Description :  获取所有未推荐的商品品牌
	 * Create Date: 2011-5-16下午04:11:57 by likg  Modified Date: 2011-5-16下午04:11:57 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Page<GoodsBrand> listNoRecommendGoodsBrand(Map<String, Object> param, Page<GoodsBrand> page) throws Exception {
		return recommendGoodsBrandDaoHibernate.listNoRecommendGoodsBrand(param, page);
	}
	
	/** 
	 * Description :  修改推荐商品品牌的排序序号
	 * Create Date: 2011-5-16下午04:20:32 by likg  Modified Date: 2011-5-16下午04:20:32 by likg
	 * @param   objId:要排序的推荐商品品牌的Id	isToUp:排序方向
	 * @return  
	 * @Exception   
	 */
	public void updateSort(String objId, boolean isToUp) throws Exception {
		recommendGoodsBrandDaoHibernate.updateSort(objId, isToUp);
	}

	/** 
	 * Description :  获得推荐商品品牌
	 * Create Date: 2011-5-16下午04:32:47 by likg  Modified Date: 2011-5-16下午04:32:47 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Page<RecommendGoodsBrand> getRecommendGoodsBrand(Page<RecommendGoodsBrand> page, Map<String, Object> paramsMap) throws Exception {
		return recommendGoodsBrandDaoHibernate.getRecommendGoodsBrand(page, paramsMap);
	}

}
