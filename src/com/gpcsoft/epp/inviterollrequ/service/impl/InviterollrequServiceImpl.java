package com.gpcsoft.epp.inviterollrequ.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.FileUtil;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.common.domain.EbuyMethodEnum;
import com.gpcsoft.epp.common.domain.FileVirtualPathEnum;
import com.gpcsoft.epp.common.utils.FileUtils;
import com.gpcsoft.epp.inviterollrequ.dao.InrqDetailDao;
import com.gpcsoft.epp.inviterollrequ.dao.InviterollrequDao;
import com.gpcsoft.epp.inviterollrequ.domain.InrqAuditStatusEnum;
import com.gpcsoft.epp.inviterollrequ.domain.InrqDetail;
import com.gpcsoft.epp.inviterollrequ.domain.InrqKindEnum;
import com.gpcsoft.epp.inviterollrequ.domain.InrqSelectTypeEnum;
import com.gpcsoft.epp.inviterollrequ.domain.Inviterollrequ;
import com.gpcsoft.epp.inviterollrequ.manager.InviterollrequManager;
import com.gpcsoft.epp.inviterollrequ.service.InviterollrequService;
import com.gpcsoft.epp.project.dao.ProjectDao;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.manager.ProjectManager;
import com.gpcsoft.epp.worktask.domain.CompletedWorkTaskResultEnum;
import com.gpcsoft.epp.worktask.domain.CompletedWorkTaskTypeEnum;
import com.gpcsoft.epp.worktask.manager.CompletedWorkTaskManager;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.plugin.wflow.domain.WorkFlowModel;
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.common.assist.service.FreeMarkerService;

@Service 
public class InviterollrequServiceImpl extends BaseGenericServiceImpl<Inviterollrequ> implements InviterollrequService {

	@Autowired(required=true) @Qualifier("inviterollrequManagerImpl")
	private InviterollrequManager inviterollrequManager;

	@Autowired(required=true)  @Qualifier("inviterollrequDaoHibernate")
	private InviterollrequDao inviterollrequDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("projectDaoHibernate")
	private ProjectDao projectDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("inrqDetailDaoHibernate")
	private InrqDetailDao inrqDetailDaoHibernate;
	
	@Autowired(required=true) @Qualifier("freeMarkerServiceImpl")
	private FreeMarkerService freeMarkerService;
	
	@Autowired(required=true) @Qualifier("projectManagerImpl")
	private ProjectManager projectManager;
	
