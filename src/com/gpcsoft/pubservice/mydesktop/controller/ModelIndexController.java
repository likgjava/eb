package com.gpcsoft.pubservice.mydesktop.controller;

import java.util.Calendar;
import java.util.Date;
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
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.bizplatform.organization.service.OrgInfoService;
import com.gpcsoft.bizplatform.organization.service.QualificationDetailService;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.utils.StringUtils;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.pubservice.application.member.domain.Member;
import com.gpcsoft.pubservice.application.member.service.MemberService;
import com.gpcsoft.pubservice.application.service.domain.ServiceBase;
import com.gpcsoft.pubservice.application.service.service.ServiceBaseService;
import com.gpcsoft.pubservice.application.service.service.ServiceSubscribeService;
import com.gpcsoft.pubservice.common.enumeration.CommonEnum;
import com.gpcsoft.pubservice.common.service.IndexViewService;
import com.gpcsoft.pubservice.mydesktop.service.ModelIndexService;
import com.gpcsoft.smallscale.chart.service.ChartService;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.auth.manager.RoleManager;

/**
 * 
 * @gpcsoft.view value="deskTopIndexView"
 *  url="view/srplatform/portal/desk/myDesktop.jsp"
 *  
 * @gpcsoft.view value="deskTopIndexViewES"
 *  url="view/srplatform/portal/index_es.jsp"
 *  
 * @gpcsoft.view value="supplierIndexView"
 *  url="view/pubservice/mydesktop/supplier_index.jsp"
 *  
 * @gpcsoft.view value="buyerIndexView"
 *  url="view/pubservice/mydesktop/buyer_index.jsp"
 *  
 * @gpcsoft.view value="expertIndexView"
 *  url="view/pubservice/mydesktop/expert_index.jsp"
 *  
 * @gpcsoft.view value="agencyIndexView"
 *  url="view/pubservice/mydesktop/agency_index.jsp"
 *  
 * @gpcsoft.view value="goodsIndexView"
 *  url="view/pubservice/mydesktop/goods_index.jsp"
 *  
 * @gpcsoft.view value="managerOrgInfoIndexView"
 *  url="view/pubservice/mydesktop/managerOrgInfo_index.jsp"
 *  
 * @gpcsoft.view value="orgInfoIndexView"
 *  url="view/pubservice/mydesktop/orgInfo_index.jsp"
 *  
 * @gpcsoft.view value="baseDataIndexView"
 *  url="view/pubservice/mydesktop/baseData_index.jsp"
 */
@Controller//标识为控制器
@Scope("request")
@RequestMapping("/ModelIndexController.do")//页面请求路径,可修改
public class ModelIndexController extends AnnotationMultiController<GpcBaseObject> {

	@Autowired(required=true) @Qualifier("modelIndexServiceImpl")
	private ModelIndexService modelIndexService;
	
	@Autowired(required=true) @Qualifier("indexViewServiceImpl")
	private IndexViewService indexViewService;
	
	@Autowired(required=true) @Qualifier("orgInfoServiceImpl")
	private OrgInfoService orgInfoService;
	
	@Autowired(required=true) @Qualifier("roleManagerImpl")
	private RoleManager roleManagerImpl;
	
	@Autowired(required=true) @Qualifier("chartServiceImpl")
	private ChartService chartService;
	
	@Autowired(required=true) @Qualifier("qualificationDetailServiceImpl")
	private QualificationDetailService qualificationDetailService;
	
	@Autowired(required=true) @Qualifier("memberServiceImpl")
	private MemberService memberService;
	
	@Autowired(required=true) @Qualifier("serviceBaseServiceImpl")
	private ServiceBaseService serviceBaseService;
	
	@Autowired(required=true) @Qualifier("serviceSubscribeServiceImpl")
	private ServiceSubscribeService serviceSubscribeService;
	
