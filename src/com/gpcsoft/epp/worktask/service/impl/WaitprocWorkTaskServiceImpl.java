package com.gpcsoft.epp.worktask.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.worktask.dao.WaitprocWorkTaskDao;
import com.gpcsoft.epp.worktask.domain.WaitTaskComparator;
import com.gpcsoft.epp.worktask.domain.WaitprocWorkTask;
import com.gpcsoft.epp.worktask.domain.WaitprocWorkTaskModel;
import com.gpcsoft.epp.worktask.manager.WaitprocWorkTaskManager;
import com.gpcsoft.epp.worktask.service.WaitprocWorkTaskService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.plugin.wflow.domain.TaskModel;
import com.gpcsoft.plugin.wflow.domain.WorkFlowModel;
import com.gpcsoft.srplatform.auth.domain.User;

import edu.emory.mathcs.backport.java.util.Collections;

@Service 
public class WaitprocWorkTaskServiceImpl extends BaseGenericServiceImpl<WaitprocWorkTask> implements WaitprocWorkTaskService {

	@Autowired(required=true) @Qualifier("waitprocWorkTaskManagerImpl")
	private WaitprocWorkTaskManager waitprocWorkTaskManager;
	
	@Autowired(required=true) @Qualifier("waitprocWorkTaskDaoHibernate")
	private WaitprocWorkTaskDao waitprocWorkTaskDao;
	/**
	 * 新增待办任务(可催办)
	 * @param paramtypeCode  //任务代码，格式:****__***__****
	 * @param bizId  //业务ID
	 * @param bizId  //顶级业务Id，用于待办任务归类
	 * @param worktaskName  //任务名称
	 * @param receiverMap  //接受者列表，put(receiverId, receiverType)
	 * @param titleArray   //显示列内容，
	 * @return  新增的待办任务ID
	 * @throws EsException
	 */
	
	public String saveTaskByHand (String paramtypeCode, String bizId,String parentBizId,String worktaskName, Map receiverMap,String[] titleArray,String userId) throws Exception {
		return waitprocWorkTaskManager.insertTaskByHand(paramtypeCode, bizId, parentBizId, worktaskName, receiverMap, titleArray, userId);
	}
	
	/**
	 * @Description: 工作流新增待办任务
	 * @param paramtypeCode 任务代码，格式:****__***__****
	 * @param bizId 业务ID
	 * @param parentBizId 上级业务ID（项目ID）
	 * @param worktaskName 任务名称
	 * @param receiverMap 接受者列表，put(receiverId, receiverType)
	 * @param titleArray 显示内容(URL)
	 * @param userId 操作人ID
	 * @return 代办任务ID
	 * @throws Exception
	 * @Create Date 2010-8-25 下午07:30:12 By wanghz
	 */
	public String saveWorkFlowTaskByHand(String paramtypeCode, String bizId,String parentBizId,String worktaskName, Map receiverMap,String[] titleArray,String userId)throws EsException{
		return waitprocWorkTaskManager.saveWorkFlowTaskByHand(paramtypeCode, bizId, parentBizId, worktaskName, receiverMap, titleArray, userId);
	}
	
    /**
     * 人工完成待办任务
     * 参数
     */
    public void saveFinishWaitProcHand (String paramtypeCode,String paramKind,String bizId, String userId, String orgId, String viewProcResultURL) throws Exception {
		waitprocWorkTaskManager.finishWaitProcHand(paramtypeCode, paramKind, bizId, userId, orgId, viewProcResultURL);
    }
	
	/**
	 * 根据业务ID和用户ID，删除待办任务
	 * @param paramtypeCode
	 * @param bizId
	 * @param userId
	 * @throws Exception
	 */
	public void deleteTaskByBizIdAndUserId (String paramtypeCode,String bizId,String userId) throws Exception {
		waitprocWorkTaskManager.deleteWaitProcWorkTaskByBizIdAndUserId(paramtypeCode, bizId, userId);
	}
	
    /**
     * @Description: 扩展查询，根据query返回待办任务
     * @param queryObject
     * @return List<WaitprocWorkTask>
     * @throws Exception
     * @Create Date 2010-8-23 下午02:17:02 By wanghz
     */
    public List<WaitprocWorkTask> getAllByQueryObject(QueryObject<WaitprocWorkTask> queryObject)throws Exception{
    	return waitprocWorkTaskManager.getAllByQueryObject(queryObject);
    }

    /** 
	 * Description :  根据待办任务ID获得对应的待办任务显示MODEL
	 * Create Date: 2010-8-23下午03:54:49 by liuy  Modified Date: 2010-8-23下午03:54:49 by liuy
	 * @param workFlowTaskId	待办任务ID
	 * @return
	 * @throws Exception
	 */
	public TaskModel getTaskModelById(String workFlowTaskId) throws Exception {
		WaitprocWorkTask waitprocWorkTask = this.get(workFlowTaskId);
		TaskModel taskModel = new TaskModel();
		taskModel.setObjId(workFlowTaskId);
		taskModel.setBizId(waitprocWorkTask.getBizId());
		taskModel.setFormUrl(waitprocWorkTask.getProcViewURL());
		taskModel.setName(waitprocWorkTask.getWorktaskName());
		return taskModel;
	}

