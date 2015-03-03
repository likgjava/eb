package com.gpcsoft.agreement.management.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.agreement.management.domain.AgreementGoods;
import com.gpcsoft.agreement.management.manager.AgreementGoodsManager;
import com.gpcsoft.agreement.management.service.AgreementGoodsService;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.goods.goods.domain.Goods;

@Service 
public class AgreementGoodsServiceImpl extends BaseGenericServiceImpl<AgreementGoods> implements AgreementGoodsService {

	
	
	@Autowired(required=true) @Qualifier("agreementGoodsManagerImpl")
	private AgreementGoodsManager agreementGoodsManager;

	/* (non-Javadoc)
	 * @see com.gpcsoft.eps.agreement.management.service.AgreementGoodsService#getAuthorizedGoods(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	public List getAuthorizedGoods(Map<String, Object> params) {
		return agreementGoodsManager.getAuthorizedGoods(params);
	}

	/* (non-Javadoc)
	 * @see com.gpcsoft.eps.agreement.management.service.AgreementGoodsService#authorizGoods(java.util.Map)
	 */
	public Integer authorizGoods(Map<String, Object> params) {
		return agreementGoodsManager.authorizGoods(params);
	}

	/* (non-Javadoc)
	 * @see com.gpcsoft.eps.agreement.management.service.AgreementGoodsService#removeAuthorizedGoods(java.util.Map)
	 */
	public Integer removeAuthorizedGoods(Map<String, Object> params) {
		return agreementGoodsManager.removeAuthorizedGoods(params);
	}

	/* (non-Javadoc)
	 * @see com.gpcsoft.eps.agreement.management.service.AgreementGoodsService#removeGoods(java.util.Map)
	 */
	public Integer removeGoods(Map<String, Object> params) {
		return agreementGoodsManager.removeGoods(params);
	}

	@SuppressWarnings("unchecked")
	public List getBrandSelectParam(Map<String, Object> params) {
		return agreementGoodsManager.getBrandSelectParam(params);
	}
	
	/** 
	 * Description :   获取行情商品展示的列表数据
	 * Create Date: 2009-4-13上午11:04:42 by yucy  Modified Date: 2009-4-13上午11:04:42 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Page<Goods> getPriceGoodsListByAgree(Page<Goods> page,Map<String, Object> param) throws Exception{
		return agreementGoodsManager.getPriceGoodsListByAgree(page,param);
	}
	
	/** 
	 * Description :  获取行情商品协议数据(折扣率，单价，协议价等等)
	 * Create Date: 2011-12-6下午02:45:02 by yucy  Modified Date: 2011-12-6下午02:45:02 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public AgreementGoods getAgreementGoodsByParam(Map<String, Object> param) throws Exception{
		return agreementGoodsManager.getAgreementGoodsByParam(param);
	}

	/** 
	 * Description :  获取行情商品列表)
	 * Create Date: 2011-12-6下午02:45:02 by yucy  Modified Date: 2011-12-6下午02:45:02 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<AgreementGoods> getAgreementGoodsListByParam( Map<String, Object> param) throws Exception {
		return agreementGoodsManager.getAgreementGoodsListByParam(param);
	}
}
