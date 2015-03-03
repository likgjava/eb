/**
 * 
 */
package com.gpcsoft.epp.webService.webService.ue.http;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.utils.DateUtil;
import com.gpcsoft.core.utils.MathUtil;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.bid.domain.BailRecord;
import com.gpcsoft.epp.bid.service.BailRecordService;
import com.gpcsoft.epp.common.domain.AuditStatusEnum;
import com.gpcsoft.epp.common.domain.DataItemSignUprecord;
import com.gpcsoft.epp.common.domain.EsExceptionEnum;
import com.gpcsoft.epp.common.manager.UserApiManager;
import com.gpcsoft.epp.common.service.UserApiService;
import com.gpcsoft.epp.common.utils.StringUtil;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.domain.ProjectRule;
import com.gpcsoft.epp.project.service.ProjectService;
import com.gpcsoft.epp.signuprecord.domain.SignUpRespone;
import com.gpcsoft.epp.signuprecord.domain.SignUprecord;
import com.gpcsoft.epp.signuprecord.domain.SignUprecordEnum;
import com.gpcsoft.epp.signuprecord.service.SignUprecordService;
import com.gpcsoft.epp.webService.common.util.XmlUtil;
import com.gpcsoft.epp.webService.ueSystem.bulletin.dto.BulletinDTO;
import com.gpcsoft.srplatform.auth.domain.User;

/**
 * @author liuk
 *
 */
@SuppressWarnings("unchecked")
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={BulletinDTO.class})
@RequestMapping("/CommonHttpService.do")//页面请求路径,可修改
public class CommonHttpService extends AnnotationMultiController{

	@Autowired(required=true) @Qualifier("userApiManagerImpl")
	private UserApiManager userApiManager;
	
	@Autowired(required=true) @Qualifier("signUprecordServiceImpl")
	private SignUprecordService signUprecordService;
	
	@Autowired(required=true) @Qualifier("projectServiceImpl")
	private ProjectService projectService;
	
	@Autowired(required=true) @Qualifier("userApiServiceImpl")
	private UserApiService userApiService;
	
