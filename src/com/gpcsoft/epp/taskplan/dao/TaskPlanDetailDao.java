package com.gpcsoft.epp.taskplan.dao;

import java.util.List;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.taskplan.domain.TaskPlanDetail;
import com.gpcsoft.epp.taskplan.domain.TaskPlanMSub;

public interface TaskPlanDetailDao extends BaseGenericDao<TaskPlanDetail> {
	
	/**
	 * FuncName:removeTaskPlanDetail
	 * Description: 删除申报书采购资金,同时删除关联申报书中间表
	 * @param objId 采购资金明细ID
	 * @return void
	 * @author wanghz
	 * @Create Date 2010-7-26 下午06:12:23 
	 */
	public void removeTaskPlanDetail(String objId)throws Exception;
	
	
	
	
	/**
	 * FuncName: saveTaskPlanDetailBySQL
	 * Description :  保存申报书资金明细对象
	 * @param 
	 * @param taskPlanDetail
	 * @throws EsException void
	 * @author: liuke
	 * @Create Date:2011-4-12  下午09:55:50
	 * @Modifier: liuke
	 * @Modified Date:2011-4-12  下午09:55:50
	 */
	public void saveTaskPlanDetailBySQL(TaskPlanDetail taskPlanDetail)throws EsException;
	

	/**
	 * FuncName: getTaskPlanDetailList
	 * Description :  查询采购资金明细批准文号
	 * @param 
	 * @return List<TaskPlanDetail>
	 * @author: shenjz
	 * @Create Date:2011-10-8  下午04:47:49
	 * @Modifier: shenjz
	 * @Modified Date:2011-10-8  下午04:47:49
	 */
	public List getTaskPlanDetailList(String taskPlanId,String approvalNumber);
}
