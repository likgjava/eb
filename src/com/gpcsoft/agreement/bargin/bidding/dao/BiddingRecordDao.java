package com.gpcsoft.agreement.bargin.bidding.dao;

import java.util.List;
import java.util.Map;

import com.gpcsoft.agreement.bargin.bidding.domain.BiddingRecord;
import com.gpcsoft.core.dao.BaseGenericDao;

public interface BiddingRecordDao extends BaseGenericDao<BiddingRecord> {
	
	/** 
	 * Description :  根据项目ID删除报价信息
	 * Create Date: 2010-9-28上午11:15:50 by sunl  Modified Date: 2010-9-28上午11:15:50 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Integer removeBiddingRecordByProjId(String projId) throws Exception;
	
	/** 
	 * Description :  根据项目ID获取报价信息(最低报价)
	 * Create Date: 2010-9-28上午11:15:50 by sunl  Modified Date: 2010-9-28上午11:15:50 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<BiddingRecord> getBiddingRecordByProjectId_allInfo(String objId,String turnId) throws Exception;
	
	/** 
	 * Description :  根据项目ID和供应商ID获取供应商的报价信息
	 * Create Date: 2010-9-28上午11:15:50 by sunl  Modified Date: 2010-9-28上午11:15:50 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public BiddingRecord getSupplierBiddingLastRecords(String projId,String supplierId,String turnId) throws Exception;
	
	/** 
	 * Description :  根据项目ID、供应商ID、轮次ID 获取供应商的报价历史
	 * Create Date: 2010-9-28上午11:15:50 by sunl  Modified Date: 2010-9-28上午11:15:50 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<BiddingRecord> getSupplierBiddingHistory(String projId,String supplierId,String turnId) throws Exception;
	
	/** 
	 * Description :  根据项目ID获取最高、最低、平均报价
	 * Create Date: 2010-9-28上午11:15:50 by sunl  Modified Date: 2010-9-28上午11:15:50 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<Object> getMaxMinAvgPriceByProjId(String objId) throws Exception;

	/** 
	 * Description :  取得每个供应商最低报价 (对象)
	 * Create Date: 2010-10-21下午09:23:44 by yucy  Modified Date: 2010-10-21下午09:23:44 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<BiddingRecord> getMinBiddingRecordByProjectId(String objId,String turnId ,String supplierId) throws Exception;
	
	
	/** 
	 * Description :  取得每个供应商最低议价报价 (对象)
	 * Create Date: 2010-10-21下午09:23:44 by yucy  Modified Date: 2010-10-21下午09:23:44 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<BiddingRecord> getMinBiddingRecordByTalkProjectId(String objId,String turnId ,String supplierId) throws Exception;
	
	/**
	 * Description :  取得单个供应商项目的最低报价
	 * Create Date: 2010-10-22上午11:05:46 by yucy  Modified Date: 2010-10-22上午11:05:46 by yucy
	 * @param   
	 * @return  
	 * @Exception
	 */
	public BiddingRecord getMinBiddingRecordByProjectAndSupplier( Map<String, Object> param) throws Exception;
	
	/** 
	 * Description :  根据轮次ID和项目ID获得报价排名
	 * Create Date: 2010-9-28上午11:15:50 by sunl  Modified Date: 2010-9-28上午11:15:50 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Map<String, Object> getSelfRanking(String projId,String turnId) throws Exception;
	
	/** 
	 * Description :  获取轮次的最低报价
	 * Create Date: 2010-9-28上午11:15:50 by sunl  Modified Date: 2010-9-28上午11:15:50 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Map<String, Object> getMinTurnBargainPrice(String projId,String turnId) throws Exception;

}
