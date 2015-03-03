package com.gpcsoft.epp.projreview.controller;

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
import com.gpcsoft.epp.bid.domain.Bid;
import com.gpcsoft.epp.bid.service.BidService;
import com.gpcsoft.epp.bulletin.domain.Bulletin;
import com.gpcsoft.epp.bulletin.domain.BulletinTypeEnum;
import com.gpcsoft.epp.common.domain.AuditStatusEnum;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.common.domain.EbuyMethodEnum;
import com.gpcsoft.epp.negotationrecord.domain.NegotationRecord;
import com.gpcsoft.epp.negotationrecord.service.NegotationRecordService;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.domain.ProjectRule;
import com.gpcsoft.epp.project.service.ProjectService;
import com.gpcsoft.epp.projreview.domain.EvaSellerRecord;
import com.gpcsoft.epp.projreview.domain.EvalBidRecord;
import com.gpcsoft.epp.projreview.domain.EvalFactorResult;
import com.gpcsoft.epp.projreview.service.EvaSellerRecordService;
import com.gpcsoft.epp.projreview.service.EvalBidRecordService;
import com.gpcsoft.epp.purchasedoc.domain.DosBuyRecord;
import com.gpcsoft.epp.purchasedoc.service.DosBuyRecordService;
import com.gpcsoft.epp.signuprecord.domain.SignUprecord;
import com.gpcsoft.epp.signuprecord.service.SignUprecordService;
import com.gpcsoft.epp.workgroup.domain.WorkGroupTypeEnum;
import com.gpcsoft.epp.workgroup.domain.WorkMemberTypeEnum;
import com.gpcsoft.epp.workgroup.service.WorkgMemberService;
import com.gpcsoft.srplatform.common.assist.service.FreeMarkerService;

