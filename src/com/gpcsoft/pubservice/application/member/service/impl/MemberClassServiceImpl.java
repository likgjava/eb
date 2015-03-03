package com.gpcsoft.pubservice.application.member.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.bizplatform.base.common.util.StringUtils;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.pubservice.application.member.domain.MemberClass;
import com.gpcsoft.pubservice.application.member.manager.MemberClassManager;
import com.gpcsoft.pubservice.application.member.service.MemberClassService;

@Service 
public class MemberClassServiceImpl extends BaseGenericServiceImpl<MemberClass> implements MemberClassService {

	@Autowired(required=true) @Qualifier("memberClassManagerImpl")
	private MemberClassManager memberClassManager;
	
	/** 
	 * Description :  新增/修改会员级别
	 * Create Date: 2011-3-23上午10:16:07 by likg  Modified Date: 2011-3-23上午10:16:07 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void saveMemberClass(MemberClass memberClass) throws Exception {
		//新增会员级别
		if(!StringUtils.hasLength(memberClass.getObjId())) {
			memberClass.setCreateUser(AuthenticationHelper.getCurrentUser(true));
			memberClass.setCreateTime(new Date());
		}
		
		memberClassManager.save(memberClass);
	}
}