	/** 
	 * Description :  根据查询对象获得所有的待办任务显示MODEL
	 * Create Date: 2010-8-23下午03:54:49 by liuy  Modified Date: 2010-8-23下午03:54:49 by liuy
	 * @param workFlowTaskId	待办任务ID
	 * @return
	 * @throws Exception
	 */
	public List<TaskModel> getAllTaskModelByQueryObject(QueryObject queryObject) throws Exception {
		User user=AuthenticationHelper.getCurrentUser();
		OrgInfo orgInfo = (OrgInfo)user.getOrgInfo();
		List userTaskList = waitprocWorkTaskDao.getTaskModelByUser(queryObject, null, user);//拿到指定当前用户的待办任务
		List orgTaskList = waitprocWorkTaskDao.getTaskModelByOrgInfo(queryObject, null, orgInfo);//拿到指定给机构的待办任务
		List allTaskList = waitprocWorkTaskDao.getTaskModelByAll(queryObject, null);//拿到指定给所有用户的待办任务
		List<WaitprocWorkTask> newList = new ArrayList<WaitprocWorkTask>(1);//目前待办任务数据量不会很大，先把所有数据拿到后再做处理，后期优化这里
		if(orgTaskList !=null && !orgTaskList.isEmpty()) newList.addAll(orgTaskList);
		if(allTaskList !=null && !allTaskList.isEmpty()) newList.addAll(allTaskList);
		if(userTaskList !=null && !userTaskList.isEmpty()) newList.addAll(userTaskList);
		WaitTaskComparator waitTaskComparator = new WaitTaskComparator();
		Collections.sort(newList, waitTaskComparator);//序列化
		String[] userAuth = user.getAuth().split(",");//拿到所有的用户资源
		List newList_ = new ArrayList(1);
		for (WaitprocWorkTask waitprocWorkTask:newList) {
				TaskModel taskModel = new TaskModel();
				taskModel.setObjId(waitprocWorkTask.getObjId());
				taskModel.setBizId(waitprocWorkTask.getBizId());
				taskModel.setFormUrl(waitprocWorkTask.getProcViewURL());
				taskModel.setName(waitprocWorkTask.getWorktaskName());
				taskModel.setCreateTime(waitprocWorkTask.getCreateTime());
				newList_.add(taskModel);
		}
		return newList_;
	}

	 /** 
     * Description :  根据待办任务ID完成对应的待办任务
     * Create Date: 2010-8-24上午09:45:31 by liuy  Modified Date: 2010-8-24上午09:45:31 by liuy
     * @param workFlowModel	待办任务ID
	 * @param userId			操作人ID
	 * @param orgId				操作人机构ID
     * @throws Exception
     */
	public void saveFinishWaitProcById(WorkFlowModel workFlowModel, String userId,
			String orgId) throws Exception {
		waitprocWorkTaskManager.saveFinishWaitProcById(workFlowModel, userId, orgId);
	}

	
	/** 
	 * Description :  根据查询条件获得对应的所有待办任务
	 * Create Date: 2010-8-24下午04:58:59 by liuy  Modified Date: 2010-8-24下午04:58:59 by liuy
	 * @param queryObject
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public Page<TaskModel> getAllTaskModelByQueryObject(
			QueryObject queryObject, Page page) throws Exception {
		Page page_ = waitprocWorkTaskDao.findByTaskModelByQueryObject(queryObject, null);
		List list = new ArrayList();
		for (int i = 0; i < page_.getData().size(); i++) {//处理待办数据
			WaitprocWorkTask waitprocWorkTask = (WaitprocWorkTask)page_.getData().get(i);
			TaskModel taskModel = new TaskModel();
			taskModel.setObjId(waitprocWorkTask.getObjId());
			taskModel.setBizId(waitprocWorkTask.getBizId());
			taskModel.setFormUrl(waitprocWorkTask.getProcViewURL());
			taskModel.setName(waitprocWorkTask.getWorktaskName());
			list.add(taskModel);
		}
		page_.setData(list);
		return page;
	}
	
	/**
	 * @Description: 获取待办任务
	 * @param paramtypeCode 任务类别
	 * @param bizId 业务ID
	 * @param parentBizId 顶级业务ID
	 * @return WaitprocWorkTask
	 * @throws Exception
	 * @Create Date 2010-8-26 上午09:52:30 By wanghz
	 */
	public WaitprocWorkTask getWaitprocWorkTask(String paramtypeCode,String bizId,String parentBizId)throws EsException{
		return waitprocWorkTaskManager.getWaitprocWorkTask(paramtypeCode, bizId, parentBizId);
	}

	/**
	 * @Description: 根据任务类型CODE获取所有代办任务
	 * @param worktaskReType 任务接受者类型[接受者为所有、机构、组织、角色或用户]
	 * @param receiveId 接收人ID
	 * @return List<WaitprocWorkTask>
	 * @throws Exception
	 * @Create Date 2010-8-27 上午10:53:34 By wanghz
	 */
	public List<WaitprocWorkTask> getAllWaitprocWorkTask(String paramtypeCode,String worktaskReType,String receiveId)throws EsException{
		return waitprocWorkTaskManager.getAllWaitprocWorkTask(paramtypeCode, worktaskReType, receiveId);
	}
	
	/**
	 * @Description: 获取所有待办任务Model
	 * @param paramtypeCodes 任务类别CODE
	 * @return List<WaitprocWorkTaskModel>
	 * @throws Exception
	 * @Create Date 2010-8-27 上午10:17:41 By wanghz
	 */
	public List<WaitprocWorkTaskModel> getAllWaitprocWorkTaskModelByParamtypeCodes(String[] paramtypeCodes)throws EsException{
		return waitprocWorkTaskManager.getAllWaitprocWorkTaskModelByParamtypeCodes(paramtypeCodes);
	}
}
