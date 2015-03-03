package com.gpcsoft.pubservice.application.message.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.pubservice.application.message.domain.Advise;
import com.gpcsoft.pubservice.application.message.manager.AdviseManager;
import com.gpcsoft.pubservice.application.message.service.AdviseService;
import com.gpcsoft.pubservice.utils.MailUtil;

@Service 
public class AdviseServiceImpl extends BaseGenericServiceImpl<Advise> implements AdviseService {

	@Autowired(required=true) @Qualifier("adviseManagerImpl")
	private AdviseManager adviseManager;
	
	public void saveReply(Advise advise) throws Exception{			
		adviseManager.save(advise);
		
		if(advise.getContactEmail()!=null && advise.getContactEmail().equals("")){
			this.sendMail(advise);
		}
		
	}
	
	private void sendMail(Advise advise) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		// 发送人信息
		model.put("userName", advise.getCreateUser().getEmp().getName());
		model.put("centent", advise.getReplyContent());		
		
		//发送邮件
		MailUtil.sendEmailNotAlways(
				new String[]{advise.getContactEmail()}, 
				messageSource.getMessage("pubservice.message.advise.replyMail.title"), 
				null, 
				messageSource.getMessage("pubservice.message.advise.replyMail.template"),
				model);
	}

}
