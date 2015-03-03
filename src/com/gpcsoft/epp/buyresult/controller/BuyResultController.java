package com.gpcsoft.epp.buyresult.controller;

import java.math.BigDecimal;
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
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.bizplatform.agency.domain.Agency;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.bulletin.domain.Bulletin;
import com.gpcsoft.epp.buyresult.domain.BuyResult;
import com.gpcsoft.epp.buyresult.domain.BuyResultComparator;
import com.gpcsoft.epp.buyresult.domain.BuyWinner;
import com.gpcsoft.epp.buyresult.domain.ResultTypeEnum;
import com.gpcsoft.epp.buyresult.service.BuyResultService;
import com.gpcsoft.epp.buyresult.service.BuyWinnerService;
import com.gpcsoft.epp.common.service.UserApiService;
import com.gpcsoft.epp.negotationrecord.service.NegotationRecordService;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.domain.ProjectRule;
import com.gpcsoft.epp.project.service.ProjectService;
import com.gpcsoft.epp.projreview.domain.OpenBidRecord;
import com.gpcsoft.epp.projreview.service.OpenBidRecordService;
import com.gpcsoft.epp.signuprecord.domain.SignUprecord;
import com.gpcsoft.epp.signuprecord.service.SignUprecordService;
import com.gpcsoft.epp.taskplan.domain.TaskPlan;
import com.gpcsoft.epp.taskplan.domain.TaskPlanDrawTypeEnum;
import com.gpcsoft.epp.taskplan.domain.TaskPlanMSub;
import com.gpcsoft.epp.taskplan.service.TaskPlanMSubService;
import com.gpcsoft.epp.taskplan.service.TaskPlanService;

import edu.emory.mathcs.backport.java.util.Collections;

