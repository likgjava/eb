package com.gpcsoft.epp.bulletin.service.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryObjectBase;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.DateUtil;
import com.gpcsoft.core.utils.FileUtil;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.bulletin.dao.BulletinDao;
import com.gpcsoft.epp.bulletin.domain.Bulletin;
import com.gpcsoft.epp.bulletin.domain.BulletinConfirmStatusEnum;
import com.gpcsoft.epp.bulletin.domain.BulletinTypeEnum;
import com.gpcsoft.epp.bulletin.domain.PubLishBulModel;
import com.gpcsoft.epp.bulletin.manager.BulletinManager;
import com.gpcsoft.epp.bulletin.manager.PublishBulletin;
import com.gpcsoft.epp.bulletin.service.BulletinService;
import com.gpcsoft.epp.common.domain.AuditStatusEnum;
import com.gpcsoft.epp.common.domain.FileVirtualPathEnum;
import com.gpcsoft.epp.common.domain.SeparationEnum;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.common.utils.FileUtils;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.domain.ProjectRule;
import com.gpcsoft.epp.project.manager.ProjectManager;
import com.gpcsoft.epp.signuprecord.dao.SignUpCondFactorDao;
import com.gpcsoft.epp.signuprecord.domain.SignUpCondFactor;
import com.gpcsoft.epp.worktask.domain.CompletedWorkTaskResultEnum;
import com.gpcsoft.epp.worktask.domain.CompletedWorkTaskTypeEnum;
import com.gpcsoft.epp.worktask.domain.CompletedWorkTaskURLEnum;
import com.gpcsoft.epp.worktask.manager.CompletedWorkTaskManager;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.plugin.wflow.domain.WorkFlowModel;
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.domain.ListBind;
import com.gpcsoft.srplatform.auth.domain.User;

@Service 
public class BulletinServiceImpl extends BaseGenericServiceImpl<Bulletin> implements BulletinService {

	@Autowired(required=true) @Qualifier("bulletinManagerImpl")
	private BulletinManager bulletinManager;
	
	@Autowired(required=true) @Qualifier("projectManagerImpl")
	private ProjectManager projectManager;
	
	@Autowired(required=true) @Qualifier("completedWorkTaskManagerImpl")
	private CompletedWorkTaskManager completedWorkTaskManager;
	
	@Autowired(required=true)  @Qualifier("bulletinDaoHibernate")
	private BulletinDao bulletinDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("signUpCondFactorDaoHibernate")
	private SignUpCondFactorDao signUpCondFactorDaoHibernate;
	
	private ProjectManager projManager;
	
	private ProjectManager getProjectManager(){
		if(null == this.projManager){
			this.projManager = (ProjectManager)FrameBeanFactory.getBean("projectManagerImpl");
		}
		return this.projManager;
	}

	/** 
	 * FuncName:getPurBulletinByProjectId
	 * Description :根据项目主键拿到采购公告
	 * @param projectId:项目主键
	 * @return Bulletin
	 * @author Administrator
	 * @Create Date: 2010-5-19下午01:53:18 
	 * @Modifier Administrator
	 * @Modified Date: 2010-5-19下午01:53:18  
	 */
	public Bulletin getPurBulletinByProjectId(String projectId) {
		logger.debug("\n BulletinServiceImpl||getPurBulletinByProjectId\n");
		QueryObject<Bulletin> queryObject = new QueryObjectBase<Bulletin>();
		queryObject.setEntityClass(Bulletin.class);
		queryObject.getQueryParam().and(new QueryParam("bullType",QueryParam.OPERATOR_EQ,BulletinTypeEnum.PURCHASE_BULLETIN));//公告类型为：采购公告
		queryObject.getQueryParam().and(new QueryParam("project.objId",QueryParam.OPERATOR_EQ,projectId));
		Bulletin bulletin = bulletinManager.getBulletinByQueryObject(queryObject);
		if(bulletin!=null&&bulletin.getContent()!=null){
			String filePath = messageSource.getMessage("uploadUrl");
			String bullContent = FileUtils.readFileByLines(filePath+bulletin.getContent().getPath()+bulletin.getContent().getFileName(), "UTF-8");//读取公告内容
			bulletin.setBullContents(bullContent);
		}
		return bulletin;
	}

	/** 
	 * FuncName:getResultBulletinByProjectId
	 * Description :  根据项目主键获取结果公告
	 * @param   projectId:项目主键
	 * @return  Bulletin
	 * @author yangx
	 * @Create Date: 2010-9-7下午02:15:48 
	 * @Modifier   yangx
	 * @Modified Date: 2010-9-7下午02:15:48 
	 */
	public Bulletin getResultBulletinByProjectId(String projectId) {
		logger.debug("\n BulletinServiceImpl||getResultBulletinByProjectId\n");
		QueryObject<Bulletin> queryObject = new QueryObjectBase<Bulletin>();
		queryObject.setEntityClass(Bulletin.class);
		queryObject.getQueryParam().and(new QueryParam("bullType",QueryParam.OPERATOR_EQ,BulletinTypeEnum.RESULT_BULLETIN));//公告类型为结果公告
		queryObject.getQueryParam().and(new QueryParam("project.objId",QueryParam.OPERATOR_EQ,projectId));
		Bulletin bulletin = bulletinManager.getBulletinByQueryObject(queryObject);
		if(bulletin!=null&&bulletin.getContent()!=null){
			String filePath = messageSource.getMessage("uploadUrl");
			String bullContent = FileUtils.readFileByLines(filePath+bulletin.getContent().getPath()+bulletin.getContent().getFileName(), "UTF-8");//读取公告内容
			bulletin.setBullContents(bullContent);
		}
		return bulletin;
	}
	
	/** 
	 * FuncName:getResultPublicityByProjectId
	 * Description :根据项目主键获取结果公示
	 * @param  projectId：项目主键
	 * @return  Bulletin
	 * @author yangx
	 * @Create Date: 2010-9-7下午10:06:09 
	 * @Modifier   yangx
	 * @Modified Date: 2010-9-7下午10:06:09     
	 */
	public Bulletin getResultPublicityByProjectId(String projectId) {
		logger.debug("\n BulletinServiceImpl||getResultPublicityByProjectId\n");
		QueryObject<Bulletin> queryObject = new QueryObjectBase<Bulletin>();
		queryObject.setEntityClass(Bulletin.class);
		queryObject.getQueryParam().and(new QueryParam("bullType",QueryParam.OPERATOR_EQ,BulletinTypeEnum.RESULT_PREVIEW));//公告类型为结果公示
		queryObject.getQueryParam().and(new QueryParam("project.objId",QueryParam.OPERATOR_EQ,projectId));
		Bulletin bulletin = bulletinManager.getBulletinByQueryObject(queryObject);
		if(bulletin!=null&&bulletin.getContent()!=null){
			String filePath = messageSource.getMessage("uploadUrl");
			String bullContent = FileUtils.readFileByLines(filePath+bulletin.getContent().getPath()+bulletin.getContent().getFileName(), "UTF-8");//读取公告内容
			bulletin.setBullContents(bullContent);
		}
		return bulletin;
	}
	
	@Override
	protected void onAfterSave(Bulletin baseobject) {
		super.onAfterSave(baseobject);
		baseobject.setBizId(baseobject.getObjId());
		baseobject.setParentBizId(baseobject.getObjId());
	}
	
	/** 
	 * FuncName:savePurBulletinForAudit
	 * Description :  审核招标公告
	 * @param ids:公告Id,passStatus:审核状态,opinion:意见
	 * @return Bulletin
	 * @author Administrator
	 * @Create Date: 2010-5-29下午02:11:37   
	 * @Modifier Administrator
	 * @Modified Date: 2010-5-29下午02:11:37 
	 */
	public Bulletin savePurBulletinForAudit(String ids, String passStatus,String opinion) {
		logger.debug("\n BulletinServiceImpl||savePurBulletinForAudit\n");
		Bulletin bulletin = null;
		String bizIds = "";
		String objIds[] = ids.split(",");
		for (String objId : objIds) {
			bulletin = bulletinManager.get(objId);
			bulletin.setRelUser(AuthenticationHelper.getCurrentUser(true).getEmp());
			bulletin.setOpinion(opinion);
			bulletin.setRelDate(new Date());
			bulletinManager.save(bulletin);
			bulletin.setAuditStatus(passStatus);//将此句放在save方法之后是为了在service里面不改变状态,而将改变状态操作放到决策类里  noted by zhouzhanghe at 2011.3.10
			Project project = projectManager.get(bulletin.getProject().getObjId());
			String parentBizId = project.getObjId();
			if (project.getParentId()!= null ) {
				parentBizId = project.getParentId();
			}
			bizIds = bizIds + parentBizId+",";
			if(bulletin.getBullType()!=null&&(BulletinTypeEnum.PURCHASE_BULLETIN).equals(bulletin.getBullType())){//判断是否为采购公告
				if((AuditStatusEnum.AUDIT_PASS).equals(bulletin.getAuditStatus())){
					if(bulletin.getSignUpSTime()!=null&&bulletin.getSignUpETime()!=null&&bulletin.getTenderStartTime()!=null&&bulletin.getTenderEndTime()!=null){//时间拷贝到项目中
						ProjectRule projectRule = projectManager.getProjectRuleByProjectId(parentBizId);
						projectRule.setSignUpETime(bulletin.getSignUpETime());
						projectRule.setSignUpSTime(bulletin.getSignUpSTime());
						projectRule.setSubmitETime(bulletin.getTenderEndTime());
						projectRule.setSubmitStTime(bulletin.getTenderStartTime());
						this.save(projectRule,ProjectRule.class);
					}
				}
			}
			String viewResultURL="";
			String taskName="";
			String processResult="";
			opinion=bulletin.getOpinion();
			taskName="审核招标公告";
			viewResultURL=CompletedWorkTaskURLEnum.WORKTASK_URL_BULLETIN_AUDIT;
			if(bulletin.getAuditStatus()!=null&&(AuditStatusEnum.AUDIT_PASS).equals(bulletin.getAuditStatus())){
				processResult=CompletedWorkTaskResultEnum.WORKTASK_PASSAUDIT;
				bulletin.setWorkflowAuditStatus(WorkFlowModel.AUDIT_STATUS_PASS);
			}else if(bulletin.getAuditStatus()!=null&&(AuditStatusEnum.AUDIT_NO_PASS).equals(bulletin.getAuditStatus())){
				processResult=CompletedWorkTaskResultEnum.WORKTASK_NOPASSAUDIT;
				bulletin.setWorkflowAuditStatus(WorkFlowModel.AUDIT_STATUS_NO_PASS);
			}
			completedWorkTaskManager.createCompTaskByPassivity(taskName,CompletedWorkTaskTypeEnum.WORKTASK_TYPE_BULLETIN,opinion,bulletin.getObjId(),bulletin.getProject().getObjId(),viewResultURL,processResult);//保存已办任务
		}
		bulletin.setUser(AuthenticationHelper.getCurrentUser());
		bulletin.setParentBizId(bizIds.substring(0,bizIds.length()-1));
		return bulletin;
	}
	
