package com.gpcsoft.pubservice.application.service.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.utils.JsonUtils;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.pubservice.application.service.domain.ServiceBase;
import com.gpcsoft.pubservice.application.service.domain.ServiceSubscribe;
import com.gpcsoft.pubservice.application.service.enumeration.ServiceEnum;
import com.gpcsoft.pubservice.application.service.service.ServiceBaseService;
import com.gpcsoft.pubservice.application.service.service.ServiceSubscribeService;
import com.gpcsoft.srplatform.auth.domain.User;

/**
 * @gpcsoft.view value="subscribeAuditView"
 *  url="view/pubservice/application/service/service_audit.jsp"
 *  
 * @gpcsoft.view value="toServicePayView"
 *  url="view/srplatform/chinabank/service_pay.jsp"
 */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={ServiceSubscribe.class})
@RequestMapping("/ServiceSubscribeController.do")//页面请求路径,可修改
public class ServiceSubscribeController extends AnnotationMultiController<ServiceSubscribe> {

	@Autowired(required=true) @Qualifier("serviceSubscribeServiceImpl")
	private ServiceSubscribeService serviceSubscribeService;
	
	@Autowired(required=true) @Qualifier("serviceBaseServiceImpl")
	private ServiceBaseService serviceBaseService;
	 
    /** 
     * Description :  
     * Create Date: 2011-7-21下午04:03:01 by sunl  Modified Date: 2011-7-21下午04:03:01 by sunl
     * @param   
     * @return  
     * @Exception   
     */
    @RequestMapping(params = "method=toServicePayView")
    public ModelAndView toServicePayView(HttpServletRequest request) throws Exception {
    	
    	Map<String, Object> model = new HashMap<String, Object>(); 
    	
    	//服务订阅ID
    	String v_business_id = request.getParameter("v_business_id");

    	//支付业务ID
    	model.put("v_business_id", v_business_id);
    	
    	//服务总金额
    	model.put("v_amount", request.getParameter("v_amount"));
    	
    	//商户返回调用业务方法
		model.put("v_back_req_method", "servicePayResult");
    	
    	return new ModelAndView("toServicePayView",model);
    }
    
    /** 
     * Description :  订阅服务
     * Create Date: 2010-8-11下午04:40:52 by sunl  Modified Date: 2010-8-11下午04:40:52 by sunl
     * @param   
     * @return  
     * @Exception   
     */
    @RequestMapping(params = "method=saveServiceSubscribe")
    public ModelAndView saveServiceSubscribe(HttpServletRequest request) throws Exception {
    	Map<String, Object> model = new HashMap<String, Object>(); 
    	
    	String subscribeStr = request.getParameter("subscribeStr");
		JSONArray subscribeArray = JSONArray.fromObject(subscribeStr);
        
        List<ServiceSubscribe> subscribeList = new ArrayList<ServiceSubscribe>();
        User user = AuthenticationHelper.getCurrentUser(true);
		ServiceBase serviceBase = new ServiceBase();
		ServiceSubscribe subscribe = new ServiceSubscribe();
		for(Object obj : subscribeArray) {
			subscribe = (ServiceSubscribe)JsonUtils.json2Bean(((JSONObject)obj).toString(), ServiceSubscribe.class);
			subscribe.setOrgInfo((OrgInfo)user.getOrgInfo());
			subscribe.setAuditStatus(OrganizationEnum.AWAIT_EXAM);//待审核
			
			serviceBase = serviceBaseService.get(subscribe.getServiceBase().getObjId());
			//设置订阅时长
			Calendar c = Calendar.getInstance();
	        c.setTime(new Date());   //设置当前日期
			if(ServiceEnum.YEAR.equals(serviceBase.getServiceUnit())) {
				c.add(Calendar.YEAR, Integer.valueOf(subscribe.getDuration()).intValue()); //年
			} else if(ServiceEnum.MONTH.equals(serviceBase.getServiceUnit())) {
				c.add(Calendar.MONTH, Integer.valueOf(subscribe.getDuration()).intValue()); //月
			} else if(ServiceEnum.MONTH.equals(serviceBase.getServiceUnit())) {//期
				c.add(Calendar.MONTH, 3); //日期加时长
			}
			Date date = c.getTime(); //结果
		    subscribe.setStartTime(new Date());
		    subscribe.setEndTime(date);
		    subscribe.setCreateUser(user);
		    subscribe.setCreateTime(new Date());
		    subscribeList.add(subscribe);
		}
        serviceSubscribeService.save(subscribeList);
        
        //返回订阅业务ID,服务费用
        model.put("v_business_id", subscribeList.get(0).getObjId());
        model.put("v_amount", subscribeList.get(0).getPayAmount());
        
    	return new ModelAndView(Constants.JSON_VIEW,model);
    }
    
