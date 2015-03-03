package com.gpcsoft.smallscale.point.manager;


import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.smallscale.point.domain.Promoter;
import com.gpcsoft.srplatform.auth.domain.Company;
import com.gpcsoft.srplatform.auth.domain.User;


public interface PromoterManager extends BaseManager<Promoter> {
	
	void createByUserId(String promoterid,User buyer,Company company,String demo);
	
	Promoter  getByOrgName(String orgName);
	

}
