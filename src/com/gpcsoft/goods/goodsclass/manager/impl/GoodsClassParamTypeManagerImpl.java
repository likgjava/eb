package com.gpcsoft.goods.goodsclass.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.goods.goodsclass.dao.GoodsClassParamTypeDao;
import com.gpcsoft.goods.goodsclass.domain.GoodsClassParamType;
import com.gpcsoft.goods.goodsclass.manager.GoodsClassParamTypeManager;

@Repository
public class GoodsClassParamTypeManagerImpl extends BaseManagerImpl<GoodsClassParamType> implements GoodsClassParamTypeManager {

	@SuppressWarnings("unused")
	@Autowired(required=true)  @Qualifier("goodsClassParamTypeDaoHibernate")
	private GoodsClassParamTypeDao goodsClassParamTypeDaoHibernate;

    
}
