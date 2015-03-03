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

import org.codehaus.groovy.antlr.treewalker.PreOrderTraversal;
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
import com.gpcsoft.core.utils.StringUtils;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.bulletin.domain.Bulletin;
import com.gpcsoft.epp.bulletin.domain.BulletinTypeEnum;
import com.gpcsoft.epp.bulletin.service.BulletinService;
import com.gpcsoft.epp.common.domain.AuditStatusEnum;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.common.domain.EbuyMethodEnum;
import com.gpcsoft.epp.common.domain.EsExceptionEnum;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.expertrule.domain.TakeExpertRule;
import com.gpcsoft.epp.expertrule.service.ExpertRuleService;
import com.gpcsoft.epp.project.domain.ProjProcessStatusEnum;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.domain.ProjectMTaskPlan;
import com.gpcsoft.epp.project.domain.ProjectRule;
import com.gpcsoft.epp.project.service.ProjectMTaskPlanService;
import com.gpcsoft.epp.project.service.ProjectService;
import com.gpcsoft.epp.projrule.domain.BidRoom;
import com.gpcsoft.epp.projrule.service.MeetRoomService;
import com.gpcsoft.epp.resproject.domain.ResProject;
import com.gpcsoft.epp.signuprecord.domain.SignUpCondFactor;
import com.gpcsoft.epp.signuprecord.domain.SignUprecord;
import com.gpcsoft.epp.signuprecord.domain.SignUprecordEnum;
import com.gpcsoft.epp.signuprecord.service.SignUpCondFactorService;
import com.gpcsoft.epp.signuprecord.service.SignUprecordService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.ListBind;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.common.assist.service.FreeMarkerService;

