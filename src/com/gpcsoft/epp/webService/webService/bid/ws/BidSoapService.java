/**
 * 
 */
package com.gpcsoft.epp.webService.webService.bid.ws;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.context.FrameMessageResource;
import com.gpcsoft.core.utils.StringUtils;
import com.gpcsoft.epp.bid.domain.Bid;
import com.gpcsoft.epp.bid.domain.BidPackage;
import com.gpcsoft.epp.bid.domain.BidSubKey;
import com.gpcsoft.epp.bid.service.BidService;
import com.gpcsoft.epp.bid.service.BidSubKeyService;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.common.service.UserApiService;
import com.gpcsoft.epp.common.utils.DateUtil;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.domain.ProjectRule;
import com.gpcsoft.epp.project.service.ProjectService;
import com.gpcsoft.epp.projreview.domain.OpenBid;
import com.gpcsoft.epp.projreview.service.OpenBidService;
import com.gpcsoft.epp.purchasedoc.service.PurchaseDocService;
import com.gpcsoft.epp.webService.common.util.ZipUtils;
import com.gpcsoft.epp.webService.evaluateSystem.projectInfo.service.ElectronicReviewService;
import com.gpcsoft.epp.webService.ueSystem.bulletin.dto.PurchaseFileDTO;
import com.gpcsoft.epp.webService.ueSystem.bulletin.dto.SubFileDTO;
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.service.AttachmentService;

/**
 * @author Administrator
 *
 */
public class BidSoapService {
	
	private ElectronicReviewService remoteProjectService;
	private BidSubKeyService bidSubKeyService;
	private UserApiService userApiService;
	private ProjectService projectService;
	private FrameMessageResource  messageSource;
	private PurchaseDocService purchaseDocService;
	private BidService bidService;
	private AttachmentService attachmentService;
	private OpenBidService openBidService;
	protected final Log logger = LogFactory.getLog(this.getClass());
	private final static String BZB = "00";//商务标
	private final static String TCB = "01";//技术标
	public BidSoapService(){
		remoteProjectService = (ElectronicReviewService) FrameBeanFactory.getBean("electronicReviewServiceImpl");
		messageSource =  (FrameMessageResource)FrameBeanFactory.getBean("frameMessageResource");
		this.purchaseDocService = (PurchaseDocService) FrameBeanFactory.getBean("purchaseDocServiceImpl");
		this.bidService = (BidService) FrameBeanFactory.getBean("bidServiceImpl");
		this.bidSubKeyService = (BidSubKeyService) FrameBeanFactory.getBean("bidSubKeyServiceImpl");
		this.userApiService = (UserApiService) FrameBeanFactory.getBean("userApiServiceImpl");
		this.projectService = (ProjectService) FrameBeanFactory.getBean("projectServiceImpl");
		this.attachmentService =(AttachmentService) FrameBeanFactory.getBean("attachmentServiceImpl");
		this.openBidService =(OpenBidService) FrameBeanFactory.getBean("openBidServiceImpl");
	}
	
