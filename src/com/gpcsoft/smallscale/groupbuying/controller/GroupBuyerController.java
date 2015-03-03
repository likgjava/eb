package com.gpcsoft.smallscale.groupbuying.controller;

import java.math.BigDecimal;
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
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.utils.StringUtils;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.smallscale.groupbuying.domain.GroupBuyer;
import com.gpcsoft.smallscale.groupbuying.domain.GroupBuying;
import com.gpcsoft.smallscale.groupbuying.service.GroupBuyerService;
import com.gpcsoft.smallscale.groupbuying.service.GroupBuyingService;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.baseData.domain.District;
import com.gpcsoft.srplatform.baseData.service.DistrictService;

/**
 * @gpcsoft.view value="groupBuyerListView"
 * url="view/smallscale/groupbuying/group_buyer_list.jsp"
 * 
 * @gpcsoft.view value="groupBuyerManageListView"
 * url="view/smallscale/groupbuying/group_buyer_manage_list.jsp"
 * 
 * @gpcsoft.view value="groupBuyerFormView"
 * url="view/smallscale/groupbuying/group_buyer_form.jsp"
 * 
 * @gpcsoft.view value="groupBuyerDetailView"
 * url="view/smallscale/groupbuying/group_buyer_detail.jsp"
 */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={GroupBuyer.class})
@RequestMapping("/GroupBuyerController.do")
public class GroupBuyerController extends AnnotationMultiController<GroupBuyer> {

	@Autowired(required=true) @Qualifier("groupBuyerServiceImpl")
	private GroupBuyerService groupBuyerService;
	
	@Autowired(required=true) @Qualifier("groupBuyingServiceImpl")
	private GroupBuyingService groupBuyingService;
	
	@Autowired(required=true) @Qualifier("districtServiceImpl")
	private DistrictService districtService;
	
	/** 
	 * Description :  跳转到团购采购人列表页面
	 * Create Date: 2011-6-21下午04:41:07 by likg  Modified Date: 2011-6-21下午04:41:07 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toGroupBuyerListView")
	public ModelAndView toGroupBuyerListView(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		String groupBuyingId = request.getParameter("groupBuyingId"); //团购id
		String viewName = null;
		
		if(StringUtils.hasLength(groupBuyingId)) {
			model.put("groupBuyingId", groupBuyingId);
			viewName = "groupBuyerManageListView";
		} else {
			model.put("orgInfoId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId());
			model.put("userId", AuthenticationHelper.getCurrentUser(true).getObjId());
			viewName = "groupBuyerListView";
		}
		
		return new ModelAndView(viewName, model);
	}
	
	/** 
	 * Description :  跳转到新增或修改团购采购人信息页面
	 * Create Date: 2011-6-21上午11:16:04 by likg  Modified Date: 2011-6-21上午11:16:04 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toCreateOrUpdateView")
	public ModelAndView toCreateOrUpdateView(String objId, HttpServletRequest request, SessionStatus status) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		String groupBuyingId = request.getParameter("groupBuyingId"); //团购id
		
		//获取团购信息
		GroupBuying groupBuying = groupBuyingService.get(groupBuyingId);
		model.put("groupBuying", groupBuying);
		
		//获取所有省份数据
		List<District> provinceList = districtService.findTopDistricts();
		model.put("provinceList", provinceList);
		
		//获取团购采购人信息
		GroupBuyer groupBuyer = null;
		if(StringUtils.hasLength(objId)) {
			groupBuyer = groupBuyerService.get(objId);
		} else {
			groupBuyer = new GroupBuyer();
			groupBuyer.setAmount(new BigDecimal(1)); //初始化团购数量
		}
		model.put("groupBuyer", groupBuyer);
		
		//获取当前登录用户的联系信息
		User user = AuthenticationHelper.getCurrentUser();
		if(user != null) {
			if(user.getEmp()!=null && user.getEmp().getCompany()!=null) {
				groupBuyer.setReceiveName(user.getEmp().getName());
				groupBuyer.setMobilePhone(user.getEmp().getCompany().getMobilePhone());
				groupBuyer.setFixedPhone(user.getEmp().getCompany().getTel());
				groupBuyer.setEmail(user.getEmp().getCompany().getEmail());
				groupBuyer.setAddress(user.getEmp().getCompany().getAddress());
				groupBuyer.setPostCode(user.getEmp().getCompany().getPostCode());
			} else if(user.getEmp() != null) {
				groupBuyer.setReceiveName(user.getEmp().getName());
				groupBuyer.setMobilePhone(user.getEmp().getMobile());
				groupBuyer.setFixedPhone(user.getEmp().getTelOffice());
				groupBuyer.setEmail(user.getEmp().getEmail());
				groupBuyer.setAddress(user.getEmp().getAddress());
				groupBuyer.setPostCode(user.getEmp().getZipCode());
			}
			if(user.getOrgInfo() != null) {
				OrgInfo orgInfo = (OrgInfo) user.getOrgInfo();
				groupBuyer.setOrgInfo(orgInfo);
			}
		}
		
		status.setComplete();
		return new ModelAndView("groupBuyerFormView", model);
	}
	
	/** 
	 * Description :  保存团购采购人信息
	 * Create Date: 2011-6-21下午04:41:07 by likg  Modified Date: 2011-6-21下午04:41:07 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveGroupBuyer")
	public ModelAndView saveGroupBuyer(GroupBuyer groupBuyer, HttpServletRequest request, SessionStatus status) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		//保存团购采购人信息
		groupBuyerService.saveGroupBuyer(groupBuyer);
		model.put("objId", groupBuyer.getObjId());
		status.setComplete();
		
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  修改支付状态
	 * Create Date: 2011-6-21下午04:41:07 by likg  Modified Date: 2011-6-21下午04:41:07 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=updatePayStatus")
	public ModelAndView updatePayStatus(String objId, HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		//修改支付状态
		String payStatus = request.getParameter("payStatus");
		groupBuyerService.updatePayStatus(objId, payStatus);
		
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  跳转到团购采购人详情页面
	 * Create Date: 2011-6-21下午04:41:07 by likg  Modified Date: 2011-6-21下午04:41:07 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toGroupBuyerDetailView")
	public ModelAndView toGroupBuyerDetailView(String objId, HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		//获取团购采购人信息
		GroupBuyer groupBuyer = groupBuyerService.get(objId);
		model.put("groupBuyer", groupBuyer);
		
		return new ModelAndView("groupBuyerDetailView", model);
	}
	
}
