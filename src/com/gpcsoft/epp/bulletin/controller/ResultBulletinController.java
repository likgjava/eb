package com.gpcsoft.epp.bulletin.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.organization.service.OrgInfoService;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.utils.DateUtil;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.bulletin.domain.Bulletin;
import com.gpcsoft.epp.bulletin.domain.BulletinTypeEnum;
import com.gpcsoft.epp.bulletin.service.BulletinService;
import com.gpcsoft.epp.buyresult.domain.BuyResult;
import com.gpcsoft.epp.buyresult.domain.BuyWinner;
import com.gpcsoft.epp.buyresult.domain.ResultTypeEnum;
import com.gpcsoft.epp.buyresult.service.BuyResultService;
import com.gpcsoft.epp.buyresult.service.BuyWinnerService;
import com.gpcsoft.epp.common.domain.AuditStatusEnum;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.common.domain.EbuyMethodEnum;
import com.gpcsoft.epp.common.domain.SeparationEnum;
import com.gpcsoft.epp.common.service.UserApiService;
import com.gpcsoft.epp.common.utils.StringUtil;
import com.gpcsoft.epp.project.domain.ProjProcessStatusEnum;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.service.ProjectService;
import com.gpcsoft.epp.projreview.domain.OpenBidRecord;
import com.gpcsoft.epp.projreview.service.OpenBidRecordService;
import com.gpcsoft.epp.taskplan.domain.TaskPlanMSub;
import com.gpcsoft.epp.taskplan.domain.TaskPlanTypeEnum;
import com.gpcsoft.epp.taskplan.service.TaskPlanMSubService;
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
 *  @gpcsoft.view value="toViewResultBulletinDetail"
 *  url="view/es/planform/bulletin/resultBulletinTabForDetail.jsp"
 *  @gpcsoft.view value="toViewResultBulletinForSupplierTab"
 *  url="view/es/planform/bulletin/resultBulletinTabForSupplierTab.jsp"
 *  @gpcsoft.view value="resultPublicityTabView"
 *  url="view/es/planform/bulletin/resultPublicityTab.jsp"
 *  @gpcsoft.view value="resultPublicityTabForSuperviseView"
 *  url="view/es/planform/bulletin/resultPublicityTabForSupervise.jsp"
 *  @gpcsoft.view value="resultPublicityTabForSupplierTabView"
 *  url="view/es/planform/bulletin/resultPublicityTabForSupplierTab.jsp"
 *  @gpcsoft.view value="noOpenBidView"
 *  url="view/es/common/noOpenBid.jsp"
 */

@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={Bulletin.class})
@RequestMapping("/ResultBulletinController.do")//页面请求路径,可修改
@SuppressWarnings(value="unchecked")
public class ResultBulletinController extends AnnotationMultiController<Bulletin>{
	
	@Autowired(required=true) @Qualifier("bulletinServiceImpl")
	private BulletinService bulletinService;
	
	@Autowired(required=true) @Qualifier("freeMarkerServiceImpl")
	private FreeMarkerService freeMarkerService;
	
	@Autowired(required=true) @Qualifier("projectServiceImpl")
	private ProjectService projectService;
	
	@Autowired(required=true) @Qualifier("buyWinnerServiceImpl")
	private BuyWinnerService buyWinnerService;
	
	@Autowired(required=true) @Qualifier("buyResultServiceImpl")
	private BuyResultService buyResultService;
	
	@Autowired(required=true) @Qualifier("userApiServiceImpl")
	private UserApiService userApiService;
	
	@Autowired(required=true) @Qualifier("openBidRecordServiceImpl")
	private OpenBidRecordService openBidRecordService;
	
	@Autowired(required=true) @Qualifier("taskPlanMSubServiceImpl")
	private TaskPlanMSubService taskPlanMSubService;
	
