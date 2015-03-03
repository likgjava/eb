package com.gpcsoft.epp.consign.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryObjectBase;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.common.domain.SeparationEnum;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.consign.domain.Consign;
import com.gpcsoft.epp.consign.domain.ConsignTaskPlan;
import com.gpcsoft.epp.consign.manager.ConsignManager;
import com.gpcsoft.epp.consign.manager.ConsignTaskPlanManager;
import com.gpcsoft.epp.consign.service.ConsignService;
import com.gpcsoft.epp.project.manager.ProjectPlanManager;
import com.gpcsoft.epp.taskplan.domain.TaskPlan;
import com.gpcsoft.epp.taskplan.domain.TaskPlanSub;
import com.gpcsoft.epp.taskplan.domain.TaskPlanTypeEnum;
import com.gpcsoft.epp.taskplan.manager.TaskPlanMSubManager;
import com.gpcsoft.epp.taskplan.manager.TaskPlanManager;
import com.gpcsoft.epp.worktask.domain.CompletedWorkTaskResultEnum;
import com.gpcsoft.epp.worktask.domain.CompletedWorkTaskTypeEnum;
import com.gpcsoft.epp.worktask.manager.CompletedWorkTaskManager;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.plugin.wflow.domain.WorkFlowModel;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.workPlan.domain.PlanTemplate;

@Service 
public class ConsignServiceImpl extends BaseGenericServiceImpl<Consign> implements ConsignService {

	@Autowired(required=true) @Qualifier("consignManagerImpl")
	private ConsignManager consignManager;
	
	@Autowired(required=true) @Qualifier("consignTaskPlanManagerImpl")
	private ConsignTaskPlanManager consignTaskPlanManager;
	
	@Autowired(required=true) @Qualifier("taskPlanManagerImpl")
	private TaskPlanManager taskPlanManager;
	
	@Autowired(required=true) @Qualifier("completedWorkTaskManagerImpl")
	private CompletedWorkTaskManager completedWorkTaskManager;
	
	@Autowired(required=true) @Qualifier("taskPlanMSubManagerImpl")
	private TaskPlanMSubManager taskPlanMSubManager;

	@Autowired(required=true) @Qualifier("projectPlanManagerImpl")
	private ProjectPlanManager projectPlanManager;

	/**
	 * FuncName:saveBatchSubmitConsign
	 * Description :  提交委托书
	 * @param   以逗号分割的id
	 * @return  void
	 * @author liangxj
	 * @Create Date: 2010-6-6下午05:52:43 
	 * @Modifier liangxj
	 * @Modified Date: 2010-6-6下午05:52:43 
	 */
	public void saveBatchSubmitConsign(String objIds,String useStatus,String confirmStatus){
		logger.debug("\nes=ConsignServiceImpl||saveBatchSubmitConsign\n");
		consignManager.submitConsign(objIds, useStatus, confirmStatus);
	}

	
	/* 同步删除中间表
	 * @see com.gpcsoft.core.service.impl.BaseGenericServiceImpl#onAfterRemove(java.lang.String)
	 */
	@Override
	protected void onAfterRemove(String id) {
		consignTaskPlanManager.removeByConsign(id);
	}

