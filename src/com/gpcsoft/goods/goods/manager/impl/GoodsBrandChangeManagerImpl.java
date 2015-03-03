package com.gpcsoft.goods.goods.manager.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.goods.goods.dao.GoodsBrandChangeDao;
import com.gpcsoft.goods.goods.manager.GoodsBrandChangeManager;
import com.gpcsoft.goods.goods.domain.GoodsBrand;
import com.gpcsoft.goods.goods.domain.GoodsBrandChange;

@Repository
public class GoodsBrandChangeManagerImpl extends BaseManagerImpl<GoodsBrandChange> implements GoodsBrandChangeManager {

	@Autowired(required=true)  @Qualifier("goodsBrandChangeDaoHibernate")
	private GoodsBrandChangeDao goodsBrandChangeDaoHibernate;

	/** 
	 * Description :  根据品牌取得变更数据
	 * Create Date: 2011-5-9上午11:37:29 by yucy  Modified Date: 2011-5-9上午11:37:29 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<GoodsBrandChange> getGoodsBrandChange(String brandId,	String type, String auditStatus) throws Exception {
		return goodsBrandChangeDaoHibernate.getGoodsBrandChange(brandId,type,auditStatus);
	}

	/** 
	 * Description :  更新品牌变更表的审核状态
	 * Create Date: 2011-5-9上午11:37:29 by yucy  Modified Date: 2011-5-9上午11:37:29 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean updateAuditStatus(String brandId, String type, String auditStatus ,String dealStatus) throws Exception {
		return goodsBrandChangeDaoHibernate.updateAuditStatus(brandId,type,auditStatus, dealStatus);
	}

	/** 
	 * Description :  获取品牌的变更审核列表
	 * Create Date: 2011-5-9下午04:20:19 by yucy  Modified Date: 2011-5-9下午04:20:19 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Page<GoodsBrand> getBrandListByBrandChange(Page<GoodsBrand> page, Map<String, Object> param) throws Exception {
		return goodsBrandChangeDaoHibernate.getBrandListByBrandChange(page, param);
	}

}
