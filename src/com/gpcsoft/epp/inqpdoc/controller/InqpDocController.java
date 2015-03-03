package com.gpcsoft.epp.inqpdoc.controller;

import java.io.File;
import java.io.FileNotFoundException;
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
import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.context.FrameMessageResource;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryObjectBase;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.dao.hibernate.query.QueryWebUtils;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.utils.FileUtil;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.common.domain.AuditStatusEnum;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.service.ProjectService;
import com.gpcsoft.epp.purchasedoc.domain.PurchaseDoc;
import com.gpcsoft.epp.purchasedoc.domain.PurchaseDocEnum;
import com.gpcsoft.epp.purchasedoc.service.PurchaseDocService;
import com.gpcsoft.epp.signuprecord.domain.SignUprecord;
import com.gpcsoft.epp.signuprecord.service.SignUprecordService;
import com.gpcsoft.epp.webService.common.util.ZipUtils;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.auth.service.AttachmentService;
import com.gpcsoft.srplatform.common.utils.AttachmentUtil;

/**
 * 
  *  Comments: <strong>InqpDocController</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   srplatform                    					          
  *  <br/>Module ID: 权限平台     		
  *  <br/>Create Date：2010-8-2 下午03:44:31 by liuke    					                            
  *  <br/>Modified Date:  2010-8-2 下午03:44:31 by liuke                                 
  *      
  *  @since 0.4
  *  @version: 0.5
 */

/**
 * @gpcsoft.view value="InqpDocFormView"
 *  url="view/es/inquiryprice/inqpdoc/inqpDocForm.jsp"
 * @gpcsoft.view value="InqpDocDetailView"
 *  url="view/es/inquiryprice/inqpdoc/inqpDocDetail.jsp"
 * @gpcsoft.view value="updateInqpDocFormView"
 *  url="view/es/inquiryprice/inqpdoc/updateInqpDocForm.jsp"  
 * @gpcsoft.view value="InqpDocConfigView"
 *  url="view/es/inquiryprice/inqpdoc/inqpDocConfig.jsp" 
 * @gpcsoft.view value="InqpDocProcurementView"
 *  url="view/es/inquiryprice/inqpdoc/inqpDocProcurement.jsp"  
 * @gpcsoft.view value="moreInqpDocRecordListView"
 *  url="view/es/inquiryprice/inqpdoc/moreInqpDocRecordList.jsp"    
 * @gpcsoft.view value="configInqpDocPageView"
 *  url="view/es/inquiryprice/inqpdoc/configInqpDocPage.jsp"    
 * @gpcsoft.view value="auditingInqpDocPageView"
 *  url="view/es/inquiryprice/inqpdoc/inqpDocProcurement.jsp"    
 * @gpcsoft.view value="InqpDocDetailForSupplierView"
 *  url="view/es/inquiryprice/inqpdoc/inqpDocDetailForSupplier.jsp"    
 * @gpcsoft.view value="inqpDocAuditingForJCJView"
 *  url="view/es/inquiryprice/inqpdoc/inqpDocAuditingForJCJ.jsp"    
 * @gpcsoft.view value="blankView"
 *  url="view/es/common/blank.jsp"    
 *  
 *   
 *  
 */

@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={PurchaseDoc.class})
@RequestMapping("/InqpDocController.do")//页面请求路径,可修改
@SuppressWarnings("unchecked")
public class InqpDocController extends AnnotationMultiController<PurchaseDoc> {
    
	@Autowired(required=true) @Qualifier("purchaseDocServiceImpl")
	private PurchaseDocService purchaseDocService;

	@Autowired(required=true) @Qualifier("attachmentServiceImpl")
	private AttachmentService attachmentService;
	
	@Autowired(required=true) @Qualifier("projectServiceImpl")
	private ProjectService projectService;	
	
