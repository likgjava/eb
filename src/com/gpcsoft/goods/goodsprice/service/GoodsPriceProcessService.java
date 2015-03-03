package com.gpcsoft.goods.goodsprice.service;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.ListOrderedMap;

import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.goods.goodsprice.domain.GoodsPriceProcess;

public interface GoodsPriceProcessService extends BaseGenericService<GoodsPriceProcess> {

	/** 
	 * Description :  获取行情图形
	 * Create Date: 2010-10-12下午02:23:46 by yucy  Modified Date: 2010-10-12下午02:23:46 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<ListOrderedMap> getPriceChartDate(Map<String, Object> param) throws Exception;

}
