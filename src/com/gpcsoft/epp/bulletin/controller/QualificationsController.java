package com.gpcsoft.epp.bulletin.controller;

import java.util.ArrayList;
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
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.bulletin.domain.Bulletin;
import com.gpcsoft.epp.bulletin.domain.BulletinTypeEnum;
import com.gpcsoft.epp.bulletin.service.BulletinService;
import com.gpcsoft.epp.common.domain.AuditStatusEnum;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.common.domain.EbuyMethodEnum;
import com.gpcsoft.epp.common.domain.EsExceptionEnum;
import com.gpcsoft.epp.common.domain.FileVirtualPathEnum;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.expertrule.domain.TakeExpertRule;
import com.gpcsoft.epp.project.domain.ProjProcessStatusEnum;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.domain.ProjectMTaskPlan;
import com.gpcsoft.epp.project.domain.ProjectRule;
import com.gpcsoft.epp.project.service.ProjectMTaskPlanService;
import com.gpcsoft.epp.project.service.ProjectService;
import com.gpcsoft.epp.projrule.domain.BidRoom;
import com.gpcsoft.epp.qualifications.dao.QualificationsFileDao;
import com.gpcsoft.epp.qualifications.domain.QualificationsApp;
import com.gpcsoft.epp.qualifications.domain.QualificationsFile;
import com.gpcsoft.epp.qualifications.service.QualificationsFileService;
import com.gpcsoft.epp.signuprecord.domain.SignUpCondFactor;
import com.gpcsoft.epp.signuprecord.service.SignUpCondFactorService;
import com.gpcsoft.epp.signuprecord.service.SignUpResponeService;
import com.gpcsoft.epp.worktask.domain.CompletedWorkTaskTypeEnum;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.ListBind;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.common.assist.service.FreeMarkerService;

/**
 * @gpcsoft.view value="qualificationView"
 *  url="view/es/planform/bulletin/qualificationView.jsp"
 * @gpcsoft.view value="qualificationFormView"
 *  url="view/es/planform/bulletin/qualificationForm.jsp"
 *  
 **/

@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={Bulletin.class})
@RequestMapping("/QualificationsController.do")//页面请求路径,可修改
@SuppressWarnings(value="unchecked")
public class QualificationsController extends AnnotationMultiController<Bulletin> {
	
	@Autowired(required=true) @Qualifier("bulletinServiceImpl")
	private BulletinService bulletinService;
	
	@Autowired(required=true) @Qualifier("signUpResponeServiceImpl")
	private SignUpResponeService signUpResponeService;
	
	@Autowired(required=true) @Qualifier("projectServiceImpl")
	private ProjectService projectService;
	
	@Autowired(required=true) @Qualifier("projectMTaskPlanServiceImpl")
	private ProjectMTaskPlanService projectMTaskPlanService;
	
