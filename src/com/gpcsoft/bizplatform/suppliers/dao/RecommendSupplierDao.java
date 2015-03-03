package com.gpcsoft.bizplatform.suppliers.dao;

import java.util.Map;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.bizplatform.suppliers.domain.RecommendSupplier;
import com.gpcsoft.bizplatform.suppliers.domain.Supplier;

public interface RecommendSupplierDao extends BaseGenericDao<RecommendSupplier> 
{
	/** 
	 * Description :  推荐指定的供应商
	 * Create Date: 2010-10-11 下午 16:30:16 by likg  Modified Date: 2010-10-11 下午 16:30:16 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	void recommendSupplier(String[] supplierIds, RecommendSupplier recommendSupplierPattern) throws Exception;
	
	/** 
	 * Description :  获取所有的未推荐供应商
	 * Create Date: 2010-10-13 下午 16:30:16 by likg  Modified Date: 2010-10-13 下午 16:30:16 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	Page listNoRecommendSupplier(Map<String,Object> param, Page page) throws Exception;
	
	/** 
	 * Description :  批量删除推荐供应商
	 * Create Date: 2010-10-13 下午 16:30:16 by likg  Modified Date: 2010-10-13 下午 16:30:16 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Boolean deleteRecommendSupplier(String[] objIds) throws Exception;
	
	/** 
	 * Description :  获得推荐的供应商
	 * Create Date: 2010-10-19上午11:07:30 by likg  Modified Date: 2010-10-19上午11:07:30 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Page<Supplier> getRecommendSupplierInfo(Page<Supplier> page, Map<String, Object> paramsMap) throws Exception;
	
	/** 
	 * Description :  修改推荐供应商的排序序号
	 * Create Date: 2010-12-22上午10:43:42 by likg  Modified Date: 2010-12-22上午10:43:42 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	void updateSort(Long sort, boolean isToUp) throws Exception;
	
}
