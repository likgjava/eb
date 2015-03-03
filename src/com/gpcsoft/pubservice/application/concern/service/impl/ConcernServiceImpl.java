package com.gpcsoft.pubservice.application.concern.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.bizplatform.base.message.CustomerMessage;
import com.gpcsoft.bizplatform.organization.dao.OrgInfoDao;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.organization.manager.OrgInfoManager;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.DateUtil;
import com.gpcsoft.core.utils.StringUtils;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.pubservice.application.concern.dao.ConcernDao;
import com.gpcsoft.pubservice.application.concern.dao.ConcernGroupDao;
import com.gpcsoft.pubservice.application.concern.domain.Concern;
import com.gpcsoft.pubservice.application.concern.domain.ConcernGroup;
import com.gpcsoft.pubservice.application.concern.enumeration.ConcernEnum;
import com.gpcsoft.pubservice.application.concern.manager.ConcernGroupManager;
import com.gpcsoft.pubservice.application.concern.manager.ConcernManager;
import com.gpcsoft.pubservice.application.concern.service.ConcernService;
import com.gpcsoft.pubservice.utils.MailUtil;
import com.gpcsoft.pubservice.utils.MessageUtil;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.auth.manager.UserManager;
import com.gpcsoft.srplatform.common.assist.service.FreeMarkerService;

import freemarker.template.TemplateException;

@Service 
public class ConcernServiceImpl extends BaseGenericServiceImpl<Concern> implements ConcernService {

	@Autowired(required=true) @Qualifier("concernManagerImpl")
	private ConcernManager concernManager;
	
	@Autowired(required=true)  @Qualifier("concernDaoHibernate")
	private ConcernDao concernDaoHibernate;
	
	@Autowired(required=true) @Qualifier("orgInfoManagerImpl")
	private OrgInfoManager orgInfoManager;
	
	@Autowired(required=true) @Qualifier("orgInfoDaoHibernate")
	private OrgInfoDao orgInfoDao;
	
	@Autowired(required=true) @Qualifier("userManagerImpl")
	private UserManager userManagerImpl;
	
	@Autowired(required=true) @Qualifier("concernGroupManagerImpl")
	private ConcernGroupManager concernGroupManager;
	
	@Autowired(required=true)  @Qualifier("concernGroupDaoHibernate")
	private ConcernGroupDao concernGroupDaoHibernate;
	
	@Autowired(required=true) @Qualifier("freeMarkerServiceImpl")
	private FreeMarkerService freeMarkerService;
		
	/** 
	 * Description : 保存直接关注客户 
	 * Create Date: 2010-11-3下午05:45:22 by guoyr  Modified Date: 2010-11-3下午05:45:22 by guoyr
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Concern saveConcern(Concern concern){
		//所属人
		User currentUser = AuthenticationHelper.getCurrentUser(true);
		
		
		// 如果没有选择分组，则设为默认分组
		if(null == concern.getConcernGroup() || "".equals(concern.getConcernGroup()) || null == concern.getConcernGroup().getObjId()){
			
			String groupType = concern.getConcernGroup().getGroupType();
			
			// 如果没有传关注对象的类型，默认为关注的供应商
			if(null == groupType || "".equals(groupType)){
				groupType = ConcernEnum.SUPPLIER_GROUP;
			}
			
			// 获取默认分组
			ConcernGroup concernGroup = concernGroupDaoHibernate.getDefaultConcernGroup(currentUser.getObjId());// 供应商
			
			// 如果没有创建过分组，则自动创建一个默认的分组
			if(null ==concernGroup){
				concernGroup = concern.getConcernGroup();
				concernGroup.setCreateTime(new Date());
				concernGroup.setGroupName("默认分组");
				concernGroup.setSort(concernGroupManager.getMaxConcernGroupSort(groupType));
				concernGroup = concernGroupManager.saveConcernGroup(concernGroup);
			}
			concern.setConcernGroup(concernGroup);
		}
		
		// 如果没有选择所属名单，则默认为短名单
		if(null == concern.getListType() || "".equals(concern.getListType())){
			concern.setListType(ConcernEnum.SHORT_LIST);
		}
		
		concern.setBelongsUser(currentUser);
		
		// 如果是修改，则只需要修改关注对象所属的分组和名单类型
		if(null != concern.getObjId() && !"".equals(concern.getObjId())){
			concernManager.updateConcern(concern.getObjId(), concern.getConcernGroup().getObjId(), concern.getListType());
		}else {
			
			OrgInfo orgInfo = orgInfoManager.get(concern.getOrgInfo().getObjId());
			if(null != orgInfo && null != orgInfo.getCompany()){
				//冗余机构管理员用户
				User user = userManagerImpl.getManagerUser(orgInfo.getCompany().getObjId(), User.USER_TYPE_MANAGER.toString());
				concern.setUser(user);
			}
			concernManager.save(concern);
		}
		
		return concern;
	}
	
	/**
	 * 移至黑名单、移出黑名单(移至我的客户)
	 */
	public void transferInOrOutHacker(String objId, String listType) {
		concernDaoHibernate.transferInOrOutHacker(objId, listType);
	}

