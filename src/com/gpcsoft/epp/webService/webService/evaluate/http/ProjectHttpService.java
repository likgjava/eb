package com.gpcsoft.epp.webService.webService.evaluate.http;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.common.domain.EbuyMethodEnum;
import com.gpcsoft.epp.common.utils.DateUtil;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.domain.ProjectEnum;
import com.gpcsoft.epp.project.domain.ProjectRule;
import com.gpcsoft.epp.project.service.ProjectService;
import com.gpcsoft.epp.webService.common.util.XmlUtil;
import com.gpcsoft.epp.webService.evaluateSystem.projectInfo.dto.ApplyDTO;
import com.gpcsoft.epp.webService.evaluateSystem.projectInfo.dto.ProjectDTO;
import com.gpcsoft.epp.webService.evaluateSystem.projectInfo.service.ElectronicReviewService;

/**
 * 为编评系统提供远程调用服务。
 * <br>
 * <strong>尚未进行安全认证</strong>.
 * @author <a href="mailto:shenjz@gpcsoft.com">zhouzhanghe</a>
 * @version 1.0
 * @since 2011-3-17 11:20
 */
@SuppressWarnings("unchecked")
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={Project.class})
@RequestMapping("/ProjectHttpService.do")//页面请求路径,可修改
public class ProjectHttpService extends AnnotationMultiController{
	
	@Autowired(required=true) @Qualifier("electronicReviewServiceImpl")
	private ElectronicReviewService electronicReviewService;
	
	@Autowired(required=true) @Qualifier("projectServiceImpl")
	private ProjectService projectService;
	
	/**
	 * FuncName: getProjectListForSimpleInfo
	 * Description :  获取项目清单
	 * @param signUpSTime
	 * @param signUpSTime_op
	 * @param signUpETime
	 * @param signUpETime_op
	 * @param submitStTime
	 * @param submitStTime_op
	 * @param submitETime
	 * @param submitETime_op
	 * @param openBidSDate
	 * @param openBidSDate_op
	 * @param evalSTime
	 * @param evalSTime_op
	 * @param evalETime
	 * @param evalETime_op
	 * @param tenderType
	 * @param tenderType_op
	 * @throws Exception ModelAndView
	 * @author: shenjz
	 * @Create Date:2011-9-8  上午09:35:04
	 * @Modifier: shenjz
	 * @Modified Date:2011-9-8  上午09:35:04
	 */
	@RequestMapping(params="method=getProjectListForSimpleInfo")
	public void getProjectListForSimpleInfo(String signUpSTime, String signUpSTime_op, String signUpETime, String signUpETime_op, String submitStTime, String submitStTime_op, String submitETime, String submitETime_op, String openBidSDate, String openBidSDate_op, String evalSTime, String evalSTime_op, String evalETime, String evalETime_op,String tenderType,String tenderType_op,HttpServletResponse response)throws Exception{
	//添加返回XML的主体内容
	StringBuffer responseXml = new StringBuffer();	
	List<Project> projectList =	electronicReviewService.getProjectListForSimpleInfo(signUpSTime, signUpSTime_op, signUpETime, signUpETime_op, submitStTime, submitStTime_op, submitETime, submitETime_op, openBidSDate, openBidSDate_op, evalSTime, evalSTime_op, evalETime, evalETime_op, tenderType, tenderType_op);
		responseXml.append("<?xml version='1.0' encoding='utf-8' ?>");
		responseXml.append("<TABLES>");
		responseXml.append("<ecpTenderProjects>");
		for (Iterator iterator = projectList.iterator(); iterator.hasNext();) {
			responseXml.append("<ecpTenderProject>");
			Project project = (Project) iterator.next();
			responseXml.append("<tenderType>"+project.getTenderType()+"</tenderType>");
			responseXml.append("<tenderName>"+project.getProjName()+"</tenderName>");
			responseXml.append("<tenderNo>"+project.getProjCode()+"</tenderNo>");
			List<Project> subProjectList = projectService.getSubProjectByProjectId(project.getObjId());
			  for (Iterator iterator2 = subProjectList.iterator(); iterator2
					.hasNext();) {
				responseXml.append("<ecpTenderProject>");
				responseXml.append("<tenderType/>");
				Project subProject = (Project) iterator2.next();
				responseXml.append("<tenderName>"+subProject.getProjName()+"</tenderName>");
				responseXml.append("<tenderNo>"+subProject.getProjCode()+"</tenderNo>");
				responseXml.append("</ecpTenderProject>");
			  }
			  responseXml.append("</ecpTenderProject>");
		}
		responseXml.append("</ecpTenderProjects>");
		responseXml.append("</TABLES>");
		responseXml.append("</downBidFileApp>");
		logger.debug("ProjectHttpService    getProjectListForSimpleInfo====================================start\n"+responseXml.toString());
		logger.debug("ProjectHttpService    getProjectListForSimpleInfo====================================end\n");
		response.getWriter().write(responseXml.toString());
	}
	
