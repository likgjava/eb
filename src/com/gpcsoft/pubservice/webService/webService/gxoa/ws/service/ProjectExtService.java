/**
 * 
 */
package com.gpcsoft.pubservice.webService.webService.gxoa.ws.service;

import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.taskplan.domain.TaskPlan;
import com.gpcsoft.epp.taskplan.domain.TaskPlanSub;

/**
 * @author Administrator
 *
 */
public interface ProjectExtService {
	/** 
	 * Description :  根据传入的立项xml字符串保存项目等信息
	 * Create Date: 2011-7-29上午09:00:34 by sunl  Modified Date: 2011-7-29上午09:00:34 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public String saveProjectByXml(String xml)throws Exception;
	
	/** 
	 * Description :  根据传入的xml同步采购文件等信息
	 * Create Date: 2011-8-1上午09:33:40 by sunl  Modified Date: 2011-8-1上午09:33:40 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public String savePurchaseFileByXml(String xml)throws Exception;
	
	/** 
	 * Description :  根据传入的xml同步采购公告等信息
	 * Create Date: 2011-8-1上午09:33:40 by sunl  Modified Date: 2011-8-1上午09:33:40 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public String saveBulletinByXml(String xml)throws Exception;
	
	/** 
	 * Description :  同步澄清公告
	 * Create Date: 2011-10-24下午02:47:56 by sunl  Modified Date: 2011-10-24下午02:47:56 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public String saveChengQingBulletinByXml(String xml)throws Exception;
	
	/** 
	 * Description :  同步采购单位
	 * Create Date: 2011-10-24下午04:59:37 by sunl  Modified Date: 2011-10-24下午04:59:37 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void saveBuyerByXml(String xml) throws Exception;
	

	/** 
	 * Description :  同步组织机构
	 * Create Date: 2011-10-24下午04:59:37 by sunl  Modified Date: 2011-10-24下午04:59:37 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void saveOrgByXml(String xml) throws Exception;
	
	/** 
	 * Description :  根据xml内容解析项目
	 * Create Date: 2011-7-29上午08:56:22 by sunl  Modified Date: 2011-7-29上午08:56:22 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Project getProjectByXml(String xml) throws Exception;
	
	/** 
	 * Description :  根据xml内容解析任务书
	 * Create Date: 2011-7-29上午08:56:22 by sunl  Modified Date: 2011-7-29上午08:56:22 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public TaskPlan getTaskPlanByXml(String xml) throws Exception;
	
	/** 
	 * Description :  根据xml内容解析任务书明细
	 * Create Date: 2011-7-29上午08:56:22 by sunl  Modified Date: 2011-7-29上午08:56:22 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public TaskPlanSub getTaskPlanSubByXml(String xml) throws Exception;
	
	/** 
	 * Description :  保存xml信息
	 * Create Date: 2011-7-29上午08:56:22 by sunl  Modified Date: 2011-7-29上午08:56:22 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void saveXml(String projCode, String syncMethod, String xmlContent) throws Exception;
	
	/** 
	 * Description :  保存结果信息
	 * Create Date: 2011-7-29上午08:56:22 by sunl  Modified Date: 2011-7-29上午08:56:22 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void saveResult(String projCode, String syncMethod, String xmlContent) throws Exception;
		
	/** 
	 * Description :创建项目计划  
	 * Create Date: 2011-8-9上午11:45:20 by sunl  Modified Date: 2011-8-9上午11:45:20 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void createProjectPlan(String projectId) throws Exception;
	
	/** 
	 * Description :修改项目状态  
	 * Create Date: 2011-8-9上午11:45:20 by sunl  Modified Date: 2011-8-9上午11:45:20 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void saveProjProcessStatus(String projectId,String status, String statusCN) throws Exception;
	
	/** 
	 * Description :修改项目计划  
	 * Create Date: 2011-8-9上午11:45:20 by sunl  Modified Date: 2011-8-9上午11:45:20 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void updateProjectPlan(String projectId,String code, String status) throws Exception;
	
	/** 
	 * Description :清理立项环节的数据  
	 * Create Date: 2011-8-9上午11:45:20 by sunl  Modified Date: 2011-8-9上午11:45:20 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void removeStepProject(String projCode) throws Exception;
	
	/** 
	 * Description :清理招标文件环节的数据  
	 * Create Date: 2011-8-9上午11:45:20 by sunl  Modified Date: 2011-8-9上午11:45:20 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void removeStepPurchaseFile(String projCode) throws Exception;
	
	/** 
	 * Description :清理招标公告环节的数据  
	 * Create Date: 2011-8-9上午11:45:20 by sunl  Modified Date: 2011-8-9上午11:45:20 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void removeStepBulletin(String projCode) throws Exception;
	
	/** 
	 * Description :  根据项目ID获取招标公告Id
	 * Create Date: 2011-10-24上午11:31:43 by sunl  Modified Date: 2011-10-24上午11:31:43 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public String getBulletinByProjId(String projId) throws Exception;
	
	/** 
	 * Description :  根据项目编号清理澄清公告
	 * Create Date: 2011-10-24下午02:10:12 by sunl  Modified Date: 2011-10-24下午02:10:12 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void removeBulletinByProjCode(String projCode, String bulletinType) throws Exception;
	
	/** 
	 * Description :  删除机构（置为无效状态）
	 * Create Date: 2011-10-25上午10:21:33 by sunl  Modified Date: 2011-10-25上午10:21:33 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Integer removeOrg(String orgInfoId,String status) throws Exception;
}
