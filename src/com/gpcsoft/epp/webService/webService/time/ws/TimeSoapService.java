package com.gpcsoft.epp.webService.webService.time.ws;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gpcsoft.core.utils.DateUtil;

/**
 * @author Administrator
 *
 */
public class TimeSoapService {
	protected final Log logger = LogFactory.getLog(this.getClass());
	

	/**
	 * FuncName: downBidFileApp
	 * Description :  创建或修改对象
	 * @param 
	 * @param tenderCode 项目编号
	 * @param packCode 包组编号
	 * @param orgCode 机构编号
	 * @param username 用户名
	 * @param password 密码
	 * @return String
	 * @author: liuke
	 * @Create Date:2011-7-1  上午11:38:10
	 * @Modifier: liuke
	 * @Modified Date:2011-7-1  上午11:38:10
	 */
	@RequestMapping(params="method=getTime")
	public String getTime(HttpServletRequest request, HttpServletResponse response) throws Exception{
		logger.debug("\nes TimeSoapService||getTime\n");
		Date date = new Date();
		StringBuffer responseXml = new StringBuffer();
		String errorMessage = "";
		String time ="";
		boolean success = true;
		try {
			time = DateUtil.format(date, "yyyy-MM-dd HH:mm:ss");
		} catch (Exception e) {
			success =false;
			errorMessage = e.getMessage();
		}
		responseXml.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
		responseXml.append("<getTime xmlns=\"http://www.gpcsoft.com\">");
		responseXml.append("<header>");
		responseXml.append("<operTag>"+(success?"Y":"N")+"</operTag>");
		responseXml.append("<operDesc>"+(success?"成功":"失败")+"</operDesc>");
		responseXml.append("<operException>"+errorMessage+"</operException>");
		responseXml.append("</header>");
		responseXml.append("<body>");
		responseXml.append("<time>"+time+"</time>");
		responseXml.append("</body>");
		responseXml.append("</getTime>");
		logger.debug("TimeSoapService  getTime start=============================================\n"+responseXml.toString());
		logger.debug("TimeSoapService  getTime end=============================================\n");
		return responseXml.toString();
	}

}
