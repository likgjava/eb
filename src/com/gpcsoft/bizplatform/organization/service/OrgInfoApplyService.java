package com.gpcsoft.bizplatform.organization.service;
import java.util.List;
import java.util.Map;

import com.gpcsoft.bizplatform.organization.domain.OrgInfoApply;
import com.gpcsoft.core.service.BaseGenericService;

public interface OrgInfoApplyService extends BaseGenericService<OrgInfoApply> {

	/** 
	 * Description :  获取申请的机构角色
	 * Create Date: 2010-7-28上午09:39:54 by sunl  Modified Date: 2010-7-28上午09:39:54 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<OrgInfoApply> getApplyOrgList(String orgId,String applyType) throws Exception;
	
	/** 
	 * Description :  申请审核
	 * Create Date: 2010-7-28上午09:39:54 by sunl  Modified Date: 2010-7-28上午09:39:54 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void auditApplyOrg(Map<String,Object> params) throws Exception;
}
