package com.gpcsoft.goods.goodsprice.dao;

import java.util.List;
import java.util.Map;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.goods.goodsprice.domain.GoodsPriceProcess;

public interface GoodsPriceProcessDao extends BaseGenericDao<GoodsPriceProcess> {

	/** 
	 * Description :  获取行情图形
	 * Create Date: 2010-10-12下午02:23:46 by yucy  Modified Date: 2010-10-12下午02:23:46 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<Object[]> getPriceChartDate(Map<String, Object> param) throws Exception;

}
