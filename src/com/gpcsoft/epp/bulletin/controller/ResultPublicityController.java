package com.gpcsoft.epp.bulletin.controller;

import java.util.ArrayList;
import java.util.Date;
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
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.bulletin.domain.Bulletin;
import com.gpcsoft.epp.bulletin.domain.BulletinTypeEnum;
import com.gpcsoft.epp.bulletin.service.BulletinService;
import com.gpcsoft.epp.buyresult.domain.BuyWinner;
import com.gpcsoft.epp.buyresult.service.BuyWinnerService;
import com.gpcsoft.epp.common.domain.AuditStatusEnum;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.service.ProjectService;
import com.gpcsoft.epp.taskplan.domain.TaskPlan;
import com.gpcsoft.epp.taskplan.service.TaskPlanService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.common.assist.service.FreeMarkerService;

/**
 * @gpcsoft.view value="toPurBulletinRead"
 *  url="view/es/planform/bulletin/bulletinAuditView.jsp"
 * @gpcsoft.view value="bulletinView"
 *  url="view/es/planform/bulletin/bulletinView.jsp"
 *  @gpcsoft.view value="blank"
 *  url="view/es/common/blank.jsp"
 * @gpcsoft.view value="bulletinFormView"
 *  url="view/es/planform/bulletin/bulletinForm.jsp"
 * @gpcsoft.view value="toSubmitPurBulletin"
 *  url="view/es/planform/bulletin/submitBulletinForm.jsp"
 * @gpcsoft.view value="bulletinTreeFormView"
 *  url="view/es/planform/bulletin/bulletinTreeForm.jsp"
 * @gpcsoft.view value="bulletinListView"
 *  url="view/es/planform/bulletin/bulletinList.jsp"
 * @gpcsoft.view value="bulletinDetailView"
 *  url="view/es/planform/bulletin/bulletinDetail.jsp"
 * @gpcsoft.view value="purBulletin"
 *  url="view/es/planform/bulletin/purBulletin.jsp"
 *  @gpcsoft.view value="resultBulletinView"
 *  url="view/es/planform/bulletin/resultBulletin.jsp"
 *  @gpcsoft.view value="resultBulletin"
 *  url="view/es/planform/bulletin/resultBulletinView.jsp"
 *  @gpcsoft.view value="toBulletinInfoByObjId"
 *  url="view/es/planform/bulletin/bulletinViewForList.jsp"
 *  @gpcsoft.view value="toBulletinAuditInfoByObjId"
 *  url="view/es/planform/bulletin/bulletinAuditForList.jsp"
 *  @gpcsoft.view value="toPurBulletinForm"
 *  url="view/es/planform/bulletin/pruBulletinForm.jsp"
 *  @gpcsoft.view value="toResultPublicityOrBulletinForm"
 *  url="view/es/planform/bulletin/resultPublicityOrBulletinForm.jsp"
 *  @gpcsoft.view value="getMoreForBulletinList"
 *  url="view/es/planform/bulletin/moreForBulletinList.jsp"
 *  @gpcsoft.view value="resultBulletinAuditView"
 *  url="view/es/planform/bulletin/resultBulletinAudit.jsp"
 *  @gpcsoft.view value="resultBulletinAffirmView"
 *  url="view/es/planform/bulletin/resultBulletinAffirm.jsp"
 *  @gpcsoft.view value="resultBulletinReleaseView"
 *  url="view/es/planform/bulletin/resultBulletinRelease.jsp"
 *  @gpcsoft.view value="purBulletinAudit"
 *  url="view/es/planform/bulletin/purBulletinAudit.jsp"
 *  @gpcsoft.view value="purBulletinAffirm"
 *  url="view/es/planform/bulletin/purBulletinAffirm.jsp"
 *  @gpcsoft.view value="purBulletinLeaderAffirm"
 *  url="view/es/planform/bulletin/purBulletinLeaderAffirm.jsp"
 *  @gpcsoft.view value="purBulletinRelease"
 *  url="view/es/planform/bulletin/purBulletinRelease.jsp"
 *  @gpcsoft.view value="correctionsBulletin"
 *  url="view/es/planform/bulletin/correctionsBulletin.jsp"
 *  @gpcsoft.view value="correctionsBulletinAudit"
 *  url="view/es/planform/bulletin/correctionsBulletinAudit.jsp"
 *  @gpcsoft.view value="correctionsBulletinAffirm"
 *  url="view/es/planform/bulletin/correctionsBulletinAffirm.jsp"
 *  @gpcsoft.view value="correctionsBulletinRelease"
 *  url="view/es/planform/bulletin/correctionsBulletinRelease.jsp"
 *  @gpcsoft.view value="suspendBulletin"
 *  url="view/es/planform/bulletin/suspendBulletin.jsp"
 *  @gpcsoft.view value="suspendBulletinAudit"
 *  url="view/es/planform/bulletin/suspendBulletinAudit.jsp"
 *  @gpcsoft.view value="suspendBulletinAffirm"
 *  url="view/es/planform/bulletin/suspendBulletinAffirm.jsp"
 *  @gpcsoft.view value="suspendBulletinRelease"
 *  url="view/es/planform/bulletin/suspendBulletinRelease.jsp"
 *  @gpcsoft.view value="resultPublicity"
 *  url="view/es/planform/bulletin/resultPublicity.jsp"
 *  @gpcsoft.view value="resultPublicityView"
 *  url="view/es/planform/bulletin/resultPublicityView.jsp"
 *  @gpcsoft.view value="toPublicityOrBulletin"
 *  url="view/es/planform/bulletin/bulletinOrPublicityAudit.jsp"
 *  @gpcsoft.view value="getBulletinAuditByObjId"
 *  url="view/es/planform/bulletin/bulletinAudit.jsp"
 *  @gpcsoft.view value="bulletinViewByApply"
 *  url="view/es/planform/bulletin/bulletinViewByApply.jsp"
 *  @gpcsoft.view value="bulletinViewByApply1"
 *  url="view/es/planform/bulletin/bulletinViewByApply1.jsp"
 *  @gpcsoft.view value="toPurOrPublicityBulletin"
 *  url="view/es/planform/bulletin/bulletinTable.jsp"
 *  @gpcsoft.view value="resultPublicityAudit"
 *  url="view/es/planform/bulletin/resultPublicityAudit.jsp"
 *  @gpcsoft.view value="resultPublicityAffirm"
 *  url="view/es/planform/bulletin/resultPublicityAffirm.jsp"
 *  @gpcsoft.view value="resultPublicityRelease"
 *  url=" view/es/planform/bulletin/resultPublicityRelease.jsp"
 *  @gpcsoft.view value="blankPageView"
 *  url=" view/es/common/blank.jsp"
 *  @gpcsoft.view value="draftVariationBulletin"
 *  url="view/es/planform/bulletin/variationBulletinFrom.jsp"
 *  @gpcsoft.view value="auditVariationBulletin"
 *  url="view/es/planform/bulletin/auditVariationBulletinFrom.jsp"
 *  @gpcsoft.view value="toUpdatePurchaseBulletin"
 *  url="view/es/planform/bulletin/updateBulletinForm.jsp"
 *  @gpcsoft.view value="toUpdateResultBulletin"
 *  url="view/es/planform/bulletin/updateResultBulletinView.jsp"
 *  @gpcsoft.view value="toModifyVariationBulletin"
 *  url="view/es/planform/bulletin/updateVariationBulletinFrom.jsp"
 *  @gpcsoft.view value="variationBulletinList"
 *  url="view/es/planform/bulletin/variationBulletinListForSupplier.jsp"
 *  @gpcsoft.view value="bulletinViewVariation"
 *  url="view/es/planform/bulletin/bulletinViewVariation.jsp"
 *  @gpcsoft.view value="toResultPublicity"
 *  url="view/es/planform/bulletin/resultPublicityForAudit.jsp"
 *  @gpcsoft.view value="toUpdateResultPublicity"
 *  url="view/es/planform/bulletin/updateResultPublicityForm.jsp"
 *  @gpcsoft.view value="toResultBulletinTab"
 *  url="view/es/planform/bulletin/resultBulletinTab.jsp"
 *  @gpcsoft.view value="toResultBulletinTabForSupervise"
 *  url="view/es/planform/bulletin/resultBulletinTabForSupervise.jsp"
 *  @gpcsoft.view value="toViewResultBulletinForBuyer"
 *  url="view/es/planform/bulletin/resultBulletinTabForBuyer.jsp"
 *  @gpcsoft.view value="toViewResultBulletinForSupplierTab"
 *  url="view/es/planform/bulletin/resultBulletinTabForSupplierTab.jsp"
 *  @gpcsoft.view value="resultPublicityTabView"
 *  url="view/es/planform/bulletin/resultPublicityTab.jsp"
 *  @gpcsoft.view value="resultPublicityTabForSuperviseView"
 *  url="view/es/planform/bulletin/resultPublicityTabForSupervise.jsp"
 *  @gpcsoft.view value="resultPublicityTabForSupplierTabView"
 *  url="view/es/planform/bulletin/resultPublicityTabForSupplierTab.jsp"
 */

