/**
 * 
 */
package com.gpcsoft.pubservice.webService.webService.gxoa.ws;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.bulletin.domain.BulletinTypeEnum;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.project.domain.ProjProcessStatusEnum;
import com.gpcsoft.epp.webService.common.util.XmlUtil;
import com.gpcsoft.pubservice.webService.webService.gxoa.ws.dto.BulletinDTO;
import com.gpcsoft.pubservice.webService.webService.gxoa.ws.dto.ProjectDTO;
import com.gpcsoft.pubservice.webService.webService.gxoa.ws.dto.PurchaseFileDTO;
import com.gpcsoft.pubservice.webService.webService.gxoa.ws.service.ProjectExtService;

/**
 * @author sunl
 *
 */
/** 
  *  Comments: <strong>SyncProjectService</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   agreement                    					          
  *  <br/>Module ID: 公共服务平台     		
  *  <br/>Create Date：2011-8-2 下午05:58:04 by liangxj    					                            
  *  <br/>Modified Date:  2011-8-2 下午05:58:04 by liangxj                                   
  *<p>@since 0.5
  *   @version: 0.5 
  */ 
@SuppressWarnings("unchecked")
@Controller//标识为控制器
@Scope("request")
@RequestMapping("/SyncProjectService.do")//页面请求路径,可修改
public class SyncProjectService extends AnnotationMultiController {
	
	private static String SYNC_PROJECT = "sync_project";//立项
	
	private static String SYNC_FILE = "sync_file";//招标文件
	
	private static String SYNC_BULLETIN = "sync_bulletin";//招标公告
	
	private static String SYNC_CHENGQING_BULLETIN = "sync_chengqing_bulletin";//澄清公告
	
	private static String SYNC_BUYER = "sync_buyer";//采购单位
	
	private static String SYNC_REMOVE_BUYER = "sync_remove_buyer";//删除采购单位
	
	private static String SYNC_ORG = "sync_org";//组织机构
	
	private static String SYNC_REMOVE_ORG = "sync_remove_org";//删除组织机构
	
	private static String RESULT_PROJECT = "result_project";//立项结果
	
	private static String RESULT_FILE = "result_file";//招标文件同步结果
	
	private static String RESULT_BULLETIN = "result_bulletin";//招标公告同步结果
	
	private static String RESULT_CHENGQING_BULLETIN = "result_chengqing_bulletin";//澄清公告同步结果
	
	private static String RESULT_BUYER = "result_buyer";//采购单位同步结果
	
	private static String RESULT_REMOVE_BUYER = "result_remove_buyer";//采购单位同步结果
	
	private static String RESULT_ORG = "result_org";//组织机构同步结果
	
	private static String RESULT_REMOVE_ORG = "result_remove_org";//组织机构删除结果
	
	public static String PREFIX_WTDW = "wtdw_";//委托单位ID前缀
	public static String PREFIX_GXZB = "GXZB_";//员工和用户ID前缀
	
	private ProjectExtService projectExtService;
	
	public SyncProjectService(){
		projectExtService = (ProjectExtService) FrameBeanFactory.getBean("projectExtServiceImpl");
	}
	
