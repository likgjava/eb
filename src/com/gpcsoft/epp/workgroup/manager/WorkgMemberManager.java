package com.gpcsoft.epp.workgroup.manager;

import java.util.List;

import com.gpcsoft.core.manager.BaseManager;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.workgroup.domain.WorkgMember;
import com.gpcsoft.srplatform.auth.domain.Role;

public interface WorkgMemberManager extends BaseManager<WorkgMember> {
	
	/** 
	 * Description :  获取评为角色
	 * Create Date: 2010-6-26下午12:25:46 by wangcl  Modified Date: 2010-6-26下午12:25:46 by wangcl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Role getExpertRole();
	
	/** 
	 * Description :  是否已经是小组成员
	 * Create Date: 2010-6-26下午02:23:24 by wangcl  Modified Date: 2010-6-26下午02:23:24 by wangcl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public boolean memberIsExisted(String userId,String workGroupId);
	
	/** 
	 * Description :  解除eager引起的关联关系 否则抛出ObjectDeletedException 
	 * Create Date: 2010-7-13上午11:02:21 by wangcl  Modified Date: 2010-7-13上午11:02:21 by wangcl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public WorkgMember remove(String objId);
	
	
	
	/**
	 * 
	 * Description :根据当前用户和包组获得成员信息  
	 * Create Date: 2010-7-29下午05:17:27 by liuke  Modified Date: 2010-7-29下午05:17:27 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<WorkgMember> getWorkMemberListByUserIdAndProjectId(String userId,String projectId);

	
	/**
	 * 
	 * Description :根据包组获得专家列表  
	 * Create Date: 2010-8-11下午02:26:35 by liuke  Modified Date: 2010-8-11下午02:26:35 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	
	public List<WorkgMember> getWorkgMemberListBysubProjectId(String subProjectId);

	/**
	 * 
	 * Description : 根据当前用户和项目获得包组 
	 * Create Date: 2010-7-29下午05:54:10 by liuke  Modified Date: 2010-7-29下午05:54:10 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<Project> getSubProjectListByUserIdAndProjectId(String userId,String projectId);
}
