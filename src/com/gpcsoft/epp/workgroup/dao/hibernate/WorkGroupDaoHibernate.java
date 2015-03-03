package com.gpcsoft.epp.workgroup.dao.hibernate;

import java.util.List;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.workgroup.dao.WorkGroupDao;
import com.gpcsoft.epp.workgroup.domain.WorkGroup;

import org.springframework.stereotype.Repository;

@Repository
public class WorkGroupDaoHibernate extends BaseGenericDaoHibernate<WorkGroup> implements WorkGroupDao {
	/**
	 * 
	 * Description :  新增工作小组
	 * Create Date: 2010-5-23下午04:47:06 by liuke  Modified Date: 2010-5-23下午04:47:06 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public void saveWorkGroup(WorkGroup workGroup) {
		this.getHibernateTemplate().save(workGroup);
	}
	/**
	 * 
	 * Description : 更新工作小组 
	 * Create Date: 2010-5-23下午04:48:32 by liuke  Modified Date: 2010-5-23下午04:48:32 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public void updateWorkGroup(WorkGroup workGroup) {
		workGroup = this.getHibernateTemplate().merge(workGroup);  
		this.getHibernateTemplate().update(workGroup);
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
		List list = this.findbyHql("select distinct wm.subProject.parentId from WorkgMember wm where wm.user.objId=?", userId);
		return list;
	}
	
	/** 
	 * Description :  根据项目Id、工作组类型查询工作组
	 * Create Date: 2010-10-22下午01:52:25 by yangx  Modified Date: 2010-10-22下午01:52:25 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public WorkGroup getWorkGroupByProjectIdAndType(String projectId,String groupType){
		List<WorkGroup> list = this.findbyHql("from WorkGroup wg where wg.project.objId=? and wg.workgType=?", projectId,groupType);
		return (list!=null&&list.size()>0)?list.get(0):null;
	}
}
