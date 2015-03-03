package com.gpcsoft.pubservice.mydesktop.dao;

import java.util.List;
import java.util.Map;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.core.model.GpcBaseObject;

public interface ModelIndexDao extends BaseGenericDao<GpcBaseObject> {
	/** 
	 * Description :  获得采购人的任务信息
	 * Create Date: 2010-8-16下午01:46:55 by sunl  Modified Date: 2010-8-16下午01:46:55 by sunl
	 * @param   
	 * @return  待提交订单、待确认合同、进行中的议价、购物车、进行中的项目
	 * @Exception   
	 */
	public Map<String,String> getBuyerTask(String userId,String orgId) throws Exception;
	
	/** 
	 * Description :  获得供应商的任务信息
	 * Create Date: 2010-8-16下午01:46:55 by sunl  Modified Date: 2010-8-16下午01:46:55 by sunl
	 * @param   
	 * @return  待提交订单、待确认合同、进行中的议价、进行中的项目
	 * @Exception   
	 */
	public Map<String,String> getSupplierTask(String userId,String orgId) throws Exception;
	
	/** 
	 * Description :  获得代理机构的任务信息
	 * Create Date: 2010-8-16下午01:46:55 by sunl  Modified Date: 2010-8-16下午01:46:55 by sunl
	 * @param   
	 * @return  进行中的项目
	 * @Exception   
	 */
	public Map<String,String> getAgencyTask(String userId,String orgId) throws Exception;
	
	/** 
	 * Description :  获得供应商的推荐公告信息（只查询5条）
	 * Create Date: 2010-8-16下午01:46:55 by sunl  Modified Date: 2010-8-16下午01:46:55 by sunl
	 * @param   根据供应商的投标范围及类别
	 * @return  推荐公告信息
	 * @Exception   
	 */
	public List<Map<String,Object>> getRecommendBulletin(String bidForRangeName) throws Exception;
	
	/** 
	 * Description :  获得供应商桌面的投标项目列表（只查询5条）
	 * Create Date: 2010-8-16下午01:46:55 by sunl  Modified Date: 2010-8-16下午01:46:55 by sunl
	 * @param   供应商的orgId
	 * @return  
	 * @Exception   
	 */
	public List<Map<String,Object>> getSupplierProjects(String orgId) throws Exception;
	
	/** 
	 * Description :  获得采购人桌面的我参与的项目列表（只查询5条）
	 * Create Date: 2010-8-16下午01:46:55 by sunl  Modified Date: 2010-8-16下午01:46:55 by sunl
	 * @param   采购人的orgId
	 * @return  
	 * @Exception   
	 */
	public List<Map<String,Object>> getBuyerProjects(String orgId) throws Exception;
	
	/** 
	 * Description :  获得代理机构桌面的我参与的项目列表（只查询5条）
	 * Create Date: 2010-8-16下午01:46:55 by sunl  Modified Date: 2010-8-16下午01:46:55 by sunl
	 * @param   供应商的orgId
	 * @return  
	 * @Exception   
	 */
	public List<Map<String,Object>> getAgencyProjects(String orgId) throws Exception;
	
	/** 
	 * Description :  获得供应商桌面的待确认合同列表（只查询5条）
	 * Create Date: 2010-8-16下午01:46:55 by sunl  Modified Date: 2010-8-16下午01:46:55 by sunl
	 * @param   供应商的orgId
	 * @return  
	 * @Exception   
	 */
	public List<Map<String,Object>> getSupplierContracts(String orgId) throws Exception;
	
	/** 
	 * Description :  获得采购人桌面的待确认合同列表（只查询5条）
	 * Create Date: 2010-8-16下午01:46:55 by sunl  Modified Date: 2010-8-16下午01:46:55 by sunl
	 * @param   供应商的orgId
	 * @return  
	 * @Exception   
	 */
	public List<Map<String,Object>> getBuyerContracts(String orgId) throws Exception;
	
	/** 
	 * Description :  获得供应商桌面的我参与竞价项目列表（只查询5条）
	 * Create Date: 2010-8-16下午01:46:55 by sunl  Modified Date: 2010-8-16下午01:46:55 by sunl
	 * @param   供应商的orgId
	 * @return  
	 * @Exception   
	 */
	public List<Map<String,Object>> getSupplierBids(String orgId,String userId) throws Exception;
	
	/** 
	 * Description :  获得采购人桌面的我创建的竞价项目列表（只查询5条）
	 * Create Date: 2010-8-16下午01:46:55 by sunl  Modified Date: 2010-8-16下午01:46:55 by sunl
	 * @param   采购人的orgId
	 * @return  
	 * @Exception   
	 */
	public List<Map<String,Object>> getBuyerBids(String orgId,String userId) throws Exception;
	
	/** 
	 * Description :  获得供应商桌面的我参与反拍项目列表（只查询5条）
	 * Create Date: 2010-8-16下午01:46:55 by sunl  Modified Date: 2010-8-16下午01:46:55 by sunl
	 * @param   供应商的orgId
	 * @return  
	 * @Exception   
	 */
	public List<Map<String,Object>> getSupplierReverses(String orgId,String userId) throws Exception;
	
	/** 
	 * Description :  获得采购人桌面的我创建的反拍项目列表（只查询5条）
	 * Create Date: 2010-8-16下午01:46:55 by sunl  Modified Date: 2010-8-16下午01:46:55 by sunl
	 * @param   采购人的orgId
	 * @return  
	 * @Exception   
	 */
	public List<Map<String,Object>> getBuyerReverses(String orgId,String userId) throws Exception;
	