	/** 
	 * FuncName:getConsignListByObject
	 * Description :  根据对象获取委托协议列表
	 * @param   page:分页对象,orgInfo:机构,confirmStatus:确认状态,consCode:委托协议标号,consName:委托协议名称,taskType:
	 * @return  Page<Consign>
	 * @author yangx
	 * @Create Date: 2010-7-13下午04:13:25 
	 * @Modifier yangx
	 * @Modified Date: 2010-7-13下午04:13:25 
	 */
	@SuppressWarnings("unchecked")
	public Page<Consign> getConsignListByObject(Page<Consign> page,OrgInfo orgInfo,String confirmStatus,String consCode,String consName,String taskType){
		logger.debug("\n=ConsignServiceImpl||getConsignListByObject\n");
		QueryObject queryObject = new QueryObjectBase();
		queryObject.getQueryParam().and(new QueryParam("consCode",QueryParam.OPERATOR_EQ,consCode));
		queryObject.getQueryParam().and(new QueryParam("consName",QueryParam.OPERATOR_EQ,consName));
		if(orgInfo.getAgencyId() != null && !"".equals(orgInfo.getAgencyId())){//代理机构
			queryObject.getQueryParam().and(new QueryParam("confirmStatus",QueryParam.OPERATOR_EQ,confirmStatus));
			queryObject.getQueryParam().and(new QueryParam("consAgent.objId",QueryParam.OPERATOR_EQ,orgInfo.getObjId()));
		}
		if(orgInfo.getBuyerId() != null && !"".equals(orgInfo.getBuyerId())){//采购人
			queryObject.getQueryParam().and(new QueryParam("consBuyer",QueryParam.OPERATOR_EQ,orgInfo.getObjId()));// 采购人Id
			queryObject.getQueryParam().and(new QueryParam("confirmStatus",QueryParam.OPERATOR_EQ,confirmStatus));
			queryObject.getQueryParam().and(new QueryParam("taskType",QueryParam.OPERATOR_EQ,TaskPlanTypeEnum.NORMAL));  //非大宗委托协议
		}
		return consignManager.getConsignListByObject(page,queryObject);
	}
	
	/**
	 * FuncName:removeTaskPlanBytaskPlanIdAndProjectId
	 * Description :根据项目主键和委托书主键删除申报书对象 
	 * @param   taskPlanId:申报书主键,consignId:委托协议主键
	 * @return  void
	 * @author liuke
	 * @Create Date: 2010-7-7下午03:29:53 
	 * @Modifier  liuke
	 * @Modified Date: 2010-7-7下午03:29:53 
	 */
	public void removeTaskPlanBytaskPlanIdAndProjectId(String taskPlanId,String consignId) {
		logger.debug("\nes=ConsignServiceImpl||removeTaskPlanBytaskPlanIdAndProjectId\n");
		//根据申报书主键设置申报书大宗状态为null
		TaskPlan taskPlan = taskPlanManager.get(taskPlanId);
		taskPlan.setBlock_check_status(null);
		taskPlanManager.save(taskPlan);
		//根据申报书主键和委托书主键删除中间表对象
		consignTaskPlanManager.removeConsignTaskPlanBytaskPlanIdAndConsignId(taskPlanId, consignId);
		
	}
    
	/**
	 * FuncName:removeConsign
	 * Description :删除大宗项目申报书
	 * @param   ConsignId:委托协议主键
	 * @return  void
	 * @author liuke
	 * @Create Date: 2010-7-7下午03:29:53 
	 * @Modifier   liuke
	 * @Modified Date: 2010-7-7下午03:29:53  
	 */
	public void removeConsign(String ConsignId) {
		logger.debug("\nes=ConsignServiceImpl||removeConsign\n");
		//设置对应的申报书大宗状态为null
		List<TaskPlan> taskplanList = consignTaskPlanManager.getTaskPlanByConsign(ConsignId);
		   for(int i=0;i<taskplanList.size();i++)
		   {
			   TaskPlan taskPlan = taskplanList.get(i);
			   taskPlan.setBlock_check_status(null);
			   taskPlanManager.save(taskPlan);
		   }
		//删除委托书与申报书中间表对象
		   consignTaskPlanManager.removeConsignTaskPlanByConsignId(ConsignId);
		//删除大宗委托书
		   consignManager.remove(ConsignId, Consign.class);
	}

	/**
	 * FuncName:createConsignCodeByQO
	 * Description: 生成委托协议编号,Service层必须开启DB事物
	 * @param queryObject 为扩展参数，暂时不用
	 * @return String
	 * @author wanghz
	 * @Create Date 2010-7-15 上午09:59:44 
	 */
	@SuppressWarnings("unchecked")
	public String createConsignCodeByQO(QueryObject queryObject)throws EsException {
		logger.debug("\nes=ConsignServiceImpl||createConsignCodeByQO\n");
		return consignManager.generatorConsignCodeByQO(queryObject);
	}
	
