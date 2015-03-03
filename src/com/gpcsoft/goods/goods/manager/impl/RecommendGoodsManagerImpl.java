package com.gpcsoft.goods.goods.manager.impl;

import org.springframework.stereotype.Repository;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.goods.goods.manager.RecommendGoodsManager;
import com.gpcsoft.goods.goods.domain.RecommendGoods;

@Repository
public class RecommendGoodsManagerImpl extends BaseManagerImpl<RecommendGoods> implements RecommendGoodsManager {

	//@Autowired(required=true)  @Qualifier("recommendGoodsDaoHibernate")
	//private RecommendGoodsDao recommendGoodsDaoHibernate;

}
