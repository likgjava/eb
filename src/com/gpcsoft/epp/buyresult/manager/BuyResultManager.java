package com.gpcsoft.epp.buyresult.manager;

import java.util.List;

import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.epp.buyresult.domain.BuyResult;

public interface BuyResultManager extends BaseManager<BuyResult> {
	/**
	 * 
	 * Description :通过子项目主键得到成交结果对象  
	 * Create Date: 2010-6-26下午04:55:33 by liuke  Modified Date: 2010-6-26下午04:55:33 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public BuyResult getBuyResultBySubProjId(String subProjId);
	
	/** 
	 * Description :  根据项目id获取中标结果记录
	 * Create Date: 2010-6-28上午10:26:23 by wangcl  Modified Date: 2010-6-28上午10:26:23 by wangcl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<BuyResult> getListByProjectId(String projectId);
	
}