	/**
	 * FuncName:getConsignListOrCountByQuery
	 * Description: 获取委托协议List 或 记录条数
	 * @param count:记录条数,type:查询类型{data:数据,count记录条数},createUser:创建人ID,confirmStatus:确认状态,consBuyer:采购人
	 * @return Object
	 * @author wanghz
	 * @Create Date 2010-9-2 下午02:36:16 
	 */
	@SuppressWarnings("unchecked")
	public Object getConsignListOrCountByQuery(String type,int count,String confirmStatus,String useStatus,User user)throws EsException{
		logger.debug("\nes=ConsignServiceImpl||getConsignListOrCountByQuery\n");
		QueryObject queryObject = new QueryObjectBase();
		OrgInfo orgInfo = (OrgInfo)user.getOrgInfo();
		queryObject.getQueryParam().and(new QueryParam("count",QueryParam.OPERATOR_EQ,count+""));
		queryObject.getQueryParam().and(new QueryParam("type",QueryParam.OPERATOR_EQ,"data"));
		if (orgInfo.getAgencyId() != null && !"".equals(orgInfo.getAgencyId())) {// 代理机构
			queryObject.getQueryParam().and(new QueryParam("createUser",QueryParam.OPERATOR_EQ,user.getObjId()));
			queryObject.getQueryParam().and(new QueryParam("confirmStatus",QueryParam.OPERATOR_EQ,confirmStatus));
			queryObject.getQueryParam().and(new QueryParam("useStatus",QueryParam.OPERATOR_EQ,useStatus));
			queryObject.getQueryParam().and(new QueryParam("consAgentId",QueryParam.OPERATOR_EQ,user.getOrgInfo().getObjId()));// 代理机构Id
		}
		if (orgInfo.getBuyerId() != null && !"".equals(orgInfo.getBuyerId())) {// 采购人
			queryObject.getQueryParam().and(new QueryParam("consBuyer",QueryParam.OPERATOR_EQ,user.getOrgInfo().getObjId()));// 采购人Id
			queryObject.getQueryParam().and(new QueryParam("confirmStatus",QueryParam.OPERATOR_EQ,CommonEnum.CONFIRM_STATUS_NEGOTIATE));
			queryObject.getQueryParam().and(new QueryParam("taskType",QueryParam.OPERATOR_EQ,TaskPlanTypeEnum.NORMAL));  //非大宗委托协议
		}
		return consignManager.getConsignListOrCountByQuery(queryObject);
	}
	
	/**
	 * FuncName:getConsignListOrCountByQuery
	 * Description: 获取委托协议List 或 记录条数
	 * @param queryObject{count:记录条数,type:查询类型{data:数据,count记录条数},createUser:创建人ID,confirmStatus:确认状态,consBuyer:采购人}
	 * @return Object
	 * @author wanghz
	 * @Create Date 2010-9-2 下午02:36:16 
	 */
	@SuppressWarnings("unchecked")
	public Object getConsignListOrCountByQuery(QueryObject queryObject)throws EsException{
		logger.debug("\nes=ConsignServiceImpl||getConsignListOrCountByQuery\n");
		return consignManager.getConsignListOrCountByQuery(queryObject);
	}
	