	@Autowired(required=true) @Qualifier("freeMarkerServiceImpl")
	private FreeMarkerService freeMarkerService;
	
	
	@Autowired(required=true) @Qualifier("signUpCondFactorServiceImpl")
	private SignUpCondFactorService signUpCondFactorService;
	
	
	@Autowired(required=true)  @Qualifier("qualificationsFileServiceImpl")
	private QualificationsFileService qualificationsFileService;
	/**
	 * 
	 * Description :  预审公告
	 * Create Date: 2011-11-17上午11:17:34 by chenhongjun  
	 * Modified Date: 2011-11-17上午11:17:34 by chenhongjun
	 *@param request
	 *@return
	 *@throws Exception
	 *上午11:17:34 
	 *ModelAndView
	 */
	@RequestMapping(params="method=toQualification")
	public ModelAndView toQualification(HttpServletRequest request)throws Exception {
		logger.debug("\nes QualificationsController||toQualification\n");
		String projectId = request.getParameter("projectId");//获取项目ID
		User user = AuthenticationHelper.getCurrentUser();
		OrgInfo orgInfo = (OrgInfo)user.getOrgInfo();
		Map model = new HashMap();
		String returnUrl = "qualificationView";//跳转到查看采购公告页面
		
		model.put("projectId", projectId);
		Project project = projectService.getProjectByObjId(projectId);//根据项目ID获取项目
		Bulletin bulletin = bulletinService.getBulletinByProjectId(projectId,BulletinTypeEnum.QUALIFICATION_BULLETIN);//根据项目ID获取公告
		ProjectRule projectRule = projectService.getProjectRuleByProjectId(projectId);
		if (bulletin==null) {//若有公告，则跳到公告显示页面，若无公告，则跳到新增页面
			bulletin = new Bulletin();
			bulletin.setBulletinNo(bulletinService.createBulletinCodeByQO(null, project.getEbuyMethod()));// 起草采购公告时默认初始化值 
			bulletin.setBullTitle(project.getProjName());
			bulletin.setTenderStartTime(projectRule.getSubmitStTime());//投标开始时间
			bulletin.setTenderEndTime(projectRule.getOpenBidSDate());//开标时间
			bulletin.setSignUpSTime(projectRule.getSignUpSTime());//报名开始时间
			bulletin.setSignUpETime(projectRule.getSignUpETime());//报名结束时间
		} 			
		String title="";
		String makerFile="";
		if(EbuyMethodEnum.OPEN_BIDDING.equals(project.getEbuyMethod())){
			title = "资格预审";
			makerFile="qualification.ftl";
		}else if(EbuyMethodEnum.NEGOTIATE.equals(project.getEbuyMethod())){
			title = "资格预审";
		}else if(EbuyMethodEnum.INVITE_BIDDING.equals(project.getEbuyMethod())){
			title=project.getEbuyMethodCN();
			makerFile="inquireBulletin.ftl";
		}else if(EbuyMethodEnum.OTHER_BIDDING.equals(project.getEbuyMethod())){
			title="资格预审";
			makerFile="qualification.ftl";
		}
		
		String totalPrice = projectService.getSumTaskPlanDetailProjectId(project.getObjId());//预算
		List<Project> subList = projectService.getSubProjectByProjectId(projectId);
		model.put("subList", subList);
	//	TakeExpertRule takeExpertRule = expertRuleService.getExpertRuleBySubProjectId(project.getObjId());//获取专家规则
		List taskPlanSubs = projectMTaskPlanService.getAllTaskPlanSubByProject(projectId);
		List<ProjectMTaskPlan> projectMTaskPlanList = projectMTaskPlanService.getProjectMTaskPlanByProjectId(projectId);
		OrgInfo buyers = (OrgInfo)projectMTaskPlanList.get(0).getBuyMainBody();//采购人
		
		Map templateMap = new HashMap();
		
	//	templateMap.put("bidRoom", bidRoom);
		templateMap.put("title", title);
		templateMap.put("project", project);
		templateMap.put("projectRule", projectRule);
		templateMap.put("totalPrice", totalPrice);
		templateMap.put("today", new Date());
		templateMap.put("taskPlanSubs", taskPlanSubs);
		templateMap.put("buyer", buyers);
//		templateMap.put("takeExpertRule", takeExpertRule);
		templateMap.put("subList", subList);
		List<SignUpCondFactor> signUpCondFactorList = signUpCondFactorService.getSignUpCondFactorListByProjectId(projectId);
	 	templateMap.put("signUpCondFactorList", signUpCondFactorList);
		
	 	bulletin.setBullContents(freeMarkerService.getFreeMarkerContent(makerFile, templateMap));////设置采购公告模板 ,输出绑定的结果
		model.put("bulletin", bulletin);
		model.put("projectRule", projectRule);
		QualificationsFile qualificationsFile = new QualificationsFile();
		qualificationsFile.setProject(project);
		qualificationsFileService.saveQualificationFile(qualificationsFile);
		if(orgInfo.getAgencyId()!=null&&bulletin.getObjId()!=null&&(CommonEnum.USER_STATUS_FORMAL).equals(bulletin.getUseStatus())){
			returnUrl = "qualificationView";//跳转到查看页面
		} 
		
		return new ModelAndView(returnUrl, model);
	}	
	
