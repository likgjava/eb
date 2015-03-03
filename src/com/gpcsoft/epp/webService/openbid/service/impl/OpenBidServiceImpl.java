package com.gpcsoft.epp.webService.openbid.service.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.context.FrameMessageResource;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.service.impl.BaseGenericServiceImpl;
import com.gpcsoft.core.utils.DateUtil;
import com.gpcsoft.core.utils.StringUtils;
import com.gpcsoft.epp.bid.dao.BidDao;
import com.gpcsoft.epp.bid.dao.BidPackageDao;
import com.gpcsoft.epp.bid.domain.Bid;
import com.gpcsoft.epp.bid.domain.BidPackage;
import com.gpcsoft.epp.bid.domain.BidSubKey;
import com.gpcsoft.epp.bid.manager.BidManager;
import com.gpcsoft.epp.bid.manager.BidPackageManager;
import com.gpcsoft.epp.bid.service.BidSubKeyService;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.common.service.UserApiService;
import com.gpcsoft.epp.common.utils.StringUtil;
import com.gpcsoft.epp.project.dao.ProjectDao;
import com.gpcsoft.epp.project.domain.ProjImplStatusEnum;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.domain.ProjectRule;
import com.gpcsoft.epp.project.manager.ProjectManager;
import com.gpcsoft.epp.project.service.ProjectService;
import com.gpcsoft.epp.projreview.dao.GenviewDefineDao;
import com.gpcsoft.epp.projreview.dao.OpenBidDao;
import com.gpcsoft.epp.projreview.dao.OpenBidGeneralVitemDao;
import com.gpcsoft.epp.projreview.dao.OpenBidRecordDao;
import com.gpcsoft.epp.projreview.dao.OpenBidSignDao;
import com.gpcsoft.epp.projreview.dao.OpenbidGeneralviewDao;
import com.gpcsoft.epp.projreview.domain.GenviewDefine;
import com.gpcsoft.epp.projreview.domain.OpenBid;
import com.gpcsoft.epp.projreview.domain.OpenBidGeneralVitem;
import com.gpcsoft.epp.projreview.domain.OpenBidRecord;
import com.gpcsoft.epp.projreview.domain.OpenBidSign;
import com.gpcsoft.epp.projreview.domain.OpenbidGeneralview;
import com.gpcsoft.epp.projreview.service.GenviewDefineService;
import com.gpcsoft.epp.projreview.service.OpenBidGeneralVitemService;
import com.gpcsoft.epp.projreview.service.OpenBidSignService;
import com.gpcsoft.epp.projrule.dao.FacilitiesDao;
import com.gpcsoft.epp.projrule.domain.BidRoom;
import com.gpcsoft.epp.projrule.domain.Facilities;
import com.gpcsoft.epp.projrule.domain.MeetRoom;
import com.gpcsoft.epp.projrule.domain.RoomTypeEnum;
import com.gpcsoft.epp.projrule.manager.MeetRoomManager;
import com.gpcsoft.epp.webService.openbid.service.OpenBidService;
import com.gpcsoft.epp.workgroup.domain.WorkGroup;
import com.gpcsoft.epp.workgroup.domain.WorkGroupTypeEnum;
import com.gpcsoft.epp.workgroup.domain.WorkgMember;
import com.gpcsoft.epp.workgroup.manager.WorkGroupManager;
import com.gpcsoft.epp.workgroup.service.WorkgMemberService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;

/**
 * NOTE：在该类中所有方法的try后都加了显示提交事务(projectDaoHibernate.getHibernateTemplate().flush();)，
 * 以避免在service方法结束后提交事务过程中发生异常，导致不能正确返回XML信息的问题。
 * zhouzhanghe at 2011-11-22 16:08
 * 
 * @author zhouzhanghe
 *
 */