	/**
	 * FuncName:saveConsignTaskPlan
	 * Description :保存委托协议中间表数据  
	 * @param   taskPlan 申报书对象,consign 委托协议对象
	 * @return  void
	 * @author liuke
	 * @Create Date: 2010-11-10下午04:52:22 
	 * @Modifier  liuke
	 * @Modified Date: 2010-11-10下午04:52:22 
	 */
	private void saveConsignTaskPlan(TaskPlan taskPlan,Consign consign){
		//获得申报书条目列表
		List<TaskPlan>  list = consignTaskPlanManager.getTaskPlanByConsign(consign.getObjId());//根据委托协议查询申报书列表
		if(list.isEmpty()){//没有保存委托协议与申报书中间表数据
			List<TaskPlanSub>  taskplansubList = taskPlanMSubManager.getTaskPlanSubByTaskPlan(taskPlan.getObjId());
			for(int j=0;j<taskplansubList.size();j++){
					ConsignTaskPlan consignTaskPlan = new ConsignTaskPlan();
					consignTaskPlan.setTaskPlan(taskPlan);
					consignTaskPlan.setTaskPlanSub(taskplansubList.get(j));
					consignTaskPlan.setConsign(consign);
					consignTaskPlanManager.save(consignTaskPlan);
			}
		}
	};
	
	/**
	 * FuncName:createConsign
	 * Description: 创建委托协议
	 * @param taskPlanId:申报书主键,consignId:委托协议主键
	 * @return Consign
	 * @author wanghz
	 * @Create Date 2010-9-6 下午03:53:35 
	 */
	public Consign createConsign(Consign consign,String taskPlanId,String consignId)throws Exception{
		logger.debug("\nes=ConsignServiceImpl||createConsign\n");
		TaskPlan taskPlan = taskPlanManager.get(taskPlanId);
		//添加委托协议代理机构电话，代理机构名称，代理机构联系人字段
		consign.setUseStatus(CommonEnum.USER_STATUS_TEMP);
		consign.setConfirmStatus(CommonEnum.CONFIRM_STATUS_WAIT);
		consignManager.save(consign);
		taskPlan.setUseStatus(CommonEnum.USER_STATUS_CANCEL);
		consignManager.save(taskPlan,TaskPlan.class);
		this.saveConsignTaskPlan(taskPlan, consign);//保存委托协议与申报书中间表数据
		if (consignId==null||"".equals(consignId)) {//判断该委托协议是否保存过：如果是则不创建工作计划否则创建工作计划
			projectPlanManager.createProjectPlan(null, AuthenticationHelper.getCurrentUser(true).getEmp().getCompany().getObjId(),consign.getObjId(),PlanTemplate.TEMPLATE_CODE_CONSIGN);
		}
		completedWorkTaskManager.createCompTaskByPassivity("创建委托协议",CompletedWorkTaskTypeEnum.WORKTASK_TYPE_CONSIGN,"",consign.getObjId(),consign.getObjId(),"",CompletedWorkTaskResultEnum.WORKTASK_NOPASSAUDIT);
		return consign;
	}
	
	/**
	 * FuncName:saveSubmitConsign
	 * Description: 提交委托协议
	 * @param taskPlanId：申报书Id;consign：委托协议对象;
	 * @return Consign
	 * @author wanghz
	 * @Create Date 2010-9-6 下午03:53:35 
	 */
	public Consign saveSubmitConsign(List<Consign> consignList,String taskPlanId,String consignId)throws Exception{
		logger.debug("\nes=ConsignServiceImpl||saveSubmitConsign\n");
		Consign consign= null;
		if (consignList!=null&&consignList.size()>0) {//存在委托协议
			if (consignList.size()>1) {//至少有两个
				consign = new Consign();
				String objIds = "";
				String useStatus = "";
				String confirmStatus = "";
				for (Consign consign_:consignList) {//循环出要提交的委托协议ID,状态
					objIds += consign_.getObjId()+SeparationEnum.COMMA;
					useStatus = consign_.getUseStatus();
					confirmStatus = consign_.getConfirmStatus();
				}
				consignManager.submitConsign(objIds.substring(0, objIds.lastIndexOf(SeparationEnum.COMMA)), useStatus, confirmStatus);
			}else{//只有一个
				consign = consignList.get(0);
				if (consign.getObjId()!=null&&!"".equals(consign.getObjId())&&CommonEnum.USER_STATUS_FORMAL.equals(consign.getUseStatus())) {//批量提交一个
					consignManager.submitConsign(consign.getObjId(),consign.getUseStatus(),consign.getConfirmStatus());
				}else{//提交
					consign = this.saveSubmitConsign(consign,taskPlanId,consignId);
				}
			}
		}
		return consign;
	}
	
