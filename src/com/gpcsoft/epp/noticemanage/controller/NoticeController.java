package com.gpcsoft.epp.noticemanage.controller;

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
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.organization.service.OrgInfoService;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryObjectBase;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.buyresult.domain.BuyWinner;
import com.gpcsoft.epp.buyresult.domain.ResultTypeEnum;
import com.gpcsoft.epp.buyresult.service.BuyWinnerService;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.common.domain.EbuyMethodEnum;
import com.gpcsoft.epp.common.domain.SeparationEnum;
import com.gpcsoft.epp.noticemanage.domain.Notice;
import com.gpcsoft.epp.noticemanage.domain.NoticeAffirmRecord;
import com.gpcsoft.epp.noticemanage.domain.NoticeStatusEnum;
import com.gpcsoft.epp.noticemanage.domain.NoticeTypeEnum;
import com.gpcsoft.epp.noticemanage.manager.NoticeManager;
import com.gpcsoft.epp.noticemanage.service.NoticeService;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.domain.SubProjectMTaskPlanSub;
import com.gpcsoft.epp.project.service.ProjectService;
import com.gpcsoft.epp.projreview.domain.OpenBidRecord;
import com.gpcsoft.epp.projreview.service.OpenBidRecordService;
import com.gpcsoft.epp.projrule.domain.ProjProcessRule;
import com.gpcsoft.epp.projrule.service.ProjProcessRuleService;
import com.gpcsoft.epp.taskplan.domain.TaskPlanMSub;
import com.gpcsoft.epp.taskplan.service.TaskPlanMSubService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.common.assist.service.FreeMarkerService;

