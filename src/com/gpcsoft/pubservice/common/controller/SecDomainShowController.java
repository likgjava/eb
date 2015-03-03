package com.gpcsoft.pubservice.common.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.bizplatform.base.common.util.StringUtils;
import com.gpcsoft.bizplatform.buyer.domain.Buyer;
import com.gpcsoft.bizplatform.buyer.service.BuyerService;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.organization.service.EvaluateService;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.pubservice.application.service.service.ShangquanhuiyuanPrivilegeService;
import com.gpcsoft.pubservice.common.service.SecDomainService;
import com.gpcsoft.pubservice.common.service.impl.SecDomainServiceImpl;

/**
  */
@Controller//标识为控制器
@Scope("request")
@RequestMapping("/SecDomainShowController.do")//页面请求路径,可修改
public class SecDomainShowController extends AnnotationMultiController<GpcBaseObject> {
	
	@Autowired(required=true) @Qualifier("buyerServiceImpl")
	private BuyerService buyerService;
	
	@Autowired(required=true) @Qualifier("evaluateServiceImpl")
	private EvaluateService evaluateService;
	
	@Autowired(required=true) @Qualifier("shangquanhuiyuanPrivilegeServiceImpl")
	private ShangquanhuiyuanPrivilegeService shangquanhuiyuanPrivilegeService;
	
	@Autowired(required=true) @Qualifier("secDomainServiceImpl")
	private SecDomainService secDomainService;
	
	/**
	 * @return跳转到二级域名
	 * @throws Exception
	 */
	@RequestMapping(params = "method=toSecDomain")
	public ModelAndView toSecDomain(String  siteName ,HttpServletRequest request) throws Exception{
		OrgInfo orgInfo = SecDomainServiceImpl.getOrgInfoByPassSite( siteName );
		Map<String,Object> model = new HashMap<String,Object>();
		
		//查询采购人
		Buyer buyer = buyerService.getBuyerAllInfo(orgInfo.getBuyerId());
		model.put("buyer", buyer);
		
		//导航参数
		model.put("unitType", request.getParameter("unitType"));
		model.put("districtId", request.getParameter("districtId"));
		
		if(request.getParameter("districtName")!=null&&!"".equals(request.getParameter("districtName"))){
			model.put("districtName", StringUtils.ascii2Native(request.getParameter("districtName")));
		}

		//查询评价详细信息 
		List<String[]> quotaDetailList = (List<String[]>)evaluateService.getQuataScoreDetail(buyer.getOrgInfo(),"02");
		model.put("quotaDetailList", quotaDetailList);
		
		//详细页面特定显示的tab
    	model.put("tabId", request.getParameter("tabId"));
    	
    	//是否显示联系方式
    	if(AuthenticationHelper.getCurrentUser() != null  && AuthenticationHelper.getCurrentUser().getOrgInfo()!=null) {
    		model.put("isShowContact", shangquanhuiyuanPrivilegeService.getIsShowContact((AuthenticationHelper.getCurrentUser().getOrgInfo()).getObjId(), buyer.getOrgInfo().getObjId()));
    	}
		return new ModelAndView("buyerShowInfoView", model);
	}
	
	/** 
	 * Description :  刷新内存中的二级域名
	 * Create Date: 2011-10-11下午04:40:50 by yucy  Modified Date: 2011-10-11下午04:40:50 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toRefreshSecondDomain")
    public ModelAndView toRefreshSecondDomain(HttpServletRequest request) throws Exception {
		secDomainService.refreshSecondDomain();
		return new ModelAndView(Constants.JSON_VIEW);
    }
}
