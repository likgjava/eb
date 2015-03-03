package com.gpcsoft.epp.taskplan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.common.domain.AuditStatusEnum;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.manager.ProjectManager;
import com.gpcsoft.epp.resproject.domain.ResProject;
import com.gpcsoft.epp.taskplan.dao.RecordFormDao;
import com.gpcsoft.epp.taskplan.domain.RecordForm;
import com.gpcsoft.epp.taskplan.manager.RecordFormManager;
import com.gpcsoft.epp.taskplan.service.RecordFormService;
import com.gpcsoft.epp.worktask.domain.CompletedWorkTaskResultEnum;
import com.gpcsoft.epp.worktask.domain.CompletedWorkTaskTypeEnum;
import com.gpcsoft.epp.worktask.manager.CompletedWorkTaskManager;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;

@Service 
@SuppressWarnings(value={"unchecked"})
public class RecordFormServiceImpl extends BaseGenericServiceImpl<RecordForm> implements RecordFormService {

	@Autowired(required=true) @Qualifier("recordFormManagerImpl")
	private RecordFormManager recordFormManager;
	
	@Autowired(required=true) @Qualifier("recordFormDaoHibernate")
	private RecordFormDao recordFormDao;
	
	@Autowired(required=true) @Qualifier("projectManagerImpl")
	private ProjectManager projectManager;
	
	@Autowired(required=true) @Qualifier("completedWorkTaskManagerImpl")
	private CompletedWorkTaskManager completedWorkTaskManager;
	/**
	 * FuncName: saveRecordForm
	 * Description :  创建备案书对象
	 * @param 
	 * @param recordForm
	 * @return RecordForm
	 * @author: shenjz
	 * @Create Date:2011-5-23  上午10:55:59
	 * @Modifier: shenjz
	 * @Modified Date:2011-5-23  上午10:55:59
	 */
	public RecordForm saveRecordForm(RecordForm recordForm) {
		recordFormManager.save(recordForm);
		return recordForm;
	}
	
	/** 
	 * FuncName : saveRecordFormAndProject
	 * Description :  保存招标投标登记表
	 * Create Date: 2011-12-21下午02:32:30 by yangx  
	 * Modified Date: 2011-12-21下午02:32:30 by yangx
	 * @param   recordForm：招标投标登记表
	 * @return  RecordForm
	 * @Exception   
	 */
	public RecordForm saveRecordFormAndProject(RecordForm recordForm){
		recordForm = recordFormManager.save(recordForm);
		this.saveResProject(recordForm);
		return recordForm;
	}
	
	
	/** 
	 * FuncName : saveResProject
	 * Description :  保存项目信息
	 * Create Date: 2011-12-21下午03:30:09 by yangx  
	 * Modified Date: 2011-12-21下午03:30:09 by yangx
	 * @param   recordForm ：备案表信息
	 * @return  ResProject
	 */
	private ResProject saveResProject(RecordForm recordForm){
		ResProject resProject = new ResProject();
		resProject.setRecordForm(recordForm);
		resProject.setMoneySource(recordForm.getMoneySource());//资金来源
		resProject.setPlanPermit(recordForm.getPlanPermit());//规划许可证
		resProject.setSingleArea(recordForm.getSingleArea());//单个建筑面积(m2)平方米 
		resProject.setTotalArea(recordForm.getTotalArea());//建筑总面积(m2) 
		resProject.setProNumber(recordForm.getProjNumber());//工程个数
		resProject.setStructStyle(recordForm.getRecFormStructureType());//结构形式
		resProject.setLayerOverGround(recordForm.getLayerOverRound());//层数地上 
		resProject.setLayerUnderGround(recordForm.getLayerUnderRound());//层数地下
		resProject.setCornice(recordForm.getCornice());//檐口高度(m) 
		resProject.setSpan(recordForm.getSpan());//跨度(m) 
		resProject.setBidInviStyle(recordForm.getRecFormBuyMethod());//招标形式(00:自主招标 01,委托招标) 
		resProject.setEbuyMethod(recordForm.getEbuyMethod());//招标方式
		resProject.setProjNature(recordForm.getProjNature());//项目性质(00:新建、01:扩建、02:改建)
		resProject.setUnitProperty(recordForm.getUnitProperty());//单位属性
		resProject.setProjApproval(recordForm.getProjApproval());//计划批文
		resProject.setProjectType(recordForm.getProjProperty());//项目类型
		resProject.setAmt(recordForm.getInvestent());//投资金额
		resProject.setBudget(recordForm.getRecFormOrgId());
		resProject.setBudgetName(recordForm.getRecFormOrgName());
		resProject.setBudgetLinker(recordForm.getLinkerName());
		resProject.setBudgetLinkerTel(recordForm.getLinkerTel());
		resProject.setProjectName(recordForm.getEngineeringName());
		resProject.setProjectNo(recordForm.getRecFormNo());
		resProject.setUseStatus(CommonEnum.USER_STATUS_TEMP);
		resProject = (ResProject)recordFormManager.save(resProject,ResProject.class);
		return resProject;
	}
	
