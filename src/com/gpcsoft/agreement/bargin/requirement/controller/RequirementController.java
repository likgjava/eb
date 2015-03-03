package com.gpcsoft.agreement.bargin.requirement.controller;

import java.util.Date;
import java.util.HashMap;
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

import com.gpcsoft.agreement.bargin.requirement.domain.Requirement;
import com.gpcsoft.agreement.bargin.requirement.service.RequirementService;
import com.gpcsoft.bizplatform.base.common.util.StringUtils;
import com.gpcsoft.bizplatform.base.purcategory.service.PurCategoryService;
import com.gpcsoft.bizplatform.organization.service.OrgInfoService;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.User;

/**
 * @gpcsoft.view value="toChooseCategoryView"
 *               url="view/agreement/purchaserequire/choose_category.jsp"
 * 
 * @gpcsoft.view value="toRequirementFormView"
 *               url="view/agreement/purchaserequire/requirement_form.jsp"
 * 
 * @gpcsoft.view value="toSaveSuccessView"
 *               url="view/agreement/purchaserequire/requirement_submit_result.jsp"
 * 
 * @gpcsoft.view value="toRequirementManagerListView"
 *               url="view/agreement/purchaserequire/requirement_list_manager.jsp"
 * 
 * @gpcsoft.view value="toRequirementPurchaserListView"
 *               url="view/agreement/purchaserequire/requirement_list_purchaser.jsp"
 * 
 * @gpcsoft.view value="toRequirementAuditView"
 *               url="view/agreement/purchaserequire/requirement_audit_form.jsp"
 * 
 * @gpcsoft.view value="toRequirementDetailView"
 *               url="view/agreement/purchaserequire/requirement_detail.jsp"
 * 
 * @gpcsoft.view value="toUpdateRequirementView"
 *               url="view/agreement/purchaserequire/requirement_form_manager.jsp"
 * 
 * @gpcsoft.view value="toUpdateRequirementPurchaserView"
 *               url="view/agreement/purchaserequire/requirement_form_purchaser.jsp"
 */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={Requirement.class})
@RequestMapping("/RequirementController.do")//页面请求路径,可修改
public class RequirementController extends AnnotationMultiController<Requirement> {

	@Autowired(required=true) @Qualifier("requirementServiceImpl")
	private RequirementService requirementService;
	
	@Autowired(required = true) @Qualifier("purCategoryServiceImpl")
	private PurCategoryService purCategoryService;
	
