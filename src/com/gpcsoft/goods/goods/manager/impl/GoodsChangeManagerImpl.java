package com.gpcsoft.goods.goods.manager.impl;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.goods.goods.dao.GoodsChangeDao;
import com.gpcsoft.goods.goods.manager.GoodsChangeManager;
import com.gpcsoft.goods.goods.domain.GoodsChange;

@Repository
public class GoodsChangeManagerImpl extends BaseManagerImpl<GoodsChange> implements GoodsChangeManager {

	@SuppressWarnings("unused")
	@Autowired(required=true)  @Qualifier("goodsChangeDaoHibernate")
	private GoodsChangeDao goodsChangeDaoHibernate;

}
