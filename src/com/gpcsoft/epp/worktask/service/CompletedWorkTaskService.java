package com.gpcsoft.epp.worktask.service;
import java.util.List;

import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.epp.worktask.domain.CompletedWorkTask;
import com.gpcsoft.srplatform.auth.domain.User;

public interface CompletedWorkTaskService extends BaseGenericService<CompletedWorkTask> {

	/** 
	 * Description :  根据父业务ID和任务ID查询对应的已操作过的记录
	 * Create Date: 2010-5-27下午04:15:14 by Administrator  Modified Date: 2010-5-27下午04:15:14 by Administrator
	 * @param taskId
	 * @param parentBizId
	 * @param user
	 * @return
	 */
	public List<CompletedWorkTask> getCompletedWorkTaskByParentBizId(String taskId,
			String parentBizId, User user);

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
			String bizId, User user);
	
	
	/** 
	 * Description :  根据父业务ID和任务类型查询对应的已操作过的记录
	 * Create Date: 2010-7-22上午10:42:34 by liuke  Modified Date: 2010-7-22上午10:42:34 by liuke
	 * @param request
	 * @param bizId
	 * @param taskType 
	 * @return  
	 * @Exception   
	 */
	public List<CompletedWorkTask> getCompletedWorkTaskByParentBizIdAndType(String taskType,
			String parentBizId, User user);

}
