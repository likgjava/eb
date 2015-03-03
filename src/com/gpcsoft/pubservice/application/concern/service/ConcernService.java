package com.gpcsoft.pubservice.application.concern.service;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.pubservice.application.concern.domain.Concern;

import freemarker.template.TemplateException;

public interface ConcernService extends BaseGenericService<Concern> {

	/** 
	 * Description : 保存直接关注客户
	 * Create Date: 2010-11-3下午05:45:22 by guoyr  Modified Date: 2010-11-3下午05:45:22 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Concern saveConcern(Concern concern);
	
	/**
	 * Description :  移至黑名单、移出黑名单(移至我的客户)
	 * Create Date: 2011-6-28上午11:39:10 by zhaojf  Modified Date: 2011-6-28上午11:39:10 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public void transferInOrOutHacker(String objId,String listType);
	
	/**
	 * Description :  保存客户(间接保存,交易时的客户)
	 * Create Date: 2011-7-1下午02:41:58 by zhaojf  Modified Date: 2011-7-1下午02:41:58 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public void saveClientConcern(String clientsIds,String projectCreator);
	
	/**
	 * Description :  根据项目的品目筛选符合条件的客户,并向其发送参加竞价项目邀请函和站内信
	 * Create Date: 2011-7-11下午03:58:47 by zhaojf  Modified Date: 2011-7-11下午03:58:47 by zhaojf
	 * @param   purCategoryIds(竞价项目的采购品目Id)
	 * @return  
	 * @throws Exception 
	 * @Exception
	 */
	public List<Concern> fitConcernByOrgBidRange(Project project) throws Exception;

	/**
	 * Description :  向客户发送短信邀请
	 * Create Date: 2011-7-11下午03:58:47 by zhaojf  Modified Date: 2011-7-11下午03:58:47 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public void sendConcerNote(Project project,OrgInfo orgInfo);

	/**
	 * Description :  向客户发送邮件邀请
	 * Create Date: 2011-7-11下午03:58:47 by zhaojf  Modified Date: 2011-7-11下午03:58:47 by zhaojf
	 * @param   
	 * @return  
	 * @throws TemplateException 
	 * @throws IOException 
	 * @throws MessagingException 
	 * @Exception
	 */
	public void sendConcernMail(Project project,OrgInfo orgInfo) throws Exception;

	/**
	 * Description :  向客户发送站内信邀请
	 * Create Date: 2011-7-11下午03:58:47 by zhaojf  Modified Date: 2011-7-11下午03:58:47 by zhaojf
	 * @param   
	 * @return  
	 * @throws Exception 
	 * @Exception
	 */
	public void sendConcernMessage(Project project,OrgInfo orgInfo) throws IOException, TemplateException, MessagingException, Exception;
	
	/** 
	 * Description :  判断机构是否为我机构的黑名单客户
	 * Create Date: 2011-7-20下午02:01:23 by likg  Modified Date: 2011-7-20下午02:01:23 by likg
	 * @param   myOrgId:我的机构id	blackOrgId:被检查的机构id
	 * @return  
	 * @Exception   
	 */
	public boolean isInBlacklist(String myOrgId, String blackOrgId) throws Exception;
	
	
	/** 
	 * Description :  获取指定机构或指定用户的黑名单客户的机构id
	 * Create Date: 2011-7-20下午07:30:27 by likg  Modified Date: 2011-7-20下午07:30:27 by likg
	 * @param   myOrgId:指定机构id	myUserId:指定用户id
	 * @return  List<String>:黑名单客户的机构id
	 * @Exception   
	 */
	public List<String> getMyBlacklist(String myOrgId, String myUserId) throws Exception;

}
