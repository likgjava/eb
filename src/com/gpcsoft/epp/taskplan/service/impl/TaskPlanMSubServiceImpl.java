package com.gpcsoft.epp.taskplan.service.impl;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryObjectBase;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.project.domain.ProjectMTaskPlan;
import com.gpcsoft.epp.taskplan.dao.TaskPlanMSubDao;
import com.gpcsoft.epp.taskplan.domain.TaskPlan;
import com.gpcsoft.epp.taskplan.domain.TaskPlanMSub;
import com.gpcsoft.epp.taskplan.domain.TaskPlanSub;
import com.gpcsoft.epp.taskplan.manager.TaskPlanMDetailManager;
import com.gpcsoft.epp.taskplan.manager.TaskPlanMSubManager;
import com.gpcsoft.epp.taskplan.manager.TaskPlanManager;
import com.gpcsoft.epp.taskplan.service.TaskPlanMSubService;

@Service 
public class TaskPlanMSubServiceImpl extends BaseGenericServiceImpl<TaskPlanMSub> implements TaskPlanMSubService {

	@Autowired(required=true) @Qualifier("taskPlanMSubManagerImpl")
	private TaskPlanMSubManager taskPlanMSubManager;
	
	@Autowired(required=true) @Qualifier("taskPlanMDetailManagerImpl")
	private TaskPlanMDetailManager taskPlanMDetailManager;
	
	@Autowired(required=true) @Qualifier("taskPlanManagerImpl")
	private TaskPlanManager taskPlanManager;
	
	@Autowired(required=true)  @Qualifier("taskPlanMSubDaoHibernate")
	private TaskPlanMSubDao taskPlanMSubDaoHibernate;
	
	/** 
	 * Description :根据任务书返回任务书条目ids，逗号分隔  
	 * Create Date: 2011-9-20下午04:09:01 by sunl  Modified Date: 2011-9-20下午04:09:01 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public String getTaskPlanSubIdsByTaskPlanId(String taskPlanId) throws Exception {
		String ids = "";
		List<TaskPlanMSub> taskPlanSubList = this.findByHql("from TaskPlanMSub t where t.taskPlan.objId=?", new String[]{taskPlanId});
		for (TaskPlanMSub taskPlanSub : taskPlanSubList) {
			ids += taskPlanSub.getTaskPlanSub().getObjId() + ",";
		}
		if(ids.length()>0) {
			ids=ids.substring(0,ids.length()-1);
		}
		return ids;
	}
	
	/** 
	 * FuncName:getSubNotSumSubsByOrg
	 * Description:根据机构,获得其下级机构的未汇总的采购书条目
	 * @param objId:部门ID,status:汇总状态,taskType:申报书类型
	 * @return  List<TaskPlanMSub> 
	 * @author liangxj
	 * @Create Date：2010-6-3 下午04:12:39      
	 * @Modified liangxj
	 * @Modified Date:  2010-6-3 下午04:12:39  
	 */
	public List<TaskPlanMSub> getSubNotSumSubsByOrg(String objId,String status, String taskType,String ebuyMethod){
		logger.debug("\nTaskPlanMSubServiceImpl||getSubNotSumSubsByOrg\n");
		return taskPlanMSubManager.getSubNotSumSubsByOrg(objId,status, taskType,ebuyMethod);
	}
	
	/** 
	 * FuncName:saveMSubsByTaskPlan
	 * Description:根据申报书的主键,将其下的条目和明细汇总
	 * @param taskPlanIds  申报书id，以逗号分割  sumTaskPlanId 汇总的申报书id
	 * @return void
	 * @author liangxj
	 * @Create Date：2010-6-3 下午04:12:39 
	 * @Modifier:liangxj
	 * @Modified Date:  2010-6-3 下午04:12:39  
	 */
	public void saveMSubsByTaskPlan(String taskPlanIds,String sumTaskPlanId,String status){
		logger.debug("\nTaskPlanMSubServiceImpl||saveMSubsByTaskPlan\n");
		taskPlanMSubManager.saveMSubsByTaskPlan(taskPlanIds, sumTaskPlanId,status);//保存条目
		taskPlanMDetailManager.saveMDetailsByTaskPlan(taskPlanIds, sumTaskPlanId, status);//保存资金明细
	}

