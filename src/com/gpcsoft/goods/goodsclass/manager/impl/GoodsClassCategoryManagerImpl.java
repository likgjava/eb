package com.gpcsoft.goods.goodsclass.manager.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.goods.goodsclass.dao.GoodsClassCategoryDao;
import com.gpcsoft.goods.goodsclass.domain.GoodsClassCategory;
import com.gpcsoft.goods.goodsclass.manager.GoodsClassCategoryManager;

@Repository
public class GoodsClassCategoryManagerImpl extends BaseManagerImpl<GoodsClassCategory> implements GoodsClassCategoryManager {

	@Autowired(required=true)  @Qualifier("goodsClassCategoryDaoHibernate")
	private GoodsClassCategoryDao goodsClassCategoryDaoHibernate;

	/** 
	 * Description :  删除中间对象
	 * Create Date: 2010-12-24下午03:20:03 by yucy  Modified Date: 2010-12-24下午03:20:03 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean deleteGoodsClassCategoryByClassId(String ClassIds)
			throws Exception {
		return goodsClassCategoryDaoHibernate.deleteGoodsClassCategoryByClassId(ClassIds);
	}

}
