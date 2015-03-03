package com.gpcsoft.smallscale.pointmall.dao;

import java.util.List;
import java.util.Map;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.smallscale.pointmall.domain.Gift;

public interface GiftDao extends BaseGenericDao<Gift> {

	/** 
	 * Description :查询礼品
	 * Create Date: 2011-1-10上午09:09:49 by yucy  Modified Date: 2011-1-10上午09:09:49 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<Gift> getGiftList(Map<String, Object> paramsMap) throws Exception;

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
	Page<Gift> getGiftListForShow(Page<Gift> page, Map<String, Object> paramsMap)throws Exception;

}
