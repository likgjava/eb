package com.gpcsoft.goods.goods.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.goods.goods.domain.GoodsBrand;
import com.gpcsoft.goods.goods.domain.GoodsBrandChange;
import com.gpcsoft.goods.goods.manager.GoodsBrandChangeManager;
import com.gpcsoft.goods.goods.service.GoodsBrandChangeService;

@Service 
public class GoodsBrandChangeServiceImpl extends BaseGenericServiceImpl<GoodsBrandChange> implements GoodsBrandChangeService {

	@Autowired(required=true) @Qualifier("goodsBrandChangeManagerImpl")
	private GoodsBrandChangeManager goodsBrandChangeManager;
	
	/** 
	 * Description :  根据品牌取得变更数据
	 * Create Date: 2011-5-9上午11:37:29 by yucy  Modified Date: 2011-5-9上午11:37:29 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<GoodsBrandChange> getGoodsBrandChange(String brandId ,String type, String auditStatus) throws Exception {
		return goodsBrandChangeManager.getGoodsBrandChange(brandId,type,auditStatus);
	}

	/** 
	 * Description :  获取品牌的变更审核列表
	 * Create Date: 2011-5-9下午04:20:19 by yucy  Modified Date: 2011-5-9下午04:20:19 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Page<GoodsBrand> getBrandListByBrandChange(Page<GoodsBrand> page, Map<String, Object> param) throws Exception {
		return goodsBrandChangeManager.getBrandListByBrandChange(page,param);
	}
}
