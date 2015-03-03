package com.gpcsoft.epp.signuprecord.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.gpcsoft.core.dao.hibernate.query.QueryObjectBase;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.bid.domain.BailRecord;
import com.gpcsoft.epp.bid.service.BailRecordService;
import com.gpcsoft.epp.bulletin.domain.Bulletin;
import com.gpcsoft.epp.bulletin.domain.BulletinTypeEnum;
import com.gpcsoft.epp.bulletin.service.BulletinService;
import com.gpcsoft.epp.common.domain.AuditStatusEnum;
import com.gpcsoft.epp.common.domain.EbuyMethodEnum;
import com.gpcsoft.epp.common.service.UserApiService;
import com.gpcsoft.epp.inviterollrequ.domain.InrqAuditStatusEnum;
import com.gpcsoft.epp.inviterollrequ.domain.InrqDetail;
import com.gpcsoft.epp.inviterollrequ.domain.Inviterollrequ;
import com.gpcsoft.epp.inviterollrequ.service.InrqDetailService;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.domain.ProjectRule;
import com.gpcsoft.epp.project.service.ProjectService;
import com.gpcsoft.epp.projreview.domain.OpenbidGeneralview;
import com.gpcsoft.epp.projreview.service.OpenBidRecordService;
import com.gpcsoft.epp.projreview.service.OpenbidGeneralviewService;
import com.gpcsoft.epp.signuprecord.dao.SignUprecordDao;
import com.gpcsoft.epp.signuprecord.domain.SignUpCondFactor;
import com.gpcsoft.epp.signuprecord.domain.SignUpRespone;
import com.gpcsoft.epp.signuprecord.domain.SignUprecord;
import com.gpcsoft.epp.signuprecord.service.SignUpCondFactorService;
import com.gpcsoft.epp.signuprecord.service.SignUpResponeService;
import com.gpcsoft.epp.signuprecord.service.SignUprecordService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.Attachment;
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
  * @gpcsoft.view value="signUprecord"
  *  url="view/es/planform/signuprecord/signUprecord.jsp"
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
  *  @gpcsoft.view value="indexPage"
  *  url="view/es/ext/apply/index.jsp"
  *  @gpcsoft.view value="indexDetailPage"
  *  url="view/es/ext/apply/indexDetail.jsp"
  *  
  *  
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={SignUprecord.class})
@RequestMapping("/SignUprecordExtController.do")//页面请求路径,可修改
@SuppressWarnings(value="unchecked")
public class SignUprecordExtController extends AnnotationMultiController<SignUprecord> {

	@Autowired(required=true) @Qualifier("signUprecordServiceImpl")
	private SignUprecordService signUprecordService;
	
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
	
