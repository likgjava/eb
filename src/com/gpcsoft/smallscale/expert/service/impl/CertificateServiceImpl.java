package com.gpcsoft.smallscale.expert.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.smallscale.expert.dao.CertificateDao;
import com.gpcsoft.smallscale.expert.domain.Certificate;
import com.gpcsoft.smallscale.expert.manager.CertificateManager;
import com.gpcsoft.smallscale.expert.service.CertificateService;

@Service 
public class CertificateServiceImpl extends BaseGenericServiceImpl<Certificate> implements CertificateService {

	@Autowired(required=true) @Qualifier("certificateManagerImpl")
	private CertificateManager certificateManager;
	
	@Autowired(required=true)  @Qualifier("certificateDaoHibernate")
	private CertificateDao certificateDaoHibernate;

	/** 
	 * Description : 保存职称信息 
	 * Create Date: 2010-11-26下午03:29:48 by guoyr  Modified Date: 2010-11-26下午03:29:48 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Certificate saveCertificate(Certificate certificate){
		return certificateManager.save(certificate);
	}
	
	/** 
	 * Description : 审核职称信息
	 * Create Date: 2010-11-29上午10:09:26 by guoyr  Modified Date: 2010-11-29上午10:09:26 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Integer updateCertificateAuditStatus(String objIds, String auditStatus){
		return certificateDaoHibernate.updateCertificateAuditStatus(objIds, auditStatus);
	}
}
