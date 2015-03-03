package com.gpcsoft.pubservice.application.member.manager;

import java.util.Map;

import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.pubservice.application.member.domain.Member;

public interface MemberManager extends BaseManager<Member> {

	/** 
	 * Description :  同步会员信息
	 * Create Date: 2010-7-28上午09:39:54 by sunl  Modified Date: 2010-7-28上午09:39:54 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void syncMemberInfo(Map<String,Object> params) throws Exception; 
	
	/** 
	 * Description :  初始化会员数据
	 * Create Date: 2011-3-30下午03:26:39 by sunl  Modified Date: 2011-3-30下午03:26:39 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void initMemberInfo(Map<String,Object> params) throws Exception;
	
	/** 
	 * Description :  根据机构ID获取会员信息
	 * Create Date: 2011-3-30下午03:26:39 by sunl  Modified Date: 2011-3-30下午03:26:39 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Member getMemberByOrgInfoId(Map<String,Object> params) throws Exception;
}