	@Autowired(required=true) @Qualifier("orgInfoServiceImpl")
	private OrgInfoService orgInfoService;
	
	
	/** 
	 * FuncName : toViewResultBulletinDetail
	 * Description :  跳转到查看中标公告
	 * Create Date: 2011-11-15下午01:46:44 by yangx  
	 * Modified Date: 2011-11-15下午01:46:44 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=toViewResultBulletinDetail")
	public ModelAndView toViewResultBulletinDetail(HttpServletRequest request)throws Exception {
		String projectId = request.getParameter("projectId");//获取项目主键
		List<Project> projectList = projectService.getSubProjectByProjectId(projectId);
		if (projectList==null||projectList.size()<1) {
			Project project = projectService.getProjectByObjId(projectId);
			projectList = new ArrayList<Project>();
			projectList.add(project);
		}
		Map model = new HashMap();
		model.put("projectList", projectList);
		return new ModelAndView("toViewResultBulletinDetail", model);//跳转到页面
	}
	
	/** 
	 * FuncName:toDraftResultBulletin
	 * Description :代理机构：跳转到起草结果公告Tab页面
	 * @param   request
	 * @return  ModelAndView
	 * @author yangx
	 * @Create Date: 2010-9-10上午11:28:09 
	 * @Modifier  yangx
	 * @Modified Date: 2010-9-10上午11:28:09 
	 */
	@RequestMapping(params="method=toDraftResultBulletin")
	public ModelAndView toDraftResultBulletin(HttpServletRequest request)throws Exception {
		logger.debug("\nes ResultBulletinController||toDraftResultBulletin\n");
		String projectId = request.getParameter("projectId");//获取项目主键
		List<Project> projectList = projectService.getSubProjectByProjectId(projectId);
		if (projectList==null||projectList.size()<1) {
			Project project = projectService.getProjectByObjId(projectId);
			projectList = new ArrayList<Project>();
			projectList.add(project);
		}
		Map model = new HashMap();
		model.put("projectList", projectList);
		return new ModelAndView("toResultBulletinTab", model);//跳转到页面
	}	

