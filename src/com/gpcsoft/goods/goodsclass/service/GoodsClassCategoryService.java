package com.gpcsoft.goods.goodsclass.service;

import java.util.List;

import com.gpcsoft.bizplatform.base.purcategory.domain.PurCategory;
import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.goods.goodsclass.domain.GoodsClass;
import com.gpcsoft.goods.goodsclass.domain.GoodsClassCategory;

public interface GoodsClassCategoryService extends BaseGenericService<GoodsClassCategory> {
	/** 
	 * Description :  根据品目获得商品分类的信息集合
	 * Create Date: 2010-7-27下午06:12:58 by liangxj  Modified Date: 2010-7-27下午06:12:58 by liangxj
	 * @param   categoryId 品目id，categoryCode 品目编号, isLeaf 是否只取叶子节点
	 * @return  
	 * @Exception   
	 */
	public List<GoodsClass> getClassListByCategory(String categoryId,String categoryCode,Boolean isLeaf) throws Exception;
	
	/** 
	 * Description :  根据品获取分类集合(jdbc查询方式)
	 * Create Date: 2010-8-20上午11:07:06 by sunl  Modified Date: 2010-8-20上午11:07:06 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<Object[]> getClassByCategory(String categoryId,String categoryCode,Boolean isLeaf) throws Exception;
	
	/** 
	 * Description :  根据品目获得商品分类的展示信息集合
	 * Create Date: 2010-7-27下午06:12:58 by liangxj  Modified Date: 2010-7-27下午06:12:58 by liangxj
	 * @param   categoryId 品目id，categoryCode 品目编号, isLeaf 是否只取叶子节点
	 * @return  
	 * @Exception   
	 */
	public List<String[]> getBrandsListShowByClass(String categoryId,String categoryCode,Boolean isLeaf) throws Exception;
	
	/** 
	 * Description : 根据品牌获取还未指定维护供应商的商品分类 
	 * Create Date: 2010-8-6上午09:18:06 by guoyr  Modified Date: 2010-8-6上午09:18:06 by guoyr
	 * @param objId   
	 * @return  
	 * @Exception   
	 */
	public List<GoodsClass> getUnSpecifiedGoodsClass(String objId,String goodsBrandId);
	
	
	/** 
	 * Description :  根据分类获取品目
	 * Create Date: 2010-8-20上午11:07:06 by sunl  Modified Date: 2010-8-20上午11:07:06 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<PurCategory> getAssignedPurcategory(String classId) throws Exception;
}