	public Consign saveSubmitConsign(Consign consign,String taskPlanId,String consignId)throws Exception{
		TaskPlan taskPlan = taskPlanManager.get(taskPlanId);
		//添加委托协议代理机构电话，代理机构名称，代理机构联系人字段
		consign.setUseStatus(CommonEnum.USER_STATUS_FORMAL);
		consign.setConfirmStatus(CommonEnum.CONFIRM_STATUS_NEGOTIATE);
		consignManager.save(consign);
		taskPlan.setUseStatus(CommonEnum.USER_STATUS_CANCEL);
		consignManager.save(taskPlan,TaskPlan.class);
		this.saveConsignTaskPlan(taskPlan, consign);//保存委托协议与申报书中间表数据
		if (consignId==null||"".equals(consignId)) {//判断该委托协议是否保存过：如果是则不创建工作计划否则创建工作计划
			projectPlanManager.createProjectPlan(null, AuthenticationHelper.getCurrentUser(true).getEmp().getCompany().getObjId(),consign.getObjId(),PlanTemplate.TEMPLATE_CODE_CONSIGN);
		}
		consign.setUser(AuthenticationHelper.getCurrentUser());
		consign.setWorkflowAuditStatus(WorkFlowModel.AUDIT_STATUS_PASS);
		consign.setParentBizId(consign.getObjId());
		completedWorkTaskManager.createCompTaskByPassivity("提交委托协议",CompletedWorkTaskTypeEnum.WORKTASK_TYPE_CONSIGN,"",consign.getObjId(),consign.getObjId(),"",CompletedWorkTaskResultEnum.WORKTASK_NOPASSAUDIT);
		return consign;
	}
	/**
	 * FuncName:saveAffirmConsign
	 * Description: 确认委托协议 支持批量  支持双向
	 * @param ids  委托协议id集，多个以逗号隔开,opinion 审核意见,flag:true pass;false no pass;
	 * @return Consign
	 * @author wanghz
	 * @Create Date 2010-9-6 下午04:55:31 
	 */
	public Consign saveAffirmConsign(String ids, String opinion,boolean flag)throws EsException{
		logger.debug("\nes=ConsignServiceImpl||saveAffirmConsign\n");
		Consign consign =null;
		String objIds[] = ids.split(",");
		for (String objId : objIds) {
			consign = consignManager.get(objId);
			if(flag){
				consign.setUseStatus(CommonEnum.USER_STATUS_FORMAL);
				consign.setConfirmStatus(CommonEnum.CONFIRM_STATUS_SURE);
				consignManager.auditConsign(consign);
				completedWorkTaskManager.createCompTaskByPassivity("确认委托协议",CompletedWorkTaskTypeEnum.WORKTASK_TYPE_CONSIGN,opinion,consign.getObjId(),consign.getObjId(),"",CompletedWorkTaskResultEnum.WORKTASK_PASSAUDIT);
				consign.setWorkflowAuditStatus(WorkFlowModel.AUDIT_STATUS_PASS);
			}
			else{
				consign.setUseStatus(CommonEnum.USER_STATUS_FORMAL);
				consign.setConfirmStatus(CommonEnum.CONFIRM_STATUS_BACK);
				consignManager.auditConsign(consign);
				completedWorkTaskManager.createCompTaskByPassivity("确认委托协议",CompletedWorkTaskTypeEnum.WORKTASK_TYPE_CONSIGN,opinion,consign.getObjId(),consign.getObjId(),"",CompletedWorkTaskResultEnum.WORKTASK_NOPASSAUDIT);
				consign.setWorkflowAuditStatus(WorkFlowModel.AUDIT_STATUS_NO_PASS);
			}
		}
		consign.setUser(AuthenticationHelper.getCurrentUser(true));
		consign.setParentBizId(ids);
		return consign;
	}
	
