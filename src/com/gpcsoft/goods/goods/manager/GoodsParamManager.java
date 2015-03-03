package com.gpcsoft.goods.goods.manager;

import com.gpcsoft.goods.goods.domain.GoodsParam;

public interface GoodsParamManager {
	/**
     * 
     * Description :  根据objId查找商品参数
     * Create Date: 2010-1-30下午04:05:15 by xiaogh  Modified Date: 2010-1-30下午04:05:15 by xiaogh
     * @param   objId
     * @return  GoodsParam
     * @Exception
     */
	public GoodsParam get(String objId);
}
