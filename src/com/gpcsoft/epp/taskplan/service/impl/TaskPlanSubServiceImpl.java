package com.gpcsoft.epp.taskplan.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.requirement.domain.PreqEntry;
import com.gpcsoft.epp.requirement.manager.PreqEntryManager;
import com.gpcsoft.epp.taskplan.dao.TaskPlanSubDao;
import com.gpcsoft.epp.taskplan.domain.TaskPlanSub;
import com.gpcsoft.epp.taskplan.manager.TaskPlanMSubManager;
import com.gpcsoft.epp.taskplan.manager.TaskPlanSubManager;
import com.gpcsoft.epp.taskplan.service.TaskPlanSubService;

@Service 
public class TaskPlanSubServiceImpl extends BaseGenericServiceImpl<TaskPlanSub> implements TaskPlanSubService {

	@Autowired(required=true) @Qualifier("taskPlanSubManagerImpl")
	private TaskPlanSubManager taskPlanSubManager;
	
	@Autowired(required=true) @Qualifier("taskPlanMSubManagerImpl")
	private TaskPlanMSubManager taskPlanMSubManagerImpl;
	
	@Autowired(required=true)  @Qualifier("taskPlanSubDaoHibernate")
	private TaskPlanSubDao taskPlanSubDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("preqEntryManagerImpl")
	private PreqEntryManager preqEntryManagerImpl;//需求条目对应的服务
	
	/**
	 * FuncName: saveTaskPlanSubAndPreqEntry
	 * Description :  保存申报书条目和需求条目
	 * @param taskPlanSub:申报书条目对象,preqEntry:需求对象,taskPlanId:申报书Id
	 * @return void
	 * @author Administrator
	 * @Create Date: 2010-7-10下午01:54:32 
	 * @Modifier Administrator
	 * @Modified Date: 2010-7-10下午01:54:32  
	 */
	public void saveTaskPlanSubAndPreqEntry(TaskPlanSub taskPlanSub,PreqEntry preqEntry, String taskPlanId) {
		logger.debug("\nTaskPlanSubServiceImpl||saveTaskPlanSubAndPreqEntry\n");
		taskPlanSubManager.saveTaskPlanSubAndPreqEntry(taskPlanSub, preqEntry, taskPlanId);
	}
	
	/** 
	 * FuncName:removeTaskPlanSubAndPreqBySubId
	 * Description :  根据申报书条目ID删除申报书条目和对应的需求条目信息
	 * @param taskPlanSubId:申报书条目主键
	 * @return void 
	 * @author Administrator
	 * @Create Date: 2010-7-10下午03:53:26
	 * @Modifier Administrator
	 * @Modified Date: 2010-7-10下午03:53:26 by Administrator
	 */
	public void removeTaskPlanSubAndPreqBySubId(String taskPlanSubId) {
		logger.debug("\nTaskPlanSubServiceImpl||removeTaskPlanSubAndPreqBySubId\n");
		taskPlanMSubManagerImpl.removeByTaskPlanSubId(taskPlanSubId);//删除中间表
		preqEntryManagerImpl.removeByTaskPlanSubId(taskPlanSubId);//删除需求条目
		taskPlanSubManager.remove(taskPlanSubId, TaskPlanSub.class);//删除申报书条目
	}
	
	/**
	 * FuncName:removeTaskPlanSub
	 * Description: 删除申报书明细,同时删除关联申报书中间表
	 * @param objId 申报书明细ID
	 * @return void
	 * @author wanghz
	 * @Create Date 2010-7-26 下午05:43:10  
	 */
	public void removeTaskPlanSub(String objId) throws Exception {
		logger.debug("\nTaskPlanSubServiceImpl||removeTaskPlanSub\n");
		taskPlanSubManager.removeTaskPlanSub(objId);
	}
	
	/**
	 * FuncName: getTaskPlanSubByTaskPlanSubIds
	 * Description :  根据申报书明细Ids获取申报书明细
	 * @param   page:分页对象,taskPlanSubIds:申报书条目主键
	 * @return  Page<TaskPlanSub>
	 * @author yangx
	 * @Create Date: 2010-9-2下午02:32:46 
	 * @Modifier:yangx
	 * @Modified Date: 2010-9-2下午02:32:46 by 
	 */
	public Page<TaskPlanSub> getTaskPlanSubByTaskPlanSubIds(Page<TaskPlanSub> page,String taskPlanSubIds){
		logger.debug("\nTaskPlanSubServiceImpl||getTaskPlanSubByTaskPlanSubIds\n");
		return taskPlanSubDaoHibernate.getTaskPlanSubByTaskPlanSubIds(page,taskPlanSubIds);
	}
	
	/** 
	 * FuncName:getTaskPlanSubByProjectId
	 * Description :  根据项目Id获取申报书明细
	 * @param   projectId:项目主键
	 * @return  List<TaskPlanSub>
	 * @author yangx
	 * @Create Date: 2010-9-2下午03:50:23 
	 * @Modifier yangx    
	 * @Modified Date: 2010-9-2下午03:50:23  
	 */
	public List<TaskPlanSub> getTaskPlanSubByProjectId(String projectId){
		logger.debug("\nTaskPlanSubServiceImpl||getTaskPlanSubByProjectId\n");
		return taskPlanSubDaoHibernate.getTaskPlanSubByProjectId(projectId);
	}
	
	/**
	 * FuncName:getLowerLeverTaskPlanSubByTaskPlan
	 * Description: 获取下级申报书汇总明细
	 * @param:taskPlanId:申报书主键
	 * @return void
	 * @author wanghz
	 * @Create Date 2010-9-15 下午06:14:48  
	 */
	public List<TaskPlanSub> getLowerLeverTaskPlanSubByTaskPlan(String taskPlanId)throws EsException{
		logger.debug("\nTaskPlanSubServiceImpl||getLowerLeverTaskPlanSubByTaskPlan\n");
		return taskPlanSubDaoHibernate.getLowerLeverTaskPlanSubByTaskPlan(taskPlanId);
	}
	public List<TaskPlanSub> getTaskPlanSubList(String objIds) throws Exception {
		return taskPlanSubDaoHibernate.getTaskPlanSubList(objIds);
	}


	public Page<TaskPlanSub> getTaskPlanSub(Page<TaskPlanSub> page,
			Map<String, Object> paramsMap) throws Exception {
		return taskPlanSubDaoHibernate.getTaskPlanSub(page, paramsMap);
	}
}
