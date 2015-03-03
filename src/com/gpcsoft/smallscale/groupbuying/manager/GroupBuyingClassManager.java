package com.gpcsoft.smallscale.groupbuying.manager;

import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.smallscale.groupbuying.domain.GroupBuyingClass;

public interface GroupBuyingClassManager extends BaseManager<GroupBuyingClass> {

	/** 
	 * Description :  获取最小排序序号
	 * Create Date: 2011-6-28上午09:34:16 by likg  Modified Date: 2011-6-28上午09:34:16 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Long getMinSort() throws Exception;

	/** 
	 * Description :  修改团购分类的排序序号
	 * Create Date: 2011-5-16下午04:20:32 by likg  Modified Date: 2011-5-16下午04:20:32 by likg
	 * @param   objId:要排序的团购分类的Id	isToUp:排序方向
	 * @return  
	 * @Exception   
	 */
	void updateSort(String objId, boolean isToUp) throws Exception;

}
