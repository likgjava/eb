package com.gpcsoft.agreement.bargin.buyresult.dao;

import java.util.List;
import java.util.Map;

import com.gpcsoft.epp.buyresult.dao.BuyWinnerDao;
import com.gpcsoft.epp.buyresult.domain.BuyWinner;

public interface BuyWinnerDaoXygh extends BuyWinnerDao{

	/** 
	 * Description :  取得供应商该项目的成交结果
	 * Create Date: 2010-10-27上午11:53:28 by yucy  Modified Date: 2010-10-27上午11:53:28 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<BuyWinner> getBuyWinnerList(Map<String, Object> param) throws Exception;

}
