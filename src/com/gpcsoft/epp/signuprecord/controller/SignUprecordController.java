package com.gpcsoft.epp.signuprecord.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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

import com.gpcsoft.bizplatform.organization.controller.OrgInfoController;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryObjectBase;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.dao.hibernate.query.QueryWebUtils;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.utils.DateUtil;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.bid.domain.BailRecord;
import com.gpcsoft.epp.bid.domain.BailStatusEnum;
import com.gpcsoft.epp.bid.domain.Bid;
import com.gpcsoft.epp.bid.domain.BidPackage;
import com.gpcsoft.epp.bid.service.BailRecordService;
import com.gpcsoft.epp.bid.service.BidPackageService;
import com.gpcsoft.epp.bid.service.BidService;
import com.gpcsoft.epp.bulletin.domain.Bulletin;
import com.gpcsoft.epp.bulletin.domain.BulletinTypeEnum;
import com.gpcsoft.epp.bulletin.service.BulletinService;
import com.gpcsoft.epp.common.domain.AuditStatusEnum;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.common.domain.EbuyMethodEnum;
import com.gpcsoft.epp.common.service.UserApiService;
import com.gpcsoft.epp.inviterollrequ.domain.InrqAuditStatusEnum;
import com.gpcsoft.epp.inviterollrequ.domain.InrqDetail;
import com.gpcsoft.epp.inviterollrequ.domain.Inviterollrequ;
import com.gpcsoft.epp.inviterollrequ.service.InrqDetailService;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.domain.ProjectRule;
import com.gpcsoft.epp.project.service.ProjectService;
import com.gpcsoft.epp.projreview.domain.OpenBidSign;
import com.gpcsoft.epp.projreview.domain.OpenbidGeneralview;
import com.gpcsoft.epp.projreview.service.OpenBidRecordService;
import com.gpcsoft.epp.projreview.service.OpenBidSignService;
import com.gpcsoft.epp.projreview.service.OpenbidGeneralviewService;
import com.gpcsoft.epp.signuprecord.domain.SignUpCondFactor;
import com.gpcsoft.epp.signuprecord.domain.SignUpRespone;
import com.gpcsoft.epp.signuprecord.domain.SignUprecord;
import com.gpcsoft.epp.signuprecord.service.SignUpCondFactorService;
import com.gpcsoft.epp.signuprecord.service.SignUpResponeService;
import com.gpcsoft.epp.signuprecord.service.SignUprecordService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.domain.Company;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.auth.service.AttachmentService;
import com.gpcsoft.srplatform.common.utils.AttachmentUtil;