@Service(value="wsOpenBidServiceImpl")
public class OpenBidServiceImpl extends BaseGenericServiceImpl<GpcBaseObject>
		implements OpenBidService {
	
	
	@Autowired(required=true) @Qualifier("openBidSignServiceImpl")
	private OpenBidSignService openBidSignService;
	
	@Autowired(required=true) @Qualifier("openBidSignDaoHibernate")
	private OpenBidSignDao openBidSignDao;
	
	@Autowired(required=true)  @Qualifier("projectDaoHibernate")
	private ProjectDao projectDao;
	
	@Autowired(required=true) @Qualifier("userApiServiceImpl")
	private UserApiService userApiService;
	
	@Autowired(required=true)  @Qualifier("openBidRecordDaoHibernate")
	private OpenBidRecordDao openBidRecordDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("bidDaoHibernate")
	private BidDao bidDaoHibernate;
	
	@Autowired(required=true) @Qualifier("projectServiceImpl")
	private ProjectService projectService;
	
	@Autowired(required=true)  @Qualifier("bidSubKeyServiceImpl")
	private BidSubKeyService bidSubKeyService;
	
	@Autowired(required=true) @Qualifier("meetRoomManagerImpl")
	private MeetRoomManager meetRoomManager;
	
	@Autowired(required=true)  @Qualifier("facilitiesDaoHibernate")
	private FacilitiesDao facilitiesDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("projectDaoHibernate")
	private ProjectDao projectDaoHibernate;
	
	@Autowired(required=true) @Qualifier("workgMemberServiceImpl")
	private WorkgMemberService workgMemberServiceImpl;
	
	@Autowired(required=true) @Qualifier("workGroupManagerImpl")
	private WorkGroupManager workGroupManager;
	
	@Autowired(required=true) @Qualifier("openBidServiceImpl")
	private com.gpcsoft.epp.projreview.service.OpenBidService openBidServiceImpl;
	
	@Autowired(required=true) @Qualifier("projectManagerImpl")
	private ProjectManager projectManager;
	
	@Autowired(required=true) @Qualifier("bidManagerImpl")
	private BidManager bidManager;
	
	@Autowired(required=true)  @Qualifier("bidPackageDaoHibernate")
	private BidPackageDao bidPackageDaoHibernate;
 
	@Autowired(required=true)  @Qualifier("bidDaoHibernate")
	private BidDao bidDao;
	
	@Autowired(required=true) @Qualifier("bidPackageManagerImpl")
	private BidPackageManager bidPackageManager;
	
	@Autowired(required=true)  @Qualifier("openBidDaoHibernate")
	private OpenBidDao openBidDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("openbidGeneralviewDaoHibernate")
	private OpenbidGeneralviewDao openbidGeneralviewDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("genviewDefineDaoHibernate")
	private GenviewDefineDao genviewDefineDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("openBidGeneralVitemDaoHibernate")
	private OpenBidGeneralVitemDao openBidGeneralVitemDaoHibernate;
	
	@Autowired(required=true)  @Qualifier("genviewDefineServiceImpl")
	private GenviewDefineService genviewDefineServiceImpl;
	
	@Autowired(required=true)  @Qualifier("frameMessageResource")
	private FrameMessageResource messageSource;
	
	@Autowired(required=true)  @Qualifier("openBidGeneralVitemServiceImpl")
	private OpenBidGeneralVitemService openBidGeneralVitemServiceImpl;

	/**
	 * 获取开标室信息 <br>
	 * 根据项目编号、包编号进入开标室，获取开标时间和状态信息以及其他信息（开标议程/视音频访问地址）。
	 * 
	 * @param prjCode
	 *            项目编号
	 * @param packCode
	 *            包组编号
	 * @param orgCode
	 *            供应商机构编号
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @return
	 * @author zhouzhanghe
	 * @created date 2011-11-03 14:07
	 */
	public String enterOpenBidRoom(String prjCode, String packCode,
			String orgCode, String username, String password) {
		boolean operTag = true;
		String operDesc = "";
		String operException = "";
		StringBuilder openBidRoomInfo = new StringBuilder(); 
		try {
			/*查找 OpenBidSign关联的项目或包组*/
			String tenderId = "";
			/*由于目前执行平台的实现，开标室没有和包组关连，故不根据包组去找开标室
			 * if(!StringUtil.empty(packCode)){
				tenderId = projectDao.findProjectByProjCodeBy(prjCode, packCode).getObjId();
			}else{
				tenderId = projectDao.findProjectByProjCode(prjCode).getObjId();
			}*/
			if(StringUtil.empty(prjCode)){
				operDesc = "传入参数prjCode不能为空";
				throw new Exception(operDesc);
			}
			Project project  = projectDao.findProjectByProjCode(prjCode);
			if(project == null){
				operDesc = "根据项目编号".concat(prjCode).concat("查找项目失败.");
				throw new Exception(operDesc);
			}
			tenderId = project.getObjId();
			ProjectRule projectRule = projectDaoHibernate.getProjectRuleByProjectId(tenderId);
			if(projectRule == null){
				operDesc = "根据项目编号".concat(prjCode).concat("查找项目规则失败.");
				throw new Exception(operDesc);
			}
			BidRoom bidRoom = meetRoomManager.getBidRoomByProjectIdAndRoomType(tenderId, RoomTypeEnum.OPEN_BID_ROOM);//开标室
			if(bidRoom == null || bidRoom.getMeetRoom() == null){
				operDesc = "获取开标室失败,是否预定开标室？";
				throw new Exception(operDesc);
			}
			MeetRoom meetRoom = bidRoom.getMeetRoom();
			List<Facilities> facilitiesList = facilitiesDaoHibernate.getFacilitiesList(meetRoom.getObjId());
			
			StringBuilder mediaList = new StringBuilder();
			for(Facilities facilities : facilitiesList){
				mediaList.append("<media>");
				mediaList.append("<name>".concat(facilities.getFacilitiesName()).concat("</name>"));
				mediaList.append("<type>".concat(facilities.getFacilitiesType()).concat("</type>"));
				mediaList.append("<method>".concat(facilities.getFacilitiesMethod()).concat("</method>"));
				mediaList.append("<url>".concat(facilities.getFacilitiesAddr() == null ? "" : messageSource.getMessage("applicationInternetAddress").concat(facilities.getFacilitiesAddr().replaceAll("&", "&amp;"))).concat("</url>"));
				mediaList.append("<memo>".concat(facilities.getFacilitiesMemo() == null ? "" : facilities.getFacilitiesMemo()).concat("</memo>"));
				mediaList.append("</media>");
			}
			
			openBidRoomInfo.append("<prjCode>".concat(prjCode).concat("</prjCode>"));
			openBidRoomInfo.append("<packCode>".concat((packCode == null ? "":packCode)).concat("</packCode>"));
			openBidRoomInfo.append("<openBidTime>".concat(DateUtil.formatDateTime(projectRule.getOpenBidSDate())).concat("</openBidTime>"));
			openBidRoomInfo.append("<agendaDownUrl></agendaDownUrl>");//TODO
			openBidRoomInfo.append("<mediaList>");
			openBidRoomInfo.append(mediaList.toString());
			openBidRoomInfo.append("</mediaList>");
			
			projectDaoHibernate.getHibernateTemplate().flush();
		} catch (Exception e) {
			operTag = false;
			operException = (e.getMessage() == null ? operException : e.getMessage());
			openBidRoomInfo.delete(0, openBidRoomInfo.length());
		}
		
		StringBuilder responseXML = new StringBuilder();
		responseXML.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
		responseXML.append("<enterOpenBidRoom xmlns=\"http://www.gpcsoft.com\">");
		responseXML.append("<header>");
		responseXML.append("<operTag>".concat(operTag ? "Y" : "N").concat("</operTag>"));
		responseXML.append("<operDesc>".concat(operDesc).concat("</operDesc>"));
		responseXML.append("<operException>".concat(operException).concat("</operException>"));
		responseXML.append("</header>");
		responseXML.append("<body>");
		responseXML.append("<openBidRoomInfo>");
		responseXML.append(openBidRoomInfo.toString());
		responseXML.append("</openBidRoomInfo>");
		responseXML.append("</body>");
		responseXML.append("</enterOpenBidRoom>");

		logger.debug("OpenBidServiceImpl.enterOpenBidRoom return String = ".concat(responseXML.toString()));
		return responseXML.toString();
	}

	/**
	 * 签到
	 * 
	 * @param prjCode
	 *            项目编号
	 * @param packCode
	 *            包组编号
	 * @param timeStamp
	 *            时间戳
	 * @param cert
	 *            签名CA人交换证书
	 * @param certNo
	 *            证书号（格式示例：证书号）
	 * @param orgName
	 *            供应商机构名称
	 * @param certType
	 *            证书类型
	 * @param signPerson
	 *            签到人
	 * @param signPersonTel
	 *            签到人联系电话
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @return
	 * @author zhouzhanghe
	 * @created date 2011-11-03 14:07
	 */
	public String saveSignOpenBidRoom(String prjCode, String packCode,
			String timeStamp, String cert, String certNo, String certType, String signType, String signPerson,
			String signPersonTel, String orgCode, String username, String password) {

		boolean operTag = true;
		String operDesc = "";
		String operException = "";
		try {
			
			/*查找 OpenBidSign关联的项目或包组*/
			String tenderId = "";
			Project project = null;
			if(!StringUtil.empty(packCode)){
				project = projectDao.findProjectByProjCodeBy(prjCode, packCode);
				if(project == null)
					project = projectDao.findProjectByProjCode(prjCode);
			}else{
				project = projectDao.findProjectByProjCode(prjCode);
			}
			if(project == null){
				operDesc = "根据项目编号".concat(prjCode).concat("和包组编号").concat(packCode).concat("查找项目失败.");
				throw new Exception(operDesc);
			}
			tenderId = project.getObjId();
			
			OrgInfo orgInfo = userApiService.getOrgInfoByOrgCode(orgCode);
			if(orgInfo == null){
				operDesc = "根据机构编号".concat(orgCode).concat("查找机构失败.");
				throw new Exception(operDesc);
			}
			/*数据数据较验*/
			List<OpenBidSign> openBidSignList = openBidSignDao.getOpenBidSignByTenderIdAndOrgCode(tenderId, orgCode);
			if(openBidSignList != null && openBidSignList.size() > 1){
				operDesc = "数据异常，由于:".concat(orgInfo.getOrgName().concat("供应商多次签到。"));
				throw new Exception(operDesc); 
			}
			
			OpenBidSign openBidSign;
			if(openBidSignList != null && openBidSignList.size() == 1)
				openBidSign = openBidSignList.get(0);
			else
				openBidSign = new OpenBidSign();
			openBidSign.setTenderId(tenderId);
			openBidSign.setSupplierId(orgInfo);
			openBidSign.setSupplierName(orgInfo.getOrgName());
			openBidSign.setSignCertType(certType);
			openBidSign.setSupplierCode(orgCode);
			openBidSign.setSignStr(timeStamp);
			openBidSign.setSignCert(cert);
			openBidSign.setSignNo(certNo);
			openBidSign.setSignCertEmpName(signPerson);
			openBidSign.setSignCertEmpTel(signPersonTel);
			openBidSign.setSignType(signType);
			
			openBidSignService.save(openBidSign);
			projectDaoHibernate.getHibernateTemplate().flush();
		} catch (SQLException e) {
			operTag = false;
			operException = (e.getMessage() == null ? operException : e.getMessage());
		} catch (Exception e) {
			operTag = false;
			operException = (e.getMessage() == null ? operException : e.getMessage());
		}
		
		StringBuilder responseXML = new StringBuilder();
		responseXML.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
		responseXML.append("<signOpenBidRoom xmlns=\"http://www.gpcsoft.com\">");
		responseXML.append("<header>");
		responseXML.append("<operTag>".concat(operTag ? "Y" : "N").concat("</operTag>"));
		responseXML.append("<operDesc>".concat(operDesc).concat("</operDesc>"));
		responseXML.append("<operException>".concat(operException).concat("</operException>"));
		responseXML.append("</header>");
		responseXML.append("</signOpenBidRoom>");

		logger.debug("OpenBidServiceImpl.signOpenBidRoom return String = ".concat(responseXML.toString()));
		return responseXML.toString();
	}

	/**
	 * 获取签到到开标室的供应商列表
	 * 
	 * @param prjCode
	 *            项目编号
	 * @param packCode
	 *            包组编号
	 * @param orgCode
	 *            供应商机构编号
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @return
	 * @author zhouzhanghe
	 * @created date 2011-11-03 14:07
	 */
	public String getSignedSuppliers(String prjCode, String packCode,
			String orgCode, String username, String password) {

		boolean operTag = true;
		String operDesc = "";
		String operException = "";
		StringBuilder supplierList = new StringBuilder();
		try {
			/*查找 OpenBidSign关联的项目或包组*/
			String tenderId = "";
			Project project = null;
			/*if  packCode 不为空
			 *   则去找包组相关信息
			 *   if 未找到包组相关信息
			 *      则根据项目编号去找相关信息
			 *   else
			 *      返回根据包组找到的相关信息
			 *else
			 *   根据项目编号去找相关信息
			 **/
			if(!StringUtil.empty(packCode)){
				project = projectDao.findProjectByProjCodeBy(prjCode, packCode);
				if(project == null)
					project = projectDao.findProjectByProjCode(prjCode);
			}else{
				project = projectDao.findProjectByProjCode(prjCode);
			}
			if(project == null){
				operDesc = "根据项目编号".concat(orgCode).concat("查找项目失败.");
				throw new Exception(operDesc);
			}
			tenderId = project.getObjId();
			
			List<OpenBidSign> openBidSignList = openBidSignDao.getOpenBidSignByTenderId(tenderId);
			if(openBidSignList == null || openBidSignList.size() == 0){
				operDesc = "没有签到供应商.";
				throw new Exception(operDesc);
			}
			
			/*判断当前供应商是否有权限查看供应商列表*/
			if(!StringUtil.empty(orgCode)){
				List<OpenBidSign> openBidSignListAuthority = openBidSignDao.getOpenBidSignByTenderIdAndOrgCode(tenderId, orgCode);
				if(openBidSignListAuthority == null || openBidSignListAuthority.size() == 0){
					operDesc = orgCode.concat("供应商").concat("没有查看该列表的权限");
					throw new Exception(operDesc);
				}
			} 
			
			for(OpenBidSign openBidSign : openBidSignList){
				supplierList.append("<supplier>");
				supplierList.append("<orgCode>".concat(openBidSign.getSupplierCode()).concat("</orgCode>"));
				supplierList.append("<orgName>".concat(openBidSign.getSupplierName()).concat("</orgName>"));
				supplierList.append("<signPerson>".concat(openBidSign.getSignCertEmpName()).concat("</signPerson>"));
				supplierList.append("<signPersonTel>".concat(openBidSign.getSignCertEmpTel() == null ? "" : openBidSign.getSignCertEmpTel()).concat("</signPersonTel>"));
				supplierList.append("<signTime>".concat(openBidSign.getCreateTime() == null ? "" : DateUtil.formatDateTime(openBidSign.getCreateTime())).concat("</signTime>"));
				supplierList.append("<signType>".concat(openBidSign.getCreateTime() == null ? "" : openBidSign.getSignType()).concat("</signType>"));
				supplierList.append("</supplier>");
			}
			
			projectDaoHibernate.getHibernateTemplate().flush();
		} catch (Exception e) {
			operTag = false;
			operException = (e.getMessage() == null ? operException : e.getMessage());
			supplierList.delete(0, supplierList.length());
		}
		
		StringBuilder responseXML = new StringBuilder();
		responseXML.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
		responseXML.append("<getSignedSuppliers xmlns=\"http://www.gpcsoft.com\">");
		responseXML.append("<header>");
		responseXML.append("<operTag>".concat(operTag ? "Y" : "N").concat("</operTag>"));
		responseXML.append("<operDesc>".concat(operDesc).concat("</operDesc>"));
		responseXML.append("<operException>".concat(operException).concat("</operException>"));
		responseXML.append("</header>");
		responseXML.append("<body>");
		responseXML.append("<supplierList>");
		responseXML.append(supplierList.toString());
		responseXML.append("</supplierList>");
		responseXML.append("</body>");
		responseXML.append("</getSignedSuppliers>");

		logger.debug("OpenBidServiceImpl.getSignedSuppliers return String = ".concat(responseXML.toString()));
		return responseXML.toString();
	}

	/**
	 * 获取工作小组成员
	 * 
	 * @param prjCode
	 *            项目编号
	 * @param packCode
	 *            包组编号
	 * @param workGroupType
	 *            工作组类型
	 * @param orgCode
	 *            供应商机构编号
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @author zhouzhanghe
	 * @created date 2011-11-03 14:07
	 */
	public String getWorkGroup(String prjCode, String packCode,
			String workGroupType, String orgCode, String username,
			String password) {
		boolean operTag = true;
		String operDesc = "";
		String operException = "";
		StringBuilder workGroupStr = new StringBuilder();
		try {
			/*查找 OpenBidSign关联的项目或包组*/
			String tenderId = "";
			Project project = null;
			if(!StringUtil.empty(packCode)){
				project = projectDao.findProjectByProjCodeBy(prjCode, packCode);
			}else{
				project = projectDao.findProjectByProjCode(prjCode);
			}
			if(project == null){
				operDesc = "根据项目编号".concat(orgCode).concat("查找项目失败.");
				throw new Exception(operDesc);
			}
			tenderId = project.getObjId();
			
			WorkGroup workGroup = workGroupManager.getWorkGroupByProjectIdAndType(tenderId, WorkGroupTypeEnum.OPEN_BID);
			if(workGroup == null){
				operDesc = "根据项目编号".concat(orgCode).concat("查找开标小组失败.");
				throw new Exception(operDesc);
			}
			List<WorkgMember> workgMemberList = workgMemberServiceImpl.getWorkgMemberByWorkGroupId(workGroup.getObjId());
			
			StringBuilder membersStr = new StringBuilder();
			for(WorkgMember workgMember : workgMemberList){
				membersStr.append("<member>");
				membersStr.append("<workgmId>".concat(workgMember.getObjId()).concat("</workgmId>"));
				membersStr.append("<workgmName>".concat(workgMember.getWorkgmName()).concat("</workgmName>"));
				membersStr.append("<workgmDuty>".concat(workgMember.getWorkgmDuty()).concat("</workgmDuty>"));
				membersStr.append("<workgmType>".concat(workgMember.getWorkgmType()).concat("</workgmType>"));
				membersStr.append("<workgmSpeciality>".concat(workgMember.getWorkgmSpeciality()).concat("</workgmSpeciality>"));
				membersStr.append("<workgmLinkerPhone>".concat(workgMember.getLinkerPhone()).concat("</workgmLinkerPhone>"));
				membersStr.append("<workgmOrgName>".concat(workgMember.getWorkgmCompany()).concat("</workgmOrgName>"));
				membersStr.append("<isLeader>".concat(workgMember.getWorkgmIsLeader()).concat("</isLeader>"));
				membersStr.append("<isAmount>".concat(workgMember.getIsAmount()).concat("</isAmount>"));
				membersStr.append("<isScene></isScene>");//TODO
				membersStr.append("<idCard>".concat(workgMember.getIsAmount()).concat("</idCard>"));//TODO
				membersStr.append("</member>");
			}
			
			workGroupStr.append("<workGroupType>".concat(workGroup.getWorkgType()).concat("</workGroupType>"));
			workGroupStr.append("<members>");
			workGroupStr.append(membersStr.toString());
			workGroupStr.append("</members>");
			
			projectDaoHibernate.getHibernateTemplate().flush();
		} catch (Exception e) {
			operTag = false;
			operException = (e.getMessage() == null ? operException : e.getMessage());
			workGroupStr.delete(0, workGroupStr.length());
		}
		
		StringBuilder responseXML = new StringBuilder();
		responseXML.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
		responseXML.append("<getWorkGroup xmlns=\"http://www.gpcsoft.com\">");
		responseXML.append("<header>");
		responseXML.append("<operTag>".concat(operTag ? "Y" : "N").concat("</operTag>"));
		responseXML.append("<operDesc>".concat(operDesc).concat("</operDesc>"));
		responseXML.append("<operException>".concat(operException).concat("</operException>"));
		responseXML.append("</header>");
		responseXML.append("<body>");
		responseXML.append("<workGroup>");
		responseXML.append(workGroupStr.toString());
		responseXML.append("<workGroup/>");
		responseXML.append("</body>");
		responseXML.append("</getWorkGroup>");
		
		logger.debug("OpenBidServiceImpl.getWorkGroup return String = ".concat(responseXML.toString()));
		return responseXML.toString();
	}

	/**
	 * 经办人启动开标
	 * 
	 * @param prjCode
	 *            项目编号
	 * @param packCode
	 *            包组编号
	 * @param caId ca证书id号
	 * @param orgCode
	 *            供应商机构编号
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @author zhouzhanghe
	 * @created date 2011-11-03 14:07
	 */
	public String saveStartOpenBid(String prjCode, String packCode, String caId, String orgCode, String username, String password) {
	    boolean isSuccess = true;
	    String exception = "";
	    String operDesc = "";
		try{
			
			Project project = this.projectService.findProjectByProjCode(prjCode);
			OrgInfo agency = userApiService.getOrgInfoByOrgCode(orgCode);
			if(agency == null ){
				operDesc = "根据机构编码"+orgCode+"查找代理机构失败！";
				throw new Exception(operDesc);
			}
			if(!agency.getObjId().equals(project.getAgencies().getObjId())){//若代理机构不是统一个，不允许继续操作
				operDesc = "您的机构编码为"+orgCode+"，不是该招标项目的代理机构，不允许启动开标！";
				throw new Exception(operDesc);
			}
		  	Project subProject = null;
		  	ProjectRule projectRule = null;
		  	if(! StringUtils.empty(packCode)){//若没有包组，则取项目信息
		  		subProject = this.projectService.findProjectByProjCodeAndParent(packCode, project.getObjId());
		  		projectRule = projectService.getProjectRuleByProjectId(project.getObjId());
		  	}else{
		  		ProjectRule subProjectRule = projectService.getProjectRuleByProjectId(project.getObjId());
		  		if(subProjectRule != null && !StringUtils.empty(subProjectRule.getObjId())){//若有包组的规则，则需要根据包组规则来判断业务
		  			projectRule = subProjectRule;
		  		}
		  	}
		  	if(com.gpcsoft.epp.common.utils.DateUtil.compareDate(projectRule.getOpenBidSDate(), new Date()) >  0){//若未到开标时间，不允许 启动开标
		  		operDesc = "还未到开标时间！";
				throw new Exception(operDesc);
			}
		   
		  	OpenBid openBid = null;
		  	if(subProject != null)
		  		openBid = openBidServiceImpl.getOpenBidByPrjIdAndPackId(project.getObjId(), subProject.getObjId());
		  	else
		  		openBid = openBidServiceImpl.getOpenBidByProjectId(project.getObjId());
		  	
		  	if(openBid == null || StringUtils.empty(openBid.getObjId())){//若没有保存过，则创建新对象
		  		openBid = new OpenBid();
		  		openBid.setProjCode(prjCode);
		  		openBid.setProjName(project.getProjName());
		  		openBid.setProjId(project.getObjId());
		  		openBid.setSubProjId(subProject!=null?subProject.getObjId():project.getObjId());//如果没分包,开标中关联的包组ID为项目ID
		  	}
		  	openBid.setSubProjId(subProject!=null?subProject.getObjId():project.getObjId());//如果没分包,开标中关联的包组ID为项目ID
		  	openBid.setOpenBStatus("01");//开标状态[00未开标 01开标中 02开标结束] 
		  	openBid.setUseStatus(CommonEnum.USER_STATUS_FORMAL);
		  	openBid.setOpenBStartTime(new Date());
		  	openBidServiceImpl.save(openBid);
		  	
		  	projectDaoHibernate.getHibernateTemplate().flush();
		} catch (Exception e) {
			isSuccess = false;
			exception = (e.getMessage() == null ? exception : e.getMessage());
		}
	    StringBuilder responseXML = new StringBuilder();
	    responseXML.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
	    responseXML.append("<startOpenBid xmlns=\"http://www.gpcsoft.com\">");
	    responseXML.append("<header>");
	    responseXML.append("<operTag>".concat(isSuccess ? "Y" : "N").concat("</operTag>"));
	    responseXML.append("<operDesc>".concat(operDesc).concat("</operDesc>"));
	    responseXML.append("<operException>".concat(exception).concat("</operException>"));
	    responseXML.append("</header>");
	    responseXML.append("</startOpenBid>");
		logger.debug("BidSoapService    startOpenBid====================================start\n"+responseXML.toString());
		logger.debug("BidSoapService    startOpenBid====================================end\n");
		return responseXML.toString();
	}

	/**
	 * 在线获取开标信息
	 * 
	 * @param prjCode
	 *            项目编号
	 * @param packCode
	 *            包件编号
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @param caId
	 *            ca证书id号
	 * @param tenderOrgCode
	 *            投标机构编号
	 * @param tenderOrgName
	 *            投标机构名称
	 * @return
	 * @author zhouzhanghe
	 * @created date 2011-11-03 14:07
	 */
	public String getOpenBidInfo(String prjCode, String packCode,
			String username, String password, String caId,
			String tenderOrgCode, String tenderOrgName) {
		
		boolean operTag = true;
		String operDesc = "";
		String operException = "";
		StringBuilder openBidInfo = new StringBuilder();
		
		try {
			/*查找 OpenBidSign关联的项目或包组*/
			Project project = null;
			Project subProject = null;
			if(!StringUtil.empty(packCode)){
				subProject = projectDao.findProjectByProjCodeBy(prjCode, packCode);
				if(subProject == null){
					operDesc = "根据项目编号".concat(prjCode).concat("，包组编号").concat(packCode).concat("查找项目失败.");
					throw new Exception(operDesc);
				}
			}
			project = projectDao.findProjectByProjCode(prjCode);
			if(project == null){
				operDesc = "根据项目编号".concat(prjCode).concat("查找项目失败.");
				throw new Exception(operDesc);
			}
			
			/*查找OpenBid*/
			OpenBid openBid = null;
			if(!StringUtil.empty(packCode)){
				openBid = openBidServiceImpl.getOpenBidByPrjIdAndPackId(project.getObjId(), subProject.getObjId());
			}
			else{
				openBid = openBidServiceImpl.getOpenBidByProjectId(project.getObjId());
			}
			if(openBid == null){
				operDesc = "根据项目编号".concat(prjCode).concat("，包组编号").concat(packCode).concat("查找开标数据失败.");
				throw new Exception(operDesc);
			}
			
			List<OpenBidRecord> openBidRecordList = openBidRecordDaoHibernate.getOpenBidRecordByOPenBidId(openBid.getObjId());
			StringBuilder supplierList = new StringBuilder();
			for(OpenBidRecord openBidRecord : openBidRecordList){
				Bid bid = bidDaoHibernate.get(openBidRecord.getBidId());
				supplierList.append("<supplier>");
				supplierList.append("<objId>".concat(openBidRecord.getSupplier().getObjId()).concat("</objId>"));
				supplierList.append("<orgCode>".concat(openBidRecord.getSupplier().getOrgCode()).concat("</orgCode>"));
				supplierList.append("<orgName>".concat(openBidRecord.getSupplier().getOrgName()).concat("</orgName>"));//zhouzhanghe at 2011-11-09 14:12 增加返回机构名称
				supplierList.append("<linker>".concat((openBidRecord.getOpenBLinker() == null ? "" : openBidRecord.getOpenBLinker())).concat("</linker>")); 
				supplierList.append("<linkerTel>".concat(openBidRecord.getOpenBLinkerTel() == null ? "" : openBidRecord.getOpenBLinkerTel()).concat("</linkerTel>"));
				supplierList.append("<price>".concat(bid.getBidQuoteSum().toString()).concat("</price>"));
				supplierList.append("<projManager>".concat((openBidRecord.getProjManager() == null ? "" : openBidRecord.getProjManager())).concat("</projManager>"));
				boolean decrptStatus = bidSubKeyService.getIsExistByBidIdAndSupplierId(openBidRecord.getBidId(), openBidRecord.getSupplier().getObjId());
				supplierList.append("<decrptStatus>".concat(decrptStatus ? "Y" : "N").concat("</decrptStatus>"));
				supplierList.append("<openTime>".concat(DateUtil.formatDateTime(openBidRecord.getCreateTime())).concat("</openTime>"));
				supplierList.append("<openStatus>".concat((openBidRecord.getOpenBRStatus() == null ? "" : openBidRecord.getOpenBRStatus())).concat("</openStatus>"));
				supplierList.append("<openDesc>".concat((openBidRecord.getOpenBRStatusMemo() == null ? "" : openBidRecord.getOpenBRStatusMemo())).concat("</openDesc>"));
				supplierList.append("<isEffective>".concat((openBidRecord.getOpenRisEffective() == null ? "" : openBidRecord.getOpenRisEffective())).concat("</isEffective>"));
				supplierList.append("<effectiveMemo>".concat((openBidRecord.getEffectiveMemo() == null ? "" : openBidRecord.getEffectiveMemo())).concat("</effectiveMemo>"));
				
				/*返回开标一览表*/
				supplierList.append("<bidForm>");
				OpenbidGeneralview openbidGeneralview = openbidGeneralviewDaoHibernate.getOpenbidGeneralviewBySupplierIdAndPackId(openBidRecord.getSupplier().getObjId(), openBidRecord.getSubProjId());
				if(openbidGeneralview != null){
					StringBuilder bidForms = new StringBuilder();
					List<OpenBidGeneralVitem> openBidGeneralVitemList = openBidGeneralVitemDaoHibernate.getOpenBidGeneralVitemListByOpenbidGeneralview(openbidGeneralview.getObjId());
					for(OpenBidGeneralVitem openBidGeneralVitem : openBidGeneralVitemList){
						bidForms.append("<item>");
						bidForms.append("<code></code>");
						bidForms.append("<name>".concat(openBidGeneralVitem.getFactorName() == null ? "" : openBidGeneralVitem.getFactorName()).concat("</name>"));
						bidForms.append("<value>".concat(openBidGeneralVitem.getRespValue() == null ? "" : openBidGeneralVitem.getRespValue()).concat("</value>"));
						bidForms.append("</item>");
					}
					supplierList.append(bidForms.toString());
				}
				supplierList.append("</bidForm>");
				
				supplierList.append("</supplier>");
			}
			
			openBidInfo.append("<prjCode>".concat(prjCode).concat("</prjCode>"));
			openBidInfo.append("<packCode>".concat(packCode).concat("</packCode>"));
			openBidInfo.append("<isEffective>".concat(openBid == null || openBid.getIsEffective() == null ? "" : openBid.getIsEffective()).concat("</isEffective>"));
			openBidInfo.append("<effectiveMemo>".concat(openBid == null || openBid.getEffectiveMemo() == null ? "" : openBid.getEffectiveMemo()).concat("</effectiveMemo>"));
			openBidInfo.append("<supplierList>");
			openBidInfo.append(supplierList.toString());
			openBidInfo.append("</supplierList>");
			
			projectDaoHibernate.getHibernateTemplate().flush();
		} catch (Exception e) {
			operTag = false;
			operException = (e.getMessage() == null ? operException : e.getMessage());
			openBidInfo.delete(0, openBidInfo.length());
		}
		
		StringBuilder responseXML = new StringBuilder();
		responseXML.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
		responseXML.append("<getOpenBidInfo xmlns=\"http://www.gpcsoft.com\">");
		responseXML.append("<header>");
		responseXML.append("<operTag>".concat(operTag ? "Y" : "N").concat("</operTag>"));
		responseXML.append("<operDesc>".concat(operDesc).concat("</operDesc>"));
		responseXML.append("<operException>".concat(operException).concat("</operException>"));
		responseXML.append("</header>");
		responseXML.append("<body>");
		responseXML.append("<openBidInfo>");
		responseXML.append(openBidInfo.toString());
		responseXML.append("</openBidInfo>");
		responseXML.append("</body>");
		responseXML.append("</getOpenBidInfo>");
		
		logger.debug("OpenBidServiceImpl.getOpenBidInfo return String = ".concat(responseXML.toString()));
		return responseXML.toString();
	}

	/**
	 * 供应商远程确认开标信息
	 * 
	 * @param prjCode
	 *            项目编号
	 * @param packCode
	 *            包件编号
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @param caId
	 *            ca证书id号
	 * @param timeStamp
	 *            时间戳
	 * @param confirmPerson
	 *            确认人
	 * @param orgCode
	 *            投标机构编号
	 * @param orgName
	 *            投标机构名称
	 * @param confirmResult
	 *            确认结果（Y/N）
	 * @param confirmMemo
	 *            确认意见
	 * @return
	 * @author zhouzhanghe
	 * @created date 2011-11-03 14:07
	 */
	public String saveConfirmOpenInfo(String prjCode, String packCode,
			String username, String password, String caId, String timeStamp,
			String confirmPerson, String orgCode, String orgName,
			String confirmResult, String confirmMemo) {
		
		boolean operTag = true;
		String operDesc = "";
		String operException = "";
		try {
			/*查找 OpenBidSign关联的项目或包组*/
			String tenderId = "";
			Project project = null;
			if(!StringUtil.empty(packCode)){
				project = projectDao.findProjectByProjCodeBy(prjCode, packCode);
				if(project == null){
					project = projectDao.findProjectByProjCode(prjCode);//zhouzhanghe at 2011-11-09 14:19 为了调试，临时加入
				}
			}else{
				project = projectDao.findProjectByProjCode(prjCode);
			}
			if(project == null){
				operDesc = "根据项目编号".concat(prjCode).concat("，包组编号").concat(packCode).concat("查找项目失败.");
				throw new Exception(operDesc);
			}
			tenderId = project.getObjId();
			OrgInfo orgInfo = userApiService.getOrgInfoByOrgCode(orgCode);
			if(orgInfo == null){
				operDesc = "根据机构编号".concat(orgCode).concat("查找机构失败.");
				throw new Exception(operDesc);
			}
			OpenBidRecord openBidRecord = openBidRecordDaoHibernate.getOpenBidRecordBySupplierIdAndSubProjectId(tenderId, orgInfo.getObjId());
			if(openBidRecord == null){
				operDesc = "根据项目编号".concat(prjCode).concat("、包组编号").concat(packCode).concat("和机构编号").concat(orgCode).concat("查找开标记录失败.");
				throw new Exception(operDesc);
			}
			if("Y".equalsIgnoreCase(confirmResult))
				openBidRecord.setConfirmStatus("01");
			else if("N".equalsIgnoreCase(confirmResult))
				openBidRecord.setConfirmStatus("02");
			openBidRecord.setConfirmPerson(confirmPerson);
			openBidRecord.setConfirmMemo(confirmMemo);
			openBidRecord.setConfirmSign(timeStamp);
			openBidRecordDaoHibernate.save(openBidRecord);
			
			projectDaoHibernate.getHibernateTemplate().flush();
		} catch (Exception e) {
			operTag = false;
			operException = (e.getMessage() == null ? operException : e.getMessage());
		}
		
		StringBuilder responseXML = new StringBuilder();
		responseXML.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
		responseXML.append("<confirmOpenInfo xmlns=\"http://www.gpcsoft.com\">");
		responseXML.append("<header>");
		responseXML.append("<operTag>".concat(operTag ? "Y" : "N").concat("</operTag>"));
		responseXML.append("<operDesc>".concat(operDesc).concat("</operDesc>"));
		responseXML.append("<operException>".concat(operException).concat("</operException>"));
		responseXML.append("</header>");
		responseXML.append("<body></body>");
		responseXML.append("</confirmOpenInfo>");
		logger.debug("OpenBidServiceImpl.confirmOpenInfo return String = ".concat(responseXML.toString()));
		return responseXML.toString();
	}

	/**
	 * 从执行平台获取确认信息
	 * 
	 * @param prjCode
	 *            项目编号
	 * @param packCode
	 *            包件编号
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @param orgCode
	 *            投标机构编号
	 * @author zhouzhanghe
	 * @created date 2011-11-03 14:07
	 */
	public String getOpenConfirmInfo(String prjCode, String packCode, String orgCode, String username, String password) {

		boolean operTag = true;
		String operDesc = "";
		String operException = "";
		StringBuilder supplierList = new StringBuilder();
		try {
			/*查找 OpenBidSign关联的项目或包组*/
			String tenderId = "";
			Project project = null;
			if(!StringUtil.empty(packCode)){
				project = projectDao.findProjectByProjCodeBy(prjCode, packCode);
				if(project == null)
					project = projectDao.findProjectByProjCode(prjCode);//zhouzhanghe at 2011-11-09 14:19 为了调试，临时加入
			}else{
				project = projectDao.findProjectByProjCode(prjCode);
			}
			if(project == null){
				operDesc = "根据项目编号".concat(prjCode).concat("，包组编号").concat(packCode).concat("查找项目失败.");
				throw new Exception(operDesc);
			}
			tenderId = project.getObjId();
			
			List<OpenBidRecord> openBidRecordList = new ArrayList<OpenBidRecord>();
			if(!StringUtil.empty(orgCode)){
				OrgInfo orgInfo = userApiService.getOrgInfoByOrgCode(orgCode);
				if(orgInfo == null){
					operDesc = "根据机构编号".concat(orgCode).concat("查找机构失败.");
					throw new Exception(operDesc);
				}
				OpenBidRecord openBidRecord = openBidRecordDaoHibernate.getOpenBidRecordBySupplierIdAndSubProjectId(tenderId, orgInfo.getObjId());
				if(openBidRecord != null)
					openBidRecordList.add(openBidRecord);
			}else{
				openBidRecordList = openBidRecordDaoHibernate.getAllOpenBidRecordByProjectId(tenderId);
			}
			for(OpenBidRecord openBidRecord : openBidRecordList){
				supplierList.append("<supplier>");
				supplierList.append("<orgCode>".concat(openBidRecord.getSupplier().getOrgCode()).concat("</orgCode>"));
				supplierList.append("<orgName>".concat(openBidRecord.getSupplier().getOrgName()).concat("</orgName>"));
				supplierList.append("<confirmPerson>".concat(openBidRecord.getConfirmPerson() == null ? "" : openBidRecord.getConfirmPerson()).concat("</confirmPerson>"));
				supplierList.append("<confirmStatus>".concat(openBidRecord.getConfirmStatus() == null ? "" : openBidRecord.getConfirmStatus()).concat("</confirmStatus>"));
				supplierList.append("<confirmMemo>".concat(openBidRecord.getConfirmMemo() == null ? "" : openBidRecord.getConfirmMemo()).concat("</confirmMemo>"));
				if(openBidRecord.getUpdateTime() != null)
					supplierList.append("<confirmTime>".concat(DateUtil.formatDateTime(openBidRecord.getUpdateTime()).concat("</confirmTime>")));
				else
					supplierList.append("<confirmTime></confirmTime>");
				supplierList.append("</supplier>");
			}
			
			projectDaoHibernate.getHibernateTemplate().flush();
		} catch (Exception e) {
			operTag = false;
			operException = (e.getMessage() == null ? operException : e.getMessage());
			supplierList.delete(0, supplierList.length());
		}
		
		StringBuilder responseXML = new StringBuilder();
		responseXML.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
		responseXML.append("<confirmOpenInfo xmlns=\"http://www.gpcsoft.com\">");
		responseXML.append("<header>");
		responseXML.append("<operTag>".concat(operTag ? "Y" : "N").concat("</operTag>"));
		responseXML.append("<operDesc>".concat(operDesc).concat("</operDesc>"));
		responseXML.append("<operException>".concat(operException).concat("</operException>"));
		responseXML.append("</header>");
		responseXML.append("<body>");
		responseXML.append("<openConfirmInfo>");
		responseXML.append("<prjCode>".concat(prjCode).concat("</prjCode>"));
		responseXML.append("<packCode>".concat(packCode).concat("</packCode>"));
		responseXML.append("<supplierList>");
		responseXML.append(supplierList.toString());
		responseXML.append("</supplierList>");
		responseXML.append("</openConfirmInfo>");
		responseXML.append("</body>");
		responseXML.append("</confirmOpenInfo>");

		logger.debug("OpenBidServiceImpl.getOpenConfirmInfo return String = ".concat(responseXML.toString()));
		return responseXML.toString();
	}

	/**
	 * 电子评审客户端向执行平台上传开标结果
	 * 
	 * @param prjCode
	 *            项目编号
	 * @param packCode
	 *            包件编号
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @param caId
	 *            ca证书id号
	 * @param orgCode
	 *            代理机构编号
	 * @param orgName
	 *            代理机构名称
	 * @param content 
	 * @return
	 * @author zhouzhanghe
	 * @created date 2011-11-03 14:07
	 */
	public String saveUploadOpenBidInfo(String paramPrjCode, String paramPackCode, String caId, String paramOrgCode, String orgName,  String username, String password, String content) {
		boolean operTag = true;
		String operDesc = "";
		String operException = "";
		
		try {
			/*数据解析*/
			Document document = DocumentHelper.parseText(content);
			Element openBidInfoElement = document.getRootElement().element("body").element("openBidInfo");
			String prjCode = openBidInfoElement.element("prjCode").getTextTrim();
			String packCode = openBidInfoElement.element("packCode").getTextTrim();
			String isEffective = openBidInfoElement.element("isEffective").getTextTrim();
			String effectiveMemo = openBidInfoElement.element("effectiveMemo").getTextTrim();
			
			/*得到项目ID和投标项目ID*/
			Project project = projectManager.findProjectByProjCode(prjCode);
			String projectName = project.getProjName();
			String projectId = project.getObjId();
			Project pack = null;
			pack = projectManager.findProjectByProjCodeAndParent(packCode,project.getObjId());
			String packId = "";
			//String packName = "";
			if(pack!=null){
				packId = pack.getObjId();
				//packName = pack.getProjName();
			}else{
				packId = projectId;
				//packName = projectName;
				pack = project;
			}
			
			/*拼装开标数据*/
			List<OpenBid> openBidList = openBidDaoHibernate.getOpenBidBySubProjectId(packId);
			OpenBid	openBid = null;
			if(openBidList.isEmpty()){
				openBid = new OpenBid();
				openBid.setProjId(project.getObjId());
				openBid.setSubProjId(pack.getObjId());
				openBid.setProjName(project.getProjName());
				openBid.setProjCode(project.getProjCode());
			}else{
				openBid = openBidList.get(0);
			}
			openBid.setIsEffective(isEffective);
			if(StringUtils.empty(isEffective)){
				openBid.setIsEffective("00");
			}
			openBid.setEffectiveMemo(effectiveMemo);
			
			List <Element> supplierElementList = openBidInfoElement.element("supplierList").elements("supplier");
			for(Element supplierElement :supplierElementList){
				/*数据解析*/
				String objId = supplierElement.element("objId").getTextTrim();
				String orgCode = supplierElement.element("orgCode").getTextTrim();
				String linker = supplierElement.element("linker").getTextTrim();
				String linkerTel = supplierElement.element("linkerTel").getTextTrim();
				String price = supplierElement.element("price").getTextTrim();
				String projManager = supplierElement.element("projManager").getTextTrim();
				String decrptStatus = supplierElement.element("decrptStatus").getTextTrim();
				String openTime = supplierElement.element("openTime").getTextTrim();
				String openStatus = supplierElement.element("openStatus").getTextTrim();
				String openDesc = supplierElement.element("openDesc").getTextTrim();
				String supplierIsEffective = supplierElement.element("isEffective").getTextTrim();
				String supplierEffectiveMemo = supplierElement.element("effectiveMemo").getTextTrim();
				
				
				/*得到投标供应商*/
				OrgInfo supplier = userApiService.getOrgInfoByOrgCode(orgCode);
				if(supplier==null){
					supplier = new OrgInfo();
					supplier.setObjId(objId);
					supplier.setOrgCode(orgCode);
				}
				
				/*计算投标总报价*/
				List<Bid> oldBidList = bidManager.getBidListByProjectIdAndUserId(projectId, supplier.getObjId());  //根据项目与供应商机构获得投标记录
				BigDecimal  priceSum = new BigDecimal(price);
				
				/*拼装供应商投标记录*/
				Bid bid = null;
				if(!oldBidList.isEmpty()){   //如果有投标记录，则修改
					bid = oldBidList.get(0);
				}else{
					bid = new Bid();
				}
				bid.setSupplier(supplier);
				bid.setProjCode(prjCode);
				bid.setProjName(projectName);
				bid.setProject(project);
				bid.setProjManagerName(projManager);
				bid.setBidQuoteSum(priceSum);
				
				/*拼装开标记录信息*/
				OpenBidRecord openBidRecord = null;
				openBidRecord = openBidRecordDaoHibernate.getOpenBidRecordBySupplierIdAndSubProjectId(packId, bid.getSupplier().getObjId());
				if(openBidRecord ==null){
					openBidRecord = new OpenBidRecord();
				}
				openBidRecord.setSellerName(bid.getSupplierName());
				openBidRecord.setOpenBRStatus(openStatus);
				openBidRecord.setBidId(bid.getObjId());
				openBidRecord.setSupplier(bid.getSupplier());
				openBidRecord.setQuoteSum(priceSum);
				openBidRecord.setCreateTime(new Date());
				openBidRecord.setCreateUser(AuthenticationHelper.getCurrentUser());
				openBidRecord.setOpenBRStatusMemo(openDesc);
				openBidRecord.setOpenBLinker(linker);
				openBidRecord.setOpenBLinkerTel(linkerTel);
				openBidRecord.setOpenRisEffective(supplierIsEffective);
				openBidRecord.setEffectiveMemo(supplierEffectiveMemo);
				openBidRecord.setSubProjId(pack.getObjId());
				if(!StringUtils.empty(openTime))
					openBidRecord.setCreateTime(DateUtil.parse(openTime));
				
				/*拼装开标一览表数据*/
				OpenbidGeneralview openbidGeneralview = openbidGeneralviewDaoHibernate.getOpenbidGeneralviewBySupplierIdAndPackId(supplier.getObjId(), packId);
				if(openbidGeneralview == null){
					openbidGeneralview = new OpenbidGeneralview();
				}
				openbidGeneralview.setBid(bid);
				openbidGeneralview.setOpenBidRecord(openBidRecord);
				openbidGeneralview.setBidQuotesum(new Float(price));
				openbidGeneralview.setSupplierId(supplier.getObjId());
				openbidGeneralview.setSupplierName(supplier.getOrgName());
				openbidGeneralview.setSubProj(pack);
				List<Element> bidFormElementList = supplierElement.element("bidForm").elements("item");
				List<OpenBidGeneralVitem> openBidGeneralVitemList = new ArrayList<OpenBidGeneralVitem>();
				boolean isContainTotoalPrice = false;//记录在bidForm——>item里是否包含“总报价”
				for(Element item : bidFormElementList){
					//String code = item.element("code").getTextTrim();
					String name = item.element("name").getTextTrim();
					String value = item.element("value").getTextTrim();
					
					if("总报价".equals(name)){
						isContainTotoalPrice = true;
					}
					
					OpenBidGeneralVitem openBidGeneralVitem = new OpenBidGeneralVitem();
					openBidGeneralVitem.setOpenbidGeneralview(openbidGeneralview);
					openBidGeneralVitem.setFactorName(name);
					openBidGeneralVitem.setRespValue(value);
					openBidGeneralVitemList.add(openBidGeneralVitem);
				}
				/*如果不包含总报价*/
				if(! isContainTotoalPrice){
					OpenBidGeneralVitem openBidGeneralVitem = new OpenBidGeneralVitem();
					openBidGeneralVitem.setFactorName("总报价");
					openBidGeneralVitem.setRespValue(price);
					openBidGeneralVitemList.add(openBidGeneralVitem);
				}
				
				this.saveOpenBidData(bid, openBid, openBidRecord, pack, project,price, openBidGeneralVitemList);
			}
			
			projectDaoHibernate.getHibernateTemplate().flush();
		} catch (Exception e) {
			operTag = false;
			operException = (e.getMessage() == null ? operException : e.getMessage());
			e.printStackTrace();
		}
		StringBuilder responseXML = new StringBuilder();
		responseXML.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
		responseXML.append("<uploadOpenBid  xmlns=\"http://www.gpcsoft.com\">");
		responseXML.append("<header>");
		responseXML.append("<operTag>".concat(operTag ? "Y" : "N").concat("</operTag>"));
		responseXML.append("<operDesc>".concat(operDesc).concat("</operDesc>"));
		responseXML.append("<operException>".concat(operException).concat("</operException>"));
		responseXML.append("</header>");
		responseXML.append("<body>");
		responseXML.append("</body>");
		responseXML.append("</uploadOpenBid>");
		
		logger.debug("OpenBidServiceImpl.uploadOpenBidInfo return String = ".concat(responseXML.toString()));
		return responseXML.toString();
	}
	
	//保存开标信息
	private void saveOpenBidData(Bid bid,OpenBid openBid, OpenBidRecord openBidRecord,Project pack,Project project,String price, List<OpenBidGeneralVitem> openBidGeneralVitemList){
		bidDao.save(bid);   //保存投标信息
		BidPackage oldBidpackage = bidPackageManager.getBidPackageByPackIdAndBidId(pack.getObjId(), bid.getObjId());
		BidPackage bidPackage = null;
		if(oldBidpackage!=null)  //如果存在则修改
			bidPackage = oldBidpackage;
		else
			bidPackage = new BidPackage();
		//pack.setObjId(pack.getObjId());
		bidPackage.setPack(pack);
		bidPackage.setBid(bid);
		Float priceSum = Float.valueOf(price);
		bidPackage.setQuotesum(priceSum);
		bidPackageManager.save(bidPackage);   //保存开标信息
		
		// 保存开标
		openBidDaoHibernate.save(openBid);
		
		// 保存开标记录
		openBidRecord.setOpenBId(openBid.getObjId());
		openBidRecordDaoHibernate.save(openBidRecord);
		
		/*保存开标一览表*/
		openBidGeneralVitemServiceImpl.saveOpenBidGeneralVitem(openBidGeneralVitemList, pack.getObjId(), openBidRecord.getSupplier().getObjId(), bid.getObjId(), openBidRecord.getObjId());
	}
	
	/**
	 * FuncName: uploadSubKey
	 * Description :上传开标子密钥       
     * @param request
	 * @param response
	 * @throws Exception void
	 * @author: caojz
	 * @Create Date:2011-8-2  下午01:45:02
	 * @Modifier: caojz
	 * @Modified Date:2011-8-2  下午01:45:02
	 */
	@RequestMapping(params="method=uploadSubKey")
	public String saveUploadSubKey(String prjCode, String packCode, String subKey, String timeStamp, String orgCode, String username, String password){
	    String errorMsg = "";
	    String supplierId = "";
	    String packId = "";
	    String bidPackId = "";
	    boolean isSuccess = true;
	    String exception = "";
		try{
		    OrgInfo supplier = userApiService.getOrgInfoByOrgCode(orgCode);
		    if(supplier != null){
		    	supplierId = supplier.getObjId();
		    }
		    Project project = projectService.findProjectByProjCode(prjCode);
			Project subProject = projectService.findProjectByProjCodeAndParent(packCode, project.getObjId());
			if(subProject==null){ 
				packId = project.getObjId();        //未分包，项目Id
			}else{
				packId = subProject.getObjId();     //分包，包组Id
			}
		    Bid bid = bidSubKeyService.getBidIdByProjCodeAndOrgId(prjCode, supplierId);
		    bidPackId = bid.getObjId();
		    BidPackage bidPackage = bidSubKeyService.getBidPackageByBidId(bidPackId, packId);
		    if(subKey == null || subKey.equals("")){
		    	isSuccess = false;
		    	errorMsg = "子密钥为空";
		    	throw new Exception(errorMsg);
		    }else{
		    	 List<BidSubKey> bidSubKeys = bidSubKeyService.getBidSubKeysById(bidPackage.getObjId());//根据投标记录ID得到开标子密钥集合 
		    	 BidSubKey bidSubKey = null;
		    	 if(bidSubKeys != null && !bidSubKeys.isEmpty()) //上传过子密钥
		    		 bidSubKey = bidSubKeys.get(0);
		    	 else
		    		 bidSubKey = new BidSubKey();
		    	 
	    		 bidSubKey.setBidPackage(bidPackage);
	             bidSubKey.setSubKey(subKey);
	             bidSubKey.setPackId(packId);
	             bidSubKeyService.saveOrUpdate(bidSubKey);
		     }
		    errorMsg = "子密钥上传成功";
		    
		    /*上传开标密钥中,将开标状态改为未开标*/
		    OpenBidRecord openBidRecord = openBidRecordDaoHibernate.getOpenBidRecordBySupplierIdAndSubProjectId(packId, supplier.getObjId());
		    if(openBidRecord != null){
		    	openBidRecord.setOpenBRStatus("00");//未开标
		    	openBidRecordDaoHibernate.save(openBidRecord);
		    }
		    
		    projectDaoHibernate.getHibernateTemplate().flush();
		} catch (Exception e) {
			isSuccess = false;
			exception = (e.getMessage() == null ? exception : e.getMessage());
			e.printStackTrace();
		}
	    StringBuilder responseXML = new StringBuilder();
	    responseXML.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
		responseXML.append("<uploadSubKey xmlns=\"http://www.gpcsoft.com\">");
		responseXML.append("<header>");
		responseXML.append("<operTag>"+(isSuccess == true ? "Y" : "N")+"</operTag>");
		responseXML.append("<operDesc>"+errorMsg+"</operDesc>");
		responseXML.append("<operException>"+exception+"</operException>");
		responseXML.append("</header>");
		responseXML.append("</uploadSubKey>"); 
		logger.debug("BidHttpService  uploadSubKey start=============================================\n"+responseXML.toString());
		logger.debug("BidHttpService  uploadSubKey end=============================================\n");
		return responseXML.toString();
	}
	
	/**
	 * FuncName: downSubKey
	 * Description :下载开标子密钥       
	 * @param prjCode   项目编号
	 * @param packCode   包组编号
	 * @param orgCode    机构编号
	 * @param subKey     子钥串
	 * @param username   用户名
	 * @param password   密码
	 * @throws Exception void
	 * @author: caojz
	 * @Create Date:2011-8-2  下午01:45:02
	 * @Modifier: caojz
	 * @Modified Date:2011-8-2  下午01:45:02
	 */
	@RequestMapping(params="method=downSubKey")
	public String downSubKey(String prjCode,String packCode,String orgCode,String caId,String username,	String password){
	    String errorMsg = "";
	    String supplierId = "";
	    String packId = "";
	    String bidPackId = "";
	    boolean isSuccess = true;
	    boolean isSuccess2 = true;//用以是否成功开标
	    String exception = "";
	    List<BidSubKey> subKeys = new ArrayList<BidSubKey>();
	    try{
		    OrgInfo supplier = userApiService.getOrgInfoByOrgCode(orgCode);
		    if(supplier != null){
		    	supplierId = supplier.getObjId();
		    }
		    Project project = projectService.findProjectByProjCode(prjCode);
			Project subProject = projectService.findProjectByProjCodeAndParent(packCode, project.getObjId());
			if(subProject==null){ 
				packId = project.getObjId();        //未分包，项目Id
			}else{
				packId = subProject.getObjId();     //分包，包组Id
			}
		    Bid bid = bidSubKeyService.getBidIdByProjCodeAndOrgId(prjCode, supplierId);
		    bidPackId = bid.getObjId();
		    BidPackage bidPackage = bidSubKeyService.getBidPackageByBidId(bidPackId, packId);
		    
		    if(bidPackage == null){
			    	isSuccess = false;
			    	errorMsg = "未找到下载信息";
		    }else{
			    subKeys = bidSubKeyService.getBidSubKeysById(bidPackage.getObjId());
			    if(!subKeys.isEmpty()){
			    	  isSuccess = true;
			    	  errorMsg = "下载子密钥成功";
			    }else{
			    	isSuccess = false;
				    errorMsg = "下载子密钥失败";
			    }
		      }
		    
		    projectDaoHibernate.getHibernateTemplate().flush();
	    }catch (Exception e) {
			isSuccess = false;
			exception = e.getMessage();
		}
	    StringBuilder responseXML = new StringBuilder();
	    responseXML.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
		responseXML.append("<downSubKey xmlns=\"http://www.gpcsoft.com\">");
		responseXML.append("<header>");
		responseXML.append("<operTag>"+(isSuccess == true ? "Y" : "N")+"</operTag>");
		responseXML.append("<operDesc>"+errorMsg+"</operDesc>");
		responseXML.append("<operException>"+exception+"</operException>");
		responseXML.append("</header>");
		responseXML.append("<body>");
		responseXML.append("<openFlag>");
		responseXML.append((subKeys.size()>0)?"01":"00");
		responseXML.append("</openFlag>");
		responseXML.append("<subKey>");
		responseXML.append(subKeys.size()>0?subKeys.get(0).getSubKey():"");
		responseXML.append("</subKey>");
		responseXML.append("</body>");
		responseXML.append("</downSubKey>");
		logger.debug("BidSoapService    downSubKey====================================start\n"+responseXML.toString());
		logger.debug("BidSoapService    downSubKey====================================end\n");
		return responseXML.toString();
	}

	/**
	 * FuncName: getPrjOpenStatus
	 * Description : 从执行平台获取项目/包件的开标信息   
	 * @param prjCode
	 * @param packCode
	 * @param orgCode
	 * @param username
	 * @param password
	 * @return
	 * @author: zhouzhanghe
	 * @Create Date:2011-11-10 10:51
	 */
	public String getPrjOpenStatus(String prjCode, String packCode,
			String orgCode, String username, String password) {

		boolean operTag = true;
		String operDesc = "";
		String operException = "";
		StringBuilder openStatusInfo = new StringBuilder();
		try {
			/*查找 OpenBidSign关联的项目或包组*/
			String tenderId = "";
			Project project = null;
			project = projectDao.findProjectByProjCode(prjCode);
			if(project == null){
				operDesc = "根据项目编号".concat(prjCode).concat("查找项目失败.");
				throw new Exception(operDesc);
			}
			tenderId = project.getObjId();
			
			/*如果项目是终止或暂停*/
			if(ProjImplStatusEnum.STOP.equals(project.getProjImplStatus()) || ProjImplStatusEnum.SUSPEND.equals(project.getProjImplStatus())){
				operDesc = project.getProjImplStatusCN();
				throw new Exception(operDesc); 
			}
			
			/*获取包组的状态*/
			List<Project> subProjectlist = projectService.getSubProjectByProjectId(tenderId);
			StringBuilder packs = new StringBuilder();
			for(Project subProject : subProjectlist){
				packs.append("<pack>");
				packs.append("<code>".concat(subProject.getProjCode()).concat("</code>"));
				packs.append("<openStatus>".concat(getOpenStatus(subProject)).concat("</openStatus>"));
				packs.append("</pack>");
			}
			
			openStatusInfo.append("<openStatusInfo>");
			openStatusInfo.append("<prjCode>".concat(project.getProjCode()).concat("</prjCode>"));
			openStatusInfo.append("<openStatus>".concat(getOpenStatus(project)).concat("</openStatus>"));
			openStatusInfo.append("<packs>");
			openStatusInfo.append(packs.toString());
			openStatusInfo.append("</packs>");
			openStatusInfo.append("</openStatusInfo>");
			
			projectDaoHibernate.getHibernateTemplate().flush();
		}catch (Exception e) {
			operTag = false;
			operException = (e.getMessage() == null ? operException : e.getMessage());
			openStatusInfo.delete(0, openStatusInfo.length());
		}
		
	    StringBuilder responseXML = new StringBuilder();
	    responseXML.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
		responseXML.append("<getPrjOpenStatus xmlns=\"http://www.gpcsoft.com\">");
		responseXML.append("<header>");
		responseXML.append("<operTag>".concat(operTag ? "Y" : "N").concat("</operTag>"));
		responseXML.append("<operDesc>".concat(operDesc).concat("</operDesc>"));
		responseXML.append("<operException>".concat(operException).concat("</operException>"));
		responseXML.append("</header>");
		responseXML.append("<body>");
		responseXML.append(openStatusInfo.toString());
		responseXML.append("</body>");
		responseXML.append("</getPrjOpenStatus>");
		return responseXML.toString();
	}
	
	/**
	 * 获取Project的开标状态
	 * @param project
	 * @return  00未开标 01开标中 02开标结束
	 * @author zhouzhanghe
	 * @Create Date:2011-11-10 10:51
	 */
	private String getOpenStatus(Project project){
		StringBuilder openStatus = new StringBuilder();;
		List<OpenBid> openBidList = openBidDaoHibernate.getOpenBidBySubProjectId(project.getObjId());
		OpenBid openBid = null;
		if(openBidList != null && openBidList.size() > 0 && openBidList.get(0) != null)
			openBid = openBidList.get(0);
		if(openBid == null || openBid.getOpenBStatus() == null){
			openStatus.delete(0, openStatus.length());
			openStatus.append("00");//未开标
		}else if("01".equals(openBid.getOpenBStatus())){
			openStatus.delete(0, openStatus.length());
			openStatus.append("01");//开标中
		}else if(!StringUtils.empty(openBid.getOpenBStatus())){//01：有效； 02废标
			openStatus.delete(0, openStatus.length());
			openStatus.append("02");//开标结束
		} 
		
		return openStatus.toString();
	}
}
