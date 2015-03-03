/**
 * 
 */
package com.gpcsoft.pubservice.webService.webService.gxoa.ws.service.impl;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.providers.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.gpcsoft.bizplatform.agency.domain.Agency;
import com.gpcsoft.bizplatform.agency.service.AgencyService;
import com.gpcsoft.bizplatform.base.purcategory.dao.PurCategoryDao;
import com.gpcsoft.bizplatform.base.purcategory.domain.PurCategory;
import com.gpcsoft.bizplatform.buyer.domain.Buyer;
import com.gpcsoft.bizplatform.buyer.service.BuyerService;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.bizplatform.organization.service.OrgInfoService;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.DateUtil;
import com.gpcsoft.core.utils.FileUtil;
import com.gpcsoft.epp.bulletin.domain.Bulletin;
import com.gpcsoft.epp.bulletin.domain.BulletinTypeEnum;
import com.gpcsoft.epp.bulletin.service.BulletinService;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.common.domain.EbuyMethodEnum;
import com.gpcsoft.epp.common.domain.EsExceptionEnum;
import com.gpcsoft.epp.common.domain.FileVirtualPathEnum;
import com.gpcsoft.epp.common.domain.SeparationEnum;
import com.gpcsoft.epp.common.exception.EsException;
import com.gpcsoft.epp.common.utils.FileViewer;
import com.gpcsoft.epp.common.utils.ZipUtils;
import com.gpcsoft.epp.project.domain.ProjPlanStatusEnum;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.domain.ProjectEnum;
import com.gpcsoft.epp.project.domain.ProjectMTaskPlan;
import com.gpcsoft.epp.project.domain.ProjectMTaskPlanEnum;
import com.gpcsoft.epp.project.domain.ProjectPlan;
import com.gpcsoft.epp.project.domain.ProjectRule;
import com.gpcsoft.epp.project.domain.ProjectTemplateCodeEnum;
import com.gpcsoft.epp.project.domain.SubProjectMTaskPlanSub;
import com.gpcsoft.epp.project.manager.ProjectManager;
import com.gpcsoft.epp.project.manager.ProjectPlanManager;
import com.gpcsoft.epp.project.service.ProjectMTaskPlanService;
import com.gpcsoft.epp.projrule.domain.BidRoom;
import com.gpcsoft.epp.projrule.domain.MeetRoom;
import com.gpcsoft.epp.projrule.domain.ProjProcessRule;
import com.gpcsoft.epp.projrule.domain.RoomTypeEnum;
import com.gpcsoft.epp.projrule.manager.MeetRoomManager;
import com.gpcsoft.epp.projrule.service.ProjProcessRuleService;
import com.gpcsoft.epp.purchasedoc.domain.PurchaseDoc;
import com.gpcsoft.epp.purchasedoc.domain.PurchaseDocAtt;
import com.gpcsoft.epp.purchasedoc.domain.PurchaseDocEnum;
import com.gpcsoft.epp.purchasedoc.service.PurchaseDocAttService;
import com.gpcsoft.epp.purchasedoc.service.PurchaseDocService;
import com.gpcsoft.epp.taskplan.domain.TaskPlan;
import com.gpcsoft.epp.taskplan.domain.TaskPlanMSub;
import com.gpcsoft.epp.taskplan.domain.TaskPlanSub;
import com.gpcsoft.epp.taskplan.service.TaskPlanMSubService;
import com.gpcsoft.epp.taskplan.service.TaskPlanService;
import com.gpcsoft.epp.taskplan.service.TaskPlanSubService;
import com.gpcsoft.pubservice.utils.DocUtil;
import com.gpcsoft.pubservice.webService.webService.gxoa.ws.dao.ProjectExtDao;
import com.gpcsoft.pubservice.webService.webService.gxoa.ws.service.ProjectExtService;
import com.gpcsoft.srplatform.auth.dao.EmployeeDao;
import com.gpcsoft.srplatform.auth.dao.UserDao;
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.domain.Company;
import com.gpcsoft.srplatform.auth.domain.Employee;
import com.gpcsoft.srplatform.auth.domain.Organization;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.auth.service.AttachmentService;
import com.gpcsoft.srplatform.auth.service.OrganizationService;
import com.gpcsoft.srplatform.common.assist.service.FreeMarkerService;
import com.gpcsoft.srplatform.sysconfig.domain.SysConfigEnum;
import com.gpcsoft.srplatform.sysconfig.domain.SysConfigItem;
import com.gpcsoft.srplatform.sysconfig.service.SysConfigService;
import com.gpcsoft.srplatform.workPlan.domain.PlanTemplate;
import com.gpcsoft.srplatform.workPlan.domain.PlanTemplateTask;
import com.gpcsoft.srplatform.workPlan.manager.PlanTemplateManager;

/**
 * @author Administrator
 *
 */
@Service 
public class ProjectExtServiceImpl extends BaseGenericServiceImpl<Project> implements ProjectExtService {

	@Autowired(required=true)  @Qualifier("projectManagerImpl")
	private ProjectManager projectManager;
	
	@Autowired(required=true) @Qualifier("projProcessRuleServiceImpl")
	private ProjProcessRuleService projProcessRuleService;
	
	@Autowired(required=true)  @Qualifier("taskPlanServiceImpl")
	private TaskPlanService taskPlanService;
	
	@Autowired(required=true)  @Qualifier("taskPlanSubServiceImpl")
	private TaskPlanSubService taskPlanSubService;
	
	@Autowired(required=true)  @Qualifier("taskPlanMSubServiceImpl")
	private TaskPlanMSubService taskPlanMSubService;
	
	@Autowired(required=true)  @Qualifier("purCategoryDaoHibernate")
	private PurCategoryDao purCategoryDao;
	
	@Autowired(required=true)  @Qualifier("orgInfoServiceImpl")
	private OrgInfoService orgInfoService;
	
	@Autowired(required=true)  @Qualifier("buyerServiceImpl")
	private BuyerService buyerService;
	
	@Autowired(required=true)  @Qualifier("agencyServiceImpl")
	private AgencyService agentService;
	
	@Autowired(required=true)  @Qualifier("organizationServiceImpl")
	private OrganizationService organizationService;
	
	@Autowired(required=true)  @Qualifier("userDaoHibernate")
	private UserDao userDao;
	
	@Autowired(required=true)  @Qualifier("employeeDaoHibernate")
	private EmployeeDao employeeDao;
	
	@Autowired(required = true) @Qualifier("sysConfigServiceImpl")
	private SysConfigService sysConfigService;
	
	@Autowired(required=true) @Qualifier("bulletinServiceImpl")
	private BulletinService bulletinService;
	
	@Autowired(required=true) @Qualifier("meetRoomManagerImpl")
	private MeetRoomManager meetRoomManager;
	
	@Autowired(required=true) @Qualifier("projectMTaskPlanServiceImpl")
	private ProjectMTaskPlanService projectMTaskPlanService;
	
	@Autowired(required=true) @Qualifier("planTemplateManagerImpl")
	private PlanTemplateManager planTemplateManager;
	
	@Autowired(required=true) @Qualifier("projectPlanManagerImpl")
	private ProjectPlanManager projectPlanManager;
	
	@Autowired(required=true)  @Qualifier("purchaseDocServiceImpl")
	private PurchaseDocService purchaseDocService;
	
	@Autowired(required=true) @Qualifier("freeMarkerServiceImpl")
	private FreeMarkerService freeMarkerService;
	
	@Autowired(required=true) @Qualifier("attachmentServiceImpl")
	private AttachmentService attachmentService;
	
	@Autowired(required=true) @Qualifier("purchaseDocAttServiceImpl")
	private PurchaseDocAttService purchaseDocAttService;
	
	@Autowired(required=true)  @Qualifier("projectExtDaoHibernate")
	private ProjectExtDao projectExtDao;
	
	public static String PREFIX_WTDW = "wtdw_";//委托单位ID前缀
	public static String PREFIX_GXZB = "GXZB_";//员工和用户ID前缀
	