	/** 
	 * FuncName:saveVariationBulletinForAudit
	 * Description :审核更正公告
	 * @param   ids:公告主键,passStatus:审核状态
	 * @return  Bulletin
	 * @author yangx
	 * @Create Date: 2010-9-7下午12:41:48 
	 * @Modifier  yangx
	 * @Modified Date: 2010-9-7下午12:41:48     
	 */
	public Bulletin saveVariationBulletinForAudit(String ids, String passStatus){
		logger.debug("\n BulletinServiceImpl||saveVariationBulletinForAudit\n");
		Bulletin bulletin = null;
		String bizIds = "";
		String objIds[] = ids.split(",");
		for (String objId : objIds) {
			bulletin = bulletinManager.get(objId);
			bulletin.setAuditStatus(passStatus);
			bulletin.setRelUser(AuthenticationHelper.getCurrentUser(true).getEmp());
			bulletin.setRelDate(new Date());
			this.save(bulletin);
			String viewResultURL="";
			String taskName="";
			String processResult="";
			String opinion=bulletin.getOpinion();
			taskName="审核更正公告";
			if(bulletin.getAuditStatus()!=null&&(AuditStatusEnum.AUDIT_PASS).equals(bulletin.getAuditStatus())){
				processResult=CompletedWorkTaskResultEnum.WORKTASK_PASSAUDIT;
				bulletin.setWorkflowAuditStatus(WorkFlowModel.AUDIT_STATUS_PASS);
			}else if(bulletin.getAuditStatus()!=null&&(AuditStatusEnum.AUDIT_NO_PASS).equals(bulletin.getAuditStatus())){
				processResult=CompletedWorkTaskResultEnum.WORKTASK_NOPASSAUDIT;
				bulletin.setWorkflowAuditStatus(WorkFlowModel.AUDIT_STATUS_NO_PASS);
			}
			completedWorkTaskManager.createCompTaskByPassivity(taskName,CompletedWorkTaskTypeEnum.WORKTASK_TYPE_BULLETIN,opinion,bulletin.getObjId(),bulletin.getProject().getObjId(),viewResultURL,processResult);//保存已办任务
			Project project = projectManager.get(bulletin.getProject().getObjId());
			String parentBizId = project.getObjId();
			if(project.getParentId()!= null ){
				parentBizId = project.getParentId();
			}
			bizIds = bizIds+parentBizId+",";
		}
		bulletin.setUser(AuthenticationHelper.getCurrentUser(true));
		bulletin.setParentBizId(bizIds.substring(0, bizIds.length()-1));
		return bulletin;
	}
	
	/** 
	 * FuncName:saveResultBulletinForAudit
	 * Description :审核结果公告
	 * @param   ids:公告Id,passStatus:审核状态,opinion:意见
	 * @return  Bulletin
	 * @author yangx
	 * @Create Date: 2010-9-7下午12:41:48 
	 * @Modifier  yangx  
	 * @Modified Date: 2010-9-7下午12:41:48     
	 */
	public Bulletin saveResultBulletinForAudit(String ids, String passStatus,String opinion){
		logger.debug("\n BulletinServiceImpl||saveResultBulletinForAudit\n");
		Bulletin bulletin = null;
		String bizIds ="";
		String objIds[] = ids.split(",");
		for (String objId : objIds) {
			bulletin = bulletinManager.get(objId);
			bulletin.setRelUser(AuthenticationHelper.getCurrentUser(true).getEmp());
			bulletin.setOpinion(opinion);
			bulletin.setRelDate(new Date());
			bulletinManager.save(bulletin);
			bulletin.setAuditStatus(passStatus);//将此句放在save方法之后是为了在service里面不改变状态,而将改变状态操作放到决策类里  noted by zhouzhanghe at 2011.3.10 17:32
			
			String viewResultURL="";
			String taskName="";
			String processResult="";
			opinion=bulletin.getOpinion();
			taskName="审核结果公告";
			viewResultURL=CompletedWorkTaskURLEnum.WORKTASK_URL_RESULT_BULLETIN_AUDIT;
			if(bulletin.getAuditStatus()!=null&&(AuditStatusEnum.AUDIT_PASS).equals(bulletin.getAuditStatus())){
				processResult=CompletedWorkTaskResultEnum.WORKTASK_PASSAUDIT;
				bulletin.setWorkflowAuditStatus(WorkFlowModel.AUDIT_STATUS_PASS);
			}else if(bulletin.getAuditStatus()!=null&&(AuditStatusEnum.AUDIT_NO_PASS).equals(bulletin.getAuditStatus())){
				processResult=CompletedWorkTaskResultEnum.WORKTASK_NOPASSAUDIT;
				bulletin.setWorkflowAuditStatus(WorkFlowModel.AUDIT_STATUS_NO_PASS);
			}
			/** 保存已办任务 */
			completedWorkTaskManager.createCompTaskByPassivity(taskName,CompletedWorkTaskTypeEnum.WORKTASK_TYPE_BULLETIN,opinion,bulletin.getObjId(),bulletin.getProject().getObjId(),viewResultURL,processResult);
			Project project = projectManager.get(bulletin.getProject().getObjId());
			String parentBizId = project.getObjId();
			if(project.getParentId()!= null ){
				parentBizId = project.getParentId();
			}
			bizIds = bizIds+parentBizId+",";
		}
		bulletin.setParentBizId(bizIds.substring(0, bizIds.length()-1));
		bulletin.setUser(AuthenticationHelper.getCurrentUser());
		return bulletin;
	}
	
	/** 
	 * FuncName:getVariationBulletinListByProjectId
	 * Description: 根据项目Id获取审核通过的更正公告
	 * @param   projectId:项目主键
	 * @return  List<Bulletin>
	 * @author yangx
	 * @Create Date: 2010-9-7下午07:08:39 
	 * @Modifier yangx
	 * @Modified Date: 2010-9-7下午07:08:39     
	 */
	public List<Bulletin> getVariationBulletinListByProjectId(String projectId){
		logger.debug("\n BulletinServiceImpl||getVariationBulletinListByProjectId\n");
		QueryObject<Bulletin> queryObject = new QueryObjectBase<Bulletin>();
		queryObject.setEntityClass(Bulletin.class);
		queryObject.getQueryParam().and(new QueryParam("bullType",QueryParam.OPERATOR_EQ,BulletinTypeEnum.VARIATION_BULLETIN));//公告类型
		queryObject.getQueryParam().and(new QueryParam("auditStatus",QueryParam.OPERATOR_EQ,AuditStatusEnum.AUDIT_PASS));//状态
		queryObject.getQueryParam().and(new QueryParam("project.objId",QueryParam.OPERATOR_EQ,projectId));//项目Id
		return bulletinManager.findByQuery(queryObject);
	}

	/**
	 * 保存公告  
	 * Description :  
	 * Create Date: 2011-11-17下午03:08:17 by chenhongjun  
	 * Modified Date: 2011-11-17下午03:08:17 by chenhongjun
	 *@param bulletin  公告对象
	 *@param projProcessStatus  项目实施状态
	 *@param fileVirtualPath  公告文件类型路径 FileVirtualPathEnum枚举对象中获取
	 *@param completedWorkTaskType   完成工作任务类型 CompletedWorkTaskTypeEnum枚举对象中获取
	 *@return 
	 *下午03:08:17 
	 *Bulletin
	 */
	public Bulletin saveBulletin(Bulletin bulletin,String projProcessStatus,String fileVirtualPath,String completedWorkTaskType) {
		logger.debug("\n BulletinServiceImpl||saveBulletinAndProjSchedule\n");
		String filePath = messageSource.getMessage("uploadUrl");
		//String path=filePath+FileVirtualPathEnum.BULLETIN_PUR;
		String path=filePath+fileVirtualPath;
		FileUtil.mkdirs(path);
		String fileName = UUID.randomUUID().toString();
		//bulletin.setContent(getAttachment(FileVirtualPathEnum.BULLETIN_PUR, bulletin,fileName));
		if(bulletin.getContent()==null||bulletin.getContent().getPath()==null){
			FileUtil.mkdirs(path);
			bulletin.setContent(getAttachment(fileVirtualPath, bulletin,fileName));
		}
		bulletin.setRelDate(new Date());
		this.save(bulletin);
		FileUtils.writerFile(path+fileName,bulletin.getBullContents());//把公告内容写到指定文件中
		if(this.getProjectManager() != null){//若存在项目组件，则保存到项目表中
			if(bulletin.getSignUpETime()!=null&&bulletin.getSignUpSTime()!=null&&bulletin.getTenderStartTime()!=null&&bulletin.getTenderEndTime()!=null){
				Project project = this.getProjectManager().get(bulletin.getProject().getObjId());//将时间写入到项目里
				ProjectRule projectRule = projectManager.getProjectRuleByProjectId(bulletin.getProject().getObjId());
				projectRule.setSignUpETime(bulletin.getSignUpETime());
				projectRule.setSignUpSTime(bulletin.getSignUpSTime());
				projectRule.setSubmitETime(bulletin.getTenderEndTime());
				projectRule.setSubmitStTime(bulletin.getTenderStartTime());
				bulletinManager.save(projectRule,ProjectRule.class);
				projManager.saveProjProcessStatus(project.getObjId(),projProcessStatus);
			}
		}
		String viewResultURL="";
		String taskName="保存"+bulletin.getBullTypeCN();
		//completedWorkTaskManager.createCompTaskByPassivity(taskName,CompletedWorkTaskTypeEnum.WORKTASK_TYPE_BULLETIN,null,bulletin.getObjId(),bulletin.getProject().getObjId(),viewResultURL,CompletedWorkTaskResultEnum.WORKTASK_SAVE);//保存已办任务
		completedWorkTaskManager.createCompTaskByPassivity(taskName,completedWorkTaskType,null,bulletin.getObjId(),bulletin.getProject().getObjId(),viewResultURL,CompletedWorkTaskResultEnum.WORKTASK_SAVE);//保存已办任务
		return bulletin;
	}
	