	/**
	 * FuncName:getResultBulletin
	 * Description :代理机构：跳转到起草结果公告页面
	 * @param   request
	 * @return  ModelAndView
	 * @author yangx
	 */
	@RequestMapping(params="method=getResultBulletin")
	public ModelAndView getResultBulletin(HttpServletRequest request)throws Exception {
		logger.debug("\nes ResultBulletinController||getResultBulletin\n");
		String projectId = request.getParameter("projectId");//获取包组ID
		User user = AuthenticationHelper.getCurrentUser();
		OrgInfo orgInfo = (OrgInfo)user.getOrgInfo();
		Map model = new HashMap();
		String returnUrl = "resultBulletin";
		Bulletin bulletin = bulletinService.getResultBulletinByProjectId(projectId);// 根据项目ID获取结果公告
		BuyResult buyResult = buyResultService.getBuyResultBySubProjId(projectId);
		Project  subProject =  projectService.getProjectByObjId(projectId);
		Bulletin zbBulletin  = bulletinService.getPurBulletinByProjectId(projectId); //根据项目ID获取招标公告
		if(null==buyResult && !TaskPlanTypeEnum.DLBX.equals(subProject.getTenderType())){   //根据包组ID判断是否已经定标
			returnUrl="noOpenBidView";
			model.put("errorType", "noResultBid");
		}else{
			if (null!=bulletin && null!=bulletin.getObjId()) {//若有公告，则跳到公告显示页面，若无公告，则跳到新增页面
				model.put("bulletin", bulletin);
				returnUrl = "toUpdateResultBulletin";
				if(orgInfo.getAgencyId()!=null&&(CommonEnum.USER_STATUS_FORMAL).equals(bulletin.getUseStatus())&&!(AuditStatusEnum.AUDIT_NO_PASS).equals(bulletin.getAuditStatus())){
					returnUrl = "bulletinView";//跳转到查看页面
				}	
			}else{
				
				Project project = null;
				if(!StringUtil.empty(subProject.getParentId())){//有父节点
					project=projectService.get(subProject.getParentId());// subProject.getpa;
				}else{
					project=subProject;
				}
				
				String title="";
				if(EbuyMethodEnum.OPEN_BIDDING.equals(project.getEbuyMethod())){
					title = "公开招标";
				}else if(EbuyMethodEnum.INVITE_BIDDING.equals(project.getEbuyMethod())){
					title = "邀请招标";
				}else if(EbuyMethodEnum.NEGOTIATE.equals(project.getEbuyMethod())){
					title = "竞争性谈判";
				}else if(EbuyMethodEnum.INQUIRY.equals(project.getEbuyMethod())){
					title = "询价";
				}else if(EbuyMethodEnum.SINGLE_SOURCE.equals(project.getEbuyMethod())){
					title = "单一来源";
				}
				String companyName = user.getEmp().getCompany().getName();
				String agentcontact = user.getEmp().getCompany().getContact();
				String agentphone = user.getEmp().getCompany().getTel();
				String buyerContact = this.getBuyerContact(subProject.getBuyersId());
				String buyerTel = this.getBuyerTel(subProject.getBuyersId());
				Map templateMap = new HashMap();
				templateMap.put("companyName", companyName);
				templateMap.put("project", project);
				templateMap.put("agentcontact", (agentcontact==null||"null".equals(agentcontact))?"":agentcontact);
				templateMap.put("agentphone", (agentphone==null||"null".equals(agentphone))?"":agentphone);
				templateMap.put("buyercontact", (buyerContact==null||"null".equals(buyerContact))?"":buyerContact);
				templateMap.put("buytel", (buyerTel==null||"null".equals(buyerTel))?"":buyerTel);
				templateMap.put("subProject", subProject);
				OrgInfo o = userApiService.getOrgInfoByUser(AuthenticationHelper.getCurrentUser());
				templateMap.put("o", o);
				
				List<OpenBidRecord> openBidRecordList = openBidRecordService.getopenBidRecordListByPackId(projectId);
				
				List<OpenBidRecord> openBidRecordWinnerList = new ArrayList<OpenBidRecord>();
				List<BuyWinner> winList = buyWinnerService.getBuyWinnerBySubProjectId(projectId);//鏌ヨ琚‘瀹氱殑鎴愪氦渚涘簲鍟�
				for(Iterator<BuyWinner>iterator =winList.iterator();iterator.hasNext(); ){
					BuyWinner winner = iterator.next();
					if(ResultTypeEnum.DEAL_YES.equals(winner.getResultType())){ //成交
						for (Iterator iterator1 = openBidRecordList.iterator(); iterator1.hasNext();) {
							 OpenBidRecord openBidRecord = (OpenBidRecord) iterator1.next();
							 if(openBidRecord.getSupplier().getObjId().equals(winner.getSelllerId())){  //中标供应商
								 openBidRecordWinnerList.add(openBidRecord);
							 }
								 
						}
					}
					
					
				}
			
				List<TaskPlanMSub> taskPlanMSubList = taskPlanMSubService.getTaskPlanMSubListByProjectId(projectId);
				TaskPlanMSub taskPlanMSub = new TaskPlanMSub();
				if(taskPlanMSubList.size()>0){//判断申报书与条目中间表列表是否有值
					taskPlanMSub = taskPlanMSubList.get(0);
				}
				templateMap.put("title", title);  //采购方式
				if(zbBulletin!=null&&zbBulletin.getCreateTime()!=null){
					templateMap.put("zbBulletinDate", DateUtil.format(zbBulletin.getCreateTime(),"yyyy-MM-dd")); //招标公告日期
				}
				templateMap.put("today", DateUtil.format(new Date(),"yyyy-MM-dd"));
				templateMap.put("taskPlanMSub", taskPlanMSub);
				templateMap.put("openBidRecordWinnerList", openBidRecordWinnerList);
				templateMap.put("openBidRecordList", openBidRecordList);
				bulletin = new Bulletin();
				bulletin.setBulletinNo(bulletinService.createBulletinCodeByQO(null, subProject.getEbuyMethod()));//起草结果公告时默认初始化值
				bulletin.setBullTitle(subProject.getProjName());
				bulletin.setBullContents(freeMarkerService.getFreeMarkerContent("compositeGradeMethodOutbidBulletin.ftl", templateMap));//输出绑定的结果
				bulletin.setBullType(BulletinTypeEnum.RESULT_BULLETIN);//公告类型为结果公告
				bulletin.setProject(subProject);
				model.put("bulletin", bulletin);
			}
		}
		return new ModelAndView(returnUrl, model);//跳转到页面
	}
	
