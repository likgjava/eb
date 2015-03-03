package com.gpcsoft.goods.goods.manager.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.core.utils.BeanUtils;
import com.gpcsoft.goods.goods.dao.GoodsDao;
import com.gpcsoft.goods.goods.dao.GoodsOptionalFittingDao;
import com.gpcsoft.goods.goods.domain.Goods;
import com.gpcsoft.goods.goods.domain.GoodsOptionalFitting;
import com.gpcsoft.goods.goods.domain.GoodsParam;
import com.gpcsoft.goods.goods.enumeration.GoodsEnum;
import com.gpcsoft.goods.goods.manager.GoodsManager;

@Repository
public class GoodsManagerImpl extends BaseManagerImpl<Goods> implements GoodsManager {

	@Autowired(required=true)  @Qualifier("goodsDaoHibernate")
	private GoodsDao goodsDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("goodsOptionalFittingDaoHibernate")
	private GoodsOptionalFittingDao goodsOptionalFittingDaoHibernate;
	
	/**
	 * Description :  拷贝商品参数的选配信息到新商品中
	 * Create Date: 2010-8-11下午02:41:57 by sunl  Modified Date: 2010-8-11下午02:41:57 by sunl
	 * @param   
	 * @return  
	 * @Exception
	 */
	public void saveOptionFittingOfModify(Goods newGoods) throws Exception {
		String gid = newGoods.getCurrentId();
		
		//如果是manager直接变更商品为正式
		if(newGoods.getUseStatus().equals(GoodsEnum.USE_VALID)) {
			Goods goods = goodsDaoHibernate.get(newGoods.getObjId());
			goods.setCurrentId(null);
		}
		
		//获取原商品的选配信息
		List<GoodsOptionalFitting> goodsOptionalFittingList = goodsDaoHibernate.getOptionFittingByGoods(gid);
		for (GoodsOptionalFitting goodsOptionalFitting : goodsOptionalFittingList) {
			GoodsOptionalFitting newFitting = new GoodsOptionalFitting();
			Set<GoodsParam> goodsParamSet = newGoods.getGoodsParamSet();
			for (GoodsParam goodsParam : goodsParamSet) {
				if(goodsParam != null) {
					if(goodsParam.getGoodsClassParam().getObjId().equals(goodsOptionalFitting.getGoodsParam().getGoodsClassParam().getObjId())){
						BeanUtils.copyPropertiesFilterEmpty(newFitting, goodsOptionalFitting);
						newFitting.setObjId(null);
						newFitting.setGoodsParam(goodsParam);
						goodsOptionalFittingDaoHibernate.save(newFitting);
					}
				}
			}
		}
	}
}
