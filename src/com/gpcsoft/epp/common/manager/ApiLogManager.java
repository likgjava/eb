package com.gpcsoft.epp.common.manager;

import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.epp.common.domain.ApiLog;

public interface ApiLogManager extends BaseManager<ApiLog>{

	/** 
	 * Description :  保存日志信息
	 * Create Date: 2010-9-13下午02:27:46 by yangx  Modified Date: 2010-9-13下午02:27:46 by yangx
	 * @param bizId：业务Id,apiType：交换类型,target：发布的目标地编号,targetName：发布的目标地名称,status：是否发布成功[成功:"true";失败:"false";异常:"exception"]
	 * @return  
	 * @Exception   
	 */
	public ApiLog saveLogRecord(String bizId,String projectId,String apiType,String target,String targetName,String status,String log);
	/** 
	 * Description :  修改日志信息
	 * Create Date: 2010-9-13下午02:27:46 by yangx  Modified Date: 2010-9-13下午02:27:46 by yangx
	 * @param bizId：业务Id,apiType：交换类型,status：是否发布成功[成功:"true";失败:"false";异常:"exception"]
	 * @return  
	 * @Exception   
	 */
	public ApiLog updateLogRecord(String bizId,String apiType,String status);
	
	/** 
	 * Description :  保存或修改操作日志信息
	 * Create Date: 2011-1-24下午02:48:45 by yangx  Modified Date: 2011-1-24下午02:48:45 by yangx
	 * @param   bizId：业务Id,apiType：交换类型,target：发布的目标地编号,targetName：发布的目标地名称,status：是否发布成功[成功:"true";失败:"false";异常:"exception"]
	 * @return  
	 * @Exception   
	 */
	public ApiLog saveOrUpdateLogRecord(String bizId,String projectId,String apiType,String target,String targetName,String status,String log);
}
