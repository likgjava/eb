package com.gpcsoft.epp.webService.webService.ue.http;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.core.Constants;
import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.context.FrameMessageResource;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.common.domain.CommonEnum;
import com.gpcsoft.epp.common.domain.EsExceptionEnum;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.service.ProjectService;
import com.gpcsoft.epp.purchasedoc.domain.PurchaseDoc;
import com.gpcsoft.epp.purchasedoc.service.PurchaseDocService;
import com.gpcsoft.epp.webService.common.util.XmlUtil;
import com.gpcsoft.epp.webService.ueSystem.bulletin.dto.ApplyProjectDTO;
import com.gpcsoft.epp.webService.ueSystem.bulletin.dto.BulletinDTO;
import com.gpcsoft.epp.webService.ueSystem.bulletin.dto.PackDTO;
import com.gpcsoft.epp.webService.ueSystem.bulletin.service.BulletinExtService;
import com.gpcsoft.epp.webService.ueSystem.bulletin.service.SignUpRecordService;

/**
  * @gpcsoft.view value="bulletinExtFormView"
  *  url="view/es/ext/bulletin/bulletinExtForm.jsp"
  * @gpcsoft.view value="bulletinExtTreeFormView"
  *  url="view/es/ext/bulletin/bulletinExtTreeForm.jsp"
  * @gpcsoft.view value="bulletinExtListView"
  *  url="view/es/ext/bulletin/bulletinExtList.jsp"
  * @gpcsoft.view value="bulletinExtDetailView"
  *  url="view/es/ext/bulletin/bulletinExtDetail.jsp"
  */
@SuppressWarnings("unchecked")
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={BulletinDTO.class})
@RequestMapping("/BulletinHttpService.do")//页面请求路径,可修改
public class BulletinHttpService extends AnnotationMultiController {

	@Autowired(required=true) @Qualifier("bulletinExtServiceImpl")
	private BulletinExtService bulletinExtService;
	
	@Autowired(required=true) @Qualifier("signUpRecordServiceImpl")
	private SignUpRecordService signUpRecordService;
	
	@Autowired(required=true) @Qualifier("projectServiceImpl")
	private ProjectService projectService;
	
	@Autowired(required=true) @Qualifier("purchaseDocServiceImpl")
	private PurchaseDocService purchaseDocService;
	
	private FrameMessageResource getMessageSource() {
		if(null == this.messageSource){
			this.messageSource = (FrameMessageResource)FrameBeanFactory.getBean("frameMessageResource");
		}
		return this.messageSource;
	}
	
