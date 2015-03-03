package com.gpcsoft.bizplatform.organization.dao;

import java.util.List;

import com.gpcsoft.bizplatform.organization.domain.QualificationDetail;
import com.gpcsoft.core.dao.BaseGenericDao;

public interface QualificationDetailDao extends BaseGenericDao<QualificationDetail> {

	/** 
	 * Description : 修改企业的资质信息 
	 * Create Date: 2010-11-15下午03:09:51 by guoyr  Modified Date: 2010-11-15下午03:09:51 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Integer updateQualificationDetail(final String objId, final String paramValue);

	/** 
	 * Description :  根据资质分类code和机构id获取信息(财务信息和法务信息用的)
	 * Create Date: 2011-8-19下午02:18:57 by yucy  Modified Date: 2011-8-19下午02:18:57 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<QualificationDetail> getQualificationDetailListByCodeAndOrgId(String code, String orgId) throws Exception;
}
