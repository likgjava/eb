package com.gpcsoft.pubservice.utils;

import java.util.Map;

import com.gpcsoft.bizplatform.base.exception.CustomerException;
import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.utils.StringUtils;
import com.gpcsoft.srplatform.common.utils.SysInfo;
import com.gpcsoft.srplatform.mail.domain.EmailMessage;
import com.gpcsoft.srplatform.mail.service.MailService;

public class MailUtil {
	
	private static MailService mailService;
	
	static {
		mailService =  (MailService) FrameBeanFactory.getBean("attachmentMailServiceImpl");
	}
	
	/** 
	 * Description : 发送短信 总是发送
	 * Create Date: 2011-7-22上午09:12:19 by yucy  Modified Date: 2011-7-22上午09:12:19 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public static void sendEmailAlways(String[] toEmail,String subject , String content, String templateName,Map<String, Object> contentModel ) throws Exception {
		sendEmail(toEmail, subject ,  content, templateName, contentModel, false  );
	}
	
	/** 
	 * Description : 发送短信 根据配置文件判断是否发送
	 * Create Date: 2011-7-22上午09:12:19 by yucy  Modified Date: 2011-7-22上午09:12:19 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public static void sendEmailNotAlways(String[] toEmail,String subject , String content, String templateName,Map<String, Object> contentModel  ) throws Exception {
		sendEmail(toEmail, subject ,  content, templateName, contentModel, true  );
	}
	
	/** 
	 * Description :  发送邮件
	 * Create Date: 2011-7-20下午01:42:31 by yucy  Modified Date: 2011-7-20下午01:42:31 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	private static void sendEmail(String[] toEmail,String subject , String content, String templateName,Map<String, Object> contentModel, Boolean isControlGloble  ) throws Exception {
		
		if( isControlGloble && !SysInfo.getInstance().isAbleEmail() ){ return; }
		
		//邮箱地址为空,则抛出异常
        if (toEmail==null || toEmail.length==0 ) throw new CustomerException("邮箱地址不能为空！");
        
        // 创建邮件模板并发送邮件
        EmailMessage emailEmessage = new EmailMessage();
        
        //接收人地址
        emailEmessage.setTo(toEmail);
        // 邮件标题
        emailEmessage.setSubject( subject );
        
        if(StringUtils.hasLength(content)){
        	//设置邮件内容
        	emailEmessage.setContent(content);
        }else{
        	//邮件模板
        	emailEmessage.setTemplateName(templateName);
        	emailEmessage.setTemplateMap(contentModel);
        }
        mailService.sendMessage(emailEmessage);
	}
}