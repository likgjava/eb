package com.gpcsoft.goods.goodsprice.manager.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.goods.goods.enumeration.GoodsEnum;
import com.gpcsoft.goods.goodsprice.dao.GoodsPriceDao;
import com.gpcsoft.goods.goodsprice.domain.GoodsPrice;
import com.gpcsoft.goods.goodsprice.manager.GoodsPriceManager;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;

@Repository
public class GoodsPriceManagerImpl extends BaseManagerImpl<GoodsPrice> implements GoodsPriceManager {

	@Autowired(required=true)  @Qualifier("goodsPriceDaoHibernate")
	private GoodsPriceDao goodsPriceDaoHibernate;

	public GoodsPrice savePrice(Map<String, Object> param) {
		
		GoodsPrice goodsPrice= (GoodsPrice)param.get("goodsPrice");
		if(null==goodsPrice.getObjId()){
			goodsPrice.setCreateTime(new Date());
			goodsPrice.setCreateUser(AuthenticationHelper.getCurrentUser(true));
		}else {
			goodsPrice.setUpdateTime(new Date());
			goodsPrice.setUpdateUser(AuthenticationHelper.getCurrentUser(true));
		}
		String opType = (String)param.get("opType");
		if("submit".equals(opType)){
			goodsPrice.setUseStatus(GoodsEnum.USE_TEMP);
			goodsPrice.setAuditStatus(GoodsEnum.AWAIT_EXAM);
		}else{
			goodsPrice.setUseStatus(GoodsEnum.USE_TEMP);
			goodsPrice.setAuditStatus(GoodsEnum.DRAFT_EXAM);
		}
		
		return goodsPriceDaoHibernate.save(goodsPrice);
	}
	
    public List<OrgInfo> getProvideSupplierByGoods(String goodsIds) throws Exception{
    return goodsPriceDaoHibernate.getProvideSupplierByGoods(goodsIds);
}
}
