package com.gpcsoft.agreement.management.manager.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.agreement.management.dao.AreaDao;
import com.gpcsoft.agreement.management.domain.Area;
import com.gpcsoft.agreement.management.manager.AreaManager;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;

@Repository
public class AreaManagerImpl extends BaseManagerImpl<Area> implements AreaManager {

	@Autowired(required=true)  @Qualifier("areaDaoHibernate")
	private AreaDao areaDaoHibernate;

	/* (non-Javadoc)
	 * @see com.gpcsoft.eps.agreement.management.manager.AreaManager#removeArea(java.lang.String)
	 */
	public Integer removeArea(String areaId) {
		return areaDaoHibernate.removeArea(areaId);
	}

	/* (non-Javadoc)
	 * @see com.gpcsoft.eps.agreement.management.manager.AreaManager#getAreaInfo(java.util.Map)
	 */
	public Map<String, Object> getAreaInfo(Map<String, Object> params) {
		return areaDaoHibernate.getAreaInfo(params);
	}

	public Object getAreaTreeById(Map<String, Object> params) {
		return areaDaoHibernate.getAreaTreeById(params);
	}

	/** 
	 * Description :  根据是否有效取到区间和区域信息
	 * Create Date: 2011-12-1下午02:24:38 by yucy  Modified Date: 2011-12-1下午02:24:38 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<Object[]> getAreaList(String isValid) throws Exception {
		return areaDaoHibernate.getAreaList(isValid);
	}

	/** 
	 * Description :  保存区间（批量）
	 * Create Date: 2011-12-2上午11:39:01 by yucy  Modified Date: 2011-12-2上午11:39:01 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Boolean saveAreaBatch(Map<String, Object> param) throws Exception {
		return areaDaoHibernate.saveAreaBatch(param);
	}
}
