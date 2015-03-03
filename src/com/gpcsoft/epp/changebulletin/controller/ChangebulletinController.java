package com.gpcsoft.epp.changebulletin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

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
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryObjectBase;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.utils.JsonUtils;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.bulletin.domain.Bulletin;
import com.gpcsoft.epp.bulletin.domain.BulletinTypeEnum;
import com.gpcsoft.epp.bulletin.service.BulletinService;
import com.gpcsoft.epp.common.domain.AuditStatusEnum;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.domain.ProjectMTaskPlan;
import com.gpcsoft.epp.project.domain.ProjectRule;
import com.gpcsoft.epp.project.service.ProjectMTaskPlanService;
import com.gpcsoft.epp.project.service.ProjectService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.common.assist.service.FreeMarkerService;

/**
  *  @gpcsoft.view value="allprojectList"
  *  url="view/es/planform/bulletin/allprojectList.jsp"
  *  @gpcsoft.view value="projectInfoFormForChangeBulletin"
  *  url="view/es/planform/project/projectInfoFormForChangeBulletin.jsp"
  *  @gpcsoft.view value="changeBulletinFrom"
  *  url="view/es/planform/project/changeBulletinFrom.jsp"
  *  @gpcsoft.view value="updateChangeBulletinFrom"
  *  url="view/es/planform/project/updateChangeBulletinFrom.jsp"
  *  @gpcsoft.view value="auditChangeBulletinFrom"
  *  url="view/es/planform/bulletin/auditChangeBulletinFrom.jsp"
  *  @gpcsoft.view value="changeBulletinListDetail"
  *  url="view/es/planform/bulletin/changeBulletinListDetail.jsp"
  *  
  */

@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={Bulletin.class})
@RequestMapping("/ChangebulletinController.do")//页面请求路径,可修改
@SuppressWarnings(value="unchecked")
public class ChangebulletinController extends AnnotationMultiController<Bulletin>{

	@Autowired(required=true) @Qualifier("bulletinServiceImpl")
	private BulletinService bulletinService;
	
	@Autowired(required=true) @Qualifier("freeMarkerServiceImpl")
	private FreeMarkerService freeMarkerService;
	
	@Autowired(required=true) @Qualifier("projectServiceImpl")
	private ProjectService projectService;
	
