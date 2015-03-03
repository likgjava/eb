package com.gpcsoft.epp.project.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.core.Constants;
import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.tree.DHtmlTree;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.common.domain.EsExceptionEnum;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.domain.ProjectPlan;
import com.gpcsoft.epp.project.service.ProjectPlanService;
import com.gpcsoft.epp.project.service.ProjectService;
import com.gpcsoft.epp.worktask.service.WaitprocWorkTaskService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.Employee;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.common.utils.SysInfo;

/**
  * @gpcsoft.view value="projectPlanFormView"
  *  url="view/es/planform/projectplan/projectPlanForm.jsp"
  * @gpcsoft.view value="projectPlanTreeFormView"
  *  url="view/es/planform/projectplan/projectPlanTreeForm.jsp"
  * @gpcsoft.view value="projectPlanListView"
  *  url="view/es/planform/projectplan/projectPlanList.jsp"
  * @gpcsoft.view value="projectPlanDetailView"
  *  url="view/es/planform/projectplan/projectPlanDetail.jsp"
  * @gpcsoft.view value="selectSysconfigitemList"
  *  url="view/es/planform/projectplan/selectSysconfigitemList.jsp"
  * @gpcsoft.view value="lookMoreWaitprocWorkTaskProjectPlan"
  *  url="view/es/planform/projectplan/lookMoreWaitprocWorkTaskProjectPlanList.jsp"
  * @gpcsoft.view value="nextplanTaskPage"
  *  url=" view/es/common/nextplanTask.jsp"
  * @gpcsoft.view value="toPrintProjectPlan"
  *  url=" view/es/planform/projectplan/printProjectPlan.jsp"
  *  
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={ProjectPlan.class})
@RequestMapping("/ProjectPlanController.do")//页面请求路径,可修改
public class ProjectPlanController extends AnnotationMultiController<ProjectPlan> {

	@Autowired(required=true) @Qualifier("projectPlanServiceImpl")
	private ProjectPlanService projectPlanService;
	
	@Autowired(required=true) @Qualifier("projectServiceImpl")
	private ProjectService projectService;
	
	private WaitprocWorkTaskService waitprocWorkTaskService;
	
	public WaitprocWorkTaskService getWaitprocWorkTaskService() {
		if (null == waitprocWorkTaskService) {
			this.waitprocWorkTaskService = (WaitprocWorkTaskService)FrameBeanFactory.getBean("waitprocWorkTaskServiceImpl");
		}
		if (null == this.waitprocWorkTaskService) {
			throw new EsException(messageSource.getMessage(EsExceptionEnum.GET_WORK_TASK_SERVICE));
		}
		return this.waitprocWorkTaskService;
	}

	/** 
	 * FuncName:createProjectPlan
	 * Description :  创建项目计划
	 * @param   项目主键
	 * @return  ModelAndView
	 * @author liangxj
	 * @Create Date: 2010-6-26下午02:29:53 
	 * @Modifier   liangxj
	 * @Modified Date: 2010-6-26下午02:29:53    
	 */
	@RequestMapping(params = "method=createProjectPlan")
	public ModelAndView createProjectPlan(String projectId) throws Exception {
		List<ProjectPlan> list = projectPlanService.getPlanByProject(projectId);
		if(list == null || list.size() == 0) {//如果没有计划，则创建计划
			projectPlanService.createProjectPlan(projectId);
		}
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("projectId", projectId);
		return new ModelAndView("projectPlanListView", model);
	}
	
	/**
	 * FuncName:toUpdate
	 * Description :  跳转到修改表单页面
	 * @param   objId:项目计划主键
	 * @return  ModelAndView
	 * @author liangxj
	 * @Create Date: 2010-6-24下午07:00:02 
	 * @Modifier    liangxj
	 * @Modified Date: 2010-6-24下午07:00:02 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=toUpdate")
	public ModelAndView toUpdate(String objId)throws Exception {
		ProjectPlan projectPlan = projectPlanService.get(objId);
		if(projectPlan.getParent() == null){//没有上级
			ProjectPlan parent = new ProjectPlan();
			parent.setName("无上级节点");
			projectPlan.setParent(parent);
		}
		if(projectPlan.getPrePlan() == null){//没有前置任务
			projectPlan.setPrePlan(new ProjectPlan());
		}
		if(projectPlan.getBackPlan() == null){//没有back任务
			projectPlan.setBackPlan(new ProjectPlan());
		}
		if(projectPlan.getEmployee() == null){//没有执行人
			projectPlan.setEmployee(new Employee());
		}
		Map model = new HashMap();	
		model.put("projectPlan", projectPlan);
		if(null != projectPlan.getRule() && !"".equals(projectPlan.getRule())){ // 获取系统配置中文名称
			String sysConfigItemName = projectPlanService.getConfigItemNameByCode(projectPlan.getRule());
			model.put("sysConfigItemName",sysConfigItemName);
		}
		return new ModelAndView("projectPlanFormView", model);
	}
	
	/**
	 * FuncName:saveOrUpdate
	 * Description :保存
	 * @param   projectPlan:项目计划,status
	 * @return ModelAndView
	 * @author liangxj 
	 * @Create Date: 2010-6-24下午07:00:02 
	 * @Modifier   liangxj
	 * @Modified Date: 2010-6-24下午07:00:02  
	 */
	@RequestMapping(params = "method=saveOrUpdate")
	public ModelAndView saveOrUpdate(ProjectPlan projectPlan,SessionStatus status) throws Exception {
		projectPlanService.saveOrUpdate(projectPlan);
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/** 
	 * FuncName:getPlanTreeByProjectAndUser
	 * Description :  根据项目主键和当前登录用户，获得项目计划
	 * @param   projectId:项目主键,dHtmlTree:用以生成html页面的树对象
	 * @return ModelAndView
	 * @author liangxj 
	 * @Create Date: 2010-6-30下午04:11:15 
	 * @Modifier  liangxj
	 * @Modified Date: 2010-6-30下午04:11:15   
	 */
	@RequestMapping(params = "method=getPlanTreeByProjectAndUser")
	public ModelAndView getPlanTreeByProjectAndUser(String projectId, DHtmlTree dHtmlTree) throws Exception {
		dHtmlTree.setIsOpen("1");//全部展开
		dHtmlTree.setId("0");
		dHtmlTree.setTreeName("项目计划");
		String xmlTree= projectPlanService.getOwnerTreeByProjectId(dHtmlTree, projectId);
		Map<String, String> model=new HashMap<String, String>();//显示树结果数据
		model.put(Constants.STRING_RESULT, xmlTree);
    	return new ModelAndView(Constants.STRING_VIEW, model);
	}
	
	/** 
	 * FuncName:getStatusLightData
	 * Description :获取项目计划状态灯数据
	 * @param   projectId:项目主键
	 * @return  ModelAndView
	 * @author wangcl
	 * @Create Date: 2010-8-18下午05:03:24 
	 * @Modifier    wangcl
	 * @Modified Date: 2010-8-18下午05:03:24   
	 */
	@RequestMapping(params = "method=getStatusLightData")
	public ModelAndView getStatusLightData(String projectId) throws Exception {
		Map<String, Object> model=new HashMap<String, Object>();
		model.put(Constants.JSON_RESULT, projectPlanService.getStatusLightByProjectId(projectId));
		return new ModelAndView(Constants.JSON_VIEW, model);
	}

	/** 
	 * FuncName:getSecondStatusLightData
	 * Description :获取项目计划二级状态灯数据
	 * @param   projectId:项目主键
	 * @return  ModelAndView
	 * @author wangcl
	 * @Create Date: 2010-8-18下午05:03:24 
	 * @Modifier wangcl   
	 * @Modified Date: 2010-8-18下午05:03:24    
	 */
	@RequestMapping(params = "method=getSecondStatusLightData")
	public ModelAndView getSecondStatusLightData(String projectId) throws Exception {
		logger.debug("\nes=ProjectPlanController||getSecondStatusLightData\n");
		Map<String, Object> model=new HashMap<String, Object>();
		
		List list = projectPlanService.getProjectPlan(projectId);
		if(list == null || list.isEmpty()){//若没有工作计划，则创建工作计划
			projectPlanService.createProjectPlan(projectId);
		}
		model.put(Constants.JSON_RESULT, projectPlanService.getSecondStatusLightData(projectId));
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	
	/** 
	 * FuncName : getAllStatusLightData
	 * Description :  获取所有状态灯数据
	 * Create Date: 2011-11-16上午10:08:06 by yangx  
	 * Modified Date: 2011-11-16上午10:08:06 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=getAllStatusLightData")
	public ModelAndView getAllStatusLightData(String projectId) throws Exception{
		logger.debug("\nes=ProjectPlanController||getAllStatusLightData\n");
		Map<String, Object> model=new HashMap<String, Object>();
		model.put(Constants.JSON_RESULT, projectPlanService.getAllStatusLightData(projectId));
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
 	
	/** 
	 * FuncName:getAllSecondStatusLightData
	 * Description :  获取项目计划所有二级状态灯数据
	 * @param   projectId:项目主键
	 * @return  ModelAndView
	 * @author wangcl
	 * @Create Date: 2010-8-18下午05:03:24 
	 * @Modifier   wangcl 
	 * @Modified Date: 2010-8-18下午05:03:24    
	 */
	@RequestMapping(params = "method=getAllSecondStatusLightData")
	public ModelAndView getAllSecondStatusLightData(String projectId) throws Exception {
		logger.debug("\nes=ProjectPlanController||getAllSecondStatusLightData\n");
		Map<String, Object> model=new HashMap<String, Object>();
		model.put(Constants.JSON_RESULT, projectPlanService.getAllSecondStatusLightData(projectId));
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/**
	 * FuncName:getStairProjectPlan
	 * Description: 获取一级项目计划
	 * @param projectId:项目主键
	 * @return ModelAndView
	 * @author wanghz
	 * @Create Date 2010-9-1 上午09:45:02  
	 */
	@RequestMapping(params = "method=getStairProjectPlan")
	public ModelAndView getStairProjectPlan(String projectId)throws EsException {
		Map<String, Object> model=new HashMap<String, Object>();
		model.put(Constants.JSON_RESULT, projectPlanService.getStairProjectPlan(projectId));
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * FuncName:getSituationByProject
	 * Description : 根据项目id，获得该项目的任务情况
	 * @param   项目id
	 * @return  ModelAndView
	 * @author liangxj
	 * @Create Date: 2010-6-26下午02:29:53 
	 * @Modifier liangxj  
	 * @Modified Date: 2010-6-26下午02:29:53    
	 */
	@RequestMapping(params = "method=getSituationByProject")
	public ModelAndView getSituationByProject(String projectId) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("situation", projectPlanService.getSituationByProject(projectId));
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * FuncName: toInterceptPlan
	 * Description: 根据当前计划id,判断是否可以获取该节点信息,并返回请求路径,并同步开始时间和状态,否则返回false
	 * @param   objId:当前计划主键
	 * @return ModelAndView
	 * @author liangxj  
	 * @Create Date: 2010-7-1下午05:07:18 
	 * @Modifier  liangxj
	 * @Modified Date: 2010-7-1下午05:07:18     
	 */
	@RequestMapping(params = "method=toInterceptPlan")
	public ModelAndView toInterceptPlan(String objId) throws Exception {
		logger.debug("\nes=ProjectPlanController||toInterceptPlan\n");
		Map<String, Object> model = new HashMap<String, Object>();
		String url = projectPlanService.updateStrartAndGetPermitUrl(objId);
		if(url == null)//如果url为空，表示该节点不允许访问，否则返回url
			model.put("failure", true);
		else {
			model.put("url",url);
		}
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * FuncName:toFinishPlan
	 * Description :  根据当前计划主键，同步完成时间和状态
	 * @param   objId:项目计划主键
	 * @return  ModelAndView
	 * @author liangxj
	 * @Create Date: 2010-7-5上午09:23:16 
	 * @Modifier   liangxj
	 * @Modified Date: 2010-7-5上午09:23:16  
	 */
	@RequestMapping(params = "method=toFinishPlan")
	public ModelAndView toFinishPlan(String objId) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		if("true".equals(SysInfo.getInstance().getUseProjectPlan())){//开启项目计划设定
			projectPlanService.updateEndTime(objId);
		}
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/**
	 * @Description:根据资源ID获取角色IDS
	 * @param resourceId
	 * @return ModelAndView
	 * @throws Exception
	 * @Create Date 2010-8-12 上午09:52:38 By wanghz
	 */
	@RequestMapping(params = "method=getRoleIdsByResourceId")
	public ModelAndView getRoleIdsByResourceId(String resourceId)throws Exception {
		String roleIds = projectPlanService.getRoleIdsByResourceId(resourceId);
		Map<String, String> model = new HashMap<String, String>();
		model.put("roleIds", roleIds);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/**
	 * @Description: 根据角色ID获取所有员工ID
	 * @param roleId
	 * @return ModelAndView
	 * @throws Exception
	 * @Create Date 2010-8-12 下午02:50:43 By wanghz
	 */
	@RequestMapping(params = "method=getEmployeeIdsByRoleId")
	public ModelAndView getEmployeeIdsByRoleId(String roleId)throws Exception {
		String employeeIds = projectPlanService.getEmployeeIdsByRoleId(roleId);
		Map<String, String> model = new HashMap<String, String>();
		model.put("employeeIds", employeeIds);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/**
	 * @Description: 跳转到选择系统配置列表
	 * @return ModelAndView
	 * @throws Exception
	 * @Create Date 2010-8-12 下午02:51:45 By wanghz
	 */
	@RequestMapping(params = "method=toSelectSysconfigitemListView")
	public ModelAndView toSelectSysconfigitemListView()throws Exception {
		Map<String, String> model = new HashMap<String, String>();
		String sysConfigTypeId = messageSource.getMessage("sysConfigTypeId");
		model.put("sysConfigTypeId", sysConfigTypeId);
		return new ModelAndView("selectSysconfigitemList", model);
	}
	
	/**
	 * @Description: 获取待办项目计划
	 * @param bizId
	 * @param parentBizId
	 * @return ModelAndView
	 * @throws Exception
	 * @Create Date 2010-9-1 下午06:48:09 By wanghz
	 */
	@SuppressWarnings("unchecked") 
	@RequestMapping(params = "method=getWaitprocWorkTaskProjectPlan")
	public ModelAndView getWaitprocWorkTaskProjectPlan(String bizId,String parentBizId)throws EsException {
		logger.debug("\nes=ProjectPlanController||getWaitprocWorkTaskProjectPlan\n");
		Map model = new HashMap();
		User user = AuthenticationHelper.getCurrentUser();
		List<ProjectPlan> projectPlanList = projectPlanService.getWaitprocWorkTaskProjectPlan(bizId,parentBizId,user.getObjId());
		model.put("projectPlanList", projectPlanList);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/**
	 * @Description: 跳转到查看更多待办项目计划页面
	 * @param bizId
	 * @param parentBizId
	 * @return ModelAndView
	 * @author wanghz
	 * @Create Date 2010-9-14 上午10:54:09 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=toLookMoreWaitprocWorkTaskProjectPlanView")
	public ModelAndView toLookMoreWaitprocWorkTaskProjectPlanView(String bizId,String parentBizId){
		Map model = new HashMap();
		User user = AuthenticationHelper.getCurrentUser();
		List<ProjectPlan> projectPlanList = projectPlanService.getWaitprocWorkTaskProjectPlan(bizId,parentBizId,user.getObjId());
		model.put("projectPlanList", projectPlanList);
		return new ModelAndView("nextplanTaskPage", model);
	}
	/**
	 * FuncName: toPrintProjectPlan
	 * Description :  打印项目计划
	 * @param projectId
	 * @return ModelAndView
	 * @author: shenjz
	 * @Create Date:2011-4-8  下午02:10:47
	 * @Modifier: shenjz
	 * @Modified Date:2011-4-8  下午02:10:47
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=toPrintProjectPlan")
	public ModelAndView toPrintProjectPlan(String projectId){
		Map model = new HashMap();
		List<ProjectPlan> projectPlanList = projectPlanService.getProjectPlan(projectId);
		Project project = projectService.getProjectByObjId(projectId);
		model.put("project", project);
		model.put("projectPlanList", projectPlanList);
		return new ModelAndView("toPrintProjectPlan", model);
	}
}
