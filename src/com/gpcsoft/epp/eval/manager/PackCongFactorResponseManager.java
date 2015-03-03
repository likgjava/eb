package com.gpcsoft.epp.eval.manager;

import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.epp.eval.domain.PackCongFactorResponse;

public interface PackCongFactorResponseManager extends BaseManager<PackCongFactorResponse> {

	/** 
	 * Description :删除包件指标中间表信息
	 * Create Date: 2010-12-15下午07:08:05 by liuke  Modified Date: 2010-12-15下午07:08:05 by liuke
	 * @param projectId
	 * @return  void
	 * @Exception
	 */
	public void removeAllPackCongFactorResponseByProject(String projectId);
}
