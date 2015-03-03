package com.gpcsoft.goods.goodsprice.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.goods.goodsprice.dao.AttentionPriceDao;
import com.gpcsoft.goods.goodsprice.domain.AttentionPrice;
import com.gpcsoft.goods.goodsprice.service.AttentionPriceService;

@Service 
public class AttentionPriceServiceImpl extends BaseGenericServiceImpl<AttentionPrice> implements AttentionPriceService {

	@Autowired(required=true) @Qualifier("attentionPriceDaoHibernate")
	private AttentionPriceDao attentionPriceDao;

	/** 
	 * Description :  取得当前关注的城市
	 * Create Date: 2010-11-4上午11:35:05 by yucy  Modified Date: 2010-11-4上午11:35:05 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Object[] getCurrentAttentionCity(Map<String, Object> param) throws Exception {
		return attentionPriceDao.getCurrentAttentionCity(param);
	}

	/** 
	 * Description : 改变关注区域
	 * Create Date: 2010-11-4下午01:49:20 by yucy  Modified Date: 2010-11-4下午01:49:20 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean changerAttentionCity(Map<String, Object> param) throws Exception {
		return attentionPriceDao.changerAttentionCity(param);
	}

	/** 
	 * Description :  是否关注过
	 * Create Date: 2010-11-17下午05:57:37 by yucy  Modified Date: 2010-11-17下午05:57:37 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean hasAttention(Map<String, Object> param) throws Exception {
		return attentionPriceDao.hasAttention(param);
	}

	/** 
	 * Description :  取得商品被关注的信息
	 * Create Date: 2011-2-28下午02:01:58 by yucy  Modified Date: 2011-2-28下午02:01:58 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Integer getAttentionNumberByGoodsId(String goodsId) throws Exception {
		return attentionPriceDao.getAttentionNumberByGoodsId(goodsId);
	}
}