/**
  * @gpcsoft.view value="supplierForAudit"
  *  url="view/es/planform/signuprecord/supplierForAudit.jsp"
  * @gpcsoft.view value="blank"
  *  url="view/es/common/blank.jsp"
  * @gpcsoft.view value="supplierDetailForm"
  *  url="view/es/planform/signuprecord/supplierDetailForm.jsp"
  * @gpcsoft.view value="signUprecordList"
  *  url="view/es/planform/signuprecord/signUprecordInfoList.jsp"
  * @gpcsoft.view value="toViewSignupRecordList"
  *  url="view/es/planform/signuprecord/signUprecordViewList.jsp"
  * @gpcsoft.view value="signUprecordList2"
  *  url="view/es/planform/signuprecord/signUprecordInfoList2.jsp"
  * @gpcsoft.view value="toSignupRecordViewList"
  *  url="view/es/planform/signuprecord/signUprecordInfoViewList.jsp"
  * @gpcsoft.view value="signUprecord"
  *  url="view/es/planform/signuprecord/signUprecord.jsp"
  * @gpcsoft.view value="toViewSignupInfo"
  *  url="view/es/planform/signuprecord/signUprecordInfoDetail.jsp"
  * @gpcsoft.view value="getMoreForSignUprecordList"
  *  url="view/es/planform/signuprecord/moreForSignUprecordList.jsp"
  * @gpcsoft.view value="signUprecordFormView"
  *  url="view/es/planform/signuprecord/signUprecordForm.jsp"
  * @gpcsoft.view value="signUprecordTreeFormView"
  *  url="view/es/planform/signuprecord/signUprecordTreeForm.jsp"
  * @gpcsoft.view value="signUprecordListView"
  *  url="view/es/planform/signuprecord/signUprecordList.jsp"
  * @gpcsoft.view value="signUprecordDetailView"
  *  url="view/es/planform/signuprecord/signUprecordDetail.jsp"
  * @gpcsoft.view value="supplierDetail"
  *  url="view/es/planform/signuprecord/supplierDetail.jsp"
  * @gpcsoft.view value="agentSignUprecordForm"
  *  url="view/es/planform/signuprecord/agentSignUprecordForm.jsp"
  * @gpcsoft.view value="signUprecordConfirmPage"
  *  url="view/es/planform/signuprecord/signUprecordConfirmPage.jsp"
  * @gpcsoft.view value="keyIntoSupplierView"
  *  url="view/es/singlesource/singlesourcedoc/keyIntoSupplier.jsp"
  * @gpcsoft.view value="keyIntoSupplierPageView"
  *  url="view/es/singlesource/singlesourcedoc/keyIntoSupplierPage.jsp"
  * @gpcsoft.view value="keyIntoSupplierListView"
  *  url="view/es/singlesource/singlesourcedoc/keyIntoSupplierList.jsp"
  * @gpcsoft.view value="viewSignupRecordList"
  *  url="view/es/planform/signuprecord/viewSignUprecordList.jsp"
  *  @gpcsoft.view value="toPageForAgency"
  *  url="view/es/planform/signuprecord/signUprecordFormForAgency.jsp"
  *  @gpcsoft.view value="updatePageForAgency"
  *  url="view/es/planform/signuprecord/updateSignUprecordFormForAgency.jsp"
  *   @gpcsoft.view value="viewSignupPageTab"
  *  url="view/es/planform/signuprecord/viewSignPageTab.jsp"
  *   @gpcsoft.view value="supplierNoSignup"
  *  url="view/es/planform/signuprecord/supplierNoSignup.jsp"
  *  @gpcsoft.view value="viewBidPackage"
  *  url="view/es/planform/signuprecord/viewBidPackage.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={SignUprecord.class})
@RequestMapping("/SignUprecordController.do")//页面请求路径,可修改
@SuppressWarnings(value="unchecked")
public class SignUprecordController extends AnnotationMultiController<SignUprecord> {

	@Autowired(required=true) @Qualifier("signUprecordServiceImpl")
	private SignUprecordService signUprecordService;
	
	@Autowired(required=true) @Qualifier("bidPackageServiceImpl")
	private BidPackageService bidPackageService;
	
	@Autowired(required=true) @Qualifier("bulletinServiceImpl")
	private BulletinService bulletinService;
	
	@Autowired(required=true) @Qualifier("attachmentServiceImpl")
	private AttachmentService attachmentService;
	
	@Autowired(required=true) @Qualifier("signUpResponeServiceImpl")
	private SignUpResponeService signUpResponeService;
	
	
	@Autowired(required=true) @Qualifier("signUpCondFactorServiceImpl")
	private SignUpCondFactorService signUpCondFactorService;
	
	@Autowired(required=true) @Qualifier("userApiServiceImpl")
	private UserApiService userApiService;
	
	@Autowired(required=true) @Qualifier("openBidRecordServiceImpl")
	private OpenBidRecordService openBidRecordService;
	
	@Autowired(required=true) @Qualifier("inrqDetailServiceImpl")
	private InrqDetailService inrqDetailService;
	
	@Autowired(required=true) @Qualifier("projectServiceImpl")
	private ProjectService projectService;	
	
	@Autowired(required=true) @Qualifier("openbidGeneralviewServiceImpl")
	private OpenbidGeneralviewService openbidGeneralviewService;
	
	@Autowired(required=true) @Qualifier("bailRecordServiceImpl")
	private BailRecordService bailRecordService;
	
	@Autowired(required=true) @Qualifier("openBidSignServiceImpl")
	private OpenBidSignService openBidSignService;
	

	@Autowired(required=true) @Qualifier("bidServiceImpl")
	private BidService bidService;
	
	/**标书下载控制
	 * @param request
	 * @param signUprecord
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params="method=saveCtrlFilePay")
	public ModelAndView saveCtrlFilePay(HttpServletRequest request,SessionStatus status) throws Exception {
		logger.debug("\nes SignUprecordController||ctrlFilePay\n");
		
		String objId = request.getParameter("objId");//报名表ID
		String resultValue = request.getParameter("resultValue");//是否选中
		
		SignUprecord signUprecord = signUprecordService.get(objId);
		if(StringUtils.hasLength(resultValue) && "true".equals(resultValue)) {
			signUprecord.setREDUNDANCY1(AuditStatusEnum.AUDIT_PASS);//是否必须支付标书费[01:是，00:否]
		} else {
			signUprecord.setREDUNDANCY1(AuditStatusEnum.WAIT_AUDIT);//是否必须支付标书费[01:是，00:否]
		}
		signUprecordService.save(signUprecord);
		
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/**
	 * FuncName: saveSignUprecord
	 * Description :  新增供应商报名
	 * @param   request,signUprecord:供应商报名记录, status
	 * @return  ModelAndView
	 * @author yangx
	 * @Create Date: 2010-6-22下午04:00:09 
	 * @Modifier  liuke
	 * @Modified Date: 2010-6-22下午04:00:09 
	 */
	@RequestMapping(params="method=saveSignUprecord")
	public ModelAndView saveSignUprecord(HttpServletRequest request,SignUprecord signUprecord, SessionStatus status)throws Exception {
		logger.debug("\nes SignUprecordController||saveSignUprecord\n");
		User user = AuthenticationHelper.getCurrentUser();
		if(null!=((OrgInfo)user.getOrgInfo()).getSupplierId()) {//如果当前用户为供应商
			signUprecord.setSupplier((OrgInfo)user.getOrgInfo());
		}
		String attachRela = request.getParameter("attachRela");
		signUprecord.setAttachRelaId(attachRela);
		String num = request.getParameter("num");
		String subPrjIds = request.getParameter("subPrjIds");
		if(null!=num &&!"".equals(num)){
			Integer nums = Integer.valueOf(num);
			List<SignUpRespone> signUpResponeList = new ArrayList<SignUpRespone>();
			for(int i=0;i<nums;i++) {
				SignUpRespone signUpRespone = new SignUpRespone();
				signUpRespone.setObjId(request.getParameter("signUpResponeId"+i));
				signUpRespone.setResponseValue(request.getParameter("responseValue"+i));
				SignUpCondFactor signupcondFactor = signUpCondFactorService.getSignUpCondFactorByObjId(request.getParameter("signUpCondFactorId"+i));
				signUpRespone.setSignUpCondFactor(signupcondFactor);
				Boolean	isUploadFile = new Boolean(request.getParameter("isUploadFile"+i));	//附件上传控制
				if(isUploadFile){
					Object o=AttachmentUtil.uploadFile(request,("attachFile"+i));
					if(o instanceof GpcBaseObject){
						Attachment attachment = (Attachment)o;
						attachmentService.saveAttachment((Attachment)o);
						signUpRespone.setSignUpResponeFile(attachment);
					}
				}else{
					String attId = request.getParameter("attachRelaId"+i);
					if(null != attId && !"".equals(attId)){
						Attachment attachment = new Attachment();
						attachment.setObjId(attId);
						signUpRespone.setSignUpResponeFile(attachment);
					}else{
						signUpRespone.setSignUpResponeFile(null);
					}
				}
				signUpResponeList.add(signUpRespone);
			}
			if(subPrjIds!=null&&!"".equals(subPrjIds)){
				signUprecordService.saveSignUprecordAndSignUpRespone(signUprecord, signUpResponeList, subPrjIds);
			}else{
				signUprecordService.saveSignUprecordAndSignUpRespone(signUprecord, signUpResponeList,null);
			}
		} else {
			if(subPrjIds!=null&&!"".equals(subPrjIds)){
				signUprecordService.saveSignUprecordAndSignUpRespone(signUprecord, new ArrayList<SignUpRespone>(), subPrjIds);
			}else{
				signUprecordService.saveSignUprecordAndSignUpRespone(signUprecord, new ArrayList<SignUpRespone>(),null);
			}
		}
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/** 
	 * FuncName:updateSignUprecord
	 * Description :修改供应商报名
	 * @param   request,signUprecord:供应商报名记录,status
	 * @return  ModelAndView
	 * @author Administrator
	 * @Create Date: 2010-6-22下午04:00:09 by liuke   Modified Date: 2010-6-22下午04:00:09 by liuke   
	 */
	@RequestMapping(params="method=updateSignUprecord")
	public ModelAndView updateSignUprecord(HttpServletRequest request,SignUprecord signUprecord, SessionStatus status)throws Exception {
		logger.debug("\nes SignUprecordController||updateSignUprecord\n");
		User user = AuthenticationHelper.getCurrentUser();   
		if(null!=((OrgInfo)user.getOrgInfo()).getSupplierId()){//如果当前用户为供应商
			signUprecord.setSupplier((OrgInfo)user.getOrgInfo());	
		}
		String attachRela = request.getParameter("attachRela");
		signUprecord.setAttachRelaId(attachRela);
		String num = request.getParameter("num");
		if(null!=num &&!"".equals(num)){
		Integer nums = Integer.valueOf(num);
		List<SignUpRespone> signUpResponeList = new ArrayList<SignUpRespone>();
		for(int i=0;i<nums;i++){
			SignUpRespone signUpRespone = new SignUpRespone();
			signUpRespone.setObjId(request.getParameter("signUpResponeId"+i));
			signUpRespone.setResponseValue(request.getParameter("responseValue"+i));
			SignUpCondFactor signupcondFactor = signUpCondFactorService.getSignUpCondFactorByObjId(request.getParameter("signUpCondFactorId"+i));
			signUpRespone.setSignUpCondFactor(signupcondFactor);
			Boolean	isUploadFile = new Boolean(request.getParameter("isUploadFile"+i));	//附件上传控制
			if(isUploadFile){
				Object o=AttachmentUtil.uploadFile(request,("attachFile"+i));
				if(o instanceof GpcBaseObject){
					Attachment attachment = (Attachment)o;
					attachmentService.saveAttachment((Attachment)o);
					signUpRespone.setSignUpResponeFile(attachment);
				}
			}else{
				String attId = request.getParameter("attachRelaId"+i);
				if(null != attId && !"".equals(attId)){
					Attachment attachment = new Attachment();
					attachment.setObjId(attId);
					signUpRespone.setSignUpResponeFile(attachment);
				}else{
					signUpRespone.setSignUpResponeFile(null);
				}
			}
			signUpResponeList.add(signUpRespone);
		}
			signUprecordService.updateSignUprecordAndSignUpRespone(signUprecord, signUpResponeList);
		}else{
			signUprecordService.updateSignUprecordAndSignUpRespone(signUprecord, new ArrayList<SignUpRespone>());
		}
		Map model = new HashMap();
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  到报名信息显示页面
	 * Create Date: 2010-6-22下午04:31:34 by yangx  Modified Date: 2010-6-22下午04:31:34 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=toViewSignupPage")
	public ModelAndView toViewSignupPage(HttpServletRequest request, SessionStatus status)throws Exception {
		logger.debug("\nes SignUprecordController||toViewSignupPage\n");
		String prjId = request.getParameter("prjId");//项目ID
		String fromType = request.getParameter("fromType");
		String projectId = "";
		SignUprecord signUprecord=signUprecordService.getSignUprecordByprojectIdAndSupplierId(prjId,AuthenticationHelper.getCurrentUser().getOrgInfo().getObjId());
		if(signUprecord!=null){
			projectId  = signUprecord.getProject().getObjId();
		}
		String supplier ="";
		List<SignUpRespone> list = signUpResponeService.getSignUpResponeListByProjectIdAndSupplierId(projectId,supplier);
		String returnUrl = "";
		Map model = new HashMap();
		model.put("signUprecord", signUprecord);
		model.put("signUpResponeList", list);
		ProjectRule pr = projectService.getProjectRuleByProjectId(projectId);
		if(list.size()==0){
			model.put("signUpResponeNum", "none");
		}
		if(signUprecord!=null&&(AuditStatusEnum.AUDIT_NO_PASS).equals(signUprecord.getAuditStatus())){
			returnUrl = "supplierDetail";
		}else if(signUprecord==null && (pr != null && pr.getRuleAnonymous() != null && !"1".equals(pr.getRuleAnonymous()))){
			Project proj = projectService.getProjectByObjId(prjId);
			String parentId = "";
			if(proj.getParentId()!=null){ //说明是包组
				parentId = proj.getParentId();
			}else{  //说明是项目
				parentId = proj.getObjId();
			}
			List subProjList = projectService.getSubProjectByProjectId(parentId);
			if(subProjList.size()==0){ //如果子项目数目为0，说明未分包
				model.put("isDividePack", false);
			}else{
				model.put("isDividePack", true);
			}
			List<Project> projList = new ArrayList<Project>();
			projList.add(proj);
			model.put("projectId", prjId);
			model.put("subPrjList", projList);
			model.put("fromType", fromType);

			User user = AuthenticationHelper.getCurrentUser();
			Company company = userApiService.getOrgnizationByOrgInfoId(user.getOrgInfo().getObjId());
			model.put("linker", user.getEmp());
			model.put("orgInfo", user.getOrgInfo());
			model.put("company", company);
			
			returnUrl ="signUprecordFormView";    //跳转到报名页面
		}else{
			returnUrl = "supplierDetailForm";
		}
	    return new ModelAndView(returnUrl, model);
	}
	
	/** 
	 * Description :  跳转到报名页面
	 * Create Date: 2010-6-24下午07:48:40 by yangx  Modified Date: 2010-6-24下午07:48:40 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=toPage")
	public ModelAndView toPage(HttpServletRequest request)throws Exception {
		logger.debug("\nes SignUprecordController||toPage\n");
		Map model = new HashMap();
		String projectId = request.getParameter("projectId");//项目ID
		User user = AuthenticationHelper.getCurrentUser();
		Company company = userApiService.getOrgnizationByOrgInfoId(user.getOrgInfo().getObjId());
		String supplier ="";
		ProjectRule pr = projectService.getProjectRuleByProjectId(projectId);
		String returnView = "signUprecordFormView";
		if(pr.getIsDividePack().equals(true)){
			List<Project> prjList = projectService.getSubProjectByProjectId(projectId);
			model.put("subPrjList", prjList);
		}
		List<SignUpRespone> list = signUpResponeService.getSignUpResponeListByProjectIdAndSupplierId(projectId,supplier);
		if(list.size()==0){
			model.put("signUpResponeNum", "none");
		}
		model.put("isDividePack", pr.getIsDividePack());
		model.put("projectId", projectId);
		model.put("linker", user.getEmp());
		model.put("orgInfo", user.getOrgInfo());
		model.put("company", company);
		model.put("signUpResponeList", list);
		return new ModelAndView(returnView, model);
	}

	/** 
	 * Description :  根据项目Id跳转到报名信息页面
	 * Create Date: 2010-6-26下午03:05:30 by yangx  Modified Date: 2010-6-26下午03:05:30 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=toSignupPage")
	public ModelAndView toSignupPage(HttpServletRequest request)throws Exception {
		logger.debug("\nes SignUprecordController||toSignupPage\n");
		String projectId = request.getParameter("projectId"); //项目ID
		User user = AuthenticationHelper.getCurrentUser();
		OrgInfo orgInfo = (OrgInfo)user.getOrgInfo();
		String supplier ="";
		Map model = new HashMap();
		model.put("projectId", projectId);
		Project project = projectService.getProjectByObjId(projectId);
		List<SignUpRespone> signUpResponelist = signUpResponeService.getSignUpResponeListByProjectIdAndSupplierId(projectId,supplier);
		if(signUpResponelist.size()==0){
			model.put("signUpResponeNum", "none");
		}
		String returnUrl = "blank";
		if ((EbuyMethodEnum.SINGLE_SOURCE).equals(project.getEbuyMethod())||(EbuyMethodEnum.INVITE_BIDDING).equals(project.getEbuyMethod())) {//判断是否为单一来源或邀请招标
			InrqDetail inrqDetail = inrqDetailService.getInrqDetailContentByProjectId(projectId);
			if (inrqDetail==null) {
				returnUrl = "blank";
			} else {
				SignUprecord signUprecord = signUprecordService.getSignUprecordBySupplierId(projectId, user);
				if (signUprecord==null) {//
					model.put("orgInfoId", orgInfo.getObjId());
					model.put("linker", user.getEmp());
					returnUrl = "signUprecordFormView";
				} else if (signUprecord!=null&&(AuditStatusEnum.AUDIT_NO_PASS).equals(signUprecord.getAuditStatus())) {
					model.put("signUprecord", signUprecord);
					returnUrl = "supplierDetail";
				} else {
					model.put("signUprecord", signUprecord);
					returnUrl = "supplierDetailForm";
				}
			}
		} else {//其他采购方式
			List<SignUpRespone> list = signUpResponeService.getSignUpResponeListByProjectIdAndSupplierId(projectId,supplier);
			if (list.size()==0) {
				model.put("signUpResponeNum", "none");
			}
			model.put("signUpResponeList", list);
			Bulletin bulletin = bulletinService.getBulletinByProjectId(projectId,BulletinTypeEnum.PURCHASE_BULLETIN);//根据项目ID获取公告 公开招标方式
			if(null==bulletin) {
					bulletin = bulletinService.getBulletinByProjectId(projectId,BulletinTypeEnum.INQPBULLETIN_BULLETIN);//询价采购方式
			}
			SignUprecord signUprecord = null;
			if(bulletin!=null) {
				if(bulletin.getProject()!=null) {
					signUprecord = signUprecordService.getSignUprecordBySupplierId(bulletin.getProject().getObjId(), user);
				}
				if(signUprecord==null) {
					model.put("orgInfoId", orgInfo.getObjId());
					model.put("linker", user.getEmp().getName());
					model.put("close", "01");//关闭按钮
					returnUrl = "signUprecordFormView";
				} else if (signUprecord!=null&&(AuditStatusEnum.AUDIT_NO_PASS).equals(signUprecord.getAuditStatus())) {
					model.put("signUprecord", signUprecord);
					returnUrl = "supplierDetail";
				} else {
					model.put("signUprecord", signUprecord);
					returnUrl = "supplierDetailForm";
				}
			}
		}
	return new ModelAndView(returnUrl, model);
	}
	

	
	/** 
	 * Description : 获取供应商报名信息列表 
	 * Create Date: 2010-6-23下午03:15:50 by yangx  Modified Date: 2010-6-23下午03:15:50 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=searchSignUprecordByQueryObject")
	public ModelAndView searchSignUprecordByQueryObject(HttpServletRequest request,SessionStatus status)throws Exception {
		logger.debug("\nes SignUprecordController||searchSignUprecordByQueryObject\n");
		String auditStatus = request.getParameter("auditStatus");
		String projCode = request.getParameter("projCode");
		String projName = request.getParameter("projName");
		String bailRecord = request.getParameter("bailRecord"); //判断是否已录入保证金
		User user = AuthenticationHelper.getCurrentUser();
		OrgInfo orgInfo = (OrgInfo)user.getOrgInfo();
		Page page = prePage(request);
		QueryObject<SignUprecord> queryObject = new QueryObjectBase<SignUprecord>();
		queryObject.getQueryParam().and(new QueryParam("auditStatus",QueryParam.OPERATOR_EQ,auditStatus));
		queryObject.getQueryParam().and(new QueryParam("projCode",QueryParam.OPERATOR_EQ,projCode));
		queryObject.getQueryParam().and(new QueryParam("projName",QueryParam.OPERATOR_EQ,projName));
		if("isPayFor".equals(bailRecord)){ //如果已经缴纳了保证金，该报名信息才能被审核
			queryObject.getQueryParam().and(new QueryParam("bailRecord",QueryParam.OPERATOR_EQ,"isPayFor"));
		}
		if(orgInfo.getAgencyId()!=null){//代理机构 
			queryObject.getQueryParam().and(new QueryParam("managerID",QueryParam.OPERATOR_EQ,user.getEmp().getObjId()));
		}
		if(orgInfo.getSupplierId()!=null){//供应商 
			queryObject.getQueryParam().and(new QueryParam("supplierID",QueryParam.OPERATOR_EQ,orgInfo.getObjId()));
		}
		Page<SignUprecord> pageData = signUprecordService.searchSignUprecordByQueryObject(page,queryObject);
		Map model = new HashMap();
		endPage(model, pageData, request);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  跳转到审核页面
	 * Create Date: 2010-6-23下午04:46:43 by yangx  Modified Date: 2010-6-23下午04:46:43 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=getSupplierForAudit")
	public ModelAndView getSupplierForAudit(HttpServletRequest request)throws Exception {
		logger.debug("\nes SignUprecordController||getSupplierForAudit\n");
		String objId = request.getParameter("objId");
		SignUprecord signUprecord=signUprecordService.getSignUprecordByobjId(objId);
		String projectId = signUprecord.getProject().getObjId();
		String supplierId = signUprecord.getSupplier().getObjId();
		List<SignUpRespone> list = signUpResponeService.getSignUpResponeListByProjectIdAndSupplierId(projectId,supplierId);
		Map model = new HashMap();
		if(list.size()==0){
			model.put("signUpResponeNum", "none");
		}
		model.put("signUprecord", signUprecord);
		model.put("signUpResponeList", list);
		return new ModelAndView("supplierForAudit", model);
	}
	
	
	/** 
	 * FuncName : toViewSignupRecordList
	 * Description :  跳转到查看供应商列表页面
	 * Create Date: 2011-11-15下午03:49:27 by yangx  
	 * Modified Date: 2011-11-15下午03:49:27 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=toViewSignupRecordList")
	public ModelAndView toViewSignupRecordList(HttpServletRequest request)throws Exception {
		logger.debug("\nes SignUprecordController||toViewSignupRecordList\n");
		String projectId = request.getParameter("projectId");
		Map model = new HashMap();
		Project project = projectService.get(projectId);
		ProjectRule pule = projectService.getProjectRuleByProjectId(projectId);
		List<Project> subPrjList = projectService.getSubProjectByProjectId(projectId);
		if(pule.getIsDividePack()==false){
			subPrjList = new ArrayList<Project>();
			subPrjList.add(project);
		}
		List<SignUprecord> signUprecordList = signUprecordService.getSignupRecordList(subPrjList.get(0).getObjId());
		model.put("signUprecordList", signUprecordList);
		model.put("ebuyMethod", project.getEbuyMethod());
		model.put("subPrjList", subPrjList);
		model.put("projectId", projectId);
		model.put("project", project);
		return new ModelAndView("toViewSignupRecordList", model);
	}
	
	/** 
	 * Description :  跳转到供应商报名信息列表页[代理机构] 
	 * Create Date: 2010-7-5下午02:11:36 by yangx  Modified Date: 2010-7-5下午02:11:36 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=getSignupRecordList")
	public ModelAndView getSignupRecordList(HttpServletRequest request)throws Exception {
		logger.debug("\nes SignUprecordController||getSignupRecordList\n");
		String projectId = request.getParameter("projectId");
		String returnUrl = "signUprecordList";
		Map model = new HashMap();
		Project project = projectService.get(projectId);
		ProjectRule pule = projectService.getProjectRuleByProjectId(projectId);
		List<Project> subPrjList = projectService.getSubProjectByProjectId(projectId);
		if(pule.getIsDividePack()==false){
			subPrjList = new ArrayList<Project>();
			subPrjList.add(project);
		}
		List<SignUprecord> signUprecordList = signUprecordService.getSignupRecordList(subPrjList.get(0).getObjId());
		model.put("signUprecordList", signUprecordList);
		model.put("ebuyMethod", project.getEbuyMethod());
		model.put("subPrjList", subPrjList);
		model.put("projectId", projectId);
		return new ModelAndView(returnUrl, model);
	}
	
	
	/** 
	 * FuncName : viewSignupRecordList
	 * Description :  viewSignupRecordList
	 * Create Date: 2011-11-15下午03:52:39 by yangx  
	 * Modified Date: 2011-11-15下午03:52:39 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=toSignupRecordViewList")
	public ModelAndView toSignupRecordViewList(HttpServletRequest request)throws Exception {
		logger.debug("\nes SignUprecordController||getSignupRecordList\n");
		Map model = new HashMap();
		String subProjectId = request.getParameter("prjId");
		List<SignUprecord> signUprecordList = signUprecordService.getSignupRecordList(subProjectId);
		List<BailRecord> bailRecordList =  bailRecordService.getBailRecordByProjectId(subProjectId);
		Project subProject = projectService.getProjectByObjId(request.getParameter("prjId"));
		String prjId = subProject.getParentId()!=null?subProject.getParentId(): subProject.getObjId();
		ProjectRule rule = projectService.getProjectRuleByProjectId(prjId);
		if(rule.getOpenBidSDate().after(DateUtil.parse(DateUtil.getCurDate()+" "+DateUtil.getCurTime(),"yyyy-MM-dd HH:mm:ss"))){
			model.put("passAudit", "YES");
		}
		//获取供应商投标文件、文件大小、投标状态等信息[参数：项目ID/包组ID]
		List<BidPackage> bidPackageList = bidPackageService.getAllBidPackageByProjId(request.getParameter("prjId"));
		List<Attachment> attachmentList = null;
		for (BidPackage bidPackage : bidPackageList) {
			Integer totalSize = 0;
			if(bidPackage.getBidFile()!=null){
				attachmentList = attachmentService.getAttachmentsByRealID(bidPackage.getBidFile());
				for (Attachment attachment : attachmentList) {
					totalSize += attachment.getFileSize()==null?0:attachment.getFileSize().intValue();
				}
				
			}
			bidPackage.setBidFileTotalSize(totalSize);
		}
		for (Iterator iterator = signUprecordList.iterator(); iterator.hasNext();) {
			SignUprecord signUprecord = (SignUprecord) iterator.next();
			 for (Iterator iterator2 = bailRecordList.iterator(); iterator2.hasNext();) {
				 BailRecord bailRecord = (BailRecord) iterator2.next();
				      if(bailRecord.getSignUprecord().getObjId().equals(signUprecord.getObjId())){
				    	  signUprecord.setBailStatus(BailStatusEnum.ALREADY_RECEIVE);
				      }
			}
		}
		model.put("bidPackageList", bidPackageList);
		model.put("signUprecordList", signUprecordList);
		return new ModelAndView("toSignupRecordViewList", model);
	}
	
	/**
	 * FuncName: getSignupRecordList
	 * Description :  应用于包组加载的报名列表页面
	 * @param 
	 * @param request
	 * @return
	 * @throws Exception ModelAndView
	 * @author: shenjz
	 * @Create Date:2011-5-30  上午11:09:08
	 * @Modifier: shenjz
	 * @Modified Date:2011-5-30  上午11:09:08
	 */
	@RequestMapping(params="method=findSignupRecordList")
	public ModelAndView findSignupRecordList(HttpServletRequest request)throws Exception {
		logger.debug("\nes SignUprecordController||getSignupRecordList\n");
		
		Map model = new HashMap();
		String subProjectId = request.getParameter("prjId");
		List<SignUprecord> signUprecordList = signUprecordService.getSignupRecordList(subProjectId);
		
		List<BailRecord> bailRecordList =  bailRecordService.getBailRecordByProjectId(subProjectId);
		Project subProject = projectService.getProjectByObjId(request.getParameter("prjId"));
		String prjId = subProject.getParentId()!=null?subProject.getParentId(): subProject.getObjId();
		ProjectRule rule = projectService.getProjectRuleByProjectId(prjId);
		if(rule.getOpenBidSDate().after(DateUtil.parse(DateUtil.getCurDate()+" "+DateUtil.getCurTime(),"yyyy-MM-dd HH:mm:ss"))){
			model.put("passAudit", "YES");
		}
		
		//获取供应商投标文件、文件大小、投标状态等信息[参数：项目ID/包组ID]
		List<BidPackage> bidPackageList = bidPackageService.getAllBidPackageByProjId(request.getParameter("prjId"));
		List<Attachment> attachmentList = null;
		for (BidPackage bidPackage : bidPackageList) {
			Integer totalSize = 0;
			if(bidPackage.getBidFile()!=null){
				attachmentList = attachmentService.getAttachmentsByRealID(bidPackage.getBidFile());
				for (Attachment attachment : attachmentList) {
					totalSize += attachment.getFileSize()==null?0:attachment.getFileSize().intValue();
				}
			}
			bidPackage.setBidFileTotalSize(totalSize);
		}
		
		for (Iterator iterator = signUprecordList.iterator(); iterator.hasNext();) {
			SignUprecord signUprecord = (SignUprecord) iterator.next();
			 for (Iterator iterator2 = bailRecordList.iterator(); iterator2.hasNext();) {
				 BailRecord bailRecord = (BailRecord) iterator2.next();
				      if(bailRecord.getSignUprecord().getObjId().equals(signUprecord.getObjId())){
				    	  signUprecord.setBailStatus(BailStatusEnum.ALREADY_RECEIVE);
				      }
			}
		}
		model.put("bidPackageList", bidPackageList);
		
		model.put("signUprecordList", signUprecordList);
		
		return new ModelAndView("signUprecordList2", model);
	}
	
	
	/** 
	 * FuncName : toViewSignupInfo
	 * Description :  跳转到查看供应商信息页面
	 * Create Date: 2011-11-15下午04:02:47 by yangx  
	 * Modified Date: 2011-11-15下午04:02:47 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=toViewSignupInfo")
	public ModelAndView toViewSignupInfo(HttpServletRequest request)throws Exception {
		logger.debug("\nes SignUprecordController||toViewSignupPageByAgent\n");
		String objId = request.getParameter("objId");
		SignUprecord signUprecord = signUprecordService.getSignUprecordByobjId(objId);
		String projectId = signUprecord.getProject().getObjId();
		String supplierId = signUprecord.getSupplier().getObjId();
		List<SignUpRespone> list = signUpResponeService.getSignUpResponeListByProjectIdAndSupplierId(projectId,supplierId);
		Map model = new HashMap();
		BailRecord bailRecord = bailRecordService.getBailRecordBySignUprecord(objId);
		model.put("signUprecord", signUprecord);
		model.put("signUpResponeList", list);
		model.put("bailRecord", bailRecord);
		return new ModelAndView("toViewSignupInfo", model);
	}
	
	/** 
	 * Description :  跳转到报名信息页面[代理机构跳转到具体的页面]
	 * Create Date: 2010-7-5下午03:06:55 by yangx  Modified Date: 2010-7-5下午03:06:55 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=toViewSignupPageByAgent")
	public ModelAndView toViewSignupPageByAgent(HttpServletRequest request)throws Exception {
		logger.debug("\nes SignUprecordController||toViewSignupPageByAgent\n");
		String objId = request.getParameter("objId");
		SignUprecord signUprecord = signUprecordService.getSignUprecordByobjId(objId);
		String projectId = signUprecord.getProject().getObjId();
		String supplierId = signUprecord.getSupplier().getObjId();
		List<SignUpRespone> list = signUpResponeService.getSignUpResponeListByProjectIdAndSupplierId(projectId,supplierId);
		Map model = new HashMap();
		User user=AuthenticationHelper.getCurrentUser();
		OrgInfo orgInfo = (OrgInfo)user.getOrgInfo();
		if(orgInfo.getAgencyId()!=null&&!"".equals(orgInfo.getAgencyId())) {//判断是否为代理机构
			model.put("agency","yes");//为代理机构标志
		}
		if(list.size()==0) {
			model.put("signUpResponeNum", "none");
		}
		BailRecord bailRecord = bailRecordService.getBailRecordBySignUprecord(objId);
		model.put("signUprecord", signUprecord);
		model.put("signUpResponeList", list);
		model.put("bailRecord", bailRecord);
		return new ModelAndView("signUprecord", model);
	}
	/**
	 * FuncName: toViewSignupPageTab
	 * Description :  跳转到查看报名信息界面
	 * @param 
	 * @param request
	 * @return
	 * @throws Exception ModelAndView
	 * @author: shenjz
	 * @Create Date:2011-5-31  上午11:05:58
	 * @Modifier: shenjz
	 * @Modified Date:2011-5-31  上午11:05:58
	 */
	@RequestMapping(params="method=toViewSignupPageTab")
	public ModelAndView toViewSignupPageTab(HttpServletRequest request)throws Exception {
		logger.debug("\nes SignUprecordController||toViewSignupPageByAgent\n");
		Map model = new HashMap();
		String objId = request.getParameter("objId");//获取的是报名Id
		String projectId =request.getParameter("projectId");//获取的是项目Id
		SignUprecord signUprecord = new SignUprecord();
		if(objId!=null){
			 signUprecord = signUprecordService.getSignUprecordByobjId(objId);
		}
		if(projectId==null){
			projectId = signUprecord.getProject().getObjId();
		}
		Project prj = projectService.get(projectId);
		if(null!=prj.getParentId()){
			projectId = prj.getParentId();
		}
		ProjectRule pru = projectService.getProjectRuleByProjectId(projectId);
		List<Project> projectList = projectService.getSubProjectByProjectId(projectId);
		if(projectList == null || projectList.isEmpty()){
			projectList = new ArrayList<Project>();
			projectList.add(projectService.get(projectId));
		}
		model.put("projectRule", pru);
		model.put("subPrjList", projectList);
		return new ModelAndView("viewSignupPageTab", model);
	}
	/** 
	 * Description :  跳转到报名信息列表：《更多》功能
	 * Create Date: 2010-7-12下午05:25:36 by yangx  Modified Date: 2010-7-12下午05:25:36 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=getMoreForSignUprecordList")
	public ModelAndView getMoreForSignUprecordList(HttpServletRequest request)throws Exception {
		System.out.println("shenjz==");
		String auditStatus = request.getParameter("auditStatus");
		Map model = new HashMap();
		model.put("auditStatus", auditStatus);
		return new ModelAndView("getMoreForSignUprecordList", model);
	}
	
	/**
	 * 
	 * Description :从供应商库中抽取供应商  
	 * Create Date: 2010-8-17上午09:37:32 by liuke  Modified Date: 2010-8-17上午09:37:32 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=getSupplierList")
	public ModelAndView getSupplierList(HttpServletRequest request)throws Exception {
		logger.debug("\nes SignUprecordController||getSupplierList\n");
		String projectId = request.getParameter("projectId");
		String typeflag = request.getParameter("typeflag");
		String num = request.getParameter("num");
		List<OrgInfo> allSupplierList = userApiService.getAllSuppliers(null);
		List<OrgInfo> supplierList = signUprecordService.getSupplierList(allSupplierList,num);
		Map model = new HashMap();
		model.put("supplierList", supplierList);
		model.put("projectId", projectId);
		model.put("typeflag", typeflag);
		return new ModelAndView("keyIntoSupplierPageView", model);
	}
	
	/**
	 * 
	 * Description :从供应商库中抽取供应商  
	 * Create Date: 2010-8-17上午09:37:32 by liuke  Modified Date: 2010-8-17上午09:37:32 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=toSupplierList")
	public ModelAndView toSupplierList(HttpServletRequest request)throws Exception {
		String projectId = request.getParameter("projectId") ;
		Map model = new HashMap();
		List subProjectList = openBidRecordService.getSubProjectByProjectId(projectId);
		model.put("subProjectList", subProjectList);
		model.put("projectId", projectId);
		return new ModelAndView("keyIntoSupplierListView", model);
	}
	
	/**
	 * 
	 * Description :  从供应商库中抽取供应商(包组) 
	 * Create Date: 2010-8-17下午03:57:43 by lic  Modified Date: 2010-8-17下午03:57:43 by lic
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=toSupplierSubList")
	public ModelAndView toSupplierSubList(HttpServletRequest request)throws Exception {
		logger.debug("\nes SignUprecordController||toSupplierSubList\n");
		String projectId = request.getParameter("projectId");
		Project pro = projectService.getProjectByObjId(projectId);
		Map<String ,Object> model = new HashMap<String ,Object>();
		model.put("projectId", projectId);
		model.put("project", pro);
		List<InrqDetail> listInrqDetail = inrqDetailService.getInrqDetailByProjectId(projectId);
		if (null!=listInrqDetail&&listInrqDetail.size()>0) {
			String typeflag = "update";
			Inviterollrequ inviterollrequ = listInrqDetail.get(0).getInviterollrequ();
			if(InrqAuditStatusEnum.AUDITING.equals(inviterollrequ.getAuditStatus())){//待审核状态
				typeflag = "auditing";
			}
			if(InrqAuditStatusEnum.AUDIT_PASS.equals(inviterollrequ.getAuditStatus())){//审核通过状态
				typeflag = "auditPass";
			}
			model.put("typeflag",typeflag);
			model.put("inviterollrequ",listInrqDetail.get(0).getInviterollrequ());
		} else {//第一次录入供应商
			model.put("typeflag","new");
		}
		model.put("listInrqDetail",listInrqDetail);
		return new ModelAndView("keyIntoSupplierView", model);
	}
	
    /**
     * 
     * Description : 审核供应商报名 
     * Create Date: 2010-9-6上午11:03:07 by liuke  Modified Date: 2010-9-6上午11:03:07 by liuke
     * @param   
     * @return  
     * @Exception
     */
	@RequestMapping(params="method=auditSignUprecord")
	public ModelAndView auditSignUprecord(HttpServletRequest request, String ids,String opinion)throws Exception {
		logger.debug("\nes SignUprecordController||auditSignUprecord\n");
		String objId = request.getParameter("objId");
		if(ids==null||"".equals(ids)){ 
			ids = objId+",";
		}
		String auditStatus = request.getParameter("auditStatus");
		signUprecordService.auditSignUprecord(ids,opinion,auditStatus);
		Map model = new HashMap();
		return new ModelAndView(Constants.JSON_VIEW, model);
	}

	/**
	 * Description :  跳转到审核通过的供应商信息显示界面
	 * Create Date: 2010-10-20上午09:36:14 by shenjianzhuang  Modified Date: 2010-10-20上午09:36:14 by shenjianzhuang
	 * @param request
	 * @return  ModelAndView
	 * @Exception
	 */	
	@RequestMapping(params="method=viewSignupRecordList")
	public ModelAndView viewSignupRecordList(HttpServletRequest request)throws Exception {
		logger.debug("\nes SignUprecordController||viewSignupRecordList\n");
		String projectId = request.getParameter("projectId");
		String returnUrl = "viewSignupRecordList";
		Map model = new HashMap();
		List<SignUprecord> signUprecordList = signUprecordService.findSignupRecordList(projectId);
		model.put("signUprecordList", signUprecordList);
		model.put("projectId", projectId);
		return new ModelAndView(returnUrl, model);
	}
	
	/** 
	 * Description :  代理机构新增补录供应商页面 
	 * Create Date: 2010-10-20上午10:36:57 by shenjianzhuang  Modified Date: 2010-10-20上午10:36:57 by shenjianzhuang
	 * @param request
	 * @return  ModelAndView
	 * @Exception
	 */		
	@RequestMapping(params="method=toPageForAgency")
	public ModelAndView toPageForAgency(HttpServletRequest request) {
		logger.debug("\nes SignUprecordController||toPageForAgency\n");
		String projectId = request.getParameter("projectId");//项目ID
		String supplier ="";
		List<SignUpRespone> list = signUpResponeService.getSignUpResponeListByProjectIdAndSupplierId(projectId,supplier);
		Map model = new HashMap();
		if(list.size()==0){
			model.put("signUpResponeNum", "none");
		}
		model.put("projectId", projectId);
		model.put("signUpResponeList", list);
		return new ModelAndView("toPageForAgency", model);
	}
	
	/** 
	 * Description :  代理机构修改补录供应商页面 
	 * Create Date: 2010-10-20上午10:36:57 by shenjianzhuang  Modified Date: 2010-10-20上午10:36:57 by shenjianzhuang
	 * @param request
	 * @return  ModelAndView
	 * @Exception
	 */		
	@RequestMapping(params="method=updatePageForAgency")
	public ModelAndView updatePageForAgency(HttpServletRequest request) {
		logger.debug("\nes SignUprecordController||updatePageForAgency\n");
		SignUprecord signUprecord = signUprecordService.getSignUprecordByobjId(request.getParameter("objId"));
		String projectId = signUprecord.getProject().getObjId();
		String supplierId = signUprecord.getSupplier().getObjId();
		List<SignUpRespone> list = signUpResponeService.getSignUpResponeListByProjectIdAndSupplierId(projectId,supplierId);
		Map model = new HashMap();
		if(list.size()==0){
			model.put("signUpResponeNum", "none");
		}
		model.put("projectId", projectId);
		model.put("signUprecord", signUprecord);
		model.put("signUpResponeList", list);
		return new ModelAndView("updatePageForAgency", model);
	}
	
	/** 
	 * Description :  补录供应商
	 * Create Date: 2010-10-20上午11:45:04 by shenjianzhuang  Modified Date: 2010-10-20上午11:45:04 by shenjianzhuang
	 * @param request
	 * @param signUprecord
	 * @param status
	 * @throws Exception
	 * @return  ModelAndView
	 * @Exception
	 */
	@RequestMapping(params="method=saveSignUprecordForAgency")
	public ModelAndView saveSignUprecordForAgency(HttpServletRequest request,SignUprecord signUprecord, SessionStatus status)throws Exception {
		logger.debug("\nes SignUprecordController||saveSignUprecordForAgency\n");
		List<SignUprecord> signUprecordList = signUprecordService.getSignupRecordList(signUprecord.getProject().getObjId()); 
		Map model = new HashMap();
		boolean checkValue = true;
		for (Iterator iterator = signUprecordList.iterator(); iterator
				.hasNext();) {
			SignUprecord signUprecord2 = (SignUprecord) iterator.next();
			if(!signUprecordService.checkSupllierInSignupRecord(signUprecord2.getSupplier().getObjId(),signUprecord.getSupplier().getObjId())){
				checkValue = false;
				break;
			}
		}
		signUprecord.setObjId(null);
		String num = request.getParameter("num");
		if(null!=num &&!"".equals(num)){
			Integer nums = Integer.valueOf(num);
			List<SignUpRespone> signUpResponeList = new ArrayList<SignUpRespone>();
			if(checkValue){
				for(int i=0;i<nums;i++) {
					SignUpRespone signUpRespone = new SignUpRespone();
					signUpRespone.setObjId(request.getParameter("signUpResponeId"+i));
					signUpRespone.setResponseValue(request.getParameter("responseValue"+i));
					SignUpCondFactor signupcondFactor = signUpCondFactorService.getSignUpCondFactorByObjId(request.getParameter("signUpCondFactorId"+i));
					signUpRespone.setSignUpCondFactor(signupcondFactor);
					signUpResponeList.add(signUpRespone);
					Boolean	isUploadFile = new Boolean(request.getParameter("isUploadFile"+i));	//附件上传控制
					if(isUploadFile){
						Object o=AttachmentUtil.uploadFile(request,("attachFile"+i));
						if(o instanceof GpcBaseObject){
							Attachment attachment = (Attachment)o;
							attachmentService.saveAttachment((Attachment)o);
							signUpRespone.setSignUpResponeFile(attachment);
						}
					}else{
						String attId = request.getParameter("attachRelaId"+i);
						if(null != attId && !"".equals(attId)){
							Attachment attachment = new Attachment();
							attachment.setObjId(attId);
							signUpRespone.setSignUpResponeFile(attachment);
						}else{
							signUpRespone.setSignUpResponeFile(null);
						}
					}
					signUpResponeList.add(signUpRespone);
				}
				signUprecordService.saveSignUprecordAndSignUpResponeForAgency(signUprecord, signUpResponeList);
			}else {
				model.put("failure", true);
			}
			
		} else {
			if(checkValue){
				signUprecordService.saveSignUprecordAndSignUpResponeForAgency(signUprecord, new ArrayList<SignUpRespone>());
			}else{
				model.put("failure", true);
			}
		}
		status.setComplete();	
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	/** 
	 * Description :  修改供应商报名信息
	 * Create Date: 2010-10-20上午11:45:04 by shenjianzhuang  Modified Date: 2010-10-20上午11:45:04 by shenjianzhuang
	 * @param request
	 * @param signUprecord
	 * @param status
	 * @throws Exception
	 * @return  ModelAndView
	 * @Exception
	 */
	@RequestMapping(params="method=updateSignUprecordForAgency")
	public ModelAndView updateSignUprecordForAgency(HttpServletRequest request,SignUprecord signUprecord, SessionStatus status)throws Exception {
		logger.debug("\nes SignUprecordController||updateSignUprecordForAgency\n");
		String num = request.getParameter("num");
		if(null!=num &&!"".equals(num)){
			Integer nums = Integer.valueOf(num);
			List<SignUpRespone> signUpResponeList = new ArrayList<SignUpRespone>();
			for(int i=0;i<nums;i++) {
				SignUpRespone signUpRespone = new SignUpRespone();
				signUpRespone.setObjId(request.getParameter("signUpResponeId"+i));
				signUpRespone.setResponseValue(request.getParameter("responseValue"+i));
				SignUpCondFactor signupcondFactor = signUpCondFactorService.getSignUpCondFactorByObjId(request.getParameter("signUpCondFactorId"+i));
				signUpRespone.setSignUpCondFactor(signupcondFactor);
				Boolean	isUploadFile = new Boolean(request.getParameter("isUploadFile"+i));	//附件上传控制
				if(isUploadFile){
					Object o=AttachmentUtil.uploadFile(request,("attachFile"+i));
					if(o instanceof GpcBaseObject){
						Attachment attachment = (Attachment)o;
						attachmentService.saveAttachment((Attachment)o);
						signUpRespone.setSignUpResponeFile(attachment);
					}
				}else{
					String attId = request.getParameter("attachRelaId"+i);
					if(null != attId && !"".equals(attId)){
						Attachment attachment = new Attachment();
						attachment.setObjId(attId);
						signUpRespone.setSignUpResponeFile(attachment);
					}else{
						signUpRespone.setSignUpResponeFile(null);
					}
				}
				signUpResponeList.add(signUpRespone);
			}
			signUprecordService.saveSignUprecordAndSignUpRespone(signUprecord, signUpResponeList,null);
		} else {
			signUprecordService.saveSignUprecordAndSignUpRespone(signUprecord, new ArrayList<SignUpRespone>(),null);
		}
		status.setComplete();	
		return new ModelAndView(Constants.JSON_VIEW);
	}
	/**
	 * Description :  删除供应商报名信息
	 * Create Date: 2010-10-20下午03:27:30 by shenjianzhuang  Modified Date: 2010-10-20下午03:27:30 by shenjianzhuang
	 * @param objId
	 * @param status
	 * @return  ModelAndView
	 * @Exception
	 */
	@RequestMapping(params="method=removeSignUprecordForAgency")
	public ModelAndView removeSignUprecordForAgency(String objId,SessionStatus status){
		logger.debug("\nes SignUprecordController||removeSignUprecordForAgency\n");
		signUprecordService.deleteSignUprecord(objId);
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
 
	
	
	
	
	
	/**
	 * Description :根据项目获得报名信息
	 * Create Date: 2010-10-20下午03:27:30 by liuke  Modified Date: 2010-10-20下午03:27:30 by liuke
	 * @param objId
	 * @param status
	 * @return  ModelAndView
	 * @Exception
	 */
	@RequestMapping(params="method=getSignUpListByProjectId")
	public ModelAndView getSignUpListByProjectId(HttpServletRequest request, String projectId,SessionStatus status)throws Exception {
		logger.debug("\nes SignUprecordController||removeSignUprecordForAgency\n");
		String packId = request.getParameter("packId");
		List<SignUprecord> signList = signUprecordService.getSignUprecordByprojectId(projectId);
		List<OpenbidGeneralview> openbidGeneralviewList  =   openbidGeneralviewService.getOpenbidGeneralviewListByProject(projectId);
		List<SignUprecord> newSignList = new ArrayList<SignUprecord>();
	    for(SignUprecord signUprecord:signList){
	    	String supplierId = signUprecord.getSupplier().getObjId();
	    	boolean flag = true;
	    	for(OpenbidGeneralview generalview:openbidGeneralviewList){
	    		String supId = generalview.getSupplierId();
	    		String subProjectId = generalview.getSubProj().getObjId();
	    		if(supplierId!=null&&supplierId.equals(supId)&&packId.equals(subProjectId)){
	    			flag=false;
	    		}
	    	}
	    	if(flag){
    			newSignList.add(signUprecord);
    		}
	    }
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("signList", newSignList);
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	/**
	 * FuncName: finishSignupRecord
	 * Description : 完成供应商报名
	 * @param 
	 * @param request
	 * @return
	 * @throws Exception ModelAndView
	 * @author: yangx
	 * @Create Date:2011-7-21  上午09:19:17
	 * @Modifier: chench
	 * @Modified Date:2011-7-21  上午09:19:17
	*/		 
	@RequestMapping(params="method=finishSignupRecord")
	public ModelAndView finishSignupRecord(HttpServletRequest request)throws Exception {
		logger.debug("\nes SignUprecordController||finishSignupRecord\n");
		String projectId = request.getParameter("objId");
		signUprecordService.checkSignupRecord(projectId);
		OrgInfoController org;
		
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/**
	 * Description :根据项目获得投标单位列表
	 * Create Date: 2010-10-20下午03:27:30 by liuke  Modified Date: 2010-10-20下午03:27:30 by liuke
	 * @param objId
	 * @param status
	 * @return  ModelAndView
	 * @Exception
	 */
	@RequestMapping(params="method=getSignListByProjectId")
	public ModelAndView getSignListByProjectId(HttpServletRequest request, String projectId,SessionStatus status)throws Exception {
		logger.debug("\nes SignUprecordController||removeSignUprecordForAgency\n");
		List<SignUprecord> signList = signUprecordService.getSignUprecordByprojectId(projectId);
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("signList", signList);
		model.put("projectId", projectId);
		status.setComplete();

		return new ModelAndView("sell_bid_file",model);
	}
	
	/**
	 * Description :根据项目获得投标报名单位列表
	 * Create Date: 2011-10-17下午03:27:30 by chenhj  Modified Date: 2011-10-17下午03:27:30 by chenhj
	 * @param objId
	 * @param status
	 * @return  ModelAndView
	 * @Exception
	 */
	@RequestMapping(params="method=getSignListToJsonByProjectId")
	public ModelAndView getSignListToJsonByProjectId(HttpServletRequest request, String projectId,SessionStatus status)throws Exception {
		logger.debug("\nes SignUprecordController||removeSignUprecordForAgency\n");
		QueryObject query =new QueryObjectBase();
		query.setQueryParam(new QueryParam("projectID",projectId));
		List<SignUprecord> signList = signUprecordService.getSignUprecordListByQueryObject(query);
		
		//过滤重复供应商
	 
		
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("signList", signList);
		model.put("projectId", projectId);
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW,model);
	} 
	/**
	 * FuncName: anonymousSignUp
	 * Description :  到匿名投标签到页面
	 * @param 
	 * @param request
	 * @param status
	 * @return
	 * @throws Exception ModelAndView
	 * @author: shenjz
	 * @Create Date:2011-12-18  下午01:24:03
	 * @Modifier: shenjz
	 * @Modified Date:2011-12-18  下午01:24:03
	 */
	@RequestMapping(params="method=viewBidPackage")
	public ModelAndView viewBidPackage(HttpServletRequest request, SessionStatus status)throws Exception {
		logger.debug("\nes SignUprecordController||anonymousSignUp\n");
		Map model = new HashMap();
		String prjId = request.getParameter("subProjectId");//项目ID
		OrgInfo orginfo = userApiService.getOrgInfoByUser(AuthenticationHelper.getCurrentUser());
		model.put("bidNo", "");
		List<OpenBidSign> openBidSignList = openBidSignService.getListBySupplierIdAndTenderId(orginfo, prjId);
		if(openBidSignList!=null&&!openBidSignList.isEmpty()){
			List<Bid> bidList = bidService.getBidListByProjectIdAndUserId(prjId, orginfo.getObjId());
			if(openBidSignList!=null&&!openBidSignList.isEmpty()){
				model.put("bidNo", bidList.get(0).getBidNo());
			}
		}
		model.put("orginfo", orginfo);
		model.put("subPrjId", prjId);
		return new ModelAndView("viewBidPackage",model);
	}
	
	
	/**
	 * FuncName: anonymousSignUp
	 * Description : 匿名签到
	 * @param 
	 * @param request
	 * @param status
	 * @return
	 * @throws Exception ModelAndView
	 * @author: shenjz
	 * @Create Date:2011-12-18  下午01:24:03
	 * @Modifier: shenjz
	 * @Modified Date:2011-12-18  下午01:24:03
	 */
	@RequestMapping(params="method=anonymousSignUp")
	public ModelAndView anonymousSignUp(HttpServletRequest request, SessionStatus status)throws Exception {
		logger.debug("\nes SignUprecordController||anonymousSignUp\n");
		Map model = new HashMap();
		String csSn="";
		OrgInfo orginfo = userApiService.getOrgInfoByUser(AuthenticationHelper.getCurrentUser());
		String tenderId = request.getParameter("subProjectId");//项目/包组ID+
		String bidNo = request.getParameter("bidNo");
		List<Bid> bidList = bidService.getBidAnonymousListByProjectIdAndBidNO(tenderId, bidNo);
		List<OpenBidSign> openBidSignList = openBidSignService.getListBySupplierIdAndTenderId(orginfo, tenderId);
		if(openBidSignList==null||openBidSignList.isEmpty()){
			if(bidList!=null&&!bidList.isEmpty()){
				OpenBidSign openBidSign = new OpenBidSign();
				openBidSign.setTenderId(tenderId);
				openBidSign.setSupplierCode(orginfo.getOrgCode());
				openBidSign.setSupplierId(orginfo);
				openBidSign.setSupplierName(orginfo.getOrgName());
				openBidSign.setSignNo(bidNo);
				openBidSignService.saveAnonymousSignUp(bidNo,tenderId,openBidSign,orginfo,AuthenticationHelper.getCurrentUser(),csSn);
			}else{
				model.put("failure", true);
				model.put("result", "匿名投标号输入不对或您未参加匿名投标，签到失败！");
			}
		}else{
			model.put("failure", true);
			model.put("result", "您已签到！");
		}
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
}
