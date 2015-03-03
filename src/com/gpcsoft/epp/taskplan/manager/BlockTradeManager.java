package com.gpcsoft.epp.taskplan.manager;

import java.util.List;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.epp.taskplan.domain.BlockTrade;
import com.gpcsoft.epp.taskplan.domain.TaskPlan;

public interface BlockTradeManager extends BaseManager<BlockTrade>{
	
	/**
	 * FuncName: getAgentForRandom
	 * Description : 抽取代理机构
	 * @param List<OrgInfo> list:机构集合,num:抽取的个数
	 * @return List<OrgInfo>
	 * @author yangx
	 * @Create Date:   2010-6-9 by yangx
	 */
	public List<OrgInfo> getAgentForRandom(List<OrgInfo> list,int num);
	
	/** 
	 * FuncName:getWinAgent
	 * Description:判断获胜的代理机构org_id
	 * @param org_ids:机构主键数组
	 * @return String
	 * @author yangx
	 * @Create Date:   2010-6-9  
	 */
	public String getWinAgent(String[] org_id);

	/** 
	 * FuncName:getTaskPlanList
	 * Description:判断获胜的代理机构
	 * @param taskPlan:申报书对象
	 * @return List<TaskPlan>
	 * @author yangx
	 * @Create Date:   2010-6-9  
	 */
	public List<TaskPlan> getTaskPlanList(TaskPlan taskPlan);
}

