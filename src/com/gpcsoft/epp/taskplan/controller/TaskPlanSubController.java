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
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.core.Constants;
import com.gpcsoft.core.utils.JsonUtils;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.requirement.domain.PreqEntry;
import com.gpcsoft.epp.taskplan.domain.TaskPlanMSub;
import com.gpcsoft.epp.taskplan.domain.TaskPlanSub;
import com.gpcsoft.epp.taskplan.service.TaskPlanMSubService;
import com.gpcsoft.epp.taskplan.service.TaskPlanSubService;

/**
  * @gpcsoft.view value="taskPlanSubFormView"
  *  url="view/es/planform/taskplan/taskPlanSubForm.jsp"
  * @gpcsoft.view value="updateTaskPlanSubFormView"
  *  url="view/es/planform/taskplan/updateTaskPlanSubForm.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={TaskPlanSub.class,TaskPlanMSub.class})
@RequestMapping("/TaskPlanSubController.do")//页面请求路径,可修改
public class TaskPlanSubController extends AnnotationMultiController<TaskPlanSub> {

	@Autowired(required=true) @Qualifier("taskPlanSubServiceImpl")
	private TaskPlanSubService taskPlanSubService;
	
	@Autowired(required=true) @Qualifier("taskPlanMSubServiceImpl")
	private TaskPlanMSubService taskPlanMSubService;
	
	/**
	 * FuncName:toCreateTaskPlanSubFormView
	 * Description: 跳转到创建申报书明细页面
	 * @param  request
	 * @return ModelAndView
	 * @author wanghz
	 * @Create Date 2010-9-6 下午05:25:19 
	 * @Modifier shenjz
	 * @Modified Date 2010-12-13 上午11:25:19  
	 */
	@RequestMapping(params = "method=toCreateTaskPlanSubFormView")
	public ModelAndView toCreateTaskPlanSubFormView(HttpServletRequest request)throws Exception {
		logger.debug("\nTaskPlanSubController||toCreateTaskPlanSubFormView\n");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("type", request.getParameter("type"));
		return new ModelAndView("taskPlanSubFormView",map);
	}
	
	/**
	 * FuncName:createTaskPlanSub
	 * Description: 创建保存申报书明细
	 * @param request,taskPlanSubString:申报书明细,preqEntryString:品目明细,taskPlanId:申报书主键,status
	 * @return ModelAndView
	 * @author wanghz
	 * @Create Date 2010-9-6 下午05:28:48   
	 * @Modifier wanghz
	 * @Modified Date 2010-12-13 上午11:25:19 
	 */
	@RequestMapping(params = "method=createTaskPlanSub")
	public ModelAndView createTaskPlanSub(HttpServletRequest request, String taskPlanSubString, String preqEntryString, String taskPlanId, SessionStatus status) throws EsException {
		logger.debug("\nTaskPlanSubController||createTaskPlanSub\n");
		TaskPlanSub taskPlanSub = JsonUtils.json2Bean(taskPlanSubString, TaskPlanSub.class);
		PreqEntry preqEntry = JsonUtils.json2Bean(preqEntryString, PreqEntry.class);
		taskPlanSub.setObjId("".equals(taskPlanSub.getObjId())?null:taskPlanSub.getObjId() );//由于页面转换问题，objId为""的需要转为NULL
		preqEntry.setObjId("".equals(preqEntry.getObjId())?null:preqEntry.getObjId());//由于页面转换问题，objId为""的需要转为NULL
		taskPlanSubService.saveTaskPlanSubAndPreqEntry(taskPlanSub, preqEntry, taskPlanId);	//将需求条目的补充文件存到系统
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/**
	 * FuncName:toUpdateTaskPlanSubFormView
	 * Description: 跳转到修改申报书明细页面
	 * @param request,totalPrice:总资金
	 * @return ModelAndView
	 * @author wanghz
	 * @Create Date 2010-9-6 下午05:25:19 
	 * @Modifier shenjz
	 * @Modified Date 2010-12-13 上午11:25:19 By shnejz
	 */
	@RequestMapping(params = "method=toUpdateTaskPlanSubFormView")
	public ModelAndView toUpdateTaskPlanSubFormView(HttpServletRequest request,String totalPrice)throws Exception {
		logger.debug("\nTaskPlanSubController||toUpdateTaskPlanSubFormView\n");
		return new ModelAndView("updateTaskPlanSubFormView");
	}

	/** 
	 * FuncName:saveTaskPlanSub
	 * Description:修改保存采购申报书明细（保存的时候进行中间表关联操作）
	 * @param request,taskPlanSubString:申报书明细,preqEntryString:品目明细,taskPlanId:申报书主键,status
	 * @return ModelAndView
	 * @author guom
	 * @Create Date:2010-6-3 下午04:12:39      
	 * @Modifier:guom
	 * @Modified Date:2010-6-3 下午04:12:39  
	 */
	@RequestMapping(params = "method=saveTaskPlanSub")
	public ModelAndView saveTaskPlanSub(HttpServletRequest request, String taskPlanSubString, String preqEntryString, String taskPlanId, SessionStatus status) throws EsException {
		logger.debug("\nTaskPlanSubController||saveTaskPlanSub\n");
		TaskPlanSub taskPlanSub = JsonUtils.json2Bean(taskPlanSubString, TaskPlanSub.class);
		PreqEntry preqEntry = JsonUtils.json2Bean(preqEntryString, PreqEntry.class);
		taskPlanSub.setObjId("".equals(taskPlanSub.getObjId())?null:taskPlanSub.getObjId() );//由于页面转换问题，objId为""的需要转为NULL
		preqEntry.setObjId("".equals(preqEntry.getObjId())?null:preqEntry.getObjId());//由于页面转换问题，objId为""的需要转为NULL
		taskPlanSubService.saveTaskPlanSubAndPreqEntry(taskPlanSub, preqEntry, taskPlanId);	//将需求条目的补充文件存到系统
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/**
	 * FuncName: removeTaskPlanSubAndPreqBySubId
	 * Description : 根据申报书条目ID删除申报书条目和对应的需求条目信息
	 * @param request,taskPlanSubId:申报书条目主键,status
	 * @return ModelAndView
	 * @author guom
	 * @Create Date：2010-6-3 下午04:12:39 
	 * @Modifier  guom
	 * @Modified Date:  2010-6-3 下午04:12:39  
	 */
	@RequestMapping(params = "method=removeTaskPlanSubAndPreqBySubId")
	public ModelAndView removeTaskPlanSubAndPreqBySubId(HttpServletRequest request, String taskPlanSubId,SessionStatus status) throws Exception {
		logger.debug("\nTaskPlanSubController||removeTaskPlanSubAndPreqBySubId\n");
		taskPlanSubService.removeTaskPlanSubAndPreqBySubId(taskPlanSubId);	
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/**
	 * FuncName:removeTaskPlanSub
	 * Description: 根据ID删除申报书明细，同时删除关联申报书中间表
	 * @param objId:申报书条目主键,status
	 * @return ModelAndView
	 * @author wanghz
	 * @Create Date 2010-7-26 下午05:40:28  
	 */
	@RequestMapping(params = "method=removeTaskPlanSub")
	public ModelAndView removeTaskPlanSub(String objId,SessionStatus status)throws Exception {
		logger.debug("\nTaskPlanSubController||removeTaskPlanSub\n");
		taskPlanSubService.removeTaskPlanSub(objId);
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/** 
	 * FuncName:getTaskPlanSubByProjectId
	 * Description : 通过项目获得申报书条目信息 
	 * @param   request
	 * @return  ModelAndView
	 * @author yangx
	 * @Create Date: 2010-9-2下午02:53:42    
	 * @Modifier:yangx
	 * @Modified Date: 2010-9-2下午02:53:42  
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=getTaskPlanSubByProjectId")
	public ModelAndView getTaskPlanSubByProjectId(HttpServletRequest request)throws Exception {
		logger.debug("\nTaskPlanSubController||getTaskPlanSubByProjectId\n");
		String taskPlanSubIds = request.getParameter("taskPlanSubIds");
		Map<String,Object> model = new HashMap<String,Object>();
		Page<TaskPlanSub> page = prePage(request);
		Page<TaskPlanSub> pageData = null;
		String taskPlanSubIds_="";
		if (taskPlanSubIds!=null&&!"".equals(taskPlanSubIds)) {
			for (String taskPlanSubId:taskPlanSubIds.split(",")) {
				taskPlanSubIds_ +="'"+taskPlanSubId+"',";
			}
			taskPlanSubIds_ = taskPlanSubIds_.substring(0,taskPlanSubIds_.lastIndexOf(","));
		}else {
			taskPlanSubIds_ = "'-1'";
		}
		pageData = taskPlanSubService.getTaskPlanSubByTaskPlanSubIds(page,taskPlanSubIds_);
		endPage(model, pageData, request);
		return new ModelAndView(Constants.JSON_VIEW,model);
	}

	/**
	 * FuncName:getLowerLeverTaskPlanSubByTaskPlan
	 * Description:  获取下级申报书汇总明细
	 * @param taskPlanId:申报书主键
	 * @return ModelAndView
	 * @author wanghz
	 * @Create Date 2010-9-15 下午06:31:58  
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=getLowerLeverTaskPlanSubByTaskPlan")
	public ModelAndView getLowerLeverTaskPlanSubByTaskPlan(String taskPlanId) {
		logger.debug("\nTaskPlanSubController||getLowerLeverTaskPlanSubByTaskPlan\n");
		List<TaskPlanSub> taskPlanSubList = taskPlanSubService.getLowerLeverTaskPlanSubByTaskPlan(taskPlanId);
		StringBuffer ids = new StringBuffer();
		for (TaskPlanSub taskPlanSub:taskPlanSubList) {
			if (!"".equals(ids.toString())) {
				ids.append(",");
			}
			ids.append(taskPlanSub.getObjId());
		}
		Map model = new HashMap();
		model.put("taskPlanSubIds", ids.toString());
		model.put("taskPlanSubList", taskPlanSubList);
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	/** 
	 * FuncName:getTaskPlanSubByProjectId
	 * Description : 根据任务书获得申报书条目IDS
	 * @param   request
	 * @return  ModelAndView
	 * @author yangx
	 * @Create Date: 2010-9-2下午02:53:42    
	 * @Modifier:yangx
	 * @Modified Date: 2010-9-2下午02:53:42  
	 */
	@RequestMapping(params = "method=getTaskPlanSubIdsByTaskPlanId")
	public ModelAndView getTaskPlanSubIdsByTaskPlanId(HttpServletRequest request,String taskPlanId)throws Exception {
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("taskplansubIds",taskPlanMSubService.getTaskPlanSubIdsByTaskPlanId(taskPlanId));
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
}
