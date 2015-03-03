package com.gpcsoft.epp.webService.webService.ue.http;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.utils.MathUtil;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.bid.domain.AnonymousEnum;
import com.gpcsoft.epp.bid.domain.Bid;
import com.gpcsoft.epp.bid.domain.BidPackage;
import com.gpcsoft.epp.bid.domain.BidReceipt;
import com.gpcsoft.epp.bid.domain.BidReceiptEnum;
import com.gpcsoft.epp.bid.domain.BidSubKey;
import com.gpcsoft.epp.bid.service.AnonymousTenderNo;
import com.gpcsoft.epp.bid.service.BidPackageService;
import com.gpcsoft.epp.bid.service.BidReceiptService;
import com.gpcsoft.epp.bid.service.BidService;
import com.gpcsoft.epp.bid.service.BidSubKeyService;
import com.gpcsoft.epp.bid.service.GenTenderNo;
import com.gpcsoft.epp.bid.service.impl.AnonymousTenderNoImpl;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.common.domain.EsExceptionEnum;
import com.gpcsoft.epp.common.service.UserApiService;
import com.gpcsoft.epp.common.utils.DateUtil;
import com.gpcsoft.epp.common.utils.StringUtil;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.domain.ProjectRule;
import com.gpcsoft.epp.project.service.ProjectService;
import com.gpcsoft.epp.projreview.domain.OpenBid;
import com.gpcsoft.epp.projreview.domain.OpenBidRecord;
import com.gpcsoft.epp.projreview.domain.OpenBidRecordEnum;
import com.gpcsoft.epp.projreview.service.OpenBidRecordService;
import com.gpcsoft.epp.projreview.service.OpenBidService;
import com.gpcsoft.epp.webService.common.util.ZipUtils;
import com.gpcsoft.epp.webService.ueSystem.bulletin.dto.SubFileDTO;
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.auth.service.AttachmentService;


/**
 * @author caojz
 *
 */
@SuppressWarnings("unchecked")
@Controller//标识为控制器
@Scope("request")
@RequestMapping("/BidHttpService.do")//页面请求路径
public class BidHttpService extends AnnotationMultiController{
	
	@Autowired(required=true)  @Qualifier("bidSubKeyServiceImpl")
	private BidSubKeyService bidSubKeyService;
	
	@Autowired(required=true) @Qualifier("userApiServiceImpl")
	private UserApiService userApiService;
	
	@Autowired(required=true) @Qualifier("projectServiceImpl")
	private ProjectService projectService;
	
	@Autowired(required=true) @Qualifier("bidServiceImpl")
	private BidService bidService;
	
	@Autowired(required=true) @Qualifier("bidPackageServiceImpl")
	private BidPackageService bidPackageService;
	
	@Autowired(required=true) @Qualifier("attachmentServiceImpl")
	private  AttachmentService attachmentService;
	
	@Autowired(required=true) @Qualifier("bidReceiptServiceImpl")
	private BidReceiptService bidReceiptService;
	

	@Autowired(required=true) @Qualifier("openBidServiceImpl")
	private OpenBidService openBidService;
	
	@Autowired(required=true) @Qualifier("openBidRecordServiceImpl")
	private OpenBidRecordService openBidRecordServiceImpl;
	
	private GenTenderNo genTenderNo; 
	private GenTenderNo getGenTenderNoImpl(){
		if(this.genTenderNo == null){
			this.genTenderNo = (GenTenderNo)FrameBeanFactory.getBean("genTenderNoImpl");
		}
		return this.genTenderNo;
	}
	
	private AnonymousTenderNo anonymousTenderNo; 
	private AnonymousTenderNo getAnonymousTenderNoImpl(){
		if(this.anonymousTenderNo == null){
			this.anonymousTenderNo = (AnonymousTenderNo)FrameBeanFactory.getBean("anonymousTenderNoImpl");
		}
		return this.anonymousTenderNo;
	}
	