	@Autowired(required=true) @Qualifier("signUprecordServiceImpl")
	private SignUprecordService signUprecordService;
	
	
	/**
	 * 
	 * Description : 跳转到代理机构制作询价文件页面
	 * Create Date: 2010-8-2下午03:31:47 by liuke  Modified Date: 2010-8-2下午03:31:47 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=toAgentMadePurchaseDoc")
	public ModelAndView toAgentMadePurchaseDoc(HttpServletRequest request)throws Exception {
		logger.debug("\nes InqpDocController||toAgentMadePurchaseDoc\n");
		String projectId = request.getParameter("projectId");
		String objId= request.getParameter("objId");
		Map model = new HashMap();
		String filePrice = "";
		PurchaseDoc purchaseDoc = null;
		String returnName = "";
		if(null!=projectId&&!"".equals(projectId)){//从项目列表进入
			 Project project = projectService.getProjectByObjId(projectId);		
			 purchaseDoc = purchaseDocService.getInqpDocByProjectId(projectId);
			 model.put("fromType", "fromList");
			 if(project.getPurDocPrice()!=null&&!"".equals(project.getPurDocPrice())){//录入项目信息录入招标文件价格
				filePrice = project.getPurDocPrice().toString();
			 }else{
				filePrice = "0.0";
			 }
		}else if(null!=objId&&!"".equals(objId)){//从我的桌面进入
			 purchaseDoc = purchaseDocService.getInqpDocByObjId(objId);
			 projectId = purchaseDoc.getProject().getObjId();
			 Project project = projectService.getProjectByObjId(projectId);
				if(project.getPurDocPrice()!=null&&!"".equals(project.getPurDocPrice())){//录入项目信息录入招标文件价格
					filePrice = project.getPurDocPrice().toString();
				}else{
					filePrice = "0.0";
				}
			 model.put("fromType", "fromDesk");
		}
		if(null!=purchaseDoc){
			List<Attachment> attachmentList = attachmentService.getAttachmentsByRealID(purchaseDoc.getAttachRelaId());
			if (null != attachmentList && !attachmentList.isEmpty()) {
				Attachment attachment = attachmentList.get(0);
				model.put("attrId", attachment.getObjId());
				model.put("attrName", attachment.getViewName());
				String path = messageSource.getMessage("epp.projectAttaPath")+attachment.getPath()+File.separator;
				String filePath= messageSource.getMessage("epp.projectAttaPath")+attachment.getPath()+File.separator+attachment.getFileName().replace("/", File.separator);//换回绝对路径
				try {
					ZipUtils.unZip(filePath,path);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			    String[] l = FileUtil.listFiles(path);
		        String viewName = "";
		        if(l!=null){
		        	  for (int i = 0; i < l.length; i++) {
				        	String endName = "";
				        	if(l[i].indexOf(".")!=-1){
				        		 endName = l[i].substring(l[i].indexOf("."));
				        	}
				        	if(endName.equals(".doc")||endName.equals(".docx")||endName.equals(".pdf")){
				        		viewName = attachment.getViewName().substring(0,attachment.getViewName().length()-4)+endName;
							}
						}
		        }else{
		        	model.put("message", ((FrameMessageResource)FrameBeanFactory.getBean(FrameMessageResource.BEAN_NAME)).getMessage("srplatform.auth.attachment.file_is_not_exit"));
		        }
				model.put("attrName2", viewName);
			}
		}
		if(null != purchaseDoc && null != purchaseDoc.getObjId()){//若已上传采购文件，则跳到显示页面，若没有，则跳到新增页面
			if(CommonEnum.USER_STATUS_TEMP.equals(purchaseDoc.getUseStatus())||PurchaseDocEnum.RETURNBACK.equals(purchaseDoc.getAuditStatus())){ //表示该文件被退回
				model.put("purchaseDoc", purchaseDoc);
				returnName = "updateInqpDocFormView";
			}
			else {
				model.put("purchaseDoc", purchaseDoc);
				returnName = "InqpDocDetailView";
			  }
		}else{
				returnName = "InqpDocFormView";
		}
		 model.put("projectId",projectId);
		 model.put("filePrice", filePrice);
		return new ModelAndView(returnName,model);
	}
	
	/**
	 * 
	 * Description :保存询价文件 
	 * Create Date: 2010-8-2下午04:40:23 by liuke  Modified Date: 2010-8-2下午04:40:23 by liuke
	 * Modified Date: 2011-3-11 15:38 by zhouzhanghe
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=savePurchaseDoc")
    public ModelAndView savePurchaseDoc(HttpServletRequest request, PurchaseDoc purchaseDoc, SessionStatus stauts)throws Exception {
		logger.debug("\nes InqpDocController||savePurchaseDoc\n");
		if(null==purchaseDoc.getObjId()){//如果第一次创建询价文件
			purchaseDoc.setFileType(PurchaseDocEnum.INQPDOC);
		}
		Boolean isUploadFile = new Boolean(request.getParameter("isUploadFile"));// 判断是否上传附件 add 2010-07-20 11:00 by wanghz
		if(isUploadFile){
			Object o=AttachmentUtil.uploadFile(request,"attachFile");
			if(o instanceof GpcBaseObject){
				Attachment attachment = (Attachment)o;
				attachmentService.saveAttachment((Attachment)o);
				purchaseDoc.setAttachRelaId(attachment.getRelationId());
			}
		}
		//将改变业务对象的状态放入决策类里 modified by zhouzhanghe at 2011.3.11 13:54
		//purchaseDoc.setAuditStatus(PurchaseDocEnum.FORAUDIT);  //FORAUDIT:00:待采购人确认,BUYERPASS:01:采购人确认通过,SUPERVISALOFFICEPASS:02:监察局审核通过,PURCHASEOFFICEPASS:03:采购办审核通过,RETURNBACK:04:被退回,SUPERVISALOFFICE_WAIT:05:待监察局审核,PURCHASEOFFICE_WAIT:06:待采购办审核
		purchaseDocService.saveInqpDoc(purchaseDoc);
		stauts.setComplete();
    	Map model = new HashMap();
    	return new ModelAndView(Constants.JSON_VIEW, model);
    }
	
	/**
	 * 
	 * Description :修改询价文件 
	 * Create Date: 2010-9-14上午10:27:32 by liuke  Modified Date: 2010-9-14上午10:27:32 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=updatePurchaseDoc")
    public ModelAndView updatePurchaseDoc(HttpServletRequest request, PurchaseDoc purchaseDoc, SessionStatus stauts)throws Exception {
		logger.debug("\nes InqpDocController||updatePurchaseDoc\n");
		if(null==purchaseDoc.getObjId()){//如果第一次创建询价文件
			purchaseDoc.setFileType(PurchaseDocEnum.INQPDOC);
		}
		Boolean isUploadFile = new Boolean(request.getParameter("isUploadFile"));	// 判断是否上传附件 add 2010-07-20 11:00 by wanghz
		if(isUploadFile){
			Object o=AttachmentUtil.uploadFile(request,"attachFile");
			if(o instanceof GpcBaseObject){
				Attachment attachment = (Attachment)o;
				attachmentService.saveAttachment((Attachment)o);
				purchaseDoc.setAttachRelaId(attachment.getRelationId());
			}
		}
		purchaseDoc.setFileType(PurchaseDocEnum.INQPDOC);
		purchaseDoc.setUseStatus(CommonEnum.USER_STATUS_FORMAL);
		purchaseDoc.setAuditStatus(PurchaseDocEnum.FORAUDIT);  //FORAUDIT:00:待采购人确认,BUYERPASS:01:采购人确认通过,SUPERVISALOFFICEPASS:02:监察局审核通过,PURCHASEOFFICEPASS:03:采购办审核通过,RETURNBACK:04:被退回,SUPERVISALOFFICE_WAIT:05:待监察局审核,PURCHASEOFFICE_WAIT:06:待采购办审核
		purchaseDocService.saveInqpDoc(purchaseDoc);
		stauts.setComplete();
    	Map model = new HashMap();
    	return new ModelAndView(Constants.JSON_VIEW, model);
    }
	
    /**
     * 
     * Description :  购买人转到确认询价文件页面
     * Create Date: 2010-8-2下午05:33:39 by liuke  Modified Date: 2010-8-2下午05:33:39 by liuke
     * @param   
     * @return  
     * @Exception
     */
	@RequestMapping(params="method=toPurchaseDocConfig")
	public ModelAndView toPurchaseDocConfig(HttpServletRequest request)throws Exception {
		logger.debug("\nes InqpDocController||toPurchaseDocConfig\n");
		String objId= request.getParameter("objId");
		String projectId = request.getParameter("projectId");
		Map model = new HashMap();
		model.put("projectId",projectId);
		PurchaseDoc purchaseDoc = null;
		String returnName = "InqpDocDetailView";
		if(null!=projectId&&!"".equals(projectId)){//从项目列表进入
			 purchaseDoc = purchaseDocService.getInqpDocByProjectId(projectId);
			 model.put("fromType", "fromList");
		}else if(null!=objId&&!"".equals(objId)){//从我的桌面进入
			 purchaseDoc = purchaseDocService.getInqpDocByObjId(objId);
			 projectId = purchaseDoc.getProject().getObjId();
			 model.put("projectId",projectId);
			 model.put("fromType", "fromDesk");
		}
		if (null != purchaseDoc) {
			List<Attachment> attachmentList = attachmentService.getAttachmentsByRealID(purchaseDoc.getAttachRelaId());
			if (null != attachmentList && !attachmentList.isEmpty()) {
				Attachment attachment = attachmentList.get(0);
				String path = messageSource.getMessage("epp.projectAttaPath")+attachment.getPath()+File.separator;
				String filePath=messageSource.getMessage("epp.projectAttaPath")+attachment.getPath()+File.separator+attachment.getFileName().replace("/", File.separator);//换回绝对路径
				try {
					ZipUtils.unZip(filePath,path);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			    String[] l = FileUtil.listFiles(path);
		        String viewName = "";
		        if(l!=null){
		        	  for (int i = 0; i < l.length; i++) {
				        	String endName = "";
				        	if(l[i].indexOf(".")!=-1){
				        		 endName = l[i].substring(l[i].indexOf("."));
				        	}
				        	if(endName.equals(".doc")||endName.equals(".docx")||endName.equals(".pdf")){
				        		viewName = attachment.getViewName().substring(0,attachment.getViewName().length()-4)+endName;
							}
						}
		        }else{
		        	model.put("message", ((FrameMessageResource)FrameBeanFactory.getBean(FrameMessageResource.BEAN_NAME)).getMessage("srplatform.auth.attachment.file_is_not_exit"));
		        }
				model.put("attrName2", viewName);
			}
		}
		if(null != purchaseDoc && null != purchaseDoc.getObjId()){
			model.put("purchaseDoc", purchaseDoc);
			if(null!=purchaseDoc.getAuditStatus()&&purchaseDoc.getAuditStatus().equals(PurchaseDocEnum.FORAUDIT)) {
				returnName = "InqpDocConfigView";
			}
		}
		
		return new ModelAndView(returnName, model);
	}
	
	/**
	 * 
	 * Description :购买人确认询价文件  
	 * Create Date: 2010-8-2下午05:43:15 by liuke  Modified Date: 2010-8-2下午05:43:15 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	
	@RequestMapping(params="method=purchaseDocConfig")
	public ModelAndView purchaseDocConfig(String objId,String ids,String opinion,String auditStatus,String workFlowTaskId, HttpServletRequest request)throws Exception {
		logger.debug("\nes InqpDocController||purchaseDocConfig\n");
		if(ids==null||ids.equals("")){
			ids = objId;
		}
		purchaseDocService.saveConfirmInqpDoc(ids,opinion,auditStatus,workFlowTaskId);
		return new ModelAndView(Constants.JSON_VIEW);	
	}
	
	/**
	 * 
	 * Description :  监察局转到审核询价文件页面
	 * Create Date: 2010-5-27上午11:35:18 by liuke  Modified Date: 2010-5-27上午11:35:18 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */  
	@RequestMapping(params="method=toPurchaseDocAuditingForJCJ")
	public ModelAndView toPurchaseDocAuditingForJCJ(HttpServletRequest request)throws Exception {
		logger.debug("\nes InqpDocController||toPurchaseDocAuditingForJCJ\n");
		String projectId = request.getParameter("projectId");
		String purchaseDocId = request.getParameter("objId");
		PurchaseDoc purchaseDoc = purchaseDocService.getInqpDocByProjectId(projectId);
		Map model = new HashMap();
		if(null==projectId||"".equals(projectId)){
			 purchaseDoc = purchaseDocService.getInqpDocByObjId(purchaseDocId);
			 projectId = purchaseDoc.getProject().getObjId();
		}
		if(null!=purchaseDoc){
			List<Attachment> attachmentList = attachmentService.getAttachmentsByRealID(purchaseDoc.getAttachRelaId());
			if (null != attachmentList && !attachmentList.isEmpty()) {
				model.put("attrId", attachmentList.get(0).getObjId());
				model.put("attrName", attachmentList.get(0).getViewName());
			}
		}
		String returnUrl = "InqpDocDetailView";
		model.put("projectId", projectId);
		if(null != purchaseDoc && null != purchaseDoc.getObjId()){
			model.put("purchaseDoc", purchaseDoc);
			if(null!=purchaseDoc.getAuditStatus()&&purchaseDoc.getAuditStatus().equals(PurchaseDocEnum.SUPERVISALOFFICE_WAIT)) {
				returnUrl = "inqpDocAuditingForJCJView";
			}
		}
		return new ModelAndView(returnUrl, model);
	}
	
	/**
	 * 
	 * Description : 跳转到采购办审核询价文件页面
	 * Create Date: 2010-5-27上午11:35:18 by liuke  Modified Date: 2010-5-27上午11:35:18 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */  
	@RequestMapping(params="method=toSuperviseAuditPurchaseDoc")
	public ModelAndView toSuperviseAuditPurchaseDoc(HttpServletRequest request)throws Exception {
		logger.debug("\nes InqpDocController||toSuperviseAuditPurchaseDoc\n");
		String projectId = request.getParameter("projectId");
		String objId= request.getParameter("objId");
		Map model = new HashMap();
		model.put("projectId",projectId);
		PurchaseDoc purchaseDoc = null;
		String returnName = "InqpDocDetailView";
		if(null!=projectId&&!"".equals(projectId)){//从项目列表进入
			 purchaseDoc = purchaseDocService.getInqpDocByProjectId(projectId);
			 model.put("fromType", "fromList");
		}else if(null!=objId&&!"".equals(objId)){//从我的桌面进入
			 purchaseDoc = purchaseDocService.getInqpDocByObjId(objId);
			 projectId = purchaseDoc.getProject().getObjId();
			 model.put("projectId",projectId);
			 model.put("fromType", "fromDesk");
		}
		if(null!=purchaseDoc){
			List<Attachment> attachmentList = attachmentService.getAttachmentsByRealID(purchaseDoc.getAttachRelaId());
			if (null != attachmentList && !attachmentList.isEmpty()) {
				Attachment attachment = attachmentList.get(0);
				model.put("attrId", attachment.getObjId());
				model.put("attrName", attachment.getViewName());
				String path = messageSource.getMessage("epp.projectAttaPath")+attachment.getPath()+File.separator;
				String filePath= messageSource.getMessage("epp.projectAttaPath")+attachment.getPath()+File.separator+attachment.getFileName().replace("/", File.separator);//换回绝对路径
				try {
					ZipUtils.unZip(filePath,path);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			    String[] l = FileUtil.listFiles(path);
		        String viewName = "";
		        if(l!=null){
		        	  for (int i = 0; i < l.length; i++) {
				        	String endName = "";
				        	if(l[i].indexOf(".")!=-1){
				        		 endName = l[i].substring(l[i].indexOf("."));
				        	}
				        	if(endName.equals(".doc")||endName.equals(".docx")||endName.equals(".pdf")){
				        		viewName = attachment.getViewName().substring(0,attachment.getViewName().length()-4)+endName;
							}
						}
		        }else{
		        	model.put("message", ((FrameMessageResource)FrameBeanFactory.getBean(FrameMessageResource.BEAN_NAME)).getMessage("srplatform.auth.attachment.file_is_not_exit"));
		        }
				model.put("attrName2", viewName);
			}
		}
		if(null != purchaseDoc && null != purchaseDoc.getObjId()){
			model.put("purchaseDoc", purchaseDoc);
			if(null!=purchaseDoc.getAuditStatus()&&purchaseDoc.getAuditStatus().equals(PurchaseDocEnum.PURCHASEOFFICE_WAIT)) {
				returnName = "InqpDocProcurementView";
			}
		}
		return new ModelAndView(returnName, model);
	}
	
	/**
	 * 
	 * Description : 采购办审核询价文件 
	 * Create Date: 2010-8-2下午06:30:45 by liuke  Modified Date: 2010-8-2下午06:30:45 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=purchaseDocDeptAudit")
	public ModelAndView purchaseDocDeptAudit(String objId,String ids,String opinion,String auditStatus,String workFlowTaskId, HttpServletRequest request)throws Exception {
		logger.debug("\nes InqpDocController||purchaseDocDeptAudit\n");
		if(ids==null||ids.equals("")){
			ids = objId;
		}
		purchaseDocService.auditInqpDoc(ids,opinion,auditStatus,workFlowTaskId);
		return new ModelAndView(Constants.JSON_VIEW);	
	}
	
	/**
	 * 
	 * Description :  监察局审核询价文件
	 * Create Date: 2010-5-25下午02:34:40 by liuke  Modified Date: 2010-5-25下午02:34:40 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=purchaseDocAuditing")
	public ModelAndView purchaseDocAuditing(String objId,String ids,String opinion,String auditStatus,String workFlowTaskId, HttpServletRequest request)throws Exception {
		logger.debug("\nes InqpDocController||purchaseDocDeptAudit\n");
		if(ids==null||ids.equals("")){
			ids = objId;
		}
		purchaseDocService.auditForJcjInqpDoc(ids,opinion,auditStatus,workFlowTaskId);
		return new ModelAndView(Constants.JSON_VIEW);	
	}
	
	/** 
	 * Description : 获取询价文件信息列表 （点击更多按钮以后）
	 * Create Date: 2010-6-23下午03:15:50 by liuke  Modified Date: 2010-6-23下午03:15:50 by liuke
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=searchPurchaseDocByQueryObject")
	public ModelAndView searchPurchaseDocByQueryObject(HttpServletRequest request,SessionStatus status)throws Exception {
		logger.debug("\nes InqpDocController||searchPurchaseDocByQueryObject\n");
		String auditStatus = request.getParameter("auditStatus");
		String projCode = request.getParameter("projCode");
		String projName = request.getParameter("projName");
		String userType = request.getParameter("userType");// 用户类型{桌面代办列表}
		if (null == userType || "NULL".equals(userType.toUpperCase()) || "undefined".equals(userType)) {
			userType = "";
		}
		User user = AuthenticationHelper.getCurrentUser();
		OrgInfo orgInfo = (OrgInfo)user.getOrgInfo();
		Page page = prePage(request);
		QueryObject<SignUprecord> queryObject = new QueryObjectBase<SignUprecord>();
		if(auditStatus!=null&&!"".equals(auditStatus)){
			queryObject.getQueryParam().and(new QueryParam("auditStatus",QueryParam.OPERATOR_EQ,auditStatus));
		}
		if(projCode!=null&&!"".equals(projCode)){
			queryObject.getQueryParam().and(new QueryParam("projCode",QueryParam.OPERATOR_EQ,projCode));
		}
		if(projName!=null&&!"".equals(projName)){
			queryObject.getQueryParam().and(new QueryParam("projName",QueryParam.OPERATOR_EQ,projName));
		}
		queryObject.getQueryParam().and(new QueryParam("fileType",QueryParam.OPERATOR_EQ,PurchaseDocEnum.INQPDOC));
		Page<PurchaseDoc> pageData = purchaseDocService.searchPurchaseDocsByQueryObject(page,queryObject);
		Map model = new HashMap();
		endPage(model, pageData, request);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  跳转到询价文件信息列表：根据状态（点击更多按钮）
	 * Create Date: 2010-7-12下午05:25:36 by liuke  Modified Date: 2010-7-12下午05:25:36 by liuke
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=getMorePurchaseDocRecordList")
	public ModelAndView getMorePurchaseDocRecordList(HttpServletRequest request)throws Exception {
		logger.debug("\nes InqpDocController||getMorePurchaseDocRecordList\n");
		String auditStatus = request.getParameter("auditStatus");
		Map model = new HashMap();
		model.put("auditStatus", auditStatus);
		model.put("fileType",PurchaseDocEnum.INQPDOC );
		return new ModelAndView("moreInqpDocRecordListView", model);
	}
	
	/** 
	 * Description :  [采购人]得到待确认询价文件列表 (待办任务列表显示)
	 * Create Date: 2010-8-3下午05:18:09 by yangx  Modified Date: 2010-8-3下午05:18:09 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=getInqpDocByBuyer")
	public ModelAndView getInqpDocByBuyer(HttpServletRequest request)throws Exception {
		logger.debug("\nes InqpDocController||getInqpDocByBuyer\n");
		Page<PurchaseDoc> page = prePage(request);
		Map<String, Object> model = new HashMap<String, Object>();
		User user=AuthenticationHelper.getCurrentUser();
		Page<PurchaseDoc> pageData=purchaseDocService.getPurchaseDocByBuyer(page,user,PurchaseDocEnum.INQPDOC);
		endPage(model, pageData, request);
    	return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  [采购办]得到待审核询价文件列表(查询待办任务列表) 
	 * Create Date: 2010-8-3下午05:51:25 by yangx  Modified Date: 2010-8-3下午05:51:25 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=getInqpDocByUser")
	public ModelAndView getInqpDocByUser(HttpServletRequest request)throws Exception {
		logger.debug("\nes InqpDocController||getInqpDocByUser\n");
		Page<PurchaseDoc> page = prePage(request);
		Map<String, Object> model = new HashMap<String, Object>();
		User user=AuthenticationHelper.getCurrentUser();
		QueryObject queryObject = QueryWebUtils.getQuery(request, PurchaseDoc.class);
		Page<PurchaseDoc> pageData=purchaseDocService.getPurchaseDocByUser(page,queryObject,user,PurchaseDocEnum.INQPDOC);
		endPage(model, pageData, request);
    	return new ModelAndView(Constants.JSON_VIEW, model);
	}
	/**
	 * 
	 * Description :供应商跳转询价文件页面
	 * Create Date: 2010-5-19下午05:40:39 by liuke  Modified Date: 2010-5-19下午05:40:39 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=toPurchaseDocbuyer")
	public ModelAndView toPurchaseDocbuyer(HttpServletRequest request)throws Exception {
		String projectId = request.getParameter("projectId");
		User user=AuthenticationHelper.getCurrentUser();
		Map model = new HashMap();
		model.put("projectId", projectId);
		PurchaseDoc purchaseDoc = purchaseDocService.getInqpDocByProjectId(projectId);
		String returnUrl = "";
		if(null!=purchaseDoc){
			List<Attachment> attachmentList = attachmentService.getAttachmentsByRealID(purchaseDoc.getAttachRelaId());
			if (null != attachmentList && !attachmentList.isEmpty()) {
				Attachment attachment = attachmentList.get(0);
				model.put("attrId", attachment.getObjId());
				model.put("attrName", attachment.getViewName());
				String path = messageSource.getMessage("epp.projectAttaPath")+attachment.getPath()+File.separator;
				String filePath= messageSource.getMessage("epp.projectAttaPath")+attachment.getPath()+File.separator+attachment.getFileName().replace("/", File.separator);//换回绝对路径
				try {
					ZipUtils.unZip(filePath,path);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			    String[] l = FileUtil.listFiles(path);
		        String viewName = "";
		        if(l!=null){
		        	  for (int i = 0; i < l.length; i++) {
				        	String endName = "";
				        	if(l[i].indexOf(".")!=-1){
				        		 endName = l[i].substring(l[i].indexOf("."));
				        	}
				        	if(endName.equals(".doc")||endName.equals(".docx")||endName.equals(".pdf")){
				        		viewName = attachment.getViewName().substring(0,attachment.getViewName().length()-4)+endName;
							}
						}
		        }else{
		        	model.put("message", ((FrameMessageResource)FrameBeanFactory.getBean(FrameMessageResource.BEAN_NAME)).getMessage("srplatform.auth.attachment.file_is_not_exit"));
		        }
				model.put("attrName2", viewName);
			}
		}
		String message ="";
		model.put("message", message);
		SignUprecord signUprecord = signUprecordService.getSignUprecordBySupplierId(projectId, user);//查询供应商的报名信息
		if(null != purchaseDoc && null != purchaseDoc.getObjId()){//若有文件，则跳到文件显示页面，若无文件，则跳到空白页面
			/** 审核通过 */
			if(purchaseDoc.getAuditStatus()!=null&&
				(PurchaseDocEnum.PURCHASEOFFICEPASS).equals(purchaseDoc.getAuditStatus())&&
				 signUprecord!=null&&signUprecord.getObjId()!=null&&
				(AuditStatusEnum.AUDIT_PASS).equals(signUprecord.getAuditStatus())){
				model.put("purchaseDoc", purchaseDoc);
				returnUrl = "InqpDocDetailView";
			} else{ /** 审核没通过 */
				returnUrl = "blankView";
			}	
		}else{
			returnUrl = "blankView";
		}
		return new ModelAndView(returnUrl, model);
	}
	
