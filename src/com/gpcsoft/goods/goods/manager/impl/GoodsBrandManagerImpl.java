package com.gpcsoft.goods.goods.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.goods.goods.dao.GoodsBrandDao;
import com.gpcsoft.goods.goods.domain.GoodsBrand;
import com.gpcsoft.goods.goods.manager.GoodsBrandManager;

@Component
public class GoodsBrandManagerImpl extends BaseManagerImpl<GoodsBrand> implements GoodsBrandManager {

	@SuppressWarnings("unused")
	@Autowired(required=true)  @Qualifier("goodsBrandDaoHibernate")
	private GoodsBrandDao goodsBrandDaoHibernate;
}