	/**
	 * 
	 * Description :  保存提交公告 
	 * Create Date: 2011-11-17下午03:34:47 by chenhongjun  
	 * Modified Date: 2011-11-17下午03:34:47 by chenhongjun
	 *@param bulletin  公告对象
	 *@param projProcessStatus   计划进程状态
	 *@param checkValue   检查值 
	 *@param fileVirtualPath   公告文件存放路径
	 *@param completedWorkTaskType  完成的工作任务类型
	 *@return
	 *下午03:34:47 
	 *Bulletin
	 */
	public Bulletin saveSubmitBulletin(Bulletin bulletin,String projProcessStatus,String checkValue,String fileVirtualPath,String completedWorkTaskType){
		logger.debug("\n BulletinServiceImpl||saveSubmitBulletinAndProjSchedule\n");
		String filePath = messageSource.getMessage("uploadUrl");
		String path=filePath+fileVirtualPath;//FileVirtualPathEnum.BULLETIN_PUR;
		String taskName="提交"+bulletin.getBullTypeCN();
		if(BulletinConfirmStatusEnum.SURE.equals(checkValue)){
			taskName = "修改"+bulletin.getBullTypeCN();
		}
		if (bulletin.getContent()==null||bulletin.getContent().getPath()==null) {
			FileUtil.mkdirs(path);
			String fileName = UUID.randomUUID().toString();
			bulletin.setContent(getAttachment(fileVirtualPath, bulletin,fileName));
		}
		bulletin.setRelDate(new Date());
		this.save(bulletin);
		FileUtils.writerFile(path+bulletin.getContent().getFileName(),bulletin.getBullContents());//把公告内容写到指定文件中
		if(this.getProjectManager() != null){//若存在项目组件，则保存到项目表中
			if(bulletin.getSignUpETime()!=null&&bulletin.getSignUpSTime()!=null&&bulletin.getTenderStartTime()!=null&&bulletin.getTenderEndTime()!=null){
				//将时间写入到项目里
				ProjectRule projectRule = projectManager.getProjectRuleByProjectId(bulletin.getProject().getObjId());
				projectRule.setSignUpETime(bulletin.getSignUpETime());
				projectRule.setSignUpSTime(bulletin.getSignUpSTime());
				projectRule.setSubmitETime(bulletin.getTenderEndTime());
				projectRule.setSubmitStTime(bulletin.getTenderStartTime());
				bulletinManager.save(projectRule,ProjectRule.class);
				//projManager.saveProjProcessStatus(project.getObjId(),projProcessStatus);已将改变状态操作移至决策类里  noted by zhouzhanghe at 2011.3.10
			}
		}
		String viewResultURL=CompletedWorkTaskURLEnum.WORKTASK_URL_BULLETIN_SUBMIT;
		completedWorkTaskManager.createCompTaskByPassivity(taskName,completedWorkTaskType,null,bulletin.getObjId(),bulletin.getProject().getObjId(),viewResultURL,CompletedWorkTaskResultEnum.WORKTASK_SAVE);//保存已办任务
		bulletin.setUser(AuthenticationHelper.getCurrentUser());
		Project project = projectManager.get(bulletin.getProject().getObjId());
		String parentBizId = project.getObjId();
		if(project.getParentId()!= null ){
			parentBizId = project.getParentId();
		}
		bulletin.setParentBizId(parentBizId);
		return bulletin;
	}
	
	
	/** 
	 * FuncName:saveBulletinAndProjSchedule
	 * Description :  保存招标公告[同步保存时间规则]
	 * @param bulletin:公告对象,projProcessStatus:项目实施状态
	 * @return Bulletin
	 * @author Administrator
	 * @Create Date: 2010-6-5上午11:30:42 
	 * @Modifier Administrator
	 * @Modified Date: 2010-6-5上午11:30:42  
	 */
	public Bulletin saveBulletinAndProjSchedule(Bulletin bulletin,String projProcessStatus) {
		logger.debug("\n BulletinServiceImpl||saveBulletinAndProjSchedule\n");
		 
		String filePath = messageSource.getMessage("uploadUrl");
		String path=filePath+FileVirtualPathEnum.BULLETIN_PUR;
		FileUtil.mkdirs(path);
		String fileName = UUID.randomUUID().toString();
		bulletin.setContent(getAttachment(FileVirtualPathEnum.BULLETIN_PUR, bulletin,fileName));
		bulletin.setRelDate(new Date());
		this.save(bulletin);
		FileUtils.writerFile(path+fileName,bulletin.getBullContents());//把公告内容写到指定文件中
		if(this.getProjectManager() != null){//若存在项目组件，则保存到项目表中
			if(bulletin.getSignUpETime()!=null&&bulletin.getSignUpSTime()!=null&&bulletin.getTenderStartTime()!=null&&bulletin.getTenderEndTime()!=null){
				Project project = this.getProjectManager().get(bulletin.getProject().getObjId());//将时间写入到项目里
				ProjectRule projectRule = projectManager.getProjectRuleByProjectId(bulletin.getProject().getObjId());
				projectRule.setSignUpETime(bulletin.getSignUpETime());
				projectRule.setSignUpSTime(bulletin.getSignUpSTime());
				projectRule.setSubmitETime(bulletin.getTenderEndTime());
				projectRule.setSubmitStTime(bulletin.getTenderStartTime());
				//projectRule.setOpenBidSDate(bulletin.getOpenBidTime());
				//projectRule.setEvalSTime(bulletin.getEvalStartTime());
				bulletinManager.save(projectRule,ProjectRule.class);
				projManager.saveProjProcessStatus(project.getObjId(),projProcessStatus);
			}
		}
		String viewResultURL="";
		String taskName="保存招标公告";
		completedWorkTaskManager.createCompTaskByPassivity(taskName,CompletedWorkTaskTypeEnum.WORKTASK_TYPE_BULLETIN,null,bulletin.getObjId(),bulletin.getProject().getObjId(),viewResultURL,CompletedWorkTaskResultEnum.WORKTASK_SAVE);//保存已办任务
		
		return bulletin;
	}
	
	/** 
	 * FuncName:saveUpdateBulletinAndProjSchedule
	 * Description :保存修改招标公告[同步保存时间规则]
	 * @param   bulletin：公告;projProcessStatus：项目状态
	 * @return  Bulletin
	 * @author     yangx
	 * @Create Date: 2010-9-7下午01:32:09 
	 * @Modifier    yangx
	 * @Modified Date: 2010-9-7下午01:32:09   
	 */
	public Bulletin saveUpdateBulletinAndProjSchedule(Bulletin bulletin,String projProcessStatus){
		logger.debug("\n BulletinServiceImpl||saveUpdateBulletinAndProjSchedule\n");
		String filePath = messageSource.getMessage("uploadUrl");
		String path=filePath+FileVirtualPathEnum.BULLETIN_PUR;
		bulletin.setRelDate(new Date());
		this.save(bulletin);
		FileUtils.writerFile(path+bulletin.getContent().getFileName(),bulletin.getBullContents());//把公告内容写到指定文件中
		if(this.getProjectManager() != null){//若存在项目组件，则保存到项目表中
			if(bulletin.getSignUpETime()!=null&&bulletin.getSignUpSTime()!=null&&bulletin.getTenderStartTime()!=null&&bulletin.getTenderEndTime()!=null){
				Project project = this.getProjectManager().get(bulletin.getProject().getObjId());//将时间写入到项目里
				ProjectRule projectRule = projectManager.getProjectRuleByProjectId(bulletin.getProject().getObjId());
				projectRule.setSignUpETime(bulletin.getSignUpETime());
				projectRule.setSignUpSTime(bulletin.getSignUpSTime());
				projectRule.setSubmitETime(bulletin.getTenderEndTime());
				projectRule.setSubmitStTime(bulletin.getTenderStartTime());
				projectRule.setOpenBidSDate(bulletin.getOpenBidTime());
				projectRule.setEvalSTime(bulletin.getEvalStartTime());
				bulletinManager.save(projectRule,ProjectRule.class);
				projManager.saveProjProcessStatus(project.getObjId(),projProcessStatus);
			}
		}
		String viewResultURL="";
		String taskName="保存招标公告";
		completedWorkTaskManager.createCompTaskByPassivity(taskName,CompletedWorkTaskTypeEnum.WORKTASK_TYPE_BULLETIN,null,bulletin.getObjId(),bulletin.getProject().getObjId(),viewResultURL,CompletedWorkTaskResultEnum.WORKTASK_SAVE);//保存已办任务
		return bulletin;
	}
	
	/** 
	 * FuncName:saveSubmitBulletinAndProjSchedule
	 * Description :  保存提交招标公告[同步保存时间规则]
	 * @param   bulletin:公告,projProcessStatus:项目状态,checkValue:用以判断招标公告是否是被退回再提交
	 * @return  Bulletin
	 * @author yangx
	 * @Create Date: 2010-9-7上午11:57:51 
	 * @Modifier yangx
	 * @Modified Date: 2010-9-7上午11:57:51  
	 */
	public Bulletin saveSubmitBulletinAndProjSchedule(Bulletin bulletin,String projProcessStatus,String checkValue){
		logger.debug("\n BulletinServiceImpl||saveSubmitBulletinAndProjSchedule\n");
		String filePath = messageSource.getMessage("uploadUrl");
		String path=filePath+FileVirtualPathEnum.BULLETIN_PUR;
		String taskName="提交招标公告";
		if(BulletinConfirmStatusEnum.SURE.equals(checkValue)){
			taskName = "修改招标公告";
		}
		if (bulletin.getContent()==null||bulletin.getContent().getPath()==null) {
			FileUtil.mkdirs(path);
			String fileName = UUID.randomUUID().toString();
			bulletin.setContent(getAttachment(FileVirtualPathEnum.BULLETIN_PUR, bulletin,fileName));
		}
		bulletin.setRelDate(new Date());
		this.save(bulletin);
		FileUtils.writerFile(path+bulletin.getContent().getFileName(),bulletin.getBullContents());//把公告内容写到指定文件中
		if(this.getProjectManager() != null){//若存在项目组件，则保存到项目表中
			if(bulletin.getSignUpETime()!=null&&bulletin.getSignUpSTime()!=null&&bulletin.getTenderStartTime()!=null&&bulletin.getTenderEndTime()!=null){
				//将时间写入到项目里
				ProjectRule projectRule = projectManager.getProjectRuleByProjectId(bulletin.getProject().getObjId());
				projectRule.setSignUpETime(bulletin.getSignUpETime());
				projectRule.setSignUpSTime(bulletin.getSignUpSTime());
				projectRule.setSubmitETime(bulletin.getTenderEndTime());
				projectRule.setSubmitStTime(bulletin.getTenderStartTime());
				//projectRule.setOpenBidSDate(bulletin.getOpenBidTime());
				//projectRule.setEvalSTime(bulletin.getEvalStartTime());
				bulletinManager.save(projectRule,ProjectRule.class);
				//projManager.saveProjProcessStatus(project.getObjId(),projProcessStatus);已将改变状态操作移至决策类里  noted by zhouzhanghe at 2011.3.10
			}
		}
		String viewResultURL=CompletedWorkTaskURLEnum.WORKTASK_URL_BULLETIN_SUBMIT;
		completedWorkTaskManager.createCompTaskByPassivity(taskName,CompletedWorkTaskTypeEnum.WORKTASK_TYPE_BULLETIN,null,bulletin.getObjId(),bulletin.getProject().getObjId(),viewResultURL,CompletedWorkTaskResultEnum.WORKTASK_SAVE);//保存已办任务
		bulletin.setUser(AuthenticationHelper.getCurrentUser());
		Project project = projectManager.get(bulletin.getProject().getObjId());
		String parentBizId = project.getObjId();
		if(project.getParentId()!= null ){
			parentBizId = project.getParentId();
		}
		bulletin.setParentBizId(parentBizId);
		return bulletin;
	}
	
