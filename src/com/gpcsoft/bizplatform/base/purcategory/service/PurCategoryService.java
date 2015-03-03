package com.gpcsoft.bizplatform.base.purcategory.service;

import java.util.List;
import java.util.Map;

import com.gpcsoft.bizplatform.base.purcategory.domain.PurCategory;
import com.gpcsoft.core.service.BaseGenericService;

public interface PurCategoryService extends BaseGenericService<PurCategory> {
    
	/** 
	 * Description : 保存商品参数，并生成拼音缩写 
	 * Create Date: 2010-8-4下午07:20:02 by guoyr  Modified Date: 2010-8-4下午07:20:02 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public PurCategory savePurCategory(PurCategory purCategory) throws Exception;
	
	/** 
	 * Description : 删除品目，如果该品目被引用，则不充许删除 
	 * Create Date: 2010-8-4下午08:03:26 by guoyr  Modified Date: 2010-8-4下午08:03:26 by guoyr
	 * @param   
	 * @return  
	 * @throws Exception 
	 * @Exception   
	 */
	public void removePurCategory(String objId) throws Exception;
	
	/**
     * 
     * Description :  获取采购品目
     * Create Date: 2010-1-18上午09:43:38 by xiaogh  Modified Date: 2010-1-18上午09:43:38 by xiaogh
     * @param   categoryCode
     * @return  List<PurCategory>
     * @Exception
     */
    public List<PurCategory>  getListPurCategory();
    
    
	/** 
	 * Description :  首页需要的采购品目3级
	 * Create Date: 2010-7-15下午02:59:17 by liangxj  Modified Date: 2010-7-15下午02:59:17 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<PurCategory> findPurCategoryForIndex(String nameFirstSpell);
	
	/** 
	 * Description :  首页需要的品目分类
	 * Create Date: 2011-2-18下午05:42:06 by sunl  Modified Date: 2011-2-18下午05:42:06 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<PurCategory> findGoodsCategoryForHasGoods(String nameFirstSpell);

	/** 
	 * Description :  分级获得品目
	 * Create Date: 2011-3-18下午05:39:26 by yucy  Modified Date: 2011-3-18下午05:39:26 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<PurCategory> getCategorysByLevel(Map<String, Object> param) throws Exception;

	/** 
	 * Description :  按关键字获得品目
	 * Create Date: 2011-3-22下午02:31:46 by yucy  Modified Date: 2011-3-22下午02:31:46 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<PurCategory> getCategorysByKeyWords(Map<String, Object> param) throws Exception;

	/** 
	 * Description :  根据经营范围code获取品目
	 * Create Date: 2010-8-20上午11:07:06 by sunl  Modified Date: 2010-8-20上午11:07:06 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<Object[]> getCategoryByMainProducts(String bidForRangeCodes) throws Exception;
}
