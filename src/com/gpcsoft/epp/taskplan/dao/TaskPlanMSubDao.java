package com.gpcsoft.epp.taskplan.dao;

import java.math.BigDecimal;
import java.util.List;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.project.domain.ProjectMTaskPlan;
import com.gpcsoft.epp.taskplan.domain.TaskPlanMSub;
import com.gpcsoft.epp.taskplan.domain.TaskPlanSub;

public interface TaskPlanMSubDao extends BaseGenericDao<TaskPlanMSub> {
	
	/** 
	 * FuncName:getSubNotSumSubsByOrg
	 * Description : 根据机构，获得其下级机构的未汇总的采购书条目
	 * @param orgId:机构ID,status:汇总状态,taskType:申报书类型
	 * @return List<TaskPlanMSub>
	 * @author liangxj
	 * @Create Date：2010-6-3 下午04:12:39   
	 * @Modifier liangxj
	 * @Modified Date:  2010-6-3 下午04:12:39  
	 */
	public List<TaskPlanMSub> getSubNotSumSubsByOrg(String objId,String status, String taskType,String ebuyMethod);
	
	/** 
	 * FuncName:removeSumMSubs
	 * Description:根据taskPlan id,删除所属的申报书所有汇总的条目,不级联删除taskPlanSub
	 * @param taskPlanIds:申报书主键,status:汇总状态  
	 * @return void
	 * @author liangxj
	 * @Create Date：2010-6-3 下午04:12:39 
	 * @Modifier  liangxj
	 * @Modified Date:  2010-6-3 下午04:12:39 
	 */
	public void removeSumMSubs(String taskPlanIds,String status);

	/** 
	 * FuncName:
	 * Description :  根据申报书条目ID删除对应的中间表数据
	 * @param taskPlanSubId:申报书条目主键
	 * @return void
	 * @author Administrator
	 * @Create Date: 2010-7-10下午04:55:41 
	 * @Modifier  Administrator
	 * @Modified Date: 2010-7-10下午04:55:41 
	 */
	public void removeByTaskPlanSubId(String taskPlanSubId);
	
	/** 
	 * FuncName:getTaskPlanMSubByProjectId
	 * Description :  根据项目id获取申报书与申报书条目中间对象
	 * @param   projectId:项目主键s
	 * @return  List<TaskPlanMSub>
	 * @author yangx
	 * @Create Date: 2010-6-24下午01:10:46 
	 * @Modifier   yangx
	 * @Modified Date: 2010-6-24下午01:10:46  
	 */
	@SuppressWarnings("unchecked")
	public List getTaskPlanMSubByProjectId(String projectId);
	
	/**
	 * FuncName:getTaskPlanSubByTaskPlan
	 * Description : 根据申报书获得申报书明细列表
	 * @param   taskPlanId:申报书主键
	 * @return  List<TaskPlanSub>
	 * @author liuke
	 * @Create Date: 2010-7-15上午09:34:57 
	 * @Modifier  liuke 
	 * @Modified Date: 2010-7-15上午09:34:57 
	 */
	public List<TaskPlanSub> getTaskPlanSubByTaskPlan(String TaskPlanId);
	
	/**
	 * FuncName:getTaskPlanMSubByStatus
	 * Description:获取本级的申报书明细
	 * @param	taskPlanId:申报书主键
	 * @return  List<TaskPlanSub>
	 * @author shenjianzhuang
	 * @Create Date: 2010-9-16上午10:26:21 
	 * @Modifier shenjianzhuang
	 * @Modified Date: 2010-9-16上午10:26:21 
	 */  
	public List<TaskPlanMSub> getTaskPlanMSubByStatus(String taskPlanId);
	
	/** 
	 * FuncName:getAllTaskPlanMSubForCreateProj
	 * Description :  得到所有已确认委托协议后的申报书、申报书明细中间列表
	 * @param page:分页对象,taskAgentId:代理机构ID,ids:申报书条目主键,purCategoryId:品目主键,ebuyMethod:采购方式,taskType:申报书类型
	 * @return  Page<TaskPlanMSub>
	 * @author yangx
	 * @Create Date: 2010-9-17上午10:57:56 
	 * @Modifier  yangx
	 * @Modified Date: 2010-9-17上午10:57:56  
	 */
	public Page getAllTaskPlanMSubForCreateProj(Page page,String taskAgentId,String ids,String purCategoryId,String ebuyMethod,String taskType);
	
	/**
	 * FuncName:getTaskPlanMSubsByPackId
	 * Description: 根据包件ID获取所有申报书明细中间表
	 * @param packId
	 * @return List<TaskPlanSub>
	 * @author wanghz
	 * @Create Date 2010-10-8 上午10:51:10 
	 */
	public List<TaskPlanMSub> getTaskPlanMSubsByPackId(String packId);
	
	/** 
	 * FuncName:getTaskPlanMSubByTaskPlanIdAndStatus
	 * Description :  根据申报书和汇总状态查询申报书、条目中间表
	 * @param   taskPlanId：申报书Id,status：状态
	 * @return  List<TaskPlanMSub>
	 * @author yangx
	 * @Create Date: 2010-11-18下午04:01:52 
	 * @Modifier  yangx
	 * @Modified Date: 2010-11-18下午04:01:52 by 
	 */
	public List<TaskPlanMSub>  getTaskPlanMSubByTaskPlanIdAndStatus(String taskPlanId,String status);
	