	/**
	 * FuncName: downBidFileApp
	 * Description :  【申请】文件下载地址
	 * @param tenderCode 项目编号
	 * @param packCode 包组编号
	 * @param orgCode 机构编号
	 * @param username 用户名
	 * @param password 密码
	 * @param sectionType 分册类型 00 ：商务标；01 技术标
	 * @return String
	 * @author: liuke
	 * @Create Date:2011-7-1  上午11:38:10
	 * @Modifier: liuke
	 * @Modified Date:2011-7-1  上午11:38:10
	 */
	public String  downBidFileApp(String tenderCode,String packCode,String orgCode,String username,String password, String sectionType){
		boolean isSuccess = true;//是否执行成功     
		String errorMessage = ""; //错误信息
		String downUrl = "";
		String fileName = "";
		String name = "";
		String filePath = "";
		String exception = "";
		Attachment bidAttachment = null;
		String bidFilePath = messageSource.getMessage("epp.projectAttaPath");
		try {
			bidAttachment = remoteProjectService.getBidAttachmentByTenderCodeAndOrgCode(tenderCode, packCode, orgCode);
			if(this.BZB.equals(sectionType)){//商务标投标文件下载
				List<Attachment> list = this.attachmentService.getAttachmentsByRealID(bidAttachment.getRelationId());
				Attachment attachment = (Attachment)list.get(0);
				filePath = messageSource.getMessage("epp.projectAttaPath")+File.separator+((attachment.getPath()==null)?"":attachment.getPath())+File.separator;
				File file2 = new File(filePath+"TenderDocument.xml");
        		SAXReader saxReader = new SAXReader();
        		Document document =  saxReader.read(file2);
        		Element element =  document.getRootElement();
        		List<Element> sectionElements = element.elements("section");
        		for (Iterator iterator = sectionElements.iterator(); iterator
    					.hasNext();) {
    				Element sectionElement = (Element) iterator.next();
    				if(sectionElement.attribute("type").getText().equals(this.BZB)){
    					fileName = sectionElement.attribute("fileName").getText();
    					name = sectionElement.attribute("docId").getText()+fileName.substring(fileName.indexOf("."));
        				break;
    				}
    			}
        		filePath = new File(filePath + name).getAbsolutePath();
        		downUrl= messageSource.getMessage("applicationInternetAddress")+"/DownloadFileHttpServlet?filePath="+filePath+"&amp;fileType="+CommonEnum.FILE_TYPE_BID;
			}else if(this.TCB.equals(sectionType)){//技术标投标文件下载
				List<Attachment> list = this.attachmentService.getAttachmentsByRealID(bidAttachment.getRelationId());
				Attachment attachment = (Attachment)list.get(0);
				filePath = messageSource.getMessage("epp.projectAttaPath")+File.separator+((attachment.getPath()==null)?"":attachment.getPath())+File.separator;
				File file2 = new File(filePath+"TenderDocument.xml");
        		SAXReader saxReader = new SAXReader();
        		Document document =  saxReader.read(file2);
        		Element element =  document.getRootElement();
        		List<Element> sectionElements = element.elements("section");
        		for (Iterator iterator = sectionElements.iterator(); iterator
    					.hasNext();) {
    				Element sectionElement = (Element) iterator.next();
    				if(sectionElement.attribute("type").getText().equals(this.TCB)){
    					fileName = sectionElement.attribute("fileName").getText();
    					name = sectionElement.attribute("docId").getText()+fileName.substring(fileName.indexOf("."));
        				break;
    				}
    			}
        		filePath = new File(filePath + name).getAbsolutePath();
        		downUrl= messageSource.getMessage("applicationInternetAddress")+"/DownloadFileHttpServlet?filePath="+filePath+"&amp;fileType="+CommonEnum.FILE_TYPE_BID;
			}else{//整体式投标文件下载
				 if(bidAttachment!=null){
					fileName =  bidAttachment.getViewName(); 
					downUrl = messageSource.getMessage("applicationInternetAddress")+"/DownloadFileHttpServlet?attachmentObjId="+bidAttachment.getRelationId()+"&amp;fileType="+CommonEnum.FILE_TYPE_BID;
				 }
				 if(!ZipUtils.fileIsExist(bidFilePath+bidAttachment.getPath(),bidAttachment.getFileName())){  //判断文件在服务器上是否存在
				    	isSuccess = false;
				    	errorMessage = "服务器上没找到文件！";
				 }
			}
		} catch (Exception e) {
			isSuccess = false;
			exception = e.getMessage();
		}
		StringBuffer responseXml = new StringBuffer();
		responseXml.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
		responseXml.append("<downBidFileApp xmlns=\"http://www.gpcsoft.com\">");
		responseXml.append("<header>");
		responseXml.append("<operTag>"+(isSuccess == true ? "Y" : "N")+"</operTag>");
		responseXml.append("<operDesc>"+errorMessage+"</operDesc>");
		responseXml.append("<operException>"+exception+"</operException>");
		responseXml.append("</header>");
		responseXml.append("<body>");
		responseXml.append("<project>");
		responseXml.append("<projCode>"+tenderCode+"</projCode>");
		responseXml.append("<bidFile>");
		responseXml.append("<fileName>"+fileName+"</fileName>");
		responseXml.append("<downType>http</downType>");
		responseXml.append("<downUrl>"+(isSuccess == true ? downUrl : "")+"</downUrl>");
		responseXml.append("</bidFile>");
		responseXml.append("</project>");
		responseXml.append("</body>");
		responseXml.append("</downBidFileApp>");
		logger.debug("BidSoapService    downBidFileApp====================================start\n"+responseXml.toString());
		logger.debug("BidSoapService    downBidFileApp====================================end\n");
		return responseXml.toString();
	}


