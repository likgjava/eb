package com.gpcsoft.epp.resproject.service;

import java.util.List;

import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.epp.resproject.domain.MoneySourceCategory;

public interface MoneySourceCategoryService extends BaseGenericService<MoneySourceCategory>{

	/**
	 * 查询资金来源类型集合
	 * @param type 资金类型
	 * @return
	 * @throws Exception
	 */
	public List<MoneySourceCategory> getMoneySourceCategoryList(String type)throws Exception;
}
