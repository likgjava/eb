package com.gpcsoft.agreement.pub.controller;

import java.io.OutputStream;
import java.util.HashMap;
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

import com.gpcsoft.agreement.order.domain.OrderItem;
import com.gpcsoft.agreement.order.service.OrderItemService;
import com.gpcsoft.agreement.pub.domain.AgContract;
import com.gpcsoft.agreement.pub.service.AgContractService;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.utils.JsonUtils;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.pubservice.common.enumeration.CommonEnum;
import com.gpcsoft.srplatform.common.assist.service.FreeMarkerService;

/**
  * @gpcsoft.view value="toContractSupplierListView"
  *  url="view/agreement/contract/contract_supplier_list.jsp"
  *  
  * @gpcsoft.view value="toContractBuyerListView"
  *  url="view/agreement/contract/contract_purchaser_list.jsp"
  *  
  * @gpcsoft.view value="toContractAdminListView"
  *  url="view/agreement/contract/contract_admin_list.jsp"
  *  
  * @gpcsoft.view value="toContractDraftBySupplierView"
  *  url="view/agreement/contract/contract_supplier_draft.jsp"
  *  
  * @gpcsoft.view value="toContractDraftByBuyerView"
  *  url="view/agreement/contract/contract_purchaser_draft.jsp"
  *  
  * @gpcsoft.view value="toBuyerContractDetailView"
  *  url="view/agreement/contract/contract_purchaser_detail.jsp"
  *  
  * @gpcsoft.view value="toSupplierContractDetailView"
  *  url="view/agreement/contract/contract_supplier_detail.jsp"
  *  
  *  @gpcsoft.view value="toAdminContractDetailView"
  *  url="view/agreement/contract/contract_admin_detail.jsp"
  *  
  *  @gpcsoft.view value="orgContractMonitorListView"
  *  url="view/agreement/contract/org_contract_monitor_list.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={AgContract.class})
@RequestMapping("/AgContractController.do")//页面请求路径,可修改
public class AgContractController extends AnnotationMultiController<AgContract> {

	@Autowired(required=true) @Qualifier("agContractServiceImpl")
	private AgContractService agContractService;
	
	@Autowired(required=true)@Qualifier("freeMarkerServiceImpl")
	private FreeMarkerService freeMarkerService;
	
	@Autowired(required=true)@Qualifier("orderItemServiceImpl")
	private OrderItemService orderItemService;

	/**
	 * Description :  保存
	 * Create Date: 2010-4-16上午11:00:14 by sunl  Modified Date: 2010-4-16上午11:00:14 by sunl
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params = "method=saveContract")
	public ModelAndView saveContract(HttpServletRequest request,String contractStr) throws Exception {	
		contractStr = contractStr.replace(",\"maxSize\":\"null\"", "");
		AgContract contract=JsonUtils.json2Bean(contractStr, AgContract.class);
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		agContractService.saveContract(contract);
		
		model.put("contract", contract);//输出绑定的结果
		return new ModelAndView(Constants.JSON_VIEW, model);	
	}
	
	/**
	 * Description :  确认合同
	 * Create Date: 2010-4-16上午11:00:14 by liangxj  Modified Date: 2010-4-16上午11:00:14 by liangxj
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params = "method=auditContract")
	public ModelAndView auditContract(HttpServletRequest request,String contractStr) throws Exception {	
		AgContract contract=JsonUtils.json2Bean(contractStr, AgContract.class);
		
		String sureRole = request.getParameter("sureRole");
		Map<String, Object> model = new HashMap<String, Object>();
		
		agContractService.auditContract(contract,sureRole);
		
		model.put("contract", contract);//输出绑定的结果
		return new ModelAndView(Constants.JSON_VIEW, model);	
	}
	
	/**
	 * Description :  确认合同
	 * Create Date: 2010-4-16上午11:00:14 by liangxj  Modified Date: 2010-4-16上午11:00:14 by liangxj
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params = "method=invalidContract")
	public ModelAndView invalidContract(HttpServletRequest request,String contractStr) throws Exception {	
		AgContract contract=JsonUtils.json2Bean(contractStr, AgContract.class);
		String sureRole = request.getParameter("sureRole");
		Map<String, Object> model = new HashMap<String, Object>();
		
		agContractService.releaseContract(contract,sureRole);
		
		model.put("contract", contract);//输出绑定的结果
		return new ModelAndView(Constants.JSON_VIEW, model);	
	}

	@SuppressWarnings("unchecked")
	@Override // by yucy
	protected QueryObject onFind(QueryObject query, HttpServletRequest request) throws Exception {
		String tabsType = request.getParameter("tabsType");
		String orgType = request.getParameter("orgType");
		
		//供应商待提交
		if(null!=tabsType&&"submit".equals(tabsType)&&"supplier".equals(orgType)){
			query.getQueryParam().and(new QueryParam("createUser.objId",AuthenticationHelper.getCurrentUser().getObjId()));
			query.getQueryParam().and(new QueryParam("useStatus",CommonEnum.USER_STATUS_DRAEF));  //使用状态是00
			query.getQueryParam().and(new QueryParam("buyerConfirmStatus",CommonEnum.CONFIRM_STATUS_WAIT));  //采购人确认状态 00
			query.getQueryParam().and(new QueryParam("supplierConfirmStatus",CommonEnum.CONFIRM_STATUS_WAIT));  //供应商确认状态 00
		}
		//采购人待提交
		else if(null!=tabsType&&"submit".equals(tabsType)&&"buyer".equals(orgType)){
			query.getQueryParam().and(new QueryParam("createUser.objId",AuthenticationHelper.getCurrentUser().getObjId()));
			query.getQueryParam().and(new QueryParam("useStatus",CommonEnum.USER_STATUS_DRAEF));  //使用状态是00
			query.getQueryParam().and(new QueryParam("buyerConfirmStatus",CommonEnum.CONFIRM_STATUS_WAIT));  //采购人确认状态 00
			query.getQueryParam().and(new QueryParam("supplierConfirmStatus",CommonEnum.CONFIRM_STATUS_WAIT));  //供应商确认状态 00
		}
		//管理员待提交
		else if(null!=tabsType&&"submit".equals(tabsType)&&"admin".equals(orgType)){
			query.getQueryParam().and(new QueryParam("useStatus",CommonEnum.USER_STATUS_DRAEF));  //使用状态是00
			query.getQueryParam().and(new QueryParam("buyerConfirmStatus",CommonEnum.CONFIRM_STATUS_WAIT));	//采购人确认状态 00
			query.getQueryParam().and(new QueryParam("supplierConfirmStatus",CommonEnum.CONFIRM_STATUS_WAIT));	//供应商确认状态 00
		}
		//供应商待确认
		else if(null!=tabsType&&"todo".equals(tabsType)&&"supplier".equals(orgType)){
			query.getQueryParam().and(new QueryParam("supplier.objId",AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId()));
			query.getQueryParam().and(new QueryParam("useStatus",CommonEnum.USER_STATUS_DRAEF));  //使用状态是00
			query.getQueryParam().and(new QueryParam("buyerConfirmStatus",CommonEnum.CONFIRM_STATUS_NEGOTIATE));  //采购人确认状态 01
			query.getQueryParam().and(new QueryParam("supplierConfirmStatus",CommonEnum.CONFIRM_STATUS_WAIT));  //供应商确认状态 00
		}
		//采购人待确认
		else if(null!=tabsType&&"todo".equals(tabsType)&&"buyer".equals(orgType)){
			query.getQueryParam().and(new QueryParam("buyer.objId",AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId()));
			query.getQueryParam().and(new QueryParam("useStatus",CommonEnum.USER_STATUS_DRAEF));  //使用状态是00
			query.getQueryParam().and(new QueryParam("buyerConfirmStatus",CommonEnum.CONFIRM_STATUS_WAIT));  //采购人确认状态 00
			query.getQueryParam().and(new QueryParam("supplierConfirmStatus",CommonEnum.CONFIRM_STATUS_NEGOTIATE));  //供应商确认状态 01
		}
		//管理员待确认
		else if(null!=tabsType&&"todo".equals(tabsType)&&"admin".equals(orgType)){
			QueryParam or = new QueryParam();
			query.getQueryParam().and(new QueryParam("useStatus",CommonEnum.USER_STATUS_DRAEF));  //使用状态是00
			or.or(new QueryParam("buyerConfirmStatus",CommonEnum.USER_STATUS_DRAEF));    //采购人确认状态 01
			or.or(new QueryParam("supplierConfirmStatus",CommonEnum.USER_STATUS_DRAEF));  //供应商确认状态 01
			query.getQueryParam().and(or);
		}
		//供应商被退回
		else if(null!=tabsType&&"notpass".equals(tabsType)&&"supplier".equals(orgType)){
			query.getQueryParam().and(new QueryParam("supplierConfirmUser.objId",AuthenticationHelper.getCurrentUser(true).getObjId()));
			query.getQueryParam().and(new QueryParam("useStatus",CommonEnum.USER_STATUS_DRAEF));  //使用状态是00
			query.getQueryParam().and(new QueryParam("buyerConfirmStatus",CommonEnum.CONFIRM_STATUS_CANCEL));  //采购人确认状态 02
			query.getQueryParam().and(new QueryParam("supplierConfirmStatus",CommonEnum.CONFIRM_STATUS_WAIT));  //供应商确认状态 00
		}
		//采购人被退回
		else if(null!=tabsType&&"notpass".equals(tabsType)&&"buyer".equals(orgType)){
			query.getQueryParam().and(new QueryParam("buyerConfirmUser.objId",AuthenticationHelper.getCurrentUser(true).getObjId()));
			query.getQueryParam().and(new QueryParam("useStatus",CommonEnum.USER_STATUS_DRAEF));  //使用状态是00
			query.getQueryParam().and(new QueryParam("buyerConfirmStatus",CommonEnum.CONFIRM_STATUS_WAIT));  //采购人确认状态 00
			query.getQueryParam().and(new QueryParam("supplierConfirmStatus",CommonEnum.CONFIRM_STATUS_CANCEL));  //供应商确认状态 02
		}
		//管理员被退回
		else if(null!=tabsType&&"notpass".equals(tabsType)&&"admin".equals(orgType)){
			query.getQueryParam().and(new QueryParam("useStatus",CommonEnum.USER_STATUS_DRAEF));  //使用状态是00
			QueryParam or = new QueryParam();
			or.or(new QueryParam("buyerConfirmStatus",CommonEnum.CONFIRM_STATUS_CANCEL));	//采购人确认状态 02
			or.or(new QueryParam("supplierConfirmStatus",CommonEnum.CONFIRM_STATUS_CANCEL));   //供应商确认状态 02
			query.getQueryParam().and(or);
		} 
		//供应商已签订
		else if(null!=tabsType&&"done".equals(tabsType)&&"supplier".equals(orgType)){
			query.getQueryParam().and(new QueryParam("supplierConfirmUser.objId",AuthenticationHelper.getCurrentUser(true).getObjId()));
			query.getQueryParam().and(new QueryParam("useStatus",CommonEnum.USER_STATUS_CONFIRM));  //使用状态是01
			query.getQueryParam().and(new QueryParam("buyerConfirmStatus",CommonEnum.CONFIRM_STATUS_NEGOTIATE));  //采购人确认状态 01
			query.getQueryParam().and(new QueryParam("supplierConfirmStatus",CommonEnum.CONFIRM_STATUS_NEGOTIATE));  //供应商确认状态 01
		}
		//采购人已签订
		else if(null!=tabsType&&"done".equals(tabsType)&&"buyer".equals(orgType)){
			query.getQueryParam().and(new QueryParam("buyerConfirmUser.objId",AuthenticationHelper.getCurrentUser(true).getObjId()));
			query.getQueryParam().and(new QueryParam("useStatus",CommonEnum.USER_STATUS_CONFIRM));  //使用状态是01
			query.getQueryParam().and(new QueryParam("buyerConfirmStatus",CommonEnum.CONFIRM_STATUS_NEGOTIATE));  //采购人确认状态 01
			query.getQueryParam().and(new QueryParam("supplierConfirmStatus",CommonEnum.CONFIRM_STATUS_NEGOTIATE));  //供应商确认状态 01
		}
		//管理员已签订
		else if(null!=tabsType&&"done".equals(tabsType)&&"admin".equals(orgType)){
			query.getQueryParam().and(new QueryParam("useStatus",CommonEnum.USER_STATUS_CONFIRM));  //使用状态是01
			query.getQueryParam().and(new QueryParam("buyerConfirmStatus",CommonEnum.CONFIRM_STATUS_NEGOTIATE));		//采购人确认状态 01
			query.getQueryParam().and(new QueryParam("supplierConfirmStatus",CommonEnum.CONFIRM_STATUS_NEGOTIATE));	//供应商确认状态 01
		} 
		//供应商待确认作废
		else if(null!=tabsType&&"tocancel".equals(tabsType)&&"supplier".equals(orgType)){
			query.getQueryParam().and(new QueryParam("supplier.objId",AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId()));
			query.getQueryParam().and(new QueryParam("useStatus",CommonEnum.USER_STATUS_CONFIRM));  //使用状态是01
			query.getQueryParam().and(new QueryParam("buyerConfirmStatus",CommonEnum.CONFIRM_STATUS_NEGOTIATE));  //采购人确认状态 01
			query.getQueryParam().and(new QueryParam("supplierConfirmStatus",CommonEnum.CONFIRM_STATUS_WAIT));  //供应商确认状态 00
		}
		//采购人待确认作废
		else if(null!=tabsType&&"tocancel".equals(tabsType)&&"buyer".equals(orgType)){
			query.getQueryParam().and(new QueryParam("buyer.objId",AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId()));
			query.getQueryParam().and(new QueryParam("useStatus",CommonEnum.USER_STATUS_CONFIRM));  //使用状态是01
			query.getQueryParam().and(new QueryParam("buyerConfirmStatus",CommonEnum.CONFIRM_STATUS_WAIT));  //采购人确认状态 00
			query.getQueryParam().and(new QueryParam("supplierConfirmStatus",CommonEnum.CONFIRM_STATUS_NEGOTIATE));  //供应商确认状态 01
		}
		//管理员待确认作废
		else if(null!=tabsType&&"tocancel".equals(tabsType)&&"admin".equals(orgType)){
			query.getQueryParam().and(new QueryParam("useStatus",CommonEnum.USER_STATUS_CONFIRM));  //使用状态是01
			QueryParam or = new QueryParam();
			or.or(new QueryParam("buyerConfirmStatus",CommonEnum.CONFIRM_STATUS_WAIT));		//采购人确认状态 00
			or.or(new QueryParam("supplierConfirmStatus",CommonEnum.CONFIRM_STATUS_WAIT));  //供应商确认状态 00
			query.getQueryParam().and(or);
		}   
		//供应商已作废
		else if(null!=tabsType&&"cancel".equals(tabsType)&&"supplier".equals(orgType)){
			query.getQueryParam().and(new QueryParam("supplierConfirmUser.objId",AuthenticationHelper.getCurrentUser(true).getObjId()));
			query.getQueryParam().and(new QueryParam("useStatus",CommonEnum.USER_STATUS_INVALID));  //使用状态是02
			query.getQueryParam().and(new QueryParam("buyerConfirmStatus",CommonEnum.CONFIRM_STATUS_NEGOTIATE));  //采购人确认状态 01
			query.getQueryParam().and(new QueryParam("supplierConfirmStatus",CommonEnum.CONFIRM_STATUS_NEGOTIATE));  //供应商确认状态 01
		}
		//采购人已作废
		else if(null!=tabsType&&"cancel".equals(tabsType)&&"buyer".equals(orgType)){
			query.getQueryParam().and(new QueryParam("buyerConfirmUser.objId",AuthenticationHelper.getCurrentUser(true).getObjId()));
			query.getQueryParam().and(new QueryParam("useStatus",CommonEnum.USER_STATUS_INVALID));  //使用状态是02
			query.getQueryParam().and(new QueryParam("buyerConfirmStatus",CommonEnum.CONFIRM_STATUS_NEGOTIATE));  //采购人确认状态 01
			query.getQueryParam().and(new QueryParam("supplierConfirmStatus",CommonEnum.CONFIRM_STATUS_NEGOTIATE));  //供应商确认状态 01
		}
		//管理员已作废
		else if(null!=tabsType&&"cancel".equals(tabsType)&&"admin".equals(orgType)){
			query.getQueryParam().and(new QueryParam("useStatus",CommonEnum.USER_STATUS_INVALID));  //使用状态是02
			query.getQueryParam().and(new QueryParam("buyerConfirmStatus",CommonEnum.CONFIRM_STATUS_NEGOTIATE));	//采购人确认状态 01
			query.getQueryParam().and(new QueryParam("supplierConfirmStatus",CommonEnum.CONFIRM_STATUS_NEGOTIATE));	//供应商确认状态 01
		} 
		return query;
	}

	/** 
	 * Description :跳转供应商合同管理
	 * Create Date: 2010-4-29上午10:12:13 by yucy  Modified Date: 2010-4-29上午10:12:13 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toContractSupplierList")   
	public ModelAndView toContractSupplierList(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   
		return new ModelAndView("toContractSupplierListView", model);
    }
	
	/** 
	 * Description :跳转采购人合同管理
	 * Create Date: 2010-4-29上午10:12:13 by yucy  Modified Date: 2010-4-29上午10:12:13 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toContractBuyerList")   
	public ModelAndView toContractBuyerList(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   
		return new ModelAndView("toContractBuyerListView", model);
    }
	

	/** 
	 * Description :  跳转管理员合同列表
	 * Create Date: 2010-10-22下午01:52:47 by dongcl  Modified Date: 2010-10-22下午01:52:47 by dongcl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toContractAdminList") 	
	public ModelAndView toContractAdminList(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   
		return new ModelAndView("toContractAdminListView", model);
    }
	
	/**
	 * Description :跳转至起草合同页面  
	 * Create Date: 2010-9-29上午10:55:28 by yucy  Modified Date: 2010-9-29上午10:55:28 by yucy
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params = "method=toContractDraft")   
	public ModelAndView toContractDraft(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>(); 
		OrgInfo orgInfo =(OrgInfo) AuthenticationHelper.getCurrentUser(true).getOrgInfo();	
		model.put("orgInfo", orgInfo);
		model.put("cureentUserId", AuthenticationHelper.getCurrentUser(true).getObjId());
		
		ModelAndView view = null;
		if("supplier".equals(request.getParameter("forType"))){
			view = new ModelAndView("toContractDraftBySupplierView",model);
		}else {
			view = new ModelAndView("toContractDraftByBuyerView",model);
		}
		return view;
    }
	
	/**
	 * Description :跳转至查看合同页面  
	 * Create Date: 2010-9-29上午10:55:28 by yucy  Modified Date: 2010-9-29上午10:55:28 by yucy
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params = "method=toContractDetail")   
	public ModelAndView toContractDetail(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>(); 
		model.put("contract",agContractService.get(request.getParameter("objId")));
		model.put("currentTabID",request.getParameter("currentTabID"));
		ModelAndView view;
		if("buyer".equals(request.getParameter("forType"))){
			view = new ModelAndView("toBuyerContractDetailView", model);
		}else if("admin".equals(request.getParameter("forType"))){
			view = new ModelAndView("toAdminContractDetailView", model);
		}else{
			view = new ModelAndView("toSupplierContractDetailView", model);
		}
		return view;
    }	
	
	/** 
	 * Description :  打印合同
	 * Create Date: 2010-12-13下午04:15:34 by liangxj  Modified Date: 2010-12-13下午04:15:34 by liangxj
	 * @param   contractTemp 合同模板 objId 合同id
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params={"method=printContract"})
	public ModelAndView printContract(HttpServletRequest request, String objId,String contractTemp) throws Exception
	{
		Map<String,Object> templateMap = new HashMap<String,Object>();
		//获得合同
	    AgContract contract = (AgContract)this.agContractService.get(objId);
	    templateMap.put("contract", contract);
	    
	    //获得订单明细
	    List<OrderItem> orderItems = orderItemService.getOrderItemByContract(objId);
	    templateMap.put("orderItems", orderItems);
	    
	    Map<String,String> model = new HashMap<String,String>();
	    
	    //加载合同模板
	    String template = "contract.ftl";
	    if(contractTemp != null)
	    	template = contractTemp;
	    
	    model.put("content", this.freeMarkerService.getFreeMarkerContent(template, templateMap));
	    return new ModelAndView("jsonView", model);
	}
	
	/** 
	 * Description :  合同的导出
	 * Create Date: 2010-12-22下午09:19:37 by yucy  Modified Date: 2010-12-22下午09:19:37 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params={"method=expertContract"})
	public void expertContract(HttpServletRequest request,String objId, HttpServletResponse response,String contractTemp) throws Exception{
		Map<String,Object> templateMap = new HashMap<String,Object>();
		//获得合同
	    AgContract contract = (AgContract)this.agContractService.get(objId);
	    templateMap.put("contract", contract);
	    
	    //获得订单明细
	    List<OrderItem> orderItems = orderItemService.getOrderItemByContract(objId);
	    templateMap.put("orderItems", orderItems);
	    
	    //加载合同模板
	    String template = "contract.ftl";
	    if(contractTemp != null){
	    	template = contractTemp;
	    }
		response.setContentType("APPLICATION/OCTET-STREAM");
		response.setHeader("Content-Disposition", "Attachment; filename="+new String(contract.getContractName().getBytes("gbk"), "ISO8859-1" )+".doc");
		OutputStream outputStream = response.getOutputStream();
		outputStream.write((this.freeMarkerService.getFreeMarkerContent(template, templateMap)).getBytes());
		outputStream.flush();
		outputStream.close(); 
	}
	
	/** 
	 * Description :  跳转到上级公司监控下级公司合同列表页面
	 * Create Date: 2011-7-29上午08:45:03 by likg  Modified Date: 2011-7-29上午08:45:03 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toOrgContractMonitorListView")
	public ModelAndView toOrgContractMonitorListView(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		//获取上级公司的信息
		OrgInfo orgInfo = (OrgInfo) AuthenticationHelper.getCurrentUser(true).getOrgInfo();
		model.put("companyId", orgInfo.getCompany().getObjId()); //上级公司的公司id
		model.put("orgInfoId", orgInfo.getObjId()); //上级公司的机构id
		
		return new ModelAndView("orgContractMonitorListView", model);
	}
}
