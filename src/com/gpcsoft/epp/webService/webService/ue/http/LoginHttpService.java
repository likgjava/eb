/**
 * 
 */
package com.gpcsoft.epp.webService.webService.ue.http;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.common.manager.UserApiManager;
import com.gpcsoft.epp.webService.ueSystem.bulletin.dto.BulletinDTO;
import com.gpcsoft.epp.webService.ueSystem.bulletin.dto.UserInfoDTO;

/**
 * @author liuk
 *
 */
@SuppressWarnings("unchecked")
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={BulletinDTO.class})
@RequestMapping("/LoginHttpService.do")//页面请求路径,可修改
public class LoginHttpService extends AnnotationMultiController{

	@Autowired(required=true) @Qualifier("userApiManagerImpl")
	private UserApiManager userApiManager;
	
	/**
	 * FuncName: login
	 * Description :  用户登录执行平台接口
	 * @param  void
	 * @author: liuke
	 * @throws Exception 
	 * @Create Date:2011-5-9  上午09:40:57
	 * @Modifier: liuke
	 * @Modified Date:2011-5-9  上午09:40:57
	 */
	@RequestMapping(params="method=login")
	public void login(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String isSuccess = "N";   //判断是否登录成功标示
		String userCode = request.getParameter("userCode");  //用户名
		String password = request.getParameter("password");  //密码
		StringBuffer responseXml = new StringBuffer();
		String errorMessage = "";
		String exception = "";
		try {
			UserInfoDTO userInfoDto = new UserInfoDTO();
			userInfoDto = userApiManager.getUserInfoDTOByUserCodeAndPassWord(userCode, password);
			if(userInfoDto!=null&&!"".equals(userInfoDto.getOrgCode())&&!"".equals(userInfoDto.getUserCode())){
				isSuccess = "Y";
			}else{
				errorMessage="用户名或密码错误";
			}
			responseXml.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
			responseXml.append("<login xmlns=\"http://www.gpcsoft.com\">");
			responseXml.append("<header>");
			responseXml.append("<operTag>"+isSuccess+"</operTag>");
			responseXml.append("<operDesc>"+errorMessage+"</operDesc>");
			responseXml.append("<operException>"+exception+"</operException>");
			responseXml.append("</header>");
			responseXml.append("<body>");
			responseXml.append("<userInfo>");
			responseXml.append("<userCode>"+userInfoDto.getUserCode()+"</userCode>");
			responseXml.append("<orgCode>"+userInfoDto.getOrgCode()+"</orgCode>");
			responseXml.append("<orgName>"+userInfoDto.getOrgName()+"</orgName>");
			responseXml.append("<userName>"+userInfoDto.getUserName()+"</userName>");
			responseXml.append("<transactionCertNo>"+userInfoDto.getTransactionCertNo()+"</transactionCertNo>");
			responseXml.append("<legalDelegate>"+userInfoDto.getLegalDelegate()+"</legalDelegate>");
			responseXml.append("</userInfo>");
			responseXml.append("</body>");
			responseXml.append("</login>");
		} catch (Exception e) {
			exception = e.getMessage();
			responseXml = new StringBuffer();
			responseXml.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
			responseXml.append("<login xmlns=\"http://www.gpcsoft.com\">");
			responseXml.append("<header>");
			responseXml.append("<operTag>"+isSuccess+"</operTag>");
			responseXml.append("<operDesc>"+errorMessage+"</operDesc>");
			responseXml.append("<operException>"+exception+"</operException>");
			responseXml.append("</header>");
			responseXml.append("<body>");
			responseXml.append("<userInfo>");
			responseXml.append("<userCode></userCode>");
			responseXml.append("<orgCode></orgCode>");
			responseXml.append("<orgName></orgName>");
			responseXml.append("<userName></userName>");
			responseXml.append("<transactionCertNo></transactionCertNo>");
			responseXml.append("<legalDelegate</legalDelegate>");
			responseXml.append("</userInfo>");
			responseXml.append("</body>");
			responseXml.append("</login>");
		}
		logger.debug("LoginHttpService  login start=============================================\n"+responseXml.toString());
		logger.debug("LoginHttpService  login end=============================================\n");
		write(responseXml.toString(), response);
	}
	
}
