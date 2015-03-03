package com.gpcsoft.epp.eval.manager;

import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.epp.eval.domain.CongFactorResponse;

public interface CongFactorResponseManager extends BaseManager<CongFactorResponse> {

	/**
	 * Description :删除投标响应对象
	 * Create Date: 2010-12-15下午06:45:09 by liuke  Modified Date: 2010-12-15下午06:45:09 by liuke
	 * @param projectId
	 * @return  void
	 * @Exception
	 */
	public void removeAllCongFactorResponseByProject(String projectId); 
	
}