	/** 
	 * Description :  同步立项
	 * Create Date: 2011-7-29上午08:51:40 by sunl  Modified Date: 2011-7-29上午08:51:40 by sunl
	 * @param   xmlContent为立项数据项字符串，包含项目编号、项目名称等信息
	 * @return  
	 * @Exception   
	 */
	public String syncProjectInfo(String xmlContent) throws Exception{
		boolean isSuccess = true;
		ProjectDTO projDTO = new ProjectDTO();
		String errorMessage = "";
		
		/*校验xml格式*/
		Object returnObj = validXml(xmlContent,ProjectDTO.class);
		
		/*有错误信息，返回*/
		if(returnObj instanceof String) {
			isSuccess =false;
			errorMessage = (String)returnObj;
			//获取的项目编号
			projDTO.setProjCode(returnObj.toString().split("_")[0]);
		} else {
			projDTO = (ProjectDTO)returnObj; 
		}
		
		/*保存xml信息*/
		projectExtService.saveXml(projDTO.getProjCode(), SYNC_PROJECT, xmlContent);
		
		String projId = "";
		
		/*保存立项信息*/
		if(returnObj instanceof ProjectDTO) {
			try {
				//清理立项环节的数据
				projectExtService.removeStepProject(projDTO.getProjCode());
			} catch (Exception ce) {
				errorMessage = errorMessage + "；/n/r;在清理立项数据时出错：" + ce.getMessage();
				isSuccess =false;
			}
			
			if(isSuccess){
				try {
					projId = projectExtService.saveProjectByXml(xmlContent);
				} catch (Exception ce) {
					errorMessage = errorMessage + "；/n/r;在同步项目信息时出错：" + ce.getMessage();
					isSuccess =false;
				}
			}
			
			if(isSuccess){
				try {	
					//创建项目计划
					projectExtService.createProjectPlan(projId);
				} catch (Exception ce) {
					errorMessage = errorMessage + "；/n/r;在创建项目计划时出错：" + ce.getMessage();
					isSuccess =false;
				}
			}
			
//			if(isSuccess){
//				try {	
//					/**更新项目状态-设置项目规则*/
//					projectExtService.saveProjProcessStatus(projId,ProjProcessStatusEnum.DESIGNATED_PROJECT_RULE,ProjProcessStatusEnum.getCN(ProjProcessStatusEnum.DESIGNATED_PROJECT_RULE));
//				} catch (Exception ce) {
//					errorMessage = errorMessage + "；/n/r;在更新项目状态时出错：" + ce.getMessage();
//					isSuccess =false;
//				}
//			}
		}
	
		//返回同步结果字符串
		String result = syncResult(isSuccess,errorMessage);
		
		/*保存xml信息*/
		projectExtService.saveResult(projDTO.getProjCode(), RESULT_PROJECT, result);

		return result;
	}

