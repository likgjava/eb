package com.gpcsoft.epp.expertrule.manager;

import java.util.List;

import com.gpcsoft.bizplatform.base.purcategory.domain.PurCategory;
import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.epp.expertrule.domain.TakeExpertRule;
import com.gpcsoft.eprocurement.taker.provider.CodePO;

public interface TakeManager extends BaseManager<TakeExpertRule> {
	/** 
	 * Description :  获取信息[来源：本地]
	 * Create Date: 2010-8-30下午04:31:01 by yangx  Modified Date: 2010-8-30下午04:31:01 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<CodePO> getInfoForFile(String infoType);
	/** 
	 * Description :  获取品目信息[来源：本地]
	 * Create Date: 2010-8-30下午04:43:33 by yangx  Modified Date: 2010-8-30下午04:43:33 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<PurCategory> getPurCategory();
	
	/** 
	 * Description :  根据单位名称进行模糊查询单位信息
	 * Create Date: 2010-8-23下午03:38:47 by yangx  Modified Date: 2010-8-23下午03:38:47 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<CodePO> getUnitByName(String unitName);
}
