package com.gpcsoft.epp.taskplan.dao;

import java.util.List;
import java.util.Map;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.taskplan.domain.TaskPlanSub;

public interface TaskPlanSubDao extends BaseGenericDao<TaskPlanSub> {
	
	/**
	 * FuncName:removeTaskPlanSub
	 * Description: 删除申报书明细,同时删除关联申报书中间表
	 * @param objId 申报书明细ID
	 * @return void
	 * @author wanghz
	 * @Create Date 2010-7-26 下午05:43:10 
	 */
	public void removeTaskPlanSub(String objId)throws Exception;
	
	/** 
	 * FuncName:getTaskPlanSubByTaskPlanSubIds
	 * Description :  根据申报书明细Ids获取申报书明细
	 * @param page:分页对象,taskPlanSubIds:申报书条目主键
	 * @return  Page<TaskPlanSub>
	 * @author yangx
	 * @Create Date: 2010-9-2下午02:32:46 
	 * @Modifier    yangx
	 * @Modified Date: 2010-9-2下午02:32:46 by 
	 */
	public Page<TaskPlanSub> getTaskPlanSubByTaskPlanSubIds(Page<TaskPlanSub> page,String taskPlanSubIds);
	
	/** 
	 * FuncName:getTaskPlanSubByProjectId
	 * Description :  根据项目Id获取申报书明细
	 * @param  projectId:项目ID 
	 * @return  List<TaskPlanSub>
	 * @author yangx
	 * @Create Date: 2010-9-2下午03:50:23 
	 * @Modifier yangx
	 * @Modified Date: 2010-9-2下午03:50:23 
	 */
	public List<TaskPlanSub> getTaskPlanSubByProjectId(String projectId);
	
	/**
	 * FuncName:getLowerLeverTaskPlanSubByTaskPlan
	 * Description: 获取下级申报书汇总明细
	 * @param taskPlanId:申报书主键
	 * @return void
	 * @author wanghz
	 * @Create Date 2010-9-15 下午06:14:48  
	 */
	public List<TaskPlanSub> getLowerLeverTaskPlanSubByTaskPlan(final String taskPlanId)throws EsException;
	
	
	/**
	 * FuncName: saveTaskPlanSubBySQL
	 * Description :  保存申报书明细对象
	 * @param  void
	 * @author: liuke
	 * @Create Date:2011-4-12  下午05:28:44
	 * @Modifier: liuke
	 * @Modified Date:2011-4-12  下午05:28:44
	 */
	public void saveTaskPlanSubBySQL(TaskPlanSub taskPlanSub)throws EsException;
	
	/**
	 * 获取任务单条目
	 * Description :  
	 * Create Date: 2010-4-13下午04:13:15 by sunl  Modified Date: 2010-4-13下午04:13:15 by sunl
	 * @param   
	 * @return  
	 * @Exception
	 */
	@SuppressWarnings("unchecked")
	public List<TaskPlanSub> getTaskPlanSubList(final String objIds) throws Exception;
	
	/**
	 * 获取预算单位的挑选任务书(仅用于协议供货)
	 * @return
	 * @throws Exception
	 * Create Date: 2011-3-29上午11:12:15 by zhouzhanghe 
	 */
	@SuppressWarnings("unchecked")
	public Page<TaskPlanSub> getTaskPlanSub(final Page<TaskPlanSub> page,
			final Map<String, Object> paramsMap) throws Exception ;
}