/**
  * @gpcsoft.view value="noticeFormView"
  *  url="view/es/planform/noticemanage/noticeForm.jsp"
  * @gpcsoft.view value="noticeTreeFormView"
  *  url="view/es/planform/noticemanage/noticeTreeForm.jsp"
  * @gpcsoft.view value="noticeListView"
  *  url="view/es/planform/noticemanage/noticeList.jsp"
  *  @gpcsoft.view value="blank"
  *  url="view/es/common/blank.jsp"
  * @gpcsoft.view value="noticeDetailView"
  *  url="view/es/planform/noticemanage/noticeDetail.jsp"
  *  @gpcsoft.view value="supplierNoticeDetail"
  *  url="view/es/planform/noticemanage/supplierNoticeDetail.jsp"
  *  @gpcsoft.view value="draftNoticeView"
  *  url="view/es/planform/noticemanage/draft_notice.jsp"
  *  @gpcsoft.view value="ownListView"
  *  url="view/es/planform/noticemanage/own_notice_list.jsp"
  *  @gpcsoft.view value="receiptView"
  *  url="view/es/planform/noticemanage/receipt_notice.jsp"
  *  @gpcsoft.view value="toViewNotice"
  *  url="view/es/planform/noticemanage/view_notice.jsp"
  *  
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={Notice.class})
@RequestMapping("/NoticeController.do")//页面请求路径,可修改
@SuppressWarnings(value="unchecked")
public class NoticeController extends AnnotationMultiController<Notice> {

	@Autowired(required=true) @Qualifier("noticeServiceImpl")
	private NoticeService noticeService;
	
	@Autowired(required=true) @Qualifier("freeMarkerServiceImpl")
	private FreeMarkerService freeMarkerService;
	
	@Autowired(required=true) @Qualifier("buyWinnerServiceImpl")
	private BuyWinnerService buyWinnerService;

	@Autowired(required=true) @Qualifier("projectServiceImpl")
	private ProjectService projectService;	
	
	@Autowired(required=true) @Qualifier("projProcessRuleServiceImpl")
	private ProjProcessRuleService projProcessRuleService;
	
	@Autowired(required=true) @Qualifier("taskPlanMSubServiceImpl")
	private TaskPlanMSubService taskPlanMSubService;
	
	@Autowired(required=true) @Qualifier("openBidRecordServiceImpl")
	private OpenBidRecordService openBidRecordService;
	
	@Autowired(required=true) @Qualifier("orgInfoServiceImpl")
	private OrgInfoService orgInfoService;
	/** 
	 * Description :  供应商确认通知书
	 * Create Date: 2010-8-30上午10:22:33 by yangx  Modified Date: 2010-8-30上午10:22:33 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=sureNotice")
	public ModelAndView sureNotice(HttpServletRequest request)throws Exception {
		logger.debug("\nes NoticeController||sureNotice\n");
		User user = AuthenticationHelper.getCurrentUser();
		String noticeId = request.getParameter("noticeId");//通知书Id
		NoticeAffirmRecord noticeAffirmRecord = new NoticeAffirmRecord();
		noticeAffirmRecord.setNoticeAAffirmTime(new Date());
		noticeAffirmRecord.setNoticeAAffirmName(user.getEmp().getName());
		noticeAffirmRecord.setUseStatus(CommonEnum.USER_STATUS_FORMAL);
		Map model = new HashMap();
		noticeService.saveNoticeAffirm(noticeId,noticeAffirmRecord);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	


	/** 
	 * Description : 起草通知书 
	 * Create Date: 2010-8-30上午11:29:38 by yangx  Modified Date: 2010-8-30上午11:29:38 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=toDraftNotice")
	public ModelAndView toDraftNotice(HttpServletRequest request)throws Exception {
		logger.debug("\nes NoticeController||toDraftNotice\n");
		String winnerId = request.getParameter("winnerId");
		String subProjectId = request.getParameter("subProjectId");
		String projectId="";
		String noticeView = "draftNoticeView";
		BuyWinner winner = (BuyWinner)buyWinnerService.getBuyWinnerByObjId(winnerId);
		Project subProject =(Project)projectService.getProjectByObjId(subProjectId);
		Project project = null;
		User user = AuthenticationHelper.getCurrentUser();
		if(subProject.getParentId()!=null){ //说明未分包
			 project = projectService.getProjectBySubProjectId(subProjectId);
			 projectId= project.getObjId();
		}else{
			project = subProject;
			projectId = subProject.getObjId();
		}
		Notice notice = noticeService.getNoticeBySelllerIdAndSubProjectId(winner.getSelllerId(),subProjectId);//供应商id和标段id确定一个通知书
		String ebuyMethodCN="";
		if(EbuyMethodEnum.OPEN_BIDDING.equals(project.getEbuyMethod())){
			ebuyMethodCN = "公开招标";
		}else if(EbuyMethodEnum.INVITE_BIDDING.equals(project.getEbuyMethod())){
			ebuyMethodCN = "邀请招标";
		}else if(EbuyMethodEnum.NEGOTIATE.equals(project.getEbuyMethod())){
			ebuyMethodCN = "竞争性谈判";
		}else if(EbuyMethodEnum.INQUIRY.equals(project.getEbuyMethod())){
			ebuyMethodCN = "询价";
		}else if(EbuyMethodEnum.SINGLE_SOURCE.equals(project.getEbuyMethod())){
			ebuyMethodCN = "单一来源";
		}
		String buyerContact = this.getBuyerContact(subProject.getBuyersId());
		String buyerTel = this.getBuyerTel(subProject.getBuyersId());
		String buyerAddress = this.getBuyerAddress(subProject.getBuyersId());
		String agentcontact = user.getEmp().getCompany().getContact();
		String agentphone = user.getEmp().getCompany().getTel();
		List<OpenBidRecord> openBidRecordList = openBidRecordService.getopenBidRecordListByPackId(subProjectId);
		OpenBidRecord openBidRecordWinner = new OpenBidRecord();
			if(ResultTypeEnum.DEAL_YES.equals(winner.getResultType())){ //成交
				for (Iterator iterator1 = openBidRecordList.iterator(); iterator1.hasNext();) {
					 OpenBidRecord openBidRecord = (OpenBidRecord) iterator1.next();
					 if(openBidRecord.getSupplier().getObjId().equals(winner.getSelllerId())){  //中标供应商
						 openBidRecordWinner = openBidRecord;
					 }
						 
				}
			}
		if (notice==null) {
			notice = new Notice();
			OrgInfo seller = new OrgInfo();
			seller.setObjId(winner.getSelllerId());
			notice.setReceiveOrg(seller);
			User receiveUser = new User();
			receiveUser.setObjId(winner.getSelllerId());
			notice.setReceiveUser(receiveUser);
			notice.setProject(subProject);
			notice.setNoticeType(winner.getResultType());
			notice.setCreateTime(new Date());
			notice.setNoticeTitle(NoticeTypeEnum.getTitle(winner.getResultType()));
			notice.setReceiveStatus(NoticeStatusEnum.RECEVICESTATUS_NO);
			notice.setUseStatus(CommonEnum.USER_STATUS_TEMP);
			notice.setSendStatus(NoticeStatusEnum.SENDSTATUS_NO);
			Map<String ,Object> map = new HashMap();
			map.put("title", notice.getNoticeTitle());
			map.put("seller", winner.getSellerName());
			map.put("agentName",((OrgInfo)AuthenticationHelper.getCurrentUser().getOrgInfo()).getOrgName());
			map.put("subProject", subProject);
			
			ProjProcessRule projProcessRule = projProcessRuleService.getProjProcessRuleByProjectIdAndCode(projectId, "SUBPROJECT");//项目规则
			map.put("project", (Project)projectService.getProjectByObjId(projectId));
			map.put("result", NoticeTypeEnum.getResult(winner.getResultType()));
			StringBuffer buyerContent = new StringBuffer();// 读取当前包件采购人信息 add 2010-07-19 13:00 by wanghz
			if (NoticeTypeEnum.DEAL_YES.equals(winner.getResultType())) {
				if(("true").equals(projProcessRule.getResValue())){//判断当前项目是否需要分包
					List<SubProjectMTaskPlanSub> projectMTaskPlanSubList = noticeService.getSubProjectMTaskPlanSubBySubProjectId(subProjectId);// 根据包件ID获取申报书条目
					map.put("projectMTaskPlanSubList",projectMTaskPlanSubList);
				}else{
					List<TaskPlanMSub> taskPlanMSubList = taskPlanMSubService.getTaskPlanSubByTaskPlanId(subProjectId);
					map.put("subproject","false");
					map.put("projectMTaskPlanSubList", taskPlanMSubList);
				}
				map.put("isDeal","YES");
			}else{
				map.put("isDeal","NO");
			}
			map.put("agentcontact", agentcontact);
			map.put("agentphone", agentphone);
			map.put("buyerContact", buyerContact);
			map.put("buyerTel", buyerTel);
			map.put("buyerAddress", buyerAddress);
			map.put("openBidRecordWinner", openBidRecordWinner);
			map.put("ebuyMethodCN", ebuyMethodCN);
			map.put("buyerPerson", buyerContent.toString());
			notice.setContents(freeMarkerService.getFreeMarkerContent("buyResultNotice.ftl", map));	
//			noticeService.saveNoticeAndAttachment(notice);
		} else{
			noticeView="toViewNotice";
		}
		Map model = new HashMap();
		model.put("winnerId", winnerId);
		model.put("notice", notice);
		return new ModelAndView(noticeView,model);
	}
	
	
	/** 
	 * FuncName : toViewNotice
	 * Description :  跳转到查看通知书
	 * Create Date: 2011-11-15下午01:38:10 by yangx  
	 * Modified Date: 2011-11-15下午01:38:10 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=toNoticeDetail")
	public ModelAndView toNoticeDetail(HttpServletRequest request)throws Exception {
		String winnerId = request.getParameter("winnerId");
		String subProjectId = request.getParameter("subProjectId");
		BuyWinner winner = (BuyWinner)buyWinnerService.getBuyWinnerByObjId(winnerId);
		Notice notice = noticeService.getNoticeBySelllerIdAndSubProjectId(winner.getSelllerId(),subProjectId);//供应商id和标段id确定一个通知书
		Map model = new HashMap();
		model.put("notice", notice);
		return new ModelAndView("toViewNotice",model);
	}
	
	/** 
	 * Description :  提交通知书附件内容 覆盖的形式
	 * Create Date: 2010-6-28下午05:41:00 by liuke  Modified Date: 2010-6-28下午05:41:00 by liuke
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=submitNoticeFile")
	public ModelAndView submitNoticeFile(Notice notice, HttpServletRequest request)throws Exception {
		logger.debug("\nes NoticeController||submitNoticeFile\n");
		String winnerId = request.getParameter("winnerId");
		String subProjectId = request.getParameter("subproject");
		
		BuyWinner winner = (BuyWinner)buyWinnerService.getBuyWinnerByObjId(winnerId);
		User receiveUser = new User();
		OrgInfo seller = new OrgInfo();
		seller.setObjId(winner.getSelllerId());
		receiveUser.setObjId(winner.getSelllerId());
		notice.setReceiveUser(receiveUser);
		notice.setReceiveOrg(seller);
		notice.setReceiveUser(receiveUser);
		notice.setNoticeType(winner.getResultType());
		notice.setCreateTime(new Date());
		notice.setNoticeTitle(NoticeTypeEnum.getTitle(winner.getResultType()));
		notice.setReceiveStatus(NoticeStatusEnum.RECEVICESTATUS_NO);
		notice.setUseStatus(CommonEnum.USER_STATUS_FORMAL);
		notice.setSendStatus(NoticeStatusEnum.SENDSTATUS_YES);
		notice.setProject((Project)projectService.getProjectByObjId(subProjectId));
		noticeService.saveNoticeAndAttachment(notice);
//		noticeService.saveSubmitNoticeFile(notice);//供应商id和标段id确定一个通知书
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/** 
	 * Description :  根据项目Id、供应商Id查询待确认的通知书
	 * Create Date: 2010-8-30上午11:57:47 by yangx  Modified Date: 2010-8-30上午11:57:47 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=toOwnList")
	public ModelAndView toOwnList(HttpServletRequest request)throws Exception {
		logger.debug("\nes NoticeController||toOwnList\n");
		String projectId = request.getParameter("projectId");
		User user = AuthenticationHelper.getCurrentUser();
		OrgInfo orgInfo = (OrgInfo)user.getOrgInfo();
		List<Notice> list = noticeService.getListBySupplier(projectId,orgInfo.getObjId());
		Map model = new HashMap();
		model.put("list", list);
		if(list.size()==0){
			model.put("nodata",true);
		}
		return new ModelAndView("ownListView", model);
	}
	
	/** 
	 * Description :  跳转到接收通知书
	 * Create Date: 2010-7-12下午01:55:16 by wangcl  Modified Date: 2010-7-12下午01:55:16 by wangcl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=toReceipt")
	public ModelAndView toReceipt(HttpServletRequest request)throws Exception {
		logger.debug("\nes NoticeController||toReceipt\n");
		String objId = request.getParameter("objId");
		Notice notice = noticeService.getNotice(objId);
		Map model = new HashMap();
		model.put("notice", notice);
		return new ModelAndView("receiptView", model);
	}
	
	/** 
	 * Description :  采购人获取通知书列表
	 * Create Date: 2010-8-1上午11:56:08 by yangx  Modified Date: 2010-8-1上午11:56:08 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=toBuyerList")
	public ModelAndView toBuyerList(HttpServletRequest request)throws Exception {
		logger.debug("\nes NoticeController||toBuyerList\n");
		String projectId = request.getParameter("projectId");
		User user = AuthenticationHelper.getCurrentUser();
		List<Notice> list = noticeService.getListByBuyer(projectId,user);
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Notice notice = (Notice) iterator.next();
			notice.getReceiveOrg().setOrgName(notice.getReceiveOrg().getOrgName());
		}
		Map model = new HashMap();
		model.put("noticeList", list);
		if(list.size()==0){
			model.put("noData", true);
		}
		return new ModelAndView(Constants.JSON_VIEW, model);
	}

	/** 
	 * Description :  根据通知书Id跳转到查看通知书页面
	 * Create Date: 2010-8-1下午01:45:58 by yangx  Modified Date: 2010-8-1下午01:45:58 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=toViewNotice")
	public ModelAndView toViewNotice(HttpServletRequest request)throws Exception {
		logger.debug("\nes NoticeController||toViewNotice\n");
		String objId = request.getParameter("objId");
		Notice notice = noticeService.getNotice(objId);
		Map model = new HashMap();
		model.put("notice", notice);
		return new ModelAndView("toViewNotice", model);
	}
	
	/** 
	 * Description :  根据通知书Id跳转到查看通知书页面
	 * Create Date: 2010-8-1下午01:45:58 by yangx  Modified Date: 2010-8-1下午01:45:58 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=getNoticeByQueryObject")
	public ModelAndView getNoticeByQueryObject(HttpServletRequest request)throws Exception {
		logger.debug("\nes NoticeController||getNoticeByQueryObject\n");
		Page<Notice> page = prePage(request);
		User currentUser = AuthenticationHelper.getCurrentUser(true);
		OrgInfo orginfo = (OrgInfo) currentUser.getOrgInfo();
		QueryObject<Notice>  queryObject = new QueryObjectBase<Notice>();
		queryObject.getQueryParam().and(new QueryParam("sendStatus",QueryParam.OPERATOR_EQ,NoticeStatusEnum.SENDSTATUS_YES));
		queryObject.getQueryParam().and(new QueryParam("receiveStatus",QueryParam.OPERATOR_EQ,NoticeStatusEnum.RECEVICESTATUS_NO));
		queryObject.getQueryParam().and(new QueryParam("orgInfoId",QueryParam.OPERATOR_EQ,orginfo.getObjId()));
		Page pageDate = noticeService.getNoticePageByQueryObject(queryObject, page);
		Map model = new HashMap();
		super.endPage(model, pageDate, request);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  获取预算单位联系人
	 * Modified Date: 2011-7-14下午04:05:13 by chench
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
	 * Description :  获取预算单位地址
	 * Modified Date: 2011-7-14下午04:05:13 by chench
	 * @param   
	 * @return  
	 * @Exception   
	 */
	private String getBuyerAddress(String buyerIds){
		//分割ids
		String buyerAddress = "";
		if (buyerIds!=null&&!"".equals(buyerIds)) {//判断预算单位id是否为空
			String[] ids = buyerIds.split(SeparationEnum.COMMA);
			for (String id:ids) {
				OrgInfo orgInfo = orgInfoService.get(id);
				buyerAddress +=orgInfo.getCompany().getAddress()+"、";
			}
		}
		if (buyerAddress!="") {//判断电话是否为空
			buyerAddress = buyerAddress.substring(0,buyerAddress.lastIndexOf("、"));
		}
		return buyerAddress;
	}
	
	
	/**
	 * FuncName: finishBuyResultNotice
	 * Description :  完成发送结果通知书
	 * @param 
	 * @param request
	 * @return
	 * @throws Exception ModelAndView
	 * @author: zhangsh
	 * @Create Date:2011-11-16  上午11:35:35
	 * @Modifier: zhangsh
	 * @Modified Date:2011-11-16  上午11:35:35
	 */
	@RequestMapping(params = "method=finishBuyResultNotice")
	public ModelAndView finishBuyResultNotice(HttpServletRequest request)throws Exception {
		logger.debug("\nes BuyWinnerController||finishBuyResultNotice\n");
		String projectId = request.getParameter("projectId");
		noticeService.finishBuyResultNotice(projectId);
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/**
	 * FuncName: toNoticePrintPage
	 * Description : 打印结果通知书
	 * @param 
	 * @param request
	 * @return
	 * @throws Exception ModelAndView
	 * @author: zhangsh
	 * @Create Date:2011-11-17  上午11:50:07
	 * @Modifier: zhangsh
	 * @Modified Date:2011-11-17  上午11:50:07
	 */
	@RequestMapping(params = "method=toNoticePrintPage")
	public ModelAndView toNoticePrintPage(HttpServletRequest request)throws Exception {
		logger.debug("\nes=TaskPlanController||toNoticePrintPage\n");
		String objId = request.getParameter("noticeId");
		Notice notice = noticeService.getNotice(objId);
		request.getSession().setAttribute("content", notice.getContents());
		return new ModelAndView(Constants.JSON_VIEW);
	}
}
