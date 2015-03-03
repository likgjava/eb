package com.gpcsoft.epp.workgroup.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.domain.ProjectMTaskPlan;
import com.gpcsoft.epp.project.service.ProjectMTaskPlanService;
import com.gpcsoft.epp.project.service.ProjectService;
import com.gpcsoft.epp.projrule.domain.ProjProcessRule;
import com.gpcsoft.epp.projrule.service.ProjProcessRuleService;
import com.gpcsoft.epp.workgroup.domain.WorkGroup;
import com.gpcsoft.epp.workgroup.domain.WorkGroupTypeEnum;
import com.gpcsoft.epp.workgroup.domain.WorkgMember;
import com.gpcsoft.epp.workgroup.service.WorkGroupService;
import com.gpcsoft.epp.workgroup.service.WorkgMemberService;
import com.gpcsoft.srplatform.sysconfig.domain.SysConfigEnum;

/**
  * @gpcsoft.view value="workGroupFormView"
  *  url="view/es/planform/workgroup/workGroupForm.jsp"
  * @gpcsoft.view value="workGroupTreeFormView"
  *  url="view/es/planform/workgroup/workGroupTreeForm.jsp"
  * @gpcsoft.view value="workGroupListView"
  *  url="view/es/planform/workgroup/workGroupList.jsp"
  * @gpcsoft.view value="workGroupDetailView"
  *  url="view/es/planform/workgroup/workGroupDetail.jsp"
  * @gpcsoft.view value="workgMemberListView"
  *  url="view/es/planform/workgroup/workgMemberList.jsp"
  * @gpcsoft.view value="workgMemberListForOpenBid"
  *  url="view/es/planform/workgroup/workgMemberListForOpenBid.jsp"
  * @gpcsoft.view value="addOpenBidMember"
  *  url="view/es/planform/workgroup/addOpenBidMember.jsp"
  * @gpcsoft.view value="updateOpenBidMember"
  *  url="view/es/planform/workgroup/updateOpenBidMember.jsp"
  * @gpcsoft.view value="openBidGroupForProject"
  *  url="view/es/planform/workgroup/openbid_member_list.jsp"
  * @gpcsoft.view value="openProofGroupForProject"
  *  url="view/es/planform/workgroup/proof_member_list.jsp"
  * @gpcsoft.view value="evalBidGroupForProject"
  *  url="view/es/planform/workgroup/evalbid_member_list.jsp"
  * @gpcsoft.view value="obtainExpertForProject"
  *  url="view/es/planform/expertrule/obtain_Expert_List.jsp"
  * @gpcsoft.view value="toSaveBidMember"
  *  url="view/es/planform/workgroup/float/floatEmployeeGrid.jsp"
  * @gpcsoft.view value="toSaveProofMember"
  *  url="view/es/planform/workgroup/float/floatEmployeeGrid2.jsp"
  * @gpcsoft.view value="openBidWorkGroup"
  *  url="view/es/planform/workgroup/openBidInfo.jsp"
  * @gpcsoft.view value="toViewOpenBidWorkGroup"
  *  url="view/es/planform/workgroup/openBidInfoList.jsp"
  *  @gpcsoft.view value="toJudgeMemberList"
  *  url="view/es/planform/workgroup/judge_member_list.jsp"
  * @gpcsoft.view value="workgMemberFormView"
  *  url="view/es/planform/workgroup/workgMemberForm.jsp"
  * @gpcsoft.view value="leaderGroupMemberList"
  *  url="view/es/planform/workgroup/leaderGroup_member_list.jsp"
  * @gpcsoft.view value="toViewLeaderWorkGroup"
  *  url="view/es/planform/workgroup/leaderGroupViewMember.jsp"
  *  
  *  
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={WorkGroup.class,WorkgMember.class})
@RequestMapping("/WorkGroupController.do")//页面请求路径,可修改
@SuppressWarnings("unchecked")
public class WorkGroupController extends AnnotationMultiController<WorkGroup> {

	@Autowired(required=true) @Qualifier("workGroupServiceImpl")
	private WorkGroupService workGroupService;
	
	@Autowired(required=true) @Qualifier("projectServiceImpl")
	private ProjectService projectService;	
	
	@Autowired(required=true) @Qualifier("projectMTaskPlanServiceImpl")
	private ProjectMTaskPlanService projectMTaskPlanService;
	
	@Autowired(required=true) @Qualifier("projProcessRuleServiceImpl")
	private ProjProcessRuleService projProcessRuleService;
	
	@Autowired(required=true) @Qualifier("workgMemberServiceImpl")
	private WorkgMemberService workgMemberServiceImpl;
	
	/** 
	 * Description :  显示某项目所有标段的开标小组明细
	 * Create Date: 2010-6-25下午01:18:29 by wangcl  Modified Date: 2010-6-25下午01:18:29 by wangcl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toWorkGroupForProject")
	public ModelAndView toWorkGroupForProject(HttpServletRequest request,String projectId,String groupType)throws Exception {
		logger.debug("\nes WorkGroupController||toWorkGroupForProject\n");
		String viewName = "obtainExpertForProject";
		String fromTab = request.getParameter("fromTab");
		if(groupType.equals(WorkGroupTypeEnum.OPEN_BID)){
			viewName = "openBidGroupForProject";
		}
		List<WorkGroup> list=workGroupService.saveOrgetAllGroupMenber(projectId, groupType);
		Map model = new HashMap();	
		model.put("projectId", projectId);
		model.put("isDividePack", list.size()>0?true:false);
		model.put("fromTab", fromTab);
		model.put("workGroupList", list);
		return new ModelAndView(viewName, model);
	}
	
	/**
	 * 
	 * Description : 跳到新增开标小组成员页面
	 * Create Date: 2010-8-30下午02:07:36 by shenjianzhuang  Modified Date: 2010-8-30下午02:07:36 by shenjianzhuang
	 * @param request
	 * @return
	 * @Exception 
	 */
	@RequestMapping(params = "method=toSaveBidMember")
	public ModelAndView toSaveBidMember(HttpServletRequest request)throws Exception {
		logger.debug("\nes WorkGroupController||toSaveBidMember\n");
		Project project = projectService.get(request.getParameter("projectId"));
		ProjectMTaskPlan projectMTaskPlan =  projectMTaskPlanService.getProjectMTaskPlanByProjectId(request.getParameter("projectId")).get(0);
		Map model = new HashMap();	
		model.put("workGroupId",request.getParameter("workGroupId"));
		model.put("agencyId", project.getAgencies().getCompany().getObjId());
		if( project.getMonitor()==null){
			throw new EsException("请先指定监管人!");
		}
		model.put("monitorId", project.getMonitor().getCompany().getObjId());
		model.put("buyId", projectMTaskPlan.getBuyMainBody().getCompany().getObjId());
		return new ModelAndView("toSaveBidMember", model);
	}
	
	/**
	 * 
	 * Description : 跳到新增论证小组成员页面
	 * Create Date: 2010-8-30下午02:07:36 by shenjianzhuang  Modified Date: 2010-8-30下午02:07:36 by shenjianzhuang
	 * @param request
	 * @return
	 * @Exception 
	 */
	@RequestMapping(params = "method=toSaveProofMember")
	public ModelAndView toSaveProofMember(HttpServletRequest request){
		logger.debug("\nes WorkGroupController||toSaveProofMember\n");
		Project project = projectService.get(request.getParameter("projectId"));
		ProjectMTaskPlan projectMTaskPlan =  projectMTaskPlanService.getProjectMTaskPlanByProjectId(request.getParameter("projectId")).get(0);
		Map model = new HashMap();	
		model.put("workGroupId",request.getParameter("workGroupId"));
		model.put("agencyId", project.getAgencies().getCompany().getObjId());
		if( project.getMonitor()==null){
			model.put("failuer", true);
		}else{
			model.put("monitorId", project.getMonitor().getCompany().getObjId());
			model.put("buyId", projectMTaskPlan.getBuyMainBody().getCompany().getObjId());
		}
		return new ModelAndView("toSaveProofMember", model);
	}
	
	/** 
	 * Description :  显示某项目所有标段的论证小组明细
	 * Create Date: 2010-9-2上午10:46:01 by shenjianzhuang  Modified Date: 2010-9-2上午10:46:01 by shenjianzhuang
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toWorkGroupForProofProject")
	public ModelAndView toWorkGroupForProofProject(HttpServletRequest request)throws Exception {
		logger.debug("\nes WorkGroupController||toWorkGroupForProofProject\n");
		String fromTab = request.getParameter("fromTab");
		String projectId = request.getParameter("projectId");
		List<WorkGroup> list =workGroupService.saveOrGetProofGroupMenber(projectId);
		Map model = new HashMap();	
		model.put("projectId", projectId);
		model.put("isDividePack", list.size()>0?true:false);
		model.put("fromTab", fromTab);
		model.put("workGroupList", list);
		return new ModelAndView("openProofGroupForProject", model);
	}
	
	
	/** 
	 * FuncName : toViewOpenBidWorkGroup
	 * Description :  跳转到查看开标小组页面
	 * Create Date: 2011-11-15下午03:10:08 by yangx  
	 * Modified Date: 2011-11-15下午03:10:08 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toViewOpenBidWorkGroup")
	public ModelAndView toViewOpenBidWorkGroup(HttpServletRequest request)throws Exception {
		logger.debug("\nes WorkGroupController||toOpenBidWorkGroupForProject\n");
		String projectId = request.getParameter("projectId");
		String groupType = request.getParameter("groupType");
		Map model = new HashMap();	
		model.put("projProcessRule","FALSE");//是否分包开标标志：FALASE不分包;TRUE分包
		ProjProcessRule projProcessRule = projProcessRuleService.getProjProcessRuleByProjectIdAndCode(projectId,SysConfigEnum.SUBPROJECTOPENBID);//查询规则是否分包开标
		if (projProcessRule!=null&&"true".equals(projProcessRule.getResValue())) {//判断是否分包开标
			List<Project> subProjectList = projectService.getSubProjectByProjectId(projectId);//查询项目下的所有包租
			model.put("subProjectList", subProjectList);
			model.put("projProcessRule","TRUE");
		}
		WorkGroup workGroup = workGroupService.getWorkGroupByProjectIdAndType(projectId, WorkGroupTypeEnum.OPEN_BID);//查询是否有共组
		if (workGroup==null) {//若没有工作组,创建工作组
			workGroup = workGroupService.saveWorkGroupByProjectIdAndType(projectId, WorkGroupTypeEnum.OPEN_BID);
		}
		model.put("projectId", projectId);
		model.put("groupType", groupType);
		model.put("workGroup", workGroup);
		return new ModelAndView("toViewOpenBidWorkGroup", model);
	}
	
	/** 
	 * Description :  组建开标小组：跳转到开标小组页
	 * Create Date: 2010-10-22上午11:05:59 by yangx  Modified Date: 2010-10-22上午11:05:59 by yangx
	 * @param   request
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toOpenBidWorkGroupForProject")
	public ModelAndView toOpenBidWorkGroupForProject(HttpServletRequest request)throws Exception {
		logger.debug("\nes WorkGroupController||toOpenBidWorkGroupForProject\n");
		String projectId = request.getParameter("projectId");
		String groupType = request.getParameter("groupType");
		Map model = new HashMap();	
		model.put("projProcessRule","FALSE");//是否分包开标标志：FALASE不分包;TRUE分包
		ProjProcessRule projProcessRule = projProcessRuleService.getProjProcessRuleByProjectIdAndCode(projectId,SysConfigEnum.SUBPROJECTOPENBID);//查询规则是否分包开标
		if (projProcessRule!=null&&"true".equals(projProcessRule.getResValue())) {//判断是否分包开标
			List<Project> subProjectList = projectService.getSubProjectByProjectId(projectId);//查询项目下的所有包租
			model.put("subProjectList", subProjectList);
			model.put("projProcessRule","TRUE");
		}
		WorkGroup workGroup = workGroupService.getWorkGroupByProjectIdAndType(projectId, WorkGroupTypeEnum.OPEN_BID);//查询是否有共组
		if (workGroup==null) {//若没有工作组,创建工作组
			workGroup = workGroupService.saveWorkGroupByProjectIdAndType(projectId, WorkGroupTypeEnum.OPEN_BID);
		}
		model.put("projectId", projectId);
		model.put("groupType", groupType);
		model.put("workGroup", workGroup);
		return new ModelAndView("openBidWorkGroup", model);
	}
	
	/**
	 * FuncName: toWorkGroupForBidProject
	 * Description : 跳转到组建评标小组页面
	 * @param request
	 * @return
	 * @throws Exception ModelAndView
	 * @author: liuke
	 * @Create Date:2011-3-3  下午06:16:33
	 * @Modifier: liuke
	 * @Modified Date:2011-3-3  下午06:16:33
	 */
	@RequestMapping(params = "method=toWorkGroupForJudgeProject")
	public ModelAndView toWorkGroupForJudgeProject(HttpServletRequest request)throws Exception {
		logger.debug("\nes WorkGroupController||toWorkGroupForJudgeProject\n");
		String fromTab = request.getParameter("fromTab");
		String projectId = request.getParameter("projectId");
		List<WorkGroup> list =workGroupService.saveOrGetJudgeGroupMenber(projectId);
		Map model = new HashMap();	
		model.put("projectId", projectId);
		model.put("isDividePack", list.size()>0?true:false);
		model.put("fromTab", fromTab);
		model.put("workGroupList", list);
		return new ModelAndView("toJudgeMemberList", model);
	}
	
	
	/**
	 * FuncName: toSaveProofMember
	 * Description :   跳转到新增评标小组页面
	 * @param request
	 * @return
	 * @throws Exception ModelAndView
	 * @author: liuke
	 * @Create Date:2011-3-3  下午07:17:16
	 * @Modifier: liuke
	 * @Modified Date:2011-3-3  下午07:17:16
	 */
	@RequestMapping(params = "method=toSaveJudgeMember")
	public ModelAndView toSaveJudgeMember(HttpServletRequest request)throws Exception {
		logger.debug("\nes WorkGroupController||toSaveProofMember\n");
		Map model = new HashMap();	
		return new ModelAndView("workgMemberFormView", model);
	}
	
	
	/** 
	 * Description :  组建领导小组：跳转到组建领导小组列表页面
	 * @param   request
	 * @return  
	 * @Exception   
	 * @author: zhouzhanghe
	 * @Create Date:2011-9-26 11:27
	 */
	@RequestMapping(params = "method=toEditLeaderWorkGroup")
	public ModelAndView toEditLeaderWorkGroup(HttpServletRequest request)throws Exception {
		logger.debug("\nes WorkGroupController||toEditLeaderWorkGroup\n");
		String projectId = request.getParameter("projectId");
		Map model = new HashMap();	
 
		WorkGroup workGroup = workGroupService.getWorkGroupByProjectIdAndType(projectId, WorkGroupTypeEnum.WORKGROUP_TYPE_LEADINGGROUP);//查询是否存在该小组
		if (workGroup==null) {//若没有工作组,创建工作组
			workGroup = workGroupService.saveWorkGroupByProjectIdAndType(projectId, WorkGroupTypeEnum.WORKGROUP_TYPE_LEADINGGROUP);
		}
		
		//查询工作小组成员
		List<WorkgMember> workgMemberList = null;
		if(workGroup != null && workGroup.getObjId() != null)
			workgMemberList = workgMemberServiceImpl.getWorkgMemberByWorkGroupId(workGroup.getObjId());
		
		model.put("projectId", projectId);
		model.put("groupType", WorkGroupTypeEnum.WORKGROUP_TYPE_LEADINGGROUP);
		model.put("workGroup", workGroup);
		model.put("workgMemberList", workgMemberList);
		return new ModelAndView("leaderGroupMemberList", model);
	}
	
	
	/** 
	 * FuncName : toViewLeaderWorkGroup
	 * Description :  跳转到查看领导小组页面
	 * Create Date: 2011-11-15下午03:18:51 by yangx  
	 * Modified Date: 2011-11-15下午03:18:51 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toViewLeaderWorkGroup")
	public ModelAndView toViewLeaderWorkGroup(HttpServletRequest request)throws Exception {
		logger.debug("\nes WorkGroupController||toEditLeaderWorkGroup\n");
		String projectId = request.getParameter("projectId");
		Map model = new HashMap();	
		WorkGroup workGroup = workGroupService.getWorkGroupByProjectIdAndType(projectId, WorkGroupTypeEnum.WORKGROUP_TYPE_LEADINGGROUP);//查询是否存在该小组
		if (workGroup==null) {//若没有工作组,创建工作组
			workGroup = workGroupService.saveWorkGroupByProjectIdAndType(projectId, WorkGroupTypeEnum.WORKGROUP_TYPE_LEADINGGROUP);
		}
		//查询工作小组成员
		List<WorkgMember> workgMemberList = null;
		if(workGroup != null && workGroup.getObjId() != null)
			workgMemberList = workgMemberServiceImpl.getWorkgMemberByWorkGroupId(workGroup.getObjId());
		model.put("projectId", projectId);
		model.put("groupType", WorkGroupTypeEnum.WORKGROUP_TYPE_LEADINGGROUP);
		model.put("workGroup", workGroup);
		model.put("workgMemberList", workgMemberList);
		return new ModelAndView("toViewLeaderWorkGroup", model);
	}
}