	/** 
	 * Description :   同步招标文件
	 * Create Date: 2011-8-1上午09:24:12 by sunl  Modified Date: 2011-8-1上午09:24:12 by sunl
	 * @param   contentXML为招标文件数据项字符串，包含分包信息、保证金价格等信息
	 * @return  
	 * @Exception   
	 */
	public String syncPurchaseFileInfo(String xmlContent) throws Exception{
		boolean isSuccess = true;
		PurchaseFileDTO projDTO = new PurchaseFileDTO();
		String errorMessage = "";
		
		/*校验xml格式*/
		Object returnObj = validXml(xmlContent,PurchaseFileDTO.class);
		
		/*有错误信息，返回*/
		if(returnObj instanceof String) {
			isSuccess =false;
			errorMessage = (String)returnObj;
			//获取的项目编号
			projDTO.setProjCode(returnObj.toString().split("_")[0]);
		} else {
			projDTO = (PurchaseFileDTO)returnObj; 
		}
		
		/*保存xml信息*/
		projectExtService.saveXml(projDTO.getProjCode(), SYNC_FILE, xmlContent);
		
		/*保存招标文件信息*/
		if(returnObj instanceof PurchaseFileDTO) {
			String projId = "";
			try {
				//清理招标文件环节的数据
				projectExtService.removeStepPurchaseFile(projDTO.getProjCode());
			} catch (Exception ce) {
				errorMessage = errorMessage + "；/n/r;在清理招标文件数据时出错：" + ce.getMessage();
				isSuccess =false;
			}
			
			if(isSuccess){
				try {
					//同步招标文件数据
					projId = projectExtService.savePurchaseFileByXml(xmlContent);
				} catch (Exception ce) {
					errorMessage = errorMessage + "；/n/r;在同步招标文件数据时出错：" + ce.getMessage();
					isSuccess =false;
				}	
			}
			
			if(isSuccess){
				try {
					//修改项目计划-01-00 组织招标-已完成-02
					projectExtService.updateProjectPlan(projId,"01-00",CommonEnum.CONFIRM_STATUS_SURE);
				} catch (Exception ce) {
					errorMessage = errorMessage + "；/n/r;在修改项目计划时出错：" + ce.getMessage();
					isSuccess =false;
				}
			}
			
			if(isSuccess){
				try {
					//修改项目计划：01-01- 招标文件(制作招标文件,采购人确认)-已完成-02
					projectExtService.updateProjectPlan(projId,"01-01-",CommonEnum.CONFIRM_STATUS_SURE);
					//修改项目计划：01-01-07 开标一览表-进行中-01
					projectExtService.updateProjectPlan(projId,"01-01-07",CommonEnum.CONFIRM_STATUS_NEGOTIATE);
				} catch (Exception ce) {
					errorMessage = errorMessage + "；/n/r;在修改项目计划时出错：" + ce.getMessage();
					isSuccess =false;
				}
			}
			
			if(isSuccess){
				try {
					//如果有公告，则不改开标室状态，否则修改开标室状态为进行中
					if(!StringUtils.hasLength(projectExtService.getBulletinByProjId(projId))) {
						//修改项目计划-01-011开标室-进行中-01
						projectExtService.updateProjectPlan(projId,"01-011",CommonEnum.CONFIRM_STATUS_NEGOTIATE);
					}
					
				} catch (Exception ce) {
					errorMessage = errorMessage + "；/n/r;在修改项目计划时出错：" + ce.getMessage();
					isSuccess =false;
				}
			}
			
			if(isSuccess){
				try {
					//修改项目计划：01-02-01 提交招标公告-进行中-01
					projectExtService.updateProjectPlan(projId,"01-02-01",CommonEnum.CONFIRM_STATUS_NEGOTIATE);
				} catch (Exception ce) {
					errorMessage = errorMessage + "；/n/r;在修改项目计划时出错：" + ce.getMessage();
					isSuccess =false;
				}
			}
			
			if(isSuccess){
				try {
					/**更新项目状态-采购办审核采购文件*/
					projectExtService.saveProjProcessStatus(projId,ProjProcessStatusEnum.PROCUREMENTOFFICE_AUDIT_FILE, ProjProcessStatusEnum.getCN(ProjProcessStatusEnum.PROCUREMENTOFFICE_AUDIT_FILE));
				} catch (Exception ce) {
					errorMessage = errorMessage + "；/n/r;在更新项目状态时出错：" + ce.getMessage();
					isSuccess =false;
				}
			}
		}
		
		//返回同步结果字符串
		String result = syncResult(isSuccess,errorMessage);
		
		/*保存xml信息*/
		projectExtService.saveResult(projDTO.getProjCode(), RESULT_FILE, result);
		
		return result;
	}
	
