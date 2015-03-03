package com.gpcsoft.epp.contract.manager;

import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.contract.domain.Contract;

public interface ContractManager extends BaseManager<Contract> {
	
	/**
	 * FuncName:generatorTaskPlanCodeByQO
	 * Description: 生成合同编号,Service层必须开启DB事物
	 * @param queryObject 为扩展参数，暂时不用
	 * @return String
	 * @author shenjz
	 * @Create Date 2011-3-22 上午09:59:44  
	 */
	public String generatorContractCodeByQO(QueryObject queryObject)throws EsException;
}
