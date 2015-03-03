package com.gpcsoft.agreement.management.manager.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.agreement.management.dao.AgreementGoodsDao;
import com.gpcsoft.agreement.management.domain.AgreementGoods;
import com.gpcsoft.agreement.management.domain.AgreementSecond;
import com.gpcsoft.agreement.management.manager.AgreementGoodsManager;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.goods.goods.domain.Goods;

@Repository
public class AgreementGoodsManagerImpl extends BaseManagerImpl<AgreementGoods> implements AgreementGoodsManager {

	@Autowired(required=true)  @Qualifier("agreementGoodsDaoHibernate")
	private AgreementGoodsDao agreementGoodsDaoHibernate;

	@SuppressWarnings("unchecked")
	public List getAuthorizedGoods(Map<String, Object> params) {
		return agreementGoodsDaoHibernate.getAuthorizedGoods(params);
	}

	public Integer authorizGoods(Map<String, Object> params) {
		
		String[] goodsIds = ((String)params.get("goodsIds")).split(",");
		
		String agreeSecondId = (String)params.get("agreeSecondId");
		
		for (String goodsId : goodsIds) {
			AgreementGoods agreementGoodsFirst = agreementGoodsDaoHibernate.get(goodsId);
			
			AgreementGoods agreementGoodsSecond = new AgreementGoods();
			
			AgreementSecond agreementSecond = new AgreementSecond();
			agreementSecond.setObjId(agreeSecondId);
			agreementGoodsSecond.setAgreementSecond(agreementSecond);//设置second
			
			agreementGoodsSecond.setAgreementType("01");//设置type
			
			agreementGoodsSecond.setBrand(agreementGoodsFirst.getBrand());//设置brand
			
			agreementGoodsSecond.setGoodsClass(agreementGoodsFirst.getGoodsClass());//设置class
			
			agreementGoodsSecond.setGoods(agreementGoodsFirst.getGoods());//设置goods
			
			agreementGoodsDaoHibernate.save(agreementGoodsSecond);//保存
		}
		
		return 1;
	}

	public Integer removeAuthorizedGoods(Map<String, Object> params) {
		return agreementGoodsDaoHibernate.removeAuthorizedGoods(params);
	}

	/* (non-Javadoc)
	 * @see com.gpcsoft.eps.agreement.management.manager.AgreementGoodsManager#removeGoods(java.util.Map)
	 */
	public Integer removeGoods(Map<String, Object> params) {
		return agreementGoodsDaoHibernate.removeGoods(params);
	}

	@SuppressWarnings("unchecked")
	public List getBrandSelectParam(Map<String, Object> params) {
		return agreementGoodsDaoHibernate.getBrandSelectParam(params);
	}

	/** 
	 * Description :   获取行情商品展示的列表数据
	 * Create Date: 2009-4-13上午11:04:42 by yucy  Modified Date: 2009-4-13上午11:04:42 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Page<Goods> getPriceGoodsListByAgree(Page<Goods> page,Map<String, Object> param) throws Exception{
		return agreementGoodsDaoHibernate.getPriceGoodsListByAgree(page,param);
	}
	
	/** 
	 * Description :  获取行情商品协议数据(折扣率，单价，协议价等等)
	 * Create Date: 2011-12-6下午02:45:02 by yucy  Modified Date: 2011-12-6下午02:45:02 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public AgreementGoods getAgreementGoodsByParam(Map<String, Object> param) throws Exception{
		return agreementGoodsDaoHibernate.getAgreementGoodsByParam(param);
	}

	/** 
	 * Description :  获取行情商品列表)
	 * Create Date: 2011-12-6下午02:45:02 by yucy  Modified Date: 2011-12-6下午02:45:02 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<AgreementGoods> getAgreementGoodsListByParam( Map<String, Object> param) throws Exception {
		return agreementGoodsDaoHibernate.getAgreementGoodsListByParam(param);
	}
	
	
	
}
