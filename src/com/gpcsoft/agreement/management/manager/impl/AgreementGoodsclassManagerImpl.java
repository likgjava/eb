package com.gpcsoft.agreement.management.manager.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.agreement.management.dao.AgreementGoodsDao;
import com.gpcsoft.agreement.management.dao.AgreementGoodsclassDao;
import com.gpcsoft.agreement.management.domain.AgreementGoods;
import com.gpcsoft.agreement.management.domain.AgreementGoodsclass;
import com.gpcsoft.agreement.management.domain.AgreementSecond;
import com.gpcsoft.agreement.management.manager.AgreementGoodsclassManager;
import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;

@Repository
public class AgreementGoodsclassManagerImpl extends BaseManagerImpl<AgreementGoodsclass> implements AgreementGoodsclassManager {

	@Autowired(required=true)  @Qualifier("agreementGoodsclassDaoHibernate")
	private AgreementGoodsclassDao agreementGoodsclassDaoHibernate;

	public int removeAgreementGoodsclassAndGoods(Map<String, Object> params) {
		return agreementGoodsclassDaoHibernate.removeAgreementGoodsclassAndGoods(params);
	}


	public Object getAuthorizedClass(Map<String, Object> params) {
		return agreementGoodsclassDaoHibernate.getAuthorizedClass(params);
	}
	public List<AgreementGoodsclass> getGoodsClassWithTips(String agreementId) {
		return agreementGoodsclassDaoHibernate.agreementGoodsclassDaoHibernate(agreementId);
	}

	public Integer removeGoodsByClassReAddGoods(AgreementGoodsclass objectService) {
		return agreementGoodsclassDaoHibernate.removeGoodsByClassReAddGoods(objectService);
	}

	public void updateClassAndGoodsDiscountRatio(AgreementGoodsclass objectService) {
		agreementGoodsclassDaoHibernate.updateClassAndGoodsDiscountRatio(objectService);
	}


	public Integer authorizGoodsClass(Map<String, Object> params) {
		
		//获得dao
		AgreementGoodsDao  agreementGoodsDao =  (AgreementGoodsDao) FrameBeanFactory.getBean("agreementGoodsDaoHibernate");
		
		String[] goodsClassIds = ((String) params.get("goodsClassIds")).split(",");
		
		String agreeSecondId = (String)params.get("agreeSecondId");
		
		for (String goodsClassId : goodsClassIds) {
		
			AgreementGoodsclass agreementGoodsclassFirst = agreementGoodsclassDaoHibernate.get(goodsClassId);
			
			AgreementGoodsclass agreementGoodsclassSecond  = new AgreementGoodsclass();

			AgreementSecond agreementSecond = new AgreementSecond();
			agreementSecond.setObjId(agreeSecondId);
			agreementGoodsclassSecond.setAgreementSecond(agreementSecond);//设置second
			
			agreementGoodsclassSecond.setBrand(agreementGoodsclassFirst.getBrand());//设置brand
			
			agreementGoodsclassSecond.setGoodsClass(agreementGoodsclassFirst.getGoodsClass());//设置class
			
			agreementGoodsclassSecond.setAgreementType("01");//设置type
			
			agreementGoodsclassSecond.setDiscountRatio(agreementGoodsclassFirst.getDiscountRatio());//设置DiscountRatio
			
			agreementGoodsclassDaoHibernate.save(agreementGoodsclassSecond);//保存
			
			Set<AgreementGoods> AgreementGoodsSet=  agreementGoodsclassFirst.getAgreementGoods();
			
			for (AgreementGoods agreementGoodsFirst : AgreementGoodsSet) {
				
				AgreementGoods agreementGoodsSecond = new AgreementGoods();
				
				agreementGoodsSecond.setAgreementGoodsclass(agreementGoodsclassSecond);//设置class
				
				agreementGoodsSecond.setAgreementType("01");//设置type
				
				agreementGoodsSecond.setBrand(agreementGoodsFirst.getBrand());//设置brand
				
				agreementGoodsSecond.setGoodsClass(agreementGoodsFirst.getGoodsClass());//设置class
				
				agreementGoodsSecond.setGoods(agreementGoodsFirst.getGoods());//设置goods
				
				agreementGoodsDao.save(agreementGoodsSecond);//保存
				
			}
		}
		return 1;
	}

	/* (non-Javadoc)
	 * @see com.gpcsoft.eps.agreement.management.manager.AgreementGoodsclassManager#removeAuthorizedClass(java.util.Map)
	 */
	public Integer removeAuthorizedClass(Map<String, Object> params) {
		return agreementGoodsclassDaoHibernate.removeAuthorizedClass(params);
	}

	/* (non-Javadoc)
	 * @see com.gpcsoft.eps.agreement.management.manager.AgreementGoodsclassManager#saveOrUpDateRow(java.util.Map)
	 */
	public void saveOrUpDateRow(Map<String, Object> params) {
		agreementGoodsclassDaoHibernate.saveOrUpDateRow(params);
	}
	
}
