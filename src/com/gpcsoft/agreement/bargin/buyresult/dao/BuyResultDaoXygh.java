package com.gpcsoft.agreement.bargin.buyresult.dao;

import java.util.List;
import java.util.Map;

import com.gpcsoft.agreement.bargin.bidding.domain.BiddingRecordItem;
import com.gpcsoft.epp.buyresult.dao.BuyResultDao;
import com.gpcsoft.epp.buyresult.domain.BuyResult;

public interface BuyResultDaoXygh extends BuyResultDao{

	/** 
	 * Description :  供应商轮次报价数据
	 * Create Date: 2010-10-14下午03:23:27 by yucy  Modified Date: 2010-10-14下午03:23:27 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Map<String,Object> getSupplierBargainByTurn(Map<String, Object> param) throws Exception;

	/** 
	 * Description :  取得成交结果
	 * Create Date: 2010-10-15下午02:22:51 by yucy  Modified Date: 2010-10-15下午02:22:51 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	BuyResult getBuyResultByProject(Map<String, Object> param) throws Exception;

	/** 
	 * Description :  获得供应商单轮报价的数据
	 * Create Date: 2010-10-18上午10:54:18 by yucy  Modified Date: 2010-10-18上午10:54:18 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<Object[]> getSingleTurnRecordDate(Map<String, Object> param) throws Exception;

	/** 
	 * Description :  取得报价记录明细by报价记录
	 * Create Date: 2010-10-18上午11:27:24 by yucy  Modified Date: 2010-10-18上午11:27:24 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<BiddingRecordItem> getRecordItemsByRecord(Map<String, Object> param) throws Exception;

}