	@Autowired(required=true) @Qualifier("projectMTaskPlanServiceImpl")
	private ProjectMTaskPlanService projectMTaskPlanService;
	/**
	 * Description :查询所有招标项目方法 
	 * Create Date: 2010-10-14上午10:19:36 by liuke  Modified Date: 2010-10-14上午10:19:36 by liuke
	 * @param   request
	 * @return  ModelAndView
	 * @Exception
	 */
	@RequestMapping(params="method=getProjectList")
	public ModelAndView getProjectList(HttpServletRequest request)throws Exception{
		logger.debug("\nes=ChangebulletinController||getProjectList\n");
		String projName = request.getParameter("projName");
		String projCode = request.getParameter("projCode");
		User user = AuthenticationHelper.getCurrentUser();//获得当前用户信息
		QueryObject<Project> queryObject = new QueryObjectBase<Project>();
		queryObject.setEntityClass(Project.class);
		queryObject.getQueryParam().and(new QueryParam("projCode",QueryParam.OPERATOR_EQ,projCode));
		queryObject.getQueryParam().and(new QueryParam("projName",QueryParam.OPERATOR_EQ,projName));
		Page<Project> page = prePage(request);
		Map model = new HashMap();
		Page<Project> pageData=page = projectService.searchProjectListForAgent(queryObject, page, user.getEmp().getObjId());
		endPage(model, pageData, request);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  代理机构：根据项目Id跳转到起草变更公告
	 * Create Date: 2010-9-1下午05:46:18 by yangx  Modified Date: 2010-9-1下午05:46:18 by yangx
	 * @param   request
	 * @return  ModelAndView
	 * @Exception   
	 */
	@RequestMapping(params="method=toDraftChangeBulletin")
	public ModelAndView toDraftChangeBulletin(HttpServletRequest request)throws Exception {
		logger.debug("\n ChangebulletinController||toDraftChangeBulletin\n");
		String projectId = request.getParameter("projectId");//获取项目ID
		User user = AuthenticationHelper.getCurrentUser();
		Bulletin bulletin = new Bulletin();
		Map model = new HashMap();
		model.put("projectId", projectId);
		Project project = projectService.getProjectByObjId(projectId);//根据项目ID获取项目
		List<ProjectMTaskPlan> projectMTaskPlanList = projectMTaskPlanService.getProjectMTaskPlanByProjectId(projectId);
		OrgInfo buyers = (OrgInfo)projectMTaskPlanList.get(0).getBuyMainBody();
		ProjectRule rule = projectService.getProjectRuleByTenderId(projectId);
		String companyName = user.getEmp().getCompany().getName();//代理机构名称
		String companyAddress = user.getEmp().getCompany().getAddress();//代理机构地址
		String name = user.getEmp().getName();//代理机构联系人名称
		String tel = user.getEmp().getCompany().getTel();//代理机构电话
		String fax = user.getEmp().getCompany().getFax();//代理机构传真
		if (companyAddress==null) {
			companyAddress = "";
		}
		if (tel==null) {
			tel="";
		}
		if (fax==null) {
			fax="";
		}
		Map templateMap = new HashMap();
		templateMap.put("companyName", companyName);
		templateMap.put("buyers", buyers);
		templateMap.put("companyAddress", companyAddress);
		templateMap.put("name", name);
		templateMap.put("tel", tel);
		templateMap.put("fax", fax);
		templateMap.put("project", project);
		bulletin.setBulletinNo(bulletinService.createChangeBulletinCodeByQO(null, project.getEbuyMethod()));//起草更正公告时默认初始化值
		bulletin.setBullTitle(project.getProjName()+"更正公告");
		bulletin.setProject(project);
		bulletin.setBullContents(freeMarkerService.getFreeMarkerContent("variationBulletin.ftl", templateMap));//输出绑定的结果
		model.put("bulletin", bulletin);
		model.put("projectRule", rule);
		return new ModelAndView("changeBulletinFrom", model);
	}
	
	/** 
	 * Description :  代理机构：保存变更公告
	 * Create Date: 2010-9-1下午06:14:52 by liuke  Modified Date: 2010-9-1下午06:14:52 by liuke
	 * @param   request,bulletin:变更公告,stauts
	 * @return  ModelAndView
	 * @Exception   
	 */
	@RequestMapping(params="method=saveChangeBulletin")
	public ModelAndView saveChangeBulletin(HttpServletRequest request,Bulletin bulletin,SessionStatus stauts)throws Exception {
		logger.debug("\n ChangebulletinController||saveChangeBulletin\n");
		bulletin.setUseStatus(CommonEnum.USER_STATUS_TEMP);//设置状态为临时
		bulletin.setBullType(BulletinTypeEnum.CHANGEBUKKETIN_BULLETIN);//公告类型
		bulletin.setAuditStatus(AuditStatusEnum.WAIT_AUDIT);
		bulletinService.saveChangeBulletin(bulletin,null);// 保存变更公告
		stauts.setComplete();//因为session会影响到页面取值，所以这里清除一下。
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	
	/** 
	 * Description :  代理机构：提交变更公告
	 * Create Date: 2010-9-1下午06:14:52 by liuke  Modified Date: 2010-9-1下午06:14:52 by liuke
	 * @param   request,bulletin:变更公告,stauts
	 * @return  ModelAndView
	 * @Exception   
	 */
	@RequestMapping(params="method=submitChangeBulletin")
	public ModelAndView submitChangeBulletin(HttpServletRequest request,Bulletin bulletin,SessionStatus stauts)throws Exception {
		logger.debug("\n ChangebulletinController||submitChangeBulletin\n");
		bulletin.setUseStatus(CommonEnum.USER_STATUS_FORMAL);//设置状态为正式
		bulletin.setBullType(BulletinTypeEnum.CHANGEBUKKETIN_BULLETIN);//公告类型
		bulletin.setAuditStatus(AuditStatusEnum.WAIT_AUDIT);
		bulletinService.saveSubmitChangeBulletin(bulletin,null);// 保存变更公告
		stauts.setComplete();//因为session会影响到页面取值，所以这里清除一下。
		return new ModelAndView(Constants.JSON_VIEW);

	}
	
	/** 
	 * Description :  代理机构:保存修改变更公告
	 * Create Date: 2010-9-1下午06:14:52 by yangx  Modified Date: 2010-9-1下午06:14:52 by yangx
	 * @param   request,bulletin:变更公告,stauts
	 * @return  ModelAndView
	 * @Exception   
	 */
	@RequestMapping(params="method=saveUpdateChangeBulletin")
	public ModelAndView saveUpdateChangeBulletin(HttpServletRequest request,Bulletin bulletin,SessionStatus stauts)throws Exception {
		logger.debug("\n ChangebulletinController||saveUpdateChangeBulletin\n");
		bulletin.setUseStatus(CommonEnum.USER_STATUS_TEMP);//设置状态为临时
		bulletin.setBullType(BulletinTypeEnum.CHANGEBUKKETIN_BULLETIN);//公告类型
		bulletin.setAuditStatus(AuditStatusEnum.WAIT_AUDIT);
		bulletinService.saveUpdateChangeBulletin(bulletin,null);// 保存变更公告
		stauts.setComplete();//因为session会影响到页面取值，所以这里清除一下。
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/** 
	 * Description :  代理机构：提交修改更正公告
	 * Create Date: 2010-9-1下午06:14:52 by yangx  Modified Date: 2010-9-1下午06:14:52 by yangx
	 * @param   request,bulletin:变更公告,stauts
	 * @return  ModelAndView
	 * @Exception   
	 */
	@RequestMapping(params="method=submitUpdateChangeBulletin")
	public ModelAndView submitUpdateChangeBulletin(HttpServletRequest request,Bulletin bulletin,SessionStatus stauts)throws Exception {
		logger.debug("\n ChangebulletinController||submitUpdateChangeBulletin\n");
		bulletin.setUseStatus(CommonEnum.USER_STATUS_FORMAL);//设置状态为正式
		bulletin.setBullType(BulletinTypeEnum.CHANGEBUKKETIN_BULLETIN);//公告类型
		bulletin.setAuditStatus(AuditStatusEnum.WAIT_AUDIT);
		bulletinService.saveUpdateSubmitChangeBulletin(bulletin,null);// 保存变更公告
		stauts.setComplete();//因为session会影响到页面取值，所以这里清除一下。
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/** 
	 * Description :  采购办：根据变更公告ID跳转审核页面
	 * Create Date: 2010-9-7下午06:14:42 by liuke  Modified Date: 2010-9-7下午06:14:42 by liuke
	 * @param   request
	 * @return  ModelAndView
	 * @Exception   
	 */
	@RequestMapping(params="method=toAuditChangeBulletinByObjId")
	public ModelAndView toAuditChangeBulletinByObjId(HttpServletRequest request)throws Exception {
		logger.debug("\n ChangebulletinController||toAuditChangeBulletinByObjId\n");
		String objId = request.getParameter("objId");
		Map model=new HashMap();
		Bulletin bulletin = bulletinService.getBulletinByObjId(objId);//根据公告ID获取公告
		model.put("bulletin", bulletin);
		return new ModelAndView("auditChangeBulletinFrom", model);//更正公告公告
	}
	
	
	/** 
	 * Description :  采购办：根据状态审核变更公告
	 * Create Date: 2010-9-7下午06:22:02 by liuke  Modified Date: 2010-9-7下午06:22:02 by liuke
	 * @param   ids,passStatus,bulletin:变更公告,stauts
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=auditChangeBulletin")
	public ModelAndView auditChangeBulletin(String ids,String passStatus, Bulletin bulletin,SessionStatus stauts)throws Exception {
		logger.debug("\n ChangebulletinController||auditChangeBulletin\n");
		if(ids == null || ids.equals("")){
			ids = bulletin.getObjId();
		}
		bulletinService.saveChangeBulletinForAudit(ids,passStatus);//保存审核结果
		stauts.setComplete();
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/** 
	 * Description :  代理机构：跳转到录入招标信息页面
	 * Create Date: 2010-9-21上午10:01:47 by yangx  Modified Date: 2010-9-21上午10:01:47 by yangx
	 * @param   request
	 * @return  ModelAndView
	 * @Exception   
	 */
	@RequestMapping(params = "method=toInputTenderInfo")
	public ModelAndView toInputTenderInfo(HttpServletRequest request)throws Exception{
		logger.debug("\n ChangebulletinController||toInputTenderInfo\n");
		String projectId = request.getParameter("projectId");
		Project project = projectService.getProjectByObjId(projectId);//获取项目
		ProjectRule projectRule = projectService.getProjectRuleByProjectId(projectId);
		List<Project> subProjectList = projectService.getSubProjectByProjectId(projectId);//获取包组
		Map model = new HashMap();
		model.put("project",project);
		model.put("projectRule",projectRule);
		model.put("subProjectList",subProjectList);
		String returnUrl = "projectInfoFormForChangeBulletin";//录入也页面
		return new ModelAndView(returnUrl, model);
	}
	
	
	
	
	/** 
	 * FuncName:saveInputTenderInfo
	 * Description :  代理机构：保存录入招标信息
	 * @param   project:项目,subProjects:包组项目,status,request
	 * @return ModelAndView
	 * @author yangx  
	 * @Create Date: 2010-9-21下午01:47:55 
	 * @Modifier  yangx
	 * @Modified Date: 2010-9-21下午01:47:55     
	 */
	@RequestMapping(params = "method=saveInputTenderInfo")
	public ModelAndView saveInputTenderInfo(String project,String subProjects,SessionStatus status,HttpServletRequest request)throws Exception {
		logger.debug("\nes=ProjectController||saveInputTenderInfo\n");
		Project projectq = JsonUtils.json2Bean(JsonUtils.getJSONString(project), Project.class);
		JSONArray jsonArray = JSONArray.fromObject(subProjects);
		projectService.saveInputTenderInfo(projectq,jsonArray);
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	
	/** 
	 * FuncName:toModifyVariationBulletin
	 * Description :  修改更正公告
	 * @param   request
	 * @return  ModelAndView
	 * @author yangx
	 * @Create Date: 2010-9-2上午10:04:19 
	 * @Modifier  yangx 
	 * @Modified Date: 2010-9-2上午10:04:19  
	 */
	@RequestMapping(params="method=toModifyChangeBulletin")
	public ModelAndView toModifyChangeBulletin(HttpServletRequest request)throws Exception {
		logger.debug("\n ChangebulletinController || toModifyChangeBulletin\n");
		String objId = request.getParameter("objId");//获取公告ID
		Bulletin bulletin = bulletinService.getBulletinByObjId(objId);
		Map model = new HashMap();
		model.put("bulletin", bulletin);
		return new ModelAndView("updateChangeBulletinFrom", model);
	}
	
	
	
	
	
	/** 
	 * FuncName:toBulletinInfoByObjId
	 * Description : 根据公告ID跳转到公告详细信息查看页面 
	 * @param   request
	 * @return ModelAndView
	 * @author yangx
	 * @Create Date: 2010-7-7上午11:07:22 
	 * @Modifier yangx
	 * @Modified Date: 2010-7-7上午11:07:22   
	 */
	@RequestMapping(params="method=toChangeBulletinListDetail")
	public ModelAndView toChangeBulletinViewList(HttpServletRequest request)throws Exception {
		logger.debug("\nes=ChangebulletinController||toChangeBulletinListDetail\n");
		String objId = request.getParameter("objId");
		String backUrl = request.getParameter("backUrl");
		String returnUrl ="changeBulletinListDetail";
		Map model=new HashMap();
		if(objId==null||"".equals(objId)){
			returnUrl = "blank"; 	//如果公告Id不存在跳转到无数据页面
		}
		Bulletin bulletin = bulletinService.getBulletinByObjId(objId);//根据公告ID获取公告
		model.put("bulletin",bulletin);
		model.put("backUrl",backUrl);
		return new ModelAndView(returnUrl, model);
	}
	
	
}
