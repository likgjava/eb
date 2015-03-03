package com.gpcsoft.pubservice.common.dao;

import java.util.Map;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.core.model.GpcBaseObject;

public interface IndexViewDao extends BaseGenericDao<GpcBaseObject> {
	/** 
	 * Description : 获得与项目有关的统计信息
	 * Create Date: 2010-8-18下午04:47:26 by guoyr  Modified Date: 2010-8-18下午04:47:26 by guoyr
	 * @param 项目的总数,订单数,合同数,议价数,采购总额  
	 * @return  
	 * @Exception   
	 */
	public Map<String,String> getProjectStatistics() throws Exception;
	
	/** 
	 * Description : 获得供应商的统计信息
	 * Create Date: 2010-8-18下午04:47:26 by guoyr  Modified Date: 2010-8-18下午04:47:26 by guoyr
	 * @param 供应商的总数,已审核通过的总数,待审核的总数,最新注册的供应商总数(7个自然日内)  
	 * @return  
	 * @Exception   
	 */
	public Map<String,String> getSupplierStatistics() throws Exception;
	/** 
	 * Description : 获得代理机构的统计信息
	 * Create Date: 2010-8-18下午04:47:26 by guoyr  Modified Date: 2010-8-18下午04:47:26 by guoyr
	 * @param 代理机构的总数,已审核通过的总数,待审核的总数,最新注册的代理机构总数(7个自然日内)  
	 * @return  
	 * @Exception   
	 */
	public Map<String,String> getAgencyStatistics() throws Exception;

	/** 
	 * Description :  获取系统统计信息
	 * Create Date: 2010-10-28下午05:06:56 by likg  Modified Date: 2010-10-28下午05:06:56 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Map<String, String> getStatisticsInfo() throws Exception;
	
}
