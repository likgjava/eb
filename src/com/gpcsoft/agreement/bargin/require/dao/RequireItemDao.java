package com.gpcsoft.agreement.bargin.require.dao;

import java.util.List;

import com.gpcsoft.agreement.bargin.require.domain.RequireItem;
import com.gpcsoft.core.dao.BaseGenericDao;

public interface RequireItemDao extends BaseGenericDao<RequireItem> {
	/** 
	 * Description :  根据项目ID获取需求条目信息
	 * Create Date: 2010-9-28上午11:15:50 by sunl  Modified Date: 2010-9-28上午11:15:50 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<RequireItem> getRequireItemsByProjId(String projId) throws Exception;
	
	/** 
	 * Description :  根据项目ID获取需求条目信息
	 * Create Date: 2010-9-28上午11:15:50 by sunl  Modified Date: 2010-9-28上午11:15:50 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Integer getRequireItemCount(String projId) throws Exception;
	
	/** 
	 * Description :  根据项目ID删除需求条目信息
	 * Create Date: 2010-9-28上午11:15:50 by sunl  Modified Date: 2010-9-28上午11:15:50 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Integer removeRequireItemByProjId(String projId) throws Exception;
	
	/** 
	 * Description :  需求条目里商品数量
	 * Create Date: 2010-9-28上午11:15:50 by sunl  Modified Date: 2010-9-28上午11:15:50 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Integer isGoodsOrDesc(String projId) throws Exception;
}
