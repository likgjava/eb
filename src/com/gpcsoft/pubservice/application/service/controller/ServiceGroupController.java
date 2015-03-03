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
import com.gpcsoft.pubservice.application.service.domain.ServiceGroup;
import com.gpcsoft.pubservice.application.service.service.ServiceBaseService;
import com.gpcsoft.pubservice.application.service.service.ServiceGroupService;

/**
 * @gpcsoft.view value="serviceGroupFormView"
 *  url="view/pubservice/application/service/service_group_form.jsp"
 *  
 * @gpcsoft.view value="serviceGroupDetailView"
 *  url="view/pubservice/application/service/service_group_detail.jsp"
 *  
 * @gpcsoft.view value="serviceGroupListView"
 *  url="view/pubservice/application/service/service_group_list.jsp"
 */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={ServiceGroup.class})
@RequestMapping("/ServiceGroupController.do")//页面请求路径,可修改
public class ServiceGroupController extends AnnotationMultiController<ServiceGroup> {

	@Autowired(required=true) @Qualifier("serviceGroupServiceImpl")
	private ServiceGroupService serviceGroupService;
	
	@Autowired(required=true) @Qualifier("serviceBaseServiceImpl")
	private ServiceBaseService serviceBaseService;
	
    /** 
	 * Description :   新增/修改服务组合
	 * Create Date: 2011-3-22下午01:22:39 by likg  Modified Date: 2011-3-22下午01:22:39 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveServiceGroup")
    public ModelAndView saveServiceGroup(ServiceGroup serviceGroup, HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		serviceGroupService.saveServiceGroup(serviceGroup);

		return new ModelAndView(Constants.JSON_VIEW, model);
	}
    
    /** 
	 * Description :   跳转到新增或修改服务组合页面
	 * Create Date: 2011-3-22下午01:22:39 by likg  Modified Date: 2011-3-22下午01:22:39 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@Override
	public ModelAndView toCreateOrUpdate(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>(); 
		ServiceGroup serviceGroup = null;
		String serviceBaseId = request.getParameter("serviceBaseId"); //服务id
		String objId = request.getParameter("objId"); //服务组合id
		
		//获取服务信息
		if(StringUtils.hasLength(serviceBaseId)) {
			model.put("serviceBase", serviceBaseService.get(serviceBaseId));
		}
		
		if(StringUtils.hasLength(objId)) {
			serviceGroup = serviceGroupService.get(objId);
		} else {
			serviceGroup = new ServiceGroup();
		}
		model.put("serviceGroup", serviceGroup);
		
		return new ModelAndView("serviceGroupFormView", model);
	}
	
	/** 
	 * Description :   跳转到服务组合详情页面
	 * Create Date: 2011-3-22下午01:22:39 by likg  Modified Date: 2011-3-22下午01:22:39 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@Override
	public ModelAndView toShowView(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>(); 
		ServiceGroup serviceGroup = null;
		String objId = request.getParameter("objId"); //服务组合id
		
		if(StringUtils.hasLength(objId)) {
			serviceGroup = serviceGroupService.get(objId);
		} else {
			serviceGroup = new ServiceGroup();
		}
		model.put("serviceGroup", serviceGroup);
		
		return new ModelAndView("serviceGroupDetailView", model);
	}
	
	/** 
     * Description :  修改服务组合的使用状态
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
    	serviceGroupService.updateUseStatus(objId, useStatus);
    	
    	return new ModelAndView(Constants.JSON_VIEW, model);
    }
    
    /** 
     * Description :  跳转到服务组合列表页面
     * Create Date: 2011-4-6下午09:01:10 by likg  Modified Date: 2011-4-6下午09:01:10 by likg
     * @param   
     * @return  
     * @Exception   
     */
    @RequestMapping(params = "method=toServiceGroupListView")
    public ModelAndView toServiceGroupListView(HttpServletRequest request) throws Exception {
    	Map<String, Object> model = new HashMap<String, Object>();
    	
    	String serviceBaseId = request.getParameter("serviceBaseId"); //主服务id
    	String hql = "from ServiceGroup sg left join fetch sg.appendService aps left join fetch sg.memberClass mc where sg.mainService.objId=? order by aps.serviceName, mc.memberClassName, sg.duration";
    	List<ServiceGroup> serviceGroupList = serviceGroupService.findByHql(hql, new Object[]{serviceBaseId});
    	model.put("serviceGroupList", serviceGroupList);
    	model.put("serviceBaseId", serviceBaseId);
    	
    	return new ModelAndView("serviceGroupListView", model);
    }

}
