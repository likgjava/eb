package com.gpcsoft.smallscale.groupbuying.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.StringUtils;
import com.gpcsoft.smallscale.groupbuying.dao.GroupBuyingClassDao;
import com.gpcsoft.smallscale.groupbuying.domain.GroupBuyingClass;
import com.gpcsoft.smallscale.groupbuying.manager.GroupBuyingClassManager;
import com.gpcsoft.smallscale.groupbuying.service.GroupBuyingClassService;

@Service 
public class GroupBuyingClassServiceImpl extends BaseGenericServiceImpl<GroupBuyingClass> implements GroupBuyingClassService {

	@Autowired(required=true) @Qualifier("groupBuyingClassManagerImpl")
	private GroupBuyingClassManager groupBuyingClassManager;
	
	@Autowired(required=true) @Qualifier("groupBuyingClassDaoHibernate")
	private GroupBuyingClassDao groupBuyingClassDaoHibernate;

	/** 
	 * Description :  保存团购分类信息
	 * Create Date: 2011-6-28上午09:34:16 by likg  Modified Date: 2011-6-28上午09:34:16 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void saveGroupBuyingClass(GroupBuyingClass groupBuyingClass) throws Exception {
		if(!StringUtils.hasLength(groupBuyingClass.getObjId())) {
			groupBuyingClass.setSort(groupBuyingClassManager.getMinSort()-1);
		}
		
		groupBuyingClassManager.save(groupBuyingClass);
	}

	/** 
	 * Description :  获取最小排序序号
	 * Create Date: 2011-6-28上午09:34:16 by likg  Modified Date: 2011-6-28上午09:34:16 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Long getMinSort() throws Exception {
		return groupBuyingClassManager.getMinSort();
	}

	/** 
	 * Description :  修改团购分类的排序序号
	 * Create Date: 2011-5-16下午04:20:32 by likg  Modified Date: 2011-5-16下午04:20:32 by likg
	 * @param   objId:要排序的团购分类的Id	isToUp:排序方向
	 * @return  
	 * @Exception   
	 */
	public void updateSort(String objId, boolean isToUp) throws Exception {
		groupBuyingClassManager.updateSort(objId, isToUp);
	}

	/** 
	 * Description :  获取团购分组
	 * Create Date: 2011-11-10下午04:46:04 by yucy  Modified Date: 2011-11-10下午04:46:04 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<GroupBuyingClass> getBuyingClassList() throws Exception {
		return groupBuyingClassDaoHibernate.getBuyingClassList();
	}

}