	/** 
	 * Description :  根据传入的立项xml字符串保存项目等信息
	 * Create Date: 2011-7-29上午09:00:34 by sunl  Modified Date: 2011-7-29上午09:00:34 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public String saveProjectByXml(String xml) throws Exception {
		
		Project project = getProjectByXml(xml);
		
		TaskPlan taskPlan = getTaskPlanByXml(xml);
		TaskPlanSub taskPlanSub = getTaskPlanSubByXml(xml);
		
		//保存任务书
		taskPlanService.save(taskPlan);
		
		//保存任务书明细
		taskPlanSubService.save(taskPlanSub);
		
		//保存项目
		project = projectManager.save(project);
		
		//创建项目规则
		ProjectRule rule = new ProjectRule();
		rule.setProject(project);
		projectManager.save(rule,ProjectRule.class);
		
		//保存任务书和任务书明细关联关系
		TaskPlanMSub taskPlanMSub = new TaskPlanMSub();
		taskPlanMSub.setTaskPlan(taskPlan);
		taskPlanMSub.setTaskPlanSub(taskPlanSub);
		taskPlanMSubService.save(taskPlanMSub);
		
		//保存招标项目与申报书明细的中间表
		ProjectMTaskPlan projectMTaskPlan = new ProjectMTaskPlan();
		projectMTaskPlan.setTproject(project);
		projectMTaskPlan.setTaskPlanSub(taskPlanSub.getObjId());
		projectMTaskPlan.setBuyMainBody(taskPlan.getBudget());
		projectMTaskPlan.setPurchaseId(taskPlanSub.getPurchase().getObjId());//品目Id
		projectMTaskPlan.setPurchaseName(taskPlanSub.getPurchase().getCategoryName());//品目名称
		projectMTaskPlan.setBudgetMoney(taskPlanSub.getTotalPrice());//预算总金额
		projectMTaskPlan.setQuantity(taskPlanSub.getQuantity());//数量
		projectMTaskPlan.setFromType(ProjectMTaskPlanEnum.CGSBS);//设置来源类型
		projectMTaskPlanService.save(projectMTaskPlan);
		
		return project.getObjId();
	}
	
	/** 
	 * Description :  根据传入的xml同步采购文件等信息
	 * Create Date: 2011-8-1上午09:33:40 by sunl  Modified Date: 2011-8-1上午09:33:40 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public String savePurchaseFileByXml(String xml)throws Exception {
		//解析xml根据code获取项目
		Document document = DocumentHelper.parseText(xml);
		Element rootElement =  document.getRootElement();
		Element tenderElement = rootElement.element("body").element("projectInfo");
		Element packsElement = tenderElement.element("packs");
		String projCode = tenderElement.element("projCode").getText();
		Project project = projectManager.findProjectByProjCode(projCode);
		
		if(project==null) {
			throw new Exception("没有找到编号为："+projCode+"的招标项目！");
		}
		
		//获取是否分包、文件售卖方式
		String isPack = tenderElement.element("isPack").getText();//是否分包
		String sellPackType = tenderElement.element("sellPackType").getText();//文件售卖方式（按项目1、按包件2）
		
		/**************************** 创建项目规则(是否分包、采购人是否确认采购文件)***********************************************/
		saveProjectRule(project,isPack);
		
		/********************************************* 创建分包 ***********************************************/
		if("1".equals(sellPackType)) {//按项目售卖,价格保存至项目中
			if(StringUtils.hasLength(tenderElement.elementText("bailPrice")) && !tenderElement.elementText("bailPrice").equals("null")) {
				project.setBail(BigDecimal.valueOf(Double.parseDouble(tenderElement.elementText("bailPrice"))));
			}
			if(StringUtils.hasLength(tenderElement.elementText("filePrice")) && !tenderElement.elementText("filePrice").equals("null")) {
				project.setPurDocPrice(BigDecimal.valueOf(Double.parseDouble(tenderElement.elementText("filePrice"))));
			}
		}
		
		/**包件*/
		List<Project> packList = new ArrayList<Project>();
		
		//如果按照包件售卖文件，则记录第一个包件的文件价格放到项目的招标文件中
		String fprice = "";
		
		//分包
		if("1".equals(isPack)) {
			List<Element> packs = packsElement.elements("pack");
			int i = 0;
			for (Element element : packs) {
				String packCode = element.elementText("packCode");
				String packName = element.elementText("packName");
				String bailPrice = element.elementText("bailPrice");
				String filePrice = element.elementText("filePrice");
				Project pack = new Project();
				pack.setParentId(project.getObjId());
				pack.setProjCode(packCode);
				pack.setProjName(packName);
				if("2".equals(sellPackType)) {
					if(StringUtils.hasLength(bailPrice) && !bailPrice.equals("null")) {
						pack.setBail(BigDecimal.valueOf(Double.parseDouble(bailPrice)));
					}
					if(StringUtils.hasLength(bailPrice) && !bailPrice.equals("null")) {
						pack.setPurDocPrice(BigDecimal.valueOf(Double.parseDouble(filePrice)));
					}
					
					//如果按包件售卖，则创建包件招标文件
					PurchaseDoc packDoc = new PurchaseDoc();
					packDoc.setProject(pack);
					packDoc.setKeyWord(project.getProjName()+"招标文件");
					packDoc.setContent(project.getProjName()+"招标文件");
					packDoc.setFileType(PurchaseDocEnum.PURCHASEDOC);//采购文件
					packDoc.setFilePrice(tenderElement.elementText("filePrice"));//文件价格
					packDoc.setAuditStatus(PurchaseDocEnum.PURCHASEOFFICEPASS);//采购办审核通过
					purchaseDocService.save(packDoc);
				}
				packList.add(pack);
				
				//包件与申报书条目中间对象，要通过项目完整性检查，money必须和项目预算总金额相同
				//所以第一个对象保存money就行
				SubProjectMTaskPlanSub ps = new SubProjectMTaskPlanSub();
				ps.setTproject(project);
				ps.setProject(pack);
				if(i==0) {
					ps.setMoney(project.getBudgetTotalMoney());
					fprice = filePrice;
				}
				projectManager.save(ps,SubProjectMTaskPlanSub.class);
				
				i++;
			}
		}
		//保存分包
		projectManager.save(packList);
		
		/********************************************* 创建招标文件 ***********************************************/
		PurchaseDoc purchaseDoc = new PurchaseDoc();
		purchaseDoc.setProject(project);
		purchaseDoc.setKeyWord(project.getProjName()+"招标文件");
		purchaseDoc.setContent(project.getProjName()+"招标文件");
		purchaseDoc.setFileType(PurchaseDocEnum.PURCHASEDOC);//采购文件
		purchaseDoc.setFilePrice(fprice);//文件价格
		purchaseDoc.setAuditStatus(PurchaseDocEnum.PURCHASEOFFICEPASS);//采购办审核通过
		purchaseDocService.save(purchaseDoc);
		
		/**项目规则的时间信息*/
		ProjectRule rule = projectManager.getProjectRuleByProjectId(project.getObjId());
		rule.setIsDividePack(isPack.equals("1")?true:false);//是否分包
		projectManager.save(rule,ProjectRule.class);
		