	/** 
	 * FunctionName:toViewResultBulletinForBuyer
	 * Description:采购人：跳转到查看结果公告Tab
	 * @param   request
	 * @return  ModelAndView
	 * @author yangx
	 * @Create Date: 2010-9-10上午11:28:09 
	 * @Modifier  yangx
	 * @Modified Date: 2010-9-10上午11:28:09 
	 */
	@RequestMapping(params="method=toViewResultBulletinForBuyer")
	public ModelAndView toViewResultBulletinForBuyer(HttpServletRequest request)throws Exception {
		logger.debug("\nes ResultBulletinController||toViewResultBulletinForBuyer\n");
		String projectId = request.getParameter("projectId");//获取项目ID
		List<Project> projectList = projectService.getSubProjectByProjectId(projectId);
		if (projectList==null||projectList.size()<1) {
			Project project = projectService.getProjectByObjId(projectId);
			projectList = new ArrayList<Project>();
			projectList.add(project);
		}
		Map model = new HashMap();
		model.put("projectList", projectList);
		return new ModelAndView("toViewResultBulletinForBuyer", model);//跳转到页面
	}
	
	/** 
	 * FuncName:toAuditResultBulletin
	 * Description :采购办:跳转到审核结果公告Tab页面
	 * @param   request
	 * @return ModelAndView
	 * @author yangx 
	 * @Create Date: 2010-9-10上午11:28:09 
	 * @Modifier yangx
	 * @Modified Date: 2010-9-10上午11:28:09 
	 */
	@RequestMapping(params="method=toAuditResultBulletin")
	public ModelAndView toAuditResultBulletin(HttpServletRequest request)throws Exception {
		logger.debug("\nes ResultBulletinController||toAuditResultBulletin\n");
		String projectId = request.getParameter("projectId");//获取项目ID
		List<Project> projectList = projectService.getSubProjectByProjectId(projectId);
		if (projectList==null||projectList.size()<1) {
			Project project = projectService.getProjectByObjId(projectId);
			projectList = new ArrayList<Project>();
			projectList.add(project);
		}
		Map model = new HashMap();
		model.put("projectList", projectList);
		return new ModelAndView("toResultBulletinTabForSupervise", model);//跳转到页面
	}
	
	/**
	 * FuncName:toBulletinAudit
	 * Description :采购办：跳转到审核结果公告页面
 	 * @param   request
	 * @return  ModelAndView
	 * @author yangx
	 */
	@RequestMapping(params="method=toBulletinAudit")
	public ModelAndView toBulletinAudit(HttpServletRequest request)throws Exception {
		logger.debug("\nes ResultBulletinController||toBulletinAudit\n");
		String projectId=request.getParameter("projectId");
		Map model=new HashMap();
		Bulletin bulletin = bulletinService.getResultBulletinByProjectId(projectId);//根据项目ID获取结果公告
		model.put("bulletin", bulletin);
		String returnUrl = "bulletinView";
		if (bulletin==null||(bulletin!=null&&bulletin.getAuditStatus()==null)) {//判断是否无公告或无审核状态
			returnUrl = "blank";//跳转到无数据页面
		}else if (bulletin.getAuditStatus()!=null&&(AuditStatusEnum.WAIT_AUDIT).equals(bulletin.getAuditStatus())) {//判断审核状态是否为待审核
			returnUrl = "toPublicityOrBulletin";//跳转到审核页面
		}
		return new ModelAndView(returnUrl, model);
	}
	
