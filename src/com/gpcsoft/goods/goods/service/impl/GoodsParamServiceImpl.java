package com.gpcsoft.goods.goods.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.goods.goods.domain.GoodsOptionalFitting;
import com.gpcsoft.goods.goods.domain.GoodsParam;
import com.gpcsoft.goods.goods.manager.GoodsParamManager;
import com.gpcsoft.goods.goods.service.GoodsParamService;
import com.gpcsoft.srplatform.common.utils.CollectionUtil;

@Service 
public class GoodsParamServiceImpl extends BaseGenericServiceImpl<GoodsParam> implements GoodsParamService {

	@SuppressWarnings("unused")
	@Autowired(required=true) @Qualifier("goodsParamManagerImpl")
	private GoodsParamManager goodsParamManager;
	/**
	 * 简化商品参数，提高查询
	 * @param goodsParam
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public GoodsParam simpleGoodsParam(GoodsParam goodsParam){
		Set<GoodsOptionalFitting> goodsOptionalFitting = new HashSet<GoodsOptionalFitting>();
		List<GoodsOptionalFitting> goodsOptionalFittings = CollectionUtil.covSetToList(goodsParam.getGoodsOptionalFittingSet());
		for(GoodsOptionalFitting fitting : goodsOptionalFittings){
			GoodsOptionalFitting ofitting = new GoodsOptionalFitting();
			ofitting.setObjId(fitting.getObjId());
			ofitting.setOptionContent(fitting.getOptionContent());
			goodsOptionalFitting.add(ofitting);
		}
		goodsParam.setGoodsOptionalFittingSet(goodsOptionalFitting);
		return goodsParam;
	}
}
