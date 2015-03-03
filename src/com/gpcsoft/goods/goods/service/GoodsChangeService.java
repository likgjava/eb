package com.gpcsoft.goods.goods.service;
import java.util.List;
import java.util.Map;

import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.goods.goods.domain.Goods;
import com.gpcsoft.goods.goods.domain.GoodsChange;

public interface GoodsChangeService extends BaseGenericService<GoodsChange> {

	/** 
	 * Description :  获取变更待审核的商品
	 * Create Date: 2010-7-28上午09:39:54 by sunl  Modified Date: 2010-7-28上午09:39:54 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<GoodsChange> getGoodsAuditList(String goodsId) throws Exception;
	
	/** 
	 * Description :  获取变更待审核的商品(分页)
	 * Create Date: 2010-7-28上午09:39:54 by sunl  Modified Date: 2010-7-28上午09:39:54 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Page<Goods> getGoodsAuditListPage(Page page,Map<String,Object> params) throws Exception;
}
