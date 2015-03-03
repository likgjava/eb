package com.gpcsoft.epp.purchasedoc.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.bizplatform.agency.domain.Agency;
import com.gpcsoft.bizplatform.agency.service.AgencyService;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.context.FrameMessageResource;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryObjectBase;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.dao.hibernate.query.QueryWebUtils;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.utils.FileUtil;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.core.utils.StringUtils;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.common.domain.AuditStatusEnum;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.common.domain.DataItem;
import com.gpcsoft.epp.common.domain.EbuyMethodEnum;
import com.gpcsoft.epp.common.domain.EsExceptionEnum;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.common.service.UserApiService;
import com.gpcsoft.epp.common.utils.StringUtil;
import com.gpcsoft.epp.eval.service.CongruousFactorService;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.domain.ProjectEnum;
import com.gpcsoft.epp.project.service.ProjectService;
import com.gpcsoft.epp.projreview.service.GenviewDefineService;
import com.gpcsoft.epp.projrule.domain.ProjProcessRule;
import com.gpcsoft.epp.projrule.service.ProjProcessRuleService;
import com.gpcsoft.epp.purchasedoc.domain.ProcFileDownRec;
import com.gpcsoft.epp.purchasedoc.domain.PurchaseDoc;
import com.gpcsoft.epp.purchasedoc.domain.PurchaseDocAtt;
import com.gpcsoft.epp.purchasedoc.domain.PurchaseDocEnum;
import com.gpcsoft.epp.purchasedoc.service.ProcFileDownRecService;
import com.gpcsoft.epp.purchasedoc.service.PurchaseDocAttService;
import com.gpcsoft.epp.purchasedoc.service.PurchaseDocService;
import com.gpcsoft.epp.signuprecord.domain.SignUprecord;
import com.gpcsoft.epp.signuprecord.service.SignUprecordService;
import com.gpcsoft.epp.webService.common.util.XmlUtil;
import com.gpcsoft.epp.webService.common.util.ZipUtils;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.domain.Employee;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.auth.service.AttachmentService;
import com.gpcsoft.srplatform.common.utils.AttachmentUtil;
import com.gpcsoft.srplatform.sysconfig.domain.SysConfigEnum;

/**
 * @gpcsoft.view value="submitPurchaseDocByJZGCDetailView"
 *               url="view/es/planform/purchasedoc/submitPurchaseDocByJZGCDetail.jsp"
 * @gpcsoft.view value="submitPurchaseDocByJZGCFormView"
 *               url="view/es/planform/purchasedoc/submitPurchaseDocByJZGCForm.jsp"
 * @gpcsoft.view value="purchaseDocByJZGCFormView"
 *               url="view/es/planform/purchasedoc/purchaseDocByJZGCForm.jsp"
 * @gpcsoft.view value="purchaseDocFormView"
 *               url="view/es/planform/purchasedoc/purchaseDocForm.jsp"
 * @gpcsoft.view value="purchaseDocTreeFormView"
 *               url="view/es/planform/purchasedoc/purchaseDocTreeForm.jsp"
 * @gpcsoft.view value="purchaseDocListView"
 *               url="view/es/planform/purchasedoc/purchaseDocList.jsp"
 * @gpcsoft.view value="purchaseDocDetailView"
 *               url="view/es/planform/purchasedoc/purchaseDocDetail.jsp"
 * @gpcsoft.view value="blankView" url="view/es/common/blank.jsp"
 * @gpcsoft.view value="PurchaseDocDeptAuditView"
 *               url="view/es/planform/purchasedoc/purchaseDoc_dept_audit.jsp"
 * @gpcsoft.view value="PurchaseDocDeptApproveView"
 *               url="view/es/planform/purchasedoc/purchaseDoc_dept_approve.jsp"
 * @gpcsoft.view value="PurchaseDocLeaderApproveView"
 *               url="view/es/planform/purchasedoc/purchaseDoc_leader_approve.jsp"
 * @gpcsoft.view value="PurchaseDocReleaseView"
 *               url="view/es/planform/purchasedoc/purchaseDoc_release.jsp"
 * @gpcsoft.view value="PurchaseDocConfigView"
 *               url="view/es/planform/purchasedoc/purchaseDoc_config.jsp"
 * @gpcsoft.view value="updatePurchaseDocFormView"
 *               url="view/es/planform/purchasedoc/UpdatePurchaseDocForm.jsp"
 * @gpcsoft.view value="submitPurchaseDocFormView"
 *               url="view/es/planform/purchasedoc/submitPurchaseDocFormView.jsp"
 * @gpcsoft.view value="purchaseDocAuditingView"
 *               url="view/es/planform/purchasedoc/purchaseDoc_Auditing.jsp"
 * @gpcsoft.view value="purchaseDocAuditingViewJCJ"
 *               url="view/es/planform/purchasedoc/purchaseDoc_Auditing_jcj.jsp"
 * @gpcsoft.view value="purchaseProcurementView"
 *               url="view/es/planform/purchasedoc/purchaseDoc_Procurement.jsp"
 * @gpcsoft.view value="auditpurchaseDocPageView"
 *               url="view/es/planform/purchasedoc/auditpurchaseDocPage.jsp"
 * @gpcsoft.view value="updatePurchaseDocPageView"
 *               url="view/es/planform/purchasedoc/updatePurchaseDocPage.jsp"
 * @gpcsoft.view value="configPurchaseDocPageView"
 *               url="view/es/planform/purchasedoc/configPurchaseDocPage.jsp"
 * @gpcsoft.view value="auditingPurchaseDocPageView"
 *               url="view/es/planform/purchasedoc/auditingPurchaseDocPage.jsp"
 * @gpcsoft.view value="getMorePurchaseDocRecordList"
 *               url="view/es/planform/purchasedoc/morePurchaseDocRecordList.jsp"
 * @gpcsoft.view value="clarificationDocDetailView"
 *               url="view/es/planform/clarificationDoc/clarificationDocDetail.jsp"
 * @gpcsoft.view value="updateClarificationDocFormView"
 *               url="view/es/planform/clarificationDoc/UpdateClarificationDocForm.jsp"
 * @gpcsoft.view value="clarificationDocFormView"
 *               url="view/es/planform/clarificationDoc/clarificationDocForm.jsp"
 * @gpcsoft.view value="clarificationProcurementView"
 *               url="view/es/planform/clarificationDoc/clarificationDoc_Procurement.jsp"
 * @gpcsoft.view value="clarificationDocConfigView"
 *               url="view/es/planform/clarificationDoc/clarificationDoc_config.jsp"
 * @gpcsoft.view value="clarificationDocAuditingView"
 *               url="view/es/planform/clarificationDoc/clarificationDoc_Auditing.jsp"
 * @gpcsoft.view value="toModifyClarificationDoc"
 *               url="view/es/planform/clarificationDoc/UpdateClarificationDocForm.jsp"
 * 
 */
