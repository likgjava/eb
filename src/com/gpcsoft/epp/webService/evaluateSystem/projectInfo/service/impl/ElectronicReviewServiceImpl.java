package com.gpcsoft.epp.webService.evaluateSystem.projectInfo.service.impl;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.CollectionUtils;
import com.gpcsoft.core.utils.DateUtil;
import com.gpcsoft.epp.bid.dao.BailRecordDao;
import com.gpcsoft.epp.bid.dao.BidDao;
import com.gpcsoft.epp.bid.dao.BidItemsDao;
import com.gpcsoft.epp.bid.dao.BidPackageDao;
import com.gpcsoft.epp.bid.domain.BailRecord;
import com.gpcsoft.epp.bid.domain.BailStatusEnum;
import com.gpcsoft.epp.bid.domain.Bid;
import com.gpcsoft.epp.bid.domain.BidItems;
import com.gpcsoft.epp.bid.domain.BidPackage;
import com.gpcsoft.epp.bid.manager.BidManager;
import com.gpcsoft.epp.bid.manager.BidPackageManager;
import com.gpcsoft.epp.bid.service.BidService;
import com.gpcsoft.epp.common.service.UserApiService;
import com.gpcsoft.epp.eval.dao.CongruousFactorDao;
import com.gpcsoft.epp.eval.dao.CongruousFactorTypeDao;
import com.gpcsoft.epp.eval.domain.CongruousFactor;
import com.gpcsoft.epp.eval.domain.CongruousFactorType;
import com.gpcsoft.epp.project.dao.ProjectDao;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.domain.ProjectRule;
import com.gpcsoft.epp.project.manager.ProjectManager;
import com.gpcsoft.epp.project.service.ProjectService;
import com.gpcsoft.epp.projreview.dao.EvaSellerRecordDao;
import com.gpcsoft.epp.projreview.dao.EvalBidRecordDao;
import com.gpcsoft.epp.projreview.dao.OpenBidDao;
import com.gpcsoft.epp.projreview.dao.OpenBidRecordDao;
import com.gpcsoft.epp.projreview.domain.EvaSellerRecord;
import com.gpcsoft.epp.projreview.domain.EvalBidRecord;
import com.gpcsoft.epp.projreview.domain.GenviewDefine;
import com.gpcsoft.epp.projreview.domain.OpenBid;
import com.gpcsoft.epp.projreview.domain.OpenBidGeneralVitem;
import com.gpcsoft.epp.projreview.domain.OpenBidRecord;
import com.gpcsoft.epp.projreview.service.OpenBidGeneralVitemService;
import com.gpcsoft.epp.projrule.domain.BidRoom;
import com.gpcsoft.epp.projrule.domain.RoomTypeEnum;
import com.gpcsoft.epp.projrule.manager.MeetRoomManager;
import com.gpcsoft.epp.signuprecord.dao.SignUprecordDao;
import com.gpcsoft.epp.signuprecord.domain.SignUprecord;
import com.gpcsoft.epp.webService.evaluateSystem.projectInfo.dao.ElectronicReviewDao;
import com.gpcsoft.epp.webService.evaluateSystem.projectInfo.dto.ApplyDTO;
import com.gpcsoft.epp.webService.evaluateSystem.projectInfo.dto.ProjectDTO;
import com.gpcsoft.epp.webService.evaluateSystem.projectInfo.dto.WorkMembersDTO;
import com.gpcsoft.epp.webService.evaluateSystem.projectInfo.service.ElectronicReviewService;
import com.gpcsoft.epp.workgroup.dao.WorkgMemberDao;
import com.gpcsoft.epp.workgroup.domain.WorkgMember;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.auth.manager.AttachmentManager;
import com.gpcsoft.srplatform.common.utils.CollectionUtil;
/**
 * @author shenjz
 *
 */
@Service 
public class ElectronicReviewServiceImpl extends BaseGenericServiceImpl<Project> implements ElectronicReviewService{
	
	@Autowired(required=true)  @Qualifier("electronicReviewDaoHibernate")
	private ElectronicReviewDao remoteProjectDao;
	
	@Autowired(required=true)  @Qualifier("workgMemberDaoHibernate")
	private WorkgMemberDao workgMemberDao;
	
	@Autowired(required=true)  @Qualifier("signUprecordDaoHibernate")
	private SignUprecordDao signUprecordDao;
	
	@Autowired(required=true)  @Qualifier("bidDaoHibernate")
	private BidDao bidDao;
	
	@Autowired(required=true) @Qualifier("projectManagerImpl")
	private ProjectManager projectManager;
    
	@Autowired(required=true)  @Qualifier("congruousFactorDaoHibernate")
	private CongruousFactorDao congruousFactorDao;
	
	@Autowired(required=true)  @Qualifier("projectDaoHibernate")
	private ProjectDao projectDao;
	
	@Autowired(required=true)  @Qualifier("congruousFactorTypeDaoHibernate")
	private CongruousFactorTypeDao congruousFactorTypeDao;
	
	@Autowired(required=true)  @Qualifier("bailRecordDaoHibernate")
	private BailRecordDao bailRecordDao;
	
	@Autowired(required=true)  @Qualifier("bidItemsDaoHibernate")
	private BidItemsDao bidItemsDao;
	
	@Autowired(required=true) @Qualifier("bidManagerImpl")
	private BidManager bidManager;
	
	@Autowired(required=true)  @Qualifier("evalBidRecordDaoHibernate")
	private EvalBidRecordDao evalBidRecordDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("evaSellerRecordDaoHibernate")
	private EvaSellerRecordDao evaSellerRecordDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("bidPackageDaoHibernate")
	private BidPackageDao bidPackageDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("openBidRecordDaoHibernate")
	private OpenBidRecordDao openBidRecordDaoHibernate;
	
	@Autowired(required=true) @Qualifier("bidPackageManagerImpl")
	private BidPackageManager bidPackageManager;
	
	@Autowired(required=true)  @Qualifier("projectDaoHibernate")
	private ProjectDao projectDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("openBidDaoHibernate")
	private OpenBidDao openBidDaoHibernate;
	
	@Autowired(required=true) @Qualifier("userApiServiceImpl")
	private UserApiService userApiService;
	
	@Autowired(required=true) @Qualifier("meetRoomManagerImpl")
	private MeetRoomManager meetRoomManager;
	
	@Autowired(required=false)@Qualifier("attachmentManagerImpl")
	private AttachmentManager attachmentManager;
	
	@Autowired(required=true) @Qualifier("bidServiceImpl")
	private BidService bidService;
	
	@Autowired(required=true) @Qualifier("projectServiceImpl")
	private ProjectService projectService;
	
	@Autowired(required=true) @Qualifier("openBidGeneralVitemServiceImpl")
	private OpenBidGeneralVitemService openBidGeneralVitemServiceImpl;
	