	/** 
	 * Description :   同步招标公告
	 * Create Date: 2011-8-1上午09:24:12 by sunl  Modified Date: 2011-8-1上午09:24:12 by sunl
	 * @param   contentXML为招标公告数据项字符串，包含报名起止时间、开标时间等信息
	 * @return  
	 * @Exception   
	 */
	public String syncBulletinInfo(String xmlContent) throws Exception{
		boolean isSuccess = true;
		BulletinDTO projDTO = new BulletinDTO();
		String errorMessage = "";
		
		/*校验xml格式*/
		Object returnObj = validXml(xmlContent,BulletinDTO.class);
		
		/*有错误信息，返回*/
		if(returnObj instanceof String) {
			isSuccess =false;
			errorMessage = (String)returnObj;
			//获取的项目编号
			projDTO.setProjCode(returnObj.toString().split("_")[0]);
		} else {
			projDTO = (BulletinDTO)returnObj; 
		}
		
		/*保存xml信息*/
		projectExtService.saveXml(projDTO.getProjCode(), SYNC_BULLETIN, xmlContent);
		
		/*保存招标公告信息*/
		if(returnObj instanceof BulletinDTO) {
			String projId = "";
			try {
				//清理招标公告环节的数据
				projectExtService.removeStepBulletin(projDTO.getProjCode());
			} catch (Exception ce) {
				errorMessage = errorMessage + "；/n/r;在清理招标公告数据时出错：" + ce.getMessage();
				isSuccess =false;
			}
			
			if(isSuccess){
				try{
					//同步招标公告数据
					projId = projectExtService.saveBulletinByXml(xmlContent);
				} catch (Exception ce) {
					errorMessage = errorMessage + "；/n/r;在同步招标公告数据时出错：" + ce.getMessage();
					isSuccess =false;
				}	
			}
			
			if(isSuccess){
				try{
					//修改项目计划-01-011 开标室-已完成-02
					projectExtService.updateProjectPlan(projId,"01-011",CommonEnum.CONFIRM_STATUS_SURE);
				} catch (Exception ce) {
					errorMessage = errorMessage + "；/n/r;修改项目计划时出错：" + ce.getMessage();
					isSuccess =false;
				}	
			}
			
			if(isSuccess){
				try{
					//修改项目计划：01-02-01提交招标公告-已完成-02
					projectExtService.updateProjectPlan(projId,"01-02-01",CommonEnum.CONFIRM_STATUS_SURE);
				} catch (Exception ce) {
					errorMessage = errorMessage + "；/n/r;修改项目计划时出错：" + ce.getMessage();
					isSuccess =false;
				}	
			}
			
			if(isSuccess){
				try{
					//修改项目计划：01-04-01供应商报名-进行中-01
					projectExtService.updateProjectPlan(projId,"01-04-01",CommonEnum.CONFIRM_STATUS_NEGOTIATE);
				} catch (Exception ce) {
					errorMessage = errorMessage + "；/n/r;修改项目计划时出错：" + ce.getMessage();
					isSuccess =false;
				}
			}
			
			if(isSuccess){
				try{
					//更新项目状态：01-04-01供应商报名
					projectExtService.saveProjProcessStatus(projId, "01-04-01", ProjProcessStatusEnum.getCN(ProjProcessStatusEnum.SUPPLIERS_APPLY));
				} catch (Exception ce) {
					errorMessage = errorMessage + "；/n/r;更新项目状态时出错：" + ce.getMessage();
					isSuccess =false;
				}
			}
		}
		
		//返回同步结果字符串
		String result = syncResult(isSuccess,errorMessage);
		
		/*保存xml信息*/
		projectExtService.saveResult(projDTO.getProjCode(), RESULT_BULLETIN, result);
		
		return result;
	}
	
	/** 
	 * Description :  同步澄清公告信息
	 * Create Date: 2011-10-24上午11:47:57 by sunl  Modified Date: 2011-10-24上午11:47:57 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public String syncChengQingBulletin(String xmlContent) throws Exception {
		boolean isSuccess = true;
		BulletinDTO projDTO = new BulletinDTO();
		String errorMessage = "";
		
		/*校验xml格式*/
		Object returnObj = validXml(xmlContent,BulletinDTO.class);
		
		/*有错误信息，返回*/
		if(returnObj instanceof String) {
			isSuccess =false;
			errorMessage = (String)returnObj;
			//获取的项目编号
			projDTO.setProjCode(returnObj.toString().split("_")[0]);
		} else {
			projDTO = (BulletinDTO)returnObj; 
		}
		
		/*保存xml信息*/
		projectExtService.saveXml(projDTO.getProjCode(), SYNC_CHENGQING_BULLETIN, xmlContent);
		