	/**
	 * FuncName: getProjectPurchaseFile
	 * Description :  获取招标书接口
	 * @param projCode  项目编号
	 * @param username  用户名
	 * @param password  密码
	 * @author: liuke
	 * @Create Date:2011-5-9  下午02:27:44
	 * @Modifier: liuke
	 * @Modified Date:2011-5-9  下午02:27:44
	 */
	public String getProjectPurchaseFile(String  projCode, String username,String password) throws Exception{
		String errorMessage = "";
		PurchaseFileDTO purchaseFileDTO = new PurchaseFileDTO();
		try {
			 purchaseFileDTO = this.purchaseDocService.saveAndGetPurchaseFileDTOByProjCode(projCode);
		} catch (Exception e) {
			errorMessage = e.getMessage();
		}
		String issuccess="N";
		String downUrl="";
		String fileName="";
		String address = this.messageSource.getMessage("applicationInternetAddress");
		if(purchaseFileDTO.getFileName()!=null&&!"".equals(purchaseFileDTO.getFileName())&&!"".equals(purchaseFileDTO.getDownUrl())){
			issuccess="Y";
			downUrl=address+purchaseFileDTO.getDownUrl();
			fileName=purchaseFileDTO.getFileName();
		}else{
			errorMessage="没有找到采购文件！";
		}
		String a ="";
		StringBuffer responseXml = new StringBuffer();
		responseXml.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
		responseXml.append("<getPrjPurchaseFile xmlns=\"http://www.gpcsoft.com\">");
		responseXml.append("<header>");
		responseXml.append("<operTag>"+issuccess+"</operTag>");
		responseXml.append("<operDesc>"+errorMessage+"</operDesc>");
		responseXml.append("</header>");
		responseXml.append("<body>");
		responseXml.append("<project>");
		responseXml.append("<projCode>"+projCode+"</projCode>");
		responseXml.append("<purchaseFile>");
		responseXml.append("<fileName>"+fileName+"</fileName>");
		responseXml.append("<downUrl>"+downUrl+"</downUrl>");
		responseXml.append("</purchaseFile>");
		responseXml.append("</project>");
		responseXml.append("</body>");
		responseXml.append("</getPrjPurchaseFile>");
		logger.debug("BidSoapService    getPrjPurchaseFile====================================start\n"+responseXml.toString());
		logger.debug("BidSoapService    getPrjPurchaseFile====================================end\n");
		return responseXml.toString();
	}
	
	/**
	 * FuncName: tenderApply
	 * Description :  申请投标接口
	 * @param projCode   项目编号
	 * @param packCode   包件编号
	 * @param username   用户名
	 * @param password   密码
	 * @param tenderOrgCode   投标机构编号
	 * @param tenderOrgName   投标机构名称
	 * @throws Exception void
	 * @author: liuke
	 * @Create Date:2011-6-21  上午11:35:38
	 * @Modifier: liuke
	 * @Modified Date:2011-6-21  上午11:35:38
	 */
	public String tenderApply(String  projCode, String packCode,String username,String password ,String tenderOrgCode,String tenderOrgName)throws Exception{
		
		StringBuffer responseXml = new StringBuffer();
		responseXml.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
		responseXml.append("<header>");
		responseXml.append("<operTag>Y</operTag>");
		responseXml.append("<operDesc>操作结果描述</operDesc>");
		responseXml.append("</header>");
		responseXml.append("<body></body>");
		responseXml.append("</tenderApply>");
		logger.debug("BidSoapService    tenderApply====================================start\n"+responseXml.toString());
		logger.debug("BidSoapService    tenderApply====================================end\n");
		return responseXml.toString();
	}
	
