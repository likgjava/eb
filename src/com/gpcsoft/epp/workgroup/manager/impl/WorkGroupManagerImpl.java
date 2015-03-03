package com.gpcsoft.epp.workgroup.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.workgroup.dao.WorkGroupDao;
import com.gpcsoft.epp.workgroup.domain.WorkGroup;
import com.gpcsoft.epp.workgroup.manager.WorkGroupManager;

@Repository
public class WorkGroupManagerImpl extends BaseManagerImpl<WorkGroup> implements WorkGroupManager {

	@Autowired(required=true)  @Qualifier("workGroupDaoHibernate")
	private WorkGroupDao workGroupDaoHibernate;
    
	/**
	 * 
	 * Description :  保存或更新工作小组对象
	 * Create Date: 2010-5-23下午04:44:37 by liuke  Modified Date: 2010-5-23下午04:44:37 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public void saveOrUpdateWorkGroup(WorkGroup workGroup) {
		String objId = workGroup.getObjId();
		if(objId == null || objId.equals(""))//主键为空说明是新增
		{
			workGroupDaoHibernate.saveWorkGroup(workGroup);
		}
		else
		{
			workGroupDaoHibernate.updateWorkGroup(workGroup);
		}
		
	}
	/**
	 * Description :通过查询对象得到工作小组对象  
	 * Create Date: 2010-5-24下午05:50:04 by liuke  Modified Date: 2010-5-24下午05:50:04 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public WorkGroup getWorkGroupByQueryObject(QueryObject queryObject) {
		List list = workGroupDaoHibernate.findByQuery(queryObject);
		if(list.size()>0){
			return (WorkGroup)list.get(0);		
		}
		else
		{
			return null;
		}	

	}
	
	/**
	 * 
	 * Description :  根据主键得到工作小组对象
	 * Create Date: 2010-5-24下午07:10:46 by liuke  Modified Date: 2010-5-24下午07:10:46 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public WorkGroup getWorkGroupByObjId(String objId) {
		
		return workGroupDaoHibernate.get(objId);
	}
	/**
	 * 
	 * Description :  通过项目主键和小组类型得到工作小组对象
	 * Create Date: 2010-5-24下午05:45:43 by liuke  Modified Date: 2010-5-24下午05:45:43 by liuke
	 * @param   projectId：项目Id,workType：工作组类型
	 * @return  WorkGroup
	 * @Exception
	 */
	public WorkGroup getWorkGroupByProjectIdAndType(String projectId,String workType) {
		return workGroupDaoHibernate.getWorkGroupByProjectIdAndType(projectId, workType);
	}
	
	/** 
	 * Description :  根据项目Id、小组类型保存工作小组对象
	 * Create Date: 2010-11-2下午02:07:09 by yangx  Modified Date: 2010-11-2下午02:07:09 by yangx
	 * @param   projectId：项目Id,workType：小组类型
	 * @return  WorkGroup：工作小组
	 * @Exception   
	 */
	public WorkGroup saveWorkGroupByProjectIdAndType(String projectId,String workType){
		Project p = new Project();
	   	p.setObjId(projectId);
	   	WorkGroup g = new WorkGroup();
	   	g.setProject(p);
	   	g.setWorkgType(workType);
	   	this.save(g);
		return g;
	}
	
	/**
	 * 
	 * Description :根据用户获得工作小组列表  
	 * Create Date: 2010-7-29上午11:25:47 by liuke  Modified Date: 2010-7-29上午11:25:47 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<String> getProjectListByUserId(String userId) {
		return workGroupDaoHibernate.getProjectListByUserId(userId);
	}

}
