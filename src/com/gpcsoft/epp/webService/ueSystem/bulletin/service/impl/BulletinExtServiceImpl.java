package com.gpcsoft.epp.webService.ueSystem.bulletin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.DateUtil;
import com.gpcsoft.epp.common.manager.UserApiManager;
import com.gpcsoft.epp.common.utils.FileUtils;
import com.gpcsoft.epp.bid.domain.AnonymousEnum;
import com.gpcsoft.epp.bid.service.BidService;
import com.gpcsoft.epp.bulletin.domain.Bulletin;
import com.gpcsoft.epp.bulletin.manager.BulletinManager;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.domain.ProjectRule;
import com.gpcsoft.epp.project.manager.ProjectManager;
import com.gpcsoft.epp.project.service.ProjectService;
import com.gpcsoft.epp.signuprecord.service.SignUprecordService;
import com.gpcsoft.epp.webService.ueSystem.bulletin.dao.BulletinExtDao;
import com.gpcsoft.epp.webService.ueSystem.bulletin.dto.BulletinDTO;
import com.gpcsoft.epp.webService.ueSystem.bulletin.dto.BulletinInfoDTO;
import com.gpcsoft.epp.webService.ueSystem.bulletin.dto.PackDTO;
import com.gpcsoft.epp.webService.ueSystem.bulletin.service.BulletinExtService;
import com.gpcsoft.srplatform.auth.domain.User;

@SuppressWarnings("unchecked")
@Service 
public class BulletinExtServiceImpl extends BaseGenericServiceImpl implements BulletinExtService {


	@Autowired(required=true) @Qualifier("bulletinManagerImpl")
	private BulletinManager bulletinManager;
	
	@Autowired(required=true) @Qualifier("projectManagerImpl")
	private ProjectManager projectManager;
	
	@Autowired(required=true) @Qualifier("projectServiceImpl")
	private ProjectService projectService;
	
	@Autowired(required=true) @Qualifier("bulletinExtDaoHibernate")
	private BulletinExtDao bulletinExtDaoHibernate;
	
	@Autowired(required=true) @Qualifier("signUprecordServiceImpl")
	private SignUprecordService signUprecordService;
	
	@Autowired(required=true) @Qualifier("bidServiceImpl")
	private BidService bidService;
	
	@Autowired(required=true) @Qualifier("userApiManagerImpl")
	private UserApiManager userApiManager;
	
	/** 
	 * Description :  getBulletinBybulNo
	 * Create Date: 2011-2-21下午01:19:41 by yangx  Modified Date: 2011-2-21下午01:19:41 by yangx
	 * @param   bulNo：公告编号
	 * @return  Bulletin
	 * @Exception   
	 */
	/*public BulletinDTO getBulletinBybulNo(String bulNo) {
		BulletinDTO bulletinExt = null;
		QueryObject<Bulletin> queryObject = new QueryObjectBase<Bulletin>();
		queryObject.setEntityClass(Bulletin.class);
		queryObject.getQueryParam().and(new QueryParam("bulletinNo",QueryParam.OPERATOR_EQ,bulNo));//公告编号
		List<Bulletin> bulletinList = bulletinManager.findByQuery(queryObject);
		if (bulletinList!=null&&bulletinList.size()>0) {
			Bulletin bulletin = bulletinList.get(0);//获取公告
			if(bulletin!=null&&bulletin.getContent()!=null){
				String filePath = messageSource.getMessage("uploadUrl");
				String bullContent = FileUtils.readFileByLines(filePath+bulletin.getContent().getPath()+bulletin.getContent().getFileName(), null);//读取公告内容
				bulletin.setBullContents(bullContent);
			}
			Project projectS = bulletin.getProject();//获取项目/包组
			String projectId = projectS.getObjId();
			ProjectRule projectRule = null;
			if (projectS!=null&&projectS.getParentId()!=null&&!"".equals(projectS.getParentId())) {//判断是否为包组
				Project project = projectManager.get(projectS.getParentId());//项目
				projectId = project.getObjId();
				projectRule = projectManager.getProjectRuleByProjectId(projectId);//项目规则
				bulletinExt = this.getBulletinExt(bulletin, project, projectS, projectRule);
			}else{
				projectRule = projectManager.getProjectRuleByProjectId(projectId);//项目规则
				bulletinExt = this.getBulletinExt(bulletin, projectS, null, projectRule);
			}
		}
		return bulletinExt;
	}*/

