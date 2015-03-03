package com.gpcsoft.epp.workgroup.manager;

import java.util.List;

import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.workgroup.domain.WorkGroup;

public interface WorkGroupManager extends BaseManager<WorkGroup> {

	/**
	 * 
	 * Description :  保存或更新工作小组对象
	 * Create Date: 2010-5-23下午04:44:37 by liuke  Modified Date: 2010-5-23下午04:44:37 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public void saveOrUpdateWorkGroup(WorkGroup workGroup);
	
	
	
	
	/**
	 * Description :通过查询对象得到工作小组对象  
	 * Create Date: 2010-5-24下午05:50:04 by liuke  Modified Date: 2010-5-24下午05:50:04 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public WorkGroup getWorkGroupByQueryObject(QueryObject queryObject);
	
	
	
	/**
	 * 
	 * Description :  根据主键得到工作小组对象
	 * Create Date: 2010-5-24下午07:10:46 by liuke  Modified Date: 2010-5-24下午07:10:46 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public  WorkGroup getWorkGroupByObjId(String objId);
	
	/**
	 * 
	 * Description :  通过项目主键和小组类型得到工作小组对象
	 * 					如果找不到则创建一个并保存 modify by wangcl
	 * Create Date: 2010-5-24下午05:45:43 by liuke  Modified Date: 2010-5-24下午05:45:43 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public WorkGroup getWorkGroupByProjectIdAndType(String projectId,String workType);
	
	/** 
	 * Description :  根据项目Id、小组类型保存工作小组对象
	 * Create Date: 2010-11-2下午02:07:09 by yangx  Modified Date: 2010-11-2下午02:07:09 by yangx
	 * @param   projectId：项目Id,workType：小组类型
	 * @return  WorkGroup：工作小组
	 * @Exception   
	 */
	public WorkGroup saveWorkGroupByProjectIdAndType(String projectId,String workType);
	
	/**
	 * 
	 * Description :根据用户获得项目列表  
	 * Create Date: 2010-7-29上午11:25:47 by liuke  Modified Date: 2010-7-29上午11:25:47 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<String> getProjectListByUserId(String userId);
		
}
