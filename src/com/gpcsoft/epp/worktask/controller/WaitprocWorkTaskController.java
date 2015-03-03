package com.gpcsoft.epp.worktask.controller;

import java.net.URLDecoder;
import java.util.HashMap;
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
import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.dao.hibernate.query.QueryWebUtils;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.worktask.domain.WaitprocWorkTask;
import com.gpcsoft.epp.worktask.service.WaitprocWorkTaskService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.plugin.wflow.domain.TaskModel;
import com.gpcsoft.srplatform.auth.domain.User;

/**
 * 
  *  Comments: <strong>WaitprocWorkTaskController</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   srplatform                    					          
  *  <br/>Module ID: 权限平台     		
  *  <br/>Create Date：2010-8-24 下午06:43:58 by liuy    					                            
  *  <br/>Modified Date:  2010-8-24 下午06:43:58 by liuy
  *  
  *  @gpcsoft.view value="waitProcWorkTaskPage"
  *  url="view/srplatform/wflow/myTaskListView.jsp"
 */

@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={WaitprocWorkTask.class})
@RequestMapping("/WaitprocWorkTaskController.do")//页面请求路径,可修改
public class WaitprocWorkTaskController extends AnnotationMultiController<WaitprocWorkTask> {

	@Autowired(required=true) @Qualifier("waitprocWorkTaskServiceImpl")
	private WaitprocWorkTaskService waitprocWorkTaskService;
	
	
	/** 
	 * 注入工作流业务逻辑构件
	*/
	private com.gpcsoft.plugin.wflow.manager.WorkFlowManager workFlowManager;
	
	
	private com.gpcsoft.plugin.wflow.manager.WorkFlowManager getWorkFlowManager(){//拿到JBPM的所有服务的入口
		if(null == this.workFlowManager){
			this.workFlowManager = (com.gpcsoft.plugin.wflow.manager.WorkFlowManager)FrameBeanFactory.getBean("workFlowWaitTaskImpl");
		}
		return this.workFlowManager;
	}
	
	/** 
	 * Description :  根据任务ID拿到任务信息
	 * Create Date: 2010-3-16下午03:13:30 by liuyang  Modified Date: 2010-3-16下午03:13:30 by liuyang
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=getTaskModel")
	public ModelAndView getTaskModel(HttpServletRequest request, String workFlowTaskId) throws Exception {
		TaskModel taskModel = waitprocWorkTaskService.getTaskModelById(workFlowTaskId);
		Map model=new HashMap();
		model.put("taskModel", taskModel);
		ModelAndView mv=new ModelAndView(Constants.JSON_VIEW, model);
		return mv;
	}
	
	
	/** 
	 * Description :  查询待办任务列表（不通过控件生成，所以不需要分页）
	 * Create Date: 2010-8-24下午04:57:21 by liuy  Modified Date: 2010-8-24下午04:57:21 by liuy
	 * @param taskType 待办任务分类CODE
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params="method=toWaitProcWorkTaskListPage")
	public ModelAndView toWaitProcWorkTaskListPage(HttpServletRequest request)throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		String cols = "name,worktaskType";
		Map<String,Object> map = request.getParameterMap();
		QueryObject queryObject = QueryWebUtils.getQuery(request, TaskModel.class);
		for(String key:cols.split(",")){
			for(Map.Entry entry:map.entrySet()){
				if(key.equals(entry.getKey())){
					model.put((String)entry.getKey(), request.getParameter((String)entry.getKey()));
					queryObject.getQueryParam().and(new QueryParam((String)entry.getKey(),QueryParam.OPERATOR_EQ,request.getParameter((String)entry.getKey())));
				}
			}
		}
		
		String pageNum=request.getParameter(Page.PAGE_NAME);
		String rp=request.getParameter(Page.PAGE_SIZE_NAME);
		Page page=new Page();
		if(null != pageNum && "0".equals(pageNum)){
			pageNum = "1";
		}
		int curpage = Integer.parseInt(pageNum == null ? "1":pageNum);//得到当前页数   
		int limit = Integer.parseInt(rp == null ? "5":rp);//得到当前页大小  
		int start = (curpage==1?0:Page.getStartOfPage(curpage, limit));
		page.setStart(start);
		page.setPageSize(limit);
		page.setStartRowNum(start);
		User user = AuthenticationHelper.getCurrentUser();
		page = this.getWorkFlowManager().getTasksByCurUser(user, page, queryObject);
		super.endPage(model, page, request);
		
		//设置分页对应的请求地址
		model.put("pageSearchUrl", "WaitprocWorkTaskController.do?method=toWaitProcWorkTaskListPage");
		model.put("PAGERESULT", page);
		return new ModelAndView("waitProcWorkTaskPage", model);
	}

	/**
	 * @Description: 获取待办任务
	 * @param paramtypeCode 任务类别
	 * @param bizId 业务ID
	 * @param parentBizId 顶级业务ID
	 * @return WaitprocWorkTask
	 * @throws Exception
	 * @Create Date 2010-8-26 上午09:52:30 By wanghz
	 */
	@RequestMapping(params="method=getWaitprocWorkTask")
	public ModelAndView getWaitprocWorkTask(String paramtypeCode,String bizId,String parentBizId)throws EsException {
		System.out.println("shenjz==");
		WaitprocWorkTask workTask = waitprocWorkTaskService.getWaitprocWorkTask(paramtypeCode, bizId, parentBizId);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("workTask", workTask);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
}
