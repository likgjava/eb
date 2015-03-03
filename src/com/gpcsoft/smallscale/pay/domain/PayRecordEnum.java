package com.gpcsoft.smallscale.pay.domain;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.core.service.impl.EnumServiceImpl;

/** 
  *  Comments: <strong>支付业务类型枚举类</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   agreement                    					          
  *  <br/>Module ID: 公共服务平台     		
  *  <br/>Create Date：2011-7-20 下午08:47:33 by liangxj    					                            
  *  <br/>Modified Date:  2011-7-20 下午08:47:33 by liangxj                                   
  *<p>@since 0.5
  *   @version: 0.5 
  */ 
public class PayRecordEnum {
	
	public static final String PAY_TYPE  = "biz.pay.business.type";//支付业务类型
	
	public static final String OD  = "OD";//订单支付业务
	public static final String TG  = "TG";//团购支付业务
	public static final String SF  = "SF";//服务订阅支付业务
	public static final String DF  = "DF";//招标文件支付业务
	public static final String BF  = "BF";//保证金支付业务
	
	/*获得支付业务类型显示中文*/
	public static final String getPayBusinessTypeCN(String str) {
        return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(PayRecordEnum.PAY_TYPE, str);
    }
	
	/*获得支付状态显示中文*/
	public static final String PAY_STATUS = "biz.pay.payStatus"; //支付状态
	public static final String PAY_SUCCESS = "20"; //支付成功
	public static final String PAY_FAILURE = "30"; //支付失败
	public static final String getPayStatusCN(String str) {
		return ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getDescByValue(PayRecordEnum.PAY_STATUS, str);
	}
}
