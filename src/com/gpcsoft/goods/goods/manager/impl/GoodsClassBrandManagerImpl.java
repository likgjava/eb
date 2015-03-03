package com.gpcsoft.goods.goods.manager.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.goods.goods.dao.GoodsClassBrandDao;
import com.gpcsoft.goods.goods.domain.GoodsClassBrand;
import com.gpcsoft.goods.goods.manager.GoodsClassBrandManager;

@Repository
public class GoodsClassBrandManagerImpl extends BaseManagerImpl<GoodsClassBrand> implements GoodsClassBrandManager {

	@Autowired(required=true)  @Qualifier("goodsClassBrandDaoHibernate")
	private GoodsClassBrandDao goodsClassBrandDaoHibernate;

	/** 
	 * Description :  根据商品品牌删除中间表
	 * Create Date: 2010-8-5上午11:08:36 by yucy  Modified Date: 2010-8-5上午11:08:36 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean deleteGoodsClassBrandByBrandId(String brandIds) throws Exception {
		return goodsClassBrandDaoHibernate.deleteGoodsClassBrandByBrandId(brandIds);
	}

}
