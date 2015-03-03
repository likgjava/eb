/**
 * 
 */
package com.gpcsoft.epp.webService.webService.ue.http;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.bid.service.BailRecordService;
import com.gpcsoft.epp.bid.service.BidPackageService;
import com.gpcsoft.epp.bid.service.BidReceiptService;
import com.gpcsoft.epp.bid.service.BidService;
import com.gpcsoft.epp.common.service.UserApiService;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.service.ProjectService;
import com.gpcsoft.epp.projreview.domain.OpenBid;
import com.gpcsoft.epp.projreview.domain.OpenBidRecord;
import com.gpcsoft.epp.projreview.service.OpenBidRecordService;
import com.gpcsoft.epp.projreview.service.OpenBidService;
import com.gpcsoft.srplatform.auth.service.AttachmentService;


/**
 * @author liuk
 *
 */
@SuppressWarnings("unchecked")
@Controller//标识为控制器
@Scope("request")
@RequestMapping("/TenderProjectHttpService.do")//页面请求路径,可修改
public class TenderProjectHttp extends AnnotationMultiController{
	@Autowired(required=true) @Qualifier("bidServiceImpl")
	private BidService bidService;
	
	@Autowired(required=true) @Qualifier("projectServiceImpl")
	private ProjectService projectService;
	
	@Autowired(required=true) @Qualifier("userApiServiceImpl")
	private UserApiService userApiService;
	
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
			//4、根据开标ID和供应商ID来获取一条开标记录信息
			if(openBid!=null){
				openBidRecord = openBidRecordServiceImpl.getOpenBidRecordListByOpenBidIdAndSupplierId(openBid.getObjId(),supplier.getObjId());
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
		responseXml.append("<decrptStatus></decrptStatus>");
		responseXml.append("<openTime></openTime>");
		responseXml.append("<openStatus>"+(openBidRecord==null?"":openBidRecord.getOpenBRStatus())+"</openStatus>");
		responseXml.append("<openDesc></openDesc>");
		responseXml.append("</project>");
		responseXml.append("</body>");
		responseXml.append("</getDecrptInfo>");
		logger.debug("TenderProjectHttpService  getDecrptInfo start=============================================\n"+responseXml.toString());
		logger.debug("TenderProjectHttpService  getDecrptInfo end=============================================\n");
		write(responseXml.toString(), response);
	} 
	
	/**
	 * FuncName: getEvaluationInfo
	 * Description :  供应商在线获取评标信息
	 * @param 
	 * @param request
	 * @param response
	 * @throws Exception void
	 * @author: liuke
	 * @Create Date:2011-7-29  上午11:29:56
	 * @Modifier: liuke
	 * @Modified Date:2011-7-29  上午11:29:56
	 */
	@RequestMapping(params="method=getEvaluationInfo")
	public void getEvaluationInfo(HttpServletRequest request, HttpServletResponse response)throws Exception{
		String projCode = request.getParameter("prjCode");  //项目编号
		String packCode = request.getParameter("packCode");  //包件编号
		String username = request.getParameter("username");  //用户名
		String password = request.getParameter("password");  //密码
		String tenderOrgCode = request.getParameter("tenderOrgCode");  //投标机构编号
		String tenderOrgName = request.getParameter("tenderOrgName");  //投标机构名称
		boolean isSuccess = true;//是否执行成功   
		StringBuffer responseXml = new StringBuffer();
		responseXml.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
		responseXml.append("<getEvaluationInfo xmlns=\"http://www.gpcsoft.com\">");
		responseXml.append("<header>");
		responseXml.append("<operTag>"+(isSuccess == true ? "Y" : "N")+"</operTag>");
		responseXml.append("<operDesc></operDesc>");
		responseXml.append("</header>");
		responseXml.append("<body>");
		responseXml.append("<project>");
		responseXml.append("<prjCode>"+projCode+"</prjCode>");
		responseXml.append("<packCode>"+packCode+"</packCode>");
		responseXml.append("<evaluateFile>");
		responseXml.append("<fileName></fileName>");
		responseXml.append("<downUrl></downUrl>");
		responseXml.append("</evaluateFile>");
		responseXml.append("</project>");
		responseXml.append("</body>");
		responseXml.append("</getEvaluationInfo>");
		logger.debug("TenderProjectHttpService  getEvaluationInfo start=============================================\n"+responseXml.toString());
		logger.debug("TenderProjectHttpService  getEvaluationInfo end=============================================\n");
		write(responseXml.toString(), response);
	} 
	
	
	/**
	 * FuncName: getProjectRecentInfo
	 * Description : 获取投标项目包件最新进度
	 * @param 
	 * @param request
	 * @param response
	 * @throws Exception void
	 * @author: liuke
	 * @Create Date:2011-8-2  下午03:11:36
	 * @Modifier: liuke
	 * @Modified Date:2011-8-2  下午03:11:36
	 */
	@RequestMapping(params="method=getProjectRecentInfo")
	public void getProjectRecentInfo(HttpServletRequest request, HttpServletResponse response)throws Exception{
		String projCode = request.getParameter("prjCode");  //项目编号
		String packCode = request.getParameter("packCode");  //包件编号
		String username = request.getParameter("username");  //用户名
		String password = request.getParameter("password");  //密码
		boolean isSuccess = true;//是否执行成功   
		StringBuffer responseXml = new StringBuffer();
		responseXml.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
		responseXml.append("<getPrjRecentInfo xmlns=\"http://www.gpcsoft.com\">");
		responseXml.append("<header>");
		responseXml.append("<operTag>"+(isSuccess == true ? "Y" : "N")+"</operTag>");
		responseXml.append("<operDesc></operDesc>");
		responseXml.append("</header>");
		responseXml.append("<body>");
		responseXml.append("<prjRecentInfo>");
		responseXml.append("<prjCode>"+projCode+"</prjCode>");
		responseXml.append("<packCode>"+packCode+"</packCode>");
		responseXml.append("<prjStatus></prjStatus>");
		responseXml.append("<prjStatusMemo></prjStatusMemo>");
		responseXml.append("</prjRecentInfo>");
		responseXml.append("</body>");
		responseXml.append("</getPrjRecentInfo>");
		logger.debug("TenderProjectHttpService  getProjectRecentInfo start=============================================\n"+responseXml.toString());
		logger.debug("TenderProjectHttpService  getProjectRecentInfo end=============================================\n");
		write(responseXml.toString(), response);
	}	
}
