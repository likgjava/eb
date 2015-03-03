package com.gpcsoft.epp.worktask.dao;

import java.util.List;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.worktask.domain.WaitprocWorkTask;
import com.gpcsoft.plugin.wflow.domain.TaskModel;
import com.gpcsoft.srplatform.auth.domain.User;

public interface WaitprocWorkTaskDao extends BaseGenericDao<WaitprocWorkTask> {

	/**
	 * @Description:删除待办任务
	 * @param bizId
	 * @param userId
	 * @param paramtypeCode
	 * @return void
	 * @throws Exception
	 * @Create Date 2010-8-23 下午01:15:50 By wanghz
	 */
	public void  deleteWaitProcWorkTaskByBizIdAndUserId(String bizId,String userId,String paramtypeCode)throws Exception;
	
    /**
     * @Description: 扩展查询，根据query返回待办任务
     * @param queryObject
     * @return List<WaitprocWorkTask>
     * @throws Exception
     * @Create Date 2010-8-23 下午02:17:02 By wanghz
     */
    public List<WaitprocWorkTask> getAllByQueryObject(QueryObject<WaitprocWorkTask> queryObject)throws Exception;

	/** 
	 * Description :  根据ID删除待办任务和待办任务的接收者信息
	 * Create Date: 2010-8-24下午03:51:21 by liuy  Modified Date: 2010-8-24下午03:51:21 by liuy
	 * @param workFlowTaskId
	 * @throws Exception
	 */
	public void removeById(String workFlowTaskId)throws Exception;

	/** 
	 * Description :  根据查询条件获得对应的待办任务数据
	 * Create Date: 2010-8-24下午05:04:27 by liuy  Modified Date: 2010-8-24下午05:04:27 by liuy
	 * @param queryObject
	 * @param page TODO
	 * @return
	 * @throws Exception
	 */
	public Page findByTaskModelByQueryObject(QueryObject queryObject, Page page) throws Exception;

	/** 
	 * Description :  拿到指定当前用户的待办任务
	 * Create Date: 2010-8-24下午05:40:37 by liuy  Modified Date: 2010-8-24下午05:40:37 by liuy
	 * @param queryObject
	 * @param page
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List getTaskModelByUser(QueryObject queryObject,
			Page<TaskModel> page, User user) throws Exception;

	/** 
	 * Description :  拿到指定给机构的待办任务
	 * Create Date: 2010-8-24下午05:57:41 by liuy  Modified Date: 2010-8-24下午05:57:41 by liuy
	 * @param queryObject
	 * @param page
	 * @param orgInfo
	 * @return
	 */
	public List getTaskModelByOrgInfo(QueryObject queryObject,
			Page<TaskModel> page, OrgInfo orgInfo);

	/** 
	 * Description :  拿到指定给所有用户的待办任务
	 * Create Date: 2010-8-24下午06:00:36 by liuy  Modified Date: 2010-8-24下午06:00:36 by liuy
	 * @param queryObject
	 * @param page
	 * @return
	 */
	public List getTaskModelByAll(QueryObject queryObject, Page<TaskModel> page);
	
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
	 * @Description: 根据待办任务分类CODE 获取所有OBJECT
	 * @param paramtypeCodes
	 * @return List<Object[]>
	 * @throws Exception
	 * @Create Date 2010-8-27 上午10:23:16 By wanghz
	 */
	public List<Object[]> getObjectsByParamtypeCodes(String[] paramtypeCodes)throws EsException;
	
	/**
	 * @Description: 获取代办任务列表
	 * @param user 
	 * @param orgInfo
	 * @param worktaskType 分类编号
	 * @param name 待办任务名称
	 * @param start
	 * @param pageSize
	 * @param queryType(count:数据记录条数,data:数据)
	 * @return List<WaitprocWorkTask>
	 * @throws Exception
	 * @Create Date 2010-8-27 下午08:29:07 By wanghz
	 */
	public Object getAllWaitprocWorkTask(final String worktaskType,final String name,final User user,final OrgInfo orgInfo ,final int start, final int pageSize,String queryType)throws EsException;
	
	/**
	 * @Description: 删除待办任务接收者
	 * @param workTaskId
	 * @param receiveIds
	 * @return Boolean
	 * @throws Exception
	 * @Create Date 2010-8-29 下午06:38:05 By wanghz
	 */
	public void deleteWorktaskReceiveByWorkTaskIdAndReceiveId(String workTaskId,String[] receiveIds)throws EsException;
	
	/**
	 * @Description: 获取待办任务接受者记录数量
	 * @param workTaskId
	 * @param receiveIds
	 * @return Long
	 * @throws Exception
	 * @Create Date 2010-8-29 下午06:44:14 By wanghz
	 */
	public Long getWorktaskReceiveCountByWorkTaskIdAndReceiveId(String workTaskId,String[] receiveIds)throws EsException;
	
	
	/**
	 * @Description: 获取待办任务记录条数
	 * @param code 待办任务CODE
	 * @param bizId 业务ID
	 * @param parentBizId 顶级业务ID
	 * @return Bolean
	 * @throws Exception
	 * @Create Date 2010-8-30 下午05:24:30 By wanghz
	 */
	public Long getProjectPlanWorkTaskIsCount(String code,String bizId,String parentBizId)throws EsException;
}
