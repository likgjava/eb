package com.gpcsoft.epp.resproject.controller;
/**
 * 项目管理业务逻辑控制器(在中化项目中，此业务功能代替了执行平台申报书的功能)
 * @author Shengn
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.service.EnumService;
import com.gpcsoft.core.service.impl.EnumServiceImpl;
import com.gpcsoft.core.utils.JsonUtils;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.common.domain.AuditStatusEnum;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.project.domain.MessageCode;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.service.ProjectPlanService;
import com.gpcsoft.epp.project.service.ProjectService;
import com.gpcsoft.epp.resproject.domain.ResProject;
import com.gpcsoft.epp.resproject.domain.ResProjectItem;
import com.gpcsoft.epp.resproject.domain.ResProjectItemEnum;
import com.gpcsoft.epp.resproject.service.ResProjectService;
import com.gpcsoft.epp.taskplan.domain.TaskPlanSub;
import com.gpcsoft.epp.taskplan.service.TaskPlanService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.Company;
import com.gpcsoft.srplatform.auth.domain.Employee;
import com.gpcsoft.srplatform.auth.domain.User;

/**
 * @gpcsoft.view value="loadResProjectManagePage"
 * url="view/es/planform/resproject/loadResProjectManagePage.jsp"
 * @gpcsoft.view value="loadResProjectInfo"
 * url="view/es/planform/resproject/loadResProjectInfo.jsp"
 * @gpcsoft.view value="lookResProjectInfo"
 * url="view/es/planform/resproject/lookResProjectInfo.jsp"
 * @gpcsoft.view value="loadDisassemblePage"
 * url="view/es/planform/resproject/loadDisassemblePage.jsp"
 * @gpcsoft.view value="loadCreateTenderProjectPage"
 * url="view/es/planform/resproject/loadCreateTenderProjectPage.jsp"
 * @gpcsoft.view value="resProjectInfoForm"
 * url="view/es/planform/resproject/resProjectInfoForm.jsp"
 * @gpcsoft.view value="setResProjectInfoItem"
 * url="view/es/planform/resproject/setResProjectInfoItem.jsp"
 * @gpcsoft.view value="resProjectInfoDetail"
 * url="view/es/planform/resproject/resProjectInfoDetail.jsp"
 * @gpcsoft.view value="resProjectInfoDetailForAudit"
 * url="view/es/planform/resproject/resProjectInfoForAudit.jsp"
 * 
 * 
 */
@Controller 
@Scope("request")
@SessionAttributes(types={ResProjectItem.class,ResProject.class})
@RequestMapping("/ResProjectController.do")
public class ResProjectController extends AnnotationMultiController<ResProject> {

	@Autowired(required=true) @Qualifier("resProjectServiceImpl")
	private ResProjectService resProjectService;
	
	@Autowired(required=true) @Qualifier("projectPlanServiceImpl")
	private ProjectPlanService projectPlanService;
	
	@Autowired(required=true) @Qualifier("projectServiceImpl")
	private ProjectService projectService;
	
	@Autowired(required=true) @Qualifier("taskPlanServiceImpl")
	private TaskPlanService taskPlanService;
	
	@SuppressWarnings("unchecked")
	@Override
	protected QueryObject onFind(QueryObject query, HttpServletRequest request) throws Exception {
		String fwgcjgkId = messageSource.getMessage("FWGCJGK");
		String szgcjgkId = messageSource.getMessage("SZGCJGK");
		User user = AuthenticationHelper.getCurrentUser(true);
		Employee employee = user.getEmp();
		if (employee.getDepartment()!=null) {
			if (fwgcjgkId!=null&&fwgcjgkId.equals(employee.getDepartment().getObjId())) {
				query.getQueryParam().and(new QueryParam("engProjType","00"));//房屋建筑工程
			}else if(szgcjgkId!=null&&szgcjgkId.equals(employee.getDepartment().getObjId())) {
				query.getQueryParam().and(new QueryParam("engProjType","01"));//市政公用设施
			}
		}
		return query;
	}
	
