package com.gpcsoft.epp.common.dao;

import java.util.List;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.epp.common.domain.FactorDe;

public interface FactorDeDao extends BaseGenericDao<FactorDe> {

	/**
	 * @Description: 根据指标ID获取所有指标
	 * @param factorIds
	 * @return List<FactorDe>
	 * @throws Exception
	 * @Create Date 2010-8-11 上午10:36:49 By wanghz
	 */
	public List<FactorDe> getAllByFactorDeIds(String[] factorIds);
}