/**
  * @gpcsoft.view value="evaSellerRecordFormView"
  *  url="view/es/planform/projreview/evaSellerRecordForm.jsp"
  * @gpcsoft.view value="evaSellerRecordTreeFormView"
  *  url="view/es/planform/projreview/evaSellerRecordTreeForm.jsp"
  * @gpcsoft.view value="evaSellerRecordListView"
  *  url="view/es/planform/projreview/evaSellerRecordList.jsp"
  * @gpcsoft.view value="evaSellerRecordDetailView"
  *  url="view/es/planform/projreview/evaSellerRecordDetail.jsp"
  * @gpcsoft.view value="newEvalSellerRecordListView"
  *  url="view/es/planform/projreview/evalBidRecordListView.jsp"
  * @gpcsoft.view value="newEvalSellerRecordView"
  *  url="view/es/planform/projreview/evaSellerRecordForm.jsp"
  *  @gpcsoft.view value="EvalSellerRecordPageView"
  *  url="view/es/planform/projreview/evalBidRecordListPage.jsp"
  *  @gpcsoft.view value="evalBidRecordResultListView"
  *  url="view/es/planform/projreview/evalBidRecordResultListView.jsp"
  *  @gpcsoft.view value="evalBidRecordResultListView2"
  *  url="view/es/planform/projreview/evalBidRecordResultListView2.jsp"
  *  @gpcsoft.view value="toKeyInEvalList"
  *  url="view/es/planform/projreview/evalBidRecordResultList.jsp"
  *  @gpcsoft.view value="evalBidRecordResultListPageView"
  *  url="view/es/planform/projreview/evalBidRecordResultListPage.jsp"
  *  @gpcsoft.view value="getEvaSellerRecordResultListForView"
  *  url="view/es/planform/projreview/evalBidRecordResultListForView.jsp"
  *  @gpcsoft.view value="AuditMessagePageView"
  *  url="view/es/planform/projreview/AuditMessagePage.jsp"
  *  @gpcsoft.view value="bidEvaluationReportView"
  *  url="view/es/planform/projreview/bidEvaluationReportView.jsp"
  *  @gpcsoft.view value="toViewBidEvaluationReport"
  *  url="view/es/planform/projreview/bidEvaluationReportViewList.jsp"
  *  @gpcsoft.view value="bidEvaluationReportFormView"
  *  url="view/es/planform/projreview/bidEvaluationReportForm.jsp"
  *  @gpcsoft.view value="bidEvaluationReportPageView"
  *  url="view/es/planform/projreview/bidEvaluationReportPage.jsp"
  *  @gpcsoft.view value="expertEvalBidRecordListView"
  *  url="view/es/planform/projreview/expertEvalBidRecordListView.jsp"
  *  @gpcsoft.view value="keyInEvalPageView"
  *  url="view/es/planform/projreview/keyInEvalPage.jsp"
  *  @gpcsoft.view value="noOpenBidView"
  *  url="view/es/common/noOpenBid.jsp"
  *  @gpcsoft.view value="bidFirstTrialTabPage"
  *  url="view/es/planform/projreview/bidFirstTrialTabPage.jsp"
  *  @gpcsoft.view value="bidSecondTrialTabPage"
  *  url="view/es/planform/projreview/bidSecondTrialTabPage.jsp"
  *  @gpcsoft.view value="bidFirstTrialPage"
  *  url="view/es/planform/projreview/bidFirstTrialPage.jsp"
  *  @gpcsoft.view value="bidSecondTrialPage"
  *  url="view/es/planform/projreview/bidSecondTrialPage.jsp"
  *  
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={EvaSellerRecord.class})
@RequestMapping("/EvaSellerRecordController.do")//页面请求路径,可修改
@SuppressWarnings("unchecked")
public class EvaSellerRecordController extends AnnotationMultiController<EvaSellerRecord> {

	@Autowired(required=true) @Qualifier("evaSellerRecordServiceImpl")
	private EvaSellerRecordService evaSellerRecordService;
	
	@Autowired(required=true) @Qualifier("bidServiceImpl")
	private BidService bidService;
    
	@Autowired(required=true) @Qualifier("projectServiceImpl")
	private ProjectService projectService;
	
	@Autowired(required=true) @Qualifier("freeMarkerServiceImpl")
	private FreeMarkerService freeMarkerService;
	
	@Autowired(required=true) @Qualifier("evalBidRecordServiceImpl")
	private EvalBidRecordService evalBidRecordService;
	
	@Autowired(required=true) @Qualifier("dosBuyRecordServiceImpl")
	private DosBuyRecordService dosBuyRecordService;
	
	@Autowired(required=true) @Qualifier("workgMemberServiceImpl")
	private WorkgMemberService workgMemberService;
	
	@Autowired(required=true) @Qualifier("negotationRecordServiceImpl")
	private NegotationRecordService negotationRecordService;
	
	@Autowired(required=true) @Qualifier("signUprecordServiceImpl")
	private SignUprecordService signUprecordService;
	/**
	 * FuncName:toEvaSellerRecordResult
	 * Description : 跳转到查看评标结果列表页面
	 * @param   request,stauts
	 * @return  ModelAndView
	 * @author 	   liuke
	 * @Create Date: 2010-6-4上午10:29:12 
	 * @Modifier   liuke
	 * @Modified Date: 2010-6-4上午10:29:12  
	 */
	@RequestMapping(params= "method=toEvaSellerRecordResult")
	public ModelAndView toEvaSellerRecordResult(HttpServletRequest request,SessionStatus stauts)throws Exception { 
		logger.debug("\nes EvaSellerRecordController||toEvaSellerRecordResult\n");
		String projectId = request.getParameter("projectId");
		Project project = projectService.getProjectByObjId(projectId);
		ProjectRule projectRule = projectService.getProjectRuleByProjectId(projectId);//项目规则
		Map model = new HashMap();
		List subProjectList = evaSellerRecordService.getSubProjectByProjectId(projectId);
		if(subProjectList.size()==0&&!projectRule.getIsDividePack()){//如果项目没分包
 			subProjectList.add(project);
 		}
		model.put("subProjectList", subProjectList);
		return new ModelAndView("evalBidRecordResultListView",model);
	}
	
	/**
	 * FuncName:toBidEvaluationReport
	 * Description:跳转到评标报告页面
	 * @param   request,stauts
	 * @return  ModelAndView
	 * @author 	   liuke
	 * @Create Date: 2010-6-4上午10:29:12 
	 * @Modifier   liuke
	 * @Modified Date: 2010-6-4上午10:29:12 
	 */
	@RequestMapping(params= "method=toBidEvaluationReport")
	public ModelAndView toBidEvaluationReport(HttpServletRequest request,SessionStatus stauts)throws Exception { 
		logger.debug("\nes EvaSellerRecordController||toBidEvaluationReport\n");
		String projectId = request.getParameter("projectId");
		Project project = projectService.getProjectByObjId(projectId);
		ProjectRule projectRule = projectService.getProjectRuleByProjectId(projectId);//项目规则
		Map model = new HashMap();
		List subProjectList = evaSellerRecordService.getSubProjectByProjectId(projectId);
		if(subProjectList.size()==0&&!projectRule.getIsDividePack()){//如果项目没分包
 			subProjectList.add(project);
 		}
		model.put("subProjectList", subProjectList);
		return new ModelAndView("bidEvaluationReportView",model);
	}
	
	
	/** 
	 * FuncName : toViewBidEvaluationReport
	 * Description :  跳转到查看评标报告页面
	 * Create Date: 2011-11-15下午02:15:07 by yangx  
	 * Modified Date: 2011-11-15下午02:15:07 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params= "method=toViewBidEvaluationReport")
	public ModelAndView toViewBidEvaluationReport(HttpServletRequest request,SessionStatus stauts)throws Exception { 
		logger.debug("\nes EvaSellerRecordController||toBidEvaluationReport\n");
		String projectId = request.getParameter("projectId");
		Project project = projectService.getProjectByObjId(projectId);
		ProjectRule projectRule = projectService.getProjectRuleByProjectId(projectId);//项目规则
		Map model = new HashMap();
		List subProjectList = evaSellerRecordService.getSubProjectByProjectId(projectId);
		if(subProjectList.size()==0&&!projectRule.getIsDividePack()){//如果项目没分包
 			subProjectList.add(project);
 		}
		model.put("subProjectList", subProjectList);
		return new ModelAndView("toViewBidEvaluationReport",model);
	}
	
	
	/** 
	 * FuncName : getEvaSellerRecordResultListForView
	 * Description :  跳转到查看评审结果列表
	 * Create Date: 2011-11-15下午02:41:44 by yangx  
	 * Modified Date: 2011-11-15下午02:41:44 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params= "method=getEvaSellerRecordResultListForView")
	public ModelAndView getEvaSellerRecordResultListForView(HttpServletRequest request,SessionStatus stauts)throws Exception { 
		logger.debug("\nes EvaSellerRecordController||getEvaSellerRecordResultList\n");
		String subProjId = request.getParameter("subProjId");
		String type = request.getParameter("type");
		List evaSellerRecordList = evaSellerRecordService.getEvaSellerRecordListByProjectId(subProjId);
		Project project = projectService.getProjectByObjId(subProjId);   //add by caojz 2011-12-19 临时
		List<SignUprecord> signUprecordList = signUprecordService.getSignupRecordList(subProjId); //add by caojz 2011-12-19 临时
		Map model = new HashMap();
		model.put("evaSellerRecordList", evaSellerRecordList);
		model.put("type", type);
		model.put("signUprecordList", signUprecordList);
		model.put("project", project);
		return new ModelAndView("getEvaSellerRecordResultListForView",model);
	}
	
	/**
	 * FuncName:getEvaSellerRecordResultList
	 * Description:得到评标结果列表 
	 * @param   request,stauts
	 * @return  ModelAndView
	 * @author   liuke
	 * @Create Date: 2010-6-23下午12:19:40 
	 * @Modifier liuke
	 * @Modified Date: 2010-6-23下午12:19:40  
	 */
	@RequestMapping(params= "method=getEvaSellerRecordResultList")
	public ModelAndView getEvaSellerRecordResultList(HttpServletRequest request,SessionStatus stauts)throws Exception { 
		logger.debug("\nes EvaSellerRecordController||getEvaSellerRecordResultList\n");
		String subProjId = request.getParameter("subProjId");
		String type = request.getParameter("type");
		List evaSellerRecordList = evaSellerRecordService.getEvaSellerRecordListByProjectId(subProjId);
		Project project = projectService.getProjectByObjId(subProjId);   //add by caojz 2011-12-19 临时
		List<SignUprecord> signUprecordList = signUprecordService.getSignupRecordList(subProjId); //add by caojz 2011-12-19 临时
		Map model = new HashMap();
		model.put("evaSellerRecordList", evaSellerRecordList);
		model.put("signUprecordList", signUprecordList);
		model.put("project", project);
		model.put("type", type);
		return new ModelAndView("evalBidRecordResultListPageView",model);
	}
	
	/**
	 * FuncName:getBidEvaluationReport
	 * Description:得到录入评标报告信息  
	 * @param  request,stauts
	 * @return  ModelAndView
	 * @author 	   liuke
	 * @Create Date: 2010-6-23下午12:19:40 
	 * @Modifier   liuke
	 * @Modified Date: 2010-6-23下午12:19:40 
	 */
	@RequestMapping(params= "method=getBidEvaluationReport")
	public ModelAndView getBidEvaluationReport(HttpServletRequest request,SessionStatus stauts)throws Exception { 
		String subProjId = request.getParameter("subProjId");
		Map model = new HashMap();
		model.put("subProjId", subProjId);
		return new ModelAndView("bidEvaluationReportFormView",model);
	}
	
	/** 
	 * FuncName : finishEvaSellerRecord
	 * Description :  完成评审打分
	 * Create Date: 2011-12-20 上午10:55:33 by caojz  
	 * Modified Date: 2011-12-20 上午10:55:33 by caojz
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=finishEvaSellerRecord")
	public ModelAndView finishBuyResult(HttpServletRequest request)throws Exception{
		String projectId = request.getParameter("projectId");
		evaSellerRecordService.finishEvaSellerRecord(projectId);
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	
	/**
	 * FuncName:saveAllEvaSellerRecord
	 * Description :  新增评标记录
	 * @param   request,evaSellerRecord:开标记录,status
	 * @return  ModelAndView
	 * @author 	 liuke
	 * @Create Date: 2010-6-4下午05:06:24 
	 * @Modifier liuke
	 * @Modified Date: 2010-6-4下午05:06:24  
	 */
	@RequestMapping(params= "method=saveAllEvaSellerRecord")
	public ModelAndView saveAllEvaSellerRecord(HttpServletRequest request, EvaSellerRecord evaSellerRecord, SessionStatus status)throws Exception {
		logger.debug("\nes EvaSellerRecordController||saveAllEvaSellerRecord\n");
		String supplierId = request.getParameter("supplierId");
		String projectId = request.getParameter("projectId");
		OrgInfo supplier = evaSellerRecordService.getOrgInfobyObjId(supplierId);
		evaSellerRecord.setSupplierName(supplier.getOrgName());
		evaSellerRecord.setSupplier(supplier);
		evaSellerRecordService.saveAllEvaSellerRecord(evaSellerRecord,projectId);
		Map model = new HashMap();
		model.put("evaSellerRecord", evaSellerRecord);
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	/**
	 * FuncName:updateAllEvaSellerRecord
	 * Description :修改评标记录
	 * @param   request,evaSellerRecord:开标记录,status
	 * @return  ModelAndView
	 * @author 		liuke
	 * @Create Date: 2010-6-4下午05:06:24 
	 * @Modifier   liuke
	 * @Modified Date: 2010-6-4下午05:06:24 
	 */
	@RequestMapping(params= "method=updateAllEvaSellerRecord")
	public ModelAndView updateAllEvaSellerRecord(HttpServletRequest request, EvaSellerRecord evaSellerRecord, SessionStatus status)throws Exception {
		logger.debug("\nes EvaSellerRecordController||updateAllEvaSellerRecord\n");
		String supplierId = request.getParameter("supplierId");
		String projectId = request.getParameter("projectId");
		OrgInfo supplier = evaSellerRecordService.getOrgInfobyObjId(supplierId);
		evaSellerRecord.setSupplierName(supplier.getOrgName());
		evaSellerRecord.setSupplier(supplier);
		evaSellerRecordService.updateAllEvaSellerRecord(evaSellerRecord,projectId);
		Map model = new HashMap();
		model.put("evaSellerRecord", evaSellerRecord);
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	/**
	 * FuncName:getAllAuditMessage
	 * Description:得到该供应商的评审信息 
	 * @param   request,status
	 * @return ModelAndView
	 * @author liuke  
	 * @Create Date: 2010-6-4下午05:06:24
	 * @Modifier liuke 
	 * @Modified Date: 2010-6-4下午05:06:24 
	 */
	@RequestMapping(params= "method=getAllAuditMessage")
	public ModelAndView getAllAuditMessage(HttpServletRequest request,SessionStatus status)throws Exception {
		logger.debug("\nes EvaSellerRecordController||getAllAuditMessage\n");
		String supplierId = request.getParameter("supplierId");
		String subProjId = request.getParameter("subProjId");
		List<EvaSellerRecord> evaSellerRecordlist = evaSellerRecordService.getAllAuditMessage(supplierId, subProjId);
		for(Iterator<EvaSellerRecord>iterator = evaSellerRecordlist.iterator();iterator.hasNext();){
			EvaSellerRecord evaSellerRecord = iterator.next();
			EvalBidRecord evalBidRecord = evalBidRecordService.getEvalBidRecordByObjId(evaSellerRecord.getEvalId());
			evaSellerRecord.setExpertName(evalBidRecord.getEvalLinkerName());
		}
		Map model = new HashMap();
		model.put("evaSellerRecordlist", evaSellerRecordlist);
		return new ModelAndView("AuditMessagePageView",model);
	}
	
	/** 
	 * FuncName : toViewBidEvaluationReportDetail
	 * Description :  查看评标报告
	 * Create Date: 2011-11-15下午02:21:40 by yangx  
	 * Modified Date: 2011-11-15下午02:21:40 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=toViewBidEvaluationReportDetail")
	public ModelAndView toViewBidEvaluationReportDetail(HttpServletRequest request)throws Exception {
		logger.debug("\nes EvaSellerRecordController||toViewBidEvaluationReportDetail\n");
		String projectId = request.getParameter("subProjId");//获取项目ID
		String returnUrl = "";
		Map model = new HashMap();
		//要对包组进行判断，
		//如果招标方式是"竞争性谈判" 或"单一来源"或"邀请招标"且有"谈判记录"才可以进行评审报告的起草工作
		//如果招标方式是"公开招标"且有评标结果时才可以进行评审报告的起草工作
		//根据包组ID查询谈判记录信息。
		Project project = projectService.getProjectByObjId(projectId);	//根据项目ID获取项目
		boolean isNext = false;
		String errorType = "";
		if(EbuyMethodEnum.NEGOTIATE.equals(project.getEbuyMethod())||EbuyMethodEnum.SINGLE_SOURCE.equals(project.getEbuyMethod())||EbuyMethodEnum.INVITE_BIDDING.equals(project.getEbuyMethod())){
			String prjId = project.getParentId()==null?project.getObjId():project.getParentId();
			String subPrjId = project.getParentId()==null?null:project.getObjId();
			List<NegotationRecord> list = negotationRecordService.searchNegotationRecordListBySubProjId(subPrjId,prjId);
			isNext = list.isEmpty();
			errorType = "noEvlBid";
		}else if(EbuyMethodEnum.OPEN_BIDDING.equals(project.getEbuyMethod())){
			List<EvaSellerRecord> list = this.evaSellerRecordService.getEvaSellerRecordList(projectId); //根据项目/包组ID获取评审结果
			isNext = list.isEmpty();
			errorType = "noReviewResult";
		}		
		if(isNext) {
			model.put("errorType",errorType);
			returnUrl ="noOpenBidView";
		}else {
			Bulletin bulletin = evaSellerRecordService.getbidEvaluationReportByProjectId(projectId);	//根据项目ID获取评标报告
			model.put("bulletin", bulletin);
			if(bulletin.getAuditStatus()!=null&&(CommonEnum.USER_STATUS_FORMAL).equals(bulletin.getUseStatus())){//判断采购公告使用状态是否为正式
				returnUrl = "bidEvaluationReportPageView";//跳转到查看页面
			}
		}
		return new ModelAndView(returnUrl, model);	
	}
	
	/**
	 * FuncName:toShowBidEvaluationReport
	 * Description:跳转评标报告显示页面:起草
	 * @param   request
	 * @return  ModelAndView
	 * @author liuke
	 */
	@RequestMapping(params="method=toShowBidEvaluationReport")
	public ModelAndView toShowBidEvaluationReport(HttpServletRequest request)throws Exception {
		logger.debug("\nes EvaSellerRecordController||toShowBidEvaluationReport\n");
		String projectId = request.getParameter("subProjId");//获取项目ID
		String returnUrl = "";
		Map model = new HashMap();
		//要对包组进行判断，
		//如果招标方式是"竞争性谈判" 或"单一来源"或"邀请招标"且有"谈判记录"才可以进行评审报告的起草工作
		//如果招标方式是"公开招标"且有评标结果时才可以进行评审报告的起草工作
		//根据包组ID查询谈判记录信息。
		Project project = projectService.getProjectByObjId(projectId);	//根据项目ID获取项目
		boolean isNext = false;
		String errorType = "";
		if(EbuyMethodEnum.NEGOTIATE.equals(project.getEbuyMethod())||EbuyMethodEnum.SINGLE_SOURCE.equals(project.getEbuyMethod())||EbuyMethodEnum.INVITE_BIDDING.equals(project.getEbuyMethod())){
			String prjId = project.getParentId()==null?project.getObjId():project.getParentId();
			String subPrjId = project.getParentId()==null?null:project.getObjId();
			List<NegotationRecord> list = negotationRecordService.searchNegotationRecordListBySubProjId(subPrjId,prjId);
			isNext = list.isEmpty();
			errorType = "noEvlBid";
		}else if(EbuyMethodEnum.OPEN_BIDDING.equals(project.getEbuyMethod())){
			//List<EvalBidRecord> list = evalBidRecordService.getEvalBidRecordBySubProjectId(projectId);
			List<EvaSellerRecord> list = this.evaSellerRecordService.getEvaSellerRecordList(projectId); //根据项目/包组ID获取评审结果
			isNext = list.isEmpty();
			errorType = "noReviewResult";
		}		
		
		if(isNext) {
			model.put("errorType",errorType);
			returnUrl ="noOpenBidView";
		}
		else {
			Bulletin bulletin = evaSellerRecordService.getbidEvaluationReportByProjectId(projectId);	//根据项目ID获取评标报告
			returnUrl ="bidEvaluationReportFormView"; //跳转到起草评标报告页面
			if(null != bulletin && null != bulletin.getObjId()){//若有评标报告，则跳到评标报告显示页面，若无评标报告，则跳到新增页面
				model.put("bulletin", bulletin);
				if(bulletin.getAuditStatus()!=null&&(CommonEnum.USER_STATUS_FORMAL).equals(bulletin.getUseStatus())){//判断采购公告使用状态是否为正式
					returnUrl = "bidEvaluationReportPageView";//跳转到查看页面
				}
			}else{
				Map templateMap = new HashMap();
				
				ProjectRule projectRule = projectService.getProjectRuleByProjectId(projectId);
				if(project.getParentId()!=null){
					projectRule = projectService.getProjectRuleByProjectId(project.getParentId());
				}
				Project parentProject = new Project();
				if(project.getParentId()!=null&&!"".equals(project.getParentId())){  //说明该对象是包件
					parentProject = projectService.getProjectByObjId(project.getParentId());
				}else{
					parentProject = project;
				}
				String totalPrice = projectService.getSumTaskPlanDetailProjectId(parentProject.getObjId());//预算
				//one
				int purchase_num = 0;//购买招标文件人数
				StringBuilder purchase_names = new StringBuilder();//购买供应商名称
				List<DosBuyRecord> records = dosBuyRecordService.getByProjectId(parentProject.getObjId());
				for (DosBuyRecord record : records) {
					if(DosBuyRecord.STATUS_PAID.equals(record.getUseStatus())){
						purchase_num++;
						purchase_names.append(record.getSupplierName()+",");
					}
				}
				if(purchase_names.toString().indexOf(",")>-1){
					templateMap.put("purchase_names", purchase_names.toString().substring(0, purchase_names.toString().length()-1));
				}else{
					templateMap.put("purchase_names", "");
				}
				templateMap.put("purchase_num", purchase_num);
				//two
				int bid_num = 0;//投标供应商数
				StringBuilder bid_names = new StringBuilder();//投标供应商名称
				List<Bid> bids = bidService.getAllBidByProjectId(projectId);
				for (Bid bid : bids) {
						bid_num++;
						bid_names.append(bid.getSupplierName()+",");
				}
				if(bid_names.toString().indexOf(",")>-1){
					templateMap.put("bid_names", bid_names.toString().substring(0, bid_names.toString().length()-1));
				}else{
					templateMap.put("bid_names", "");
				}
				templateMap.put("bid_num", bid_num);
				
				templateMap.put("project", parentProject);
				templateMap.put("subProject", project);
				templateMap.put("totalPrice", totalPrice);
				templateMap.put("today", new Date());
				templateMap.put("projectRule", projectRule);
				bulletin = new Bulletin();
				bulletin.setProject(project);
				bulletin.setAuditStatus(AuditStatusEnum.WAIT_AUDIT);
				bulletin.setBullContents(freeMarkerService.getFreeMarkerContent("bidEvaluationReport.ftl", templateMap));//输出绑定的结果
				model.put("bullType", BulletinTypeEnum.BID_EVALUATION_REPORT);//公告类型
				model.put("bulletin", bulletin);
				}
		}
		return new ModelAndView(returnUrl, model);	
	}
	
	/**
	 * FuncName:saveBidEvaluationReport
	 * Description :  保存评标报告：起草
	 * @param   request,bulletin:评标报告,status
	 * @return ModelAndView
	 * @author 	   liuke  
	 * @Create Date: 2010-6-4下午05:06:24 
	 * @Modifier   liuke
	 * @Modified Date: 2010-6-4下午05:06:24 
	 */
	@RequestMapping(params= "method=saveBidEvaluationReport")
	public ModelAndView saveBidEvaluationReport(HttpServletRequest request,Bulletin bulletin, SessionStatus status)throws Exception {
		logger.debug("\nes EvaSellerRecordController||saveBidEvaluationReport\n");
		bulletin.setUseStatus(CommonEnum.USER_STATUS_TEMP);//设置状态为临时
		bulletin.setBullType(BulletinTypeEnum.BID_EVALUATION_REPORT);
		bulletin.setBullTitle(bulletin.getBullTitle()+"评标报告");
		evaSellerRecordService.saveBidEvaluationReport(bulletin); //评标报告
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/**
	 * FuncName:saveSubmitBidEvaluationReport
	 * Description :  保存评标报告：提交
	 * @param   request,bulletin:评标报告,status
	 * @return ModelAndView
	 * @author liuke  
	 * @Create Date: 2010-6-4下午05:06:24  
	 * @Modifier liuke
	 * @Modified Date: 2010-6-4下午05:06:24 
	 */
	@RequestMapping(params= "method=saveSubmitBidEvaluationReport")
	public ModelAndView saveSubmitBidEvaluationReport(HttpServletRequest request,Bulletin bulletin, SessionStatus status)throws Exception {
		logger.debug("\nes EvaSellerRecordController||saveSubmitBidEvaluationReport\n");
		bulletin.setBullType(BulletinTypeEnum.BID_EVALUATION_REPORT);
		bulletin.setBullTitle(bulletin.getBullTitle()+"评标报告");
		evaSellerRecordService.saveSubmitBidEvaluationReport(bulletin);//评标报告
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/**
	 * FuncName:toKeyInEvalPage
	 * Description: 跳转到录入评审结果页面
	 * @param projectId:项目主键
	 * @return ModelAndView
	 * @author liuke
	 * @Create Date 2010-8-1 下午01:42:30  
	 */
	@RequestMapping(params = "method=toKeyInEvalPage")
	public ModelAndView toKeyInEvalPage(String projectId)throws Exception {
		logger.debug("\nes EvaSellerRecordController||toKeyInEvalPage\n");
		Map model = new HashMap();
		Project project = projectService.getProjectByObjId(projectId);
		ProjectRule projectRule = projectService.getProjectRuleByProjectId(projectId);//项目规则
 		List subProjectList = evaSellerRecordService.getSubProjectByProjectId(projectId);
 		if(subProjectList.size()==0&&!projectRule.getIsDividePack()){//如果项目没分包
 			subProjectList.add(project);
 		}
		model.put("subProjectList", subProjectList);
		model.put("type", "keyin");
		return new ModelAndView("evalBidRecordResultListView",model);  
	}
	
	/**
	 * FuncName:saveCreateEvaSellerRecord
	 * Description: 创建评审记录与批量保存评审打分
	 * @param evaSellerRecord 评审记录
	 * @param request,status,evaSellerRecord:开标记录,evalFactorResults:评审打分JSON对象字符串,packId:包组主键
	 * @return ModelAndView
	 * @author wanghz
	 * @Create Date 2010-10-9 下午06:02:58 
	 */
	@RequestMapping(params= "method=saveCreateEvaSellerRecord")
	public ModelAndView saveCreateEvaSellerRecord(HttpServletRequest request,SessionStatus status,EvaSellerRecord evaSellerRecord,String evalFactorResults,String packId){
		logger.debug("\nes EvaSellerRecordController||saveCreateEvaSellerRecord\n");
		List<EvalFactorResult> evalFactorResultList = new ArrayList<EvalFactorResult>();
		JSONArray jsonArray = JSONArray.fromObject(evalFactorResults);
		if (null != jsonArray && jsonArray.isArray()) {
			for(int i=0;i<jsonArray.size();i++){
				JSONObject jSONObject = (JSONObject)jsonArray.get(i);
				EvalFactorResult evalFactorResult = (EvalFactorResult)JSONObject.toBean(jSONObject,EvalFactorResult.class);
				evalFactorResultList.add(evalFactorResult);
			}
		}
		evaSellerRecordService.saveCreateEvaSellerRecord(evaSellerRecord,evalFactorResultList,packId);
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/**
	 * FuncName:updateEvaSellerRecord
	 * Description: 更新评审记录与批量保存评审打分
	 * @param request,status,evaSellerRecord:评审记录,evalFactorResults:评审打分JSON对象字符串,packId:包组主键
	 * @return ModelAndView
	 * @author wanghz
	 * @Create Date 2010-10-9 下午06:02:58  
	 */
	@RequestMapping(params= "method=updateEvaSellerRecord")
	public ModelAndView updateEvaSellerRecord(HttpServletRequest request,SessionStatus status,EvaSellerRecord evaSellerRecord,String evalFactorResults,String packId){
		logger.debug("\nes EvaSellerRecordController||updateEvaSellerRecord\n");
		List<EvalFactorResult> evalFactorResultList = new ArrayList<EvalFactorResult>();
		JSONArray jsonArray = JSONArray.fromObject(evalFactorResults);
		if (null != jsonArray && jsonArray.isArray()) {
			for(int i=0;i<jsonArray.size();i++){
				JSONObject jSONObject = (JSONObject)jsonArray.get(i);
				EvalFactorResult evalFactorResult = (EvalFactorResult)JSONObject.toBean(jSONObject,EvalFactorResult.class);
				evalFactorResultList.add(evalFactorResult);
			}
		}
		evaSellerRecordService.updateEvaSellerRecord(evaSellerRecord,evalFactorResultList,packId);
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	
	/** 
	 * FuncName : toKeyInEvalList
	 * Description :  跳转到查看评审结果
	 * Create Date: 2011-11-15下午02:36:44 by yangx  
	 * Modified Date: 2011-11-15下午02:36:44 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toKeyInEvalList")
	public ModelAndView toKeyInEvalList(String projectId)throws Exception {
		logger.debug("\nes EvaSellerRecordController||toKeyInEvalList\n");
		Map model = new HashMap();
		Project project = projectService.getProjectByObjId(projectId);
		ProjectRule projectRule = projectService.getProjectRuleByProjectId(projectId);//项目规则
 		List subProjectList = evaSellerRecordService.getSubProjectByProjectId(projectId);
 		if(subProjectList.size()==0&&!projectRule.getIsDividePack()){//如果项目没分包
 			subProjectList.add(project);
 		}
		model.put("subProjectList", subProjectList);
		model.put("type", "keyin");
		return new ModelAndView("toKeyInEvalList",model);  
	}
	
	/**
	 * FuncName:toKeyInEvalPage
	 * Description: 跳转到录入专家评审结果页面页面
	 * @param projectId:项目主键
	 * @return ModelAndView
	 * @author liuke
	 * @Create Date 2010-8-1 下午01:42:30  
	 */
	@RequestMapping(params = "method=toKeyInEvalPage2")
	public ModelAndView toKeyInEvalPage2(String projectId)throws Exception {
		logger.debug("\nes EvaSellerRecordController||toKeyInEvalPage\n");
		Map model = new HashMap();
		Project project = projectService.getProjectByObjId(projectId);
		ProjectRule projectRule = projectService.getProjectRuleByProjectId(projectId);//项目规则
 		List subProjectList = evaSellerRecordService.getSubProjectByProjectId(projectId);
 		if(subProjectList.size()==0&&!projectRule.getIsDividePack()){//如果项目没分包
 			subProjectList.add(project);
 		}
		model.put("subProjectList", subProjectList);
		model.put("type", "keyin");
		return new ModelAndView("evalBidRecordResultListView2",model);  
	}
	
	/** 
	 * Description :  代理机构：跳转到公告打印预览页面页面
	 * Create Date: 2011-1-16下午10:10:41 by yangx  Modified Date: 2011-1-16下午10:10:41 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=toEvaSellerRecordPrint")
	public ModelAndView toEvaSellerRecordPrint(HttpServletRequest request,Bulletin bulletin)throws Exception {
		logger.debug("\nes=BulletinController||toEvaSellerRecordPrint\n");
		request.getSession().setAttribute("content", bulletin.getBullContents());
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/** 
	 * Description:代理机构：录入评审结果
	 */
	@RequestMapping(params= "method=getKeyInEvalPage")
	public ModelAndView getKeyInEvalPage(HttpServletRequest request,SessionStatus status)throws Exception
	{
		String quoteSum = request.getParameter("quoteSum");
		String supplierId = request.getParameter("supplierId");
		String projectId = request.getParameter("projectId");
		String subProjId = request.getParameter("subProjId");
		List<EvaSellerRecord> evaSellerRecordlist = evaSellerRecordService.getEvaSellerRecordListForEval(subProjId, supplierId);
		Map model = new HashMap();
		model.put("quoteSum", quoteSum);
		model.put("projectId", projectId);
		model.put("supplierId", supplierId);
		model.put("subProjId", subProjId);
		model.put("evaSellerRecordlist", evaSellerRecordlist);
		return new ModelAndView("keyInEvalPageView",model);
	}
	
	
	/**
	 * FuncName: toFirstTrialPage
	 * Description : 跳转到评标初审页面
	 * @param projectId
	 * @return
	 * @throws Exception ModelAndView
	 * @author: liuke
	 * @Create Date:2011-3-4  上午09:41:39
	 * @Modifier: liuke
	 * @Modified Date:2011-3-4  上午09:41:39
	 */
	@RequestMapping(params = "method=toFirstTrialPage")
	public ModelAndView toFirstTrialPage(String projectId)throws Exception {
		logger.debug("\nes EvaSellerRecordController||toFirstTrialPage\n");
		Map model = new HashMap();
		Project project = projectService.getProjectByObjId(projectId);
		ProjectRule projectRule = projectService.getProjectRuleByProjectId(projectId);//项目规则
 		List subProjectList = evaSellerRecordService.getSubProjectByProjectId(projectId);
 		if(subProjectList.size()==0&&!projectRule.getIsDividePack()){//如果项目没分包
 			subProjectList.add(project);
 		}
		model.put("subProjectList", subProjectList);
		model.put("type", "keyin");
		return new ModelAndView("bidFirstTrialTabPage",model);  
	}
	
	/**
	 * FuncName: toSecondTrialPage
	 * Description :  跳转到复审页面
	 * @param 
	 * @param projectId
	 * @return
	 * @throws Exception ModelAndView
	 * @author: liuke
	 * @Create Date:2011-3-4  上午09:50:00
	 * @Modifier: liuke
	 * @Modified Date:2011-3-4  上午09:50:00
	 */
	@RequestMapping(params = "method=toSecondTrialPage")
	public ModelAndView toSecondTrialPage(String projectId)throws Exception {
		logger.debug("\nes EvaSellerRecordController||toSecondTrialPage\n");
		Map model = new HashMap();
		Project project = projectService.getProjectByObjId(projectId);
		ProjectRule projectRule = projectService.getProjectRuleByProjectId(projectId);//项目规则
 		List subProjectList = evaSellerRecordService.getSubProjectByProjectId(projectId);
 		if(subProjectList.size()==0&&!projectRule.getIsDividePack()){//如果项目没分包
 			subProjectList.add(project);
 		}
		model.put("subProjectList", subProjectList);
		model.put("type", "keyin");
		return new ModelAndView("bidSecondTrialTabPage",model);  
	}
	
	/**
	 * FuncName: toFirstTrialPageDetail
	 * Description : 跳转到初审页面
	 * @param request
	 * @param stauts
	 * @return
	 * @throws Exception ModelAndView
	 * @author: liuke
	 * @Create Date:2011-3-4  上午11:31:19
	 * @Modifier: liuke
	 * @Modified Date:2011-3-4  上午11:31:19
	 */
	@RequestMapping(params= "method=toFirstTrialPageDetail")
	public ModelAndView toFirstTrialPageDetail(HttpServletRequest request,SessionStatus stauts)throws Exception { 
		logger.debug("\nes EvaSellerRecordController||toFirstTrialPageDetail\n");
		String subProjId = request.getParameter("subProjId");
		List evaSellerRecordList = evaSellerRecordService.getEvaSellerRecordListByProjectId(subProjId);
		List WorkgMemberList = workgMemberService.getWorkgMemberByProjectIdAndGroupType(subProjId, WorkGroupTypeEnum.APPRAISAL, WorkMemberTypeEnum.EXPERT_REPRESENT);
		Map model = new HashMap();
		model.put("evaSellerRecordList", evaSellerRecordList);
		model.put("WorkgMemberList", WorkgMemberList);
		return new ModelAndView("bidFirstTrialPage",model);
	}
	
	/**
	 * FuncName: toFirstTrialPageDetail
	 * Description : 跳转到复审页面
	 * @param request
	 * @param stauts
	 * @return
	 * @throws Exception ModelAndView
	 * @author: liuke
	 * @Create Date:2011-3-4  上午11:31:19
	 * @Modifier: liuke
	 * @Modified Date:2011-3-4  上午11:31:19
	 */
	@RequestMapping(params= "method=toSecondTrialPageDetail")
	public ModelAndView toSecondTrialPageDetail(HttpServletRequest request,SessionStatus stauts)throws Exception { 
		logger.debug("\nes EvaSellerRecordController||toFirstTrialPageDetail\n");
		String subProjId = request.getParameter("subProjId");
		List evaSellerRecordList = evaSellerRecordService.getEvaSellerRecordListByProjectId(subProjId);
		List WorkgMemberList = workgMemberService.getWorkgMemberByProjectIdAndGroupType(subProjId, WorkGroupTypeEnum.APPRAISAL, WorkMemberTypeEnum.EXPERT_REPRESENT);
		Map model = new HashMap();
		model.put("evaSellerRecordList", evaSellerRecordList);
		model.put("WorkgMemberList", WorkgMemberList);
		return new ModelAndView("bidSecondTrialPage",model);
	}
	
}
