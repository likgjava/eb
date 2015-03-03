package com.gpcsoft.goods.goodsclass.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.goods.goodsclass.dao.GoodsClassParamDao;
import com.gpcsoft.goods.goodsclass.domain.GoodsClassParam;
import com.gpcsoft.goods.goodsclass.manager.GoodsClassParamManager;

@Repository
public class GoodsClassParamManagerImpl extends BaseManagerImpl<GoodsClassParam> implements GoodsClassParamManager {

	@SuppressWarnings("unused")
	@Autowired(required=true)  @Qualifier("goodsClassParamDaoHibernate")
	private GoodsClassParamDao goodsClassParamDaoHibernate;
	
}
