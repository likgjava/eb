package com.gpcsoft.goods.goods.dao;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.goods.goods.domain.GoodsOptionalFitting;

public interface GoodsOptionalFittingDao extends BaseGenericDao<GoodsOptionalFitting> {
	/** 
	 * Description :  禁用选配
	 * Create Date: 2010-8-10下午03:55:09 by sunl  Modified Date: 2010-8-10下午03:55:09 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Integer disableFitting(String objId) throws Exception;
}
