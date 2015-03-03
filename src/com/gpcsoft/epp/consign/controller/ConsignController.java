package com.gpcsoft.epp.consign.controller;

import java.util.ArrayList;
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

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.common.domain.EsExceptionEnum;
import com.gpcsoft.epp.common.domain.SeparationEnum;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.consign.domain.Consign;
import com.gpcsoft.epp.consign.service.ConsignService;
import com.gpcsoft.epp.consign.service.ConsignTaskPlanService;
import com.gpcsoft.epp.taskplan.domain.TaskPlan;
import com.gpcsoft.epp.taskplan.service.TaskPlanService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.auth.service.AttachmentService;

/**
  * @gpcsoft.view value="consignFormView"
  *  url="view/es/planform/consign/consignForm.jsp"
  * @gpcsoft.view value="consignDetailView"
  *  url="view/es/planform/consign/consignDetail.jsp"
  * @gpcsoft.view value="createBlockTradeConsignFormView"
  *  url="view/es/planform/consign/createBlockTradeConsignForm.jsp"
  * @gpcsoft.view value="updateBlockTradeConsignFormView"
  *  url="view/es/planform/consign/updateBlockTradeConsignForm.jsp"
  * @gpcsoft.view value="blockTradeConsignDetailView"
  *  url="view/es/planform/consign/blockTradeConsignDetail.jsp"
  * @gpcsoft.view value="addconsignListPage"
  *  url="view/es/planform/consign/consignFormForBuyer.jsp"
  * @gpcsoft.view value="toUpdateConsignForBuyer"
  *  url="view/es/planform/consign/updateConsignFormForBuyer.jsp"
  *  @gpcsoft.view value="toConsignDetailForBuyer"
  *  url="view/es/planform/consign/consignDatailForBuyer.jsp"
  *  @gpcsoft.view value="toConsignDetailForAgency"
  *  url="view/es/planform/consign/consignDatailForAgency.jsp"
  *  @gpcsoft.view value="toConfirmConsignForAgency"
  *  url="view/es/planform/consign/consignFormForAgencyAccept.jsp"
  *  
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={Consign.class,TaskPlan.class})
@RequestMapping("/ConsignController.do")//页面请求路径,可修改
public class ConsignController extends AnnotationMultiController<Consign> {

	@Autowired(required=true) @Qualifier("consignServiceImpl")
	private ConsignService consignService;
	
	@Autowired(required=true) @Qualifier("consignTaskPlanServiceImpl")
	private ConsignTaskPlanService consignTaskPlanService;
	
	@Autowired(required=true) @Qualifier("taskPlanServiceImpl")
	private TaskPlanService taskPlanService;
	
	@Autowired(required = true) @Qualifier("attachmentServiceImpl")
	private AttachmentService attachmentService;
	
	@Override
	public Page<Consign> onList(HttpServletRequest request) throws Exception {
		return super.onList(request);
	}

	/**
	 * FuncName: onFind
	 * Description :  重写继承的基类方法:用于查询与当前登录人相匹配的委托协议列表的前置条件
	 * @param query:组装的用于数据查询的对象, user:当前登录用户,findMethod:用以判断当前登录角色
	 * @return QueryObject
	 * @author: shenjz
	 * @Create Date:2010-12-20  下午03:31:25
	 * @Modifier: shenjz
	 * @Modified Date:2010-12-20  下午03:31:25
	 */
	@Override
	@SuppressWarnings("unchecked")
	protected QueryObject onFind(QueryObject query, HttpServletRequest request)throws Exception {
		String findMethod = request.getParameter("findMethod");
		User user = AuthenticationHelper.getCurrentUser();
		return consignService.getQueryObject(query, user, findMethod);
	}
	
	/**
	 * FuncName:toAddConsignListPage
	 * Description: 跳转到新增委托协议页面
	 * @param   request
	 * @return ModelAndView
	 * @author liuke
	 * @Create Date: 2010-5-29上午10:59:19 by   
	 * @Modifier liuke
	 * @Modified Date: 2010-5-29上午10:59:19 by  
	 */
	@RequestMapping(params = "method=toAddConsignListPage")
	public ModelAndView toAddConsignListPage(HttpServletRequest request)throws Exception {
		logger.debug("\nes ConsignController||toAddConsignListPage\n");
		Map<String,Object> model = new HashMap<String,Object> ();
		User user = AuthenticationHelper.getCurrentUser();
		OrgInfo buyer = (OrgInfo)user.getOrgInfo();
		model.put("consignCode", consignService.createConsignCodeByQO(null));
		model.put("consBuyer", buyer);
		model.put("orgName", buyer.getOrgName());
		model.put("consBuyerLinker", user.getEmp().getName());
		model.put("consBuyerTel", user.getEmp().getTelOffice());
		return new ModelAndView("addconsignListPage", model);
	}
	
	/**
	 * FuncName:toSaveOrUpdate
	 * Description:跳转到修改页面
	 * @param   objId:委托协议主键,request
	 * @return  ModelAndView
	 * @author liuke
	 * @Create Date: 2010-5-29上午10:59:19    
	 * @Modifier liuke
	 * @Modified Date: 2010-5-29上午10:59:19 
	 */
	@RequestMapping(params = "method=toSaveOrUpdate")
	public ModelAndView toSaveOrUpdate(String objId,HttpServletRequest request)throws Exception {
		logger.debug("\nes ConsignController||toSaveOrUpdate\n");
		String isSave = request.getParameter("isSave");
		Consign consign = consignService.get(objId);//获得委托书
		List<TaskPlan> taskList = consignTaskPlanService.getTaskPlanByConsign(objId);//获得采购申报书
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("consign", consign);
		model.put("isSave", isSave);
		if(taskList != null && taskList.size() > 0){
			model.put("taskPlanIds", taskList.get(0).getObjId());
			model.put("taskPlanId", taskList.get(0).getObjId());
			model.put("taskPlan", taskList.get(0));
		}
		return new ModelAndView("consignFormView", model);
	}
	
	/**
	 * FuncName:batchSubmitConsign
	 * Description :  提交委托计划
	 * @param   objIds:委托协议主键,request
	 * @return  ModelAndView
	 * @author liangxj
	 * @Create Date: 2010-6-6下午05:50:50 
	 * @Modifier  liangxj
	 * @Modified Date: 2010-6-6下午05:50:50 
	 */
	@RequestMapping(params = "method=batchSubmitConsign")
	public ModelAndView batchSubmitConsign(String objIds,HttpServletRequest request)throws Exception {
		logger.debug("\nes ConsignController||batchSubmitConsign\n");
		String useStatus = request.getParameter("useStatus");
		String confirmStatus = request.getParameter("confirmStatus");
//		consignService.saveBatchSubmitConsign(objIds,useStatus,confirmStatus);
		//modify by yangx
		List<Consign> consignList = new ArrayList<Consign>();
		for (String objId:objIds.split(SeparationEnum.COMMA)) {//委托协议Id
			Consign consign = new Consign();
			consign.setObjId(objId);
			consign.setUseStatus(useStatus);
			consign.setConfirmStatus(confirmStatus);
			consignList.add(consign);
		}
		consignService.saveSubmitConsign(consignList,null,null);
		return new ModelAndView(Constants.JSON_VIEW);				
	}

	/**
	 * FuncName:toCreateBlockTradeDraft
	 * Description :  跳转到大宗项目委托新增页面
	 * @param   taskPlanIds:大宗申报书主键,request
	 * @return  ModelAndView
	 * @author liuke
	 * @Create Date: 2010-7-8上午10:59:19 
	 * @Modifier liuke
	 * @Modified Date: 2010-7-8上午10:59:19 
	 */
	@RequestMapping(params = "method=toCreateBlockTradeDraft")
	public ModelAndView toCreateBlockTradeDraft(String taskPlanIds,HttpServletRequest request)throws Exception {
		logger.debug("\nes ConsignController||toCreateBlockTradeDraft\n");
		List<TaskPlan> taskPlanList = taskPlanService.getTaskPlanListByTaskPlanIds(taskPlanIds);
		String num = Integer.toString(taskPlanList.size());
		Map<String,Object> model = new HashMap<String,Object>();
		Consign consign = new Consign();
		consign.setConsCode(consignService.createConsignCodeByQO(null));
		consign.setConsBuyer((OrgInfo)taskPlanList.get(0).getBudget());//采购人联系方式  大宗项目采购人是创立委托书的采购办的人
		consign.setConsBuyerName(((OrgInfo)taskPlanList.get(0).getBudget()).getOrgName());
		consign.setConsBuyerLinker(taskPlanList.get(0).getBudgetLinker());
		consign.setConsBuyerTel(taskPlanList.get(0).getBudgetLinkerTel());
		model.put("taskPlanList", taskPlanList);
		model.put("num", num);
		model.put("consign", consign);
		return new ModelAndView("createBlockTradeConsignFormView", model);
	}
	
	/**
	 * FuncName:toUpdateBlockTradeDraft
	 * Description:跳转到大宗委托修改页面
	 * @param   objId:大宗委托主键
	 * @return  ModelAndView
	 * @author liuke
	 * @Create Date: 2010-5-29上午10:59:19 
	 * @Modifier   liuke
	 * @Modified Date: 2010-5-29上午10:59:19 
	 */
	@RequestMapping(params = "method=toUpdateBlockTradeDraft")
	public ModelAndView toUpdateBlockTradeDraft(String objId,HttpServletRequest request)throws Exception {
		logger.debug("\nes ConsignController||toUpdateBlockTradeDraft\n");
		Consign consign = consignService.getConsignByObjId(objId);
		List<TaskPlan> taskList = consignTaskPlanService.getTaskPlanByConsign(objId);//获得采购申报书
		String num = Integer.toString(taskList.size());
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("num", num);
		model.put("consign", consign);
		model.put("taskPlanList", taskList);
		return new ModelAndView("updateBlockTradeConsignFormView", model);
	}
	
	/**
	 * FuncName:createBlockTradeDraftConsign
	 * Description: 创建大宗委托协议
	 * @param consign:委托协议对象,request,status
	 * @return ModelAndView
	 * @author wanghz
	 * @Create Date 2010-9-7 上午11:46:23 
	 */
	@RequestMapping(params = "method=createBlockTradeDraftConsign")
	public ModelAndView createBlockTradeDraftConsign(Consign consign,HttpServletRequest request, SessionStatus status)throws Exception {
		logger.debug("\nes ConsignController||createBlockTradeDraftConsign\n");
		String attachRelaId = request.getParameter("attachRelaId");
		consign.setConsContentsAtt(attachRelaId);
		this.checkConsignAtt(consign.getConsContentsAtt());
		Map<String,Object> model = new HashMap<String,Object>();
		String taskPlanIds = request.getParameter("taskPlanIds");
		consignService.saveCreateBlockTradeDraftConsign(consign,taskPlanIds);
	  	status.setComplete();
		model.put("consignId", consign.getObjId());
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/**
	 * FuncName:updateBlockTradeDraftConsign
	 * Description: 更新大宗委托协议
	 * @param consign:委托协议对象,request,status
	 * @return ModelAndView
	 * @author wanghz
	 * @Create Date 2010-9-7 上午11:46:34 
	 */
	@RequestMapping(params = "method=updateBlockTradeDraftConsign")
	public ModelAndView updateBlockTradeDraftConsign(Consign consign,HttpServletRequest request, SessionStatus status)throws Exception {
		logger.debug("\nes ConsignController||updateBlockTradeDraftConsign\n");
		String attachRelaId = request.getParameter("attachRelaId");
		consign.setConsContentsAtt(attachRelaId);
		this.checkConsignAtt(consign.getConsContentsAtt());
		consignService.updateBlockTradeDraftConsign(consign);
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/**
	 * FuncName:submitBlockTradeDraftConsign
	 * Description: 提交大宗委托协议
	 * @param consign:委托协议对象,request,status
	 * @return ModelAndView
	 * @author wanghz
	 * @Create Date 2010-9-7 上午11:48:04 
	 */
	@RequestMapping(params = "method=submitBlockTradeDraftConsign")
	public ModelAndView submitBlockTradeDraftConsign(Consign consign,HttpServletRequest request, SessionStatus status)throws Exception {
		logger.debug("\nes ConsignController||submitBlockTradeDraftConsign\n");
		String attachRelaId = request.getParameter("attachRelaId");
		consign.setConsContentsAtt(attachRelaId);
		this.checkConsignAtt(consign.getConsContentsAtt());
		Map<String,Object> model = new HashMap<String,Object>();
		String taskPlanIds = request.getParameter("taskPlanIds");
		consignService.saveSubmitBlockTradeDraftConsign(consign,taskPlanIds);
	  	status.setComplete();
		model.put("consignId", consign.getObjId());
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * FuncName:getConsignList
	 * Description :  获取委托协议列表
	 * @param   request
	 * @return  ModelAndView
	 * @author yangx
	 * @Create Date: 2010-7-13下午04:22:45    
	 * @Modifier yangx
	 * @Modified Date: 2010-7-13下午04:22:45 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params="method=getConsignList")
	public ModelAndView getConsignList(HttpServletRequest request)throws Exception {
		logger.debug("\nes=ConsignController||getConsignList\n");
		User user = AuthenticationHelper.getCurrentUser();
		OrgInfo orgInfo = (OrgInfo)user.getOrgInfo();
		String confirmStatus = request.getParameter("confirmStatus");
		String consCode = request.getParameter("consCode");
		String consName = request.getParameter("consName");
		String taskType = request.getParameter("taskType");//申报书类型
		Map<String,Object> model=new HashMap<String,Object>();
		Page<Consign> page = prePage(request);
		Page<Consign> pageData = consignService.getConsignListByObject(page, orgInfo, confirmStatus, consCode, consName, taskType);
		endPage(model, pageData, request);
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	/**
	 * FuncName:showDetail
	 * Description :  查看详细信息
	 * @param   request
	 * @return  ModelAndView
	 * @author liangxj
	 * @Create Date: 2010-5-20下午07:00:02    
	 * @Modifier liangxj
	 * @Modified Date: 2010-5-20下午07:00:02 
	 */
	@RequestMapping(params = "method=showDetail")
	public ModelAndView showDetail(String objId,HttpServletRequest request)throws Exception {
		logger.debug("\nes=ConsignController||showDetail\n");
		String isBlockTrade = request.getParameter("isBlockTrade");//是否为大宗委托
		Consign consign = consignService.getConsignByObjId(objId); //获得委托协议
		String returntype = request.getParameter("returntype");//返回是否为查看页面
		List<TaskPlan> taskList = consignTaskPlanService.getTaskPlanByConsign(objId);//获得采购申报书
		String fromto = request.getParameter("fromto");//页面是从哪里过来
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("returntype", returntype);
		model.put("fromto", fromto);
		model.put("consign", consign);
		String returnUrl="consignDetailView";
		if(taskList != null && taskList.size() > 0) {
			model.put("tpList", taskList);
		}
        if(null!=isBlockTrade&&"isBlockTrade".equals(isBlockTrade)) {//表示为查看大宗项目
        	returnUrl="blockTradeConsignDetailView";
        }
		return new ModelAndView(returnUrl, model);
	}
	
	/**
	 * FuncName:deleteTaskPlanForBlockTradeProject
	 * Description:删除大宗委托书中的申报书
	 * @param request
	 * @return  ModelAndView
	 * @author  liuke
	 */
	@RequestMapping(params = "method=deleteTaskPlanForBlockTradeProject")
	public ModelAndView deleteTaskPlanForBlockTradeProject(HttpServletRequest request)throws Exception {
		logger.debug("\nes=ConsignController||deleteTaskPlanForBlockTradeProject\n");
		String taskPlanId = request.getParameter("taskPlanId");
		String consignId = request.getParameter("consignId");
		consignService.removeTaskPlanBytaskPlanIdAndProjectId(taskPlanId, consignId);
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/**
	 * FuncName:deleteConsign
	 * Description:删除大宗委托书
	 * @param   request
	 * @return  ModelAndView
	 * @author liuke
	 * @Create Date: 2010-7-14下午05:20:29 
	 * @Modifier  liuke
	 * @Modified Date: 2010-7-14下午05:20:29 
	 */
	@RequestMapping(params = "method=deleteConsign")
	public ModelAndView deleteConsign(HttpServletRequest request)throws Exception {
		logger.debug("\nes=ConsignController||deleteConsign\n");
		String consignId = request.getParameter("consignId");
		consignService.removeConsign(consignId);
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/**
	 * FuncName:getBackConsign
	 * Description: 获取委托协议
	 * @param type:,count:,request
	 * @return ModelAndView
	 * @author wanghz
	 * @Create Date 2010-9-2 下午02:29:32 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=getBackConsign")
	public ModelAndView getBackConsign(String type,int count,HttpServletRequest request)throws EsException {
		logger.debug("\nes=ConsignController||getBackConsign\n");
		String confirmStatus = request.getParameter("confirmStatus");
		String useStatus = request.getParameter("useStatus");
		User user=AuthenticationHelper.getCurrentUser();
		List<Consign> consignList = (List<Consign>)consignService.getConsignListOrCountByQuery(type, count, confirmStatus, useStatus, user);
		Map model = new HashMap();
		model.put("consignList", consignList);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/**
	 * FuncName:toCreateConsign
	 * Description: 跳转到创建委托协议页面
	 * @param request,taskPlanId 申报书主键
	 * @return ModelAndView
	 * @author wanghz
s	 * @Create Date 2010-9-6 下午03:20:55 
	 */
	@RequestMapping(params = "method=toCreateConsign")
	public ModelAndView toCreateConsign(HttpServletRequest request,String taskPlanId)throws EsException {
		logger.debug("\nes=ConsignController||toCreateConsign\n");
		Map<String,Object> model = new HashMap<String,Object>();
		TaskPlan taskPlan = taskPlanService.get(taskPlanId);
		model.put("taskPlanId", taskPlanId);
		Consign consign = new Consign();
		consign.setConsCode(consignService.createConsignCodeByQO(null));
		User user = AuthenticationHelper.getCurrentUser();
		OrgInfo orgInfo = (OrgInfo)user.getOrgInfo();
		consign.setConsAgent(orgInfo);//设定代理机构
		consign.setConsAgentName(orgInfo.getOrgName());
		consign.setConsAgentLinker(user.getEmp().getName());
		consign.setConsAgentTel(user.getEmp().getTelOffice());
		OrgInfo buyer = taskPlan.getBudget();//采购人联系方式
		consign.setConsBuyer(buyer);
		consign.setConsBuyerName(buyer.getOrgName());
		consign.setConsBuyerLinker(buyer.getCompany().getContact());
		consign.setConsBuyerTel(buyer.getCompany().getTel());
		model.put("taskPlan", taskPlan);
		model.put("consign", consign);
		return new ModelAndView("consignFormView", model);
	}
	
	/**
	 * FuncName:createConsign
	 * Description: 创建委托协议
	 * @param consign:委托协议对象,request,status,taskPlanIds:申报书主键
	 * @return ModelAndView
	 * @author wanghz
	 * @Create Date 2010-9-6 下午03:47:36 
	 */
	@RequestMapping(params = "method=createConsign")
	public ModelAndView createConsign(Consign consign,HttpServletRequest request, SessionStatus status,String taskPlanIds)throws Exception {
		logger.debug("\nes ConsignController||createConsign\n");
		String attachRelaId = request.getParameter("attachRelaId");
		consign.setConsContentsAtt(attachRelaId);
		consignService.createConsign(consign,taskPlanIds,consign.getObjId()); 
	  	status.setComplete();
		Map<String,String> model = new HashMap<String,String>();	
		model.put("consignId", consign.getObjId());
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/**
	 * FuncName:submitConsign
	 * Description: 提交委托协议
	 * @param consign
	 * @return ModelAndView
	 * @author wanghz
	 * @Create Date 2010-9-6 下午03:47:36 
	 */
	@RequestMapping(params = "method=submitConsign")
	public ModelAndView submitConsign(Consign consign,HttpServletRequest request, SessionStatus status,String taskPlanIds)throws Exception {
		logger.debug("\nes ConsignController||submitConsign\n");
		String attachRelaId = request.getParameter("attachRelaId");
		consign.setConsContentsAtt(attachRelaId);
		this.checkConsignAtt(consign.getConsContentsAtt());
		List<Consign> consignList = new ArrayList<Consign>();
		consignList.add(consign);
		consignService.saveSubmitConsign(consignList,taskPlanIds,consign.getObjId()); 
	  	status.setComplete();
		Map<String,String> model = new HashMap<String,String>();	
		model.put("consignId", consign.getObjId());
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/**
	 * FuncName:affirmConsignPass
	 * Description: 确认委托协议[确认]
	 * @param consign:委托协议对象,ids:委托协议主键(批量),request,status,opinion:申报书意见
	 * @return ModelAndView
	 * @author wanghz
	 * @Create Date 2010-9-6 下午04:53:31 
	 */
	@RequestMapping(params = "method=affirmConsignPass")
	public ModelAndView affirmConsignPass(Consign consign,String ids,HttpServletRequest request,SessionStatus status,String opinion)throws EsException {
		logger.debug("\nes ConsignController||affirmConsignPass\n");
		if(ids == null || ids.equals("")){
			ids = consign.getObjId();
		}
		consignService.saveAffirmConsign(ids, opinion,true);
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/**
	 * FuncName:affirmConsignReject
	 * Description: 确认委托协议[退回]
	 * @param consign:委托协议对象,request,status,opinion:审核意见
	 * @return ModelAndView
	 * @author wanghz
	 * @Create Date 2010-9-6 下午04:53:31 
	 */
	@RequestMapping(params = "method=affirmConsignReject")
	public ModelAndView affirmConsignReject(Consign consign,String ids,HttpServletRequest request,SessionStatus status,String opinion)throws EsException {
		logger.debug("\nes ConsignController||affirmConsignReject\n");
		if(ids == null || ids.equals("")){
			ids = consign.getObjId();
		}
		consignService.saveAffirmConsign(ids, opinion,false);
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/**
	 * FuncName:removeConsign
	 * Description:删除非大宗委托书
	 * @param   request
	 * @return  ModelAndView
	 * @author liuke
	 * @Create Date: 2010-7-14下午05:20:29 
	 * @Modifier  liuke
	 * @Modified Date: 2010-7-14下午05:20:29 
	 */
	@RequestMapping(params = "method=removeConsign")
	public ModelAndView removeConsign(HttpServletRequest request)throws Exception {
		String consignId = request.getParameter("objId");
		consignService.removeNotBlockConsign(consignId);
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/**
	 * FuncName:createConsignForBuyer
	 * Description :  采购人创建保存委托协议
	 * @param consign:委托协议对象,request,status
	 * @return ModelAndView
	 * @author shenjianzhuang
	 * @Create Date: 2010-10-12下午03:01:14 
	 * @Modifier   shenjianzhuang
	 * @Modified Date: 2010-10-12下午03:01:14 
	 */	
	@RequestMapping(params = "method=createConsignForBuyer")
	public ModelAndView createConsignForBuyer(Consign consign,HttpServletRequest request, SessionStatus status)throws EsException {
		logger.debug("\nes ConsignController||createConsignForBuyer\n");
		consignService.saveConsign(consign) ;
	  	status.setComplete();
		Map<String,String> model = new HashMap<String,String>();	
		model.put("consignId", consign.getObjId());
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/**
	 * FuncName:submitConsignForBuyer
	 * Description :  采购人创建提交委托协议
	 * @param consign:委托协议,request,status
	 * @return  ModelAndView
	 * @author shenjianzhuang
	 * @Create Date: 2010-10-12下午04:06:44   
	 * @Modifier shenjianzhuang
	 * @Modified Date: 2010-10-12下午04:06:44 
	 */		
	@RequestMapping(params = "method=submitConsignForBuyer")
	public ModelAndView submitConsignForBuyer(Consign consign,HttpServletRequest request, SessionStatus status)throws EsException {
		logger.debug("\nes ConsignController||submitConsignForBuyer\n");
		consignService.saveConsign(consign) ;
	  	status.setComplete();
		Map<String,String> model = new HashMap<String,String>();	
		model.put("consignId", consign.getObjId());
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/**
	 * FuncName:toUpdateConsignForBuyer
	 * Description :  采购人到达修改委托协议的页面
	 * @param objId:委托协议主键,request,status
	 * @return  ModelAndView
	 * @author shenjianzhuang
	 * @Create Date: 2010-10-12下午04:06:44 
	 * @Modifier shenjianzhuang
	 * @Modified Date: 2010-10-12下午04:06:44 
	 */		
	@RequestMapping(params = "method=toUpdateConsignForBuyer")
	public ModelAndView toUpdateConsignForBuyer(String objId,HttpServletRequest request, SessionStatus status)throws EsException {
		logger.debug("\nes ConsignController||toUpdateConsignForBuyer\n");
		Consign consign = consignService.getConsignByObjId(objId);
		Map<String,Object> model = new HashMap<String,Object>();	
		model.put("consign", consign);
		return new ModelAndView("toUpdateConsignForBuyer", model);
	}
	
	/**
	 * FuncName:updateSaveConsignForBuyer
	 * Description :  采购人修改保存委托协议
	 * @param consign:委托协议对象,request,status,consId
	 * @return  ModelAndView
	 * @author shenjianzhuang
	 * @Create Date: 2010-10-12下午04:06:44 
	 * @Modifier shenjianzhuang
	 * @Modified Date: 2010-10-12下午04:06:44 
	 */		
	@RequestMapping(params = "method=updateSaveConsignForBuyer")
	public ModelAndView updateSaveConsignForBuyer(Consign consign ,HttpServletRequest request, SessionStatus status,String consId)throws EsException {
		logger.debug("\nes ConsignController||updateSaveConsignForBuyer\n");
		Consign consigns = consignService.getConsignByObjId(consId);
		consigns.setConsName(consign.getConsName());
		consigns.setEffectiveStartTime(consign.getEffectiveStartTime());
		consigns.setEffectiveEndTime(consign.getEffectiveEndTime());
		consigns.setConsTime(consign.getConsTime());
		consigns.setConsFinishTime(consign.getConsFinishTime());
		consigns.setConsType(consign.getConsType());
		consignService.saveConsign(consigns);
	  	status.setComplete();
		Map<String,String> model = new HashMap<String,String>();	
		model.put("consignId", consign.getObjId());
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/**
	 * FuncName:updateSubmitConsignForBuyer
	 * Description :  采购人修改提交委托协议
	 * @param consign:委托协议对象,request,status,consId:委托协议主键
	 * @return  ModelAndView
	 * @author shenjianzhuang
	 * @Create Date: 2010-10-12下午04:06:44 
	 * @Modifier shenjianzhuang
	 * @Modified Date: 2010-10-12下午04:06:44 
	 */		
	@RequestMapping(params = "method=updateSubmitConsignForBuyer")
	public ModelAndView updateSubmitConsignForBuyer(Consign consign,HttpServletRequest request, SessionStatus status,String consId)throws EsException {
		logger.debug("\nes ConsignController||updateSubmitConsignForBuyer\n");
		Consign consigns = consignService.getConsignByObjId(consId);
		consigns.setConsName(consign.getConsName());
		consigns.setEffectiveEndTime(consign.getEffectiveEndTime());
		consigns.setEffectiveStartTime(consign.getEffectiveStartTime());
		consigns.setConsTime(consign.getConsTime());
		consigns.setConsFinishTime(consign.getConsFinishTime());
		consigns.setUseStatus(CommonEnum.USER_STATUS_FORMAL);
		consigns.setConsType(consign.getConsType());
		consignService.saveConsign(consigns);
	  	status.setComplete();
		Map<String,String> model = new HashMap<String,String>();	
		model.put("consignId", consign.getObjId());
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/**
	 * FuncName:toConsignDetailForBuyer
	 * Description :  采购人查看委托协议详情
	 * @param objId:委托协议主键,request,status
	 * @return  ModelAndView
	 * @author shenjianzhuang
	 * @Create Date: 2010-10-12下午04:06:44 
	 * @Modifier shenjianzhuang
	 * @Modified Date: 2010-10-12下午04:06:44 
	 */		
	@RequestMapping(params = "method=toConsignDetailForBuyer")
	public ModelAndView toConsignDetailForBuyer(String objId,HttpServletRequest request, SessionStatus status)throws EsException {
		logger.debug("\nes ConsignController||toConsignDetailForBuyer\n");
		Consign consign = consignService.getConsignByObjId(objId);
		Map<String,Object> model = new HashMap<String,Object>();	
		model.put("consign", consign);
		return new ModelAndView("toConsignDetailForBuyer", model);
	}
	
	/**
	 * FuncName:toConfirmConsignForAgency
	 * Description :  代理机构到达确认委托协议页面
	 * @param objId:委托协议主键,opinion:审核意见,request,status
	 * @return  ModelAndView
	 * @author shenjianzhuang
	 * @Create Date: 2010-10-13上午10:21:21 
	 * @Modifier shenjianzhuang
	 * @Modified Date: 2010-10-13上午10:21:21 
	 */	
	@RequestMapping(params = "method=toConfirmConsignForAgency")
	public ModelAndView toConfirmConsignForAgency(String objId,String opinion,HttpServletRequest request, SessionStatus status)throws EsException {
		logger.debug("\nes ConsignController||toConfirmConsignForAgency\n");
		Consign consign = consignService.getConsignByObjId(objId);
		Map<String,Object> model = new HashMap<String,Object>();	
		model.put("consign", consign);
		return new ModelAndView("toConfirmConsignForAgency", model);
	}
	
	/**
	 * FuncName:confirmConsignForAgency
	 * Description :  代理机构确认委托协议
	 * @param objId:委托协议主键,request,status
	 * @return  ModelAndView
	 * @author shenjianzhuang
	 * @Create Date: 2010-10-13上午10:21:21 
	 * @Modifier shenjianzhuang
	 * @Modified Date: 2010-10-13上午10:21:21 
	 */	
	@RequestMapping(params = "method=confirmConsignForAgency")
	public ModelAndView confirmConsignForAgency(String objId,String ids,String opinion,HttpServletRequest request, SessionStatus status)throws EsException {
		logger.debug("\nes ConsignController||confirmConsignForAgency\n");
		if(ids == null || ids.equals("")){
			ids = objId;
		}
		consignService.saveAffirmConsign(ids, opinion,true);
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/**
	 * FuncName:backConsignForAgency
	 * Description :  代理机构退回委托协议
	 * @param objId:委托协议主键，ids:委托协议主键,opinion:审核意见,request,status
	 * @return  ModelAndView
	 * @author shenjianzhuang
	 * @Create Date: 2010-10-13上午10:21:21   
	 * @Modifier shenjianzhuang
	 * @Modified Date: 2010-10-13上午10:21:21 
	 */	
	@RequestMapping(params = "method=backConsignForAgency")
	public ModelAndView backConsignForAgency(String objId,String ids,String opinion,HttpServletRequest request, SessionStatus status)throws EsException {
		logger.debug("\nes ConsignController||backConsignForAgency\n");
		if(ids == null || ids.equals("")){
			ids = objId;
		}
		consignService.saveAffirmConsign(ids, opinion,false);
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/**
	 * FuncName:toConsignDetailForAgency
	 * Description:代理机构查看委托协议详情
	 * @param objId:委托协议主键,request,status
	 * @return ModelAndView
	 * @author shenjianzhuang
	 * @Create Date: 2010-10-12下午04:06:44 
	 * @Modifier shenjianzhuang
	 * @Modified Date: 2010-10-12下午04:06:44 
	 */		
	@RequestMapping(params = "method=toConsignDetailForAgency")
	public ModelAndView toConsignDetailForAgency(String objId,HttpServletRequest request, SessionStatus status)throws EsException {
		logger.debug("\nes ConsignController||toConsignDetailForAgency\n");
		Consign consign = consignService.getConsignByObjId(objId);
		Map<String,Object> model = new HashMap<String,Object>();	
		model.put("consign", consign);
		return new ModelAndView("toConsignDetailForAgency", model);
	}
	
	/** 
	 * FunctionName:checkConsignAtt
	 * Description:根据关联附件Id获取是否上传了附件
	 * @param   consContentsAttId:关联附件Id
	 * @return void
	 * @author yangx
	 * @Create Date: 2010-10-26下午05:00:21 
	 * @Modifier  yangx
	 * @Modified Date: 2010-10-26下午05:00:21  
	 */
	private void checkConsignAtt(String consContentsAttId){
		if (consContentsAttId!=null&&!"".equals(consContentsAttId)) {
			List<Attachment> listAttachment = attachmentService.getAttachmentsByRealID(consContentsAttId);
			if (listAttachment==null||listAttachment.size()<1) {
				throw new EsException(messageSource.getMessage(EsExceptionEnum.CONSIGN_ATT_NULL));
			}
		}else{
			throw new EsException(messageSource.getMessage(EsExceptionEnum.CONSIGN_ATT_NULL));
		}
	}
}