	/**
	 * FuncName:saveCreateBlockTradeDraftConsign
	 * Description: 创建大宗委托协议
	 * @param consign:委托协议,taskPlanIds:申报书主键
	 * @return Consign
	 * @author wanghz
	 * @Create Date 2010-9-7 上午11:56:17 
	 */
	public Consign saveCreateBlockTradeDraftConsign(Consign consign ,String taskPlanIds)throws EsException{
		logger.debug("\nes ConsignController||saveCreateBlockTradeDraftConsign\n");
		//添加委托协议代理机构电话，代理机构名称，代理机构联系人字段
		String consAgentId = consign.getConsAgent().getObjId();
		OrgInfo orgInfo = (OrgInfo) consignManager.get(consAgentId, OrgInfo.class);
		if(null!=orgInfo){
			consign.setConsAgentName(orgInfo.getCompany().getName());
			consign.setConsAgentLinker(orgInfo.getCompany().getContact());
			consign.setConsAgentTel(orgInfo.getCompany().getTel());
		}
		consign.setConfirmStatus(CommonEnum.CONFIRM_STATUS_WAIT);
		consignManager.save(consign);
		String[] taskPlanArray = taskPlanIds.split(",");
		for(int i=0;i<taskPlanArray.length;i++){
			/*设置大宗申报书的大宗审核状态为待确认 */
			TaskPlan taskPlan  = taskPlanManager.get(taskPlanArray[i]);
			taskPlan.setBlock_check_status(CommonEnum.CONFIRM_STATUS_WAIT);
			/*设置大宗申报书的新的代理机构 */
			if(null!=orgInfo){
				taskPlan.setTaskAgent(orgInfo);
				taskPlan.setTaskAgentName(orgInfo.getCompany().getName());
			}
			taskPlanManager.save(taskPlan);    //修改状态
			
			this.saveConsignTaskPlan(taskPlan, consign);//保存委托协议与申报书中间表数据
		}
		
		// 更新采购单位信息
		if (null != taskPlanArray && taskPlanArray.length>0) {
			TaskPlan taskPlan  = taskPlanManager.get(taskPlanArray[0]);
			consign.setConsBuyer((OrgInfo)taskPlan.getBudget());
			consign.setConsBuyerName(((OrgInfo)taskPlan.getBudget()).getOrgName());
			consign.setConsBuyerLinker(taskPlan.getBudgetLinker());
			consign.setConsBuyerTel(taskPlan.getBudgetLinkerTel());
			consignManager.save(consign);
		}
		completedWorkTaskManager.createCompTaskByPassivity("确认委托协议",CompletedWorkTaskTypeEnum.WORKTASK_TYPE_CONSIGN,"",consign.getObjId(),consign.getObjId(),"",CompletedWorkTaskResultEnum.WORKTASK_NOPASSAUDIT);
		return consign;
	}
	
