package com.gpcsoft.pubservice.application.member.service;
import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.pubservice.application.member.domain.MemberClass;

public interface MemberClassService extends BaseGenericService<MemberClass> {

	/** 
	 * Description :  新增/修改会员级别
	 * Create Date: 2011-3-23上午10:16:07 by likg  Modified Date: 2011-3-23上午10:16:07 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	void saveMemberClass(MemberClass memberClass) throws Exception;
	
}
