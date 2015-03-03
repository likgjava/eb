package com.gpcsoft.epp.common.manager.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryObjectBase;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.epp.common.dao.ApiLogDao;
import com.gpcsoft.epp.common.domain.ApiLog;
import com.gpcsoft.epp.common.domain.ApiLogStatusEnum;
import com.gpcsoft.epp.common.domain.SeparationEnum;
import com.gpcsoft.epp.common.manager.ApiLogManager;
import com.gpcsoft.epp.project.domain.Project;

@Repository
public class ApiLogManagerImpl extends BaseManagerImpl<ApiLog> implements ApiLogManager{

	@Autowired(required=true)  @Qualifier("apiLogDaoHibernate")
	private ApiLogDao apiLogDaoHibernate;
	
	/** 
	 * Description :  保存操作日志信息
	 * Create Date: 2010-9-13下午02:27:46 by yangx  Modified Date: 2010-9-13下午02:27:46 by yangx
	 * @param bizId：业务Id,apiType：交换类型,target：发布的目标地编号,targetName：发布的目标地名称,status：是否发布成功[成功:"true";失败:"false";异常:"exception"]
	 * @return  
	 * @Exception   
	 */
	public ApiLog saveLogRecord(String bizId,String projectId,String apiType,String target,String targetName,String status,String log){
		ApiLog apiLog = new ApiLog();
		apiLog.setBizId(bizId);
		if (projectId!=null&&!"".equals(projectId)) {
			Project project = new Project();
			project.setObjId(projectId);
			apiLog.setProject(project);
		}
		apiLog.setApiType(apiType);
		apiLog.setTarget(target);
		apiLog.setTargetName(targetName);
		apiLog.setStatus(status);
		apiLog.setDoCount(new BigDecimal(SeparationEnum.NUMBER_ONE));
		apiLog.setLog(log);
		this.save(apiLog);
		return apiLog;
	}
	/** 
	 * Description :  修改日志信息
	 * Create Date: 2010-9-13下午02:27:46 by yangx  Modified Date: 2010-9-13下午02:27:46 by yangx
	 * @param bizId：业务Id,apiType：交换类型,status：是否发布成功[成功:"true";失败:"false";异常:"exception"]
	 * @return  
	 * @Exception   
	 */
	public ApiLog updateLogRecord(String bizId,String apiType,String status){
		QueryObject<ApiLog> queryObject = new QueryObjectBase<ApiLog>();
		queryObject.setEntityClass(ApiLog.class);
		queryObject.getQueryParam().and(new QueryParam("bizId",QueryParam.OPERATOR_EQ,bizId));
		queryObject.getQueryParam().and(new QueryParam("apiType",QueryParam.OPERATOR_EQ,apiType));
//		queryObject.getQueryParam().and(new QueryParam("status",QueryParam.OPERATOR_EQ,status));
		List<ApiLog> apiLogList = this.findByQuery(queryObject);
		ApiLog apiLog = null;
		if (apiLogList!=null&&apiLogList.size()>0) {
			apiLog = apiLogList.get(0);
			if (status!=null&&ApiLogStatusEnum.APILOG_TRUE.equals(status)) {//成功
				apiLog.setStatus(ApiLogStatusEnum.APILOG_TRUE);
			}else{
				if (apiLog.getDoCount().compareTo(new BigDecimal(messageSource.getMessage("comparaNumber")))==0||apiLog.getDoCount().compareTo(new BigDecimal(messageSource.getMessage("comparaNumber")))==1) {//判断是否已经操作过2次
					apiLog.setStatus(ApiLogStatusEnum.APILOG_EXCEPTION);
				}
			} 
			apiLog.setDoCount(apiLog.getDoCount().add(new BigDecimal(SeparationEnum.NUMBER_ONE)));
			this.save(apiLog);
		}
		return apiLog;
	}
	
	/** 
	 * Description :  保存或修改操作日志信息
	 * Create Date: 2011-1-24下午02:48:45 by yangx  Modified Date: 2011-1-24下午02:48:45 by yangx
	 * @param   bizId：业务Id,apiType：交换类型,target：发布的目标地编号,targetName：发布的目标地名称,status：是否发布成功[成功:"true";失败:"false";异常:"exception"]
	 * @return  
	 * @Exception   
	 */
	public ApiLog saveOrUpdateLogRecord(String bizId,String projectId,String apiType,String target,String targetName,String status,String log){
		QueryObject<ApiLog> queryObject = new QueryObjectBase<ApiLog>();
		queryObject.setEntityClass(ApiLog.class);
		queryObject.getQueryParam().and(new QueryParam("bizId",QueryParam.OPERATOR_EQ,bizId));
		if (projectId!=null&&!"".equals(projectId)) {
			queryObject.getQueryParam().and(new QueryParam("project.objId",QueryParam.OPERATOR_EQ,projectId));
		}
		List<ApiLog> apiLogList = this.findByQuery(queryObject);
		ApiLog apiLog = null;
		if (apiLogList!=null&&apiLogList.size()>0) {//判断是否已经发送过了
			apiLog = this.updateLogRecord(bizId, apiType, status);//修改日志信息
		}else{
			apiLog = this.saveLogRecord(bizId, projectId, apiType, target, targetName, status, log);//新增日志信息
		}
		return apiLog;
	}
}
