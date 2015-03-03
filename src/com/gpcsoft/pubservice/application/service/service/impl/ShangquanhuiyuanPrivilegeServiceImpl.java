package com.gpcsoft.pubservice.application.service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.pubservice.application.service.domain.ServiceSubscribe;
import com.gpcsoft.pubservice.application.service.service.ServiceSubscribeService;
import com.gpcsoft.pubservice.application.service.service.ShangquanhuiyuanPrivilegeService;

@Service 
public class ShangquanhuiyuanPrivilegeServiceImpl extends BaseGenericServiceImpl<ServiceSubscribe> implements ShangquanhuiyuanPrivilegeService {

	@Autowired(required=true)  @Qualifier("serviceSubscribeServiceImpl")
	private ServiceSubscribeService serviceSubscribeService;
	
	/** 
	 * Description :  当前机构是否可以查看此机构的联系方式
	 * Create Date: 2011-2-12上午09:11:34 by yucy  Modified Date: 2011-2-12上午09:11:34 by yucy
	 * @param   currentOrgInfoId 当前机构ID
	 * @param   targetOrgInfoId  被查看机构ID
	 * @return  
	 * @Exception   
	 */
	public Boolean getIsShowContact(String currentOrgInfoId,String targetOrgInfoId)throws Exception {
		//被查看的机构是不是商圈会员
		Boolean isBusinessMember = serviceSubscribeService.isShangQuanHuiYuan(targetOrgInfoId);
		
		//当前用户的机构是不是商圈会员
		Boolean isBusinessMemberCurrent = serviceSubscribeService.isShangQuanHuiYuan(currentOrgInfoId);
		
		//是否能够查看
		if(isBusinessMember && isBusinessMemberCurrent){
			return true;
		}else{
			return false;
		}
	}
}