	/** 
	 * Description : 加载项目管理页面
	 * Create Date: 2011-11-14 14:22:00 by shengn 
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=loadResProjectManagePage")
	public ModelAndView loadResProjectManagePage()throws Exception {
		logger.debug("\nes=ResProjectController||loadResProjectManagePage\n");
		Map<String,Object> model = new HashMap<String,Object>();
		User loginUser = AuthenticationHelper.getCurrentUser(true);
		Company agenty = loginUser.getEmp().getCompany();
		
		model.put("loginUser", loginUser);
		model.put("agenty", agenty);
		return new ModelAndView("loadResProjectManagePage", model);
	}
	
	/**
	 * Description : 加载项目列表
	 * Create Date: 2011-11-14  14:22:00 by shengn 
	 * @param projectName
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=loadResProjectInfoListForAgenty")
	public ModelAndView loadResProjectInfoListForAgenty(String projectName,String projectStatus,HttpServletRequest request)throws Exception {
		logger.debug("\nes=ResProjectController||loadResProjectInfoListForAgenty\n");
		Map<String,Object> queryParamMap = new HashMap<String,Object>();
		if(projectName!=null&&!"undefined".equals(projectName)){
			queryParamMap.put("projectName", projectName);
		}
		if(projectStatus!=null&&!"undefined".equals(projectStatus)){
			queryParamMap.put("projectStatus", projectStatus);
		}
		
		
		Map<String,Object> model = resProjectService.findResProjectInfoListForAgenty(queryParamMap,prePage(request));
		model.put("projectStatus", projectStatus);
		Page<ResProject> resultPage = (Page<ResProject>)model.get("resultPage");
		super.endPage(model, resultPage, request);
		
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description : 加载项目信息
	 * Create Date: 2011-11-14  14:22:00 by shengn 
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=loadResProjectInfo")
	public ModelAndView loadResProjectInfo(String parentId,String resProjectId,String viewName)throws Exception {
		logger.debug("\nes=ResProjectController||loadResProjectInfo\n");
		Map<String,Object> model = resProjectService.findResProjectInfo(parentId,resProjectId);
		if("look".equals(viewName)){
			return new ModelAndView("lookResProjectInfo", model);
		}
		return new ModelAndView("loadResProjectInfo", model);
	}
	
	/** 
	 * Description : 保存项目信息
	 * Create Date: 2011-11-14  14:22:00 by shengn 
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveResProjectInfo")
	public ModelAndView saveResProjectInfo(String resProjectStr,String moneySourceArray,HttpServletRequest request)throws Exception {
		logger.debug("\nes=ResProjectController||saveResProjectInfo\n");
		String returnMessage = "";
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			String departmentId = request.getParameter("departmentId");
			ResProject resProject= JsonUtils.json2Bean(JsonUtils.getJSONString(resProjectStr), ResProject.class);
			
			Map<String,String[]> moneySourceMap = new HashMap<String,String[]>();
			JSONArray moneySourceJSONArray = JSONArray.fromObject(moneySourceArray);
			if(null != moneySourceJSONArray && (!moneySourceJSONArray.isEmpty())){
				String[] categoryIds = new String[moneySourceJSONArray.size()];
				String[] moneys = new String[moneySourceJSONArray.size()];
				for(int i=0;i<moneySourceJSONArray.size();i++){
					JSONObject jsonObject = (JSONObject)moneySourceJSONArray.get(i);
					categoryIds[i] = (String)jsonObject.get("categoryId");
					moneys[i] = (String)jsonObject.get("money");
				}
				moneySourceMap.put("categoryIds", categoryIds);
				moneySourceMap.put("moneys", moneys);
			}
			
			resProjectService.saveResProjectInfo(resProject, moneySourceMap);
			returnMessage="保存成功！";
		} catch (Exception e) {
			e.printStackTrace();
			returnMessage="保存失败！";
		}
		model.put(Constants.JSON_RESULT,returnMessage);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description : 删除项目信息
	 * Create Date: 2011-11-14  14:22:00 by shengn 
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=deleteResProjectInfo")
	public ModelAndView deleteResProjectInfo(String resProjectId)throws Exception {
		logger.debug("\nes=ResProjectController||deleteResProjectInfo\n");
		String returnMessage = "";
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			resProjectService.deleteResProjectInfo(resProjectId);
			returnMessage="删除成功！";
		} catch (Exception e) {
			e.printStackTrace();
			returnMessage="删除失败！";
		}
		model.put(Constants.JSON_RESULT,returnMessage);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description : 结束项目
	 * Create Date: 2011-11-14  14:22:00 by shengn 
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=endResProject")
	public ModelAndView endResProject(String resProjectId)throws Exception {
		logger.debug("\nes=ResProjectController||endResProject\n");
		String returnMessage = "";
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			resProjectService.updateResProjectToEnd(resProjectId);
			returnMessage="结束成功！";
		} catch (Exception e) {
			e.printStackTrace();
			returnMessage="结束失败！";
		}
		model.put(Constants.JSON_RESULT,returnMessage);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description : 加载项目的分解页面
	 * Create Date: 2011-11-14  14:22:00 by shengn 
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=loadDisassemblePage")
	public ModelAndView loadDisassemblePage(String resProjectId)throws Exception {
		logger.debug("\nes=ResProjectController||loadDisassembleProjectList\n");
		ResProject parent = (ResProject)resProjectService.get(resProjectId,ResProject.class );
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("parent", parent);
		return new ModelAndView("loadDisassemblePage", model);
	}
	
	/** 
	 * Description : 加载项目子项目
	 * Create Date: 2011-11-14  14:22:00 by shengn 
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=loadResProjectOfSubList")
	public ModelAndView loadResProjectOfSubList(String parentId,HttpServletRequest request)throws Exception {
		logger.debug("\nes=ResProjectController||loadDisassembleProjectList\n");
		
		Map<String,Object> model = resProjectService.findResProjectOfSubList(parentId, prePage(request));
		Page<ResProject> resultPage = (Page<ResProject>)model.get("resultPage");
		super.endPage(model, resultPage, request);
		
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/**
	 * 加载招标项目创建信息页面
	 * @param parentId
	 * @param resProjectId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=loadCreateTenderProjectPage")
	public ModelAndView loadCreateTenderProjectPage(String parentId,String resProjectId)throws Exception {
		logger.debug("\nes=ResProjectController||loadCreateTenderProjectPage\n");
		
		Map<String,Object> model = resProjectService.findResProjectInfo(parentId,resProjectId);
		model.put("tenderProjectCode", projectService.createProjectCodeByQO(null, "01"));
		
		ResProject resProject = (ResProject)model.get("resProject");
		String taskPlanSubIds = "";
		List<TaskPlanSub> subs = taskPlanService.getTaskPlanSubByTaskPlan(resProject.getTaskPlanId());
		for (TaskPlanSub taskPlanSub : subs) {
			taskPlanSubIds += taskPlanSub.getObjId() + ",";
		}
		if(taskPlanSubIds.length()>0) {
			taskPlanSubIds = taskPlanSubIds.substring(0,taskPlanSubIds.length()-1);
		}
		
		if (taskPlanSubIds!=null&&!"".equals(taskPlanSubIds)) {
			model.put("taskPlanSubIds",taskPlanSubIds);
		}else {
			model.put("taskPlanSubIds","-1");
		}
		return new ModelAndView("loadCreateTenderProjectPage", model);
	}
	
	/** 
	 * Description : 保存招标项目
	 * Create Date: 2011-11-14  14:22:00 by shengn 
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveTenderProjectInfo")
	public ModelAndView saveTenderProjectInfo(String projectStr,String resProjectId,String projSubIds)throws Exception {
		logger.debug("\nes=ResProjectController||saveTenderProjectInfo\n");
		String returnMessage = "";
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			Project project= JsonUtils.json2Bean(JsonUtils.getJSONString(projectStr), Project.class);
			
			resProjectService.saveTenderProjectInfo(project, resProjectId,projSubIds);
			projectPlanService.createProjectPlan(project.getObjId());//因为后续还有创建工作计划功能，故这里不用考虑事务问题
			model.put("projectId",project.getObjId());
			returnMessage="保存成功！";
		} catch (Exception e) {
			e.printStackTrace();
			returnMessage="保存失败！";
		}
		model.put(Constants.JSON_RESULT,returnMessage);
		
		return new ModelAndView(Constants.JSON_VIEW, model);
	}

	
	/**
	 * FuncName : toResProjectInfoForm
	 * Description :  跳转到录入招标项目详细信息页面
	 * Create Date: 2011-12-21下午05:51:43 by liuke
	 * Modified Date: 2011-12-21下午05:51:43 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params = "method=toResProjectInfoForm")
	public ModelAndView toResProjectInfoForm(HttpServletRequest request)throws Exception {
		logger.debug("\nes=ResProjectController||toResProjectInfoForm\n");
		String objId = request.getParameter("objId");
		ResProject resProject = (ResProject)resProjectService.get(objId);
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("resProject", resProject);
		return new ModelAndView("resProjectInfoForm", model);
	}
	
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=loadResProjectInfoListForBudget")
	public ModelAndView loadResProjectInfoListForBudget(String projectName,String projectStatus,HttpServletRequest request)throws Exception {
		logger.debug("\nes=ResProjectController||loadResProjectInfoListForBudget\n");
		Map<String,Object> queryParamMap = new HashMap<String,Object>();
		if(projectName!=null&&!"undefined".equals(projectName)){
			queryParamMap.put("projectName", projectName);
		}
		String budgetName = request.getParameter("budgetName");
		if(budgetName!=null&&!"undefined".equals(budgetName)){
			queryParamMap.put("budgetName", budgetName);
		}
		Map<String,Object> model = resProjectService.findResProjectInfoListForBudget(queryParamMap,prePage(request));
		model.put("projectStatus", projectStatus);
		Page<ResProject> resultPage = (Page<ResProject>)model.get("resultPage");
		super.endPage(model, resultPage, request);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	
	
	/**
	 * FuncName : toSetResProjectInfoItemPage
	 * Description : 跳转到起草项目明细页面
	 * Create Date: 2011-12-22上午10:39:03 by liuke
	 * Modified Date: 2011-12-22上午10:39:03 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params = "method=toSetResProjectInfoItemPage")
	public ModelAndView toSetResProjectInfoItemPage(HttpServletRequest request)throws Exception {
		logger.debug("\nes=ResProjectController||toSetResProjectInfoItemPage\n");
		String resProjectId = request.getParameter("resProjectId");
				
		List<String> list = ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getEnum(ResProjectItemEnum.RESPROJECTITEMENUM);
		List<MessageCode> messageList = new ArrayList<MessageCode>();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			 String string = (String) iterator.next();
			 MessageCode mc = new MessageCode(string.split(":")[0], string.split(":")[1]);
			 messageList.add(mc); 
		}
		/*Added at 2011-5-26 by zhouzhanghe，对messageList排序*/
		Collections.sort(messageList, new MessageCode.MessageCodeComparator());
		
