package com.gpcsoft.epp.webService.purchaseFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.context.FrameMessageResource;
import com.gpcsoft.core.utils.MathUtil;
import com.gpcsoft.epp.common.domain.AuditStatusEnum;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.common.domain.DataItem;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.common.service.UserApiService;
import com.gpcsoft.epp.common.utils.StringUtil;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.service.ProjectService;
import com.gpcsoft.epp.projreview.service.GenviewDefineService;
import com.gpcsoft.epp.purchasedoc.domain.PurchaseDoc;
import com.gpcsoft.epp.purchasedoc.domain.PurchaseDocAtt;
import com.gpcsoft.epp.purchasedoc.service.PurchaseDocAttService;
import com.gpcsoft.epp.purchasedoc.service.PurchaseDocService;
import com.gpcsoft.epp.signuprecord.domain.SignUprecord;
import com.gpcsoft.epp.signuprecord.service.SignUprecordService;
import com.gpcsoft.epp.webService.common.util.XmlUtil;
import com.gpcsoft.epp.webService.common.util.ZipUtils;
import com.gpcsoft.epp.webService.ueSystem.bulletin.dto.PurchaseFileDTO;
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.auth.service.AttachmentService;

public class PurchaseFileSoapService {
	
	protected FrameMessageResource messageSource;
	
	private ProjectService projectService;	
	
	private PurchaseDocAttService purchaseDocAttService;
	
	private AttachmentService attachmentService;
	
	private PurchaseDocService purchaseDocService;
	
	private UserApiService userApiService;

	private SignUprecordService signUprecordService;
	
	private GenviewDefineService genviewDefineService;
	
	protected final Log logger = LogFactory.getLog(this.getClass());
	
	public PurchaseFileSoapService(){
		messageSource = (FrameMessageResource) FrameBeanFactory.getBean("frameMessageResource");
		projectService = (ProjectService) FrameBeanFactory.getBean("projectServiceImpl");
		purchaseDocAttService = (PurchaseDocAttService) FrameBeanFactory.getBean("purchaseDocAttServiceImpl");
		attachmentService = (AttachmentService) FrameBeanFactory.getBean("attachmentServiceImpl");
		purchaseDocService = (PurchaseDocService) FrameBeanFactory.getBean("purchaseDocServiceImpl");
		userApiService = (UserApiService) FrameBeanFactory.getBean("userApiServiceImpl");
		signUprecordService = (SignUprecordService) FrameBeanFactory.getBean("signUprecordServiceImpl");
		genviewDefineService = (GenviewDefineService) FrameBeanFactory.getBean("genviewDefineServiceImpl");
	}
	