/**
  * @gpcsoft.view value="toPurBulletinRead"
  *  url="view/es/planform/bulletin/bulletinAuditView.jsp"
  * @gpcsoft.view value="bulletinView"
  *  url="view/es/planform/bulletin/bulletinView.jsp"
  *  @gpcsoft.view value="blankView"
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
@RequestMapping("/PurBulletinController.do")//页面请求路径,可修改
@SuppressWarnings(value="unchecked")
public class PurBulletinController extends AnnotationMultiController<Bulletin>{

	@Autowired(required=true) @Qualifier("bulletinServiceImpl")
	private BulletinService bulletinService;
	
	@Autowired(required=true) @Qualifier("freeMarkerServiceImpl")
	private FreeMarkerService freeMarkerService;
	
	@Autowired(required=true) @Qualifier("projectServiceImpl")
	private ProjectService projectService;
	
	@Autowired(required=true) @Qualifier("meetRoomServiceImpl")
	private MeetRoomService meetRoomService;
	
	@Autowired(required=true) @Qualifier("signUprecordServiceImpl")
	private SignUprecordService signUprecordService;
		
	@Autowired(required=true) @Qualifier("projectMTaskPlanServiceImpl")
	private ProjectMTaskPlanService projectMTaskPlanService;
	
	@Autowired(required=true) @Qualifier("signUpCondFactorServiceImpl")
	private SignUpCondFactorService signUpCondFactorService;
	
	@Autowired(required=true) @Qualifier("expertRuleServiceImpl")
	private ExpertRuleService expertRuleService;
	
	/**
	 * FuncName:toDraftPurBulletin
	 * Description : 代理机构：跳转到起草招标公告页面
	 * @param  request
	 * @return  ModelAndView
	 * @author  yangx
	 * @modifier zhouzhanghe
	 * @modified date 2011-11-17 16:21
	 */
	@RequestMapping(params="method=toDraftPurBulletin")
	public ModelAndView toDraftPurBulletin(HttpServletRequest request)throws Exception {
		logger.debug("\nes PurBulletinController||toDraftPurBulletin\n");
		String projectId = request.getParameter("projectId");//获取项目ID
		User user = AuthenticationHelper.getCurrentUser();
		OrgInfo orgInfo = (OrgInfo)user.getOrgInfo();
		Map model = new HashMap();
		String returnUrl = "bulletinFormView";//跳转到起草采购公告页面
		model.put("projectId", projectId);
		Project project = projectService.getProjectByObjId(projectId);//根据项目ID获取项目
		Bulletin bulletin = bulletinService.getPurBulletinByProjectId(projectId);//根据项目ID获取公告
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
		if(EbuyMethodEnum.OPEN_BIDDING.equals(project.getEbuyMethod())){
			title = "公开招标";
		}else if(EbuyMethodEnum.NEGOTIATE.equals(project.getEbuyMethod())){
			title = "竞争性谈判";
		}
		String totalPrice = projectService.getSumTaskPlanDetailProjectId(project.getObjId());//预算
		List<Project> subList = projectService.getSubProjectByProjectId(projectId);
		model.put("subList", subList);
		TakeExpertRule takeExpertRule = expertRuleService.getExpertRuleBySubProjectId(project.getObjId());//获取专家规则
		List taskPlanSubs = projectMTaskPlanService.getAllTaskPlanSubByProject(projectId);
		List<ProjectMTaskPlan> projectMTaskPlanList = projectMTaskPlanService.getProjectMTaskPlanByProjectId(projectId);
		OrgInfo buyers =  null;
		if(projectMTaskPlanList.size()>0){
			buyers=(OrgInfo)projectMTaskPlanList.get(0).getBuyMainBody();
		}else{
			buyers=getBuyer(project);
		}
		
		
		
		List<BidRoom> bidRoomList = meetRoomService.getBidRoomByProjectId(projectId);
		BidRoom bidRoom = new BidRoom();
		for(BidRoom bid:bidRoomList) {
			if("01".equals(bid.getMeetRoom().getRoomType())) {
				bidRoom = bid;
			}else if("00".equals(bid.getMeetRoom().getRoomType())) {
				projectRule.setOpenBidAddr(bid.getBidRoomAddress());
			}
		}
		/*如果是新建公告(解决经保存的公告,其内容被还原成模板的问题) by zhouzhanghe at 2011-11-17 16:16*/
		if(bulletin == null || StringUtils.empty(bulletin.getObjId())){ 
			Map templateMap = new HashMap();
			templateMap.put("bidRoom", bidRoom);
			templateMap.put("title", title);
			templateMap.put("project", project);
			templateMap.put("projectRule", projectRule);
			templateMap.put("totalPrice", totalPrice);
			templateMap.put("today", new Date());
			templateMap.put("taskPlanSubs", taskPlanSubs);
			templateMap.put("buyer", buyers);
			templateMap.put("takeExpertRule", takeExpertRule);
			templateMap.put("subList", subList);
			List<SignUpCondFactor> signUpCondFactorList = signUpCondFactorService.getSignUpCondFactorListByProjectId(projectId);
			templateMap.put("signUpCondFactorList", signUpCondFactorList);
			bulletin.setBullContents(freeMarkerService.getFreeMarkerContent("publicBiddingBulletin.ftl", templateMap));////设置采购公告模板 ,输出绑定的结果
		}
		model.put("bulletin", bulletin);
		model.put("projectRule", projectRule);
		
		if(orgInfo.getAgencyId()!=null&&bulletin.getObjId()!=null&&(CommonEnum.USER_STATUS_FORMAL).equals(bulletin.getUseStatus())&&!(AuditStatusEnum.AUDIT_NO_PASS).equals(bulletin.getAuditStatus())){
			returnUrl = "bulletinView";//跳转到查看页面
		}
		return new ModelAndView(returnUrl, model);
	}	
	
	/** 
	 * Description :  
	 * Create Date: 2011-12-24下午05:02:12 by chenhongjun  
	 * Modified Date: 2011-12-24下午05:02:12 by chenhongjun
	 *@param project
	 *@return
	 *下午05:02:12 
	 *OrgInfo 
	 */
	private OrgInfo getBuyer(Project project) {
		String resProjectId=project.getResProjectId();
		if(resProjectId==null)resProjectId="-1";
		ResProject resProject=(ResProject)projectService.get(resProjectId,ResProject.class);
		return resProject.getBudget();
	}

	/**
	 * FuncName:toPurBulletinRead
	 * Description :采购办:根据项目Id跳转招标公告审核页面
	 * @param    request
	 * @return  ModelAndView
	 * @author yangx
	 */
	@RequestMapping(params="method=toPurBulletinRead")
	public ModelAndView toPurBulletinRead(HttpServletRequest request)throws Exception {
		logger.debug("\nes PurBulletinController||toPurBulletinRead\n");
		String projectId=request.getParameter("projectId");
		Map model=new HashMap();
		model.put("projectId", projectId);
		Bulletin bulletin = bulletinService.getPurBulletinByProjectId(projectId);
		model.put("bulletin", bulletin);
		String returnUrl = "toPurBulletinRead";
		if (bulletin==null||(bulletin!=null&&bulletin.getAuditStatus()==null)) {//判断是否有采购公告、审核状态是否存在
			returnUrl = "blankView";// 无采购公告或是审核状态不存在跳转到无数据页面 
		} else if(bulletin.getAuditStatus()!=null&&!(AuditStatusEnum.WAIT_AUDIT).equals(bulletin.getAuditStatus())) {//判断审核状态是否不是待审核 
			returnUrl = "bulletinView";//跳转到查看页面 
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
	@RequestMapping(params="method=saveDraftPurBulletin")
	public ModelAndView saveDraftPurBulletin(HttpServletRequest request,ListBind list, Bulletin bulletin, SessionStatus stauts,String contractStr)throws Exception {
		logger.debug("\nes=PurBulletinController||saveDraftPurBulletin\n");
		bulletin.setUseStatus(CommonEnum.USER_STATUS_TEMP);//设置使用状态为临时
		bulletin.setBullType(BulletinTypeEnum.PURCHASE_BULLETIN);//公告类型:采购公告
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
		if ((bulletin.getSignUpETime()).before((bulletin.getSignUpSTime()))) {//判断报名结束时间是否大于报名开始时间
			throw new EsException(messageSource.getMessage(EsExceptionEnum.SINGUP_TIME));
		}
		if ((bulletin.getTenderStartTime()).before((bulletin.getSignUpETime()))) {//判断投标开始时间是否大于报名结束时间
			throw new EsException(messageSource.getMessage(EsExceptionEnum.TENDER_SINGUP_TIME));
		}
		if ((bulletin.getTenderEndTime()).before((bulletin.getTenderStartTime()))) {//判断投标结束时间是否大于投标开始时间
			throw new EsException(messageSource.getMessage(EsExceptionEnum.TENDER_TIME));
		}
		bulletinService.saveBulletinAndProjSchedule(bulletin,ProjProcessStatusEnum.DRAFT_PURCHASE_BULLETIN);//保存采购公告
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
	 * FuncName:saveUpdateDraftPurBulletin
	 * Description :代理机构：保存修改招标公告
	 * @param  request,list:报名响应集合,bulletin:公告对象,stauts
	 * @return  ModelAndView
	 * @author  yangx
	 */
	@RequestMapping(params="method=saveUpdateDraftPurBulletin")
	public ModelAndView saveUpdateDraftPurBulletin(HttpServletRequest request,ListBind list,Bulletin bulletin, SessionStatus stauts,String contractStr)throws Exception {
		logger.debug("\nes=PurBulletinController||saveUpdateDraftPurBulletin\n");
		bulletin.setUseStatus(CommonEnum.USER_STATUS_TEMP);//设置使用状态为临时
		bulletin.setBullType(BulletinTypeEnum.PURCHASE_BULLETIN);//公告类型:采购公告
		if ((bulletin.getSignUpETime()).before((bulletin.getSignUpSTime()))) {//判断报名结束时间是否大于报名开始时间
			throw new EsException(messageSource.getMessage(EsExceptionEnum.SINGUP_TIME));
		}
		if ((bulletin.getTenderStartTime()).before((bulletin.getSignUpETime()))) {//判断投标开始时间是否大于报名结束时间
			throw new EsException(messageSource.getMessage(EsExceptionEnum.TENDER_SINGUP_TIME));
		}
		if ((bulletin.getTenderEndTime()).before((bulletin.getTenderStartTime()))) {//判断投标结束时间是否大于投标开始时间
			throw new EsException(messageSource.getMessage(EsExceptionEnum.TENDER_TIME));
		}
		bulletinService.saveUpdateBulletinAndProjSchedule(bulletin,ProjProcessStatusEnum.DRAFT_PURCHASE_BULLETIN);//保存采购公告
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
		for (Iterator iterator = applyInfoList.iterator(); iterator.hasNext();) {
			SignUpCondFactor signUpCondFactor = (SignUpCondFactor) iterator.next();
			if(("").equals(signUpCondFactor.getObjId())){
				signUpCondFactor.setObjId(null);
			}
			signUpCondFactor.setProject(bulletin.getProject());
			signUpCondFactorService.saveSignUpCondFactor(signUpCondFactor);
		}
		stauts.setComplete();//因为session会影响到页面取值，所以这里清除一下。
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/**
	 * FuncName:saveSubmitPurBulletin
	 * Description : 代理机构：保存提交招标公告
	 * @param   request,list:报名响应集合,bulletin:公告对象,stauts
	 * @return  ModelAndView
	 * @author yangx
	 */
	@RequestMapping(params="method=saveSubmitPurBulletin")
	public ModelAndView saveSubmitPurBulletin(HttpServletRequest request,ListBind list, Bulletin bulletin, SessionStatus stauts,String contractStr)throws Exception {
		logger.debug("\nes=PurBulletinController||saveSubmitPurBulletin\n");
		bulletin.setBullType(BulletinTypeEnum.PURCHASE_BULLETIN);//公告类型
		if ((bulletin.getSignUpETime()).before((bulletin.getSignUpSTime()))) {//判断报名结束时间是否大于报名开始时间
			throw new EsException(messageSource.getMessage(EsExceptionEnum.SINGUP_TIME));
		}
		if ((bulletin.getTenderStartTime()).before((bulletin.getSignUpETime()))) {//判断投标开始时间是否大于报名结束时间
			throw new EsException(messageSource.getMessage(EsExceptionEnum.TENDER_SINGUP_TIME));
		}
		if ((bulletin.getTenderEndTime()).before((bulletin.getTenderStartTime()))) {//判断投标结束时间是否大于投标开始时间
			throw new EsException(messageSource.getMessage(EsExceptionEnum.TENDER_TIME));
		}
		String checkValue = request.getParameter("checkValue");
		
		Project project = projectService.getProjectByObjId(bulletin.getProject().getObjId());
		bulletin.setPurcategoryIds(project.getPurCategoryIds());
		bulletin.setPurcategoryNames(project.getPurCategoryNames());
		bulletinService.saveSubmitBulletinAndProjSchedule(bulletin,ProjProcessStatusEnum.SUBMIT_PURCHASE_BULLETIN,checkValue);//保存审核采购公告
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
	
	/**
	 * FuncName:saveUpdateSubmitPurBulletin
	 * Description : 代理机构：提交修改招标公告
	 * @param  request,list:报名响应集合,bulletin:公告对象,stauts
	 * @return  ModelAndView
	 * @author yangx
	 */
	@RequestMapping(params="method=saveUpdateSubmitPurBulletin")
	public ModelAndView saveUpdateSubmitPurBulletin(HttpServletRequest request,ListBind list, Bulletin bulletin, SessionStatus stauts,String contractStr)throws Exception {
		logger.debug("\nes=PurBulletinController||saveUpdateSubmitPurBulletin\n");
		bulletin.setUseStatus(CommonEnum.USER_STATUS_FORMAL);//设置使用状态为正式
		bulletin.setAuditStatus(AuditStatusEnum.WAIT_AUDIT);//设置审核状态为待审核
		bulletin.setBullType(BulletinTypeEnum.PURCHASE_BULLETIN);//公告类型
		if ((bulletin.getSignUpETime()).before((bulletin.getSignUpSTime()))) {//判断报名结束时间是否大于报名开始时间
			throw new EsException(messageSource.getMessage(EsExceptionEnum.SINGUP_TIME));
		}
		if ((bulletin.getTenderStartTime()).before((bulletin.getSignUpETime()))) {//判断投标开始时间是否大于报名结束时间
			throw new EsException(messageSource.getMessage(EsExceptionEnum.TENDER_SINGUP_TIME));
		}
		if ((bulletin.getTenderEndTime()).before((bulletin.getTenderStartTime()))) {//判断投标结束时间是否大于投标开始时间
			throw new EsException(messageSource.getMessage(EsExceptionEnum.TENDER_TIME));
		}
		String checkValue = request.getParameter("checkValue");
		bulletinService.saveSubmitBulletinAndProjSchedule(bulletin,ProjProcessStatusEnum.SUBMIT_PURCHASE_BULLETIN,checkValue);//保存审核采购公告
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
	
	/**
	 * FuncName:purbulletinAudit
	 * Description : 采购办：审核招标公告
	 * @param   ids:招标公告主键,passStatus:审核状态,bulletin:公告,stauts
	 * @return  ModelAndView
	 * @author  yangx
	 */
	@RequestMapping(params="method=purbulletinAudit")
	public ModelAndView purbulletinAudit(String ids,String passStatus,Bulletin bulletin,SessionStatus stauts)throws Exception {
		logger.debug("\nes=PurBulletinController||purbulletinAudit\n");
		if(ids==null||"".equals(ids)){
			ids = bulletin.getObjId();
		}
		bulletinService.savePurBulletinForAudit(ids,passStatus,bulletin.getOpinion());//保存审批状态
		stauts.setComplete();//清除Session
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/** 
	 * FuncName:getBulletinByObjId
	 * Description :  供应商：根据公告ID跳转到内容公告页面
	 * @param   request
	 * @return  ModelAndView
	 * @author yangx
	 * @Create Date: 2010-6-21上午09:56:48 
	 * @Modifier yangx
	 * @Modified Date: 2010-6-21上午09:56:48 
	 */	
	@RequestMapping(params="method=getBulletinByObjId")
	public ModelAndView getBulletinByObjId(HttpServletRequest request)throws Exception {
		logger.debug("\nes=PurBulletinController||getBulletinByObjId\n");
		String objId = request.getParameter("objId");
		String returnUrl = request.getParameter("returnUrl");
		Map model=new HashMap();
		model.put("returnUrl", returnUrl);
		Bulletin bulletin = bulletinService.getBulletinByObjId(objId);//根据公告ID获取公告
		User user = AuthenticationHelper.getCurrentUser();
		OrgInfo orgInfo = (OrgInfo)user.getOrgInfo();
		SignUprecord signUprecord = null;
		String returnName = "bulletinView"; //跳转到查看页面
		if(bulletin.getProject()!=null){
			signUprecord = signUprecordService.getSignUprecordBySupplierId(bulletin.getProject().getObjId(), user);//根据项目Id和操作人查询报名信息
		}
		model.put("bulletin", bulletin);
		model.put("signUprecord", signUprecord);
		if(signUprecord!=null){
			model.put("signUprecordMsg",  SignUprecordEnum.SIGNUP_RECORD_FLAG);//有报名信息标志
		}else{
			model.put("signUprecordMsg", SignUprecordEnum.NO_SIGNUP_RECORD_FLAG);//无报名信息标志
		}
		if(orgInfo!=null&&orgInfo.getSupplierId()!=null&&!"".equals(orgInfo.getSupplierId())){//判断是否为供应商
			String projectId = "";
			Project project = projectService.getProjectByObjId(bulletin.getProject().getObjId());
			if(project.getParentId()==null||"".equals(project.getParentId())){ //说明是项目
				projectId = project.getObjId();
			}else{
				projectId = project.getParentId();
			}
			ProjectRule projectRule = projectService.getProjectRuleByProjectId(projectId);//项目
			Date nowDate = new Date();
			if (projectRule.getSignUpSTime()!=null&&projectRule.getSignUpETime()!=null&&nowDate.after(projectRule.getSignUpSTime())&&nowDate.before(projectRule.getSignUpETime())){//判断能否报名
				model.put("isApply",SignUprecordEnum.CAN_SIGNUP_RECORD);//可以报名标志
			}else{
				model.put("isApply",SignUprecordEnum.CAN_NOT_SIGNUP_RECORD);//不可以报名标志
				if(nowDate.before(projectRule.getSignUpSTime())) {
					model.put("time", "before");
				}
			}
			model.put("orgInfoId",orgInfo.getObjId());
			if(bulletin.getBullType()!=null&&(BulletinTypeEnum.PURCHASE_BULLETIN).equals(bulletin.getBullType())){
				returnName = "bulletinViewByApply";//跳转到报名页面
			}
		}
		return new ModelAndView(returnName, model);//跳转到查看页面
	}
	
	/** 
	 * FuncName:toViewBulletinByProjectId
	 * Description :  采购人：根据项目Id跳转到招标公告查看页
	 * @param   request
	 * @return  ModelAndView
	 * @author yangx
	 * @Create Date: 2010-9-7下午01:45:52 
	 * @Modifier    yangx
	 * @Modified Date: 2010-9-7下午01:45:52 
	 */
	@RequestMapping(params="method=toViewBulletinByProjectId")
	public ModelAndView toViewBulletinByProjectId(HttpServletRequest request)throws Exception {
		logger.debug("\nes=PurBulletinController||toViewBulletinByProjectId\n");
		String projectId = request.getParameter("projectId");
		String returnName = "bulletinView";
		Map model=new HashMap();
		Bulletin bulletin = bulletinService.getPurBulletinByProjectId(projectId);	//根据项目ID获取公告
		model.put("fromType", "fromDesk");
		if(null!=bulletin){ //如果招标文件不为空
			model.put("bulletin", bulletin);
		}else{
			returnName = "blankView";
		}
		return new ModelAndView(returnName, model);//跳转到查看页面
	}
	
	/** 
	 * FuncName:toBulletinByProjectId
	 * Description :根据项目ID跳转到公告内容页面
	 * @param   request
	 * @return  ModelAndView
	 * @author yangx
	 * @Create Date: 2010-6-21上午09:56:48 
	 * @Modifier  yangx
	 * @Modified Date: 2010-6-21上午09:56:48 
	 */	
	@RequestMapping(params="method=toBulletinByProjectId")
	public ModelAndView toBulletinByProjectId(HttpServletRequest request)throws Exception {
		logger.debug("\nes=PurBulletinController||toBulletinByProjectId\n");
		String projectId = request.getParameter("projectId");
		String bullType = request.getParameter("bullType");
		Map model=new HashMap();
		Bulletin bulletin = bulletinService.getBulletinByProjectId(projectId,bullType);	/** 根据项目ID获取公告 */
		model.put("bulletin", bulletin);
		User user = AuthenticationHelper.getCurrentUser();
		OrgInfo orgInfo = (OrgInfo)user.getOrgInfo();
		SignUprecord signUprecord = null;
		String returnName = "bulletinView"; 	/** 跳转到查看页面 */
		if(bulletin!=null){
			if (bulletin.getProject()!=null) {
				signUprecord = signUprecordService.getSignUprecordBySupplierId(bulletin.getProject().getObjId(), user);
			}
			model.put("signUprecord", signUprecord);
			if (orgInfo!=null&&orgInfo.getSupplierId()!=null&&!"".equals(orgInfo.getSupplierId())) {//判断是否为供应商 
				Date nowDate = new Date();
				if (bulletin.getSignUpSTime()!=null&&bulletin.getSignUpETime()!=null) {
					if (nowDate.after(bulletin.getSignUpSTime())&&nowDate.before(bulletin.getSignUpETime())) {	//判断能否报名 
						model.put("isApply",SignUprecordEnum.CAN_SIGNUP_RECORD);//可以报名 
					} else {
						model.put("isApply",SignUprecordEnum.CAN_NOT_SIGNUP_RECORD);	//不可以报名 
					}
				}
				model.put("orgInfoId",orgInfo.getObjId());
				returnName = "bulletinViewByApply1";//跳转到查看页面
			} else if(orgInfo!=null&&orgInfo.getBuyerId()!=null&&!"".equals(orgInfo.getBuyerId())){	//判断是否为采购人
				if (bulletin.getAuditStatus()!=null&&(AuditStatusEnum.AUDIT_PASS).equals(bulletin.getAuditStatus())) {
					returnName = "bulletinView";//跳转到查看页面 
				} else {
					returnName = "blankView";
				}
			}
		} else {
				returnName = "blankView";
		}
		return new ModelAndView(returnName, model);
	}
	
	/** 
	 * FuncName:getPurchaseBulletinAuditByObjId
	 * Description :  采购办：桌面根据公告ID跳转到审核招标公告页面
	 * @param   request
	 * @return getPurchaseBulletinAuditByObjId
	 * @author yangx  
	 * @Create Date: 2010-9-7下午12:58:44 
	 * @Modifier   yangx
	 * @Modified Date: 2010-9-7下午12:58:44 
	 */
	@RequestMapping(params="method=getPurchaseBulletinAuditByObjId")
	public ModelAndView getPurchaseBulletinAuditByObjId(HttpServletRequest request)throws Exception {
		logger.debug("\nes=PurBulletinController||getPurchaseBulletinAuditByObjId\n");
		String objId = request.getParameter("objId");
		Map model=new HashMap();
		Bulletin bulletin = bulletinService.getBulletinByObjId(objId);//根据公告ID获取公告 
		model.put("bulletin", bulletin);
		return new ModelAndView("getBulletinAuditByObjId", model);//招标公告
	}
		
	/** 
	 * FuncName:toUpdatePurchaseBulletin
	 * Description :  代理机构：桌面根据招标公告Id跳转到修改页面
	 * @param   request
	 * @return  ModelAndView
	 * @author yangx
	 * @Create Date: 2010-9-7下午01:13:58 
	 * @Modifier  yangx
	 * @Modified Date: 2010-9-7下午01:13:58 
	 */
	@RequestMapping(params="method=toUpdatePurchaseBulletin")
	public ModelAndView toUpdatePurchaseBulletin(HttpServletRequest request)throws Exception {
		logger.debug("\nes=PurBulletinController||toUpdatePurchaseBulletin\n");
		String objId = request.getParameter("objId");
		String fromList = request.getParameter("fromList");//是否从列表跳转过来的标志
		String fromDesk = request.getParameter("fromDesk");//是否从桌面跳转过来的标志
		Map model=new HashMap();
		model.put("fromList",fromList);
		model.put("fromDesk",fromDesk);
		Bulletin bulletin = bulletinService.getBulletinByObjId(objId);	//根据公告ID获取公告 
		model.put("bulletin",bulletin);
		model.put("project",bulletin.getProject());
		return new ModelAndView("toUpdatePurchaseBulletin",model);
	}
}
