package com.gpcsoft.agreement.order.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.agreement.order.domain.ProtaskItem;
import com.gpcsoft.agreement.order.manager.ProtaskItemManager;
import com.gpcsoft.agreement.order.service.ProtaskItemService;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;

@Service 
public class ProtaskItemServiceImpl extends BaseGenericServiceImpl<ProtaskItem> implements ProtaskItemService {

	@Autowired(required=true) @Qualifier("protaskItemManagerImpl")
	private ProtaskItemManager protaskItemManager;

	/**
	 * 获取任务单条目
	 * Description :  
	 * Create Date: 2010-4-13下午04:13:15 by sunl  Modified Date: 2010-4-13下午04:13:15 by sunl
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<ProtaskItem> getProtaskItemList(String objIds) throws Exception {
		return protaskItemManager.getProtaskItemList(objIds);
	}
}
