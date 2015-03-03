package com.gpcsoft.epp.taskplan.controller;

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

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.extra.json.FrameJsonView;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.taskplan.domain.TaskPlanMSub;
import com.gpcsoft.epp.taskplan.domain.TaskPlanSumEnum;
import com.gpcsoft.epp.taskplan.service.TaskPlanMSubService;
import com.gpcsoft.epp.taskplan.service.TaskPlanService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.User;

/**
 * @gpcsoft.view value="taskPlanMSubView"
 *  url="view/es/planform/taskplan/taskPlanMSubDetail.jsp"
 */

@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={TaskPlanMSub.class})
@RequestMapping("/TaskPlanMSubController.do")//页面请求路径,可修改
public class TaskPlanMSubController extends AnnotationMultiController<TaskPlanMSub> {

	@Autowired(required=true) @Qualifier("taskPlanMSubServiceImpl")
	private TaskPlanMSubService taskPlanMSubService;
	
	@Autowired(required=true) @Qualifier("taskPlanServiceImpl")
	private TaskPlanService taskPlanService;
	
	/** 
	 * FuncName:getSubNotSumSubsByOrg
	 * Description : 根据机构，获得其下级机构的未汇总的采购书条目 
	 * @param  orgId:机构主键,taskType:申报书类型
	 * @return ModelAndView
	 * @author liangxj
	 * @Create Date：2010-6-3 下午04:12:39 
	 * @Modifier liangxj
	 * @Modified Date:  2010-6-3 下午04:12:39  
	 */
	@RequestMapping(params = "method=getSubNotSumSubsByOrg")
	public ModelAndView getSubNotSumSubsByOrg(String orgId,String taskType,String ebuyMethod)throws Exception {
		logger.debug("\nTaskPlanMSubController||getSubNotSumSubsByOrg\n");
		List<TaskPlanMSub> taskPlanSubs = taskPlanMSubService.getSubNotSumSubsByOrg(orgId,TaskPlanSumEnum.SUMMARY, taskType,ebuyMethod);
		Map<String,Object> model = new HashMap<String,Object>();
		model.put(Page.DATA, taskPlanSubs);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * FuncName:sumMSubsByTaskPlan
	 * Description:根据TaskPlan的id，将其下的条目和明细汇总 
	 * @param taskPlanIds:申报书id,sumTaskPlanId:汇总的申报书id
	 * @return ModelAndView
	 * @author liangxj
	 * @Create Date：2010-6-3 下午04:12:39 
	 * @Modifier liangxj
	 * @Modified Date:  2010-6-3 下午04:12:39  
	 */
	@RequestMapping(params = "method=sumMSubsByTaskPlan")
	public ModelAndView sumMSubsByTaskPlan(String taskPlanIds,String sumTaskPlanId)throws Exception {
		logger.debug("\nTaskPlanMSubController||sumMSubsByTaskPlan\n");
		taskPlanMSubService.saveMSubsByTaskPlan(taskPlanIds,sumTaskPlanId,TaskPlanSumEnum.SUMMARY);
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/** 
	 * FuncName:removeSumMSubs
	 * Description : 根据申报书条目主键,删除所属的申报书所有汇总的条目,不级联删除申报书条目
	 * @param taskPlanSubIds:以逗号分隔的申报书条目主键  
	 * @return ModelAndView
	 * @author liangxj
	 * @Create Date：2010-6-3 下午04:12:39 
	 * @Modifier liangxj
	 * @Modified Date:  2010-6-3 下午04:12:39  
	 */
	@RequestMapping(params = "method=removeSumMSubs")
	public ModelAndView removeSumMSubs(String taskPlanSubIds)throws Exception {
		logger.debug("\nTaskPlanMSubController||removeSumMSubs\n");
		taskPlanMSubService.removeSumMSubs(taskPlanSubIds,TaskPlanSumEnum.SUMMARY);
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/** 
	 * FuncName:backSumMSubs
	 * Description:退回条目，根据申报书条目主键,删除所属的申报书所有汇总的条目,不级联删除申报书条目,并将任务书的状态改为临时
	 * @param taskPlanSubIds:以逗号分隔的申报书条目主键  
	 * @return ModelAndView
	 * @throws Exception
	 * @author liangxj
	 * @Create Date：2010-6-3 下午04:12:39    
	 * @Modifier liangxj
	 * @Modified Date:  2010-6-3 下午04:12:39 
	 */
	@RequestMapping(params = "method=backSumMSubs")
	public ModelAndView backSumMSubs(String taskPlanSubIds)throws Exception {
		logger.debug("\nTaskPlanMSubController||backSumMSubs\n");
		taskPlanMSubService.backSumMSubs(taskPlanSubIds,TaskPlanSumEnum.SUMMARY);
		return new ModelAndView(Constants.JSON_VIEW);
	}

	/** 
	 * FuncName:getAllTaskPlanSubListForCreateProj
	 * Description :  立项：跳转到申报书明细列表
	 * @param   request
	 * @return  ModelAndView
	 * @author yangx
	 * @Create Date: 2010-9-17上午10:49:22
	 * @Modifier  yangx
	 * @Modified Date: 2010-9-17上午10:49:22  
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=getAllTaskPlanSubListForCreateProj")
	public ModelAndView getAllTaskPlanSubListForCreateProj(HttpServletRequest request)throws Exception {
		logger.debug("\nes=TaskPlanMSubController||getAllTaskPlanSubListForCreateProj\n");
		String purCategoryId = request.getParameter("purchase.objId");//品目Id
		String ebuyMethod = request.getParameter("ebuyMethod");//采购方式
		String taskType = request.getParameter("taskType");//类型
		User user = AuthenticationHelper.getCurrentUser();
		OrgInfo orgInfo = (OrgInfo)user.getOrgInfo();
		Map<String,Object> model = new HashMap<String,Object>();
		//String usedTaskPlanSub = taskPlanService.getTaskPlanSubInProject();//在项目申报书中间表中存在的申报书条目	
		Page<TaskPlanMSub> page = prePage(request);
		//page = taskPlanMSubService.getAllTaskPlanMSubForCreateProj(page,orgInfo.getObjId(),usedTaskPlanSub,purCategoryId,ebuyMethod,taskType);
		page = taskPlanMSubService.getTaskPlanMSubForECPCreateProj(page,orgInfo.getObjId(),purCategoryId,ebuyMethod,taskType);//modify by yangx 修改查询条件
		super.endPage(model, page, request);
		model.put(FrameJsonView.INCLUDED_PROPERTIES, new String[]{"taskPlanSub.budgetName"});
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * FuncName:viewTaskPlanMSubByObjId
	 * Description:根据主键获取申报书中间表
	 * @param   request
	 * @return  ModelAndView
	 * @author yangx
	 * @Create Date: 2010-9-19上午10:58:09 
	 * @Modifier yangx
	 * @Modified Date: 2010-9-19上午10:58:09  
	 */
	@RequestMapping(params = "method=viewTaskPlanMSubByObjId")
	public ModelAndView viewTaskPlanMSubByObjId(HttpServletRequest request)throws Exception {
		logger.debug("\nes=TaskPlanMSubController||viewTaskPlanMSubByObjId\n");
		String TaskPlanMSubId = request.getParameter("TaskPlanMSubId");
		TaskPlanMSub taskPlanMSub = taskPlanMSubService.getTaskPlanMSubByObjId(TaskPlanMSubId);
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("taskPlanMSub",taskPlanMSub);
		return new ModelAndView("taskPlanMSubView", model);
	}
}