	/*private BulletinDTO getBulletinExt(Bulletin bulletin,Project project,Project subProject,ProjectRule projectRule){
		String esAddress = messageSource.getMessage("esAddress");//执行平台外网地址
		BulletinDTO bulletinExt = new BulletinDTO();
		bulletinExt.setBulName(bulletin.getBullTitle());//公告名称
		bulletinExt.setBulNo(bulletin.getBulletinNo());//公告编号
		bulletinExt.setBulletinType(bulletin.getBullType());//公告类型
		bulletinExt.setContent(bulletin.getBullContents());//公告内容
		bulletinExt.setProjectName(project.getProjName());//关联项目名称
		bulletinExt.setProjectNo(project.getProjCode());//关联项目编号
		bulletinExt.setSignupStartDate(DateUtil.format(projectRule.getSignUpSTime(),"yyyy-MM-dd HH:mm:ss"));//报名开始时间
		bulletinExt.setSignupEndDate(DateUtil.format(projectRule.getSignUpETime(),"yyyy-MM-dd HH:mm:ss"));//报名结束时间
		bulletinExt.setSubmitStartDate(DateUtil.format(projectRule.getSubmitStTime(),"yyyy-MM-dd HH:mm:ss"));//投标开始时间
		bulletinExt.setSubmitEndDate(DateUtil.format(projectRule.getSubmitETime(),"yyyy-MM-dd HH:mm:ss"));//投标结束时间
		bulletinExt.setFromUrl(esAddress); //系统来源
		bulletinExt.setPurCategoryNames(project.getPurCategoryNames());//采购品目名称
		bulletinExt.setPurCategoryNos(project.getPurCategoryIds());//采购品目编号
		if (subProject!=null) {//包组不为空
			bulletinExt.setPackName(subProject.getProjName());//关联包名称
			bulletinExt.setPackNo(subProject.getProjCode());//关联包编号
		}
		return bulletinExt;
	}*/
	
	/** 
	 * Description :  获取公告列表
	 * Create Date: 2011-3-3下午06:07:54 by yangx  Modified Date: 2011-3-3下午06:07:54 by yangx
	 * @param   bulletintype:公告类型,startTime:开始时间,endTime:结束时间,purareacode:采购区域,purcategorycode:采购品目编号,bulletinnum,获取记录条数
	 * @return  String：拼装好的XML字符串
	 * @Exception   
	 * @Modifier zhouzhanghe
	 * @Modifiy Date 2011-7-7 15:46(优化)
	 */
	public List<BulletinDTO> getBulletinList(String bulletintype,String startTime,String endTime,String purareacode,String purcategorycode,String bulletinnum,String keyword) throws Exception{
		logger.debug("\nes BulletinExtServiceImpl||getBulletinList\n");
		String applicationInternetAddress = messageSource.getMessage("applicationInternetAddress");//获取应用外网访问地址
		
		List<Bulletin> bulletinList = null;
	
		if (bulletintype!=null&&!"".equals(bulletintype)) {
			bulletintype = bulletintype.replaceAll(",","','");
		}
		bulletinList = bulletinExtDaoHibernate.getBulletinList(bulletintype, startTime,endTime, purareacode, purcategorycode, bulletinnum,keyword);
		List<BulletinDTO> bulletinDTOList = new ArrayList<BulletinDTO>(); 
		for(Bulletin bulletin : bulletinList){
			BulletinDTO bulletinDTO = new BulletinDTO();
			
			bulletinDTO.setBulletinCode(bulletin.getBulletinNo());
			bulletinDTO.setBulletinName(bulletin.getBullTitle());
			bulletinDTO.setProjectNo(bulletin.getProject().getProjCode());
			bulletinDTO.setBulletinType(bulletin.getBullType());
			bulletinDTO.setRelDate(DateUtil.format(bulletin.getRelDate(),"yyyy-MM-dd HH:mm:ss"));
			bulletinDTO.setFromUrl(applicationInternetAddress);
			bulletinDTO.setPurCategoryNos(bulletin.getProject().getPurCategoryIds());
			bulletinDTO.setSignupStartDate(bulletin.getSignUpSTime().toString());
			bulletinDTO.setSignupEndDate(bulletin.getSignUpETime().toString());
			//TODO 放入采购区域
			bulletinDTO.setPurAreaNos("");
			
			bulletinDTOList.add(bulletinDTO);
		}
		return bulletinDTOList;
	}
	
