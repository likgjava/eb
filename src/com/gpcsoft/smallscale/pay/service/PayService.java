package com.gpcsoft.smallscale.pay.service;
import com.gpcsoft.core.service.BaseGenericService;

@SuppressWarnings("unchecked")
public interface PayService extends BaseGenericService {
	
	/** 
	 * Description :  获取支付属性
	 * Create Date: 2011-6-15下午11:57:41 by yucy  Modified Date: 2011-6-15下午11:57:41 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	String getPayProperties(String key) throws Exception ;
	
	/** 
	 * Description :  获得支付号
	 * Create Date: 2011-7-20下午10:47:35 by sunl  Modified Date: 2011-7-20下午10:47:35 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	String savePayNo() throws Exception ;
}