	/**
	 * FuncName: purchaseDocAuditing
	 * Description :  创建或修改对象
	 * @param 
	 * @param objId
	 * @param ids
	 * @param opinion
	 * @param auditStatus
	 * @param workFlowTaskId
	 * @param request
	 * @return
	 * @throws Exception ModelAndView
	 * @author: liuke
	 * @Create Date:2011-7-5  下午02:01:51
	 * @Modifier: liuke
	 * @Modified Date:2011-7-5  下午02:01:51
	 */
	@RequestMapping(params="method=toInqpDocDetailView")
	public ModelAndView toInqpDocDetailView(HttpServletRequest request)throws Exception {
		logger.debug("\nes InqpDocController||toInqpDocDetailView\n");
		String projectId = request.getParameter("projectId");
		String returnName = "InqpDocDetailView";
		Map model = new HashMap();
		model.put("fromType", "fromDesk");
		PurchaseDoc	purchaseDoc = purchaseDocService.getInqpDocByProjectId(projectId);
		if(null!=purchaseDoc){ //如果招标文件不为空
			List<Attachment> attachmentList = attachmentService.getAttachmentsByRealID(purchaseDoc.getAttachRelaId());
			if (null != attachmentList && !attachmentList.isEmpty()) {
				Attachment attachment = attachmentList.get(0);
				model.put("attrId", attachment.getObjId());
				model.put("attrName", attachment.getViewName());
				String path = messageSource.getMessage("epp.projectAttaPath")+attachment.getPath()+File.separator;
				String filePath= messageSource.getMessage("epp.projectAttaPath")+attachment.getPath()+File.separator+attachment.getFileName().replace("/", File.separator);//换回绝对路径
				try {
					ZipUtils.unZip(filePath,path);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			    String[] l = FileUtil.listFiles(path);
		        String viewName = "";
		        if(l!=null){
		        	  for (int i = 0; i < l.length; i++) {
				        	String endName = "";
				        	if(l[i].indexOf(".")!=-1){
				        		 endName = l[i].substring(l[i].indexOf("."));
				        	}
				        	if(endName.equals(".doc")||endName.equals(".docx")||endName.equals(".pdf")){
				        		viewName = attachment.getViewName().substring(0,attachment.getViewName().length()-4)+endName;
							}
						}
		        }else{
		        	model.put("message", ((FrameMessageResource)FrameBeanFactory.getBean(FrameMessageResource.BEAN_NAME)).getMessage("srplatform.auth.attachment.file_is_not_exit"));
		        }
				model.put("attrName2", viewName);
			}
			model.put("purchaseDoc", purchaseDoc);
		}else{
			returnName = "blankView";
		}
		return new ModelAndView(returnName,model);	
	}
	
}
