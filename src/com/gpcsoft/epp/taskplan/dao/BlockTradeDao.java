package com.gpcsoft.epp.taskplan.dao;

import java.util.List;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.epp.taskplan.domain.BlockTrade;
import com.gpcsoft.epp.taskplan.domain.TaskPlan;

public interface BlockTradeDao extends BaseGenericDao<BlockTrade>{
	
	
	/** 
	 * FuncName:getTaskPlanList
	 * Description:判断获胜的代理机构
	 * @param taskPlan:申报书对象
	 * @return List<TaskPlan>
	 * @author yangx
	 * @create Date:2010-6-9 
	 */
	public List<TaskPlan> getTaskPlanList(TaskPlan taskPlan);
	
}
