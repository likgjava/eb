package com.gpcsoft.epp.Statistics.service;

import java.util.List;

import org.apache.commons.collections.map.ListOrderedMap;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.service.BaseGenericService;

public interface StatisticsService extends BaseGenericService<GpcBaseObject>{
	
	/** 
	 * Description :  代理机构项目负责人：取得采购方式的统计数据(5种采购方式的项目数量)
	 * Create Date: 2010-10-15下午04:59:57 by yangx  Modified Date: 2010-10-15下午04:59:57 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List statisticsEbuyMethodForAgent(String Id);

	/** 
	 * Description :  代理机构管理员：取得采购方式的统计数据(5种采购方式的项目数量)
	 * Create Date: 2010-10-15下午05:22:17 by yangx  Modified Date: 2010-10-15下午05:22:17 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List statisticsEbuyMethodForAgentManager(String Id);

	/** 
	 * Description :  监管单位经办人:取得采购方式的统计数据(5种采购方式的项目数量) 
	 * Create Date: 2010-10-15下午04:59:22 by yangx  Modified Date: 2010-10-15下午04:59:22 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List statisticsEbuyMethodForSupervise(String Id);

	/** 
	 * Description :  监管单位管理员:取得采购方式的统计数据(5种采购方式的项目数量) 
	 * Create Date: 2010-10-15下午05:24:42 by yangx  Modified Date: 2010-10-15下午05:24:42 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List statisticsEbuyMethodForSuperviseManager();

	/** 
	 * Description :  采购人:取得采购方式的统计数据(5种采购方式的项目数量) 
	 * Create Date: 2010-10-15下午05:28:38 by yangx  Modified Date: 2010-10-15下午05:28:38 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List statisticsEbuyMethodForBuyer(String Id);

	/** 
	 * Description :  业务处室:取得采购方式的统计数据(5种采购方式的项目数量) 
	 * Create Date: 2010-10-15下午05:31:27 by yangx  Modified Date: 2010-10-15下午05:31:27 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List statisticsEbuyMethodForGovernment(String Id);

	/** 
	 * Description :  供应商:取得采购方式的统计数据(5种采购方式的项目数量) 
	 * Create Date: 2010-10-15下午06:00:15 by yangx  Modified Date: 2010-10-15下午06:00:15 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List statisticsEbuyMethodForSupply(String Id);

}