	@Autowired(required=true) @Qualifier("completedWorkTaskManagerImpl")
	private CompletedWorkTaskManager completedWorkTaskManager;
	/**
	 * 
	 * Description :保存邀请函 
	 * Create Date: 2010-8-17下午03:16:44 by liuke  Modified Date: 2010-8-17下午03:16:44 by liuke
	 * @param   String[] supplier 供应商数组  String projectId 项目主键
	 * @return  
	 * @Exception
	 */
	public Inviterollrequ saveInviterollrequ(String[] supplier, String projectId) throws Exception{
		logger.debug("\nes InviterollrequServiceImpl||saveInviterollrequ\n");
		Inviterollrequ inviterollrequ = inviterollrequDaoHibernate.getInviterollrequByProjectId(projectId,null);
		if(null==inviterollrequ){//如果项目对应的申报邀请函对象为空
			Inviterollrequ inviterollrequs = new Inviterollrequ();
			Project project = projectDaoHibernate.get(projectId);
			inviterollrequs.setProject(project);
			inviterollrequs.setProjCode(project.getProjCode());
			inviterollrequs.setProjName(project.getProjName());
			inviterollrequs.setInrqKind(InrqKindEnum.NORMAL); //设置邀请函种类为普通
			inviterollrequs.setSelectType(InrqSelectTypeEnum.BY_RANDOM_DRAW);//设置供应商选择种类为随机抽取
			inviterollrequDaoHibernate.save(inviterollrequs);
			inviterollrequ = inviterollrequs;
		}
		for(String supplierId :supplier){//保存邀请函对象
			InrqDetail inrqdetail = new InrqDetail();
			OrgInfo suppliers = (OrgInfo) inviterollrequManager.get(supplierId, OrgInfo.class);
			inrqdetail.setInviterollrequ(inviterollrequ);
			inrqdetail.setSupplier(suppliers);
			inrqdetail.setSupplierName(suppliers.getOrgName());
			inrqdetail.setInrqDKind(InrqKindEnum.NORMAL);//设置邀请函种类为普通
			inrqdetail.setUseStatus(CommonEnum.USER_STATUS_FORMAL);//使用状态为正式
			Project project = inviterollrequ.getProject();//保存附件
			OrgInfo agencies = project.getAgencies();
			Map<String ,Object> templateMap = new HashMap<String ,Object>();
			if (EbuyMethodEnum.SINGLE_SOURCE.equals(project.getEbuyMethod())) {//判断采购方式单一来源
				templateMap.put("title", "单一来源邀请函");
			}else {//判断采购方式邀请招标
				templateMap.put("title", "邀请招标邀请函");
			}
			//templateMap.put("supplier",suppliers.getOrgName());
			templateMap.put("project", project);
			templateMap.put("agentName",agencies.getOrgName());
			
			//更改邀请函 by yucy
			templateMap.put("projectRule",projectManager.getProjectRuleByProjectId(projectId));

			inrqdetail.setContents(freeMarkerService.getFreeMarkerContent("inrqDetail.ftl", templateMap));
			//创建附件对象
			String path=messageSource.getMessage("uploadUrl");
			String filePath=path+FileVirtualPathEnum.INRQDETAIL;
			FileUtil.mkdirs(filePath);
			String saveName=java.util.UUID.randomUUID().toString();
			FileUtils.writerFile(filePath+saveName,inrqdetail.getContents());
			Attachment atta = new Attachment();
			atta.setPath(FileVirtualPathEnum.INRQDETAIL);
			atta.setFileName(saveName);
			atta.setViewName(inrqdetail.getInrqDKindCN());
			atta.setCreateTime(new Date());
			this.save(atta, Attachment.class);
			inrqdetail.setInrqDContent(atta);
			inrqDetailDaoHibernate.save(inrqdetail);
		}
		User user=AuthenticationHelper.getCurrentUser();
		inviterollrequ.setUser(user);
		Project project = projectManager.get(inviterollrequ.getProject().getObjId());
		String parentBizId = project.getObjId();
		if(project.getParentId()!= null ){
			parentBizId = project.getParentId();
		}
		inviterollrequ.setParentBizId(parentBizId);
		completedWorkTaskManager.createCompTaskByPassivity("抽取供应商",CompletedWorkTaskTypeEnum.WORKTASK_TYPE_PURCHASEDOC,null,inviterollrequ.getObjId(),parentBizId,"",CompletedWorkTaskResultEnum.WORKTASK_SAVE);
		return inviterollrequ;
	}

