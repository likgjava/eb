package com.gpcsoft.smallscale.expert.service;
import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.smallscale.expert.domain.Certificate;

public interface CertificateService extends BaseGenericService<Certificate> {

	/** 
	 * Description : 保存职称信息 
	 * Create Date: 2010-11-26下午03:29:48 by guoyr  Modified Date: 2010-11-26下午03:29:48 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Certificate saveCertificate(Certificate certificate);
	
	/** 
	 * Description : 审核职称信息
	 * Create Date: 2010-11-29上午10:09:26 by guoyr  Modified Date: 2010-11-29上午10:09:26 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Integer updateCertificateAuditStatus(String objIds, String auditStatus);
}
