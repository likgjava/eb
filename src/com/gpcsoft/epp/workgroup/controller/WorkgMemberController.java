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
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.utils.StringUtils;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.common.service.UserApiService;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.domain.ProjectMTaskPlan;
import com.gpcsoft.epp.project.domain.ProjectPlan;
import com.gpcsoft.epp.project.service.ProjectMTaskPlanService;
import com.gpcsoft.epp.project.service.ProjectPlanService;
import com.gpcsoft.epp.project.service.ProjectService;
import com.gpcsoft.epp.workgroup.domain.WorkGroup;
import com.gpcsoft.epp.workgroup.domain.WorkGroupTypeEnum;
import com.gpcsoft.epp.workgroup.domain.WorkMemberTypeEnum;
import com.gpcsoft.epp.workgroup.domain.WorkgMember;
import com.gpcsoft.epp.workgroup.service.WorkgMemberService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.auth.service.AttachmentService;
import com.gpcsoft.srplatform.common.utils.AttachmentUtil;

/**
  * @gpcsoft.view value="workgMemberFormView"
  *  url="view/es/planform/workgroup/workgMemberForm.jsp"
  * @gpcsoft.view value="workgMemberTreeFormView"
  *  url="view/es/planform/workgroup/workgMemberTreeForm.jsp"
  * @gpcsoft.view value="workgMemberListView"
  *  url="view/es/planform/workgroup/workgMemberList.jsp"
  * @gpcsoft.view value="workgMemberDetailView"
  *  url="view/es/planform/workgroup/workgMemberDetail.jsp"
  * @gpcsoft.view value="signinProjectListPage"
  *  url="view/es/planform/workgroup/signinProjectListPage.jsp"
  * @gpcsoft.view value="bidProjectListPage"
  *  url="view/es/planform/workgroup/bidProjectListPage.jsp"
  * @gpcsoft.view value="signinProjectListPageView"
  *  url="view/es/planform/workgroup/signinProjectListPageView.jsp" 
  * @gpcsoft.view value="signinSubProjectListPageView"
  *  url="view/es/planform/workgroup/signinSubProjectListPageView.jsp" 
  * @gpcsoft.view value="bidProjectListPageView"
  *  url="view/es/planform/workgroup/bidProjectListPageView.jsp"   
   * @gpcsoft.view value="bidSubProjectListPageView"
  *  url="view/es/planform/workgroup/bidSubProjectListPageView.jsp"   
  * @gpcsoft.view value="workgMemberList"
  *  url="view/es/planform/workgroup/openBidInfo_member_list.jsp"   
  * @gpcsoft.view value="toViewWorkgMemberByProjectIdAndGroupType"
  *  url="view/es/planform/workgroup/openBidInfoViewMemberList.jsp"   
  * @gpcsoft.view value="toAddWorkgMember"
  *  url="view/es/planform/workgroup/openBidInfo_member.jsp"   
  * @gpcsoft.view value="toUpdateWorkgMember"
  *  url="view/es/planform/workgroup/updateOpenBidInfo_member.jsp"   
  * @gpcsoft.view value="toViewWorkgMember"
  *  url="view/es/planform/workgroup/viewOpenBidInfo_member.jsp"   
  * @gpcsoft.view value="updateLeaderGroupMember"
  *  url="view/es/planform/workgroup/updateLeaderGroup_member.jsp"   
  * @gpcsoft.view value="workgMemberPrintList"
  *  url="view/es/planform/workgroup/printLeaderMember.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={WorkgMember.class})
@RequestMapping("/WorkgMemberController.do")//页面请求路径,可修改
public class WorkgMemberController extends AnnotationMultiController<WorkgMember> {

	@Autowired(required=true) @Qualifier("workgMemberServiceImpl")
	private WorkgMemberService workgMemberService;
	 
	@Autowired(required=true) @Qualifier("projectServiceImpl")
	private ProjectService projectService;	
	
	@Autowired(required=true) @Qualifier("projectMTaskPlanServiceImpl")
	private ProjectMTaskPlanService projectMTaskPlanService;
	
	@Autowired(required=true) @Qualifier("projectPlanServiceImpl")
	private ProjectPlanService projectPlanService;
	
	@Autowired(required = true)
	@Qualifier("attachmentServiceImpl")
	private AttachmentService attachmentService;
	
	@Autowired(required=true) @Qualifier("userApiServiceImpl")
	private UserApiService userApiService;
	
	/**
	 * 
	 * Description : 保存开标小组成员
	 * Create Date: 2010-9-14上午10:45:45 by shenjz  Modified Date: 2010-9-14上午10:45:45 by shenjz
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params = "method=saveOpenBidMember")
	public ModelAndView saveOpenBidMember(String [] empIds,String workGroupId,String projectId) throws Exception {
		logger.debug("\nWorkgMemberController||saveOpenBidMember\n");
		System.out.println("shenjz==");
		workgMemberService.saveBidMemberByEmp(empIds, workGroupId,projectId);
		Map<String, Object> model = new HashMap<String, Object>();
		return new ModelAndView(Constants.JSON_VIEW,model);//json输出; 
    } 
	
	/**
	 * 
	 * Description : 保存论证小组成员
	 * Create Date: 2010-9-14上午10:45:45 by shenjz  Modified Date: 2010-9-14上午10:45:45 by shenjz
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params = "method=saveProofMember")
	public ModelAndView saveProofMember(String [] empIds,String workGroupId,String projectId) throws Exception {
		logger.debug("\nWorkgMemberController||saveProofMember\n");
		workgMemberService.saveProofMemberByEmp(empIds, workGroupId,projectId);
		Map<String, Object> model = new HashMap<String, Object>();
		return new ModelAndView(Constants.JSON_VIEW,model);//json输出; 
	} 
	
	/**
	 * 
	 * Description : 跳转到评标项目列表页面
	 * Create Date: 2010-7-29上午10:45:45 by liuke  Modified Date: 2010-7-29上午10:45:45 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params = "method=toBidProjectList")
	public ModelAndView toBidProjectList(HttpServletRequest request) throws Exception {
		logger.debug("\nes=WorkgMemberController||toBidProjectList\n");
		User user = AuthenticationHelper.getCurrentUser();
		String userId = user.getObjId();
		List<Project> list = workgMemberService.getProjectListByUserId(userId);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("projectList", list);
		return new ModelAndView("bidProjectListPageView",model);
	} 
	
	
	/** 
     * Description :  删除开标小组成员
     * Create Date: 2010-9-7下午04:11:47 by wangcl  Modified Date: 2010-9-7下午04:11:47 by wangcl
     * @param   
     * @return  
     * @Exception   
     */
    @RequestMapping(params = "method=removeBidMember")
	public ModelAndView removeBidMember(String objId) throws Exception {
    	logger.debug("\nes=WorkgMemberController||removeBidMember\n");
    	workgMemberService.removeBidMember(objId);
        return new ModelAndView(Constants.JSON_VIEW);
    }
	
    /** 
     * Description :  删除论证小组成员
     * Create Date: 2010-9-7下午04:12:03 by wangcl  Modified Date: 2010-9-7下午04:12:03 by wangcl
     * @param   
     * @return  
     * @Exception   
     */
    @RequestMapping(params = "method=removeProofMember")
	public ModelAndView removeProofMember(String objId) throws Exception {
    	logger.debug("\nes=WorkgMemberController||removeProofMember\n");
    	workgMemberService.removeProofMember(objId);
        return new ModelAndView(Constants.JSON_VIEW);
    }

    
    /** 
     * FuncName : toViewWorkgMemberByProjectIdAndGroupType
     * Description :  跳转到查看开标小组页面
     * Create Date: 2011-11-15下午03:12:52 by yangx  
     * Modified Date: 2011-11-15下午03:12:52 by yangx
     * @param   
     * @return  
     * @Exception   
     */
    @RequestMapping(params = "method=toViewWorkgMemberByProjectIdAndGroupType")
    public ModelAndView toViewWorkgMemberByProjectIdAndGroupType(HttpServletRequest request) throws Exception {
    	logger.debug("\nes=WorkgMemberController||getWorkgMemberByProjectIdAndGroupType\n");
    	String projectId = request.getParameter("projectId");
    	String groupType = request.getParameter("groupType");
    	String workGroupId = request.getParameter("workGroupId");
    	//按照成员类型查询小组成员:采购人代表
    	List<WorkgMember> workgMemberBuyerList = workgMemberService.getWorkgMemberByProjectIdAndGroupType(projectId,groupType,WorkMemberTypeEnum.BUYER_REPRESENT);
    	//按照成员类型查询小组成员:代理机构代表
    	List<WorkgMember> workgMemberAgentList = workgMemberService.getWorkgMemberByProjectIdAndGroupType(projectId,groupType,WorkMemberTypeEnum.AGENT_REPRESENT);
    	//按照成员类型查询小组成员:监管机构代表
    	List<WorkgMember> workgMemberSuperviseList = workgMemberService.getWorkgMemberByProjectIdAndGroupType(projectId,groupType,WorkMemberTypeEnum.SUPERVISE_REPRESENT);
    	//按照成员类型查询小组成员:专家代表
    	List<WorkgMember> workgMemberExpertList = workgMemberService.getWorkgMemberByProjectIdAndGroupType(projectId,groupType,WorkMemberTypeEnum.EXPERT_REPRESENT);
    	Map<String,Object> model = new HashMap<String,Object>();
    	model.put("projectId",projectId);
    	model.put("groupType",groupType);
    	model.put("workGroupId",workGroupId);
    	model.put("workgMemberBuyerList",workgMemberBuyerList);
    	model.put("workgMemberExpertList", workgMemberExpertList);
    	model.put("workgMemberAgentList",workgMemberAgentList);
    	model.put("workgMemberSuperviseList",workgMemberSuperviseList);
    	return new ModelAndView("toViewWorkgMemberByProjectIdAndGroupType",model);
    }
    
    /** 
     * Description :  根据项目Id、小组类型查询开标人列表
     * Create Date: 2010-10-22上午11:15:46 by yangx  Modified Date: 2010-10-22上午11:15:46 by yangx
     * @param   request
     * @return  
     * @Exception   
     */
    @RequestMapping(params = "method=getWorkgMemberByProjectIdAndGroupType")
    public ModelAndView getWorkgMemberByProjectIdAndGroupType(HttpServletRequest request) throws Exception {
    	logger.debug("\nes=WorkgMemberController||getWorkgMemberByProjectIdAndGroupType\n");
    	String projectId = request.getParameter("projectId");
    	String groupType = request.getParameter("groupType");
    	String workGroupId = request.getParameter("workGroupId");
    	//按照成员类型查询小组成员:采购人代表
    	List<WorkgMember> workgMemberBuyerList = workgMemberService.getWorkgMemberByProjectIdAndGroupType(projectId,groupType,WorkMemberTypeEnum.BUYER_REPRESENT);
    	//按照成员类型查询小组成员:代理机构代表
    	List<WorkgMember> workgMemberAgentList = workgMemberService.getWorkgMemberByProjectIdAndGroupType(projectId,groupType,WorkMemberTypeEnum.AGENT_REPRESENT);
    	//按照成员类型查询小组成员:监管机构代表
    	List<WorkgMember> workgMemberSuperviseList = workgMemberService.getWorkgMemberByProjectIdAndGroupType(projectId,groupType,WorkMemberTypeEnum.SUPERVISE_REPRESENT);
     	//按照成员类型查询小组成员:专家代表
    	List<WorkgMember> workgMemberExpertList = workgMemberService.getWorkgMemberByProjectIdAndGroupType(projectId,groupType,WorkMemberTypeEnum.EXPERT_REPRESENT);
    	Map<String,Object> model = new HashMap<String,Object>();
    	model.put("projectId",projectId);
    	model.put("groupType",groupType);
    	model.put("workGroupId",workGroupId);
    	model.put("workgMemberBuyerList",workgMemberBuyerList);
    	model.put("workgMemberExpertList",workgMemberExpertList);
    	model.put("workgMemberAgentList",workgMemberAgentList);
    	model.put("workgMemberSuperviseList",workgMemberSuperviseList);
    	return new ModelAndView("workgMemberList",model);
    }
    
    /** 
     * Description :  保存开标人
     * Create Date: 2010-10-22下午01:40:12 by yangx  Modified Date: 2010-10-22下午01:40:12 by yangx
     * @param   workgMember：开标小组成员
     * @return  
     * @Exception   
     */
    @RequestMapping(params = "method=saveWorkgMember")
    public ModelAndView saveWorkgMember(HttpServletRequest request,WorkgMember workgMember,SessionStatus stauts) throws Exception {
    	logger.debug("\nes=WorkgMemberController||saveWorkgMember\n");
	    String projectId = request.getParameter("projectId");
	    String groupType = request.getParameter("groupType");
	    String workGroupId = request.getParameter("workGroupId");
	    String empId = request.getParameter("empId");
	    Boolean isUploadFile = new Boolean(request.getParameter("isUploadFile"));// 判断是否上传附件
	    if(isUploadFile){
	    	Object o1 = AttachmentUtil.uploadFile(request, "workgmCert");
			if (o1 instanceof GpcBaseObject) {
				Attachment attachment = (Attachment) o1;
				attachmentService.saveAttachment((Attachment) o1);
				workgMember.setWorkgmCert(attachment.getRelationId());
			}
	    }
	    String isLeader = request.getParameter("isLeader");
    	if(isLeader != null && !"".equals(isLeader) && !"undefined".equals(isLeader)){
    		workgMember.setWorkgmIsLeader(isLeader);
    	}
	    workgMemberService.saveWorkgMember(workgMember,projectId,groupType,workGroupId,empId);
	    stauts.setComplete();
	    return new ModelAndView(Constants.JSON_VIEW);
    }    

    /** 
     * Description :  跳转到新增开标人页面
     * Create Date: 2010-10-22下午03:41:15 by yangx  Modified Date: 2010-10-22下午03:41:15 by yangx
     * @param   workgMember：小组成员
     * @return  
     * @Exception   
     */
    @RequestMapping(params = "method=toAddWorkgMember")
    public ModelAndView toAddWorkgMember(HttpServletRequest request,WorkgMember workgMember,SessionStatus stauts) throws Exception {
    	logger.debug("\nes=WorkgMemberController||toAddWorkgMember\n");
		String projectId = request.getParameter("projectId");
		String groupType = request.getParameter("groupType");
    	String workgmType = request.getParameter("workgmType");
    	String workGroupId = request.getParameter("workGroupId");
    	String empIds = request.getParameter("empIds");
    	Map<String,Object> model = new HashMap<String,Object>();	
		if (workgmType!=null&&!"".equals(workgmType)&&WorkMemberTypeEnum.AGENT_REPRESENT.equals(workgmType)) {//代理机构
			Project project = projectService.get(projectId);
			if (project.getParentId()!=null&&!"".equals(project.getParentId())) {//判断是否为包组:如果为包组查询此包组对应得项目
				project = projectService.get(project.getParentId());
			}
			model.put("orgId", project.getAgencies().getCompany().getObjId());
		}else if (workgmType!=null&&!"".equals(workgmType)&&WorkMemberTypeEnum.BUYER_REPRESENT.equals(workgmType)) {//采购人
			Project project = projectService.get(projectId);
			if (project.getParentId()!=null&&!"".equals(project.getParentId())) {
				project = projectService.get(project.getParentId());
			}
			OrgInfo orginfo = userApiService.getOrgInfoById(project.getBuyersId());
			model.put("orgId", orginfo.getCompany().getObjId());//项目里冗余有招标人单位
		}else if (workgmType!=null&&!"".equals(workgmType)&&WorkMemberTypeEnum.SUPERVISE_REPRESENT.equals(workgmType)) {//监管人
			Project project = projectService.get(projectId);
			if (project.getParentId()!=null&&!"".equals(project.getParentId())) {
				project = projectService.get(project.getParentId());
			}
			if( project.getMonitor()==null){
				throw new EsException("请先指定监管人!");
			}
			model.put("orgId", project.getMonitor().getCompany().getObjId());
		}else if (workgmType!=null&&!"".equals(workgmType)&&WorkMemberTypeEnum.EXPERT_REPRESENT.equals(workgmType)) {//专家
//			model.put("orgId", "4028829033863a880133864441da0004");
		}
		model.put("projectId",projectId);
		model.put("groupType",groupType);
		model.put("workgmType",workgmType);
		model.put("workGroupId",workGroupId);
		model.put("empIds",empIds);
    	return new ModelAndView("toAddWorkgMember",model);
    }    

    /** 
     * Description :  跳转到修改开标人页面
     * Create Date: 2010-10-22下午07:12:59 by yangx  Modified Date: 2010-10-22下午07:12:59 by yangx
     * @param   
     * @return  
     * @Exception   
     */
    @RequestMapping(params = "method=toUpdateWorkgMember")
    public ModelAndView toUpdateWorkgMember(HttpServletRequest request) throws Exception {
    	logger.debug("\nes=WorkgMemberController||toUpdateWorkgMember\n");
    	String workgMemberId = request.getParameter("workgMemberId");
    	String workgmType = request.getParameter("workgmType");
    	String projectId = request.getParameter("projectId");
    	String empIds = request.getParameter("empIds");
    	WorkgMember workgMember = workgMemberService.getWorkgMemberByObjId(workgMemberId);
    	Map<String,Object> model = new HashMap<String,Object>();
    	if (workgmType!=null&&!"".equals(workgmType)&&WorkMemberTypeEnum.AGENT_REPRESENT.equals(workgmType)) {//代理机构
			Project project = projectService.get(projectId);
			if (project.getParentId()!=null&&!"".equals(project.getParentId())) {//判断是否为包组:如果为包组查询此包组对应得项目
				project = projectService.get(project.getParentId());
			}
			model.put("orgId", project.getAgencies().getCompany().getObjId());
		}else if (workgmType!=null&&!"".equals(workgmType)&&WorkMemberTypeEnum.BUYER_REPRESENT.equals(workgmType)) {//采购人
			Project project = projectService.get(projectId);
			if (project.getParentId()!=null&&!"".equals(project.getParentId())) {//判断是否为包组:如果为包组查询此包组对应得项目
				project = projectService.get(project.getParentId());
			}
			ProjectMTaskPlan projectMTaskPlan =  projectMTaskPlanService.getProjectMTaskPlanByProjectId(project.getObjId()).get(0);
			model.put("orgId", projectMTaskPlan.getBuyMainBody().getCompany().getObjId());
		}else if (workgmType!=null&&!"".equals(workgmType)&&WorkMemberTypeEnum.SUPERVISE_REPRESENT.equals(workgmType)) {//监管人
			Project project = projectService.get(projectId);
			if (project.getParentId()!=null&&!"".equals(project.getParentId())) {//判断是否为包组:如果为包组查询此包组对应得项目
				project = projectService.get(project.getParentId());
			}
			if( project.getMonitor()==null){
				throw new EsException("请先指定监管人!");
			}
			model.put("orgId", project.getMonitor().getCompany().getObjId());
		}
    	model.put("workgMember",workgMember);
    	model.put("empIds",empIds);
    	return new ModelAndView("toUpdateWorkgMember",model);
    }
  

    /** 
     * Description :  修改开标人
     * Create Date: 2010-10-22下午07:28:35 by yangx  Modified Date: 2010-10-22下午07:28:35 by yangx
     * @param   workgMember：小组成员
     * @return  
     * @Exception   
     */
    @RequestMapping(params = "method=updateWorkgMember")
    public ModelAndView updateWorkgMember(HttpServletRequest request,WorkgMember workgMember,SessionStatus stauts) throws Exception {
    	logger.debug("\nes=updateWorkgMember||toUpdateWorkgMember\n");
    	String empId = request.getParameter("empId");
    	String isLeader = request.getParameter("isLeader");
    	if(isLeader != null && !"".equals(isLeader) && !"undefined".equals(isLeader)){
    		workgMember.setWorkgmIsLeader(isLeader);
    	}
    	workgMemberService.updateWorkgMember(workgMember,empId);
    	stauts.setComplete();
	    return new ModelAndView(Constants.JSON_VIEW);
    }
   
    /** 
     * Description :  查看开标人信息
     * Create Date: 2010-10-26上午09:48:37 by yangx  Modified Date: 2010-10-26上午09:48:37 by yangx
     * @param   
     * @return  
     * @Exception   
     */
    @RequestMapping(params = "method=toViewWorkgMember")
    public ModelAndView toViewWorkgMember(HttpServletRequest request) throws Exception {
    	logger.debug("\nes=updateWorkgMember||toViewWorkgMember\n");
    	String workgMemberId = request.getParameter("workgMemberId");
    	WorkgMember workgMember = workgMemberService.getWorkgMemberByObjId(workgMemberId);
    	Map<String,Object> model = new HashMap<String,Object>();
    	model.put("workgMember",workgMember);
    	return new ModelAndView("toViewWorkgMember",model);
    }
    
    @RequestMapping(params = "method=saveJudgeMember")
    public ModelAndView saveJudgeMember(HttpServletRequest request,WorkgMember workgMember,SessionStatus stauts) throws Exception {
    	logger.debug("\nes=WorkgMemberController||saveJudgeMember\n");
	    String projectId = request.getParameter("projectId");
	    String groupType = request.getParameter("groupType");
	    String workGroupId = request.getParameter("workGroupId");
	    workgMemberService.saveJudgeMember(workgMember,projectId,groupType,workGroupId);
	    stauts.setComplete();
	    return new ModelAndView(Constants.JSON_VIEW);
    } 
    
    /**
     * FuncName: saveWorkgMemberDesion
     * Description:完成组建开标小组节点的方法
     * @param request
     * @return ModelAndView
     * @throws Exception ModelAndView
     * @author: shenjz
     * @Create Date:2011-4-1  上午10:42:39
     * @Modifier: shenjz
     * @Modified Date:2011-4-1  上午10:42:39
     */
    @RequestMapping(params = "method=saveWorkgMemberDesion")
    public ModelAndView saveWorkgMemberDesion(HttpServletRequest request) throws Exception {
    	logger.debug("\nes=WorkgMemberController||getWorkgMemberByProjectIdAndGroupType\n");
    	Map model = new HashMap();
    	String projectId = request.getParameter("projectId");
    	String projecPlanId = request.getParameter("projecPlanId");
    	ProjectPlan projectPlan = projectPlanService.get(projecPlanId);
    	WorkgMember workgMember = workgMemberService.saveWorkgMemberDesion(projectId);
    	return new ModelAndView(Constants.JSON_VIEW,model);
    }
    

    /** 
     * Description :  跳转到新增领导小组人员界面
     * @param  
     * @return  
     * @Exception  
     * @author: zhouzhanghe
     * @Create Date:2011-9-27 15:11 
     */
    @RequestMapping(params = "method=toUpdateLeaderGroupMember")
    public ModelAndView toUpdateLeaderGroupMember(HttpServletRequest request) throws Exception {
    	logger.debug("\nes=WorkgMemberController||toUpdateLeaderGroupMember\n");
		String projectId = request.getParameter("projectId");
		String groupType = request.getParameter("groupType");
    	String workgmType = request.getParameter("workgmType");
    	String workGroupId = request.getParameter("workGroupId");

    	
    	String workgMemberId = request.getParameter("workgMemberId");
    	WorkgMember workgMember = null;
    	if(workgMemberId == null || StringUtils.empty(workgMemberId)){
    		workgMember = new WorkgMember();
    		
    		Project subProject = new Project();
    		subProject.setObjId(projectId);
    		workgMember.setSubProject(subProject);
    		
    		WorkGroup workGroup = new WorkGroup();
    		workGroup.setObjId(workGroupId);
    		workgMember.setWorkGroup(workGroup);
    	}else{
    		workgMember = workgMemberService.get(workgMemberId);
    		if(workgMember == null)
    			throw new EsException("传入参数错误!");
    	}

    	Map<String,Object> model = new HashMap<String,Object>();
    	model.put("workGroupMemberTypeList", new WorkGroupTypeEnum().getLeadingWorkGroupMemberType());
    	model.put("workgMember",workgMember);
		model.put("projectId",projectId);
		model.put("groupType",groupType);
		model.put("workgmType",workgmType);
		model.put("workGroupId",workGroupId);
    	return new ModelAndView("updateLeaderGroupMember",model);
    } 
    
	/**
	 * 
	 * Description : 保存领导小组成员
	 * @param   
	 * @return  
	 * @Exception
     * @author: zhouzhanghe
     * @Create Date:2011-9-27 15:11 
	 */
	@RequestMapping(params = "method=saveLeaderGroupMember")
	public ModelAndView saveLeaderGroupMember(HttpServletRequest request,WorkgMember workgMember) throws Exception {
		logger.debug("\nWorkgMemberController||saveLeaderGroupMember\n");
		String projectId = request.getParameter("projectId");
		workgMemberService.saveLeaderWorkgMember(workgMember,projectId);
		return new ModelAndView(Constants.JSON_VIEW);//json输出; 
    } 
	
	/**
	 * 
	 * Description : 打印领导小组成员
	 * @param   
	 * @return  
	 * @Exception
     * @author: caojz
     * @Create Date:2011-10-8 15:11 
	 */
	@RequestMapping(params = "method=printLeaderGroupMember")
	public ModelAndView printLeaderGroupMember(HttpServletRequest request) throws Exception {
		logger.debug("\nWorkgMemberController||saveLeaderGroupMember\n");
		String projectId = request.getParameter("projectId");
    	String groupType = request.getParameter("groupType");
    	Project project = this.projectService.getProjectByObjId(projectId);
    	//按照成员类型查询小组成员:组长
    	List<WorkgMember> workgMemberLeaderList = workgMemberService.getWorkgMemberByProjectIdAndGroupType(projectId,groupType,WorkMemberTypeEnum.WORKMEMBER_TYPE_LEADER);
    	//按照成员类型查询小组成员:副组长
    	List<WorkgMember> workgMemberDeputyLeaderList = workgMemberService.getWorkgMemberByProjectIdAndGroupType(projectId,groupType,WorkMemberTypeEnum.WORKMEMBER_TYPE_DEPUTY_LEADER);
    	//按照成员类型查询小组成员:成员
    	List<WorkgMember> workgMemberWorkerList = workgMemberService.getWorkgMemberByProjectIdAndGroupType(projectId,groupType,WorkMemberTypeEnum.WORKMEMBER_TYPE_WORKER);
    	Map<String,Object> model = new HashMap<String,Object>();
    	model.put("projectId",projectId);
    	model.put("groupType",groupType);
    	model.put("project", project);
    	model.put("leaderList",workgMemberLeaderList);
    	model.put("deputyList",workgMemberDeputyLeaderList);
    	model.put("workerList",workgMemberWorkerList);
    	return new ModelAndView("workgMemberPrintList",model);
    } 
	
	/**
	 * FuncName: finishLeanderWorkgMember
	 * Description :  完成领导小组创建
	 * @param request
	 * @return
	 * @throws Exception ModelAndView
	 * @author: zhangsh
	 * @Create Date:2011-11-16  上午10:00:36
	 * @Modifier: zhangsh
	 * @Modified Date:2011-11-16  上午10:00:36
	*/	
	@RequestMapping(params="method=finishLeanderWorkgMember")
	public ModelAndView finishLeanderWorkgMember(HttpServletRequest request)throws Exception{
		String projectId = request.getParameter("projectId");
		workgMemberService.finishLeanderWorkgMember(projectId);
		return new ModelAndView(Constants.JSON_VIEW);
	}
}
