package com.gpcsoft.smallscale.business.controller;

import java.util.ArrayList;
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
import com.gpcsoft.bizplatform.organization.domain.Evaluate;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.organization.service.EvaluateService;
import com.gpcsoft.bizplatform.organization.service.IllegalRecService;
import com.gpcsoft.bizplatform.organization.service.OrgInfoService;
import com.gpcsoft.bizplatform.suppliers.domain.Supplier;
import com.gpcsoft.bizplatform.suppliers.service.SupplierService;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.goods.goods.domain.Goods;
import com.gpcsoft.goods.goodsclass.domain.GoodsClass;
import com.gpcsoft.goods.goodsprice.service.GoodsPriceSupplierService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.pubservice.application.message.domain.Complain;
import com.gpcsoft.pubservice.application.message.service.ComplainService;
import com.gpcsoft.pubservice.application.service.service.ServiceSubscribeService;
import com.gpcsoft.srplatform.auth.domain.Company;
import com.gpcsoft.srplatform.auth.domain.Employee;
import com.gpcsoft.srplatform.auth.service.EmployeeService;

/**
  *  @gpcsoft.view value="orgShopIndexView"
  *  url="view/smallscale/orgshop/show_orgshop_index.jsp"
  *  
  *  @gpcsoft.view value="orgshopIntroductionView"
  *  url="view/smallscale/orgshop/orgshop_introduction.jsp"
  *  
  *  @gpcsoft.view value="orgshopCertificateView"
  *  url="view/smallscale/orgshop/orgshop_certificate.jsp"
  *  
  *  @gpcsoft.view value="orgshopCaseView"
  *  url="view/smallscale/orgshop/orgshop_case.jsp"
  *  
  *  @gpcsoft.view value="orgshopGoodsInfoView"
  *  url="view/smallscale/orgshop/orgshop_goods_info.jsp"
  *  
  *  @gpcsoft.view value="orgshopEvaluationView"
  *  url="view/smallscale/orgshop/orgshop_evaluation.jsp"
  *  
  *  @gpcsoft.view value="orgshopComplainView"
  *  url="view/smallscale/orgshop/orgshop_complain.jsp"
  *  
  *  @gpcsoft.view value="orgshopContactView"
  *  url="view/smallscale/orgshop/orgshop_contact.jsp"
  *  
  */
@Controller//标识为控制器
@Scope("request")
@RequestMapping("/OrgShopController.do")//页面请求路径,可修改
public class OrgShopController extends AnnotationMultiController<GpcBaseObject> {
	
	@Autowired(required=true) @Qualifier("orgInfoServiceImpl")
	private OrgInfoService orgInfoService;
	
	@Autowired(required=true) @Qualifier("supplierServiceImpl")
	private SupplierService supplierService;
	
	@Autowired(required=true) @Qualifier("goodsPriceSupplierServiceImpl")
	private GoodsPriceSupplierService goodsPriceSupplierService;
	
	@Autowired(required=true) @Qualifier("employeeServiceImpl")
	private EmployeeService employeeService;
	
	@Autowired(required=true) @Qualifier("evaluateServiceImpl")
	private EvaluateService evaluateService;
	
	@Autowired(required=true) @Qualifier("complainServiceImpl")
	private ComplainService complainService;
	
	@Autowired(required=true) @Qualifier("illegalRecServiceImpl")
	private IllegalRecService illegalRecService;
	
	@Autowired(required=true)  @Qualifier("serviceSubscribeServiceImpl")
	private ServiceSubscribeService serviceSubscribeService;
	
