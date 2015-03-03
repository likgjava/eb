package com.gpcsoft.pubservice.application.member.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.pubservice.application.member.domain.Member;
import com.gpcsoft.pubservice.application.member.manager.MemberManager;
import com.gpcsoft.pubservice.application.member.service.MemberService;

@Service 
public class MemberServiceImpl extends BaseGenericServiceImpl<Member> implements MemberService {

	@Autowired(required=true) @Qualifier("memberManagerImpl")
	private MemberManager memberManager;

	/** 
	 * Description :  初始化会员数据
	 * Create Date: 2011-3-30下午03:26:39 by sunl  Modified Date: 2011-3-30下午03:26:39 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void initMemberInfo(Map<String,Object> params) throws Exception {
		memberManager.initMemberInfo(params);
	}
	
	/** 
	 * Description :  根据机构ID获取会员信息
	 * 			      如果该机构不是会员，手动返回一个会员级别为0级的会员信息
	 * Create Date: 2011-3-30下午03:26:39 by sunl  Modified Date: 2011-3-30下午03:26:39 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Member getMemberByOrgInfoId(Map<String,Object> params) throws Exception {
		return memberManager.getMemberByOrgInfoId(params);
	}
}
