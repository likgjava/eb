package com.gpcsoft.epp.worktask.controller;


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

import com.gpcsoft.core.Constants;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.worktask.domain.CompletedWorkTask;
import com.gpcsoft.epp.worktask.service.CompletedWorkTaskService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.User;

/**
  * @gpcsoft.view value="completedWorkTaskList"
  *  url="view/es/worktask/completedWorkTaskList.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={CompletedWorkTask.class})
@RequestMapping("/CompletedWorkTaskController.do")//页面请求路径,可修改
public class CompletedWorkTaskController extends AnnotationMultiController<CompletedWorkTask> {

	@Autowired(required=true) @Qualifier("completedWorkTaskServiceImpl")
	private CompletedWorkTaskService completedWorkTaskService;
	
	/** 
	 * Description :  根据父业务ID和任务ID查询对应的已操作过的记录
	 * Create Date: 2010-5-27下午04:24:55 by Administrator  Modified Date: 2010-5-27下午04:24:55 by Administrator
	 * @param request
	 * @param parentBizId
	 * @param taskId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=getCompletedWorkTaskByParentBizId")
	public ModelAndView getCompletedWorkTaskByParentBizId(HttpServletRequest request, String parentBizId, String taskId) throws Exception {
		User user = AuthenticationHelper.getCurrentUser();
		List<CompletedWorkTask> completedWorkTaskList = completedWorkTaskService.getCompletedWorkTaskByParentBizId(taskId,parentBizId,user);
		Map<String,Object> model=new HashMap<String,Object>();
		model.put("completedWorkTaskList", completedWorkTaskList);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  根据业务ID和任务类型查询对应的已操作过的记录
	 * Create Date: 2010-7-22上午10:42:34 by yangx  Modified Date: 2010-7-22上午10:42:34 by yangx
	 * @param request
	 * @param bizId
	 * @param taskType 
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=getCompletedWorkTaskByBizId")
	public ModelAndView getCompletedWorkTaskByBizId(HttpServletRequest request, String bizId, String taskType) throws Exception {
		logger.debug("\nes CompletedWorkTaskController||getCompletedWorkTaskByBizId\n");
		User user = AuthenticationHelper.getCurrentUser();
		List<CompletedWorkTask> completedWorkTaskList = completedWorkTaskService.getCompletedWorkTaskByBizId(taskType,bizId,user);
		Map model=new HashMap();
		model.put("completedWorkTaskList", completedWorkTaskList);
		return new ModelAndView("completedWorkTaskList", model);
	}
	
	/** 
	 * Description :  根据父业务ID和任务类型查询对应的已操作过的记录
	 * Create Date: 2010-7-22上午10:42:34 by liuke  Modified Date: 2010-7-22上午10:42:34 by liuke
	 * @param request
	 * @param bizId
	 * @param taskType 
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=getCompletedWorkTaskByParentBizIdAndType")
	public ModelAndView getCompletedWorkTaskByParentBizIdAndType(HttpServletRequest request, String parentBizId, String taskType) throws Exception {
		User user = AuthenticationHelper.getCurrentUser();
		List<CompletedWorkTask> completedWorkTaskList = completedWorkTaskService.getCompletedWorkTaskByParentBizIdAndType(taskType,parentBizId,user);
		Map model=new HashMap();
		model.put("completedWorkTaskList", completedWorkTaskList);
		return new ModelAndView("completedWorkTaskList", model);
	}

}