		String[] ItemNameArray = new String[messageList.size()];
		String[] ItemCodeArray = new String[messageList.size()];

		for(int i=0;i<messageList.size();i++){
			MessageCode mc =  messageList.get(i);
			ItemNameArray[i]= mc.getMessage();
			ItemCodeArray[i]= mc.getCode();
		}
		
		boolean flag = true;
		List<ResProjectItem> resProjectItemFromTableList = new ArrayList<ResProjectItem>();
		List<ResProjectItem> resProjectItemList = new ArrayList<ResProjectItem>();
		resProjectItemFromTableList = resProjectService.findResProjectItemListByResProjectId(resProjectId);
			
		for(int i=0;i<ItemNameArray.length;i++){
				for (Iterator iterator = resProjectItemFromTableList.iterator(); iterator.hasNext();) {
					ResProjectItem resProjectItem = (ResProjectItem) iterator.next();
					if(ItemNameArray[i].equals(resProjectItem.getItemName())){  //如果条目名称相同，则同步该条数据
						resProjectItemList.add(resProjectItem);
						flag = false;
					}	
				}
			if(flag){
				ResProjectItem resProjectItem = new ResProjectItem();
				resProjectItem.setItemName(ItemNameArray[i]);
				resProjectItem.setItemCode(ItemCodeArray[i]);
				resProjectItemList.add(resProjectItem); 
				}
				flag = true;
			}
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("resProjectItemList", resProjectItemList);
		return new ModelAndView("setResProjectInfoItem", model);
	}
	
	
	
	
	/**
	 * FuncName : saveResProjectItem
	 * Description :  
	 * Create Date: 2011-12-22下午03:01:34 by liuke
	 * Modified Date: 2011-12-22下午03:01:34 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params = "method=saveResProjectItem")
	public ModelAndView saveResProjectItem(HttpServletRequest request,ResProjectItem resProjectItem)throws Exception {
		logger.debug("\nes=ResProjectController||saveResProjectItem\n");
		String resProjectId = request.getParameter("resProjectId");
		if(resProjectItem.getResProject()==null){
			ResProject resProject = new ResProject();
			resProject.setObjId(resProjectId);
			resProjectItem.setResProject(resProject);
		}
		resProjectService.saveResProjectItem(resProjectItem);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("resProjectItem", resProjectItem);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	
	/** 
	 * FuncName : checkResProjectItem
	 * Description :  检查项目是否保存条目
	 * Create Date: 2011-12-22下午08:01:42 by yangx  
	 * Modified Date: 2011-12-22下午08:01:42 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=checkResProjectItem")
	public ModelAndView checkResProjectItem(HttpServletRequest request)throws Exception {
		String resProjectId = request.getParameter("resProjectId");
		List<ResProjectItem> resProjectItem = resProjectService.findResProjectItemListByResProjectId(resProjectId);
		Map<String, Object> model = new HashMap<String, Object>();
		if (resProjectItem!=null&&resProjectItem.size()>0) {
			model.put("result", "YES");
		}
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	
	/** 
	 * FuncName : 
	 * Description :  
	 * Create Date: 2011-12-22下午08:02:11 by yangx  
	 * Modified Date: 2011-12-22下午08:02:11 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveResProject")
	public ModelAndView saveResProject(HttpServletRequest request,SessionStatus status)throws Exception {
		String landNo = request.getParameter("landNo");
		String engProjType = request.getParameter("engProjType");
		String isVoting = request.getParameter("isVoting");
		String agencyId = request.getParameter("agencyId");
		String agencyName = request.getParameter("agencyName");
		String resProjectId = request.getParameter("resProjectId");
		ResProject resProject = resProjectService.get(resProjectId);
		resProject.setLandNo(landNo);
		resProject.setEngProjType(engProjType);
		resProject.setIsVoting(isVoting);
		resProject.setAuditStatus(AuditStatusEnum.WAIT_AUDIT);
		resProject.setUseStatus(CommonEnum.USER_STATUS_FORMAL);
		if (agencyId!=null&&!"".equals(agencyId)&&!"undefined".equals(agencyId)) {
			OrgInfo agenty = new OrgInfo();
			agenty.setObjId(agencyId);
			resProject.setAgenty(agenty);
		}
		if (agencyName!=null&&!"".equals(agencyName)&&!"undefined".equals(agencyName)) {
			resProject.setAgentyName(agencyName);
		}
		resProjectService.saveResProject(resProject);
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	
	/**
	 * FuncName : toResProjectInfoDetail
	 * Description :  跳转到查看详情页面
	 * Create Date: 2011-12-23下午02:36:47 by liuke
	 * Modified Date: 2011-12-23下午02:36:47 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params = "method=toResProjectInfoDetail")
	public ModelAndView toResProjectInfoDetail(HttpServletRequest request)throws Exception {
		String objId = request.getParameter("objId");
		String from = request.getParameter("from");
		ResProject resProject = (ResProject)resProjectService.get(objId);
		List<String> list = ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getEnum(ResProjectItemEnum.RESPROJECTITEMENUM);
		List<MessageCode> messageList = new ArrayList<MessageCode>();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			 String string = (String) iterator.next();
			 MessageCode mc = new MessageCode(string.split(":")[0], string.split(":")[1]);
			 messageList.add(mc); 
		}
		/*Added at 2011-5-26 by zhouzhanghe，对messageList排序*/
		Collections.sort(messageList, new MessageCode.MessageCodeComparator());
		
		String[] ItemNameArray = new String[messageList.size()];
		String[] ItemCodeArray = new String[messageList.size()];

		for(int i=0;i<messageList.size();i++){
			MessageCode mc =  messageList.get(i);
			ItemNameArray[i]= mc.getMessage();
			ItemCodeArray[i]= mc.getCode();
		}
		
		boolean flag = true;
		List<ResProjectItem> resProjectItemFromTableList = new ArrayList<ResProjectItem>();
		List<ResProjectItem> resProjectItemList = new ArrayList<ResProjectItem>();
		resProjectItemFromTableList = resProjectService.findResProjectItemListByResProjectId(objId);
			
		for(int i=0;i<ItemNameArray.length;i++){
				for (Iterator iterator = resProjectItemFromTableList.iterator(); iterator.hasNext();) {
					ResProjectItem resProjectItem = (ResProjectItem) iterator.next();
					if(ItemNameArray[i].equals(resProjectItem.getItemName())){  //如果条目名称相同，则同步该条数据
						resProjectItemList.add(resProjectItem);
						flag = false;
					}	
				}
			if(flag){
				ResProjectItem resProjectItem = new ResProjectItem();
				resProjectItem.setItemName(ItemNameArray[i]);
				resProjectItem.setItemCode(ItemCodeArray[i]);
				resProjectItemList.add(resProjectItem); 
				}
				flag = true;
			}
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("resProject", resProject);
		model.put("from", from);
		model.put("resProjectItemList", resProjectItemList);
		return new ModelAndView("resProjectInfoDetail", model);
	}
	
	
	/** 
	 * FuncName : toResProjectInfoForAudit
	 * Description :  跳转到审核招标项目列表页面
	 * Create Date: 2011-12-24下午12:16:33 by yangx  
	 * Modified Date: 2011-12-24下午12:16:33 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toResProjectInfoForAudit")
	public ModelAndView toResProjectInfoForAudit(HttpServletRequest request,SessionStatus status)throws Exception {
		String objId = request.getParameter("objId");
		ResProject resProject = (ResProject)resProjectService.get(objId);
		List<String> list = ((EnumService)FrameBeanFactory.getBean(EnumServiceImpl.BEAN_NAME)).getEnum(ResProjectItemEnum.RESPROJECTITEMENUM);
		List<MessageCode> messageList = new ArrayList<MessageCode>();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			 String string = (String) iterator.next();
			 MessageCode mc = new MessageCode(string.split(":")[0], string.split(":")[1]);
			 messageList.add(mc); 
		}
		/*Added at 2011-5-26 by zhouzhanghe，对messageList排序*/
		Collections.sort(messageList, new MessageCode.MessageCodeComparator());
		
		String[] ItemNameArray = new String[messageList.size()];
		String[] ItemCodeArray = new String[messageList.size()];

		for(int i=0;i<messageList.size();i++){
			MessageCode mc =  messageList.get(i);
			ItemNameArray[i]= mc.getMessage();
			ItemCodeArray[i]= mc.getCode();
		}
		
		boolean flag = true;
		List<ResProjectItem> resProjectItemFromTableList = new ArrayList<ResProjectItem>();
		List<ResProjectItem> resProjectItemList = new ArrayList<ResProjectItem>();
		resProjectItemFromTableList = resProjectService.findResProjectItemListByResProjectId(objId);
			
		for(int i=0;i<ItemNameArray.length;i++){
				for (Iterator iterator = resProjectItemFromTableList.iterator(); iterator.hasNext();) {
					ResProjectItem resProjectItem = (ResProjectItem) iterator.next();
					if(ItemNameArray[i].equals(resProjectItem.getItemName())){  //如果条目名称相同，则同步该条数据
						resProjectItemList.add(resProjectItem);
						flag = false;
					}	
				}
			if(flag){
				ResProjectItem resProjectItem = new ResProjectItem();
				resProjectItem.setItemName(ItemNameArray[i]);
				resProjectItem.setItemCode(ItemCodeArray[i]);
				resProjectItemList.add(resProjectItem); 
				}
				flag = true;
			}
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("resProject", resProject);
		model.put("resProjectItemList", resProjectItemList);
		return new ModelAndView("resProjectInfoDetailForAudit", model);
	}
	
	
	/** 
	 * FuncName : auditResProject
	 * Description :  审核招标项目
	 * Create Date: 2011-12-24下午12:28:58 by yangx  
	 * Modified Date: 2011-12-24下午12:28:58 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=auditResProject")
	public ModelAndView auditResProject(HttpServletRequest request,SessionStatus status)throws Exception {
		String objId = request.getParameter("objId");
		String auditStatus = request.getParameter("auditStatus");
		String opinion = request.getParameter("opinion");
		ResProject resProject = resProjectService.get(objId);
		resProject.setAuditStatus(auditStatus);
		resProjectService.saveResProjectForAudit(resProject,opinion);
		return new ModelAndView(Constants.JSON_VIEW);
	}
}
