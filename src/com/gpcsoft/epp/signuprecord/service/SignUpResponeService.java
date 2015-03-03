package com.gpcsoft.epp.signuprecord.service;
import java.util.List;
import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.epp.signuprecord.domain.SignUpRespone;
public interface SignUpResponeService extends BaseGenericService<SignUpRespone> {
	/**
	 * 
	 * Description :根据项目得到报名响应列表  
	 * Create Date: 2010-8-13上午09:16:46 by liuke  Modified Date: 2010-8-13上午09:16:46 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<SignUpRespone> getSignUpResponeListByProjectIdAndSupplierId(String projectId,String supplierId);
}
