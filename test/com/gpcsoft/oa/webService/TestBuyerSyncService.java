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
public class TestBuyerSyncService {
	public static void main(String[] args) {
		//同步采购单位
		String oaxml = "<?xml version='1.0' encoding='utf-8' ?><buyer xmlns='http://www.gpcsoft.com'><body><buyerInfo><buyerId>10435</buyerId><buyerName>哈尔滨市公路工程质量监督站工程实验仪器采购项目</buyerName><buyerCode>23010000000000</buyerCode><registeredCapital>500</registeredCapital><legalPerson>不详</legalPerson><mainProducts>工程质量监督</mainProducts><belongIndustry></belongIndustry><belongArea>（08）黑龙江</belongArea><entPrpt></entPrpt><parentUnit></parentUnit><telephone>0451-55520133</telephone><fax>0451-55520133</fax><email>null@qq.com</email><postCode>150001</postCode><address>不详</address></buyerInfo></body></buyer>";
		//同步组织机构
//		String oaxml = "<?xml version='1.0' encoding='utf-8' ?><orgnization xmlns='http://www.gpcsoft.com'><body><orgInfo><orgId>888888</orgId> <orgType>1</orgType><parentId></parentId><orgName>ssssssss</orgName></orgInfo></body></orgnization>";
		//删除组织机构/采购单位的xml(只传入id即可)
//		String oaxml = "<?xml version='1.0' encoding='utf-8' ?><org xmlns='http://www.gpcsoft.com'><body><orgInfo><orgId>999999</orgId></orgInfo></body></org>";
		try {
			long currentTime = System.currentTimeMillis();
			String webAddress = "http://192.168.5.31:8088/xycg/services/es/gxoa/syncProjectService";//访问的web服务地址
			String invokeMethod = "createOrUpdateBuyer";//调用的方法--同步采购单位信息
//			String invokeMethod = "createOrUpdateOrg";//调用的方法--同步组织机构信息
//			String invokeMethod = "removeBuyer";//调用的方法--删除采购单位信息
//			String invokeMethod = "removeOrg";//调用的方法--删除组织机构信息
			Service service = new Service();//访问SOAP的第一步
			Call call = null;//实际调用Web Service

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