		/*同步澄清公告信息*/
		if(returnObj instanceof BulletinDTO) {
			try {
				//清理澄清公告
				projectExtService.removeBulletinByProjCode(projDTO.getProjCode(),BulletinTypeEnum.VARIATION_BULLETIN);
			} catch (Exception ce) {
				errorMessage = errorMessage + "；/n/r;在清理澄清公告数据时出错：" + ce.getMessage();
				isSuccess =false;
			}
			
			if(isSuccess){
				try{
					//同步澄清公告数据
					projectExtService.saveChengQingBulletinByXml(xmlContent);
				} catch (Exception ce) {
					errorMessage = errorMessage + "；/n/r;在同步澄清公告数据时出错：" + ce.getMessage();
					isSuccess =false;
				}	
			}
		}
		
		//返回同步结果字符串
		String result = syncResult(isSuccess,errorMessage);
		
		/*保存xml信息*/
		projectExtService.saveResult(projDTO.getProjCode(), RESULT_CHENGQING_BULLETIN, result);
		
		return result;
	}
	
	/** 
	 * Description :  同步采购单位信息
	 * 新增采购单位时，传入xml里要求的全部数据
	 * 修改采购单位时，传入ID、所修改的数据项值即可
	 * Create Date: 2011-10-24上午11:47:57 by sunl  Modified Date: 2011-10-24上午11:47:57 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public String createOrUpdateBuyer(String xmlContent) throws Exception {
		boolean isSuccess = true;
		String errorMessage = "";
		
		Document document = DocumentHelper.parseText(xmlContent);
		Element element =  document.getRootElement();
		Element buyerEle = element.element("body").element("buyerInfo");
		
		/*保存xml数据信息，保存到gxoa/buyerid/*/
		projectExtService.saveXml(buyerEle.elementText("buyerId")+File.separator, SYNC_BUYER, xmlContent);
		
		/*同步采购单位信息*/
		try{
			//同步采购单位数据
			projectExtService.saveBuyerByXml(xmlContent);
		} catch (Exception ce) {
			errorMessage = errorMessage + "；/n/r;在同步采购单位数据时出错：" + ce.getMessage();
			isSuccess =false;
		}	
		
		//返回同步结果字符串
		String result = syncResult(isSuccess,errorMessage);
		
		/*保存xml结果信息*/
		projectExtService.saveResult(buyerEle.elementText("buyerId")+File.separator, RESULT_BUYER, result);
		
		return result;
	}
	
	/** 
	 * Description :  删除采购单位信息
	 * Create Date: 2011-10-24上午11:47:57 by sunl  Modified Date: 2011-10-24上午11:47:57 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public String removeBuyer(String xmlContent) throws Exception {
		boolean isSuccess = true;
		String errorMessage = "";
		
		Document document = DocumentHelper.parseText(xmlContent);
		Element element =  document.getRootElement();
		Element buyerEle = element.element("body").element("orgInfo");
		
		/*保存xml信息，保存到gxoa/buyerid*/
		projectExtService.saveXml(buyerEle.elementText("orgId")+File.separator, SYNC_REMOVE_BUYER, xmlContent);
		
		try{
			//删除采购单位信息(改为无效)
			projectExtService.removeOrg(PREFIX_WTDW+buyerEle.elementText("orgId"), OrganizationEnum.USE_INVALID);
		} catch (Exception ce) {
			errorMessage = errorMessage + "；/n/r;在删除采购单位数据时出错：" + ce.getMessage();
			isSuccess =false;
		}	
		
		//返回同步结果字符串
		String result = syncResult(isSuccess,errorMessage);
		
		/*保存xml信息*/
		projectExtService.saveResult(buyerEle.elementText("orgId")+File.separator, RESULT_REMOVE_BUYER, result);
		
		return result;
	}
	
	/** 
	 * Description :  同步组织机构信息
	 * Create Date: 2011-10-24上午11:47:57 by sunl  Modified Date: 2011-10-24上午11:47:57 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public String createOrUpdateOrg(String xmlContent) throws Exception {
		boolean isSuccess = true;
		String errorMessage = "";
		
		Document document = DocumentHelper.parseText(xmlContent);
		Element element =  document.getRootElement();
		Element orgEle = element.element("body").element("orgInfo");
		
		/*保存xml信息，保存到gxoa/buyerid/org/下*/
		projectExtService.saveXml(orgEle.elementText("orgId")+File.separator, SYNC_ORG, xmlContent);
		
		/*同步组织机构信息*/
		try{
			projectExtService.saveOrgByXml(xmlContent);
		} catch (Exception ce) {
			errorMessage = errorMessage + "；/n/r;在同步组织机构数据时出错：" + ce.getMessage();
			isSuccess =false;
		}	
		
		//返回同步结果字符串
		String result = syncResult(isSuccess,errorMessage);
		
		/*保存xml信息*/
		projectExtService.saveResult(orgEle.elementText("orgId")+File.separator, RESULT_ORG, result);
		
		return result;
	}
	
	/** 
	 * Description :  删除组织机构信息
	 * Create Date: 2011-10-24上午11:47:57 by sunl  Modified Date: 2011-10-24上午11:47:57 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public String removeOrg(String xmlContent) throws Exception {
		boolean isSuccess = true;
		String errorMessage = "";
		
		Document document = DocumentHelper.parseText(xmlContent);
		Element element =  document.getRootElement();
		Element buyerEle = element.element("body").element("orgInfo");
		
		/*保存xml信息，保存到gxoa/buyerid/下*/
		projectExtService.saveXml(buyerEle.elementText("orgId")+File.separator, SYNC_REMOVE_ORG, xmlContent);
		
		try{
			//删除组织机构信息(改为无效)
			projectExtService.removeOrg(buyerEle.elementText("orgId"), OrganizationEnum.USE_INVALID);
		} catch (Exception ce) {
			errorMessage = errorMessage + "；/n/r;在删除组织机构数据时出错：" + ce.getMessage();
			isSuccess =false;
		}	
		
		//返回同步结果字符串
		String result = syncResult(isSuccess,errorMessage);
		
		/*保存xml信息*/
		projectExtService.saveResult(buyerEle.elementText("buyerId")+File.separator, RESULT_REMOVE_ORG, result);
		
		return result;
	}
	
	/** 
	 * Description :  校验xml信息
	 * Create Date: 2011-7-29上午08:56:22 by sunl  Modified Date: 2011-7-29上午08:56:22 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public Object validXml(String xml,Class clazz) throws Exception {
		//elementOtObject正常时返回class对象
		Object object = null;
		//elementOtObject出现异常时返回项目编号和错误信息，保存xml时用
		String projCode = "";
		try {
			Document doc = DocumentHelper.parseText(xml);
			Element rootElement =  doc.getRootElement();
			Element tenderElement = rootElement.element("body").element("projectInfo");
			projCode = tenderElement.element("projCode").getText();
			
			//招标文件具有子节点暂时特殊处理
			if(clazz == PurchaseFileDTO.class) {
				PurchaseFileDTO fileDTO = new PurchaseFileDTO();
				fileDTO.setProjCode(projCode);
				object = fileDTO;
			} else {
				object = XmlUtil.elementToObject(tenderElement, clazz);
			}
		}catch (Exception ce) {
			object = projCode + "_" + ce.getMessage();
		}
		return object;
	}
	
	/** 
	 * Description :  阳光易购同步结果，返回成功或失败
	 * Create Date: 2011-7-29上午08:56:22 by sunl  Modified Date: 2011-7-29上午08:56:22 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	public String syncResult(boolean result,String message) throws Exception {
		//添加返回XML的主体内容
		StringBuffer responseXml = new StringBuffer();
		responseXml.append("<?xml version='1.0' encoding='utf-8' ?>");
		responseXml.append("<syncResult xmlns='http://www.gpcsoft.com'>");
		responseXml.append("<header>");
		responseXml.append("<operTag>" + (result == true ? "Y" : "N") + "</operTag>");
		responseXml.append("<operDesc>" + message + "</operDesc>");
		responseXml.append("</header>");
		responseXml.append("<body>");
		responseXml.append("</body>");
		responseXml.append("</syncResult>");
		return responseXml.toString();
	}
}