	/**
	 * FuncName:  tenderProject
	 * Description :  验证投标接口
	 * @param projCode  项目编号
	 * @param packCode  包件编号
	 * @param username  用户名
	 * @param password  密码
	 * @param contect   输入分册验证信息
	 * @param tenderOrgCode  投标机构编号
	 * @param tenderOrgName  投标机构名称
	 * @param tenderFile  投标文件
	 * @param isFinished  检验投标是否完整
	 * @throws Exception void
	 * @author: liuke
	 * @Create Date:2011-5-11  上午10:56:57
	 * @Modifier: liuke
	 * @Modified Date:2011-5-11  上午10:56:57
	 */
	public String tenderProject(String projCode, String packCode, String username,String password,String contect,String tenderOrgCode,String tenderOrgName,String tenderFile,String isFinished) throws Exception{
		String errorMessage = "";
		String bidFilePath = this.messageSource.getMessage("epp.projectAttaPath");
		String issuccess="N";
		String exception = "";
		boolean flag = false;
		boolean md5Flag = false;     //md5Flag 	 
		Document document;
		List<SubFileDTO> subFileList = new ArrayList<SubFileDTO>();
		try {
			if(contect!=null&&!"".equals(contect)){
				document = DocumentHelper.parseText(contect);
				Element element =  document.getRootElement();
				List <Element> tenderFileElementList = element.element("body").element("tenderFile").elements("subFile");
				for(Element tenderFileElement:tenderFileElementList){
					SubFileDTO subFile = new SubFileDTO();
					subFile.setDocId(tenderFileElement.element("docId").getText());
					subFile.setHashCode(tenderFileElement.element("hashCode").getText());
					subFileList.add(subFile);
				}
				md5Flag = this.bidService.checkBidFileMd5Complete(subFileList, bidFilePath);
			}
			if(md5Flag){
				issuccess = "Y";
			}else{
				issuccess = "N";
				errorMessage = "投标文件md5值检测不一致";
			}
			
			
			if("Y".equals(isFinished)){
				 flag =  this.bidService.checkBidFileComplete(bidFilePath);
				 if(flag){
						issuccess = "Y";
					}else{
						issuccess = "N";
						errorMessage = "投标文件上传不完整";
					}
			}
		} catch (Exception e) {
			errorMessage = "系统抛出异常错误！";
			exception = e.getMessage();
		}
		
		StringBuffer responseXml = new StringBuffer();
		responseXml.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
		responseXml.append("<tenderProject xmlns=\"http://www.gpcsoft.com\">");
		responseXml.append("<header>");
		responseXml.append("<operTag>"+issuccess+"</operTag>");
		responseXml.append("<operDesc>"+errorMessage+"</operDesc>");
		responseXml.append("<operException>"+exception+"</operException>");
		responseXml.append("</header>");
		responseXml.append("<body>");
		responseXml.append("<returnFile>");
		responseXml.append("<fileName>投标回执文件</fileName>");
		responseXml.append("<downType>下载协议类型</downType>");
		responseXml.append("<downUrl>下载地址</downUrl>");
		responseXml.append("</returnFile>");
		responseXml.append("</body>");
		responseXml.append("</tenderProject>");
		logger.debug("BidSoapService    tenderProject====================================start\n"+responseXml.toString());
		logger.debug("BidSoapService    tenderProject====================================end\n");
		return responseXml.toString();
	}
	