	/**
	 * FuncName:  tenderProject
	 * Description :  验证投标接口
	 * @param 
	 * @param request
	 * @param response
	 * @throws Exception void
	 * @author: liuke
	 * @Create Date:2011-5-11  上午10:56:57
	 * @Modifier: liuke
	 * @Modified Date:2011-5-11  上午10:56:57
	 */
	@RequestMapping(params="method=tenderProject")
	public void tenderProject(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String projCode = request.getParameter("prjCode");  //项目编号
		String packCode = request.getParameter("packCode");  //包件编号
		String username = request.getParameter("username");  //用户名
		String password = request.getParameter("password");  //密码
		String contect = request.getParameter("content");    //输入
		String tenderOrgCode = request.getParameter("tenderOrgCode");  //投标机构编号
		String tenderOrgName = request.getParameter("tenderOrgName");  //投标机构名称
		String errorMessage = "";    //错误信息
		String packId = "";     //包组或项目ID
		String supplierId = "";      //供应商ID
		String projectAttaPath = messageSource.getMessage("epp.projectAttaPath");   //保存上传文件根路径
		String issuccess="N";
		String bidFilePath = "";  //投标文件物理路径
		boolean md5Flag = false;     //md5Flag 	 
		String exception = "";
		Document document;  
		Project project = projectService.findProjectByProjCode(projCode);
		Project subProject = projectService.findProjectByProjCodeAndParent(packCode, project.getObjId());
		if(subProject==null){
			packId = project.getObjId();
		}else{
			packId = subProject.getObjId();
		}
		OrgInfo supplier =  userApiService.getOrgInfoByOrgCode(tenderOrgCode);
		if(supplier!=null){
			supplierId = supplier.getObjId();
		}
		bidFilePath = projectAttaPath.replace("/", File.separator) +project.getObjId()+File.separator+packId+File.separator+supplierId+File.separator+CommonEnum.BID_FILE_DIR+File.separator;
		List<SubFileDTO> subFileList = new ArrayList<SubFileDTO>();
		try {
			if(contect!=null&&!"".equals(contect)){   //如果验证xml不为null
				document = DocumentHelper.parseText(contect);
				Element element =  document.getRootElement();
				List <Element> tenderFileElementList = element.element("body").element("tenderFile").elements("subFile");
				for(Element tenderFileElement:tenderFileElementList){
					SubFileDTO subFile = new SubFileDTO();
					subFile.setDocId(tenderFileElement.element("docId").getText());
					subFile.setHashCode(tenderFileElement.element("hashCode").getText());
					subFileList.add(subFile);
				}
				md5Flag = bidService.checkBidFileMd5Complete(subFileList, bidFilePath);  //校验每次所投标分册文件的md5值
			}
			if(md5Flag){
				issuccess = "Y";
			}else{
				issuccess = "N";
				errorMessage = "投标文件md5值检测不一致";
			}  
		} catch (Exception e) {
			errorMessage = EsExceptionEnum.ES_SYSTEM_ERROR;
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
		responseXml.append("<fileName></fileName>");
		responseXml.append("<downType>http</downType>");
		responseXml.append("<downUrl></downUrl>");
		responseXml.append("</returnFile>");
		responseXml.append("</body>");
		responseXml.append("</tenderProject>");
		logger.debug("BidHttpService  tenderProject start=============================================\n"+responseXml.toString());
		logger.debug("BidHttpService  tenderProject end=============================================\n");
		write(responseXml.toString(), response);
	}
	
	/**
	 * FuncName: tenderApply
	 * Description :  申请投标接口
	 * @param  prjCode:项目编号
	 * @param packCode:包件编号
     * @param  username:用户名
     * @param  password: 密码	
     * @param  tenderOrgCode:投标机构编号
     * @param  tenderOrgName:投标机构名称	
	 * @param request
	 * @param response
	 * @throws Exception void
	 * @author: liuke
	 * @Create Date:2011-6-21  上午11:35:38
	 * @Modifier: liuke
	 * @Modified Date:2011-6-21  上午11:35:38
	 */
	@RequestMapping(params="method=tenderApply")
	public void tenderApply(HttpServletRequest request, HttpServletResponse response)throws Exception{
		String projCode = request.getParameter("prjCode");  //项目编号
		String packCode = request.getParameter("packCode");  //包件编号
		String tenderOrgCode = request.getParameter("tenderOrgCode");  //投标机构编号
		String projId = "";
		String supplierId = "";
		String errorMessage = "";
		StringBuffer operDesc = new StringBuffer(); //操作描述
		boolean isSuccess = true;//是否执行成功                   
		try {                                       
			Project project = projectService.findProjectByProjCode(projCode);
			Project subProject = projectService.findProjectByProjCodeAndParent(packCode, project.getObjId());
			ProjectRule projectRule = new ProjectRule();
			if(project!=null&&project.getObjId()!=null){
				projectRule = projectService.getProjectRuleByProjectId(project.getObjId());
			}
			if(subProject==null){
				projId = project.getObjId();
			}else{
				projId = subProject.getObjId();
			}
			OrgInfo supplier =  userApiService.getOrgInfoByOrgCode(tenderOrgCode);
			if(supplier!=null){
				supplierId = supplier.getObjId();
			}
			if (null==project) {//项目不存在
				operDesc = new StringBuffer(messageSource.getMessage(EsExceptionEnum.PROJECT_ISNOTEXISTED));
				isSuccess = false;
			} else {
				Date today = new Date();
				if(today.before(projectRule.getSubmitStTime())){//未到投标开始时间
					operDesc.append(messageSource.getMessage(EsExceptionEnum.BEFORE_SUBMIT_TIME)).append(",");
					isSuccess = false;
				}
				if(today.after(projectRule.getSubmitETime())){//投标时间已过
					operDesc.append(messageSource.getMessage(EsExceptionEnum.AFTER_SUBMIT_TIME)).append(",");
					isSuccess = false;
				}
			}
			if(isSuccess){
					List<Bid> bidList = bidService.getBidListByProjectIdAndUserId(project.getObjId(), supplierId);
					if(bidList!=null&&!bidList.isEmpty()){
						Bid bid = bidList.get(0);
						BidPackage bidPackage = bidPackageService.getBidPackageByPackIdAndBidId(projId, bid.getObjId()); 
						if(bidPackage!=null&&CommonEnum.USER_STATUS_FORMAL.equals(bidPackage.getUseStatus()) ){
							isSuccess = false;  //如果投标已经确认，就不能再投标
							operDesc.append("已经完成投标！").append(",");
						}
					}
			}
			if(isSuccess){
				//向数据库中保存信息     如果验证通过，就保存投标信息
				bidService.saveBidForClient(tenderOrgCode,projCode,packCode);
			}
		} catch (Exception e) {
			isSuccess = false;
			errorMessage = e.getMessage();
		}
		StringBuffer responseXml = new StringBuffer();
		responseXml.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
		responseXml.append("<tenderApply xmlns=\"http://www.gpcsoft.com\">");
		responseXml.append("<header>");
		responseXml.append("<operTag>"+(isSuccess == true ? "Y" : "N")+"</operTag>");
		responseXml.append("<operDesc>"+(isSuccess == true ? "操作成功" : operDesc)+"</operDesc>");
		responseXml.append("<operException>"+errorMessage+"</operException>");
		responseXml.append("</header>");
		responseXml.append("<body></body>");
		responseXml.append("</tenderApply>");
		logger.debug("BidHttpService  tenderApply start=============================================\n"+responseXml.toString());
		logger.debug("BidHttpService  tenderApply end=============================================\n");
		write(responseXml.toString(), response);
	}
	/**
	 * FuncName: anonymousTenderApply
	 * Description :  申请匿名投标
	 * @param 
	 * @param request
	 * @param response
	 * @throws Exception void
	 * @author: shenjz
	 * @Create Date:2011-12-13  上午11:23:27
	 * @Modifier: shenjz
	 * @Modified Date:2011-12-13  上午11:23:27
	 */
	@RequestMapping(params="method=anonymousTenderApply")
	public void anonymousTenderApply(HttpServletRequest request, HttpServletResponse response)throws Exception{
		String projCode = request.getParameter("prjCode");  //项目编号
		String packCode = request.getParameter("packCode");  //包件编号
		String tenderMark = projCode+packCode+request.getParameter("tenderMark");//投标标识，添加项目和包组编号
		String errorMessage = "";
		String bidNo = "";
		String projId = "";
		StringBuffer operDesc = new StringBuffer(); //操作描述
		boolean isSuccess = true;//是否执行成功                   
		try {          
			bidNo = this.getGenTenderNoImpl().getTenderNo(projCode,packCode);//获取匿名编号
			Project project = projectService.findProjectByProjCode(projCode);
			String anonymousTenderC = this.getAnonymousTenderNoImpl().getCheckResult(tenderMark);
			String anonymousTenderV = anonymousTenderC.substring(0, anonymousTenderC.indexOf("_"));//检查的结果
			String anonymousTenderM = anonymousTenderC.substring(anonymousTenderC.indexOf("_")+1, anonymousTenderC.length());//检查的描述
			if("true".equals(anonymousTenderV)){
				if(anonymousTenderM != null && !"".equals(anonymousTenderM)){//若有描述信息，则将信息添加到描述里
					operDesc.append(anonymousTenderM);
				}
				if (null==project) {//项目不存在
					operDesc.append(messageSource.getMessage(EsExceptionEnum.PROJECT_ISNOTEXISTED));
					isSuccess = false;
				} else {
					Project subProject = projectService.findProjectByProjCodeAndParent(packCode, project.getObjId());
					ProjectRule projectRule = new ProjectRule();
					if(project!=null&&project.getObjId()!=null){
						projectRule = projectService.getProjectRuleByProjectId(project.getObjId());
					}
					if(subProject==null){
						projId = project.getObjId();
					}else{
						projId = subProject.getObjId();
					}
					if(projectRule!=null){
						Date today = new Date();
						if(today.before(projectRule.getSubmitStTime())){//未到投标开始时间
							operDesc.append(messageSource.getMessage(EsExceptionEnum.BEFORE_SUBMIT_TIME)).append(",");
							isSuccess = false;
						}
						if(today.after(projectRule.getSubmitETime())){//投标时间已过
							operDesc.append(messageSource.getMessage(EsExceptionEnum.AFTER_SUBMIT_TIME)).append(",");
							isSuccess = false;
						}
						if(AnonymousEnum.notAnonymous.equals(projectRule.getRuleAnonymous())){
							operDesc.append("该项目不能进行匿名投标！");
							isSuccess = false;
						}
					}
				}
				if(isSuccess){
					//向数据库中保存信息     如果验证通过，就保存投标信息
					bidService.saveBidAnonymousForClient(bidNo, projCode, packCode);
				}
			}else{
				isSuccess = false;
				operDesc.append(anonymousTenderM);//将不能申请匿名投标的内容添加到返回信息里
			}			
		} catch (Exception e) {
			isSuccess = false;
			errorMessage = e.getMessage();
		}
		StringBuffer responseXml = new StringBuffer();
		logger.debug("BidHttpService  anonymousTenderApply  start=============================================\n"+responseXml.toString());
		responseXml.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
		responseXml.append("<anonymousTenderApply  xmlns=\"http://www.gpcsoft.com\">");
		responseXml.append("<header>");
		responseXml.append("<operTag>"+(isSuccess == true ? "Y" : "N")+"</operTag>");
		responseXml.append("<operDesc>"+(isSuccess == true ? "操作成功" : operDesc)+"</operDesc>");
		responseXml.append("<operException>"+errorMessage+"</operException>");
		responseXml.append("</header>");
		responseXml.append("<body>");
		responseXml.append("<tenderNo>"+(isSuccess == true ? bidNo:"")+"</tenderNo>");
		responseXml.append("</body>");
		responseXml.append("</anonymousTenderApply >");
		logger.debug("BidHttpService  anonymousTenderApply  end=============================================\n");
		write(responseXml.toString(), response);
	}
	/**
	 * FuncName: getTenderInfo
	 * Description :  供应商在线获取投标文件及信息
	 * @param 	prjCode:项目编号
	 * @param	packCode:包件编号
	 * @param   username:用户名
	 * @param   password: 密码	
	 * @param request
	 * @param response
	 * @throws Exception void
	 * @author: liuke
	 * @Create Date:2011-6-21  下午02:52:12
	 * @Modifier: liuke
	 * @Modified Date:2011-6-21  下午02:52:12
	 */
	@RequestMapping(params="method=getTenderInfo")
	public void getTenderInfo(HttpServletRequest request, HttpServletResponse response)throws Exception{
		String projCode = request.getParameter("prjCode");  //项目编号
		String packCode = request.getParameter("packCode");  //包件编号
		String username = request.getParameter("username");  //用户名
		String password = request.getParameter("password");  //密码
		String errorMessage = "";
		String packId = "";
		String supplierId = "";
		String xmlContent = "";
		boolean isSuccess = true;//是否执行成功     
		String isFinish ="";  //投标是否完成
		String tempPath ="";
		StringBuffer responseXml = new StringBuffer();
		try {
			String bidFilePath = messageSource.getMessage("epp.projectAttaPath");
			Project project = projectService.findProjectByProjCode(projCode);
			if(project!=null){
				Project subProject = projectService.findProjectByProjCodeAndParent(packCode, project.getObjId());
				if(subProject==null){
					packId = project.getObjId();
				}else{ 
					packId = subProject.getObjId();
				}
				String decodeStringPassword = StringUtil.decodeString(password); //用Base64算法对字符串进行解码.
				String md5PassWord =  MathUtil.encryptPassWordSHA(decodeStringPassword);//密码转换成加密字符串
				List<User> userList = userApiService.getUserByUserName(username, md5PassWord);
				if(userList!=null&&!userList.isEmpty()){
					User user = userList.get(0);
					OrgInfo supplier = userApiService.getOrgInfoByUser(user);
					if(supplier!=null){
						supplierId = supplier.getObjId();
					}
				}
				tempPath = project.getObjId()+File.separator+packId+File.separator+supplierId+File.separator+CommonEnum.BID_FILE_DIR; //此处要拼出投标文件路径，因为此时还未往数据库中存储文件路径
				xmlContent = bidService.getTenderInfoXml(tempPath, bidFilePath);
				List<Bid> bidList = bidService.getBidListByProjectIdAndUserId(project.getObjId(), supplierId);
				if(bidList!=null&&!bidList.isEmpty()){
					Bid bid = bidList.get(0);
					BidPackage bidPackage = bidPackageService.getBidPackageByPackIdAndBidId(packId, bid.getObjId()); 
					if(bidPackage!=null&&CommonEnum.USER_STATUS_FORMAL.equals(bidPackage.getUseStatus()) ){
						isFinish = "Y";  //如果已经完成投标
					}else if(bidPackage!=null&&CommonEnum.USER_STATUS_TEMP.equals(bidPackage.getUseStatus()) ){
						isFinish = "N";  //如果已经未完成投标
					}
				}
			}
		} catch (Exception e) {
			isSuccess = false;
			errorMessage = e.getMessage();
		}
		responseXml.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
		responseXml.append("<getTenderInfo xmlns=\"http://www.gpcsoft.com\">");
		responseXml.append("<header>");
		responseXml.append("<operTag>"+(isSuccess == true ? "Y" : "N")+"</operTag>");
		responseXml.append("<operDesc>"+(isSuccess == true ? "成功" : "失败")+"</operDesc>");
		responseXml.append("<operException>"+errorMessage+"</operException>");
		responseXml.append("</header>");
		responseXml.append("<body>");
		responseXml.append("<project>");
		responseXml.append("<prjCode>"+projCode+"</prjCode>");
		responseXml.append("<packCode>"+packCode+"</packCode>");
		responseXml.append("<isFinished>"+isFinish+"</isFinished>");
		responseXml.append("<sections>");     //不是true的不输出。
		responseXml.append(xmlContent);
		responseXml.append("</sections>");
		responseXml.append("</project>");
		responseXml.append("</body>");
		responseXml.append("</getTenderInfo>");
		logger.debug("BidHttpService  getTenderInfo start=============================================\n"+responseXml.toString());
		logger.debug("BidHttpService  getTenderInfo end=============================================\n");
		write(responseXml.toString(), response);
	}
	
	/**
	 * FuncName: confirmTender
	 * Description :确认投标
	 * @param 
	 * @param request
	 * @param response
	 * @throws Exception void
	 * @author: liuke
	 * @Create Date:2011-6-21  下午03:10:16
	 * @Modifier: liuke
	 * @Modified Date:2011-6-21  下午03:10:16
	 */
	@RequestMapping(params="method=confirmTender")
	public void confirmTender(HttpServletRequest request, HttpServletResponse response)throws Exception{
		String projCode = request.getParameter("prjCode");  //项目编号
		String packCode = request.getParameter("packCode");  //包件编号
		String username = request.getParameter("username");  //用户名
		String password = request.getParameter("password");  //密码
		boolean isSuccess = true;//是否执行成功     
		String errorMessage = "";
		String exception ="";
		String attachRelaId = "";
		String packId = "";
		String supplierId = "";
		String bidFilePath = "";
		try {
			String projectAttaPath = messageSource.getMessage("epp.projectAttaPath");   //保存上传文件根路径
			Project project = projectService.findProjectByProjCode(projCode);
			if(project!=null){
				Project subProject = projectService.findProjectByProjCodeAndParent(packCode, project.getObjId());
				if(subProject==null){
					packId = project.getObjId();
				}else{
					packId = subProject.getObjId();
				}
				String decodeStringPassword = StringUtil.decodeString(password); //用Base64算法对字符串进行解码.
				String md5PassWord =  MathUtil.encryptPassWordSHA(decodeStringPassword);//密码转换成加密字符串
				List<User> userList = userApiService.getUserByUserName(username, md5PassWord);
				if(userList!=null&&!userList.isEmpty()){
					User user = userList.get(0);
					OrgInfo supplier = userApiService.getOrgInfoByUser(user);
					if(supplier!=null){
						supplierId = supplier.getObjId();
					}
				}
				bidFilePath = projectAttaPath.replace("/", File.separator) +project.getObjId()+File.separator+packId+File.separator+supplierId+File.separator+CommonEnum.BID_FILE_DIR+File.separator;
				isSuccess =  bidService.checkBidFileComplete(bidFilePath);   //检查所有分册文件是否全部投标
				if(isSuccess){ //如果分册全部上传完整
					BidPackage bidPackage =	bidService.saveConfirmTender(projCode, packCode, username, password);
					BidReceipt bidReceipt = bidReceiptService.getBidReceiptByBidPackageId(bidPackage.getObjId(), BidReceiptEnum.TB_Receipt);
					attachRelaId = bidReceipt.getBidReFileId();
				}else{
					errorMessage = "投标文件上传不完整";
				}
				
			}
		} catch (Exception e) {
			isSuccess = false;
			exception = e.getMessage();
		}
		StringBuffer responseXml = new StringBuffer();
		responseXml.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
		responseXml.append("<confirmTender xmlns=\"http://www.gpcsoft.com\">");
		responseXml.append("<header>");
		responseXml.append("<operTag>"+ (isSuccess == true ? "Y" : "N") +"</operTag>");
		responseXml.append("<operDesc>"+(isSuccess == true ? "成功" : errorMessage) +"</operDesc>");
		responseXml.append("<operException>"+exception+"</operException>");
		responseXml.append("</header>");
		responseXml.append("<body>");
		responseXml.append("<project>");
		responseXml.append("<prjCode>"+projCode+"</prjCode>");
		responseXml.append("<packCode>"+packCode+"</packCode>");
		responseXml.append("<downUrl>" + messageSource.getMessage("applicationInternetAddress") + "/DownloadFileHttpServlet?attachmentObjId="+attachRelaId+"&amp;fileType="+CommonEnum.FILE_TYPE_RECEIPT+"</downUrl>");
		responseXml.append("</project>");
		responseXml.append("</body>");
		responseXml.append("</confirmTender>");
		logger.debug("BidHttpService  confirmTender start=============================================\n"+responseXml.toString());
		logger.debug("BidHttpService  confirmTender end=============================================\n");
		write(responseXml.toString(), response);
	}
	
	/**
	 * FuncName: confirmAnonymousTender
	 * Description :供应商确认匿名投标
	 * @param 
	 * @param request
	 * @param response
	 * @throws Exception void
	 * @author: shenjz
	 * @Create Date:2011-12-13  下午01:45:39
	 * @Modifier: shenjz
	 * @Modified Date:2011-12-13  下午01:45:39
	 */
	@RequestMapping(params="method=confirmAnonymousTender")
	public void confirmAnonymousTender(HttpServletRequest request, HttpServletResponse response)throws Exception{
		String projCode = request.getParameter("prjCode");  //项目编号
		String packCode = request.getParameter("packCode");  //包件编号
		String tenderMark = projCode+packCode+request.getParameter("tenderMark");//投标标识，添加项目和包组编号
		boolean isSuccess = true;//是否执行成功     
		String errorMessage = "";
		String exception ="";
		String attachRelaId = "";
		String packId = "";
		String bidFilePath = "";
		String bidNo = request.getParameter("tenderNo");
		try {
			String projectAttaPath = messageSource.getMessage("epp.projectAttaPath");   //保存上传文件根路径
			Project project = projectService.findProjectByProjCode(projCode);
			if(project!=null){
				Project subProject = projectService.findProjectByProjCodeAndParent(packCode, project.getObjId());
				if(subProject==null){
					packId = project.getObjId();
				}else{
					packId = subProject.getObjId();
				}
				bidFilePath = projectAttaPath.replace("/", File.separator) +project.getObjId()+File.separator+packId+File.separator+bidNo+File.separator+CommonEnum.BID_FILE_DIR+File.separator;
				isSuccess =  bidService.checkBidFileComplete(bidFilePath);   //检查所有分册文件是否全部投标
				if(isSuccess){ //如果分册全部上传完整
					BidPackage bidPackage =	bidService.saveConfirmAnonymousTender(projCode, packCode, bidNo);
					BidReceipt bidReceipt = bidReceiptService.getBidReceiptByBidPackageId(bidPackage.getObjId(), BidReceiptEnum.TB_Receipt);
					attachRelaId = bidReceipt.getBidReFileId();
				}else{
					errorMessage = "投标文件上传不完整";
				}
			}
			if(isSuccess){//若成功，则将数据加1
				this.getAnonymousTenderNoImpl().checkResult(tenderMark);
			}
		} catch (Exception e) {
			isSuccess = false;
			exception = e.getMessage();
		}
		StringBuffer responseXml = new StringBuffer();
		responseXml.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
		responseXml.append("<confirmTender xmlns=\"http://www.gpcsoft.com\">");
		responseXml.append("<header>");
		responseXml.append("<operTag>"+ (isSuccess == true ? "Y" : "N") +"</operTag>");
		responseXml.append("<operDesc>"+(isSuccess == true ? "成功" : errorMessage) +"</operDesc>");
		responseXml.append("<operException>"+exception+"</operException>");
		responseXml.append("</header>");
		responseXml.append("<body>");
		responseXml.append("<project>");
		responseXml.append("<prjCode>"+projCode+"</prjCode>");
		responseXml.append("<packCode>"+packCode+"</packCode>");
		responseXml.append("<downUrl>" + messageSource.getMessage("applicationInternetAddress") + "/DownloadFileHttpServlet?attachmentObjId="+attachRelaId+"&amp;fileType="+CommonEnum.FILE_TYPE_RECEIPT+"</downUrl>");
		responseXml.append("</project>");
		responseXml.append("</body>");
		responseXml.append("</confirmTender>");
		logger.debug("BidHttpService  confirmTender start=============================================\n"+responseXml.toString());
		logger.debug("BidHttpService  confirmTender end=============================================\n");
		write(responseXml.toString(), response);
	}
	/**
	 * FuncName: getReceiptFile
	 * Description :获取投标回执
	 * @param 
	 * @param request
	 * @param response
	 * @throws Exception void
	 * @author: liuke
	 * @Create Date:2011-6-21  下午03:19:29
	 * @Modifier: liuke
	 * @Modified Date:2011-6-21  下午03:19:29
	 */
	@RequestMapping(params="method=getReceiptFile")
	public void getReceiptFile(HttpServletRequest request, HttpServletResponse response)throws Exception{
		String projCode = request.getParameter("prjCode");  //项目编号
		String packCode = request.getParameter("packCode");  //包件编号
		String username = request.getParameter("username");  //用户名
		String password = request.getParameter("password");  //密码
		boolean isSuccess = true;//是否执行成功     
		String exception = "";
		String errorMessage = "";
		String attachRelaId = "";
		String downUrl="";
		try {
			BidPackage bidPackage = null;
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
			Bid bid = bidService.getBidByProjCode(projCode, supplierId);
			bidPackage = bidPackageService.getBidPackageByPackCodeAndBidId(packCode, bid.getObjId());
			BidReceipt bidReceipt = bidReceiptService.getBidReceiptByBidPackageId(bidPackage.getObjId(), BidReceiptEnum.TB_Receipt);
			attachRelaId = bidReceipt.getBidReFileId();
			downUrl = messageSource.getMessage("applicationInternetAddress") + "/DownloadFileHttpServlet?attachmentObjId="+attachRelaId+"&amp;fileType="+CommonEnum.FILE_TYPE_RECEIPT;
			List attachmentList = attachmentService.getAttachmentsByRealID(attachRelaId);	
			if(attachmentList != null && !attachmentList.isEmpty()){
				Attachment attachment = (Attachment)attachmentList.get(0);
				String  filePath = (messageSource.getMessage("epp.projectAttaPath")+((attachment.getPath()==null)?"":attachment.getPath())).replace("/", File.separator);
				if(!ZipUtils.fileIsExist(filePath,attachment.getFileName())){//判断文件在服务器上是否存在
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
		responseXml.append("<getReceiptFile xmlns=\"http://www.gpcsoft.com\">");
		responseXml.append("<header>");
		responseXml.append("<operTag>"+(isSuccess?"Y":"N")+"</operTag>");
		responseXml.append("<operDesc>"+(isSuccess?"成功":errorMessage)+"</operDesc>");
		responseXml.append("<operException>"+exception+"</operException>");
		responseXml.append("</header>");
		responseXml.append("<body>");
		responseXml.append("<project>");
		responseXml.append("<prjCode>"+projCode+"</prjCode>");
		responseXml.append("<packCode>"+packCode+"</packCode>");
		responseXml.append("<downUrl>" +(isSuccess == true ? downUrl : "")+"</downUrl>");
		responseXml.append("</project>");
		responseXml.append("</body>");
		responseXml.append("</getReceiptFile>");
		logger.debug("BidHttpService  getReceiptFile start=============================================\n"+responseXml.toString());
		logger.debug("BidHttpService  getReceiptFile end=============================================\n");
		write(responseXml.toString(), response);
	}
	
	
	/**
	 * FuncName: withdrawTender
	 * Description :撤标
	 * @param 
	 * @param request
	 * @param response
	 * @throws Exception void
	 * @author: liuke
	 * @Create Date:2011-6-21  下午03:22:02
	 * @Modifier: liuke
	 * @Modified Date:2011-6-21  下午03:22:02
	 */
	@RequestMapping(params="method=withdrawTender")
	public void withdrawTender(HttpServletRequest request, HttpServletResponse response)throws Exception{
		String projCode = request.getParameter("prjCode");  //项目编号
		String packCode = request.getParameter("packCode");  //包件编号
		String username = request.getParameter("username");  //用户名
		String password = request.getParameter("password");  //密码
		String bidNo = request.getParameter("tenderNo");
		boolean isSuccess = true;//是否执行成功   
		String attachRelaId = ""; //撤标回执Id
		String errorMessage = "";
		Project project = projectService.findProjectByProjCode(projCode);
		if(project!=null){
			ProjectRule projectRule = projectService.getProjectRuleByProjectId(project.getObjId());
			try {
				if(projectRule!=null&&AnonymousEnum.isAnonymous.equals(projectRule.getRuleAnonymous())){
					BidPackage bidPackage =	bidService.saveWithdrawTender(projCode, packCode, username, password);	
					BidReceipt bidReceipt = bidReceiptService.getBidReceiptByBidPackageId(bidPackage.getObjId(), BidReceiptEnum.CB_Receipt);
					attachRelaId = bidReceipt.getBidReFileId();
				}else{
					BidPackage bidPackage =	bidService.saveWithdrawAnonymousTender(projCode, packCode,bidNo);	
					BidReceipt bidReceipt = bidReceiptService.getBidReceiptByBidPackageId(bidPackage.getObjId(), BidReceiptEnum.CB_Receipt);
					attachRelaId = bidReceipt.getBidReFileId();
				}
			} catch (Exception e) {
				isSuccess = false;
				errorMessage = EsExceptionEnum.ES_SYSTEM_ERROR;
				e.printStackTrace();
			}
		}else {
			isSuccess = false;
			errorMessage = "项目不存在!";
		}
		StringBuffer responseXml = new StringBuffer();
		responseXml.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
		responseXml.append("<withdrawTender xmlns=\"http://www.gpcsoft.com\">");
		responseXml.append("<header>");
		responseXml.append("<operTag>"+(isSuccess == true ? "Y" : "N")+"</operTag>");
		responseXml.append("<operDesc>"+errorMessage+"</operDesc>");
		responseXml.append("</header>");
		responseXml.append("<body>");
		responseXml.append("<project>");
		responseXml.append("<prjCode>"+projCode+"</prjCode>");
		responseXml.append("<packCode>"+packCode+"</packCode>");
		responseXml.append("<downUrl>"+messageSource.getMessage("applicationInternetAddress") + "/DownloadFileHttpServlet?attachmentObjId="+attachRelaId+"&amp;fileType="+CommonEnum.FILE_TYPE_RECEIPT+"</downUrl>");
		responseXml.append("</project>");
		responseXml.append("</body>");
		responseXml.append("</withdrawTender>");
		logger.debug("BidHttpService  withdrawTender start=============================================\n"+responseXml.toString());
		logger.debug("BidHttpService  withdrawTender end=============================================\n");
		write(responseXml.toString(), response);
	} 
	
	
	/**
	 * FuncName: decryptTenderFile
	 * Description :  供应商在线解密投标文件
	 * @param 
	 * @param request
	 * @param response
	 * @throws Exception void
	 * @author: liuke
	 * @Create Date:2011-7-29  上午11:20:55
	 * @Modifier: liuke
	 * @Modified Date:2011-7-29  上午11:20:55
	 */
	@RequestMapping(params="method=decryptTenderFile")
	public void decryptTenderFile(HttpServletRequest request, HttpServletResponse response)throws Exception{
		String projCode = request.getParameter("prjCode");  //项目编号
		String packCode = request.getParameter("packCode");  //包件编号
		String username = request.getParameter("username");  //用户名
		String password = request.getParameter("password");  //密码
		String caId = request.getParameter("caId");  //ca证书id号
		String tenderOrgCode = request.getParameter("tenderOrgCode");  //投标机构编号
		String tenderOrgName = request.getParameter("tenderOrgName");  //投标机构名称
		String encryptedText = request.getParameter("encryptedText");  //加密原文
		boolean isSuccess = true;//是否执行成功   
		StringBuffer responseXml = new StringBuffer();
		responseXml.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
		responseXml.append("<decryptTenderFile xmlns=\"http://www.gpcsoft.com\">");
		responseXml.append("<header>");
		responseXml.append("<operTag>"+(isSuccess == true ? "Y" : "N")+"</operTag>");
		responseXml.append("<operDesc></operDesc>");
		responseXml.append("</header>");
		responseXml.append("<body>");
		responseXml.append("<project>");
		responseXml.append("<prjCode>"+projCode+"</prjCode>");
		responseXml.append("<packCode>"+packCode+"</packCode>");
		responseXml.append("<openStatus>Y</openStatus>");
		responseXml.append("<openDesc></openDesc>");
		responseXml.append("</project>");
		responseXml.append("</body>");
		responseXml.append("</decryptTenderFile>");
		logger.debug("BidHttpService  decryptTenderFile start=============================================\n"+responseXml.toString());
		logger.debug("BidHttpService  decryptTenderFile end=============================================\n");
		write(responseXml.toString(), response);
	} 
	
	/**
	 * FuncName: getProjectProcessStatus
	 * Description :获取项目状态 
	 * @param prjCode:项目编号
	 * @param packCodes:包件编号(可为空；若为多个包件，则包件编号之间以“,”拼装)
	 * @param username:用户名
	 * @param password: 密码
	 * @return String
	 * @author: shenjz
	 * @Create Date:2011-9-30  上午09:47:16
	 * @Modifier: shenjz
	 * @Modified Date:2011-9-30  上午09:47:16
	 */
	@RequestMapping(params="method=getProjectProcessStatus")
	public void getProjectProcessStatus(String prjCode,String packCodes,String username,String password,HttpServletResponse response)throws Exception{
		StringBuffer sb = new StringBuffer();
		Boolean success = true;
		String exception = "";
		String packCode[] = {};
		Project project = null;
		List<Project> projectList = new ArrayList<Project>();
		try {
			project = 	projectService.findProjectByProjCode(prjCode);
			if(packCodes==null||"".equals(packCodes)){
				projectList = projectService.getSubProjectByProjectId((project!=null?project.getObjId():""));
			}else{
				packCode = packCodes.split(",");	
				for (int i = 0; i < packCode.length; i++) {
					Project tempProject = projectService.findProjectByProjCodeAndParent(packCode[i], project.getObjId());
					if(tempProject != null)
						projectList.add(tempProject);
				}
			}
		} catch (Exception e) {
			success = false;
			exception = e.getMessage();
		}
		sb.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
		sb.append("<getPrjRecentInfo xmlns=\"http://www.gpcsoft.com\">");
		sb.append("<header>");
		sb.append("<operTag>"+(success?"Y":"N")+"</operTag>");
		sb.append("<operDesc>"+(success?"成功":"失败")+"</operDesc>");
		sb.append("<operException>"+(success?"":exception)+"</operException>");
		sb.append("</header>");
		sb.append("<body>");
		sb.append("<prjRecentInfo>");
		sb.append("<prjCode>"+project.getProjCode()+"</prjCode>");//项目编号
		sb.append("<packs>");
		if(projectList != null && !projectList.isEmpty()){
			for (Iterator iterator = projectList.iterator(); iterator.hasNext();) {
				Project pack = (Project) iterator.next();
				getProjectStatus(project);
				sb.append("<pack>");
				sb.append("<packCode>"+pack.getProjCode()+"</packCode>");//包件编号
				sb.append("<packStatus>"+(project!=null?project.getProjProcessStatus():"")+"</packStatus>");//当前阶段标志
				sb.append("<packStatusMemo>"+(project!=null?project.getProjProcessStatusCN():"")+"</packStatusMemo>");//当前阶段文字描述
				sb.append("<participateStatus>解密成功</participateStatus>");//参与阶段标志
				sb.append("</pack>");
			}
		}else{
			getProjectStatus(project);
			sb.append("<pack>");
			sb.append("<packCode>"+project.getProjCode()+"</packCode>");//包件编号
			sb.append("<packStatus>"+(project!=null?project.getProjProcessStatus():"")+"</packStatus>");//当前阶段标志
			sb.append("<packStatusMemo>"+(project!=null?project.getProjProcessStatusCN():"")+"</packStatusMemo>");//当前阶段文字描述
			sb.append("<participateStatus>解密成功</participateStatus>");//参与阶段标志
			sb.append("</pack>");
		}		
		sb.append("</packs>");
		sb.append("</prjRecentInfo>");
		sb.append("</body>");
		sb.append("</getPrjRecentInfo>");
		
		logger.debug("BidHttpService getProjectProcessStatus ==========================start\n"+sb.toString());
		logger.debug("BidHttpService getProjectProcessStatus ==========================end\n");
		response.getWriter().write(sb.toString().replaceAll("null", ""));
	}
	
	private Project getProjectStatus(Project project)throws Exception{
		
		String prjStatus = "";
		String prjStatusCN = "";
		ProjectRule projectRule = projectService.getProjectRuleByProjectId(project.getObjId());
		/*if(){
			prjStatus = "apply";//报名阶段
		}
		if(){
			prjStatus = "tender";//投标阶段
		}
		
		if(){
			prjStatus = "bDecrpt";//开始解密
		}
		if(DateUtil.compareDate(new Date(), project.getOpenBidStartDate())>=0){
			prjStatus = "bOpen";//开始开标
			prjStatusCN = "开始开标";
		}
		*/
		if(DateUtil.compareDate(new Date(), projectRule.getOpenBidSDate())<0){
			prjStatus = "eTender";//等待开标
			prjStatusCN = "等待开标";
		}
		if(DateUtil.compareDate(new Date(), projectRule.getOpenBidSDate())>=0){
			prjStatus = "bDecrpt";//开始解密
			prjStatusCN = "开始解密";
		}
		if(DateUtil.compareDate(new Date(), projectRule.getEvalSTime())>=0&&DateUtil.compareDate(new Date(), projectRule.getEvalETime())<0){
			prjStatus = "bEvaluate";//评标开始
			prjStatusCN = "评标开始";
		}
		/*if(){
			prjStatus = "eEvaluate";//评标完成
		}
		if(){
			prjStatus = "ePub ";//已发布结果公示
		}
		if(){
			prjStatus = "bConfirm";//开始确定招标结果
		}
		if(){
			prjStatus = "finish";//项目完成
		}
		if(){
			prjStatus = "pause";//项目暂停
		}
		if(){
			prjStatus = "halt";//终止
		}
		if(){
			prjStatus = "bDecrpt";//开始解密
		}
		if(){
			prjStatus = "bOpen";//开始开标
		}
		if(){
			prjStatus = "eEvaluate";//评标完成
		}
		if(){
			prjStatus = "bConfirm";//开始确定招标结果
		}*/
		project.setProjProcessStatus(prjStatus);
		project.setProjProcessStatusCN(prjStatusCN);
		return project;
	}	
	
	/**
	 * FuncName: getDecrptInfo
	 * Description :  供应商在线获取开标信息
	 * @param 
	 * @param request
	 * @param response
	 * @throws Exception void
	 * @author: liuke
	 * @Create Date:2011-7-29  上午11:20:55
	 * @Modifier: liuke
	 * @Modified Date:2011-7-29  上午11:20:55
	 */
	@RequestMapping(params="method=getDecrptInfo")
	public void getDecrptInfo(HttpServletRequest request, HttpServletResponse response)throws Exception{
		String projCode = request.getParameter("prjCode");  //项目编号
		String packCode = request.getParameter("packCode");  //包件编号
		String username = request.getParameter("username");  //用户名
		String password = request.getParameter("password");  //密码
		String tenderOrgCode = request.getParameter("tenderOrgCode");  //投标机构编号
		String tenderOrgName = request.getParameter("tenderOrgName");  //投标机构名称
		String exception = "";
		boolean isSuccess = true;//是否执行成功   
		String decrptStatus = "N";//是否解密
		String openStatus = "00";//是否正常开标
		String openDesc = "";  //开标成功与否描述
		OpenBidRecord openBidRecord = null;
		try {
			if(projCode==null) throw new Exception("上传的项目编号不能为空！");
			//1、获取项目信息和包组信息
			Project project = projectService.findProjectByProjCode(projCode);
			if(project==null) throw new Exception("上传的项目不存在！");		
			
			Project subProject = packCode==null?null:projectService.findProjectByProjCodeAndParent(packCode,project.getObjId());
			
			//2、获取供应商
			OrgInfo supplier = userApiService.getOrgInfoByOrgCode(tenderOrgCode);
			
			//3、根据项目ID或包组ID获取开标信息
			List<OpenBid> openBidList = null;
			OpenBid openBid = null;
			if(subProject!=null){
				openBidList = openBidService.getOpenBidByProjectIdAndPackId(project.getObjId(), subProject.getObjId());
			}else{
				openBidList = openBidService.getOpenBidByProjectIdAndPackId(project.getObjId(), project.getObjId());
			}
			if(!openBidList.isEmpty()){
				openBid = openBidList.get(0);
			}
			BidPackage bidPack = null;
			if(subProject!=null){
				bidPack = bidPackageService.getBidPackageByPackIdAndSupplierId(subProject.getObjId(), supplier.getObjId());
			}else{
				bidPack = bidPackageService.getBidPackageByPackIdAndSupplierId(project.getObjId(), supplier.getObjId());
			}
			List<BidSubKey> subKeys = bidSubKeyService.getBidSubKeysById(bidPack.getObjId());//根据投标记录ID得到开标子密钥集合
	    	if(!subKeys.isEmpty()){
	    		decrptStatus = "Y";
	    		openDesc = "上传解密信息成功！";
	    	}else{
	    		decrptStatus = "N";
	    		openDesc ="上传解密信息失败！";
	    	}
			//4、根据开标ID和供应商ID来获取一条开标记录信息
			if(openBid!=null){
				openBidRecord = openBidRecordServiceImpl.getOpenBidRecordListByOpenBidIdAndSupplierId(openBid.getObjId(),supplier.getObjId());
				openStatus = openBidRecord.getOpenBRStatus();
			
		    	if(OpenBidRecordEnum.OPENBID_SUCCESS.equals(openStatus)){//如果开标成功
		    		openDesc = "开标成功，你的报价金额为￥"+openBidRecord.getQuoteSum().toString()+"元。";
		    	}else if(OpenBidRecordEnum.OPENBID_ERROR.equals(openStatus)){
		    		openDesc = "开标失败！";
		    	}
			}
		
		} catch (Exception e) {
			isSuccess = false;
			exception = e.getMessage();
		}
		StringBuffer responseXml = new StringBuffer();
		responseXml.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
		responseXml.append("<getDecrptInfo xmlns=\"http://www.gpcsoft.com\">");
		responseXml.append("<header>");
		responseXml.append("<operTag>" + (isSuccess == true ? "Y" : "N") + "</operTag>");
		responseXml.append("<operDesc>" + (isSuccess == true ? "成功" : "失败") + "</operDesc>");
		responseXml.append("<operException>"+exception+"</operException>");
		responseXml.append("</header>");
		responseXml.append("<body>");
		responseXml.append("<project>");
		responseXml.append("<prjCode>"+projCode+"</prjCode>");
		responseXml.append("<packCode>"+packCode+"</packCode>");
		responseXml.append("<decrptStatus>"+decrptStatus+"</decrptStatus>");//由于整个方案未定，现在先由开标状态来处理
		responseXml.append("<openTime></openTime>");
		responseXml.append("<openStatus>"+openStatus+"</openStatus>");
		responseXml.append("<openDesc>"+openDesc+"</openDesc>");
		responseXml.append("</project>");
		responseXml.append("</body>");
		responseXml.append("</getDecrptInfo>");
		logger.debug("BidHttpService    getDecrptInfo====================================start\n"+responseXml.toString());
		logger.debug("BidHttpService    getDecrptInfo====================================end\n");
		write(responseXml.toString(), response);
	} 
	/**
	 * FuncName: anonymousTenderProject
	 * Description :  确认匿名投标
	 * @param 
	 * @param request
	 * @param response
	 * @throws Exception void
	 * @author: shenjz
	 * @Create Date:2011-12-16  上午09:46:54
	 * @Modifier: shenjz
	 * @Modified Date:2011-12-16  上午09:46:54
	 */
	@RequestMapping(params="method=anonymousTenderProject")
	public void anonymousTenderProject(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String projCode = request.getParameter("prjCode");  //项目编号
		String packCode = request.getParameter("packCode");  //包件编号
		String contect = request.getParameter("content");    //输入
		String bidNo = request.getParameter("tenderNo");//匿名投标号
		String errorMessage = "";    //错误信息
		String packId = "";     //包组或项目ID
		String projectAttaPath = messageSource.getMessage("epp.projectAttaPath");   //保存上传文件根路径
		String issuccess="N";
		String bidFilePath = "";  //投标文件物理路径
		boolean md5Flag = false;     //md5Flag 	 
		String exception = "";
		Document document;  
		Project project = projectService.findProjectByProjCode(projCode);
		Project subProject = projectService.findProjectByProjCodeAndParent(packCode, project.getObjId());
		ProjectRule projectRule = projectService.getProjectRuleByProjectId(project.getObjId());
		if(subProject==null){
			packId = project.getObjId();
		}else{
			packId = subProject.getObjId();
		}
		if(projectRule!=null&&AnonymousEnum.isAnonymous.equals(projectRule.getRuleAnonymous())){
			bidFilePath = projectAttaPath.replace("/", File.separator) +project.getObjId()+File.separator+packId+File.separator+bidNo+File.separator+CommonEnum.BID_FILE_DIR+File.separator;
		}
		List<SubFileDTO> subFileList = new ArrayList<SubFileDTO>();
		try {
			if(contect!=null&&!"".equals(contect)){   //如果验证xml不为null
				document = DocumentHelper.parseText(contect);
				Element element =  document.getRootElement();
				List <Element> tenderFileElementList = element.element("body").element("tenderFile").elements("subFile");
				for(Element tenderFileElement:tenderFileElementList){
					SubFileDTO subFile = new SubFileDTO();
					subFile.setDocId(tenderFileElement.element("docId").getText());
					subFile.setHashCode(tenderFileElement.element("hashCode").getText());
					subFileList.add(subFile);
				}
				if(!subFileList.isEmpty()){
					if("".equals(subFileList.get(0).getDocId().trim())&&"".equals(subFileList.get(0).getHashCode().trim())){
						md5Flag = true;
					}else{
						md5Flag = bidService.checkBidFileMd5Complete(subFileList, bidFilePath);  //校验每次所投标分册文件的md5值
					}
				}
			}
			if(md5Flag){
				issuccess = "Y";
			}else{
				issuccess = "N";
				errorMessage = "投标文件md5值检测不一致";
			}  
		} catch (Exception e) {
			errorMessage = EsExceptionEnum.ES_SYSTEM_ERROR;
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
		responseXml.append("<fileName></fileName>");
		responseXml.append("<downType>http</downType>");
		responseXml.append("<downUrl></downUrl>");
		responseXml.append("</returnFile>");
		responseXml.append("</body>");
		responseXml.append("</tenderProject>");
		logger.debug("BidHttpService  tenderProject start=============================================\n"+responseXml.toString());
		logger.debug("BidHttpService  tenderProject end=============================================\n");
		write(responseXml.toString(), response);
	}
}
