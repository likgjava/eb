package com.gpcsoft.goods.goodsprice.manager.impl;

import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.goods.goods.domain.Goods;
import com.gpcsoft.goods.goodsprice.dao.GoodsPriceSupplierDao;
import com.gpcsoft.goods.goodsprice.manager.GoodsPriceSupplierManager;
import com.gpcsoft.goods.goodsprice.domain.GoodsPriceSupplier;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;

@Repository
public class GoodsPriceSupplierManagerImpl extends BaseManagerImpl<GoodsPriceSupplier> implements GoodsPriceSupplierManager {

	@Autowired(required=true)  @Qualifier("goodsPriceSupplierDaoHibernate")
	private GoodsPriceSupplierDao goodsPriceSupplierDaoHibernate;

	/** 
	 * Description : 保存供应商行情 
	 * Create Date: 2010-4-15下午02:18:09 by yucy  Modified Date: 2010-4-15下午02:18:09 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public GoodsPriceSupplier saveSupplierPrice(Map<String, Object> param) throws Exception {
		String goodsId = (String)param.get("goodsId");
		Goods goods = new Goods();
		goods.setObjId(goodsId);
		
		GoodsPriceSupplier goodsPriceSupplier = new GoodsPriceSupplier();
		goodsPriceSupplier.setGoods(goods);
		goodsPriceSupplier.setSupplier((OrgInfo)AuthenticationHelper.getCurrentUser(true).getOrgInfo());
		goodsPriceSupplier.setCreateTime(new Date());
		goodsPriceSupplier.setCreateUser(AuthenticationHelper.getCurrentUser(true));
		goodsPriceSupplier.setIsProtocol(true);
		goodsPriceSupplier.setIsShow(true);
		
		return goodsPriceSupplierDaoHibernate.save(goodsPriceSupplier);
	}
}
