package com.gpcsoft.goods.goodsclass.service;

import java.util.List;
import java.util.Map;

import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.goods.goodsclass.domain.GoodsClass;

public interface GoodsClassService extends BaseGenericService<GoodsClass> {
    /**
     * Description : 删除指定的商品分类。
     * Create Date: Jan 27, 2010 1:19:06 PM by liujf  Modified Date: Jan 27, 2010 1:19:06 PM by yucy
     * @param   
     * @return  
     * @Exception
     */
    public void removeGoodsClass(String goodsClassId) throws Exception;
    
    /** 
     * Description :保存商品分类  
     * Create Date: 2010-7-28下午04:50:00 by yucy  Modified Date: 2010-7-28下午04:50:00 by yucy
     * @param   分类,名目id
     * @return  
     * @Exception   
     */
    public void saveGoodsClass(GoodsClass goodsClass,String purCategoryIds) throws Exception;

	/** 
	 * Description :  首页需要的3级商品分类
	 * Create Date: 2010-4-17下午06:59:27 by wangsw  Modified Date: 2010-4-17下午06:59:27 by wangsw
	 * @return  3级分类
	 * @Exception   
	 */
	List<GoodsClass> findGoodsClassForIndex(String nameFirstSpell);

	/** 
	 * Description :  取得分配的分类编号
	 * Create Date: 2010-7-28下午05:21:27 by yucy  Modified Date: 2010-7-28下午05:21:27 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public String getGoodsClassCode(String parentClassId) throws Exception;

	/** 
	 * Description :  查看商品分类的详细信息。
	 * Create Date: 2010-8-9下午02:35:38 by yucy  Modified Date: 2010-8-9下午02:35:38 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Map<String, Object> getGoodsClassDetail(String goodsClassId) throws Exception;
	
	/** 
	 * Description :  根据商品分类code获得商品分类
	 * Create Date: 2010-8-18上午11:13:27 by liangxj  Modified Date: 2010-8-18上午11:13:27 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public GoodsClass getGoodsClassByCode(String goodsClassCode) throws Exception;
	
	/** 
	 * Description :  首页需要的商品分类,只查询有商品的
	 * Create Date: 2010-12-17下午02:51:55 by liangxj  Modified Date: 2010-12-17下午02:51:55 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<GoodsClass> findGoodsClassForHasGoods(String nameFirstSpell);
}
