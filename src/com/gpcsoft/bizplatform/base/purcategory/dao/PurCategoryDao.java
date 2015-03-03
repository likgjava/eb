package com.gpcsoft.bizplatform.base.purcategory.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.gpcsoft.bizplatform.base.purcategory.domain.PurCategory;
import com.gpcsoft.core.dao.BaseGenericDao;

public interface PurCategoryDao extends BaseGenericDao<PurCategory> {
    /**
     * 
     * Description :  根据parentCode得到它下级品目
     * Create Date: 2010-1-18上午09:43:38 by xiaogh  Modified Date: 2010-1-18上午09:43:38 by xiaogh
     * @param   parentCode
     * @return  List<PurCategory>
     * @Exception
     */
    public List<PurCategory> getPurCategoryItems(String parentCode) throws Exception;
    
	/**
	 * 
	 * Description :  根据categoryCode判断是否有下级品目
	 * Create Date: 2010-1-18上午09:43:38 by xiaogh  Modified Date: 2010-1-18上午09:43:38 by xiaogh
	 * @param   categoryCode
	 * @return  boolean
	 * @Exception
	 */
    public boolean isHasNextPurCategory(String categoryCode)throws Exception;
    
    /** 
	 * Description :  首页需要的采购品目3级
	 * Create Date: 2010-7-15下午02:59:17 by liangxj  Modified Date: 2010-7-15下午02:59:17 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<PurCategory> findPurCategoryForIndex(String nameFirstSpell);
	
	/** 
	 * Description :  首页需要的品目分类
	 * Create Date: 2011-2-18下午05:42:06 by sunl  Modified Date: 2011-2-18下午05:42:06 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<PurCategory> findGoodsCategoryForHasGoods(String nameFirstSpell);
	
	/** 
	 * Description :  根据code获得品目
	 * Create Date: 2011-2-18下午05:49:02 by sunl  Modified Date: 2011-2-18下午05:49:02 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<PurCategory> findGoodsCategoryByCodes(final Set<String> codes);
	
	/** 
	 * Description : 修改采购品目是否为叶子结点 
	 * Create Date: 2010-7-30上午10:44:19 by guoyr  Modified Date: 2010-7-30上午10:44:19 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void updatePurCategoryIsLeaf(String objId,boolean isleaf);
	
	/** 
	 * Description : 查询当前商品参数的父结点的子结点的个数 
	 * Create Date: 2010-8-3下午06:16:29 by guoyr  Modified Date: 2010-8-3下午06:16:29 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Long getSubPurCategoryCount(String objId);
	
	/** 
	 * Description :  获得该父类下面最大的编号。(by hql)
	 * Create Date: 2010-7-28下午05:50:26 by yucy  Modified Date: 2010-7-28下午05:50:26 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public String getMaxChildKeyCodeByHQl(final String parentId, final String domain, final String parentPropertyName, final String targetPropertyName);

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
	public List<PurCategory> getCategorysByKeyWords(Map<String, Object> param)throws Exception;

	/** 
	 * Description :  根据经营范围code获取品目
	 * Create Date: 2010-8-20上午11:07:06 by sunl  Modified Date: 2010-8-20上午11:07:06 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<Object[]> getCategoryByMainProducts(String bidForRangeCodes) throws Exception;

	/** 
	 * Description :  获取采购品目的最新更新时间
	 * Create Date: 2011-5-13上午11:45:39 by likg  Modified Date: 2011-5-13上午11:45:39 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Date getLastUpdateTime() throws Exception;

	/** 
	 * Description :  根据品目code获取品目
	 * Create Date: 2011-12-9下午03:07:20 by yucy  Modified Date: 2011-12-9下午03:07:20 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public PurCategory getPurCategoryByCategoryCode(String categoryCode) throws Exception;
}