	/** 
	 * FuncName:saveResultBulletin
	 * Description :  保存结果公告
	 * @param   bulletin:公告对象,projProcessStatus:项目实施状态
	 * @return  Bulletin
	 * @author  yangx
	 * @Create Date: 2010-9-7下午02:25:35 
	 * @Modifier yangx
	 * @Modified Date: 2010-9-7下午02:25:35  
	 */
	public Bulletin saveResultBulletin(Bulletin bulletin,String projProcessStatus){
		logger.debug("\n BulletinServiceImpl||getVariationBulletinListByProjectId\n");
		String filePath = messageSource.getMessage("uploadUrl");
		String path=filePath+FileVirtualPathEnum.BULLETIN_RESULT;
		FileUtil.mkdirs(path);
		String fileName = UUID.randomUUID().toString();
		bulletin.setContent(getAttachment(FileVirtualPathEnum.BULLETIN_RESULT, bulletin,fileName));
		bulletin.setRelDate(new Date());
		this.save(bulletin);
		FileUtils.writerFile(path+fileName,bulletin.getBullContents());//把公告内容写到指定文件中
		if(projProcessStatus!=null&&!"".equals(projProcessStatus)){//项目实施状态是否存在
			this.getProjectManager().saveProjProcessStatus(bulletin.getProject().getObjId(),projProcessStatus);//保存项目实施状态
		}
		String viewResultURL="";
		String taskName="保存结果公告";
		completedWorkTaskManager.createCompTaskByPassivity(taskName,CompletedWorkTaskTypeEnum.WORKTASK_TYPE_BULLETIN,null,bulletin.getObjId(),bulletin.getProject().getObjId(),viewResultURL,CompletedWorkTaskResultEnum.WORKTASK_SAVE);//保存已办任务
		return bulletin;
	}
	
	/** 
	 * FuncName:saveSubmitResultBulletin
	 * Description :  提交结果公告
	 * @param   bulletin:公告对象,projProcessStatus:项目实施状态
	 * @return  Bulletin
	 * @author  yangx
	 * @Create Date: 2010-9-7下午02:25:35 
	 * @Modifier  yangx
	 * @Modified Date: 2010-9-7下午02:25:35 
	 */
	public Bulletin saveSubmitResultBulletin(Bulletin bulletin,String projProcessStatus){
		logger.debug("\n BulletinServiceImpl||saveSubmitResultBulletin\n");
		String filePath = messageSource.getMessage("uploadUrl");
		String path=filePath+FileVirtualPathEnum.BULLETIN_RESULT;
		if (bulletin.getContent()==null) {
			FileUtil.mkdirs(path);
			String fileName = UUID.randomUUID().toString();
			bulletin.setContent(getAttachment(FileVirtualPathEnum.BULLETIN_RESULT, bulletin,fileName));
		}
		bulletin.setRelDate(new Date());
		this.save(bulletin);
		FileUtils.writerFile(path+bulletin.getContent().getFileName(),bulletin.getBullContents());//把公告内容写到指定文件中
		/* 已将改变状态操作移至决策类里  noted by zhouzhanghe at 2011.3.10 17:29
		 * if(projProcessStatus!=null&&!"".equals(projProcessStatus)){//项目实施状态是否存在
			this.getProjectManager().saveProjProcessStatus(bulletin.getProject().getObjId(),projProcessStatus);//保存项目实施状态
		}*/
		String viewResultURL=CompletedWorkTaskURLEnum.WORKTASK_URL_RESULT_BULLETIN_SUBMIT;
		String taskName="提交结果公告";
		completedWorkTaskManager.createCompTaskByPassivity(taskName,CompletedWorkTaskTypeEnum.WORKTASK_TYPE_BULLETIN,null,bulletin.getObjId(),bulletin.getProject().getObjId(),viewResultURL,CompletedWorkTaskResultEnum.WORKTASK_SAVE);//保存已办任务
		User user = AuthenticationHelper.getCurrentUser();
		bulletin.setUser(user);
		Project pack = projectManager.get(bulletin.getProject().getObjId());
		String prjoectId;
		if (pack.getParentId()==null) {
			prjoectId = pack.getObjId();
		} else {
			prjoectId = pack.getParentId();
		}
		bulletin.setParentBizId(prjoectId);
		return bulletin;
	}
	
	/** 
	 * FuncName:saveUpdateResultBulletin
	 * Description :  保存修改结果公告
	 * @param   bulletin:公告对象,projProcessStatus:项目实施状态
	 * @return  Bulletin
	 * @author yangx
	 * @Create Date: 2010-9-7下午02:25:35 
	 * @Modifier yangx
	 * @Modified Date: 2010-9-7下午02:25:35  
	 */
	public Bulletin saveUpdateResultBulletin(Bulletin bulletin,String projProcessStatus){
		logger.debug("\n BulletinServiceImpl||saveUpdateResultBulletin\n");
		String filePath = messageSource.getMessage("uploadUrl");
		String path=filePath+FileVirtualPathEnum.BULLETIN_RESULT;
		bulletin.setRelDate(new Date());
		this.save(bulletin);
		FileUtils.writerFile(path+bulletin.getContent().getFileName(),bulletin.getBullContents());//把公告内容写到指定文件中
		if(projProcessStatus!=null&&!"".equals(projProcessStatus)){//项目实施状态是否存在
			this.getProjectManager().saveProjProcessStatus(bulletin.getProject().getObjId(),projProcessStatus);//保存项目实施状态
		}
		String viewResultURL="";
		String taskName="保存修改结果公告";
		completedWorkTaskManager.createCompTaskByPassivity(taskName,CompletedWorkTaskTypeEnum.WORKTASK_TYPE_BULLETIN,null,bulletin.getObjId(),bulletin.getProject().getObjId(),viewResultURL,CompletedWorkTaskResultEnum.WORKTASK_SAVE);//保存已办任务
		return bulletin;
	}
	
	/** 
	 * FuncName:saveVariationBulletin
	 * Description :  保存更正公告
	 * @param   bulletin:公告对象,projProcessStatus:项目实施状态
	 * @return  Bulletin
	 * @author yangx
	 * @Create Date: 2010-9-7下午02:25:35 
	 * @Modifier yangx
	 * @Modified Date: 2010-9-7下午02:25:35   
	 */
	public Bulletin saveVariationBulletin(Bulletin bulletin,String projProcessStatus){
		logger.debug("\n BulletinServiceImpl||saveUpdateResultBulletin\n");
		String filePath = messageSource.getMessage("uploadUrl");
		String path=filePath+FileVirtualPathEnum.BULLETIN_VARIATION;
		FileUtil.mkdirs(path);
		String fileName = UUID.randomUUID().toString();
		bulletin.setContent(getAttachment(FileVirtualPathEnum.BULLETIN_VARIATION, bulletin,fileName));
		bulletin.setRelDate(new Date());
		this.save(bulletin);
		FileUtils.writerFile(path+fileName,bulletin.getBullContents());//把公告内容写到指定文件中
		if(projProcessStatus!=null&&!"".equals(projProcessStatus)){//项目实施状态是否存在
			this.getProjectManager().saveProjProcessStatus(bulletin.getProject().getObjId(),projProcessStatus);//保存项目实施状态
		}
		String viewResultURL="";
		String taskName="保存更正公告";
		completedWorkTaskManager.createCompTaskByPassivity(taskName,CompletedWorkTaskTypeEnum.WORKTASK_TYPE_BULLETIN,null,bulletin.getObjId(),bulletin.getProject().getObjId(),viewResultURL,CompletedWorkTaskResultEnum.WORKTASK_SAVE);//保存已办任务
		return bulletin;
	}

	/** 
	 * FuncName:saveUpdateVariationBulletin
	 * Description :  保存修改更正公告
	 * @param   bulletin:公告对象,projProcessStatus:项目实施状态
	 * @return  Bulletin
	 * @author yangx
	 * @Create Date: 2010-9-7下午06:31:08    
	 * @Modifier yangx
	 * @Modified Date: 2010-9-7下午06:31:08  
	 */
	public Bulletin saveUpdateVariationBulletin(Bulletin bulletin,String projProcessStatus) {
		logger.debug("\n BulletinServiceImpl||saveUpdateResultBulletin\n");
		String filePath = messageSource.getMessage("uploadUrl");
		bulletin.setRelDate(new Date());
		this.save(bulletin);
		FileUtils.writerFile(filePath+bulletin.getContent().getPath()+bulletin.getContent().getFileName(),bulletin.getBullContents());//把公告内容写到指定文件中
		if(projProcessStatus!=null&&!"".equals(projProcessStatus)){//项目实施状态是否存在
			this.getProjectManager().saveProjProcessStatus(bulletin.getProject().getObjId(),projProcessStatus);//保存项目实施状态
		}
		String viewResultURL="";
		String taskName="保存修改更正公告";
		completedWorkTaskManager.createCompTaskByPassivity(taskName,CompletedWorkTaskTypeEnum.WORKTASK_TYPE_BULLETIN,null,bulletin.getObjId(),bulletin.getProject().getObjId(),viewResultURL,CompletedWorkTaskResultEnum.WORKTASK_SAVE);//保存已办任务
		return bulletin;
	}
	
	/** 
	 * FuncName:saveSubmitVariationBulletin
	 * Description:提交更正公告
	 * @param   bulletin:公告对象,projProcessStatus:项目实施状态
	 * @return  Bulletin
	 * @author yangx
	 * @Create Date: 2010-9-7下午02:25:35 
	 * @Modifier  yangx
	 * @Modified Date: 2010-9-7下午02:25:35
	 */
	public Bulletin saveSubmitVariationBulletin(Bulletin bulletin,String projProcessStatus){
		logger.debug("\n BulletinServiceImpl||saveSubmitVariationBulletin\n");
		String filePath = messageSource.getMessage("uploadUrl");
		String path=filePath+FileVirtualPathEnum.BULLETIN_VARIATION;
		if (bulletin.getContent()==null||bulletin.getContent().getPath()==null) {
			FileUtil.mkdirs(path);
			String fileName = UUID.randomUUID().toString();
			bulletin.setContent(getAttachment(FileVirtualPathEnum.BULLETIN_VARIATION, bulletin,fileName));
		}
		bulletin.setRelDate(new Date());
		this.save(bulletin);
		FileUtils.writerFile(path+bulletin.getContent().getFileName(),bulletin.getBullContents());//把公告内容写到指定文件中
		if(projProcessStatus!=null&&!"".equals(projProcessStatus)){//项目实施状态是否存在
			this.getProjectManager().saveProjProcessStatus(bulletin.getProject().getObjId(),projProcessStatus);//保存项目实施状态
		}
		String viewResultURL=CompletedWorkTaskURLEnum.WORKTASK_URL_RESULT_BULLETIN_SUBMIT;
		String taskName="提交更正公告";
		completedWorkTaskManager.createCompTaskByPassivity(taskName,CompletedWorkTaskTypeEnum.WORKTASK_TYPE_BULLETIN,null,bulletin.getObjId(),bulletin.getProject().getObjId(),viewResultURL,CompletedWorkTaskResultEnum.WORKTASK_SAVE);//保存已办任务
		return bulletin;
	}
	
