package com.gpcsoft.smallscale.pointmall.service;
import java.util.List;
import java.util.Map;

import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.smallscale.pointmall.domain.Gift;

public interface GiftService extends BaseGenericService<Gift> {

	/** 
	 * Description :  保存礼品
	 * Create Date: 2011-1-7下午05:03:57 by yucy  Modified Date: 2011-1-7下午05:03:57 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Gift saveGift(Map<String, Object> param) throws Exception;

	/** 
	 * Description :查询礼品
	 * Create Date: 2011-1-10上午09:09:49 by yucy  Modified Date: 2011-1-10上午09:09:49 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<Gift> getGiftList(Map<String, Object> paramsMap) throws Exception;

	/** 
	 * Description :  查询实际礼品和系列
	 * Create Date: 2011-1-10上午09:51:57 by yucy  Modified Date: 2011-1-10上午09:51:57 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<Map<String,Object>> getSeriesAndReallyGift(Map<String, Object> paramsMap) throws Exception;

	/** 
	 * Description :  查询礼品信息详情
	 * Create Date: 2011-1-10下午02:43:57 by yucy  Modified Date: 2011-1-10下午02:43:57 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Gift getGiftAllInfo(Map<String, Object> paramsMap) throws Exception;

	/** 
	 * Description : 更新是否推荐 
	 * Create Date: 2011-1-10下午11:20:01 by yucy  Modified Date: 2011-1-10下午11:20:01 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Boolean updateGiftReCommond(Map<String, Object> param) throws Exception;

	/** 
	 * Description :  取得礼品列表
	 * Create Date: 2011-1-11下午01:51:07 by yucy  Modified Date: 2011-1-11下午01:51:07 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Page<Gift> getGiftListForShow(Page<Gift> page, Map<String, Object> paramsMap) throws Exception;

}
