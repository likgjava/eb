package com.gpcsoft.goods.goodsclass.service;
import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.goods.goodsclass.domain.GoodsClassParam;

public interface GoodsClassParamService extends BaseGenericService<GoodsClassParam> {
    
	/** 
	 * Description : 保存商品参数 
	 * Create Date: 2010-7-29下午03:17:34 by guoyr  Modified Date: 2010-7-29下午03:17:34 by guoyr
	 * @param   
	 * @return  
	 * @throws Exception 
	 * @Exception   
	 */
	public GoodsClassParam saveGoodsClassParam(GoodsClassParam goodsClassParam) throws Exception ; 
	
	 /** 
	 * Description : 删除商品参数，如果该参数被GoodsParam引用，则不充许删除，如果删除则判断是否新修改父结点为叶子结点 
	 * Create Date: 2010-8-3下午02:47:14 by guoyr  Modified Date: 2010-8-3下午02:47:14 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void removeGoodsClassParam(String objId);
}
