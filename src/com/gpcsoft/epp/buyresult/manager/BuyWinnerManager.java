package com.gpcsoft.epp.buyresult.manager;

import java.util.List;

import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.epp.buyresult.domain.BuyWinner;

public interface BuyWinnerManager extends BaseManager<BuyWinner> {
	
	/** 
	 * Description :  获取某标段的中标结果集合
	 * Create Date: 2010-6-28下午12:58:08 by wangcl  Modified Date: 2010-6-28下午12:58:08 by wangcl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<BuyWinner> getListByResultId(String resultId);

}