    /** 
     * Description :  跳转到审核订阅的服务页面
     * Create Date: 2010-8-11下午04:40:52 by sunl  Modified Date: 2010-8-11下午04:40:52 by sunl
     * @param   
     * @return  
     * @Exception   
     */
    @RequestMapping(params = "method=toAuditSubscribe")
    public ModelAndView toAuditSubscribe(HttpServletRequest request) throws Exception {
    	Map<String, Object> model = new HashMap<String, Object>(); 
    	String objId = request.getParameter("objId");
    	ServiceSubscribe servcieSubscribe = serviceSubscribeService.get(objId);
    	model.put("subscribe", servcieSubscribe);
        return new ModelAndView("subscribeAuditView", model);
    }
    
    /** 
     * Description :  审核订阅的服务
     * 			      审核通过后同步会员信息
     * Create Date: 2010-8-11下午04:40:52 by sunl  Modified Date: 2010-8-11下午04:40:52 by sunl
     * @param   
     * @return  
     * @Exception   
     */
    @RequestMapping(params = "method=auditSubscribe")
    public ModelAndView auditSubscribe(HttpServletRequest request) throws Exception {
    	Map<String, Object> model = new HashMap<String, Object>(); 
    	Map<String, Object> params = new HashMap<String, Object>(); 
    	
    	params.put("subscribeId", request.getParameter("objId"));
    	params.put("auditStatus", request.getParameter("auditStatus"));
    	
    	serviceSubscribeService.auditSubscribe(params);
    	
        return new ModelAndView(Constants.JSON_VIEW, model);
    }
    
    /** 
     * Description :  取消订阅的服务
     * 				  管理员对服务进行取消时,要根据服务状态进行判断,未审核,删掉,已审核,把结束时间改为当前时间
     * Create Date: 2010-8-11下午04:40:52 by sunl  Modified Date: 2010-8-11下午04:40:52 by sunl
     * @param   
     * @return  
     * @Exception   
     */
    @RequestMapping(params = "method=cancelSubscribe")
    public ModelAndView cancelSubscribe(HttpServletRequest request) throws Exception {
    	Map<String, Object> model = new HashMap<String, Object>(); 
    	
    	ServiceSubscribe subscribe = serviceSubscribeService.get(request.getParameter("objId"));
    	
    	//审核通过，把结束时间改为当前时间
    	if(ServiceEnum.PASS_EXAM.equals(subscribe.getAuditStatus())) {
    		subscribe.setEndTime(new Date());
    		subscribe.setRemark(AuthenticationHelper.getCurrentUser(true).getEmp().getName()+"于"+new Date()+" 取消了此服务的订阅");
    		serviceSubscribeService.save(subscribe);
    	}
    	//还未审核，删除
    	else {
    		serviceSubscribeService.remove(request.getParameter("objId"), ServiceSubscribe.class);
    	}
    	
        return new ModelAndView(Constants.JSON_VIEW, model);
    }
    
    /** 
     * Description :  判断是否已订阅过该服务
     * Create Date: 2011-4-1下午03:08:41 by sunl  Modified Date: 2011-4-1下午03:08:41 by sunl
     * @param   
     * @return  
     * @Exception   
     */
    @RequestMapping(params = "method=isServcieSubscribed")
    public ModelAndView isServcieSubscribed(HttpServletRequest request) throws Exception {
    	Map<String, Object> model = new HashMap<String, Object>(); 
    	Map<String, Object> params = new HashMap<String, Object>(); 
    	params.put("serviceId", request.getParameter("serviceId"));
    	params.put("orgInfoId", ((OrgInfo)AuthenticationHelper.getCurrentUser(true).getOrgInfo()).getObjId());
    	Integer result = serviceSubscribeService.isServcieSubscribed(params);
    	model.put("result", result);
    	return new ModelAndView(Constants.JSON_VIEW, model);
    }
}
