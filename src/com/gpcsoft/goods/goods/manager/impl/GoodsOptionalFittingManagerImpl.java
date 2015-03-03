package com.gpcsoft.goods.goods.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.goods.goods.dao.GoodsOptionalFittingDao;
import com.gpcsoft.goods.goods.domain.GoodsOptionalFitting;
import com.gpcsoft.goods.goods.manager.GoodsOptionalFittingManager;

@Repository
public class GoodsOptionalFittingManagerImpl extends BaseManagerImpl<GoodsOptionalFitting> implements GoodsOptionalFittingManager {

	@SuppressWarnings("unused")
	@Autowired(required=true)  @Qualifier("goodsOptionalFittingDaoHibernate")
	private GoodsOptionalFittingDao goodsOptionalFittingDaoHibernate;

}
