package com.gpcsoft.pubservice.common.service;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.srplatform.auth.domain.User;

public interface IndexViewService extends BaseGenericService<GpcBaseObject> {
	
	/** 
	 * Description :  获取当前登录用户，并获得机构、角色、级别等信息
	 * Create Date: 2011-4-11上午10:15:05 by liangxj  Modified Date: 2011-4-11上午10:15:05 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public User getAndSetCurrentUserInfo(Map<String, Object> model,HttpSession session) throws Exception;
	
	/** 
	 * Description :获得与项目有关的统计信息 
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
	 * Create Date: 2010-10-28下午05:04:34 by likg  Modified Date: 2010-10-28下午05:04:34 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Map<String, String> getStatisticsInfo() throws Exception;
}
