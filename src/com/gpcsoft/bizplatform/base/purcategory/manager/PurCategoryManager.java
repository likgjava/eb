package com.gpcsoft.bizplatform.base.purcategory.manager;

import java.util.Date;
import java.util.List;

import com.gpcsoft.bizplatform.base.purcategory.domain.PurCategory;
import com.gpcsoft.core.manager.BaseManager;

public interface PurCategoryManager extends BaseManager<PurCategory> {

	/**
	 * 
	 * Description :  获取采购品目
	 * Create Date: 2010-1-18上午09:43:38 by xiaogh  Modified Date: 2010-1-18上午09:43:38 by xiaogh
	 * @param   orderName:排序字段	isAsc:排序方式
	 * @return  List<PurCategory>
     * @Exception
     */
	public List<PurCategory> getPurCategory(String orderName, boolean isAsc);
  
	/** 
	 * Description :  首页需要的采购品目3级
	 * Create Date: 2010-7-15下午02:59:17 by liangxj  Modified Date: 2010-7-15下午02:59:17 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<PurCategory> findPurCategoryForIndex(String nameFirstSpell);

    /**
     * Description : 获得该父类下面的子类的下一个最大的编号。
     * Create Date: May 6, 2010 10:11:03 AM by liujf  Modified Date: May 6, 2010 10:11:03 AM by yucy
     * @param   
     * @return  
     * @Exception
     */
	public String getNextChildKeyCode(String firstCode, String parentId, String domian, String parentPropertyName, String targetPropertyName) throws Exception;
	
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
	PurCategory getPurCategoryByCategoryCode(String categoryCode ) throws Exception;
}   
