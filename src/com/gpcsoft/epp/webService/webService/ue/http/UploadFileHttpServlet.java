package com.gpcsoft.epp.webService.webService.ue.http;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.context.FrameMessageResource;
import com.gpcsoft.core.utils.UrlDecodeUtil;
import com.gpcsoft.epp.bid.domain.AnonymousEnum;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.common.domain.EsExceptionEnum;
import com.gpcsoft.epp.common.service.UserApiService;
import com.gpcsoft.epp.common.utils.FileUtils;
import com.gpcsoft.epp.common.utils.MD5Util;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.domain.ProjectRule;
import com.gpcsoft.epp.project.manager.ProjectManager;
import com.gpcsoft.epp.purchasedoc.domain.PurchaseDoc;
import com.gpcsoft.epp.purchasedoc.domain.PurchaseDocAtt;
import com.gpcsoft.epp.purchasedoc.domain.PurchaseDocEnum;
import com.gpcsoft.epp.purchasedoc.service.PurchaseDocAttService;
import com.gpcsoft.epp.purchasedoc.service.PurchaseDocService;
import com.gpcsoft.epp.webService.common.util.ZipUtils;
import com.gpcsoft.epp.webService.ueSystem.orgInfo.user.service.UserService;
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.auth.service.AttachmentService;

public class UploadFileHttpServlet extends HttpServlet {
	
	private static Map<String, String> supplerUploadMap=new HashMap<String, String>();
	
	
	protected final Log logger = LogFactory.getLog(this.getClass());
	
	private AttachmentService attachmentService;
	
	private FrameMessageResource  messageSource;
	
	private UserApiService userApiService;
		
	private ProjectManager projectManager;
	
	private PurchaseDocService purchaseDocService;
	
	private PurchaseDocAttService  purchaseDocAttService;
	
	private ProjectManager getProjectManager(){
		if(null == this.projectManager){
			this.projectManager = (ProjectManager)FrameBeanFactory.getBean("projectManagerImpl");
		}
		return this.projectManager;
	}
	
	private AttachmentService getAttachmentService(){
		if(null == this.attachmentService){
			this.attachmentService = (AttachmentService)FrameBeanFactory.getBean("attachmentServiceImpl");
		}
		return this.attachmentService;
	}
	
	private FrameMessageResource getMessageSource() {
		if(null == this.messageSource){
			this.messageSource = (FrameMessageResource)FrameBeanFactory.getBean("frameMessageResource");
		}
		return this.messageSource;
	}
	
	private UserApiService getUserApiService(){
		if(null == this.userApiService){
			this.userApiService = (UserApiService)FrameBeanFactory.getBean("userApiServiceImpl");
		}
		return this.userApiService;
	}
	
 
	private PurchaseDocService getPurchaseDocService(){
		if(null == this.purchaseDocService){
			this.purchaseDocService = (PurchaseDocService)FrameBeanFactory.getBean("purchaseDocServiceImpl");
		}
		return this.purchaseDocService;
	}
	
	private PurchaseDocAttService getPurchaseDocAttService(){
		if(null == this.purchaseDocAttService){
			this.purchaseDocAttService = (PurchaseDocAttService)FrameBeanFactory.getBean("purchaseDocAttServiceImpl");
		}
		return this.purchaseDocAttService;
	}
	
