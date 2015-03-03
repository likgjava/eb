package com.gpcsoft.goods.goodsprice.manager.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.goods.goods.domain.GoodsOptionalFitting;
import com.gpcsoft.goods.goodsprice.dao.GoodsOptFitPriceDao;
import com.gpcsoft.goods.goodsprice.domain.GoodsOptFitPrice;
import com.gpcsoft.goods.goodsprice.domain.GoodsPrice;
import com.gpcsoft.goods.goodsprice.manager.GoodsOptFitPriceManager;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;

@Repository
public class GoodsOptFitPriceManagerImpl extends BaseManagerImpl<GoodsOptFitPrice> implements GoodsOptFitPriceManager {

	@Autowired(required=true)  @Qualifier("goodsOptFitPriceDaoHibernate")
	private GoodsOptFitPriceDao goodsOptFitPriceDaoHibernate;

	/** 
	 * Description :  保存选配行情
	 * Create Date: 2010-4-15下午01:43:24 by yucy  Modified Date: 2010-4-15下午01:43:24 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void saveOptFitPrice(Map<String, Object> param) {
		String OptFitPriceJson = (String) param.get("OptFitPriceJson");
		
		Set<GoodsOptFitPrice> goodsOptFitPriceSet = new HashSet<GoodsOptFitPrice>();
		
		if(((GoodsPrice)param.get("goodsPrice")).getGoodsOptFitPriceSet().size()>0){
			goodsOptFitPriceSet = ((GoodsPrice)param.get("goodsPrice")).getGoodsOptFitPriceSet();
		}
		
		if(null!=OptFitPriceJson&&!"".equals(OptFitPriceJson)){
			for (String OptFitPriceString : OptFitPriceJson.split(",")) {
				GoodsOptFitPrice goodsOptFitPrice = new GoodsOptFitPrice();
				GoodsOptionalFitting goodsOptionalFitting = new GoodsOptionalFitting();
				goodsOptionalFitting.setObjId(OptFitPriceString.split(":")[0]);
				
				goodsOptFitPrice.setGoodsPrice((GoodsPrice)param.get("goodsPrice"));
				goodsOptFitPrice.setGoodsOptionalFitting(goodsOptionalFitting);
				
				//修改
				if(OptFitPriceString.split(":").length==3){
					for (GoodsOptFitPrice goodsOptFitPrice2 : goodsOptFitPriceSet) {
						if(OptFitPriceString.split(":")[2].equals(goodsOptFitPrice2.getObjId())){
							goodsOptFitPrice2.setRelativePrice(new BigDecimal(OptFitPriceString.split(":")[1]));
						}
					}
				}else{
					goodsOptFitPrice.setCreateTime(new Date());
					goodsOptFitPrice.setCreateUser(AuthenticationHelper.getCurrentUser(true));
					goodsOptFitPrice.setRelativePrice(new BigDecimal(OptFitPriceString.split(":")[1]));
					goodsOptFitPriceSet.add(goodsOptFitPrice);
				}
			}
		}
		goodsOptFitPriceDaoHibernate.save(goodsOptFitPriceSet);
	}

	/** 
	 * Description :  根据行情id取得选配行情
	 * Create Date: 2011-12-8上午12:22:50 by yucy  Modified Date: 2011-12-8上午12:22:50 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<GoodsOptFitPrice> getGoodsOptFitPriceListByGoodsPriceId (Map<String, Object> param) throws Exception {
		return goodsOptFitPriceDaoHibernate.getGoodsOptFitPriceListByGoodsPriceId(param);
	}
	
	
	
}
