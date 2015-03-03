package com.gpcsoft.pubservice.application.member.manager.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.pubservice.application.member.dao.MemberClassDao;
import com.gpcsoft.pubservice.application.member.domain.MemberClass;
import com.gpcsoft.pubservice.application.member.manager.MemberClassManager;

@Repository
public class MemberClassManagerImpl extends BaseManagerImpl<MemberClass> implements MemberClassManager {

	@Autowired(required=true)  @Qualifier("memberClassDaoHibernate")
	private MemberClassDao memberClassDaoHibernate;
	
	/** 
	 * Description :  获得初始普通会员级别
	 * Create Date: 2011-3-30下午03:53:20 by likg  Modified Date: 2011-3-30下午03:53:20 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public MemberClass getInitMemberClass() throws Exception {
		return memberClassDaoHibernate.getInitMemberClass();
	}

	/** 
	 * Description :  根据总缴费金额返回级别（最最大级别）
	 * Create Date: 2011-3-23上午10:16:07 by sunl  Modified Date: 2011-3-23上午10:16:07 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public MemberClass getMemberClassByDurationAndFee(Map<String,Object> params) throws Exception {
		return memberClassDaoHibernate.getMemberClassByDurationAndFee(params);
	}
}