	/**
	 * FuncName: getTenderInfo
	 * Description :  供应商在线获取投标文件及信息
	 * @param projCode   项目编号
	 * @param packCode   包件编号
	 * @param username   用户名
	 * @param password   密码
	 * @throws Exception 
	 * @author: liuke
	 * @Create Date:2011-6-21  下午02:52:12
	 * @Modifier: liuke
	 * @Modified Date:2011-6-21  下午02:52:12
	 */
	public String getTenderInfo(String projCode, String packCode ,String username ,String password)throws Exception{
		StringBuffer responseXml = new StringBuffer();
		responseXml.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
		responseXml.append("<getTenderInfo xmlns=\"gpcsoft.com\">");
		responseXml.append("<header>");
		responseXml.append("<operTag>Y</operTag>");
		responseXml.append("<operDesc>操作结果描述</operDesc>");
		responseXml.append("</header>");
		responseXml.append("<body>");
		responseXml.append("<project>");
		responseXml.append("<prjCode>项目编号</prjCode>");
		responseXml.append("<packCode>包件编号</packCode>");
		responseXml.append("<name>投标文件格式<name>");
		responseXml.append("<sections>");
		responseXml.append("<section>");
		responseXml.append("<name>开标一览表</name>");
		responseXml.append("<docId>文件保存记录号</docId>");
		responseXml.append("<type>投标文件类型</type>");
		responseXml.append("<filename>投标文件名称</filename>");
		responseXml.append("<fileSize>文件大小</fileSize>");
		responseXml.append("<tenderTime>投递时间</tenderTime>");
		responseXml.append("<downUrl>分册下载地址</downUrl>");
		responseXml.append("</section>");
		responseXml.append("</sections>");
		responseXml.append("</project>");
		responseXml.append("</body>");
		responseXml.append("</getTenderInfo>");
		logger.debug("BidSoapService    getTenderInfo====================================start\n"+responseXml.toString());
		logger.debug("BidSoapService    getTenderInfo====================================end\n");
		return responseXml.toString();
	}
	
	
	/**
	 * FuncName: confirmTender
	 * Description :确认投标
	 * @param projCode  项目编号
	 * @param packCode  包件编号 
	 * @param username  用户名
	 * @param password  密码
	 * @throws Exception void
	 * @author: liuke
	 * @Create Date:2011-6-21  下午03:10:16
	 * @Modifier: liuke
	 * @Modified Date:2011-6-21  下午03:10:16
	 */
	public String  confirmTender(String  projCode, String  packCode,String username ,String password)throws Exception{
		StringBuffer responseXml = new StringBuffer();
		responseXml.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
		responseXml.append("<confirmTender xmlns=\"gpcsoft.com\">");
		responseXml.append("<header>");
		responseXml.append("<operTag>Y/N</operTag>");
		responseXml.append("<operDesc>操作结果描述</operDesc>");
		responseXml.append("</header>");
		responseXml.append("<body>");
		responseXml.append("<project>");
		responseXml.append("<prjCode>项目编号</prjCode>");
		responseXml.append("<packCode>包件编号</packCode>");
		responseXml.append("<downUrl>投标回执下载地址</downUrl>");
		responseXml.append("</project>");
		responseXml.append("</body>");
		responseXml.append("</confirmTender>");
		logger.debug("BidSoapService    confirmTender====================================start\n"+responseXml.toString());
		logger.debug("BidSoapService    confirmTender====================================end\n");
		return responseXml.toString();
	}
	
	
	/**
	 * FuncName: getReceiptFile
	 * Description :获取投标回执
	 * @param projCode   项目编号
	 * @param packCode   包件编号
	 * @param username   用户名
	 * @param password   密码
	 * @throws Exception void
	 * @author: liuke
	 * @Create Date:2011-6-21  下午03:19:29
	 * @Modifier: liuke
	 * @Modified Date:2011-6-21  下午03:19:29
	 */
	public String  getReceiptFile(String  projCode, String packCode,String username ,String password)throws Exception{
		StringBuffer responseXml = new StringBuffer();
		responseXml.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
		responseXml.append("<getReceiptFile xmlns=\"gpcsoft.com\">");
		responseXml.append("<header>");
		responseXml.append("<operTag>Y/N</operTag>");
		responseXml.append("<operDesc>操作结果描述</operDesc>");
		responseXml.append("</header>");
		responseXml.append("<body>");
		responseXml.append("<project>");
		responseXml.append("<prjCode>项目编号</prjCode>");
		responseXml.append("<packCode>包件编号</packCode>");
		responseXml.append("<downUrl>投标回执下载地址</downUrl>");
		responseXml.append("</project>");
		responseXml.append("</body>");
		responseXml.append("</getReceiptFile>");
		logger.debug("BidSoapService    getReceiptFile====================================start\n"+responseXml.toString());
		logger.debug("BidSoapService    getReceiptFile====================================end\n");
		return responseXml.toString();
	}
	
	/**
	 * FuncName: withdrawTender
	 * Description :撤标
	 * @param projCode   项目编号
	 * @param packCode   包件编号
	 * @param username   用户名
	 * @param password   密码
	 * @throws Exception void
	 * @author: liuke
	 * @Create Date:2011-6-21  下午03:22:02
	 * @Modifier: liuke
	 * @Modified Date:2011-6-21  下午03:22:02
	 */
	public String withdrawTender(String projCode, String packCode,String username,String password)throws Exception{
		StringBuffer responseXml = new StringBuffer();
		responseXml.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
		responseXml.append("<withdrawTender xmlns=\"gpcsoft.com\">");
		responseXml.append("<header>");
		responseXml.append("<operTag>Y/N</operTag>");
		responseXml.append("<operDesc>操作结果描述</operDesc>");
		responseXml.append("</header>");
		responseXml.append("<body>");
		responseXml.append("</body>");
		responseXml.append("</withdrawTender>");
		logger.debug("BidSoapService    withdrawTender====================================start\n"+responseXml.toString());
		logger.debug("BidSoapService    withdrawTender====================================end\n");
		return responseXml.toString();
	}
}
