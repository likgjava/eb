package com.gpcsoft.epp.worktask.manager.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryObjectBase;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.epp.common.dao.UserApiDao;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.common.domain.EsExceptionEnum;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.worktask.dao.WaitprocWorkTaskDao;
import com.gpcsoft.epp.worktask.domain.CompletedWorkTask;
import com.gpcsoft.epp.worktask.domain.CompletedWorktaskEnum;
import com.gpcsoft.epp.worktask.domain.WaitprocWorkTask;
import com.gpcsoft.epp.worktask.domain.WaitprocWorkTaskModel;
import com.gpcsoft.epp.worktask.domain.WorkTaskPriorityTypeEnum;
import com.gpcsoft.epp.worktask.domain.WorkTaskReceiverTypeEnum;
import com.gpcsoft.epp.worktask.domain.WorktaskReceive;
import com.gpcsoft.epp.worktask.domain.WorktaskStatusEnum;
import com.gpcsoft.epp.worktask.manager.WaitprocWorkTaskManager;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.plugin.wflow.domain.WorkFlowModel;
import com.gpcsoft.srplatform.auth.dao.RoleDao;
import com.gpcsoft.srplatform.auth.domain.Employee;
import com.gpcsoft.srplatform.auth.domain.Role;
import com.gpcsoft.srplatform.auth.domain.User;

@Repository
public class WaitprocWorkTaskManagerImpl extends BaseManagerImpl<WaitprocWorkTask> implements WaitprocWorkTaskManager {

