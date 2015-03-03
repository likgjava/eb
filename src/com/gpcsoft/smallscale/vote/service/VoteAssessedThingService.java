package com.gpcsoft.smallscale.vote.service;
import java.util.List;
import java.util.Map;

import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.goods.goods.domain.GoodsBrand;
import com.gpcsoft.smallscale.vote.domain.VoteAssessedThing;

public interface VoteAssessedThingService extends BaseGenericService<VoteAssessedThing> {
	/**
	 * Description :  获取主题下的评选单位
	 * Create Date: 2011-2-21下午02:59:48 by zhaojf  Modified Date: 2011-2-21下午02:59:48 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<VoteAssessedThing> findAssessedThingOfTemplate(String templateId);
	
	/**
	 * Description :  是否推荐(isRecommended)
	 * Create Date: 2011-4-26下午07:12:51 by zhaojf  Modified Date: 2011-4-26下午07:12:51 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public Boolean updateIsRecommendedStatus(Map<String, Object> params);
	
	/**
	 * Description :  判断是否存在参选对象
	 * Create Date: 2011-5-5下午01:18:00 by zhaojf  Modified Date: 2011-5-5下午01:18:00 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public Boolean isExitAttender(Map<String, Object> params);
	
	/**
	 * Description :  根据所属机构和品牌名称获取品牌对象
	 * Create Date: 2011-5-18下午05:01:32 by zhaojf  Modified Date: 2011-5-18下午05:01:32 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public GoodsBrand getGoodsBrandByNameAndBelongs(String belongsId,String brandName);
	
	/**
	 * Description :  设置排序号
	 * Create Date: 2011-5-20下午01:17:40 by zhaojf  Modified Date: 2011-5-20下午01:17:40 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public Boolean setNumSort(Map<String, Object> params);
	
}
