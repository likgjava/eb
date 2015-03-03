package com.gpcsoft.epp.webService.webService.evaluate.soap;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.gpcsoft.bizplatform.agency.domain.Agency;
import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.utils.DateUtil;
import com.gpcsoft.epp.common.domain.DataItem;
import com.gpcsoft.epp.common.domain.EbuyMethodEnum;
import com.gpcsoft.epp.common.service.UserApiService;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.domain.ProjectEnum;
import com.gpcsoft.epp.project.domain.ProjectRule;
import com.gpcsoft.epp.project.service.ProjectService;
import com.gpcsoft.epp.purchasedoc.domain.PurchaseDocEnum;
import com.gpcsoft.epp.requirement.domain.PreqEntry;
import com.gpcsoft.epp.requirement.service.PreqEntryService;
import com.gpcsoft.epp.webService.common.util.XmlUtil;
import com.gpcsoft.epp.webService.evaluateSystem.projectInfo.dto.ApplyDTO;
import com.gpcsoft.epp.webService.evaluateSystem.projectInfo.dto.CongruousFactorDTO;
import com.gpcsoft.epp.webService.evaluateSystem.projectInfo.dto.ProjectDTO;
import com.gpcsoft.epp.webService.evaluateSystem.projectInfo.dto.WorkMembersDTO;
import com.gpcsoft.epp.webService.evaluateSystem.projectInfo.service.ElectronicReviewService;
import com.gpcsoft.epp.workgroup.domain.WorkMemberTypeEnum;
import com.gpcsoft.epp.workgroup.domain.WorkgMember;
import com.gpcsoft.epp.workgroup.service.WorkgMemberService;
import com.gpcsoft.srplatform.auth.domain.Employee;

/**
 * 为编评系统提供远程调用服务。
 * <br>
 * <strong>尚未进行安全认证</strong>.
 * @author <a href="mailto:zhouzh@gpcsoft.com">zhouzhanghe</a>
 * @version 1.0
 * @since 2011-3-17 11:20
 */
public class ProjectSoapService {
	
	@Autowired(required=true) @Qualifier("projectServiceImpl")
	private ProjectService projectService;	
	
	@Autowired(required=true) @Qualifier("userApiServiceImpl")
	private UserApiService userApiService;
	
	@Autowired(required=true) @Qualifier("preqEntryServiceImpl")
	private PreqEntryService preqEntryService;
	
	private WorkgMemberService workgMemberService;
	
	private ElectronicReviewService remoteProjectService;
	
	protected final Log logger = LogFactory.getLog(this.getClass());
	public ProjectSoapService(){
		remoteProjectService = (ElectronicReviewService) FrameBeanFactory.getBean("electronicReviewServiceImpl");
		projectService = (ProjectService) FrameBeanFactory.getBean("projectServiceImpl");
		userApiService = (UserApiService) FrameBeanFactory.getBean("userApiServiceImpl");
		preqEntryService = (PreqEntryService) FrameBeanFactory.getBean("preqEntryServiceImpl");
		workgMemberService = (WorkgMemberService)FrameBeanFactory.getBean("workgMemberServiceImpl");
	}
	/**
	 * FuncName: getList
	 * Description :  获取电子评审系统xml
	 * @param signUpSTime:报名开始时间 
	 * @param signUpSTime_op:对应关系的运算符>=,<=,=
	 * @param signUpETime:报名结束时间
	 * @param signUpETime_op:对应的关系运算符>=,<=,=
	 * @param submitStTime:投标开始时间
	 * @param submitStTime_op:对应的关系运算符>=,<=,=
	 * @param submitETime:投标结束时间
	 * @param submitETime_op:对应的关系运算符>=,<=,=
	 * @param openBidSDate:开标开始时间
	 * @param openBidSDate_op:对应的关系运算符>=,<=,=
	 * @param evalSTime:评标开始时间
	 * @param evalSTime_op:对应的关系运算符>=,<=,=
	 * @param evalETime:评标结束时间
	 * @param evalETime_op:对应的关系运算符>=,<=,=
	 * @param tenderType:项目类型
	 * @param tenderType_op:in
	 * @return String
	 * @author: shenjz
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 * @Create Date:2011-3-23  上午11:23:40
	 * @Modifier: shenjz
	 * @Modified Date:2011-3-23  上午11:23:40
	 * Modified at 2011-5-23 15:44 by zhouzhanghe (加入返回报名信息)
	 * Modified at 2011-5-27 11:08 by zhouzhanghe (调用remoteProjectService.getSignUprecordList方法前加入条件判断)
	 */
	public String getProjectInfo(String signUpSTime, String signUpSTime_op, String signUpETime, String signUpETime_op, String submitStTime, String submitStTime_op, String submitETime, String submitETime_op, String openBidSDate, String openBidSDate_op, String evalSTime, String evalSTime_op, String evalETime, String evalETime_op,String tenderType,String tenderType_op){
		StringBuffer projectDTOInfo = new StringBuffer();//存放返回的项目列表
		StringBuffer workMembersInfo = new StringBuffer();//存放返回的专家 列表
		StringBuffer congruousFactorDTOInfo = new StringBuffer();//存放返回的专家 列表
		StringBuffer applyDTOInfo = new StringBuffer();//存放返回的报名 列表
		String errorMessage = "";
		try {
			List<ProjectDTO> projectDTOList = remoteProjectService.getList(signUpSTime, signUpSTime_op, signUpETime,signUpETime_op, submitStTime, submitStTime_op, submitETime, submitETime_op, openBidSDate, openBidSDate_op, evalSTime, evalSTime_op, evalETime, evalETime_op,tenderType,tenderType_op);
			/*Added at 2011-5-23 15:41 by zhouzhanghe 将符合条件的项目ID放入链表*/
			ArrayList<String> tenderIdList = new ArrayList<String>(); 
			for(ProjectDTO projectDTO : projectDTOList){
				tenderIdList.add(projectDTO.getTenderiId());
			}
			List<CongruousFactorDTO> congruousFactorDTOList = new ArrayList<CongruousFactorDTO>();//remoteProjectService.getCongruousFactorDTOList();
			List<WorkMembersDTO> workMembersDTOList =  new ArrayList<WorkMembersDTO>();//remoteProjectService.getWorkMemberList();
			List<ApplyDTO> applyDTOList = new ArrayList<ApplyDTO>();
			if(tenderIdList != null && tenderIdList.size() > 0){
				applyDTOList = remoteProjectService.getSignUprecordList(tenderIdList);
			}
				for(ProjectDTO projectDTO : projectDTOList){
					List<ProjectDTO> l = projectDTO.getSubProjectList();
					if(ProjectEnum.JZGC.equals(projectDTO.getTenderType())){
						projectDTO.setTenderMethod(EbuyMethodEnum.getEBuyMethodCN(projectDTO.getTenderMethod()));
					}
					
					projectDTOInfo.append(XmlUtil.objectToXml(projectDTO).split("</ecpTenderProject>")[0]);
					if(l!=null){
						for(ProjectDTO projectDTO2 : l){
							projectDTO2.setEvalStartTime(projectDTO.getEvalStartTime());
							projectDTO2.setEvalEndTime(projectDTO.getEvalEndTime());
							if(ProjectEnum.JZGC.equals(projectDTO.getTenderType())){
								projectDTO2.setTenderMethod(EbuyMethodEnum.getEBuyMethodCN(projectDTO2.getTenderMethod()));
							}
							projectDTOInfo.append(XmlUtil.objectToXml(projectDTO2));
						}
					}
					projectDTOInfo.append("</ecpTenderProject>");
				}
				for(WorkMembersDTO workMembersDTO : workMembersDTOList){
					workMembersInfo.append(XmlUtil.objectToXml(workMembersDTO));
				}
				for(ApplyDTO applyDTO : applyDTOList){
					applyDTOInfo.append(XmlUtil.objectToXml(applyDTO));
				}
				for(CongruousFactorDTO congruousFactorDTO : congruousFactorDTOList){
					congruousFactorDTOInfo.append(XmlUtil.objectToXml(congruousFactorDTO));
				}			
		} catch (Exception e) {
			errorMessage = e.getMessage();
		}
		//添加返回XML的主体内容
		StringBuffer responseXml = new StringBuffer();
		responseXml.append("<?xml version='1.0' encoding='utf-8' ?>");
		responseXml.append("<getProjectInfo xmlns='http://www.gpcsoft.com'>");
		responseXml.append("<header>");
		responseXml.append("<prjCode>");
		responseXml.append("</prjCode>");
		responseXml.append("<prjName>");
		responseXml.append("</prjName>");
		responseXml.append("<operTag>"+(errorMessage.equals("")?"Y":"N")+"</operTag>");
		responseXml.append("<operDesc>"+(errorMessage.equals("")?"操作成功!":"操作失败!")+"</operDesc>");
		responseXml.append("<operException>"+errorMessage+"</operException>");
		responseXml.append("</header>");
		responseXml.append("<body>");
		responseXml.append("<ecpTenderProjects>");
		responseXml.append(projectDTOInfo.toString());
		responseXml.append("</ecpTenderProjects>");
		responseXml.append("<ecpCongruousFactors>");
		responseXml.append(congruousFactorDTOInfo.toString());
		responseXml.append("</ecpCongruousFactors>");
		responseXml.append("<ecpWorkgMembers>");
		responseXml.append(workMembersInfo.toString());
		responseXml.append("</ecpWorkgMembers>");
		responseXml.append("<ecpTenderApplyRecs>");
		responseXml.append(applyDTOInfo.toString());
		responseXml.append("</ecpTenderApplyRecs>");
		responseXml.append("</body>");
		responseXml.append("</getProjectInfo >");
		return responseXml.toString();
	}
	
