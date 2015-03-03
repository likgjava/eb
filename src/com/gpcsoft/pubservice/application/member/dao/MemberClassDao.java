package com.gpcsoft.pubservice.application.member.dao;

import java.util.Map;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.pubservice.application.member.domain.MemberClass;

public interface MemberClassDao extends BaseGenericDao<MemberClass> {

	/** 
	 * Description :  获得初始普通会员级别
	 * Create Date: 2011-3-30下午03:53:20 by likg  Modified Date: 2011-3-30下午03:53:20 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public MemberClass getInitMemberClass() throws Exception;
	
	/** 
	 * Description :  根据总缴费金额返回级别（最最大级别）
	 * Create Date: 2011-3-23上午10:16:07 by sunl  Modified Date: 2011-3-23上午10:16:07 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public MemberClass getMemberClassByDurationAndFee(Map<String,Object> params) throws Exception;
}
