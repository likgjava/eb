package com.gpcsoft.epp.eval.manager.impl;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.epp.eval.dao.CongFactorResponseDao;
import com.gpcsoft.epp.eval.domain.CongFactorResponse;
import com.gpcsoft.epp.eval.manager.CongFactorResponseManager;

@Repository
public class CongFactorResponseManagerImpl extends BaseManagerImpl<CongFactorResponse> implements CongFactorResponseManager {

	@Autowired(required=true)  @Qualifier("congFactorResponseDaoHibernate")
	private CongFactorResponseDao congFactorResponseDaoHibernate;

	/**
	 * Description :删除投标响应对象
	 * Create Date: 2010-12-15下午06:45:09 by liuke  Modified Date: 2010-12-15下午06:45:09 by liuke
	 * @param projectId
	 * @return  void
	 * @Exception
	 */
	public void removeAllCongFactorResponseByProject(String projectId) {
		congFactorResponseDaoHibernate.removeAllCongFactorResponseByProject(projectId);
	}

}
