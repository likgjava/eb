package com.gpcsoft.smallscale.point.controller;

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

import com.gpcsoft.core.Constants;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.smallscale.point.domain.Promoter;
import com.gpcsoft.smallscale.point.service.PromoterService;
import com.gpcsoft.srplatform.auth.domain.User;

/**
 * @gpcsoft.view value="toPromoterUserListView"
 *               url="view/smallscale/promoter/promoter_user_list.jsp"
 * 
 * @gpcsoft.view value="toPromoterAdminListView"
 *               url="view/smallscale/promoter/promoter_admin_list.jsp"
 * 
 * @gpcsoft.view value="toSendMailPageView"
 *               url="view/smallscale/promoter/mail_send_form.jsp"
 * 
 * @gpcsoft.view value="promoterFormView"
 *               url="view/smallscale/promoter/promoter_form.jsp"
 * 
 * @gpcsoft.view value="toPromoterDealView"
 *               url="view/smallscale/promoter/promoter_view.jsp"
 * 
 * @gpcsoft.view value="toPromoterBuyerFormView"
 *               url="view/smallscale/promoter/promoter_buyer_form.jsp"
 * 
 * @gpcsoft.view value="toPromoterBuyerView"
 *               url="view/smallscale/promoter/promoter_buyer_view.jsp"              
 *               
 * @gpcsoft.view value="toApplyPromoterView"
 *               url="view/smallscale/promoter/promoter_apply.jsp"
 *               
 * @gpcsoft.view value="toAdminFormView"
 *               url="view/smallscale/promoter/promoter_admin_form.jsp"
 *               
 * @gpcsoft.view value="toPromoterAdminDealView"
 *               url="view/smallscale/promoter/promoter_admin_view.jsp"
 *               
 * @gpcsoft.view value="toPromoterDoingPageView"
 *               url="view/smallscale/promoter/promoter_doing_form.jsp"
 */
@Controller
@Scope("request")
@SessionAttributes(types = { Promoter.class })
@RequestMapping("/PromoterController.do")
public class PromoterController extends AnnotationMultiController<Promoter> {

	@Autowired(required = true) @Qualifier("promoterServiceImpl")
	private PromoterService promoterService;

