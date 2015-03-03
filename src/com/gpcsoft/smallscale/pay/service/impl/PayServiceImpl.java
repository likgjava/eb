package com.gpcsoft.smallscale.pay.service.impl;

import java.io.InputStream;
import java.util.Properties;

import org.springframework.stereotype.Service;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.pubservice.application.advertisement.service.impl.AdvertisingSubscribeServiceImpl;
import com.gpcsoft.pubservice.utils.SequenceNumberUtil;
import com.gpcsoft.smallscale.pay.service.PayService;

@SuppressWarnings("unchecked")
@Service 
public class PayServiceImpl extends BaseGenericServiceImpl implements PayService {
	
	/** 
	 * Description :  获取支付属性
	 * Create Date: 2011-6-15下午11:57:41 by yucy  Modified Date: 2011-6-15下午11:57:41 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public String getPayProperties(String key) throws Exception {
		Properties properties = new Properties();
		InputStream in =AdvertisingSubscribeServiceImpl.class.getClassLoader().getResourceAsStream("sys/chinabankpay.properties");
		properties.load(in);
		in.close();
		return properties.getProperty(key);
	}
	
	/** 
	 * Description :  获取支付号
	 * Create Date: 2011-7-20下午10:47:35 by sunl  Modified Date: 2011-7-20下午10:47:35 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public String savePayNo() throws Exception {
		return SequenceNumberUtil.getOrderSN();
	}
}
