package com.gpcsoft.bizplatform.organization.controller;

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
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.bizplatform.base.common.util.StringUtils;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.organization.domain.SuccessCase;
import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.bizplatform.organization.service.SuccessCaseService;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.Role;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.auth.manager.RoleManager;

/**
  * @gpcsoft.view value="successCaseFormView"
  *  url="view/bizplatform/organization/successcase/successcase_form.jsp"
  * @gpcsoft.view value="successCaseListView"
  *  url="view/bizplatform/organization/successcase/successcase_list.jsp"
  * @gpcsoft.view value="successCaseDetailView"
  *  url="view/bizplatform/organization/successcase/successcase_detail.jsp"
  * @gpcsoft.view value="toAuditFormView"
  *  url="view/bizplatform/organization/successcase/successcase_audit_form.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={SuccessCase.class})
@RequestMapping("/SuccessCaseController.do")//页面请求路径,可修改
public class SuccessCaseController extends AnnotationMultiController<SuccessCase> {

	@Autowired(required=true) @Qualifier("successCaseServiceImpl")
	private SuccessCaseService successCaseService;
	
	@Autowired(required=true) @Qualifier("roleManagerImpl")
	private RoleManager roleManagerImpl;
	
	/** 
	 * Description : 案例审核页面，加载案例信息
	 * Create Date: 2010-7-30上午10:45:50 by sunl  Modified Date: 2010-7-30上午10:45:50 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toAudit")
    public ModelAndView toAudit(HttpServletRequest request,String objId,String type) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		SuccessCase successCase = successCaseService.get(objId);
		model.put("successCase", successCase);
		if(type != null && type.equals("view")){
			return new ModelAndView("successCaseDetailView",model);
		}
		return new ModelAndView("toAuditFormView",model);
	}
	
	/** 
	 * Description : 案例审核,如果通过，useStatus=01(正式)
	 * Create Date: 2010-7-30上午10:45:50 by sunl  Modified Date: 2010-7-30上午10:45:50 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=updateSuccessCaseInfo")
    public ModelAndView updateSuccessCaseInfo(HttpServletRequest request, SuccessCase successCase) throws Exception {
		successCase.setVerifyTime(new Date());
		successCase.setVerifyUser(AuthenticationHelper.getCurrentUser(true));

		//审核通过
		if(StringUtils.hasLength(successCase.getAuditStatus()) && successCase.getAuditStatus().equals(OrganizationEnum.PASS_EXAM)){
			successCase.setAuditStatus(OrganizationEnum.PASS_EXAM);//通过
			successCase.setUseStatus(OrganizationEnum.USE_VALID);//正式
			successCaseService.save(successCase);
		}
		//审核不通过
		else if(StringUtils.hasLength(successCase.getAuditStatus()) && successCase.getAuditStatus().equals(OrganizationEnum.NO_PASS_EXAM)){
			successCase.setAuditStatus(OrganizationEnum.NO_PASS_EXAM);//不通过
			successCaseService.save(successCase);
		}
		return new ModelAndView(Constants.JSON_VIEW);
	}

	@Override
	public ModelAndView toCreateOrUpdate(HttpServletRequest request)throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   
		if(null != request.getParameter("objId") && !"".equals(request.getParameter("objId"))) {
			SuccessCase successCase  = successCaseService.get(request.getParameter("objId"));
			model.put("successCase", successCase);
		}else {
			model.put("successCase", new SuccessCase());
		}
		
		return new ModelAndView("successCaseFormView", model);
	}
	
	/**
	 * 查询案例列表，如果不是admin,查询orgInfoId是自己的数据
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected QueryObject onFind(QueryObject query, HttpServletRequest request) throws Exception {
		User currentUser = AuthenticationHelper.getCurrentUser(true);
		if(!currentUser.getUsrIsAdmin().equals(Role.ROLE_TYPE_ADMIN)){
			//如果是业务管理员的审核列表则不过滤
			if(!roleManagerImpl.isHasThisRole(currentUser.getObjId(), OrganizationEnum.ROLE_MANAGER)){
				query.getQueryParam().and(new QueryParam("orgInfo.objId",QueryParam.OPERATOR_EQ ,((OrgInfo) currentUser.getOrgInfo()).getObjId()));
			}
		}
		return query;
	}
	
	@Override
	protected void onSave(SuccessCase successCase) throws Exception {
		if(null == successCase.getObjId() || "".equals(successCase.getObjId())) {
			successCase.setOrgInfo((OrgInfo) AuthenticationHelper.getCurrentUser(true).getOrgInfo());
		}
	}
}