/**
  * @gpcsoft.view value="buyResultFormView"
  *  url="view/es/planform/buyresult/buyResultForm.jsp"
  * @gpcsoft.view value="buyResultTreeFormView"
  *  url="view/es/planform/buyresult/buyResultTreeForm.jsp"
  * @gpcsoft.view value="buyResultListView"
  *  url="view/es/planform/buyresult/buyResultList.jsp"
  * @gpcsoft.view value="buyResultDetailView"
  *  url="view/es/planform/buyresult/buyResultDetail.jsp"
  *  @gpcsoft.view value="configBuyResultView"
  *  url="view/es/planform/buyresult/configBuyResultView.jsp"
  *  @gpcsoft.view value="toViewConfigBuyResult"
  *  url="view/es/planform/buyresult/configBuyResultViewDetail.jsp"
  *  @gpcsoft.view value="bidEvaluationReportListPageView"
  *  url="view/es/planform/buyresult/bidEvaluationReportListPage.jsp"
  *  @gpcsoft.view value="configBuyResultPageView"
  *  url="view/es/planform/buyresult/configBuyResultPage.jsp"
  *  @gpcsoft.view value="toConfigBuyResultViewList"
  *  url="view/es/planform/buyresult/configBuyResultPageView.jsp"
  *  @gpcsoft.view value="listBuyResultView"
  *  url="view/es/planform/buyresult/buy_result_list.jsp"
  *  @gpcsoft.view value="noOpenBidView"
  *  url="view/es/common/noOpenBid.jsp" 
  *  @gpcsoft.view value="toViewListBuyResult"
  *  url="view/es/planform/buyresult/buy_result_list_view.jsp" 
  *  @gpcsoft.view value="randomBuyResultView"
  *  url="view/es/planform/buyresult/randomSelectAgentDetailView.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={BuyResult.class})
@RequestMapping("/BuyResultController.do")//页面请求路径,可修改
@SuppressWarnings("unchecked")
public class BuyResultController extends AnnotationMultiController<BuyResult> {

	@Autowired(required=true) @Qualifier("buyResultServiceImpl")
	private BuyResultService buyResultService;

	@Autowired(required=true) @Qualifier("buyWinnerServiceImpl")
	private BuyWinnerService buyWinnerService;
	
	@Autowired(required=true) @Qualifier("projectServiceImpl")
	private ProjectService projectService;
	
	@Autowired(required=true) @Qualifier("openBidRecordServiceImpl")
	private OpenBidRecordService openBidRecordService;
	
	@Autowired(required=true) @Qualifier("negotationRecordServiceImpl")
	private NegotationRecordService negotationRecordServiceImpl;
	
	@Autowired(required=true) @Qualifier("signUprecordServiceImpl")
	private SignUprecordService signUprecordService;
	
	@Autowired(required=true) @Qualifier("taskPlanMSubServiceImpl")
	private TaskPlanMSubService taskPlanMSubService;
	
	@Autowired(required=true) @Qualifier("taskPlanServiceImpl")
	private TaskPlanService taskPlanService;
	
	@Autowired(required=true) @Qualifier("userApiServiceImpl")
	private UserApiService userApiService;
	
	/**
	 * Funcname:saveOrUpdateBuyResult
	 * Description :保存成交结果 
	 * Create Date: 2010-6-2下午04:25:50 by liuke  Modified Date: 2010-6-2下午04:25:50 by liuke
	 * @param   request,buyResult:成交结果;
	 * @return  ModelAndView
	 * @Exception
	 */
	@RequestMapping(params = "method=saveOrUpdateBuyResult")
	public ModelAndView saveOrUpdateBuyResult(HttpServletRequest request, BuyResult buyResult)throws Exception {
		logger.debug("\nes BuyResultController||saveOrUpdateBuyResult\n");
		String suppliers = request.getParameter("supplierIds");
		String allSupplierIds = request.getParameter("allSupplierIds");
		buyResultService.saveBuyResultAndBuyWinner(buyResult, suppliers, allSupplierIds);
		Map model = new HashMap();	
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	
	/** 
	 * FuncName : toViewConfigBuyResult
	 * Description :  跳转到查看中标人页面
	 * Create Date: 2011-11-15下午01:54:14 by yangx  
	 * Modified Date: 2011-11-15下午01:54:14 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toViewConfigBuyResult")
	public ModelAndView toViewConfigBuyResult(HttpServletRequest request)throws Exception {
		logger.debug("\nes BuyResultController||toConfigBuyResult\n");
		String projectId = request.getParameter("projectId");
		Project project = projectService.getProjectByObjId(projectId);
		List subProjectList = buyResultService.getSubProjectByProjectId(projectId);
		ProjectRule projectRule = projectService.getProjectRuleByProjectId(projectId);//项目规则
		if(subProjectList.size()==0&&!projectRule.getIsDividePack()){//没有分包
			subProjectList.add(project);
		}
		List<Bulletin> bidEvaluationReportList = buyResultService.getbidEvaluationReportByProjectId(projectId);
		List<BuyWinner> buyWinnerList = buyWinnerService.getBuyWinnerByProjectId(projectId);  //获得定标数据
		for (Iterator iterator = subProjectList.iterator(); iterator.hasNext();) {
			Project proj = (Project) iterator.next();
			   for (Iterator iterator2 = bidEvaluationReportList.iterator(); iterator2.hasNext();) {
				Bulletin bulletin = (Bulletin) iterator2.next();
				   if(proj.getObjId().equals(bulletin.getProject().getObjId())){
					   proj.setBidEvaluationReport(bulletin);
				   }
			}
			  String winnerNames = "";
			  for (Iterator iterator2 = buyWinnerList.iterator(); iterator2.hasNext();) {
				BuyWinner buyWinner = (BuyWinner) iterator2.next();
				if(proj.getObjId().equals(buyWinner.getBuyResult().getSubProjId())){
					proj.setIsConfigBuyResult("true");   //表示已经定标
					if(ResultTypeEnum.DEAL_YES.equals(buyWinner.getResultType())){ //判断是否为成交供应商
						winnerNames += buyWinner.getSellerName()+",";
					}
				}
			} 
			  if(winnerNames.length()>0){
				  winnerNames = winnerNames.substring(0, winnerNames.length()-1);
			  }
			  
			  proj.setWinnerNames(winnerNames);   
		}
		
		Map model = new HashMap();	
		model.put("subProjectList", subProjectList);
		model.put("projectRule", projectRule);
		model.put("tenderType", project.getTenderType()); 
		return new ModelAndView("toViewConfigBuyResult", model);
	}
	
	/**
	 * Funcname:toConfigBuyResult
	 * Description :跳转到定标页面 
	 * @Create Date: 2010-6-26上午11:37:06 
	 * @author liuke
	 * @Modified Date: 2010-6-26上午11:37:06 
	 * @author liuke
	 * @param   request
	 * @return  ModelAndView
	 * @Exception
	 */
	@RequestMapping(params = "method=toConfigBuyResult")
	public ModelAndView toConfigBuyResult(HttpServletRequest request)throws Exception {
		logger.debug("\nes BuyResultController||toConfigBuyResult\n");
		String projectId = request.getParameter("projectId");
		Project project = projectService.getProjectByObjId(projectId);
		List subProjectList = buyResultService.getSubProjectByProjectId(projectId);
		ProjectRule projectRule = projectService.getProjectRuleByProjectId(projectId);//项目规则
		if(subProjectList.size()==0&&!projectRule.getIsDividePack()){//没有分包
			subProjectList.add(project);
		}
		List<Bulletin> bidEvaluationReportList = buyResultService.getbidEvaluationReportByProjectId(projectId);
		List<BuyWinner> buyWinnerList = buyWinnerService.getBuyWinnerByProjectId(projectId);  //获得定标数据
		for (Iterator iterator = subProjectList.iterator(); iterator.hasNext();) {
			Project proj = (Project) iterator.next();
			   for (Iterator iterator2 = bidEvaluationReportList.iterator(); iterator2.hasNext();) {
				Bulletin bulletin = (Bulletin) iterator2.next();
				   if(proj.getObjId().equals(bulletin.getProject().getObjId())){
					   proj.setBidEvaluationReport(bulletin);
				   }
			}
			  String winnerNames = "";
			  for (Iterator iterator2 = buyWinnerList.iterator(); iterator2.hasNext();) {
				BuyWinner buyWinner = (BuyWinner) iterator2.next();
				if(proj.getObjId().equals(buyWinner.getBuyResult().getSubProjId())){
					proj.setIsConfigBuyResult("true");   //表示已经定标
					if(ResultTypeEnum.DEAL_YES.equals(buyWinner.getResultType())){ //判断是否为成交供应商
						winnerNames += buyWinner.getSellerName()+",";
					}
				}
			} 
			  if(winnerNames.length()>0){
				  winnerNames = winnerNames.substring(0, winnerNames.length()-1);
			  }
			  
			  proj.setWinnerNames(winnerNames);   
		}
		Map model = new HashMap();	
		model.put("subProjectList", subProjectList);
		model.put("projectRule", projectRule);
		model.put("tenderType", project.getTenderType());
		return new ModelAndView("configBuyResultView", model);
	}
	
	/**
	 * Funcname:toRandomBuyResult
	 * Description :跳转到随机抽取页面 
	 * @Create Date: 2011-12-22上午11:37:06 
	 * @author caojz
	 * @Modified Date: 2011-12-22上午11:37:06 
	 * @author caojz
	 * @param   request
	 * @return  ModelAndView
	 * @Exception
	 */
	@RequestMapping(params = "method=toRandomBuyResult")
	public ModelAndView toRandomBuyResult(HttpServletRequest request)throws Exception {
		logger.debug("\nes BuyResultController||toConfigBuyResult\n");
		String projectId = request.getParameter("projectId");
		Project project = projectService.getProjectByObjId(projectId);
		List<SignUprecord> signUprecordList = signUprecordService.getSignupRecordList(projectId);
		List<TaskPlanMSub> taskPlanMSubList = taskPlanMSubService.getTaskPlanMSubByProjectId(projectId);
		String taskPlanId = ""; 
		if(taskPlanMSubList!= null){
			TaskPlanMSub  taskPlanMSub = taskPlanMSubList.get(0); 
			taskPlanId = taskPlanMSub.getTaskPlan().getObjId(); 
//			TaskPlan taskPlan = taskPlanService.getTaskPlanByObjId(taskPlanId);
//			String agentName = taskPlan.getTaskAgentName();
		}
		Map model = new HashMap();	
		model.put("taskPlanId", taskPlanId);
		model.put("drawType", TaskPlanDrawTypeEnum.RANDOM_SELECT);
		model.put("project", project);
		model.put("signNum", signUprecordList.size());
		model.put("signUprecordList", signUprecordList);
		return new ModelAndView("randomBuyResultView",model);
	}
	
	/**
	 * Funcname:toConfigBuyResultPage
	 * Description :跳转评审报告列表页面  
	 * @Create Date: 2010-6-26下午12:06:07   
	 * @author liuke
	 * @Modified Date: 2010-6-26下午12:06:07 
	 * @author  liuke
	 * @param   request
	 * @return  ModelAndView
	 * @Exception
	 */
	@RequestMapping(params = "method=toConfigBuyResultPage")
	public ModelAndView toConfigBuyResultPage(HttpServletRequest request)throws Exception {
		logger.debug("\nes BuyResultController||toConfigBuyResultPage\n");
		String subProjId = request.getParameter("subProjId");
		String returnUrl = "";
		Map model = new HashMap();	
		returnUrl = "bidEvaluationReportListPageView";
		List<Bulletin> bidEvaluationReportList = buyResultService.getbidEvaluationReportByProjectId(subProjId);
		model.put("bidEvaluationReportList", bidEvaluationReportList);
		return new ModelAndView(returnUrl, model);
	}
	
	
	/** 
	 * FuncName : toConfigBuyResultViewList
	 * Description :  跳转到查看中标人页面
	 * Create Date: 2011-11-15下午01:58:01 by yangx  
	 * Modified Date: 2011-11-15下午01:58:01 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toConfigBuyResultViewList")
	public ModelAndView toConfigBuyResultViewList(HttpServletRequest request)throws Exception {
		logger.debug("\nes BuyResultController||toConfigBuyResultPageList\n");
		String projectId = request.getParameter("projectId");
		String subProjId = request.getParameter("subProjId");
		Project project = projectService.getProjectByObjId(projectId);
		Project subProject = projectService.getProjectByObjId(subProjId);
		ProjectRule projectRule = projectService.getProjectRuleByProjectId(projectId);//项目规则
		BuyResult buyResult = buyResultService.getBuyResultBySubProjId(subProjId);
		List<Bulletin> bulletinList =  buyResultService.getbidEvaluationReportByProjectId(subProjId);
		Bulletin bulletin = null;
		if(bulletinList != null && !bulletinList.isEmpty()){
			bulletin = bulletinList.get(0);
		}
		String isConfig = "NO";
		if(null!=buyResult){
			isConfig = "YES";
		}
		List<OpenBidRecord> openBidRecordList = openBidRecordService.getopenBidRecordListByPackId(subProjId);
		Map map = new HashMap();
		for(OpenBidRecord openBidRecord:openBidRecordList){
			map.put(openBidRecord.getSupplier().getObjId(), openBidRecord);
		}
		if(openBidRecordList != null && !openBidRecordList.isEmpty()){
			 BuyResultComparator buyResultComparator = new BuyResultComparator();
			 Collections.sort(openBidRecordList, buyResultComparator);//序列化
		}
		List<BuyWinner> winList = buyWinnerService.getBuyWinnerBySubProjectId(subProjId);//查询被确定的成交供应商
		for(Iterator<BuyWinner>iterator =winList.iterator();iterator.hasNext(); ){
			BuyWinner winner = iterator.next();
			OpenBidRecord openBidRecord2 = (OpenBidRecord)map.get(winner.getSelllerId());
			 openBidRecord2.setIsSelect(winner.getResultType());
			 map.put(winner.getSelllerId(), openBidRecord2);
		}
		negotationRecordServiceImpl.getFillLastNegotiatePriceInOpenBidRecordQuoteSum(openBidRecordList);
		Map model = new HashMap();	
		model.put("openBidRecordList",openBidRecordList);
		model.put("buyResult", buyResult);
		model.put("project", project);
		model.put("bulletin", bulletin);
		model.put("subProject",  subProject);
		model.put("isConfig",  isConfig);
		model.put("projectRule", projectRule);
		return new ModelAndView("toConfigBuyResultViewList", model);
	}
	
	/**
	 * Description :跳转确认成交供应商列表页面 
	 * Create Date: 2010-6-26下午12:06:07 by liuke  Modified Date: 2010-6-26下午12:06:07 by liuke
	 * @param   request
	 * @return  ModelAndView
	 * @Exception
	 * @Modifier zhouzhanghe
	 * @Modified date 2011-10-12 17:49
	 */
	@RequestMapping(params = "method=toConfigBuyResultPageList")
	public ModelAndView toConfigBuyResultPageList(HttpServletRequest request)throws Exception {
		logger.debug("\nes BuyResultController||toConfigBuyResultPageList\n");
		String projectId = request.getParameter("projectId");
		String subProjId = request.getParameter("subProjId");
		Project project = projectService.getProjectByObjId(projectId);
		Project subProject = null;
		if(!projectId.equals(subProjId)){
		  subProject = projectService.getProjectByObjId(subProjId);
		}else{
			subProject = project;
		}
		ProjectRule projectRule = projectService.getProjectRuleByProjectId(projectId);//项目规则
		BuyResult buyResult = buyResultService.getBuyResultBySubProjId(subProjId);
		List<Bulletin> bulletinList =  buyResultService.getbidEvaluationReportByProjectId(subProjId);
		Bulletin bulletin = null;   
		if(bulletinList != null && !bulletinList.isEmpty()){
			bulletin = bulletinList.get(0);
		}
		String isConfig = "NO";
		if(null!=buyResult){
			isConfig = "YES";
		}
		List<OpenBidRecord> openBidRecordList = openBidRecordService.getopenBidRecordListByPackId(subProjId);
		Map map = new HashMap();
		for(OpenBidRecord openBidRecord:openBidRecordList){
			map.put(openBidRecord.getSupplier().getObjId(), openBidRecord);
		}
		if(openBidRecordList != null && !openBidRecordList.isEmpty()){
			 BuyResultComparator buyResultComparator = new BuyResultComparator();
			 Collections.sort(openBidRecordList, buyResultComparator);//序列化
		}
		List<BuyWinner> winList = buyWinnerService.getBuyWinnerBySubProjectId(subProjId);//查询被确定的成交供应商
		for(Iterator<BuyWinner>iterator =winList.iterator();iterator.hasNext(); ){
			BuyWinner winner = iterator.next();
			OpenBidRecord openBidRecord2 = (OpenBidRecord)map.get(winner.getSelllerId());
			openBidRecord2.setIsSelect(winner.getResultType());
			map.put(winner.getSelllerId(), openBidRecord2);
		}

		/**
		 *看是否有谈判记录，如果有，则将最后一次谈判价格作为定标价格
		 *zhouzhanghe at 2011-10-12 17:48
		 */
		negotationRecordServiceImpl.getFillLastNegotiatePriceInOpenBidRecordQuoteSum(openBidRecordList);
		// add by caojz 2011-12-19 获取代理机构比选报名信息 (临时)
		List<SignUprecord> signUprecordList = signUprecordService.getSignupRecordList(projectId);
		
		Map model = new HashMap();	
		model.put("openBidRecordList",openBidRecordList);
		model.put("buyResult", buyResult);
		model.put("project", project);
		model.put("bulletin", bulletin);
		model.put("subProject",  subProject);
		model.put("signUprecordList", signUprecordList);
		model.put("isConfig",  isConfig);
		model.put("projectRule", projectRule);
		return new ModelAndView("configBuyResultPageView", model);
	}
	
	/** 
	 * Description :  跳转到展示中标结果页 一个标段对应一条BuyResult记录
	 * Create Date: 2010-6-28上午10:21:08 by wangcl  Modified Date: 2010-6-28上午10:21:08 by wangcl
	 * @param   request
	 * @return  ModelAndView
	 * @Exception   
	 */
	@RequestMapping(params = "method=toListBuyResult")
	public ModelAndView toListBuyResult(HttpServletRequest request)throws Exception {
		logger.debug("\nes BuyResultController||toListBuyResult\n");
		String projectId = request.getParameter("projectId");
		List<BuyResult> resultsList = buyResultService.getListByProjectId(projectId);
		Map model = new HashMap();	
		model.put("results", resultsList);
		return new ModelAndView("listBuyResultView", model);
	}
	
	
	/** 
	 * FuncName : toViewListBuyResult
	 * Description :  查看中标通知书
	 * Create Date: 2011-11-15下午01:30:25 by yangx  
	 * Modified Date: 2011-11-15下午01:30:25 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toViewListBuyResult")
	public ModelAndView toViewListBuyResult(HttpServletRequest request)throws Exception {
		logger.debug("\nes BuyResultController||toViewListBuyResult\n");
		String projectId = request.getParameter("projectId");
		List<BuyResult> resultsList = buyResultService.getListByProjectId(projectId);
		Map model = new HashMap();	
		model.put("results", resultsList);
		return new ModelAndView("toViewListBuyResult", model);
	}
	
	
	/** 
	 * FuncName : finishBuyResult
	 * Description :  完成定标
	 * Create Date: 2011-11-16下午02:51:33 by yangx  
	 * Modified Date: 2011-11-16下午02:51:33 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=finishBuyResult")
	public ModelAndView finishBuyResult(HttpServletRequest request)throws Exception{
		String projectId = request.getParameter("projectId");
		buyResultService.finishBuyResult(projectId);
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	public  int getHascode(String str){
		return str.hashCode();
	}
	
}