	@Autowired(required = true) @Qualifier("orgInfoServiceImpl")
	private OrgInfoService orgInfoService;
	
	
	/** 
	 * Description :  跳转到选择品目页面
	 * Create Date: 2011-3-21上午10:20:47 by yucy  Modified Date: 2011-3-21上午10:20:47 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toChooseCategory")   
	public ModelAndView toChooseCategory(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();  
		Map<String, Object> param = new HashMap<String, Object>();  
		
		//返回时带值
    	model.put("categoryvalues",request.getParameter("categoryvalues"));
    	model.put("categoryNames",StringUtils.ascii2Native(request.getParameter("categoryNames")));
		
    	param.put("level", "second");
		model.put("purCategoryList", purCategoryService.getCategorysByLevel(param));

		return new ModelAndView("toChooseCategoryView", model);
	}
	
	/** 
	 * Description :  跳转到采购需求页面
	 * Create Date: 2011-3-21上午10:20:47 by yucy  Modified Date: 2011-3-21上午10:20:47 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toRequirementForm")   
	public ModelAndView toRequirementForm(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();  
    	model.put("categoryvalues",request.getParameter("categoryvalues"));
    	model.put("categoryNames",StringUtils.ascii2Native(request.getParameter("categoryNames")));
    	Requirement requirement = new Requirement();
    	requirement.setPubOrg( orgInfoService.getLastedOrgInfo(AuthenticationHelper.getCurrentUser(true).getEmp().getCompany().getObjId(), true));
    	
    	User currentUser = AuthenticationHelper.getCurrentUser(true);//联系方式带出
    	requirement.setEmail(currentUser.getEmp().getEmail());
    	requirement.setLinkMen(currentUser.getEmp().getName());
    	requirement.setLinkTel(currentUser.getEmp().getMobile());
    	
    	model.put("requirement",requirement);
		return new ModelAndView("toRequirementFormView", model);
	}
	
	/** 
	 * Description :  保存采购需求
	 * Create Date: 2011-3-23下午04:35:32 by yucy  Modified Date: 2011-3-23下午04:35:32 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveRequirement")   
	public ModelAndView saveRequirement(HttpServletRequest request ,Requirement requirement ,SessionStatus status) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();  
		
		//如果是新增则初始化发布时间
		if(!StringUtils.hasLength(requirement.getObjId())){
			requirement.setPubTime(new Date());
		}
		
		requirementService.saveRequirement(requirement , "save" );
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  跳转到保存成功页面
	 * Create Date: 2011-3-24上午11:50:56 by yucy  Modified Date: 2011-3-24上午11:50:56 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toSaveSuccess")   
	public ModelAndView toSaveSuccess(HttpServletRequest request ) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();  
		return new ModelAndView("toSaveSuccessView", model);
	}
	
	/** 
	 * Description :  跳转到采购需求管理列表页面
	 * Create Date: 2011-3-24下午03:15:14 by yucy  Modified Date: 2011-3-24下午03:15:14 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toRequirementManagerList")   
	public ModelAndView toRequirementManagerList(HttpServletRequest request ) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();  
		return new ModelAndView("toRequirementManagerListView", model);
	}

	/** 
	 * Description :  跳转到采购需求管理列表页面
	 * Create Date: 2011-3-24下午03:15:14 by yucy  Modified Date: 2011-3-24下午03:15:14 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toRequirementPurchaserList")   
	public ModelAndView toRequirementPurchaserList(HttpServletRequest request ) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();  
		return new ModelAndView("toRequirementPurchaserListView", model);
	}
	
	/** 
	 * Description :  跳转到修改采购需求
	 * Create Date: 2011-3-25上午09:25:56 by yucy  Modified Date: 2011-3-25上午09:25:56 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toCreateOrUpdateRequirement")   
	public ModelAndView toCreateOrUpdateRequirement(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();  
		String objId = request.getParameter("objId");
		if(StringUtils.hasLength(objId)){
			model.put("requirement", requirementService.get(objId));
		}else{
	    	Requirement requirement = new Requirement();
	    	
	    	requirement.setPubOrg(orgInfoService.getLastedOrgInfo(AuthenticationHelper.getCurrentUser(true).getEmp().getCompany().getObjId(), true));
	    	
	    	User currentUser = AuthenticationHelper.getCurrentUser(true);//联系方式带出
	    	requirement.setEmail(currentUser.getEmp().getEmail());
	    	requirement.setLinkMen(currentUser.getEmp().getName());
	    	requirement.setLinkTel(currentUser.getEmp().getMobile());
			model.put("requirement", requirement);
		}
		return new ModelAndView("toUpdateRequirementView", model);
	}
	
	/** 
	 * Description :  跳转到修改采购需求(采购人)
	 * Create Date: 2011-3-25上午09:25:56 by yucy  Modified Date: 2011-3-25上午09:25:56 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toCreateOrUpdateRequirementPurchaser")   
	public ModelAndView toCreateOrUpdateRequirementPurchaser(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();  
		String objId = request.getParameter("objId");
		if(StringUtils.hasLength(objId)){
			model.put("requirement", requirementService.get(objId));
		}else{
			model.put("requirement", new Requirement());
		}
		return new ModelAndView("toUpdateRequirementPurchaserView", model);
	}
	
	/** 
	 * Description :  跳转到采购需求审核页面(管理员)
	 * Create Date: 2011-3-25上午01:07:58 by yucy  Modified Date: 2011-3-25上午01:07:58 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toRequirementAudit")   
	public ModelAndView toRequirementAudit(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();  
		String objId = request.getParameter("objId");
		if(StringUtils.hasLength(objId)){
			model.put("requirement", requirementService.get(objId));
		}
		return new ModelAndView("toRequirementAuditView", model);
	}
	
	
	/** 
	 * Description :  更新采购需求的状态
	 * Create Date: 2011-3-11下午05:05:28 by yucy  Modified Date: 2011-3-11下午05:05:28 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=updateStatus")
	public ModelAndView updateStatus(HttpServletRequest request ,String objIds) throws Exception{
		Map<String, Object> model=new HashMap<String, Object>();
		Map<String, Object> param=new HashMap<String, Object>();
		if(StringUtils.hasLength(objIds)){
			param.put("auditStatus", request.getParameter("auditStatus"));
			param.put("pubStatus", request.getParameter("pubStatus"));
			requirementService.updateStatus(objIds, param);
		}
		return new ModelAndView(com.gpcsoft.core.Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  跳转到采购需求详情页面
	 * Create Date: 2011-3-25上午01:09:03 by yucy  Modified Date: 2011-3-25上午01:09:03 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toRequirementDetail")   
	public ModelAndView toRequirementDetail(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();  
		String objId = request.getParameter("objId");
		if(StringUtils.hasLength(objId)){
			model.put("requirement", requirementService.get(objId));
		}
		return new ModelAndView("toRequirementDetailView", model);
	}
	
	/** 
	 * Description :  将项目采购公告通过邮件分享给好友
	 * Create Date: 2011-2-28上午11:26:17 by likg  Modified Date: 2011-2-28上午11:26:17 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=shareRequirement")
	public ModelAndView shareRequirement(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		
		param.put("requirementId", request.getParameter("requirementId"));
		param.put("toEmail", request.getParameter("toEmail"));
		
		requirementService.shareRequirement(param);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected QueryObject onFind(QueryObject query, HttpServletRequest request)throws Exception {
		if("purchaser".equals(request.getParameter("userType"))){
			query.getQueryParam().and(new QueryParam("pubOrg.objId",AuthenticationHelper.getCurrentUser().getOrgInfo().getObjId()));
		}
		return query;
	}
}
