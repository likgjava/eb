package com.gpcsoft.bizplatform.suppliers.service.impl;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.bizplatform.suppliers.dao.RecommendSupplierDao;
import com.gpcsoft.bizplatform.suppliers.domain.RecommendSupplier;
import com.gpcsoft.bizplatform.suppliers.domain.Supplier;
import com.gpcsoft.bizplatform.suppliers.manager.RecommendSupplierManager;
import com.gpcsoft.bizplatform.suppliers.service.RecommendSupplierService;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.Page;

@Service 
public class RecommendSupplierServiceImpl extends BaseGenericServiceImpl<RecommendSupplier> implements RecommendSupplierService {

	@Autowired(required=true) @Qualifier("recommendSupplierManagerImpl")
	private RecommendSupplierManager recommendSupplierManager;

	@Autowired(required=true)  @Qualifier("recommendSupplierDaoHibernate")
	private RecommendSupplierDao recommendSupplierDaoHibernate;

	/** 
	 * Description :  推荐供应商
	 * Create Date: 2010-10-11 下午 16:30:16 by likg  Modified Date: 2010-10-11 下午 16:30:16 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void recommendSupplier(String supplierIds, String recommendReason) throws Exception
	{
		RecommendSupplier recommendSupplierPattern = new RecommendSupplier();
		recommendSupplierPattern.setReason(recommendReason);
		recommendSupplierPattern.setAuditStatus("00");
		recommendSupplierPattern.setUseStatus(false);
		recommendSupplierPattern.setCreateTime(new Date());
		recommendSupplierPattern.setSort(0L);
		
		recommendSupplierDaoHibernate.recommendSupplier(supplierIds.split(","), recommendSupplierPattern);
	}

	/** 
	 * Description :  获取所有的未推荐供应商
	 * Create Date: 2010-10-13 下午 16:30:16 by likg  Modified Date: 2010-10-13 下午 16:30:16 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Page listNoRecommendSupplier(Map<String,Object> param, Page page) throws Exception 
	{
		return recommendSupplierDaoHibernate.listNoRecommendSupplier(param, page);
	}

	/** 
	 * Description :  批量删除推荐供应商
	 * Create Date: 2010-10-13 下午 16:30:16 by likg  Modified Date: 2010-10-13 下午 16:30:16 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean deleteRecommedSupplier(String[] objIds) throws Exception 
	{
		return recommendSupplierDaoHibernate.deleteRecommendSupplier(objIds);
	}
	
	/** 
	 * Description :  修改推荐供应商的排序序号
	 * Create Date: 2010-10-15上午10:43:42 by likg  Modified Date: 2010-10-15上午10:43:42 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void updateSort(String sourceObjId, boolean isToUp) throws Exception 
	{
		RecommendSupplier sourceRecommendSupplier = recommendSupplierManager.get(sourceObjId);
		
		recommendSupplierDaoHibernate.updateSort(sourceRecommendSupplier.getSort(), isToUp);
	}
	
	/** 
	 * Description :  获得推荐的供应商
	 * Create Date: 2010-10-19上午11:05:02 by likg  Modified Date: 2010-10-19上午11:05:02 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Page<Supplier> getRecommendSupplierInfo(Page<Supplier> page, Map<String, Object> paramsMap) throws Exception 
	{
		return recommendSupplierDaoHibernate.getRecommendSupplierInfo(page, paramsMap);
	}
	
}
