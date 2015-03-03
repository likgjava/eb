package com.gpcsoft.bizplatform.organization.service;
import java.util.List;

import com.gpcsoft.bizplatform.organization.domain.QualificationDetail;
import com.gpcsoft.core.service.BaseGenericService;

public interface QualificationDetailService extends BaseGenericService<QualificationDetail> {

	/** 
	 * Description :  保存或修改企业的财务信息
	 * Create Date: 2010-11-15下午03:15:45 by guoyr  Modified Date: 2010-11-15下午03:15:45 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void saveOrUpdateQualificationDetail(String financeInfoJSONString);
	
	/** 
	 * Description :  根据资质分类code和机构id获取信息(财务信息和法务信息用的)
	 * Create Date: 2011-8-19下午02:18:57 by yucy  Modified Date: 2011-8-19下午02:18:57 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<QualificationDetail> getQualificationDetailListByCodeAndOrgId(String code ,String orgId) throws Exception ;

}
