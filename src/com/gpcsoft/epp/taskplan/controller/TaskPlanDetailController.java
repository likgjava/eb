package com.gpcsoft.epp.taskplan.controller;

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

import com.gpcsoft.core.Constants;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.taskplan.domain.TaskPlanDetail;
import com.gpcsoft.epp.taskplan.service.TaskPlanDetailService;

/**
  * @gpcsoft.view value="taskPlanDetailFormView"
  *  url="view/es/planform/taskplan/taskPlanDetailForm.jsp"
  * @gpcsoft.view value="updateTaskPlanDetailFormView"
  *  url="view/es/planform/taskplan/updateTaskPlanDetailForm.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={TaskPlanDetail.class})
@RequestMapping("/TaskPlanDetailController.do")//页面请求路径,可修改
public class TaskPlanDetailController extends AnnotationMultiController<TaskPlanDetail> {

	@Autowired(required=true) @Qualifier("taskPlanDetailServiceImpl")
	private TaskPlanDetailService taskPlanDetailService;
    
	/**
	 * FuncName:toCreateTaskPlanDetail
	 * Description: 跳转到创建采购资金页面
	 * @param
	 * @return ModelAndView
	 * @author wanghz 
	 * @Create Date 2010-9-6 下午06:14:22 
	 */
	@RequestMapping(params = "method=toCreateTaskPlanDetail")
	public ModelAndView toCreateTaskPlanDetail()throws EsException {
		logger.debug("\nes=TaskPlanDetailController||toCreateTaskPlanDetail\n");
		return new ModelAndView("taskPlanDetailFormView");
	}

	/**
	 * FuncName:saveTaskPlanDetail
	 * Description : 创建保存采购资金明细
	 * @param taskPlanDetail:资金明细,taskPlanId:申报书主键,request,status  
	 * @return ModelAndView
	 * @author liuke
	 * @Create Date: 2010-5-21下午02:26:05  
	 * @Modifier: liuke
	 * @Modified Date: 2010-5-21下午02:26:05
	 */	
	@RequestMapping(params = "method=saveTaskPlanDetail")
	public ModelAndView saveTaskPlanDetail(TaskPlanDetail taskPlanDetail,String taskPlanId, HttpServletRequest request,SessionStatus status)throws Exception {
		logger.debug("\nes=TaskPlanDetailController||saveTaskPlanDetail\n");
		Map<String,Object> model = new HashMap<String,Object>();
		taskPlanDetailService.saveTaskPlanSet(taskPlanDetail, taskPlanId);
		status.setComplete();
		model.put("taskPlanDetail", taskPlanDetail);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/**
	 * FuncName:toUpdateTaskPlanDetail
	 * Description: 跳转到修改采购资金页面
	 * @param
	 * @return ModelAndView
	 * @author wanghz
	 * @Create Date 2010-9-6 下午06:14:22
	 * @Modifier shenjz
	 * @Modified Date: 2010-12-13下午02:26:05  
	 */
	@RequestMapping(params = "method=toUpdateTaskPlanDetail")
	public ModelAndView toUpdateTaskPlanDetail()throws EsException {
		logger.debug("\nes=TaskPlanDetailController||toUpdateTaskPlanDetail\n");
		return new ModelAndView("updateTaskPlanDetailFormView");
	}
	
	/**
	 * FuncName:updateTaskPlanDetail
	 * Description: 修改保存采购资金明细
	 * @param taskPlanDetail:资金明细,taskPlanId:申报书主键,request,status
	 * @return ModelAndView
	 * @author wanghz
	 * @Create Date 2010-9-6 下午06:18:46 
	 * @Modifier :shenjz
	 * @Modified Date: 2010-12-13下午02:26:05  
	 */
	@RequestMapping(params = "method=updateTaskPlanDetail")
	public ModelAndView updateTaskPlanDetail(TaskPlanDetail taskPlanDetail,String taskPlanId, HttpServletRequest request,SessionStatus status)throws Exception {
		logger.debug("\nes=TaskPlanDetailController||updateTaskPlanDetail\n");
		Map<String,Object> model = new HashMap<String,Object>();
		taskPlanDetailService.updateTaskPlanSet(taskPlanDetail);
		status.setComplete();
		model.put("taskPlanDetail", taskPlanDetail);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
}
