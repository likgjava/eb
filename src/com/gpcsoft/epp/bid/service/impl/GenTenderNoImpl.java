package com.gpcsoft.epp.bid.service.impl;

import java.util.Date;

import com.gpcsoft.epp.bid.service.GenTenderNo;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.common.utils.DateUtil;
import com.gpcsoft.epp.common.utils.GeneratorSequence;
import com.gpcsoft.epp.common.utils.StringUtil;

public class GenTenderNoImpl implements GenTenderNo {

	public String getTenderNo(String prjCode, String packCode)
			throws EsException {
		String tenderNo = GeneratorSequence.generatorSeqPreFix(prjCode+"-"+DateUtil.format(new Date(), "yyyyMMdd"), 5);
		tenderNo = StringUtil.upperStrintAt(tenderNo);//全转为大写
		return tenderNo;
	}

}