	/** 
	 * FuncName:getResultBulletinAuditByObjId
	 * Description :  采购办：桌面根据公告ID跳转到审核结果公告页面
	 * @param   request
	 * @return  ModelAndView
	 * @author yangx
	 * @Create Date: 2010-9-7下午12:58:44 
	 * @Modifier yangx
	 * @Modified Date: 2010-9-7下午12:58:44 
	 */
	@RequestMapping(params="method=getResultBulletinAuditByObjId")
	public ModelAndView getResultBulletinAuditByObjId(HttpServletRequest request)throws Exception {
		logger.debug("\nes ResultBulletinController||getResultBulletinAuditByObjId\n");
		String objId = request.getParameter("objId");
		Map model=new HashMap();
		Bulletin bulletin = bulletinService.getBulletinByObjId(objId);//根据公告ID获取公告
		model.put("bulletin", bulletin);
		return new ModelAndView("toPublicityOrBulletin", model);//结果公告
	}
	
	/** 
	 * FuncName:auditResultBulletin
	 * Description :  采购办：审核结果公告
	 * @param   ids:公告主键,passStatus:审核状态,bulletin:公告对象,stauts
	 * @return  ModelAndView
	 * @author yangx
	 * @Create Date: 2010-9-7下午03:07:58 
	 * @Modifier yangx
	 * @Modified Date: 2010-9-7下午03:07:58   
	 */
	@RequestMapping(params="method=auditResultBulletin")
	public ModelAndView auditResultBulletin(String ids,String passStatus, Bulletin bulletin,SessionStatus stauts)throws Exception {
		logger.debug("\nes ResultBulletinController||auditResultBulletin\n");
		if(ids==null||"".equals(ids)){
			ids = bulletin.getObjId();
		}
		bulletinService.saveResultBulletinForAudit(ids,passStatus,bulletin.getOpinion());//保存审核结果
		stauts.setComplete();
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/**
	 * FuncName:saveResultBulletin
	 * Description :代理机构：保存起草结果公告
	 * @param   request,bulletin 公告对象,stauts
	 * @return  ModelAndView
	 * @author yangx
	 */
	@RequestMapping(params="method=saveResultBulletin")
	public ModelAndView saveResultBulletin(HttpServletRequest request, Bulletin bulletin, SessionStatus stauts)throws Exception {
		logger.debug("\nes ResultBulletinController||saveResultBulletin\n");
		bulletin.setUseStatus(CommonEnum.USER_STATUS_TEMP);//设置状态为临时
		bulletin.setBullType(BulletinTypeEnum.RESULT_BULLETIN);//公告类型
		bulletinService.saveResultBulletin(bulletin,ProjProcessStatusEnum.DRAFT_RESULT_BULLETIN);//保存公告
		stauts.setComplete();//因为session会影响到页面取值，所以这里清除一下。
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/**
	 * FuncName:saveUpdateResultBulletin
	 * Description : 代理机构：保存修改结果公告
	 * @param   request,bulletin 公告对象,stauts
	 * @return  ModelAndView
	 * @author yangx
	 */
	@RequestMapping(params="method=saveUpdateResultBulletin")
	public ModelAndView saveUpdateResultBulletin(HttpServletRequest request, Bulletin bulletin, SessionStatus stauts)throws Exception {
		logger.debug("\nes=ResultBulletinController||saveUpdateResultBulletin\n");
		bulletin.setUseStatus(CommonEnum.USER_STATUS_TEMP);//设置状态为临时
		bulletin.setBullType(BulletinTypeEnum.RESULT_BULLETIN);//公告类型
		bulletinService.saveUpdateResultBulletin(bulletin,ProjProcessStatusEnum.DRAFT_RESULT_BULLETIN);//保存公告
		stauts.setComplete();//因为session会影响到页面取值，所以这里清除一下。
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/** 
	 * FuncName:submitResultBulletin
	 * Description :  代理机构：提交结果公告
	 * @param   Bulletin bulletin 公告对象
	 * @return  ModelAndView
	 * @author   yangx
	 * @Create Date: 2010-7-6上午11:21:14
	 * @Modifier  yangx
	 * @Modified Date: 2010-7-6上午11:21:14 
	 */
	@RequestMapping(params="method=submitResultBulletin")
	public ModelAndView submitResultBulletin(HttpServletRequest request, Bulletin bulletin, SessionStatus stauts)throws Exception {
		logger.debug("\nes=ResultBulletinController||submitResultBulletin\n");
		bulletin.setBullType(BulletinTypeEnum.RESULT_BULLETIN);//公告类型
		bulletinService.saveSubmitResultBulletin(bulletin,ProjProcessStatusEnum.SUBMIT_RESULT_BULLETIN);//保存公告
		stauts.setComplete();//因为session会影响到页面取值，所以这里清除一下。
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/** 
	 * FuncName:submitUpdateResultBulletin
	 * Description :  代理机构：提交修改结果公告
	 * @param   request,bulletin 公告对象,stauts
	 * @return ModelAndView 
	 * @author yangx
	 * @Create Date: 2010-7-6上午11:21:14 
	 * @Modifier yangx
	 * @Modified Date: 2010-7-6上午11:21:14 
	 */
	@RequestMapping(params="method=submitUpdateResultBulletin")
	public ModelAndView submitUpdateResultBulletin(HttpServletRequest request, Bulletin bulletin, SessionStatus stauts)throws Exception {
		logger.debug("\nes=ResultBulletinController||submitUpdateResultBulletin\n");
		bulletin.setUseStatus(CommonEnum.USER_STATUS_FORMAL);//设置使用状态为正式
		bulletin.setAuditStatus(AuditStatusEnum.WAIT_AUDIT);//设置审核状态为待审核
		bulletin.setBullType(BulletinTypeEnum.RESULT_BULLETIN);//公告类型
		bulletinService.saveSubmitResultBulletin(bulletin,ProjProcessStatusEnum.SUBMIT_RESULT_BULLETIN);//保存公告
		stauts.setComplete();//因为session会影响到页面取值，所以这里清除一下。
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/** 
	 * FuncName:toViewResultBulletinForSupplierTab
	 * Description :供应商：进入项目后查看结果公告Tab
	 * @param   toViewResultBulletinForSupplierTab
	 * @return  ModelAndView
	 * @author yangx
	 * @Create Date: 2010-9-10上午11:28:09  
	 * @Modifier  yangx
	 * @Modified Date: 2010-9-10上午11:28:09  
	 */
	@RequestMapping(params="method=toViewResultBulletinForSupplierTab")
	public ModelAndView toViewResultBulletinForSupplierTab(HttpServletRequest request)throws Exception {
		logger.debug("\nes ResultBulletinController||toViewResultBulletinForSupplierTab\n");
		String projectId = request.getParameter("projectId");//获取项目ID
		List<Project> projectList = projectService.getSubProjectByProjectId(projectId);
		if (projectList==null||projectList.size()<1) {
			Project project = projectService.getProjectByObjId(projectId);
			projectList = new ArrayList<Project>();
			projectList.add(project);
		}
		Map model = new HashMap();
		model.put("projectList", projectList);
		return new ModelAndView("toViewResultBulletinForSupplierTab", model);//跳转到页面
	}
	
	/** 
	 * FuncName:toViewResultBulletin
	 * Description : 查看结果公告
	 * @param   request
	 * @return ModelAndView
	 * @author yangx  
	 * @Create Date: 2010-7-30上午10:47:58 
	 * @Modifier   yangx
	 * @Modified Date: 2010-7-30上午10:47:58 
	 */
	@RequestMapping(params="method=toViewResultBulletin")
	public ModelAndView toViewResultBulletin(HttpServletRequest request)throws Exception {
		logger.debug("\nes=ResultBulletinController||toViewResultBulletin\n");
		String returnUrl = "bulletinView";
		String projectId = request.getParameter("projectId");//获取项目ID
		Bulletin bulletin = bulletinService.getResultBulletinByProjectId(projectId);//根据项目ID获取结果公告
		
		Map model=new HashMap();
		if(bulletin!=null){
			model.put("bulletin", bulletin);
		}else{
			returnUrl = "blank";
		}
		return new ModelAndView(returnUrl,model);
	}
	
	/** 
	 * FuncName:toViewResultBulletinForSupplier
	 * Description :供应商：根据结果公告Id查看结果公告
	 * @param   request
	 * @return ModelAndView
	 * @author yangx 
	 * @Create Date: 2010-7-30上午10:47:58 
	 * @Modifier yangx
	 * @Modified Date: 2010-7-30上午10:47:58 
	 */
	@RequestMapping(params="method=toViewResultBulletinForSupplier")
	public ModelAndView toViewResultBulletinForSupplier(HttpServletRequest request)throws Exception {
		logger.debug("\nes ResultBulletinController||toViewResultBulletinForSupplier\n");
		String objId = request.getParameter("objId");//获取项目ID
		Bulletin bulletin = bulletinService.getBulletinByObjId(objId);//根据结果公告ID获取结果公告
		Map model=new HashMap();
		model.put("bulletin", bulletin);
		String returnUrl = "blank";
		if(bulletin!=null&&bulletin.getObjId()!=null){
			returnUrl = "bulletinView";
		}
		return new ModelAndView(returnUrl,model);
	}
	
	/** 
	 * FuncName:toUpdateResultBulletin
	 * Description :  代理机构：桌面根据结果公告Id跳转到修改页面
	 * @param   request
	 * @return  ModelAndView
	 * @author yangx
	 * @Create Date: 2010-9-7下午01:13:58 
	 * @Modifier yangx
	 * @Modified Date: 2010-9-7下午01:13:58 by 
	 */
	@RequestMapping(params="method=toUpdateResultBulletin")
	public ModelAndView toUpdateResultBulletin(HttpServletRequest request)throws Exception {
		logger.debug("\nes ResultBulletinController||toUpdateResultBulletin\n");
		String objId = request.getParameter("objId");
		String fromList = request.getParameter("fromList");//是否从列表跳转过来的标志
		String fromDesk = request.getParameter("fromDesk");//是否从桌面跳转过来的标志
		Map model=new HashMap();
		model.put("fromList",fromList);
		model.put("fromDesk",fromDesk);
		Bulletin bulletin = bulletinService.getBulletinByObjId(objId);//根据公告ID获取公告 
		model.put("bulletin",bulletin);
		model.put("project",bulletin.getProject());
		return new ModelAndView("toUpdateResultBulletin",model);
	}
	
	/** 
	 * Description :  获取预算单位联系人
	 * Modified Date: 2011-7-14下午04:07:14 by chench
	 * @param   
	 * @return  
	 * @Exception   
	 */
	private String getBuyerContact(String buyerIds){
		//分割ids
		String buyerContact = "";
		if (buyerIds!=null&&!"".equals(buyerIds)) {//判断预算单位id是否为空
			String[] ids = buyerIds.split(SeparationEnum.COMMA);
			for (String id:ids) {
				OrgInfo orgInfo = orgInfoService.get(id);
				buyerContact +=orgInfo.getCompany().getContact()+"、";
			}
		}
		if (buyerContact!="") {//判断联系人是否为空
			buyerContact = buyerContact.substring(0,buyerContact.lastIndexOf("、"));
		}
		return buyerContact;
	}
	
	
	/** 
	 * Description :  获取预算单位电话
	 * Modified Date: 2011-7-14下午04:05:13 by chench
	 * @param   
	 * @return  
	 * @Exception   
	 */
	private String getBuyerTel(String buyerIds){
		//分割ids
		String buyerTel = "";
		if (buyerIds!=null&&!"".equals(buyerIds)) {//判断预算单位id是否为空
			String[] ids = buyerIds.split(SeparationEnum.COMMA);
			for (String id:ids) {
				OrgInfo orgInfo = orgInfoService.get(id);
				buyerTel +=orgInfo.getCompany().getTel()+"、";
			}
		}
		if (buyerTel!="") {//判断电话是否为空
			buyerTel = buyerTel.substring(0,buyerTel.lastIndexOf("、"));
		}
		return buyerTel;
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
	
}
