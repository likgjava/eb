package com.gpcsoft.epp.taskplan.service.impl;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.gpcsoft.epp.taskplan.manager.ProjMemberManager;
import com.gpcsoft.epp.taskplan.service.ProjMemberService;
import com.gpcsoft.epp.taskplan.domain.ProjMember;

@Service 
public class ProjMemberServiceImpl extends BaseGenericServiceImpl<ProjMember> implements ProjMemberService {

	@Autowired(required=true) @Qualifier("projMemberManagerImpl")
	private ProjMemberManager projMemberManager;

}
