/**
 * 
 */
package com.gpcsoft.epp.webService.webService.ue.http;

import java.io.File;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.utils.MathUtil;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.common.domain.AuditStatusEnum;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.common.service.UserApiService;
import com.gpcsoft.epp.common.utils.StringUtil;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.service.ProjectService;
import com.gpcsoft.epp.purchasedoc.domain.ProcFileDownRec;
import com.gpcsoft.epp.purchasedoc.domain.PurchaseDoc;
import com.gpcsoft.epp.purchasedoc.domain.PurchaseDocAtt;
import com.gpcsoft.epp.purchasedoc.service.ProcFileDownRecService;
import com.gpcsoft.epp.purchasedoc.service.PurchaseDocAttService;
import com.gpcsoft.epp.purchasedoc.service.PurchaseDocService;
import com.gpcsoft.epp.signuprecord.domain.SignUprecord;
import com.gpcsoft.epp.signuprecord.service.SignUprecordService;
import com.gpcsoft.epp.webService.common.util.ZipUtils;
import com.gpcsoft.epp.webService.ueSystem.bulletin.dto.BulletinDTO;
import com.gpcsoft.epp.webService.ueSystem.bulletin.dto.PurchaseFileDTO;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.auth.service.AttachmentService;

/**
 * @author liuk
 *
 */
@SuppressWarnings("unchecked")
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={BulletinDTO.class})
@RequestMapping("/ProjectPurchaseFileHttpService.do")//页面请求路径,可修改
public class ProjectPurchaseFileHttp extends AnnotationMultiController{

	@Autowired(required=true) @Qualifier("purchaseDocServiceImpl")
	private PurchaseDocService purchaseDocService;

	@Autowired(required=true) @Qualifier("projectServiceImpl")
	private ProjectService projectService;	
	
	@Autowired(required=true) @Qualifier("purchaseDocAttServiceImpl")
	private PurchaseDocAttService purchaseDocAttService;
	
	@Autowired(required=true) @Qualifier("signUprecordServiceImpl")
	private SignUprecordService signUprecordService;
	
	@Autowired(required=true) @Qualifier("userApiServiceImpl")
	private UserApiService userApiService;
	
	@Autowired(required=true) @Qualifier("attachmentServiceImpl")
	private AttachmentService attachmentService;
	
	@Autowired(required = true) @Qualifier("procFileDownRecServiceImpl")
	private ProcFileDownRecService procFileDownRecService;
	