		/**下载招标文件到本地 D:/gpcsoftfile/project/*/
		if(StringUtils.hasLength(tenderElement.elementText("purchaseDocUrl"))) {
			try {
				String purchaseFilePath = messageSource.getMessage("epp.projectAttaPath")+File.separator+project.getObjId()+File.separator+CommonEnum.PUR_FILE_DIR+File.separator;
				this.downOAFileToLocal(tenderElement.elementText("purchaseDocUrl"), purchaseFilePath);
			
				//保存招标文件描述xml
				String fileS[] = FileUtil.list(purchaseFilePath);
				StringBuilder biddingDocumentXML = new StringBuilder();
				biddingDocumentXML.append("<?xml version='1.0' encoding='utf-8'?>");
				biddingDocumentXML.append("<appendices>");
				List<PurchaseDocAtt> purAttList = new ArrayList<PurchaseDocAtt>();
				List<Attachment> attList = new ArrayList<Attachment>();
				for (String string : fileS) {
					/**保存附件*/
					String relaId = UUID.randomUUID().toString();
					Attachment attachment = new Attachment();
					attachment.setRelationId(relaId);
					attachment.setFileName(string==null?"~":string);
					attachment.setViewName(string==null?"~":string);
					attachment.setPath(project.getObjId()+File.separator+CommonEnum.PUR_FILE_DIR+File.separator);
					attachment.setCreateTime(new Date());
					attachment.setUploadTime(new Date());
					attList.add(attachment);
					
					PurchaseDocAtt purAtt = new PurchaseDocAtt();
					purAtt.setPurchaseDoc(purchaseDoc);
					purAtt.setTenderId(project.getObjId());
					purAtt.setAttRaId(relaId);
					purAttList.add(purAtt);
					
					biddingDocumentXML.append("<appendix name='"+string+"' docId='"+string+"' fileName='"+string+"' type='none' packCode=''/>");
				}
				biddingDocumentXML.append("</appendices>");
				FileUtil.writerFile(purchaseFilePath+"BiddingAppendix.xml",biddingDocumentXML.toString(),"UTF-8");
				
				attachmentService.save(attList);
				purchaseDocAttService.save(purAttList);//保存招标文件附件业务表
			}catch (Exception ce) {
				throw new Exception("在下载招标文件时出错，项目编号："+projCode+"错误原因："+ce.getMessage());
			}
		}
		
