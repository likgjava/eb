package com.gpcsoft.goods.goods.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.goods.goods.dao.GoodsOptionalFittingDao;
import com.gpcsoft.goods.goods.domain.GoodsOptionalFitting;
import com.gpcsoft.goods.goods.service.GoodsOptionalFittingService;

@Service 
public class GoodsOptionalFittingServiceImpl extends BaseGenericServiceImpl<GoodsOptionalFitting> implements GoodsOptionalFittingService {

	@Autowired(required=true) @Qualifier("goodsOptionalFittingDaoHibernate")
	private GoodsOptionalFittingDao goodsOptionalFittingDaoHibernate;

	/** 
	 * Description :  禁用选配
	 * Create Date: 2010-8-10下午03:55:09 by sunl  Modified Date: 2010-8-10下午03:55:09 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Integer disableFitting(String objId) throws Exception {
		return goodsOptionalFittingDaoHibernate.disableFitting(objId);
	}
}
