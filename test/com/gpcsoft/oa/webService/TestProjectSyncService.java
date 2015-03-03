/**
 * 
 */
package com.gpcsoft.oa.webService;

import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
/**
 * @author dell
 *
 */
public class TestProjectSyncService {
    public static final String ENCODING_REQUEST = "utf-8";
    public static final String ENCODING_RESPONSE = "utf-8";
	
	public static void main(String[] args) {
		String oaxml = "<?xml version='1.0' encoding='utf-8' ?><project xmlns='http://www.gpcsoft.com'><header><pmOrgId>31</pmOrgId><pmUserId>474</pmUserId></header><body><projectInfo><projCode>GXTC-222</projCode><projName>同步招标项目信息222</projName><ebuyMethod>00</ebuyMethod><content>同步招标项目信息111</content><buyerId>1937</buyerId><buyerLinkerName>张大山</buyerLinkerName><buyerLinkerPhone></buyerLinkerPhone><categoryCode>B</categoryCode><createDate>2011-11-28 00:00:00</createDate><department>31</department><totalBudget>1200</totalBudget><entrustDate></entrustDate></projectInfo></body></project>";
		try {
			long currentTime = System.currentTimeMillis();
			String webAddress = "http://192.168.5.30:8080/services/es/gxoa/syncProjectService";//访问的web服务地址
			String invokeMethod = "syncProjectInfo";//调用的方法
			Service service = new Service();//访问SOAP的第一步
			Call call = null;//实际调用Web Servicef

			call = (Call) service.createCall();

			call.setOperationName(new QName(webAddress, invokeMethod));
			call.setTargetEndpointAddress(new java.net.URL(webAddress));

			Object[] objectArray = new Object[]{oaxml};
			String ret = (String) call.invoke(objectArray);//调用方法，object数组里为调用时传递的参数

			System.out.println("Return value is " + ret);
			System.out.println("Time = " + (System.currentTimeMillis() - currentTime)/1000 + "S");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