	@Autowired(required=true) @Qualifier("signUprecordDaoHibernate")
	private SignUprecordDao signUprecordDao;
	
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
		String returnUrl = "indexDetailPage";
		Map<String,Object> model = new HashMap<String,Object>();
		try {
			String userName = request.getParameter("userName");
			String password = request.getParameter("password");
			String subPrjIds = request.getParameter("subPrjIds");
			String projectId = signUprecord.getProject().getObjId();
			Project project = projectService.get(projectId);
			Set<Project> subProjs = project.getSubProject();
			if(subProjs.isEmpty()){
				subProjs = new HashSet<Project>();
				subProjs.add(project);
				model.put("isNotPacked", "isNotPacked");
				subPrjIds="";  //设置包组ID为""
			}
			model.put("subProjs", subProjs);
			User user = AuthenticationHelper.getCurrentUser();
			if(null!=((OrgInfo)user.getOrgInfo()).getSupplierId()) {//如果当前用户为供应商
				signUprecord.setSupplier((OrgInfo)user.getOrgInfo());
			}
			signUprecordService.saveSignUprecordAndSignUpRespone(signUprecord, new ArrayList<SignUpRespone>(),subPrjIds);
			List<SignUprecord> signUprecordList = signUprecordDao.getSignUprecordListByprojectIdAndSupplierId(projectId,user.getOrgInfo().getObjId());
			model.put("signUprecordList", signUprecordList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(returnUrl,model);
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
		String objId = request.getParameter("objId");//项目ID
		SignUprecord signUprecord=signUprecordService.getSignUprecordByobjId(objId);
		String projectId = signUprecord.getProject().getObjId();
		String supplier ="";
		List<SignUpRespone> list = signUpResponeService.getSignUpResponeListByProjectIdAndSupplierId(projectId,supplier);
		String returnUrl = "";
		Map model = new HashMap();
		model.put("signUprecord", signUprecord);
		model.put("signUpResponeList", list);
		if(list.size()==0){
			model.put("signUpResponeNum", "none");
		}
		if(signUprecord!=null&&(AuditStatusEnum.AUDIT_NO_PASS).equals(signUprecord.getAuditStatus())){
			returnUrl = "supplierDetail";
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
		String projectId = request.getParameter("projectId");//项目ID
		User user = AuthenticationHelper.getCurrentUser();
		String supplier ="";
		List<SignUpRespone> list = signUpResponeService.getSignUpResponeListByProjectIdAndSupplierId(projectId,supplier);
		Map model = new HashMap();
		if(list.size()==0){
			model.put("signUpResponeNum", "none");
		}
		model.put("projectId", projectId);
		model.put("linker", user.getEmp());
		model.put("signUpResponeList", list);
		return new ModelAndView("signUprecordFormView", model);
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
		User user = AuthenticationHelper.getCurrentUser();
		OrgInfo orgInfo = (OrgInfo)user.getOrgInfo();
		Page page = prePage(request);
		QueryObject<SignUprecord> queryObject = new QueryObjectBase<SignUprecord>();
		queryObject.getQueryParam().and(new QueryParam("auditStatus",QueryParam.OPERATOR_EQ,auditStatus));
		queryObject.getQueryParam().and(new QueryParam("projCode",QueryParam.OPERATOR_EQ,projCode));
		queryObject.getQueryParam().and(new QueryParam("projName",QueryParam.OPERATOR_EQ,projName));
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
		List<SignUprecord> signUprecordList = signUprecordService.getSignupRecordList(projectId);
		model.put("signUprecordList", signUprecordList);
		model.put("ebuyMethod", project.getEbuyMethod());
		if(signUprecordList==null||signUprecordList.size()<1){
			returnUrl = "blank";
		}
		return new ModelAndView(returnUrl, model);
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
			if(inviterollrequ.getAuditStatus().equals(InrqAuditStatusEnum.AUDITING)){//待审核状态
				typeflag = "auditing";
			}
			if(inviterollrequ.getAuditStatus().equals(InrqAuditStatusEnum.AUDIT_PASS)){//审核通过状态
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
	 * FuncName: toIndexPage
	 * Description :  跳转到报名页面
	 * @param 
	 * @param objId
	 * @param status
	 * @return ModelAndView
	 * @author: liuke
	 * @Create Date:2011-4-22  下午02:52:10
	 * @Modifier: liuke
	 * @Modified Date:2011-4-22  下午02:52:10
	 */
	@RequestMapping(params="method=toIndexPage")
	public ModelAndView toIndexPage(HttpServletRequest request,SessionStatus status){
		logger.debug("\nes SignUprecordExtController||toIndexPage\n");
		Map<String,Object> model = new HashMap<String,Object>();
		String returnUrl = "indexPage";
		boolean isPack = false; 
		try {
			String projectId = (String)request.getAttribute("projectId");
			if(projectId == null || "".equals(projectId)){
				projectId = request.getParameter("projectId");
			}
			String packCode = request.getParameter("packCode");
			Project project = projectService.get(projectId);
			Set<Project> subProjs = project.getSubProject();
			User user = AuthenticationHelper.getCurrentUser();
			Date nowDate = new Date();
			ProjectRule projectRule = projectService.getProjectRuleByProjectId(projectId);
			Date signUpSTime = projectRule.getSignUpSTime();  //报名开始时间
			Date signUpETime = projectRule.getSignUpETime();  //报名结束时间
			String signUpFlag = "";
			String packId = "";
			if(nowDate.after(signUpSTime)&&nowDate.before(signUpETime)){
				signUpFlag = "isCanSignUp";
			}else{
				signUpFlag = "isNotSignUp";
			}
			model.put("signUpFlag", signUpFlag);
			model.put("project", project);
			if(subProjs.isEmpty()){   //如果该项目没有分包
				subProjs = new HashSet<Project>();
				subProjs.add(project);
				model.put("isNotPacked", "isNotPacked");
				isPack = false;
			}else{
				isPack = true;
				if(packCode!=null){ //如果是按包组进行报名
					model.put("pack", packCode);
				   for (Iterator iterator = subProjs.iterator(); iterator.hasNext();) { //遍历包组
					Project subProj = (Project) iterator.next();
					if(packCode.equals(subProj.getProjCode())){  //获得当前投标包组ID
						packId  = subProj.getObjId();
					}
				}
			}
			}
			model.put("subProjs", subProjs);
			model.put("projectId", projectId);
			if(user != null){
				List<SignUprecord> signUprecordList = signUprecordDao.getSignUprecordListByprojectIdAndSupplierId(projectId,user.getOrgInfo().getObjId());	
				SignUprecord signUprecordPack = signUprecordDao.getSignUprecordByprojectIdAndSupplierId(packId,user.getOrgInfo().getObjId());	
				if(signUprecordList==null||signUprecordList.isEmpty()){  //未报名
					returnUrl ="indexPage";
				}else{  //已报名
					if(isPack&&signUprecordPack==null){ //如果该包组还未报名，则跳转到报名页面
						returnUrl = "indexPage";
					}else{
						returnUrl = "indexDetailPage";
					}
					model.put("signUprecordList", signUprecordList);
				}
			}else{
				returnUrl ="indexPage";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return new ModelAndView(returnUrl,model);
	}
	
}