	/** 
	 * Description :  根据公告编号获取公告
	 * Create Date: 2011-3-3下午05:08:05 by yangx  Modified Date: 2011-3-3下午05:08:05 by yangx
	 * @param   bulNo：公告编号
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=getBulInfo")
	public ModelAndView getBulInfo(HttpServletRequest request)throws Exception {
		String bulNo = request.getParameter("bulNo");
		//BulletinDTO bulletinExt = bulletinExtService.getBulletinBybulNo(bulNo);
		Map model=new HashMap();
		//model.put("bulletinExt",bulletinExt);
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	@RequestMapping(params="method=getBulletinList")
	public void getBulletinList(HttpServletRequest request, HttpServletResponse response)throws Exception {
		boolean isSuccess = true;//是否执行成功
		String totalCount = "0";//返回记录总数
		String errorMessage = "";//操作结果信息
		/**
		 * 公告类型(00－采购公示/预告 01－采购公告 02－更正公告 03－资格预审公告 
		 * 04－资格预审结果公示 05－结果公示 06－结果公告 07－暂停公告 08－失败公告 
		 * 10－询价公告 11－变更公告)
		 */
		List<BulletinDTO> bulletinDTOList = null;
		StringBuffer bulletinListInfo = new StringBuffer();//存放返回的公告列表
		try {
			String bulletintype = request.getParameter("bulletinType");//00,01,
			String startTime = request.getParameter("fromDate");//开始时间
			String endTime = request.getParameter("toDate");//结束时间
			String purareacode = request.getParameter("purAreaCode");//采购区域
			String purcategorycode = request.getParameter("purCategoryCode");//采购品目编号
			String bulletinnum = request.getParameter("bulletinNum");//获取记录条数
			String keyword = null;
			
			keyword = request.getParameter("keyword");
			if(keyword != null && !"null".equals(keyword.toString()) && !"".equals(keyword.toString()))
				keyword = URLDecoder.decode(keyword.toString(), "UTF-8");//关键字
			
			String newPurcategorycode = getPurcategorycode(purcategorycode);
			
			//查询公告信息	
			bulletinDTOList = bulletinExtService.getBulletinList(bulletintype,startTime,endTime,purareacode,newPurcategorycode,bulletinnum,keyword);
			for(BulletinDTO bulletinDTO : bulletinDTOList){
				bulletinListInfo.append(XmlUtil.objectToXml(bulletinDTO));
			}
			
			totalCount = (bulletinDTOList == null ? "0" : String.valueOf(bulletinDTOList.size()));
		} catch (Exception e) {
			isSuccess = false;
			e.printStackTrace();
			errorMessage = EsExceptionEnum.ES_SYSTEM_ERROR;
		}
		//添加返回XML的主体内容
		StringBuffer responseXml = new StringBuffer();
		responseXml.append("<?xml version='1.0' encoding='utf-8' ?>");
		responseXml.append("<getBulletinList xmlns='http://www.gpcsoft.com'>");
		responseXml.append("<header>");
		responseXml.append("<operTag>" + (isSuccess == true ? "Y" : "N") + "</operTag>");
		responseXml.append("<operDesc>"+errorMessage+"</operDesc>");
		responseXml.append("<totalCount>" + totalCount + "</totalCount>");
		responseXml.append("</header>");
		responseXml.append("<body>");
		responseXml.append("<bulletinList>" + bulletinListInfo.toString() + "</bulletinList>");
		responseXml.append("</body>");
		responseXml.append("</getBulletinList>");
		logger.debug("BulletinHttpService  getBulletinList start=============================================\n"+responseXml.toString());
		logger.debug("BulletinHttpService  getBulletinList end=============================================\n");
		write(responseXml.toString(), response);
	}
	
	/**
	 * 查询公告详情
	 * @param request
	 * @param response
	 * @throws Exception
	 * @author zhouzhanghe
	 * @since 2011.3.28
	 * Modified by zhouzhanghe at 2011.3.28 15:36
	 */
	@RequestMapping(params="method=getBulletinInfo")
	public void getBulletinInfo(HttpServletRequest request, HttpServletResponse response) throws Exception{
		boolean isSuccess = true;
		String errorMessage = "";//操作结果信息
		StringBuffer bulletinContent = new StringBuffer();//存放公告信息
		StringBuffer packageContent = new StringBuffer();//存放包组信息
		try {
			String bulletinCode = request.getParameter("bulletinCode");//公告编号
			String userId = request.getParameter("userId");//用户ID
			//添加存放公告信息
			if(isSuccess)
				bulletinContent.append(XmlUtil.objectToXml(bulletinExtService.getBulletinInfoDTO(bulletinCode, userId)));
			
			//查询包组信息
			if(isSuccess){
				List<PackDTO> packDTOList = bulletinExtService.getPackDTO(bulletinCode, userId);
				for(PackDTO packDTO : packDTOList){
					packageContent.append(XmlUtil.objectToXml(packDTO));
				}
			}
		} catch (Exception e) {
			isSuccess = false;
			errorMessage = EsExceptionEnum.ES_SYSTEM_ERROR;
			e.printStackTrace();
		}
		
		//添加返回XML的主体内容
		StringBuffer responseXml = new StringBuffer();
		responseXml.append("<?xml version='1.0' encoding='utf-8' ?>");
		responseXml.append("<getBulletinInfo xmlns='http://www.gpcsoft.com'>");
		responseXml.append("<header>");
		responseXml.append("<operTag>" + (isSuccess == true ? "Y" : "N") + "</operTag>");
		responseXml.append("<operDesc>" + errorMessage + "</operDesc>");
		responseXml.append("</header>");
		responseXml.append("<body>");
		
		if(isSuccess){
			String retuBlletinContent = bulletinContent.toString();
			retuBlletinContent = retuBlletinContent.substring(0, retuBlletinContent.lastIndexOf("</bulletin>"));
			responseXml.append(retuBlletinContent);
			responseXml.append("<packList>" + packageContent.toString() + "</packList>");
			responseXml.append("<prjFactor></prjFactor>");
			responseXml.append("</bulletin>");
		}

		responseXml.append("</body>");
		responseXml.append("</getBulletinInfo>");
		logger.debug("BulletinHttpService  getBulletinInfo start=============================================\n"+responseXml.toString());
		logger.debug("BulletinHttpService  getBulletinInfo end=============================================\n");
		write(responseXml.toString(), response);
	}
	
	/**
	 * FuncName: applyProject
	 * Description : 获得项目报名信息
	 * @param 
	 * @param request
	 * @param response
	 * @throws Exception void
	 * @author: liuke
	 * @Create Date:2011-6-9  下午02:36:29
	 * @Modifier: liuke
	 * @Modified Date:2011-6-9  下午02:36:29
	 */
	@RequestMapping(params="method=applyProject")
	public void applyProject(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//定义查询是否成功
		boolean isSuccess = true;
		String errorMessage= "";
		try {
			String projCode = request.getParameter("prjCode");
			String orgCode = request.getParameter("orgCode");
			String orgName = request.getParameter("orgName");
			String applyDate = request.getParameter("applyDate");
			String linkerMan = request.getParameter("linkerMan");
			String linkTel = request.getParameter("linkTel");
			String linkerIdCard = request.getParameter("linkerIdCard");
			String linkAddress = request.getParameter("linkAddress");
			String zipCode = request.getParameter("zipCode");
			String applyFile = request.getParameter("applyFile");
			String memo = request.getParameter("memo");
			
			ApplyProjectDTO applyProjectDTO = new ApplyProjectDTO();
			applyProjectDTO.setProjCode(projCode);
			applyProjectDTO.setOrgCode(orgCode);
			applyProjectDTO.setOrgName(orgName);
			applyProjectDTO.setApplyDate(applyDate);
			applyProjectDTO.setLinkerMan(linkerMan);
			applyProjectDTO.setLinkTel(linkTel);
			applyProjectDTO.setLinkerIdCard(linkerIdCard);
			applyProjectDTO.setLinkAddress(linkAddress);
			applyProjectDTO.setZipCode(zipCode);
			applyProjectDTO.setApplyFile(applyFile);
			applyProjectDTO.setMemo(memo);
			signUpRecordService.saveSignUpRecord(applyProjectDTO, null);
		} catch (Exception e) {
			isSuccess = false;
			e.printStackTrace();
			errorMessage = EsExceptionEnum.ES_SYSTEM_ERROR;
		}
		
		StringBuffer responseXml = new StringBuffer();
		//添加返回XML的主体内容
		responseXml.append("<?xml version='1.0' encoding='utf-8' ?>");
		responseXml.append("<getBulletinInfo xmlns='http://www.gpcsoft.com'>");
		responseXml.append("<header>");
		responseXml.append("<operTag>" + (isSuccess == true ? "Y" : "N") + "</operTag>");
		responseXml.append("<operDesc>"+errorMessage+"</operDesc>");
		responseXml.append("</header>");
		responseXml.append("<body>");
		responseXml.append("</body>");
		responseXml.append("</getBulletinInfo>");
		logger.debug("BulletinHttpService  applyProject start=============================================\n"+responseXml.toString());
		logger.debug("BulletinHttpService  applyProject end=============================================\n");
		write(responseXml.toString(), response);
	}
	
	
	/**
	 * FuncName: getProjectPurchaseFile
	 * Description :  获得项目文件
	 * @param 
	 * @param request
	 * @param response
	 * @throws Exception void
	 * @author: liuke
	 * @Create Date:2011-6-9  下午02:37:22
	 * @Modifier: liuke
	 * @Modified Date:2011-6-9  下午02:37:22
	 */
	@RequestMapping(params="method=getProjectPurchaseFile")
	public void getProjectPurchaseFile(HttpServletRequest request, HttpServletResponse response) throws Exception{
		boolean isSuccess = true;
		Project project = null;
		PurchaseDoc purchaseDoc = null;
		String errorMessage= "";
		try {
			String projCode = request.getParameter("prjCode");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			project = projectService.findProjectByProjCode(projCode);
			if(project == null)
				throw new Exception("根据项目编号" + projCode + "查询项目失败");
			purchaseDoc = purchaseDocService.getPurchaseDocByProjectId(project.getObjId());
			if(purchaseDoc == null)
				throw new Exception("根据项目编号" + projCode + "查询项目的招标文件失败");
		} catch (Exception e) {
			isSuccess = false;
			e.printStackTrace();
			errorMessage = EsExceptionEnum.ES_SYSTEM_ERROR;
		}

		StringBuffer responseXml = new StringBuffer();
		//添加返回XML的主体内容
		responseXml.append("<?xml version='1.0' encoding='utf-8' ?>");
		responseXml.append("<getPrjPurchaseFile  xmlns='http://www.gpcsoft.com'>");
		responseXml.append("<header>");
		responseXml.append("<operTag>" + (isSuccess == true ? "Y" : "N") + "</operTag>");
		responseXml.append("<operDesc>"+errorMessage+"</operDesc>");
		responseXml.append("</header>");
		responseXml.append("<body>");
		responseXml.append("<project>");
		responseXml.append("<projCode>" + project.getProjCode() + "</projCode>");
		responseXml.append("<purchaseFile>");
		responseXml.append("<fileName>" + project.getProjCode() + "</fileName>");
		if(isSuccess){
			responseXml.append("<downUrl>" + this.getMessageSource().getMessage("applicationInternetAddress") + "/DownloadFileHttpServlet?attachmentObjId=" + purchaseDoc.getAttachRelaId() + "&amp;fileType="+CommonEnum.FILE_TYPE_PURCHASE+"</downUrl>");
		}else{
			responseXml.append("<downUrl></downUrl>");
		}
		responseXml.append("<downType>http</downType>");
		responseXml.append("</purchaseFile>");
		responseXml.append("</project>");
		responseXml.append("</body>");
		responseXml.append("</getPrjPurchaseFile>");
		logger.debug("BulletinHttpService  getProjectPurchaseFile start=============================================\n"+responseXml.toString());
		logger.debug("BulletinHttpService  getProjectPurchaseFile end=============================================\n");
		write(responseXml.toString(), response);
	}
	
	
	/**
	 * FuncName: getBulletinNumInfo
	 * Description :  获得公告数目接口
	 * @param 
	 * @param request
	 * @param response
	 * @throws Exception void
	 * @author: liuke
	 * @Create Date:2011-6-9  下午02:44:24
	 * @Modifier: liuke
	 * @Modified Date:2011-6-9  下午02:44:24
	 */
	@RequestMapping(params="method=getBulletinNumInfo")
	public void getBulletinNumInfo(HttpServletRequest request, HttpServletResponse response) throws Exception{
		logger.debug("\nes BulletinHttpService||getBulletinNumInfo\n");
		String fromDate = request.getParameter("fromDate");
		String toDate = request.getParameter("toDate");
		boolean isSuccess = true;
		String errorMessage= "";
		String num = "0";
		try {
			 num =  bulletinExtService.getBulletinNumInfo(fromDate, toDate);
		} catch (Exception e) {
			isSuccess = false;
			e.printStackTrace();
			errorMessage = EsExceptionEnum.ES_SYSTEM_ERROR;
		}
		
	    StringBuffer responseXml = new StringBuffer();
	  //添加返回XML的主体内容
		responseXml.append("<?xml version='1.0' encoding='utf-8' ?>");
		responseXml.append("<getBulletinNum  xmlns='http://www.gpcsoft.com'>");
		responseXml.append("<header>");
		responseXml.append("<operTag>" + (isSuccess == true ? "Y" : "N") + "</operTag>");
		responseXml.append("<operDesc>"+errorMessage+"</operDesc>");
		responseXml.append("</header>");
		responseXml.append("<body>");
		responseXml.append("<total>"+num+"</total>");
		responseXml.append("</body>");
		responseXml.append("</getBulletinNum>");
		logger.debug("BulletinHttpService  getBulletinNumInfo start=============================================\n"+responseXml.toString());
		logger.debug("BulletinHttpService  getBulletinNumInfo end=============================================\n");
		write(responseXml.toString(), response);
	}
	
	/**
	 * FuncName: getPurcategorycode
	 * Description :  处理品目查询条件
	 * @param 
	 * @param purcategorycode
	 * @return String
	 * @author: liuke
	 * @Create Date:2011-10-12  下午03:49:11
	 * @Modifier: liuke
	 * @Modified Date:2011-10-12  下午03:49:11
	 */
	private String getPurcategorycode(String purcategorycode){
		String[] purcategoryArray = purcategorycode.split(",");
		List<String> oneLevel = new ArrayList<String>();
		Map<String,String> purcategoryMap = new HashMap<String, String>();
		for (int i=0;i <purcategoryArray.length; i++) {
			String purcategory = (String)purcategoryArray[i] ;
			if(purcategory.length()==1){ //如果一级品目存在，说明二级品目全部选中
				oneLevel.add(purcategory);
			}
			purcategoryMap.put(purcategory, "true");
		} 
		String purcategorycodeInfo = "";
		 if(oneLevel.isEmpty()){
			 for (int i = 0; i < purcategoryArray.length; i++) {
					String purcategory = purcategoryArray[i];
						purcategorycodeInfo += purcategory+",";
					}
		 }else{
			 for (Iterator iterator = oneLevel.iterator(); iterator.hasNext();) {
					String level = (String) iterator.next();
					purcategorycodeInfo += level + ",";	
					for (int i = 0; i < purcategoryArray.length; i++) {
						 String purcategory = purcategoryArray[i];
						 if(purcategory.indexOf(level)!=-1){
							 purcategoryMap.put(purcategory, "false");
						 }
					}
				}
			 for (Iterator iterator2 = purcategoryMap.keySet().iterator(); iterator2.hasNext();) {
					String key = (String) iterator2.next();
					if("true".equals(purcategoryMap.get(key))){
						purcategorycodeInfo += key + ",";
					}
				}
		 }
		if(purcategorycodeInfo.length()>0){
			purcategorycodeInfo = purcategorycodeInfo.substring(0, purcategorycodeInfo.length()-1);
		}
		return purcategorycodeInfo;
	}
	
}