	/**
	 * FuncName:saveDraftPurBulletin
	 * Description : 代理机构：保存起草招标公告
	 * @param   request,list:报名响应集合,bulletin:公告对象,stauts
	 * @return  ModelAndView
	 * @author yangx
	 */
	@RequestMapping(params="method=saveQualification")
	public ModelAndView saveQualification(HttpServletRequest request,ListBind list, Bulletin bulletin, SessionStatus stauts,String contractStr)throws Exception {
		logger.debug("\nes=QualificationsController||saveDQualification\n");
		bulletin.setUseStatus(CommonEnum.USER_STATUS_TEMP);//设置使用状态为临时
		bulletin.setBullType(BulletinTypeEnum.QUALIFICATION_BULLETIN);//公告类型:采购公告
		Project project = projectService.getProjectByObjId(bulletin.getProject().getObjId());
		bulletin.setPurcategoryIds(project.getPurCategoryIds());
		bulletin.setPurcategoryNames(project.getPurCategoryNames());
		List<SignUpCondFactor> applyInfoList = new ArrayList<SignUpCondFactor>();
		if (null != contractStr && !"".equals(contractStr)) {
			JSONArray reportPlanRelatingDetailArray = JSONArray.fromObject(contractStr);
			if (null != reportPlanRelatingDetailArray && !reportPlanRelatingDetailArray.isEmpty()) {
				applyInfoList = new ArrayList<SignUpCondFactor>(reportPlanRelatingDetailArray.size());
				for (int i=0;i<reportPlanRelatingDetailArray.size();i++) {
					SignUpCondFactor applyInfo = (SignUpCondFactor)JSONObject.toBean(((JSONObject)reportPlanRelatingDetailArray.get(i)),SignUpCondFactor.class);
					applyInfoList.add(applyInfo);
				}
			}
		}
	 
		bulletinService.saveBulletin(bulletin, ProjProcessStatusEnum.DRAFT_QUALIFICATION, FileVirtualPathEnum.BULLETIN_QUALIFICATION, CompletedWorkTaskTypeEnum.WORKTASK_TYPE_BULLETIN);
		for (Iterator iterator = applyInfoList.iterator(); iterator.hasNext();) {
			SignUpCondFactor signUpCondFactor = (SignUpCondFactor) iterator.next();
			signUpCondFactor.setProject(bulletin.getProject());
			if(("").equals(signUpCondFactor.getObjId())){
				signUpCondFactor.setObjId(null);
			}
			signUpCondFactorService.saveSignUpCondFactor(signUpCondFactor);
		}
		stauts.setComplete();//因为session会影响到页面取值，所以这里清除一下。
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/**
	 * FuncName:saveUpdateSubmitQualificationBulletin
	 * Description : 代理机构：提交修改公告
	 * @param  request,list:报名响应集合,bulletin:公告对象,stauts
	 * @return  ModelAndView
	 * @author yangx
	 */
	
	/**
	 * 
	 * Description :  代理机构：提交修改公告
	 * Create Date: 2011-11-17下午04:05:10 by chenhongjun  
	 * Modified Date: 2011-11-17下午04:05:10 by chenhongjun
	 *@param request  
	 *@param list  报名响应集合
	 *@param bulletin  公告对象
	 *@param stauts  session状态对象
	 *@param contractStr  公告内容对象
	 *@return
	 *@throws Exception
	 *下午04:05:10 
	 *ModelAndView
	 */
	@RequestMapping(params="method=saveUpdateSubmitQualificationBulletin")
	public ModelAndView saveUpdateSubmitQualificationBulletin(HttpServletRequest request,ListBind list, Bulletin bulletin, SessionStatus stauts,String contractStr)throws Exception {
		logger.debug("\nes=PurBulletinController||saveUpdateSubmitPurBulletin\n");
		bulletin.setUseStatus(CommonEnum.USER_STATUS_FORMAL);//设置使用状态为正式
		bulletin.setAuditStatus(AuditStatusEnum.WAIT_AUDIT);//设置审核状态为待审核
		bulletin.setBullType(BulletinTypeEnum.QUALIFICATION_BULLETIN);//公告类型
	 
		String checkValue = request.getParameter("checkValue");
		bulletinService.saveSubmitBulletin(bulletin,ProjProcessStatusEnum.SUBMIT_QUALIFICATION,checkValue,FileVirtualPathEnum.BULLETIN_QUALIFICATION,CompletedWorkTaskTypeEnum.WORKTASK_TYPE_BULLETIN);//保存审核采购公告
		List<SignUpCondFactor> applyInfoList = new ArrayList<SignUpCondFactor>();
		if (null != contractStr && !"".equals(contractStr)) {
			JSONArray reportPlanRelatingDetailArray = JSONArray.fromObject(contractStr);
			if (null != reportPlanRelatingDetailArray && !reportPlanRelatingDetailArray.isEmpty()) {
				applyInfoList = new ArrayList<SignUpCondFactor>(reportPlanRelatingDetailArray.size());
				for (int i=0;i<reportPlanRelatingDetailArray.size();i++) {
					SignUpCondFactor applyInfo = (SignUpCondFactor)JSONObject.toBean(((JSONObject)reportPlanRelatingDetailArray.get(i)),SignUpCondFactor.class);
					applyInfoList.add(applyInfo);
				}
			}
		}
		for (Iterator iterator = applyInfoList.iterator(); iterator.hasNext();) {
			SignUpCondFactor signUpCondFactor = (SignUpCondFactor) iterator.next();
			signUpCondFactor.setProject(bulletin.getProject());
			if(("").equals(signUpCondFactor.getObjId())){
				signUpCondFactor.setObjId(null);
			}
			signUpCondFactorService.saveSignUpCondFactor(signUpCondFactor);
		}
		stauts.setComplete();//因为session会影响到页面取值，所以这里清除一下。
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	
	
	
}