	/**
	 * 保存客户(间接保存,交易时的客户)
	 */
	public void saveClientConcern(String clientsIds,String projectCreator) {
		String[] clientIdArr = clientsIds.substring(0, clientsIds.length()-1).split(",");
		User currentUser = AuthenticationHelper.getCurrentUser(true);//当前用户
		OrgInfo orgInfoCurrent = (OrgInfo) currentUser.getOrgInfo();//当前用户机构
		Map<String, Object>paramMap = new HashMap<String, Object>();
		
		//保存至当前用户的客户管理
		for(int i=0;i<clientIdArr.length;i++){
			paramMap.clear();
			paramMap.put("belongsUserId", currentUser.getObjId());
			paramMap.put("clientId", clientIdArr[i]);			
			if(!concernDaoHibernate.isExistClient(paramMap)){
				Concern concern = new Concern();
				// 获取默认分组
				ConcernGroup concernGroup = concernGroupDaoHibernate.getDefaultConcernGroup(currentUser.getObjId());
				// 如果没有创建过分组，则自动创建一个默认的分组
				if(null ==concernGroup){
					concernGroup = new ConcernGroup();
					concernGroup.setGroupType(ConcernEnum.SUPPLIER_GROUP);
					concernGroup.setCreateTime(new Date());
					concernGroup.setGroupName("默认分组");
					concernGroup.setSort(concernGroupManager.getMaxConcernGroupSort(ConcernEnum.SUPPLIER_GROUP));
					concernGroup = concernGroupManager.saveConcernGroup(concernGroup);
				}
				concern.setConcernGroup(concernGroup);
				concern.setListType(ConcernEnum.CLIENT_LIST);//潜在客户
				//客户人
				OrgInfo orgInfo = orgInfoManager.get(clientIdArr[i]);
				concern.setOrgInfo(orgInfo);
				//冗余客户管理员
				User user = userManagerImpl.getManagerUser(orgInfo.getCompany().getObjId(), User.USER_TYPE_MANAGER.toString());
				concern.setUser(user);				
				
				concern.setBelongsUser(currentUser);
				
				//保存
				concernManager.save(concern);
			}
		}
		
		//保存至交易对象的客户管理
		for(int i=0;i<clientIdArr.length;i++){
			OrgInfo orgInfo = orgInfoManager.get(clientIdArr[i]);
			//机构管理员
			User userS = userManagerImpl.getManagerUser(orgInfo.getCompany().getObjId(), User.USER_TYPE_MANAGER.toString());
			paramMap.clear();
			paramMap.put("belongsUserId", userS.getObjId());
			paramMap.put("clientId", orgInfoCurrent.getObjId());
			if(!concernDaoHibernate.isExistClient(paramMap)){
				Concern concern = new Concern();
				// 获取默认分组
				ConcernGroup concernGroup = concernGroupDaoHibernate.getDefaultConcernGroup(userS.getObjId());
				// 如果没有创建过分组，则自动创建一个默认的分组
				if(null ==concernGroup){
					concernGroup = new ConcernGroup();
					concernGroup.setGroupType(ConcernEnum.SUPPLIER_GROUP);
					concernGroup.setCreateTime(new Date());
					concernGroup.setGroupName("默认分组");
					concernGroup.setSort(concernGroupManager.getMaxConcernGroupSort(ConcernEnum.SUPPLIER_GROUP));
					concernGroup.setBelongsOrg(orgInfo);
					concernGroup.setBelongsUser(userS);
					concernGroup = concernGroupManager.saveConcernGroup(concernGroup);
				}
				concern.setConcernGroup(concernGroup);
				concern.setListType(ConcernEnum.CLIENT_LIST);//潜在客户
				concern.setOrgInfo(orgInfoCurrent);
				concern.setUser(currentUser);
				//当为竞价项目时，由于项目是已经创建的，拥有明确的采购人
				if(projectCreator != null){
					User us = new User();
					us.setObjId(projectCreator);
					concern.setBelongsUser(us);
				}
				//concern.setBelongsUser(userS);
				//保存
				concernManager.save(concern);
			}
		}
	}

