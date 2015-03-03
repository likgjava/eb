package com.gpcsoft.epp.workgroup.dao.hibernate;

import java.util.List;

import com.gpcsoft.core.dao.hibernate.BaseGenericDaoHibernate;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.workgroup.dao.WorkgMemberDao;
import com.gpcsoft.epp.workgroup.domain.WorkGroupTypeEnum;
import com.gpcsoft.epp.workgroup.domain.WorkMemberTypeEnum;
import com.gpcsoft.epp.workgroup.domain.WorkgMember;
import com.gpcsoft.srplatform.auth.domain.Role;

import org.springframework.stereotype.Repository;

@Repository
public class WorkgMemberDaoHibernate extends BaseGenericDaoHibernate<WorkgMember> implements WorkgMemberDao {

	public Role getExpertRole() {
			String hql = "from Role where type = ?";
			String expertRoleType = messageSource.getMessage("expertRoleType");
			return (Role)getHibernateCacheTemplate().find(hql,expertRoleType).get(0);
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
		List<WorkgMember> list = this.findbyHql("from WorkgMember wm where wm.subProject.objId=? and wm.user.objId=?", projectId,userId);
		return list;
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
		List list = this.findbyHql("select wm.subProject from WorkgMember wm where wm.user.objId=? and wm.subProject.parentId=? ", userId,projectId);
		return list;
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
		StringBuffer hql = new StringBuffer();
		hql.append("select wm  from WorkgMember wm , WorkGroup wg  where  wm.workGroup.objId = wg.objId and  wg.workgType = ? and wm.subProject.objId = ?");
		List<WorkgMember> list = this.findbyHql(hql.toString(),WorkGroupTypeEnum.APPRAISAL ,subProjectId);
		return list;
	}

	/**
	 * 
	 * Description :根据包组和成员类型获得专家列表  
	 * Create Date: 2010-9-14下午02:26:35 by caojz  Modified Date: 2010-9-14下午02:26:35 by caojz
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<WorkgMember> getWorkgMemberByProjectAndMemberType(String subProjectId,String isLeader){
//		String hql = "from WorkgMember wm where wm.subProject.objId = ? and wm.workgmType = ? and wm.workgmIsLeader = ?";
//		List<WorkgMember> list = this.findbyHql(hql.toString(),subProjectId,WorkMemberTypeEnum.EXPERT_REPRESENT,isLeader);
		List<WorkgMember> list = this.findbyHql("from WorkgMember wm where wm.subProject.objId = '"+subProjectId+"' and wm.workgmType = '"+WorkMemberTypeEnum.EXPERT_REPRESENT+"' and wm.workgmIsLeader = '"+isLeader+"'");
		return list;
	}
	/**
	 * 
	 * Description :根据项目/包组Id和工作小组类型获得成员列表 
	 * Create Date: 2011-9-2上午 11:40:30 by caojz  Modified Date: 2011-9-2上午 11:40:30 by caojz
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<WorkgMember> getWorkgMemberListByWorkgroupId(String projectId,String groupType){
		String hql = "from WorkgMember wm where wm.subProject.objId = ? and wm.workGroup.workgType = ? ";
		List<WorkgMember> list = this.findbyHql(hql.toString(),projectId,groupType);
		return  list;
	}
	/** 
	 * Description :  根据项目Id、工作组类型、成员类型查询小组成员
	 * Create Date: 2010-10-22上午11:20:21 by yangx  Modified Date: 2010-10-22上午11:20:21 by yangx
	 * @param   projectId：项目Id,groupType：工作组类型,workgmType：成员类型
	 * @return  List<WorkgMember>
	 * @Exception   
	 */
	public List<WorkgMember> getWorkgMemberByProjectIdAndGroupType(String projectId,String groupType,String workgmType){
		StringBuffer hql = new StringBuffer();
		hql.append("From WorkgMember wm where ");
		hql.append(" wm.workgmType = '"+workgmType+"'");
		hql.append(" and wm.workGroup.workgType='"+groupType+"'");
		hql.append(" and wm.subProject.objId='"+projectId+"'");
		List<WorkgMember> list = this.findbyHql(hql.toString());
		return list;
	}
	/** 
	 * Description :  根据项目Id、工作组类型查询小组成员
	 * Create Date: 2010-10-22上午11:20:21 by yangx  Modified Date: 2010-10-22上午11:20:21 by yangx
	 * @param   projectId：项目Id,groupType：工作组类型,workgmType：成员类型
	 * @return  List<WorkgMember>
	 * @Exception   
	 */
	public List<WorkgMember> getWorkgMemberByProjectId(String projectId,String groupType){
		StringBuffer hql = new StringBuffer();
		hql.append("From WorkgMember wm where 1=1");
		hql.append(" and wm.workGroup.workgType='"+groupType+"'");
		hql.append(" and wm.subProject.objId='"+projectId+"'");
		List<WorkgMember> list = this.findbyHql(hql.toString());
		return list;
	}
	/** 
	 * Description :  根据项目Id或包组Id获取小组成员
	 * Create Date: 2010-11-2下午08:33:41 by yangx  Modified Date: 2010-11-2下午08:33:41 by yangx
	 * @param   projectId：项目或包组Id
	 * @return  List<WorkgMember>
	 * @Exception   
	 */
	public List<WorkgMember> getWorkgMemberByProjectId(String projectId){
		StringBuffer hql = new StringBuffer();
		hql.append("From WorkgMember wm where ");
		hql.append(" wm.subProject.objId='"+projectId+"'");
		List<WorkgMember> list = this.findbyHql(hql.toString());
		return list;
	}
	
	/**
	 * FuncName: getWorkMemberList
	 * Description :  获取评审专家集合(电子评审系统接口)
	 * @param 
	 * @return List<WorkgMember>
	 * @author: shenjz
	 * @Create Date:2011-3-24  上午09:25:02
	 * @Modifier: shenjz
	 * @Modified Date:2011-3-24  上午09:25:02
	 */
	public List<WorkgMember> getWorkMemberList(String projectIds){
		StringBuffer sb = new StringBuffer();
		sb.append("from WorkgMember t where t.workGroup.project.objId in ("+projectIds+")");
		return this.findbyHql(sb.toString());
	}

	/** 
	 * Description :  根据工作小组Id查询小组成员
	 * @param   workGroupId：工作小组Id
	 * @return  List<WorkgMember>
	 * @Exception   
	 * @author: zhouzhanghe
	 * @Create Date:2011-9-26 15:21
	 */
	public List<WorkgMember> getWorkgMemberByWorkGroupId(String workGroupId) {
		return this.findbyHql("from WorkgMember t where t.workGroup.objId = ?", new Object[]{workGroupId});
	}
}
