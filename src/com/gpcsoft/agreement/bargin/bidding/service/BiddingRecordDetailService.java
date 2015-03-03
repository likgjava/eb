package com.gpcsoft.agreement.bargin.bidding.service;
import java.util.List;
import java.util.Map;

import com.gpcsoft.agreement.bargin.bidding.domain.BiddingRecordDetail;
import com.gpcsoft.agreement.bargin.project.domain.BargainTurn;
import com.gpcsoft.core.service.BaseGenericService;

public interface BiddingRecordDetailService extends BaseGenericService<BiddingRecordDetail> {

	/** 
	 * Description : 获取最低报价  
	 * Create Date: 2011-5-17下午12:05:37 by yucy  Modified Date: 2011-5-17下午12:05:37 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<Object[]> getMinRecord(String objId) throws Exception;
	
	/** 
	 * Description : 获取各供应商的报价,按需求
	 * Create Date: 2011-5-16下午06:39:08 by yucy  Modified Date: 2011-5-16下午06:39:08 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<Object[]> loadRecordByRequire(String requireId ,Map<String, Object> param) throws Exception;
	
	/** 
	 * Description :  获取供应商对需求的所有报价
	 * Create Date: 2011-5-20下午02:36:03 by yucy  Modified Date: 2011-5-20下午02:36:03 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<Object[]> getDetailBySupplierAndRequire(String supplierId, String requireId) throws Exception;

	/** 
	 * Description : 更新选中状态 
	 * Create Date: 2011-5-17下午06:43:12 by yucy  Modified Date: 2011-5-17下午06:43:12 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<Object[]> updateRecordChooseStatus(String changedIdandValue ,String requireId) throws Exception;

	/** 
	 * Description :  保存排序
	 * Create Date: 2011-5-24下午02:31:18 by yucy  Modified Date: 2011-5-24下午02:31:18 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Boolean saveSort(String[] orderArray) throws Exception;

	/**
	 * Description :  获取我报过的最低价
	 * Create Date: 2011-5-26上午10:39:06 by yucy  Modified Date: 2011-5-26上午10:39:06 by yucy
	 * @param   
	 * @return  
	 * @Exception
	 */
	List<Object[]> getMyMinRecord(String projectId,String orgId) throws Exception;
	
	/** 
	 * Description :  保存报价信息[保存报价和保存历史]
	 * Create Date: 2011-5-26下午12:49:57 by sunl  Modified Date: 2011-5-26下午12:49:57 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void saveBargain(BiddingRecordDetail bargainDetail,BargainTurn bargainTurn) throws Exception;
	
	/** 
	 * Description :  重新报价[更新报价和更新历史报价]
	 * Create Date: 2011-5-26下午12:49:57 by sunl  Modified Date: 2011-5-26下午12:49:57 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void updateBargain(Map<String,Object> params) throws Exception;
	
	/** 
	 * Description :  根据项目id、需求条目id、供应商id查询报价
	 * Create Date: 2011-5-26下午03:08:08 by sunl  Modified Date: 2011-5-26下午03:08:08 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<Object[]> getBiddingDetailList(Map<String,Object> params) throws Exception;
	
	/** 
	 * Description :  获取我的排名
	 * Create Date: 2010-9-28上午11:15:50 by sunl  Modified Date: 2010-9-28上午11:15:50 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<Object[]> getMyRanking(String projId,String requireId,String supplierId) throws Exception;
	
	/** 
	 * Description :  需求条目最低报价[根据项目、供应商、需求条目]
	 * Create Date: 2010-9-28上午11:15:50 by sunl  Modified Date: 2010-9-28上午11:15:50 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<Object[]> getRequireMinPrice(String projId,String requireId,String supplierId) throws Exception;

	/** 
	 * Description :  获取最低报价(确认成交供应商)
	 * Create Date: 2011-6-1上午09:55:47 by yucy  Modified Date: 2011-6-1上午09:55:47 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<Object[]> getMinRecordAndDetail(String projectId) throws Exception;

	/** 
	 * Description : 获取中标供应商的最低报价或选中的报价
	 * Create Date: 2011-6-13下午01:30:48 by yucy  Modified Date: 2011-6-13下午01:30:48 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<Object[]> getMinOrSelectRecordDetail(String projectId, String dealSupplier) throws Exception;

	/**
	 * 
	 * Description :  更新为成交报价
	 * Create Date: 2011-6-13下午04:17:00 by yucy  Modified Date: 2011-6-13下午04:17:00 by yucy
	 * @param   
	 * @return  
	 * @Exception
	 */
	List<BiddingRecordDetail> updateRecordDetailForResult(String detailIds) throws Exception;

	/** 
	 * Description :  获取成交报价条目(按供应商)
	 * Create Date: 2011-6-14上午10:49:12 by yucy  Modified Date: 2011-6-14上午10:49:12 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<BiddingRecordDetail> getDealRecordDetail(Map<String, Object> param) throws Exception;

	/** 
	 * Description :  获取供应商报价排名显示FusionChart的XML数据
	 * Create Date: 2011-7-1上午09:59:34 by likg  Modified Date: 2011-7-1上午09:59:34 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	String getBiddingRankChartXml(Map<String, Object> param) throws Exception;

	/** 
	 * Description :  获取供应商报价排名数据
	 * Create Date: 2011-7-1上午09:42:38 by likg  Modified Date: 2011-7-1上午09:42:38 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Map<String, Object> getBiddingRankData(Map<String, Object> param) throws Exception;
} 