	/**
	 * 根据项目的品目筛选符合条件的客户,并向其发送参加竞价项目邀请函和站内信
	 * purCategoryIds(竞价项目的采购品目Id)
	 * @throws Exception 
	 */
	public List<Concern> fitConcernByOrgBidRange(Project project) throws Exception {
		List<Concern> concernList = new ArrayList<Concern>();
		String purCategoryIds = project.getPurCategoryIds();
		
		//获取当前用户的客户 
		User currentUser = AuthenticationHelper.getCurrentUser(true);
		String hql = "from Concern c where c.belongsUser.objId = '" + currentUser.getObjId() + "'";
		List<Concern> currentUserConcernList = concernDaoHibernate.findbyHql(hql);
		
		//筛选符合条件的客户 并发送邀请函和站内信
		for(int i=0;i<currentUserConcernList.size();i++){
			Concern concern = currentUserConcernList.get(i);
			OrgInfo orgInfo = concern.getOrgInfo();//客户机构
			String bidForRange = orgInfo.getBidForRangeCode();//客户机构经营范围的id code
			
			//当此机构经营范围包含竞价项目采购品目时 
			if(bidForRange.indexOf(purCategoryIds) != -1){
				concernList.add(concern);				
				//发送参加竞价项目邀请函
				sendConcernMail(project,orgInfo);
				//发送站内信
				sendConcernMessage(project,orgInfo);
				//发送短信
				sendConcerNote(project,orgInfo);
			}
		}
		return concernList;
	}

	/**
	 * 向客户发送短信邀请
	 */
	public void sendConcerNote(Project project,OrgInfo orgInfo) {
		
	}

	/**
	 * 向客户发送邮件邀请
	 * @throws TemplateException 
	 * @throws IOException 
	 * @throws MessagingException 
	 */
	public void sendConcernMail(Project project,OrgInfo orgInfo) throws Exception {
		String email = null;
		
		User managerUser =  userManagerImpl.getManagerUser(orgInfo.getObjId(), User.USER_TYPE_MANAGER.toString());
		
		if(managerUser!=null&&StringUtils.hasLength(managerUser.getEmail())){
			email = managerUser.getEmail();
		}else if(StringUtils.hasLength(orgInfo.getCompany().getEmail())){
			email = orgInfo.getCompany().getEmail();
		}
		Map<String, Object> templateMap = new HashMap<String, Object>();
		
		templateMap.put("project",project);
		templateMap.put("signUpSTime",DateUtil.format(project.getSignUpETime(), "yyyy-MM-dd HH:mm:ss"));
		
		String  messageContent = freeMarkerService.getFreeMarkerContent(messageSource.getMessage(CustomerMessage.INVITATION_CONCERN_CLIENT_PROJECT_TEMPLATE), templateMap);
		
		//发送邮件
		MailUtil.sendEmailNotAlways(
				new String[] {email}, 
				project.getProjName()+"邀请函", 
				messageContent, 
				null, 
				null);
	}

	/**
	 * 向客户发送站内信邀请
	 * @throws Exception 
	 */
	public void sendConcernMessage(Project project,OrgInfo orgInfo) throws Exception {		
		Map<String, Object> templateMap = new HashMap<String, Object>();
		
		templateMap.put("project",project);
		templateMap.put("signUpSTime",DateUtil.format(project.getSignUpETime(), "yyyy-MM-dd HH:mm:ss"));
		
		String  messageContent = freeMarkerService.getFreeMarkerContent(messageSource.getMessage(CustomerMessage.INVITATION_CONCERN_CLIENT_PROJECT_TEMPLATE), templateMap);
		
		//发送站内信
		MessageUtil.sendMessagePrivete(
				orgInfoDao.getAllManagersByOrgInfoIds(User.USER_TYPE_MANAGER.toString(), orgInfo.getObjId()),
				project.getProjName()+"邀请函", 
				messageContent,
				null, 
				null, 
				true);
		
	}

	/** 
	 * Description :  判断机构是否为我机构的黑名单客户
	 * Create Date: 2011-7-20下午02:01:23 by likg  Modified Date: 2011-7-20下午02:01:23 by likg
	 * @param   myOrgId:我的机构id	blackOrgId:被检查的机构id
	 * @return  
	 * @Exception   
	 */
	public boolean isInBlacklist(String myOrgId, String blackOrgId) throws Exception {
		return concernManager.isInBlacklist(myOrgId, blackOrgId);
	}

	/** 
	 * Description :  获取指定机构或指定用户的黑名单客户的机构id
	 * Create Date: 2011-7-20下午07:30:27 by likg  Modified Date: 2011-7-20下午07:30:27 by likg
	 * @param   myOrgId:指定机构id	myUserId:指定用户id
	 * @return  List<String>:黑名单客户的机构id
	 * @Exception   
	 */
	public List<String> getMyBlacklist(String myOrgId, String myUserId) throws Exception {
		return concernManager.getMyBlacklist(myOrgId, myUserId);
	}
}
