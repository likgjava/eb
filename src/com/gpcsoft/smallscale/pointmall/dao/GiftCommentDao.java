package com.gpcsoft.smallscale.pointmall.dao;

import java.util.Map;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.smallscale.pointmall.domain.GiftComment;

public interface GiftCommentDao extends BaseGenericDao<GiftComment> {
	
	/**
	 * Description :  是否已经评价
	 * Create Date: 2011-1-14下午04:50:24 by zhaojf  Modified Date: 2011-1-14下午04:50:24 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	Long hasGiftComment(Map<String, Object> param) throws Exception;

}
