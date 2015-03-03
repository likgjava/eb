package com.gpcsoft.agreement.order.service;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.gpcsoft.agreement.order.domain.Order;
import com.gpcsoft.agreement.order.domain.Procurementtask;
import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.common.utils.UploadFileResult;

public interface ProcurementtaskService extends BaseGenericService<Procurementtask> {

	@SuppressWarnings("unchecked")
	Page<Order> listOrderBySql(Page page, HttpServletRequest request);

	/** 
	 * Description :  保存对象
	 * Create Date: 2010-11-9下午03:14:37 by yucy  Modified Date: 2010-11-9下午03:14:37 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Procurementtask createOrUpdatePlan(Map<String, Object> param) throws Exception;

	/** 
	 * Description :  导入采购计划XML
	 * Create Date: 2011-12-9上午10:41:14 by yucy  Modified Date: 2011-12-9上午10:41:14 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Boolean saveImportPlanXML(UploadFileResult result) throws Exception;

	/** 
	 * Description : 导入采购计划Excel
	 * Create Date: 2011-12-9上午10:51:43 by yucy  Modified Date: 2011-12-9上午10:51:43 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Boolean saveImportPlanExcel( UploadFileResult result ) throws Exception;

}
