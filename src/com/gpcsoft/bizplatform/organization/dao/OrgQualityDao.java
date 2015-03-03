package com.gpcsoft.bizplatform.organization.dao;

import java.util.List;
import java.util.Map;

import com.gpcsoft.bizplatform.organization.domain.OrgQuality;
import com.gpcsoft.core.dao.BaseGenericDao;

public interface OrgQualityDao extends BaseGenericDao<OrgQuality> {

	/** 
	 * Description :  取得以保存的资质（组织内部对象,包括新添加的参数）
	 * Create Date: 2010-8-30上午11:37:11 by yucy  Modified Date: 2010-8-30上午11:37:11 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	Map<String, Object> getCreateOrUpdateOrgQuality(String parameter) throws Exception;

	/** 
	 * Description :  取得机构的资质(根据机构id)
	 * Create Date: 2009-4-8下午07:50:19 by yucy  Modified Date: 2009-4-8下午07:50:19 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	List<OrgQuality> getOrgQuality(String objId) throws Exception;
}