		return project.getObjId();
	}
	
	/** 
	 * Description :  保存项目规则数据
	 * Create Date: 2011-8-1上午10:31:34 by sunl  Modified Date: 2011-8-1上午10:31:34 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void saveProjectRule(Project project,String ispack) throws Exception {
		List<ProjProcessRule> ruleList = new ArrayList<ProjProcessRule>();//项目规则
		ProjProcessRule rule_isPack = new ProjProcessRule();//项目规则-是否分包
		ProjProcessRule rule_isConfirm = new ProjProcessRule();//项目规则-是否采购人确认
		
		//查询Boolean类型的系统配置条目
		SysConfigItem isPack = sysConfigService.getSysConfigItemByCode(SysConfigEnum.PUBLEOPENBID+SeparationEnum.SYS_CONFIG_STRING_DELIMITER_B+SysConfigEnum.PROJRULE+SeparationEnum.SYS_CONFIG_STRING_DELIMITER_A+SysConfigEnum.SUBPROJECT);
		SysConfigItem isConfirm = sysConfigService.getSysConfigItemByCode(SysConfigEnum.PUBLEOPENBID+SeparationEnum.SYS_CONFIG_STRING_DELIMITER_B+SysConfigEnum.PROJRULE+SeparationEnum.SYS_CONFIG_STRING_DELIMITER_A+SysConfigEnum.NEEDBUYERACKPURCHASEDOC);
		
		//设置是否分包规则
		rule_isPack.setProject(project);
		rule_isPack.setSysItemId(isPack.getObjId());
		rule_isPack.setSysItemName(isPack.getName());
		if("1".equals(ispack)) {//分包
			rule_isPack.setResValue("true");
		} else {
			rule_isPack.setResValue("false");
		}
		rule_isPack.setCode(isPack.getCode());
		ruleList.add(rule_isPack);
		
		//设置是否采购人确认采购文件规则
		rule_isConfirm.setProject(project);
		rule_isConfirm.setSysItemId(isConfirm.getObjId());
		rule_isConfirm.setSysItemName(isConfirm.getName());
		rule_isConfirm.setResValue("false");//默认不确认
		rule_isConfirm.setCode(isConfirm.getCode());
		ruleList.add(rule_isConfirm);
		
		projProcessRuleService.save(ruleList);
	}
	
	/** 
	 * Description :  根据传入的xml同步采购公告等信息
	 * Create Date: 2011-8-1上午09:33:40 by sunl  Modified Date: 2011-8-1上午09:33:40 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public String saveBulletinByXml(String xml)throws Exception {
		Document document = DocumentHelper.parseText(xml);
		Element element =  document.getRootElement();
		Element tenderElement = element.element("body").element("projectInfo");
		String projCode = tenderElement.element("projCode").getText();
		Project project = projectManager.findProjectByProjCode(projCode);
		
		if(project==null) {
			throw new Exception("没有找到编号为："+projCode+"的招标项目！");
		}
		
		/**公告信息*/
		Bulletin bulletin = new Bulletin();
		bulletin.setBulletinNo(bulletinService.createBulletinCodeByQO(null, project.getEbuyMethod()));//公告编号
		bulletin.setProject(project);
		bulletin.setBullTitle(tenderElement.elementText("bulletinTitle"));//公告标题
		bulletin.setBullContents(tenderElement.elementText("bulletinContent"));//公告内容
		bulletin.setBullType(BulletinTypeEnum.PURCHASE_BULLETIN);//公告类型--招标公告
		
		if(StringUtils.hasLength(tenderElement.elementText("signStartTime"))) {
			bulletin.setSignUpSTime(DateUtil.parse(tenderElement.elementText("signStartTime")));
		} else {
			bulletin.setSignUpSTime(new Date());
		}
		if(StringUtils.hasLength(tenderElement.elementText("signEndTime")))
			bulletin.setSignUpETime(DateUtil.parse(tenderElement.elementText("signEndTime")));
		if(StringUtils.hasLength(tenderElement.elementText("bidStartTime")))
			bulletin.setTenderStartTime(DateUtil.parse(tenderElement.elementText("bidStartTime")));
		if(StringUtils.hasLength(tenderElement.elementText("bidEndTime")))
			bulletin.setTenderEndTime(DateUtil.parse(tenderElement.elementText("bidEndTime")));
		bulletin.setAuditStatus(CommonEnum.USER_STATUS_FORMAL);//审核通过
		bulletin.setUseStatus(CommonEnum.USER_STATUS_FORMAL);//审核通过
		bulletinService.save(bulletin);
		
		/**更新项目规则的时间地点信息*/
		ProjectRule rule = projectManager.getProjectRuleByProjectId(project.getObjId());
		if(rule == null) {
			rule = new ProjectRule();
		}
		
		Date date = null;
		try {
			if(StringUtils.hasLength(tenderElement.elementText("signStartTime"))) {
				rule.setSignUpSTime(DateUtil.parse(tenderElement.elementText("signStartTime")));
			} else {
				rule.setSignUpSTime(new Date());
			}
			if(StringUtils.hasLength(tenderElement.elementText("signEndTime")))
				rule.setSignUpETime(DateUtil.parse(tenderElement.elementText("signEndTime")));
			if(StringUtils.hasLength(tenderElement.elementText("bidStartTime")))
				date = DateUtil.parse(tenderElement.elementText("bidStartTime"), "yyyy-MM-dd hh:MM:ss");
				rule.setSubmitStTime(date);
			if(StringUtils.hasLength(tenderElement.elementText("bidEndTime")))
				rule.setSubmitETime(DateUtil.parse(tenderElement.elementText("bidEndTime")));
			if(StringUtils.hasLength(tenderElement.elementText("openBidTime")))
				rule.setOpenBidSDate(DateUtil.parse(tenderElement.elementText("openBidTime")));
			rule.setOpenBidAddr(tenderElement.elementText("meetRoomAddress"));//开标地点
			projectManager.save(rule, ProjectRule.class);
		} catch(Exception ce) {
			throw new Exception(ce.getMessage());
		}
		
		/**保存评标室*/
		MeetRoom meetRoom = new MeetRoom();
		meetRoom.setMeetRoomAddress(tenderElement.elementText("meetRoomAddress"));
		meetRoom.setMeetRoomName(tenderElement.elementText("meetRoomAddress"));
		meetRoom.setRoomType(RoomTypeEnum.BID_ROOM);//评标室
		meetRoomManager.save(meetRoom);
		
		/**保存开标室*/
		MeetRoom openBidRoom = new MeetRoom();
		openBidRoom.setMeetRoomAddress(tenderElement.elementText("meetRoomAddress"));
		openBidRoom.setMeetRoomName(tenderElement.elementText("meetRoomAddress"));
		openBidRoom.setRoomType(RoomTypeEnum.OPEN_BID_ROOM);//开标室
		meetRoomManager.save(openBidRoom);
		
		/**保存预订开评标室*/
		BidRoom meetRoomReview = new BidRoom();
		meetRoomReview.setBidRoomAddress(tenderElement.elementText("meetRoomAddress"));
		meetRoomReview.setBidRoomName(tenderElement.elementText("meetRoomAddress"));
		meetRoomReview.setMeetRoom(meetRoom);
		meetRoomReview.setProject(project);
		if(project.getParentId() != null){//若还有父级，则表示拿到的是包组，需要获得招标项目ID
			meetRoomReview.setParentBizId(project.getObjId());
		}
		meetRoomManager.savePresetTime(meetRoomReview);
		
		BidRoom openBidRoomReview = new BidRoom();
		openBidRoomReview.setBidRoomAddress(tenderElement.elementText("meetRoomAddress"));
		openBidRoomReview.setBidRoomName(tenderElement.elementText("meetRoomAddress"));
		openBidRoomReview.setMeetRoom(openBidRoom);
		openBidRoomReview.setProject(project);
		if(project.getParentId() != null){//若还有父级，则表示拿到的是包组，需要获得招标项目ID
			openBidRoomReview.setParentBizId(project.getObjId());
		}
		meetRoomManager.savePresetTime(openBidRoomReview);
		
		//下载公告附件并读取公告内容到公告
		//this.readBulletinFile(tenderElement.elementText("bulletinUrl"), project, bulletin);
		
		//保存招标公告(把XML中公告内容保存到附件中)
		this.saveBulletinFile(bulletin);
		
		return project.getObjId();
	}
	
	/** 
	 * Description :  同步澄清公告
	 * Create Date: 2011-10-24下午02:47:56 by sunl  Modified Date: 2011-10-24下午02:47:56 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public String saveChengQingBulletinByXml(String xml)throws Exception {
		Document document = DocumentHelper.parseText(xml);
		Element element =  document.getRootElement();
		Element tenderElement = element.element("body").element("projectInfo");
		String projCode = tenderElement.element("projCode").getText();
		Project project = projectManager.findProjectByProjCode(projCode);
		
		if(project==null) {
			throw new Exception("没有找到编号为："+projCode+"的招标项目！");
		}
		
		/**公告信息*/
		Bulletin bulletin = new Bulletin();
		bulletin.setBulletinNo(bulletinService.createBulletinCodeByQO(null, project.getEbuyMethod()));//公告编号
		bulletin.setProject(project);
		bulletin.setBullTitle(tenderElement.elementText("bulletinTitle"));//公告标题
		bulletin.setBullContents(tenderElement.elementText("bulletinContent"));//公告内容
		bulletin.setBullType(BulletinTypeEnum.VARIATION_BULLETIN);//公告类型--澄清公告
		
		if(StringUtils.hasLength(tenderElement.elementText("signStartTime"))) {
			bulletin.setSignUpSTime(DateUtil.parse(tenderElement.elementText("signStartTime")));
		} else {
			bulletin.setSignUpSTime(new Date());
		}
		if(StringUtils.hasLength(tenderElement.elementText("signEndTime")))
			bulletin.setSignUpETime(DateUtil.parse(tenderElement.elementText("signEndTime")));
		if(StringUtils.hasLength(tenderElement.elementText("bidStartTime")))
			bulletin.setTenderStartTime(DateUtil.parse(tenderElement.elementText("bidStartTime")));
		if(StringUtils.hasLength(tenderElement.elementText("bidEndTime")))
			bulletin.setTenderEndTime(DateUtil.parse(tenderElement.elementText("bidEndTime")));
		bulletin.setAuditStatus(CommonEnum.USER_STATUS_FORMAL);//审核通过
		bulletin.setUseStatus(CommonEnum.USER_STATUS_FORMAL);//审核通过
		bulletinService.save(bulletin);
		
		//下载公告附件并读取公告内容到公告
		this.readBulletinFile(tenderElement.elementText("bulletinUrl"), project, bulletin);
		
		return project.getObjId();
	}
	
	/** 
	 * Description :  同步采购单位
	 * Create Date: 2011-10-24下午04:59:37 by sunl  Modified Date: 2011-10-24下午04:59:37 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void saveBuyerByXml(String xml) throws Exception {
		Document document = DocumentHelper.parseText(xml);
		Element element =  document.getRootElement();
		Element buyerEle = element.element("body").element("buyerInfo");
		
		if(!StringUtils.hasLength(buyerEle.elementText("buyerId")) || "null".equals(buyerEle.elementText("buyerId"))) {
			throw new Exception("buyerId为空！");
		}
		
		/**保存auth_orgnization（组织机构）信息*/
		Company org = (Company)organizationService.get(PREFIX_WTDW+buyerEle.elementText("buyerId"),Company.class);
		String saveType = "save";
		if(org == null) {
			org = new Company();
			org.setObjId(PREFIX_WTDW+buyerEle.elementText("buyerId"));
			org.setCreateTime(new Date());
		} else {
			saveType = "update";
		}
		if(StringUtils.hasLength(buyerEle.elementText("buyerName")) && !"null".equals(buyerEle.elementText("buyerName"))) {
			org.setName(buyerEle.elementText("buyerName"));
		}
		if(StringUtils.hasLength(buyerEle.elementText("telephone")) && !"null".equals(buyerEle.elementText("telephone"))) {
			org.setTel(buyerEle.elementText("telephone"));
		}
		org.setAuditStatus("2");//审核通过
		org.setStatus("1");//有效状态
		org.setType("1");//1、公司，2、部门，3、岗位
		
		orgInfoService.saveCompany(org,saveType);
		
		/**保存org_info（公司）信息*/
		OrgInfo orgInfo = orgInfoService.get(PREFIX_WTDW+buyerEle.elementText("buyerId"));
		String saveType2 = "save";
		if(orgInfo == null) {
			orgInfo = new OrgInfo();
			orgInfo.setObjId(PREFIX_WTDW+buyerEle.elementText("buyerId"));
			orgInfo.setCreateTime(new Date());
			
		} else {
			orgInfo.setUpdateTime(new Date());
			saveType2 = "update";
		}
		if(StringUtils.hasLength(buyerEle.elementText("buyerCode")) && !"null".equals(buyerEle.elementText("buyerCode"))) {
			orgInfo.setOrgCode(buyerEle.elementText("buyerCode"));
		}
		if(StringUtils.hasLength(buyerEle.elementText("buyerName")) && !"null".equals(buyerEle.elementText("buyerName"))) {
			orgInfo.setOrgName(buyerEle.elementText("buyerName"));
		}
		if(StringUtils.hasLength(buyerEle.elementText("mainProducts")) && !"null".equals(buyerEle.elementText("mainProducts"))) {
			orgInfo.setMainProducts(buyerEle.elementText("mainProducts"));
		}
		orgInfo.setEntPrpt(OrganizationEnum.ENT_GYQY);//默认全都是01
		orgInfo.setAuditStatus(OrganizationEnum.PASS_EXAM);//审核通过
		orgInfo.setUseStatus(OrganizationEnum.USE_VALID);//有效
		orgInfo.setCompany(org);
		
		orgInfoService.saveOrgInfo(orgInfo,saveType2);
		
		/**保存buy_buyer（采购人扩展）信息*/
		Buyer buyer = buyerService.getBuyerByOrgInfoId(buyerEle.elementText("buyerId"));
		if(buyer == null) {
			buyer = new Buyer();
			buyer.setCreateTime(new Date());
		} else {
			buyer.setUpdateTime(new Date());
		}
		buyer.setOrgInfo(orgInfo);
		buyer.setAuditStatus(OrganizationEnum.PASS_EXAM);//审核通过
		buyerService.save(buyer);
		
		orgInfo.setBuyerId(buyer.getObjId());
	}
	
	/** 
	 * Description :  同步组织机构
	 * Create Date: 2011-10-24下午04:59:37 by sunl  Modified Date: 2011-10-24下午04:59:37 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void saveOrgByXml(String xml) throws Exception {
		Document document = DocumentHelper.parseText(xml);
		Element element =  document.getRootElement();
		Element orgEle = element.element("body").element("orgInfo");
		//机构类型，如果是分公司、子公司：1，如果是部门：2
		String orgType = orgEle.elementText("orgType")==null?"":orgEle.elementText("orgType");
		
		if(!StringUtils.hasLength(orgEle.elementText("orgId")) || "null".equals(orgEle.elementText("orgId"))) {
			throw new Exception("orgId为空！");
		}
		
		/**保存auth_orgnization（组织机构）信息*/
		Company org = (Company)organizationService.get(orgEle.elementText("orgId"),Company.class);
		String saveType = "save";
		if(org == null) {
			org = new Company();
			org.setObjId(orgEle.elementText("orgId"));
			org.setCreateTime(new Date());
		} else {
			saveType = "update";
		}
		if(StringUtils.hasLength(orgEle.elementText("orgName")) && !"null".equals(orgEle.elementText("orgName"))) {
			org.setName(orgEle.elementText("orgName"));
		}
		org.setAuditStatus("2");//审核通过
		org.setStatus("1");//有效状态
		org.setType(orgType);//1、公司，2、部门，3、岗位
		orgInfoService.saveCompany(org, saveType);
		
		/**保存org_info（公司）信息*/
		OrgInfo orgInfo = orgInfoService.get(orgEle.elementText("orgId"));
		String saveType2 = "save";
		if(orgInfo == null) {
			orgInfo = new OrgInfo();
			orgInfo.setObjId(orgEle.elementText("orgId"));
			orgInfo.setCreateTime(new Date());
		} else {
			orgInfo.setUpdateTime(new Date());
			saveType2 = "update";
		}
		if(StringUtils.hasLength(orgEle.elementText("orgName")) && !"null".equals(orgEle.elementText("orgName"))) {
			orgInfo.setOrgName(orgEle.elementText("orgName"));
		}
		orgInfo.setEntPrpt(OrganizationEnum.ENT_GYQY);//默认全都是01
		orgInfo.setAuditStatus(OrganizationEnum.PASS_EXAM);//审核通过
		orgInfo.setUseStatus(OrganizationEnum.USE_VALID);//有效
		orgInfo.setCompany(org);
		orgInfoService.saveOrgInfo(orgInfo, saveType2);
		
		/**如果是分公司或子公司类型，则作为代理机构*/
		if(orgType.equals("1")) {
			/**保存Agency agent（代理机构扩展）信息*/
			Agency agent = agentService.getAgentByOrgInfoId(orgEle.elementText("orgId"));
			if(agent == null) {
				agent = new Agency();
				agent.setCreateTime(new Date());
			} else {
				agent.setUpdateTime(new Date());
			}
			agent.setOrgInfo(orgInfo);
			agent.setAuditStatus(OrganizationEnum.PASS_EXAM);//审核通过
			agentService.save(agent);
		}
		/**如果是部门*/
		else if(orgType.equals("1")) {
			org.setType("2");//部门
			if(StringUtils.hasLength(orgEle.elementText("parentId")) && !"null".equals(orgEle.elementText("parentId"))) {
				Organization parent = organizationService.get(orgEle.elementText("parentId"));
				org.setParent(parent);
			}
		}
	}
	
	/** 
	 * Description :  下载公告附件并读取公告内容到公告
	 * Create Date: 2011-10-24下午02:54:14 by sunl  Modified Date: 2011-10-24下午02:54:14 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	public void readBulletinFile(String bulletinUrl,Project project,Bulletin bulletin) throws Exception {
		//公告附件路径
		String bulletinPath = messageSource.getMessage("bulletinUrl")+FileVirtualPathEnum.BULLETIN_PUR;//公告附件路径
		String fileName = "_bulletin_temp";
		
		if(StringUtils.hasLength(bulletinUrl)) {
			String backPath = "";
			try {
				/**下载公告文件到本地D:\\gpcsoftfile\\gxoa\*/
				backPath = messageSource.getMessage("gxoa.filePath")+project.getProjCode()+File.separator+bulletin.getBullType()+File.separator;
				fileName = this.downOAFileToLocal(bulletinUrl, backPath);
				if(fileName.equals("")) {
					fileName = "_bulletin_temp";
				}
			}catch (Exception ce) {
				throw new Exception("在下载公告时出错，项目编号："+project.getProjCode()+"错误原因："+ce.getMessage());
			}
			
			try {
				/*
				 * 1、将公告文件解压
				 * 2、将word文件转换成html文件
				 * 3、保存附件，并存放到D:\\gpcsoftfile\\upload\\purBulletin\\目录下
				 */
				String fileS[] = FileUtil.list(backPath);
				for (String string : fileS) {
					if(string.indexOf("公告") > -1 && string.indexOf(".rar")>-1) {
						//解压公告文件,从gxoa解压到本目录
						ZipUtils.unrar(backPath+fileName, backPath);
						//读取公告关键字的doc文件
						List fileList=FileViewer.readDirFiles(backPath);
						if(fileList!=null && fileList.size()>0) {
							for (Object object : fileList) {
								File file = (File)object;
								String string2 = file.getName();
								if(string2.indexOf("公告")>-1 && (string2.indexOf(".doc")>-1 || string2.indexOf(".docx")>-1) && string2.indexOf("~")==-1) {
									//读取公告doc内容并生成文件到purBulletin下
									String bullContent = DocUtil.getWordAndStyle(backPath+string2);
									
									File bulletinFile = new File(bulletinPath+string2); 
									//写入文件到指定路径
									if (!bulletinFile.getParentFile().exists()) {
										bulletinFile.getParentFile().mkdirs(); 
									}
									//生成文件
									String fName = java.util.UUID.randomUUID().toString().replace('-', '_');
									FileUtil.writerFile(bulletinPath+fName,bullContent,"UTF-8");
									
									//保存公告附件
									Attachment attachment = new Attachment();
									attachment.setPath(FileVirtualPathEnum.BULLETIN_PUR);//相对路径
									attachment.setViewName(bulletin.getBullTitle());
									attachment.setFileName(fName);
									attachment.setCreateTime(bulletin.getCreateTime());
									attachment.setUploadTime(bulletin.getCreateTime());
									attachmentService.save(attachment);
									if(BulletinTypeEnum.PURCHASE_BULLETIN.equals(bulletin.getBullType())) {
										bulletin.setContent(attachment);
										bulletinService.save(bulletin);
									}
								}
							}
						}
					}
				}
			}catch (Exception ce) {
				//如果解压公告出错就利用模板创建公告
				try {
					/**利用模板创建公告文件*/
					//公告附件路径
					System.out.println("========================"+bulletinPath+fileName+"===========================");
					this.createBulletinFileByTemplate(project, bulletin.getBullType(), bulletinPath+fileName);
					
					//保存公告附件
					Attachment attachment = new Attachment();
					attachment.setPath(FileVirtualPathEnum.BULLETIN_PUR);//相对路径
					attachment.setViewName(bulletin.getBullTitle());
					attachment.setFileName(fileName);
					attachment.setCreateTime(bulletin.getCreateTime());
					attachment.setUploadTime(bulletin.getCreateTime());
					attachmentService.save(attachment);
					if(BulletinTypeEnum.PURCHASE_BULLETIN.equals(bulletin.getBullType())) {
						bulletin.setContent(attachment);
						bulletinService.save(bulletin);
					}
				}catch (Exception de) {
					throw new Exception("利用模板创建公告文件时出错，项目编号："+project.getProjCode()+"错误原因："+de.getMessage());
				}
				
				throw new Exception("在解压公告并读取公告内容时出错,"+project.getProjCode()+"错误原因："+ce.getMessage());
			}
		} else {
			//如果没有上传公告附件，则利用模板生成
			this.createBulletinFileByTemplate(project, bulletin.getBullType(), bulletinPath+fileName);
			
			//保存公告附件
			Attachment attachment = new Attachment();
			attachment.setPath(FileVirtualPathEnum.BULLETIN_PUR);//相对路径
			attachment.setViewName(bulletin.getBullTitle());
			attachment.setFileName(fileName);
			attachment.setCreateTime(bulletin.getCreateTime());
			attachment.setUploadTime(bulletin.getCreateTime());
			attachmentService.save(attachment);
			if(BulletinTypeEnum.PURCHASE_BULLETIN.equals(bulletin.getBullType())) {
				bulletin.setContent(attachment);
				bulletinService.save(bulletin);
			}
		}
	}
	
	/** 
	 * Description :  保存招标公告(把XML中公告内容保存到附件中)
	 * Create Date: 2011-12-23上午09:00:58 by likg  Modified Date: 2011-12-23上午09:00:58 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	private void saveBulletinFile(Bulletin bulletin) throws Exception {
		//公告附件路径
		String bulletinPath = messageSource.getMessage("bulletinUrl")+FileVirtualPathEnum.BULLETIN_PUR;
		
		//获取公告内容
		String bullContent = bulletin.getBullContents();
		
		//把招标公告文件写到指定路径
		File bulletinFile = new File(bulletinPath);
		if (!bulletinFile.exists()) {
			bulletinFile.mkdirs();
		}
		String fName = java.util.UUID.randomUUID().toString().replace('-', '_');
		FileUtil.writerFile(bulletinPath+fName, bullContent, "UTF-8");
		
		//保存公告附件
		Attachment attachment = new Attachment();
		attachment.setPath(FileVirtualPathEnum.BULLETIN_PUR); //相对路径
		attachment.setViewName(bulletin.getBullTitle());
		attachment.setFileName(fName);
		attachment.setCreateTime(bulletin.getCreateTime());
		attachment.setUploadTime(bulletin.getCreateTime());
		attachmentService.save(attachment);
		if(BulletinTypeEnum.PURCHASE_BULLETIN.equals(bulletin.getBullType())) {
			bulletin.setContent(attachment);
			bulletinService.save(bulletin);
		}
		
	}
	
	/** 
	 * Description :  根据xml内容解析项目对象
	 * Create Date: 2011-7-29上午08:56:22 by sunl  Modified Date: 2011-7-29上午08:56:22 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Project getProjectByXml(String xml) throws Exception {
		Document document = DocumentHelper.parseText(xml);
		Element element =  document.getRootElement();
		Element headerElement = element.element("header");
		Element tenderElement = element.element("body").element("projectInfo");
		String orgId = headerElement.element("pmOrgId").getText();
		Organization org = (Organization)orgInfoService.get(orgId, Organization.class);
		OrgInfo agency = null;
		if(org != null) {
			//如果是部门，则获取上级
			if("2".equals(org.getType())) {
				Organization parent = org.getParent();
				//如果部门下还有部门，就获取最上级机构作为代理机构
				if(parent.getParent() != null && StringUtils.hasLength(parent.getParent().getObjId())) {
					agency = orgInfoService.getLastedOrgInfo(parent.getParent().getObjId(), false);
				} else {
					agency = orgInfoService.getLastedOrgInfo(parent.getObjId(), false);
				}
			} else {
				agency = orgInfoService.get(orgId);//代理机构ID
			}
		} else {
			agency = orgInfoService.get(orgId);//代理机构ID
		}
		if(agency==null) {
			throw new Exception("数据库中没有ID为："+headerElement.element("pmOrgId").getText()+"的代理机构！");
		}
		OrgInfo buyer = orgInfoService.get(PREFIX_WTDW+tenderElement.element("buyerId").getText());//委托单位ID
		if(buyer==null) {
			throw new Exception("数据库中没有ID为："+tenderElement.element("buyerId").getText()+"的招标单位！");
		}
		
		PurCategory purcategory = purCategoryDao.get(tenderElement.element("categoryCode").getText());//招标类型
		
		Project project = new Project();
		project.setUseStatus(CommonEnum.USER_STATUS_FORMAL);//正式
		project.setProjCode(tenderElement.element("projCode").getText());
		project.setProjName(tenderElement.element("projName").getText());
		project.setEbuyMethod(tenderElement.element("ebuyMethod").getText());
		project.setContent(tenderElement.element("content").getText());
		project.setBudgetTotalMoney(BigDecimal.valueOf(Double.parseDouble(tenderElement.element("totalBudget").getText())));
		project.setPurCategoryIds(purcategory.getCategoryCode());
		project.setPurCategoryNames(purcategory.getCategoryName());
		project.setBuyersId(buyer.getObjId());//委托单位
		project.setBuyersName(buyer.getOrgName());//单位名称
		project.setCreateTime(DateUtil.parse(tenderElement.element("createDate").getText()));//立项日期
		project.setTenderType(ProjectEnum.ZFCG);//项目类型-政府采购
		project.setSysFlag(CommonEnum._SYS_FLAG);//系统
		project.setAgencies(agency);
		project.setRedundancy1(tenderElement.element("department").getText());//项目经理所在部门ID
		Employee manager = employeeDao.get(PREFIX_GXZB+headerElement.element("pmUserId").getText());//项目经理
		if(manager==null) {
			throw new Exception("数据库中没有ID为："+headerElement.element("pmUserId").getText()+"的项目经理！");
		} else {
			User user = userDao.getUserByUsName(manager.getName()); 
			project.setCreateUser(user);
		}
		project.setManager(manager);//项目经理
		return project;
	}
	
	/** 
	 * Description :  根据xml内容解析taskPlan对象
	 * Create Date: 2011-7-29上午08:56:22 by sunl  Modified Date: 2011-7-29上午08:56:22 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public TaskPlan getTaskPlanByXml(String xml) throws Exception {
		Document document = DocumentHelper.parseText(xml);
		Element element =  document.getRootElement();
		Element headerElement = element.element("header");
		Element tenderElement = element.element("body").element("projectInfo");
		TaskPlan taskPlan = new TaskPlan();
		taskPlan.setBudgetLinker(tenderElement.element("buyerLinkerName").getText());
		taskPlan.setBudgetLinkerTel(tenderElement.element("buyerLinkerPhone").getText());
		if(StringUtils.hasLength(tenderElement.element("entrustDate").getText())) {
			taskPlan.setApplyDate(DateUtil.parse(tenderElement.element("entrustDate").getText()));
		}
		taskPlan.setAmt(BigDecimal.valueOf(Double.parseDouble(tenderElement.element("totalBudget").getText())));
		
		Employee leader = employeeDao.get(PREFIX_GXZB+headerElement.element("pmUserId").getText());//项目经理
		OrgInfo buyer = orgInfoService.get(PREFIX_WTDW+tenderElement.element("buyerId").getText());
		if(leader==null) {
			throw new Exception("数据库中没有ID为："+headerElement.element("pmUserId").getText()+"的员工！");
		}
		if(buyer==null) {
			throw new Exception("数据库中没有ID为："+headerElement.element("pmOrgId").getText()+"的委托单位！");
		}
		taskPlan.setLeader(leader);//负责人
		taskPlan.setBudget(buyer);//预算单位
		taskPlan.setBudgetName(buyer.getOrgName());
		return taskPlan;
	}
	
	/** 
	 * Description :  根据xml内容解析taskPlanSub对象
	 * Create Date: 2011-7-29上午08:56:22 by sunl  Modified Date: 2011-7-29上午08:56:22 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public TaskPlanSub getTaskPlanSubByXml(String xml) throws Exception {
		Document document = DocumentHelper.parseText(xml);
		Element element =  document.getRootElement();
		Element tenderElement = element.element("body").element("projectInfo");
		TaskPlanSub taskplanSub = new TaskPlanSub();
		PurCategory purcategory = purCategoryDao.get(tenderElement.element("categoryCode").getText());
		taskplanSub.setPurchase(purcategory);
		taskplanSub.setPurchaseName(purcategory.getCategoryName());
		OrgInfo buyer = orgInfoService.get(PREFIX_WTDW+tenderElement.element("buyerId").getText());//委托单位ID
		if(buyer==null) {
			throw new Exception("数据库中没有ID为："+tenderElement.element("buyerId").getText()+"的委托单位！");
		}
		taskplanSub.setBudgetName(buyer.getOrgName());
		taskplanSub.setQuantity(new BigDecimal(1));
		taskplanSub.setTotalPrice(new BigDecimal(1));
		return taskplanSub;
	}
	
	/** 
	 * Description :  保存xml信息
	 * Create Date: 2011-7-29上午08:56:22 by sunl  Modified Date: 2011-7-29上午08:56:22 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void saveXml(String projCode, String syncMethod, String xmlContent) throws Exception {
		String filePath = messageSource.getMessage("gxoa.filePath");
		String path=(filePath+projCode+File.separator).replace("/", File.separator);
		String[] fileArray = FileUtil.list(path);
		int i = 0;
		if(fileArray != null) {
			i = fileArray.length + 1;
		}
		String fileName = syncMethod+i+".xml";
		File file = new File(path+fileName); 
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs(); 
		}
		FileUtil.writerFile(path+fileName, xmlContent, "UTF-8");
	}
	
	/** 
	 * Description :  保存结果信息
	 * Create Date: 2011-7-29上午08:56:22 by sunl  Modified Date: 2011-7-29上午08:56:22 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void saveResult(String projCode, String fileName, String xmlContent) throws Exception {
		String filePath = messageSource.getMessage("gxoa.filePath");
		String path=(filePath+projCode+File.separator).replace("/", File.separator);
		String fName = fileName+".xml";
		File file = new File(path+fileName); 
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs(); 
		}
		FileUtil.writerFile(path+fName, xmlContent, "UTF-8");
	}
	
	/** 
	 * Description :创建项目计划  
	 * Create Date: 2011-8-9上午11:45:20 by sunl  Modified Date: 2011-8-9上午11:45:20 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void createProjectPlan(String projectId) throws Exception{
		Project project = projectManager.get(projectId);//根据项目主键获得项目
		String buyMethod = project.getEbuyMethod();  //获得采购方式
		String tenderType = project.getTenderType();  //获取项目类型
		String code = ProjectTemplateCodeEnum.getCode(tenderType,buyMethod); 
		PlanTemplate planTemplate = null;
		planTemplate = planTemplateManager.getDefaultTemplate(code);
		if(planTemplate == null || planTemplate.getObjId() == null) {//若没有对应的工作计划，则取默认的模版
			planTemplate = planTemplateManager.getDefaultTemplate(code);
		}
		
		Date planDate = new Date();
		if(project!=null){
			if(project.getPlanStartDate() == null) {
				project.setPlanStartDate(planDate);  //同步项目的计划开始时间
			}else{
				planDate = project.getPlanStartDate();
			}
		}
		try {
			 planDate = DateUtil.addDay(planDate, 1);
			 planDate = DateUtil.parse(DateUtil.format(planDate,DateUtil.defaultPattern)+" 00:00:00",DateUtil.hour24HMSPattern);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String key = (new Date()).getTime()+"";
		Set<PlanTemplateTask> listPTT = planTemplate.getPlanTemplateTasks();
		List<ProjectPlan> listPP = new ArrayList<ProjectPlan>();
		int i = 0;
		for (PlanTemplateTask planTemplateTask : listPTT) {
			i++;
			ProjectPlan projectPlan = new ProjectPlan();
			projectPlan.setObjId(encrypterString(planTemplateTask.getObjId(),key));//设定id
			projectPlan.setName(planTemplateTask.getName()); //设定名称、编号、规则
			projectPlan.setCode(planTemplateTask.getCode());
			projectPlan.setRule(planTemplateTask.getRule());
			projectPlan.setCodeTemplate(planTemplateTask.getCodeTemplate());
			projectPlan.setBizRuleId(planTemplateTask.getBizRuleId());
			projectPlan.setBizRuleName(planTemplateTask.getBizRuleName());
			
			projectPlan.setProject(project); //设定项目、模板、模板任务
			projectPlan.setPlanTemplate(planTemplateTask.getPlanTemplate());
			projectPlan.setServiceClassName(planTemplateTask.getServiceClassName());
			projectPlan.setServiceMethodName(planTemplateTask.getServiceMethodName());
			projectPlan.setSysicon(planTemplateTask.getSysicon());
			projectPlan.setStartRuleMethod(planTemplateTask.getStartRuleMethod());
			projectPlan.setEndRuleMethod(planTemplateTask.getEndRuleMethod());
			
			//如果有上级，设定上级id
			if(planTemplateTask.getParent() != null){
				ProjectPlan parent = new ProjectPlan();
				parent.setObjId(encrypterString(planTemplateTask.getParent().getObjId(),key));
				projectPlan.setParent(parent);
				
				//设定path
				if(planTemplateTask.getParent().getPath() == null)
					projectPlan.setPath(projectPlan.getParent().getObjId());
				else{
					String path = planTemplateTask.getParent().getPath();
					String newPath = "";
					for (String id : path.split("#")) {
						newPath += encrypterString(id,key) + "#";
					}
					projectPlan.setPath(newPath + projectPlan.getParent().getObjId());
				}
				
			}
			if(planTemplateTask.getPrePlan() != null){//如果有前置任务，设定前置任务id
				ProjectPlan prePlan = new ProjectPlan();
				prePlan.setObjId(encrypterString(planTemplateTask.getPrePlan().getObjId(),key));
				projectPlan.setPrePlan(prePlan);
			}			
			if(planTemplateTask.getBackPlan() != null){//如果有back任务，设定back任务id
				ProjectPlan backPlan = new ProjectPlan();
				backPlan.setObjId(encrypterString(planTemplateTask.getBackPlan().getObjId(),key));
				projectPlan.setBackPlan(backPlan);
			}			
			projectPlan.setResource(planTemplateTask.getResource());//设定资源路径
			projectPlan.setUrl(planTemplateTask.getResource().getUrl());
			projectPlan.setRole(planTemplateTask.getRole());//设定任务角色
			
			/*如果当前计划为叶子结点*/
			if(planTemplateTask.getIsLeaf()){
				/*如果当前工作计划没有前置任务，或者说当前计划的前置任务已完成，则将当前工作工作计划的状态改为进行中*/
				if( planTemplateTask.getPrePlan() == null || ProjPlanStatusEnum.FINISH.equals(projectPlan.getPrePlan().getStatus()))
					projectPlan.setStatus(ProjPlanStatusEnum.ONGOING);//设定为进行中
				else
					projectPlan.setStatus(ProjPlanStatusEnum.NOT_START);//设定状态
			}else
				projectPlan.setStatus(ProjPlanStatusEnum.NOT_START);//设定状态
			projectPlan.setIsAutoEnd(planTemplateTask.getIsAutoEnd());//设定是否自动完成
			//设定开始时间和结束时间
			if(planTemplateTask.getIsLeaf() && planTemplateTask.getDuration().floatValue() > 0){
				projectPlan.setPlanStartDate(planDate);
//				planDate = DateUtil.addDay(planDate,planTemplateTask.getDuration().intValue()-1);
				planDate = new Date(planDate.getTime()+ (planTemplateTask.getDuration().multiply(new BigDecimal(86400000)).intValue()));
				projectPlan.setPlanEndDate(planDate);
			}
			//设定创建人和创建时间
			projectPlan.setCreateUser(project.getCreateUser());
			//设定是否是叶子和层级
			projectPlan.setIsLeaf(planTemplateTask.getIsLeaf());
			projectPlan.setLevel(planTemplateTask.getLevel());
			// 项目计划默认状态为有效
			projectPlan.setUseStatus(ProjPlanStatusEnum.IN_EFFECT);
			//任务编号
			projectPlan.setTaskCode(planTemplateTask.getTaskCode());
			listPP.add(projectPlan);
			//如果是最后一个节点，同步项目的计划完成时间
			if(project!=null)
				if(i == listPTT.size() && project.getPlanEndDate() == null)
					project.setPlanEndDate(projectPlan.getPlanEndDate());
		}
		//批量保存项目计划
		projectPlanManager.createProjectPlan(listPP);
	}
	
	/** 
	 * Description :修改项目计划  
	 * Create Date: 2011-8-9上午11:45:20 by sunl  Modified Date: 2011-8-9上午11:45:20 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void updateProjectPlan(String projectId,String code, String status) throws Exception{
		projectExtDao.updateProjectPlan(projectId, code, status);
	}
	
	/** 
	 * Description :修改项目状态  
	 * Create Date: 2011-8-9上午11:45:20 by sunl  Modified Date: 2011-8-9上午11:45:20 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void saveProjProcessStatus(String projectId, String status, String statusCN) throws Exception {
		//projectManager.saveProjProcessStatus(projectId, status);
		
		Project project = projectManager.get(projectId);
		if(project==null){
			throw new EsException(messageSource.getMessage(EsExceptionEnum.PROJECT_ISNOTEXISTED));
		}
		
		project.setProjProcessStatus(status);
		if(StringUtils.hasLength(statusCN)) {
			project.setProjProcessStatusCN(statusCN);
		}
		projectManager.save(project);
	}
	
	/** 
	 * FuncName:encrypterString
	 * Description :  将指定的字符串与指定的key进行加密
	 * @param   target:指定字符串,key:指定的key
	 * @return  加密后的字符串
	 * @author liangxj
	 * @Create Date: 2010-6-28下午02:59:23
	 * @Modifier   liangxj
	 * @Modified Date: 2010-6-28下午02:59:23  
	 */
	private String encrypterString(String target,String key){
		Md5PasswordEncoder md5 = new Md5PasswordEncoder();  
		String encPassword = md5.encodePassword(target, key);         
		return encPassword;
	}
	
	/** 
	 * Description :  利用模板创建公告文件
	 * Create Date: 2011-8-10下午02:46:07 by sunl  Modified Date: 2011-8-10下午02:46:07 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	private String createBulletinFileByTemplate(Project project, String bulletinType, String path) {
		String errorMsg = "";
		try {
			/**利用公告模板生成公告文件*/
			Map<String, Object> templateMap = new HashMap<String, Object>();

			String title="";
			if(EbuyMethodEnum.OPEN_BIDDING.equals(project.getEbuyMethod())){
				title = "公开招标";
			}else if(EbuyMethodEnum.NEGOTIATE.equals(project.getEbuyMethod())){
				title = "竞争性谈判";
			}
			ProjectRule projectRule = projectManager.getProjectRuleByProjectId(project.getObjId());
			String totalPrice = "";
			List taskPlanSubs = projectMTaskPlanService.getAllTaskPlanSubByProject(project.getObjId());
			OrgInfo buyers = orgInfoService.get(project.getBuyersId());
			
			templateMap.put("title", title);
			templateMap.put("project", project);
			templateMap.put("projectRule", projectRule);
			templateMap.put("totalPrice", totalPrice);
			templateMap.put("today", new Date());
			templateMap.put("taskPlanSubs", taskPlanSubs);
			templateMap.put("buyer", buyers);
			templateMap.put("takeExpertRule", null);
			
			String templateName = "";//公告模板名称
			if(StringUtils.hasLength(bulletinType) && BulletinTypeEnum.PURCHASE_BULLETIN.equals(bulletinType)) {
				templateName = "publicBiddingBulletin.ftl";//采购公告
			} else if(StringUtils.hasLength(bulletinType) && BulletinTypeEnum.VARIATION_BULLETIN.equals(bulletinType)) {
				templateName = "variationBulletin.ftl";//更正公告
			}
			
			String  contentsString = freeMarkerService.getFreeMarkerContent(templateName, templateMap);
			//生成文件
			FileUtil.writerFile(path,contentsString,"UTF-8");
		} catch (Exception ce) {
			errorMsg = ce.getMessage();
		}
		return errorMsg;
	}
	
	/**  
	 * Description :  从OA给定url下载招标文件或公告，并保存至本地
	 * Create Date: 2011-8-10下午02:46:07 by sunl  Modified Date: 2011-8-10下午02:46:07 by sunl
	 * @param   downUrl下载链接，filePath保存本地路径
	 * @return  成功则返回文件名称
	 * @Exception   
	 */
	private String downOAFileToLocal(String downUrl, String filePath) throws Exception{
		String fileName = "temp.zip";
		try {
			//对方传入的文件可能有多个，以逗号分隔
			String files[] = downUrl.split(",");
			for (int i=0;i<files.length;i++) {
				URL url = new URL(files[i]);
		        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		        if (connection.getResponseCode() == 200) {  
					  String file = connection.getURL().getFile();  
					  fileName = file.substring(file.lastIndexOf('=')+1)+".rar";
				}  
		        DataInputStream in = new DataInputStream(connection.getInputStream());
		        
		        File file = null;
		        
		        //公告文件名称中有&字符串不变xml解析器解析，需转换
	        	file = new File(filePath + fileName.replace("---", "&")); 
		        
				if (!file.getParentFile().exists()) {
					file.getParentFile().mkdirs(); 
				}
				
		        DataOutputStream out = new DataOutputStream(new FileOutputStream(file));
		        byte[] buffer = new byte[4096];
		        int count = 0;
		        while ((count = in.read(buffer)) > 0) {
		          out.write(buffer, 0, count);
		        }
		        
		        out.close();
		        in.close();
			}
		}catch (Exception ce) {
			throw new Exception(ce.getMessage());
		}
		return fileName.replace("---", "&");
	}
	
	/** 
	 * Description :清理立项数据  
	 * Create Date: 2011-8-9上午11:45:20 by sunl  Modified Date: 2011-8-9上午11:45:20 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void removeStepProject(String projCode) throws Exception {
		projectExtDao.removeStepProject(projCode);
	}
	
	/** 
	 * Description :清理招标文件环节的所有数据  
	 * Create Date: 2011-8-9上午11:45:20 by sunl  Modified Date: 2011-8-9上午11:45:20 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void removeStepPurchaseFile(String projCode) throws Exception {
		projectExtDao.removeStepPurchaseFile(projCode);
	}
	
	/** 
	 * Description :清理招标公告环节的所有数据  
	 * Create Date: 2011-8-9上午11:45:20 by sunl  Modified Date: 2011-8-9上午11:45:20 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void removeStepBulletin(String projCode) throws Exception {
		projectExtDao.removeStepBulletin(projCode);
	}
	
	/** 
	 * Description :  根据项目编号清理澄清公告
	 * Create Date: 2011-10-24下午02:10:12 by sunl  Modified Date: 2011-10-24下午02:10:12 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public void removeBulletinByProjCode(String projCode, String bulletinType) throws Exception {
		projectExtDao.removeBulletinByProjCode(projCode, bulletinType);
	}
	
	/** 
	 * Description :  删除机构（置为无效状态）
	 * Create Date: 2011-10-25上午10:21:33 by sunl  Modified Date: 2011-10-25上午10:21:33 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Integer removeOrg(String orgInfoId,String status) throws Exception{
		return projectExtDao.removeOrg(orgInfoId, status);
	}
	
	/** 
	 * Description :  根据项目ID获取招标公告Id
	 * Create Date: 2011-10-24上午11:31:43 by sunl  Modified Date: 2011-10-24上午11:31:43 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public String getBulletinByProjId(String projId) throws Exception {
		StringBuilder hql = new StringBuilder();
		hql.append("from Bulletin t where t.project.objId=?");
		List<Bulletin> bulletinList = bulletinService.findByHql(hql.toString(), new Object[]{projId});
		if(bulletinList != null && bulletinList.size()>0) {
			return bulletinList.get(0).getObjId();
		} else {
			return "";
		}
	}
}
