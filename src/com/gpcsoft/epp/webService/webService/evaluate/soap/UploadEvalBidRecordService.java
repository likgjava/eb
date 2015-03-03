package com.gpcsoft.epp.webService.webService.evaluate.soap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.epp.webService.evaluateSystem.projectInfo.service.ElectronicReviewService;



public class UploadEvalBidRecordService {
	protected final Log logger = LogFactory.getLog(this.getClass());
	
	private ElectronicReviewService remoteProjectService;
	
	public UploadEvalBidRecordService(){
		remoteProjectService = (ElectronicReviewService) FrameBeanFactory.getBean("electronicReviewServiceImpl");
	}
	
	/**
	 * FuncName: uploadOpenBid
	 * Description :  根据查询项目code和包code，上传对应项目（或包组）的评标信息。
	 * @param tenderCode   项目编码（可为空）
	 * @param packCode     包组编码 （可为空）
	 * @param contentXML   xml内容 （必填）
	 * @param username     用户名   （可为空）
	 * @param password	   密码	（可为空）
	 * @return String
	 * @throws Exception String
	 * @author: shenjz
	 * @Create Date:2011-5-25  上午09:27:40
	 * @Modifier: shenjz
	 * @Modified Date:2011-5-25  上午09:27:40
	 */
	public String uploadEvalBidRecord(String tenderCode,String packCode,String contentXML,String username,String password) throws Exception{
		boolean isSuccess = true;
		String exception = "";
		try {
			 remoteProjectService.saveEvalBidRecordByXml(contentXML);
		} catch (Exception e) {
			exception = e.getMessage();
			isSuccess =false;
			e.printStackTrace();
		}
		//添加返回XML的主体内容
		StringBuffer responseXml = new StringBuffer();
		responseXml.append("<?xml version='1.0' encoding='utf-8' ?>");
		responseXml.append("<evlMemberService  xmlns='http://www.gpcsoft.com'>");
		responseXml.append("<header>");
		responseXml.append("<operTag>" + (isSuccess == true ? "Y" : "N") + "</operTag>");
		responseXml.append("<operDesc>" + (isSuccess == true ? "成功" : "失败") + "</operDesc>");
		responseXml.append("<operException>"+exception+"</operException>");
		responseXml.append("</header>");
		responseXml.append("<body>");
		responseXml.append("</body>");
		responseXml.append("</evlMemberService>");
		return responseXml.toString();
	}
}
