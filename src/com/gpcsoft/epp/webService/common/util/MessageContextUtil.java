package com.gpcsoft.epp.webService.common.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.axis.MessageContext;
import org.apache.axis.transport.http.HTTPConstants;

/**
 * 上下文信息
 * Created at 2011-5-28 12:22 by zhouzhanghe
 */
public class MessageContextUtil {

	/**
	 * 获取客户端请求的应用地址
	 * @param HttpServletRequest request
	 * @return 如客户端请求URL为http://128.61.3.4:80/es/test.do，则该方法返回http://128.61.3.4:80/es/
	 * Created at 2011-5-28 12:22 by zhouzhanghe
	 */
	public static String getRequestApplicationURL(HttpServletRequest request){
		return request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
	}
	
	/**
	 * 获取Axis请求的应用地址
	 * @return 如客户端请求URL为http://128.61.3.4:80/es/test.do，则该方法返回http://128.61.3.4:80/es/
	 * Created at 2011-5-28 12:22 by zhouzhanghe
	 */
	public static String getAxisRequestApplicationURL(){
		MessageContext messageContext = MessageContext.getCurrentContext();
		HttpServletRequest request = (HttpServletRequest) messageContext.getProperty(HTTPConstants.MC_HTTP_SERVLETREQUEST);
		return getRequestApplicationURL(request);
	}
}