	/** 
	 * FuncName:getAllTaskPlanMSubForCreatedProj
	 * Description :  查询已完成采购申报书
	 * @param  page:分页对象 createUser:创建人 purchaseName:品目名称 ebuyMethod:采购方式
	 * @return  Page<ProjectMTaskPlan> 
	 * @author shenjz
	 * @Create Date: 2010-11-22上午10:57:56 
	 * @Modifier shenjz
	 * @Modified Date: 2010-11-22上午10:57:56 
	 */
	public Page<ProjectMTaskPlan> getAllTaskPlanMSubForCreatedProj(final Page page,final String createUser,final String purchaseName,final String ebuyMethod) ;
	
	/**
	 * FuncName:getTaskPlanMSubListByQueryObject
	 * Description :根据项目查询申报书与申报书条目中间表数据  
	 * @param   queryObject:组装的查询条件对象
	 * @return  List<TaskPlanMSub>
	 * @author liuke
	 * @Create Date: 2010-11-25上午10:38:53 
	 * @Modifier liuke
	 * @Modified Date: 2010-11-25上午10:38:53 
	 */
	public List<TaskPlanMSub> getTaskPlanMSubListByQueryObject(QueryObject queryObject);
	
	/** 
	 * Description :  查询待立项的申报书条目数
	 * Create Date: 2011-1-22上午11:34:14 by yangx  Modified Date: 2011-1-22上午11:34:14 by yangx
	 * @param   taskAgentId：代理机构Id,ids：已经立项的Ids
	 * @return  List<TaskPlanMSub>
	 * @Exception   
	 */
	public List<TaskPlanMSub> getAllTaskPlanMSubForWaitCreateProj(final String taskAgentId,final String ids);
	
	
	
	
	/**
	 * FuncName: getTaskPlanMSubForECPCreateProj
	 * Description :  创建或修改对象
	 * @param page:分页对象,taskAgentId:代理机构ID,ids:申报书条目主键,purCategoryId:品目主键,ebuyMethod:采购方式,taskType:申报书类型
	 * @param page
	 * @param taskAgentId
	 * @param purCategoryId
	 * @param ebuyMethod
	 * @param taskType
	 * @return Page
	 * @author: liuke
	 * @Create Date:2011-3-1  下午12:26:31
	 * @Modifier: liuke
	 * @Modified Date:2011-3-1  下午12:26:31
	 */
	public Page getTaskPlanMSubForECPCreateProj(Page page,String taskAgentId,String purCategoryId,String ebuyMethod,String taskType);
	
	
	/**
	 * FuncName: getTaskPlanMSubForECPWaitCreateProj
	 * Description :  创建或修改对象
	 * @param 
	 * @param taskAgentId
	 * @param ids
	 * @return List<TaskPlanMSub>
	 * @author: liuke
	 * @Create Date:2011-3-1  下午02:30:34
	 * @Modified Date: 2011-10-19下午01:28:09 by yangx
	 */
	public BigDecimal getTaskPlanMSubForECPWaitCreateProj(final String taskAgentId);
	
	/**
	 * FuncName: getTaskPlanMSubByTaskPlanSubId
	 * Description : 根据申报书条目主键得到申报书条目与申报书中间表对象
	 * @param taskPlanSubId 申报书条目ID
	 * @return List<TaskPlanMSub>
	 * @author: liuke
	 * @Create Date:2011-3-1  下午06:29:50
	 * @Modifier: liuke
	 * @Modified Date:2011-3-1  下午06:29:50
	 */
	public List<TaskPlanMSub> getTaskPlanMSubByTaskPlanSubId(String taskPlanSubId);
	
	
	
	
	
	/**
	 * FuncName: saveTaskPlanMSubBySQL
	 * Description :  保存申报书明细对象
	 * @param  void
	 * @author: liuke
	 * @Create Date:2011-4-12  下午05:28:44
	 * @Modifier: liuke
	 * @Modified Date:2011-4-12  下午05:28:44
	 */
	public void saveTaskPlanMSubBySQL(TaskPlanMSub taskPlanMSub)throws EsException;

	/**
	 * FuncName: getTaskPlanMSubForECPCreateProj
	 * Description :  创建或修改对象
	 * @param page:分页对象,taskAgentId:代理机构ID,ids:申报书条目主键,purCategoryId:品目主键,ebuyMethod:采购方式,taskType:申报书类型
	 * @param page
	 * @param taskAgentId
	 * @param ids
	 * @param purCategoryId
	 * @param ebuyMethod
	 * @param taskType
	 * @return Page
	 * @author: liuke
	 * @Create Date:2011-3-1  下午12:26:31
	 * @Modifier: liuke
	 * @Modified Date:2011-3-1  下午12:26:31
	 */
	public Page getTaskPlanMSubForECPCreateProj(Page page,String taskAgentId,String ids,String purCategoryId,String ebuyMethod,String taskType);

	/**
	 * FuncName: getTaskPlanMSubForECPWaitCreateProj
	 * Description :  创建或修改对象
	 * @param 
	 * @param taskAgentId
	 * @param ids
	 * @return List<TaskPlanMSub>
	 * @author: liuke
	 * @Create Date:2011-3-1  下午02:30:34
	 * @Modifier: liuke
	 * @Modified Date:2011-3-1  下午02:30:34
	 */
	public List<TaskPlanMSub> getTaskPlanMSubForECPWaitCreateProj(final String taskAgentId,final String ids);
	
	
}