	/** 
	 * FuncName:getBulletinByObjId
	 * Description :  根据公告ID获取公告
	 * @param   objId:公告主键
	 * @return  Bulletin
	 * @author yangx
	 * @Create Date: 2010-6-21上午09:56:48  
	 * @Modifier yangx
	 * @Modified Date: 2010-6-21上午09:56:48 
	 */	
	public Bulletin getBulletinByObjId(String objId){
		logger.debug("\n BulletinServiceImpl||getBulletinByObjId\n");
		Bulletin bulletin = bulletinManager.get(objId);
		String filePath = messageSource.getMessage("uploadUrl");
		if(bulletin!=null&&bulletin.getContent()!=null){
			String bullContent = FileUtils.readFileByLines(filePath+bulletin.getContent().getPath()+bulletin.getContent().getFileName(), "UTF-8");//读取公告内容
			bulletin.setBullContents(bullContent);
		}
		return bulletin;
	}
	
	/** 
	 * FuncName: getBulletinByProjectId
	 * Description :根据项目主键、公告类型获取公告
	 * @param   projectId:项目主键,bullType:公告类型
	 * @return  Bulletin
	 * @author yangx
	 * @Create Date: 2010-6-21上午09:56:48 
	 * @Modifier  yangx
	 * @Modified Date: 2010-6-21上午09:56:48    
	 */	
	public Bulletin getBulletinByProjectId(String projectId,String bullType){
		logger.debug("\n BulletinServiceImpl||getBulletinByObjId\n");
		QueryObject<Bulletin> queryObject = new QueryObjectBase<Bulletin>();
		queryObject.setEntityClass(Bulletin.class);
		queryObject.getQueryParam().and(new QueryParam("project.objId",QueryParam.OPERATOR_EQ,projectId));
		if(bullType!=null&&!"".equals(bullType)){
			queryObject.getQueryParam().and(new QueryParam("bullType",QueryParam.OPERATOR_EQ,bullType));
		}
		Bulletin bulletin = bulletinManager.getBulletinByQueryObject(queryObject);
		if(bulletin!=null&&bulletin.getContent()!=null){
			String filePath = messageSource.getMessage("uploadUrl");
			String bullContent = FileUtils.readFileByLines(filePath+bulletin.getContent().getPath()+bulletin.getContent().getFileName(), "UTF-8");//读取公告内容
			bulletin.setBullContents(bullContent);
		}
		return bulletin;
	}

	/**
	 * FuncName:getBulletinTotalByQueryObject
	 * Description :  根据查询条件统计对应的公告数据
	 * @param queryObject[buyerId:采购人ID;bullType:公告类型;auditStatus:审核状态;monitorId:监管人]公告类型一定是第一个参数
	 * @return	BigDecimal
	 * @author Administrator
	 * @Create Date: 2010-7-7下午01:56:46 
	 * @Modifier  Administrator
	 * @Modified Date: 2010-7-7下午01:56:46  
	 */
	@SuppressWarnings("unchecked")
	public BigDecimal getBulletinTotalByQueryObject(QueryObject queryObject) {
		logger.debug("\n BulletinServiceImpl||getBulletinTotalByQueryObject\n");
		return bulletinManager.getBulletinTotalByQueryObject(queryObject);
	}
	
	/** 
	 * FuncName:getBulletinListByObject
	 * Description :  根据条件获取公告列表
	 * @param 	page:公告分页对象,queryObject:查询条件
	 * @return  Page<Bulletin>
	 * @author yangx
	 * @Create Date: 2010-7-8上午10:26:13 
	 * @Modifier   yangx
	 * @Modified Date: 2010-7-8上午10:26:13 
	 */
	@SuppressWarnings("unchecked")
	public Page<Bulletin> getBulletinListByObject(Page<Bulletin> page,QueryObject queryObject){
		logger.debug("\n=BulletinServiceImpl||getBulletinListByObject\n");
		return bulletinManager.getBulletinListByObject(page, queryObject);
	}

	/** 
	 * FuncName:getBulletinListForRel
	 * Description :  获取待发送的公告列表
	 * Create Date: 2011-1-15下午12:10:05 by yangx  Modified Date: 2011-1-15下午12:10:05 by yangx
	 * @param   page:公告分页对象,queryObject:查询条件
	 * @return  Page<Bulletin>
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public Page<Bulletin> getBulletinListForRel(Page<Bulletin> page,QueryObject queryObject){
		logger.debug("\n=BulletinServiceImpl||getBulletinListForRel\n");
		return bulletinDaoHibernate.getBulletinListForRel(page, queryObject);
	}
	
	/**
	 * FuncName:getInqpBulletinByProjectId
	 * Description : 根据项目ID获取询价公告 
	 * @param   projectId:项目主键
	 * @return  Bulletin
	 * @author liuke
	 * @Create Date: 2010-8-3下午05:17:35
	 * @Modifier liuke
	 * @Modified Date: 2010-8-3下午05:17:35 
	 */
	public Bulletin getInqpBulletinByProjectId(String projectId) {
		logger.debug("\n BulletinServiceImpl||getInqpBulletinByProjectId\n");
		QueryObject<Bulletin> queryObject = new QueryObjectBase<Bulletin>();
		queryObject.setEntityClass(Bulletin.class);
		queryObject.getQueryParam().and(new QueryParam("bullType",QueryParam.OPERATOR_EQ,BulletinTypeEnum.INQPBULLETIN_BULLETIN));//公告类型为：询价公告
		queryObject.getQueryParam().and(new QueryParam("project.objId",QueryParam.OPERATOR_EQ,projectId));
		Bulletin bulletin = bulletinManager.getBulletinByQueryObject(queryObject);
		if(bulletin!=null&&bulletin.getContent()!=null){
			String filePath = messageSource.getMessage("uploadUrl");
			String bullContent = FileUtils.readFileByLines(filePath+bulletin.getContent().getPath()+bulletin.getContent().getFileName(), null);//读取公告内容
			bulletin.setBullContents(bullContent);
		}
		return bulletin;
	}

	/**
	 * FuncName:saveInqpBulletinForAudit
	 * Description :审核询价公告  
	 * @param  bulletin:询价公告对象, user:用户对象
	 * @return  Bulletin
	 * @author liuke
	 * @Create Date: 2010-8-3下午05:52:20 
	 * @Modifier   liuke
	 * @Modified Date: 2010-8-3下午05:52:20 
	 */
	public Bulletin saveInqpBulletinForAudit(Bulletin bulletin, User user) {
		logger.debug("\n BulletinServiceImpl||getInqpBulletinByProjectId\n");
		bulletin.setRelUser(user.getEmp());
		bulletin.setRelDate(new Date());
		this.save(bulletin);
		if(bulletin.getBullType()!=null&&(BulletinTypeEnum.INQPBULLETIN_BULLETIN).equals(bulletin.getBullType())){//判断是否为采购公告
		if((AuditStatusEnum.AUDIT_PASS).equals(bulletin.getAuditStatus())){
			if(bulletin.getSignUpSTime()!=null&&bulletin.getSignUpETime()!=null&&bulletin.getTenderStartTime()!=null&&bulletin.getTenderEndTime()!=null){//时间拷贝到项目中
				ProjectRule projectRule = projectManager.getProjectRuleByProjectId(bulletin.getProject().getObjId());
				projectRule.setSignUpETime(bulletin.getSignUpETime());
				projectRule.setSignUpSTime(bulletin.getSignUpSTime());
				projectRule.setSubmitETime(bulletin.getTenderEndTime());
				projectRule.setSubmitStTime(bulletin.getTenderStartTime());
				bulletinManager.save(projectRule,ProjectRule.class);
			}
			}
		}	
		User users=AuthenticationHelper.getCurrentUser();
		bulletin.setUser(users);
		Project project = projectManager.get(bulletin.getProject().getObjId());
		String parentBizId = project.getObjId();
		if(project.getParentId()!= null ){
			parentBizId = project.getParentId();
		}
		bulletin.setBizId(bulletin.getObjId());
		bulletin.setParentBizId(parentBizId);
		String completedWorkTaskResult = "";
		if(null!=bulletin.getAuditStatus()&&AuditStatusEnum.AUDIT_PASS.equals(bulletin.getAuditStatus())){
			completedWorkTaskResult = CompletedWorkTaskResultEnum.WORKTASK_PASSAUDIT;
			bulletin.setWorkflowAuditStatus(WorkFlowModel.AUDIT_STATUS_PASS);
		}else if(null!=bulletin.getAuditStatus()&&AuditStatusEnum.AUDIT_NO_PASS.equals(bulletin.getAuditStatus())){
			completedWorkTaskResult = CompletedWorkTaskResultEnum.WORKTASK_NOPASSAUDIT;
			bulletin.setWorkflowAuditStatus(WorkFlowModel.AUDIT_STATUS_NO_PASS);
		}
		String taskName="审核询价公告";//保存已办任务
		completedWorkTaskManager.createCompTaskByPassivity(taskName,CompletedWorkTaskTypeEnum.WORKTASK_TYPE_BULLETIN,bulletin.getOpinion(),bulletin.getObjId(),bulletin.getProject().getObjId(),null,completedWorkTaskResult);
		return bulletin;
	}
	
	/** 
	 * FuncName:saveResultPublicity
	 * Description :保存结果公示
	 * @param   bulletin:结果公示,projProcessStatus:项目实施状态
	 * @return  Bulletin
	 * @author yangx
	 * @Create Date: 2010-9-7下午10:24:34 
	 * @Modifier yangx   
	 * @Modified Date: 2010-9-7下午10:24:34 by   
	 */
	public Bulletin saveResultPublicity(Bulletin bulletin,String projProcessStatus){
		logger.debug("\n BulletinServiceImpl||saveResultPublicity\n");
		String filePath = messageSource.getMessage("uploadUrl");
		String path=filePath+FileVirtualPathEnum.BULLETIN_RESULTPUBLICITY;
		FileUtil.mkdirs(path);
		String fileName = UUID.randomUUID().toString();
		bulletin.setContent(getAttachment(FileVirtualPathEnum.BULLETIN_RESULTPUBLICITY, bulletin,fileName));
		bulletin.setRelDate(new Date());
		this.save(bulletin);
		FileUtils.writerFile(path+fileName,bulletin.getBullContents());//把公告内容写到指定文件中
		if(projProcessStatus!=null&&!"".equals(projProcessStatus)){//项目实施状态是否存在
			this.getProjectManager().saveProjProcessStatus(bulletin.getProject().getObjId(),projProcessStatus);//保存项目实施状态
		}
		String viewResultURL="";
		String taskName="保存结果公示";
		completedWorkTaskManager.createCompTaskByPassivity(taskName,CompletedWorkTaskTypeEnum.WORKTASK_TYPE_BULLETIN,null,bulletin.getObjId(),bulletin.getProject().getObjId(),viewResultURL,CompletedWorkTaskResultEnum.WORKTASK_SAVE);
		return bulletin;
	}
	