	/**
	 * FuncName: getProjectListForSimpleInfo
	 * Description:获取项目清单
	 * @param signUpSTime:报名开始时间 
	 * @param signUpSTime_op:对应关系的运算符>=,<=,=
	 * @param signUpETime:报名结束时间
	 * @param signUpETime_op:对应的关系运算符>=,<=,=
	 * @param submitStTime:投标开始时间
	 * @param submitStTime_op:对应的关系运算符>=,<=,=
	 * @param submitETime:投标结束时间
	 * @param submitETime_op:对应的关系运算符>=,<=,=
	 * @param openBidSDate:开标开始时间
	 * @param openBidSDate_op:对应的关系运算符>=,<=,=
	 * @param evalSTime:评标开始时间
	 * @param evalSTime_op:对应的关系运算符>=,<=,=
	 * @param evalETime:评标结束时间
	 * @param evalETime_op:对应的关系运算符>=,<=,=
	 * @param tenderType:项目类型
	 * @param tenderType_op:in
	 * @return String
	 * @author: liuke
	 * @Create Date:2011-9-7  上午09:36:55
	 * @Modifier: liuke
	 * @Modified Date:2011-9-7  上午09:36:55
	 */
	public String getProjectListForSimpleInfo(String signUpSTime, String signUpSTime_op, String signUpETime, String signUpETime_op, String submitStTime, String submitStTime_op, String submitETime, String submitETime_op, String openBidSDate, String openBidSDate_op, String evalSTime, String evalSTime_op, String evalETime, String evalETime_op,String tenderType,String tenderType_op){
	//添加返回XML的主体内容
		StringBuffer responseXml = new StringBuffer();	
		try {
			responseXml.append("<?xml version='1.0' encoding='utf-8' ?>");
			responseXml.append("<getProjectListForSimpleInfo xmlns='http://www.gpcsoft.com'>");
			responseXml.append("<header>");
			responseXml.append("<prjCode>");
			responseXml.append("</prjCode>");
			responseXml.append("<prjName>");
			responseXml.append("</prjName>");
			responseXml.append("<operTag>Y</operTag>");
			responseXml.append("<operDesc>操作成功!</operDesc>");
			responseXml.append("<operException></operException>");
			responseXml.append("</header>");
			List<Project> projectList =	remoteProjectService.getProjectListForSimpleInfo(signUpSTime, signUpSTime_op, signUpETime, signUpETime_op, submitStTime, submitStTime_op, submitETime, submitETime_op, openBidSDate, openBidSDate_op, evalSTime, evalSTime_op, evalETime, evalETime_op, tenderType, tenderType_op);
			responseXml.append("<body>");
			responseXml.append("<ecpTenderProjects>");
			for (Iterator iterator = projectList.iterator(); iterator.hasNext();) {
				responseXml.append("<ecpTenderProject>");
				Project project = (Project) iterator.next();
				responseXml.append("<tenderType>"+project.getTenderType()+"</tenderType>");
				responseXml.append("<tenderName>"+project.getProjName()+"</tenderName>");
				responseXml.append("<tenderNo>"+project.getProjCode()+"</tenderNo>");
				List<Project> subProjectList = projectService.getSubProjectByProjectId(project.getObjId());
				for (Iterator iterator2 = subProjectList.iterator(); iterator2.hasNext();) {
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
			responseXml.append("</body>");
			responseXml.append("</getProjectListForSimpleInfo >");
		} catch (Exception e) {
			responseXml = new StringBuffer();
			String message = e.getMessage();
			responseXml.append("<?xml version='1.0' encoding='utf-8' ?>");
			responseXml.append("<getProjectListForSimpleInfo xmlns='http://www.gpcsoft.com'>");
			responseXml.append("<header>");
			responseXml.append("<prjCode>");
			responseXml.append("</prjCode>");
			responseXml.append("<prjName>");
			responseXml.append("</prjName>");
			responseXml.append("<operTag>N</operTag>");
			responseXml.append("<operDesc>操作失败!</operDesc>");
			responseXml.append("<operException>");
			if(message.length()<=(4096*1024)){
				responseXml.append(message.substring(0, message.length()));
			}else {
				responseXml.append(message.substring(0,(4096*1024)));
			}
			responseXml.append("</operException>");
			responseXml.append("</header>");
			responseXml.append("<body>");
			responseXml.append("<ecpTenderProjects>");
			responseXml.append("</ecpTenderProjects>");
			responseXml.append("</body>");
			responseXml.append("</getProjectListForSimpleInfo >");
		}
		return responseXml.toString();
	}
	
	/**
	 * FuncName: getProjectInfoByCode
	 * Description: 获取项目详细信息
	 * @param prjCode:项目编号
	 * @param packCodes:包件编号(可为空；若为多个包件，则包件编号之间以“,”拼装)
	 * @param username:用户名
	 * @param password: 密码
	 * @return String
	 * @author: liuke
	 * @Create Date:2011-9-7  上午11:44:30
	 * @Modifier: liuke
	 * @Modified Date:2011-9-7  上午11:44:30
	 */
	public String getProjectInfoByCode(String prjCode,String packCodes,String username,String password){
		StringBuffer responseXml = new StringBuffer();
		ProjectDTO projectDTO = new ProjectDTO();
		try {
			List<ProjectDTO> projectDTOList = remoteProjectService.getProjectInfoByCode(prjCode, packCodes);
			projectDTO = projectDTOList.get(0);
			if(ProjectEnum.JZGC.equals(projectDTO.getTenderType())){
				projectDTO.setTenderMethod(EbuyMethodEnum.getEBuyMethodCN(projectDTO.getTenderMethod()));
			}
			ArrayList<String> tenderIdList = new ArrayList<String>(); 
			for(ProjectDTO projectdto : projectDTOList){
				tenderIdList.add(projectdto.getTenderiId());
			}
			List<ApplyDTO> applyDTOList = new ArrayList<ApplyDTO>();
			if(tenderIdList != null && tenderIdList.size() > 0){
				applyDTOList = remoteProjectService.getApplyInfoByCode(tenderIdList, packCodes);
			}
			//添加返回XML的主体内容
			responseXml.append("<?xml version='1.0' encoding='utf-8' ?>");
			responseXml.append("<getProjectInfoByCode xmlns=\"http://www.gpcsoft.com\">");
			responseXml.append("<header>");
			responseXml.append("<prjCode>");
			responseXml.append(prjCode);
			responseXml.append("</prjCode>");
			responseXml.append("<prjName>");
			responseXml.append(projectDTO.getTenderName());
			responseXml.append("</prjName>");
			responseXml.append("<operTag>Y</operTag>");
			responseXml.append("<operDesc>操作成功!</operDesc>");
			responseXml.append("<operException></operException>");
			responseXml.append("</header>");
			responseXml.append("<body>");
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
			responseXml.append("</body>");
			responseXml.append("</getProjectInfoByCode>");
		} catch (Exception e) {
			e.printStackTrace();
			responseXml = new StringBuffer();
			String message = e.getMessage();
			responseXml.append("<?xml version='1.0' encoding='utf-8' ?>");
			responseXml.append("<getProjectInfoByCode xmlns='http://www.gpcsoft.com'>");
			responseXml.append("<header>");
			responseXml.append("<prjCode>");
			responseXml.append(prjCode);
			responseXml.append("</prjCode>");
			responseXml.append("<prjName>");
			responseXml.append(projectDTO.getTenderName());
			responseXml.append("</prjName>");
			responseXml.append("<operTag>N</operTag>");
			responseXml.append("<operDesc>操作失败!</operDesc>");
			responseXml.append("<operException>");
			responseXml.append(message);
			responseXml.append("</operException>");
			responseXml.append("</header>");
			responseXml.append("<body>");
			responseXml.append("<ecpTenderProjects>");
			responseXml.append("</ecpTenderProjects>");
			responseXml.append("</body>");
			responseXml.append("</getProjectInfoByCode>");
		}
		return responseXml.toString();
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
	public String getProjectProcessStatus(String prjCode,String packCodes,String username,String password){
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
		sb.append("<getProjectProcessStatus xmlns=\"http://www.gpcsoft.com\">");
		sb.append("<header>");
		sb.append("<operTag>"+(success?"Y":"N")+"</operTag>");
		sb.append("<operDesc>"+(success?"成功":"失败")+"</operDesc>");
		sb.append("<operException>"+(success?"":exception)+"</operException>");
		sb.append("</header>");
		sb.append("<body>");
		sb.append("<ecpTenderProject>");
		sb.append("<tenderType>");
		sb.append((project!=null?project.getTenderType():""));
		sb.append("</tenderType>");
		sb.append("<tenderName>");
		sb.append((project!=null?project.getProjName():""));
		sb.append("</tenderName>");
		sb.append("<processStatus>");
		sb.append((project!=null?project.getProjProcessStatus():""));
		sb.append("</processStatus>");
		sb.append("<processStatusCN>");
		sb.append((project!=null?project.getProjProcessStatusCN():""));
		sb.append("</processStatusCN>");
		for (Iterator iterator = projectList.iterator(); iterator.hasNext();) {
			Project pro = (Project) iterator.next();
			sb.append("<ecpTenderProject>");
			sb.append("<tenderType>");
			sb.append((pro!=null?(pro.getTenderType()!=null?pro.getTenderType():""):""));
			sb.append("</tenderType>");
			sb.append("<tenderName>");
			sb.append((pro!=null?(pro.getProjName()!=null?pro.getProjName():""):""));
			sb.append("</tenderName>");
			sb.append("<processStatus>");
			sb.append((pro!=null?(pro.getProjProcessStatus()!=null?pro.getProjProcessStatus():""):""));
			sb.append("</processStatus>");
			sb.append("<processStatusCN>");
			sb.append((pro!=null?(pro.getProjProcessStatusCN()!=null?pro.getProjProcessStatusCN():""):""));
			sb.append("</processStatusCN>");
			sb.append("</ecpTenderProject>");
		}
		sb.append("</ecpTenderProject>");
		sb.append("</body>");
		sb.append("</getProjectProcessStatus>");
		return sb.toString();
	}
	
	/**
	 * Description:获取项目数据
	 * @param prjCode:项目记录号
     * @param username:用户名
     * @param password: 密码
	 * @throws Exception
	 * @author: liuy
	 * @Create Date:2011-6-30  上午09:58:27
	 */
	public String getProjectData(String prjCode, String username, String password) throws Exception{

		boolean operDesc = true;//存放操作结果
		List<DataItem> dataItemDTOList = new ArrayList<DataItem>();
		String errorMessage ="";
		try {
			Project p =  projectService.findProjectByProjCode(prjCode);
			DataItem prjCodeDataItem = new DataItem();
			prjCodeDataItem.setId("prjCode");
			prjCodeDataItem.setName("项目编号");
			prjCodeDataItem.setValue(p.getProjCode());
			prjCodeDataItem.setType("project");
			prjCodeDataItem.setReadOnly("true");
			dataItemDTOList.add(prjCodeDataItem);
			
			DataItem prjName = new DataItem();
			prjName.setId("prjName");
			prjName.setName("项目名称");
			prjName.setValue(p.getProjName());
			prjName.setType("project");
			prjName.setReadOnly("true");
			dataItemDTOList.add(prjName);
			
			DataItem angentCode = new DataItem();
			angentCode.setId("angentCode");
			angentCode.setName("代理机构编号"); 
			angentCode.setValue(p.getAgencies().getOrgCode());
			angentCode.setType("buyingCenter");
			angentCode.setReadOnly("true");
			dataItemDTOList.add(angentCode);
			
			DataItem angentName = new DataItem();
			angentName.setId("angentName");
			angentName.setName("代理机构名称"); 
			angentName.setValue(p.getAgencies().getOrgName());
			angentName.setType("buyingCenter");
			angentName.setReadOnly("true");
			dataItemDTOList.add(angentName);
			
			DataItem fileType = new DataItem();
			fileType.setId("fileType");
			fileType.setName("文件类型"); 
			String fileTypes = "";
			if(EbuyMethodEnum.INQUIRY.equals(p.getEbuyMethod())){//如果采购方式是询价
				fileTypes = PurchaseDocEnum.INQPDOC;
			}else{ //其他采购方式
				fileTypes = PurchaseDocEnum.PURCHASEDOC;
			}
			fileType.setValue(PurchaseDocEnum.getCN(fileTypes));
			fileType.setType("project");
			fileType.setReadOnly("true");
			dataItemDTOList.add(fileType);
			
			DataItem projectTime = new DataItem();
			projectTime.setId("projectTime");
			projectTime.setName("立项时间"); 
			projectTime.setValue(DateUtil.format(p.getCreateTime(), "yyyy年MM月dd日"));
			projectTime.setType("project");
			projectTime.setReadOnly("true");
			dataItemDTOList.add(projectTime);
			
			DataItem purchaseType = new DataItem();
			purchaseType.setId("purchaseType");
			purchaseType.setName("采购方式"); 
			purchaseType.setValue(p.getEbuyMethodCN());
			purchaseType.setType("project");
			purchaseType.setReadOnly("true");
			dataItemDTOList.add(purchaseType);
			
			DataItem biddingOrgName = new DataItem();
			biddingOrgName.setId("biddingOrgName");
			biddingOrgName.setName("招标机构"); 
			biddingOrgName.setValue(p.getBuyersName());
			biddingOrgName.setType("project");
			biddingOrgName.setReadOnly("true");
			dataItemDTOList.add(biddingOrgName);
			
			DataItem purchaseOrgName = new DataItem();
			purchaseOrgName.setId("purchaseOrgName");
			purchaseOrgName.setName("采购人"); 
			purchaseOrgName.setValue(p.getBuyersName());
			purchaseOrgName.setType("project");
			purchaseOrgName.setReadOnly("true");
			dataItemDTOList.add(purchaseOrgName);
			
			//TODO 目前只放入代理机构的地址
			DataItem applyLocation = new DataItem();
			applyLocation.setId("applyLocation");
			applyLocation.setName("线下报名地址"); 
			applyLocation.setValue(p.getAgencies().getOrgAddress());
			applyLocation.setType("project");
			applyLocation.setReadOnly("true");
			dataItemDTOList.add(applyLocation);
			
			DataItem applyStartTime = new DataItem();
			applyStartTime.setId("applyStartTime");
			applyStartTime.setName("开始时间（报名）"); 
			ProjectRule projectRule = projectService.getProjectRuleByProjectId(p.getObjId());
			if(projectRule != null && projectRule.getSignUpSTime() != null)
				applyStartTime.setValue(DateUtil.format(projectRule.getSignUpSTime(),"yyyy年MM月dd日"));
			applyStartTime.setType("project");
			applyStartTime.setReadOnly("true");
			dataItemDTOList.add(applyStartTime);
			
			DataItem applyEndTime = new DataItem();
			applyEndTime.setId("applyEndTime");
			applyEndTime.setName("截止时间（报名）"); 
			if(projectRule != null && projectRule.getSignUpETime() != null)
			applyEndTime.setValue(DateUtil.format(projectRule.getSignUpETime(),"yyyy年MM月dd日"));
			applyEndTime.setType("project");
			applyEndTime.setReadOnly("true");
			dataItemDTOList.add(applyEndTime);
			
			DataItem tenderFeeNumber = new DataItem();
			tenderFeeNumber.setId("tenderFeeNumber");
			tenderFeeNumber.setName("标书费金额"); 
			tenderFeeNumber.setValue((p.getPurDocPrice()!=null)?p.getPurDocPrice().toString():"");
			tenderFeeNumber.setType("project");
			tenderFeeNumber.setReadOnly("true");
			dataItemDTOList.add(tenderFeeNumber);
			
			//TODO 目前"标书费缴纳截止时间"取为"报名结束时间"
			DataItem tenderFeeEndTime = new DataItem();
			tenderFeeEndTime.setId("tenderFeeEndTime");
			tenderFeeEndTime.setName("标书费缴纳截止时间"); 
			if(projectRule != null && projectRule.getSignUpETime() != null)
			tenderFeeEndTime.setValue(DateUtil.format(projectRule.getSignUpETime(),"yyyy年MM月dd日"));
			tenderFeeEndTime.setType("project");
			tenderFeeEndTime.setReadOnly("true");
			dataItemDTOList.add(tenderFeeEndTime);
			
			DataItem openBankOrg = new DataItem();
			openBankOrg.setId("openBankOrg");
			openBankOrg.setName("开户单位（缴纳）"); 
			Agency agency = userApiService.getAgentMessageByObjId(p.getAgencies().getObjId());
			if(agency != null && agency.getOpenBank() != null)
				openBankOrg.setValue(agency.getOpenBank());
			openBankOrg.setType("project");
			openBankOrg.setReadOnly("true");
			dataItemDTOList.add(openBankOrg);
			
			DataItem openBankAccount = new DataItem();
			openBankAccount.setId("openBankAccount");
			openBankAccount.setName("账号（缴纳）"); 
			if(agency != null && agency.getOpenAccount() != null)
				openBankAccount.setValue(agency.getOpenAccount());
			openBankAccount.setType("project");
			openBankAccount.setReadOnly("true");
			dataItemDTOList.add(openBankAccount);
			
			//TODO 未存入支付方式
			DataItem openBankPayment = new DataItem();
			openBankPayment.setId("openBankPayment");
			openBankPayment.setName("支付方式（缴纳）"); 
			openBankPayment.setValue("");
			openBankPayment.setType("project");
			openBankPayment.setReadOnly("true");
			dataItemDTOList.add(openBankPayment);
			
			DataItem tenderStartTime = new DataItem();
			tenderStartTime.setId("tenderStartTime");
			tenderStartTime.setName("开始时间（投标）"); 
			if(projectRule != null && projectRule.getSubmitStTime() != null)
				tenderStartTime.setValue(DateUtil.format(projectRule.getSubmitStTime(),"yyyy年MM月dd日"));
			tenderStartTime.setType("project");
			tenderStartTime.setReadOnly("true");
			dataItemDTOList.add(tenderStartTime);
			
			DataItem tenderEndTime = new DataItem();
			tenderEndTime.setId("tenderEndTime");
			tenderEndTime.setName("截止时间（投标）"); 
			if(projectRule != null && projectRule.getSubmitETime() != null)
				tenderEndTime.setValue(DateUtil.format(projectRule.getSubmitETime(),"yyyy年MM月dd日"));
			tenderEndTime.setType("project");
			tenderEndTime.setReadOnly("true");
			dataItemDTOList.add(tenderEndTime);
			
			DataItem openTime = new DataItem();
			openTime.setId("openTime");
			openTime.setName("开标时间"); 
			if(projectRule != null && projectRule.getOpenBidSDate() != null)
				openTime.setValue(DateUtil.format(projectRule.getOpenBidSDate(),"yyyy年MM月dd日"));
			openTime.setType("project");
			openTime.setReadOnly("true");
			dataItemDTOList.add(openTime);
			
			DataItem openLocation = new DataItem();
			openLocation.setId("openLocation");
			openLocation.setName("开标地点"); 
			if(projectRule != null && projectRule.getOpenBidAddr() != null)
				openLocation.setValue(projectRule.getOpenBidAddr());
			openLocation.setType("project");
			openLocation.setReadOnly("true");
			dataItemDTOList.add(openLocation);
			
			DataItem prjLeader = new DataItem();
			prjLeader.setId("prjLeader");
			prjLeader.setName("项目负责人"); 
			if(p.getManager() != null)
				prjLeader.setValue(p.getManager().getName());
			prjLeader.setType("project");
			prjLeader.setReadOnly("true");
			dataItemDTOList.add(prjLeader);
			
			DataItem prjLeaderLink = new DataItem();
			prjLeaderLink.setId("prjLeaderLink");
			prjLeaderLink.setName("负责人联系方式"); 
			if(p.getManager() != null && p.getManager().getTelOffice() != null)
				prjLeaderLink.setValue(p.getManager().getTelOffice());
			prjLeaderLink.setType("project");
			prjLeaderLink.setReadOnly("true");
			dataItemDTOList.add(prjLeaderLink);
			
			//工程类型
			DataItem projectType = new DataItem();
			projectType.setId("projectType");
			projectType.setName("工程类型"); 
			projectType.setValue("1");
			prjLeader.setType("project");
			prjLeader.setReadOnly("true");
			dataItemDTOList.add(projectType);
			
			/*查询包组数据*/
			List<Project> subProjectList = new ArrayList<Project>();
			subProjectList = projectService.getSubProjectByProjectId(p.getObjId());
			
			
			
			
			if(subProjectList == null || subProjectList.size() == 0){//如果当前项目未分包
				List<PreqEntry> preqEntryList = preqEntryService.getPreqEntryByprojectId(p.getObjId());
				for (int i=0;i<preqEntryList.size();i++) {
					PreqEntry preqEntry = preqEntryList.get(i);
					DataItem project_requirement_1_itemCode = new DataItem();
					project_requirement_1_itemCode.setId("project_requirement_"+i+"_itemCode");
					project_requirement_1_itemCode.setName("采购品目"); 
					project_requirement_1_itemCode.setValue(preqEntry.getPurchaseCode());
					project_requirement_1_itemCode.setType("project");
					project_requirement_1_itemCode.setReadOnly("true");
					dataItemDTOList.add(project_requirement_1_itemCode);
					
					DataItem project_requirement_1_itemName = new DataItem();
					project_requirement_1_itemName.setId("project_requirement_"+i+"_itemName");
					project_requirement_1_itemName.setName("采购品目名称"); 
					project_requirement_1_itemName.setValue(preqEntry.getPurchaseName());
					project_requirement_1_itemName.setType("project");
					project_requirement_1_itemName.setReadOnly("true");
					dataItemDTOList.add(project_requirement_1_itemName);
					
					
					DataItem project_requirement_1_purNum = new DataItem();
					project_requirement_1_purNum.setId("project_requirement_"+i+"_purNum");
					project_requirement_1_purNum.setName("采购数量"); 
					project_requirement_1_purNum.setValue(preqEntry.getQuantity().toString());
					project_requirement_1_purNum.setType("project");
					project_requirement_1_purNum.setReadOnly("true");
					dataItemDTOList.add(project_requirement_1_purNum);
					
					DataItem project_requirement_1_unitPrice = new DataItem();
					project_requirement_1_unitPrice.setId("project_requirement_"+i+"_unitPrice");
					project_requirement_1_unitPrice.setName("单价"); 
					project_requirement_1_unitPrice.setValue((preqEntry.getTotalPrice().floatValue()/preqEntry.getQuantity().floatValue())+""); 
					project_requirement_1_unitPrice.setType("project");
					project_requirement_1_unitPrice.setReadOnly("true");
					dataItemDTOList.add(project_requirement_1_unitPrice);
			 
					DataItem project_requirement_1_specification = new DataItem();
					project_requirement_1_specification.setId("project_requirement_"+i+"_specification");
					project_requirement_1_specification.setName("规格"); 
					project_requirement_1_specification.setValue(preqEntry.getSpec()); 
					project_requirement_1_specification.setType("project");
					project_requirement_1_specification.setReadOnly("true");
					dataItemDTOList.add(project_requirement_1_specification);
					
					/*放入技术要求*/
					DataItem project_requirement_1_reqTechnic = new DataItem();
					project_requirement_1_reqTechnic.setId("project_requirement_"+i+"_reqTechnic");
					project_requirement_1_reqTechnic.setName("技术要求"); 
					project_requirement_1_reqTechnic.setType("project");
					project_requirement_1_reqTechnic.setValue(preqEntry.getTechnical());
					project_requirement_1_reqTechnic.setReadOnly("true");
					dataItemDTOList.add(project_requirement_1_reqTechnic);
					
					//放入付款条件
					DataItem project_requirement_1_reqPayCause = new DataItem();
					project_requirement_1_reqPayCause.setId("project_requirement_"+i+"_reqPayCause");
					project_requirement_1_reqPayCause.setName("付款条件"); 
					project_requirement_1_reqPayCause.setValue(preqEntry.getPaymentClause());
					project_requirement_1_reqPayCause.setType("project");
					project_requirement_1_reqPayCause.setReadOnly("true");
					dataItemDTOList.add(project_requirement_1_reqPayCause);
					
					//放入交货要求
					DataItem project_requirement_1_reqDelivery = new DataItem();
					project_requirement_1_reqDelivery.setId("project_requirement_"+i+"_reqDelivery");
					project_requirement_1_reqDelivery.setName("交货要求"); 
					project_requirement_1_reqDelivery.setValue(preqEntry.getDeliveryRequire());
					project_requirement_1_reqDelivery.setType("project");
					project_requirement_1_reqDelivery.setReadOnly("true");
					dataItemDTOList.add(project_requirement_1_reqDelivery);
					
					DataItem project_requirement_1_reqService = new DataItem();
					project_requirement_1_reqService.setId("project_requirement_"+i+"_reqService");
					project_requirement_1_reqService.setName("服务承诺"); 
					project_requirement_1_reqService.setValue(preqEntry.getServicePromise());
					project_requirement_1_reqService.setType("project");
					project_requirement_1_reqService.setReadOnly("true");
					dataItemDTOList.add(project_requirement_1_reqService);
					
					//放入质量要求
					DataItem project_requirement_1_reqQuality = new DataItem();
					project_requirement_1_reqQuality.setId("project_requirement_"+i+"_reqQuality");
					project_requirement_1_reqQuality.setName("质量要求"); 
					project_requirement_1_reqQuality.setValue(preqEntry.getQualityAssurance());
					project_requirement_1_reqQuality.setType("project");
					project_requirement_1_reqQuality.setReadOnly("true");
					dataItemDTOList.add(project_requirement_1_reqQuality);
					
					//放入维修期限
					DataItem project_requirement_1_reqWarrenty = new DataItem();
					project_requirement_1_reqWarrenty.setId("project_requirement_"+i+"_reqWarrenty");
					project_requirement_1_reqWarrenty.setName("维修期限"); 
					project_requirement_1_reqWarrenty.setValue(preqEntry.getWarrentyLen());
					project_requirement_1_reqWarrenty.setType("project");
					project_requirement_1_reqWarrenty.setReadOnly("true");
					dataItemDTOList.add(project_requirement_1_reqWarrenty);
					
					//放入验收标准
					DataItem project_requirement_1_reqAcceptStandard = new DataItem();
					project_requirement_1_reqAcceptStandard.setId("project_requirement_"+i+"_reqAcceptStandard");
					project_requirement_1_reqAcceptStandard.setName("验收标准"); 
					project_requirement_1_reqAcceptStandard.setValue(preqEntry.getAcceptStandard());
					project_requirement_1_reqAcceptStandard.setType("project");
					project_requirement_1_reqAcceptStandard.setReadOnly("true");
					dataItemDTOList.add(project_requirement_1_reqAcceptStandard);
					
					DataItem project_requirement_1_memo = new DataItem();
					project_requirement_1_memo.setId("project_requirement_"+i+"_memo");
					project_requirement_1_memo.setName("备注"); 
					project_requirement_1_memo.setValue(preqEntry.getRemark());
					project_requirement_1_memo.setType("project");
					project_requirement_1_memo.setReadOnly("true");
					dataItemDTOList.add(project_requirement_1_memo);
					
				}
			}else{ //如果项目分包
				/*封装包组数据*/
				for(int i = 0 ; i < subProjectList.size() ; i ++){
					Project subP = subProjectList.get(i);
					List<PreqEntry> subPreqEntryList = preqEntryService.getPreqEntryBySubProjectId(subP.getObjId());//根据包件获取项目需求;
					PreqEntry subPreqEntry = null;
					if(subPreqEntryList != null && !subPreqEntryList.isEmpty()){
						subPreqEntry = subPreqEntryList.get(0);
					}
					DataItem packId = new DataItem();
					packId.setId("pack_" + String.valueOf(i + 1) + "_id");
					packId.setName("记录号"); 
					packId.setValue(subP.getObjId());
					packId.setType("project");
					packId.setReadOnly("true");
					dataItemDTOList.add(packId);
					
					DataItem packCode = new DataItem();
					packCode.setId("pack_" + String.valueOf(i + 1) + "_code");
					packCode.setName("包件编号"); 
					packCode.setValue(subP.getProjCode());
					packCode.setType("project");
					packCode.setReadOnly("true");
					dataItemDTOList.add(packCode);
					
					DataItem packName = new DataItem();
					packName.setId("pack_" + String.valueOf(i + 1) + "_name");
					packName.setName("包件名称"); 
					packName.setValue(subP.getProjName());
					packName.setType("project");
					packName.setReadOnly("true");
					dataItemDTOList.add(packName);
					
					DataItem packMemo = new DataItem();
					packMemo.setId("pack_" + String.valueOf(i + 1) + "_memo");
					packMemo.setName("采购摘要"); 
					packMemo.setValue(subP.getProjSummary());
					packMemo.setType("project");
					packMemo.setReadOnly("true");
					dataItemDTOList.add(packMemo);
					
					DataItem subPapplyStartTime = new DataItem();
					subPapplyStartTime.setId("pack_" + String.valueOf(i + 1) + "_applyStartTime");
					subPapplyStartTime.setName("开始时间（报名）"); 
					if(projectRule != null && projectRule.getSignUpSTime() != null)
						subPapplyStartTime.setValue(DateUtil.format(projectRule.getSignUpSTime(),"yyyy年MM月dd日"));
					subPapplyStartTime.setType("project");
					subPapplyStartTime.setReadOnly("true");
					dataItemDTOList.add(subPapplyStartTime);
					
					DataItem subPapplyEndTime = new DataItem();
					subPapplyEndTime.setId("pack_" + String.valueOf(i + 1) + "_applyEndTime");
					subPapplyEndTime.setName("截止时间（报名）"); 
					if(projectRule != null && projectRule.getSignUpETime() != null)
						subPapplyEndTime.setValue(DateUtil.format(projectRule.getSignUpETime(),"yyyy年MM月dd日"));
					subPapplyEndTime.setType("project");
					subPapplyEndTime.setReadOnly("true");
					dataItemDTOList.add(subPapplyEndTime);
					
					DataItem subPtenderStartTime = new DataItem();
					subPtenderStartTime.setId("pack_" + String.valueOf(i + 1) + "_tenderStartTime");
					subPtenderStartTime.setName("开始时间（投标）"); 
					if(projectRule != null && projectRule.getSubmitStTime() != null)
						subPtenderStartTime.setValue(DateUtil.format(projectRule.getSubmitStTime(),"yyyy年MM月dd日"));
					subPtenderStartTime.setType("project");
					subPtenderStartTime.setReadOnly("true");
					dataItemDTOList.add(subPtenderStartTime);
					
					DataItem subPtenderEndTime = new DataItem();
					subPtenderEndTime.setId("pack_" + String.valueOf(i + 1) + "_tenderEndTime");
					subPtenderEndTime.setName("截止时间（投标）"); 
					if(projectRule != null && projectRule.getSubmitETime() != null)
						subPtenderEndTime.setValue(DateUtil.format(projectRule.getSubmitETime(),"yyyy年MM月dd日"));
					subPtenderEndTime.setType("project");
					subPtenderEndTime.setReadOnly("true");
					dataItemDTOList.add(subPtenderEndTime);
					
					DataItem subPopenTime = new DataItem();
					subPopenTime.setId("pack_" + String.valueOf(i + 1) + "_openTime");
					subPopenTime.setName("开标时间"); 
					if(projectRule != null && projectRule.getOpenBidSDate() != null)
						subPopenTime.setValue(DateUtil.format(projectRule.getOpenBidSDate(),"yyyy年MM月dd日"));
					subPopenTime.setType("project");
					subPopenTime.setReadOnly("true");
					dataItemDTOList.add(subPopenTime);
					
					DataItem requirement_1_itemCode = new DataItem();
					requirement_1_itemCode.setId("pack_" + String.valueOf(i + 1) + "_requirement_1_itemCode");
					requirement_1_itemCode.setName("采购品目"); 
					requirement_1_itemCode.setValue(subP.getPurCategoryIds());
					requirement_1_itemCode.setType("project");
					requirement_1_itemCode.setReadOnly("true");
					dataItemDTOList.add(requirement_1_itemCode);
					
					DataItem requirement_1_itemName = new DataItem();
					requirement_1_itemName.setId("pack_" + String.valueOf(i + 1) + "_requirement_1_itemName");
					requirement_1_itemName.setName("采购品目名称"); 
					requirement_1_itemName.setValue(subP.getPurCategoryNames());
					requirement_1_itemName.setType("project");
					requirement_1_itemName.setReadOnly("true");
					dataItemDTOList.add(requirement_1_itemName);
					
					if(subPreqEntry != null && subPreqEntry.getObjId() != null){
						DataItem requirement_1_purNum = new DataItem();
						requirement_1_purNum.setId("pack_" + String.valueOf(i + 1) + "_requirement_1_purNum");
						requirement_1_purNum.setName("采购数量"); 
						requirement_1_purNum.setValue(subPreqEntry.getQuantity().toString());
						requirement_1_purNum.setType("project");
						requirement_1_purNum.setReadOnly("true");
						dataItemDTOList.add(requirement_1_purNum);
						
						DataItem requirement_1_unitPrice = new DataItem();
						requirement_1_unitPrice.setId("pack_" + String.valueOf(i + 1) + "_requirement_1_unitPrice");
						requirement_1_unitPrice.setName("单价"); 
						requirement_1_unitPrice.setValue((subPreqEntry.getTotalPrice().floatValue()/subPreqEntry.getQuantity().floatValue())+"");
						requirement_1_unitPrice.setType("project");
						requirement_1_unitPrice.setReadOnly("true");
						dataItemDTOList.add(requirement_1_unitPrice);
						
						DataItem requirement_1_specification = new DataItem();
						requirement_1_specification.setId("pack_" + String.valueOf(i + 1) + "_requirement_1_specification");
						requirement_1_specification.setName("规格"); 
						requirement_1_specification.setValue(subPreqEntry.getSpec());
						requirement_1_specification.setType("project");
						requirement_1_specification.setReadOnly("true");
						dataItemDTOList.add(requirement_1_specification);
						
						DataItem pack_1_requirement_1_reqTechnic = new DataItem();
						pack_1_requirement_1_reqTechnic.setId("pack_" + String.valueOf(i + 1) + "_requirement_1_reqTechnic");
						pack_1_requirement_1_reqTechnic.setName("技术要求"); 
						pack_1_requirement_1_reqTechnic.setValue(subPreqEntry.getTechnical());
						pack_1_requirement_1_reqTechnic.setType("project");
						pack_1_requirement_1_reqTechnic.setReadOnly("true");
						dataItemDTOList.add(pack_1_requirement_1_reqTechnic);
						
						DataItem requirement_1_reqPayCause = new DataItem();
						requirement_1_reqPayCause.setId("pack_" + String.valueOf(i + 1) + "_requirement_1_reqPayCause");
						requirement_1_reqPayCause.setName("付款条件"); 
						requirement_1_reqPayCause.setValue(subPreqEntry.getPaymentClause());
						requirement_1_reqPayCause.setType("project");
						requirement_1_reqPayCause.setReadOnly("true");
						dataItemDTOList.add(requirement_1_reqPayCause);
						
						DataItem requirement_1_reqDelivery = new DataItem();
						requirement_1_reqDelivery.setId("pack_" + String.valueOf(i + 1) + "_requirement_1_reqDelivery");
						requirement_1_reqDelivery.setName("交货要求"); 
						requirement_1_reqDelivery.setValue(subPreqEntry.getDeliveryRequire());
						requirement_1_reqDelivery.setType("project");
						requirement_1_reqDelivery.setReadOnly("true");
						dataItemDTOList.add(requirement_1_reqDelivery);
						
						DataItem requirement_1_reqQuality = new DataItem();
						requirement_1_reqQuality.setId("pack_" + String.valueOf(i + 1) + "_requirement_1_reqQuality");
						requirement_1_reqQuality.setName("质量要求"); 
						requirement_1_reqQuality.setValue(subPreqEntry.getQualityAssurance());
						requirement_1_reqQuality.setType("project");
						requirement_1_reqQuality.setReadOnly("true");
						dataItemDTOList.add(requirement_1_reqQuality);
						
						DataItem requirement_1_reqWarrenty = new DataItem();
						requirement_1_reqWarrenty.setId("pack_" + String.valueOf(i + 1) + "_requirement_1_reqWarrenty");
						requirement_1_reqWarrenty.setName("维修期限"); 
						requirement_1_reqWarrenty.setValue(subPreqEntry.getWarrentyLen());
						requirement_1_reqWarrenty.setType("project");
						requirement_1_reqWarrenty.setReadOnly("true");
						dataItemDTOList.add(requirement_1_reqWarrenty);
						
						DataItem requirement_1_reqAcceptStandard = new DataItem();
						requirement_1_reqAcceptStandard.setId("pack_" + String.valueOf(i + 1) + "_requirement_1_reqAcceptStandard");
						requirement_1_reqAcceptStandard.setName("验收标准"); 
						requirement_1_reqAcceptStandard.setValue(subPreqEntry.getAcceptStandard());
						requirement_1_reqAcceptStandard.setType("project");
						requirement_1_reqAcceptStandard.setReadOnly("true");
						dataItemDTOList.add(requirement_1_reqAcceptStandard);
						
						DataItem requirement_1_memo = new DataItem();
						requirement_1_memo.setId("pack_" + String.valueOf(i + 1) + "_requirement_1_memo");
						requirement_1_memo.setName("备注"); 
						requirement_1_memo.setValue(subPreqEntry.getRemark()); 
						requirement_1_memo.setType("project");
						requirement_1_memo.setReadOnly("true");
						dataItemDTOList.add(requirement_1_memo);
					}
				}
			}
		} catch (Exception e) {
			errorMessage = e.getMessage();
			e.printStackTrace();
			operDesc = false;
		}
		
		/*生成响应数据*/
		StringBuffer responseXML = new StringBuffer("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
		responseXML.append("<getProjectData xmlns=\"http://www.gpcsoft.com\">");
		responseXML.append("<header>");
		responseXML.append("<operTag>"+(operDesc ? "Y" : "N")+"</operTag>");
		responseXML.append("<operDesc/>");
		responseXML.append("<operException>"+errorMessage+"</operException>");
		responseXML.append("<prjCode>"+ prjCode +"</prjCode>");
		responseXML.append("</header>");
		responseXML.append("<body>");
		responseXML.append("<dataItemList>");
		for(DataItem dataItem : dataItemDTOList){
			responseXML.append(XmlUtil.objectToXml(dataItem));
		}
		responseXML.append("</dataItemList>");
		responseXML.append("</body>");
		responseXML.append("</getProjectData>");
		logger.debug("ProjectSoapSerivce  getProjectData start=============================================\n"+responseXML.toString());
		logger.debug("ProjectSoapSerivce  getProjectData end=============================================\n");
		return responseXML.toString();
	}
	
	
	/**
	 * FuncName: getOpenBidProjectList
	 * Description :  根据查询条件获取待开标的项目清单xml
	 * @param startDate:开始时间(例：2011-10-03 23:00:33)
	 * @param endDate:结束时间(例：2011-10-04 23:00:33)
	 * @param tenderType:项目类型(枚举：01:政府采购, 02:土地交易, 03:产权交易,04:建筑工程,00:所有)
     * @param agencyCode:代理机构编号
     * @param username:经办人帐号
	 * @return String
	 * @author: liuy	 
	 * @Modified Date:2011-10-20  上午11:23:40
	 */
	public String getOpenBidProjectList(String startDate, String endDate, String tenderType, String agencyCode, String username){
		StringBuffer projectDTOInfo = new StringBuffer();//存放返回的项目列表
		StringBuffer workMembersInfo = new StringBuffer();//存放返回的开标小组
		String errorMessage = "";
		String operTag = "Y";
		try {
			if("00".equals(tenderType)){//若是录入00，则表示不需要根据类型区分
				tenderType = null;
			}
			List<ProjectDTO> projectDTOList = remoteProjectService.getOpenBidProjectList(startDate, endDate, tenderType, agencyCode, username);
			ArrayList<String> tenderIdList = new ArrayList<String>(); 
			for(ProjectDTO projectDTO : projectDTOList){
				tenderIdList.add(projectDTO.getTenderiId());
			}
			List<WorkMembersDTO> workMembersDTOList =  new ArrayList<WorkMembersDTO>();//remoteProjectService.getWorkMemberList();
			for(ProjectDTO projectDTO : projectDTOList){
				List<ProjectDTO> l = projectDTO.getSubProjectList();
				if(ProjectEnum.JZGC.equals(projectDTO.getTenderType())){
					projectDTO.setTenderMethod(EbuyMethodEnum.getEBuyMethodCN(projectDTO.getTenderMethod()));
				}
				projectDTOInfo.append(XmlUtil.objectToXml(projectDTO).split("</ecpTenderProject>")[0]);
				if(l!=null){
					for(ProjectDTO projectDTO2 : l){
						projectDTO2.setEvalStartTime(projectDTO.getEvalStartTime());
						projectDTO2.setEvalEndTime(projectDTO.getEvalEndTime());
						if(ProjectEnum.JZGC.equals(projectDTO.getTenderType())){
							projectDTO2.setTenderMethod(EbuyMethodEnum.getEBuyMethodCN(projectDTO2.getTenderMethod()));
						}
						projectDTOInfo.append(XmlUtil.objectToXml(projectDTO2));
					}
				}
				projectDTOInfo.append("<workGroups><workGroup>");
				projectDTOInfo.append("<workGroupType>03</workGroupType>");
				projectDTOInfo.append("<members>");
		    	List<WorkgMember> workgMemberList = workgMemberService.getWorkgMemberByProjectId(projectDTO.getTenderiId(),"03");
		    	for (Iterator<WorkgMember> iterator = workgMemberList.iterator(); iterator
						.hasNext();) {
					WorkgMember workgMember = (WorkgMember) iterator.next();
					WorkMembersDTO workMembersDTO1 = new WorkMembersDTO();
					workMembersDTO1.setWorkgmType(workgMember.getWorkgmType());
					workMembersDTO1.setWorkgmName(workgMember.getWorkgmName());
					String leader = workgMember.getWorkgmIsLeader();
					if(leader != null && leader.length()>1){//因设计和协议发生冲突，所以这里做一次转义
						leader = ("01".equals(leader))?"1":"0";
					}
					workMembersDTO1.setIsLeader(leader);
					String signinStatus = workgMember.getSigninStatus();
					if(signinStatus != null && signinStatus.length()>1){//因设计和协议发生冲突，所以这里做一次转义
						signinStatus = ("01".equals(signinStatus))?"1":"0";
					}
					workMembersDTO1.setSigninStatus(signinStatus);
					String isAmount = workgMember.getIsAmount();
					if(isAmount != null && isAmount.length()>1){//因设计和协议发生冲突，所以这里做一次转义
						isAmount = ("00".equals(isAmount))?"1":"0";
					}
					workMembersDTO1.setIsAmount(isAmount);
					workMembersDTOList.add(workMembersDTO1);
				}
		    	//监管人员数据、株洲需求
		    	WorkMembersDTO workMembersDTO_M = new WorkMembersDTO();
		    	Employee employee = userApiService.getEmployeeById(projectDTO.getPrjMonitor());;
		    	if(employee != null){
		    		workMembersDTO_M.setWorkgmName(employee.getName());
		    	}
		    	workMembersDTO_M.setWorkgmType("03");
				workMembersDTO_M.setIsLeader("1");//组长
				workMembersDTO_M.setSigninStatus("1");//现场
				workMembersDTO_M.setIsAmount("1");//正选
				
				
		    	
				for(WorkMembersDTO workMembersDTO : workMembersDTOList){
					workMembersInfo.append(XmlUtil.objectToXml(workMembersDTO));
				}
				projectDTOInfo.append(workMembersInfo.toString());
				projectDTOInfo.append("</members>");
				projectDTOInfo.append("</workGroup></workGroups>");
				projectDTOInfo.append("</ecpTenderProject>");
			}
		}catch(Exception e){
			e.printStackTrace();
			errorMessage = e.getMessage();
			operTag = "N";
		}
		
		//添加返回XML的主体内容
		StringBuffer responseXml = new StringBuffer();
		responseXml.append("<?xml version='1.0' encoding='utf-8' ?>");
		responseXml.append("<getProjectInfo xmlns='http://www.gpcsoft.com'>");
		responseXml.append("<header>");
		responseXml.append("<prjCode>");
		responseXml.append("</prjCode>");
		responseXml.append("<prjName>");
		responseXml.append("</prjName>");
		responseXml.append("<operTag>"+(operTag.equals("Y")?"Y":"N")+"</operTag>");
		responseXml.append("<operDesc>"+(operTag.equals("Y")?"操作成功!":"操作失败!")+"</operDesc>");
		responseXml.append("<operException>"+errorMessage+"</operException>");
		responseXml.append("</header>");
		responseXml.append("<body>");
		responseXml.append("<ecpTenderProjects>");
		responseXml.append(projectDTOInfo.toString());
		responseXml.append("</ecpTenderProjects>");
		responseXml.append("<ecpWorkgMembers>");
		responseXml.append(workMembersInfo.toString());
		responseXml.append("</ecpWorkgMembers>");
		responseXml.append("</body>");
		responseXml.append("</getProjectInfo >");
		logger.debug("ProjectSoapSerivce  getOpenBidProjectList start=============================================\n"+responseXml.toString());
		logger.debug("ProjectSoapSerivce  getOpenBidProjectList end=============================================\n");
		return responseXml.toString();
	}
	

	/** 
	 * FuncName : getBiddingList
	 * Description :  根据包组、项目编号查询条件获取标段投标人信息
	 * Create Date: 2011-10-20下午04:44:18 by yangx  
	 * Modified Date: 2011-10-20下午04:44:18 by yangx
	 * @param   prjCode:招标项目编号
	 * @param   packCode:标段编号
	 * @return  
	 * @Exception   
	 */
	public String getBiddingList(String prjCode, String packCode) throws Exception{
		Boolean operDesc = true;
		String errorMessage = "";
		ProjectDTO projectDTO = new ProjectDTO();
		List<ApplyDTO> applyDTOList = new ArrayList<ApplyDTO>();
		try {
			List<ProjectDTO> projectDTOList = remoteProjectService.getProjectInfoByCode(prjCode, packCode);
			projectDTO = projectDTOList.get(0);
			if(ProjectEnum.JZGC.equals(projectDTO.getTenderType())){
				projectDTO.setTenderMethod(EbuyMethodEnum.getEBuyMethodCN(projectDTO.getTenderMethod()));
			}
			ArrayList<String> tenderIdList = new ArrayList<String>(); 
			for(ProjectDTO projectdto : projectDTOList){
				tenderIdList.add(projectdto.getTenderiId());
			}
			if(tenderIdList != null && tenderIdList.size() > 0){
				applyDTOList = remoteProjectService.getApplyInfoByCode(tenderIdList, packCode);
			}
		}catch(Exception e){
			errorMessage = e.getMessage();
			operDesc = false;
		}
		
		/*生成响应数据*/
		StringBuffer responseXML = new StringBuffer("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
		responseXML.append("<getBiddingList  xmlns=\"http://www.gpcsoft.com\">");
		responseXML.append("<header>");
		responseXML.append("<operTag>"+(operDesc ? "Y" : "N")+"</operTag>");
		responseXML.append("<operDesc></operDesc>");
		responseXML.append("<operException>"+errorMessage+"</operException>");
		responseXML.append("</header>");
		responseXML.append("<body>");
		responseXML.append("<ecpTenderApplyRecs>");
		for(ApplyDTO applyDTO : applyDTOList){
			responseXML.append(XmlUtil.objectToXml(applyDTO));
		}
		responseXML.append("</ecpTenderApplyRecs>");
		responseXML.append("</body>");
		responseXML.append("</getBiddingList>");
		logger.debug("ProjectSoapSerivce  getBiddingList start=============================================\n"+responseXML.toString());
		logger.debug("ProjectSoapSerivce  getBiddingList end=============================================\n");
		return responseXML.toString();
	}
}
