package com.gpcsoft.pubservice.application.service.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.core.Constants;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.utils.StringUtils;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.pubservice.application.service.domain.ServiceBase;
import com.gpcsoft.pubservice.application.service.domain.ServiceCharging;
import com.gpcsoft.pubservice.application.service.domain.ServiceGroup;
import com.gpcsoft.pubservice.application.service.domain.ServiceSubscribe;
import com.gpcsoft.pubservice.application.service.enumeration.ServiceEnum;
import com.gpcsoft.pubservice.application.service.service.ServiceBaseService;
import com.gpcsoft.pubservice.application.service.service.ServiceChargingService;
import com.gpcsoft.pubservice.application.service.service.ServiceGroupService;
import com.gpcsoft.pubservice.application.service.service.ServiceSubscribeService;
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.service.AttachmentService;
import com.gpcsoft.srplatform.common.utils.AttachmentUtil;

/**
  * @gpcsoft.view value="serviceBaseFormView"
  *  url="view/pubservice/application/service/service_form.jsp"
  *  
  * @gpcsoft.view value="serviceBaseListView"
  *  url="view/pubservice/application/service/service_list.jsp"
  *  
  * @gpcsoft.view value="serviceSubscribeFormView"
  *  url="view/pubservice/application/service/service_order.jsp"
  *  
  * @gpcsoft.view value="serviceSubscribeListView"
  *  url="view/pubservice/application/service/service_order_list.jsp"
  *  
  * @gpcsoft.view value="toMySubscribeListView"
  *  url="view/pubservice/application/service/my_order_list.jsp"
  *  
  * @gpcsoft.view value="serviceBaseDetailView"
  *  url="view/pubservice/application/service/service_detail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={ServiceBase.class})
@RequestMapping("/ServiceBaseController.do")//页面请求路径,可修改
public class ServiceBaseController extends AnnotationMultiController<ServiceBase> {

	@Autowired(required=true) @Qualifier("serviceBaseServiceImpl")
	private ServiceBaseService serviceBaseService;
	
	@Autowired(required=true) @Qualifier("serviceSubscribeServiceImpl")
	private ServiceSubscribeService serviceSubscribeService;
	
	@Autowired(required=true) @Qualifier("serviceGroupServiceImpl")
	private ServiceGroupService serviceGroupService;
	
	@Autowired(required=true) @Qualifier("serviceChargingServiceImpl")
	private ServiceChargingService serviceChargingService;
	
	@Autowired(required=true) @Qualifier("attachmentServiceImpl")
	private AttachmentService attachmentService;
	
	/** 
	 * Description :   新增/修改服务
	 * Create Date: 2011-3-22下午01:22:39 by likg  Modified Date: 2011-3-22下午01:22:39 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveService")
    public ModelAndView saveService(ServiceBase serviceBase, HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		//处理服务图片
		String returnMessage = "success";
		CommonsMultipartFile file = null;
		 try {
			 MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
		     file = (CommonsMultipartFile)multipartRequest.getFile("pictureFile");
			 if(file != null && file.getSize() != 0){
				 Object o=AttachmentUtil.uploadFile(request,"pictureFile");
				 if(o instanceof GpcBaseObject){
						Attachment attachment = (Attachment)o;
						attachmentService.saveAttachment((Attachment)o);
						serviceBase.setServicePic(attachment.getObjId());
				 }
			 }
		 } catch (Exception de) {
			returnMessage = de.getMessage();
		 }
		 
		 //图片上传成功
		 if("success".equals(returnMessage)) {
			 serviceBaseService.saveService(serviceBase);
		 }

		 model.put("returnMessage", returnMessage);
		
		 return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :   跳转到新增或修改服务页面
	 * Create Date: 2011-3-22下午01:22:39 by likg  Modified Date: 2011-3-22下午01:22:39 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@Override
	public ModelAndView toCreateOrUpdate(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>(); 
		ServiceBase serviceBase = null;
		String objId = request.getParameter("objId"); //服务id
		
		if(StringUtils.hasLength(objId)) {
			serviceBase = serviceBaseService.get(objId);
		} else {
			serviceBase = new ServiceBase();
		}
		model.put("serviceBase", serviceBase);
		
		return new ModelAndView("serviceBaseFormView", model);
	}
	
	/** 
	 * Description :   查看服务详情
	 * Create Date: 2011-3-22下午01:22:39 by likg  Modified Date: 2011-3-22下午01:22:39 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@Override
	public ModelAndView toShowView(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>(); 
		ServiceBase serviceBase = null;
		String objId = request.getParameter("objId"); //服务id
		String viewType = request.getParameter("viewType"); //服务详情页面类型[allInfo:包括服务计费和搭配服务列表；simpleInfo:只有服务信息]
		
		if(StringUtils.hasLength(objId)) {
			serviceBase = serviceBaseService.get(objId);
		} else {
			serviceBase = new ServiceBase();
		}
		model.put("serviceBase", serviceBase);
		model.put("viewType", viewType);
		
		return new ModelAndView("serviceBaseDetailView", model);
	}

	/** 
     * Description :  跳转到订阅服务列表页面
     * Create Date: 2010-8-11下午04:40:52 by sunl  Modified Date: 2010-8-11下午04:40:52 by sunl
     * @param   
     * @return  
     * @Exception   
     */
    @RequestMapping(params = "method=toServiceSubscribeList")
    public ModelAndView toServiceSubscribeList(HttpServletRequest request,HttpSession session) throws Exception {
    	Map<String, Object> model = new HashMap<String, Object>(); 
    	Map<String, Object> params = new HashMap<String, Object>(); 
    	params.put("orgInfoId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId());
    	
    	//获取服务列表
    	List<ServiceBase> serviceBaseList = serviceBaseService.getServiceSubscribeList();
    	
    	//获取当前机构订阅的所有服务ID（包括前置服务和待审核的服务）
    	String subscribedServiceIds = serviceSubscribeService.getSubscribedServiceIds(params);
    	
    	model.put("serviceBaseList", serviceBaseList);
    	model.put("subscribedServiceIds", subscribedServiceIds);
    	
        return new ModelAndView("serviceSubscribeListView", model);
    }

    /** 
     * Description :  跳转到我的已订阅服务页面
     * Create Date: 2010-8-11下午04:40:52 by sunl  Modified Date: 2010-8-11下午04:40:52 by sunl
     * @param   
     * @return  
     * @Exception   
     */
    @RequestMapping(params = "method=toMySubscribeList")
    public ModelAndView toMySubscribeList(HttpServletRequest request,HttpSession session) throws Exception {
    	Map<String, Object> model = new HashMap<String, Object>(); 
    	Map<String, Object> params = new HashMap<String, Object>(); 
    	params.put("orgInfoId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId());
    	//我已订阅的服务
    	List<ServiceSubscribe> mySubscribeList = serviceSubscribeService.getMySubscribeList(params);
    	model.put("mySubscribeList", mySubscribeList);
        return new ModelAndView("toMySubscribeListView", model);
    }
    
	/** 
     * Description :  跳转到订阅服务页面
     * Create Date: 2010-8-11下午04:40:52 by sunl  Modified Date: 2010-8-11下午04:40:52 by sunl
     * @param   
     * @return  
     * @Exception   
     */
    @RequestMapping(params = "method=toServiceSubscribeForm")
    public ModelAndView toServiceSubscribeForm(HttpServletRequest request) throws Exception {
    	Map<String, Object> model = new HashMap<String, Object>(); 
    	Map<String, Object> params = new HashMap<String, Object>(); 
    	String objId = request.getParameter("objId");
    	ServiceBase serviceBase = serviceBaseService.get(objId);
    	
    	//获取搭配服务--根据会员级别进行过滤（会员级别为空的也应该被查询出来）
    	params.put("serviceId", objId);
    	params.put("memberClassId", request.getSession().getAttribute("memberClassId")==null?"":request.getSession().getAttribute("memberClassId").toString());
    	List<ServiceGroup> serviceGroupList = serviceGroupService.getServiceGroupList(params);
    	
    	//获取服务计费信息
    	params.put("useStatus", ServiceEnum.USE_VALID);
    	List<ServiceCharging> serviceChargingList = serviceChargingService.getServiceChargingList(params);
    	
    	model.put("serviceBase", serviceBase);
    	model.put("serviceGroupList", serviceGroupList);
    	model.put("serviceChargingList", serviceChargingList);
    	
        return new ModelAndView("serviceSubscribeFormView", model);
    }
    
    /** 
     * Description :  修改服务的状态
     * Create Date: 2011-3-23下午05:09:24 by likg  Modified Date: 2011-3-23下午05:09:24 by likg
     * @param   
     * @return  
     * @Exception   
     */
    @RequestMapping(params = "method=updateStatus")
    public ModelAndView updateStatus(HttpServletRequest request) throws Exception {
    	Map<String, Object> model = new HashMap<String, Object>();
    	Map<String, Object> param = new HashMap<String, Object>();
    	
    	param.put("objId", request.getParameter("objId"));
    	param.put("statusName", request.getParameter("statusName"));
    	param.put("statusValue", request.getParameter("statusValue"));
    	serviceBaseService.updateStatus(param);
    	
    	return new ModelAndView(Constants.JSON_VIEW, model);
    }
    
    /** 
     * Description :  根据服务ID获取各个级别最小时长的服务计费
     * Create Date: 2010-8-11下午04:40:52 by sunl  Modified Date: 2010-8-11下午04:40:52 by sunl
     * @param   
     * @return  
     * @Exception   
     */
    @RequestMapping(params = "method=getFeeByServiceId")
    public ModelAndView getFeeByServiceId(HttpServletRequest request) throws Exception {
    	Map<String, Object> model = new HashMap<String, Object>(); 
    	
    	Map<String, Object> params = new HashMap<String, Object>(); 
    	params.put("serviceId", request.getParameter("serviceId"));
    	List<ServiceCharging> feeList = serviceChargingService.getMinAgeAndFeeByOrgInfoId(params);
    	
    	model.put("feeList", feeList);
    	
    	return new ModelAndView(Constants.JSON_VIEW, model);
    }
    
    /** 
     * Description :  跳转到服务列表页面
     * Create Date: 2011-4-6下午07:57:38 by likg  Modified Date: 2011-4-6下午07:57:38 by likg
     * @param   
     * @return  
     * @Exception   
     */
    @RequestMapping(params = "method=toServiceBaseListView")
    public ModelAndView toServiceBaseListView(HttpServletRequest request) throws Exception {
    	Map<String, Object> model = new HashMap<String, Object>();
    	Map<String, Object> param = new HashMap<String, Object>();
    	
    	String serviceName = request.getParameter("serviceName");
    	param.put("serviceName", serviceName);
    	serviceBaseService.getServiceAllInfo(param, model);
    	
    	//回填服务名称
    	model.put("serviceName", serviceName);
    	
    	return new ModelAndView("serviceBaseListView", model);
    }
}
