package com.gpcsoft.epp.taskplan.dao;

import java.util.List;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.taskplan.domain.TaskPlanDetail;
import com.gpcsoft.epp.taskplan.domain.TaskPlanMDetail;

public interface TaskPlanMDetailDao extends BaseGenericDao<TaskPlanMDetail> {
	
	/** 
	 * FuncName:getSubNotSumDetailsByOrg
	 * Description : 根据机构，获得其下级机构的未汇总的采购书条目
	 * @param objId:部门ID,status:为汇总状态,taskType:申报书类型
	 * @return List<TaskPlanMDetail>
	 * @author liangxj
	 * @Create Date：2010-6-3 下午04:12:39 
	 * @Modifier  liangxj  
	 * @Modified Date:  2010-6-3 下午04:12:39  
	 */
	public List<TaskPlanMDetail> getSubNotSumDetailsByOrg(String objId,String status, String taskType,String ebuyMethod);
	
	/** 
	 * FuncName:removeSumMDetails
	 * Description : 根据taskPlan id ，删除所属的申报书所有汇总的资金明细，不级联删除taskPlanDetail
	 * @param taskPlanIds:申报书主键,status为汇总状态  
	 * @return void
	 * @author liangxj
	 * @Create Date：2010-6-3 下午04:12:39 
	 * @Modifier  liangxj    
	 * @Modified Date:  2010-6-3 下午04:12:39 
	 */
	public void removeSumMDetails(final String taskPlanIds, final String status);
	
	/**
	 * FucnName:getTaskPlanMDetailByStatus
	 * Description : 获取本级的资金明细
	 * @param taskPlanId:申报书ID
	 * @return  List<TaskPlanDetail>
	 * @author shenjianzhuang
	 * @Create Date: 2010-9-16上午10:25:15 
	 * @Modifier  shenjianzhuang
	 * @Modified Date: 2010-9-16上午10:25:15 
	 */	
	public List<TaskPlanMDetail> getTaskPlanMDetailByStatus(String taskPlanId);
	
	
	
	
	/**
	 * FuncName: saveTaskPlanDetailBySQL
	 * Description :  保存申报书资金明细中间表对象
	 * @param 
	 * @param taskPlanDetail
	 * @throws EsException void
	 * @author: liuke
	 * @Create Date:2011-4-12  下午09:55:50
	 * @Modifier: liuke
	 * @Modified Date:2011-4-12  下午09:55:50
	 */
	public void saveTaskPlanMDetailBySQL(TaskPlanMDetail taskPlanMDetail)throws EsException;
	
}
