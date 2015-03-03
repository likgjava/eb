package com.gpcsoft.goods.goods.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.goods.goods.dao.GoodsAccessoriesPriceDao;
import com.gpcsoft.goods.goods.domain.GoodsAccessoriesPrice;
import com.gpcsoft.goods.goods.service.GoodsAccessoriesPriceService;

@Service 
public class GoodsAccessoriesPriceServiceImpl extends BaseGenericServiceImpl<GoodsAccessoriesPrice> implements GoodsAccessoriesPriceService {

	@Autowired(required=true) @Qualifier("goodsAccessoriesPriceDaoHibernate")
	private GoodsAccessoriesPriceDao goodsAccessoriesPriceDao;

	/** 
	 * Description :  零配件价格列表
	 * Create Date: 2010-7-28上午09:39:54 by sunl  Modified Date: 2010-7-28上午09:39:54 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Page<GoodsAccessoriesPrice> listAccPrice(Page<GoodsAccessoriesPrice> page,Map<String,Object> param) throws Exception {
		return goodsAccessoriesPriceDao.listAccPrice(page, param);
	}
	
	/** 
	 * Description :  获取零配件价格列表
	 * Create Date: 2011-1-10下午12:43:37 by likg  Modified Date: 2011-1-10下午12:43:37 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<GoodsAccessoriesPrice> getGoodsAccessPriceList(Map<String, Object> param) throws Exception {
		return goodsAccessoriesPriceDao.getGoodsAccessPriceList(param);
	}
}
