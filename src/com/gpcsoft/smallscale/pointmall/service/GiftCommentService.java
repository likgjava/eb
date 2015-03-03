package com.gpcsoft.smallscale.pointmall.service;
import java.util.Map;

import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.smallscale.pointmall.domain.GiftComment;

public interface GiftCommentService extends BaseGenericService<GiftComment> {

	
	/**
	 * Description :  保存礼品评价
	 * Create Date: 2011-1-14下午04:39:20 by zhaojf  Modified Date: 2011-1-14下午04:39:20 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	Map<String, Object> saveGiftComment(Map<String, Object> param) throws Exception;

}
