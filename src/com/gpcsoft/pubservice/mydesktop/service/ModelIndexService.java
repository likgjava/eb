package com.gpcsoft.pubservice.mydesktop.service;
import java.util.Map;

import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.service.BaseGenericService;

public interface ModelIndexService extends BaseGenericService<GpcBaseObject> {
	
	/** 
	 * Description :  获得供应商的首页
	 * Create Date: 2010-8-16上午10:48:54 by sunl  Modified Date: 2010-8-16上午10:48:54 by sunl
	 * @param   
	 * @return  包含用户信息、业务角色信息、任务信息、推荐公告信息
	 * @Exception   
	 */
	public Map<String, Object> getSupplierIndex() throws Exception;
	
	/** 
	 * Description :  获得采购人的首页
	 * Create Date: 2010-8-16上午10:48:54 by sunl  Modified Date: 2010-8-16上午10:48:54 by sunl
	 * @param   
	 * @return  包含用户信息、业务角色信息、任务信息
	 * @Exception   
	 */
	public Map<String, Object> getBuyerIndex() throws Exception;
	
	/** 
	 * Description :  获得专家的首页
	 * Create Date: 2010-8-16上午10:48:54 by sunl  Modified Date: 2010-8-16上午10:48:54 by sunl
	 * @param   
	 * @return  包含用户信息、业务角色信息、任务信息
	 * @Exception   
	 */
	public Map<String, Object> getExpertIndex() throws Exception;
	
	/** 
	 * Description :  获得代理机构的首页
	 * Create Date: 2010-8-16上午10:48:54 by sunl  Modified Date: 2010-8-16上午10:48:54 by sunl
	 * @param   
	 * @return  包含用户信息、业务角色信息、任务信息
	 * @Exception   
	 */
	public Map<String, Object> getAgencyIndex() throws Exception;
	
	/** 
	 * Description :  获得商品库管理的首页
	 * Create Date: 2010-8-16上午10:48:54 by sunl  Modified Date: 2010-8-16上午10:48:54 by sunl
	 * @param   
	 * @Exception   
	 */
	public Map<String, Object> getGoodsIndex() throws Exception;
	
	/** 
	 * Description :  获得机构管理的首页
	 * Create Date: 2010-8-16上午10:47:31 by sunl  Modified Date: 2010-8-16上午10:47:31 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Map<String, Object> getOrgInfoIndex(String orgInfoId) throws Exception;
	
	/** 
	 * Description :  获得基础数据的首页
	 * Create Date: 2010-8-16上午10:48:54 by sunl  Modified Date: 2010-8-16上午10:48:54 by sunl
	 * @param   
	 * return 	待审核集中采购目录数目
	 * @Exception   
	 */
	public Map<String, Object> getBaseDataIndex() throws Exception;
	
	/** 
	 * Description :  获取指定时间段的机构统计信息(被收藏次数，关注数量，销售金额，采购金额)
	 * Create Date: 2011-8-18下午04:21:40 by likg  Modified Date: 2011-8-18下午04:21:40 by likg
	 * @param   orgInfoId:机构Id
	 * @return  
	 * @Exception   
	 */
	public Map<String, String> getOrgStatisticsInfoByTime(String orgInfoId, int days) throws Exception;
}
