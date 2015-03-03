package com.gpcsoft.goods.goodsclass.manager;


import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.goods.goodsclass.domain.GoodsClassCategory;

public interface GoodsClassCategoryManager extends BaseManager<GoodsClassCategory> {
	
	/** 
	 * Description :  删除中间对象
	 * Create Date: 2010-12-24下午03:20:03 by yucy  Modified Date: 2010-12-24下午03:20:03 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean deleteGoodsClassCategoryByClassId(String classIds) throws Exception;

}