	/**
	 * FuncName: getRecordFormByPrjId
	 * Description :  根据项目主键获取备案书对象
	 * @param 
	 * @param projectId
	 * @return RecordForm
	 * @author: shenjz
	 * @Create Date:2011-5-23  下午01:50:29
	 * @Modifier: shenjz
	 * @Modified Date:2011-5-23  下午01:50:29
	 */
	public RecordForm getRecordFormByPrjId(String projectId){
		 return recordFormDao.getRecordFormByPrjId(projectId);
	 }
	/**
	 * FuncName: saveRecordForm
	 * Description :  备案书确定底价
	 * @param 
	 * @param recordForm
	 * @return RecordForm
	 * @author: shenjz
	 * @Create Date:2011-5-23  上午10:55:59
	 * @Modifier: shenjz
	 * @Modified Date:2011-5-23  上午10:55:59
	 */
	public RecordForm updateRecordForm(RecordForm recordForm) {
		recordFormManager.save(recordForm);
		recordForm.setUser(AuthenticationHelper.getCurrentUser());
		Project project = projectManager.get(recordForm.getProject().getObjId());
		String parentBizId = project.getObjId();
		if(project.getParentId()!= null ){
			parentBizId = project.getParentId();
		}
		recordForm.setParentBizId(parentBizId);
		return recordForm;
	}
	/**
	 * FuncName:  toTrackRecordFormList
	 * Description :  根据查询条件获得相关的备案书信息
	 * @param queryObject:组装的查询条件对象,page:分页对象
	 * @return Page<RecordForm>
	 * @author caojz
	 * @Create Date: 2011-8-30上午10:30:42   
	 * @Modifier caojz
	 * @Modified Date: 2011-8-30上午10:30:42 
	 */
	public Page<RecordForm> toTrackRecordFormList(QueryObject queryObject,
			Page<RecordForm> page){
		return recordFormDao.toTrackRecordFormList(queryObject, page);
	}
	
	/** 
	 * FuncName : saveRecordFormForAudit
	 * Description :  审核招标投标登记表
	 * Create Date: 2011-12-22下午06:16:39 by yangx  
	 * Modified Date: 2011-12-22下午06:16:39 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public RecordForm saveRecordFormForAudit(RecordForm recordForm,String opinion){
		recordForm = recordFormDao.save(recordForm);
		String auditResult = CompletedWorkTaskResultEnum.WORKTASK_NOPASSAUDIT;
		if (AuditStatusEnum.AUDIT_PASS.equals(recordForm.getAuditStatus())) {
			auditResult = CompletedWorkTaskResultEnum.WORKTASK_PASSAUDIT;
		}
		completedWorkTaskManager.createCompTaskByPassivity("审核招标投标登记表","",opinion,recordForm.getObjId(),recordForm.getObjId(),"",auditResult);
		return recordForm;
	}
}
