package com.gpcsoft.goods.goods.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.goods.goods.dao.GoodsParamDao;
import com.gpcsoft.goods.goods.domain.GoodsParam;
import com.gpcsoft.goods.goods.manager.GoodsParamManager;

@Repository
public class GoodsParamManagerImpl extends BaseManagerImpl<GoodsParam> implements GoodsParamManager {

	@Autowired(required=true)  @Qualifier("goodsParamDaoHibernate")
	private GoodsParamDao goodsParamDaoHibernate;
	
	/**
     * 
     * Description :  根据objId查找商品参数
     * Create Date: 2010-1-30下午04:05:15 by xiaogh  Modified Date: 2010-1-30下午04:05:15 by xiaogh
     * @param   objId
     * @return  GoodsParam
     * @Exception
     */
	public GoodsParam get(String objId){
		return goodsParamDaoHibernate.get(objId);
	}	
}
