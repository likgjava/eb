package com.gpcsoft.bizplatform.base.purcatalog.dao;

import java.util.List;
import java.util.Map;

import com.gpcsoft.bizplatform.base.purcatalog.domain.PurCatalog;
import com.gpcsoft.core.dao.BaseGenericDao;

public interface PurCatalogDao extends BaseGenericDao<PurCatalog> {

	/** 
	 * Description :  保存采购目录
	 * Create Date: 2010-8-10上午10:32:48 by yucy  Modified Date: 2010-8-10上午10:32:48 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	PurCatalog savePurCatalog(PurCatalog purCatalog);

	/** 
	 * Description :  保存采购目录明细
	 * Create Date: 2010-8-10下午04:25:32 by yucy  Modified Date: 2010-8-10下午04:25:32 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Boolean saveCatalogDetailOrProcType(Map<String, Object> param);

	/** 
	 * Description :  获得展开节点的信息
	 * Create Date: 2010-8-11上午12:33:36 by yucy  Modified Date: 2010-8-11上午12:33:36 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	 List<Object> getOpenItemInfo(Map<String, Object> param) throws Exception;
	
	/** 
	 * Description :  发布
	 * Create Date: 2010-8-25上午11:21:23 by yucy  Modified Date: 2010-8-25上午11:21:23 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Boolean updatePubStatus(String catalogId) throws Exception;

	/** 
	 * Description :  根据品目取得目录
	 * Create Date: 2010-9-3下午04:21:32 by yucy  Modified Date: 2010-9-3下午04:21:32 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<String[]> getCatalogByCategory(Map<String, Object> param) throws Exception;

	/** 
	 * Description :  取得年度
	 * Create Date: 2009-4-9下午04:13:26 by yucy  Modified Date: 2009-4-9下午04:13:26 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<Integer> getAnual() throws Exception;

}
