package com.gpcsoft.epp.project.manager.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.core.utils.DateUtil;
import com.gpcsoft.epp.common.domain.EbuyMethodEnum;
import com.gpcsoft.epp.common.domain.EsExceptionEnum;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.project.manager.GenTenderCode;
import com.gpcsoft.srplatform.baseData.dao.SequenceNumberDao;

public class GenTenderCodeImpl extends BaseManagerImpl implements GenTenderCode {
	
	@Autowired(required=true)  @Qualifier("sequenceNumberDaoHibernate")
	private SequenceNumberDao sequenceNumberdao;

	public String getTenderCode(String projBuyMethod) throws EsException {
		String prefixCode = "";// 1.根据项目采购方式获取前缀编号
		try {
			if (projBuyMethod.equals(EbuyMethodEnum.OPEN_BIDDING)) {				// 公开招标
				prefixCode = messageSource.getMessage("openProjectPrefixCode");
			} else if (projBuyMethod.equals(EbuyMethodEnum.INVITE_BIDDING)) {		// 邀请招标
				prefixCode = messageSource.getMessage("inviteProjectPrefixCode");
			} else if (projBuyMethod.equals(EbuyMethodEnum.NEGOTIATE)) {			// 竞争性谈判
				prefixCode = messageSource.getMessage("negotiateProjectPrefixCode");
			} else if (projBuyMethod.equals(EbuyMethodEnum.INQUIRY)) {				// 询价
				prefixCode = messageSource.getMessage("inquiryProjectPrefixCode");
			} else if (projBuyMethod.equals(EbuyMethodEnum.SINGLE_SOURCE)) {		// 单一来源
				prefixCode = messageSource.getMessage("singleSourceProjectPrefixCode");
			} else if (projBuyMethod.equals(EbuyMethodEnum.RANDOM)) {		// 随机抽取
				prefixCode = messageSource.getMessage("inviteProjectPrefixCode");
			} else if (projBuyMethod.equals(EbuyMethodEnum.BIDCOM)) {		// 综合评估
				prefixCode = messageSource.getMessage("negotiateProjectPrefixCode");
			}{
				prefixCode = "";
			}
		} catch (Exception e) {
			throw new EsException(messageSource.getMessage(EsExceptionEnum.GENERATOR_CODE_GET_PREFIX_CODE_ISNULL));
		}
		// 2.按照规则生成项目编号
		StringBuffer returnCode = new StringBuffer();
		returnCode.append(prefixCode);
		if(null != prefixCode && !"".equals(prefixCode)){
			returnCode.append("");
		}
		returnCode.append(DateUtil.format(new Date(), "yyyyMMdd"));
		try {
			returnCode.append(""+sequenceNumberdao.updateAndGetCurSn(returnCode.toString(), 3));
		} catch (Exception e) {
			throw new EsException(messageSource.getMessage(EsExceptionEnum.GENERATOR_CODE_IS_DB_READONLY));
		}
		return returnCode.toString();
	}

}