	/**
	 * FuncName:saveSubmitBlockTradeDraftConsign
	 * Description: 提交大宗委托协议
	 * @param consign:委托协议对象,taskPlanIds:申报书主键
	 * @return Consign
	 * @author wanghz
	 * @Create Date 2010-9-7 上午11:56:17  
	 */
	public Consign saveSubmitBlockTradeDraftConsign(Consign consign ,String taskPlanIds)throws EsException{
		logger.debug("\nes ConsignController||saveSubmitBlockTradeDraftConsign\n");
		//添加委托协议代理机构电话，代理机构名称，代理机构联系人字段
		String consAgentId = consign.getConsAgent().getObjId();
		OrgInfo orgInfo = (OrgInfo) consignManager.get(consAgentId, OrgInfo.class);
		if(null!=orgInfo){
			consign.setConsAgentName(orgInfo.getCompany().getName());
			consign.setConsAgentLinker(orgInfo.getCompany().getContact());
			consign.setConsAgentTel(orgInfo.getCompany().getTel());
		}
		String consignId = consign.getObjId();
		consign.setConfirmStatus(CommonEnum.CONFIRM_STATUS_NEGOTIATE);
		consignManager.save(consign);
		String[] taskPlanArray = taskPlanIds.split(",");
		if (null == consignId || "".equals(consignId)) {
			for(int i=0;i<taskPlanArray.length;i++){
				/*设置大宗申报书的大宗审核状态为待确认 */
				TaskPlan taskPlan  = taskPlanManager.get(taskPlanArray[i]);
				taskPlan.setBlock_check_status(CommonEnum.CONFIRM_STATUS_WAIT);
				/*设置大宗申报书的新的代理机构 */
				if(null!=orgInfo){
					taskPlan.setTaskAgent(orgInfo);
					taskPlan.setTaskAgentName(orgInfo.getCompany().getName());
				}
				taskPlanManager.save(taskPlan);    //修改状态
				
				this.saveConsignTaskPlan(taskPlan, consign);//保存委托协议与申报书中间表数据
			}
		}
		// 更新采购单位信息
		if (null != taskPlanArray && taskPlanArray.length>0) {
			TaskPlan taskPlan  = taskPlanManager.get(taskPlanArray[0]);
			consign.setConsBuyer((OrgInfo)taskPlan.getBudget());
			consign.setConsBuyerName(((OrgInfo)taskPlan.getBudget()).getOrgName());
			consign.setConsBuyerLinker(taskPlan.getBudgetLinker());
			consign.setConsBuyerTel(taskPlan.getBudgetLinkerTel());
			consignManager.save(consign);
		}
		return consign;
	}
	
	/**
	 * FuncName:updateBlockTradeDraftConsign
	 * Description: 修改大宗委托协议
	 * @param consign:委托协议对象
	 * @return Consign
	 * @author wanghz
	 * @Create Date 2010-9-7 上午11:56:17 
	 */
	public Consign updateBlockTradeDraftConsign(Consign consign)throws EsException{
		logger.debug("\nes ConsignController||updateBlockTradeDraftConsign\n");
		consignManager.save(consign);
		return consign;
	}
	
	/**
	 * FuncName:removeNotBlockConsign
	 * Description : 删除非大宗委托协议 
	 * @param   consignId:委托协议主键
	 * @return void
	 * @author liuke  
	 * @Create Date: 2010-9-17上午10:43:31 
	 * @Modifier liuke
	 * @Modified Date: 2010-9-17上午10:43:31 
	 */
	public void removeNotBlockConsign(String consignId) throws EsException {
		List<TaskPlan> taskplanList = consignTaskPlanManager.getTaskPlanByConsign(consignId);//修改申报书状态
		   for(int i=0;i<taskplanList.size();i++) {
			   TaskPlan taskPlan = taskplanList.get(i);
			   taskPlan.setUseStatus(CommonEnum.USER_STATUS_FORMAL);
			   taskPlanManager.save(taskPlan);
		   }
		   consignTaskPlanManager.removeConsignTaskPlanByConsignId(consignId);//删除委托书与申报书中间表对象
		   consignManager.remove(consignId, Consign.class);//删除委托书
	}
	
