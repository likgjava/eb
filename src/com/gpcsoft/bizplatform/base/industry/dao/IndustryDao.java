package com.gpcsoft.bizplatform.base.industry.dao;

import java.util.List;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.bizplatform.base.industry.domain.Industry;

public interface IndustryDao extends BaseGenericDao<Industry> {
	
	/** 
	 * Description :  修改行业是否为叶子结点 
	 * Create Date: 2010-11-16下午01:38:28 by liangxj  Modified Date: 2010-11-16下午01:38:28 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void updateIndustryIsLeaf(String objId,boolean isleaf);
	
	
	/** 
	 * Description :  查询当前行业的父结点的子结点的个数 
	 * Create Date: 2010-11-16下午01:38:35 by liangxj  Modified Date: 2010-11-16下午01:38:35 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Long getSubIndustryCount(String objId);

	/** 
	 * Description :  获得X级行业
	 * Create Date: 2011-11-8上午11:04:45 by yucy  Modified Date: 2011-11-8上午11:04:45 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<Industry> getIndustryByLevel(Short level) throws Exception;
}
