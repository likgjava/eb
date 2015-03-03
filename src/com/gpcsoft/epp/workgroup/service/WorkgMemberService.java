package com.gpcsoft.epp.workgroup.service;
import java.util.List;

import com.gpcsoft.core.service.BaseGenericService;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.workgroup.domain.WorkgMember;

public interface WorkgMemberService extends BaseGenericService<WorkgMember> {
	
	/** 
	 * Description :  选择员工作为开标小组成员
	 * Create Date: 2010-6-26上午10:59:53 by wangcl  Modified Date: 2010-6-26上午10:59:53 by wangcl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Project saveBidMemberByEmp(String[] empIds,String workGroupId,String projectId);
	
	/** 
	 * Description :  选择员工作为论证小组成员
	 * Create Date: 2010-6-26上午10:59:53 by wangcl  Modified Date: 2010-6-26上午10:59:53 by wangcl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Project saveProofMemberByEmp(String[] empIds,String workGroupId,String projectId);
	
	
	/**
	 * 
	 * Description : 根据当前用户ID获得项目列表
	 * Create Date: 2010-7-29上午10:56:21 by liuke  Modified Date: 2010-7-29上午10:56:21 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<Project> getProjectListByUserId(String userId);
	
	/** 
	 * Description :  删除开标小组成员
	 * Create Date: 2010-9-7下午04:15:56 by wangcl  Modified Date: 2010-9-7下午04:15:56 by wangcl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public WorkgMember removeBidMember(String objId);
	/** 
	 * Description :  删除论证小组成员
	 * Create Date: 2010-9-7下午04:15:59 by wangcl  Modified Date: 2010-9-7下午04:15:59 by wangcl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public WorkgMember removeProofMember(String objId);
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
	 * Description :  保存开标人
	 * Create Date: 2010-10-22下午01:43:29 by yangx  Modified Date: 2010-10-22下午01:43:29 by yangx
	 * @param   workgMember:成员,projectId：项目Id,groupType：工作组类型,workGroupId：工作组Id,empId：员工Id
	 * @return  WorkgMember：工作组成员
	 * @Exception   
	 */
	public WorkgMember saveWorkgMember(WorkgMember workgMember,String projectId,String groupType,String workGroupId,String empId);

	/** 
	 * Description :  根据主键获取开标人
	 * Create Date: 2010-10-22下午07:15:16 by yangx  Modified Date: 2010-10-22下午07:15:16 by yangx
	 * @param   workgMemberId：开标人Id
	 * @return   WorkgMember：工作组成员
	 * @Exception   
	 */
	public WorkgMember getWorkgMemberByObjId(String workgMemberId);
	
	/** 
	 * Description :  修改开标人
	 * Create Date: 2010-10-22下午07:29:44 by yangx  Modified Date: 2010-10-22下午07:29:44 by yangx
	 * @param   workgMember:开标人对象;empId：员工Id
	 * @return  WorkgMember:开标人对象
	 * @Exception   
	 */
	public WorkgMember updateWorkgMember(WorkgMember workgMember,String empId);
	
	
	
	/**
	 * FuncName: saveJudgeMember
	 * Description :  保存评标人
	 * @param 
	 * @param workgMember
	 * @param projectId
	 * @param groupType
	 * @param workGroupId
	 * @return WorkgMember
	 * @author: liuke
	 * @Create Date:2011-3-4  上午10:19:51
	 * @Modifier: liuke
	 * @Modified Date:2011-3-4  上午10:19:51
	 */
	public WorkgMember saveJudgeMember(WorkgMember workgMember,String projectId,String groupType,String workGroupId);
	
    /**
     * FuncName: saveWorkgMemberDesion
     * Description:完成组建开标小组节点的方法
     * @param 
     * @param request
     * @return
     * @throws Exception ModelAndView
     * @author: shenjz
     * @Create Date:2011-4-1  上午10:42:39
     * @Modifier: shenjz
     * @Modified Date:2011-4-1  上午10:42:39
     */
	public WorkgMember saveWorkgMemberDesion(String projectId);
	
	/** 
	 * Description :  根据工作小组Id查询小组成员
	 * @param   workGroupId：工作小组Id
	 * @return  List<WorkgMember>
	 * @Exception   
	 * @author: zhouzhanghe
	 * @Create Date:2011-9-26 15:04
	 */
	public List<WorkgMember> getWorkgMemberByWorkGroupId(String workGroupId);
	
	
	/** 
	 * FuncName : saveWorkgMember
	 * Description :  保存开标小组
	 * Create Date: 2011-11-10下午05:56:40 by yangx  
	 * Modified Date: 2011-11-10下午05:56:40 by yangx
	 * @param   workgMember
	 * @return  WorkgMember
	 */
	public WorkgMember saveLeaderWorkgMember(WorkgMember workgMember,String projectId);
	
	/**
	 * FuncName: finishLeanderWorkgMember
	 * Description :  完成领导小组创建
	 * @param projectId
	 * @return WorkgMember
	 * @author: zhangsh
	 * @Create Date:2011-11-16  上午10:01:00
	 * @Modifier: zhangsh
	 * @Modified Date:2011-11-16  上午10:01:00
	*/	
	public WorkgMember finishLeanderWorkgMember(String projectId);
	


	/**
	 * FuncName: saveWorkgMemberDesion
	 * Description:完成组建评审小组节点的方法
	 * @param 
	 * @param request
	 * @return
	 * @throws Exception ModelAndView
	 * @Created at 2011-5-17 17:02 by zhouzhanghe
	 */
	public WorkgMember saveScoreWorkgMember(String projectId);


	/**
	 * 
	 * Description :根据包组和成员类型获得专家列表  
	 * Create Date: 2010-9-14下午02:26:35 by caojz  Modified Date: 2010-9-14下午02:26:35 by caojz
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<WorkgMember> getWorkgMemberByProjectAndMemberType(String subProjectId,String isLeader);

	/**
	 * 
	 * Description :根据包组获得专家列表  
	 * Create Date: 2010-8-11下午02:26:35 by liuke  Modified Date: 2010-8-11下午02:26:35 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	public List<WorkgMember> getWorkgMemberListBysubProjectId(
			String subProjectId) ;


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
	 * FuncName: saveLeaderGroupMember
	 * Description: 保存领导小组成员
	 * @param 
	 * @param request
	 * @return
	 * @throws Exception ModelAndView
	 * @Created at 2011-10-19 18:04 by zhouzhanghe
	 */
	public WorkgMember saveLeaderGroupMember(WorkgMember workgMember);
}