	/**
	* FunName: getSaveFileAbsoluteDirectory
	* Description : 根据文件类型得到文件的绝对存放目录
	* @param： 
	* @return String：文件存放目录
	* @Author: zhouzhanghe
	* @Create Date: 2011-7-5 16:15
	*/
	private String getSaveFileAbsoluteDirectory(HttpServletRequest request){
		String saveFileAbsolutePath = "";
		String fileType = request.getParameter("FileType");//文件类型
		if(CommonEnum.FILE_TYPE_BID.equals(fileType)){//投标文件
			String bidFilePath = this.getMessageSource().getMessage("epp.projectAttaPath");
			saveFileAbsolutePath = bidFilePath + getSaveFileRelativeDirectory(request);
		}else if(CommonEnum.FILE_TYPE_PURCHASE.equals(fileType)||CommonEnum.FILE_TYPE_PURCHASE_BUSINESS.equals(fileType)||CommonEnum.FILE_TYPE_PURCHASE_TECHNICAL.equals(fileType)){//采购文件
			String epp_projectAttaPath = this.getMessageSource().getMessage("epp.projectAttaPath");
			saveFileAbsolutePath = epp_projectAttaPath + getSaveFileRelativeDirectory(request);
		}
		return saveFileAbsolutePath;
	}
	
 
	/**
	* FunName: getSaveFileRelativeDirectory
	* Description : 根据文件类型得到文件的相对存放目录
	* @param： 
	* @return String：文件存放目录
	* @Author: zhouzhanghe
	* @Create Date: 2011-7-5 16:15
	*/
	private String getSaveFileRelativeDirectory(HttpServletRequest request){
		String saveFileRelativePath = "";
		String fileType = request.getParameter("FileType");//文件类型
		
		if(CommonEnum.FILE_TYPE_BID.equals(fileType)){//投标文件
			
			String tenderOrgCode = request.getParameter("tenderOrgCode");  //投标机构编码
			String prjCode = request.getParameter("prjCode");    //项目编码
			String packCode = request.getParameter("packCode");    //包件编码
			String bidNo = request.getParameter("tenderNo");
			Project project =  this.getProjectManager().findProjectByProjCode(prjCode);
			Project	subProject  =  this.getProjectManager().findProjectByProjCodeAndParent(packCode, project.getObjId());
			ProjectRule projectRule = this.getProjectManager().getProjectRuleByProjectId(project.getObjId());
			String packId="";
			if(subProject!=null){
				packId = subProject.getObjId();
			}else{
				packId = project.getObjId();
			}
			OrgInfo orginfo = null;
			try {
				if(projectRule!=null&&AnonymousEnum.isAnonymous.equals(projectRule.getRuleAnonymous())){
					saveFileRelativePath = File.separator + project.getObjId() + File.separator 
					+ packId + File.separator + bidNo + File.separator+ CommonEnum.BID_FILE_DIR + File.separator;
				}else {
					orginfo = this.getUserApiService().getOrgInfoByOrgCode(tenderOrgCode);
					saveFileRelativePath = File.separator + project.getObjId() + File.separator 
											+ packId + File.separator + orginfo.getObjId() + File.separator+ CommonEnum.BID_FILE_DIR + File.separator;
				}
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(CommonEnum.FILE_TYPE_PURCHASE.equals(fileType)||CommonEnum.FILE_TYPE_PURCHASE_BUSINESS.equals(fileType)||CommonEnum.FILE_TYPE_PURCHASE_TECHNICAL.equals(fileType)){//采购文件
			String prjCode = request.getParameter("prjCode");    //项目编码
			Project project =  this.getProjectManager().findProjectByProjCode(prjCode);
			saveFileRelativePath = File.separator+project.getObjId() + File.separator +  CommonEnum.PUR_FILE_DIR + File.separator;
		}
		return saveFileRelativePath;
	}
	
	
	
	/**
	* FunName: getUploadFileName
	* Description : 得到上传的文件名
	* @param： 
	* @Author: zhouzhanghe
	* @Create Date: 2011-7-6 11:34
	*/
	private String getUploadFileName(HttpServletRequest request){
		String fileName = "errorFileName";
		try{
			fileName = request.getHeader("filemd5");
		}catch(Exception e){
			e.printStackTrace();
		}
		return fileName;
		//文件名称
	}
	
	/**
	* FunName: uploadEndSaveAttaBussObject
	* Description : 文件上传结束后做相应的业务操作。
	* @param： 
	* @Author: zhouzhanghe
	* @Create Date: 2011-7-6 11:34
	*/
	private void afterUploadEndSaveAttaBussObject(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String fileSize = request.getHeader("FileSize");//文件大小
		String fileType = request.getParameter("FileType");//文件类型
		String fileName = getUploadFileName(request);
		//保存上传记录到数据库
		Attachment attachment = new Attachment();
		attachment.setFileSize((Long.parseLong(fileSize == null ? "0" : fileSize)));
		attachment.setFileName(fileName);
		attachment.setRelationId(java.util.UUID.randomUUID().toString());
		attachment.setPath(getSaveFileRelativeDirectory(request));
		if(CommonEnum.FILE_TYPE_BID.equals(fileType)){
			attachment = (Attachment) this.getAttachmentService().save(attachment, Attachment.class);
			response.setHeader("FileId", attachment.getObjId());
			/*解压缩到投标目录下*/
			ZipUtils.unZip(getSaveFileAbsoluteDirectory(request) + getUploadFileName(request), getSaveFileAbsoluteDirectory(request)); //解压临时文件
		}else if(CommonEnum.FILE_TYPE_PURCHASE.equals(fileType)){
			String prjCode = request.getParameter("prjCode");    //项目编码
			Project project =  this.getProjectManager().findProjectByProjCode(prjCode);
			attachment.setViewName(project.getProjName()+"采购文件"+"."+CommonEnum.GPCSOFT_FILE_TYPE);
			attachment = (Attachment) this.getAttachmentService().save(attachment, Attachment.class);
			response.setHeader("FileId", attachment.getObjId());
			PurchaseDoc purchaseDoc = getPurchaseDocService().getPurchaseDocByProjectId(project.getObjId());
			if(purchaseDoc == null){
				purchaseDoc = new PurchaseDoc();
				purchaseDoc.setUseStatus(CommonEnum.USER_STATUS_TEMP);
			}
			purchaseDoc.setFileType(PurchaseDocEnum.PURCHASEDOC);
			purchaseDoc.setProject(project);//关联项目
			purchaseDoc.setAttachRelaId(attachment.getRelationId());//关联附件
			purchaseDoc.setCreateUser((User)request.getAttribute("createUser"));//add by sunl:修改桌面待办读取不到的问题
			getPurchaseDocService().save(purchaseDoc);
		}else if(CommonEnum.FILE_TYPE_PURCHASE_BUSINESS.equals(fileType)){//上传的是商务标
			String prjCode = request.getParameter("prjCode");    //项目编码
			String packCode = request.getParameter("packCode");//包组编号
			Project project =  this.getProjectManager().findProjectByProjCode(prjCode);
			Project pack =  this.getProjectManager().findProjectByProjCodeAndParent(packCode, project.getObjId());
			attachment.setViewName(project.getProjName()+(pack != null&&pack.getObjId()!=null ?pack.getProjName():"")+"商务标"+"."+CommonEnum.GPCSOFT_FILE_TYPE);
			attachment = (Attachment) this.getAttachmentService().save(attachment, Attachment.class);
			response.setHeader("FileId", attachment.getObjId());
			PurchaseDoc purchaseDoc = getPurchaseDocService().getPurchaseDocByProjectId(project.getObjId());
			PurchaseDocAtt  purchaseDocAtt =  getPurchaseDocAttService().getPurchaseDocAtt(project.getObjId(), pack.getObjId(), CommonEnum.FILE_TYPE_PURCHASE_BUSINESS_STATUS);
			if(purchaseDoc == null){
				purchaseDoc = new PurchaseDoc();
				purchaseDoc.setUseStatus(CommonEnum.USER_STATUS_TEMP);
			}
			if(purchaseDocAtt==null){
				purchaseDocAtt = new PurchaseDocAtt();
			}
			purchaseDoc.setFileType(PurchaseDocEnum.PURCHASEDOC);
			purchaseDoc.setProject(project);//关联项目
			purchaseDoc.setBidBusinessFile(attachment.getRelationId());//关联附件
			purchaseDoc.setCreateUser((User)request.getAttribute("createUser"));//add by sunl:修改桌面待办读取不到的问题
			getPurchaseDocService().save(purchaseDoc);
			purchaseDocAtt.setFileType(CommonEnum.FILE_TYPE_PURCHASE_BUSINESS_STATUS);
			purchaseDocAtt.setAttRaId(attachment.getRelationId());
			purchaseDocAtt.setPurchaseDoc(purchaseDoc);
			purchaseDocAtt.setTenderId(project.getObjId());
			purchaseDocAtt.setPackId(pack.getObjId());
			getPurchaseDocAttService().save(purchaseDocAtt);
		}else if(CommonEnum.FILE_TYPE_PURCHASE_TECHNICAL.equals(fileType)){//上传的是技术标
			String prjCode = request.getParameter("prjCode");    //项目编码
			String packCode = request.getParameter("packCode");	//包组编号
			Project project =  this.getProjectManager().findProjectByProjCode(prjCode);
			Project pack =  this.getProjectManager().findProjectByProjCodeAndParent(packCode, project.getObjId());
			attachment.setViewName(project.getProjName()+(pack != null&&pack.getObjId()!=null ?pack.getProjName():"")+"技术标"+"."+CommonEnum.GPCSOFT_FILE_TYPE);
			attachment = (Attachment) this.getAttachmentService().save(attachment, Attachment.class);
			response.setHeader("FileId", attachment.getObjId());
			PurchaseDoc purchaseDoc = getPurchaseDocService().getPurchaseDocByProjectId(project.getObjId());
			PurchaseDocAtt  purchaseDocAtt =  getPurchaseDocAttService().getPurchaseDocAtt(project.getObjId(), pack.getObjId(), CommonEnum.FILE_TYPE_PURCHASE_TECHNICAL_STATUS);
			if(purchaseDoc == null){
				purchaseDoc = new PurchaseDoc();
				purchaseDoc.setUseStatus(CommonEnum.USER_STATUS_TEMP);
			}
			if(purchaseDocAtt==null){
				purchaseDocAtt = new PurchaseDocAtt();
			}
			purchaseDoc.setFileType(PurchaseDocEnum.PURCHASEDOC);
			purchaseDoc.setProject(project);//关联项目
			purchaseDoc.setBidTechnicalFile(attachment.getRelationId());//关联附件
			purchaseDoc.setCreateUser((User)request.getAttribute("createUser"));//add by sunl:修改桌面待办读取不到的问题
			getPurchaseDocService().save(purchaseDoc);
			purchaseDocAtt.setFileType(CommonEnum.FILE_TYPE_PURCHASE_TECHNICAL_STATUS);
			purchaseDocAtt.setAttRaId(attachment.getRelationId());
			purchaseDocAtt.setPurchaseDoc(purchaseDoc);
			purchaseDocAtt.setTenderId(project.getObjId());
			purchaseDocAtt.setPackId(pack.getObjId());
			getPurchaseDocAttService().save(purchaseDocAtt);
		}
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("gb2312");//设置输出流编码
		String md5 = "";//存放上传文件的MD5值
		try {
			//验证用户名密码
			String Account = URLDecoder.decode(request.getHeader("Account"),"UTF-8");
			String Password = request.getHeader("Password");
	 
			/*暂时屏蔽掉用户认证
			 * if(Account==null||Password==null||"".equals(Account)||"".equals(Password)){
				write("{'success':false,'msg':'用户名或密码不能为空'}", response);
				return;
			}	
*/
			//验证上传范围
			String fileType = request.getParameter("FileType");//文件类型
			String range = request.getHeader("Range");//当次上传范围
			String rangePath = "";
			if(range == null || range.split("-").length != 2){
				logger.error("无效的上传范围," + range);
				return;
			}
			//验证输入参数
			if(fileType == null || request.getParameter("FileName") == null){
				logger.error("Signature、fileType、fileName不能为空");
				return;
			}
			if(range!=null&&range.indexOf("=")!=-1){    //截取bytes=字符串
				rangePath = range.substring(range.indexOf("=")+1);
			}
			
			//在map中保存文件上传路径，避免每次多线程调用都查询数据库
			String prjCode = request.getParameter("prjCode");    //项目编码
			String packCode = request.getParameter("packCode");    //包件编码
			String tenderOrgCode = request.getParameter("tenderOrgCode");
			String bidNo= request.getParameter("tenderNo");
			String tempPath = prjCode+"-"+packCode+"-"+tenderOrgCode+"-"+fileType;
			if(bidNo!=null){
				tempPath = prjCode+"-"+packCode+"-"+bidNo+"-"+fileType;
			}
			String absolutePath =supplerUploadMap.get(tempPath);
			
			if(absolutePath==null||absolutePath.length()==0){
				//创建文件上传路径
				String subDirectory =getSaveFileAbsoluteDirectory(request);
				absolutePath = (String)subDirectory;
				supplerUploadMap.put(tempPath, absolutePath);
			}
			
			String filePath = absolutePath + getUploadFileName(request);
			File absolutePathFile = new File(filePath);
			// 如果目录不存在，则创建目录
			if (!absolutePathFile.getParentFile().exists()) {
				absolutePathFile.getParentFile().mkdirs(); 
			}
			
			//处理结束标志
			if("0-0".equals(rangePath)){
				md5 = MD5Util.getFileMD5String(absolutePathFile);//读取整个文件MD5值		
				List<User> creatorList = null;
				if(userApiService == null) {
					creatorList = ((UserApiService)FrameBeanFactory.getBean("userApiServiceImpl")).getUserByUserName(Account, Password);
				}
				request.setAttribute("createUser",creatorList==null||creatorList.isEmpty()?null:creatorList.get(0));
				afterUploadEndSaveAttaBussObject(request, response);
				supplerUploadMap.clear();
			}//处理非结束标志
			else{
				String[] parts = rangePath.split("-");
				int startPosition = Integer.parseInt(parts[0].trim());
				int endPosition = Integer.parseInt(parts[1].trim());
				if(startPosition < 0 || endPosition < 0 || startPosition >= endPosition){
					logger.error("upload range is error," + range);
					return;
				}
				//上传文件，并做MD5，同时操作减少内存的使用量
				md5 = FileUtils.randomWriteInputStreamToFileAndMd5(request.getInputStream(), startPosition, filePath);				
			}
			response.addHeader("MD5", md5);
			logger.debug(md5);
		} catch (Exception e) {
			response.addHeader("operTag", "N");  //上传异常
			response.addHeader("operDesc", EsExceptionEnum.ES_SYSTEM_ERROR); //文件未找到错误
			e.printStackTrace();
		}finally{
			response.getOutputStream().close();  
			request.getInputStream().close();
			System.gc();
		}
		
	}

	 private void write(String json, HttpServletResponse response)
     throws Exception{
     response.setCharacterEncoding("UTF-8");
     PrintWriter out = response.getWriter();
     out.print(json.trim());
     out.close();
	 }
	

}
