package com.gpcsoft.epp.worktask.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryObjectBase;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.epp.worktask.domain.CompletedWorkTask;
import com.gpcsoft.epp.worktask.manager.CompletedWorkTaskManager;
import com.gpcsoft.epp.worktask.service.CompletedWorkTaskService;
import com.gpcsoft.srplatform.auth.domain.User;

@Service 
public class CompletedWorkTaskServiceImpl extends BaseGenericServiceImpl<CompletedWorkTask> implements CompletedWorkTaskService {

	@Autowired(required=true) @Qualifier("completedWorkTaskManagerImpl")
	private CompletedWorkTaskManager completedWorkTaskManager;

	/** 
	 * Description :  根据父业务ID和任务ID查询对应的已操作过的记录
	 * Create Date: 2010-5-27下午04:15:14 by Administrator  Modified Date: 2010-5-27下午04:15:14 by Administrator
	 * @param taskId
	 * @param parentBizId
	 * @param user
	 * @return
	 */
	public List<CompletedWorkTask> getCompletedWorkTaskByParentBizId(
			String taskId, String parentBizId, User user) {
		QueryObject<CompletedWorkTask> queryObject = new QueryObjectBase<CompletedWorkTask>();
		queryObject.setEntityClass(CompletedWorkTask.class);
		queryObject.getQueryProjections().setOrderProperty("createTime");
		queryObject.getQueryProjections().setDescFlag(false);
		queryObject.getQueryParam().and(new QueryParam("parentBizId",QueryParam.OPERATOR_EQ,parentBizId));
		return completedWorkTaskManager.findByQuery(queryObject);
	}

	/** 
	 * Description :  根据业务ID和任务类型查询对应的已操作过的记录
	 * Create Date: 2010-7-22上午10:42:34 by yangx  Modified Date: 2010-7-22上午10:42:34 by yangx
	 * @param request
	 * @param bizId
	 * @param taskType 
	 * @return  
	 * @Exception   
	 */
	public List<CompletedWorkTask> getCompletedWorkTaskByBizId(String taskType,
			String bizId, User user) {
		QueryObject<CompletedWorkTask> queryObject = new QueryObjectBase<CompletedWorkTask>();
		queryObject.setEntityClass(CompletedWorkTask.class);
		queryObject.getQueryProjections().setOrderProperty("createTime");
		queryObject.getQueryProjections().setDescFlag(true);
		queryObject.getQueryParam().and(new QueryParam("bizId",QueryParam.OPERATOR_EQ,bizId));
		queryObject.getQueryParam().and(new QueryParam("worktaskType",QueryParam.OPERATOR_EQ,taskType));
//		queryObject.getQueryParam().and(new QueryParam("viewResultURL",QueryParam.OPERATOR_NE,null));
		return completedWorkTaskManager.findByQuery(queryObject);
	}

	
	/** 
	 * Description :  根据父业务ID和任务类型查询对应的已操作过的记录
	 * Create Date: 2010-7-22上午10:42:34 by liuke  Modified Date: 2010-7-22上午10:42:34 by liuke
	 * @param request
	 * @param bizId
	 * @param taskType 
	 * @return  
	 * @Exception   
	 */
	public List<CompletedWorkTask> getCompletedWorkTaskByParentBizIdAndType(
			String taskType, String parentBizId, User user) {
		QueryObject<CompletedWorkTask> queryObject = new QueryObjectBase<CompletedWorkTask>();
		queryObject.setEntityClass(CompletedWorkTask.class);
		queryObject.getQueryProjections().setOrderProperty("createTime");
		queryObject.getQueryProjections().setDescFlag(true);
		queryObject.getQueryParam().and(new QueryParam("parentBizId",QueryParam.OPERATOR_EQ,parentBizId));
		queryObject.getQueryParam().and(new QueryParam("taskType",QueryParam.OPERATOR_EQ,taskType));
		return completedWorkTaskManager.findByQuery(queryObject);
	}

}
