package com.gpcsoft.epp.contract.controller;

import java.text.ParseException;
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
import com.gpcsoft.core.utils.DateUtil;
import com.gpcsoft.core.utils.JsonUtils;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.buyresult.domain.BuyWinner;
import com.gpcsoft.epp.buyresult.service.BuyWinnerService;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.common.service.UserApiService;
import com.gpcsoft.epp.contract.domain.Contract;
import com.gpcsoft.epp.contract.domain.ContractAcquirer;
import com.gpcsoft.epp.contract.domain.ContractPaymentApplyInfo;
import com.gpcsoft.epp.contract.domain.ContractSupplier;
import com.gpcsoft.epp.contract.domain.WitnessParty;
import com.gpcsoft.epp.contract.service.ContractService;
import com.gpcsoft.epp.noticemanage.domain.Notice;
import com.gpcsoft.epp.noticemanage.domain.NoticeTypeEnum;
import com.gpcsoft.epp.noticemanage.service.NoticeService;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.domain.ProjectRule;
import com.gpcsoft.epp.project.domain.SubProjectMTaskPlanSub;
import com.gpcsoft.epp.project.service.ProjectMTaskPlanService;
import com.gpcsoft.epp.project.service.ProjectService;
import com.gpcsoft.epp.taskplan.domain.TaskPlanMSub;
import com.gpcsoft.epp.taskplan.service.TaskPlanMSubService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.ListBind;
import com.gpcsoft.srplatform.auth.domain.User;

