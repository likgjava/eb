package com.gpcsoft.epp.project.controller;

import java.util.HashMap;
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
import com.gpcsoft.core.extra.json.FrameJsonView;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.common.domain.EsExceptionEnum;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.domain.ProjectMTaskPlan;
import com.gpcsoft.epp.project.service.ProjectMTaskPlanService;
import com.gpcsoft.epp.taskplan.service.TaskPlanMSubService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.User;
/**
  * @gpcsoft.view value="projectMTaskPlanFormView"
  *  url="view/es/planform/project/projectMTaskPlanForm.jsp"
  * @gpcsoft.view value="projectMTaskPlanTreeFormView"
  *  url="view/es/planform/project/projectMTaskPlanTreeForm.jsp"
  * @gpcsoft.view value="projectMTaskPlanListView"
  *  url="view/es/planform/project/projectMTaskPlanList.jsp"
  * @gpcsoft.view value="projectMTaskPlanDetailView"
  *  url="view/es/planform/project/projectMTaskPlanDetail.jsp" 
  *  @gpcsoft.view value="purRequirementListPageView"
  *  url="view/es/planform/project/purRequirementListPage.jsp"
  *  
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={ProjectMTaskPlan.class,Project.class})
@RequestMapping("/ProjectMTaskPlanController.do")//页面请求路径,可修改
public class ProjectMTaskPlanController extends AnnotationMultiController<ProjectMTaskPlan> {

	@Autowired(required=true) @Qualifier("projectMTaskPlanServiceImpl")
	private ProjectMTaskPlanService projectMTaskPlanService;

	@Autowired(required=true) @Qualifier("taskPlanMSubServiceImpl")
	private TaskPlanMSubService taskPlanMSubService;
	
	/** 
	 * FuncName:getAllTaskPlanSubListForCreatedProj
	 * Description :  立项：跳转到申报书明细列表
	 * @param   request
	 * @return  ModelAndView
	 * @author    yangx
	 * @Create Date: 2010-9-17上午10:49:22 
	 * @Modifier   yangx
	 * @Modified Date: 2010-9-17上午10:49:22    
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=getAllTaskPlanSubListForCreatedProj")
	public ModelAndView getAllTaskPlanSubListForCreatedProj(HttpServletRequest request)throws Exception {
		logger.debug("\nes=TaskPlanSearchController||getAllTaskPlanSubListForCreatedProj\n");
		String purchaseName = request.getParameter("purchaseName");
		String ebuyMethod = request.getParameter("ebuyMethod");
		User user = AuthenticationHelper.getCurrentUser();
		OrgInfo orgInfo = (OrgInfo)user.getOrgInfo();
		Map<String,Object> model = new HashMap<String,Object>();
		Page<ProjectMTaskPlan> page = prePage(request);
		page = taskPlanMSubService.getAllTaskPlanMSubForCreatedProj(page,orgInfo.getObjId(),purchaseName,ebuyMethod);
		super.endPage(model, page, request);
		String[] include = {"taskPlanSub"}; 
		model.put(FrameJsonView.INCLUDED_PROPERTIES, include);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * FuncName:modifyProjectByTaskPlanSubs
	 * Description :  修改项目
	 * @param   project:项目,taskPlanSubIds:申报书条目主键,request,status
	 * @return  ModelAndView
	 * @author yangx
	 * @Create Date: 2010-9-2下午04:41:17    
	 * @Modifier yangx
	 * @Modified Date: 2010-9-2下午04:41:17   
	 */
	@RequestMapping(params = "method=modifyProjectByTaskPlanSubs")
	public ModelAndView modifyProjectByTaskPlanSubs(Project project,String taskPlanSubIds, HttpServletRequest request, SessionStatus status)throws Exception {
		logger.debug("\nes ProjectMTaskPlanController||modifyProjectByTaskPlanSubs\n");
		taskPlanSubIds = taskPlanSubIds.replace("-1,", "");//剔除掉页面中的“-1”
		taskPlanSubIds = taskPlanSubIds.replace("-1", "");
		if(taskPlanSubIds.length() <= 1){//如果没接收到申报书条目，需要提示
			throw new EsException(messageSource.getMessage(EsExceptionEnum.TASKPLANSUB_ISSELECT));
		}
		projectMTaskPlanService.updateProjectByTaskPlanSubs(project, taskPlanSubIds);
		status.setComplete();
		Map model = new HashMap();
		model.put("projectId", project.getObjId());
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
}