	private String getXmlForBulletinList(List<Bulletin> bulletinList,boolean flag){
		StringBuffer content = new StringBuffer();
		String esAddress = messageSource.getMessage("esAddress");//执行平台外网地址
		content.append("<?xml version=1.0 encoding='utf-8' ?>");
		content.append("<getBulletinList xmlns='http://www.gpcsoft.com'>");
		if (flag) {//判断是否错误
			content.append("<header><totalcount>"+bulletinList.size()+"</totalcount></header>");
		}else{//错误
			content.append("<header><totalcount>-1</totalcount></header>");
		}
			content.append("<body><bulletinlist>");
		if (flag&&bulletinList!=null&&bulletinList.size()>0) {
			for (Bulletin bulletin:bulletinList) {
				content.append("<bulletin>");
				content.append("<bulletinCode>"+bulletin.getBulletinNo()+"</bulletinCode>");//公告编号
				content.append("<prjCode>"+bulletin.getProject().getProjCode()+"</prjCode>");//关联项目编号
				content.append("<bulletinName>"+bulletin.getBullTitle()+"</bulletinName>");//公告名称
				content.append("<bulletinType>"+bulletin.getBullTypeCN()+"</bulletinType>");//公告类型
				content.append("<releaseDate>"+DateUtil.format(bulletin.getRelDate(),"yyyy-MM-dd HH:mm:ss")+"</releaseDate>");//发布时间
				content.append("<fromUrl></fromUrl>");//系统来源
				content.append("<purCategoryNos>"+bulletin.getProject().getPurCategoryIds()+"</purCategoryNos>");//采购品目编号
				content.append("<purAreaNos></purAreaNos>");//采购区域编号
				content.append(" </bulletin>");
			}
		}else{
			content.append("<bulletin>");
			content.append("<bulletinCode></bulletinCode>");//公告编号
			content.append("<prjCode></prjCode>");//关联项目编号
			content.append("<bulletinName></bulletinName>");//公告名称
			content.append("<bulletinType></bulletinType>");//公告类型
			content.append("<releaseDate></releaseDate>");//发布时间
			content.append("<fromUrl></fromUrl>");//系统来源
			content.append("<purCategoryNos></purCategoryNos>");//采购品目编号
			content.append("<purAreaNos></purAreaNos>");//采购区域编号
			content.append("<bulletin/>");
		}
		content.append("</bulletinlist></body>");
		content.append("</getBulletinList>");
		return content.toString();
	}

	public String getBulletinHeatInfo(String bulletinCode) {return null;}
	
	/**
	 * Description :获取公告数量统计信息
	 * @param fromDate 开始时间
	 * @param toDate 截止时间
	 * @return
	 * @author <a href="mailto:zhouzh@gpcsoft.com">zhouzhanghe</a>
	 * @since 2011-3-14 14:38
	 */
	public String getBulletinNumInfo(String fromDate, String toDate)throws Exception {
		List<Bulletin> list =	 bulletinExtDaoHibernate.getBulletinList("", fromDate, toDate, "", "", "", "");
		return (list!=null&&list.size()>0)?String.valueOf(list.size()):"0";
	}

	public String uploadApplyInfo(String bulletinCode, String bulletinTitle,
			String itemCode, String areaCode, String orgCode) {
		// TODO Auto-generated method stub
		return null;
	}

	public String uploadHeatInfo(String bulletinCode, String bulletinTitle,
			String itemCode, String areaCode, String orgCode) {
		// TODO Auto-generated method stub
		return null;
	}