	/** 
	 * FuncName:removeSumMSubs
	 * Description : 根据申报书条目主键,删除所属的申报书所有汇总的条目,不级联删除taskPlanSub
	 * @param taskPlanIds:申报书主键,status:汇总状态
	 * @return void
	 * @author liangxj
	 * @Create Date：2010-6-3 下午04:12:39   
	 * @Modifier:liangxj
	 * @Modified Date:  2010-6-3 下午04:12:39  
	 */
	public void removeSumMSubs(String taskPlanSubIds, String status) {
		logger.debug("\nTaskPlanMSubServiceImpl||removeSumMSubs\n");
		//查询taskPlan
		List<TaskPlan> taskPlanList = taskPlanManager.getTaskPlanBySubs(taskPlanSubIds, status);
		//拼装taskPlan的id，以逗号分隔
		StringBuffer tIds = new StringBuffer();
		for (TaskPlan taskPlan : taskPlanList) {
			tIds.append(taskPlan.getObjId() + ",");
		}
		taskPlanMSubManager.removeSumMSubs(tIds.substring(0,tIds.length()-1), status);//删除任务条目
		taskPlanMDetailManager.removeSumMDetails(tIds.substring(0,tIds.length()-1), status);//删除资金明细
	}
	
	/** 
	 * FuncName:backSumMSubs
	 * Description : 退回申报书条目，根据申报书条目主键,删除所属的申报书所有汇总的条目,不级联删除申报书条目,
	 * 并同步将资金明细删除,将对应申报书的状态改为临时
	 * @param taskPlanSubIds:申报书条目主键,status:汇总状态
	 * @return void
	 * @author liangxj
	 * @Create Date：2010-6-3 下午04:12:39 
	 * @Modifier  liangxj
	 * @Modified Date:  2010-6-3 下午04:12:39  
	 */
	public void backSumMSubs(String taskPlanSubIds, String status) {
		logger.debug("\nTaskPlanMSubServiceImpl||backSumMSubs\n");
		//查询申报书集合
		List<TaskPlan> taskPlanList = taskPlanManager.getTaskPlanBySubs(taskPlanSubIds, status);
		//拼装taskPlan的主键，以逗号分隔
		String tIds = "";
		for (TaskPlan taskPlan : taskPlanList) {
			tIds += taskPlan.getObjId() + ",";
		}
		tIds = tIds.substring(0,tIds.length()-1);
		taskPlanMSubManager.removeSumMSubs(tIds, status);//删除任务条目
		taskPlanMDetailManager.removeSumMDetails(tIds, status);//删除资金明细
		//修改对应申报书的状态
		taskPlanManager.submitTaskPlan(tIds,CommonEnum.USER_STATUS_TEMP,CommonEnum.CONFIRM_STATUS_WAIT,CommonEnum.CONFIRM_STATUS_WAIT);
	}
	
	/** 
	 * FuncName:getTaskPlanSubByTaskPlanId
	 * Description:根据申报书id获取采购申报书明细
	 * @param   taskPlanId:申报书主键
	 * @return  List<TaskPlanMSub>
	 * @author yangx
	 * @Create Date: 2010-6-30上午10:32:43 
	 * @Modifier  yangx
	 * @Modified Date: 2010-6-30上午10:32:43  
	 */
	public List<TaskPlanMSub> getTaskPlanSubByTaskPlanId(String taskPlanId){
		logger.debug("\nTaskPlanMSubServiceImpl||getTaskPlanSubByTaskPlanId\n");
		return taskPlanMSubManager.getTaskPlanSubByTaskPlanId(taskPlanId);
	}
	
	/**
	 * FuncName:getTaskPlanSubByTaskPlan
	 * Description:根据申报书获得申报书明细列表
	 * @param   TaskPlanId:申报书主键
	 * @return  List<TaskPlanSub>
	 * @author liuke
	 * @Create Date: 2010-7-15上午09:34:57    
	 * @Modifier liuke
	 * @Modified Date: 2010-7-15上午09:34:57 
	 */
	public List<TaskPlanSub> getTaskPlanSubByTaskPlan(String TaskPlanId) {
		logger.debug("\nTaskPlanMSubServiceImpl||getTaskPlanSubByTaskPlan\n");
		return taskPlanMSubManager.getTaskPlanSubByTaskPlan(TaskPlanId);
	}
	