/**
  * @gpcsoft.view value="contractFormView"
  *  url="view/es/planform/contract/contractForm.jsp"
  *  @gpcsoft.view value="contractAgencyFormView"
  *  url="view/es/planform/contract/contractAgencyForm.jsp"
  * @gpcsoft.view value="contractTreeFormView"
  *  url="view/es/planform/contract/contractTreeForm.jsp"
  * @gpcsoft.view value="contractListView"
  *  url="view/es/planform/contract/contractList.jsp"
  * @gpcsoft.view value="contractDetailView"
  *  url="view/es/planform/contract/contractDetail.jsp"
  * @gpcsoft.view value="confirmContract"
  *  url="view/es/planform/contract/confirmContract.jsp"
  *  @gpcsoft.view value="updateContractFormView"
  *  url="view/es/planform/contract/updateContractForm.jsp"
  *  @gpcsoft.view value="contractDetailViewForBuyer"
  *  url="view/es/planform/contract/contractDetailForBuyer.jsp"
  *  @gpcsoft.view value="toContractDetailForAgency"
  *  url="view/es/planform/contract/contractDetailForAgency.jsp"
  *  @gpcsoft.view value="contractListForSupplier"
  *  url="view/es/planform/contract/contractListForSupplier.jsp"
  *  @gpcsoft.view value="contractListForBuyer"
  *  url="view/es/planform/contract/contractListForBuyer.jsp"
  *   @gpcsoft.view value="contractSupplierList"
  *  url="view/es/planform/contract/contractSupplierList.jsp"
  *   @gpcsoft.view value="contractBuyerList"
  *  url="view/es/planform/contract/contractBuyerList.jsp"
  *  
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={Contract.class})
@RequestMapping("/ContractController.do")//页面请求路径,可修改
public class ContractController extends AnnotationMultiController<Contract> {

	@Autowired(required=true) @Qualifier("contractServiceImpl")
	private ContractService contractService;
	
	@Autowired(required=true) @Qualifier("projectServiceImpl")
	private ProjectService projectService;	
	
	@Autowired(required=true) @Qualifier("userApiServiceImpl")
	private UserApiService userApiService;
	
	@Autowired(required=true) @Qualifier("taskPlanMSubServiceImpl")
	private TaskPlanMSubService taskPlanMSubService;
	
	@Autowired(required=true) @Qualifier("projectMTaskPlanServiceImpl")
	private ProjectMTaskPlanService projectMTaskPlanService;
	
	@Autowired(required=true) @Qualifier("buyWinnerServiceImpl")
	private BuyWinnerService buyWinnerService;
	
	@Autowired(required=true) @Qualifier("noticeServiceImpl")
	private NoticeService noticeService;
	
	/**
	 * FuncName: toContractList
	 * Description :  到达供应商合同列表页面
	 * @param 
	 * @return ModelAndView
	 * @throws Exception ModelAndView
	 * @author: shenjz
	 * @Create Date:2011-3-16  下午05:12:14
	 * @Modifier: shenjz
	 * @Modified Date:2011-3-16  下午05:12:14
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=toContractList")
	public ModelAndView toContractList(HttpServletRequest request,SessionStatus status,String projectId)throws Exception {
		Map model = new HashMap();
		boolean flag = false;  //是否可以看到合同标识
		User user = AuthenticationHelper.getCurrentUser();
		OrgInfo supplier = (OrgInfo)user.getOrgInfo();  //获得当前登录机构
		List<SubProjectMTaskPlanSub> subProjectList = projectService.getSubProjectMTaskPlanSubByProjectId(projectId);
		List<SubProjectMTaskPlanSub> subProjectList2 = new ArrayList<SubProjectMTaskPlanSub>();
		List<BuyWinner> buyWinnerList = buyWinnerService.getBuyWinnerList(projectId);
		List<Contract> contractList = contractService.getContractList(projectId);
		if(contractList!=null&&contractList.size()>0){
			Contract contract2 = contractList.get(0);
			if(contract2.getCupplierId()!=null&&contract2.getCupplierId().equals(supplier.getObjId())){ //当前登录供应商是起草合同的供应商
				flag = true;
			}
			model.put("contractMethod", contract2.getContractMethod());//用以判断该项目按照何种方式合同
		}else{//第一次起草合同
			if(subProjectList.isEmpty()){
				Notice notice= noticeService.getNoticeBy(projectId);
				if(notice!=null&&NoticeTypeEnum.DEAL_YES.equals(notice.getNoticeType())){//如果是成交通知书
					flag = true;
				}
			}else{
				flag = true;
			}
			
		}
		for (Iterator iterator = subProjectList.iterator(); iterator.hasNext();) {
			SubProjectMTaskPlanSub project = (SubProjectMTaskPlanSub) iterator.next();
			for (Iterator iterator2 = contractList.iterator(); iterator2.hasNext();) {
				Contract contract = (Contract) iterator2.next();
				if(contract.getSubProjectId()!=null){
					if(contract.getSubProjectId().equals(project.getProject().getObjId())){
						iterator.remove();//用以移除已经起草合同的包组
					}
				}
			}
		}
		for (Iterator iterator = subProjectList.iterator(); iterator.hasNext();) {
			SubProjectMTaskPlanSub subProjectMTaskPlanSub = (SubProjectMTaskPlanSub) iterator.next();
			for (Iterator iterator2 = buyWinnerList.iterator(); iterator2.hasNext();) {
				BuyWinner buyWinner = (BuyWinner) iterator2.next();
				if(!subProjectMTaskPlanSub.getTproject().getObjId().equals(buyWinner.getBuyResult().getSubProjId())){
					subProjectList2.add(subProjectMTaskPlanSub);//用以重组该供应商中标的包组
					break;
				}
			}
		}
		ProjectRule projectRule = projectService.getProjectRuleByProjectId(projectId);
		Project project = projectService.getProjectByObjId(projectId);
		OrgInfo org = userApiService.getOrgInfoByUser(AuthenticationHelper.getCurrentUser());
		List<TaskPlanMSub> taskPlanMSubList = taskPlanMSubService.getTaskPlanMSubByProjectId(projectId);
		if(projectRule.getIsDividePack()==true&&subProjectList.size()!=0){
			for (Iterator iterator = subProjectList2.iterator(); iterator.hasNext();) {
				SubProjectMTaskPlanSub subProjectMTaskPlanSub = (SubProjectMTaskPlanSub) iterator.next();
				Notice notice= noticeService.getNoticeBy(subProjectMTaskPlanSub.getProject().getObjId());
				if(notice==null||(NoticeTypeEnum.DEAL_NO).equals(notice.getNoticeType())){//该通知书伟结果通知书
					iterator.remove();//该供应商未确认通知书,移除该包组
				}
			}
			if(flag){
				model.put("subProjectList", subProjectList2);//生成的包租
			}
			model.put("notDivide", false);//分包
		}else{
			Notice notice= noticeService.getNoticeBy(projectId);
			if(notice!=null&&notice.getReceiveStatus().equals(CommonEnum.USER_STATUS_FORMAL)){
			}else{
				model.put("noConfirm",true);//该供应商未确认通知书
			}
			model.put("notDivide", true);//未分包
		}
		if(flag){
			model.put("contractList", contractList);
			model.put("contractListSize", contractList.size());	
			model.put("subProjectListSize", subProjectList2.size());
			model.put("isCan", "Contract");
		}
		model.put("project", project);
		model.put("projectRule", projectRule);
		model.put("supplier", org);
		model.put("projectMTaskPlan", projectMTaskPlanService.getProjectMTaskPlan(projectId));
		model.put("taskPlanMSubList", taskPlanMSubList);
		return  new ModelAndView("contractListForSupplier",model);
	}
	/**
	 * FuncName: toContractListForBuyer
	 * Description :  到达采购人合同列表页面
	 * @param 
	 * @return ModelAndView
	 * @throws Exception ModelAndView
	 * @author: shenjz
	 * @Create Date:2011-3-16  下午05:12:14
	 * @Modifier: shenjz
	 * @Modified Date:2011-3-16  下午05:12:14
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=toContractListForBuyer")
	public ModelAndView toContractListForBuyer(HttpServletRequest request,SessionStatus status,String projectId)throws Exception {
		Map model = new HashMap();
		List<Contract> contractList = contractService.getContractListForBuyer(projectId);
		model.put("contractList", contractList);
		return  new ModelAndView("contractListForBuyer",model);
	}
	/**
	 * FuncName: toCreateContract
	 * Description :  到达合同创建页面
	 * @param 
	 * @return ModelAndView
	 * @throws Exception ModelAndView
	 * @author: shenjz
	 * @Create Date:2011-3-16  下午05:12:14
	 * @Modifier: shenjz
	 * @Modified Date:2011-3-16  下午05:12:14
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=toCreateContract")
	public ModelAndView toCreateContract(HttpServletRequest request,SessionStatus status,String projectId)throws Exception {
		Map model = new HashMap();
		String subId = request.getParameter("subId");
		String contractMethod = request.getParameter("contractMethod");
		List<Contract> contractList = contractService.getContractList(projectId);
		if(contractMethod.equals("01")){
			Project subproj = projectService.getProjectByObjId(request.getParameter("subId"));
			model.put("subproj", subproj);
		}else{
			List<TaskPlanMSub> taskPlanMSubList = taskPlanMSubService.getTaskPlanMSubByProjectId(projectId);
			for (Iterator iterator = taskPlanMSubList.iterator(); iterator
					.hasNext();) {
				TaskPlanMSub taskPlanMSub = (TaskPlanMSub) iterator.next();
				for (Iterator iterator2 = contractList.iterator(); iterator2.hasNext();) {
					Contract contract = (Contract) iterator2.next();
					if(null!=contract.getTaskPlanSubIds()&&!"".equals(contract.getTaskPlanSubIds())){
						String[] str = contract.getTaskPlanSubIds().split(",");
						for (int i = 0; i < str.length; i++) {
							if(str[i].equals(taskPlanMSub.getTaskPlanSub().getObjId())){
								iterator.remove();
							}
						}
					}
				}
			}
			model.put("taskPlanMSubListSize", taskPlanMSubList.size());
			model.put("taskPlanMSubList", taskPlanMSubList);
		}
		List<SubProjectMTaskPlanSub> subProjectList2 = new ArrayList<SubProjectMTaskPlanSub>();
		List<SubProjectMTaskPlanSub> subProjectList = projectService.getSubProjectMTaskPlanSubByProjectId(projectId);
		for (Iterator iterator = subProjectList.iterator(); iterator.hasNext();) {
			SubProjectMTaskPlanSub subProjectMTaskPlanSub = (SubProjectMTaskPlanSub) iterator
					.next();
			if(subId!=null&&subProjectMTaskPlanSub.getProject().getObjId().equals(subId)){
				subProjectList2.add(subProjectMTaskPlanSub);
			}
		}
		ProjectRule projectRule = projectService.getProjectRuleByProjectId(projectId);
		Project project = projectService.getProjectByObjId(projectId);
		OrgInfo org = userApiService.getOrgInfoByUser(AuthenticationHelper.getCurrentUser());
		
		if(subProjectList.size()!=0){
			model.put("subProjectList", subProjectList2);
			model.put("notDivide", false);
		}else{
			model.put("notDivide", true);
		}
		model.put("contractMethod",contractMethod);
		model.put("contractNo", contractService.createContractCodeByQO(null));
		model.put("project", project);
		model.put("projectRule", projectRule);
		model.put("supplier", org);
		model.put("projectMTaskPlan", projectMTaskPlanService.getProjectMTaskPlan(projectId));
		return  new ModelAndView("contractFormView",model);
	}
	
	/**
	 * FuncName: saveContract
	 * Description :  创建合同
	 * @param 
	 * @return ModelAndView
	 * @throws Exception ModelAndView
	 * @author: shenjz
	 * @throws ParseException 
	 * @Create Date:2011-3-16  下午05:12:14
	 * @Modifier: shenjz
	 * @Modified Date:2011-3-16  下午05:12:14
	 */
	@RequestMapping(params = "method=saveContract")
	public ModelAndView saveContract(HttpServletRequest request, String contractStr,ListBind list,String contractString, String contractAcquirerString, String contractSupplierString,String witnessPartyString, SessionStatus status) throws ParseException {
		List<ContractPaymentApplyInfo> applyInfoList = null;
		if (null != contractStr && !"".equals(contractStr)) {
			JSONArray reportPlanRelatingDetailArray = JSONArray.fromObject(contractStr);
			if (null != reportPlanRelatingDetailArray && !reportPlanRelatingDetailArray.isEmpty()) {
				applyInfoList = new ArrayList<ContractPaymentApplyInfo>(reportPlanRelatingDetailArray.size());
				for (int i=0;i<reportPlanRelatingDetailArray.size();i++) {
					ContractPaymentApplyInfo applyInfo = (ContractPaymentApplyInfo)JSONObject.toBean(((JSONObject)reportPlanRelatingDetailArray.get(i)),ContractPaymentApplyInfo.class);
					applyInfoList.add(applyInfo);
				}
			}
		}
		Map<String,String> model = new HashMap<String,String>();
		String projectId = request.getParameter("projectId");
		Contract contract = JsonUtils.json2Bean(contractString,Contract.class);
		contract.setProjectId(projectId);
		contract.setContractSignTime(new Date());
		contract.setContractEndTime(new Date());
		ContractAcquirer contractAcquirer = JsonUtils.json2Bean(contractAcquirerString,ContractAcquirer.class);
		ContractSupplier contractSupplier = JsonUtils.json2Bean(contractSupplierString,ContractSupplier.class);
		WitnessParty witnessParty = JsonUtils.json2Bean(witnessPartyString,WitnessParty.class);
		contractService.saveContract(contract, contractAcquirer, contractSupplier, witnessParty,applyInfoList);
		return  new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	/**
	 * FuncName: toCreateContract
	 * Description :  到达合同修改页面
	 * @param 
	 * @return ModelAndView
	 * @throws Exception ModelAndView
	 * @author: shenjz
	 * @Create Date:2011-3-16  下午05:12:14
	 * @Modifier: shenjz
	 * @Modified Date:2011-3-16  下午05:12:14
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=toUpdateContract")
	public ModelAndView toUpdateContract(HttpServletRequest request,SessionStatus status,String objId)throws Exception {
		Map model = new HashMap();
		Contract contract = contractService.get(objId);
		String projectId = contract.getProjectId();
		List<SubProjectMTaskPlanSub> subProjectList = projectService.getSubProjectMTaskPlanSubByProjectId(projectId);
		List<Contract> contractList = contractService.getContractList(projectId);
		for (Iterator iterator = subProjectList.iterator(); iterator.hasNext();) {
			SubProjectMTaskPlanSub project = (SubProjectMTaskPlanSub) iterator.next();
			for (Iterator iterator2 = contractList.iterator(); iterator2.hasNext();) {
				Contract contract1 = (Contract) iterator2.next();
				if(contract.getSubProjectId()!=null){
					if(contract1.getSubProjectId().equals(project.getProject().getObjId())&&!contract.getSubProjectId().equals(project.getProject().getObjId())){
						iterator.remove();
					}
				}
			}
		}
		ProjectRule projectRule = projectService.getProjectRuleByProjectId(projectId);
		Project project = projectService.getProjectByObjId(projectId);
		OrgInfo org = userApiService.getOrgInfoByUser(AuthenticationHelper.getCurrentUser());
		List<SubProjectMTaskPlanSub> subProjectList2  = new ArrayList<SubProjectMTaskPlanSub>();
		List<TaskPlanMSub> taskPlanMSubList = taskPlanMSubService.getTaskPlanMSubByProjectId(projectId);
		for (Iterator iterator = subProjectList.iterator(); iterator.hasNext();) {
			SubProjectMTaskPlanSub subProjectMTaskPlanSub = (SubProjectMTaskPlanSub) iterator
					.next();
			if(contract.getSubProjectId()!=null&&subProjectMTaskPlanSub.getProject().getObjId().equals(contract.getSubProjectId())){
				subProjectList2.add(subProjectMTaskPlanSub);
			}
		}
		if(subProjectList.size()!=0){
			model.put("subProjectList", subProjectList2);
			model.put("notDivide", false);
		}else{
			model.put("notDivide", true);
		}
		model.put("subProjectList", subProjectList2);
		model.put("project", project);
		model.put("projectRule", projectRule);
		model.put("supplier", org);
		model.put("taskPlanMSubList", taskPlanMSubList);
		List<ContractPaymentApplyInfo>  applyInfoList = contract.getContractPaymentApplyInfo();
		model.put("applyInfoList", applyInfoList);
		model.put("contract", contract);
		model.put("fromType", request.getParameter("fromType"));
		return  new ModelAndView("updateContractFormView",model);
	}
	
	/**
	 * FuncName: updateContract
	 * Description:修改合同
	 * @param 
	 * @return ModelAndView
	 * @throws Exception ModelAndView
	 * @author: shenjz
	 * @Create Date:2011-3-16  下午05:12:14
	 * @Modifier: shenjz
	 * @Modified Date:2011-3-16  下午05:12:14
	 */
	@RequestMapping(params = "method=updateContract")//修改合同的方法
	public ModelAndView updateContract(HttpServletRequest request,String contractStr, String contractString,ListBind list, String contractAcquirerString, String contractSupplierString,String witnessPartyString, SessionStatus status) {
		List<ContractPaymentApplyInfo> applyInfoList = null;
		if (null != contractStr && !"".equals(contractStr)) {
			JSONArray reportPlanRelatingDetailArray = JSONArray.fromObject(contractStr);//contractStr转换为一个数组
			if (null != reportPlanRelatingDetailArray && !reportPlanRelatingDetailArray.isEmpty()) {
				applyInfoList = new ArrayList<ContractPaymentApplyInfo>(reportPlanRelatingDetailArray.size());//实例化集合  长度和数组一样
				for (int i=0;i<reportPlanRelatingDetailArray.size();i++) { //将数组的值放在集合里面
					ContractPaymentApplyInfo applyInfo = (ContractPaymentApplyInfo)JSONObject.toBean(((JSONObject)reportPlanRelatingDetailArray.get(i)),ContractPaymentApplyInfo.class); //执行平台提供的一个方法  
					applyInfoList.add(applyInfo);//添加对象到集合里面  
				}
			}
		}
		Map<String,String> model = new HashMap<String,String>();
		String projectId = request.getParameter("projectId");
		Contract contract = JsonUtils.json2Bean(contractString,Contract.class);
		contract.setProjectId(projectId); //设置项目的Id   表示该合同到底属于哪一个项目
		ContractAcquirer contractAcquirer = JsonUtils.json2Bean(contractAcquirerString,ContractAcquirer.class);
		ContractSupplier contractSupplier = JsonUtils.json2Bean(contractSupplierString,ContractSupplier.class);
		WitnessParty witnessParty = JsonUtils.json2Bean(witnessPartyString,WitnessParty.class);
		contractService.saveContract(contract, contractAcquirer, contractSupplier, witnessParty,applyInfoList);
		model.put("fromType", request.getParameter("fromType"));
		return  new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	/**
	 * FuncName: toConfirmContract
	 * Description :  到达合同确认页面
	 * @param 
	 * @return ModelAndView
	 * @throws Exception ModelAndView
	 * @author: shenjz
	 * @Create Date:2011-3-16  下午05:12:14
	 * @Modifier: shenjz
	 * @Modified Date:2011-3-16  下午05:12:14
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=toConfirmContract")
	public ModelAndView toConfirmContract(HttpServletRequest request,SessionStatus status,String contractId)throws Exception {
		Map model = new HashMap();
		String objId = request.getParameter("objId");
		Contract contract = contractService.get(objId);
		List<TaskPlanMSub> taskPlanMSubList = taskPlanMSubService.getTaskPlanMSubByProjectId(contract.getProjectId());
		for (Iterator iterator = taskPlanMSubList.iterator(); iterator
				.hasNext();) {
			TaskPlanMSub taskPlanMSub = (TaskPlanMSub) iterator.next();
			if(contract.getTaskPlanSubIds()!=null){
				String[]str = contract.getTaskPlanSubIds().split(",");
				for (int i = 0; i < str.length; i++) {
					if(taskPlanMSub.getTaskPlanSub().getObjId().equals(str[i])){
						iterator.remove();
					}
				}
			}
		}
		Project project = projectService.getProjectByObjId(contract.getProjectId());  //获得项目
		if(project.getParentId()!=null){ //说明是包组
			project = projectService.getProjectByObjId(project.getParentId());
		}
		List<SubProjectMTaskPlanSub> subProjectList2 = new ArrayList<SubProjectMTaskPlanSub>();
		List<SubProjectMTaskPlanSub> subProjectList = projectService.getSubProjectMTaskPlanSubByProjectId(contract.getProjectId());
		for (Iterator iterator = subProjectList.iterator(); iterator.hasNext();) {
			SubProjectMTaskPlanSub subProjectMTaskPlanSub = (SubProjectMTaskPlanSub) iterator
					.next();
			if(contract.getSubProjectId()!=null&&subProjectMTaskPlanSub.getProject().getObjId().equals(contract.getSubProjectId())){
				subProjectList2.add(subProjectMTaskPlanSub);
			}
		}
		
		if(subProjectList.size()!=0){
			model.put("subProjectList", subProjectList2);
			model.put("notDivide", false);
		}else{
			model.put("notDivide", true);
		}
		
		model.put("project", project);
		model.put("subProjectList", subProjectList2);
		model.put("taskPlanMSubList", taskPlanMSubList);
		model.put("contract", contract);
		List<ContractPaymentApplyInfo>  applyInfoList = contract.getContractPaymentApplyInfo();
		model.put("applyInfoList", applyInfoList);
		model.put("fromType", request.getParameter("fromType"));
		return  new ModelAndView("confirmContract",model);
	}
	
	/**
	 * FuncName: saveContractForConfirm
	 * Description :  确认合同
	 * @param request
	 * @param status
	 * @param contractId
	 * @return
	 * @throws Exception ModelAndView
	 * @author: shenjz
	 * @Create Date:2011-3-17  下午04:46:28
	 * @Modifier: shenjz
	 * @Modified Date:2011-3-17  下午04:46:28
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=saveContractForConfirm")
	public ModelAndView saveContractForConfirm(HttpServletRequest request,SessionStatus status,String contractId)throws Exception {
		Map model = new HashMap();
		String objId = request.getParameter("objId");
		String opinion = request.getParameter("opinion");
		Contract contract = contractService.get(objId);
		contract.setContractStatus(request.getParameter("contractStatus"));
		contract.setOpinion(opinion);
		contractService.saveConfirmContract(contract);
		return  new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	/**
	 * FuncName: toContractDetail
	 * Description :  到达供应商合同详情页面
	 * @param 
	 * @return ModelAndView
	 * @throws Exception ModelAndView
	 * @author: shenjz
	 * @Create Date:2011-3-16  下午05:12:14
	 * @Modifier: shenjz
	 * @Modified Date:2011-3-16  下午05:12:14
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=toContractDetail")
	public ModelAndView toContractDetail(HttpServletRequest request,SessionStatus status,String projectId)throws Exception {
		Map model = new HashMap();
		String objId = request.getParameter("objId");
		Contract contract = contractService.get(objId);
		List<TaskPlanMSub> taskPlanMSubList = taskPlanMSubService.getTaskPlanMSubByProjectId(contract.getProjectId());
		List<SubProjectMTaskPlanSub> subProjectList2 = new ArrayList<SubProjectMTaskPlanSub>();
		List<SubProjectMTaskPlanSub> subProjectList = projectService.getSubProjectMTaskPlanSubByProjectId(contract.getProjectId());
		for (Iterator iterator = subProjectList.iterator(); iterator.hasNext();) {
			SubProjectMTaskPlanSub subProjectMTaskPlanSub = (SubProjectMTaskPlanSub) iterator
					.next();
			if(contract.getSubProjectId()!=null&&subProjectMTaskPlanSub.getProject().getObjId().equals(contract.getSubProjectId())){
				subProjectList2.add(subProjectMTaskPlanSub);
			}
		}
		
		if(subProjectList.size()!=0){
			model.put("subProjectList", subProjectList2);
			model.put("notDivide", false);
		}else{
			model.put("notDivide", true);
		}
		List<ContractPaymentApplyInfo>  applyInfoList = contract.getContractPaymentApplyInfo();
		model.put("applyInfoList", applyInfoList);
		model.put("subProjectList", subProjectList2);
		model.put("taskPlanMSubList", taskPlanMSubList);
		model.put("contract", contract);
		model.put("fromType", request.getParameter("fromType"));
		return  new ModelAndView("contractDetailView",model);
	}
	
	/**
	 * FuncName: toContractDetailForBuyer
	 * Description :  到达采购人合同详情页面
	 * @param 
	 * @return ModelAndView
	 * @throws Exception ModelAndView
	 * @author: shenjz
	 * @Create Date:2011-3-16  下午05:12:14
	 * @Modifier: shenjz
	 * @Modified Date:2011-3-16  下午05:12:14
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=toContractDetailForBuyer")
	public ModelAndView toContractDetailForBuyer(HttpServletRequest request,SessionStatus status,String projectId)throws Exception {
		Map model = new HashMap();
		String objId = request.getParameter("objId");
		Contract contract = contractService.get(objId);
		List<TaskPlanMSub> taskPlanMSubList = taskPlanMSubService.getTaskPlanMSubByProjectId(contract.getProjectId());
		List<SubProjectMTaskPlanSub> subProjectList2 = new ArrayList<SubProjectMTaskPlanSub>();
		List<SubProjectMTaskPlanSub> subProjectList = projectService.getSubProjectMTaskPlanSubByProjectId(contract.getProjectId());
		for (Iterator iterator = subProjectList.iterator(); iterator.hasNext();) {
			SubProjectMTaskPlanSub subProjectMTaskPlanSub = (SubProjectMTaskPlanSub) iterator
					.next();
			if(contract.getSubProjectId()!=null&&subProjectMTaskPlanSub.getProject().getObjId().equals(contract.getSubProjectId())){
				subProjectList2.add(subProjectMTaskPlanSub);
			}
		}
		Project project = projectService.getProjectByObjId(contract.getProjectId());  //获得项目
		if(project.getParentId()!=null){ //说明是包组
			project = projectService.getProjectByObjId(project.getParentId());
		}
		
		if(subProjectList.size()!=0){
			model.put("subProjectList", subProjectList2);
			model.put("notDivide", false);
		}else{
			model.put("notDivide", true);
		}
		List<ContractPaymentApplyInfo>  applyInfoList = contract.getContractPaymentApplyInfo();
		model.put("project", project);
		model.put("applyInfoList", applyInfoList);
		model.put("subProjectList", subProjectList2);
		model.put("taskPlanMSubList", taskPlanMSubList);
		model.put("contract", contract);
		model.put("fromType", request.getParameter("fromType"));
		return  new ModelAndView("contractDetailViewForBuyer",model);
	}
	/**
	 * FuncName: toCreateContract
	 * Description :  代理机构到达合同创建页面
	 * @param 
	 * @return ModelAndView
	 * @throws Exception ModelAndView
	 * @author: shenjz
	 * @Create Date:2011-3-16  下午05:12:14
	 * @Modifier: shenjz
	 * @Modified Date:2011-3-16  下午05:12:14
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=toCreateContractForAgency")
	public ModelAndView toCreateContractForAgency(HttpServletRequest request,SessionStatus status,String projectId)throws Exception {
		Map model = new HashMap();
		OrgInfo org = userApiService.getOrgInfoByUser(AuthenticationHelper.getCurrentUser());
		model.put("contractNo", contractService.createContractCodeByQO(null));
		model.put("agency", org);
		return  new ModelAndView("contractAgencyFormView",model);
	}
	/**
	 * FuncName: saveContractForAgency
	 * Description :  代理机构创建合同
	 * @param 
	 * @return ModelAndView
	 * @throws Exception ModelAndView
	 * @author: shenjz
	 * @throws ParseException 
	 * @Create Date:2011-3-16  下午05:12:14
	 * @Modifier: shenjz
	 * @Modified Date:2011-3-16  下午05:12:14
	 */
	@RequestMapping(params = "method=saveContractForAgency")
	public ModelAndView saveContractForAgency(HttpServletRequest request, String contractStr,ListBind list,String contractString, String contractAcquirerString, String contractSupplierString,String witnessPartyString, SessionStatus status) throws ParseException {
		List<ContractPaymentApplyInfo> applyInfoList = null;
		if (null != contractStr && !"".equals(contractStr)) {
			JSONArray reportPlanRelatingDetailArray = JSONArray.fromObject(contractStr);
			if (null != reportPlanRelatingDetailArray && !reportPlanRelatingDetailArray.isEmpty()) {
				applyInfoList = new ArrayList<ContractPaymentApplyInfo>(reportPlanRelatingDetailArray.size());
				for (int i=0;i<reportPlanRelatingDetailArray.size();i++) {
					ContractPaymentApplyInfo applyInfo = (ContractPaymentApplyInfo)JSONObject.toBean(((JSONObject)reportPlanRelatingDetailArray.get(i)),ContractPaymentApplyInfo.class);
					applyInfoList.add(applyInfo);
				}
			}
		}
		Map<String,String> model = new HashMap<String,String>();
		Contract contract = JsonUtils.json2Bean(contractString,Contract.class);
		ContractAcquirer contractAcquirer = JsonUtils.json2Bean(contractAcquirerString,ContractAcquirer.class);
		ContractSupplier contractSupplier = JsonUtils.json2Bean(contractSupplierString,ContractSupplier.class);
		WitnessParty witnessParty = JsonUtils.json2Bean(witnessPartyString,WitnessParty.class);
		contractService.saveContractForAgency(contract, contractAcquirer, contractSupplier, witnessParty,applyInfoList);
		return  new ModelAndView(Constants.JSON_VIEW,model);
	}
	/**
	 * FuncName: toContractDetailForBuyer
	 * Description :  到达代理机构合同详情页面
	 * @param 
	 * @return ModelAndView
	 * @throws Exception ModelAndView
	 * @author: shenjz
	 * @Create Date:2011-3-16  下午05:12:14
	 * @Modifier: shenjz
	 * @Modified Date:2011-3-16  下午05:12:14
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=toContractDetailForAgency")
	public ModelAndView toContractDetailForAgency(HttpServletRequest request,SessionStatus status,String projectId)throws Exception {
		Map model = new HashMap();
		String objId = request.getParameter("objId");
		Contract contract = contractService.get(objId);
		List<ContractPaymentApplyInfo>  applyInfoList = contract.getContractPaymentApplyInfo();
		model.put("applyInfoList", applyInfoList);
		model.put("contract", contract);
		return  new ModelAndView("toContractDetailForAgency",model);
	}
	
	/**
	 * 
	 * Description :通过不同角色跳转到合同管理页面 
	 * Create Date: 2010-6-29下午02:14:59 by liuke  Modified Date: 2010-6-29下午02:14:59 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params = "method=toContractSupplierListByRole")
	public ModelAndView toContractSupplierListByRole(HttpServletRequest request) throws Exception {
		logger.debug("\nes ContractController||toContractSupplierListByRole\n");
		User user = AuthenticationHelper.getCurrentUser(true);
		OrgInfo orgInfo = (OrgInfo) user.getOrgInfo();
		String role = "";
		String returnUrl = "contractSupplierList";
		if(orgInfo.getSupplierId()!=null){//如果当前角色是供应商
			role = "supplier";
		}else if(orgInfo.getBuyerId()!=null){//如果当前角色是采购人
			role = "buyer";
			returnUrl = "contractBuyerList";
		}
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("role", role);
		model.put("orgInfoId", orgInfo.getObjId());
		return new ModelAndView(returnUrl, model);	
	}
}
