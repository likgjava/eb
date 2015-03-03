package com.gpcsoft.smallscale.expert.dao;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.smallscale.expert.domain.Certificate;

public interface CertificateDao extends BaseGenericDao<Certificate> {

	/** 
	 * Description : 审核职称信息
	 * Create Date: 2010-11-29上午10:09:26 by guoyr  Modified Date: 2010-11-29上午10:09:26 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Integer updateCertificateAuditStatus(String objIds, String auditStatus);
}