	@Autowired(required = true) @Qualifier("bailRecordServiceImpl")
	private BailRecordService bailRecordService;
	/**
	 * FuncName: getTime
	 * Description :  获得项目当前时间
	 * @param 
	 * @param request
	 * @param response
	 * @throws Exception void
	 * @author: liuke
	 * @Create Date:2011-7-26  下午02:11:57
	 * @Modifier: liuke
	 * @Modified Date:2011-7-26  下午02:11:57
	 */
	@RequestMapping(params="method=getTime")
	public void getTime(HttpServletRequest request, HttpServletResponse response) throws Exception{
		logger.debug("\nes CommonHttpService||getTime\n");
		Date date = new Date();
		StringBuffer responseXml = new StringBuffer();
		String errorMessage = "";
		String time ="";
		boolean success = true;
		try {
			time = DateUtil.format(date, "yyyy-MM-dd HH:mm:ss");
		} catch (Exception e) {
			success =false;
			errorMessage = e.getMessage();
		}
		responseXml.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
		responseXml.append("<getTime xmlns=\"http://www.gpcsoft.com\">");
		responseXml.append("<header>");
		responseXml.append("<operTag>"+(success?"Y":"N")+"</operTag>");
		responseXml.append("<operDesc>"+(success?"成功":"失败")+"</operDesc>");
		responseXml.append("<operException>"+errorMessage+"</operException>");
		responseXml.append("</header>");
		responseXml.append("<body>");
		responseXml.append("<time>"+time+"</time>");
		responseXml.append("</body>");
		responseXml.append("</getTime>");
		logger.debug("CommonHttpService  getTime start=============================================\n"+responseXml.toString());
		logger.debug("CommonHttpService  getTime end=============================================\n");
		write(responseXml.toString(), response);
	}
	
	
	/**
	 * FuncName: getApplyItem
	 * Description :获取报名所需数据
	 * @param 
	 * @param request
	 * @param response
	 * @throws Exception void
	 * @author: liuke
	 * @Create Date:2011-7-28  下午04:19:25
	 * @Modifier: liuke
	 * @Modified Date:2011-7-28  下午04:19:25
	 */
	@RequestMapping(params="method=getApplyItem")
	public void getApplyItem(HttpServletRequest request, HttpServletResponse response) throws Exception{
		logger.debug("\nes CommonHttpService||getApplyItem\n");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String projCode = request.getParameter("prjCode");
		String packCode = request.getParameter("packCode");
		List<DataItemSignUprecord> dataItemDTOList = new ArrayList<DataItemSignUprecord>();
		String signUprecordInfo = "";
		String supplierId = "";
		String errorMessage = "";
		boolean isSuccess = true;//是否执行成功       
		try {
			Project project = projectService.findProjectByProjCode(projCode);
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
			
		SignUprecord oldSignUprecord = signUprecordService.getSignUprecordByprojectAndSupplierId(project.getObjId(), supplierId);
		DataItemSignUprecord linkerManDataItem = new DataItemSignUprecord();
		linkerManDataItem.setId("linker");
		linkerManDataItem.setName("联系人");
		if(oldSignUprecord!=null){
			linkerManDataItem.setValue(oldSignUprecord.getLinker());
		}else{
			linkerManDataItem.setValue("");
		}
		linkerManDataItem.setType("text");
		linkerManDataItem.setIsMust("true");
		linkerManDataItem.setLength("50");
		linkerManDataItem.setRemark("");
		dataItemDTOList.add(linkerManDataItem);
		
		DataItemSignUprecord linkerIdCardDataItem = new DataItemSignUprecord();
		linkerIdCardDataItem.setId("idCard");
		linkerIdCardDataItem.setName("身份证号");
		if(oldSignUprecord!=null){
			linkerIdCardDataItem.setValue(oldSignUprecord.getIdCard());
		}else{
			linkerIdCardDataItem.setValue("");
		}
		linkerIdCardDataItem.setType("text");
		linkerIdCardDataItem.setIsMust("true");
		linkerIdCardDataItem.setLength("100");
		linkerIdCardDataItem.setRemark("");
		dataItemDTOList.add(linkerIdCardDataItem);
		
		DataItemSignUprecord linkTelDataItem = new DataItemSignUprecord();
		linkTelDataItem.setId("linkerTel");
		linkTelDataItem.setName("联系电话");
		if(oldSignUprecord!=null){
			linkTelDataItem.setValue(oldSignUprecord.getLinkerTel());
		}else{
			linkTelDataItem.setValue("");
		}
		linkTelDataItem.setType("text");
		linkTelDataItem.setIsMust("true");
		linkTelDataItem.setLength("50");
		linkTelDataItem.setRemark("");
		dataItemDTOList.add(linkTelDataItem);
		
		DataItemSignUprecord linkAddressDataItem = new DataItemSignUprecord();
		linkAddressDataItem.setId("address");
		linkAddressDataItem.setName("联系地址");
		if(oldSignUprecord!=null){
			linkAddressDataItem.setValue(oldSignUprecord.getAddress());
		}else{
			linkAddressDataItem.setValue("");
		}
		linkAddressDataItem.setType("text");
		linkAddressDataItem.setIsMust("true");
		linkAddressDataItem.setLength("100");
		linkAddressDataItem.setRemark("");
		dataItemDTOList.add(linkAddressDataItem);

		DataItemSignUprecord zipCodeDataItem = new DataItemSignUprecord();
		zipCodeDataItem.setId("zipCode");
		zipCodeDataItem.setName("邮编");
		if(oldSignUprecord!=null){
			zipCodeDataItem.setValue(oldSignUprecord.getZipCode());
		}else{
			zipCodeDataItem.setValue("");
		}
		zipCodeDataItem.setType("text");
		zipCodeDataItem.setIsMust("true");
		zipCodeDataItem.setLength("50");
		zipCodeDataItem.setRemark("");
		dataItemDTOList.add(zipCodeDataItem);
		
		DataItemSignUprecord memoDataItem = new DataItemSignUprecord();
		memoDataItem.setId("memo");
		memoDataItem.setName("备注");
		if(oldSignUprecord!=null){
			memoDataItem.setValue(oldSignUprecord.getMemo());
		}else{
			memoDataItem.setValue("");
		}
		memoDataItem.setType("text");
		memoDataItem.setIsMust("false");
		memoDataItem.setLength("500");
		memoDataItem.setRemark("");
		dataItemDTOList.add(memoDataItem);
		
		signUprecordInfo = this.getSignUprecordInfo(project, supplierId, packCode);		
		
		} catch (Exception e) {
		  e.printStackTrace();
		  errorMessage = EsExceptionEnum.ES_SYSTEM_ERROR;
		}
		StringBuffer responseXml = new StringBuffer();
		responseXml.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
		responseXml.append("<getApplyItem xmlns=\"http://www.gpcsoft.com\">");
		responseXml.append("<header>");
		responseXml.append("<operTag>"+(isSuccess == true ? "Y" : "N")+"</operTag>");
		responseXml.append("<operDesc>"+errorMessage+"</operDesc>");
		responseXml.append("</header>");
		responseXml.append("<body>");
		responseXml.append("<applyInfo>");
		responseXml.append("<project>"+signUprecordInfo+"</project>");
		responseXml.append("<dataItemList>");
		for(DataItemSignUprecord dataItem : dataItemDTOList){
			responseXml.append(XmlUtil.objectToXml(dataItem));
		}
		responseXml.append("</dataItemList>");
		responseXml.append("</applyInfo>");
		responseXml.append("</body>");
		responseXml.append("</getApplyItem>");
		logger.debug("CommonHttpService  getApplyItem start=============================================\n"+responseXml.toString());
		logger.debug("CommonHttpService  getApplyItem end=============================================\n");
		write(responseXml.toString(), response);
	}
	