	/**
	 * FuncName: getProjectInfoByCode
	 * Description :  根据编号获取项目或包组信息
	 * @param prjCode
	 * @param packCodes
	 * @param username
	 * @param password
	 * @throws Exception ModelAndView
	 * @author: shenjz
	 * @Create Date:2011-9-8  上午09:35:26
	 * @Modifier: shenjz
	 * @Modified Date:2011-9-8  上午09:35:26
	 */
	@RequestMapping(params="method=getProjectInfoByCode")
	public void getProjectInfoByCode(String prjCode,String packCodes,String username,String password,HttpServletResponse response)throws Exception{
		List<ProjectDTO> projectDTOList = electronicReviewService.getProjectInfoByCode(prjCode, packCodes);
		ProjectDTO projectDTO = projectDTOList.get(0);
		if(ProjectEnum.JZGC.equals(projectDTO.getTenderType())){
			projectDTO.setTenderMethod(EbuyMethodEnum.getEBuyMethodCN(projectDTO.getTenderMethod()));
		}
		ArrayList<String> tenderIdList = new ArrayList<String>(); 
		for(ProjectDTO projectdto : projectDTOList){
			tenderIdList.add(projectdto.getTenderiId());
		}
		List<ApplyDTO> applyDTOList = new ArrayList<ApplyDTO>();
		if(tenderIdList != null && tenderIdList.size() > 0){
			applyDTOList = electronicReviewService.getApplyInfoByCode(tenderIdList, packCodes);
		}
		//添加返回XML的主体内容
		StringBuffer responseXml = new StringBuffer();
		responseXml.append("<?xml version='1.0' encoding='utf-8' ?>");
		responseXml.append("<getProjectSimpleInfo>");
		responseXml.append("<tender>");
		responseXml.append("<tenderType>"+projectDTO.getTenderType()+"</tenderType>");
		responseXml.append("<tenderName>"+projectDTO.getTenderName()+"</tenderName>");
		responseXml.append("<tenderNo>"+projectDTO.getTenderNo()+"</tenderNo>");
		responseXml.append("<tenderUrl>"+projectDTO.getTenderUrl()+"</tenderUrl>");
		responseXml.append("<tenderiId>"+projectDTO.getTenderiId()+"</tenderiId>");
		responseXml.append("<tenderContent>"+projectDTO.getTenderContent()+"</tenderContent>");
		responseXml.append("<tenderSummary>"+projectDTO.getTenderSummary()+"</tenderSummary> ");
		responseXml.append("<agenciesId>"+projectDTO.getAgenciesId()+"</agenciesId>");
		responseXml.append("<agenciesName>"+projectDTO.getAgenciesName()+"</agenciesName>");
		responseXml.append("<tenderBudgetTotalMoney>"+projectDTO.getTenderBudgetTotalMoney()+"</tenderBudgetTotalMoney>");
		responseXml.append("<purcategoryIds>"+projectDTO.getPurcategoryIds()+"</purcategoryIds>");
		responseXml.append("<purcategoryNames>"+projectDTO.getPurcategoryNames()+"</purcategoryNames>");
		responseXml.append("<goodsclassIds>"+projectDTO.getGoodsclassIds()+"</goodsclassIds>");
		responseXml.append("<goodsclassNames>"+projectDTO.getGoodsclassNames()+"</goodsclassNames>");
		responseXml.append("<projManager>"+projectDTO.getProjManager()+"</projManager>");
		responseXml.append("<tenderMethod>"+projectDTO.getTenderMethod()+"</tenderMethod>");
		responseXml.append("<buyerIds>"+projectDTO.getBuyerIds()+"</buyerIds>");
		responseXml.append("<buyerNames>"+projectDTO.getBuyerNames()+"</buyerNames>");
		responseXml.append("<evalEndTime>"+projectDTO.getEvalEndTime()+"</evalEndTime>");
		responseXml.append("<openBidTime>"+projectDTO.getOpenBidTime()+"</openBidTime>");
		responseXml.append("<openBidAddr>"+projectDTO.getOpenBidAddr()+"</openBidAddr>");
		responseXml.append("<evalStartTime>"+projectDTO.getEvalStartTime()+"</evalStartTime>");
		responseXml.append("<evalEndTime>"+projectDTO.getEvalEndTime()+"</evalEndTime>");
		responseXml.append("<evalAddr></evalAddr>");
		responseXml.append("<processStatus></processStatus>");

		List<ProjectDTO> subProjDtoList = projectDTO.getSubProjectList();
		for (Iterator iterator = subProjDtoList.iterator(); iterator.hasNext();) {
			ProjectDTO subProj = (ProjectDTO) iterator.next();
			responseXml.append("<ecpTenderProject>");
			responseXml.append("<tenderType></tenderType>");
			responseXml.append("<tenderName>"+subProj.getTenderName()+"</tenderName>");
			responseXml.append("<tenderNo>"+subProj.getTenderNo()+"</tenderNo>");
			responseXml.append("<tenderUrl></tenderUrl>");
			responseXml.append("<tenderiId>"+subProj.getTenderiId()+"</tenderiId>");
			responseXml.append("<buyerIds>"+projectDTO.getBuyerIds()+"</buyerIds>");
			responseXml.append("<buyerNames>"+projectDTO.getBuyerNames()+"</buyerNames>");
			responseXml.append("<tenderContent></tenderContent>");
			responseXml.append("<tenderSummary></tenderSummary> ");
			responseXml.append("<agenciesId></agenciesId>");
			responseXml.append("<tenderBudgetTotalMoney></tenderBudgetTotalMoney>");
			responseXml.append("<purcategoryIds>"+subProj.getPurcategoryIds()+"</purcategoryIds>");
			responseXml.append("<purcategoryNames>"+subProj.getPurcategoryNames()+"</purcategoryNames>");
			responseXml.append("<goodsclassIds></goodsclassIds>");
			responseXml.append("<goodsclassNames></goodsclassNames>");
			responseXml.append("<projManager>"+projectDTO.getProjManager()+"</projManager>");
			responseXml.append("<tenderMethod>"+projectDTO.getTenderMethod()+"</tenderMethod>");
			responseXml.append("<openBidTime>"+projectDTO.getOpenBidTime()+"</openBidTime>");
			responseXml.append("<openBidAddr>"+projectDTO.getOpenBidAddr()+"</openBidAddr>");
			responseXml.append("<evalStartTime>"+projectDTO.getEvalStartTime()+"</evalStartTime>");
			responseXml.append("<evalEndTime>"+projectDTO.getEvalEndTime()+"</evalEndTime>");
			responseXml.append("<ecpTenderApplyRecs>");
			for(ApplyDTO applyDTO : applyDTOList){
				if(applyDTO.getTenderId().equals(subProj.getTenderiId())){
					responseXml.append(XmlUtil.objectToXml(applyDTO));
				}
			}	
			responseXml.append("</ecpTenderApplyRecs>");
			responseXml.append("</ecpTenderProject>");
			
		}
		responseXml.append("</tender>");
		responseXml.append("</getProjectSimpleInfo>");
		logger.debug("ProjectHttpService    getProjectInfoByCode====================================start\n"+responseXml.toString());
		logger.debug("ProjectHttpService    getProjectInfoByCode====================================end\n");
		response.getWriter().write(responseXml.toString().replaceAll("null", ""));
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
					projectList.add(projectService.findProjectByProjCodeAndParent(packCode[i], project.getObjId()));
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
		if(projectList != null && projectList.isEmpty()){
			for (Iterator iterator = projectList.iterator(); iterator.hasNext();) {
				Project pack = (Project) iterator.next();
				sb.append("<pack>");
				sb.append("<packCode>"+pack.getProjCode()+"</packCode>");//包件编号
				sb.append("<packStatus>"+pack.getProjProcessStatus()+"</packStatus>");//当前阶段标志
				sb.append("<packStatusMemo>"+pack.getProjProcessStatusCN()+"</packStatusMemo>");//当前阶段文字描述
				sb.append("<participateStatus>"+pack.getProjProcessStatus()+"</participateStatus>");//参与阶段标志
				sb.append("</pack>");
			}
		}else{
			getProjectStatus(project);
			sb.append("<pack>");
			sb.append("<packCode>"+project.getProjCode()+"</packCode>");//包件编号
			sb.append("<packStatus>"+(project!=null?project.getProjProcessStatus():"")+"</packStatus>");//当前阶段标志
			sb.append("<packStatusMemo>"+(project!=null?project.getProjProcessStatusCN():"")+"</packStatusMemo>");//当前阶段文字描述
			sb.append("<participateStatus>"+getProjectStatus(project)+"</participateStatus>");//参与阶段标志
			sb.append("</pack>");
		}		
		sb.append("<packs/>");
		sb.append("</prjRecentInfo>");
		sb.append("</body>");
		sb.append("</getPrjRecentInfo>");
		logger.debug("ProjectHttpService    getProjectProcessStatus====================================start\n"+sb.toString());
		logger.debug("ProjectHttpService    getProjectProcessStatus====================================end\n");
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
	
}