	/**
	 * FuncName: getProjectPurchaseFile
	 * Description :  获取招标书接口
	 * @param   prjCode:项目记录号
	 * @param 	packCode：包件编号
     * @param   fileType: 文件类型
     * @param   username:用户名
     * @param   password: 密码
	 * @author: liuke
	 * @Create Date:2011-5-9  下午02:27:44
	 * @Modifier: liuke
	 * @Modified Date:2011-5-9  下午02:27:44
	 */
	@RequestMapping(params="method=getProjectPurchaseFile")
	public void getProjectPurchaseFile(HttpServletRequest request, HttpServletResponse response) {
		boolean isSuccess = true;//是否执行成功     
		String errorMessage = "";
		String downUrl="";
		String projCode = request.getParameter("prjCode");  //项目编号
		String viewName="";
		try {
			//文件类型，默认为 integration（整体式，不区分商务和技术标），business商务标，technical技术标
			String fileType = request.getParameter("fileType");  
			String username = request.getParameter("username");  //用户名
			String password = request.getParameter("password");  //密码
			String packCode = request.getParameter("packCode");
			String ip = request.getRemoteAddr();
			if(fileType==null){
				fileType = "";
			}
			String filePath = "";
			String fileName="";
			String supplierId = "";
			User user = null;
			if(username!=null&&!"".equals(username)&&password!=null&&!"".equals(password)){ //如果用户名与密码不为null或""
				String decodeStringPassword = StringUtil.decodeString(password); //用Base64算法对字符串进行解码.
				String md5PassWord =  MathUtil.encryptPassWordSHA(decodeStringPassword);//密码转换成加密字符串
				List<User> userList = userApiService.getUserByUserName(username, md5PassWord);
				if(userList!=null&&userList.size()>0){
					 user = userList.get(0);
				}
				if(user!=null){
					 supplierId = userApiService.getOrgInfoByUser(user).getObjId();
				}
			}
			String address = messageSource.getMessage("applicationInternetAddress");
			String newPath = messageSource.getMessage("epp.projectAttaPath");
			Project project = projectService.findProjectByProjCode(projCode);
			Project pack = projectService.findProjectByProjCodeAndParent(packCode, project.getObjId());
			PurchaseFileDTO purchaseFileDTO = new PurchaseFileDTO();
				if(("business").equals(fileType)){//商务标招标文件下载
					String filePath1= newPath+File.separator+project.getObjId()+File.separator+CommonEnum.PUR_FILE_DIR+File.separator;
		       		File file2 = new File(filePath1+"BiddingAppendix.xml");
	        		SAXReader saxReader = new SAXReader();
	        		if(file2.exists()){
		        		Document document =  saxReader.read(file2);
		        		Element element =  document.getRootElement();
		        		List<Element> sectionElements = element.elements("appendix");
		        		for (Iterator iterator = sectionElements.iterator(); iterator
		    					.hasNext();) {
		    				Element sectionElement = (Element) iterator.next();
		    				if("business".equals(sectionElement.attribute("type").getText())&&packCode.equals(sectionElement.attribute("packCode").getText())){
		    					viewName = sectionElement.attribute("fileName").getText();
		    					fileName = sectionElement.attribute("docId").getText()+viewName.substring(viewName.indexOf("."));
		        				break;
		    				}
		    			}
		        		filePath = new File(filePath1+fileName).getAbsolutePath();//获得商务标文件的绝对路径
	        		}
	        		if(!new File(filePath).exists()){//若没有根据XMl找到相关数据，则去数据库里查询是否有包组分册文件上传
	        			isSuccess = false;
	        			PurchaseDocAtt purchaseDocAtt = new PurchaseDocAtt();
	        			if(pack != null){
	        				purchaseDocAtt = purchaseDocAttService.getPurchaseDocAtt(project.getObjId(), pack.getObjId(), CommonEnum.FILE_TYPE_PURCHASE_BUSINESS_STATUS);	
	        			}else{
	        				purchaseDocAtt = purchaseDocAttService.getPurchaseDocAtt(project.getObjId(), pack.getObjId(), CommonEnum.FILE_TYPE_PURCHASE_BUSINESS_STATUS);
	        			}    
	        			isSuccess = false;
	        			if(purchaseDocAtt.getAttRaId() != null && !"".equals(purchaseDocAtt.getAttRaId())){
	        				List tempList = attachmentService.getAttachmentsByRealID(purchaseDocAtt.getAttRaId());
	        				if(tempList != null && !tempList.isEmpty()){
	        					Attachment purchaseDocAttFile = (Attachment)tempList.get(0);
	        					filePath = newPath+File.separator+project.getObjId()+File.separator+CommonEnum.PUR_FILE_DIR+File.separator+purchaseDocAttFile.getFileName();
	        					if(new File(filePath).exists()){
	        	        			isSuccess = true;
	        	        			viewName = purchaseDocAttFile.getFileName();
	        	        			filePath = new File(filePath).getAbsolutePath();
	        					}
	        				}
	        			}
	        		}
	        		if(!isSuccess){
	        			errorMessage = "服务器上没找到文件！";
	        		}
	        		downUrl= address+"/DownloadFileHttpServlet?filePath="+filePath+"&amp;fileType="+CommonEnum.FILE_TYPE_PURCHASE;
				}else if(("technical").equals(fileType)){//技术标招标文件下载
					String filePath1= newPath+File.separator+project.getObjId()+File.separator+CommonEnum.PUR_FILE_DIR+File.separator;
		       		File file2 = new File(filePath1+"BiddingAppendix.xml");
	        		SAXReader saxReader = new SAXReader();
	        		if(file2.exists()){
		        		Document document =  saxReader.read(file2);
		        		Element element =  document.getRootElement();
		        		List<Element> sectionElements = element.elements("appendix");
		        		for (Iterator iterator = sectionElements.iterator(); iterator
		    					.hasNext();) {
		    				Element sectionElement = (Element) iterator.next();
		    				if("technical".equals(sectionElement.attribute("type").getText())&&packCode.equals(sectionElement.attribute("packCode").getText())){
		    					viewName = sectionElement.attribute("fileName").getText();
		    					fileName = sectionElement.attribute("docId").getText()+viewName.substring(viewName.indexOf("."));
		        				break;
		    				}
		    			}
		        		filePath = new File(filePath1+fileName).getAbsolutePath();//获得商务标文件的绝对路径
	        		}
	        		if(!new File(filePath).exists()){//若没有根据XMl找到相关数据，则去数据库里查询是否有包组分册文件上传
	        			isSuccess = false;
	        			PurchaseDocAtt purchaseDocAtt = new PurchaseDocAtt();
	        			if(pack != null){
	        				purchaseDocAtt = purchaseDocAttService.getPurchaseDocAtt(project.getObjId(), pack.getObjId(), CommonEnum.FILE_TYPE_PURCHASE_TECHNICAL_STATUS);	
	        			}else{
	        				purchaseDocAtt = purchaseDocAttService.getPurchaseDocAtt(project.getObjId(), pack.getObjId(), CommonEnum.FILE_TYPE_PURCHASE_TECHNICAL_STATUS);
	        			}
	        			if(purchaseDocAtt.getAttRaId() != null && !"".equals(purchaseDocAtt.getAttRaId())){
	        				List tempList = attachmentService.getAttachmentsByRealID(purchaseDocAtt.getAttRaId());
	        				if(tempList != null && !tempList.isEmpty()){
	        					Attachment purchaseDocAttFile = (Attachment)tempList.get(0);
	        					filePath = newPath+File.separator+project.getObjId()+File.separator+CommonEnum.PUR_FILE_DIR+File.separator+purchaseDocAttFile.getFileName();
	        					if(new File(filePath).exists()){
	        	        			isSuccess = true;
	        	        			viewName = purchaseDocAttFile.getFileName();
	        	        			filePath = new File(filePath).getAbsolutePath();
	        					}
	        				}
	        			}
	        		}
	        		if(!isSuccess){
	        			errorMessage = "服务器上没找到文件！";
	        		}
	        		downUrl= address+"/DownloadFileHttpServlet?filePath="+filePath+"&amp;fileType="+CommonEnum.FILE_TYPE_PURCHASE;
				}else{//整体式招标文件下载
					 purchaseFileDTO = purchaseDocService.saveAndGetPurchaseFileDTOByProjCode(projCode);
					 filePath = purchaseFileDTO.getFilePath();
					 if(purchaseFileDTO.getFileName()!=null&&!"".equals(purchaseFileDTO.getFileName())&&!"".equals(purchaseFileDTO.getDownUrl())){
							downUrl=address+purchaseFileDTO.getDownUrl();
							fileName=purchaseFileDTO.getFileName();
							viewName=purchaseFileDTO.getViewName();
					 }
					 if(!ZipUtils.fileIsExist(newPath+File.separator+""+filePath+File.separator+"",fileName)){//判断文件在服务器上是否存在
							isSuccess = false;
							errorMessage = "服务器上没找到文件！";
					 } 
				 }
				 //判读当前用户是否可以下载采购文件
				 if(password!=null&&!"".equals(password)&&username!=null&&!"".equals(username)){//如果用户名与密码不为null或"",则表示由供应商下载招标文件，需要进行报名审核判断，否则表示由项目经理下载招标文件，不需要报名审核判断
					
					 float  price = (project.getPurDocPrice()!=null)?project.getPurDocPrice().floatValue():0f; //招标文件价格
					 if(price>0 && supplierId != null && !"".equals(supplierId)){ //如果价格大于0，登录的帐号为供应商，则要判断供应商报名是否审核
						 List signUprecordList = signUprecordService.getSignUprecordByprojectId(project.getObjId());
						 boolean hasSignup = false;//判断是否有报名，且审核通过。
						 if(signUprecordList != null && !signUprecordList.isEmpty()){
							 for (Iterator iterator = signUprecordList.iterator(); iterator
									.hasNext();) {
								 SignUprecord signUprecord = (SignUprecord) iterator.next();
								 //若有报名记录，且报名状态为审核通过，则判断为true
								 if(supplierId.equals(signUprecord.getSupplier().getObjId()) && AuditStatusEnum.AUDIT_PASS.equals(signUprecord.getAuditStatus())){
									 hasSignup = true;
								 }
							 }
						 }
						 if(!hasSignup){
							 isSuccess = false;
							 errorMessage = "报名审核未通过，无法下载文件！";
						 }
					 }
				 }
				 
				
				 if(isSuccess){
					//记录下载记录信息
					 if(user!=null){
						 PurchaseDoc purchaseDoc = purchaseDocService.getPurchaseDocByProjectIdAndFileType(project.getObjId(), "");
						 OrgInfo orginfo = userApiService.getOrgInfoByUser(user);
						 ProcFileDownRec downRec = new ProcFileDownRec();
							downRec.setDownDate(new Date());//下载日期
							downRec.setDownOrg(orginfo);//下载单位
							downRec.setDownUser(user);
							downRec.setFileId(purchaseDoc.getObjId()==null?null:purchaseDoc.getObjId());
							downRec.setDownReason("招标文件下载");
							downRec.setDownStatus(CommonEnum.CONFIRM_STATUS_WAIT);//00：成功，01：失败
							downRec.setDownIP(ip);//下载端ip地址
							procFileDownRecService.save(downRec); 
					 }
				 }
		} catch (Exception e) {
			e.printStackTrace();
			isSuccess = false;
			errorMessage = e.getMessage();
		}
		StringBuffer responseXml = new StringBuffer();
		responseXml.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
		responseXml.append("<downPurchaseFile  xmlns=\"http://www.gpcsoft.com\">");
		responseXml.append("<header>");
		responseXml.append("<operTag>"+(isSuccess == true ? "Y" : "N")+"</operTag>");
		responseXml.append("<operDesc>"+(isSuccess == true ? "成功" : "失败")+"</operDesc>");
		responseXml.append("<operException>"+errorMessage+"</operException>");
		responseXml.append("</header>");
		responseXml.append("<body>");
		responseXml.append("<project>");
		responseXml.append("<projCode>"+projCode+"</projCode>");
		responseXml.append("<purchaseFile>");
		responseXml.append("<fileName>"+viewName+"</fileName>");
		responseXml.append("<downUrl>"+(isSuccess == true ? downUrl : "")+"</downUrl>");
		responseXml.append("</purchaseFile>");
		responseXml.append("</project>");
		responseXml.append("</body>");
		responseXml.append("</downPurchaseFile >");
		logger.debug("ProjectPurchaseFileHttp  getProjectPurchaseFile start=============================================\n"+responseXml.toString());
		logger.debug("ProjectPurchaseFileHttp  getProjectPurchaseFile end=============================================\n");
		try {
			write(responseXml.toString(), response);
		} catch (Exception e) {
		}
	}
	
}
