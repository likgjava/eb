package com.gpcsoft.agreement.management.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.agreement.management.dao.AgreementDao;
import com.gpcsoft.agreement.management.domain.Agreement;
import com.gpcsoft.agreement.management.manager.AgreementManager;
import com.gpcsoft.agreement.management.service.AgreementService;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.pubservice.utils.SequenceNumberUtil;

@Service 
public class AgreementServiceImpl extends BaseGenericServiceImpl<Agreement> implements AgreementService {

	
	@Autowired(required=true) @Qualifier("agreementManagerImpl")
	private AgreementManager agreementManager;
	
	@Autowired(required=true) @Qualifier("agreementDaoHibernate")
	private AgreementDao agreementDaoHibernate;

	/** 
	 * Description :  生成协议编号
	 * Create Date: 2010-5-14下午04:30:55 by yucy  Modified Date: 2010-5-14下午04:30:55 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public String createAgreementNo() {
		return agreementManager.createAgreementNo();
	}

	/** 
	 * Description :  取得供应商可以录入行情的区间  
	 * Create Date: 2010-9-26下午05:20:12 by yucy  Modified Date: 2010-9-26下午05:20:12 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<String[]> getDistrictByOrg(Map<String, Object> param) throws Exception{
		return agreementDaoHibernate.getDistrictByOrg(param);
	}

	/** 
	 * Description :  保存协议
	 * Create Date: 2011-12-5下午05:10:46 by yucy  Modified Date: 2011-12-5下午05:10:46 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean saveAgreement(Agreement agreement) throws Exception {
		if(null==agreement.getObjId()||"".equals(agreement.getObjId())){
			agreement.setAgreementNo(SequenceNumberUtil.getAgreementSN());
			agreement.setUseStatus("00");
		}
		agreementDaoHibernate.save(agreement);
		return true;
	}
}