	/** 
	 * Description :  获得采购人桌面的我的议价列表（只查询5条）
	 * Create Date: 2010-8-16下午01:46:55 by sunl  Modified Date: 2010-8-16下午01:46:55 by sunl
	 * @param   采购人的orgId
	 * @return  
	 * @Exception   
	 */
	public List<Map<String,Object>> getBuyerBargains(String userId) throws Exception;
	
	/** 
	 * Description :  获得供应商桌面的我的议价列表（只查询5条）
	 * Create Date: 2010-8-16下午01:46:55 by sunl  Modified Date: 2010-8-16下午01:46:55 by sunl
	 * @param   供应商的orgId
	 * @return  
	 * @Exception   
	 */
	public List<Map<String,Object>> getSupplierBargains(String orgId) throws Exception;
	
	/** 
	 * Description :  获得供应商桌面的待确认订单列表（只查询5条）
	 * Create Date: 2010-8-16下午01:46:55 by sunl  Modified Date: 2010-8-16下午01:46:55 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<Map<String,Object>> getSupplierOrders(String userId) throws Exception;
	
	/** 
	 * Description :  获得采购人桌面的待提交订单列表（只查询5条）
	 * Create Date: 2010-8-16下午01:46:55 by sunl  Modified Date: 2010-8-16下午01:46:55 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<Map<String,Object>> getBuyerOrders(String userId) throws Exception;
	
	/** 
	 * Description :  获得商品库管理的任务信息
	 * Create Date: 2010-8-16下午01:46:55 by sunl  Modified Date: 2010-8-16下午01:46:55 by sunl
	 * @param   
	 * @Exception   
	 */
	public Map<String,String> getGoodsTask() throws Exception;
	
	/** 
	 * Description :  获得机构管理的首页
	 * Create Date: 2010-8-16上午10:47:31 by sunl  Modified Date: 2010-8-16上午10:47:31 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Map<String, String> getOrgInfoIndex(String orgInfoId) throws Exception;
	
	/** 
	 * Description :  获得基础数据的首页
	 * Create Date: 2010-8-16上午10:47:31 by sunl  Modified Date: 2010-8-16上午10:47:31 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Map<String, String> getBaseDataIndex() throws Exception;
	
	/** 
	 * Description :  获取统计信息(违规记录数，被关注记录数，未读站内信数，未读留言数，发布项目数，发布采购需求数)
	 * Create Date: 2011-8-18下午04:21:40 by likg  Modified Date: 2011-8-18下午04:21:40 by likg
	 * @param   orgInfoId:机构Id userId:用户Id
	 * @return  
	 * @Exception   
	 */
	public Map<String, String> getStatisticsInfo(String orgInfoId, String userId) throws Exception;
	
	/** 
	 * Description :  获取机构的统计信息(认证资质数，成功案例数，已有联系人数，客户评价次数，客户评价总分，好评次数，被投诉次数，被举报次数，违规记录数)
	 * Create Date: 2011-8-18下午04:21:40 by likg  Modified Date: 2011-8-18下午04:21:40 by likg
	 * @param   orgInfoId:机构Id
	 * @return  
	 * @Exception   
	 */
	public Map<String, String> getOrgStatisticsInfo(String orgInfoId) throws Exception;
	
	/** 
	 * Description :  获取指定时间段的机构统计信息(被收藏次数，关注数量，销售金额，采购金额)
	 * Create Date: 2011-8-18下午04:21:40 by likg  Modified Date: 2011-8-18下午04:21:40 by likg
	 * @param   orgInfoId:机构Id
	 * @return  
	 * @Exception   
	 */
	public Map<String, String> getOrgStatisticsInfoByTime(String orgInfoId, int days) throws Exception;
	
	/** 
	 * Description :  获取子公司采购信息(采购计划数量和金额，订单数量和金额，项目数量)
	 * Create Date: 2011-8-19上午11:18:40 by likg  Modified Date: 2011-8-19上午11:18:40 by likg
	 * @param   parentOrgInfoId:总公司的机构Id
	 * @return  
	 * @Exception   
	 */
	public List<Object[]> getSubCompanyBuyInfo(String parentOrgInfoId) throws Exception;
	
	/** 
	 * Description :  获取子公司销售信息(参与项目数，中标项目数，成交总金额)
	 * Create Date: 2011-8-19上午11:18:40 by likg  Modified Date: 2011-8-19上午11:18:40 by likg
	 * @param   parentOrgInfoId:总公司的机构Id
	 * @return  
	 * @Exception   
	 */
	public List<Object[]> getSubCompanySaleInfo(String parentOrgInfoId) throws Exception;
	
	/** 
	 * Description :  获取子公司的统计信息(认证资质数，成功案例数，已有联系人数，客户评价次数，客户评价总分，好评次数，被投诉次数，被举报次数，违规记录数)
	 * Create Date: 2011-8-18下午04:21:40 by likg  Modified Date: 2011-8-18下午04:21:40 by likg
	 * @param   parentOrgInfoId:总公司的机构Id
	 * @return  
	 * @Exception   
	 */
	public List<Object[]> getSubCompanyStatisticsInfo(String parentOrgInfoId) throws Exception;
	
}
