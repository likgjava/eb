package com.gpcsoft.epp.worktask.manager.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.project.manager.ProjectPlanManager;
import com.gpcsoft.epp.worktask.dao.WaitprocWorkTaskDao;
import com.gpcsoft.epp.worktask.domain.WaitTaskComparator;
import com.gpcsoft.epp.worktask.domain.WaitprocWorkTask;
import com.gpcsoft.epp.worktask.manager.WaitprocWorkTaskManager;
import com.gpcsoft.plugin.wflow.domain.NodeModel;
import com.gpcsoft.plugin.wflow.domain.ProceDefModel;
import com.gpcsoft.plugin.wflow.domain.TaskModel;
import com.gpcsoft.plugin.wflow.domain.WorkFlowModel;
import com.gpcsoft.plugin.wflow.manager.WorkFlowManager;
import com.gpcsoft.srplatform.auth.domain.User;

import edu.emory.mathcs.backport.java.util.Collections;

@Repository
@SuppressWarnings("unchecked")
public class WaitProcWorkTaskWorkFlowImpl implements WorkFlowManager {

	@Autowired(required=true) @Qualifier("waitprocWorkTaskManagerImpl")
	private WaitprocWorkTaskManager waitprocWorkTaskManager;
	
	@Autowired(required=true)  @Qualifier("waitprocWorkTaskDaoHibernate")
	private WaitprocWorkTaskDao waitprocWorkTaskDaoHibernate;
	
	@Autowired(required=true) @Qualifier("projectPlanManagerImpl")
	private ProjectPlanManager projectPlanManager;
	
	public String deployProcDefByXMLFile(String fileName,
			InputStream inputStream) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ProceDefModel> getLatestProcDef(QueryObject queryObject)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List<NodeModel> getNodeModelsByProcDefKeyAndProcInId(
			String procDefKey, String procInId, User user) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public TaskModel getTaskModelById(String taskId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public Page<TaskModel> getTasksByCurUser(User user, Page<TaskModel> page,QueryObject queryObject) throws Exception {
		OrgInfo orgInfo = (OrgInfo)user.getOrgInfo();
		List<QueryParam> queryList = queryObject.getQueryParam().getAndParams();
		Map<String, String> params = new HashMap<String, String>(1);
		if(queryList != null && !queryList.isEmpty()){
			for (int i = 0; i < queryList.size(); i++) {
				QueryParam queryParam = (QueryParam)queryList.get(i);
				if("name".equals(queryParam.getName())){
					params.put("name", (String)queryParam.getValue());
				}
				if("worktaskType".equals(queryParam.getName())){
					params.put("worktaskType", (String)queryParam.getValue());
				}
			}
		}
		List<WaitprocWorkTask> list = (List)waitprocWorkTaskDaoHibernate.getAllWaitprocWorkTask(params.get("worktaskType"), params.get("name"), user, orgInfo, page.getStart(), page.getPageSize(),"data");
		Long num = (Long)waitprocWorkTaskDaoHibernate.getAllWaitprocWorkTask(params.get("worktaskType"), params.get("name"), user, orgInfo, page.getStart(), page.getPageSize(), "count");
		List<TaskModel> TaskModelList = new ArrayList<TaskModel>(0);
		for (WaitprocWorkTask waitprocWorkTask:list) {
			TaskModel taskModel = new TaskModel();
			taskModel.setObjId(waitprocWorkTask.getObjId());
			taskModel.setBizId(waitprocWorkTask.getBizId());
			taskModel.setFormUrl(waitprocWorkTask.getProcViewURL());
			taskModel.setName(waitprocWorkTask.getWorktaskName());
			taskModel.setCreateTime(waitprocWorkTask.getCreateTime());
			TaskModelList.add(taskModel);
		}
		Page dataList = new Page<TaskModel>(page.getStart(), num, page.getPageSize(), TaskModelList);
		return dataList;
	}

	public void removeProcDefById(String id) throws Exception {
		
	}

	public void saveForCompleteTask(WorkFlowModel workFlowModel, User user)throws Exception {
		
		// 1.1完成项目计划
		projectPlanManager.saveFinishPlan(workFlowModel.getBizDoType(), workFlowModel.getBizId(), workFlowModel.getParentBizId(), user.getObjId());
		// 1.2完成待办任务
		OrgInfo orgInfo = (OrgInfo)user.getOrgInfo();
		waitprocWorkTaskManager.saveFinishWaitProcById(workFlowModel, user.getObjId(), orgInfo.getObjId());
	}

	public String startProcInByKey(String procInKey, String bizId, User user)
			throws Exception {
		return null;
	}

	public static void main(String[] args) {
		String test1 = "abc";
		String test2 = "ab";
		System.out.println(test1.compareTo(test2));
	}
}
