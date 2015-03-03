package com.gpcsoft.goods.goodsprice.service;
import java.util.List;
import java.util.Map;

import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.goods.goods.domain.Goods;
import com.gpcsoft.goods.goodsclass.domain.GoodsClass;
import com.gpcsoft.goods.goodsprice.domain.GoodsPriceSupplier;

public interface GoodsPriceSupplierService extends BaseGenericService<GoodsPriceSupplier> {

	/** 
	 * Description :  是否有供应商行情
	 * Create Date: 2010-4-15下午03:02:54 by yucy  Modified Date: 2010-4-15下午03:02:54 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	GoodsPriceSupplier getgoodsPriceSupplierBySupplier(Map<String, Object> param ) throws Exception;

	/** 
	 * Description :  显示供应商行情/禁止显示供应商行情
	 * Create Date: 2010-4-17上午12:31:27 by yucy  Modified Date: 2010-4-17上午12:31:27 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Boolean updateShowStatus(Map<String, Object> param) throws Exception;
	
	/** 
	 * Description :  判断供应商是否具有商圈会员角色
	 * Create Date: 2010-7-28上午09:39:54 by sunl  Modified Date: 2010-7-28上午09:39:54 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public boolean isMember(Map<String,Object> param) throws Exception;
	
	/** 
	 * Description :  获取供应商报价的商品列表
	 * Create Date: 2011-2-23上午10:50:45 by likg  Modified Date: 2011-2-23上午10:50:45 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Page<Goods> getGoodsList(Page<Goods> page, Map<String, Object> param) throws Exception;
	
	/** 
	 * Description :  获取供应商报价的商品分类列表
	 * Create Date: 2011-2-23上午10:50:45 by likg  Modified Date: 2011-2-23上午10:50:45 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<GoodsClass> getGoodsClassList(Map<String, Object> param) throws Exception;
}
