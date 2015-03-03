package com.gpcsoft.agreement.management.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.agreement.management.domain.AgreementGoodsclass;
import com.gpcsoft.agreement.management.manager.AgreementGoodsclassManager;
import com.gpcsoft.agreement.management.service.AgreementGoodsclassService;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;

@Service 
public class AgreementGoodsclassServiceImpl extends BaseGenericServiceImpl<AgreementGoodsclass> implements AgreementGoodsclassService {

	@Autowired(required=true) @Qualifier("agreementGoodsclassManagerImpl")
	private AgreementGoodsclassManager agreementGoodsclassManager;

	
	/* (non-Javadoc)
	 * @see com.gpcsoft.eps.agreement.management.service.AgreementGoodsclassService#delAgreementGoodsclassAndGoods(java.lang.String)
	 */
	public int removeAgreementGoodsclassAndGoods(Map<String, Object> params) {
		return agreementGoodsclassManager.removeAgreementGoodsclassAndGoods(params);
	}


	/* (non-Javadoc)
	 * @see com.gpcsoft.eps.agreement.management.service.AgreementGoodsclassService#getAuthorizedClass(java.util.Map)
	 */
	public Object getAuthorizedClass(Map<String, Object> params) {
		return agreementGoodsclassManager.getAuthorizedClass(params);
	}


	/* (non-Javadoc)
	 * @see com.gpcsoft.eps.agreement.management.service.AgreementGoodsclassService#getGoodsClassWithTips(java.lang.String)
	 */
	public List<AgreementGoodsclass> getGoodsClassWithTips(String agreementId) {
		return agreementGoodsclassManager.getGoodsClassWithTips(agreementId);
	}


	/* (non-Javadoc)
	 * @see com.gpcsoft.eps.agreement.management.service.AgreementGoodsclassService#DelGoodsByClassReAddGoods(com.gpcsoft.eps.agreement.management.domain.AgreementGoodsclass)
	 */
	public Integer removeGoodsByClassReAddGoods(AgreementGoodsclass objectService) {
		return agreementGoodsclassManager.removeGoodsByClassReAddGoods(objectService);
	}


	/* (non-Javadoc)
	 * @see com.gpcsoft.eps.agreement.management.service.AgreementGoodsclassService#updateClassAndGoodsDiscountRatio(com.gpcsoft.eps.agreement.management.domain.AgreementGoodsclass)
	 */
	public void updateClassAndGoodsDiscountRatio(AgreementGoodsclass objectService) {
		agreementGoodsclassManager.updateClassAndGoodsDiscountRatio(objectService);
	}


	/* (non-Javadoc)
	 * @see com.gpcsoft.eps.agreement.management.service.AgreementGoodsclassService#authorizGoodsClass(java.util.Map)
	 */
	public Integer authorizGoodsClass(Map<String, Object> params) {
		return agreementGoodsclassManager.authorizGoodsClass(params);
	}


	/* (non-Javadoc)
	 * @see com.gpcsoft.eps.agreement.management.service.AgreementGoodsclassService#delAuthorizedClass(java.util.Map)
	 */
	public Integer removeAuthorizedClass(Map<String, Object> params) {
		return agreementGoodsclassManager.removeAuthorizedClass(params);
	}


	/* (non-Javadoc)
	 * @see com.gpcsoft.eps.agreement.management.service.AgreementGoodsclassService#saveOrUpDateRow(java.util.Map)
	 */
	public void saveOrUpDateRow(Map<String, Object> params) {
		agreementGoodsclassManager.saveOrUpDateRow(params);
	}

}
