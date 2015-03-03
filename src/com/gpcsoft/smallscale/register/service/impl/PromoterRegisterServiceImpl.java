package com.gpcsoft.smallscale.register.service.impl;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.bizplatform.base.message.CustomerMessage;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.pubservice.utils.MailUtil;
import com.gpcsoft.smallscale.point.domain.Promoter;
import com.gpcsoft.smallscale.point.domain.PromoterInfo;
import com.gpcsoft.smallscale.point.manager.PromoterInfoManager;
import com.gpcsoft.smallscale.register.manager.PromoterRegisterManager;
import com.gpcsoft.smallscale.register.service.PromoterRegisterService;
import com.gpcsoft.srplatform.auth.domain.Company;
import com.gpcsoft.srplatform.auth.domain.Employee;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.common.utils.SysInfo;

@Service 
public class PromoterRegisterServiceImpl extends BaseGenericServiceImpl<Promoter> implements PromoterRegisterService {

	
	@Autowired(required=true) @Qualifier("promoterRegisterManagerImpl")
	private PromoterRegisterManager promoterRegisterManager;
	
	@Autowired(required=true) @Qualifier("promoterInfoManagerImpl")
	private PromoterInfoManager promoterInfoManager;
	
	public void saveOfRegisterPromoter(Company company,Employee employee,User user,PromoterInfo proInof) throws Exception{
		//加密之前的密码要在邮件里告知用户
		final String password = user.getPassword();		
		
		
	
		promoterRegisterManager.saveOfRegisterPromoter(company,employee, user);
		proInof.setUser(user);
		promoterInfoManager.save(proInof);
		this.sendEmail(user,password,CustomerMessage.PROMOTER_REGISTER_COMPLETE,CustomerMessage.PROMOTER_REGISTER_TEMPLATE);
		
	}
	
	/** 
	 * Description :  发送通知邮件
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	private void sendEmail(User user,String password, String subjectMsg, String templateMsg) throws Exception {
		//此处不能从内存中取得用户邮箱地址,因为可能用户已经修改了邮箱地址
		//User user = (User)get(orgInfo.getUser().getObjId(), User.class);
        
        Map model = new HashMap();

        //加密之前的密码要在邮件里告知用户
        user.setUsrPasswordInit(password);//用户密码
        model.put("user", user);   
        model.put("appName",SysInfo.getInstance().getProjectname());//系统名称
        model.put("appUrl", SysInfo.getInstance().getSitename());//首页
        
        //发送邮件
        MailUtil.sendEmailAlways(new String[] {user.getEmail()}, 
        		messageSource.getMessage(subjectMsg), 
        		null, 
        		messageSource.getMessage(templateMsg),
        		model);
	}
	

}
