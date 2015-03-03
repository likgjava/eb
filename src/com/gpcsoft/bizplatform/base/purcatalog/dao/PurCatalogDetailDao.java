package com.gpcsoft.bizplatform.base.purcatalog.dao;

import java.util.List;
import java.util.Map;

import com.gpcsoft.bizplatform.base.purcatalog.domain.PurCatalogDetail;
import com.gpcsoft.core.dao.BaseGenericDao;

public interface PurCatalogDetailDao extends BaseGenericDao<PurCatalogDetail> {

	/** 
	 * Description :  获得catalogdetail明细
	 * Create Date: 2010-8-11上午10:50:38 by yucy  Modified Date: 2010-8-11上午10:50:38 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<PurCatalogDetail> getDetailInfo(Map<String, Object> param) throws Exception;
	
	/** 
	 * Description :  根据目录Id取得PurCatalogDetailList
	 * Create Date: 2010-8-11下午11:51:21 by yucy  Modified Date: 2010-8-11下午11:51:21 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<PurCatalogDetail> getDetailInfoByCatalogId(String catelogId) throws Exception;
}
