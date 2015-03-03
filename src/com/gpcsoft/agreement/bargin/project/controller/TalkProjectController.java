package com.gpcsoft.agreement.bargin.project.controller;

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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.agreement.bargin.bidding.domain.BiddingChat;
import com.gpcsoft.agreement.bargin.bidding.domain.BiddingRecord;
import com.gpcsoft.agreement.bargin.bidding.service.BiddingChatService;
import com.gpcsoft.agreement.bargin.bidding.service.BiddingRecordService;
import com.gpcsoft.agreement.bargin.invitation.service.InvitationService;
import com.gpcsoft.agreement.bargin.project.service.BargainProjectService;
import com.gpcsoft.agreement.bargin.require.domain.RequireItem;
import com.gpcsoft.agreement.bargin.require.service.RequireItemService;
import com.gpcsoft.agreement.bargin.requirement.domain.Requirement;
import com.gpcsoft.agreement.bargin.requirement.service.RequirementService;
import com.gpcsoft.agreement.cart.domain.ShoppingCartItem;
import com.gpcsoft.agreement.cart.service.ShoppingCartService;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.organization.service.OrgInfoService;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.common.domain.EbuyMethodEnum;
import com.gpcsoft.epp.inviterollrequ.domain.Inviterollrequ;
import com.gpcsoft.epp.project.domain.ProjProcessStatusEnum;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.service.ProjectService;
import com.gpcsoft.epp.signuprecord.domain.SignUprecord;
import com.gpcsoft.epp.signuprecord.service.SignUprecordService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;

/**
  *  @gpcsoft.view value="updateTalkProjectView"
  *  url="view/agreement/bargin/project/talk/s_talk_project_modify.jsp"
  *  
  *  @gpcsoft.view value="toStalkProjectDetailView"
  *  url="view/agreement/bargin/project/talk/s_talk_project_detail_view.jsp"
  *  
  *  @gpcsoft.view value="createTalkProjectView"
  *  url="view/agreement/bargin/project/talk/start_talk_project.jsp"
  *  
  *  @gpcsoft.view value="intoTalkHallBuyerXEJYView"
  *  url="view/agreement/bargin/project/talk/s_talk_hall.jsp"
  *  
  *  @gpcsoft.view value="intoTalkHallSupplierView"
  *  url="view/agreement/bargin/project/talk/s_talk_hall_supplier.jsp"
  *  
  *  @gpcsoft.view value="talkProjectDetailView"
  *  url="view/agreement/bargin/project/talk/talk_project_detail.jsp"
  *  
  *  @gpcsoft.view value="talkBiddingRecordAllView"
  *  url="view/agreement/bargin/project/talk/talk_bidding_record_all.jsp"
  *  
  *  @gpcsoft.view value="talkBiddingAndChatView"
  *  url="view/agreement/bargin/project/talk/talk_bidding_and_chat.jsp"
 */

@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={Project.class})
@RequestMapping("/TalkProjectController.do")//页面请求路径,可修改
public class TalkProjectController extends AnnotationMultiController<Project> 
{
	@Autowired(required=true) @Qualifier("bargainProjectServiceImpl")
	private BargainProjectService bargainProjectService;
	
	@Autowired(required=true) @Qualifier("projectServiceImpl")
	private ProjectService projectServiceImpl;
	
	@Autowired(required=true) @Qualifier("requireItemServiceImpl")
	private RequireItemService requireItemServiceImpl;
	
	@Autowired(required=true) @Qualifier("shoppingCartServiceImpl")
	private ShoppingCartService shoppingCartService;
	
	@Autowired(required=true) @Qualifier("signUprecordServiceImpl")
	private SignUprecordService signUprecordService;
	
	@Autowired(required=true) @Qualifier("biddingRecordServiceImpl")
	private BiddingRecordService biddingRecordServiceImpl;
	
	@Autowired(required=true) @Qualifier("invitationServiceImpl")
	private InvitationService invitationService;
	
	@Autowired(required=true) @Qualifier("requirementServiceImpl")
	private RequirementService requirementService;
	
	@Autowired(required=true) @Qualifier("biddingChatServiceImpl")
	private BiddingChatService biddingChatService;
	
	@Autowired(required=true) @Qualifier("orgInfoServiceImpl")
	private OrgInfoService orgInfoService;
	
