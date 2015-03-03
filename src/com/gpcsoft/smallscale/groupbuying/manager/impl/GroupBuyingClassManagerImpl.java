package com.gpcsoft.smallscale.groupbuying.manager.impl;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.smallscale.groupbuying.dao.GroupBuyingClassDao;
import com.gpcsoft.smallscale.groupbuying.manager.GroupBuyingClassManager;
import com.gpcsoft.smallscale.groupbuying.domain.GroupBuyingClass;

@Repository
public class GroupBuyingClassManagerImpl extends BaseManagerImpl<GroupBuyingClass> implements GroupBuyingClassManager {

	@Autowired(required=true)  @Qualifier("groupBuyingClassDaoHibernate")
	private GroupBuyingClassDao groupBuyingClassDaoHibernate;

	/** 
	 * Description :  获取最小排序序号
	 * Create Date: 2011-6-28上午09:34:16 by likg  Modified Date: 2011-6-28上午09:34:16 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Long getMinSort() throws Exception {
		return groupBuyingClassDaoHibernate.getMinSort();
	}

	/** 
	 * Description :  修改团购分类的排序序号
	 * Create Date: 2011-5-16下午04:20:32 by likg  Modified Date: 2011-5-16下午04:20:32 by likg
	 * @param   objId:要排序的团购分类的Id	isToUp:排序方向
	 * @return  
	 * @Exception   
	 */
	public void updateSort(String objId, boolean isToUp) throws Exception {
		groupBuyingClassDaoHibernate.updateSort(objId, isToUp);
	}

}
