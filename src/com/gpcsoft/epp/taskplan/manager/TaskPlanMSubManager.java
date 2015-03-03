package com.gpcsoft.epp.taskplan.manager;

import java.util.List;

import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.epp.taskplan.domain.TaskPlanMSub;
import com.gpcsoft.epp.taskplan.domain.TaskPlanSub;

public interface TaskPlanMSubManager extends BaseManager<TaskPlanMSub> {
	
	/** 
	 * FuncName:getSubNotSumSubsByOrg
	 * Description : 根据机构，获得其下级机构的未汇总的采购书条目
	 * @param objId:申报书主键,status:汇总状态,taskType:申报书类型
	 * @return List<TaskPlanMSub>
	 * @author liangxj
	 * @Create Date：2010-6-3 下午04:12:39 
	 * @Modifier  liangxj 
	 * @Modified Date:  2010-6-3 下午04:12:39  
	 */
	public List<TaskPlanMSub> getSubNotSumSubsByOrg(String objId,String status, String taskType,String ebuyMethod);

	/** 
	 * FuncName:saveMSubsByTaskPlan
	 * Description : 根据申报书的主键，将其下的条目和明细汇总
	 * @param taskPlanIds:申报书主键,sumTaskPlanId:汇总的申报书主键,status:汇总状态
	 * @return void
	 * @author liangxj
	 * @Create Date：2010-6-3 下午04:12:39 
	 * @Modifier   liangxj  
	 * @Modified Date:  2010-6-3 下午04:12:39  
	 */
	public void saveMSubsByTaskPlan(String taskPlanIds,String sumTaskPlanId,String status);
	
	/** 
	 * FuncName:removeSumMSubs
	 * Description : 根据申报书条目主键,删除所属的申报书所有汇总的条目,不级联删除申报书条目
	 * @param taskPlanSubIds:申报书条目主键,status:汇总状态
	 * @return void
	 * @author liangxj
	 * @Create Date：2010-6-3 下午04:12:39 
	 * @Modifier   liangxj   
	 * @Modified Date:  2010-6-3 下午04:12:39  
	 */
	public void removeSumMSubs(String taskPlanIds,String status);
	
	/** 
	 * FuncName:getSubByTaskPlan
	 * Description:根据申报书的主键,获得条目
	 * @param taskPlanIds:以逗号分割的申报书主键
	 * @return List<TaskPlanSub>
	 * @author liangxj
	 * @Create Date：2010-6-3 下午04:12:39 
	 * @Modifier    liangxj
	 * @Modified Date:  2010-6-3 下午04:12:39  
	 */
	public List<TaskPlanSub> getSubByTaskPlan(String taskPlanIds);

	/** 
	 * FuncName:removeByTaskPlanSubId
	 * Description:根据申报书条目ID删除对应的中间表数据
	 * @param taskPlanSubId:申报书条目主键
	 * @return void
	 * @author Administrator
	 * @Create Date: 2010-7-10下午04:55:41
	 * @Modifier :Administrator
	 * @Modified Date: 2010-7-10下午04:55:41 
	 */
	public void removeByTaskPlanSubId(String taskPlanSubId);
	
	/** 
	/** 
	 * FuncName:getTaskPlanMSubByProjectId
	 * Description :  根据项目主键获取申报书与申报书条目中间对象
	 * @param   projectId:项目主键
	 * @return  List
	 * @author yangx
	 * @Create Date: 2010-6-24下午01:10:46 
	 * @Modifier    yangx
	 * @Modified Date: 2010-6-24下午01:10:46  
	 */
	@SuppressWarnings("unchecked")
	public List getTaskPlanMSubByProjectId(String projectId);
	
	/** 
	 * FuncName:getProjectMPreqByTaskPlanMSubId
	 * Description:获取采购申报书明细与项目标段中间表
	 * @param   queryObject:组装的查询条件对象
	 * @return  List
	 * @author yangx
	 * @Create Date: 2010-6-30上午11:03:45 
	 * @Modifier   yangx
	 * @Modified Date: 2010-6-30上午11:03:45  
	 */
	@SuppressWarnings("unchecked")
	public List getProjectMPreqByTaskPlanMSubId(QueryObject queryObject);

	/**
	 * FuncName:getTaskPlanSubByTaskPlan
	 * Description : 根据申报书获得申报书明细列表
 	 * @param   taskPlanId:申报书主键
	 * @return   List<TaskPlanSub>
	 * @author liuke
	 * @Create Date: 2010-7-15上午09:34:57 
	 * @Modifier    liuke
	 * @Modified Date: 2010-7-15上午09:34:57  
	 */
	public List<TaskPlanSub> getTaskPlanSubByTaskPlan(String TaskPlanId);
	
	/** 
	 * FuncName:getTaskPlanSubByTaskPlanId
	 * Description :  根据申报书id获取采购申报书明细
	 * @param   projectId:项目主键
	 * @return  List<TaskPlanMSub>
	 * @author yangx
	 * @Create Date: 2010-6-30上午10:32:43 
	 * @Modifier yangx
	 * @Modified Date: 2010-6-30上午10:32:43  
	 */
	public List<TaskPlanMSub> getTaskPlanSubByTaskPlanId(String projectId);
	
	/**
	 * FuncName:getTaskPlanMSubsByPackId
	 * Description: 根据包件主键获取所有申报书明细中间表
	 * @param packId:包件主键
	 * @return List<TaskPlanSub>
	 * @author wanghz
	 * @Create Date 2010-10-8 上午10:51:10  
	 */
	public List<TaskPlanMSub> getTaskPlanMSubsByPackId(String packId);
	
	/** 
	 * FuncName:getTaskPlanMSubByTaskPlanIdAndStatus
	 * Description :  根据申报书和汇总状态查询申报书、条目中间表
	 * @param   taskPlanId:申报书主键,status:汇总状态
	 * @return  List<TaskPlanMSub>
	 * @author yangx
	 * @Create Date: 2010-11-18下午04:01:52 
	 * @Modifier    yangx
	 * @Modified Date: 2010-11-18下午04:01:52  
	 */
	public List<TaskPlanMSub>  getTaskPlanMSubByTaskPlanIdAndStatus(String taskPlanId,String status);
	
	/**
	 * FuncName:getTaskPlanMSubByStatus
	 * Description :  获取本级的申报书明细
	 * @param	taskPlanId:申报书主键
	 * @return  List<TaskPlanSub>
	 * @author shenjianzhuang
	 * @Create Date: 2010-9-16上午10:26:21 
	 * @Modifier   shenjianzhuang
	 * @Modified Date: 2010-9-16上午10:26:21 
	 */   
	public List<TaskPlanMSub> getTaskPlanMSubByStatus(String taskPlanId);
}