@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={Bulletin.class})
@RequestMapping("/ResultPublicityController.do")//页面请求路径,可修改
@SuppressWarnings(value="unchecked")
public class ResultPublicityController extends AnnotationMultiController<Bulletin>{
	
	@Autowired(required=true) @Qualifier("bulletinServiceImpl")
	private BulletinService bulletinService;
	
	@Autowired(required=true) @Qualifier("freeMarkerServiceImpl")
	private FreeMarkerService freeMarkerService;
	
	@Autowired(required=true) @Qualifier("projectServiceImpl")
	private ProjectService projectService;
	
	@Autowired(required=true) @Qualifier("taskPlanServiceImpl")
	private TaskPlanService taskPlanService;
	
	@Autowired(required=true) @Qualifier("buyWinnerServiceImpl")
	private BuyWinnerService buyWinnerService;
	
	/**
	 * FuncName:toDraftResultPublicity
	 * Description : 跳转到起草结果公示Tab页面 
	 * @param   request
	 * @return  ModelAndView
	 * @author liuke
	 * @Create Date: 2010-9-27下午05:17:24  
	 * @Modifier liuke
	 * @Modified Date: 2010-9-27下午05:17:24 
	 */
	@RequestMapping(params="method=toDraftResultPublicity")
	public ModelAndView toDraftResultPublicity(HttpServletRequest request)throws Exception {
		logger.debug("\nes ResultPublicityController||toDraftResultPublicity\n");
		String projectId = request.getParameter("projectId");//获取项目ID
		List<Project> projectList = projectService.getSubProjectByProjectId(projectId);
		if (projectList==null||projectList.size()<1) {
			Project project = projectService.getProjectByObjId(projectId);
			projectList = new ArrayList<Project>();
			projectList.add(project);
		}
		Map model = new HashMap();
		model.put("projectList", projectList);
		return new ModelAndView("resultPublicityTabView", model);//跳转到页面
	}	
	
