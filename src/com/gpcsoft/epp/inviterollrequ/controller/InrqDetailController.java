package com.gpcsoft.epp.inviterollrequ.controller;

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

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryObjectBase;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.common.domain.EbuyMethodEnum;
import com.gpcsoft.epp.inviterollrequ.domain.InrqAuditStatusEnum;
import com.gpcsoft.epp.inviterollrequ.domain.InrqDetail;
import com.gpcsoft.epp.inviterollrequ.domain.InrqKindEnum;
import com.gpcsoft.epp.inviterollrequ.domain.Inviterollrequ;
import com.gpcsoft.epp.inviterollrequ.service.InrqDetailService;
import com.gpcsoft.epp.project.domain.ProjectRule;
import com.gpcsoft.epp.project.service.ProjectService;
import com.gpcsoft.epp.signuprecord.domain.SignUprecord;
import com.gpcsoft.epp.signuprecord.domain.SignUprecordEnum;
import com.gpcsoft.epp.signuprecord.service.SignUprecordService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.User;

/**
  * @gpcsoft.view value="inrqDetailFormView"
  *  url="view/es/planform/inviterollrequ/inrqDetailForm.jsp"
  * @gpcsoft.view value="inrqDetailTreeFormView"
  *  url="view/es/planform/inviterollrequ/inrqDetailTreeForm.jsp"
  * @gpcsoft.view value="inrqDetailListView"
  *  url="view/es/planform/inviterollrequ/inrqDetailList.jsp"
  * @gpcsoft.view value="inrqDetailDetailView"
  *  url="view/es/planform/inviterollrequ/inrqDetailDetail.jsp"
  * @gpcsoft.view value="getInrqDetailView"
  * url="view/es/singlesource/singlesourcedoc/inrqDetailView.jsp"
  * @gpcsoft.view value="blank"
  *  url="view/es/common/blank.jsp"
  * @gpcsoft.view value="inrqDetailViewForbuyer"
  *  url="view/es/singlesource/singlesourcedoc/inrqDetailViewForbuyer.jsp"
  *  @gpcsoft.view value="toUpdateInrqDetailView"
  *  url="view/es/singlesource/singlesourcedoc/updateInrqDetailView.jsp"
  *  
  */

@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={InrqDetail.class})
@RequestMapping("/InrqDetailController.do")//页面请求路径,可修改
public class InrqDetailController extends AnnotationMultiController<InrqDetail> {

	@Autowired(required=true) @Qualifier("inrqDetailServiceImpl")
	private InrqDetailService inrqDetailService;

	@Autowired(required=true) @Qualifier("signUprecordServiceImpl")
	private SignUprecordService signUprecordService;
	
