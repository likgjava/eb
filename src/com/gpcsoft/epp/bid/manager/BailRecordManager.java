package com.gpcsoft.epp.bid.manager;

import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.epp.bid.domain.BailRecord;

public interface BailRecordManager extends BaseManager<BailRecord> {
	
	 /**
	   * Funcname:checkBailStatusForSupllier
	   * Description : 保证金记录对供应商参与招标项目的影响(保证金记录状态00：未收；01：已收；02：已退)
	   * @Create Date: 2010-8-11下午02:08:50 
	   * @author shenjianzhuang  
	   * @Modified Date: 2010-8-11下午02:08:50 
	   * @author  shenjianzhuang
	   * @return  boolean
	   * @Exception 
	   */
	  public boolean checkBailStatusForSupllier(String  bailRecordId);
}