	/**
	 * FuncName: getList
	 * Description :  获取已到评审时间的项目集合(电子评审系统接口)
	 * @param 
	 * @return List<Project>
	 * @author: shenjz
	 * @Create Date:2011-3-23  上午11:23:40
	 * @Modifier: shenjz
	 * @Modified Date:2011-3-23  上午11:23:40
	 * @Modifier: zhouzhanghe
	 * @Modified Date:2011-7-8  下午3:35:45
	 * @Modifier: zhouzhanghe
	 * @Modified Date:2011-7-13  下午1:00:
	 * @Modifier: zhouzhanghe(统一取项目规则的时间)
	 * @Modified Date:2011-10-24 10:51
	 */
	public List<ProjectDTO> getList(String signUpSTime, String signUpSTime_op, String signUpETime, String signUpETime_op, String submitStTime, String submitStTime_op, String submitETime, String submitETime_op, String openBidSDate, String openBidSDate_op, String evalSTime, String evalSTime_op, String evalETime, String evalETime_op,String tenderType,String tenderType_op){
		List list =  remoteProjectDao.getList(signUpSTime, signUpSTime_op, signUpETime,signUpETime_op, submitStTime, submitStTime_op, submitETime, submitETime_op, openBidSDate, openBidSDate_op, evalSTime, evalSTime_op, evalETime, evalETime_op,tenderType, tenderType_op);
		List<ProjectDTO> list2 = new ArrayList<ProjectDTO>();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Object[] object = (Object[]) iterator.next();
			ProjectDTO p = new ProjectDTO();
			if(object[0]!=null){
				p.setTenderiId(object[0].toString());
				List<Project> subProject = projectManager.getSubProjectByQueryObject(object[0].toString());
				List<ProjectDTO> l2 = new ArrayList<ProjectDTO>();
				if(subProject.size()!=0){
					for (Iterator iterator2 = subProject.iterator(); iterator2.hasNext();) {
						Project p1 = (Project) iterator2.next();
						
						ProjectRule projectRule = projectManager.getProjectRuleByProjectId(p1.getObjId());
						
						ProjectDTO p2 = new ProjectDTO();
						p2.setTenderNo(p1.getProjCode());
						p2.setTenderiId(p1.getObjId());
						p2.setTenderName(p1.getProjName());
						p2.setTenderMethod(p1.getEbuyMethod());
						p2.setTenderContent(p1.getContent());
						p2.setTenderSummary(p1.getProjSummary());
						OrgInfo o = p1.getAgencies();
						if(o!=null){
							p2.setAgenciesId(o.getObjId());
						}
						p2.setTenderType(p1.getTenderType());
						if(p1.getBudgetTotalMoney()!=null){
							p2.setTenderBudgetTotalMoney(p1.getBudgetTotalMoney().toString());
						}
						p2.setPurcategoryIds(p1.getPurCategoryIds());
						p2.setPurcategoryNames(p1.getPurCategoryNames());
						p2.setGoodsclassIds(p1.getGoodsClassIds());
						p2.setGoodsclassNames(p1.getGoodsClassNames());
						if(object[12]!=null){
							p2.setTenderUrl(messageSource.getMessage("applicationInternetAddress")+"/ProjectPurchaseFileHttpService.do?method=getProjectPurchaseFile&prjCode="+(object[1].toString()));
						}
						p2.setEvalStartTime(DateUtil.format(projectRule.getEvalSTime(), "yyyy-MM-dd HH:mm:ss"));
						p2.setEvalEndTime(DateUtil.format(projectRule.getEvalETime(), "yyyy-MM-dd HH:mm:ss"));
						p2.setOpenBidTime(DateUtil.format(projectRule.getOpenBidSDate(), "yyyy-MM-dd HH:mm:ss"));//包组开标时间取项目开标时间 by zhouzhanghe at 2011-7-13 12:59
						l2.add(p2);
					}
					p.setSubProjectList(l2);
				}
			}
			if(object[1]!=null){
				p.setTenderNo(object[1].toString());
			}
			if(object[2]!=null){
				p.setTenderName(object[2].toString());
			}
			if(object[3]!=null){
				p.setTenderContent(object[3].toString());
			}
			if(object[4]!=null){
				p.setTenderSummary(object[4].toString());
			}
			if(object[5]!=null){
				p.setAgenciesId(object[5].toString());
			}
			if(object[6]!=null){
				p.setTenderType(object[6].toString());
			}
			if(object[7]!=null){
				p.setTenderBudgetTotalMoney(object[7].toString());
			}
			if(object[8]!=null){
				p.setPurcategoryIds(object[8].toString());
			}
			if(object[9]!=null){
				p.setPurcategoryNames(object[9].toString());
			}
			if(object[10]!=null){
				p.setGoodsclassIds(object[10].toString());
			}
			if(object[11]!=null){
				p.setGoodsclassNames(object[11].toString());
			}
			if(object[12]!=null){
				p.setTenderUrl(messageSource.getMessage("applicationInternetAddress")+"/ProjectPurchaseFileHttpService.do?method=getProjectPurchaseFile&prjCode="+(object[1].toString()));
			}
			if(object[13]!=null){
				p.setBuyerIds(object[13].toString());
			}
			if(object[14]!=null){
				p.setBuyerNames(object[14].toString());
			}
			if(object[15]!=null){
				p.setOpenBidTime(object[15].toString());
			}
			if(object[16]!=null){
				p.setOpenBidAddr(object[16].toString());
			}
			if(object[17]!=null){
				p.setEvalStartTime(object[17].toString());
			}
			if(object[18]!=null){
				p.setEvalEndTime(object[18].toString());
			}
			if(object[19]!=null){
				p.setTenderMethod(object[19].toString());
			}
			if(object[20]!=null){
				p.setAgenciesName(object[20].toString());
			}
			if(object[21]!=null){
				p.setProjManager(object[21].toString());
			}
			list2.add(p);
		}
		return list2;
	}
	/**
	 * FuncName: getCongruousFactorDTOList
	 * Description : 获取指标集合(电子评审系统接口)
	 * @param 
	 * @return List<CongruousFactorDTO>
	 * @author: shenjz
	 * @Create Date:2011-3-24  下午01:55:28
	 * @Modifier: shenjz
	 * @Modified Date:2011-3-24  下午01:55:28
	 */
	public String getCongruousFactor(String projCode,String packCode){
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		sb.append("<getCongruousFactorList  xmlns=\"http://www.gpcsoft.com\">");
		sb.append("<header>");
		try {
			Project project = projectDao.findProjectByProjCode(projCode);
			ProjectRule pr = projectDao.getProjectRuleByProjectId(project.getObjId());
			if(project!=null){
				sb.append("<prjCode>"+projCode+"</prjCode>");
				sb.append("<prjName>"+project.getProjName()+"</prjName>");
			}
			sb.append("<operTag>Y</operTag>");
			sb.append("<operDesc>成功 ！</operDesc>");
			sb.append("<operException></operException>");
			sb.append("</header>");
			sb.append("<body>");
			sb.append("<packs>");
			if(pr.getIsDividePack()==true){//分包时的情况
				if(packCode!=null&&!"".equals(packCode)){//传递项目编号,同时传递包组编号
					List<CongruousFactor> l =  congruousFactorDao.getCongruousFactorList(projCode,packCode);
					Project pack = projectDao.findProjectByProjCodeBy(projCode, packCode);
					sb.append("<pack packId=\""+pack.getObjId()+"\" packCode=\""+pack.getProjCode()+"\" packName=\""+pack.getProjName()+"\" isPack=\"true\">");
					for (Iterator<CongruousFactor> iterator = l.iterator(); iterator.hasNext();) {
							CongruousFactor congruousFactor = (CongruousFactor) iterator.next();
							sb.append("<factor >");
							sb.append("<name>"+analysisStr(congruousFactor.getFactorName())+"</name>");
							sb.append("<code>"+analysisStr(congruousFactor.getFactorCode())+"</code>");
							sb.append("<auditType>"+(null!= congruousFactor.getAuditType()?congruousFactor.getAuditType():"00")+"</auditType>");
							sb.append("<auditMethod>"+(null!= congruousFactor.getAuditMethod()?congruousFactor.getAuditMethod():"00")+"</auditMethod>");
							sb.append("<score>"+(null!= congruousFactor.getScore()?congruousFactor.getScore():"")+"</score>");
							sb.append("<scoreMin>"+(null!= congruousFactor.getMinScore()?congruousFactor.getMinScore():"")+"</scoreMin>");
							sb.append("<scoreMax>"+(null!= congruousFactor.getMaxScore()?congruousFactor.getMaxScore():"")+"</scoreMax>");
							sb.append("<rate>"+(null!= congruousFactor.getWeightCoefficient()?congruousFactor.getWeightCoefficient():"")+"</rate>");
							sb.append("<isNeed>"+analysisStr(congruousFactor.getIsNeed())+"</isNeed>");
							sb.append("<remark>"+analysisStr(congruousFactor.getRemark())+"</remark>");
							sb.append("<id>"+analysisStr(congruousFactor.getObjId())+"</id>");
							sb.append("<parentId>"+analysisStr(congruousFactor.getFactorType().getObjId())+"</parentId>");
							sb.append("<isLeaf>Y</isLeaf>");
							sb.append("<sortNo>"+analysisStr(congruousFactor.getShowNo())+"</sortNo>");
							sb.append("</factor>");
					}
					List<CongruousFactorType> congruousFactorTypeList = congruousFactorTypeDao.getCongruousFactorTypList(projCode, pack.getProjCode());
					Set<CongruousFactorType> congruousFactorTypeList2 = new HashSet<CongruousFactorType>();
					for (Iterator iterator2 = congruousFactorTypeList
							.iterator(); iterator2.hasNext();) {
						CongruousFactorType congruousFactorType = (CongruousFactorType) iterator2.next();
						congruousFactorTypeList2.add(congruousFactorType);
						this.addCongruousFactorType(congruousFactorType, congruousFactorTypeList2);
					}
					for (Iterator iterator2 = congruousFactorTypeList2
							.iterator(); iterator2.hasNext();) {
						CongruousFactorType congruousFactorType = (CongruousFactorType) iterator2.next();
						sb.append("<factor>");
						sb.append("<name>"+analysisStr(congruousFactorType.getTypeName())+"</name>");
						sb.append("<code>"+analysisStr(congruousFactorType.getTypeCode())+"</code>");
						sb.append("<auditType>"+(null!= congruousFactorType.getAuditType()?congruousFactorType.getAuditType():"00")+"</auditType>");
						sb.append("<auditMethod>"+(null!= congruousFactorType.getAuditMethod()?congruousFactorType.getAuditMethod():"00")+"</auditMethod>");
						sb.append("<score></score>");
						sb.append("<scoreMin></scoreMin>");
						sb.append("<scoreMax></scoreMax>");
						sb.append("<rate>"+(null!= congruousFactorType.getWeightCoefficient()?congruousFactorType.getWeightCoefficient():"")+"</rate>");
						sb.append("<isNeed></isNeed>");
						sb.append("<remark>"+analysisStr(congruousFactorType.getRemark())+"</remark>");
						sb.append("<id>"+analysisStr(congruousFactorType.getObjId())+"</id>");
						if(congruousFactorType.getParent()!=null){
							sb.append("<parentId>"+congruousFactorType.getParent().getObjId()+"</parentId>");
						}else{
							sb.append("<parentId></parentId>");
						}
						sb.append("<isLeaf>N</isLeaf>");
						sb.append("<sortNo>"+analysisStr(congruousFactorType.getSort())+"</sortNo>");
						sb.append("</factor>");
					}
					sb.append("</pack>");
				}else{//只传递项目编号,未传递包组编号
					List<Project> pcakList = projectDao.getSubProjectList(projCode);
					for (Iterator iterator = pcakList.iterator(); iterator
							.hasNext();) {
						Project pack = (Project) iterator.next();
						List<CongruousFactor> l =  congruousFactorDao.getCongruousFactorList(projCode,pack.getProjCode());
						sb.append("<pack packId=\""+pack.getObjId()+"\" packCode=\""+pack.getProjCode()+"\" packName=\""+pack.getProjName()+"\" isPack=\"true\">");
						for (Iterator<CongruousFactor> iterator3 = l.iterator(); iterator3.hasNext();) {
								CongruousFactor congruousFactor = (CongruousFactor) iterator3.next();
								sb.append("<factor>");
								sb.append("<name>"+analysisStr(congruousFactor.getFactorName())+"</name>");
								sb.append("<code>"+analysisStr(congruousFactor.getFactorCode())+"</code>");
								sb.append("<auditType>"+(null!= congruousFactor.getAuditType()?congruousFactor.getAuditType():"00")+"</auditType>");
								sb.append("<auditMethod>"+(null!= congruousFactor.getAuditMethod()?congruousFactor.getAuditMethod():"00")+"</auditMethod>");
								sb.append("<score>"+analysisStr(congruousFactor.getScore())+"</score>");
								sb.append("<scoreMin>"+analysisStr(congruousFactor.getMinScore())+"</scoreMin>");
								sb.append("<scoreMax>"+analysisStr(congruousFactor.getMaxScore())+"</scoreMax>");
								sb.append("<rate>"+analysisStr(congruousFactor.getWeightCoefficient())+"</rate>");
								sb.append("<isNeed>"+analysisStr(congruousFactor.getIsNeed())+"</isNeed>");
								sb.append("<remark>"+analysisStr(congruousFactor.getRemark())+"</remark>");
								sb.append("<id>"+analysisStr(congruousFactor.getObjId())+"</id>");
								sb.append("<parentId>"+analysisStr(congruousFactor.getFactorType().getObjId())+"</parentId>");
								sb.append("<isLeaf>Y</isLeaf>");
								sb.append("<sortNo>"+analysisStr(congruousFactor.getShowNo())+"</sortNo>");
								sb.append("</factor>");
						}
						List<CongruousFactorType> congruousFactorTypeList = congruousFactorTypeDao.getCongruousFactorTypList(projCode, pack.getProjCode());
						Set<CongruousFactorType> congruousFactorTypeList2 = new HashSet<CongruousFactorType>();
						for (Iterator iterator2 = congruousFactorTypeList
								.iterator(); iterator2.hasNext();) {
							CongruousFactorType congruousFactorType = (CongruousFactorType) iterator2.next();
							congruousFactorTypeList2.add(congruousFactorType);
							this.addCongruousFactorType(congruousFactorType, congruousFactorTypeList2);
						}
						for (Iterator iterator2 = congruousFactorTypeList2
								.iterator(); iterator2.hasNext();) {
							CongruousFactorType congruousFactorType = (CongruousFactorType) iterator2.next();
							sb.append("<factor>");
							sb.append("<name>"+analysisStr(congruousFactorType.getTypeName())+"</name>");
							sb.append("<code>"+analysisStr(congruousFactorType.getTypeCode())+"</code>");
							sb.append("<auditType>"+(null!= congruousFactorType.getAuditType()?congruousFactorType.getAuditType():"00")+"</auditType>");
							sb.append("<auditMethod>"+(null!= congruousFactorType.getAuditMethod()?congruousFactorType.getAuditMethod():"00")+"</auditMethod>");
							sb.append("<score></score>");
							sb.append("<scoreMin></scoreMin>");
							sb.append("<scoreMax></scoreMax>");
							sb.append("<rate>"+analysisStr(congruousFactorType.getWeightCoefficient())+"</rate>");
							sb.append("<isNeed></isNeed>");
							sb.append("<remark>"+analysisStr(congruousFactorType.getRemark())+"</remark>");
							sb.append("<id>"+analysisStr(congruousFactorType.getObjId())+"</id>");
							if(congruousFactorType.getParent()!=null){
								sb.append("<parentId>"+congruousFactorType.getParent().getObjId()+"</parentId>");
							}else{
								sb.append("<parentId></parentId>");
							}
							sb.append("<isLeaf>N</isLeaf>");
							sb.append("<sortNo>"+(congruousFactorType.getSort()==null?0:congruousFactorType.getSort())+"</sortNo>");
							sb.append("</factor>");
						}
						sb.append("</pack>");
					}
				}
			}else{//未分包的情况
				List<CongruousFactor> l =  congruousFactorDao.getCongruousFactorList(projCode,packCode);
				sb.append("<pack packId=\""+project.getObjId()+"\" packCode=\""+project.getProjCode()+"\" packName=\""+project.getProjName()+"\" isPack=\"false\">");
				for (Iterator<CongruousFactor> iterator = l.iterator(); iterator.hasNext();) {
					CongruousFactor congruousFactor = (CongruousFactor) iterator.next();
					sb.append("<factor>");
					sb.append("<name>"+analysisStr(congruousFactor.getFactorName())+"</name>");
					sb.append("<code>"+analysisStr(congruousFactor.getFactorCode())+"</code>");
					sb.append("<auditType>"+(null!= congruousFactor.getAuditType()?congruousFactor.getAuditType():"00")+"</auditType>");
					sb.append("<auditMethod>"+(null!= congruousFactor.getAuditMethod()?congruousFactor.getAuditMethod():"00")+"</auditMethod>");
					sb.append("<score>"+(congruousFactor.getScore()==null?0:congruousFactor.getScore())+"</score>");
					sb.append("<scoreMin>"+analysisStr(congruousFactor.getMinScore())+"</scoreMin>");
					sb.append("<scoreMax>"+analysisStr(congruousFactor.getMaxScore())+"</scoreMax>");
					sb.append("<rate>"+(congruousFactor.getWeightCoefficient()==null?0:congruousFactor.getWeightCoefficient())+"</rate>");
					sb.append("<isNeed>"+analysisStr(congruousFactor.getIsNeed())+"</isNeed>");
					sb.append("<remark>"+analysisStr(congruousFactor.getRemark())+"</remark>");
					sb.append("<id>"+analysisStr(congruousFactor.getObjId())+"</id>");
					sb.append("<parentId>"+analysisStr(congruousFactor.getFactorType().getObjId())+"</parentId>");
					sb.append("<isLeaf>Y</isLeaf>");
					sb.append("<sortNo>"+(congruousFactor.getShowNo()==null?0:congruousFactor.getShowNo())+"</sortNo>");
					sb.append("</factor>");
					}
					List<CongruousFactorType> congruousFactorTypeList = congruousFactorTypeDao.getCongruousFactorTypList(projCode, null);
					Set<CongruousFactorType> congruousFactorTypeList2 = new HashSet<CongruousFactorType>();
					for (Iterator iterator2 = congruousFactorTypeList
							.iterator(); iterator2.hasNext();) {
						CongruousFactorType congruousFactorType = (CongruousFactorType) iterator2.next();
						congruousFactorTypeList2.add(congruousFactorType);
						this.addCongruousFactorType(congruousFactorType, congruousFactorTypeList2);
					}
					for (Iterator iterator2 = congruousFactorTypeList2
							.iterator(); iterator2.hasNext();) {
						CongruousFactorType congruousFactorType = (CongruousFactorType) iterator2.next();
						sb.append("<factor>");
						sb.append("<name>"+analysisStr(congruousFactorType.getTypeName())+"</name>");
						sb.append("<code>"+analysisStr(congruousFactorType.getTypeCode())+"</code>");
						sb.append("<auditType>"+analysisStr(congruousFactorType.getAuditType())+"</auditType>");
						sb.append("<auditMethod>"+analysisStr(congruousFactorType.getAloneEval())+"</auditMethod>");
						sb.append("<score></score>");
						sb.append("<scoreMin></scoreMin>");
						sb.append("<scoreMax></scoreMax>");
						sb.append("<rate>"+analysisStr(congruousFactorType.getWeightCoefficient())+"</rate>");
						sb.append("<isNeed></isNeed>");
						sb.append("<remark>"+analysisStr(congruousFactorType.getRemark())+"</remark>");
						sb.append("<id>"+analysisStr(congruousFactorType.getObjId())+"</id>");
						if(congruousFactorType.getParent()!=null){
							sb.append("<parentId>"+congruousFactorType.getParent().getObjId()+"</parentId>");
						}else{
							sb.append("<parentId></parentId>");
						}
						sb.append("<isLeaf>N</isLeaf>");
						sb.append("<sortNo>"+(congruousFactorType.getSort()==null?0:congruousFactorType.getSort())+"</sortNo>");
						sb.append("</factor>");
					}
				sb.append("</pack>");
			}
		} catch (Exception e) {
			sb = new StringBuffer();
			String errorMessage = e.getMessage();
			sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
			sb.append("<getCongruousFactorList  xmlns=\"http://www.gpcsoft.com\">");
			sb.append("<header>");
			sb.append("<operTag>N</operTag>");
			sb.append("<operDesc>失败 ！</operDesc>");
			sb.append("<operException>"+(errorMessage!=null?errorMessage:"")+"</operException>");
			sb.append("</header>");
			sb.append("<body>");
			sb.append("<packs>");
		}
		sb.append("</packs>");
		sb.append("</body>");
		sb.append("</getCongruousFactorList>");
		logger.debug("ElectronicReviewServiceImpl  getCongruousFactorList start=============================================\n"+sb.toString());
		logger.debug("ElectronicReviewServiceImpl  getCongruousFactorList end=============================================\n");
		return sb.toString();
	}
	
	/**
	 * FuncName: getWorkMemberList
	 * Description :  获取评审专家集合(电子评审系统接口)
	 * @param 
	 * @return List<WorkgMember>
	 * @author: shenjz
	 * @Create Date:2011-3-24  上午09:25:02
	 * @Modifier: shenjz
	 * @Modified Date:2011-3-24  上午09:25:02
	 */
	public List<WorkMembersDTO>  getWorkMemberList(){
		StringBuffer projectids = new StringBuffer();
		List list =  null;//remoteProjectDao.getList();//TODO
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Object[] object = (Object[]) iterator.next();
			projectids.append("'"+object[0].toString()+"'");
			projectids.append(",");
		}
		List<WorkgMember> l = workgMemberDao.getWorkMemberList(projectids.substring(0, projectids.length()-1));
		List<WorkMembersDTO> l2 = new ArrayList<WorkMembersDTO>();
		for (Iterator iterator = l.iterator(); iterator.hasNext();) {
			WorkgMember workgMember = (WorkgMember) iterator.next();
			WorkMembersDTO  workMembersDTO = new WorkMembersDTO();
			workMembersDTO.setWorkgmId(workgMember.getObjId());
			if(workgMember.getSubProject()!=null){
				workMembersDTO.setSubProjId(workgMember.getSubProject().getObjId());
			}else {
				workMembersDTO.setSubProjId(workgMember.getWorkGroup().getProject().getObjId());
			}
			workMembersDTO.setWorkgmDuty(workgMember.getWorkgmDuty());
			workMembersDTO.setWorkgmLinkerPhone(workgMember.getLinkerPhone());
			workMembersDTO.setWorkgmSpeciality(workgMember.getWorkgmSpeciality());
			l2.add(workMembersDTO);
		}
		return l2;
	}
	/**
	 * FuncName: getSignUprecordList
	 * Description :  获取报名集合(电子评审系统接口)
	 *  @param tenderIdList 项目Id List
	 * @return List<SignUprecord>
	 * @author: shenjz
	 * @Create Date:2011-3-24  上午11:33:21
	 * @Modifier: shenjz
	 * @Modified Date:2011-3-24  上午11:33:21
	 */
	public List<ApplyDTO> getSignUprecordList(List<String> tenderIdList)throws Exception{
		StringBuffer projectids = new StringBuffer();
		List list =  null;//remoteProjectDao.getList(null);//TODO
		for (String str : tenderIdList) {
			projectids.append("'"+str+"'");
			projectids.append(",");
		}
		List<SignUprecord> signUpRecords = signUprecordDao.getSignUprecordList(projectids.substring(0, projectids.length()-1));
	    
		
		List<ApplyDTO> l2 = new ArrayList<ApplyDTO>();
		for (Iterator iterator = signUpRecords.iterator(); iterator.hasNext();) {
			SignUprecord signUprecord = (SignUprecord) iterator.next();
			ApplyDTO applyDTO = new ApplyDTO();
			applyDTO.setApplyId(signUprecord.getObjId());
			String projectId = signUprecord.getProject().getObjId();
			String supplierId = signUprecord.getSupplier().getObjId();
			applyDTO.setTenderId(projectId);
			applyDTO.setSupplyerId(supplierId);
			applyDTO.setSupplyerName(signUprecord.getSupplier().getOrgName());
			applyDTO.setOrgCode(signUprecord.getSupplier().getOrgCode());
			applyDTO.setApplyDate(DateUtil.format(signUprecord.getApplyDate(), DateUtil.hour24HMSPattern));
			applyDTO.setLinker(signUprecord.getLinker());
			applyDTO.setApplyUrl(messageSource.getMessage("applicationInternetAddress")+"/services/epp/evaluatesys/bidSoapService@downBidFileApp");
			applyDTO.setLinkerTel(signUprecord.getLinkerTel());
			applyDTO.setIdCard(signUprecord.getIdCard());
			applyDTO.setAddress(signUprecord.getAddress());
			applyDTO.setZipCode(signUprecord.getZipCode());
			applyDTO.setMemo(signUprecord.getMemo());
			Bid bid = bidDao.getBidBySupplierid(supplierId, projectId);
			BailRecord br =  bailRecordDao.getBailRecordBySignUprecord(signUprecord.getObjId());
			String[]prj ={projectId};
			List<BidItems> biss =  null;
			if(bid!=null){
				bidItemsDao.getBidItemsByPackIds(prj,bid.getObjId());
			}
			BidItems bis = null;
			if(biss!=null){
				bis = biss.get(0);
			}
			if(bid!=null){
				applyDTO.setTenderTime(DateUtil.format(bid.getCreateTime(), DateUtil.hour24HMSPattern));
			}
			if(bis!=null){
				applyDTO.setProjManager(bis.getProjManager());
			}
			if(br!=null){
				if(br.getBailStatus().equals(BailStatusEnum.ALREADY_RECEIVE)){
					applyDTO.setBailRecord("true");
				}else {
					applyDTO.setBailRecord("false");
				}
			}
			l2.add(applyDTO);
		}
		return l2;
	}
	/**
	 * FuncName: addCongruousFactorType
	 * Description :  递归方法获取到一个指标类型的父节点和所有超父节点
	 * @param 
	 * @param congruousFactorType
	 * @param congruousFactorTypeList
	 * @return CongruousFactorType
	 * @author: shenjz
	 * @Create Date:2011-5-18  上午11:19:25
	 * @Modifier: shenjz
	 * @Modified Date:2011-5-18  上午11:19:25
	 */
	public CongruousFactorType addCongruousFactorType(CongruousFactorType congruousFactorType,Set<CongruousFactorType> congruousFactorTypeList){
		CongruousFactorType c2 = congruousFactorType;
		while(c2.getParent()!=null){
			CongruousFactorType c3 = c2.getParent();
			c2=this.addCongruousFactorType(c3,congruousFactorTypeList);
			congruousFactorTypeList.add(c3);
		}
		return c2;
	}
	
	
	
	/**
	 * FuncName: saveOpenBidByXml
	 * Description :  根据xml文档录入开标结果对象
	 * @param 
	 * @param xml
	 * @return boolean
	 * @author: liuke
	 * @Create Date:2011-5-26  上午09:32:03
	 * @Modifier: liuke
	 * @Modified Date:2011-5-26  上午09:32:03
	 * @Modifier: zhouzhanghe
	 * @Modified Date:2011-10-18 18:14
	 */
	@SuppressWarnings("unused")
	public Project saveOpenBidByXml(String xml)throws Exception{
		Document document;
		Project project = new Project();
			document = DocumentHelper.parseText(xml);
			Element element =  document.getRootElement();
			Element tenderElement = element.element("body").element("tenderInfo");
			String projectCode = tenderElement.attributeValue("code");
			project = projectManager.findProjectByProjCode(projectCode);
			String projectName = project.getProjName();
			String projectId = project.getObjId();
			Element packElement = tenderElement.element("pack");
			Project pack = new Project();
			String packCode = packElement.attributeValue("code");
			pack = projectManager.findProjectByProjCodeAndParent(packCode,projectId);
			String packId = "";
			String packName = "";
			if(pack!=null){
				packId = pack.getObjId();
				packName = pack.getProjName();
			}else{
				packId = projectId;
				packName = projectName;
				pack = projectManager.get(projectId);
			}
			List <Element> supplierElementList = packElement.elements("supplier");
			for(Element supplierElement :supplierElementList){
				String objId = supplierElement.element("objId").getTextTrim();
				String orgCode = supplierElement.element("orgCode").getTextTrim();
				String price = supplierElement.element("price").getTextTrim();
				String ManagerName = "";
				if(supplierElement.element("projManager")!=null){
					ManagerName = supplierElement.element("projManager").getTextTrim();
				}
				String isEffective = supplierElement.element("isEffective").getTextTrim();
				
				OrgInfo supplier = userApiService.getOrgInfoByOrgCode(orgCode);
				if(supplier==null){
					supplier = new OrgInfo();
					supplier.setObjId(objId);
					supplier.setOrgCode(orgCode);
				}
				
				//拼装供应商投标记录
				List<Bid> oldBidList = bidManager.getBidListByProjectIdAndUserId(projectId, supplier.getObjId());  //根据项目与供应商机构获得投标记录
				BigDecimal  priceSum = new BigDecimal(price);
				Bid bid = new Bid();
				if(!oldBidList.isEmpty()){   //如果有投标记录，则修改
					bid = oldBidList.get(0);
				    List<BidPackage> bidpackageList = bidPackageDaoHibernate.getAllBidPackagesByBidAndNotInPack(bid.getObjId(),packId);
				    for(BidPackage bidPackage:bidpackageList){
				    	if(bidPackage.getQuotesum()!=null){ //如果投标金额不为空
				    		priceSum =	priceSum.add(new BigDecimal(bidPackage.getQuotesum()));
				    	}
				    }
				}
				bid.setSupplier(supplier);
				bid.setProjCode(projectCode);
				bid.setProjName(projectName);
				bid.setProject(project);
				bid.setProjManagerName(ManagerName);
				bid.setBidQuoteSum(priceSum);
				
				//拼装开标记录信息
				OpenBidRecord openBidRecord = null;
				openBidRecord = openBidRecordDaoHibernate.getOpenBidRecordBySupplierIdAndSubProjectId(packId, bid.getSupplier().getObjId());
				if(openBidRecord ==null){
					openBidRecord = new OpenBidRecord();
				}
				openBidRecord.setSellerName(bid.getSupplierName());
				openBidRecord.setOpenBRStatus(isEffective);
				openBidRecord.setBidId(bid.getObjId());
				openBidRecord.setSupplier(bid.getSupplier());
				openBidRecord.setQuoteSum(new BigDecimal(price));
				openBidRecord.setCreateTime(new Date());
				openBidRecord.setCreateUser(AuthenticationHelper.getCurrentUser());
				this.saveOpenBidData(bid,openBidRecord,packId,projectId,price);
				List<BidItems> bidItems = bidService.getBidItemsByPackIds(pack.getObjId().split(","), bid.getObjId());
				BidPackage bidPackage = bidPackageManager.getBidPackageByPackIdAndBidId(packId,bid.getObjId());
				for (Iterator iterator = bidItems.iterator(); iterator.hasNext();) {
					BidItems bidItems2 = (BidItems) iterator.next();
					bidItems2.setPrice(new BigDecimal(price!=null?price:"0"));
					bidItems2.setBidPackId(bidPackage!=null?bidPackage.getObjId():"");
					bidItemsDao.save(bidItems2, BidItems.class);
				}
				
				/*added by zhouzhanghe at 2011-11-19 11:20*/
				/*拼装开标一览表数据*/
				List<Element> bidFormElementList = null;
				if(supplierElement.element("bidForm") != null)
					bidFormElementList = supplierElement.element("bidForm").elements("item");
				List<OpenBidGeneralVitem> openBidGeneralVitemList = new ArrayList<OpenBidGeneralVitem>();
				if(bidFormElementList != null){
					for(Element item : bidFormElementList){
						String name = item.element("name").getTextTrim();
						String value = item.element("value").getTextTrim();
						
						OpenBidGeneralVitem openBidGeneralVitem = new OpenBidGeneralVitem();
						openBidGeneralVitem.setFactorName(name);
						openBidGeneralVitem.setRespValue(value);
						openBidGeneralVitemList.add(openBidGeneralVitem);
					}
				}
				/*如果没有传入开标一览表数据*/
				if(bidFormElementList == null || bidFormElementList.size() == 0){
					OpenBidGeneralVitem openBidGeneralVitem = new OpenBidGeneralVitem();
					openBidGeneralVitem.setFactorName("总报价");
					openBidGeneralVitem.setRespValue(priceSum.toString());
					openBidGeneralVitemList.add(openBidGeneralVitem);
				}
				openBidGeneralVitemServiceImpl.saveOpenBidGeneralVitem(openBidGeneralVitemList, packId, supplier.getObjId(), bid.getObjId(), openBidRecord.getObjId());
			}
					
		User user = AuthenticationHelper.getCurrentUser();
		if(user==null){
			user = new User(); 
			String	objId =UUID.randomUUID().toString();
			user.setObjId(objId);
		}
		project.setUser(user);
		project.setParentBizId(project.getObjId());
		return project;
	}
	
	
	
	/**
	 * FuncName: saveEvalBidRecord
	 * Description :  根据xml文档录入评标结果对象
	 * @param 
	 * @param xml
	 * @return boolean
	 * @author: liuke
	 * @Create Date:2011-5-27  上午11:32:56
	 * @Modifier: liuke
	 * @Modified Date:2011-5-27  上午11:32:56
	 */
	public Project saveEvalBidRecordByXml(String xml)throws Exception {
		Document document;
		Project project = new Project();
		document = DocumentHelper.parseText(xml);
		Element element =  document.getRootElement();
		Element tenderElement = element.element("body").element("tenderInfo");
		String projectCode = tenderElement.attributeValue("code");
		project = projectManager.findProjectByProjCode(projectCode);
		String projectId = project.getObjId();
		String projectName = project.getProjName();
		Element packElement = tenderElement.element("pack");
		String packCode = packElement.attributeValue("code");
		Project pack = new Project();
		pack = projectManager.findProjectByProjCodeAndParent(packCode,projectId);
		String packId = "";
		String packName = "";
		if(pack!=null){
			packId = pack.getObjId();
			packName = pack.getProjName();
		}else{
			packId = projectId;
			packName = projectName;
		}
		//删除评标结果数据
		evaSellerRecordDaoHibernate.removeEvaSellerRecordByPack(packId);
		evalBidRecordDaoHibernate.removeEvalBidRecordByPack(packId);
		List <Element> evalBidRecordElementList = packElement.elements("evalBidRecord");
		for(Element evalBidRecordElement:evalBidRecordElementList){
			String expertId = evalBidRecordElement.attributeValue("objId");
			String expertName = evalBidRecordElement.attributeValue("name");
			EvalBidRecord evalBidRecord = new EvalBidRecord();
			evalBidRecord.setProjId(projectId);
			evalBidRecord.setProjName(projectName);
			evalBidRecord.setProjCode(projectCode);
			evalBidRecord.setSubProjId(packId);
			evalBidRecord.setEvalLinker(expertId);
			evalBidRecord.setEvalLinkerName(expertName);
			evalBidRecordDaoHibernate.save(evalBidRecord);
			List <Element> evaSellerRecordElementList = evalBidRecordElement.elements("evaSellerRecord");
			for(Element evaSellerRecordElement:evaSellerRecordElementList){
				 String supplierId = evaSellerRecordElement.element("objId").getTextTrim();
				 String supplierCode = evaSellerRecordElement.element("orgCode").getTextTrim();
				 String factorScore =  evaSellerRecordElement.element("factorScore").getTextTrim();
				 String isRecommend =  evaSellerRecordElement.element("isRecommend").getTextTrim();
				 EvaSellerRecord evaSellerRecord = new EvaSellerRecord();    
				 evaSellerRecord.setEvalId(evalBidRecord.getObjId());
				 OrgInfo supplier = userApiService.getOrgInfoByOrgCode(supplierCode);
				 if(supplier==null){
					supplier = new OrgInfo();
					supplier.setObjId(supplierId);
					supplier.setOrgCode(supplierCode);
				 }
				 evaSellerRecord.setSupplier(supplier);
				 evaSellerRecord.setSupplierName(supplierCode);
				 evaSellerRecord.setFactorScore(factorScore);
				 evaSellerRecord.setRecommend(isRecommend);
				 evaSellerRecord.setSubProjId(packId);
				 evaSellerRecord.setSubProjCode(packName);
				 evaSellerRecord.setSubProjCode(packCode);
				 evaSellerRecordDaoHibernate.save(evaSellerRecord);            //保存开标结果对象   
			}
		}	
		User user = AuthenticationHelper.getCurrentUser();
		if(user==null){
			user = new User();
			String	objId =UUID.randomUUID().toString();
			user.setObjId(objId);
		}
		project.setUser(user);
		project.setParentBizId(project.getObjId());
		return project;
	}
	
	
	//保存开标信息
	private void saveOpenBidData(Bid bid,OpenBidRecord openBidRecord,String packId,String projId,String price){
		bidDao.save(bid);   //保存投标信息
		Project pack = new Project();
		BidPackage oldBidpackage = bidPackageManager.getBidPackageByPackIdAndBidId(packId, bid.getObjId());
		BidPackage bidPackage = new BidPackage();
		if(oldBidpackage!=null){  //如果存在则修改
			bidPackage = oldBidpackage;
		}
		pack.setObjId(packId);
		bidPackage.setPack(pack);
		bidPackage.setBid(bid);
		Float priceSum = Float.valueOf(price);
		bidPackage.setQuotesum(priceSum);
		bidPackageManager.save(bidPackage);   //保存开标信息
		Project project = projectDaoHibernate.get(projId);//得到招标项目
		Project subPack = projectDaoHibernate.get(packId);   //得到包组信息
		List<OpenBid> list = openBidDaoHibernate.getOpenBidBySubProjectId(packId);
		
		
		// 保存开标
		String openBidId="";
		if(list.isEmpty()){
			OpenBid	openBid = new OpenBid();
			openBid.setProjId(project.getObjId());
			openBid.setSubProjId(subPack.getObjId());
			openBid.setProjName(subPack.getProjName());
			openBid.setProjCode(subPack.getProjCode());
			openBidDaoHibernate.save(openBid);
			openBidId = openBid.getObjId();
		}else{
			openBidId = list.get(0).getObjId();
		}
		// 保存开标记录
		openBidRecord.setOpenBId(openBidId);
		openBidRecord.setSubProjId(subPack.getObjId());
		openBidRecordDaoHibernate.save(openBidRecord);
	}
	
	
	
	/**
	 * FuncName: getBidAttachmentByPackCodeAndOrgCode
	 * Description :  根据包件Code与机构Code获得投标文件附件对象
	 * @param 
	 * @param packCode
	 * @param orgCode
	 * @return Attachment
	 * @author: liuke
	 * @Create Date:2011-7-1  上午11:54:45
	 * @Modifier: liuke
	 * @Modified Date:2011-7-1  上午11:54:45
	 */
	public Attachment getBidAttachmentByTenderCodeAndOrgCode(String tenderCode,String packCode,String orgCode)throws Exception {
		Attachment bidAttachment = null;
		String packId = "";
		String supplierId = "";
		Project project = projectManager.findProjectByProjCode(tenderCode);
		if(project!=null){
			Project subProject = projectManager.findProjectByProjCodeAndParent(packCode, project.getObjId());
			if(subProject==null){
				packId = project.getObjId();
			}else{ 
				packId = subProject.getObjId();
			}
			OrgInfo supplier = userApiService.getOrgInfoByOrgCode(orgCode);
			if(supplier!=null){
				supplierId = supplier.getObjId();
			}
			
			List<Bid> bidList = bidManager.getBidListByProjectIdAndUserId(project.getObjId(), supplierId);
			if(bidList!=null&&!bidList.isEmpty()){
				Bid bid = bidList.get(0);
				BidPackage bidPackage = bidPackageManager.getBidPackageByPackIdAndBidId(packId, bid.getObjId()); 
				if(bidPackage!=null&&!"".equals(bidPackage.getBidFile())){
					List attachmentList = attachmentManager.getAttachmentsByRealID(bidPackage.getBidFile());
					if(attachmentList != null && !attachmentList.isEmpty()){
						bidAttachment = (Attachment)attachmentList.get(0);
					}
				}
			}
		}
		return bidAttachment;
	}
	
	public static void main(String[] args) throws IOException {
		   FileWriter fw = new FileWriter("c:\\1.txt");  
		   String s = "hello world";  
		   fw.write(s,0,s.length());  
		   fw.flush();  
		   fw.close();
		   OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("c:\\2.txt"));  
		   osw.write(s,0,s.length());  
		   osw.flush();  
		   osw.close();
	}
	//用以处理NULl值返回空字符串
	public String analysisStr(Object str){
		if(null==str){
			return "";
		}
		return str.toString();
	}
	
	
	
	
	/**
	 * FuncName: ProjectListForSimpleInfo
	 * Description :  获取项目清单
	 * @param 
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
	 * @return Project
	 * @author: liuke
	 * @Create Date:2011-9-7  上午09:52:43
	 * @Modifier: liuke
	 * @Modified Date:2011-9-7  上午09:52:43
	 */
	public List<Project> getProjectListForSimpleInfo(String signUpSTime,
			String signUpSTime_op, String signUpETime, String signUpETime_op,
			String submitStTime, String submitStTime_op, String submitETime,
			String submitETime_op, String openBidSDate, String openBidSDate_op,
			String evalSTime, String evalSTime_op, String evalETime,
			String evalETime_op, String tenderType, String tenderType_op) {
		List list =  remoteProjectDao.getList(signUpSTime, signUpSTime_op, signUpETime,signUpETime_op, submitStTime, submitStTime_op, submitETime, submitETime_op, openBidSDate, openBidSDate_op, evalSTime, evalSTime_op, evalETime, evalETime_op,tenderType, tenderType_op);
		List<Project> projectList = new ArrayList<Project>();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Object[] object = (Object[]) iterator.next();
			Project project = new Project();
			 project.setObjId((String)object[0]);	
			 project.setTenderType((String)object[6]);
			 project.setProjName((String)object[2]);
			 project.setProjCode((String)object[1]);	
			 projectList.add(project);
		}
		return projectList;
	}
	
	
	
	/**
	 * FuncName: getProjectInfoByCode
	 * Description :  创建或修改对象
	 * @param 
	 * @param prjCode
	 * @param packCodes
	 * @return List<ProjectDTO>
	 * @author: liuke
	 * @Create Date:2011-9-7  上午11:54:39
	 * @Modifier: liuke
	 * @Modified Date:2011-9-7  上午11:54:39
	 * @Modifier: zhouzhanghe(统一取项目规则的时间)
	 * @Modified Date:2011-10-24 10:51
	 */
	public List<ProjectDTO> getProjectInfoByCode(String prjCode,
			String packCodes) {
		List list =  remoteProjectDao.getListByProjCode(prjCode);
		List<ProjectDTO> list2 = new ArrayList<ProjectDTO>();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Object[] object = (Object[]) iterator.next();
			ProjectDTO p = new ProjectDTO();
			if(object[0]!=null){
				p.setTenderiId(object[0].toString());
				List<Project> subProject = projectManager.getSubProjectByQueryObject(object[0].toString());
				List<ProjectDTO> l2 = new ArrayList<ProjectDTO>();
				if(subProject.size()!=0){
					for (Iterator iterator2 = subProject.iterator(); iterator2.hasNext();) {
						Project p1 = (Project) iterator2.next();
						ProjectRule projectRule = projectService.getProjectRuleByTenderId(p1.getObjId());
						if(packCodes==null||"".equals(packCodes)){
							ProjectDTO p2 = new ProjectDTO();
							p2.setTenderNo(p1.getProjCode());
							p2.setTenderiId(p1.getObjId());
							p2.setTenderName(p1.getProjName());
							p2.setTenderMethod(p1.getEbuyMethod());
							p2.setTenderContent(p1.getContent());
							p2.setTenderSummary(p1.getProjSummary());
							OrgInfo o = p1.getAgencies();
							if(o!=null){
								p2.setAgenciesId(o.getObjId());
							}
							p2.setTenderType(p1.getTenderType());
							if(p1.getBudgetTotalMoney()!=null){
								p2.setTenderBudgetTotalMoney(p1.getBudgetTotalMoney().toString());
							}
							p2.setPurcategoryIds(p1.getPurCategoryIds());
							p2.setPurcategoryNames(p1.getPurCategoryNames());
							p2.setGoodsclassIds(p1.getGoodsClassIds());
							p2.setGoodsclassNames(p1.getGoodsClassNames());
							if(object[12]!=null){
								p2.setTenderUrl(messageSource.getMessage("applicationInternetAddress")+"/ProjectPurchaseFileHttpService.do?method=getProjectPurchaseFile&amp;prjCode="+(object[1].toString()));
							}
							p2.setEvalStartTime(DateUtil.format(projectRule.getEvalSTime(), "yyyy-MM-dd HH:mm:ss"));
							p2.setEvalEndTime(DateUtil.format(projectRule.getEvalETime(), "yyyy-MM-dd HH:mm:ss"));
							p2.setOpenBidTime(DateUtil.format(projectRule.getOpenBidSDate(), "yyyy-MM-dd HH:mm:ss"));//包组开标时间取项目开标时间 by zhouzhanghe at 2011-7-13 12:59
							l2.add(p2);
						}else if(packCodes.indexOf(p1.getProjCode())!=-1){ //包组对象在入参内
							ProjectDTO p2 = new ProjectDTO();
							p2.setTenderNo(p1.getProjCode());
							p2.setTenderiId(p1.getObjId());
							p2.setTenderName(p1.getProjName());
							p2.setTenderMethod(p1.getEbuyMethod());
							p2.setTenderContent(p1.getContent());
							p2.setTenderSummary(p1.getProjSummary());
							OrgInfo o = p1.getAgencies();
							if(o!=null){
								p2.setAgenciesId(o.getObjId());
							}
							p2.setTenderType(p1.getTenderType());
							if(p1.getBudgetTotalMoney()!=null){
								p2.setTenderBudgetTotalMoney(p1.getBudgetTotalMoney().toString());
							}
							p2.setPurcategoryIds(p1.getPurCategoryIds());
							p2.setPurcategoryNames(p1.getPurCategoryNames());
							p2.setGoodsclassIds(p1.getGoodsClassIds());
							p2.setGoodsclassNames(p1.getGoodsClassNames());
							if(object[12]!=null){
								p2.setTenderUrl(messageSource.getMessage("applicationInternetAddress")+"/ProjectPurchaseFileHttpService.do?method=getProjectPurchaseFile&amp;prjCode="+(object[1].toString()));
							}
							p2.setEvalStartTime(DateUtil.format(projectRule.getEvalSTime(), "yyyy-MM-dd HH:mm:ss"));
							p2.setEvalEndTime(DateUtil.format(projectRule.getEvalETime(), "yyyy-MM-dd HH:mm:ss"));
							p2.setOpenBidTime(DateUtil.format(projectRule.getOpenBidSDate(), "yyyy-MM-dd HH:mm:ss"));//包组开标时间取项目开标时间 by zhouzhanghe at 2011-7-13 12:59
							l2.add(p2);	
						}
					}
					p.setSubProjectList(l2);
				}else{
					l2.add(p);
					p.setSubProjectList(l2);
				}
			}
			if(object[1]!=null){
				p.setTenderNo(object[1].toString());
			}
			if(object[2]!=null){
				p.setTenderName(object[2].toString());
			}
			if(object[3]!=null){
				p.setTenderContent(object[3].toString());
			}
			if(object[4]!=null){
				p.setTenderSummary(object[4].toString());
			}
			if(object[5]!=null){
				p.setAgenciesId(object[5].toString());
			}
			if(object[6]!=null){
				p.setTenderType(object[6].toString());
			}
			if(object[7]!=null){
				p.setTenderBudgetTotalMoney(object[7].toString());
			}
			if(object[8]!=null){
				p.setPurcategoryIds(object[8].toString());
			}
			if(object[9]!=null){
				p.setPurcategoryNames(object[9].toString());
			}
			if(object[10]!=null){
				p.setGoodsclassIds(object[10].toString());
			}
			if(object[11]!=null){
				p.setGoodsclassNames(object[11].toString());
			}
			if(object[12]!=null){
				p.setTenderUrl(messageSource.getMessage("applicationInternetAddress")+"/ProjectPurchaseFileHttpService.do?method=getProjectPurchaseFile&amp;prjCode="+(object[1].toString()));
			}
			if(object[13]!=null){
				p.setBuyerIds(object[13].toString());
			}
			if(object[14]!=null){
				p.setBuyerNames(object[14].toString());
			}
			if(object[15]!=null){
				p.setOpenBidTime(object[15].toString());
			}
			if(object[16]!=null){
				p.setOpenBidAddr(object[16].toString());
			}
			if(object[17]!=null){
				p.setEvalStartTime(object[17].toString());
			}
			if(object[18]!=null){
				p.setEvalEndTime(object[18].toString());
			}
			if(object[19]!=null){
				p.setTenderMethod(object[19].toString());
			}
			if(object[20]!=null){
				p.setAgenciesName(object[20].toString());
			}
			if(object[21]!=null){
				p.setProjManager(object[21].toString());
			}
			list2.add(p);
		}
		return list2;
	}
	
	
	/**
	 * FuncName: getApplyInfoByCode
	 * Description :  根据包组编号获取报名信息
	 * @param 
	 * @param tenderIdList
	 * @param projCode
	 * @return
	 * @throws Exception List<ApplyDTO>
	 * @author: liuke
	 * @Create Date:2011-9-8  上午10:46:12
	 * @Modifier: liuke
	 * @Modified Date:2011-9-8  上午10:46:12
	 */
	public List<ApplyDTO> getApplyInfoByCode(List<String> tenderIdList,
			String projCodes) throws Exception {
		StringBuffer projectids = new StringBuffer();
		List list =  null;//remoteProjectDao.getList(null);//TODO
		for (String str : tenderIdList) {
			projectids.append("'"+str+"'");
			projectids.append(",");
		}
		List<SignUprecord> signUpRecords = signUprecordDao.getSignUprecordList(projectids.substring(0, projectids.length()-1));
		List<ApplyDTO> l2 = new ArrayList<ApplyDTO>();
		for (Iterator iterator = signUpRecords.iterator(); iterator.hasNext();) {
			SignUprecord signUprecord = (SignUprecord) iterator.next();
			if(projCodes==null||"".equals(projCodes)){
				ApplyDTO applyDTO = new ApplyDTO();
				applyDTO.setApplyId(signUprecord.getObjId());
				String projectId = signUprecord.getProject().getObjId();
				String supplierId = signUprecord.getSupplier().getObjId();
				applyDTO.setTenderId(projectId);
				applyDTO.setSupplyerId(supplierId);
				applyDTO.setSupplyerName(signUprecord.getSupplier().getOrgName());
				applyDTO.setOrgCode(signUprecord.getSupplier().getOrgCode());
				applyDTO.setApplyDate(DateUtil.format(signUprecord.getApplyDate(), DateUtil.hour24HMSPattern));
				applyDTO.setLinker(signUprecord.getLinker());
				applyDTO.setApplyUrl(messageSource.getMessage("applicationInternetAddress")+"/services/epp/evaluatesys/bidSoapService@downBidFileApp");
				applyDTO.setLinkerTel(signUprecord.getLinkerTel());
				applyDTO.setIdCard(signUprecord.getIdCard());
				applyDTO.setAddress(signUprecord.getAddress());
				applyDTO.setZipCode(signUprecord.getZipCode());
				applyDTO.setMemo(signUprecord.getMemo());
				Bid bid = bidDao.getBidBySupplierid(supplierId, projectId);
				BailRecord br =  bailRecordDao.getBailRecordBySignUprecord(signUprecord.getObjId());
				String[]prj ={projectId};
				List<BidItems> biss =  null;
				if(bid!=null){
					bidItemsDao.getBidItemsByPackIds(prj,bid.getObjId());
				}
				BidItems bis = null;
				if(biss!=null){
					bis = biss.get(0);
				}
				if(bid!=null){
					applyDTO.setTenderTime(DateUtil.format(bid.getCreateTime(), DateUtil.hour24HMSPattern));
				}
				if(bis!=null){
					applyDTO.setProjManager(bis.getProjManager());
				}
				if(br!=null){
					if(br.getBailStatus().equals(BailStatusEnum.ALREADY_RECEIVE)){
						applyDTO.setBailRecord("true");
						applyDTO.setBailRecordAmount(br.getBallMoney().toString());
					}else {
						applyDTO.setBailRecord("false");
						applyDTO.setBailRecordAmount("0");
					}
				}
				l2.add(applyDTO);
			}else if(signUprecord.getProject()!=null&&projCodes.indexOf(signUprecord.getProject().getProjCode())!=-1){
				ApplyDTO applyDTO = new ApplyDTO();
				applyDTO.setApplyId(signUprecord.getObjId());
				String projectId = signUprecord.getProject().getObjId();
				String supplierId = signUprecord.getSupplier().getObjId();
				applyDTO.setTenderId(projectId);
				applyDTO.setSupplyerId(supplierId);
				applyDTO.setSupplyerName(signUprecord.getSupplier().getOrgName());
				applyDTO.setOrgCode(signUprecord.getSupplier().getOrgCode());
				applyDTO.setApplyDate(DateUtil.format(signUprecord.getApplyDate(), DateUtil.hour24HMSPattern));
				applyDTO.setLinker(signUprecord.getLinker());
				applyDTO.setApplyUrl(messageSource.getMessage("applicationInternetAddress")+"/services/epp/evaluatesys/bidSoapService@downBidFileApp");
				applyDTO.setLinkerTel(signUprecord.getLinkerTel());
				applyDTO.setIdCard(signUprecord.getIdCard());
				applyDTO.setAddress(signUprecord.getAddress());
				applyDTO.setZipCode(signUprecord.getZipCode());
				applyDTO.setMemo(signUprecord.getMemo());
				Bid bid = bidDao.getBidBySupplierid(supplierId, projectId);
				BailRecord br =  bailRecordDao.getBailRecordBySignUprecord(signUprecord.getObjId());
				String[]prj ={projectId};
				List<BidItems> biss =  null;
				if(bid!=null){
					bidItemsDao.getBidItemsByPackIds(prj,bid.getObjId());
				}
				BidItems bis = null;
				if(biss!=null){
					bis = biss.get(0);
				}
				if(bid!=null){
					applyDTO.setTenderTime(DateUtil.format(bid.getCreateTime(), DateUtil.hour24HMSPattern));
				}
				if(bis!=null){
					applyDTO.setProjManager(bis.getProjManager());
				}
				if(br!=null){
					if(BailStatusEnum.ALREADY_RECEIVE.equals(br.getBailStatus())){
						applyDTO.setBailRecord("true");
						applyDTO.setBailRecordAmount(br.getBallMoney().toString());
					}else {
						applyDTO.setBailRecord("false");
						applyDTO.setBailRecordAmount("0");
					}
				}
				l2.add(applyDTO);
			}
		}
		return l2;
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
	public List<ProjectDTO> getOpenBidProjectList(String startDate,
			String endDate, String tenderType, String agencyCode,
			String username) {
		List list =  remoteProjectDao.getOpenBidProjectList(startDate, endDate, tenderType, agencyCode, username);
		List<ProjectDTO> list2 = new ArrayList<ProjectDTO>();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Object[] object = (Object[]) iterator.next();
			ProjectDTO p = new ProjectDTO();
			if(object[0]!=null){
				p.setTenderiId(object[0].toString());
				BidRoom openBidRoom = meetRoomManager.getBidRoomByProjectIdAndRoomType(object[0].toString(), RoomTypeEnum.OPEN_BID_ROOM);//开标室
				BidRoom bidRoom = meetRoomManager.getBidRoomByProjectIdAndRoomType(object[0].toString(), RoomTypeEnum.BID_ROOM);//评标室
				List<Project> subProject = projectManager.getSubProjectByQueryObject(object[0].toString());
				List<ProjectDTO> l2 = new ArrayList<ProjectDTO>();
				if(subProject.size()!=0){
					for (Iterator iterator2 = subProject.iterator(); iterator2.hasNext();) {
						Project p1 = (Project) iterator2.next();
						ProjectDTO p2 = new ProjectDTO();
						p2.setTenderNo(p1.getProjCode());
						p2.setTenderiId(p1.getObjId());
						p2.setTenderName(p1.getProjName());
						p2.setTenderMethod(p1.getEbuyMethod());
						p2.setTenderContent(p1.getContent());
						p2.setTenderSummary(p1.getProjSummary());
						OrgInfo o = p1.getAgencies();
						if(o!=null){
							p2.setAgenciesId(o.getObjId());
						}
						p2.setTenderType(p1.getTenderType());
						if(p1.getBudgetTotalMoney()!=null){
							p2.setTenderBudgetTotalMoney(p1.getBudgetTotalMoney().toString());
						}
						p2.setPurcategoryIds(p1.getPurCategoryIds());
						p2.setPurcategoryNames(p1.getPurCategoryNames());
						p2.setGoodsclassIds(p1.getGoodsClassIds());
						p2.setGoodsclassNames(p1.getGoodsClassNames());
						if(object[12]!=null){
							p2.setTenderUrl(messageSource.getMessage("applicationInternetAddress")+"/ProjectPurchaseFileHttpService.do?method=getProjectPurchaseFile&prjCode="+(object[1].toString()));
						}
						p2.setEvalStartTime(DateUtil.format(p1.getEvalStartTime(), "yyyy-MM-dd HH:mm:ss"));
						p2.setEvalEndTime(DateUtil.format(p1.getEvalEndTime(), "yyyy-MM-dd HH:mm:ss"));
						p2.setOpenBidTime(object[15]!=null?object[15].toString():"");//包组开标时间取项目开标时间 by zhouzhanghe at 2011-7-13 12:59
						l2.add(p2);
					}
					p.setSubProjectList(l2);
				}
				if (openBidRoom!=null) {
					p.setOpenBidAddr(openBidRoom.getBidRoomName());//开标室 add by yangx
				}
				if (bidRoom!=null) {
					p.setEvlRoom(bidRoom.getBidRoomName());//评标室 add by yangx
				}
			}
			
			p.setProjectType("01");//工程类型[1=施工2=监理3=资格预审] add by yangx 先写死工程类型
			
			if(object[1]!=null){
				p.setTenderNo(object[1].toString());
			}
			if(object[2]!=null){
				p.setTenderName(object[2].toString());
			}
			if(object[3]!=null){
				p.setTenderContent(object[3].toString());
			}
			if(object[4]!=null){
				p.setTenderSummary(object[4].toString());
			}
			if(object[5]!=null){
				p.setAgenciesId(object[5].toString());
			}
			if(object[6]!=null){
				p.setTenderType(object[6].toString());
			}
			if(object[7]!=null){
				p.setTenderBudgetTotalMoney(object[7].toString());
			}
			if(object[8]!=null){
				p.setPurcategoryIds(object[8].toString());
			}
			if(object[9]!=null){
				p.setPurcategoryNames(object[9].toString());
			}
			if(object[10]!=null){
				p.setGoodsclassIds(object[10].toString());
			}
			if(object[11]!=null){
				p.setGoodsclassNames(object[11].toString());
			}
			if(object[12]!=null){
				p.setTenderUrl(messageSource.getMessage("applicationInternetAddress")+"/ProjectPurchaseFileHttpService.do?method=getProjectPurchaseFile&prjCode="+(object[1].toString()));
			}
			if(object[13]!=null){
				p.setBuyerIds(object[13].toString());
			}
			if(object[14]!=null){
				p.setBuyerNames(object[14].toString());
			}
			if(object[15]!=null){
				p.setOpenBidTime(object[15].toString());
			}
			if(object[16]!=null){
				p.setOpenBidAddr(object[16].toString());
			}
			if(object[17]!=null){
				p.setEvalStartTime(object[17].toString());
			}
			if(object[18]!=null){
				p.setEvalEndTime(object[18].toString());
			}
			if(object[19]!=null){
				p.setTenderMethod(object[19].toString());
			}
			if(object[20]!=null){
				p.setAgenciesName(object[20].toString());
			}
			if(object[21]!=null){
				p.setProjManager(object[21].toString());
			}
			if(object[22]!=null){
				p.setPrjMonitor(object[22].toString());
			}
			p.setProjectType("01");
			p.setEvlRoom("第一评标室");
			p.setOpenBidAddr("第一开标室");
			list2.add(p);
		}
		return list2;
	}
}
