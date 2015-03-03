package com.gpcsoft.pubservice.application.advertisement.controller;

import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gpcsoft.bizplatform.base.common.util.StringUtils;
import com.gpcsoft.bizplatform.base.message.CustomerMessage;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.utils.FileUtil;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.pubservice.application.advertisement.service.AdvertisingPositionService;
import com.gpcsoft.pubservice.application.advertisement.service.AdvertisingSubscribeService;
import com.gpcsoft.pubservice.application.advertisement.service.impl.AdvertisingSubscribeServiceImpl;
import com.gpcsoft.pubservice.application.advertisement.domain.AdvertisingPosition;
import com.gpcsoft.pubservice.application.advertisement.domain.AdvertisingSubscribe;
import com.gpcsoft.pubservice.application.advertisement.enumeration.AdvertisingMessage;
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.service.AttachmentService;
import com.gpcsoft.srplatform.common.assist.service.FreeMarkerService;
import com.gpcsoft.srplatform.common.utils.AttachmentUtil;

/**
  * @gpcsoft.view value="adverSubscribeAddOrModifyView"
  *  url="view/pubservice/application/advertisement/adver_subscribe_add_modify.jsp"
  * @gpcsoft.view value="advertisingSubscribeListView"
  *  url="view/pubservice/application/advertisement/adver_subscribe_manager_list.jsp"
  * @gpcsoft.view value="adverSubscribeAuditListView"
  *  url="view/pubservice/application/advertisement/adver_subscribe_audit_list.jsp"
  * @gpcsoft.view value="adverSubscribeDetailView"
  *  url="view/pubservice/application/advertisement/adver_subscribe_detail.jsp"
  * @gpcsoft.view value="adverSubscribeAuditAddView"
  *  url="view/pubservice/application/advertisement/adver_subscribe_audit_add.jsp"
  * @gpcsoft.view value="adverSubscribeLimitModifyView"
  *  url="view/pubservice/application/advertisement/adver_subscribe_limit_modify.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={AdvertisingSubscribe.class})
@RequestMapping("/AdvertisingSubscribeController.do")//页面请求路径,可修改
public class AdvertisingSubscribeController extends AnnotationMultiController<AdvertisingSubscribe> {

	@Autowired(required=true) @Qualifier("advertisingSubscribeServiceImpl")
	private AdvertisingSubscribeService advertisingSubscribeService;
	
	@Autowired(required=true) @Qualifier("advertisingPositionServiceImpl")
	private AdvertisingPositionService advertisingPositionService;
	
	@Autowired(required=true) @Qualifier("attachmentServiceImpl")
	private AttachmentService attachmentService;
	
	@Autowired(required=true) @Qualifier("freeMarkerServiceImpl")
	private FreeMarkerService freeMarkerService;

	/**
	 * Description :  新增或修改
	 * Create Date: 2011-3-23下午04:13:48 by zhaojf  Modified Date: 2011-3-23下午04:13:48 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=toAddORModifyAdverSubscribe")
	public ModelAndView toAddORModifyAdverSubscribe(String objId) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		if(objId != null && objId != ""){//修改
			AdvertisingSubscribe advertisingSubscribe = advertisingSubscribeService.get(objId);
			model.put("advertisingSubscribe", advertisingSubscribe);
		}else{//新增
			AdvertisingSubscribe advertisingSubscribe = new AdvertisingSubscribe();
			//添加广告位对象
			AdvertisingPosition adverPosition = new AdvertisingPosition();
			advertisingSubscribe.setAdvertisingPosition(adverPosition);
			//添加投放机构
			OrgInfo orgInfo = new OrgInfo();
			advertisingSubscribe.setOrgInfo(orgInfo);
			//广告表最大排序号
			advertisingSubscribe.setSort(advertisingSubscribeService.adverSubscribeMaxSort()+1);
			
			model.put("advertisingSubscribe", advertisingSubscribe);
		}
		
		//广告位列表
		List<AdvertisingPosition> adverPositionList = advertisingPositionService.getAll();
		model.put("adverPositionList", adverPositionList);
		
		return new ModelAndView("adverSubscribeAddOrModifyView",model);
	}
	
	/**
	 * Description :  删除(可批量删除)
	 * Create Date: 2011-3-23下午04:28:52 by zhaojf  Modified Date: 2011-3-23下午04:28:52 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=removeAdverSubscribe")
	public ModelAndView removeAdverSubscribe(String adverSubscribeIds) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		model = advertisingSubscribeService.removeAdverSubscribe(adverSubscribeIds);//删除
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	/**
	 * Description :  查看详情或跳至审核页面
	 * Create Date: 2011-3-23下午05:27:30 by zhaojf  Modified Date: 2011-3-23下午05:27:30 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=toAdverSubscribeDetailOrAudit")
	public ModelAndView toAdverSubscribeDetailOrAudit(String objId,String operationStyle) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("advertisingSubscribe", advertisingSubscribeService.get(objId));
		//默认跳至查看详情页面
		String returnView = "adverSubscribeDetailView";
		//当为审核时,跳转至 审核页面
		if("AuditAdd".equals(operationStyle)){
			returnView = "adverSubscribeAuditAddView";
		}
		
		//有效的、审核通过的修改
		if("limit_modify".equals(operationStyle)){
			returnView = "adverSubscribeLimitModifyView";
		}
		return new ModelAndView(returnView,model);
	}
	
	/**
	 * Description :  保存提交
	 * Create Date: 2011-3-24上午10:21:24 by zhaojf  Modified Date: 2011-3-24上午10:21:24 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=saveAdverSubscribe")
	public ModelAndView saveAdverSubscribe(AdvertisingSubscribe advertisingSubscribe,HttpServletRequest request,SessionStatus status) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		//广告位的最大容量(附件的最大容量3M)
		Integer adverFileMaxValue = Integer.valueOf(request.getParameter("adverFileMaxValue"));
		//跑马灯时才有值
		Integer fileValueTotal = Integer.valueOf(request.getParameter("fileValueTotal"));
		Boolean flag = true;//保存标记
		
		//处理附件
		CommonsMultipartFile file = null;
		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
			file = (CommonsMultipartFile)multipartRequest.getFile("pictureFile");
			if(file!=null && file.getSize()!=0){
				if(adverFileMaxValue*1024 > (file.getSize()+fileValueTotal)){
					Object o=AttachmentUtil.uploadFile(request,"pictureFile");
					if(o instanceof GpcBaseObject){
						Attachment attachment = (Attachment)o;
						attachmentService.saveAttachment((Attachment)o);
						advertisingSubscribe.setAdverFile(attachment.getObjId());
					}
				}else{
					flag = false;
					model.put(Constants.JSON_RESULT, "false");
				}
			}
		}catch(Exception de) {
			model.put(Constants.JSON_RESULT, StringUtils.ascii2Native(de.getMessage()));
		}
		
		if(flag){
			advertisingSubscribeService.save(advertisingSubscribe);
			model.put(Constants.JSON_RESULT, "true");
		}
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	/**
	 * Description :  跳到审核列表页面
	 * Create Date: 2011-3-24下午03:29:10 by zhaojf  Modified Date: 2011-3-24下午03:29:10 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=toAdverSubscribeAuditList")
	public ModelAndView toAdverSubscribeAuditList() throws Exception{
		return new ModelAndView("adverSubscribeAuditListView");
	}
	
	/**
	 * Description :  更改使用状态或审核状态
	 * Create Date: 2011-3-24下午05:07:15 by zhaojf  Modified Date: 2011-3-24下午05:07:15 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=modifyUseORAuditStatus")
	public ModelAndView modifyUseORAuditStatus(String objId,String field,String estatus,String[] adverIdsArray) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("objId", objId);
		paramMap.put("field", field);
		paramMap.put("estatus", estatus);
		paramMap.put("adverIdsArray", adverIdsArray);
		
		advertisingSubscribeService.modifyUseORAuditStatus(paramMap);
		
		AdvertisingSubscribe advertisingSubscribe = advertisingSubscribeService.get(objId);
		
		//当'废弃'操作时,设置广告为默认的页面
		if("useStatus".equals(field) && "02".equals(estatus) && "02".equals(advertisingSubscribe.getUseStatus())){
			Map<String, Object> templateMap = new HashMap<String, Object>();
			Map<String, Object> paramsMap = new HashMap<String, Object>();
			String contentsString = "";
			templateMap.put("advertisingSubscribe", advertisingSubscribe);
			String ftlName = messageSource.getMessage(CustomerMessage.ADVERTISING_DEFAULT_TEMPLATE);// 广告默认图片模板名称
			contentsString = freeMarkerService.getFreeMarkerContent(ftlName, templateMap);
			
			//当为跑马灯废弃
			if(AdvertisingMessage.ADVERTYPE_PAOMADENG.equals(advertisingSubscribe.getAdvertisingPosition().getAdverType())){
				paramsMap.put("adverPositionId", advertisingSubscribe.getAdvertisingPosition().getObjId());
				//取得此广告位下剩余的有效广告,组成跑马灯
				List<AdvertisingSubscribe> adverSubscribeList = advertisingPositionService.getAdverSubscribeByPositionId(paramsMap);
				templateMap.put("adverSubscribeList", adverSubscribeList);
				templateMap.put("advertisingSubscribe", advertisingSubscribe);
				if(adverSubscribeList.size()>0){
					ftlName = messageSource.getMessage(CustomerMessage.ADVERTISING_POMADENG_TEMPLATE);//广告跑马灯模板名称
					contentsString = freeMarkerService.getFreeMarkerContent(ftlName, templateMap);
				}
			}
				
			/** 公告文件的存储路径 */
			Properties properties = new Properties();
	        InputStream in =AdvertisingSubscribeServiceImpl.class.getClassLoader().getResourceAsStream("sys/attachment.properties");
	        properties.load(in);
	        String adverUrl = properties.getProperty("adverSubscribePath");
			String saveName=advertisingSubscribe.getAdvertisingPosition().getPositionName();
			String path2 = (Constants.ROOTPATH + adverUrl+File.separator+saveName).replace("/", File.separator);;
			File file1 = new File((Constants.ROOTPATH + adverUrl).replace("/", File.separator));
			if(!file1.isDirectory()){
				file1.mkdirs();
			}
			File file2 = new File(path2);
			if(!file2.exists()){
				file2.createNewFile();
			}	
			/** 把文件写在指定的存储路径 */
			FileUtil.writerFile(path2,contentsString,"UTF-8");
		}
		
		model.put(Constants.JSON_RESULT, "true");
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	/**
	 * Description :  审核、审核通过并发布广告
	 * Create Date: 2011-4-1上午10:41:55 by zhaojf  Modified Date: 2011-4-1上午10:41:55 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=auditAdverAndRelease")
	public ModelAndView auditAdverAndRelease(AdvertisingSubscribe advertisingSubscribe,String isPublish,SessionStatus status) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		
		//保存审核信息
		advertisingSubscribe.setVerifyTime(new Date());//设置审核时间
		advertisingSubscribe.setVerifyUser(AuthenticationHelper.getCurrentUser(true));//设置审核人
		advertisingSubscribe = advertisingSubscribeService.save(advertisingSubscribe);
		
		//审核通过并发布，生成Jsp页面
		if("true".equals(isPublish) && AdvertisingMessage.USE_VALID.equals(advertisingSubscribe.getUseStatus())){
			Map<String, Object> templateMap = new HashMap<String, Object>();
			Map<String, Object> paramsMap = new HashMap<String, Object>();
			String contentsString = "";
			String ftlName = messageSource.getMessage(CustomerMessage.ADVERTISING_PICTURE_TEMPLATE);// 广告图片模板名称
			if("01".equals(advertisingSubscribe.getAdvertisingPosition().getAdverType())){//广告flash模板名称
				ftlName = messageSource.getMessage(CustomerMessage.ADVERTISING_FLASH_TEMPLATE);
			}
			if("02".equals(advertisingSubscribe.getAdvertisingPosition().getAdverType())){//广告跑马灯模板名称
				ftlName = messageSource.getMessage(CustomerMessage.ADVERTISING_POMADENG_TEMPLATE);
				paramsMap.put("adverPositionId", advertisingSubscribe.getAdvertisingPosition().getObjId());
				//取得此广告位下的有效广告,组成跑马灯
				List<AdvertisingSubscribe> adverSubscribeList = advertisingPositionService.getAdverSubscribeByPositionId(paramsMap);
				templateMap.put("adverSubscribeList", adverSubscribeList);
			}
			
			templateMap.put("advertisingSubscribe", advertisingSubscribe);
			contentsString = freeMarkerService.getFreeMarkerContent(ftlName, templateMap);
			
			/** 公告文件的存储路径 */
			Properties properties = new Properties();
	        InputStream in =AdvertisingSubscribeServiceImpl.class.getClassLoader().getResourceAsStream("sys/attachment.properties");
	        properties.load(in);
	        String adverUrl = properties.getProperty("adverSubscribePath") ;
			String saveName=advertisingSubscribe.getAdvertisingPosition().getPositionName();
			String path2 = (Constants.ROOTPATH + adverUrl+File.separator+saveName).replace("/", File.separator);;
			File file1 = new File((Constants.ROOTPATH + adverUrl).replace("/", File.separator));
			if(!file1.isDirectory()){
				file1.mkdirs();
			}
			File file2 = new File(path2);
			if(!file2.exists()){
				file2.createNewFile();
			}	
			/** 把文件写在指定的存储路径 */
			FileUtil.writerFile(path2,contentsString,"UTF-8");
		}
		
		model.put(Constants.JSON_RESULT, "true");
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	/**
	 * Description :  手动发布广告
	 * Create Date: 2011-4-11下午02:45:08 by zhaojf  Modified Date: 2011-4-11下午02:45:08 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=publishAdverSubscribe")
	public ModelAndView publishAdverSubscribe(String adverSubscribeIds) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		String[] adverSubscribeArray = adverSubscribeIds.split(",");
		for(String objId : adverSubscribeArray){
			AdvertisingSubscribe advertisingSubscribe = advertisingSubscribeService.get(objId);
			//发布广告
			if(AdvertisingMessage.USE_VALID.equals(advertisingSubscribe.getUseStatus())){
				Map<String, Object> templateMap = new HashMap<String, Object>();
				Map<String, Object> paramsMap = new HashMap<String, Object>();
				String contentsString = "";
				String ftlName = messageSource.getMessage(CustomerMessage.ADVERTISING_PICTURE_TEMPLATE);// 广告图片模板名称
				if("01".equals(advertisingSubscribe.getAdvertisingPosition().getAdverType())){//广告flash模板名称
					ftlName = messageSource.getMessage(CustomerMessage.ADVERTISING_FLASH_TEMPLATE);
				}
				if("02".equals(advertisingSubscribe.getAdvertisingPosition().getAdverType())){//广告跑马灯模板名称
					ftlName = messageSource.getMessage(CustomerMessage.ADVERTISING_POMADENG_TEMPLATE);
					paramsMap.put("adverPositionId", advertisingSubscribe.getAdvertisingPosition().getObjId());
					//取得此广告位下的有效广告,组成跑马灯
					List<AdvertisingSubscribe> adverSubscribeList = advertisingPositionService.getAdverSubscribeByPositionId(paramsMap);
					templateMap.put("adverSubscribeList", adverSubscribeList);
				}
				
				templateMap.put("advertisingSubscribe", advertisingSubscribe);
				contentsString = freeMarkerService.getFreeMarkerContent(ftlName, templateMap);
				
				/** 公告文件的存储路径 */
				Properties properties = new Properties();
		        InputStream in =AdvertisingSubscribeServiceImpl.class.getClassLoader().getResourceAsStream("sys/attachment.properties");
		        properties.load(in);
		        String adverUrl = properties.getProperty("adverSubscribePath") ;
				String saveName=advertisingSubscribe.getAdvertisingPosition().getPositionName();
				String path2 = (Constants.ROOTPATH + adverUrl+File.separator+saveName).replace("/", File.separator);
				File file1 = new File((Constants.ROOTPATH + adverUrl).replace("/", File.separator));
				if(!file1.isDirectory()){
					file1.mkdirs();
				}
				File file2 = new File(path2);
				if(!file2.exists()){
					file2.createNewFile();
				}				
				
				/** 把文件写在指定的存储路径 */
				FileUtil.writerFile(path2,contentsString,"UTF-8");
			}
		}
		model.put(Constants.JSON_RESULT, "true");
		return new ModelAndView(Constants.JSON_VIEW,model);
	}

	/**
	 * Description :  更改广告跑马灯排序
	 * Create Date: 2011-9-23上午09:16:18 by zhaojf  Modified Date: 2011-9-23上午09:16:18 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=updateSort")
	public ModelAndView updateSort(String objId,String isToUp) throws Exception{
		advertisingSubscribeService.updateSort(objId, ("true".equals(isToUp) ? true : false));
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
}
