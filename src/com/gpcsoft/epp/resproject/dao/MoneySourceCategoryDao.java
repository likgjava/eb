package com.gpcsoft.epp.resproject.dao;

import java.util.List;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.epp.resproject.domain.MoneySourceCategory;

public interface MoneySourceCategoryDao extends BaseGenericDao<MoneySourceCategory>{

	/**
	 * 查询资金来源类型集合
	 * @param type 代理机构ID
	 * @return
	 * @throws Exception
	 */
	public List<MoneySourceCategory> getMoneySourceCategoryList(String type)throws Exception;
}
