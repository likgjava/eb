package com.gpcsoft.bizplatform.organization.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.gpcsoft.bizplatform.organization.domain.OrgInfoModify;
import com.gpcsoft.bizplatform.organization.manager.OrgInfoModifyManager;
import com.gpcsoft.bizplatform.organization.service.OrgInfoModifyService;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;

@Service 
public class OrgInfoModifyServiceImpl extends BaseGenericServiceImpl<OrgInfoModify> implements OrgInfoModifyService {

	@Autowired(required=true) @Qualifier("orgInfoModifyManagerImpl")
	private OrgInfoModifyManager orgInfoModifyManager;
	
	/** 
	 * Description :  根据机构ID获取变更的记录
	 * Create Date: 2010-7-28上午09:39:54 by sunl  Modified Date: 2010-7-28上午09:39:54 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<OrgInfoModify> getOrgInfoModifyList(String orgId) throws Exception {
		return orgInfoModifyManager.getOrgInfoModifyList(orgId);
	}
	
	/** 
	 * Description :  获取变更的机构
	 * Create Date: 2010-7-28上午09:39:54 by sunl  Modified Date: 2010-7-28上午09:39:54 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public List<OrgInfoModify> getOrgInfoAuditList(String orgId) throws Exception {
		List<OrgInfoModify> modifyList = orgInfoModifyManager.getOrgInfoAuditList(orgId);
		
		List<OrgInfoModify> resList = new ArrayList<OrgInfoModify>();
		
		if(StringUtils.hasLength(orgId)) {
			resList = modifyList;
		} else {
			String groupOrgId = "";
			for (OrgInfoModify orgInfoModify : modifyList) {
				if(!groupOrgId.equals(orgInfoModify.getOrgInfo().getObjId())) {
					groupOrgId = orgInfoModify.getOrgInfo().getObjId();
					resList.add(orgInfoModify);
				}
			}
		}
		
		return resList;
	}
}
