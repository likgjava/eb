package com.gpcsoft.agreement.bargin.project.service;
import java.util.Map;

import com.gpcsoft.agreement.bargin.project.domain.ProjectQueryDto;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.domain.ProjectExceptionApply;
import com.gpcsoft.epp.project.service.ProjectService;

public interface BargainProjectService extends ProjectService {
	
	/** 
	 * Description :  创建竞价项目,同步创建轮次，项目与任务书关联表，需求条目表
	 * Create Date: 2010-9-28上午11:15:50 by sunl  Modified Date: 2010-9-28上午11:15:50 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Project createProjectNoGoods(Project project,Map<String, Object> paramsMap) throws Exception;
	
	/** 
	 * Description :  创建竞价项目(有商品),同步创建轮次，项目与任务书关联表，需求条目表
	 * Create Date: 2010-9-28上午11:15:50 by sunl  Modified Date: 2010-9-28上午11:15:50 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Project createProjectGoods(Project project,Map<String, Object> paramsMap) throws Exception;
	
	/** 
	 * Description :  修改议价项目
	 * Create Date: 2010-9-28上午11:15:50 by sunl  Modified Date: 2010-9-28上午11:15:50 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Project updateTalkProject(Project project, Map<String, Object> paramsMap) throws Exception;
	
	/** 
	 * Description :  保存议价项目
	 * Create Date: 2010-9-28上午11:15:50 by sunl  Modified Date: 2010-9-28上午11:15:50 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Project saveTalkProject(Project project, Map<String,Object> paramMap) throws Exception;
	
	/** 
	 * Description :  创建轮次和项目规则信息，同步项目状态
	 * Create Date: 2010-9-28上午11:15:50 by sunl  Modified Date: 2010-9-28上午11:15:50 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void createTurnAndRule(Project project,Map<String,Object> paramMap) throws Exception;
	
	/** 
	 * Description :  创建报名信息、付款方式、联系方式等信息
	 * Create Date: 2010-9-28上午11:15:50 by sunl  Modified Date: 2010-9-28上午11:15:50 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void createSignPayLinkerInfo(Map<String,Object> paramMap) throws Exception;
	
	/** 
	 * Description :  发布项目，发公告
	 * Create Date: 2010-9-28下午05:41:38 by sunl  Modified Date: 2010-9-28下午05:41:38 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void releaseProject(String projId) throws Exception;
	
	/** 
	 * Description :  项目延时，修改公告
	 * Create Date: 2010-9-28下午05:41:38 by sunl  Modified Date: 2010-9-28下午05:41:38 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void saveDelayProject(Map<String,Object> paramMap) throws Exception;
	
	/** 
	 * Description :  删除竞价或反拍项目
	 * Create Date: 2010-9-28上午11:15:50 by sunl  Modified Date: 2010-9-28上午11:15:50 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void removeRBProject(String projId) throws Exception;
	
	/** 
	 * Description :  取出待评价对象
	 * Create Date: 2010-10-28下午03:45:12 by yucy  Modified Date: 2010-10-28下午03:45:12 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Map<String, Object> getEvaluateObjectSupplier(String projectId) throws Exception;

	/** 
	 * Description :  项目查询数据(采购人)
	 * Create Date: 2011-6-29下午02:14:33 by yucy  Modified Date: 2011-6-29下午02:14:33 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Page<Object> getProjectList(Page<Object> page, Map<String, Object> param) throws Exception;
	
	/** 
	 * Description :  项目查询数据(供应商)
	 * Create Date: 2011-6-29下午02:14:33 by yucy  Modified Date: 2011-6-29下午02:14:33 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Page<Object> getMyProjectList(Page<Object> page, Map<String, Object> param) throws Exception;
	
	/**
	 * Description :  我与客户的交易项目记录
	 * Create Date: 2011-7-7下午03:50:20 by zhaojf  Modified Date: 2011-7-7下午03:50:20 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	public Page<ProjectQueryDto> getExchangeProjectRecord(Page<ProjectQueryDto> page, Map<String, Object> param) throws Exception;

	/** 
	 * Description :  保存终止项目的信息
	 * Create Date: 2011-12-6下午06:11:43 by likg  Modified Date: 2011-12-6下午06:11:43 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void saveStopProjectInfo(ProjectExceptionApply projectExceptionApply, Map<String, Object> param) throws Exception;
	
}
