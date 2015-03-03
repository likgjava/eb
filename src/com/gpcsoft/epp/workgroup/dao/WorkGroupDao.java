package com.gpcsoft.epp.workgroup.dao;

import java.util.List;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.epp.workgroup.domain.WorkGroup;

public interface WorkGroupDao extends BaseGenericDao<WorkGroup> {

	/**
	 * 
	 * Description :  新增工作小组
	 * Create Date: 2010-5-23下午04:47:06 by liuke  Modified Date: 2010-5-23下午04:47:06 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public void saveWorkGroup(WorkGroup workGroup);
	
	
	
	/**
	 * 
	 * Description : 更新工作小组 
	 * Create Date: 2010-5-23下午04:48:32 by liuke  Modified Date: 2010-5-23下午04:48:32 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public void updateWorkGroup(WorkGroup workGroup);
	
	
	
	/**
	 * 
	 * Description :根据用户获得项目列表  
	 * Create Date: 2010-7-29上午11:25:47 by liuke  Modified Date: 2010-7-29上午11:25:47 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<String> getProjectListByUserId(String userId);
	
	/** 
	 * Description :  根据项目Id、工作组类型查询工作组
	 * Create Date: 2010-10-22下午01:52:25 by yangx  Modified Date: 2010-10-22下午01:52:25 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public WorkGroup getWorkGroupByProjectIdAndType(String projectId,String groupType);
}