	/** 
	 * FuncName:toAuditResultPublicity
	 * Description :采购办：跳转到审核结果公示Tab页面
	 * @param   request
	 * @return  ModelAndView
	 * @author yangx
	 * @Create Date: 2010-9-10上午11:28:09 
	 * @Modifier yangx 
	 * @Modified Date: 2010-9-10上午11:28:09 
	 */
	@RequestMapping(params="method=toAuditResultPublicity")
	public ModelAndView toAuditResultPublicity(HttpServletRequest request)throws Exception {
		logger.debug("\nes ResultPublicityController||toAuditResultPublicity\n");
		String projectId = request.getParameter("projectId");//获取项目ID
		List<Project> projectList = projectService.getSubProjectByProjectId(projectId);
		if (projectList==null||projectList.size()<1) {
			Project project = projectService.getProjectByObjId(projectId);
			projectList = new ArrayList<Project>();
			projectList.add(project);
		}
		Map model = new HashMap();
		model.put("projectList", projectList);
		return new ModelAndView("resultPublicityTabForSuperviseView", model);//跳转到页面
	}
	
	/** 
	 * FuncName:toResultPublicity
	 * Description :代理机构：跳转到起草结果公示页面
	 * @param   request
	 * @return  ModelAndView
	 * @author yangx
	 * @Create Date: 2010-9-7下午10:05:32 
	 * @Modifier  yangx
	 * @Modified Date: 2010-9-7下午10:05:32    
	 */
	@RequestMapping(params="method=toResultPublicity")
	public ModelAndView toResultPublicity(HttpServletRequest request)throws Exception {
		logger.debug("\nes ResultPublicityController||toResultPublicity\n");
		String projectId = request.getParameter("projectId");//获取项目ID
		Bulletin bulletin = bulletinService.getResultPublicityByProjectId(projectId);//根据项目ID获取结果公示
		User user = AuthenticationHelper.getCurrentUser();
		String returnUrl = "resultPublicityView";
		Map model = new HashMap();
		if(bulletin!=null && bulletin.getObjId()!=null){//若有公告，则跳到公告显示页面，若无公告，则跳到新增页面
			model.put("bulletin", bulletin);
			returnUrl = "toUpdateResultPublicity";
		}else{
			Project project = projectService.getProjectByObjId(projectId);//根据项目ID获取项目
			TaskPlan taskPlan = taskPlanService.getTaskPlanByObjId(project.getTaskPlan().getObjId());
			model.put("subproject", project);
			String companyName = user.getEmp().getCompany().getName();
			Map templateMap = new HashMap();
			templateMap.put("companyName", companyName);
			templateMap.put("project", project);
			templateMap.put("buyer", project.getBuyersName());
			templateMap.put("agency_linker", taskPlan.getAgentLeader());//代理机构联系人
			templateMap.put("buyer_linker", taskPlan.getBudgetLinker());//委托单位联系人
			templateMap.put("agency_linker_phone", taskPlan.getAgentLeaderTel());//代理机构联系电话
			templateMap.put("buyer_linker_phone", taskPlan.getBudgetLinkerTel());//委托单位联系电话
			templateMap.put("today", new Date());
			bulletin = new Bulletin();
			bulletin.setBulletinNo(project.getProjCode());//起草结果公示时默认初始化值
			bulletin.setBullTitle(project.getProjName());//起草结果公示时默认初始化值
			List<BuyWinner> buyWinnerList = buyWinnerService.getBuyWinnerByProjectId(projectId);
			templateMap.put("buyWinnerList", buyWinnerList);
			bulletin.setBullContents(freeMarkerService.getFreeMarkerContent("compositeGradeResultPublicityBulletin.ftl", templateMap));//输出绑定的结果
			model.put("bullType", BulletinTypeEnum.RESULT_PREVIEW);//公告类型为结果公示
			model.put("bulletin", bulletin);
		}
		if(bulletin!=null&&bulletin.getAuditStatus()!=null&&!(AuditStatusEnum.AUDIT_NO_PASS).equals(bulletin.getAuditStatus())&&(CommonEnum.USER_STATUS_FORMAL).equals(bulletin.getUseStatus())){
				returnUrl = "bulletinView";//跳转到查看页面
		}
		return new ModelAndView(returnUrl,model);
	}
	