	/** 
	 * Description :  跳转到企业商铺首页
	 * Create Date: 2011-2-23上午09:35:28 by likg  Modified Date: 2011-2-23上午09:35:28 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toOrgShopIndexView")
    public ModelAndView toOrgShopIndexView(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		
		String orgInfoId = request.getParameter("orgInfoId");
		OrgInfo orgInfo = null;
		if(StringUtils.hasLength(orgInfoId)) {
			orgInfo = orgInfoService.get(orgInfoId);
		} else {
			orgInfo = (OrgInfo)AuthenticationHelper.getCurrentUser(true).getOrgInfo();
		}
		paramsMap.put("orgInfoId", orgInfo.getObjId());
		
		//获取供应商信息
		Supplier supplier = supplierService.getSupplierAllInfo(orgInfo.getObjId());
		model.put("supplier", supplier);
		
		//获取机构联系人信息，先判断当前用户是否为商圈会员
    	if(AuthenticationHelper.getCurrentUser() != null  
    			&& AuthenticationHelper.getCurrentUser().getOrgInfo()!= null
    			&& serviceSubscribeService.isShangQuanHuiYuan((AuthenticationHelper.getCurrentUser(true).getOrgInfo()).getObjId())){
    		if(orgInfo.getUser()==null){//处理没有用户的机构
    			Employee emp = new Employee();
    			Company com = orgInfo.getCompany();
    			if(com != null){
    				emp.setName(com.getContact());
    				emp.setEmail(com.getEmail());
    				if(com.getMobilePhone()==null ){
    					emp.setMobile(com.getTel());
    				}
    				else{
    					emp.setMobile(com.getMobilePhone());
    				}    				
    				emp.setFax(com.getFax());
    			}
    			model.put("contact", emp);
    		}
    		else{
    			model.put("contact", orgInfo.getUser().getEmp());
    		}
    	}
		
		//获取供应商报价的商品
    	Page<Goods> page1 = new Page<Goods>(1L, 12L, 12, new ArrayList<Goods>());
    	Page<Goods> pageData1 = goodsPriceSupplierService.getGoodsList(page1, paramsMap);
    	model.put("goodsList", pageData1.getData());
    	
    	//获取供应商报价的商品分类
    	List<GoodsClass> goodsClassList = goodsPriceSupplierService.getGoodsClassList(paramsMap);
    	model.put("goodsClassList", goodsClassList);
    	
    	//获取评价详细信息 
		Page<Evaluate> page2 = new Page<Evaluate>(1L, 4L, 4, new ArrayList<Evaluate>());
		Page<Evaluate> pageData2 = evaluateService.getEvaluateList(page2, paramsMap);
		model.put("evaluateList", pageData2.getData());
		
		if(StringUtils.hasLength(request.getParameter("type")) &&"CGAL".equals(request.getParameter("type"))) {
			model.put("currentTab", "3");//成功案例选项卡
		} else {
			model.put("currentTab", "0");//成功案例选项卡
		}
		
		return new ModelAndView("orgShopIndexView", model);
	}
	
	/** 
	 * Description :  跳转到企业商铺的其他视图页面
	 * Create Date: 2011-2-23下午02:15:42 by likg  Modified Date: 2011-2-23下午02:15:42 by likg
	 * @param   viewName:[orgshopIntroductionView=企业介绍][orgshopCertificateView=企业资质]
	 * [orgshopGoodsInfoView=产品信息][orgshopEvaluationView=评价信息][orgshopCaseView=成功案例][orgshopContactView=联系方式]
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=toOrgShopOtherView")
	public ModelAndView toOrgShopOtherView(HttpServletRequest request, String viewName) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		
		String orgInfoId = request.getParameter("orgInfoId");
		OrgInfo orgInfo = null;
		if(StringUtils.hasLength(orgInfoId)) {
			orgInfo = orgInfoService.get(orgInfoId);
		} else {
			orgInfo = (OrgInfo)AuthenticationHelper.getCurrentUser(true).getOrgInfo();
		}
		
		paramsMap.put("orgInfoId", orgInfo.getObjId());
		
		//获取供应商信息
		if("orgshopIntroductionView".equals(viewName) || "orgshopCertificateView".equals(viewName) || "orgshopCaseView".equals(viewName)) {
			Supplier supplier = supplierService.getSupplierAllInfo(orgInfo.getObjId());
			model.put("supplier", supplier);
		}
		
		//获取供应商报价的商品
		if("orgshopGoodsInfoView".equals(viewName)) {
			paramsMap.put("goodsClassCode", request.getParameter("goodsClassCode"));
			Page<Goods> page1 = prePage(request);
			Page<Goods> pageData1 = goodsPriceSupplierService.getGoodsList(page1, paramsMap);
			endPage(model, pageData1, request);
	    	model.put("PAGERESULT", pageData1);
		}
		
		//获取评价详细信息 
		if("orgshopEvaluationView".equals(viewName)) {
			Page<Evaluate> page2 = prePage(request);
			Page<Evaluate> pageData2 = evaluateService.getEvaluateList(page2, paramsMap);
			endPage(model, pageData2, request);
	    	model.put("PAGERESULT", pageData2);
		}
		
		//获取投诉举报详细信息 
		if("orgshopComplainView".equals(viewName)) {
			paramsMap.put("complainType", request.getParameter("complainType"));
			Page<Complain> page3 = prePage(request);
			page3.setPageSize(Integer.MAX_VALUE);
			Page<Complain> pageData3 = complainService.getComplainList(page3, paramsMap);
			endPage(model, pageData3, request);
	    	model.put("PAGERESULT", pageData3);
	    	
	    	//违法纪录
	    	paramsMap.put("isShow", true);
	    	model.put("illegalRecList", illegalRecService.getIllegalRec(paramsMap));
		}
		
		//获取联系方式详细信息 
		if("orgshopContactView".equals(viewName)) {
			List<Employee> contactList = employeeService.findByHql("from Employee e where e.company.objId = ?", new Object[]{orgInfo.getCompany().getObjId()});
			model.put("contactList", contactList);
			model.put("orgInfo", orgInfo);
		}
		
		return new ModelAndView(viewName, model);
	}
    
}
