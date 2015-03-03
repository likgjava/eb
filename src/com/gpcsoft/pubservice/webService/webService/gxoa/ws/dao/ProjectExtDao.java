/**
 * 
 */
package com.gpcsoft.pubservice.webService.webService.gxoa.ws.dao;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.epp.project.domain.Project;

/**
 * @author Administrator
 *
 */
public interface ProjectExtDao extends BaseGenericDao<Project> {
	
	/** 
	 * Description :修改项目计划  
	 * Create Date: 2011-8-9上午11:45:20 by sunl  Modified Date: 2011-8-9上午11:45:20 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Integer updateProjectPlan(String projectId,String code, String status) throws Exception;
	
	/** 
	 * Description :清理立项数据  
	 * Create Date: 2011-8-9上午11:45:20 by sunl  Modified Date: 2011-8-9上午11:45:20 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void removeStepProject(String projCode) throws Exception;
	
	/** 
	 * Description :清理招标文件环节数据  
	 * Create Date: 2011-8-9上午11:45:20 by sunl  Modified Date: 2011-8-9上午11:45:20 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void removeStepPurchaseFile(String projCode) throws Exception;
	
	/** 
	 * Description :清理招标公告环节数据  
	 * Create Date: 2011-8-9上午11:45:20 by sunl  Modified Date: 2011-8-9上午11:45:20 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void removeStepBulletin(String projCode) throws Exception;
	
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