	/**
	 * FuncName:toViewResultPublicityForSupplierTab
	 * Description :供应商：进入项目后查看结果公示
	 * @param   request
	 * @return ModelAndView
	 * @author liuke  
	 * @Create Date:2010-7-30上午10:47:58 
	 * @Modifier  liuke 
	 * @Modified Date: 2010-7-30上午10:47:58 
	 */
	@RequestMapping(params="method=toViewResultPublicityForSupplierTab")
	public ModelAndView toViewResultPublicityForSupplierTab(HttpServletRequest request)throws Exception{
		logger.debug("\nes ResultPublicityController||toViewResultPublicityForSupplierTab\n");
		String projectId = request.getParameter("projectId");//获取项目ID
		List<Project> projectList = projectService.getSubProjectByProjectId(projectId);
		if (projectList==null||projectList.size()<1) {
			Project project = projectService.getProjectByObjId(projectId);
			projectList = new ArrayList<Project>();
			projectList.add(project);
		}
		Map model = new HashMap();
		model.put("projectList", projectList);
		return new ModelAndView("resultPublicityTabForSupplierTabView", model);
	}
	
	/** 
	 * FuncName:toViewResultPublicityForSupplier
	 * Description :供应商:根据结果公示主键查看结果公示
	 * @param   request
	 * @return ModelAndView
	 * @author yangx  
	 * @Create Date: 2010-7-30上午10:47:58 
	 * @Modifier    yangx
	 * @Modified Date: 2010-7-30上午10:47:58 
	 */
	@RequestMapping(params="method=toViewResultPublicityForSupplier")
	public ModelAndView toViewResultPublicityForSupplier(HttpServletRequest request)throws Exception {
		logger.debug("\nes ResultPublicityController||toViewResultPublicityForSupplier\n");
		String objId = request.getParameter("objId");//获取项目ID
		Bulletin bulletin = bulletinService.getBulletinByObjId(objId);//根据结果公告ID获取结果公告
		Map model=new HashMap();
		model.put("bulletin", bulletin);
		String returnUrl = "blank";
		if(bulletin!=null&&bulletin.getObjId()!=null&&AuditStatusEnum.AUDIT_PASS.equals(bulletin.getAuditStatus())){
			returnUrl = "bulletinView";
		}
		return new ModelAndView(returnUrl,model);
	}
	