	/** 
	 * Description :  跳转到议价项目的查看页面
	 * Create Date: 2011-7-21下午02:51:08 by likg  Modified Date: 2011-7-21下午02:51:08 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toTalkProjectDetailView")   
	public ModelAndView toTalkProjectDetailView(String objId, HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		String userType = request.getParameter("userType"); //用户类型
		
		//获取项目信息
		Project project = projectServiceImpl.get(objId);
		model.put("project", project);
		model.put("cureentDate", new Date().getTime());
		model.put("evalStartTime",project.getEvalStartTime().getTime());
		model.put("evalEndTime", project.getEvalEndTime().getTime());
		
		//获取项目发布者的机构信息
		OrgInfo publisher = orgInfoService.get(project.getBuyersId());
		model.put("createBuyerId", publisher.getBuyerId());
		model.put("createBuyerName", publisher.getOrgName());
		
		//计算项目进度[0:未开始；1:进行中；2:已结束]
		int[] projStatus = null;
		//项目结束
		if(ProjProcessStatusEnum.CALIBRATION_BID.equals(project.getProjProcessStatus())) {
			projStatus = new int[]{2, 2, 2, 2};
		}
		//确定成交供应商
		else if(ProjProcessStatusEnum.OPEN_BID.equals(project.getProjProcessStatus()) || new Date().after(project.getEvalEndTime())) {
			projStatus = new int[]{2, 2, 1, 0};
		}
		//供应商报价
		else if(ProjProcessStatusEnum.SUPPLIERS_BID.equals(project.getProjProcessStatus()) && new Date().after(project.getEvalStartTime()) && new Date().before(project.getEvalEndTime())) {
			projStatus = new int[]{2, 1, 0, 0};
		}
		//邀请供应商
		else if(ProjProcessStatusEnum.SUPPLIERS_BID.equals(project.getProjProcessStatus()) && project.getEvalStartTime().after(new Date())) {
			projStatus = new int[]{2, 0, 0, 0};
		}
		//未提交
		else if(CommonEnum.USER_STATUS_TEMP.equals(project.getUseStatus())) {
			projStatus = new int[]{0, 0, 0, 0};
		}
		model.put("projStatus", projStatus);
		
		//获取项目需求信息
		List<RequireItem> requireItemList =  requireItemServiceImpl.getRequireItemsByProjId(objId);
		model.put("requireItemList", requireItemList);
		
		//获取供应商报名信息
		List<SignUprecord> signUprecordList = new ArrayList<SignUprecord>();
		if("buyer".equals(userType) || "manager".equals(userType)) {
			signUprecordList = signUprecordService.getSignupRecordList(objId);
		}
		//当前用户是供应商，则拼装一个报名对象里面只包含当前供应商
		else {
			OrgInfo supplier = (OrgInfo)AuthenticationHelper.getCurrentUser(true).getOrgInfo();
			SignUprecord signUprecord = new SignUprecord();
			signUprecord.setSupplier(supplier);
			signUprecordList.add(signUprecord);
		}
		model.put("signUprecordList", signUprecordList);
		
		//第一个报名供应商
		OrgInfo firstSupplier = signUprecordList.get(0).getSupplier();
		
		//获取第一个报名供应商的最低报价
		param.clear();
		param.put("projectId", objId);
		param.put("supplierId", firstSupplier.getObjId());
		BiddingRecord minBiddingRecord = biddingRecordServiceImpl.getMinBiddingRecordByProjectAndSupplier(param);
		model.put("minBiddingRecord", minBiddingRecord);
		
		//获取第一个报名供应商的聊天信息
		param.clear();
		param.put("projId", objId);
		param.put("orgInfoId", firstSupplier.getObjId()); //既是发送人又是接收人
		List<BiddingChat> chatList = biddingChatService.findChatContent(param);
		model.put("chatList", chatList);
		model.put("sayOrgId", firstSupplier.getObjId());
		
		//用户类型
		model.put("userType", userType);
		
		return new ModelAndView("talkProjectDetailView", model);
	}
	
	/** 
	 * Description :  议价项目查看页面加载供应商最低报价和聊天信息页面
	 * Create Date: 2011-7-23上午11:57:02 by likg  Modified Date: 2011-7-23上午11:57:02 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=loadTalkBiddingAndChatView")
	public ModelAndView loadTalkBiddingAndChatView(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		String projId = request.getParameter("projId"); //项目id
		String supplierId = request.getParameter("supplierId"); //供应商id
		
		//获取供应商的最低报价
		param.put("projectId", projId);
		param.put("supplierId", supplierId);
		BiddingRecord minBiddingRecord = biddingRecordServiceImpl.getMinBiddingRecordByProjectAndSupplier(param);
		model.put("minBiddingRecord", minBiddingRecord);
		
		//获取供应商的聊天信息
		param.clear();
		param.put("projId", projId);
		param.put("orgInfoId", supplierId); //既是发送人又是接收人
		List<BiddingChat> chatList = biddingChatService.findChatContent(param);
		model.put("chatList", chatList);
		model.put("sayOrgId", supplierId);
		
		return new ModelAndView("talkBiddingAndChatView", model);
	}
	
	/** 
	 * Description :  修改议价项目
	 * Create Date: 2010-11-1上午10:02:35 by likg  Modified Date: 2010-11-1上午10:02:35 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=updateTalkProject")
	public ModelAndView updateTalkProject(Project project, HttpServletRequest request, SessionStatus status) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		
		//保存议价项目信息
		paramsMap.put("task", request.getParameter("task")); //任务书条目
		paramsMap.put("require", request.getParameter("require")); //需求条目
		Project resultProject = bargainProjectService.updateTalkProject(project, paramsMap);
		
		//项目提交时，调用发邀请函和站内信接口
		if(resultProject.getUseStatus().equals(CommonEnum.USER_STATUS_FORMAL)) {
			List<SignUprecord> signUprecords = signUprecordService.getSignupRecordList(project.getObjId());
			StringBuilder supplierIds = new StringBuilder();
			for(SignUprecord su : signUprecords){
				supplierIds.append(su.getSupplier().getObjId()).append(",");
			}
			if(supplierIds.indexOf(",") != -1){
				supplierIds = supplierIds.deleteCharAt(supplierIds.length()-1);
			}
			
			Inviterollrequ inviterollrequ = new Inviterollrequ();
			inviterollrequ.setProjCode(resultProject.getProjCode());
			inviterollrequ.setProjName(resultProject.getProjName());
			inviterollrequ.setProject(resultProject);
			String content = invitationService.getInviterollrequContent(resultProject);
			
			invitationService.saveInvitation(inviterollrequ, content, supplierIds.toString());
		}
		
		model.put("projId", resultProject.getObjId());
		status.setComplete();
		
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  跳转到查看项目页面
	 * Create Date: 2010-11-1上午11:47:29 by likg  Modified Date: 2010-11-1上午11:47:29 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toProjectDetailView")   
	public ModelAndView toProjectDetailView(HttpServletRequest request) throws Exception {
		Map<String ,Object> model = new HashMap<String , Object>();
		String objId = request.getParameter("projectId"); //项目id
		String userType = request.getParameter("userType"); //用户类型
		
		//获取项目信息
		Project project = projectServiceImpl.get(objId);
		model.put("project", project);
		model.put("cureentDate", new Date().getTime());
		model.put("evalStartTime",project.getEvalStartTime().getTime());
		model.put("evalEndTime", project.getEvalEndTime().getTime());
		
		//获取项目需求信息
		List<RequireItem> requireItemList =  requireItemServiceImpl.getRequireItemsByProjId(objId);
		model.put("requireItemList", requireItemList);
		
		//获取供应商报名信息
		if("buyer".equals(userType) || "manager".equals(userType)) {
			List<SignUprecord> signUprecordList = signUprecordService.getSignupRecordList(project.getObjId());
			model.put("signUprecordList", signUprecordList);
		}
		model.put("userType", userType);
		
		return new ModelAndView("toStalkProjectDetailView", model);
	}
	
	/** 
	 * Description :  跳转到修改议价项目页面
	 * Create Date: 2010-11-1上午11:55:47 by likg  Modified Date: 2010-11-1上午11:55:47 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toUpdateTalkProjectView")
	public ModelAndView toUpdateTalkProjectView(String objId, HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		//获取项目信息
		Project project = projectServiceImpl.get(objId);
		model.put("project", project);

		//获取需求条目信息
		List<RequireItem> requireItemList = requireItemServiceImpl.getRequireItemsByProjId(objId);
		model.put("requireItemList", requireItemList);
		
		return new ModelAndView("updateTalkProjectView", model);
	}
	
	/** 
	 * Description :  跳转到创建议价项目页面
	 * Create Date: 2010-11-1下午06:11:49 by likg  Modified Date: 2010-11-1下午06:11:49 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toCreateTalkProjectView")
	public ModelAndView toCreateTalkProjectView(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		
		request.getSession().removeAttribute("project");
		
		//获取购物车条目<由购物车创建议价项目>
		String cartItem = request.getParameter("cartItem");
		if(StringUtils.hasLength(cartItem)) {
			param.put("cartItem", cartItem);
			List<ShoppingCartItem> ShoppingCartItemList = shoppingCartService.getShoppingCarItem(param);
			model.put("ShoppingCartItemList", ShoppingCartItemList);
		}
		
		//获取采购需求<由采购需求创建议价项目>
		String requirementIds = request.getParameter("requirementIds");
		if(StringUtils.hasLength(requirementIds)) {
			List<Requirement> requirementList = requirementService.getRequirementList(requirementIds);
			model.put("requirementList", requirementList);
		}
		
		return new ModelAndView("createTalkProjectView", model);
	}
	
	/** 
	 * Description :  保存议价项目
	 * Create Date: 2010-11-1下午06:11:00 by likg  Modified Date: 2010-11-1下午06:11:00 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveTalkProject")
	public ModelAndView saveTalkProject(Project project, HttpServletRequest request, SessionStatus status) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("task", request.getParameter("task")); //任务书条目和项目关联关系
		paramMap.put("require", request.getParameter("require")); //需求条目
		paramMap.put("cartItemIds", request.getParameter("cartItemIds")); //购物车条目数量单价等
		paramMap.put("requirementIds", request.getParameter("requirementIds")); //采购需求Id by yucy
		paramMap.put("supplierIds", request.getParameter("supplierIds")); //报价供应商id
		
		paramMap.put("ebuyMethod", EbuyMethodEnum.TALK);  //采购方式-议价
		if(project.getUseStatus().equals(CommonEnum.USER_STATUS_FORMAL)){
			project.setProjProcessStatus(ProjProcessStatusEnum.SUPPLIERS_BID); //供应商投标
			project.setProjProcessStatusCN(ProjProcessStatusEnum.getCN(ProjProcessStatusEnum.SUPPLIERS_BID));
		}
		Project resultProject = bargainProjectService.saveTalkProject(project, paramMap);
		
		model.put("projId", resultProject.getObjId());
		status.setComplete();
		
		//项目提交时，调用发邀请函和站内信接口
		if(resultProject.getUseStatus().equals(CommonEnum.USER_STATUS_FORMAL)) {
			Inviterollrequ inviterollrequ = new Inviterollrequ();
			inviterollrequ.setProjCode(resultProject.getProjCode());
			inviterollrequ.setProjName(resultProject.getProjName());
			inviterollrequ.setProject(resultProject);
			String content = invitationService.getInviterollrequContent(resultProject);
			
			invitationService.saveInvitation(inviterollrequ, content, request.getParameter("supplierIds"));
		}
		
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  跳转到采购人的议价厅页面
	 * Create Date: 2010-11-8下午03:56:55 by likg  Modified Date: 2010-11-8下午03:56:55 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toBuyerTalkHall")
	public ModelAndView toBuyerTalkHall(HttpServletRequest request) throws Exception 
	{
		Map<String, Object> model = new HashMap<String, Object>(); 
		String objId = request.getParameter("objId");
		
		//项目信息
		Project project = projectServiceImpl.get(objId);
		model.put("talkProject", project);
		
		model.put("orgInfo", AuthenticationHelper.getCurrentUser(true).getOrgInfo());
		
		ModelAndView mv  = null;
		//采购人
		if("buyer".equals(request.getParameter("inType")))
		{
			mv = new ModelAndView("intoTalkHallBuyerXEJYView", model);
		}
		else //供应商
		{
			mv =  new ModelAndView("intoTalkHallSupplierView", model);	
		}
		
		return mv;
	}
	
	
	/** 
	 * Description :  获得供应商的报名列表信息
	 * Create Date: 2010-11-8下午03:55:58 by likg  Modified Date: 2010-11-8下午03:55:58 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getSignupRecordList")
	public ModelAndView getSignupRecordList(String projectObjId) throws Exception 
	{
		Map<String, Object> model = new HashMap<String, Object>(); 
		
		List<SignUprecord> signUprecordList= signUprecordService.getSignupRecordList(projectObjId);
		model.put("signUprecordList", signUprecordList);
		
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  获得需求条目
	 * Create Date: 2010-11-1上午11:55:47 by likg  Modified Date: 2010-11-1上午11:55:47 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getRequireItemsByProjId")
	public ModelAndView getRequireItemsByProjId(HttpServletRequest request) throws Exception 
	{
		Map<String, Object> model = new HashMap<String, Object>();
		
		String objId = request.getParameter("projectObjId");
		
		//获取需求条目信息
		List<RequireItem> requireItemList = requireItemServiceImpl.getRequireItemsByProjId(objId);
		model.put("requireItemList", requireItemList);
		
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  获取供应商的报价历史
	 * Create Date: 2010-11-1上午11:55:47 by likg  Modified Date: 2010-11-1上午11:55:47 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getSupplierBiddingHistory")
	public ModelAndView getSupplierBiddingHistory(HttpServletRequest request) throws Exception 
	{
		Map<String, Object> model = new HashMap<String, Object>();
		
		String projId = request.getParameter("projectObjId");
		String supplierId = request.getParameter("supplierObjId");
		String biddingType = request.getParameter("biddingType");
		
		//获取报价记录信息
		List<BiddingRecord> biddingRecordList = biddingRecordServiceImpl.getSupplierBiddingHistory(projId, supplierId, null);
		if("theLast".equals(biddingType)) { //获取最后一次的报价记录
			if(biddingRecordList!=null && biddingRecordList.size()>0) {
				BiddingRecord biddingRecord = biddingRecordList.get(0);
				model.put("biddingRecordItemSet", biddingRecord.getBiddingRecordItemSet());
			}
		}else{
			model.put("biddingRecordList", biddingRecordList);
		}
		
		//项目是否结束议价状态
		Project proj = projectServiceImpl.get(projId);
		model.put("projStatus", proj.getProjProcessStatus());
		
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  取得每个供应商最低报价 (对象)
	 * Create Date: 2010-11-1上午11:55:47 by likg  Modified Date: 2010-11-1上午11:55:47 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getMinBiddingRecordByProjectId")
	public ModelAndView getMinBiddingRecordByProjectId(HttpServletRequest request) throws Exception 
	{
		Map<String, Object> model = new HashMap<String, Object>();
		
		String projId = request.getParameter("projectObjId");
		
		//获取需求条目信息
		List<BiddingRecord> biddingRecordList = biddingRecordServiceImpl.getMinBiddingRecordByProjectId(projId, null);
		model.put("biddingRecordList", biddingRecordList);
		
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/**
	 * Description :  提交议价项目
	 * Create Date: 2010-11-1上午11:55:47 by likg  Modified Date: 2010-11-1上午11:55:47 by likg
	 * @param request
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "method=toSubmitProject")
	public ModelAndView toSubmitProject(HttpServletRequest request, SessionStatus status) throws Exception {
		String projId = request.getParameter("projectId");
		Project project = projectServiceImpl.get(projId);
		
		project.setAuditStatus(CommonEnum.USER_STATUS_FORMAL);//审核状态
		project.setUseStatus(CommonEnum.USER_STATUS_FORMAL);//提交状态
		project.setProjProcessStatus(ProjProcessStatusEnum.SUPPLIERS_BID); //供应商投标
		project.setProjProcessStatusCN(ProjProcessStatusEnum.getCN(ProjProcessStatusEnum.SUPPLIERS_BID));
		projectServiceImpl.save(project);
		
		//项目提交时，调用发邀请函和站内信接口
		List<SignUprecord> signUprecords = signUprecordService.getSignupRecordList(project.getObjId());
		StringBuilder supplierIds = new StringBuilder();
		for(SignUprecord su : signUprecords){
			supplierIds.append(su.getSupplier().getObjId()).append(",");
		}
		if(supplierIds.indexOf(",") != -1){
			supplierIds = supplierIds.deleteCharAt(supplierIds.length()-1);
		}
		Inviterollrequ inviterollrequ = new Inviterollrequ();
		inviterollrequ.setProjCode(project.getProjCode());
		inviterollrequ.setProjName(project.getProjName());
		inviterollrequ.setProject(project);
		String content = invitationService.getInviterollrequContent(project);
		
		invitationService.saveInvitation(inviterollrequ, content, supplierIds.toString());
		
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/** 
	 * Description :  查看供应商的全部报价记录(议价项目)
	 * Create Date: 2011-7-22下午05:05:52 by likg  Modified Date: 2011-7-22下午05:05:52 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toTalkBiddingRecordAllView")
	public ModelAndView toTalkBiddingRecordAllView(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		String projId = request.getParameter("projId"); //项目id
		String supplierId = request.getParameter("supplierId"); //供应商id
		
		//获取报价记录信息
		List<BiddingRecord> biddingRecordList = biddingRecordServiceImpl.getSupplierBiddingHistory(projId, supplierId, null);
		model.put("biddingRecordList", biddingRecordList);
		
		return new ModelAndView("talkBiddingRecordAllView", model);
	}
	
}