	/**
	 * FuncName:getTaskPlanMSubByStatus
	 * Description:获取本级的申报书明细预算总金额的和
	 * @param taskPlanId:申报书主键
	 * @return  float
	 * @author shenjianzhuang
	 * @Create Date: 2010-9-16上午10:26:21    
	 * @Modifier:shenjianzhuang
	 * @Modified Date: 2010-9-16上午10:26:21  
	 */  
	public float getTaskPlanMSubByStatus(String taskPlanId) {
		logger.debug("\nTaskPlanMSubServiceImpl||getTaskPlanMSubByStatus\n");
		float moneySub = 0;
		List<TaskPlanMSub> taskPlanMSubs = taskPlanMSubManager.getTaskPlanMSubByStatus(taskPlanId);
		if(taskPlanMSubs.size()!=0){
			for (Iterator<TaskPlanMSub> iterator = taskPlanMSubs.iterator(); iterator
					.hasNext();) {
				TaskPlanMSub taskPlanMSub = (TaskPlanMSub) iterator.next();
				TaskPlanSub taskPlanSub = taskPlanMSub.getTaskPlanSub();
				moneySub+= taskPlanSub.getTotalPrice().intValue();
			}
		}
		return moneySub;
	}
	
	/** 
	 * FuncName:getAllTaskPlanMSubForCreateProj
	 * Description:得到所有已确认委托协议后的申报书、申报书明细中间列表
	 * @param   page:分页对象,taskAgentId:代理机构id,purCategoryId:品目Id,ebuyMethod:采购方式,taskType:申报书类型
	 * @return  Page
	 * @author yangx
	 * @Create Date: 2010-9-17上午10:57:56   
	 * @Modifier : yangx
	 * @Modified Date: 2010-9-17上午10:57:56  
	 */
	public Page getAllTaskPlanMSubForCreateProj(Page page,String taskAgentId,String ids,String purCategoryId,String ebuyMethod,String taskType){
		logger.debug("\nes=TaskPlanMSubServiceImpl||getAllTaskPlanMSubForCreateProj\n");
		String id = null;
		if (ids!=null&&!"".equals(ids)) {
			id = ids.replace(",", "','");
		}
		return taskPlanMSubDaoHibernate.getAllTaskPlanMSubForCreateProj(page,taskAgentId,id,purCategoryId,ebuyMethod,taskType);
	}
	
	/** 
	 * Description :  查询待立项的申报书条目数
	 * Create Date: 2011-1-22上午11:34:14 by yangx  Modified Date: 2011-1-22上午11:34:14 by yangx
	 * @param   taskAgentId：代理机构Id,ids：已经立项的Ids
	 * @return  List<TaskPlanMSub>
	 * @Exception   
	 */
	public List<TaskPlanMSub> getAllTaskPlanMSubForWaitCreateProj(final String taskAgentId,final String ids){
		logger.debug("\nes=TaskPlanMSubServiceImpl||getAllTaskPlanMSubForWaitCreateProj\n");
		String id = null;
		if (ids!=null&&!"".equals(ids)) {
			id = ids.replace(",", "','");
		}
		return taskPlanMSubDaoHibernate.getAllTaskPlanMSubForWaitCreateProj(taskAgentId, id);

	}
	
	/** 
	 * FuncName:getTaskPlanMSubByObjId
	 * Description:根据主键获取申报书、申报书条目中间对象
	 * @param   objId:申报书与申报书条目中间表对象主键
	 * @return  TaskPlanMSub
	 * @author yangx
	 * @Create Date: 2010-9-19上午11:02:31 
	 * @Modifier    yangx
	 * @Modified Date: 2010-9-19上午11:02:31  
	 */
	public TaskPlanMSub getTaskPlanMSubByObjId(String objId){
		logger.debug("\nes=TaskPlanMSubServiceImpl||getTaskPlanMSubByObjId\n");
		return taskPlanMSubManager.get(objId);
	}
	
	/** 
	 * FucnName getTaskPlanMSubByProjectId
	 * Description:根据项目主键获取申报书与申报书条目中间对象
	 * @param   projectId:项目主键
	 * @return  List
	 * @author yangx
	 * @Create Date: 2010-6-24下午01:10:46 
	 * @Modifier   	yangx
	 * @Modified Date: 2010-6-24下午01:10:46   
	 */
	@SuppressWarnings("unchecked")
	public List getTaskPlanMSubByProjectId(String projectId){
		logger.debug("\nes=TaskPlanMSubServiceImpl||getTaskPlanMSubByProjectId\n");
		return taskPlanMSubDaoHibernate.getTaskPlanMSubByProjectId(projectId);
	}
	
