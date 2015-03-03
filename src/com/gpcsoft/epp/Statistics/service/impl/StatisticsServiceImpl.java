package com.gpcsoft.epp.Statistics.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryObjectBase;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.epp.Statistics.service.StatisticsService;
import com.gpcsoft.epp.project.manager.ProjectManager;
@Service
public class StatisticsServiceImpl extends BaseGenericServiceImpl<GpcBaseObject> implements StatisticsService{
	@Autowired(required=true) @Qualifier("projectManagerImpl")
	private ProjectManager projectManager; 
	
	@SuppressWarnings("unchecked")
	public List statisticsEbuyMethodForAgent(String Id) {
		QueryObject queryObject = new QueryObjectBase();
		queryObject.getQueryParam().and(new QueryParam("manager",QueryParam.OPERATOR_EQ,Id));
		List list=projectManager.statisticsEbuyMethod(queryObject);
		return list;
	}

	/** 
	 * Description :  代理机构管理员：取得采购方式的统计数据(5种采购方式的项目数量)
	 * Create Date: 2010-10-15下午05:22:17 by yangx  Modified Date: 2010-10-15下午05:22:17 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List statisticsEbuyMethodForAgentManager(String Id){
		QueryObject queryObject = new QueryObjectBase();
		queryObject.getQueryParam().and(new QueryParam("agencies",QueryParam.OPERATOR_EQ,Id));
		List list=projectManager.statisticsEbuyMethod(queryObject);
		return list;
	}
	
	/** 
	 * Description :  监管单位经办人:取得采购方式的统计数据(5种采购方式的项目数量) 
	 * Create Date: 2010-10-15下午04:59:22 by yangx  Modified Date: 2010-10-15下午04:59:22 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List statisticsEbuyMethodForSupervise(String Id){
		QueryObject queryObject = new QueryObjectBase();
		queryObject.getQueryParam().and(new QueryParam("superviseId",QueryParam.OPERATOR_EQ,Id));
		List list=projectManager.statisticsEbuyMethod(queryObject);
		return list;
	}
	
	/** 
	 * Description :  监管单位管理员:取得采购方式的统计数据(5种采购方式的项目数量) 
	 * Create Date: 2010-10-15下午05:24:42 by yangx  Modified Date: 2010-10-15下午05:24:42 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List statisticsEbuyMethodForSuperviseManager(){
		List list=projectManager.statisticsEbuyMethod(null);
		return list;
	}
	
	/** 
	 * Description :  采购人:取得采购方式的统计数据(5种采购方式的项目数量) 
	 * Create Date: 2010-10-15下午05:28:38 by yangx  Modified Date: 2010-10-15下午05:28:38 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List statisticsEbuyMethodForBuyer(String Id){
		QueryObject queryObject = new QueryObjectBase();
		queryObject.getQueryParam().and(new QueryParam("buyerId",QueryParam.OPERATOR_EQ,Id));
		List list=projectManager.statisticsEbuyMethod(queryObject);
		return list;
	}
	

	/** 
	 * Description :  业务处室:取得采购方式的统计数据(5种采购方式的项目数量) 
	 * Create Date: 2010-10-15下午05:31:27 by yangx  Modified Date: 2010-10-15下午05:31:27 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List statisticsEbuyMethodForGovernment(String Id){
		QueryObject queryObject = new QueryObjectBase();
		queryObject.getQueryParam().and(new QueryParam("governmentId",QueryParam.OPERATOR_EQ,Id));
		List list=projectManager.statisticsEbuyMethod(queryObject);
		return list;
	}
	

	/** 
	 * Description :  供应商:取得采购方式的统计数据(5种采购方式的项目数量) 
	 * Create Date: 2010-10-15下午06:00:15 by yangx  Modified Date: 2010-10-15下午06:00:15 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public List statisticsEbuyMethodForSupply(String Id){
		QueryObject queryObject = new QueryObjectBase();
		queryObject.getQueryParam().and(new QueryParam("supplyId",QueryParam.OPERATOR_EQ,Id));
		List list=projectManager.statisticsEbuyMethod(queryObject);
		return list;
	}

}
