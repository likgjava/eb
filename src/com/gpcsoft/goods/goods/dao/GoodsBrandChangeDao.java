package com.gpcsoft.goods.goods.dao;

import java.util.List;
import java.util.Map;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.goods.goods.domain.GoodsBrand;
import com.gpcsoft.goods.goods.domain.GoodsBrandChange;

public interface GoodsBrandChangeDao extends BaseGenericDao<GoodsBrandChange> {

	/** 
	 * Description :  根据品牌取得变更数据
	 * Create Date: 2011-5-9上午11:37:29 by yucy  Modified Date: 2011-5-9上午11:37:29 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<GoodsBrandChange> getGoodsBrandChange(String brandId,	String type, String auditStatus) throws Exception;

	/** 
	 * Description :  更新品牌变更表的审核状态
	 * Create Date: 2011-5-9上午11:37:29 by yucy  Modified Date: 2011-5-9上午11:37:29 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Boolean updateAuditStatus(String brandId, String type, String auditStatus ,String dealStatus) throws Exception ;

	/** 
	 * Description :  获取品牌的变更审核列表
	 * Create Date: 2011-5-9下午04:20:19 by yucy  Modified Date: 2011-5-9下午04:20:19 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Page<GoodsBrand> getBrandListByBrandChange(Page<GoodsBrand> page,  Map<String, Object> param) throws Exception;

}