	public String uploadViewInfo(String bulletinCode, String bulletinTitle,
			String itemCode, String areaCode, String orgCode) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Description :  获取公告详情
	 * @param bulletinNo 公告编号
	 * @param userId 用户Id
	 * @return
	 * @author <a href="mailto:zhouzh@gpcsoft.com">zhouzhanghe</a>
	 * @since 2011-3-14 14:38
	 * Modified on 2011-4-15 11:32 by zhouzhanghe(修改根据公告查询包组信息)
	 */
	public BulletinInfoDTO getBulletinInfoDTO(String bulletinNo,String userId) throws Exception{
		String applicationInternetAddress = messageSource.getMessage("applicationInternetAddress");//获取应用外网访问地址
		BulletinInfoDTO bulletinInfoDTO = new BulletinInfoDTO();
		
		//查询公告信息
		Bulletin bulletin = bulletinManager.getBulletinByBulletinNo(bulletinNo);
		//查询用户信息
		User user = null;
		if(userId != null){
			user = userApiManager.getUserByObjId(userId);
		}
		if(user != null){
			user.setOrgInfo(userApiManager.getOrgInfoByUserId(user.getObjId()));
		}
			
		//查找公告对应的项目
		Project project = null;
		if(bulletin.getProject() == null){
			logger.error("公告编号" + bulletinNo + "未与项目关联!");
		}else if(bulletin.getProject().getParentId() != null){
			project = projectManager.get(bulletin.getProject().getParentId());
		}else{
			project = bulletin.getProject();
		}
		ProjectRule projectRule = projectService.getProjectRuleByProjectId(project.getObjId());
		
		if(project == null){
			logger.error("根据公告编号" + bulletinNo + "查找项目失败!");
			return bulletinInfoDTO;
		}
		
		//放入业务数据
		bulletinInfoDTO.setBulletinCode(bulletinNo);
		bulletinInfoDTO.setBulletinName(bulletin.getBullTitle());
		bulletinInfoDTO.setPrjNo(project.getProjCode());
		bulletinInfoDTO.setPrjName(project.getProjName());
		bulletinInfoDTO.setPrjType(project.getTenderType()); //放入项目类型      by liuke 2011-07-26
		bulletinInfoDTO.setPurType(project.getEbuyMethod()); //放入采购方式      by liuke 2011-07-26
		bulletinInfoDTO.setPurchaseOrgName(project.getBuyersName());
		bulletinInfoDTO.setAngentName(project.getAgencies().getOrgName());
		bulletinInfoDTO.setPrjSummary(project.getContent());
		if(bulletin.getProject().getParentId() != null){
			bulletinInfoDTO.setIsPack("Y");
		}else{
			bulletinInfoDTO.setIsPack("N");
		}
		if(AnonymousEnum.notAnonymous.equals(projectRule.getRuleAnonymous())){
			bulletinInfoDTO.setTenderType("open");
		}else{
			bulletinInfoDTO.setTenderType("anonymous");
		}
		bulletinInfoDTO.setOpenBidUrl("");
		//放入包组
		List<Project> subProjects = null;
		if(bulletin.getProject() == null){
			logger.error("公告编号" + bulletinNo + "未与项目关联!");
		}else if(bulletin.getProject().getParentId() == null){
			bulletinInfoDTO.setPackNo(project.getProjCode());
			bulletinInfoDTO.setPackName(project.getProjName());
		}else{
			subProjects = projectManager.getSubProjectByQueryObject(bulletin.getProject().getParentId());
			for(Project subProject : subProjects){
				if(bulletinInfoDTO.getPackNo() == null){
					bulletinInfoDTO.setPackNo(subProject.getProjCode());
					bulletinInfoDTO.setPackName(subProject.getProjName());
				}else{
					bulletinInfoDTO.setPackNo(bulletinInfoDTO.getPackNo() + "," + subProject.getProjCode());
					bulletinInfoDTO.setPackName(bulletinInfoDTO.getPackName() + "," + subProject.getProjName());
				}
			}
		}

		bulletinInfoDTO.setBulletinType(bulletin.getBullType());
		
		//读取公告内容
		String filePath = messageSource.getMessage("uploadUrl");
		if(bulletin != null && bulletin.getContent() != null){
			String bullContent = FileUtils.readFileByLines(filePath+bulletin.getContent().getPath()+bulletin.getContent().getFileName(), null);//读取公告内容
			bulletinInfoDTO.setContent(bullContent);
		}
		
		bulletinInfoDTO.setSignupStartDate(DateUtil.format(projectRule.getSignUpSTime(),"yyyy-MM-dd HH:mm"));
		bulletinInfoDTO.setSignupEndDate(DateUtil.format(projectRule.getSignUpETime(),"yyyy-MM-dd HH:mm"));
		bulletinInfoDTO.setSubmitStartDate(DateUtil.format(projectRule.getSubmitStTime(),"yyyy-MM-dd HH:mm"));
		bulletinInfoDTO.setSubmitEndDate(DateUtil.format(projectRule.getSubmitETime(),"yyyy-MM-dd HH:mm"));
		bulletinInfoDTO.setRelDate(DateUtil.format(bulletin.getRelDate(),"yyyy-MM-dd HH:mm"));
		bulletinInfoDTO.setBulletinFile(applicationInternetAddress + "/AttachmentController.do?method=downLoadFile&objId=" + bulletin.getContent().getObjId());
		bulletinInfoDTO.setFromUrl(applicationInternetAddress);
		
		//放入采购品目
		if(bulletin.getProject() == null){
			logger.error("公告编号" + bulletinNo + "未与项目关联!");
		}else if(bulletin.getProject().getParentId() == null){
			bulletinInfoDTO.setPurCategoryNos(project.getPurCategoryIds());
			bulletinInfoDTO.setPurCategoryNames(project.getPurCategoryNames());
		}else{
			for(Project subProject : subProjects){
				if(bulletinInfoDTO.getPurCategoryNos() == null){
					bulletinInfoDTO.setPurCategoryNos(subProject.getPurCategoryIds());
					bulletinInfoDTO.setPurCategoryNames(subProject.getPurCategoryNames());
				}else{
					bulletinInfoDTO.setPurCategoryNos(bulletinInfoDTO.getPurCategoryNos() + "," + subProject.getPurCategoryIds());
					bulletinInfoDTO.setPurCategoryNames(bulletinInfoDTO.getPurCategoryNames() + "," + subProject.getPurCategoryNames());
				}
			}
		}

		bulletinInfoDTO.setPurAreaNos("");
		bulletinInfoDTO.setPurAreaNames("");
		bulletinInfoDTO.setHandler(project.getManager().getName());
		
		//放入预算
		if(bulletin.getProject() == null){
			logger.error("公告编号" + bulletinNo + "未与项目关联!");
		}else if(bulletin.getProject().getParentId() == null){
			bulletinInfoDTO.setBudget(projectService.getSumTaskPlanDetailProjectId(bulletin.getProject().getObjId()));
		}else{
			bulletinInfoDTO.setBudget(projectService.getSumTaskPlanDetailProjectId(bulletin.getProject().getParentId()));
		}
		bulletinInfoDTO.setPrjStatus(project.getProjProcessStatusCN());
		
		//设置报名状态
		if(project != null  && user != null){
			if(signUprecordService.getSignUprecordBySupplierId(project.getObjId(), user) != null){
				bulletinInfoDTO.setApplyStatus("Y");
			}else{
				bulletinInfoDTO.setApplyStatus("N");
			}
		}
		
		//设置投标状态
		if(project != null && user != null){
			if(bidService.getBidListByProjectIdAndUserId(project.getObjId(), user) != null){
				bulletinInfoDTO.setTenderStatus("Y");
				}else{
				bulletinInfoDTO.setTenderStatus("N");	
				}
		}
		bulletinInfoDTO.setLinker(project.getManager().getName());
		bulletinInfoDTO.setLinkTel(project.getAgencies().getCompany().getTel());
		bulletinInfoDTO.setLinkAddress(project.getAgencies().getCompany().getAddress());
		bulletinInfoDTO.setLinkId("");
		bulletinInfoDTO.setPrjSummary(project.getContent());
		
		/*放入是否分包*/
		bulletinInfoDTO.setIsPack(String.valueOf(projectService.getIsExistSubProject(project.getObjId())?"Y":"N"));
		
		String applyType = messageSource.getMessage("applyType");
		String applyUrl = "";
		if(applyType.equals("01")){ //报名方式，00 只支持线下报名 01支持平台页面报名 02支持客户端接口报名。
			applyUrl = applicationInternetAddress + "/SignUprecordExtController.do?method=toIndexPage&projectId=" + project.getObjId();
		}else if(applyType.equals("02")){
			applyUrl = applicationInternetAddress+"/CommonHttpService.do";
		}
		//设置报名地址
		bulletinInfoDTO.setApplyUrl(applyUrl);
		//设置下载采购文件地址
		bulletinInfoDTO.setDownUrl(applicationInternetAddress + "/ProjectPurchaseFileHttpService.do");
		//设置投标地址
		bulletinInfoDTO.setTenderUrl(applicationInternetAddress +"/BidHttpService.do");
		//投标文件地址
		bulletinInfoDTO.setTenderFileUrl(applicationInternetAddress + "/UploadFileHttpServlet");
		//登录地址
		bulletinInfoDTO.setLoginServiceUrl(applicationInternetAddress +"/LoginHttpService.do");
		//公共服务地址
		bulletinInfoDTO.setCommonServerUrl(applicationInternetAddress+"/CommonHttpService.do");
		//开标访问地址
		bulletinInfoDTO.setOpenBidUrl(applicationInternetAddress+"/services/openBidSoapService?wsdl");
		bulletinInfoDTO.setApplyType(applyType);
		return bulletinInfoDTO;
	}
	
	
	/**
	 * Description :  获取公告关联的包组信息
	 * @param bulletinNo 公告编号
	 * @param userId 用户Id
	 * @param contextPath 应用上下文
	 * @return
	 * @author <a href="mailto:zhouzh@gpcsoft.com">zhouzhanghe</a>
	 * @since 2011-3-14 14:38
	 */
	public List<PackDTO> getPackDTO(String bulletinNo,String userId) throws Exception{
		List<PackDTO> packDTOList = new ArrayList<PackDTO>();
		
		Bulletin bulletin = bulletinManager.getBulletinByBulletinNo(bulletinNo);
		User user = null;
		if(userId != null){
			user = userApiManager.getUserByObjId(userId);
		}
		if(user != null){
			user.setOrgInfo(userApiManager.getOrgInfoByUserId(user.getObjId()));
		}
			
		
		//查找公告对应的项目
		Project project = null;
		if(bulletin.getProject() == null){
			logger.error("公告编号" + bulletinNo + "未与项目关联!");
		}else if(bulletin.getProject().getParentId() != null){
			project = projectManager.get(bulletin.getProject().getParentId());
		}else{
			project = bulletin.getProject();
		}
		ProjectRule projectRule = projectService.getProjectRuleByProjectId(project.getObjId());
		if(project == null){
			logger.error("根据公告编号" + bulletinNo + "查找项目失败!");
		}
		
		/**
		 * 查询包组
		 */
		List<Project> packageList = new ArrayList<Project>();
		if(bulletin.getProject().getSubProject() == null || bulletin.getProject().getSubProject().size() == 0){
			packageList.add(bulletin.getProject());
		}else{
			packageList.addAll(projectManager.getSubProjectByQueryObject(bulletin.getProject().getObjId()));
		}
		if(packageList == null || packageList.size() == 0){
			logger.error("根据公告编号" + bulletinNo + "查找包组失败!");
			return packDTOList;
		}
		
		for(Project subProject : packageList){
			PackDTO packDTO = new PackDTO();
			packDTO.setPackCode(subProject.getProjCode());
			packDTO.setPackName(subProject.getProjName());
			packDTO.setStartSignInDateTime(DateUtil.format(projectRule.getSignUpSTime(),"yyyy-MM-dd HH:mm"));
			packDTO.setEndSignInDateTime(DateUtil.format(projectRule.getSignUpETime(),"yyyy-MM-dd HH:mm"));
			packDTO.setBeginSubmitBidDateTime(DateUtil.format(projectRule.getSubmitStTime(),"yyyy-MM-dd HH:mm"));
			packDTO.setEndSubmitBidDateTime(DateUtil.format(projectRule.getSubmitETime(),"yyyy-MM-dd HH:mm"));
			packDTO.setPackStatus(subProject.getProjProcessStatusCN());
			//设置报名状态
			if(project != null && user != null){
				if(signUprecordService.getSignUprecordBySupplierId(project.getObjId(), user) != null){
					packDTO.setApplyStatus("Y");
				}else{
					packDTO.setApplyStatus("N");
				}
			}
			
			//设置投标状态
			if(project != null && user != null){
				if(bidService.getBidListByProjectIdAndUserId(project.getObjId(), user) != null){
					packDTO.setTenderStatus("Y");
				}else{
					packDTO.setTenderStatus("N");
				}
			}
			packDTO.setPackSummary(subProject.getProjSummary());
			
			packDTOList.add(packDTO);
		}
		return packDTOList;
	}
}