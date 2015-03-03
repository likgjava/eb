package com.gpcsoft.smallscale.register.service;
import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.smallscale.point.domain.Promoter;
import com.gpcsoft.smallscale.point.domain.PromoterInfo;
import com.gpcsoft.srplatform.auth.domain.Company;
import com.gpcsoft.srplatform.auth.domain.Employee;
import com.gpcsoft.srplatform.auth.domain.User;

public interface PromoterRegisterService extends BaseGenericService<Promoter> {
	
	

	/**
	 * 保存注册推广人
	 * @param employee
	 * @param user
	 */
	void saveOfRegisterPromoter(Company company,Employee employee,User user,PromoterInfo proInof) throws Exception;

}
