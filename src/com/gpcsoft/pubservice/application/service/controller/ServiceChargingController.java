package com.gpcsoft.pubservice.application.service.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.core.Constants;
import com.gpcsoft.core.utils.StringUtils;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.pubservice.application.service.domain.ServiceCharging;
import com.gpcsoft.pubservice.application.service.service.ServiceBaseService;
import com.gpcsoft.pubservice.application.service.service.ServiceChargingService;

/**
 * @gpcsoft.view value="serviceChargingFormView"
 *  url="view/pubservice/application/service/service_charging_form.jsp"
 *  
 * @gpcsoft.view value="serviceChargingListView"
 *  url="view/pubservice/application/service/service_charging_list.jsp"
 */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={ServiceCharging.class})
@RequestMapping("/ServiceChargingController.do")//页面请求路径,可修改
public class ServiceChargingController extends AnnotationMultiController<ServiceCharging> {

	@Autowired(required=true) @Qualifier("serviceChargingServiceImpl")
	private ServiceChargingService serviceChargingService;
	
	@Autowired(required=true) @Qualifier("serviceBaseServiceImpl")
	private ServiceBaseService serviceBaseService;
	
    /** 
	 * Description :   新增/修改服务计费
	 * Create Date: 2011-3-22下午01:22:39 by likg  Modified Date: 2011-3-22下午01:22:39 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveServiceCharging")
    public ModelAndView saveServiceCharging(ServiceCharging serviceCharging, HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		serviceChargingService.saveServiceCharging(serviceCharging);

		return new ModelAndView(Constants.JSON_VIEW, model);
	}
    
    /** 
	 * Description :   跳转到新增或修改服务计费页面
	 * Create Date: 2011-3-22下午01:22:39 by likg  Modified Date: 2011-3-22下午01:22:39 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@Override
	public ModelAndView toCreateOrUpdate(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>(); 
		ServiceCharging serviceCharging = null;
		String serviceBaseId = request.getParameter("serviceBaseId"); //服务id
		String objId = request.getParameter("objId"); //服务计费id
		
		//获取服务信息
		if(StringUtils.hasLength(serviceBaseId)) {
			model.put("serviceBase", serviceBaseService.get(serviceBaseId));
		}
		
		if(StringUtils.hasLength(objId)) {
			serviceCharging = serviceChargingService.get(objId);
		} else {
			serviceCharging = new ServiceCharging();
		}
		model.put("serviceCharging", serviceCharging);
		
		return new ModelAndView("serviceChargingFormView", model);
	}
	
	/** 
     * Description :  修改服务计费的使用状态
     * Create Date: 2011-3-23下午05:09:24 by likg  Modified Date: 2011-3-23下午05:09:24 by likg
     * @param   
     * @return  
     * @Exception   
     */
    @RequestMapping(params = "method=updateUseStatus")
    public ModelAndView updateUseStatus(HttpServletRequest request) throws Exception {
    	Map<String, Object> model = new HashMap<String, Object>();
    	
    	String useStatus = request.getParameter("useStatus");
    	String objId = request.getParameter("objId");
    	serviceChargingService.updateUseStatus(objId, useStatus);
    	
    	return new ModelAndView(Constants.JSON_VIEW, model);
    }
    
    /** 
     * Description :  跳转到服务计费列表页面
     * Create Date: 2011-4-6下午09:01:10 by likg  Modified Date: 2011-4-6下午09:01:10 by likg
     * @param   
     * @return  
     * @Exception   
     */
    @RequestMapping(params = "method=toServiceChargingListView")
    public ModelAndView toServiceChargingListView(HttpServletRequest request) throws Exception {
    	Map<String, Object> model = new HashMap<String, Object>();
    	
    	String serviceBaseId = request.getParameter("serviceBaseId"); //所属服务id
    	String hql = "from ServiceCharging sc left join fetch sc.memberClass mc where sc.serviceBase.objId=? order by mc.memberClassName , sc.duration";
    	List<ServiceCharging> serviceChargingList = serviceChargingService.findByHql(hql, new Object[]{serviceBaseId});
    	model.put("serviceChargingList", serviceChargingList);
    	model.put("serviceBaseId", serviceBaseId);
    	
    	return new ModelAndView("serviceChargingListView", model);
    }

}