	@Autowired(required=true)  @Qualifier("waitprocWorkTaskDaoHibernate")
	private WaitprocWorkTaskDao waitprocWorkTaskDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("userApiDaoHibernate")
	private UserApiDao userApiDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("roleDaoHibernate")
	private RoleDao roleDao;
	
	
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
	public String insertTaskByHand(String paramtypeCode, String bizId,String parentBizId,String worktaskName, Map receiverMap,String[] titleArray,String userId) throws EsException {
		return this.insertTaskByHand(paramtypeCode, bizId, parentBizId, worktaskName, receiverMap, titleArray, userId, true,WorkTaskPriorityTypeEnum.LEVEL3,WorktaskStatusEnum.CODING_COMMISSION_TASK);
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
	 * @throws EsException
	 * @Create Date 2010-8-25 下午07:30:12 By wanghz
	 */
	public String saveWorkFlowTaskByHand(String paramtypeCode, String bizId,String parentBizId,String worktaskName, Map receiverMap,String[] titleArray,String userId)throws EsException{
		return this.insertTaskByHand(paramtypeCode, bizId, parentBizId, worktaskName, receiverMap, titleArray, userId, true,WorkTaskPriorityTypeEnum.LEVEL3,WorktaskStatusEnum.FLOW_COMMISSION_TASK);
	}
	
	/**
	 * 新增待办任务
	 * @param paramtypeCode  //任务代码，格式:****__***__****
	 * @param bizId  		//业务ID
	 * @param parentBizId  	//顶级业务Id，用于待办任务归类
	 * @param worktaskName  //任务名称
	 * @param receiverMap  //接受者列表，put(receiverId, receiverType)
	 * @param titleArray   //显示列内容(URL)
	 * @return  新增的待办任务ID
	 * @throws Exception
	 */
	private String insertTaskByHand(String paramtypeCode, String bizId,String parentBizId,String worktaskName, Map receiverMap,String[] titleArray,String userId,boolean isCanHasten,String workTaskPriorityType,String procKind) throws EsException {
		// 参数有效性验证
		if (null == paramtypeCode || "".equals(paramtypeCode)) {
			throw new EsException(messageSource.getMessage(EsExceptionEnum.INSERT_WORK_TASK_ERROR)+"paramtypeCode["+paramtypeCode+"]"+messageSource.getMessage(EsExceptionEnum.INSERT_WORK_TASK_PARAMS_ERROR));
		}
		if (null == bizId || "".equals(bizId)) {
			throw new EsException(messageSource.getMessage(EsExceptionEnum.INSERT_WORK_TASK_ERROR)+"bizId["+bizId+"]"+messageSource.getMessage(EsExceptionEnum.INSERT_WORK_TASK_PARAMS_ERROR));
		}
		if (null == worktaskName || "".equals(worktaskName)) {
			throw new EsException(messageSource.getMessage(EsExceptionEnum.INSERT_WORK_TASK_ERROR)+"worktaskName["+worktaskName+"]"+messageSource.getMessage(EsExceptionEnum.INSERT_WORK_TASK_PARAMS_ERROR));
		}
		if (null == receiverMap || receiverMap.isEmpty()) {
			throw new EsException(messageSource.getMessage(EsExceptionEnum.INSERT_WORK_TASK_ERROR)+"receiverMap["+receiverMap+"]"+messageSource.getMessage(EsExceptionEnum.INSERT_WORK_TASK_PARAMS_ERROR));
		}
		if (null == titleArray || titleArray.length<1) {
			throw new EsException(messageSource.getMessage(EsExceptionEnum.INSERT_WORK_TASK_ERROR)+"titleArray["+titleArray+"]"+messageSource.getMessage(EsExceptionEnum.INSERT_WORK_TASK_PARAMS_ERROR));
		}
		if (null == workTaskPriorityType || "".equals(workTaskPriorityType)) {
			throw new EsException(messageSource.getMessage(EsExceptionEnum.INSERT_WORK_TASK_ERROR)+"workTaskPriorityType["+workTaskPriorityType+"]"+messageSource.getMessage(EsExceptionEnum.INSERT_WORK_TASK_PARAMS_ERROR));
		}
		if (null == procKind || "".equals(procKind)) {
			throw new EsException(messageSource.getMessage(EsExceptionEnum.INSERT_WORK_TASK_ERROR)+"procKind["+procKind+"]"+messageSource.getMessage(EsExceptionEnum.INSERT_WORK_TASK_PARAMS_ERROR));
		}
		
		try {
			WaitprocWorkTask waitprocWorkTask = new WaitprocWorkTask();
			waitprocWorkTask.setBizId(bizId);
			waitprocWorkTask.setCreateTime(new java.util.Date());
			waitprocWorkTask.setCreateUser(AuthenticationHelper.getCurrentUser());
			waitprocWorkTask.setParentBizId(parentBizId);
			waitprocWorkTask.setPriorityType(workTaskPriorityType);
			waitprocWorkTask.setUseStatus(CommonEnum.USER_STATUS_FORMAL);
			waitprocWorkTask.setWorktaskName(worktaskName);
			waitprocWorkTask.setWorktaskStatus(WorktaskStatusEnum.IN_GEAR);
			waitprocWorkTask.setWorktaskType(paramtypeCode);
			waitprocWorkTask.setProcViewURL(titleArray[0]);
			waitprocWorkTask.setProcKind(procKind);
			this.save(waitprocWorkTask);
			
			//获得任务接收用户ID列表
        	if (receiverMap!=null && receiverMap.size()>0) {
        		Iterator iterator =  receiverMap.keySet().iterator();
        		while (iterator.hasNext()) {
        			Object key = iterator.next();
        			String receiverId = (String)key;
        			String receiverType = (String)receiverMap.get(key);
        			WorktaskReceive worktaskReceive = new WorktaskReceive();
        			worktaskReceive.setWorktaskReType(receiverType);
        			worktaskReceive.setWorkTask(waitprocWorkTask);
        			if (WorkTaskReceiverTypeEnum.ALL.equals(receiverType)) {
        				worktaskReceive.setReceiveId("-1");
        				worktaskReceive.setReceiveNam(receiverType);
        			} else if (WorkTaskReceiverTypeEnum.ORG.equals(receiverType)) {
        				worktaskReceive.setReceiveId(receiverId);
        				OrgInfo orgInfo = userApiDaoHibernate.getOrgInfoById(receiverId);
        				worktaskReceive.setReceiveNam(orgInfo.getOrgName());
        			} else if (WorkTaskReceiverTypeEnum.ROLE.equals(receiverType)) {
        				worktaskReceive.setReceiveId(receiverId);
        				Role role = roleDao.get(receiverId);
        				worktaskReceive.setReceiveNam(role.getEnName());
        			} else if (WorkTaskReceiverTypeEnum.UNIT.equals(receiverType)){
        				worktaskReceive.setReceiveId(receiverId);
        				OrgInfo orgInfo = userApiDaoHibernate.getOrgInfoById(receiverId);
        				worktaskReceive.setReceiveNam(orgInfo.getOrgName());
        			} else if (WorkTaskReceiverTypeEnum.USER.equals(receiverType)){
        				worktaskReceive.setReceiveId(receiverId);
        				//由于权限平台user employee的问题，这里根据user的主键和employee的主健去拿数据
        				User thisUser = userApiDaoHibernate.getUserById(receiverId);
        				if(thisUser == null){
        					Employee employee = userApiDaoHibernate.getEmployeeById(receiverId);
        					worktaskReceive.setReceiveNam(employee.getName());
        				}else{
        					worktaskReceive.setReceiveNam(thisUser.getEmp().getName());
        				}
        			}
        			this.save(worktaskReceive, WorktaskReceive.class);
        		}	        		
        	}
        	
			//判断此分类是否有效

        	//默认为指定角色

        	
        	//获得当前采购区域

        	
        	//增加待办任务
        	
        	//增加接收者
        	
			
        	//新增待办任务 数据交换开始
			
        	//新增待办任务 数据交换结束
    		
        	//获得任务接收用户ID列表
			
        	//同步代办任务
        	return waitprocWorkTask.getObjId();
		} catch (Exception e) {
			e.printStackTrace();
			throw new EsException("");
		}
	}	
	    
    /**
     * 根据业务ID和用户ID，删除待办任务
     * @param paramtypeCode
     * @param paramKind
     * @param bizId
     * @param userId
     * @throws Exception
     */
    public void deleteWaitProcWorkTaskByBizIdAndUserId (String paramtypeCode, String bizId,String userId) throws Exception {
    	waitprocWorkTaskDaoHibernate.deleteWaitProcWorkTaskByBizIdAndUserId(bizId,userId,paramtypeCode);
    }
    
    /**
     * 人工完成待办任务
     * 参数
     */
    public void finishWaitProcHand(String paramtypeCode,String paramKind,String bizId, String userId, String orgId, String viewProcResultURL) throws Exception {
    	// 1.0获取代办任务
    	QueryObject<WaitprocWorkTask> waitprocWorkTaskQuery = new QueryObjectBase<WaitprocWorkTask>();
    	waitprocWorkTaskQuery.setEntityClass(WaitprocWorkTask.class);
    	waitprocWorkTaskQuery.getQueryParam().and(new QueryParam("bizId",QueryParam.OPERATOR_EQ,bizId));
    	waitprocWorkTaskQuery.getQueryParam().and(new QueryParam("worktaskType",QueryParam.OPERATOR_EQ,paramtypeCode));
    	List<WaitprocWorkTask> waitprocWorkTaskList = waitprocWorkTaskDaoHibernate.findListByQueryObject(waitprocWorkTaskQuery);
    	// 2.0完成代办任务
    	if(null != waitprocWorkTaskList && waitprocWorkTaskList.size()>0){
    		WaitprocWorkTask WaitprocWorkTask = waitprocWorkTaskList.get(0);
    		waitprocWorkTaskDaoHibernate.deleteWorktaskReceiveByWorkTaskIdAndReceiveId(WaitprocWorkTask.getObjId(),new String[]{userId,orgId});
    		if (waitprocWorkTaskDaoHibernate.getWorktaskReceiveCountByWorkTaskIdAndReceiveId(WaitprocWorkTask.getObjId(),new String[]{userId,orgId}) == 0 ) {
    			CompletedWorkTask completedWorktask = new CompletedWorkTask();
        		completedWorktask.setViewProcResultUrl(viewProcResultURL);
        		completedWorktask.setWorktaskName(WaitprocWorkTask.getWorktaskName());
        		completedWorktask.setWorktaskType(WaitprocWorkTask.getWorktaskType());
        		completedWorktask.setBizId(WaitprocWorkTask.getBizId());
        		completedWorktask.setProcType(CompletedWorktaskEnum.MANUAL_COMPLETE);
        		completedWorktask.setPrcessResult(CompletedWorktaskEnum.PROCESS_RESULT_C);
        		completedWorktask.setCreateTime(new java.util.Date());
        		completedWorktask.setCreateUser(AuthenticationHelper.getCurrentUser());
        		this.save(completedWorktask, CompletedWorkTask.class);
        		waitprocWorkTaskDaoHibernate.deleteWaitProcWorkTaskByBizIdAndUserId(bizId,userId,paramtypeCode);
    		}
    	}
    }
    
    /**
     * @Description: 扩展查询，根据query返回待办任务
     * @param queryObject
     * @return List<WaitprocWorkTask>
     * @throws Exception
     * @Create Date 2010-8-23 下午02:17:02 By wanghz
     */
    public List<WaitprocWorkTask> getAllByQueryObject(QueryObject<WaitprocWorkTask> queryObject)throws Exception{
    	return waitprocWorkTaskDaoHibernate.getAllByQueryObject(queryObject);
    }

    /** 
     * Description :  根据待办任务ID完成对应的待办任务
     * Create Date: 2010-8-24上午09:45:31 by liuy  Modified Date: 2010-8-24上午09:45:31 by liuy
     * @param workFlowModel	待办任务ID
     * @param userId			操作人ID
     * @param orgId				操作人机构ID
     * @throws Exception
     */
	public void saveFinishWaitProcById(WorkFlowModel workFlowModel, String userId,String orgId) throws Exception {
		WaitprocWorkTask waitprocWorkTask = this.get(workFlowModel.getWorkFlowTaskId());
		waitprocWorkTaskDaoHibernate.deleteWorktaskReceiveByWorkTaskIdAndReceiveId(waitprocWorkTask.getObjId(),new String[]{userId,orgId});
		if (waitprocWorkTaskDaoHibernate.getWorktaskReceiveCountByWorkTaskIdAndReceiveId(waitprocWorkTask.getObjId(),new String[]{userId,orgId}) == 0 ) {
			CompletedWorkTask completedWorktask = new CompletedWorkTask();
			completedWorktask.setWorkTask(waitprocWorkTask);
			completedWorktask.setViewProcResultUrl(waitprocWorkTask.getProcViewURL());
			completedWorktask.setWorktaskName(waitprocWorkTask.getWorktaskName());
			completedWorktask.setWorktaskType(waitprocWorkTask.getWorktaskType());
			completedWorktask.setBizId(workFlowModel.getBizId());
			completedWorktask.setProcType(CompletedWorktaskEnum.MANUAL_COMPLETE);
			completedWorktask.setPrcessResult(CompletedWorktaskEnum.PROCESS_RESULT_C);
			completedWorktask.setCreateTime(new java.util.Date());
			completedWorktask.setCreateUser(AuthenticationHelper.getCurrentUser());
			completedWorktask.setOpinion(workFlowModel.getOpinion());
			this.save(completedWorktask, CompletedWorkTask.class);
			waitprocWorkTaskDaoHibernate.removeById(workFlowModel.getWorkFlowTaskId());
		}
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
		if (null == paramtypeCode || "".equals(paramtypeCode)) {
			throw new EsException("paramtypeCode["+paramtypeCode+"]"+messageSource.getMessage(EsExceptionEnum.INSERT_WORK_TASK_PARAMS_ERROR));
		}
		return waitprocWorkTaskDaoHibernate.getWaitprocWorkTask(paramtypeCode, bizId, parentBizId);
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
		if (null == paramtypeCode || "".equals(paramtypeCode)) {
			throw new EsException(messageSource.getMessage(EsExceptionEnum.GET_WAITPROC_WORK_TASK_ERROR)+"paramtypeCode["+paramtypeCode+"]"+messageSource.getMessage(EsExceptionEnum.INSERT_WORK_TASK_PARAMS_ERROR));
		}
		if (null == worktaskReType || "".equals(worktaskReType)) {
			throw new EsException(messageSource.getMessage(EsExceptionEnum.GET_WAITPROC_WORK_TASK_ERROR)+"worktaskReType["+worktaskReType+"]"+messageSource.getMessage(EsExceptionEnum.INSERT_WORK_TASK_PARAMS_ERROR));
		}
		if (null == receiveId || "".equals(receiveId)) {
			throw new EsException(messageSource.getMessage(EsExceptionEnum.GET_WAITPROC_WORK_TASK_ERROR)+"receiveId["+receiveId+"]"+messageSource.getMessage(EsExceptionEnum.INSERT_WORK_TASK_PARAMS_ERROR));
		}
		return waitprocWorkTaskDaoHibernate.getAllWaitprocWorkTask(paramtypeCode, worktaskReType, receiveId);
	}
	
	/**
	 * @Description: 获取所有待办任务Model
	 * @param paramtypeCodes 任务类别CODE
	 * @return List<WaitprocWorkTaskModel>
	 * @throws Exception
	 * @Create Date 2010-8-27 上午10:17:41 By wanghz
	 */
	public List<WaitprocWorkTaskModel> getAllWaitprocWorkTaskModelByParamtypeCodes(String[] paramtypeCodes)throws EsException{
		List<Object[]> objects = waitprocWorkTaskDaoHibernate.getObjectsByParamtypeCodes(paramtypeCodes);
		List<WaitprocWorkTaskModel> waitprocWorkTaskModelList = new ArrayList<WaitprocWorkTaskModel>(0);
		if (!objects.isEmpty() && objects.size()>0) {
			for (Object[] object:objects) {
				if (Long.parseLong(object[2]+"") == 0) {
					continue;
				}
				WaitprocWorkTaskModel waitprocWorkTaskModel = new WaitprocWorkTaskModel();
				waitprocWorkTaskModel.setWorktaskName(object[0]+"");
				waitprocWorkTaskModel.setWorktaskType(object[1]+"");
				waitprocWorkTaskModel.setCount(Long.parseLong(object[2]+""));
				waitprocWorkTaskModelList.add(waitprocWorkTaskModel);
			}
		}
		return waitprocWorkTaskModelList;
	}
}
