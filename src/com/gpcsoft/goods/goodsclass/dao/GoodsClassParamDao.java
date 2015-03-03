package com.gpcsoft.goods.goodsclass.dao;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.goods.goodsclass.domain.GoodsClassParam;

public interface GoodsClassParamDao extends BaseGenericDao<GoodsClassParam> {
    
	/** 
	 * Description : 查询商品参数被GoodsParam引用的个数
	 * Create Date: 2010-8-3下午02:47:14 by guoyr  Modified Date: 2010-8-3下午02:47:14 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Long getGoodsClassParamCountFromGoodsParam(String objId);
}
