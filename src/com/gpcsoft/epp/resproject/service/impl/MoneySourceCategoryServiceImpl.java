package com.gpcsoft.epp.resproject.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.epp.resproject.dao.MoneySourceCategoryDao;
import com.gpcsoft.epp.resproject.domain.MoneySourceCategory;
import com.gpcsoft.epp.resproject.service.MoneySourceCategoryService;

@Service 
public class MoneySourceCategoryServiceImpl extends BaseGenericServiceImpl<MoneySourceCategory> implements MoneySourceCategoryService {
	
	@Autowired(required = true) @Qualifier("moneySourceCategoryDaoHibernate")
	private MoneySourceCategoryDao moneySourceCategoryDao;

	/**
	 * 查询资金来源类型集合
	 * @param type 资金类型
	 * @return
	 * @throws Exception
	 */
	public List<MoneySourceCategory> getMoneySourceCategoryList(String type)throws Exception {
		return moneySourceCategoryDao.getMoneySourceCategoryList(type);
	}

}
