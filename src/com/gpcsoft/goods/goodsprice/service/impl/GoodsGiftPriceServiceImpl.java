package com.gpcsoft.goods.goodsprice.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.bizplatform.base.common.util.StringUtils;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.JsonUtils;
import com.gpcsoft.goods.goods.domain.GoodsGift;
import com.gpcsoft.goods.goods.manager.GoodsGiftManager;
import com.gpcsoft.goods.goodsprice.dao.GoodsGiftPriceDao;
import com.gpcsoft.goods.goodsprice.dao.GoodsPriceSupplierDao;
import com.gpcsoft.goods.goodsprice.domain.GoodsGiftPrice;
import com.gpcsoft.goods.goodsprice.domain.GoodsPriceSupplier;
import com.gpcsoft.goods.goodsprice.service.GoodsGiftPriceService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;

@Service 
public class GoodsGiftPriceServiceImpl extends BaseGenericServiceImpl<GoodsGiftPrice> implements GoodsGiftPriceService {

//	@Autowired(required=true) @Qualifier("goodsGiftPriceManagerImpl")
//	private GoodsGiftPriceManager goodsGiftPriceManager;
	
	@Autowired(required=true)  @Qualifier("goodsGiftPriceDaoHibernate")
	private GoodsGiftPriceDao goodsGiftPriceDaoHibernate;
	
	@Autowired(required=true) @Qualifier("goodsPriceSupplierDaoHibernate")
	private GoodsPriceSupplierDao goodsPriceSupplierDao;
	
	@Autowired(required=true) @Qualifier("goodsGiftManagerImpl")
	private GoodsGiftManager goodsGiftManager;

	/** 
	 * Description :  获取礼包价格列表
	 * Create Date: 2011-1-10下午12:43:37 by likg  Modified Date: 2011-1-10下午12:43:37 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<GoodsGiftPrice> getGoodsGiftPriceList(Map<String, Object> param) throws Exception {
		return goodsGiftPriceDaoHibernate.getGoodsGiftPriceList(param);
	}

	/** 
	 * Description :  保存和修改礼包价格信息
	 * Create Date: 2011-1-10下午03:17:25 by likg  Modified Date: 2011-1-10下午03:17:25 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void saveGoodsGiftPrice(Map<String, Object> paramMap) throws Exception {
		JSONArray taskArray = JSONArray.fromObject(paramMap.get("goodsGiftPrice").toString());
		List<GoodsGiftPrice> goodsGiftPriceList = new ArrayList<GoodsGiftPrice>();
		GoodsGiftPrice goodsGiftPrice = new GoodsGiftPrice();
		for(Object obj : taskArray) {
			goodsGiftPrice = (GoodsGiftPrice)JsonUtils.json2Bean(((JSONObject)obj).toString(), GoodsGiftPrice.class);
			
			//旧的礼包价格信息
			if(StringUtils.hasLength(goodsGiftPrice.getObjId())){
				GoodsGiftPrice oldGoodsGiftPrice = goodsGiftPriceDaoHibernate.get(goodsGiftPrice.getObjId());
				oldGoodsGiftPrice.setGiftPrice(goodsGiftPrice.getGiftPrice());
				oldGoodsGiftPrice.setUpdateUser(AuthenticationHelper.getCurrentUser(true));
				oldGoodsGiftPrice.setUpdateTime(new Date());
			}else{//新建礼包信息
				//获得行情供应商
				Map<String, Object> param = new HashMap<String, Object>();
				GoodsGift goodsGift = goodsGiftManager.get(goodsGiftPrice.getGoodsGift().getObjId());
				param.put("goodsId", goodsGift.getGoods().getObjId());
				GoodsPriceSupplier goodsPriceSupplier = goodsPriceSupplierDao.getgoodsPriceSupplierBySupplier(param);
				goodsGiftPrice.setGoodsPriceSupplier(goodsPriceSupplier);
				
				goodsGiftPrice.setCreateUser(AuthenticationHelper.getCurrentUser(true));
				goodsGiftPrice.setCreateTime(new Date());
				goodsGiftPriceList.add(goodsGiftPrice);
			}
		}
		
		goodsGiftPriceDaoHibernate.save(goodsGiftPriceList);
	}
}
