package com.gpcsoft.smallscale.register.manager;


import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.smallscale.point.domain.Promoter;
import com.gpcsoft.srplatform.auth.domain.Company;
import com.gpcsoft.srplatform.auth.domain.Employee;
import com.gpcsoft.srplatform.auth.domain.User;

public interface PromoterRegisterManager extends BaseManager<Promoter> {
	
	
	void saveOfRegisterPromoter(Company company,Employee employee,User user);
	
}
