package com.gpcsoft.epp.taskplan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryObjectBase;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.common.domain.EsExceptionEnum;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.common.manager.UserApiManager;
import com.gpcsoft.epp.taskplan.domain.BlockTrade;
import com.gpcsoft.epp.taskplan.domain.TaskPlan;
import com.gpcsoft.epp.taskplan.domain.TaskPlanConfirmEnum;
import com.gpcsoft.epp.taskplan.domain.TaskPlanTypeEnum;
import com.gpcsoft.epp.taskplan.manager.BlockTradeManager;
import com.gpcsoft.epp.taskplan.manager.TaskPlanManager;
import com.gpcsoft.epp.taskplan.service.BlockTradeService;
@Service 
public class BlockTradeServiceImpl extends BaseGenericServiceImpl<BlockTrade> implements BlockTradeService{
	
	@Autowired(required=true) @Qualifier("blockTradeManagerImpl")
	private BlockTradeManager blockTradeManager;

	@Autowired(required=true) @Qualifier("userApiManagerImpl")
	private UserApiManager userApiManager;
	
	@Autowired(required=true) @Qualifier("taskPlanManagerImpl")
	private TaskPlanManager taskPlanManager;
	
	/**
	 * FuncName:getAgentForRandom
	 * Description : 抽取代理机构
	 * @param
	 * @return OrgInfo
	 * @author yangx
	 * @Create Date:   2010-6-9  
	 */
	public OrgInfo getAgentForRandom(){
		logger.debug("\nBlockTradeServiceImpl||getAgentForRandom\n");
		List<OrgInfo> lists = userApiManager.getAgentForFile();
		/** 随机抽取一个代理机构 */
		List<OrgInfo> list = blockTradeManager.getAgentForRandom(lists,1);
		OrgInfo orgInfo = null;
		if (list!=null&&list.size()>0) {
			orgInfo = list.get(0);
		} else {
			throw new EsException(messageSource.getMessage(EsExceptionEnum.IS_NOT_AGENT));
		}
		return orgInfo;
	}
	
	/** 
	 * FuncName:getWinAgent
	 * Description:判断获胜的代理机构
	 * @param org_id:机构ID
	 * @return OrgInfo
	 * @author yangx
	 * @Create Date:2010-6-9  
	 */
	public OrgInfo getWinAgent(String org_id){
		logger.debug("\nes=BlockTradeServiceImpl||getWinAgent\n");
		if(org_id==null||"".equals(org_id))return null;
		String[] org_ids = org_id.split(",");
		/** 获取获胜的代理机构id */
		String orgId = blockTradeManager.getWinAgent(org_ids);
		if(orgId==null||"false".equals(orgId)||"".equals(orgId)) return null;
		OrgInfo orginfo = (OrgInfo) blockTradeManager.get(orgId, OrgInfo.class);
		/** 获取获胜的代理机构 */
		return orginfo;
	}
	
	/** 
	 * FuncName:getTaskPlanList
	 * Description:获取代理机构
	 * @param taskPlan:申报书对象
	 * @return List<TaskPlan>
	 * @author Administrator
	 * @Create Date:2010-6-9  
	 */
	public List<TaskPlan> getTaskPlanList(TaskPlan taskPlan){
		logger.debug("\nes=BlockTradeServiceImpl||getTaskPlanList\n");
		return blockTradeManager.getTaskPlanList(taskPlan);
	}

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
	public List<TaskPlan> getTaskPlanListByObjId(String objId) {
		logger.debug("\nes=BlockTradeServiceImpl||getTaskPlanListByObjId\n");
		return taskPlanManager.getTaskPlanListByObjId(objId);
	}

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
	public Page<TaskPlan> getTaskPlanPage(String taskCode,String applyDate, Page page) {
		logger.debug("\n=BlockTradeServiceImpl||getTaskPlanPage\n");
		QueryObject<TaskPlan> queryObject = new QueryObjectBase<TaskPlan>();
		queryObject.getQueryParam().and(new QueryParam("taskType",QueryParam.OPERATOR_EQ,TaskPlanTypeEnum.BLOCK_TRADE));
		queryObject.getQueryParam().and(new QueryParam("useStatus",QueryParam.OPERATOR_EQ,CommonEnum.USER_STATUS_FORMAL));
		queryObject.getQueryParam().and(new QueryParam("confirmStatus",QueryParam.OPERATOR_EQ,TaskPlanConfirmEnum.SELECT_AGENT_PASS));
		queryObject.getQueryParam().and(new QueryParam("block_check_status",QueryParam.OPERATOR_EQ,null));
		queryObject.getQueryParam().and(new QueryParam("taskCode",QueryParam.OPERATOR_EQ,taskCode));
		queryObject.getQueryParam().and(new QueryParam("applyDate",QueryParam.OPERATOR_EQ,applyDate));
		return taskPlanManager.getTaskPlanPageByQO(queryObject,page);
	}
}
