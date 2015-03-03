package com.gpcsoft.epp.eval.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.epp.eval.dao.PackCongFactorResponseDao;
import com.gpcsoft.epp.eval.domain.PackCongFactorResponse;
import com.gpcsoft.epp.eval.manager.PackCongFactorResponseManager;

@Repository
public class PackCongFactorResponseManagerImpl extends BaseManagerImpl<PackCongFactorResponse> implements PackCongFactorResponseManager {

	@Autowired(required=true)  @Qualifier("packCongFactorResponseDaoHibernate")
	private PackCongFactorResponseDao packCongFactorResponseDaoHibernate;

	/** 
	 * Description :删除包件指标中间表信息
	 * Create Date: 2010-12-15下午07:08:05 by liuke  Modified Date: 2010-12-15下午07:08:05 by liuke
	 * @param projectId
	 * @return  void
	 * @Exception
	 */
	public void removeAllPackCongFactorResponseByProject(String projectId) {
		packCongFactorResponseDaoHibernate.removeAllPackCongFactorResponseByProject(projectId);
	}

}
