package com.gpcsoft.epp.contract.manager.impl;

import java.util.Date;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.core.utils.DateUtil;
import com.gpcsoft.epp.common.domain.EsExceptionEnum;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.contract.dao.ContractDao;
import com.gpcsoft.epp.contract.domain.Contract;
import com.gpcsoft.epp.contract.manager.ContractManager;
import com.gpcsoft.srplatform.baseData.dao.SequenceNumberDao;

@Repository
public class ContractManagerImpl extends BaseManagerImpl<Contract> implements ContractManager {

	@Autowired(required=true)  @Qualifier("contractDaoHibernate")
	private ContractDao contractDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("sequenceNumberDaoHibernate")
	private SequenceNumberDao sequenceNumberdao;
	
	/**
	 * FuncName:generatorTaskPlanCodeByQO
	 * Description: 生成合同编号,Service层必须开启DB事物
	 * @param queryObject 为扩展参数，暂时不用
	 * @return String
	 * @author wanghz
	 * @Create Date 2010-7-15 上午09:59:44  
	 */
	public String generatorContractCodeByQO(QueryObject queryObject)throws EsException {
		String prefixCode = "";// 1.获取合同编号前缀编号
		try {
			prefixCode = messageSource.getMessage("contractPrefixCode");
		} catch (Exception e) {
			throw new EsException(messageSource.getMessage(EsExceptionEnum.GENERATOR_CODE_GET_PREFIX_CODE_ISNULL));
		}
		StringBuffer returnCode = new StringBuffer();// 2.按照规则生成合同编号
		returnCode.append(prefixCode);
		if(null != prefixCode && !"".equals(prefixCode)){
			returnCode.append("-");
		}
		returnCode.append(DateUtil.format(new Date(), "yyyyMMdd"));
		try {
			returnCode.append("-"+sequenceNumberdao.updateAndGetCurSn(returnCode.toString(), 3));
		} catch (Exception e) {
			throw new EsException(messageSource.getMessage(EsExceptionEnum.GENERATOR_CODE_IS_DB_READONLY));
		}
		return returnCode.toString();
	}
	
}
