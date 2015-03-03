package com.gpcsoft.epp.worktask.service;
import java.util.List;
import java.util.Map;

import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.worktask.domain.WaitprocWorkTask;
import com.gpcsoft.epp.worktask.domain.WaitprocWorkTaskModel;
import com.gpcsoft.plugin.wflow.domain.TaskModel;
import com.gpcsoft.plugin.wflow.domain.WorkFlowModel;

public interface WaitprocWorkTaskService extends BaseGenericService<WaitprocWorkTask> {

	/**
	 * 硬编码新增待办任务(可催办)
	 * @param paramtypeCode  //任务代码，格式:****__***__****
	 * @param bizId  		//业务ID
	 * @param bizId  		//顶级业务Id，用于待办任务归类
	 * @param worktaskName  //任务名称
	 * @param receiverMap  	//接受者列表，put(receiverId, receiverType)
	 * @param titleArray   	//显示列内容，
	 * @return  新增的待办任务ID
	 * @throws EsException
	 */
	public String saveTaskByHand (String paramtypeCode, String bizId,String parentBizId,String worktaskName, Map receiverMap,String[] titleArray,String userId) throws Exception;
	
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
	public String saveWorkFlowTaskByHand(String paramtypeCode, String bizId,String parentBizId,String worktaskName, Map receiverMap,String[] titleArray,String userId)throws EsException;
	
	/**
	 * 根据业务ID和用户ID，删除待办任务
	 * @param paramtypeCode
	 * @param bizId
	 * @param userId
	 * @throws Exception
	 */
	public void deleteTaskByBizIdAndUserId (String paramtypeCode,String bizId,String userId) throws Exception;
	
    /**
     * 人工完成待办任务
     * 参数
     */
    public void saveFinishWaitProcHand (String paramtypeCode,String paramKind,String bizId, String userId, String orgId, String viewProcResultURL) throws Exception;
    
    
    
    /** 
     * Description :  根据待办任务完成对应的待办任务
     * Create Date: 2010-8-24上午09:45:31 by liuy  Modified Date: 2010-8-24上午09:45:31 by liuy
     * @param workFlowModel		待办任务MODEL
     * @param userId			操作人ID
     * @param orgId				操作人机构ID
     * @throws Exception
     */
    public void saveFinishWaitProcById (WorkFlowModel workFlowModel,String userId, String orgId) throws Exception;
    
    /**
     * @Description: 扩展查询，根据query返回待办任务
     * @param queryObject
     * @return List<WaitprocWorkTask>
     * @throws Exception
     * @Create Date 2010-8-23 下午02:17:02 By wanghz
     */
    public List<WaitprocWorkTask> getAllByQueryObject(QueryObject<WaitprocWorkTask> queryObject)throws Exception;

	/** 
	 * Description :  根据待办任务ID获得对应的待办任务显示MODEL
	 * Create Date: 2010-8-23下午03:54:49 by liuy  Modified Date: 2010-8-23下午03:54:49 by liuy
	 * @param workFlowTaskId	待办任务ID
	 * @return
	 * @throws Exception
	 */
	public TaskModel getTaskModelById(String workFlowTaskId)throws Exception;

	/** 
	 * Description :  根据查询对象获得所有的待办任务显示MODEL
	 * Create Date: 2010-8-23下午03:54:49 by liuy  Modified Date: 2010-8-23下午03:54:49 by liuy
	 * @param workFlowTaskId	待办任务ID
	 * @return
	 * @throws Exception
	 */
	public List<TaskModel> getAllTaskModelByQueryObject(QueryObject queryObject) throws Exception;

	/** 
	 * Description :  根据查询条件获得对应的所有待办任务
	 * Create Date: 2010-8-24下午04:58:59 by liuy  Modified Date: 2010-8-24下午04:58:59 by liuy
	 * @param queryObject
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public Page<TaskModel> getAllTaskModelByQueryObject(QueryObject queryObject, Page page) throws Exception;
	
	/**
	 * @Description: 获取待办任务
	 * @param paramtypeCode 任务类别
	 * @param bizId 业务ID
	 * @param parentBizId 顶级业务ID
	 * @return WaitprocWorkTask
	 * @throws Exception
	 * @Create Date 2010-8-26 上午09:52:30 By wanghz
	 */
	public WaitprocWorkTask getWaitprocWorkTask(String paramtypeCode,String bizId,String parentBizId)throws EsException;
	
	/**
	 * @Description: 根据任务类型CODE获取所有代办任务
	 * @param worktaskReType 任务接受者类型[接受者为所有、机构、组织、角色或用户]
	 * @param receiveId 接收人ID
	 * @return List<WaitprocWorkTask>
	 * @throws Exception
	 * @Create Date 2010-8-27 上午10:53:34 By wanghz
	 */
	public List<WaitprocWorkTask> getAllWaitprocWorkTask(String paramtypeCode,String worktaskReType,String receiveId)throws EsException;
	
	/**
	 * @Description: 获取所有待办任务Model
	 * @param paramtypeCodes 任务类别CODE
	 * @return List<WaitprocWorkTaskModel>
	 * @throws Exception
	 * @Create Date 2010-8-27 上午10:17:41 By wanghz
	 */
	public List<WaitprocWorkTaskModel> getAllWaitprocWorkTaskModelByParamtypeCodes(String[] paramtypeCodes)throws EsException;
}