	@RequestMapping(params = { "method=listbyUser" })
	public ModelAndView listbyUser(HttpServletRequest request,String fromWeb) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("fromWeb", fromWeb);
		return new ModelAndView("toPromoterUserListView", model);
	}

	@RequestMapping(params = { "method=listbyAdmin" })
	public ModelAndView listbyAdmin(HttpServletRequest request)
			throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		return new ModelAndView("toPromoterAdminListView", model);
	}

	@RequestMapping(params = { "method=toDealView" })
	public ModelAndView toDealView(HttpServletRequest request) throws Exception {
		Promoter promoter = promoterService.get(request.getParameter("objId"));
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("promoter",promoter);
		return new ModelAndView("toPromoterDealView", model);
	}
	
	@RequestMapping(params = { "method=toAdminDealView" })
	public ModelAndView toAdminDealView(HttpServletRequest request) throws Exception {
		Promoter promoter = promoterService.get(request.getParameter("objId"));
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("promoter",promoter);
		return new ModelAndView("toPromoterAdminDealView", model);
	}

	/**
	 * Description : 保存推广信息 Create Date: 2010-11-16上午09:01:18 by zhaojf Modified
	 * Date: 2010-11-16上午09:01:18 by zhaojf
	 * 
	 * @param
	 * @return
	 * @Exception
	 */
	@RequestMapping(params = { "method=promoterSave" })
	public ModelAndView promoterSave(Promoter promoter,
			HttpServletRequest request, SessionStatus status) throws Exception {
		String returnMessage = "";
		try {
			promoterService.saveByType(promoter);// 保存
			returnMessage = promoter.getValidationCode();
		} catch (Exception ce) {
			returnMessage = "faile";
		}

		status.setComplete();

		Map<String, Object> model = new HashMap<String, Object>();
		model.put(Constants.JSON_RESULT, returnMessage);

		return new ModelAndView(Constants.JSON_VIEW, model);
	}

	/**
	 * Description : 验证码确认推广信息 Create Date: 2010-11-16上午09:00:55 by zhaojf
	 * Modified Date: 2010-11-16上午09:00:55 by zhaojf
	 * 
	 * @param
	 * @return
	 * @Exception
	 */
	@RequestMapping(params = { "method=promoterDealSave" })
	public ModelAndView promoterDealSave(Promoter promoter,
			HttpServletRequest request, SessionStatus status, String dealStatus)
			throws Exception {
		boolean isCanDeal = promoterService.saveDeal(promoter, dealStatus);
		String returnMessage = "";
		if (isCanDeal) {// 处理成功
			returnMessage = "success";
		} else {// 验证码不对
			returnMessage = "faile";
		}

		status.setComplete();

		Map<String, Object> model = new HashMap<String, Object>();
		model.put(Constants.JSON_RESULT, returnMessage);

		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/**
	 * Description : 跳转到推广页面
	 * 
	 * @param
	 * @return
	 * @Exception
	 */
	@RequestMapping(params = "method=toPromoterDoingPage")
	public ModelAndView toPromoterDoingPage(HttpServletRequest request,String fromWeb)
			throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("fromWeb", fromWeb);
		return new ModelAndView("toPromoterDoingPageView", model);

	}
	
	/**
	 * Description : 推广提交
	 * 
	 * @param
	 * @return
	 * @Exception
	 */
	@RequestMapping(params = "method=savePromoter")
	public ModelAndView savePromoter(Promoter promoter,
			HttpServletRequest request, SessionStatus status) throws Exception {
		boolean isNew = promoterService.savePromoter(promoter);
		status.setComplete();
		Map<String, Object> model = new HashMap<String, Object>();	
		model.put("isNew", isNew);
		return new ModelAndView("jsonView", model);
	}

	/**
	 * Description : 跳转到推广人邮件发送页面
	 * 
	 * @param
	 * @return
	 * @Exception
	 */
	@RequestMapping(params = "method=toSendMailPage")
	public ModelAndView toSendMailPage(HttpServletRequest request)
			throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		return new ModelAndView("toSendMailPageView", model);

	}

	/**
	 * Description : 推广人发送邮件
	 * 
	 * @param
	 * @return
	 * @Exception
	 */
	@RequestMapping(params = "method=sendMail")
	public ModelAndView sendMail(String mailadds, String objIds,
			String receiverName, String title, String content,
			HttpServletRequest request) throws Exception {
		promoterService
				.sendMail(mailadds, objIds, receiverName, title, content);
		Map<String, Object> model = new HashMap<String, Object>();
		return new ModelAndView("jsonView", model);
	}

	@SuppressWarnings("unchecked")
	@Override
	public QueryObject onFind(QueryObject query, HttpServletRequest request)
			throws Exception {
		User user = AuthenticationHelper.getCurrentUser(true);
		QueryParam qp = query.getQueryParam() == null ? new QueryParam()
				: query.getQueryParam();
		String userType = request.getParameter("userType");
		if (null != userType && userType.equals("user")) {// 普通用户
			//qp.and(new QueryParam("promoter.objId", "=", user.getObjId()));
			qp.and(new QueryParam("promoterCID", "=", user.getEmp().getIdCard()));
		}

		query.setQueryParam(qp);
		
		/*增加排序字段*/
		query.getQueryProjections().setOrderProperty("createTime");
		query.getQueryProjections().setDescFlag(true); //按时间倒序

		return query;
	}

	public PromoterService getPromoterService() {
		return promoterService;
	}
	
	/** 
	 * Description :  管理员录入推进记录页面
	 * Create Date: 2010-11-19上午11:34:19 by dongcl  Modified Date: 2010-11-19上午11:34:19 by dongcl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toAdminRecord")
	public ModelAndView toAdminRecord(HttpServletRequest request){
		
		Map<String, Object> model = new HashMap<String, Object>();
	   
		return new ModelAndView("toAdminFormView", model);
		
	}

	/** 
	 * Description :  跳转到采购人 登记/确认/查看 其推广人页面
	 * Create Date: 2011-10-17下午03:03:28 by zhaojf  Modified Date: 2011-10-17下午03:03:28 by zhaojf
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toPromoterBuyerRecord")
	public ModelAndView toPromoterBuyerRecord(SessionStatus status) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		String viewStr = "toPromoterBuyerFormView"; //跳转到登记页面

		//根据当前用户的机构名称获取其推广人信息
		User user = AuthenticationHelper.getCurrentUser(true); //获取当前用户
		Promoter promoter = promoterService.getByOrgName(user.getEmp().getCompany().getName());

		//如果已有推广人信息,则跳转确认查看页面
		if(promoter != null) {
			model.put("currentUserId", user.getObjId());
			model.put("promoter", promoter);
			viewStr = "toPromoterBuyerView";
		}

		status.setComplete();
		return new ModelAndView(viewStr, model);
	}

	@Override
	protected Map<String, String> getEnumColumns() {
		Map<String, String> enumMap = new HashMap<String, String>();
		enumMap.put("recordType", "promoter.recordType");// 第一个参数是属性，第二参数是枚举类中定义名
		enumMap.put("dealStatus", "promoter.dealStatus");

		return enumMap;
	}
	
	/**
	 * 跳转到申请推广员页面
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "method=toApplyPromoterView")
	public ModelAndView toApplyPromoterView(HttpServletRequest request){		
		User user = AuthenticationHelper.getCurrentUser(true);  	     
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("user", user);		
		return new ModelAndView("toApplyPromoterView",model);	
	}
	
	 /** 
	 * Description : 为用户添加推广人角色
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toRegistrationPromoter")
	public ModelAndView toRegistrationPromoter(HttpServletRequest request,String idCard,SessionStatus status) throws Exception {
		status.setComplete();
		
		User user = AuthenticationHelper.getCurrentUser(true);
		
		user.getEmp().setIdCard(idCard);
	    promoterService.saveUser(user);//保存用户
	    
	    status.setComplete();  
	     
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("user", user);
		model.put("isPromoter", "yes");
	    return new ModelAndView(Constants.JSON_VIEW, model);	
		
	}

}
