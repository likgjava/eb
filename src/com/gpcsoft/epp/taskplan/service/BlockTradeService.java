package com.gpcsoft.epp.taskplan.service;

import java.util.List;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.taskplan.domain.BlockTrade;
import com.gpcsoft.epp.taskplan.domain.TaskPlan;

public interface BlockTradeService  extends BaseGenericService<BlockTrade> {
	
	/**
	 * FuncName:getAgentForRandom
	 * Description : 抽取代理机构
	 * @param
	 * @return OrgInfo
	 * @author yangx
	 * @Create Date:   2010-6-9  
	 */
	public OrgInfo getAgentForRandom();
	
	/** 
	 * FuncName:getWinAgent
	 * Description:判断获胜的代理机构
	 * @param org_id:机构ID
	 * @return OrgInfo
	 * @author yangx
	 * @Create Date:2010-6-9  
	 */
	public OrgInfo getWinAgent(String org_id);
	
	/** 
	 * FuncName:getTaskPlanList
	 * Description:获取代理机构
	 * @param taskPlan:申报书对象
	 * @return List<TaskPlan>
	 * @author Administrator
	 * @Create Date:2010-6-9  
	 */
	public List<TaskPlan> getTaskPlanList(TaskPlan taskPlan);
	
	/** 
	 * FuncName:getTaskPlanPage
	 * Description :  获取采购申报书
	 * @param taskCode:申报书编号,applyDate:申请日期,page:分页对象
	 * @return Page<TaskPlan>
	 * @author liuke
	 * @Create Date: 2010-7-5下午03:16:16 
	 * @Modifier:liuke
	 * @Modified Date: 2010-7-5下午03:16:16  
	 */
	@SuppressWarnings("unchecked")
	public Page<TaskPlan> getTaskPlanPage(String taskCode,String applyDate,Page page);
	
	/**
	 * FuncName:getTaskPlanListByObjId
	 * Description:根据主键得到项目申报书 
	 * @param objId:申报书主键
	 * @return  List<TaskPlan>
	 * @author liuke
	 * @Create Date: 2010-7-5下午03:16:16    
	 * @Modifier liuke
	 * @Modified Date: 2010-7-5下午03:16:16  
	 */
	public List<TaskPlan> getTaskPlanListByObjId(String objId);
}
