package com.gpcsoft.agreement.order.manager;

import java.util.List;

import com.gpcsoft.agreement.order.domain.ProtaskItem;
import com.gpcsoft.core.manager.BaseManager;

public interface ProtaskItemManager extends BaseManager<ProtaskItem> {
	/**
	 * 获取任务单条目
	 * Description :  
	 * Create Date: 2010-4-13下午04:13:15 by sunl  Modified Date: 2010-4-13下午04:13:15 by sunl
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<ProtaskItem> getProtaskItemList(String objIds) throws Exception;
	
	/**
	 * 获得当前用户未完成的任务书明细
	 * Description :  
	 * Create Date: 2010-5-10上午10:32:53 by liangxj  Modified Date: 2010-5-10上午10:32:53 by liangxj
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<ProtaskItem> getNotFinishProtaskItems();
	
	/**
	 * 获得指定采购人未完成的任务书明细
	 * Description :  
	 * Create Date: 2010-5-10上午10:32:53 by liangxj  Modified Date: 2010-5-10上午10:32:53 by liangxj
	 * @param   companyId 当前用户所在的company的Id
	 * @return  
	 * @Exception
	 */
	public List<ProtaskItem> getNotFinishProtaskItems(String companyId);
	
	/**
	 * 获得当前用户未完成的品目id和名称
	 * Description :  
	 * Create Date: 2010-5-10上午10:43:32 by liangxj  Modified Date: 2010-5-10上午10:43:32 by liangxj
	 * @param  如果没有数据，是否抛异常
	 * @return  二维数组，{"以逗号分割的id","以逗号分割的name"}
	 * @Exception
	 */
	public String[] getNotFinishProtaskCategory(boolean isException);
	
	/**
	 * 获得指定采购人未完成的品目id和名称
	 * Description :  
	 * Create Date: 2010-5-10上午10:43:32 by liangxj  Modified Date: 2010-5-10上午10:43:32 by liangxj
	 * @param   果没有数据，是否抛异常
	 * @return  二维数组，{"以逗号分割的id","以逗号分割的name"}
	 * @Exception
	 */
	public String[] getNotFinishProtaskCategory(String companyId,boolean isException);
}
