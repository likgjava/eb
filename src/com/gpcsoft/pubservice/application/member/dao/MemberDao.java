package com.gpcsoft.pubservice.application.member.dao;

import java.util.Map;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.pubservice.application.member.domain.Member;

public interface MemberDao extends BaseGenericDao<Member> {
	
	/** 
	 * Description :  根据机构ID获取会员信息
	 * Create Date: 2011-3-30下午03:26:39 by sunl  Modified Date: 2011-3-30下午03:26:39 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Member getMemberByOrgInfoId(Map<String,Object> params) throws Exception;
}
