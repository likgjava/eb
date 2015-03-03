package com.gpcsoft.epp.workgroup.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gpcsoft.core.manager.impl.BaseManagerImpl;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.workgroup.dao.WorkgMemberDao;
import com.gpcsoft.epp.workgroup.domain.WorkgMember;
import com.gpcsoft.epp.workgroup.manager.WorkgMemberManager;
import com.gpcsoft.srplatform.auth.domain.Role;

@Repository
public class WorkgMemberManagerImpl extends BaseManagerImpl<WorkgMember> implements WorkgMemberManager {

	@Autowired(required=true)  @Qualifier("workgMemberDaoHibernate")
	private WorkgMemberDao workgMemberDaoHibernate;

	public Role getExpertRole() {
		return workgMemberDaoHibernate.getExpertRole();
	}

	public boolean memberIsExisted(String userId, String workGroupId) {
		boolean b = false;
		List list =workgMemberDaoHibernate.findbyHql("select t from WorkgMember t where user.objId=? and workGroup.objId=?", userId,workGroupId);
		if(list!=null&&list.size()>0)b=true;
		return b;
	}

	public WorkgMember remove(String objId) {
		WorkgMember m = this.get(objId);
		m.getWorkGroup().getMemberSet().remove(m);
		m.setWorkGroup(null);
		workgMemberDaoHibernate.remove(m);
		return m;
	}

	/**
	 * 
	 * Description :根据当前用户和包组获得成员信息  
	 * Create Date: 2010-7-29下午05:17:27 by liuke  Modified Date: 2010-7-29下午05:17:27 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<WorkgMember> getWorkMemberListByUserIdAndProjectId(
			String userId, String projectId) {
		return workgMemberDaoHibernate.getWorkMemberListByUserIdAndProjectId(userId, projectId);
	}

	
	/**
	 * 
	 * Description : 根据当前用户和项目获得包组 
	 * Create Date: 2010-7-29下午05:54:10 by liuke  Modified Date: 2010-7-29下午05:54:10 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<Project> getSubProjectListByUserIdAndProjectId(String userId,
			String projectId) {
		return workgMemberDaoHibernate.getSubProjectListByUserIdAndProjectId(userId, projectId);
	}

	/**
	 * 
	 * Description :根据包组获得专家列表  
	 * Create Date: 2010-8-11下午02:26:35 by liuke  Modified Date: 2010-8-11下午02:26:35 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<WorkgMember> getWorkgMemberListBysubProjectId(
			String subProjectId) {
		
		return workgMemberDaoHibernate.getWorkgMemberListBysubProjectId(subProjectId);
	}
}
