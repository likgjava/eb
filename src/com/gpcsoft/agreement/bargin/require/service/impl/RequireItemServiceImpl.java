package com.gpcsoft.agreement.bargin.require.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.agreement.bargin.require.dao.RequireItemDao;
import com.gpcsoft.agreement.bargin.require.domain.RequireItem;
import com.gpcsoft.agreement.bargin.require.service.RequireItemService;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;

@Service 
public class RequireItemServiceImpl extends BaseGenericServiceImpl<RequireItem> implements RequireItemService {
	
	@Autowired(required=true) @Qualifier("requireItemDaoHibernate")
	private RequireItemDao requireItemDaoHibernate;
	
	/** 
	 * Description :  根据项目ID获取需求条目信息
	 * Create Date: 2010-9-28上午11:15:50 by sunl  Modified Date: 2010-9-28上午11:15:50 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<RequireItem> getRequireItemsByProjId(String projId) throws Exception {
		return requireItemDaoHibernate.getRequireItemsByProjId(projId);
	}
	
	/** 
	 * Description :  根据项目ID获取需求条目信息
	 * Create Date: 2010-9-28上午11:15:50 by sunl  Modified Date: 2010-9-28上午11:15:50 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Integer getRequireItemCount(String projId) throws Exception {
		return requireItemDaoHibernate.getRequireItemCount(projId);
	}
	
	/** 
	 * Description :  根据项目ID删除需求条目信息
	 * Create Date: 2010-9-28上午11:15:50 by sunl  Modified Date: 2010-9-28上午11:15:50 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Integer removeRequireItemByProjId(String projId) throws Exception {
		return requireItemDaoHibernate.removeRequireItemByProjId(projId);
	}
	
	/** 
	 * Description :  需求条目里商品数量
	 * Create Date: 2010-9-28上午11:15:50 by sunl  Modified Date: 2010-9-28上午11:15:50 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Integer isGoodsOrDesc(String projId) throws Exception {
		return requireItemDaoHibernate.isGoodsOrDesc(projId);
	}
}
