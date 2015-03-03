package com.gpcsoft.pubservice.application.message.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.pubservice.application.message.domain.Complain;
import com.gpcsoft.pubservice.application.message.manager.ComplainManager;
import com.gpcsoft.pubservice.application.message.service.ComplainService;
import com.gpcsoft.pubservice.utils.MailUtil;

@Service 
public class ComplainServiceImpl extends BaseGenericServiceImpl<Complain> implements ComplainService {

	@Autowired(required=true) @Qualifier("complainManagerImpl")
	private ComplainManager complainManager;
	
	public void saveTell(Complain complain ){		
		complain.setComplainant(AuthenticationHelper.getCurrentUser(true));		
		complain.setComplainantName(AuthenticationHelper.getCurrentUser(true).getUsername());
		complainManager.save(complain);		
	}
	
    public void saveDeal(Complain complain ) throws Exception{		
    	
    	Complain newComplain = complainManager.get(complain.getObjId());
    	
    	//AuthenticationHelper.getCurrentUser().getUsername();
    	newComplain.setDisposer(AuthenticationHelper.getCurrentUser(true));
    	newComplain.setDisposeTime(new Date());
    	newComplain.setIsDispose(true);
    	newComplain.setResult(complain.getResult());
    	complainManager.save(newComplain);
    	if(newComplain.getReplyEmail()!= null && !newComplain.getReplyEmail().equals("")){
    		this.sentMail(newComplain);
    	}
    	
		
	}
    
    private void sentMail(Complain complain) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		// 发送人信息
		model.put("userName", complain.getComplainantName());
		model.put("centent", complain.getResult());

		//发送邮件
		MailUtil.sendEmailAlways(
				new String[]{complain.getReplyEmail()}, 
				messageSource.getMessage("pubservice.message.complain.dealMail.title"),
				null, 
				messageSource.getMessage("pubservice.message.complain.dealMail.template"),
				model);
    }

	/** 
	 * Description :  查询投诉列表
	 * Create Date: 2011-7-11下午04:40:14 by yucy  Modified Date: 2011-7-11下午04:40:14 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Page<Complain> getComplainList(Page<Complain> page, Map<String, Object> paramsMap) throws Exception {
		return complainManager.getComplainList(page,paramsMap);
	}


}
