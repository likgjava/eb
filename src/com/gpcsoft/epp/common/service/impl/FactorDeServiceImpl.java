package com.gpcsoft.epp.common.service.impl;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.epp.common.domain.FactorDe;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.common.manager.FactorDeManager;
import com.gpcsoft.epp.common.service.FactorDeService;

@Service 
public class FactorDeServiceImpl extends BaseGenericServiceImpl<FactorDe> implements FactorDeService {

	@Autowired(required=true) @Qualifier("factorDeManagerImpl")
	private FactorDeManager factorDeManager;

	
	/**
	 * @Description: 创建指标
	 * @param factorDe
	 * @return FactorDe
	 * @throws Exception
	 * @Create Date 2010-9-7 下午03:59:21 By wanghz
	 */
	public FactorDe saveCreateFactorDe(FactorDe factorDe)throws EsException{
		factorDe.setCreateTime(new java.util.Date());
		factorDeManager.save(factorDe);
		return factorDe;
	}
	
	/**
	 * @Description: 更新指标
	 * @param factorDe
	 * @return FactorDe
	 * @throws Exception
	 * @Create Date 2010-9-7 下午03:59:21 By wanghz
	 */
	public FactorDe saveUpdateFactorDe(FactorDe factorDe)throws EsException{
		factorDeManager.save(factorDe);
		return factorDe;
	}
	
	/**
	 * @Description: 根据ID删除指标
	 * @return void
	 * @throws Exception
	 * @Create Date 2010-9-7 下午04:08:09 By wanghz
	 */
	public void removeFactorDe(String objId)throws EsException{
		factorDeManager.remove(objId, FactorDe.class);
	}
}