	/** 
	 * FuncName getAllTaskPlanMSubForCreatedProj
	 * Description:查询已完成采购申报书
	 * @param   page:分页对象,createUser:创建人,purchaseName:申报书名称,ebuyMethod:采购方式
	 * @return  Page<ProjectMTaskPlan>
	 * @author shenjz
	 * @Create Date: 2010-11-22上午10:57:56 
	 * @Modifier :  shenjz
	 * @Modified Date: 2010-11-22上午10:57:56 
	 */
	@SuppressWarnings("unchecked")
	public Page<ProjectMTaskPlan> getAllTaskPlanMSubForCreatedProj( Page page,String createUser,String purchaseName,String ebuyMethod){
		logger.debug("\nes=TaskPlanMSubServiceImpl||getAllTaskPlanMSubForCreatedProj\n");
		return taskPlanMSubDaoHibernate.getAllTaskPlanMSubForCreatedProj(page,createUser,purchaseName,ebuyMethod);
	}

	/**
	 * FuncName:getTaskPlanMSubListByProjectId
	 * Description:根据项目主键对象查询申报书条目与申报书中间表 
	 * @param   projectId:项目主键
	 * @return  List<TaskPlanMSub>
	 * @author liuke
	 * @Create Date: 2010-11-25上午10:54:31   
	 * @Modifier:liuke
	 * @Modified Date: 2010-11-25上午10:54:31  
	 */
	public List<TaskPlanMSub> getTaskPlanMSubListByProjectId(String projectId) {
			logger.debug("\nes=TaskPlanMSubServiceImpl||getAllTaskPlanMSubForCreatedProj\n");
		    QueryObject<TaskPlanMSub> queryObject = new QueryObjectBase<TaskPlanMSub>();
		    queryObject.setEntityClass(TaskPlanMSub.class);
		    queryObject.getQueryParam().and(new QueryParam("projectId",QueryParam.OPERATOR_EQ,projectId));
		    return taskPlanMSubDaoHibernate.getTaskPlanMSubListByQueryObject(queryObject);
	}

	
	/**
	 * FuncName: getTaskPlanMSubForECPCreateProj
	 * Description :得到所有已确认委托协议后的申报书、申报书明细中间列表
	 * @param page:分页对象,taskAgentId:代理机构id,purCategoryId:品目Id,ebuyMethod:采购方式,taskType:申报书类型
	 * @param page
	 * @param taskAgentId
	 * @param ids
	 * @param purCategoryId
	 * @param ebuyMethod
	 * @param taskType
	 * @return Page
	 * @author: liuke
	 * @Create Date:2011-3-1  上午11:57:18
	 * @Modifier: liuke
	 * @Modified Date:2011-3-1  上午11:57:18
	 */
	public Page getTaskPlanMSubForECPCreateProj(Page page, String taskAgentId,String purCategoryId, String ebuyMethod, String taskType) {
		logger.debug("\nes=TaskPlanMSubServiceImpl||getTaskPlanMSubForECPCreateProj\n");
//		String id = null;
//		if (ids!=null&&!"".equals(ids)) {
//			id = ids.replace(",", "','");
//		}
		return taskPlanMSubDaoHibernate.getTaskPlanMSubForECPCreateProj(page, taskAgentId,purCategoryId, ebuyMethod, taskType);
	}

	
	/**
	 * FuncName: getTaskPlanMSubForECPWaitCreateProj
	 * Description :  创建或修改对象
	 * @param  taskAgentId：代理机构Id,ids：已经立项的Ids
	 * @param taskAgentId
	 * @param ids
	 * @return List<TaskPlanMSub>
	 * @author: liuke
	 * @Create Date:2011-3-1  下午02:28:39
	 * @Modified Date: 2011-10-19下午01:28:09 by yangx
	 */
	public BigDecimal getTaskPlanMSubForECPWaitCreateProj(String taskAgentId) {
		logger.debug("\nes=TaskPlanMSubServiceImpl||getTaskPlanMSubForECPWaitCreateProj\n");
		return taskPlanMSubDaoHibernate.getTaskPlanMSubForECPWaitCreateProj(taskAgentId);
	}

	
	/**
	 * FuncName: getTaskPlanMSubByTaskPlanSubId
	 * Description :  根据申报书条目主键得到申报书条目与申报书中间表对象
	 * @param 
	 * @param TaskPlanSubId
	 * @return List<TaskPlanMSub>
	 * @author: liuke
	 * @Create Date:2011-3-1  下午06:27:31
	 * @Modifier: liuke
	 * @Modified Date:2011-3-1  下午06:27:31
	 */
	public List<TaskPlanMSub> getTaskPlanMSubByTaskPlanSubId(
			String TaskPlanSubId) {
		String id = null;
		if (TaskPlanSubId!=null&&!"".equals(TaskPlanSubId)) {
			id = TaskPlanSubId.replace(",", "','");
		}
		return taskPlanMSubDaoHibernate.getTaskPlanMSubByTaskPlanSubId(id);
	}
}