	/**
	 * FuncName: getQuery
	 * Description : 用于查询与当前登录人相匹配的委托协议列表的前置条件
	 * @param query:组装的用于数据查询的对象, user:当前登录用户,findMethod:用以判断当前登录角色
	 * @return QueryObject
	 * @author: shenjz
	 * @Create Date:2010-12-20  下午03:31:25
	 * @Modifier: shenjz
	 * @Modified Date:2010-12-20  下午03:31:25
	 */
	public QueryObject getQueryObject(QueryObject query,User user,String findMethod){
		if("forConfirmByBuyer".equals(findMethod)){//采购人确认委托协议列表
			query.getQueryParam().and(new QueryParam("consBuyer.objId",QueryParam.OPERATOR_EQ ,((OrgInfo)user.getOrgInfo()).getObjId()));
			query.getQueryParam().and(new QueryParam("confirmStatus",QueryParam.OPERATOR_EQ ,CommonEnum.CONFIRM_STATUS_NEGOTIATE));
		}
		if("forPassByBuyer".equals(findMethod)){//采购人已经确认委托协议列表
			query.getQueryParam().and(new QueryParam("consBuyer.objId",QueryParam.OPERATOR_EQ ,((OrgInfo)user.getOrgInfo()).getObjId()));
			query.getQueryParam().and(new QueryParam("confirmStatus",QueryParam.OPERATOR_EQ ,CommonEnum.CONFIRM_STATUS_SURE));
		}
		if("forSubmitByAgent".equals(findMethod)){//代理机构待提交委托协议列表
			query.getQueryParam().and(new QueryParam("createUser.objId",QueryParam.OPERATOR_EQ ,user.getObjId()));
			query.getQueryParam().and(new QueryParam("confirmStatus",QueryParam.OPERATOR_EQ ,CommonEnum.CONFIRM_STATUS_WAIT));
		}
		if("waitConfirmByBuyer".equals(findMethod)){//代理机构待采购人确认
			query.getQueryParam().and(new QueryParam("createUser.objId",QueryParam.OPERATOR_EQ ,user.getObjId()));
			query.getQueryParam().and(new QueryParam("confirmStatus",QueryParam.OPERATOR_EQ ,CommonEnum.CONFIRM_STATUS_NEGOTIATE));
		}
		if("returnBackByBuyer".equals(findMethod)){//代理机构被采购人退回
			query.getQueryParam().and(new QueryParam("createUser.objId",QueryParam.OPERATOR_EQ ,user.getObjId()));
			query.getQueryParam().and(new QueryParam("confirmStatus",QueryParam.OPERATOR_EQ ,CommonEnum.CONFIRM_STATUS_BACK));
		}
		if("alreadyPassByBuyer".equals(findMethod)){//被采购人接收的委托协议列表
			query.getQueryParam().and(new QueryParam("createUser.objId",QueryParam.OPERATOR_EQ ,user.getObjId()));
			query.getQueryParam().and(new QueryParam("confirmStatus",QueryParam.OPERATOR_EQ ,CommonEnum.CONFIRM_STATUS_SURE));
		}
		if("forConfirmByAgency".equals(findMethod)){
			query.getQueryParam().and(new QueryParam("consAgent.objId",QueryParam.OPERATOR_EQ ,((OrgInfo)user.getOrgInfo()).getObjId()));
		}
		if("foCreateByBuyer".equals(findMethod)){
			query.getQueryParam().and(new QueryParam("createUser.objId",QueryParam.OPERATOR_EQ ,user.getObjId()));
		}
		query.getQueryProjections().setOrderProperty("consCode");
		query.getQueryProjections().setDescFlag(true);
		query.getQueryProjections().setOrderProperty("createTime");
		query.getQueryProjections().setDescFlag(true);
		return query;
	}


	/**
	 * 
	 * FuncName: getConsignByObjId
	 * Description :  根据主键得到委托协议
	 * @param 
	 * @param objId
	 * @return Consign
	 * @author: liuke
	 * @Create Date:2010-12-30  下午02:14:00
	 * @Modifier: liuke
	 * @Modified Date:2010-12-30  下午02:14:00
	 */
	public Consign getConsignByObjId(String objId) {
		return consignManager.get(objId);
	}


	/**
	 * 
	 * FuncName: saveConsign
	 * Description : 保存委托协议 
	 * @param 
	 * @param consign void
	 * @author: liuke
	 * @Create Date:2010-12-30  下午03:15:38
	 * @Modifier: liuke
	 * @Modified Date:2010-12-30  下午03:15:38
	 */
	public void saveConsign(Consign consign) {
		consignManager.save(consign);
	}
}

