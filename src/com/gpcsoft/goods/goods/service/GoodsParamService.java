package com.gpcsoft.goods.goods.service;
import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.goods.goods.domain.GoodsParam;

public interface GoodsParamService extends BaseGenericService<GoodsParam> {

	/**
	 * 简化商品参数，提高查询
	 * @param goodsParam
	 * @return
	 */
	public GoodsParam simpleGoodsParam(GoodsParam goodsParam);
}
