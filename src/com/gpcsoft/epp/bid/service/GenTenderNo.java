package com.gpcsoft.epp.bid.service;

import com.gpcsoft.epp.common.exception.EsException;

public interface GenTenderNo {
	/**
	 * @param prjCode	招标采购项目编号
	 * @param packCode  包组编号
	 * @return				返回匿名投标编号
	 * @throws Exception
	 */
	public String getTenderNo(String prjCode, String packCode) throws EsException;

}
