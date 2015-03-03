package com.gpcsoft.epp.workgroup.dao;

import java.util.List;

import com.gpcsoft.core.dao.BaseGenericDao;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.workgroup.domain.WorkgMember;
import com.gpcsoft.srplatform.auth.domain.Role;

public interface WorkgMemberDao extends BaseGenericDao<WorkgMember> {
	
	/** 
	 * Description :  获取评为角色
	 * Create Date: 2010-6-26下午12:25:46 by wangcl  Modified Date: 2010-6-26下午12:25:46 by wangcl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Role getExpertRole();
	
	
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
	 * Description : 根据当前用户和项目获得包组 
	 * Create Date: 2010-7-29下午05:54:10 by liuke  Modified Date: 2010-7-29下午05:54:10 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<Project> getSubProjectListByUserIdAndProjectId(String userId,String projectId);
	
	
	
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
	 * Description :  根据项目Id、工作组类型、成员类型查询小组成员
	 * Create Date: 2010-10-22上午11:20:21 by yangx  Modified Date: 2010-10-22上午11:20:21 by yangx
	 * @param   projectId：项目Id,groupType：工作组类型,workgmType：成员类型
	 * @return  List<WorkgMember>
	 * @Exception   
	 */
	public List<WorkgMember> getWorkgMemberByProjectIdAndGroupType(String projectId,String groupType,String workgmType);
	
	/** 
	 * Description :  根据项目Id、工作组类型查询小组成员
	 * Create Date: 2010-10-22上午11:20:21 by yangx  Modified Date: 2010-10-22上午11:20:21 by yangx
	 * @param   projectId：项目Id,groupType：工作组类型,workgmType：成员类型
	 * @return  List<WorkgMember>
	 * @Exception   
	 */
	public List<WorkgMember> getWorkgMemberByProjectId(String projectId,String groupType);

	/** 
	 * Description :  根据项目Id或包组Id获取小组成员
	 * Create Date: 2010-11-2下午08:33:41 by yangx  Modified Date: 2010-11-2下午08:33:41 by yangx
	 * @param   projectId：项目或包组Id
	 * @return  List<WorkgMember>
	 * @Exception   
	 */
	public List<WorkgMember> getWorkgMemberByProjectId(String projectId);
	
	/**
	 * 
	 * Description :根据项目/包组Id和工作小组类型获得成员列表 
	 * Create Date: 2011-9-2上午 11:40:30 by caojz  Modified Date: 2011-9-2上午 11:40:30 by caojz
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<WorkgMember> getWorkgMemberListByWorkgroupId(String projectId,String groupType);
	
	/**
	 * 
	 * Description :根据包组和成员类型获得专家列表  
	 * Create Date: 2010-9-14下午02:26:35 by caojz  Modified Date: 2010-9-14下午02:26:35 by caojz
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<WorkgMember> getWorkgMemberByProjectAndMemberType(String subProjectId,String isLeader);
	 /** FuncName: getWorkMemberList
	 * Description :  获取评审专家集合(电子评审系统接口)
	 * @param 
	 * @return List<WorkgMember>
	 * @author: shenjz
	 * @Create Date:2011-3-24  上午09:25:02
	 * @Modifier: shenjz
	 * @Modified Date:2011-3-24  上午09:25:02
	 */
	public List<WorkgMember> getWorkMemberList(String projectIds);
	

	/** 
	 * Description :  根据工作小组Id查询小组成员
	 * @param   workGroupId：工作小组Id
	 * @return  List<WorkgMember>
	 * @Exception   
	 * @author: zhouzhanghe
	 * @Create Date:2011-9-26 15:04
	 */
	public List<WorkgMember> getWorkgMemberByWorkGroupId(String workGroupId);
}
