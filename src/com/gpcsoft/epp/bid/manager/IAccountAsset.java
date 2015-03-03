package com.gpcsoft.epp.bid.manager;

import java.math.BigDecimal;
import java.util.List;

public interface IAccountAsset {
	/** 
	 * Description :  查询该企业资金
	 * Create Date: 2010-7-21上午10:19:52 by liyc  Modified Date: 2011-03-31上午10:19:52 by liyc
	 * @param   
	 * @return  
	 * @Exception   
	 */
	BigDecimal getAccountAsset(String userId,String orginfoId);
	
	/** 
	 * Description :  根据流水账号查询支付
	 * Create Date: 2010-7-21上午10:19:52 by liyc  Modified Date: 2011-12-13上午10:19:52 by liqy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List getAccountAssetByPayNo(String paymentNo);
	
	/** 
	 * Description :  冻结资金[验证可用资金是否够用，够用则冻结]
	 * Create Date: 2011-12-20上午10:19:52 by liqy  Modified Date: 2011-12-20上午10:19:52 by liqy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean saveAccountForFreeze(String userId,String orgInfoId,String amount);
	
	/** 
	 * Description :  解冻已经冻结的资金
	 * Create Date: 2011-12-20上午10:19:52 by liqy  Modified Date: 2011-12-20上午10:19:52 by liqy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean saveAccountForUnfreeze(String userId,String orgInfoId,String amount);

}