	@Autowired(required=true) @Qualifier("projectServiceImpl")
	private ProjectService projectService;
	/**
	 * 
	 * Description :获得单一来源邀请函列表  
	 * Create Date: 2010-8-26下午05:10:21 by liuke  Modified Date: 2010-8-26下午05:10:21 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params="method=searchInrqDetailByQueryObject")
	public ModelAndView searchInrqDetailByQueryObject(HttpServletRequest request,SessionStatus status)throws Exception {	
		logger.debug("\n InrqDetailServiceImpl||searchInrqDetailByQueryObject\n");
		User user = AuthenticationHelper.getCurrentUser();
		OrgInfo orgInfo = (OrgInfo)user.getOrgInfo();
		String projName = request.getParameter("projName");
		String projCode = request.getParameter("projCode");
		Page page = prePage(request);
		QueryObject<InrqDetail> queryObject = new QueryObjectBase<InrqDetail>();
		if(orgInfo.getSupplierId()!=null){//供应商
			queryObject.getQueryParam().and(new QueryParam("supplierId",QueryParam.OPERATOR_EQ,orgInfo.getObjId()));
			queryObject.getQueryParam().and(new QueryParam("inrqDKind",QueryParam.OPERATOR_EQ,InrqKindEnum.NORMAL));
			queryObject.getQueryParam().and(new QueryParam("ebuyMethodType",QueryParam.OPERATOR_EQ,EbuyMethodEnum.SINGLE_SOURCE));
			queryObject.getQueryParam().and(new QueryParam("auditStatus",QueryParam.OPERATOR_EQ,InrqAuditStatusEnum.AUDIT_PASS));
			queryObject.getQueryParam().and(new QueryParam("projName",QueryParam.OPERATOR_EQ,projName));
			queryObject.getQueryParam().and(new QueryParam("projCode",QueryParam.OPERATOR_EQ,projCode));
		}
		Page<Inviterollrequ> pageData = inrqDetailService.getInrqDetailByQueryObject(queryObject, page);
		Map model = new HashMap();
		endPage(model, pageData, request);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  获得邀请函列表[可区分采购方式]
	 * Create Date: 2011-8-17上午11:17:55 by liuy  Modified Date: 2011-8-17上午11:17:55 by liuy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params="method=searchInvrqteByQueryObject")
	public ModelAndView searchInvrqteByQueryObject(HttpServletRequest request,SessionStatus status)throws Exception {	
		logger.debug("\n InrqDetailServiceImpl||searchInviteByQueryObject\n");
		User user = AuthenticationHelper.getCurrentUser();
		OrgInfo orgInfo = (OrgInfo)user.getOrgInfo();
		String projName = request.getParameter("projName");
		String projCode = request.getParameter("projCode");
		String ebuyMethod = request.getParameter("ebuyMethod");
		Page page = prePage(request);
		QueryObject<Inviterollrequ> queryObject = new QueryObjectBase<Inviterollrequ>();
		if(orgInfo.getSupplierId()!=null){//供应商 
			queryObject.getQueryParam().and(new QueryParam("supplierId",QueryParam.OPERATOR_EQ,orgInfo.getObjId()));
			queryObject.getQueryParam().and(new QueryParam("inrqDKind",QueryParam.OPERATOR_EQ,InrqKindEnum.NORMAL));
			String ebuyMethodStr = EbuyMethodEnum.getEBuyMethodValue(ebuyMethod);
			queryObject.getQueryParam().and(new QueryParam("ebuyMethodType",QueryParam.OPERATOR_EQ,ebuyMethodStr));
			queryObject.getQueryParam().and(new QueryParam("auditStatus",QueryParam.OPERATOR_EQ,InrqAuditStatusEnum.AUDIT_PASS));
			queryObject.getQueryParam().and(new QueryParam("projName",QueryParam.OPERATOR_EQ,projName));
			queryObject.getQueryParam().and(new QueryParam("projCode",QueryParam.OPERATOR_EQ,projCode));
		}
		Page<Inviterollrequ> pageData = inrqDetailService.getInrqDetailByQueryObject(queryObject, page);
		Map model = new HashMap();
		endPage(model, pageData, request);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  获得邀请招标邀请函列表
	 * Create Date: 2010-8-27上午11:17:55 by yangx  Modified Date: 2010-8-27上午11:17:55 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params="method=searchInviteByQueryObject")
	public ModelAndView searchInviteByQueryObject(HttpServletRequest request,SessionStatus status)throws Exception {	
		logger.debug("\n InrqDetailServiceImpl||searchInviteByQueryObject\n");
		User user = AuthenticationHelper.getCurrentUser();
		OrgInfo orgInfo = (OrgInfo)user.getOrgInfo();
		String projName = request.getParameter("projName");
		String projCode = request.getParameter("projCode");
		Page page = prePage(request);
		QueryObject<Inviterollrequ> queryObject = new QueryObjectBase<Inviterollrequ>();
		if(orgInfo.getSupplierId()!=null){//供应商 
			queryObject.getQueryParam().and(new QueryParam("supplierId",QueryParam.OPERATOR_EQ,orgInfo.getObjId()));
			queryObject.getQueryParam().and(new QueryParam("inrqDKind",QueryParam.OPERATOR_EQ,InrqKindEnum.NORMAL));
			queryObject.getQueryParam().and(new QueryParam("ebuyMethodType",QueryParam.OPERATOR_EQ,EbuyMethodEnum.INVITE_BIDDING));
			queryObject.getQueryParam().and(new QueryParam("auditStatus",QueryParam.OPERATOR_EQ,InrqAuditStatusEnum.AUDIT_PASS));
			queryObject.getQueryParam().and(new QueryParam("projName",QueryParam.OPERATOR_EQ,projName));
			queryObject.getQueryParam().and(new QueryParam("projCode",QueryParam.OPERATOR_EQ,projCode));
		}
		Page<Inviterollrequ> pageData = inrqDetailService.getInrqDetailByQueryObject(queryObject, page);
		Map model = new HashMap();
		endPage(model, pageData, request);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}

	/** 
	 * Description :  查看邀请函[采购办]
	 * Create Date: 2010-8-28下午03:15:07 by yangx  Modified Date: 2010-8-28下午03:15:07 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=getInrqDetailView")
	public ModelAndView getInrqDetailView(HttpServletRequest request,SessionStatus status)throws Exception {	
		logger.debug("\n InrqDetailServiceImpl||getInrqDetailView\n");
		String objId = request.getParameter("objId");
		InrqDetail inrqDetail = inrqDetailService.getInrqDetailByObjId(objId);
		Map<String ,Object> model = new HashMap<String ,Object>();
		model.put("fromType", "look");
		model.put("inrqDetail", inrqDetail);
		return new ModelAndView("getInrqDetailView", model);
	}

	/** 
	 * Description :  供应商：根据项目Id、登陆人Id跳转到邀请函查看页
	 * Create Date: 2010-9-9上午09:14:53 by yangx  Modified Date: 2010-9-9上午09:14:53 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=toViewInrqDetailForSupplier")
	public ModelAndView toViewInrqDetailForSupplier(HttpServletRequest request) throws Exception {
		logger.debug("\n InrqDetailServiceImpl||toViewInrqDetailForSupplier\n");
		String projectId = request.getParameter("projectId");
		String fromProj = request.getParameter("fromProj");
		Map<String ,Object> model = new HashMap<String ,Object>();
		InrqDetail inrqDetail = inrqDetailService.getInrqDetailContentByProjectId(projectId);
		String returnUrl = "getInrqDetailView";
		if (inrqDetail==null) {
			returnUrl = "blank";
		}else {
		SignUprecord signUprecord = signUprecordService.getSignUprecordBySupplierId(inrqDetail.getInviterollrequ().getProject().getObjId(), AuthenticationHelper.getCurrentUser());
		if (signUprecord!=null) {//判断是否已经报名
			model.put("signUprecordF","yes");
			model.put("signUprecord",signUprecord);
		}else {
			model.put("signUprecordF","no");
		}
		ProjectRule projectRule = projectService.getProjectRuleByProjectId(projectId);
		Date nowDate = new Date();
		if (projectRule.getSignUpSTime()!=null&&projectRule.getSignUpETime()!=null&&nowDate.after(projectRule.getSignUpSTime())&&nowDate.before(projectRule.getSignUpETime())){
			model.put("isApply",SignUprecordEnum.CAN_SIGNUP_RECORD);//可以报名标志
		}else{
			model.put("isApply",SignUprecordEnum.CAN_NOT_SIGNUP_RECORD);//不可以报名标志
		}
		
		model.put("inrqDetail", inrqDetail);
		model.put("fromProj",fromProj);
		}
		return new ModelAndView(returnUrl, model);
	}
	
	/** 
	 * Description :  采购人：根据项目Id、登陆人Id跳转到邀请函查看页
	 * Create Date: 2010-9-9上午09:14:53 by yangx  Modified Date: 2010-9-9上午09:14:53 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=toViewInrqDetailForBuyer")
	public ModelAndView toViewInrqDetailForBuyer(HttpServletRequest request) throws Exception {
		logger.debug("\n InrqDetailServiceImpl||toViewInrqDetailForBuyer\n");
		String projectId = request.getParameter("projectId");
		Map<String ,Object> model = new HashMap<String ,Object>();
		InrqDetail inrqDetail = inrqDetailService.getInrqDetailContentByProjectId(projectId);
		String returnUrl = "inrqDetailViewForbuyer";
		if (inrqDetail==null) {
			returnUrl = "blank";
		}
		return new ModelAndView(returnUrl, model);
	}
	/**
	 * Description :  到达更新邀请函对象页
	 * Create Date: 2010-10-15下午03:08:30 by shenjianzhuang  Modified Date: 2010-10-15下午03:08:30 by shenjianzhuang
	 * @param request
	 * @param status
	 * @return
	 * @throws Exception
	 * @return  ModelAndView
	 * @Exception	 		
	 */		
	@RequestMapping(params="method=toUpdateInrqDetailView")
	public ModelAndView toUpdateInrqDetailView(HttpServletRequest request,SessionStatus status)throws Exception {	
		logger.debug("\n InrqDetailServiceImpl||toUpdateInrqDetailView\n");
		String objId = request.getParameter("objId");
		InrqDetail inrqDetail = inrqDetailService.getInrqDetailByObjId(objId);
		Map<String ,Object> model = new HashMap<String ,Object>();
		model.put("fromType", "look");
		model.put("inrqDetail", inrqDetail);
		return new ModelAndView("inrqDetailViewForbuyer", model);
	}
	
	/**
	 * Description :  查看或更新邀请函对象
	 * Create Date: 2010-10-15下午03:08:30 by shenjianzhuang  Modified Date: 2010-10-15下午03:08:30 by shenjianzhuang
	 * @param request
	 * @param status
	 * @return
	 * @throws Exception
	 * @return  ModelAndView
	 * @Exception	 		
	 */		
	@RequestMapping(params="method=updateInrqDetailView")
	public ModelAndView updateInrqDetailView(HttpServletRequest request,SessionStatus status,InrqDetail inrqDetail)throws Exception {	
		logger.debug("\n InrqDetailServiceImpl||updateInrqDetailView\n");
		request.setCharacterEncoding("utf-8");
		String objId = request.getParameter("objId");
		inrqDetailService.updateInrqDetail(objId,inrqDetail.getContents());
		status.setComplete();
		Map<String ,Object> model = new HashMap<String ,Object>();
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
}