	/**
	 * FuncName: getProjectPurchaseFile
	 * Description :  获取招标文件下载地址
	 * @param tenderCode 项目编号
	 * @param packCode 包组编号
	 * @param orgCode 机构编号
	 * @param username 用户名UploadOpenBidService
	 * @param password 密码
	 * @return String
	 */
	public String  getProjectPurchaseFile(String prjCode,String packCode,String fileType,String username,String password){
		boolean isSuccess = true;//是否执行成功     
		String errorMessage = "";
		String downUrl="";
		String viewName="";
		try {
			//文件类型，默认为 integration（整体式，不区分商务和技术标），business商务标，technical技术标
			if(fileType==null){
				fileType = "";
			}
			String filePath = "";
			String fileName="";
			String address = messageSource.getMessage("applicationInternetAddress");
			String newPath = messageSource.getMessage("epp.projectAttaPath");
			Project project = projectService.findProjectByProjCode(prjCode);
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
				 purchaseFileDTO = purchaseDocService.saveAndGetPurchaseFileDTOByProjCode(prjCode);
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
				 String decodeStringPassword = StringUtil.decodeString(password); //用Base64算法对字符串进行解码.
				 String md5PassWord =  MathUtil.encryptPassWordSHA(decodeStringPassword);//密码转换成加密字符串
				 List<User> userList = userApiService.getUserByUserName(username, md5PassWord);
				 User user = null;
				 String supplierId = "";
				 if(userList!=null&&userList.size()>0){
					 user = userList.get(0);
				 }
				 if(user!=null){
					 supplierId = userApiService.getOrgInfoByUser(user).getObjId();
				 }
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
		} catch (Exception e) {
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
		responseXml.append("<projCode>"+prjCode+"</projCode>");
		responseXml.append("<purchaseFile>");
		responseXml.append("<fileName>"+viewName+"</fileName>");
		responseXml.append("<downUrl>"+(isSuccess == true ? downUrl : "")+"</downUrl>");
		responseXml.append("</purchaseFile>");
		responseXml.append("</project>");
		responseXml.append("</body>");
		responseXml.append("</downPurchaseFile >");
		logger.debug("PurchaseFileSoapService  downPurchaseFile start=============================================\n"+responseXml.toString());
		logger.debug("PurchaseFileSoapService  downPurchaseFile end=============================================\n");
		return responseXml.toString();
	}
	
	
	/**
	 * FuncName: savePurchaseFile
	 * Description:确认本地采购文件整体或部分保存到执行平台。
	 * @param tenderCode 项目编号
	 * @param prjCode:项目记录号
	 * @param packCode：包件编号
     * @param username:用户名
     * @param password: 密码
	 * @param fileType: 文件类型
	 * @param purchaseFile:采购文件
	 * @return String
	 */
	@SuppressWarnings("unchecked")
	public String savePurchaseFile(String prjCode, String packCode, String username, String password, String fileType, String purchaseFile) throws IOException {
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
			Attachment attachment = null;
			List<Attachment> attachmentList = attachmentService.getAttachmentsByRealID(purchaseDoc.getAttachRelaId());
			if (null != attachmentList && !attachmentList.isEmpty())
				attachment = attachmentList.get(0);
			String epp_projectAttaPath = messageSource.getMessage("epp.projectAttaPath");
			File purchaseFile_ = new File(epp_projectAttaPath
					+ attachment.getPath() + attachment.getFileName());
			/* 处理项目数据 */
			Document projectDataDocument = ZipUtils.readXMLFileFromZipFileByfileName(purchaseFile_.getAbsolutePath(), projectDataFileName);
			List<Element> projectDataElementList = projectDataDocument.getRootElement().elements();
			List<DataItem> projectDataItemList = new ArrayList<DataItem>();
			for (Element e : projectDataElementList) {
				DataItem dataItem = (DataItem) XmlUtil.elementToObject(e,DataItem.class);
				if ("false".equalsIgnoreCase(dataItem.getReadOnly())) {// 将非只读的数据放入list
					projectDataItemList.add((DataItem) XmlUtil.elementToObject(e, DataItem.class));
				}
			}
			/* 处理指标数据 */
			Document evalFactorsDocument = ZipUtils.readXMLFileFromZipFileByfileName(purchaseFile_.getAbsolutePath(), evalFactorsDataFileName);
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
			Document PriceDocument = ZipUtils.readXMLFileFromZipFileByfileName(purchaseFile_.getAbsolutePath(), PriceName);
			List<Element> itemElementList = PriceDocument.getRootElement().element("package").elements("item");

			List<String> priceInfoList = new ArrayList<String>();
			priceInfoList.add("总报价");
			for (Iterator iterator = itemElementList.iterator(); iterator.hasNext();) {
				Element element = (Element) iterator.next();
				priceInfoList.add(element.elementText("name"));
			}
			genviewDefineService.savePriceInfo(priceInfoList, project); // 保存开标一览表数据项

			/* 判断是否存在投标文件结构 */
			if (!ZipUtils.whetherTheFileExistsInZipFile(purchaseFile_.getAbsolutePath(), tenderDocument)) {
				throw new EsException("找不到" + tenderDocument + "文件");
			}

			/* 更新项目数据和指标数据 */
			purchaseDocService.saveUpdatePurchaseFile(project.getObjId(),purchaseDoc.getObjId(), projectDataItemList,congruousFactorList);
		} catch (Exception e) {
			errorMessage = e.getMessage();
			operDesc = false;
		}
		StringBuffer responseXML = new StringBuffer("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
		responseXML.append("<uploadPurchaseFile xmlns=\"http://www.gpcsoft.com\">");
		responseXML.append("<header>");
		responseXML.append("<operTag>"+(operDesc?"Y":"N")+"</operTag>");
		responseXML.append("<operDesc>"+(operDesc? "成功":"失败")+"</operDesc>");
		responseXML.append("<operException>"+errorMessage+"</operException>");
		responseXML.append("</header>");
		responseXML.append("<body>");
		responseXML.append("</body>");
		responseXML.append("</uploadPurchaseFile>");
		logger.debug("PurchaseFileSoapService  uploadPurchaseFile start=============================================\n"+responseXML.toString());
		logger.debug("PurchaseFileSoapService  uploadPurchaseFile end=============================================\n");
		return responseXML.toString();
	}
}