	/** 
	 * FuncName:saveResultPublicity
	 * Description :代理机构：保存结果公示
	 * @param   request,bulletin:结果公示对象,stauts
	 * @return  ModelAndView
	 * @author yangx
	 * @Create Date: 2010-9-7下午10:21:46 
	 * @Modifier  yangx
	 * @Modified Date: 2010-9-7下午10:21:46 
	 */
	@RequestMapping(params="method=saveResultPublicity")
	public ModelAndView saveResultPublicity(HttpServletRequest request, Bulletin bulletin, SessionStatus stauts)throws Exception {
		logger.debug("\nes ResultPublicityController||saveResultPublicity\n");
		bulletin.setUseStatus(CommonEnum.USER_STATUS_TEMP);//设置状态为临时
		bulletin.setBullType(BulletinTypeEnum.RESULT_PREVIEW);// 公告类型
		bulletinService.saveResultPublicity(bulletin,null);//保存结果公示
		stauts.setComplete();//因为session会影响到页面取值，所以这里清除一下。
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/** 
	 * FuncName:saveUpdateResultPublicity
	 * Description :代理机构：保存修改结果公示
	 * @param   request,bulletin:结果公示对象,stauts
	 * @return ModelAndView
	 * @author yangx  
	 * @Create Date: 2010-9-7下午10:21:46 
	 * @Modifier yangx
	 * @Modified Date: 2010-9-7下午10:21:46 
	 */
	@RequestMapping(params="method=saveUpdateResultPublicity")
	public ModelAndView saveUpdateResultPublicity(HttpServletRequest request, Bulletin bulletin, SessionStatus stauts)throws Exception {
		logger.debug("\nes ResultPublicityController||saveUpdateResultPublicity\n");
		bulletin.setUseStatus(CommonEnum.USER_STATUS_TEMP);//设置状态为临时
		bulletin.setBullType(BulletinTypeEnum.RESULT_PREVIEW);// 公告类型
		bulletinService.saveUpdateResultPublicity(bulletin,null);//保存结果公示
		stauts.setComplete();//因为session会影响到页面取值，所以这里清除一下。
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/** 
	 * FuncName:submitResultPublicity
	 * Description :   代理机构：提交结果公示
	 * @param   request,bulletin:结果公示,stauts
	 * @return  ModelAndView
	 * @author yangx
	 * @Create Date: 2010-9-7下午10:34:40 
	 * @Modifier yangx
	 * @Modified Date: 2010-9-7下午10:34:40 
	 */
	@RequestMapping(params="method=submitResultPublicity")
	public ModelAndView submitResultPublicity(HttpServletRequest request, Bulletin bulletin, SessionStatus stauts)throws Exception {
		logger.debug("\nes ResultPublicityController||submitResultPublicity\n");
		bulletin.setUseStatus(CommonEnum.USER_STATUS_FORMAL);//设置使用状态为正式
		bulletin.setAuditStatus(AuditStatusEnum.WAIT_AUDIT);//设置审核状态为待审核
		bulletin.setBullType(BulletinTypeEnum.RESULT_PREVIEW);//公告类型
		bulletinService.saveSubmitResultPublicity(bulletin,null);//保存结果公示
		stauts.setComplete();//因为session会影响到页面取值，所以这里清除一下。
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/** 
	 * FuncName:submitUpdateResultPublicity
	 * Description :代理机构:提交修改结果公示
	 * @param   request,bulletin:结果公示,stauts
	 * @return  ModelAndView
	 * @author yangx
	 * @Create Date: 2010-9-7下午10:34:40 
	 * @Modifier yangx
	 * @Modified Date: 2010-9-7下午10:34:40  
	 */
	@RequestMapping(params="method=submitUpdateResultPublicity")
	public ModelAndView submitUpdateResultPublicity(HttpServletRequest request, Bulletin bulletin, SessionStatus stauts)throws Exception {
		logger.debug("\nes ResultPublicityController||submitUpdateResultPublicity\n");
		bulletin.setUseStatus(CommonEnum.USER_STATUS_FORMAL);//设置使用状态为正式
		bulletin.setAuditStatus(AuditStatusEnum.WAIT_AUDIT);//设置审核状态为待审核
		bulletin.setBullType(BulletinTypeEnum.RESULT_PREVIEW);//公告类型
		bulletinService.saveSubmitResultPublicity(bulletin,null);//保存结果公示
		stauts.setComplete();//因为session会影响到页面取值，所以这里清除一下。
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/** 
	 * FuncName:toPublicityAudit
	 * Description :采购办：根据项目Id跳转到审核结果公示
	 * @param   request
	 * @return  ModelAndView
	 * @author yangx
	 * @Create Date: 2010-9-7下午10:44:23 
	 * @Modifier yangx
	 * @Modified Date: 2010-9-7下午10:44:23 
	 */
	@RequestMapping(params="method=toPublicityAudit")
	public ModelAndView toPublicityAudit(HttpServletRequest request)throws Exception {
		logger.debug("\nes ResultPublicityController||toPublicityAudit\n");
		String projectId=request.getParameter("projectId");
		Map model=new HashMap();
		Bulletin bulletin = bulletinService.getResultPublicityByProjectId(projectId);//根据项目ID获取结果公示
		model.put("bulletin", bulletin);
		String returnUrl = "bulletinView";
		if (bulletin==null||(bulletin!=null&&bulletin.getAuditStatus()==null)) {//判断是否无公告或无审核状态
			returnUrl = "blank";//跳转到无数据页面
		}else if (bulletin.getAuditStatus()!=null&&(AuditStatusEnum.WAIT_AUDIT).equals(bulletin.getAuditStatus())) {//判断审核状态是否为待审核
			returnUrl = "toResultPublicity";//跳转到审核页面 
		}
		return new ModelAndView(returnUrl, model);
	}
	
	/** 
	 * FuncName:auditResultPublicity
	 * Description :  采购办：审核结果公示
	 * @param   request,bulletin:公告,stauts
	 * @return  ModelAndView
	 * @author yangx
	 * @Create Date: 2010-9-7下午03:07:58 
	 * @Modifier yangx
	 * @Modified Date: 2010-9-7下午03:07:58 
	 */
	@RequestMapping(params="method=auditResultPublicity")
	public ModelAndView auditResultPublicity(HttpServletRequest request,Bulletin bulletin,SessionStatus stauts)throws Exception {
		logger.debug("\nes ResultPublicityController||auditResultPublicity\n");
		String passStatus=request.getParameter("passStatus");//审批是否通过：通过(01)、不通过(02)
		if (passStatus!=null&&(AuditStatusEnum.AUDIT_PASS).equals(passStatus)) {//审批通过
			bulletin.setAuditStatus(AuditStatusEnum.AUDIT_PASS);
		} else {//审批不通过
			bulletin.setAuditStatus(AuditStatusEnum.AUDIT_NO_PASS);
		}
		User user = AuthenticationHelper.getCurrentUser();
		bulletinService.saveResultPublicityForAudit(bulletin,user);//保存审核结果
		stauts.setComplete();
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/** 
	 * FuncName:getResultPublicityAuditByObjId
	 * Description :  采购办：待办任务跳转到审核结果公示页面
	 * @param   request
	 * @return ModelAndView
	 * @author yangx  
	 * @Create Date: 2010-9-7下午12:58:44 
	 * @Modifier   yangx
	 * @Modified Date: 2010-9-7下午12:58:44 
	 */
	@RequestMapping(params="method=getResultPublicityAuditByObjId")
	public ModelAndView getResultPublicityAuditByObjId(HttpServletRequest request)throws Exception{
		logger.debug("\nes ResultPublicityController||getResultPublicityAuditByObjId\n");
		String objId = request.getParameter("objId");
		Map model=new HashMap();
		Bulletin bulletin = bulletinService.getBulletinByObjId(objId);//根据公告ID获取公告
		model.put("bulletin", bulletin);
		return new ModelAndView("toResultPublicity", model);//招标公告
	}
	
}
