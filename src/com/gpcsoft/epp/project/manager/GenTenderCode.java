package com.gpcsoft.epp.project.manager;

import com.gpcsoft.epp.common.exception.EsException;

public interface GenTenderCode {
	/**
	 * @param projBuyMethod	招标采购方式
	 * @return				返回项目编号
	 * @throws Exception
	 */
	public String getTenderCode(String projBuyMethod) throws EsException;
}