@Controller
// 标识为控制器
@Scope("request")
@SessionAttributes(types = { PurchaseDoc.class })
@RequestMapping("/PurchaseDocController.do")
// 页面请求路径,可修改
@SuppressWarnings("unchecked")
public class PurchaseDocController extends
		AnnotationMultiController<PurchaseDoc> {

	@Autowired(required = true)
	@Qualifier("purchaseDocServiceImpl")
	private PurchaseDocService purchaseDocService;
	
	@Autowired(required=true) @Qualifier("congruousFactorServiceImpl")
	private CongruousFactorService congruousFactorService;	
	
	@Autowired(required=true) @Qualifier("agencyServiceImpl")
	private AgencyService agencyService;	

	@Autowired(required=true) @Qualifier("projProcessRuleServiceImpl")
	private ProjProcessRuleService projProcessRuleService;
	
	@Autowired(required = true)
	@Qualifier("userApiServiceImpl")
	private UserApiService userApiService;

	@Autowired(required = true)
	@Qualifier("attachmentServiceImpl")
	private AttachmentService attachmentService;

	@Autowired(required = true)
	@Qualifier("signUprecordServiceImpl")
	private SignUprecordService signUprecordService;

	@Autowired(required = true)
	@Qualifier("purchaseDocAttServiceImpl")
	private PurchaseDocAttService purchaseDocAttService;

	@Autowired(required = true)
	@Qualifier("projectServiceImpl")
	private ProjectService projectService;

	@Autowired(required = true)
	@Qualifier("genviewDefineServiceImpl")
	private GenviewDefineService genviewDefineService;
	
	@Autowired(required = true)
	@Qualifier("procFileDownRecServiceImpl")
	private ProcFileDownRecService procFileDownRecService;

	private FrameMessageResource messageSource;

	private FrameMessageResource getMessageSource() {
		if (this.messageSource == null) {
			this.messageSource = (FrameMessageResource) FrameBeanFactory
					.getBean("frameMessageResource");
		}
		return this.messageSource;
	}

	/**
	 * Description : 打开优易客户端审核招标文件
	 * Modified Date: 2011-11-23下午05:33:54 by sunl
	 * @param
	 * @return
	 * @Exception
	 */
	@RequestMapping(params = "method=toOpenUeForAudit")
	public ModelAndView toOpenUeForAudit(HttpServletRequest request) throws Exception {
		String docId = request.getParameter("docId");//采购文件ID
		String isFinal = request.getParameter("isFinal");//是否终审
		
		PurchaseDoc doc = purchaseDocService.get(docId);
		Project project = projectService.get(doc.getProject().getObjId());
		
		//获取审核人、审核人账号
		Employee auditor = project.getMonitor();//项目监管人
		User auditorUser = userApiService.getUserByEmpId(auditor.getObjId());
		
		//拼装协议地址
		StringBuilder protocal = new StringBuilder();
		protocal.append("uepfm://un=").append(auditorUser.getUsername());//用户名
		protocal.append("&pwd=").append(auditorUser.getPassword());//密码
		protocal.append("&prjCode=").append(project.getProjCode());//项目编号
		/*
		 * 以下三个参数暂时不传
		protocal.append("&packCode=").append("");//包件编号
		protocal.append("&commonService=").append("");//公共服务接口地址
		protocal.append("&biddingService=").append("");//采购文件服务接口地址
		*/
		protocal.append("&sourcecode=").append("110000");//来源系统编号
		protocal.append("&trackRevisions=").append("true");//是否审核，true进入采购文件审核，false进入审核文件编制
		protocal.append("&signName=").append(auditor.getName());//审批时，修订人签名值
		protocal.append("&isFinal=").append(isFinal);//是否终审 true，通过时形成采购文件最终版，false时保留采购文件修订版
		
		Map model = new HashMap();
		model.put("protocal", protocal.toString());
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	
	/**
	 * Description : 代理机构：上传采购文件 Create Date: 2010-5-19下午05:33:54 by liuke
	 * Modified Date: 2010-5-19下午05:33:54 by liuke
	 * 
	 * @param
	 * @return
	 * @Exception
	 */
	@RequestMapping(params = "method=toPurchaseDoc")
	public ModelAndView toPurchaseDoc(HttpServletRequest request)
			throws Exception {
		logger.debug("\nes PurchaseDocController||toPurchaseDoc\n");
		String projectId = request.getParameter("projectId");
		String purchaseDocId = request.getParameter("objId");
		String fromType = request.getParameter("fromType");
		Project proj = projectService.getProjectByObjId(projectId);
		PurchaseDoc purchaseDoc = purchaseDocService
				.getPurchaseDocByProjectId(projectId);
		if (null == projectId || "".equals(projectId)) {
			purchaseDoc = purchaseDocService
					.getPurchaseDocByObjId(purchaseDocId);
			projectId = purchaseDoc.getProject().getObjId();
		}
		List<Project> subProjectList = projectService
				.getSubProjectByProjectId(projectId);
		Map model = new HashMap();
		model.put("subProjectList", subProjectList);
		model.put("project", proj);
		if ((subProjectList == null ? 0 : subProjectList.size()) != 0) {
			model.put("divided", true);
		}
		if (null != purchaseDoc) {
			/*add by sunl 读取招标文件附件*/
			List<Attachment> attachmentList = purchaseDoc.getAttachRelaId()==null?null:attachmentService.getAttachmentsByRealID(purchaseDoc.getAttachRelaId());
			List<PurchaseDocAtt> purAttList = purchaseDocAttService.getPurchaseDocAttList(projectId);
			model.put("purAttList", purAttList);
			
			List<Attachment> attList = new ArrayList<Attachment>();
			List<Attachment> tempList=null;
			for (PurchaseDocAtt purchaseDocAtt : purAttList) {
				tempList = attachmentService.getAttachmentsByRealID(purchaseDocAtt.getAttRaId());
				if(tempList!=null && tempList.size()>0) {
					Attachment att = tempList.get(0);
					attList.add(att);
				}
			}
			model.put("attList",attList);
			
			if (null != attachmentList && !attachmentList.isEmpty()) {
				Attachment attachment = attachmentList.get(0);
				model.put("attrId", attachment.getObjId());
				model.put("attrName", attachment.getViewName());
				String path = getMessageSource().getMessage(
						"epp.projectAttaPath")
						+ attachment.getPath() + File.separator;
				String filePath = getMessageSource().getMessage(
						"epp.projectAttaPath")
						+ attachment.getPath()
						+ File.separator
						+ attachment.getFileName().replace("/", File.separator);// 换回绝对路径
				try {
					if (CommonEnum.USER_STATUS_FORMAL.equals(purchaseDoc
							.getUseStatus())) {
						ZipUtils.unZip(filePath, path);
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				String[] l = FileUtil.listFiles(path);
				String viewName = "";
				if (l != null) {
					for (int i = 0; i < l.length; i++) {
						String endName = "";
						if (l[i].indexOf(".") != -1) {
							endName = l[i].substring(l[i].indexOf("."));
						}
						if (endName.equals(".doc") || endName.equals(".docx")
								|| endName.equals(".pdf")) {
							viewName = attachment.getViewName().substring(0,
									attachment.getViewName().length() - 4)
									+ endName;
						}
					}
				} else {
					model
							.put(
									"message",
									((FrameMessageResource) FrameBeanFactory
											.getBean(FrameMessageResource.BEAN_NAME))
											.getMessage("srplatform.auth.attachment.file_is_not_exit"));
				}
				model.put("attrName2", viewName);
			}
		}
		model.put("projectId", projectId);
		model.put("purchaseDoc", purchaseDoc);
		model.put("fromType", fromType);
		model.put("filePrice", proj.getPurDocPrice() == null ? "" : proj
				.getPurDocPrice());
		String returnName = "purchaseDocDetailView";
		if (ProjectEnum.JZGC.equals(proj.getTenderType())) {// 如果是建设工程，则跳到建设工程页面
			List<PurchaseDocAtt> list = purchaseDocAttService
					.getPurchaseDocAttsByPrjId(projectId);
			// Modified Date: 2011-9-20下午02:33:54 by caojz
			model.put("purchaseDocAttList", list);
			if (null != purchaseDoc && null != purchaseDoc.getObjId()) {
				if (CommonEnum.USER_STATUS_TEMP.equals(purchaseDoc
						.getUseStatus())
						|| PurchaseDocEnum.RETURNBACK.equals(purchaseDoc
								.getAuditStatus())) {// 若是临时数据，则需要跳转到提交页面
					returnName = "submitPurchaseDocByJZGCFormView";
				} else {
					returnName = "submitPurchaseDocByJZGCDetailView"; // 若已提交，则跳转到显示页面
				}
			} else {
				returnName = "purchaseDocByJZGCFormView"; // 新增采购文件页面
			}
		} else if (ProjectEnum.ZFCG.equals(proj.getTenderType())) {// 如果是政府采购，则跳到政府采购页面
			if (null != purchaseDoc && null != purchaseDoc.getObjId()) {// 若已上传采购文件，则跳到显示页面，若没有，则跳到新增页面
				if (CommonEnum.USER_STATUS_TEMP.equals(purchaseDoc
						.getUseStatus())
						|| PurchaseDocEnum.RETURNBACK.equals(purchaseDoc
								.getAuditStatus())) {// 若是临时数据，则需要跳转到提交页面
					returnName = "submitPurchaseDocFormView";
				}
			} else {
				returnName = "purchaseDocFormView";
			}
		}else if (ProjectEnum.TDJY.equals(proj.getTenderType())) {// 如果是土地交易，则跳到土地交易页面
			if (null != purchaseDoc && null != purchaseDoc.getObjId()) {// 若已上传采购文件，则跳到显示页面，若没有，则跳到新增页面
				if (CommonEnum.USER_STATUS_TEMP.equals(purchaseDoc.getUseStatus())|| PurchaseDocEnum.RETURNBACK.equals(purchaseDoc.getAuditStatus())) {// 若是临时数据，则需要跳转到提交页面
					returnName = "submitPurchaseDocFormView";
				}
			} else {
				returnName = "purchaseDocFormView";
			}
		}else if (ProjectEnum.CQJY.equals(proj.getTenderType())) {// 如果是产权交易，则跳到产权交易页面
			if (null != purchaseDoc && null != purchaseDoc.getObjId()) {// 若已上传采购文件，则跳到显示页面，若没有，则跳到新增页面
				if (CommonEnum.USER_STATUS_TEMP.equals(purchaseDoc.getUseStatus())|| PurchaseDocEnum.RETURNBACK.equals(purchaseDoc.getAuditStatus())) {// 若是临时数据，则需要跳转到提交页面
					returnName = "submitPurchaseDocFormView";
				}
			} else {
				returnName = "purchaseDocFormView";
			}
		}
		
		
		
		
		return new ModelAndView(returnName, model);
	}

	/**
	 * Description : 提交采购文件 Create Date: 2011-7-9下午20:57:27
	 * 
	 * @author liuy Modified Date: 2011-7-9下午20:57:27
	 * @author liuy
	 * @param purchaseDoc
	 *            ;采购文件;request,stauts
	 * @return
	 * @Exception
	 */
	@RequestMapping(params = "method=submitPurDoc")
	public ModelAndView submitPurDoc(HttpServletRequest request,
			PurchaseDoc purchaseDoc, SessionStatus stauts) throws Exception {
		logger.debug("\nes PurchaseDocController||submitPurDoc\n");
		PurchaseDoc purchaseDoc_ = purchaseDocService.get(purchaseDoc
				.getObjId());
		List<Attachment> attachmentList = attachmentService
				.getAttachmentsByRealID(purchaseDoc.getAttachRelaId());
		Boolean isUploadFile = new Boolean(request.getParameter("isUploadFile"));// 判断是否上传附件
		try {
			if (null != attachmentList && !attachmentList.isEmpty()) {
				Attachment attachment = attachmentList.get(0);
				String path = getMessageSource().getMessage(
						"epp.projectAttaPath")
						+ attachment.getPath() + File.separator;
				String filePath = getMessageSource().getMessage(
						"epp.projectAttaPath")
						+ attachment.getPath()
						+ File.separator
						+ attachment.getFileName().replace("/", File.separator);// 换回绝对路径
				ZipUtils.unZip(filePath, path);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		purchaseDoc_.setUseStatus(CommonEnum.USER_STATUS_FORMAL);
		purchaseDoc_.setFileType(PurchaseDocEnum.PURCHASEDOC);
		if (purchaseDoc.getKeyWord() != null) {
			purchaseDoc_.setKeyWord(purchaseDoc.getKeyWord());
		}
		if (purchaseDoc.getFilePrice() != null) {
			purchaseDoc_.setFilePrice(purchaseDoc.getFilePrice());
		}
		if (purchaseDoc.getContent() != null) {
			purchaseDoc_.setContent(purchaseDoc.getContent());
		}
		if (isUploadFile) {
			Object o = AttachmentUtil.uploadFile(request, "attachFile");
			if (o instanceof GpcBaseObject) {
				Attachment attachment = (Attachment) o;
				attachmentService.saveAttachment((Attachment) o);
				purchaseDoc_.setAttachRelaId(attachment.getRelationId());
			}
		}
		
		ProjProcessRule projProcessRule= projProcessRuleService.getProjProcessRuleByProjectIdAndCode(purchaseDoc.getProject().getObjId(),SysConfigEnum.NEEDBUYERACKPURCHASEDOC);
		if (projProcessRule!=null&&"false".equals(projProcessRule.getResValue())) {//业务规则不需要采购人确认招标文件
			purchaseDoc.setAuditStatus(PurchaseDocEnum.PURCHASEOFFICE_WAIT);//采购办审核
			purchaseDoc.setUseStatus(AuditStatusEnum.AUDIT_PASS);//有效状态
		}else{
			purchaseDoc.setAuditStatus(PurchaseDocEnum.FORAUDIT);//00待采购人确认   01 采购人确认通过  02  采购办审核通过 03 监察局审核通过
		}
		
		purchaseDocService.savePurchaseDoc(purchaseDoc_);
		stauts.setComplete();
		Map model = new HashMap();
		return new ModelAndView(Constants.JSON_VIEW, model);
	}

	/**
	 * FuncName:toPurchaseDocDeptAudit Description:采购办：审核页面 Create Date:
	 * 2010-5-27上午11:35:18 by liuke Modified Date: 2010-5-27上午11:35:18 by liuke
	 * 
	 * @param request
	 * @return ModelAndView
	 * @Exception
	 */
	@RequestMapping(params = "method=toPurchaseDocDeptAudit")
	public ModelAndView toPurchaseDocDeptAudit(HttpServletRequest request)
			throws Exception {
		logger.debug("\nes PurchaseDocController||toPurchaseDocDeptAudit\n");
		String projectId = request.getParameter("projectId");
		String objId = request.getParameter("objId");
		String returnUrl = "blankView";
		String fromType = request.getParameter("fromType");
		Map model = new HashMap();
		PurchaseDoc purchaseDoc = null;
		if (projectId != null && !"".equals(projectId)) {
			purchaseDoc = purchaseDocService
					.getPurchaseDocByProjectId(projectId);
		} else {
			purchaseDoc = purchaseDocService.getPurchaseDocByObjId(objId);
			projectId = purchaseDoc.getProject().getObjId();
		}
		if (null != purchaseDoc
				&& CommonEnum.USER_STATUS_FORMAL.equals(purchaseDoc
						.getUseStatus())) {
			returnUrl = "purchaseDocDetailView";
			List<Attachment> attachmentList = attachmentService
					.getAttachmentsByRealID(purchaseDoc.getAttachRelaId());
			Attachment attachment = attachmentList.get(0);
			if (null != attachmentList && !attachmentList.isEmpty()) {
				model.put("attrId", attachment.getObjId());
				model.put("attrName", attachment.getViewName());
			}
			String path = getMessageSource().getMessage("epp.projectAttaPath")
					+ attachment.getPath() + File.separator;
			String filePath = getMessageSource().getMessage(
					"epp.projectAttaPath")
					+ attachment.getPath()
					+ File.separator
					+ attachment.getFileName().replace("/", File.separator);// 换回绝对路径
			try {
				ZipUtils.unZip(filePath, path);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			String[] l = FileUtil.listFiles(path);
			String viewName = "";
			if (l != null) {
				for (int i = 0; i < l.length; i++) {
					String endName = "";
					if (l[i].indexOf(".") != -1) {
						endName = l[i].substring(l[i].indexOf("."));
					}
					if (endName.equals(".doc") || endName.equals(".docx")
							|| endName.equals(".pdf")) {
						viewName = attachment.getViewName().substring(0,
								attachment.getViewName().length() - 4)
								+ endName;
					}
				}
			} else {
				model
						.put(
								"message",
								((FrameMessageResource) FrameBeanFactory
										.getBean(FrameMessageResource.BEAN_NAME))
										.getMessage("srplatform.auth.attachment.file_is_not_exit"));
			}
			model.put("attrName2", viewName);
		}
		model.put("projectId", projectId);
		model.put("fromType", fromType);
		if (null != purchaseDoc && null != purchaseDoc.getObjId()) {
			model.put("purchaseDoc", purchaseDoc);
			if (PurchaseDocEnum.PURCHASEOFFICE_WAIT.equals(purchaseDoc
					.getAuditStatus())
					&& CommonEnum.USER_STATUS_FORMAL.equals(purchaseDoc
							.getUseStatus())) {
				returnUrl = "purchaseProcurementView";
			}
		}
		return new ModelAndView(returnUrl, model);
	}

	/**
	 * FuncName:toPurchaseDocConfig Description :购买人：确认采购文件页面 Create Date:
	 * 2010-5-27上午11:35:18 by liuke Modified Date: 2010-5-27上午11:35:18 by liuke
	 * 
	 * @param request
	 * @return ModelAndView
	 * @Exception
	 */
	@RequestMapping(params = "method=toPurchaseDocConfig")
	public ModelAndView toPurchaseDocConfig(HttpServletRequest request)
			throws Exception {
		logger.debug("\nes PurchaseDocController||toPurchaseDocConfig\n");
		String projectId = request.getParameter("projectId");
		String objId = request.getParameter("objId");
		Map model = new HashMap();
		PurchaseDoc purchaseDoc = null;
		String returnUrl = "blankView";
		if (projectId == null || "".equals(projectId)) {
			purchaseDoc = purchaseDocService.getPurchaseDocByObjId(objId);
			projectId = purchaseDoc.getProject().getObjId();
		} else {
			purchaseDoc = purchaseDocService
					.getPurchaseDocByProjectId(projectId);
		}
		if (null != purchaseDoc
				&& CommonEnum.USER_STATUS_FORMAL.equals(purchaseDoc
						.getUseStatus())) {
			returnUrl = "purchaseDocDetailView";
			model.put("purchaseDoc", purchaseDoc);
			List<Attachment> attachmentList = attachmentService
					.getAttachmentsByRealID(purchaseDoc.getAttachRelaId());
			if (null != attachmentList && !attachmentList.isEmpty()) {
				Attachment attachment = attachmentList.get(0);
				String path = getMessageSource().getMessage(
						"epp.projectAttaPath")
						+ attachment.getPath() + File.separator;
				String filePath = getMessageSource().getMessage(
						"epp.projectAttaPath")
						+ attachment.getPath()
						+ File.separator
						+ attachment.getFileName().replace("/", File.separator);// 换回绝对路径
				try {
					ZipUtils.unZip(filePath, path);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				String[] l = FileUtil.listFiles(path);
				String viewName = "";
				if (l != null) {
					for (int i = 0; i < l.length; i++) {
						String endName = "";
						if (l[i].indexOf(".") != -1) {
							endName = l[i].substring(l[i].indexOf("."));
						}
						if (endName.equals(".doc") || endName.equals(".docx")
								|| endName.equals(".pdf")) {
							viewName = attachment.getViewName().substring(0,
									attachment.getViewName().length() - 4)
									+ endName;
						}
					}
				} else {
					model
							.put(
									"message",
									((FrameMessageResource) FrameBeanFactory
											.getBean(FrameMessageResource.BEAN_NAME))
											.getMessage("srplatform.auth.attachment.file_is_not_exit"));
				}
				model.put("attrName2", viewName);
			}
		}
		model.put("projectId", projectId);
		if (null != purchaseDoc && null != purchaseDoc.getObjId()) {
			model.put("purchaseDoc", purchaseDoc);
			if (PurchaseDocEnum.FORAUDIT.equals(purchaseDoc.getAuditStatus())
					&& CommonEnum.USER_STATUS_FORMAL.equals(purchaseDoc
							.getUseStatus())) {
				returnUrl = "PurchaseDocConfigView";
			}
		}
		return new ModelAndView(returnUrl, model);
	}

	/**
	 * FuncName:toPurchaseDocbuyer Description :供应商跳转采购文件页面 Create Date:
	 * 2010-5-19下午05:40:39 by liuke Modified Date: 2010-5-19下午05:40:39 by liuke
	 * 
	 * @param request
	 * @return ModelAndView
	 * @Exception
	 */
	@RequestMapping(params = "method=toPurchaseDocbuyer")
	public ModelAndView toPurchaseDocbuyer(HttpServletRequest request)
			throws Exception {
		logger.debug("\nes PurchaseDocController||toPurchaseDocbuyer\n");
		String projectId = request.getParameter("projectId");
		User user = AuthenticationHelper.getCurrentUser();
		Map model = new HashMap();
		PurchaseDoc purchaseDoc = purchaseDocService
				.getPurchaseDocByProjectId(projectId);
		SignUprecord signUprecord = signUprecordService
				.getSignUprecordBySupplierId(projectId, user);// 查询供应商的报名信息
		if (null != purchaseDoc
				&& CommonEnum.USER_STATUS_FORMAL.equals(purchaseDoc
						.getUseStatus())) {
			model.put("purchaseDoc", purchaseDoc);
			List<Attachment> attachmentList = attachmentService
					.getAttachmentsByRealID(purchaseDoc.getAttachRelaId());
			if (null != attachmentList && !attachmentList.isEmpty()) {
				Attachment attachment = attachmentList.get(0);
				String path = getMessageSource().getMessage(
						"epp.projectAttaPath")
						+ attachment.getPath() + File.separator;
				String filePath = getMessageSource().getMessage(
						"epp.projectAttaPath")
						+ attachment.getPath()
						+ File.separator
						+ attachment.getFileName().replace("/", File.separator);// 换回绝对路径
				try {
					ZipUtils.unZip(filePath, path);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				String[] l = FileUtil.listFiles(path);
				String viewName = "";
				if (l != null) {
					for (int i = 0; i < l.length; i++) {
						String endName = "";
						if (l[i].indexOf(".") != -1) {
							endName = l[i].substring(l[i].indexOf("."));
						}
						if (endName.equals(".doc") || endName.equals(".docx")
								|| endName.equals(".pdf")) {
							viewName = attachment.getViewName().substring(0,
									attachment.getViewName().length() - 4)
									+ endName;
						}
					}
				} else {
					model
							.put(
									"message",
									((FrameMessageResource) FrameBeanFactory
											.getBean(FrameMessageResource.BEAN_NAME))
											.getMessage("srplatform.auth.attachment.file_is_not_exit"));
				}
				model.put("attrName2", viewName);
			}
		}
		String returnUrl = "";
		if (null != purchaseDoc && null != purchaseDoc.getObjId()) {// 若有文件，则跳到文件显示页面，若无文件，则跳到空白页面
			if (purchaseDoc.getAuditStatus() != null
					&& PurchaseDocEnum.PURCHASEOFFICEPASS.equals(purchaseDoc
							.getAuditStatus())
					&& signUprecord != null
					&& signUprecord.getObjId() != null
					&& AuditStatusEnum.AUDIT_PASS.equals(signUprecord
							.getAuditStatus())) {// 审核通过
				model.put("purchaseDoc", purchaseDoc);
				returnUrl = "purchaseDocDetailView";
			} else {// 审核没通过
				returnUrl = "blankView";
			}
		} else {
			returnUrl = "blankView";
		}
		model.put("resault", "supplier");
		return new ModelAndView(returnUrl, model);
	}

	/**
	 * FuncName:savePurchaseDoc Description : 保存采购文件 Create Date:
	 * 2010-5-25下午02:34:40 by liuke Modified Date: 2010-5-25下午02:34:40 by liuke
	 * 
	 * @param request
	 *            ,purchaseDoc:招标文件,stauts
	 * @return ModelAndView
	 * @Exception
	 */
	@RequestMapping(params = "method=savePurchaseDoc")
	public ModelAndView savePurchaseDoc(HttpServletRequest request,
			PurchaseDoc purchaseDoc, SessionStatus stauts) throws Exception {
		logger.debug("\nes PurchaseDocController||savePurchaseDoc\n");
		if (null == purchaseDoc.getObjId()) {// 如果第一次创建采购文件
			purchaseDoc.setFileType(PurchaseDocEnum.PURCHASEDOC);
		}
		Boolean isUploadFile = new Boolean(request.getParameter("isUploadFile"));// 判断是否上传附件
		if (isUploadFile) {
			Object o = AttachmentUtil.uploadFile(request, "attachFile");
			if (o instanceof GpcBaseObject) {
				Attachment attachment = (Attachment) o;
				attachmentService.saveAttachment((Attachment) o);
				purchaseDoc.setAttachRelaId(attachment.getRelationId());
			}
		}
		purchaseDoc.setBizDoType("CreatePurchaseDoc");
		
		ProjProcessRule projProcessRule= projProcessRuleService.getProjProcessRuleByProjectIdAndCode(purchaseDoc.getProject().getObjId(),SysConfigEnum.NEEDBUYERACKPURCHASEDOC);
		if (projProcessRule!=null&&"false".equals(projProcessRule.getResValue())) {//业务规则不需要采购人确认招标文件
			purchaseDoc.setAuditStatus(PurchaseDocEnum.PURCHASEOFFICE_WAIT);//采购办审核
			purchaseDoc.setUseStatus(AuditStatusEnum.AUDIT_PASS);//有效状态
		}else{
			purchaseDoc.setAuditStatus(PurchaseDocEnum.FORAUDIT);//00待采购人确认   01 采购人确认通过  02  采购办审核通过 03 监察局审核通过
		}
		purchaseDocService.savePurchaseDoc(purchaseDoc);
		stauts.setComplete();
		return new ModelAndView(Constants.JSON_VIEW);
	}

	/**
	 * FuncName:updatePurchaseDoc Description:修改采购文件 Create Date:
	 * 2010-9-6下午02:46:37 by yangx Modified Date: 2010-9-6下午02:46:37 by yangx
	 * 
	 * @param request
	 *            ,purchaseDoc:招标文件,stauts
	 * @return ModelAndView
	 * @Exception
	 */
	@RequestMapping(params = "method=updatePurchaseDoc")
	public ModelAndView updatePurchaseDoc(HttpServletRequest request,
			PurchaseDoc purchaseDoc, SessionStatus stauts) throws Exception {
		logger.debug("\nes PurchaseDocController||updatePurchaseDoc\n");
		if (null == purchaseDoc.getObjId()) {// 如果第一次创建采购文件
			purchaseDoc.setFileType(PurchaseDocEnum.PURCHASEDOC);
		}
		Boolean isUploadFile = new Boolean(request.getParameter("isUploadFile"));// 判断是否上传附件
		if (isUploadFile) {
			Object o = AttachmentUtil.uploadFile(request, "attachFile");
			if (o instanceof GpcBaseObject) {
				Attachment attachment = (Attachment) o;
				attachmentService.saveAttachment((Attachment) o);
				purchaseDoc.setAttachRelaId(attachment.getRelationId());
			}
		}
		purchaseDoc.setBizDoType("CreatePurchaseDoc");
		// purchaseDocService.updatePurchaseDoc(purchaseDoc);
		
		ProjProcessRule projProcessRule= projProcessRuleService.getProjProcessRuleByProjectIdAndCode(purchaseDoc.getProject().getObjId(),SysConfigEnum.NEEDBUYERACKPURCHASEDOC);
		if (projProcessRule!=null&&"false".equals(projProcessRule.getResValue())) {//业务规则不需要采购人确认招标文件
			purchaseDoc.setAuditStatus(PurchaseDocEnum.PURCHASEOFFICE_WAIT);//采购办审核
			purchaseDoc.setUseStatus(AuditStatusEnum.AUDIT_PASS);//有效状态
		}else{
			purchaseDoc.setAuditStatus(PurchaseDocEnum.FORAUDIT);//00待采购人确认   01 采购人确认通过  02  采购办审核通过 03 监察局审核通过
		}
		
		purchaseDocService.savePurchaseDoc(purchaseDoc); // modify by yangx
		stauts.setComplete();
		Map model = new HashMap();
		return new ModelAndView(Constants.JSON_VIEW, model);
	}

	/**
	 * FuncName:getPurchaseDocByUser Description:[采购办]得到待审核采购文件列表 Create Date:
	 * 2010-6-21下午01:36:42 by liuke Modified Date: 2010-6-21下午01:36:42 by liuke
	 * 
	 * @param request
	 * @return ModelAndView
	 * @Exception
	 */
	@RequestMapping(params = "method=getPurchaseDocByUser")
	public ModelAndView getPurchaseDocByUser(HttpServletRequest request)
			throws Exception {
		logger.debug("\nes PurchaseDocController||getPurchaseDocByUser\n");
		Page<PurchaseDoc> page = prePage(request);
		Map<String, Object> model = new HashMap<String, Object>();
		User user = AuthenticationHelper.getCurrentUser();
		QueryObject queryObject = QueryWebUtils.getQuery(request,
				PurchaseDoc.class);
		Page<PurchaseDoc> pageData = purchaseDocService.getPurchaseDocByUser(
				page, queryObject, user, PurchaseDocEnum.PURCHASEDOC);
		endPage(model, pageData, request);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}

	/**
	 * FuncName:getPurchaseDocByBuyer Description :[采购人]得到待确认采购文件列表 Create Date:
	 * 2010-7-6下午05:34:00 by liuke Modified Date: 2010-7-6下午05:34:00 by liuke
	 * 
	 * @param request
	 * @return ModelAndView
	 * @Exception
	 */
	@RequestMapping(params = "method=getPurchaseDocByBuyer")
	public ModelAndView getPurchaseDocByBuyer(HttpServletRequest request)
			throws Exception {
		logger.debug("\nes PurchaseDocController||getPurchaseDocByBuyer\n");
		Page<PurchaseDoc> page = prePage(request);
		Map<String, Object> model = new HashMap<String, Object>();
		User user = AuthenticationHelper.getCurrentUser();
		Page<PurchaseDoc> pageData = purchaseDocService.getPurchaseDocByBuyer(
				page, user, PurchaseDocEnum.PURCHASEDOC);
		endPage(model, pageData, request);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}

	/**
	 * Funcname:getMorePurchaseDocRecordList Description : 跳转到采购文件信息列表：根据状态
	 * Create Date: 2010-7-12下午05:25:36 by liuke Modified Date:
	 * 2010-7-12下午05:25:36 by liuke
	 * 
	 * @param request
	 * @return
	 * @Exception
	 */
	@RequestMapping(params = "method=getMorePurchaseDocRecordList")
	public ModelAndView getMorePurchaseDocRecordList(HttpServletRequest request)
			throws Exception {
		logger
				.debug("\nes PurchaseDocController||getMorePurchaseDocRecordList\n");
		String auditStatus = request.getParameter("auditStatus");
		String fileType = request.getParameter("fileType");
		String useStatus = request.getParameter("useStatus");
		String title = "";
		if (fileType == "07") {
			title = "招标文件";
		} else if (fileType == "08") {
			title = "澄清文件";
		}
		Map model = new HashMap();
		model.put("auditStatus", auditStatus);
		model.put("fileType", fileType);
		model.put("useStatus", useStatus);
		return new ModelAndView("getMorePurchaseDocRecordList", model);
	}

	/**
	 * Funcname:searchPurchaseDocByQueryObject Description : 获取采购文件信息列表 Create
	 * Date: 2010-6-23下午03:15:50 by liuke Modified Date: 2010-6-23下午03:15:50 by
	 * liuke
	 * 
	 * @param request
	 *            ,status
	 * @return ModelAndView
	 * @Exception
	 */
	@RequestMapping(params = "method=searchPurchaseDocByQueryObject")
	public ModelAndView searchPurchaseDocByQueryObject(
			HttpServletRequest request, SessionStatus status) throws Exception {
		logger.debug("\nes PurchaseDocController||searchPurchaseDocByQueryObject\n");
		String useStatus = request.getParameter("useStatus");
		String fileType = request.getParameter("fileType");
		String auditStatus = request.getParameter("auditStatus");
		String projCode = request.getParameter("projCode");
		String projName = request.getParameter("projName");
		String userType = request.getParameter("userType");// 用户类型{桌面代办列表}
		String ebuyMethod = request.getParameter("ebuyMethod");// 采购方式
		User user = AuthenticationHelper.getCurrentUser();
		if (null == userType || "NULL".equals(userType.toUpperCase())
				|| "undefined".equals(userType)) {
			userType = "";
		}
		Page page = prePage(request);
		QueryObject<SignUprecord> queryObject = new QueryObjectBase<SignUprecord>();
		queryObject.getQueryParam().and(
				new QueryParam("auditStatus", QueryParam.OPERATOR_EQ,auditStatus));
		queryObject.getQueryParam().and(
				new QueryParam("projCode", QueryParam.OPERATOR_EQ, projCode));
		queryObject.getQueryParam().and(
				new QueryParam("projName", QueryParam.OPERATOR_EQ, projName));
		queryObject.getQueryParam().and(
				new QueryParam("fileType", QueryParam.OPERATOR_EQ, fileType));
		queryObject.getQueryParam().and(
				new QueryParam("useStatus", QueryParam.OPERATOR_EQ, useStatus));
		queryObject.getQueryParam().and(
				new QueryParam("ebuyMethod", QueryParam.OPERATOR_EQ,ebuyMethod));
		if(PurchaseDocEnum.PURCHASEOFFICE_WAIT.equals(auditStatus)) {
			queryObject.getQueryParam().and(
				new QueryParam("monitorId", QueryParam.OPERATOR_EQ, user.getEmp().getObjId()));
		}
		if (PurchaseDocEnum.RETURNBACK.equals(auditStatus)) {//待经办人修改时。
			queryObject.getQueryParam().and(//招标文件创建人
					new QueryParam("creater", QueryParam.OPERATOR_EQ, user.getObjId()));
		}
		if (PurchaseDocEnum.FORAUDIT.equals(auditStatus)) { // 待采购人确认时，要根据采购人进行过滤
			OrgInfo buyer = (OrgInfo) user.getOrgInfo();
			queryObject.getQueryParam().and(
					new QueryParam("buyerId", QueryParam.OPERATOR_EQ, buyer.getObjId()));
		}
		Page<PurchaseDoc> pageData = purchaseDocService
				.searchPurchaseDocsByQueryObject(page, queryObject);
		Map model = new HashMap();
		endPage(model, pageData, request);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}

	/**
	 * Funcname:saveProofOpinion Description : 增加招标文件论证结论 Create Date:
	 * 2010-8-6下午01:16:58 by liuke Modified Date: 2010-8-6下午01:16:58 by liuke
	 * 
	 * @param request
	 *            ,purchaseDoc:招标文件,stauts
	 * @return
	 * @Exception
	 */
	@RequestMapping(params = "method=saveProofOpinion")
	public ModelAndView saveProofOpinion(HttpServletRequest request,
			PurchaseDoc purchaseDoc, SessionStatus stauts) throws Exception {
		logger.debug("\nes PurchaseDocController||saveProofOpinion\n");
		String objId = purchaseDoc.getObjId();
		String proofOpinion = purchaseDoc.getProofOpinion();
		PurchaseDoc purchaseDocs = purchaseDocService.get(objId);
		purchaseDocs.setProofOpinion(proofOpinion);
		purchaseDocService.saveProofOpinion(purchaseDocs);
		stauts.setComplete();
		Map<String, Object> model = new HashMap<String, Object>();
		return new ModelAndView(Constants.JSON_VIEW, model);
	}

	/**
	 * Funcname:saveConfirmPruchaseDocAudit Description :采购人：确认采购文件 Create Date:
	 * 2010-8-23下午05:19:46 by liuke Modified Date: 2010-8-23下午05:19:46 by liuke
	 * 
	 * @param objId
	 *            ,ids,opinion,auditStatus,workFlowTaskId,request
	 * @return ModelAndView
	 * @Exception
	 */
	@RequestMapping(params = "method=saveConfirmPruchaseDocAudit")
	public ModelAndView saveConfirmPruchaseDocAudit(String objId, String ids,
			String opinion, String auditStatus, String workFlowTaskId,
			HttpServletRequest request) throws Exception {
		logger
				.debug("\nes PurchaseDocController||saveConfirmPruchaseDocAudit\n");
		if (ids == null || ids.equals("")) {
			ids = objId;
		}
		purchaseDocService.saveConfirmPurchaseDoc(ids, opinion, auditStatus,
				workFlowTaskId);
		return new ModelAndView(Constants.JSON_VIEW);
	}

	/**
	 * Funcname:savePruchaseDocAudit Description :审核采购文件（重构以前多余的审核方法） Create
	 * Date: 2010-8-23下午05:19:46 by liuke Modified Date: 2010-8-23下午05:19:46 by
	 * liuke
	 * 
	 * @param objId
	 *            ,ids,opinion,auditStatus,workFlowTaskId,request
	 * @return
	 * @Exception
	 */
	@RequestMapping(params = "method=savePruchaseDocAudit")
	public ModelAndView savePruchaseDocAudit(String objId, String ids,
			String opinion, String auditStatus, String workFlowTaskId,
			HttpServletRequest request) throws Exception {
		logger.debug("\nes=PurchaseDocController||savePruchaseDocAudit\n");
		if (ids == null || ids.equals("")) {
			ids = objId;
		}
		User auditor = AuthenticationHelper.getCurrentUser();
		purchaseDocService.auditPurchaseDoc(ids,auditor, opinion, auditStatus,
				workFlowTaskId);
		return new ModelAndView(Constants.JSON_VIEW);
	}

	/**
	 * Funcname:savePruchaseDocAuditForJCJ Description :监察局审核采购文件[定制] Create
	 * Date: 2010-8-23下午05:19:46 by liuke Modified Date: 2010-8-23下午05:19:46 by
	 * liuke
	 * 
	 * @param objId
	 *            ,ids,opinion,auditStatus,workFlowTaskId,request
	 * @return ModelAndView
	 * @Exception
	 */
	@RequestMapping(params = "method=savePruchaseDocAuditForJCJ")
	public ModelAndView savePruchaseDocAuditForJCJ(String objId, String ids,
			String opinion, String auditStatus, String workFlowTaskId,
			HttpServletRequest request) throws Exception {
		logger
				.debug("\nes PurchaseDocController||savePruchaseDocAuditForJCJ\n");
		if (ids == null || ids.equals("")) {
			ids = objId;
		}
		purchaseDocService.saveForJcjAudit(ids, opinion, auditStatus,
				workFlowTaskId);
		return new ModelAndView(Constants.JSON_VIEW);
	}

	/**
	 * Funcname:saveClarificationDocAuditForJCJ Description :监察局审核澄清文件[定制]
	 * Create Date: 2010-8-23下午05:19:46 by liuke Modified Date:
	 * 2010-8-23下午05:19:46 by liuke
	 * 
	 * @param objId
	 *            ,ids,opinion,auditStatus,workFlowTaskId,request
	 * @return ModelAndView
	 * @Exception
	 */
	@RequestMapping(params = "method=saveClarificationDocAuditForJCJ")
	public ModelAndView saveClarificationDocAuditForJCJ(String objId,
			String ids, String opinion, String auditStatus,
			String workFlowTaskId, HttpServletRequest request) throws Exception {
		logger
				.debug("\nes PurchaseDocController||saveClarificationDocAuditForJCJ\n");
		if (ids == null || ids.equals("")) {
			ids = objId;
		}
		purchaseDocService.saveClarificationForJcjAudit(ids, opinion,
				auditStatus, workFlowTaskId);
		return new ModelAndView(Constants.JSON_VIEW);
	}

	/**
	 * Funcname:toPurchaseDocAuditingForJCJ Description : 监察局转到审核采购文件页面 Create
	 * Date: 2010-5-27上午11:35:18 by liuke Modified Date: 2010-5-27上午11:35:18 by
	 * liuke
	 * 
	 * @param request
	 * @return ModelAndView
	 * @Exception
	 */
	@RequestMapping(params = "method=toPurchaseDocAuditingForJCJ")
	public ModelAndView toPurchaseDocAuditingForJCJ(HttpServletRequest request)
			throws Exception {
		logger
				.debug("\nes PurchaseDocController||toPurchaseDocAuditingForJCJ\n");
		String projectId = request.getParameter("projectId");
		String purchaseDocId = request.getParameter("objId");
		PurchaseDoc purchaseDoc = purchaseDocService
				.getPurchaseDocByProjectId(projectId);
		Map model = new HashMap();
		if (null == projectId || "".equals(projectId)) {
			purchaseDoc = purchaseDocService
					.getPurchaseDocByObjId(purchaseDocId);
			projectId = purchaseDoc.getProject().getObjId();
		}
		if (null != purchaseDoc) {
			List<Attachment> attachmentList = attachmentService
					.getAttachmentsByRealID(purchaseDoc.getAttachRelaId());
			if (null != attachmentList && !attachmentList.isEmpty()) {
				model.put("attrId", attachmentList.get(0).getObjId());
				model.put("attrName", attachmentList.get(0).getViewName());
			}
		}
		String returnUrl = "purchaseDocDetailView";
		model.put("projectId", projectId);
		if (null != purchaseDoc && null != purchaseDoc.getObjId()) {
			model.put("purchaseDoc", purchaseDoc);
			if (null != purchaseDoc.getAuditStatus()
					&& purchaseDoc.getAuditStatus().equals(
							PurchaseDocEnum.SUPERVISALOFFICE_WAIT)) {
				returnUrl = "purchaseDocAuditingViewJCJ";
			}
		}
		return new ModelAndView(returnUrl, model);
	}

	/**
	 * Funcname:toClarificationDoc Description : 代理机构跳转澄清文件页面 Create Date:
	 * 2010-9-2下午01:15:53 by shenjianzhuang Modified Date: 2010-9-2下午01:15:53 by
	 * shenjianzhuang
	 * 
	 * @param request
	 * @return ModelAndView
	 * @Exception
	 */
	@RequestMapping(params = "method=toClarificationDoc")
	public ModelAndView toClarificationDoc(HttpServletRequest request)
			throws Exception {
		logger.debug("\nes PurchaseDocController||toClarificationDoc\n");
		String projectId = request.getParameter("projectId");
		String purchaseDocId = request.getParameter("objId");
		String fromType = request.getParameter("fromType");
		PurchaseDoc purchaseDoc = purchaseDocService
				.getPurchaseDocByProjectIdAndFileType(projectId,
						PurchaseDocEnum.CLARIFICATIONDOC);// 通过项目Id和文件类型获取文件
		if (null == projectId || "".equals(projectId)) {
			purchaseDoc = purchaseDocService.get(purchaseDocId);
			projectId = purchaseDoc.getProject().getObjId();
		}
		Map model = new HashMap();
		model.put("projectId", projectId);
		model.put("purchaseDoc", purchaseDoc);
		model.put("fromType", fromType);
		String returnName = "clarificationDocDetailView";
		if (null != purchaseDoc && null != purchaseDoc.getObjId()) {// 若已上传澄清文件，则跳到显示页面，若没有，则跳到新增页面
			if (PurchaseDocEnum.RETURNBACK.equals(purchaseDoc.getAuditStatus())) { // 表示该文件被退回
				returnName = "updateClarificationDocFormView";
			}
		} else {
			returnName = "clarificationDocFormView";
		}
		return new ModelAndView(returnName, model);
	}

	/**
	 * Funcname:saveClarificationDoc Description : 保存澄清文件 Create Date:
	 * 2010-9-2下午01:15:53 by shenjianzhuang Modified Date: 2010-9-2下午01:15:53 by
	 * shenjianzhuang
	 * 
	 * @param request
	 *            ,purchaseDoc:澄清文件,stauts
	 * @return ModelAndView
	 * @Exception
	 */
	@RequestMapping(params = "method=saveClarificationDoc")
	public ModelAndView saveClarificationDoc(HttpServletRequest request,
			PurchaseDoc purchaseDoc, SessionStatus stauts) throws Exception {
		logger.debug("\nes PurchaseDocController||saveClarificationDoc\n");
		if (null == purchaseDoc.getObjId()) {// 如果第一次创建澄清文件
			purchaseDoc.setFileType(PurchaseDocEnum.CLARIFICATIONDOC);
		}
		Boolean isUploadFile = new Boolean(request.getParameter("isUploadFile"));// 判断是否上传附件
		if (isUploadFile) {
			Object o = AttachmentUtil.uploadFile(request, "attachFile");
			if (o instanceof GpcBaseObject) {
				Attachment attachment = (Attachment) o;
				attachmentService.saveAttachment((Attachment) o);
				purchaseDoc.setAttachRelaId(attachment.getRelationId());
			}
		}
		purchaseDocService.saveClarificationDoc(purchaseDoc);
		stauts.setComplete();
		Map model = new HashMap();
		return new ModelAndView(Constants.JSON_VIEW, model);
	}

	/**
	 * Funcname:updateSaveClarificationDoc Description : 修改：保存澄清文件 Create Date:
	 * 2010-9-2下午01:15:53 by shenjianzhuang Modified Date: 2010-9-2下午01:15:53 by
	 * shenjianzhuang
	 * 
	 * @param request
	 *            ,purchaseDoc:澄清文件,stauts
	 * @return ModelAndView
	 * @Exception
	 */
	@RequestMapping(params = "method=updateSaveClarificationDoc")
	public ModelAndView updateSaveClarificationDoc(HttpServletRequest request,
			PurchaseDoc purchaseDoc, SessionStatus stauts) throws Exception {
		logger
				.debug("\nes PurchaseDocController||updateSaveClarificationDoc\n");
		if (null == purchaseDoc.getObjId()) {// 如果第一次创建澄清文件
			purchaseDoc.setFileType(PurchaseDocEnum.CLARIFICATIONDOC);
		}
		Boolean isUploadFile = new Boolean(request.getParameter("isUploadFile"));// 判断是否上传附件
		if (isUploadFile) {
			Object o = AttachmentUtil.uploadFile(request, "attachFile");
			if (o instanceof GpcBaseObject) {
				Attachment attachment = (Attachment) o;
				attachmentService.saveAttachment((Attachment) o);
				purchaseDoc.setAttachRelaId(attachment.getRelationId());
			}
		}
		if (purchaseDoc.getAuditStatus().equals(CommonEnum.CONFIRM_STATUS_BACK)) {
			purchaseDoc.setUseStatus(CommonEnum.CONFIRM_STATUS_NEGOTIATE);
		}
		purchaseDocService.updateSaveClarificationDoc(purchaseDoc);
		stauts.setComplete();
		Map model = new HashMap();
		return new ModelAndView(Constants.JSON_VIEW, model);
	}

	/**
	 * Funcname:submitClarificationDoc Description : 提交澄清文件 Create Date:
	 * 2010-9-6下午03:57:27 by yangx Modified Date: 2010-9-6下午03:57:27 by yangx
	 * 
	 * @param request
	 *            ,purchaseDoc:澄清文件,stauts
	 * @return ModelAndView
	 * @Exception
	 */
	@RequestMapping(params = "method=submitClarificationDoc")
	public ModelAndView submitClarificationDoc(HttpServletRequest request,
			PurchaseDoc purchaseDoc, SessionStatus stauts) throws Exception {
		logger.debug("\nes PurchaseDocController||submitClarificationDoc\n");
		if (null == purchaseDoc.getObjId()) {// 如果第一次创建澄清文件
			purchaseDoc.setFileType(PurchaseDocEnum.CLARIFICATIONDOC);
		}
		Boolean isUploadFile = new Boolean(request.getParameter("isUploadFile"));// 判断是否上传附件
		if (isUploadFile) {
			Object o = AttachmentUtil.uploadFile(request, "attachFile");
			if (o instanceof GpcBaseObject) {
				Attachment attachment = (Attachment) o;
				attachmentService.saveAttachment((Attachment) o);
				purchaseDoc.setAttachRelaId(attachment.getRelationId());
			}
		}
		purchaseDocService.saveSubmitClarificationDoc(purchaseDoc);
		stauts.setComplete();
		Map model = new HashMap();
		return new ModelAndView(Constants.JSON_VIEW, model);
	}

	/**
	 * Funcname:updateSubmitClarificationDoc Description : 修改：提交澄清文件 Create
	 * Date: 2010-9-6下午03:57:27 by yangx Modified Date: 2010-9-6下午03:57:27 by
	 * yangx
	 * 
	 * @param request
	 *            ,purchaseDoc:澄清文件,stauts
	 * @return ModelAndView
	 * @Exception
	 */
	@RequestMapping(params = "method=updateSubmitClarificationDoc")
	public ModelAndView updateSubmitClarificationDoc(
			HttpServletRequest request, PurchaseDoc purchaseDoc,
			SessionStatus stauts) throws Exception {
		logger
				.debug("\nes PurchaseDocController||updateSubmitClarificationDoc\n");
		if (null == purchaseDoc.getObjId()) {// 如果第一次创建澄清文件
			purchaseDoc.setFileType(PurchaseDocEnum.CLARIFICATIONDOC);
		}
		Boolean isUploadFile = new Boolean(request.getParameter("isUploadFile"));// 判断是否上传附件
		if (isUploadFile) {
			Object o = AttachmentUtil.uploadFile(request, "attachFile");
			if (o instanceof GpcBaseObject) {
				Attachment attachment = (Attachment) o;
				attachmentService.saveAttachment((Attachment) o);
				purchaseDoc.setAttachRelaId(attachment.getRelationId());
			}
		}
		purchaseDocService.updateSubmitClarificationDoc(purchaseDoc);
		stauts.setComplete();
		Map model = new HashMap();
		return new ModelAndView(Constants.JSON_VIEW, model);
	}

	/**
	 * Funcname:toClarificationDocPageForBuyer Description : 采购人跳转到确认澄清文件页面
	 * Create Date: 2010-9-7上午11:00:42 by yangx Modified Date:
	 * 2010-9-7上午11:00:42 by yangx
	 * 
	 * @param request
	 * @return ModelAndView
	 * @Exception
	 */
	@RequestMapping(params = "method=toClarificationDocPageForBuyer")
	public ModelAndView toClarificationDocPageForBuyer(
			HttpServletRequest request) throws Exception {
		logger
				.debug("\nes PurchaseDocController||toClarificationDocPageForBuyer\n");
		String projectId = request.getParameter("projectId");
		String purchaseDocId = request.getParameter("objId");
		String fromType = request.getParameter("fromType");
		Map model = new HashMap();
		PurchaseDoc purchaseDoc = purchaseDocService
				.getPurchaseDocByObjId(purchaseDocId);
		projectId = purchaseDoc.getProject().getObjId();
		if (null != purchaseDoc) {
			List<Attachment> attachmentList = attachmentService
					.getAttachmentsByRealID(purchaseDoc.getAttachRelaId());
			if (null != attachmentList && !attachmentList.isEmpty()) {
				model.put("attrId", attachmentList.get(0).getObjId());
				model.put("attrName", attachmentList.get(0).getViewName());
			}
		}
		model.put("projectId", projectId);
		model.put("purchaseDoc", purchaseDoc);
		model.put("fromType", fromType);
		String returnName = "clarificationDocDetailView";// 采购文件查看详情页面
		if (null != purchaseDoc && null != purchaseDoc.getObjId()) {
			if (null != purchaseDoc.getAuditStatus()
					&& PurchaseDocEnum.FORAUDIT.equals(purchaseDoc
							.getAuditStatus())) {
				returnName = "clarificationDocConfigView"; // 跳转到采购人确认页面
			}
		}
		return new ModelAndView(returnName, model);
	}

	/**
	 * Funcname:toClarificationDocPageForJCJ Description : 监察局跳转到审核澄清文件页面 Create
	 * Date: 2010-9-7上午11:00:42 by yangx Modified Date: 2010-9-7上午11:00:42 by
	 * yangx
	 * 
	 * @param request
	 * @return ModelAndView
	 * @Exception
	 */
	@RequestMapping(params = "method=toClarificationDocPageForJCJ")
	public ModelAndView toClarificationDocPageForJCJ(HttpServletRequest request)
			throws Exception {
		logger
				.debug("\nes PurchaseDocController||toClarificationDocPageForJCJ\n");
		String projectId = request.getParameter("projectId");
		String purchaseDocId = request.getParameter("objId");
		String fromType = request.getParameter("fromType");
		Map model = new HashMap();
		PurchaseDoc purchaseDoc = purchaseDocService
				.getPurchaseDocByProjectIdAndFileType(projectId,
						PurchaseDocEnum.CLARIFICATIONDOC);// 通过项目id和文件类型获取文件
		if (null == projectId || "".equals(projectId)) {
			purchaseDoc = purchaseDocService
					.getPurchaseDocByObjId(purchaseDocId);
			projectId = purchaseDoc.getProject().getObjId();
		}
		if (null != purchaseDoc) {
			List<Attachment> attachmentList = attachmentService
					.getAttachmentsByRealID(purchaseDoc.getAttachRelaId());
			if (null != attachmentList && !attachmentList.isEmpty()) {
				model.put("attrId", attachmentList.get(0).getObjId());
				model.put("attrName", attachmentList.get(0).getViewName());
			}
		}
		model.put("projectId", projectId);
		model.put("purchaseDoc", purchaseDoc);
		model.put("fromType", fromType);
		String returnName = "clarificationDocDetailView";// 采购文件查看详情页面
		if (null != purchaseDoc && null != purchaseDoc.getObjId()) {
			if (null != purchaseDoc.getAuditStatus()
					&& PurchaseDocEnum.SUPERVISALOFFICE_WAIT.equals(purchaseDoc
							.getAuditStatus())) {
				returnName = "clarificationDocAuditingView";
			}
		}
		return new ModelAndView(returnName, model);
	}

	/**
	 * Funcname:toClarificationDocPage Description :跳转到澄清文件页面 Create Date:
	 * 2010-9-2下午01:15:53 by shenjianzhuang Modified Date: 2010-9-2下午01:15:53 by
	 * shenjianzhuang
	 * 
	 * @param request
	 * @return ModelAndView
	 * @Exception
	 */
	@RequestMapping(params = "method=toClarificationDocPage")
	public ModelAndView toClarificationDocPage(HttpServletRequest request)
			throws Exception {
		logger.debug("\nes PurchaseDocController||toClarificationDocPage\n");
		String projectId = request.getParameter("projectId");
		String purchaseDocId = request.getParameter("objId");
		String fromType = request.getParameter("fromType");
		PurchaseDoc purchaseDoc = purchaseDocService
				.getPurchaseDocByProjectIdAndFileType(projectId,
						PurchaseDocEnum.CLARIFICATIONDOC);// 通过项目id和文件类型获取文件
		if (null == projectId || "".equals(projectId)) {
			purchaseDoc = purchaseDocService.get(purchaseDocId);
			projectId = purchaseDoc.getProject().getObjId();
		}
		User user = AuthenticationHelper.getCurrentUser();// 以下是根据角色的不同跳转不同的方法
		OrgInfo orgInfo = (OrgInfo) user.getOrgInfo();
		Map model = new HashMap();
		model.put("projectId", projectId);
		model.put("purchaseDoc", purchaseDoc);
		model.put("fromType", fromType);
		String returnName = "clarificationDocDetailView";// 采购文件查看详情页面
		if (orgInfo.getSupplierId() != null) {// 当前用户是供应商
			if (null != purchaseDoc && null != purchaseDoc.getObjId()) {
				if (purchaseDoc.getAuditStatus() == null
						|| !PurchaseDocEnum.PURCHASEOFFICEPASS
								.equals(purchaseDoc.getAuditStatus())) {
					returnName = "blankView";
				}
			} else {
				returnName = "blankView";
			}
		}
		return new ModelAndView(returnName, model);
	}

	/**
	 * Funcname:saveConfirmClarificationDocAudit Description :采购人：确认澄清文件 Create
	 * Date: 2010-8-23下午05:19:46 by liuke Modified Date: 2010-8-23下午05:19:46 by
	 * liuke
	 * 
	 * @param objId
	 *            ,ids,opinion,auditStatus,workFlowTaskId,request
	 * @return ModelAndView
	 * @Exception
	 */
	@RequestMapping(params = "method=saveConfirmClarificationDocAudit")
	public ModelAndView saveConfirmClarificationDocAudit(String objId,
			String ids, String opinion, String auditStatus,
			String workFlowTaskId, HttpServletRequest request) throws Exception {
		logger
				.debug("\nes PurchaseDocController||saveConfirmClarificationDocAudit\n");
		if (ids == null || ids.equals("")) {
			ids = objId;
		}
		purchaseDocService.saveConfirmClarification(ids, opinion, auditStatus,
				workFlowTaskId);
		return new ModelAndView(Constants.JSON_VIEW);
	}

	/**
	 * Funcname:saveClarificationDocAudit Description :审核澄清文件 Create Date:
	 * 2010-9-2下午01:15:53 by shenjianzhuang Modified Date: 2010-9-2下午16:15:53 by
	 * shenjianzhuang
	 * 
	 * @param objId
	 *            ,ids,opinion,auditStatus,workFlowTaskId,request
	 * @return ModelAndView
	 * @Exception
	 */
	@RequestMapping(params = "method=saveClarificationDocAudit")
	public ModelAndView saveClarificationDocAudit(String objId, String ids,
			String opinion, String auditStatus, String workFlowTaskId,
			HttpServletRequest request) throws Exception {
		logger.debug("\nes PurchaseDocController||saveClarificationDocAudit\n");
		if (ids == null || ids.equals("")) {
			ids = objId;
		}
		purchaseDocService.auditClarificationDoc(ids, opinion, auditStatus,
				workFlowTaskId);
		return new ModelAndView(Constants.JSON_VIEW);
	}

	/**
	 * Funcname:toAddClarificationDoc Description : 代理机构：澄清文件新增页面 Create Date:
	 * 2010-9-2下午01:15:53 by shenjianzhuang Modified Date: 2010-9-2下午01:15:53 by
	 * shenjianzhuang
	 * 
	 * @param request
	 * @return ModelAndView
	 * @Exception
	 */
	@RequestMapping(params = "method=toAddClarificationDoc")
	public ModelAndView toAddClarificationDoc(HttpServletRequest request)
			throws Exception {
		logger.debug("\nes PurchaseDocController||toAddClarificationDoc\n");
		String projectId = request.getParameter("projectId");
		PurchaseDoc purchasedoc = purchaseDocService
				.getPurchaseDocByProjectIdAndFileType(projectId,
						PurchaseDocEnum.PURCHASEDOC);
		Map model = new HashMap();
		model.put("projectId", projectId);
		model.put("purchasedocId", purchasedoc.getObjId());
		return new ModelAndView("clarificationDocFormView", model);
	}

	/**
	 * Funcname:toUpdateClarificationDoc Description : 代理机构：澄清文件修改页面 Create
	 * Date: 2010-9-6下午01:36:40 by yangx Modified Date: 2010-9-6下午01:36:40 by
	 * yangx
	 * 
	 * @param request
	 * @return ModelAndView
	 * @Exception
	 */
	@RequestMapping(params = "method=toUpdateClarificationDoc")
	public ModelAndView toUpdateClarificationDoc(HttpServletRequest request)
			throws Exception {
		logger.debug("\nes PurchaseDocController||toUpdateClarificationDoc\n");
		Map model = new HashMap();
		PurchaseDoc purchaseDoc = purchaseDocService
				.getPurchaseDocByObjId(request.getParameter("objId"));
		if (null != purchaseDoc) {
			List<Attachment> attachmentList = attachmentService
					.getAttachmentsByRealID(purchaseDoc.getAttachRelaId());
			if (null != attachmentList && !attachmentList.isEmpty()) {
				model.put("attrId", attachmentList.get(0).getObjId());
				model.put("attrName", attachmentList.get(0).getViewName());
			}
		}
		model.put("purchaseDoc", purchaseDoc);
		return new ModelAndView("toModifyClarificationDoc", model);
	}

	/**
	 * Funcname:toViewClarificationDoc Description : 查看更正澄清文件 Create Date:
	 * 2010-9-6下午01:40:25 by yangx Modified Date: 2010-9-6下午01:40:25 by yangx
	 * 
	 * @param request
	 * @return ModelAndView
	 * @Exception
	 */
	@RequestMapping(params = "method=toViewClarificationDoc")
	public ModelAndView toViewClarificationDoc(HttpServletRequest request)
			throws Exception {
		logger.debug("\nes PurchaseDocController||toViewClarificationDoc\n");
		Map model = new HashMap();
		PurchaseDoc purchaseDoc = purchaseDocService
				.getPurchaseDocByObjId(request.getParameter("objId"));
		if (null != purchaseDoc) {
			List<Attachment> attachmentList = attachmentService
					.getAttachmentsByRealID(purchaseDoc.getAttachRelaId());
			if (null != attachmentList && !attachmentList.isEmpty()) {
				model.put("attrId", attachmentList.get(0).getObjId());
				model.put("attrName", attachmentList.get(0).getViewName());
			}
		}
		model.put("purchaseDoc", purchaseDoc);
		return new ModelAndView("clarificationDocDetailView", model);
	}

	/**
	 * Funcname:toClarificationDocForOffice Description :采购办跳转到审核澄清文件页面 Create
	 * Date: 2010-9-2下午01:15:53 by shenjianzhuang Modified Date:
	 * 2010-9-2下午01:15:53 by shenjianzhuang
	 * 
	 * @param request
	 * @return ModelAndView
	 * @Exception
	 */
	@RequestMapping(params = "method=toClarificationDocForOffice")
	public ModelAndView toClarificationDocForOffice(HttpServletRequest request)
			throws Exception {
		logger
				.debug("\nes PurchaseDocController||toClarificationDocForOffice\n");
		Map model = new HashMap();
		PurchaseDoc purchaseDoc = purchaseDocService
				.getPurchaseDocByObjId(request.getParameter("objId"));
		if (null != purchaseDoc) {
			List<Attachment> attachmentList = attachmentService
					.getAttachmentsByRealID(purchaseDoc.getAttachRelaId());
			if (null != attachmentList && !attachmentList.isEmpty()) {
				model.put("attrId", attachmentList.get(0).getObjId());
				model.put("attrName", attachmentList.get(0).getViewName());
			}
		}
		model.put("purchaseDoc", purchaseDoc);
		model.put("projectId", purchaseDoc.getProject().getObjId());
		return new ModelAndView("clarificationProcurementView", model);
	}

	/**
	 * Funcname:toUpdatePurchaseDocPage Description : 代理机构：修改采购文件页面 Create Date:
	 * 2010-6-21下午01:36:42 by lic Modified Date: 2010-6-21下午01:36:42 by lic
	 * 
	 * @param request
	 * @return ModelAndView
	 * @throws Exception
	 * @Exception
	 */
	@RequestMapping(params = "method=toUpdatePurchaseDocPage")
	public ModelAndView toUpdatePurchaseDocPage(HttpServletRequest request)
			throws Exception {
		String purchaseDocId = request.getParameter("objId");
		PurchaseDoc purchaseDoc = purchaseDocService
				.getPurchaseDocByObjId(purchaseDocId);
		Map model = new HashMap();
		if (null != purchaseDoc) {
			List<Attachment> attachmentList = attachmentService
					.getAttachmentsByRealID(purchaseDoc.getAttachRelaId());
			if (null != attachmentList && !attachmentList.isEmpty()) {
				Attachment attachment = attachmentList.get(0);
				model.put("attrId", attachment.getObjId());
				model.put("attrName", attachment.getViewName());
				String path = getMessageSource().getMessage(
						"epp.projectAttaPath")
						+ attachment.getPath() + File.separator;
				String filePath = getMessageSource().getMessage(
						"epp.projectAttaPath")
						+ attachment.getPath()
						+ File.separator
						+ attachment.getFileName().replace("/", File.separator);// 换回绝对路径
				try {
					if (CommonEnum.USER_STATUS_FORMAL.equals(purchaseDoc
							.getUseStatus())) {
						ZipUtils.unZip(filePath, path);
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				String[] l = FileUtil.listFiles(path);
				String viewName = "";
				if (l != null) {
					for (int i = 0; i < l.length; i++) {
						String endName = "";
						if (l[i].indexOf(".") != -1) {
							endName = l[i].substring(l[i].indexOf("."));
						}
						if (endName.equals(".doc") || endName.equals(".docx")
								|| endName.equals(".pdf")) {
							viewName = attachment.getViewName().substring(0,
									attachment.getViewName().length() - 4)
									+ endName;
						}
					}
				} else {
					model
							.put(
									"message",
									((FrameMessageResource) FrameBeanFactory
											.getBean(FrameMessageResource.BEAN_NAME))
											.getMessage("srplatform.auth.attachment.file_is_not_exit"));
				}
				model.put("attrName2", viewName);
			}
		}
		model.put("purchaseDoc", purchaseDoc);
		return new ModelAndView("updatePurchaseDocFormView", model);
	}

	/**
	 * FunName: uploadPurchaseFile Description : 远程提交采购文件.
	 * 
	 * @return String：返回值说明
	 * @throws IOException
	 * @Author: zhouzhanghe
	 * @Create Date: 2011-7-4
	 */
	@RequestMapping(params = "method=savePurchaseFile")
	public void savePurchaseFile(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String prjCode = request.getParameter("prjCode");// 项目编号
		String username = request.getParameter("username");//用户名
		String password = request.getParameter("password");//密码
		// 文件类型，默认为 integration（整体式，不区分商务和技术标），business商务标，technical技术标
		String projectDataFileName = "DataItems.xml";// 压缩文件中项目数据的文件名
		String evalFactorsDataFileName = "EvalFactors.xml";// 压缩文件中评审指标文件名
		String PriceName = "Price.xml"; // 开标一览表数据文件名
		String tenderDocument = "TenderDocument.xml";// 投标文件结构
		boolean operDesc = true;// 存放服务器是否正确执行
		String errorMessage = "";
		try {
			Project project = projectService.findProjectByProjCode(prjCode);
			PurchaseDoc purchaseDoc = purchaseDocService.getPurchaseDocByProjectId(project.getObjId());
			if(purchaseDoc!=null && project.getPurDocPrice()!=null){    //通过电子辅助评审系统上传招标文件时同步文件价格    add by liuke 1205
				purchaseDoc.setFilePrice(project.getPurDocPrice().toString());
			}
			//提交后usestatus状态改为01
			purchaseDoc.setUseStatus(AuditStatusEnum.AUDIT_PASS);//有效状态
			
			List<User> userList = userApiService.getUserByUserName(username, password);
			if(userList != null && userList.size() > 0) {
				purchaseDoc.setUser(userList.get(0));
			}
			
			Attachment attachment = null;
			List<Attachment> attachmentList = attachmentService
					.getAttachmentsByRealID(purchaseDoc.getAttachRelaId());
			if (null != attachmentList && !attachmentList.isEmpty())
				attachment = attachmentList.get(0);
			String epp_projectAttaPath = this.getMessageSource().getMessage(
					"epp.projectAttaPath");
			File purchaseFile = new File(epp_projectAttaPath
					+ attachment.getPath() + attachment.getFileName());
			/* 处理项目数据 */
			Document projectDataDocument = ZipUtils
					.readXMLFileFromZipFileByfileName(purchaseFile
							.getAbsolutePath(), projectDataFileName);
			List<Element> projectDataElementList = projectDataDocument
					.getRootElement().elements();
			List<DataItem> projectDataItemList = new ArrayList<DataItem>();
			for (Element e : projectDataElementList) {
				DataItem dataItem = (DataItem) XmlUtil.elementToObject(e,
						DataItem.class);
				if ("false".equalsIgnoreCase(dataItem.getReadOnly())) {// 将非只读的数据放入list
					projectDataItemList.add((DataItem) XmlUtil.elementToObject(
							e, DataItem.class));
				}
			}
			/* 处理指标数据 */
			Document evalFactorsDocument = ZipUtils
					.readXMLFileFromZipFileByfileName(purchaseFile
							.getAbsolutePath(), evalFactorsDataFileName);
			List<Element> evalFactorElementList = evalFactorsDocument
					.getRootElement().element("packs").elements();
			List<Map> congruousFactorList = new ArrayList<Map>();
			for (Element packE : evalFactorElementList) {
				List<Element> factorList = packE.elements();
				for (Element factorE : factorList) {
					Map congruousFactorMap = new HashMap();
					congruousFactorMap.put("projId", project.getObjId());
					Project subProject = projectService
							.findProjectByProjCodeAndParent(packE
									.attributeValue("packCode"), project
									.getObjId());
					if (subProject == null || subProject.getObjId() == null
							|| "".equals(subProject.getObjId())) {// 若该项目没有分包，则包组ID为该项目ID
						subProject = project;
					}
					congruousFactorMap.put("packId", subProject.getObjId());
					congruousFactorMap
							.put("packName", subProject.getProjName());
					congruousFactorMap.put("name", factorE.elementText("name"));
					congruousFactorMap.put("code", factorE.elementText("code"));
					congruousFactorMap.put("auditType", factorE
							.elementText("auditType"));
					congruousFactorMap.put("auditMethod", factorE
							.elementText("auditMethod"));
					congruousFactorMap.put("score", factorE
							.elementText("score"));
					congruousFactorMap.put("scoreMin", factorE
							.elementText("scoreMin"));
					congruousFactorMap.put("scoreMax", factorE
							.elementText("scoreMax"));
					congruousFactorMap.put("rate", factorE.elementText("rate"));
					congruousFactorMap.put("isNeed", factorE
							.elementText("isNeed"));
					congruousFactorMap.put("remark", factorE
							.elementText("remark"));
					congruousFactorMap.put("id", factorE.elementText("id"));
					congruousFactorMap.put("parentId", factorE
							.elementText("parentId"));
					congruousFactorMap.put("isLeaf", factorE
							.elementText("isLeaf"));
					congruousFactorMap.put("sortNo", factorE
							.elementText("sortNo"));
					congruousFactorMap.put("isNeed", factorE
							.elementText("isNeed"));

					congruousFactorList.add(congruousFactorMap);
				}
			}
			/* 处理开标一览表数据 */
			Document PriceDocument = ZipUtils.readXMLFileFromZipFileByfileName(
					purchaseFile.getAbsolutePath(), PriceName);
			List<Element> itemElementList = PriceDocument.getRootElement()
					.element("package").elements("item");

			List<String> priceInfoList = new ArrayList<String>();
			for (Iterator iterator = itemElementList.iterator(); iterator
					.hasNext();) {
				Element element = (Element) iterator.next();
				priceInfoList.add(element.elementText("name"));
			}
			genviewDefineService.savePriceInfo(priceInfoList, project); // 保存开标一览表数据项

			/* 判断是否存在投标文件结构 */
			if (!ZipUtils.whetherTheFileExistsInZipFile(purchaseFile
					.getAbsolutePath(), tenderDocument)) {
				throw new EsException("找不到" + tenderDocument + "文件");
			}

			/*更新项目数据*/
			if(EbuyMethodEnum.INQUIRY.equals(project.getEbuyMethod())){  //如果是询价文件，则需要修改文件类型
				purchaseDoc.setFileType(PurchaseDocEnum.INQPDOC);
			}
			Agency agency = userApiService.getAgentMessageByObjId(project.getAgencies().getObjId());
			for(DataItem dataItem : projectDataItemList){
				if("dataItemName".equals(dataItem.getId())){//更新项目名称
					project.setProjName(dataItem.getValue());
				}else if("tenderFeeNumber".equals(dataItem.getId())){//更新标书费金额
					purchaseDoc.setFilePrice(dataItem.getValue());
				}else if("openBankOrg".equals(dataItem.getId())){//更新开户单位（缴纳）
					agency.setOpenBank(dataItem.getValue());
				}else if("openBankAccount".equals(dataItem.getId())){//更新账号（缴纳）
					agency.setOpenAccount(dataItem.getValue());
				}
			}
			projectService.save(project);
			if(EbuyMethodEnum.INQUIRY.equals(project.getEbuyMethod())){ //如果是询价方式，则调用保存询价文件的方法  add by liuke 20111207
				purchaseDocService.saveInqpDoc(purchaseDoc);
			}else{
				purchaseDocService.savePurchaseDoc(purchaseDoc);
			}
			agencyService.save(agency);
			/*更新指标数据*/
			congruousFactorService.saveCongruousFactor(congruousFactorList);
		} catch (Exception e) {
			errorMessage = e.getMessage();
			operDesc = false;
			e.printStackTrace();
		}
		StringBuffer responseXML = new StringBuffer(
				"<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
		responseXML
				.append("<uploadPurchaseFile xmlns=\"http://www.gpcsoft.com\">");
		responseXML.append("<header>");
		responseXML.append("<operTag>" + (operDesc ? "Y" : "N") + "</operTag>");
		responseXML.append("<operDesc>" + (operDesc ? "成功" : "失败")
				+ "</operDesc>");
		responseXML.append("<operException>" + errorMessage
				+ "</operException>");
		responseXML.append("</header>");
		responseXML.append("<body>");
		responseXML.append("</body>");
		responseXML.append("</uploadPurchaseFile>");
		response.getWriter().print(responseXML.toString());
	}
	
	/**
	 * FunName: uploadPurchaseFile Description : 网盘保存采购文件
	 * @return String：返回值说明
	 * @throws IOException
	 * @Author: sunl
	 * @Create Date: 2011-11-24 16:26:03
	 */
	@RequestMapping(params = "method=snapSavePurchaseFile")
	public void snapSavePurchaseFile(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String prjCode = request.getParameter("prjCode");// 项目编号
		String username = request.getParameter("username");//用户名
		String password = request.getParameter("password");//密码
		String projectDataFileName = "DataItems.xml";// 压缩文件中项目数据的文件名
		String evalFactorsDataFileName = "EvalFactors.xml";// 压缩文件中评审指标文件名
		String PriceName = "Price.xml"; // 开标一览表数据文件名
		String tenderDocument = "TenderDocument.xml";// 投标文件结构
		boolean operDesc = true;// 存放服务器是否正确执行
		String errorMessage = "";
		try {
			Project project = projectService.findProjectByProjCode(prjCode);
			if(project == null) {
				throw new Exception("未找到编号为：\""+prjCode+"\"的项目!");
			}
			PurchaseDoc purchaseDoc = purchaseDocService.getPurchaseDocByProjectId(project.getObjId());
			if(purchaseDoc == null) {
				throw new Exception("未找到编号为：\""+prjCode+"\"的采购文件数据!");
			}
			List<User> userList = userApiService.getUserByUserName(username, password);
			if(userList != null && userList.size() > 0) {
				purchaseDoc.setUser(userList.get(0));
			}
			
			//保存后usestatus状态改为00
			purchaseDoc.setUseStatus(AuditStatusEnum.WAIT_AUDIT);//临时状态
			
			purchaseDocService.save(purchaseDoc);
			
			if(PurchaseDocEnum.PURCHASEOFFICE_WAIT.equals(purchaseDoc.getAuditStatus()))
				throw new Exception("采购文件已经提交，此时不能进行修改!");
			else if(PurchaseDocEnum.PURCHASEOFFICEPASS.equals(purchaseDoc.getAuditStatus())){
				throw new Exception("采购文件已经审核通过，此时不能进行修改!");
			}
			
			Attachment attachment = null;
			List<Attachment> attachmentList = attachmentService.getAttachmentsByRealID(purchaseDoc.getAttachRelaId());
			if (null != attachmentList && !attachmentList.isEmpty())
				attachment = attachmentList.get(0);
			String epp_projectAttaPath = this.getMessageSource().getMessage("epp.projectAttaPath");
			File purchaseFile = new File(epp_projectAttaPath + attachment.getPath() + attachment.getFileName());
			/* 处理项目数据 */
			Document projectDataDocument = ZipUtils.readXMLFileFromZipFileByfileName(purchaseFile.getAbsolutePath(), projectDataFileName);
			List<Element> projectDataElementList = projectDataDocument.getRootElement().elements();
			List<DataItem> projectDataItemList = new ArrayList<DataItem>();
			for (Element e : projectDataElementList) {
				DataItem dataItem = (DataItem) XmlUtil.elementToObject(e,DataItem.class);
				if ("false".equalsIgnoreCase(dataItem.getReadOnly())) {// 将非只读的数据放入list
					projectDataItemList.add((DataItem) XmlUtil.elementToObject(e, DataItem.class));
				}
			}
			/* 处理指标数据 */
			Document evalFactorsDocument = ZipUtils.readXMLFileFromZipFileByfileName(purchaseFile.getAbsolutePath(), evalFactorsDataFileName);
			List<Element> evalFactorElementList = evalFactorsDocument.getRootElement().element("packs").elements();
			List<Map> congruousFactorList = new ArrayList<Map>();
			for (Element packE : evalFactorElementList) {
				List<Element> factorList = packE.elements();
				for (Element factorE : factorList) {
					Map congruousFactorMap = new HashMap();
					congruousFactorMap.put("projId", project.getObjId());
					Project subProject = projectService.findProjectByProjCodeAndParent(packE.attributeValue("packCode"), project.getObjId());
					if (subProject == null || subProject.getObjId() == null|| "".equals(subProject.getObjId())) {// 若该项目没有分包，则包组ID为该项目ID
						subProject = project;
					}
					congruousFactorMap.put("packId", subProject.getObjId());
					congruousFactorMap.put("packName", subProject.getProjName());
					congruousFactorMap.put("name", factorE.elementText("name"));
					congruousFactorMap.put("code", factorE.elementText("code"));
					congruousFactorMap.put("auditType", factorE.elementText("auditType"));
					congruousFactorMap.put("auditMethod", factorE.elementText("auditMethod"));
					congruousFactorMap.put("score", factorE.elementText("score"));
					congruousFactorMap.put("scoreMin", factorE.elementText("scoreMin"));
					congruousFactorMap.put("scoreMax", factorE.elementText("scoreMax"));
					congruousFactorMap.put("rate", factorE.elementText("rate"));
					congruousFactorMap.put("isNeed", factorE.elementText("isNeed"));
					congruousFactorMap.put("remark", factorE.elementText("remark"));
					congruousFactorMap.put("id", factorE.elementText("id"));
					congruousFactorMap.put("parentId", factorE.elementText("parentId"));
					congruousFactorMap.put("isLeaf", factorE.elementText("isLeaf"));
					congruousFactorMap.put("sortNo", factorE.elementText("sortNo"));
					congruousFactorMap.put("isNeed", factorE.elementText("isNeed"));
					congruousFactorList.add(congruousFactorMap);
				}
			}
			/* 处理开标一览表数据 */
			Document PriceDocument = ZipUtils.readXMLFileFromZipFileByfileName(purchaseFile.getAbsolutePath(), PriceName);
			List<Element> itemElementList = PriceDocument.getRootElement().element("package").elements("item");
			List<String> priceInfoList = new ArrayList<String>();
			for (Iterator iterator = itemElementList.iterator(); iterator.hasNext();) {
				Element element = (Element) iterator.next();
				priceInfoList.add(element.elementText("name"));
			}
			genviewDefineService.savePriceInfo(priceInfoList, project); // 保存开标一览表数据项

			/* 判断是否存在投标文件结构 */
			if (!ZipUtils.whetherTheFileExistsInZipFile(purchaseFile.getAbsolutePath(), tenderDocument)) {
				throw new EsException("找不到" + tenderDocument + "文件");
			}

			/* 更新项目数据和指标数据 */
			purchaseDocService.saveUpdatePurchaseFile(project.getObjId(),purchaseDoc.getObjId(), projectDataItemList,congruousFactorList);
		} catch (Exception e) {
			errorMessage = e.getMessage();
			operDesc = false;
			e.printStackTrace();
		}
		StringBuffer responseXML = new StringBuffer("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
		responseXML.append("<uploadPurchaseFile xmlns=\"http://www.gpcsoft.com\">");
		responseXML.append("<header>");
		responseXML.append("<operTag>" + (operDesc ? "Y" : "N") + "</operTag>");
		responseXML.append("<operDesc>" + (operDesc ? "成功" : "失败")+ "</operDesc>");
		responseXML.append("<operException>" + errorMessage+ "</operException>");
		responseXML.append("</header>");
		responseXML.append("<body>");
		responseXML.append("</body>");
		responseXML.append("</uploadPurchaseFile>");
		response.getWriter().print(responseXML.toString());
	
	}

	/**
	 * FunName: auditPurchaseFile Description : 
	 * 审核采购文件
	 * @throws Exception 
	 */
	@RequestMapping(params = "method=auditPurchaseFile")
	public void auditPurchaseFile(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String prjCode = request.getParameter("prjCode");// 项目编号
		String isAdopt = request.getParameter("isAdopt");//是否通过
		String username = request.getParameter("username");//用户名
		String password = request.getParameter("password");//密码
		String auditMemo = request.getParameter("auditMemo");//审批意见
		
		String projectDataFileName = "DataItems.xml";// 压缩文件中项目数据的文件名
		String evalFactorsDataFileName = "EvalFactors.xml";// 压缩文件中评审指标文件名
		String PriceName = "Price.xml"; // 开标一览表数据文件名
		String tenderDocument = "TenderDocument.xml";// 投标文件结构
		boolean operDesc = true;// 存放服务器是否正确执行
		String errorMessage = "";
		try{
			List<User> auditUserList = userApiService.getUserByUserName(username, password);
			User auditor = null;
			if(auditUserList != null && auditUserList.size()>0) {
				auditor = auditUserList.get(0);
			}
			if(auditor == null) {
				throw new Exception("未找到用户名为：\""+username+"\"的用户!");
			}
			Project project = projectService.findProjectByProjCode(prjCode);
			if(project == null) {
				throw new Exception("未找到编号为：\""+prjCode+"\"的项目!");
			}
			PurchaseDoc purchaseDoc = purchaseDocService.getPurchaseDocByProjectId(project.getObjId());
			if(purchaseDoc == null) {
				throw new Exception("未找到编号为：\""+prjCode+"\"的采购文件数据!");
			}
			
			if(purchaseDoc.getAuditStatus()==null 
					|| PurchaseDocEnum.TEMP.equals(purchaseDoc.getAuditStatus()==null?"":purchaseDoc.getAuditStatus())) {
				errorMessage = "采购文件尚未提交，此时不能进行审核！";
				throw new Exception("采购文件尚未提交，此时不能进行审核！");
			}
			else if(PurchaseDocEnum.PURCHASEOFFICEPASS.equals(purchaseDoc.getAuditStatus())){
				errorMessage = "采购文件已经审核通过，此时不能进行审核！";
				throw new Exception("采购文件已经审核通过，此时不能进行审核！");
			} else if(PurchaseDocEnum.RETURNBACK.equals(purchaseDoc.getAuditStatus()==null?"":purchaseDoc.getAuditStatus())){
				errorMessage = "采购文件已经审核不通过，此时不能进行审核！";
				throw new Exception("采购文件已经审核不通过，此时不能进行审核！");
			}
			
			Attachment attachment = null;
			List<Attachment> attachmentList = attachmentService.getAttachmentsByRealID(purchaseDoc.getAttachRelaId());
			if (null != attachmentList && !attachmentList.isEmpty())
				attachment = attachmentList.get(0);
			String epp_projectAttaPath = this.getMessageSource().getMessage("epp.projectAttaPath");
			File purchaseFile = new File(epp_projectAttaPath + attachment.getPath() + attachment.getFileName());
			/* 处理项目数据 */
			Document projectDataDocument = ZipUtils.readXMLFileFromZipFileByfileName(purchaseFile.getAbsolutePath(), projectDataFileName);
			List<Element> projectDataElementList = projectDataDocument.getRootElement().elements();
			List<DataItem> projectDataItemList = new ArrayList<DataItem>();
			for (Element e : projectDataElementList) {
				DataItem dataItem = (DataItem) XmlUtil.elementToObject(e,DataItem.class);
				if ("false".equalsIgnoreCase(dataItem.getReadOnly())) {// 将非只读的数据放入list
					projectDataItemList.add((DataItem) XmlUtil.elementToObject(e, DataItem.class));
				}
			}
			/* 处理指标数据 */
			Document evalFactorsDocument = ZipUtils.readXMLFileFromZipFileByfileName(purchaseFile.getAbsolutePath(), evalFactorsDataFileName);
			List<Element> evalFactorElementList = evalFactorsDocument.getRootElement().element("packs").elements();
			List<Map> congruousFactorList = new ArrayList<Map>();
			for (Element packE : evalFactorElementList) {
				List<Element> factorList = packE.elements();
				for (Element factorE : factorList) {
					Map congruousFactorMap = new HashMap();
					congruousFactorMap.put("projId", project.getObjId());
					Project subProject = projectService.findProjectByProjCodeAndParent(packE.attributeValue("packCode"), project.getObjId());
					if (subProject == null || subProject.getObjId() == null|| "".equals(subProject.getObjId())) {// 若该项目没有分包，则包组ID为该项目ID
						subProject = project;
					}
					congruousFactorMap.put("packId", subProject.getObjId());
					congruousFactorMap.put("packName", subProject.getProjName());
					congruousFactorMap.put("name", factorE.elementText("name"));
					congruousFactorMap.put("code", factorE.elementText("code"));
					congruousFactorMap.put("auditType", factorE.elementText("auditType"));
					congruousFactorMap.put("auditMethod", factorE.elementText("auditMethod"));
					congruousFactorMap.put("score", factorE.elementText("score"));
					congruousFactorMap.put("scoreMin", factorE.elementText("scoreMin"));
					congruousFactorMap.put("scoreMax", factorE.elementText("scoreMax"));
					congruousFactorMap.put("rate", factorE.elementText("rate"));
					congruousFactorMap.put("isNeed", factorE.elementText("isNeed"));
					congruousFactorMap.put("remark", factorE.elementText("remark"));
					congruousFactorMap.put("id", factorE.elementText("id"));
					congruousFactorMap.put("parentId", factorE.elementText("parentId"));
					congruousFactorMap.put("isLeaf", factorE.elementText("isLeaf"));
					congruousFactorMap.put("sortNo", factorE.elementText("sortNo"));
					congruousFactorMap.put("isNeed", factorE.elementText("isNeed"));
					congruousFactorList.add(congruousFactorMap);
				}
			}
			/* 处理开标一览表数据 */
			Document PriceDocument = ZipUtils.readXMLFileFromZipFileByfileName(purchaseFile.getAbsolutePath(), PriceName);
			List<Element> itemElementList = PriceDocument.getRootElement().element("package").elements("item");
			List<String> priceInfoList = new ArrayList<String>();
			for (Iterator iterator = itemElementList.iterator(); iterator.hasNext();) {
				Element element = (Element) iterator.next();
				priceInfoList.add(element.elementText("name"));
			}
			genviewDefineService.savePriceInfo(priceInfoList, project); // 保存开标一览表数据项

			/* 判断是否存在投标文件结构 */
			if (!ZipUtils.whetherTheFileExistsInZipFile(purchaseFile.getAbsolutePath(), tenderDocument)) {
				throw new EsException("找不到" + tenderDocument + "文件");
			}

			/*更新指标数据*/
			congruousFactorService.saveCongruousFactor(congruousFactorList);
			
			/*审核采购文件*/
			purchaseDocService.auditPurchaseDoc(purchaseDoc.getObjId(), auditor,auditMemo, isAdopt,"");
		} catch (Exception e) {
			errorMessage = e.getMessage();
			operDesc = false;
			e.printStackTrace();
		}
		StringBuffer responseXML = new StringBuffer("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
		responseXML.append("<uploadPurchaseFile xmlns=\"http://www.gpcsoft.com\">");
		responseXML.append("<header>");
		responseXML.append("<operTag>" + (operDesc ? "Y" : "N") + "</operTag>");
		responseXML.append("<operDesc>" + (operDesc ? "成功" : "失败"+",原因："+errorMessage)+ "</operDesc>");
		responseXML.append("<operException>" + errorMessage+ "</operException>");
		responseXML.append("</header>");
		responseXML.append("<body>");
		responseXML.append("</body>");
		responseXML.append("</uploadPurchaseFile>");
		response.getWriter().print(responseXML.toString());
	}
	
	/**
	 * FuncName: toInqpDocDetailView Description : 创建或修改对象
	 * 
	 * @param request
	 * @return ModelAndView
	 * @throws Exception
	 *             ModelAndView
	 * @author: liuke
	 * @Create Date:2011-7-5 下午02:27:20
	 * @Modifier: liuke
	 * @Modified Date:2011-7-5 下午02:27:20
	 */
	@RequestMapping(params = "method=toPurchaseDocDetailView")
	public ModelAndView toPurchaseDocDetailView(HttpServletRequest request)
			throws Exception {
		logger.debug("\nes PurchaseDocController||toPurchaseDocDetailView\n");
		String projectId = request.getParameter("projectId");
		String returnName = "purchaseDocDetailView";
		Map model = new HashMap();
		model.put("fromType", "fromDesk");
		PurchaseDoc purchaseDoc = purchaseDocService
				.getPurchaseDocByProjectId(projectId);
		if (null != purchaseDoc) { // 如果招标文件不为空
			List<Attachment> attachmentList = attachmentService
					.getAttachmentsByRealID(purchaseDoc.getAttachRelaId());
			if (null != attachmentList && !attachmentList.isEmpty()) { // 如果招标文件附件不为空
				Attachment attachment = attachmentList.get(0);
				model.put("attrId", attachmentList.get(0).getObjId());
				model.put("attrName", attachmentList.get(0).getViewName());
				String path = getMessageSource().getMessage(
						"epp.projectAttaPath")
						+ attachment.getPath() + File.separator;
				String filePath = getMessageSource().getMessage(
						"epp.projectAttaPath")
						+ attachment.getPath()
						+ File.separator
						+ attachment.getFileName().replace("/", File.separator);// 换回绝对路径
				ZipUtils.unZip(filePath, path);
				String[] l = FileUtil.listFiles(path);
				String viewName = "";
				if (l != null) {
					for (int i = 0; i < l.length; i++) {
						String endName = "";
						if (l[i].indexOf(".") != -1) {
							endName = l[i].substring(l[i].indexOf("."));
						}
						if (endName.equals(".doc") || endName.equals(".docx")
								|| endName.equals(".pdf")) {
							viewName = attachment.getViewName().substring(0,
									attachment.getViewName().length() - 4)
									+ endName;
						}
					}
				} else {
					model
							.put(
									"message",
									((FrameMessageResource) FrameBeanFactory
											.getBean(FrameMessageResource.BEAN_NAME))
											.getMessage("srplatform.auth.attachment.file_is_not_exit"));
				}
				model.put("attrName2", viewName);
			}
			model.put("purchaseDoc", purchaseDoc);
		} else {
			returnName = "blankView";
		}
		return new ModelAndView(returnName, model);
	}

	/**
	 * FuncName: toInqpDocDetailView Description : 下载采购文件
	 * 
	 * @param objId
	 *            附件主键ID
	 * @param request
	 * @return void
	 * @throws Exception
	 *             ModelAndView
	 * @author: liuke
	 * @Create Date:2011-7-5 下午02:27:20
	 * @Modifier: liuke
	 * @Modified Date:2011-7-5 下午02:27:20
	 */

	@RequestMapping(params = "method=downLoadPurFile")
	public void downLoadPurFile(String objId, HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		AttachmentService attachmentService = (AttachmentService) FrameBeanFactory
				.getBean("attachmentServiceImpl");
		PurchaseDoc purchaseDoc = purchaseDocService.get(request
				.getParameter("purchaseDocId"));
		// modify by shenjz at 2011-07-20 10:28,更改为通过业务对象获取到关联ID,再获取相应的附件对象
		Attachment attachment = null;
		if (null != purchaseDoc) {
			List<Attachment> attachmentList = attachmentService
					.getAttachmentsByRealID(purchaseDoc.getAttachRelaId());
			if (null != attachmentList && !attachmentList.isEmpty()) {
				attachment = attachmentList.get(0);
			}
		}
		String filePath = getMessageSource().getMessage("epp.projectAttaPath")
				+ attachment.getPath() + File.separator
				+ attachment.getFileName().replace("/", File.separator);// 换回绝对路径
		File file = new File(filePath);
		FileInputStream in = null;
		try {
			in = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new Exception(((FrameMessageResource) FrameBeanFactory
					.getBean(FrameMessageResource.BEAN_NAME))
					.getMessage("srplatform.auth.attachment.file_is_not_exit"));
		}
		byte[] content = new byte[(int) file.length()];
		int readLen = in.read(content);
		in.close();
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename="
				+ new String(attachment.getViewName().getBytes(
						request.getHeader("User-Agent").toUpperCase().indexOf(
								"MSIE") > 0 ? "gbk" : "utf-8"), "ISO8859-1")); // 火狐和Ie的下载文件名乱码问题
		response.addIntHeader("Content-Length", readLen); // 增加这一行
		try {
			response.getOutputStream().write(content);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			response.getOutputStream().close();
		}

	}

	/**
	 * FuncName: downLoadDocFile Description : 下载采购文件doc文档
	 * 
	 * @param objId
	 * @param response
	 * @param request
	 * @throws Exception void
	 * @author: shenjz
	 * @Create Date:2011-7-20 上午10:13:04
	 * @Modifier: shenjz
	 * @Modified Date:2011-7-20 上午10:13:04
	 */
	@RequestMapping(params = "method=downLoadDocFile")
	public void downLoadDocFile(String objId, HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		AttachmentService attachmentService = (AttachmentService) FrameBeanFactory
				.getBean("attachmentServiceImpl");
		String purchaseDocId = request.getParameter("purchaseDocId");
		StringBuilder returnFilePath = new StringBuilder();
		StringBuilder returnFileViewName = new StringBuilder();
		purchaseDocService.getPurchaseDocFilePathAndNameByPurchaseDocId(returnFilePath, returnFileViewName, purchaseDocId);
		File file = new File(returnFilePath.toString());
		FileInputStream in = null;
		try {
			in = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new Exception(((FrameMessageResource) FrameBeanFactory
					.getBean(FrameMessageResource.BEAN_NAME))
					.getMessage("srplatform.auth.attachment.file_is_not_exit"));
		}
		byte[] content = new byte[(int) file.length()];
		int readLen = in.read(content);
		in.close();
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename="
				+ new String(returnFileViewName.toString().getBytes(request.getHeader("User-Agent")
						.toUpperCase().indexOf("MSIE") > 0 ? "gbk" : "utf-8"),
						"ISO8859-1")); // 火狐和Ie的下载文件名乱码问题
		response.addIntHeader("Content-Length", readLen); // 增加这一行
		try {
			//记录下载记录信息
			User user = AuthenticationHelper.getCurrentUser(true);
			ProcFileDownRec downRec = new ProcFileDownRec();
			downRec.setDownDate(new Date());//下载日期
			downRec.setDownOrg((OrgInfo)user.getOrgInfo());//下载单位
			downRec.setDownUser(user);
			downRec.setFileId(purchaseDocId==null?null:purchaseDocId);
			downRec.setDownReason("招标文件下载");
			downRec.setDownStatus(CommonEnum.CONFIRM_STATUS_WAIT);//00：成功，01：失败
			downRec.setDownIP(AuthenticationHelper.getCurrentUser(true).getIp());//下载端ip地址
			procFileDownRecService.save(downRec);
			
			response.getOutputStream().write(content);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			response.getOutputStream().close();
		}
	}

	/**
	 * FuncName: downLoadUeDocFile Description : 下载UE采购文件doc文档
	 * 
	 * @param
	 * @param objId
	 * @param response
	 * @param request
	 * @throws Exception void
	 * @author: shenjz
	 * @Create Date:2011-7-20 上午10:13:23
	 * @Modifier: shenjz
	 * @Modified Date:2011-7-20 上午10:13:23
	 */
	@RequestMapping(params = "method=downLoadUeDocFile")
	public void downLoadUeDocFile(String objId, HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		AttachmentService attachmentService = (AttachmentService) FrameBeanFactory
				.getBean("attachmentServiceImpl");
		Attachment attachment = (Attachment) attachmentService.get(objId);
		String path = getMessageSource().getMessage("epp.projectAttaPath")
				+ attachment.getPath() + File.separator;
		String fileName = "";
		String filePath = getMessageSource().getMessage("epp.projectAttaPath")
				+ attachment.getPath() + File.separator;// 换回绝对路径
		String[] l = FileUtil.listFiles(path);
		String viewName = "";
		for (int i = 0; i < l.length; i++) {
			String endName = "";
			if (l[i].indexOf(".") != -1) {
				endName = l[i].substring(l[i].indexOf("."));
			}
			if (l[i].equals("PurchaseFile.doc")
					|| l[i].equals("PurchaseFile.docx")
					|| l[i].equals("PurchaseFile.pdf")) {
				fileName = l[i];
				viewName = attachment.getViewName().substring(0,
						attachment.getViewName().length() - 4)
						+ endName;
			}
		}
		filePath = filePath + fileName;
		File file = new File(filePath);
		FileInputStream in = null;
		try {
			in = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new Exception(((FrameMessageResource) FrameBeanFactory
					.getBean(FrameMessageResource.BEAN_NAME))
					.getMessage("srplatform.auth.attachment.file_is_not_exit"));
		}
		byte[] content = new byte[(int) file.length()];
		int readLen = in.read(content);
		in.close();
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename="
				+ new String(viewName.getBytes(request.getHeader("User-Agent")
						.toUpperCase().indexOf("MSIE") > 0 ? "gbk" : "utf-8"),
						"ISO8859-1")); // 火狐和Ie的下载文件名乱码问题
		response.addIntHeader("Content-Length", readLen); // 增加这一行
		try {
			response.getOutputStream().write(content);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			response.getOutputStream().close();
		}
	}

	/**
	 * FuncName: confirmFinish Description : 确认完成上传招标文件
	 * 
	 * @param objId
	 *            :项目主键
	 * @param response
	 * @param request
	 * @throws Exception void
	 * @author: shenjz
	 * @Create Date:2011-8-26 上午11:43:23
	 * @Modifier: shenjz
	 * @Modified Date:2011-8-26 上午11:43:23
	 */
	@RequestMapping(params = "method=confirmFinish")
	public ModelAndView confirmFinish(String prjId,
			HttpServletResponse response, HttpServletRequest request)
			throws Exception {
		Map model = new HashMap();
		Boolean isUploadFile = new Boolean(request.getParameter("isUploadFile"));// 判断是否上传附件
		StringBuffer sb = new StringBuffer();
		String epp_projectAttaPath = getMessageSource().getMessage(
				"epp.projectAttaPath")
				+ File.separator
				+ prjId
				+ File.separator
				+ "purFile"
				+ File.separator;
		if (!new File(epp_projectAttaPath).exists()) {
			FileUtil.mkdirs(epp_projectAttaPath);
		}
		if (isUploadFile) {
			Object o = this.uploadFile(request, "attachFile",
					epp_projectAttaPath);
			if (o instanceof GpcBaseObject) {
				Attachment attachment = (Attachment) o;
				sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
				sb.append("<format name=\"招标书格式\" docId=\"对应的文档名称\" remark=\"备注\"><section name=\"招标文件\" docId=\""
								+ attachment.getFileName()
								+ "\"  fileName=\""
								+ attachment.getViewName()
								+ "\"  type=\"Default\" /></format>");
				FileUtil.writerFile(
						epp_projectAttaPath + "BiddingDocument.xml", sb
								.toString(), "UTF-8");
			}
		}
		projectService.saveConfirmFinish(prjId,
				request.getParameter("content"), request
						.getParameter("keyWord"));
		return new ModelAndView(Constants.JSON_VIEW, model);
	}

	/**
	 * FuncName: downLoadPartsFile Description : 分册下载商务标文件或技术标文件
	 * 
	 * @param
	 * @param objId
	 * @param response
	 * @param request
	 * @throws Exception void
	 * @author: shenjz
	 * @Create Date:2011-8-30 下午02:39:06
	 * @Modifier: shenjz
	 * @Modified Date:2011-8-30 下午02:39:06
	 */
	@RequestMapping(params = "method=downLoadPartsFile")
	public void downLoadPartsFile(String attRaId, HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		AttachmentService attachmentService = (AttachmentService) FrameBeanFactory
				.getBean("attachmentServiceImpl");
		List<Attachment> attachmentList = attachmentService
				.getAttachmentsByRealID(attRaId);
		Attachment attachment = attachmentList.get(0);
		String filePath = getMessageSource().getMessage("epp.projectAttaPath")
				+ attachment.getPath() + File.separator
				+ attachment.getFileName();// 换回绝对路径
		String viewName = attachment.getViewName();
		File file = new File(filePath);
		FileInputStream in = null;
		try {
			in = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new Exception(((FrameMessageResource) FrameBeanFactory
					.getBean(FrameMessageResource.BEAN_NAME))
					.getMessage("srplatform.auth.attachment.file_is_not_exit"));
		}
		byte[] content = new byte[(int) file.length()];
		int readLen = in.read(content);
		in.close();
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename="
				+ new String(viewName.getBytes(request.getHeader("User-Agent")
						.toUpperCase().indexOf("MSIE") > 0 ? "gbk" : "utf-8"),
						"ISO8859-1")); // 火狐和Ie的下载文件名乱码问题
		response.addIntHeader("Content-Length", readLen); // 增加这一行
		try {
			response.getOutputStream().write(content);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			response.getOutputStream().close();
		}
	}

	/**
	 * FuncName : delFile Description : 删除招标文件 Create Date: 2011-9-21上午10:41:29
	 * by yangx Modified Date: 2011-9-21上午10:41:29 by yangx
	 * 
	 * @param
	 * @return
	 * @Exception
	 */
	@RequestMapping(params = "method=delFile")
	public ModelAndView delFile(HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		String fileId = request.getParameter("fileId");
		String attaPat = getMessageSource().getMessage("epp.projectAttaPath");
		purchaseDocService.removePurchaseDocFile(fileId, attaPat);
		return new ModelAndView(Constants.JSON_VIEW);
	}

	/**
	 * Description : 上传附件 Create Date: 2010-3-4下午02:41:41 by liangxj Modified
	 * Date: 2010-3-4下午02:41:41 by wangsw
	 * 
	 * @param request
	 *            、input输入框里的name属性
	 * @return Attachment 附件对象
	 * @Exception
	 */
	private Attachment uploadFile(HttpServletRequest request,
			String fileInputName, String filePath) throws Exception {
		Assert.notNull(fileInputName);
		if (!(request instanceof MultipartHttpServletRequest))
			throw new IllegalArgumentException(
					((FrameMessageResource) FrameBeanFactory
							.getBean(FrameMessageResource.BEAN_NAME))
							.getMessage("srplatform.auth.attachment.file_can_not_empty"));
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		CommonsMultipartFile file = null;
		file = (CommonsMultipartFile) multipartRequest.getFile(fileInputName); // 取上传文件路径
		if (file == null) {
			throw new IllegalArgumentException(
					((FrameMessageResource) FrameBeanFactory
							.getBean(FrameMessageResource.BEAN_NAME))
							.getMessage("srplatform.auth.attachment.file_can_not_empty"));
		}
		String maxSizeStr = request.getParameter("maxSize");
		if (maxSizeStr == null || "".equals(maxSizeStr)) {
			AttachmentService attachmentService = (AttachmentService) FrameBeanFactory
					.getBean("attachmentServiceImpl");
			maxSizeStr = attachmentService.getDefaultMaxSize();
		}
		if (maxSizeStr != null && !maxSizeStr.equals("")
				&& !maxSizeStr.equals("undefined")) {
			long maxSize = Long.valueOf(maxSizeStr);
			if (file.getSize() > (maxSize * 1024))// 单位K
				throw new IllegalArgumentException(
						((FrameMessageResource) FrameBeanFactory
								.getBean(FrameMessageResource.BEAN_NAME))
								.getMessage("srplatform.auth.attachment.file_is_too_large")
								+ maxSize + " KB");
		}
		// 得到文件名
		String filename = file.getOriginalFilename();
		if (file.getSize() > 0) {
			try {
				AttachmentService attachmentService = (AttachmentService) FrameBeanFactory
						.getBean("attachmentServiceImpl");
				File distFile = new File((filePath)
						.replace("/", File.separator));
				if (!distFile.getParentFile().exists()) {
					distFile.getParentFile().mkdirs();
				}
				attachmentService.saveFileByInputStream(file.getInputStream(),
						filePath, filename);
				Attachment attachment = new Attachment();
				attachment.setViewName(filename);
				attachment.setFileName(filename);
				return attachment;
			} catch (IOException e) {
				throw new IllegalArgumentException(
						((FrameMessageResource) FrameBeanFactory
								.getBean(FrameMessageResource.BEAN_NAME))
								.getMessage("srplatform.auth.attachment.upload_failer"));
			}
		} else {
			throw new IllegalArgumentException(
					((FrameMessageResource) FrameBeanFactory
							.getBean(FrameMessageResource.BEAN_NAME))
							.getMessage("srplatform.auth.attachment.file_can_not_empty"));
		}
	}
	
	
	
	/**
	 * FuncName: toPurchaseView
	 * Description :  招标文件查看页面
	 * @param 
	 * @param request
	 * @return
	 * @throws Exception ModelAndView
	 * @author: zhangsh
	 * @Create Date:2011-11-15  上午07:27:01
	 * @Modifier: zhangsh
	 * @Modified Date:2011-11-15  上午07:27:01
	 */
	@RequestMapping(params = "method=toPurchaseView")
	public ModelAndView toPurchaseView(HttpServletRequest request)
			throws Exception {
		logger.debug("\nes PurchaseDocController||toPurchaseView\n");
		String projectId = request.getParameter("projectId");
		String purchaseDocId = request.getParameter("objId");
		String fromType = request.getParameter("fromType");
		Project proj = projectService.getProjectByObjId(projectId);
		PurchaseDoc purchaseDoc = purchaseDocService.getPurchaseDocByProjectId(projectId);
		if (null == projectId || "".equals(projectId)) {
			purchaseDoc = purchaseDocService.getPurchaseDocByObjId(purchaseDocId);
			projectId = purchaseDoc.getProject().getObjId();
		}
		List<Project> subProjectList = projectService.getSubProjectByProjectId(projectId);
		Map model = new HashMap();
		model.put("subProjectList", subProjectList);
		model.put("project", proj);
		if ((subProjectList == null ? 0 : subProjectList.size()) != 0) {
			model.put("divided", true);
		}
		if (null != purchaseDoc) {
			/*add by sunl 读取招标文件附件*/
			List<Attachment> attachmentList = purchaseDoc.getAttachRelaId()==null?null:attachmentService.getAttachmentsByRealID(purchaseDoc.getAttachRelaId());
			List<PurchaseDocAtt> purAttList = purchaseDocAttService.getPurchaseDocAttList(projectId);
			model.put("purAttList", purAttList);
			
			List<Attachment> attList = new ArrayList<Attachment>();
			List<Attachment> tempList=null;
			for (PurchaseDocAtt purchaseDocAtt : purAttList) {
				tempList = attachmentService.getAttachmentsByRealID(purchaseDocAtt.getAttRaId());
				if(tempList!=null && tempList.size()>0) {
					Attachment att = tempList.get(0);
					attList.add(att);
				}
			}
			model.put("attList",attList);
			
			if (null != attachmentList && !attachmentList.isEmpty()) {
				Attachment attachment = attachmentList.get(0);
				model.put("attrId", attachment.getObjId());
				model.put("attrName", attachment.getViewName());
				String path = getMessageSource().getMessage("epp.projectAttaPath") + attachment.getPath() + File.separator;
				String filePath = getMessageSource().getMessage("epp.projectAttaPath")+ attachment.getPath()
						+ File.separator + attachment.getFileName().replace("/", File.separator);// 换回绝对路径
				try {
					if (CommonEnum.USER_STATUS_FORMAL.equals(purchaseDoc.getUseStatus())) {
						ZipUtils.unZip(filePath, path);
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				String[] l = FileUtil.listFiles(path);
				String viewName = "";
				if (l != null) {
					for (int i = 0; i < l.length; i++) {
						String endName = "";
						if (l[i].indexOf(".") != -1) {
							endName = l[i].substring(l[i].indexOf("."));
						}
						if (endName.equals(".doc") || endName.equals(".docx")|| endName.equals(".pdf")) {
							viewName = attachment.getViewName().substring(0,attachment.getViewName().length() - 4)+ endName;
						}
					}
				} else {
					model.put("message",((FrameMessageResource) FrameBeanFactory.getBean(FrameMessageResource.BEAN_NAME)).getMessage("srplatform.auth.attachment.file_is_not_exit"));
				}
				model.put("attrName2", viewName);
			}
		}
		model.put("projectId", projectId);
		model.put("purchaseDoc", purchaseDoc);
		model.put("fromType", fromType);
		model.put("filePrice", proj.getPurDocPrice() == null ? "" : proj.getPurDocPrice());
		return new ModelAndView("purchaseDocDetailView", model);
	}
	
	/**
	 * 查看建筑工程采购文件
	 * @param request
	 * @param response
	 * @param purchaseDocId
	 * @author zhouzhanghe
	 * @throws IOException 
	 * @created date 2011-11-19 14:13
	 */
	@RequestMapping(params = "method=viewJZGCPurchaseDoc")
	public void viewJZGCPurchaseDoc(HttpServletRequest request, HttpServletResponse response, String purchaseDocId) throws IOException{
		/*查找采购文件附件*/
		PurchaseDoc purchaseDoc = purchaseDocService.get(purchaseDocId);
		if(purchaseDoc == null){
			throw new EsException(EsExceptionEnum.ES_ERROR_NULLOBJECT);
		}
		if(StringUtils.empty(purchaseDoc.getAttachRelaId())){
			throw new EsException(EsExceptionEnum.ES_ERROR_NULLKEY);
		}
		Attachment attachment= null;
		if (null != purchaseDoc) {
			List<Attachment> attachmentList = attachmentService.getAttachmentsByRealID(purchaseDoc.getAttachRelaId());
			if(attachmentList != null && attachmentList.size() != 1){
				throw new EsException(EsExceptionEnum.ES_ERROR_EXPECTEDFORTHEONE);
			}
			attachment = attachmentList.get(0);
		}
		
		/*拼装查看地址*/
		String viewJZGZPurAddress = getMessageSource().getMessage("viewJZGZPurAddress");
		String applicationInternetAddress = getMessageSource().getMessage("applicationInternetAddress");
		if(viewJZGZPurAddress == null || applicationInternetAddress == null)
			throw new EsException(EsExceptionEnum.ES_ERROR_NULLOBJECT);
		StringBuilder url = new StringBuilder();
		url.append(viewJZGZPurAddress);
		url.append("/Bin/GCPWebServer.dll?Action=Execute&AppName=GZTB&RedirectUrl=gcp://gztb/?context=");
		url.append(StringUtil.encodeString(applicationInternetAddress+"/DownloadFileHttpServlet?attachmentObjId=".concat(attachment.getRelationId()).concat("&fileType=Purchase")));
		url.append("&entryurl=");
		url.append(viewJZGZPurAddress);
		url.append("/Bin/GCPWebServer.dll?Action=Execute&AppName=GZTB");
		response.sendRedirect(url.toString());
	}
}
