package com.gpcsoft.bizplatform.base.purcatalog.service;
import java.util.List;
import java.util.Map;

import com.gpcsoft.bizplatform.base.purcatalog.domain.PurCatalog;
import com.gpcsoft.bizplatform.base.purcatalog.domain.PurCatalogDetail;
import com.gpcsoft.bizplatform.base.purcatalog.domain.PurCatalogProcType;
import com.gpcsoft.core.service.BaseGenericService;

public interface PurCatalogService extends BaseGenericService<PurCatalog> {

	/** 
	 * Description :  保存目录
	 * Create Date: 2010-8-10上午10:31:45 by yucy  Modified Date: 2010-8-10上午10:31:45 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	PurCatalog savePurCatalog(PurCatalog purCatalog ,String saveType ,String districtString) throws Exception;

	/** 
	 * Description :  保存目录明细对象
	 * Create Date: 2010-8-10下午04:19:18 by yucy  Modified Date: 2010-8-10下午04:19:18 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Boolean saveCatalogDetailOrProcType(Map<String, Object> param) throws Exception;

	/** 
	 * Description :  获得展开节点的信息
	 * Create Date: 2010-8-11上午12:33:36 by yucy  Modified Date: 2010-8-11上午12:33:36 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	 List<Object> getOpenItemInfo(Map<String, Object> param) throws Exception;

	/** 
	 * Description :  获得catalogdetail明细
	 * Create Date: 2010-8-11上午10:50:38 by yucy  Modified Date: 2010-8-11上午10:50:38 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<PurCatalogDetail> getDetailInfo(Map<String, Object> param) throws Exception;

	/** 
	 * Description :  获得catalogType 明细
	 * Create Date: 2010-8-11上午10:51:13 by yucy  Modified Date: 2010-8-11上午10:51:13 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<PurCatalogProcType> getTypeInfo(Map<String, Object> param) throws Exception;

	/** 
	 * Description : 拷贝 catalog 
	 * Create Date: 2010-8-11下午07:45:08 by yucy  Modified Date: 2010-8-11下午07:45:08 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	String createCopyCatalog(Map<String, Object> param) throws Exception;

	/** 
	 * Description :  删除目录(备以约束删除)
	 * Create Date: 2010-8-12上午01:06:03 by yucy  Modified Date: 2010-8-12上午01:06:03 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Boolean deleteCatalog(Map<String, Object> param) throws Exception;

	/** 
	 * Description :  确认提交
	 * Create Date: 2010-8-12上午10:23:48 by yucy  Modified Date: 2010-8-12上午10:23:48 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Boolean getIshasAnyDetail(Map<String, Object> param) throws Exception;

	/** 
	 * Description :  审核
	 * Create Date: 2010-8-12下午01:56:08 by yucy  Modified Date: 2010-8-12下午01:56:08 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Boolean auditPurCatalog(Map<String, Object> param) throws Exception;

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
	 * Create Date: 2009-4-9下午04:12:16 by yucy  Modified Date: 2009-4-9下午04:12:16 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<Integer> getAnual() throws Exception;

}
