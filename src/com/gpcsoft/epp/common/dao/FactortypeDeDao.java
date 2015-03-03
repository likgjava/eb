package com.gpcsoft.epp.common.dao;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.epp.common.domain.FactortypeDe;
import com.gpcsoft.epp.common.exception.EsException;

public interface FactortypeDeDao extends BaseGenericDao<FactortypeDe> {

	/**
	 * @Description: 根据指标分类ID,获取下级指标分类记录条数
	 * @param 
	 * @return Integer
	 * @throws Exception
	 * @Create Date 2010-8-10 下午03:44:05 By wanghz
	 */
	public Integer getsubFactortypeDes(String factortypeId)throws EsException;
}
