package com.gpcsoft.epp.common.service;
import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.epp.common.domain.FactorDe;
import com.gpcsoft.epp.common.exception.EsException;

public interface FactorDeService extends BaseGenericService<FactorDe> {

	/**
	 * @Description: 创建指标
	 * @param factorDe
	 * @return FactorDe
	 * @throws Exception
	 * @Create Date 2010-9-7 下午03:59:21 By wanghz
	 */
	public FactorDe saveCreateFactorDe(FactorDe factorDe)throws EsException;
	
	/**
	 * @Description: 更新指标
	 * @param factorDe
	 * @return FactorDe
	 * @throws Exception
	 * @Create Date 2010-9-7 下午03:59:21 By wanghz
	 */
	public FactorDe saveUpdateFactorDe(FactorDe factorDe)throws EsException;
	
	/**
	 * @Description: 根据ID删除指标
	 * @return void
	 * @throws Exception
	 * @Create Date 2010-9-7 下午04:08:09 By wanghz
	 */
	public void removeFactorDe(String objId)throws EsException;
}