	/** 
	 * FuncName:saveUpdateResultPublicity
	 * Description :保存修改结果公示
	 * @param   bulletin:公告对象,projProcessStatus:项目实施状态
	 * @return  Bulletin
	 * @author yangx
	 * @Create Date: 2010-9-7下午10:24:34 
	 * @Modifier  yangx
	 * @Modified Date: 2010-9-7下午10:24:34 
	 */
	public Bulletin saveUpdateResultPublicity(Bulletin bulletin,String projProcessStatus){
		logger.debug("\n BulletinServiceImpl||saveUpdateResultPublicity\n");
		String filePath = messageSource.getMessage("uploadUrl");
		bulletin.setRelDate(new Date());
		this.save(bulletin);
		FileUtils.writerFile(filePath+bulletin.getContent().getPath()+bulletin.getContent().getFileName(),bulletin.getBullContents());//把公告内容写到指定文件中
		if(projProcessStatus!=null&&!"".equals(projProcessStatus)){//项目实施状态是否存在
			this.getProjectManager().saveProjProcessStatus(bulletin.getProject().getObjId(),projProcessStatus);//保存项目实施状态
		}
		String viewResultURL="";
		String taskName="保存修改结果公示";
		completedWorkTaskManager.createCompTaskByPassivity(taskName,CompletedWorkTaskTypeEnum.WORKTASK_TYPE_BULLETIN,null,bulletin.getObjId(),bulletin.getProject().getObjId(),viewResultURL,CompletedWorkTaskResultEnum.WORKTASK_SAVE);
		return bulletin;
	}
	
	/** 
	 * FuncName:saveSubmitResultPublicity
	 * Description :  提交结果公示
	 * @param   bulletin:公告对象,projProcessStatus:项目实施状态
	 * @return  Bulletin
	 * @author yangx
	 * @Create Date: 2010-9-7下午10:24:34
	 * @Modifier  yangx
	 * @Modified Date: 2010-9-7下午10:24:34   
	 */
	public Bulletin saveSubmitResultPublicity(Bulletin bulletin,String projProcessStatus){
		logger.debug("\n BulletinServiceImpl||saveSubmitResultPublicity\n");
		String filePath = messageSource.getMessage("uploadUrl");
		String path=filePath+FileVirtualPathEnum.BULLETIN_RESULTPUBLICITY;
		if (bulletin.getContent()==null||bulletin.getContent().getPath()==null) {
			FileUtil.mkdirs(path);
			String fileName = UUID.randomUUID().toString();
			bulletin.setContent(getAttachment(FileVirtualPathEnum.BULLETIN_RESULTPUBLICITY, bulletin,fileName));
		}
		bulletin.setRelDate(new Date());
		this.save(bulletin);
		FileUtils.writerFile(path+bulletin.getContent().getFileName(),bulletin.getBullContents());//把公告内容写到指定文件中
		if(projProcessStatus!=null&&!"".equals(projProcessStatus)){//项目实施状态是否存在
			this.getProjectManager().saveProjProcessStatus(bulletin.getProject().getObjId(),projProcessStatus);//保存项目实施状态
		}
		String viewResultURL=CompletedWorkTaskURLEnum.WORKTASK_URL_RESULT_PREVIEW_SUBMIT;
		String taskName="提交结果公示";
		completedWorkTaskManager.createCompTaskByPassivity(taskName,CompletedWorkTaskTypeEnum.WORKTASK_TYPE_BULLETIN,null,bulletin.getObjId(),bulletin.getProject().getObjId(),viewResultURL,CompletedWorkTaskResultEnum.WORKTASK_SAVE);
		bulletin.setUser(AuthenticationHelper.getCurrentUser());
		Project project = projectManager.get(bulletin.getProject().getObjId());
		String parentBizId = project.getObjId();
		if(project.getParentId()!= null ){
			parentBizId = project.getParentId();
		}
		bulletin.setParentBizId(parentBizId);
		return bulletin;
	}
	
	/** 
	 * FuncName:saveResultPublicityForAudit
	 * Description :审核结果公示 
	 * @param   bulletin:结果公示,user:用户对象
	 * @return  Bulletin
	 * @author yangx
	 * @Create Date: 2010-9-7下午10:55:21  
	 * @Modifier yangx
	 * @Modified Date: 2010-9-7下午10:55:21    
	 */
	public Bulletin saveResultPublicityForAudit(Bulletin bulletin, User user){
		logger.debug("\n BulletinServiceImpl||saveResultPublicityForAudit\n");
		bulletin.setRelUser(user.getEmp());
		bulletin.setRelDate(new Date());
		this.save(bulletin);
		bulletin.setBizId(bulletin.getObjId());
		bulletin.setParentBizId(bulletin.getObjId());
		String viewResultURL="";
		String taskName="";
		String processResult="";
		String opinion=bulletin.getOpinion();
		taskName="审核结果公示 ";
		viewResultURL=CompletedWorkTaskURLEnum.WORKTASK_URL_RESULT_PREVIEW_AUDIT;
		if(bulletin.getAuditStatus()!=null&&(AuditStatusEnum.AUDIT_PASS).equals(bulletin.getAuditStatus())){
			processResult=CompletedWorkTaskResultEnum.WORKTASK_PASSAUDIT;
			bulletin.setWorkflowAuditStatus(WorkFlowModel.AUDIT_STATUS_PASS);
		}else if(bulletin.getAuditStatus()!=null&&(AuditStatusEnum.AUDIT_NO_PASS).equals(bulletin.getAuditStatus())){
			processResult=CompletedWorkTaskResultEnum.WORKTASK_NOPASSAUDIT;
			bulletin.setWorkflowAuditStatus(WorkFlowModel.AUDIT_STATUS_NO_PASS);
		}
		/** 保存已办任务 */
		completedWorkTaskManager.createCompTaskByPassivity(taskName,CompletedWorkTaskTypeEnum.WORKTASK_TYPE_BULLETIN,opinion,bulletin.getObjId(),bulletin.getProject().getObjId(),viewResultURL,processResult);
		bulletin.setUser(AuthenticationHelper.getCurrentUser());
		Project project = projectManager.get(bulletin.getProject().getObjId());
		String parentBizId = project.getObjId();
		if(project.getParentId()!= null ){
			parentBizId = project.getParentId();
		}
		bulletin.setParentBizId(parentBizId);
		return bulletin;		
	}
	
	/** 
	 * FuncName: relBulletin
	 * Description :  发布公告
	 * @param   
	 * @return  void
	 * @author yangx
	 * @Create Date: 2010-8-24下午03:12:50 
	 * @Modifier  yangx
	 * @Modified Date: 2010-8-24下午03:12:50 
	 */
	public void relBulletin(){
		logger.debug("\n BulletinServiceImpl||relBulletin\n");
		String bullType = messageSource.getMessage("bullType");
		String basePath = messageSource.getMessage("uploadUrl");
		String publishBulletinKey = messageSource.getMessage("publishBulletinKey");//获取发布清单
		bullType = bullType.replaceAll(",","','");
		List<Bulletin> list = bulletinDaoHibernate.getBulletinByTypeAndAuditStatus(bullType);//根据公告类型查询审核通过且没有发布过的公告
		if (list!=null&&list.size()>0) {//判断是否有要发布的公告
		if (publishBulletinKey!=null&&!"".equals(publishBulletinKey)) {
			String[] publishBulletinKeys = publishBulletinKey.split(SeparationEnum.NUM);
			for (String className:publishBulletinKeys) {//循环要发布的清单
				PublishBulletin publishBulletin = null;
				try {
					publishBulletin = (PublishBulletin)Class.forName(className).newInstance();//获取发布实现类
					for (int i=0;i<list.size();i++) {
						PubLishBulModel pubLishBulModel = new PubLishBulModel();
						Bulletin bulletin = list.get(i);
						String bullContent = FileUtils.readFileByLines(basePath+bulletin.getContent().getPath()+bulletin.getContent().getFileName(), null);//读取公告内容
						bulletin.setBullContents(bullContent);
						pubLishBulModel.setBulletin(bulletin);
						publishBulletin.publishBulletin(pubLishBulModel);//发布公告
					}
				} catch (Exception e) {
					this.publishBulletinImplError("获取发布公告实现类异常");
					e.printStackTrace();
				}
				}
			}
		}
	}
	