	/**
	 * FuncName: getSignUprecordInfo
	 * Description :  得到包组报名信息
	 * @param 
	 * @return String
	 * @author: liuke
	 * @Create Date:2011-7-29  下午03:54:27
	 * @Modifier: liuke
	 * @Modified Date:2011-7-29  下午03:54:27
	 */
	private String getSignUprecordInfo(Project project,String supplierId,String packCode)throws Exception{
		List<Project>  subProjs = projectService.getSubProjectByProjectId(project.getObjId());
		if(subProjs.isEmpty()){   //如果该项目没有分包
			subProjs = new ArrayList<Project>();
			subProjs.add(project);
		   }
		List<SignUprecord> signUprecordList = signUprecordService.getSignUprecordByprojectId(project.getObjId());
		StringBuffer signBuff = new StringBuffer();
		if(packCode==null||"".equals(packCode)){  //如果没有传入packCode，则查询所有的包组报名信息
			for (Iterator iterator = subProjs.iterator(); iterator.hasNext();) {//遍历所有包组
				Project subProj = (Project) iterator.next();
				signBuff.append("<pack>");
				signBuff.append("<code>"+subProj.getProjCode()+"</code>");
				signBuff.append("<name>"+subProj.getProjName()+"</name>");
				if(signUprecordList.size()==0){
					signBuff.append("<isApplied>N</isApplied>");
					signBuff.append("<auditStatus>"+SignUprecordEnum.NO_SIGNUP_RECORD+"</auditStatus>");
					signBuff.append("<memo></memo>");
				}else{
					boolean flag = false; //标示是否存在记录
					for (Iterator iterator1 = signUprecordList.iterator(); iterator1.hasNext();) {
						SignUprecord signUprecord = (SignUprecord) iterator1.next();
						if(supplierId.equals(signUprecord.getSupplier().getObjId())&&subProj.getObjId().equals(signUprecord.getProject().getObjId())){//该组有效用户的报名记录
							flag = true;
							signBuff.append("<isApplied>Y</isApplied>");
							String auditStatus =SignUprecordEnum.NO_SIGNUP_RECORD;
							BailRecord bailRecord = bailRecordService.getBailRecordBySignUprecord(signUprecord.getObjId());
							if(AuditStatusEnum.WAIT_AUDIT.equals(signUprecord.getAuditStatus())){ //报名审核中
								auditStatus = SignUprecordEnum.SIGNUP_RECORD_FOR_AUDIT;
							}else if(AuditStatusEnum.WAIT_AUDIT.equals(signUprecord.getAuditStatus())&&bailRecord!=null){//报名审核通过，需缴纳标书费
								auditStatus = SignUprecordEnum.SIGNUP_RECORD_FOR_PAY;
							}else if(AuditStatusEnum.AUDIT_PASS.equals(signUprecord.getAuditStatus())){//报名成功
								auditStatus = SignUprecordEnum.SIGNUP_RECORD_SUCCESS;
							}
							signBuff.append("<auditStatus>"+auditStatus+"</auditStatus>");
							signBuff.append("<memo>"+signUprecord.getMemo()+"</memo>");
						}
					}
					if(!flag){ //如果没有报名
						signBuff.append("<isApplied>N</isApplied>");
						signBuff.append("<auditStatus>"+SignUprecordEnum.NO_SIGNUP_RECORD+"</auditStatus>");
						signBuff.append("<memo></memo>");
					}
				}
				signBuff.append("</pack>");
			}
		}else{ //如果传入packCode，则只查询本包组报名信息
				for (Iterator iterator = subProjs.iterator(); iterator.hasNext();) {//遍历所有包组
					Project subProj = (Project) iterator.next();
					if(packCode.equals(subProj.getProjCode())){
						signBuff.append("<pack>");
						signBuff.append("<code>"+subProj.getProjCode()+"</code>");
						signBuff.append("<name>"+subProj.getProjName()+"</name>");
						if(signUprecordList.size()==0){
							signBuff.append("<isApplied>N</isApplied>");
							signBuff.append("<auditStatus>"+SignUprecordEnum.NO_SIGNUP_RECORD+"</auditStatus>");
							signBuff.append("<memo></memo>");
						}else{
							boolean flag = false; //标示是否存在记录
							for (Iterator iterator1 = signUprecordList.iterator(); iterator1.hasNext();) {
								SignUprecord signUprecord = (SignUprecord) iterator1.next();
								if(supplierId.equals(signUprecord.getSupplier().getObjId())&&subProj.getObjId().equals(signUprecord.getProject().getObjId())){//该组有效用户的报名记录
									flag = true;
									signBuff.append("<isApplied>Y</isApplied>");
									String auditStatus =SignUprecordEnum.NO_SIGNUP_RECORD;
									BailRecord bailRecord = bailRecordService.getBailRecordBySignUprecord(signUprecord.getObjId());
									if(AuditStatusEnum.WAIT_AUDIT.equals(signUprecord.getAuditStatus())){ //报名审核中
										auditStatus = SignUprecordEnum.SIGNUP_RECORD_FOR_AUDIT;
									}else if(AuditStatusEnum.WAIT_AUDIT.equals(signUprecord.getAuditStatus())&&bailRecord!=null){//报名审核通过，需缴纳标书费
										auditStatus = SignUprecordEnum.SIGNUP_RECORD_FOR_PAY;
									}else if(AuditStatusEnum.AUDIT_PASS.equals(signUprecord.getAuditStatus())){//报名成功
										auditStatus = SignUprecordEnum.SIGNUP_RECORD_SUCCESS;
									}
									signBuff.append("<auditStatus>"+auditStatus+"</auditStatus>");
									signBuff.append("<memo>"+signUprecord.getMemo()+"</memo>");
								}
							}
							if(!flag){ //如果没有报名
								signBuff.append("<isApplied>N</isApplied>");
								signBuff.append("<auditStatus>"+SignUprecordEnum.NO_SIGNUP_RECORD+"</auditStatus>");
								signBuff.append("<memo></memo>");
							}
						}
						signBuff.append("</pack>");
					}
				}
			}
		logger.debug("CommonHttpService  getSignUprecordInfo start=============================================\n"+signBuff.toString());
		logger.debug("CommonHttpService  getSignUprecordInfo end=============================================\n");
		return signBuff.toString();
	}
	
	
	/**
	 * FuncName: sendPrjApply
	 * Description :  项目报名
	 * @param 
	 * @param request
	 * @param response void
	 * @author: liuke
	 * @Create Date:2011-8-1  上午10:04:10
	 * @Modifier: liuke
	 * @Modified Date:2011-8-1  上午10:04:10
	 */
	@RequestMapping(params="method=sendPrjApply")
	public void sendPrjApply(HttpServletRequest request, HttpServletResponse response)throws Exception{
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String projCode = request.getParameter("prjCode");
		String packCode = request.getParameter("packCode");
		String orgCode = request.getParameter("orgCode");
		String content = request.getParameter("content");
		String errorMessage = "";
		String operDesc = "";
		boolean isSuccess = true;//是否执行成功       
		try {
			Document document;  
			document = DocumentHelper.parseText(content);
			Element element =  document.getRootElement();
			List<DataItemSignUprecord> dataItemDTOList = new ArrayList<DataItemSignUprecord>();
			OrgInfo supplier =  userApiService.getOrgInfoByOrgCode(orgCode);
			User  user = supplier.getUser();
			Project project = projectService.findProjectByProjCode(projCode);
			ProjectRule rule = projectService.getProjectRuleByProjectId(project.getObjId());
			Date date = new Date();
			Date signUpETime = rule.getSignUpETime();  //报名结束时间
			Date signUpSTime = rule.getSignUpSTime();  //报名开始时间
			if(date.before(signUpSTime)){  //过了报名结束时间
				operDesc = "报名时间未到！";
				isSuccess = false;
			}else if(date.after(signUpETime)){  //过了报名结束时间
				operDesc = "报名时间已过！";
				isSuccess = false;
			}
			
			String subProjIds = "";
			String[] packCodeArray = packCode.split(",");
			for (String pack:packCodeArray) {
				Project subProject = projectService.findProjectByProjCodeAndParent(pack, project.getObjId());
				if(subProject!=null){
					subProjIds += subProject.getObjId()+",";
				}
			}
			List <Element> dataItemElementList = element.element("body").element("dataItemList").elements("dataItem");
			for(Element dataItemElement:dataItemElementList){ //组装报名信息
				DataItemSignUprecord dataItem = new DataItemSignUprecord();	
				dataItem.setId(dataItemElement.element("id").getText());
				dataItem.setName(dataItemElement.element("name").getText());
				dataItem.setValue(dataItemElement.element("value").getText());
				dataItem.setType(dataItemElement.element("type").getText());
				dataItem.setIsMust(dataItemElement.element("isMust").getText());
				dataItem.setLength(dataItemElement.element("length").getText());
				dataItemDTOList.add(dataItem);
			}
			SignUprecord signUprecord = new SignUprecord();
			signUprecord.setSupplier(supplier);
			signUprecord.setSupplierName(supplier.getOrgName());
			signUprecord.setApplyDate(new Date());
			signUprecord.setProject(project);
			signUprecord.setAuditStatus(AuditStatusEnum.WAIT_AUDIT);
			for (Iterator iterator = dataItemDTOList.iterator(); iterator.hasNext();) {
				DataItemSignUprecord data = (DataItemSignUprecord) iterator.next();
				if("linker".equals(data.getId())){   //联系人
					signUprecord.setLinker(data.getValue());
				}
				if("linkerTel".equals(data.getId())){   //联系电话
					signUprecord.setLinkerTel(data.getValue());
				}
				if("idCard".equals(data.getId())){   //身份证号
					signUprecord.setIdCard(data.getValue());
				}
				if("address".equals(data.getId())){   //联系地址
					signUprecord.setAddress(data.getValue());
				}
				if("zipCode".equals(data.getId())){   //邮编
					signUprecord.setZipCode(data.getValue());
				}
				if("memo".equals(data.getId())){   //备注
					signUprecord.setMemo(data.getValue());
				}
			}
			if(signUprecord.getUser()==null){
				signUprecord.setUser(user);
			}
			signUprecordService.saveSignUprecordAndSignUpRespone(signUprecord, new ArrayList<SignUpRespone>(),(subProjIds.length()>0)?subProjIds.substring(0, subProjIds.length()-1):"");
		} catch (Exception e) {
			isSuccess = false;
			e.printStackTrace();
			errorMessage = e.getMessage();
		}
		StringBuffer responseXml = new StringBuffer();
		responseXml.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
		responseXml.append("<sendPrjApply xmlns=\"http://www.gpcsoft.com\">");
		responseXml.append("<header>");
		responseXml.append("<operTag>"+(isSuccess == true ? "Y" : "N")+"</operTag>");
		responseXml.append("<operDesc>"+operDesc+"</operDesc>");
		responseXml.append("<operException>"+errorMessage+"</operException>");
		responseXml.append("</header>");
		responseXml.append("<body></body>");
		responseXml.append("</sendPrjApply>");
		logger.debug("CommonHttpService  sendPrjApply start=============================================\n"+responseXml.toString());
		logger.debug("CommonHttpService  sendPrjApply end=============================================\n");
		write(responseXml.toString(), response);
	}
}