	/**
	 * 
	 * Description :删除邀请函  
	 * Create Date: 2010-8-27下午04:56:59 by liuke  Modified Date: 2010-8-27下午04:56:59 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public void removeInviterollrequ(String projectId) {
		logger.debug("\nes InviterollrequServiceImpl||removeInviterollrequ\n");
		Inviterollrequ inviterollrequ = inviterollrequDaoHibernate.getInviterollrequByProjectId(projectId,null);
		//删除从表对象
		inrqDetailDaoHibernate.removeinrqDetailList(inviterollrequ.getObjId());
		this.remove(inviterollrequ.getObjId(), Inviterollrequ.class);
		
	}

	/**
	 * 
	 * Description :审核邀请函  
	 * Create Date: 2010-8-27下午04:56:59 by liuke  Modified Date: 2010-8-27下午04:56:59 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public Inviterollrequ audit(String objId, String status,String opinion) {
		logger.debug("\nes InviterollrequServiceImpl||audit\n");
		Inviterollrequ inviterollrequ = this.get(objId);
		this.save(inviterollrequ);
		String completedWorkTaskResult = "";
		if(CommonEnum.CONFIRM_STATUS_SURE.equals(status)) {//审核通过
			inviterollrequ.setWorkflowAuditStatus(WorkFlowModel.AUDIT_STATUS_PASS);
			inviterollrequ.setAuditStatus(InrqAuditStatusEnum.AUDIT_PASS);
			completedWorkTaskResult = CompletedWorkTaskResultEnum.WORKTASK_PASSAUDIT;
		}else{
			inviterollrequ.setWorkflowAuditStatus(WorkFlowModel.AUDIT_STATUS_NO_PASS);
			inviterollrequ.setAuditStatus(InrqAuditStatusEnum.AUDIT_NO_PASS);
			completedWorkTaskResult = CompletedWorkTaskResultEnum.WORKTASK_NOPASSAUDIT;
		}
		inviterollrequ.setUser(AuthenticationHelper.getCurrentUser());
		Project project = projectManager.get(inviterollrequ.getProject().getObjId());
		String parentBizId = project.getObjId();
		if(project.getParentId()!= null ){
			parentBizId = project.getParentId();
		}
		inviterollrequ.setParentBizId(parentBizId);
		completedWorkTaskManager.createCompTaskByPassivity("审核邀请函",CompletedWorkTaskTypeEnum.WORKTASK_TYPE_INVITEROLLREQU,opinion,inviterollrequ.getObjId(),parentBizId,"",completedWorkTaskResult);
		return inviterollrequ;
	}
	/**
	 * Description :  提交邀请函
	 * Create Date: 2010-10-17上午10:10:45 by shenjianzhuang  Modified Date: 2010-10-17上午10:10:45 by shenjianzhuang
	 * @param objId
	 * @return
	 * @return  Inviterollrequ
	 * @Exception	
	 */
	public Inviterollrequ updateInviterollrequ(String objId) {
		logger.debug("\nes InviterollrequServiceImpl||updateInviterollrequ\n");
		Inviterollrequ inviterollrequ = this.get(objId);
		inviterollrequ.setAuditStatus(InrqAuditStatusEnum.AUDIT_PASS);
		inviterollrequManager.save(inviterollrequ, Inviterollrequ.class);
		Project project = projectManager.get(inviterollrequ.getProject().getObjId());
		String parentBizId = project.getObjId();
		if(project.getParentId()!= null ){
			parentBizId = project.getParentId();
		}
		completedWorkTaskManager.createCompTaskByPassivity("提交邀请函",CompletedWorkTaskTypeEnum.WORKTASK_TYPE_INVITEROLLREQU,"",inviterollrequ.getObjId(),parentBizId,"",CompletedWorkTaskResultEnum.WORKTASK_SAVE);
		return inviterollrequ;
	}

	/**
	 * 
	 * Description :根据QueryObject对象获得邀请函数量  
	 * Create Date: 2010-10-25下午02:03:23 by liuke  Modified Date: 2010-10-25下午02:03:23 by liuke
	 * @param   QueryObject queryObject
	 * @return  BigDecimal
	 * @Exception
	 */
	@SuppressWarnings("unchecked")
	public BigDecimal getInviterollrequTotalByQueryObject(QueryObject queryObject) {
		logger.debug("\nes InviterollrequServiceImpl||getInviterollrequTotalByQueryObject\n");
		return inviterollrequDaoHibernate.getInviterollrequTotalByQueryObject(queryObject);
	}
	
	/**
	 * 
	 * Description :根据QueryObject对象获得邀请函列表
	 * Create Date: 2010-10-25下午02:03:23 by liuke  Modified Date: 2010-10-25下午02:03:23 by liuke
	 * @param   QueryObject queryObject
	 * @return  Page<Inviterollrequ>
	 * @Exception
	 */
	@SuppressWarnings("unchecked")
	public Page<Inviterollrequ> getInviterollrequByQueryObject(QueryObject queryObject, Page page) {
		logger.debug("\nes InviterollrequServiceImpl||getInviterollrequByQueryObject\n");
		return inviterollrequDaoHibernate.getInviterollrequByQueryObject(queryObject, page);
	}
	
	
	
	
	
	
}