	/** 
	 * FuncName: saveRelBulletin
	 * Description :  手动发送公告
	 * Create Date: 2011-1-15下午12:28:44 by yangx  Modified Date: 2011-1-15下午12:28:44 by yangx
	 * @param   bulletinIds：公告Ids
	 * @return  
	 * @Exception   
	 */
	public void saveRelBulletin(String bulletinIds){
		bulletinIds = bulletinIds.replace(",","','");
		List<Bulletin> bulletinList = bulletinDaoHibernate.getBulletinListByObjIds(bulletinIds);
		String publishBulletinKey = messageSource.getMessage("publishBulletinKey");//获取发布清单
		String basePath = messageSource.getMessage("uploadUrl");
		if (publishBulletinKey!=null&&!"".equals(publishBulletinKey)) {
			String[] publishBulletinKeys = publishBulletinKey.split(SeparationEnum.NUM);
			for (String className:publishBulletinKeys) {//循环要发布的清单
				PublishBulletin publishBulletin;
				try {
					publishBulletin = (PublishBulletin)Class.forName(className).newInstance();
					for (Bulletin bulletin:bulletinList) {
						PubLishBulModel pubLishBulModel = new PubLishBulModel();
						String bullContent = FileUtils.readFileByLines(basePath+bulletin.getContent().getPath()+bulletin.getContent().getFileName(), null);//读取公告内容
						bulletin.setBullContents(bullContent);
						pubLishBulModel.setBulletin(bulletin);
						publishBulletin.publishBulletin(pubLishBulModel);//发布公告
					}
				} catch (Exception e) {
					this.publishBulletinImplError("获取发布公告实现类异常");
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * FuncName:createBulletinCodeByQO
	 * Description: 生成采购公告编号,Service层必须开启DB事物
	 * @param queryObject:为扩展参数，暂时不用,projBuyMethod:项目采购方式
	 * @return String
	 * @author wanghz
	 * @Create Date 2010-7-15 上午09:59:44 
	 */
	@SuppressWarnings("unchecked")
	public String createBulletinCodeByQO(QueryObject queryObject,String projBuyMethod) throws EsException {
		logger.debug("\n BulletinServiceImpl||createBulletinCodeByQO\n");
		return this.bulletinManager.generatorBulletinCodeByQO(queryObject, projBuyMethod);
	}
	
	/**
	 * FuncName:saveInqpBulletinAndProjSchedule
	 * Description:保存询价公告[同步保存时间规则]
	 * @param   bulletin:询价公告实体对象,projProcessStatus:项目进度,list:传入的报名响应集合
	 * @return  Bulletin
	 * @author liuke
	 * @Create Date: 2010-9-8下午06:54:56 
	 * @Modifier liuke
     * @Modified Date: 2010-9-8下午06:54:56 
	 */
	public Bulletin saveInqpBulletinAndProjSchedule(Bulletin bulletin,String projProcessStatus,ListBind list) {
		logger.debug("\n BulletinServiceImpl||saveInqpBulletinAndProjSchedule\n");
		//保存报名指标列表
		Project project = projectManager.get(bulletin.getProject().getObjId());
		for(Iterator<SignUpCondFactor> iterator = list.getSets().iterator(); iterator.hasNext();){
			SignUpCondFactor signUpCondFactor = iterator.next();
			signUpCondFactor.setProject(project);
			signUpCondFactorDaoHibernate.save(signUpCondFactor);
		}
		String filePath = messageSource.getMessage("uploadUrl");
		String path=filePath+FileVirtualPathEnum.BULLETIN_INQP;
		FileUtil.mkdirs(path);
		String fileName = UUID.randomUUID().toString();
		bulletin.setContent(getAttachment(FileVirtualPathEnum.BULLETIN_INQP,bulletin,fileName));
		bulletin.setRelDate(new Date());
		this.save(bulletin);
		FileUtils.writerFile(path+fileName,bulletin.getBullContents());//把公告内容写到指定文件中
		if(this.getProjectManager() != null){//若存在项目组件，则保存到项目表中
			if(bulletin.getSignUpETime()!=null&&bulletin.getSignUpSTime()!=null&&bulletin.getTenderStartTime()!=null&&bulletin.getTenderEndTime()!=null){
			ProjectRule projectRule = projectManager.getProjectRuleByProjectId(bulletin.getProject().getObjId());
			projectRule.setSignUpETime(bulletin.getSignUpETime());
			projectRule.setSignUpSTime(bulletin.getSignUpSTime());
			projectRule.setSubmitETime(bulletin.getTenderEndTime());
			projectRule.setSubmitStTime(bulletin.getTenderStartTime());
			bulletinManager.save(projectRule,ProjectRule.class);
			projManager.saveProjProcessStatus(project.getObjId(),projProcessStatus);
		}
		}
		String viewResultURL="";
		String taskName="保存询价公告";
		completedWorkTaskManager.createCompTaskByPassivity(taskName,CompletedWorkTaskTypeEnum.WORKTASK_TYPE_BULLETIN,null,bulletin.getObjId(),bulletin.getProject().getObjId(),viewResultURL,CompletedWorkTaskResultEnum.WORKTASK_SAVE);
		User user=AuthenticationHelper.getCurrentUser();
		bulletin.setUser(user);
		String parentBizId = project.getObjId();
		if(project.getParentId()!= null ){
			parentBizId = project.getParentId();
		}
		bulletin.setParentBizId(parentBizId);
		return bulletin;
	}
	
	/**
	 * FuncName:saveSubmitInqpBulletinAndProjSchedule
	 * Description : 保存提交询价公告[同步保存时间规则] 
	 * @param   bulletin:询价公告,projProcessStatus:项目进度,list:传入的报名响应集合
	 * @return  Bulletin
	 * @author liuke
	 * @Create Date: 2010-9-8下午07:00:21 
	 * @Modifier   liuke
	 * @Modified Date: 2010-9-8下午07:00:21 
	 * Modified Date: 2011-3-11 15:38 by zhouzhanghe
	 */
	public Bulletin saveSubmitInqpBulletinAndProjSchedule(Bulletin bulletin,String projProcessStatus,ListBind list) {
		logger.debug("\n BulletinServiceImpl||saveSubmitInqpBulletinAndProjSchedule\n");
		//保存报名指标列表
		Project project = projectManager.get(bulletin.getProject().getObjId());
		for(Iterator<SignUpCondFactor> iterator = list.getSets().iterator(); iterator.hasNext();){
			SignUpCondFactor signUpCondFactor = iterator.next();
			signUpCondFactor.setProject(project);
			signUpCondFactorDaoHibernate.save(signUpCondFactor);
		}
		String filePath = messageSource.getMessage("uploadUrl");
		String path=filePath+FileVirtualPathEnum.BULLETIN_INQP;
		if (bulletin.getContent()==null||bulletin.getContent().getPath()==null) {
			FileUtil.mkdirs(path);
			String fileName = UUID.randomUUID().toString();
			bulletin.setContent(getAttachment(FileVirtualPathEnum.BULLETIN_INQP,bulletin,fileName));
		}
		bulletin.setRelDate(new Date());
		this.save(bulletin);
		FileUtils.writerFile(path+bulletin.getContent().getFileName(),bulletin.getBullContents());//把公告内容写到指定文件中
		if(this.getProjectManager() != null){//若存在项目组件，则保存到项目表中
			if(bulletin.getSignUpETime()!=null&&bulletin.getSignUpSTime()!=null&&bulletin.getTenderStartTime()!=null&&bulletin.getTenderEndTime()!=null){
				ProjectRule projectRule = projectManager.getProjectRuleByProjectId(bulletin.getProject().getObjId());
				projectRule.setSignUpETime(bulletin.getSignUpETime());
				projectRule.setSignUpSTime(bulletin.getSignUpSTime());
				projectRule.setSubmitETime(bulletin.getTenderEndTime());
				projectRule.setSubmitStTime(bulletin.getTenderStartTime());
				projectRule.setOpenBidSDate(bulletin.getOpenBidTime());
				projectRule.setEvalSTime(bulletin.getEvalStartTime());
				bulletinManager.save(projectRule,ProjectRule.class);
			}
		}
		String viewResultURL=CompletedWorkTaskURLEnum.WORKTASK_URL_BULLETIN_SUBMIT;
		String taskName="提交询价公告";
		completedWorkTaskManager.createCompTaskByPassivity(taskName,CompletedWorkTaskTypeEnum.WORKTASK_TYPE_BULLETIN,null,bulletin.getObjId(),bulletin.getProject().getObjId(),viewResultURL,CompletedWorkTaskResultEnum.WORKTASK_SAVE);
		User user=AuthenticationHelper.getCurrentUser();
		bulletin.setUser(user);
		String parentBizId = project.getObjId();
		if(project.getParentId()!= null ){
			parentBizId = project.getParentId();
		}
		bulletin.setParentBizId(parentBizId);
		return bulletin;
	}
	
	/**
	 * FuncName:saveUpdateInqpBulletinAndProjSchedule
	 * Description :保存修改询价公告[同步保存时间规则]  
	 * @param   bulletin:询价公告,projProcessStatus:项目进度,list:传入的报名响应
	 * @return  Bulletin
	 * @author liuke
	 * @Create Date: 2010-9-9上午09:14:05 
	 * @Modifier liuke
	 * @Modified Date: 2010-9-9上午09:14:05 
	 */
	public Bulletin saveUpdateInqpBulletinAndProjSchedule(Bulletin bulletin,String projProcessStatus,ListBind list) {
		logger.debug("\n BulletinServiceImpl||saveSubmitInqpBulletinAndProjSchedule\n");
		//保存报名指标列表
		Project project = projectManager.get(bulletin.getProject().getObjId());
		for(Iterator<SignUpCondFactor> iterator = list.getSets().iterator(); iterator.hasNext();){
			SignUpCondFactor signUpCondFactor = iterator.next();
			signUpCondFactor.setProject(project);
			signUpCondFactorDaoHibernate.save(signUpCondFactor);
		}
		String filePath = messageSource.getMessage("uploadUrl");
		bulletin.setRelDate(new Date());
		this.save(bulletin);
		FileUtils.writerFile(filePath+bulletin.getContent().getPath()+bulletin.getContent().getFileName(),bulletin.getBullContents());//把公告内容写到指定文件中
		if(this.getProjectManager() != null){//若存在项目组件，则保存到项目表中
			if(bulletin.getSignUpETime()!=null&&bulletin.getSignUpSTime()!=null&&bulletin.getTenderStartTime()!=null&&bulletin.getTenderEndTime()!=null){
				ProjectRule projectRule = projectManager.getProjectRuleByProjectId(bulletin.getProject().getObjId());
				projectRule.setSignUpETime(bulletin.getSignUpETime());
				projectRule.setSignUpSTime(bulletin.getSignUpSTime());
				projectRule.setSubmitETime(bulletin.getTenderEndTime());
				projectRule.setSubmitStTime(bulletin.getTenderStartTime());
				bulletinManager.save(projectRule,ProjectRule.class);
				projManager.saveProjProcessStatus(project.getObjId(),projProcessStatus);
			}
		}
		String viewResultURL="";
		String taskName="保存修改询价公告";
		completedWorkTaskManager.createCompTaskByPassivity(taskName,CompletedWorkTaskTypeEnum.WORKTASK_TYPE_BULLETIN,null,bulletin.getObjId(),bulletin.getProject().getObjId(),viewResultURL,CompletedWorkTaskResultEnum.WORKTASK_SAVE);
		return bulletin;
	}

	/** 
	 * FuncName:getAttachment
	 * Description :  组装公告附件对像
	 * @param   path:公告文件的存储路径,bulletin:公告对象,fileName:文件名称
	 * @return  Attachment
	 * @author yangx
	 * @Create Date: 2010-9-21下午04:00:13 
	 * @Modifier yangx
	 * @Modified Date: 2010-9-21下午04:00:13  
	 */
	private Attachment getAttachment(String path,Bulletin bulletin,String fileName){
		logger.debug("\n BulletinServiceImpl||getAttachment\n");
		Attachment attachment = new Attachment();//公告文件的存储路径和文件名
		attachment.setPath(path);
		attachment.setViewName(bulletin.getBullTitle());
		attachment.setFileName(fileName);
		attachment.setCreateTime(bulletin.getCreateTime());
		return attachment;
	}
	
	/**
	 * FuncName:saveChangeBulletin
	 * Description :保存变更公告  
	 * @param   bulletin: 变更公告,projProcessStatus:项目进度
	 * @return  Bulletin
	 * @author liuke
	 * @Create Date: 2010-10-14下午03:05:15 
	 * @Modifier liuke
	 * @Modified Date: 2010-10-14下午03:05:15  
	 */
	public Bulletin saveChangeBulletin(Bulletin bulletin,String projProcessStatus) {
		logger.debug("\n BulletinServiceImpl||saveChangeBulletin\n");
		String filePath = messageSource.getMessage("uploadUrl");
		String path=filePath+FileVirtualPathEnum.BULLETIN_CHANGE;
		FileUtil.mkdirs(path);
		String fileName = UUID.randomUUID().toString();
		bulletin.setContent(getAttachment(FileVirtualPathEnum.BULLETIN_CHANGE, bulletin,fileName));
		bulletin.setRelDate(new Date());
		this.save(bulletin);
		FileUtils.writerFile(path+fileName,bulletin.getBullContents());//把公告内容写到指定文件中
		if(projProcessStatus!=null&&!"".equals(projProcessStatus)){//项目实施状态是否存在
			this.getProjectManager().saveProjProcessStatus(bulletin.getProject().getObjId(),projProcessStatus);//保存项目实施状态
		}
		String viewResultURL="";
		String taskName="保存变更公告";
		completedWorkTaskManager.createCompTaskByPassivity(taskName,CompletedWorkTaskTypeEnum.WORKTASK_TYPE_BULLETIN,null,bulletin.getObjId(),bulletin.getProject().getObjId(),viewResultURL,CompletedWorkTaskResultEnum.WORKTASK_SAVE);//保存已办任务
		return bulletin;
	}
	
	/** 
	 * FuncName:saveSubmitChangeBulletin
	 * Description:提交变更公告
	 * @param   bulletin:变更公告对象,projProcessStatus:项目实施状态
	 * @return  Bulletin
	 * @author liuke
	 * @Create Date: 2010-9-7下午02:25:35 
	 * @Modifier   liuke
	 * @Modified Date: 2010-9-7下午02:25:35   
	 */
	public Bulletin saveSubmitChangeBulletin(Bulletin bulletin,	String projProcessStatus) {
		logger.debug("\n BulletinServiceImpl||saveSubmitChangeBulletin\n");
		String filePath = messageSource.getMessage("uploadUrl");
		String path=filePath+FileVirtualPathEnum.BULLETIN_CHANGE;
		if (bulletin.getContent()==null||bulletin.getContent().getPath()==null) {
			FileUtil.mkdirs(path);
			String fileName = UUID.randomUUID().toString();
			bulletin.setContent(getAttachment(FileVirtualPathEnum.BULLETIN_CHANGE, bulletin,fileName));
		}
		bulletin.setRelDate(new Date());
		this.save(bulletin);
		FileUtils.writerFile(path+bulletin.getContent().getFileName(),bulletin.getBullContents());//把公告内容写到指定文件中
		if(projProcessStatus!=null&&!"".equals(projProcessStatus)){//项目实施状态是否存在
			this.getProjectManager().saveProjProcessStatus(bulletin.getProject().getObjId(),projProcessStatus);//保存项目实施状态
		}
		String viewResultURL=CompletedWorkTaskURLEnum.WORKTASK_URL_RESULT_BULLETIN_SUBMIT;
		String taskName="提交变更公告";
		completedWorkTaskManager.createCompTaskByPassivity(taskName,CompletedWorkTaskTypeEnum.WORKTASK_TYPE_BULLETIN,null,bulletin.getObjId(),bulletin.getProject().getObjId(),viewResultURL,CompletedWorkTaskResultEnum.WORKTASK_SAVE);//保存已办任务
		return bulletin;
	}
	
	/**
	 * FuncName:createChangeBulletinCodeByQO
	 * Description :生成变更公告编号,Service层必须开启DB事物  
	 * @param   queryObject:用于扩展的对象,projBuyMethod:采购方式
	 * @return  String
	 * @author liuke
	 * @Create Date: 2010-10-14下午03:54:59    
	 * @Modifier  liuke
	 * @Modified Date: 2010-10-14下午03:54:59  
	 */
	@SuppressWarnings("unchecked")
	public String createChangeBulletinCodeByQO(QueryObject queryObject,String projBuyMethod) throws EsException {
		logger.debug("\n BulletinServiceImpl||saveSubmitChangeBulletin\n");
		return this.bulletinManager.generatorBulletinCodeByQO(queryObject, projBuyMethod);
	}
	
	/**
	 * FuncName:saveUpdateChangeBulletin
	 * Description:保存修改变更公告 
	 * @param   bulletin:变更公告,projProcessStatus:项目实施状态
	 * @return Bulletin
	 * @author liuke 
	 * @Create Date: 2010-10-15上午10:46:09 
	 * @Modifier  liuke
	 * @Modified Date: 2010-10-15上午10:46:09 
	 */
	public Bulletin saveUpdateChangeBulletin(Bulletin bulletin,String projProcessStatus) {
		logger.debug("\n BulletinServiceImpl||saveUpdateChangeBulletin\n");
		String filePath = messageSource.getMessage("uploadUrl");
		bulletin.setRelDate(new Date());
		this.save(bulletin);
		FileUtils.writerFile(filePath+bulletin.getContent().getPath()+bulletin.getContent().getFileName(),bulletin.getBullContents());//把公告内容写到指定文件中
		if(projProcessStatus!=null&&!"".equals(projProcessStatus)){//项目实施状态是否存在
			this.getProjectManager().saveProjProcessStatus(bulletin.getProject().getObjId(),projProcessStatus);//保存项目实施状态
		}
		String viewResultURL="";
		String taskName="保存修改变更公告";
		completedWorkTaskManager.createCompTaskByPassivity(taskName,CompletedWorkTaskTypeEnum.WORKTASK_TYPE_BULLETIN,null,bulletin.getObjId(),bulletin.getProject().getObjId(),viewResultURL,CompletedWorkTaskResultEnum.WORKTASK_SAVE);//保存已办任务
		return bulletin;
	}
	
	/** 
	 * FuncName:saveUpdateSubmitChangeBulletin
	 * Description:提交修改变更公告
	 * @param   bulletin:变更公告,projProcessStatus:项目实施状态
	 * @return  Bulletin
	 * @author liuke
	 * @Create Date: 2010-9-7下午06:32:35 
	 * @Modifier liuke
	 * @Modified Date: 2010-9-7下午06:32:35     
	 */
	public Bulletin saveUpdateSubmitChangeBulletin(Bulletin bulletin,String projProcessStatus) {
		logger.debug("\n BulletinServiceImpl||saveUpdateSubmitChangeBulletin\n");
		String filePath = messageSource.getMessage("uploadUrl");
		bulletin.setRelDate(new Date());
		this.save(bulletin);
		FileUtils.writerFile(filePath+bulletin.getContent().getPath()+bulletin.getContent().getFileName(),bulletin.getBullContents());//把公告内容写到指定文件中
		if(projProcessStatus!=null&&!"".equals(projProcessStatus)){//项目实施状态是否存在
			this.getProjectManager().saveProjProcessStatus(bulletin.getProject().getObjId(),projProcessStatus);//保存项目实施状态
		}
		String viewResultURL=CompletedWorkTaskURLEnum.WORKTASK_URL_RESULT_BULLETIN_SUBMIT;
		String taskName="提交修改变更公告";
		completedWorkTaskManager.createCompTaskByPassivity(taskName,CompletedWorkTaskTypeEnum.WORKTASK_TYPE_BULLETIN,null,bulletin.getObjId(),bulletin.getProject().getObjId(),viewResultURL,CompletedWorkTaskResultEnum.WORKTASK_SAVE);//保存已办任务
		return bulletin;
	}
	
	/**
	 * FuncName:saveChangeBulletinForAudit
	 * Description :审核变更公告  
	 * @param   ids:变更公告主键,passStatus:审核状态
	 * @return  Bulletin
	 * @author liuke
	 * @Create Date: 2010-10-15下午01:12:39 
	 * @Modifier   liuke
	 * @Modified Date: 2010-10-15下午01:12:39  
	 */
	public Bulletin saveChangeBulletinForAudit(String ids, String passStatus) {
		logger.debug("\n BulletinServiceImpl||saveUpdateSubmitChangeBulletin\n");
		Bulletin bulletin = null ;
		String bizIds = "";
		String objIds[] = ids.split(",");
		for (String objId : objIds) {
			bulletin = bulletinManager.get(objId);
			bulletin.setAuditStatus(passStatus);
			bulletin.setRelUser(AuthenticationHelper.getCurrentUser(true).getEmp());
			bulletin.setRelDate(new Date());
			this.save(bulletin);
			String viewResultURL="";
			String taskName="";
			String processResult="";
			String opinion=bulletin.getOpinion();
			taskName="审核变更公告";
			if(bulletin.getAuditStatus()!=null&&(AuditStatusEnum.AUDIT_PASS).equals(bulletin.getAuditStatus())){
				processResult=CompletedWorkTaskResultEnum.WORKTASK_PASSAUDIT;
				bulletin.setWorkflowAuditStatus(WorkFlowModel.AUDIT_STATUS_PASS);
			}else if(bulletin.getAuditStatus()!=null&&(AuditStatusEnum.AUDIT_NO_PASS).equals(bulletin.getAuditStatus())){
				processResult=CompletedWorkTaskResultEnum.WORKTASK_NOPASSAUDIT;
				bulletin.setWorkflowAuditStatus(WorkFlowModel.AUDIT_STATUS_NO_PASS);
			}
			completedWorkTaskManager.createCompTaskByPassivity(taskName,CompletedWorkTaskTypeEnum.WORKTASK_TYPE_BULLETIN,opinion,bulletin.getObjId(),bulletin.getProject().getObjId(),viewResultURL,processResult);//保存已办任务
			Project project = projectManager.get(bulletin.getProject().getObjId());
			String parentBizId = project.getObjId();
			if(project.getParentId()!= null ){
				parentBizId = project.getParentId();
			}
			bizIds = bizIds+parentBizId+",";
		}
		bulletin.setUser(AuthenticationHelper.getCurrentUser(true));
		bulletin.setParentBizId(bizIds.substring(0, bizIds.length()-1));
		return bulletin;
	}
	
	/** 
	 * FuncName:publishBulletinImplError
	 * Description :  获取发布服务错误
	 * @param   errorContent:错误内容
	 * @author yangx
	 * @Create Date: 2010-11-26下午01:38:19 
	 * @Modifier   yangx
	 * @Modified Date: 2010-11-26下午01:38:19 
	 */
	private void publishBulletinImplError(String errorContent){
		logger.debug("\n BulletinServiceImpl||publishBulletinImplError\n");
		OutputStreamWriter osw=null;
		try {
			File file = this.createFile();
			osw = new OutputStreamWriter(new FileOutputStream(file,true));
		} catch (Exception e1) {
			e1.printStackTrace();
		} 
		if(osw!=null){
			BufferedWriter bw=new BufferedWriter(osw); 
			try {
				bw.write("\n"+DateUtil.getCurDateTime()+"_"+errorContent);
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				try {
					bw.close();
					osw.close();
				} catch (IOException e) {
					e.printStackTrace();
				} 			
			}
		}
	}
	
	/**
	 * FuncName: createFile
	 * Description :  创建或修改对象
	 * @param 
	 * @return File
	 * @throws Exception File
	 * @author: shenjz
	 * @Create Date:2010-12-30  上午10:30:34
	 * @Modifier: shenjz
	 * @Modified Date:2010-12-30  上午10:30:34
	 */
	private File createFile() throws Exception{
		logger.debug("\n BulletinServiceImpl||createFile\n");
		Properties props = new Properties();
    	//拿到配置的日志存储路径
		try {			
			InputStream in = this.getClass().getResourceAsStream("/sys/publishLog.properties");
			props.load(in);
	        in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String logFileAddr = props.getProperty("logFlie.addr");
    	//这里要注意文件的格式“CZW_2010-11-24.log".CZW是为了识别是发送到CZW的日志。
    	File fileMK = new File(logFileAddr);
		if (!fileMK.exists()) {
			fileMK.mkdir();
		}
		File file = new File(logFileAddr+"PublishBulletinImpl_ERROR"+DateUtil.getCurDate()+".log");
		if (!file.exists()) {
			file.createNewFile();
		}
		return file;
    }

 
}