	/** 
	 * Description :  获得基础数据的首页
	 * Create Date: 2010-8-16上午10:47:31 by sunl  Modified Date: 2010-8-16上午10:47:31 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getBaseDataIndex")
	public ModelAndView getBaseDataIndex(HttpServletRequest request) throws Exception {	
		return new ModelAndView("baseDataIndexView", modelIndexService.getBaseDataIndex());	
	}
			
	/** 
	 * Description :  跳转到我的桌面
	 * Create Date: 2010-8-16上午10:47:31 by sunl  Modified Date: 2010-8-16上午10:47:31 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toDeskTopIndex")
	public ModelAndView toDeskTopIndex(HttpServletRequest request, HttpSession session) throws Exception {	
		Map<String, Object> model = new HashMap<String, Object>();  
		
		String viewName = "deskTopIndexView";
		
		//关闭外网样式标记
		request.getSession().setAttribute("isOutCss", false);
		
		User user = indexViewService.getAndSetCurrentUserInfo(model, session);
		model.put("user",user);
		
		if(user == null) {
			viewName = "indexView";
		}else{
			//获取供应商或采购人机构状态
			Map<String,Object> resultMap = orgInfoService.getCurrentOrgStatus();
			model.put("currSuppStatus", resultMap.get("currSuppStatus").toString());
			model.put("currBuyStatus", resultMap.get("currBuyStatus").toString());
			
			//设置跳转的viewName为传入的值
			String vn = request.getParameter("viewName");
			if(vn != null && !"".equals(vn))
				viewName = vn;
		}
		
		if(viewName.equals("deskTopIndexView")){
			session.setAttribute("sysFlag", CommonEnum.XEJY); //传入系统标识符为小额采购
		}
		else if(viewName.equals("deskTopIndexViewES")){
			session.setAttribute("sysFlag", CommonEnum.ZTB); //传入系统标识符为项目采购
		}

		return new ModelAndView(viewName,model);	
	}

	
	/** 
	 * Description :  获得供应商的首页
	 * Create Date: 2010-8-16上午10:47:31 by sunl  Modified Date: 2010-8-16上午10:47:31 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getSupplierIndex")
	public ModelAndView getSupplierIndex(HttpServletRequest request) throws Exception {	
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String,Object> param = new HashMap<String, Object>();
		model = modelIndexService.getSupplierIndex();
		
		//获取会员信息
		param.put("orgInfoId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId());
		Member member = memberService.getMemberByOrgInfoId(param);
		model.put("member", member);
		
		//获取‘月销售额和销售量同比、环比图’的图表
		param.clear();
		param.put("year1", (Calendar.getInstance().get(Calendar.YEAR)-1)+""); //年份1
		param.put("year2", Calendar.getInstance().get(Calendar.YEAR)+""); //年份2
		param.put("supplierId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId());
		Map<String, Object> result = chartService.getAmountQtyCompareData(param);
		model.put("chartXml1", result.get("chartXml"));
		
		//获取‘各品目年度销售总额分布图’
		param.clear();
		param.put("year", Calendar.getInstance().get(Calendar.YEAR)+""); //年份
		param.put("supplierId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId());
		result = chartService.getPurcategorysAmountPieData(param);
		model.put("chartXml2", result.get("chartXml"));
		
		return new ModelAndView("supplierIndexView", model);	
	}
	
	/** 
	 * Description :  获得采购人的首页
	 * Create Date: 2010-8-16上午10:47:31 by sunl  Modified Date: 2010-8-16上午10:47:31 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getBuyerIndex")
	public ModelAndView getBuyerIndex(HttpServletRequest request) throws Exception {	
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String,Object> param = new HashMap<String, Object>();
		model = modelIndexService.getBuyerIndex();
		
		//获取会员信息
		param.put("orgInfoId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId());
		Member member = memberService.getMemberByOrgInfoId(param);
		model.put("member", member);
		
		
		
		//获取‘月采购额和采购量同比、环比图’的图表
		param.clear();
		param.put("year1", (Calendar.getInstance().get(Calendar.YEAR)-1)+""); //年份1
		param.put("year2", Calendar.getInstance().get(Calendar.YEAR)+""); //年份2
		param.put("buyerId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId());
		Map<String, Object> result = chartService.getAmountQtyCompareData(param);
		model.put("chartXml1", result.get("chartXml"));
		
		//获取‘各品目年度采购总额分布图’
		param.clear();
		param.put("year", Calendar.getInstance().get(Calendar.YEAR)+""); //年份
		param.put("buyerId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId());
		result = chartService.getPurcategorysAmountPieData(param);
		model.put("chartXml2", result.get("chartXml"));
		
		return new ModelAndView("buyerIndexView", model);	
	}
	
	/** 
	 * Description :  获得专家的首页
	 * Create Date: 2010-8-16上午10:47:31 by sunl  Modified Date: 2010-8-16上午10:47:31 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getExpertIndex")
	public ModelAndView getExpertIndex(HttpServletRequest request) throws Exception {	
		return new ModelAndView("expertIndexView", modelIndexService.getExpertIndex());	
	}
	
	/** 
	 * Description :  获得代理机构的首页
	 * Create Date: 2010-8-16上午10:47:31 by sunl  Modified Date: 2010-8-16上午10:47:31 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getAgencyIndex")
	public ModelAndView getAgencyIndex(HttpServletRequest request) throws Exception {	
		return new ModelAndView("agencyIndexView", modelIndexService.getAgencyIndex());	
	}
	
	/** 
	 * Description :  获得商品库的首页
	 * Create Date: 2010-8-16上午10:47:31 by sunl  Modified Date: 2010-8-16上午10:47:31 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getGoodsIndex")
	public ModelAndView getGoodsIndex(HttpServletRequest request) throws Exception {	
		return new ModelAndView("goodsIndexView", modelIndexService.getGoodsIndex());	
	}
	
	/** 
	 * Description :  获得机构管理的首页
	 * Create Date: 2010-8-16上午10:47:31 by sunl  Modified Date: 2010-8-16上午10:47:31 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getOrgInfoIndex")
	public ModelAndView getOrgInfoIndex(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		String viewName = "";
		User user = AuthenticationHelper.getCurrentUser(true);
		OrgInfo orgInfo = (OrgInfo)user.getOrgInfo();
		
		//如果当前用户是manager，进入manager的机构管理首页
		if(roleManagerImpl.isHasThisRole(user.getObjId(), OrganizationEnum.ROLE_MANAGER)){
			viewName = "managerOrgInfoIndexView";
			model = modelIndexService.getOrgInfoIndex("");
		}
		else{
			viewName = "orgInfoIndexView";
			model = modelIndexService.getOrgInfoIndex(orgInfo.getObjId());
			
			//获取会员信息
			Map<String,Object> param = new HashMap<String, Object>();
			param.put("orgInfoId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId());
			Member member = memberService.getMemberByOrgInfoId(param);
			model.put("member", member);
			
			//获取供应商或采购人机构状态
			Map<String,Object> resultMap = orgInfoService.getCurrentOrgStatus();
			model.put("currSuppStatus", resultMap.get("currSuppStatus").toString());
			model.put("currBuyStatus", resultMap.get("currBuyStatus").toString());
			
			//如果当前用户机构buyerId 不为空
			if(StringUtils.hasLength(orgInfo.getBuyerId())) {
				model.put("isRoleBuyer", "true");
			}
			
			//获取财务财务信息的完整性
			model.put("qC01", qualificationDetailService.getQualificationDetailListByCodeAndOrgId("C01", orgInfo.getObjId()));
			
			//获取可订阅的服务
			param.clear();
			param.put("orgInfoId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId());
			List<ServiceBase> serviceList = serviceBaseService.getServiceSubscribeList();
			String subServiceIds = serviceSubscribeService.getSubscribedServiceIds(param);
			for(ServiceBase service : serviceList) {
				if(!StringUtils.hasLength(subServiceIds) || service.getObjId().indexOf(subServiceIds)!=-1) {
					model.put("serviceBase", service);
					break ;
				}
			}

		}
		model.put("nowDate", new Date()); //当前日期
		
		return new ModelAndView(viewName, model);	
	}
	
	/** 
	 * Description :  获取不同时间段的机构统计信息
	 * Create Date: 2011-8-22下午05:00:01 by likg  Modified Date: 2011-8-22下午05:00:01 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getOrgStatInfoByTime")
	public ModelAndView getOrgStatInfoByTime(HttpServletRequest request) throws Exception {
		Map<String, String> model = new HashMap<String, String>();
		model = modelIndexService.getOrgStatisticsInfoByTime(request.getParameter("orgInfoId"), Integer.parseInt(request.getParameter("days")));
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
}
