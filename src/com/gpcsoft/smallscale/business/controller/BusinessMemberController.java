package com.gpcsoft.smallscale.business.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.bizplatform.organization.service.OrgInfoService;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.utils.JsonUtils;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.pubservice.common.enumeration.CommonEnum;
import com.gpcsoft.smallscale.business.domain.BusinessMember;
import com.gpcsoft.smallscale.business.service.BusinessMemberService;
import com.gpcsoft.srplatform.auth.domain.Role;
import com.gpcsoft.srplatform.auth.manager.RoleManager;
import com.gpcsoft.srplatform.auth.service.OrganizationService;

/**
  * @gpcsoft.view value="toJoinBusinessMemberView"
  *  url="view/smallscale/business/business_member_join.jsp"
  *  
  * @gpcsoft.view value="toBusinessMemberDetailView"
  *  url="view/smallscale/business/business_member_detail.jsp"
  *  
  * @gpcsoft.view value="toBusinessMemberAuditView"
  *  url="view/smallscale/business/business_member_audit.jsp"
  *  
  * @gpcsoft.view value="toZhanhuiListView"
  *  url="view/smallscale/business/trade_show_list.jsp"
  *  
  * @gpcsoft.view value="toZhanhuiDetailView"
  *  url="view/smallscale/business/trade_show_detail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={BusinessMember.class})
@RequestMapping("/BusinessMemberController.do")//页面请求路径,可修改
public class BusinessMemberController extends AnnotationMultiController<BusinessMember> {

	@Autowired(required=true) @Qualifier("businessMemberServiceImpl")
	private BusinessMemberService businessMemberService;
	@Autowired(required = true) @Qualifier("orgInfoServiceImpl")
	private OrgInfoService orgInfoServiceImpl;
	@Autowired(required=true) @Qualifier("roleManagerImpl")
	private RoleManager roleManagerImpl;
	@Autowired(required=true) @Qualifier("organizationServiceImpl")
	private OrganizationService organizationServiceImpl;
	
	/** 
	 * Description :  跳转到查看
	 * Create Date: 2010-7-19下午06:29:00 by sunl  Modified Date: 2010-7-19下午06:29:00 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toView")
    public ModelAndView toView(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();

		String objId = request.getParameter("objId");
		
		BusinessMember businessMember = businessMemberService.get(objId);
		
		model.put("businessMember",businessMember);
		
		return new ModelAndView("toBusinessMemberDetailView", model);
    }
	
	/** 
	 * Description :  跳转到审核
	 * Create Date: 2010-7-19下午06:29:00 by sunl  Modified Date: 2010-7-19下午06:29:00 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toAudit")
    public ModelAndView toAudit(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		String objId = request.getParameter("objId");
		
		BusinessMember businessMember = businessMemberService.get(objId);
		
		model.put("businessMember",businessMember);
		
		return new ModelAndView("toBusinessMemberAuditView", model);
    }
	
	/** 
	 * Description :  审核
	 * Create Date: 2010-7-19下午06:29:00 by sunl  Modified Date: 2010-7-19下午06:29:00 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=auditBusinessMember")
    public ModelAndView auditBusinessMember(BusinessMember businessMember,HttpServletRequest request) throws Exception {
		//若审核通过，授权商圈会员角色
		if(businessMember.getAuditStatus().equals(CommonEnum.PASS_EXAM)) {
			//获取申请会员的机构
			OrgInfo orgInfo = orgInfoServiceImpl.get(businessMember.getOrgInfo().getObjId());
			StringBuffer orgRoles = new StringBuffer();//记录角色ID
			
			//获取商圈会员角色
			Role businessRole = roleManagerImpl.getRoleByType(OrganizationEnum.ROLE_BUSINESS_MEMBER);
			orgRoles.append(businessRole.getObjId()+",");
			
			//将当前角色权限授予机构
			organizationServiceImpl.saveOrgRole(orgRoles.toString().substring(0,orgRoles.toString().length()-1).split(","),orgInfo.getCompany().getObjId());
		}
		
		//保存审核状态
		businessMemberService.save(businessMember);
		
		return new ModelAndView(Constants.JSON_VIEW);
    }
	
	/** 
	 * Description :  跳转到加入商圈
	 * Create Date: 2010-7-19下午06:29:00 by sunl  Modified Date: 2010-7-19下午06:29:00 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toJoin")
    public ModelAndView toJoin(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		OrgInfo orgInfo = (OrgInfo)AuthenticationHelper.getCurrentUser(true).getOrgInfo();
		model.put("orgInfo", orgInfo);
		
		Map<String, Object> resMap = businessMemberService.getBusinessMemberList(orgInfo.getObjId());
		model.put("resMap", resMap);
		
		return new ModelAndView("toJoinBusinessMemberView", model);
    }
	
	/** 
	 * Description :  保存商圈会员
	 * Create Date: 2010-7-19下午06:29:00 by sunl  Modified Date: 2010-7-19下午06:29:00 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveJoin")
    public ModelAndView saveJoin(HttpServletRequest request) throws Exception {
		String businessMemberStr = request.getParameter("businessMemberStr");
		BusinessMember businessMember = JsonUtils.json2Bean(businessMemberStr, BusinessMember.class);
		
		if(businessMember.getObjId() != null && "".equals(businessMember.getObjId())) {
			businessMember.setObjId(null);
		}
		businessMember.setAuditStatus(OrganizationEnum.AWAIT_EXAM);//待审核
		businessMember.setUseStatus(OrganizationEnum.USE_TEMP);//临时
		businessMember.setBegainDate(new Date());//开始日期
		businessMember.setIsOff(OrganizationEnum.ENABLE);//默认启用
		
		Integer timeType = Integer.valueOf(businessMember.getTimeType());
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, +timeType);//当前年加上会员时长
		businessMember.setEndDate(cal.getTime());//结束日期
		
		businessMemberService.save(businessMember);
		
		return new ModelAndView(Constants.JSON_VIEW);
    }
	
	@SuppressWarnings("unchecked")
	@Override
	protected QueryObject onFind(QueryObject query, HttpServletRequest request) throws Exception {
		String owner = request.getParameter("owner");
		//查询个人的商圈会员记录
		if(StringUtils.hasLength(owner) && "mine".equals(owner)) {
			query.getQueryParam().and(new QueryParam("createUser.objId",AuthenticationHelper.getCurrentUser(true).getObjId()));		
		}
		return query;
	}
}
