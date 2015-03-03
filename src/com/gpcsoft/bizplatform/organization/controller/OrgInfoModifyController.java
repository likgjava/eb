package com.gpcsoft.bizplatform.organization.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.organization.domain.OrgInfoModify;
import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.bizplatform.organization.service.OrgInfoModifyService;
import com.gpcsoft.bizplatform.organization.service.OrgInfoService;
import com.gpcsoft.bizplatform.suppliers.domain.Supplier;
import com.gpcsoft.bizplatform.suppliers.service.SupplierService;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;

/**
*  @gpcsoft.view value="toModifyOrgView"
*  url="view/bizplatform/organization/manager/organization_change.jsp"
*  @gpcsoft.view value="toAuditModifyOrgListView"
*  url="view/bizplatform/organization/manager/organization_change_audit_list.jsp"
*  @gpcsoft.view value="toAuditModifyOrgView"
*  url="view/bizplatform/organization/manager/organization_change_audit.jsp"
*/

@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={OrgInfoModify.class})
@RequestMapping("/OrgInfoModifyController.do")//页面请求路径,可修改
public class OrgInfoModifyController extends AnnotationMultiController<OrgInfoModify> {

	@Autowired(required=true) @Qualifier("orgInfoModifyServiceImpl")
	private OrgInfoModifyService orgInfoModifyService;
	
	@Autowired(required=true) @Qualifier("orgInfoServiceImpl")
	private OrgInfoService orgInfoService;
	
	@Autowired(required=true) @Qualifier("supplierServiceImpl")
	private SupplierService supplierService;
	
	/** 
	 * Description :  跳转到机构变更
	 * Create Date: 2010-7-19下午06:29:00 by sunl  Modified Date: 2010-7-19下午06:29:00 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toModifyOrg")
    public ModelAndView toModifyOrg(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		String companyId = request.getParameter("companyId");
		
		companyId = StringUtils.hasLength(companyId)?companyId : AuthenticationHelper.getCurrentUser(true).getEmp().getCompany().getObjId();
		
		//根据当前用户的companyId，获得有效的orgInfo
		OrgInfo orgInfo = orgInfoService.getLastedOrgInfo(companyId,true);
		model.put("oldOrgInfo", orgInfo);
		
		//获取变更的机构信息
		if(orgInfo != null && null != orgInfo.getObjId()) {
			List<OrgInfoModify> orgModifyList = orgInfoModifyService.getOrgInfoAuditList(orgInfo.getObjId());
			model.put("orgModifyList", orgModifyList);
		}
		
		return new ModelAndView("toModifyOrgView", model);
    }
	
	/** 
	 * Description :  待审核变更机构列表
	 * Create Date: 2010-7-19下午06:29:00 by sunl  Modified Date: 2010-7-19下午06:29:00 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toAuditModifyOrgList")
    public ModelAndView toAuditModifyOrgList(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		List<OrgInfoModify> modifyAuditList = orgInfoModifyService.getOrgInfoAuditList(null);
		
		model.put("modifyAuditList", modifyAuditList);
		
		return new ModelAndView("toAuditModifyOrgListView", model);
	}
	
	/** 
	 * Description :  跳转到审核变更机构
	 * Create Date: 2010-7-19下午06:29:00 by sunl  Modified Date: 2010-7-19下午06:29:00 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toAuditModifyOrg")
    public ModelAndView toAuditModifyOrg(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		String orgId = request.getParameter("orgId");
		
		List<OrgInfoModify> modifyAudit = orgInfoModifyService.getOrgInfoAuditList(orgId);
		
		model.put("modifyAudit", modifyAudit);
		model.put("orgId", orgId);
		
		return new ModelAndView("toAuditModifyOrgView", model);
	}
	
	/** 
	 * Description :  审核变更机构
	 * Create Date: 2010-7-19下午06:29:00 by sunl  Modified Date: 2010-7-19下午06:29:00 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=auditModifyOrg")
    public ModelAndView auditModifyOrg(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		String orgId = request.getParameter("orgId");
		String status = request.getParameter("status");
		
		OrgInfo org = orgInfoService.get(orgId);
		
		List<OrgInfoModify> modifyAudit = orgInfoModifyService.getOrgInfoAuditList(orgId);
		for (OrgInfoModify orgInfoModify : modifyAudit) {
			orgInfoModify.setAuditStatus(status);
			orgInfoModify.setVerifyUser(AuthenticationHelper.getCurrentUser(true));
			orgInfoModify.setVerifyTime(new Date());
			orgInfoModify.setOpinion(request.getParameter("opinion"));
			
			//审核通过
			if(OrganizationEnum.PASS_EXAM.equals(status)) {
				if("orgName".equals(orgInfoModify.getModifyType())) {
					org.setOrgName(orgInfoModify.getNewValue());
				} else if("orgCode".equals(orgInfoModify.getModifyType())) {
					org.setOrgCode(orgInfoModify.getNewValue());
				} else if("bidForRange".equals(orgInfoModify.getModifyType())) {
					org.setBidForRange(orgInfoModify.getNewValue());
					//更新供应商的投标范围值
					if(StringUtils.hasLength(org.getSupplierId())) {
						Supplier supplier = supplierService.get(org.getSupplierId());
						supplier.setBidForRange(orgInfoModify.getNewValue());
						supplierService.save(supplier);
					}
				}
			}
		}
		orgInfoModifyService.save(modifyAudit);
		orgInfoService.save(org);
		
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	/** 
	 * Description :  保存机构变更
	 * Create Date: 2010-7-19下午06:29:00 by sunl  Modified Date: 2010-7-19下午06:29:00 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveChange")
    public ModelAndView saveChange(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		String changeOrgStr = request.getParameter("changeOrgStr");
		
		JSONArray jsonArray = JSONArray.fromObject(changeOrgStr);
		JSONObject[] jsonbject=(JSONObject[]) jsonArray.toArray(new JSONObject[]{});
		List<OrgInfoModify> orgModifyList = new ArrayList<OrgInfoModify>();
		if(jsonbject!=null) {
			for(JSONObject obj:jsonbject){
				OrgInfoModify orgModify = (OrgInfoModify)JSONObject.toBean(obj, OrgInfoModify.class);
				orgModify.setCreateUser(AuthenticationHelper.getCurrentUser(true));
				orgModify.setCreateTime(new Date());
				orgModifyList.add(orgModify);
			}
		}
		orgInfoModifyService.save(orgModifyList);
		
		return new ModelAndView(Constants.JSON_VIEW,model);
    }
}
