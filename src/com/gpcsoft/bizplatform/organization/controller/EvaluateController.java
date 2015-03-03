package com.gpcsoft.bizplatform.organization.controller;


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

import com.gpcsoft.bizplatform.base.evaluate.domain.Quota;
import com.gpcsoft.bizplatform.base.evaluate.service.QuotaService;
import com.gpcsoft.bizplatform.organization.domain.Evaluate;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.bizplatform.organization.service.EvaluateService;
import com.gpcsoft.bizplatform.organization.service.OrgInfoService;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.utils.StringUtils;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.service.ProjectService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.User;

/**
  * @gpcsoft.view value="evaluateFormView"
  *  url="view/bizplatform/organization/evaluate/evaluate_form.jsp"
  *  
  * @gpcsoft.view value="evaluateDetailView"
  *  url="view/bizplatform/organization/evaluate/evaluate_detail.jsp"
  *  
  * @gpcsoft.view value="myEvaluateView"
  *  url="view/bizplatform/organization/evaluate/my_evaluate_view.jsp"
  */

@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={Evaluate.class})
@RequestMapping("/EvaluateController.do")//页面请求路径,可修改
public class EvaluateController extends AnnotationMultiController<Evaluate> {

	@Autowired(required=true) @Qualifier("evaluateServiceImpl")
	private EvaluateService evaluateService;
	
	@Autowired(required=true) @Qualifier("quotaServiceImpl")
	private QuotaService quotaService;
	
	@Autowired(required=true) @Qualifier("orgInfoServiceImpl")
	private OrgInfoService orgInfoService;
	
	@Autowired(required=true) @Qualifier("projectServiceImpl")
	private ProjectService projectService;
	
	/** 
	 * Description :  跳转至评价页面
	 * Create Date: 2010-7-26上午11:48:51 by yucy  Modified Date: 2010-7-26上午11:48:51 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toEvaluate")   
	public ModelAndView toEvaluate(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   
		List<Quota> quotaList = null;
		
		Evaluate evaluate = new Evaluate();
		
		String orgId = request.getParameter("orgId");//传过来的orgInfoId
		OrgInfo org = orgInfoService.get(orgId);
		evaluate.setOrg(org);
		evaluate.setProject(request.getParameter("projectId"));//传过来的projectId  
		
		if(StringUtils.hasLength(request.getParameter("projectName"))){
			evaluate.setProjectName(com.gpcsoft.bizplatform.base.common.util.StringUtils.ascii2Native(request.getParameter("projectName")));//传过来的projectName  --fireFox 与ie冲突
		}
		
		Map<String, Object> params = new HashMap<String, Object>();   
		params.put("org", org);
		if(StringUtils.hasLength(request.getParameter("projectId"))){
			Project project = projectService.get(request.getParameter("projectId"));
			params.put("buyerId", project.getBuyersId());
		}

		quotaList =  quotaService.getQuotaLisstByOrgInfo(params);
		User user = AuthenticationHelper.getCurrentUser(true);
		evaluate.setRater(user);
		
		model.put("evaluate", evaluate);
		model.put("quotaList", quotaList);
		
		ModelAndView mv = new ModelAndView("evaluateFormView", model);

		return mv;
	}
	
	/** 
	 * Description :  跳转至查看评价页面
	 * Create Date: 2010-7-26下午01:52:03 by yucy  Modified Date: 2010-7-26下午01:52:03 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toEvaluateView")   
	public ModelAndView toEvaluateView(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   
		
		String evaluateId = request.getParameter("evaluateId");
		
	    Evaluate evaluate =	evaluateService.get(evaluateId);
	  
	    model.put("evaluate", evaluate);
	    
		ModelAndView mv = new ModelAndView("evaluateDetailView", model);

		return mv;
	}
	
	/** 
	 * Description : 保存评价以及具体分数
	 * Create Date: 2010-7-21下午05:33:06 by yucy  Modified Date: 2010-7-21下午05:33:06 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveEvaluate")   
	public ModelAndView saveEvaluate(HttpServletRequest request, Evaluate evaluate, SessionStatus status) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   
		
		OrgInfo currentOrg = (OrgInfo) AuthenticationHelper.getCurrentUser(true).getOrgInfo();
		
		evaluate.setRateOrg(currentOrg);
		
		evaluateService.save(evaluate);
		
		status.setComplete();

		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	
	/** 
	 * Description :  跳转到我的评价页面
	 * Create Date: 2010-8-12下午03:11:47 by yucy  Modified Date: 2010-8-12下午03:11:47 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=toMyEvaluate")   
	public ModelAndView toMyEvaluate(HttpServletRequest request, SessionStatus status) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();   
		
		//取得当前orgInfo
		OrgInfo currentOrg = (OrgInfo) AuthenticationHelper.getCurrentUser(true).getOrgInfo();
		model.put("currentOrgId", currentOrg.getObjId());
		
		//是代理机构
		if(currentOrg.getAgencyId()!=null){
			model.put("Buyer", true);
			model.put("Supplier", true);
		}
		
		List quotaDetailList = null;
		
		//是采购人
		if(currentOrg.getBuyerId()!=null){
			//model.put("Agent", true);
			model.put("Supplier", true);
			quotaDetailList = (List)evaluateService.getQuataScoreDetail(currentOrg,OrganizationEnum.BUYER);
		}
		
		//是供应商
		if(currentOrg.getSupplierId()!=null){
			//model.put("Agent", true);
			model.put("Buyer", true);
			quotaDetailList = (List)evaluateService.getQuataScoreDetail(currentOrg,OrganizationEnum.SUPPLIER);//换成枚举
		}	
		//好
		Map<String, Object> row1 = ( Map<String, Object> ) evaluateService.getTotalTableInfo(currentOrg,'0');
		//中
		Map<String, Object> row2 = ( Map<String, Object> ) evaluateService.getTotalTableInfo(currentOrg,'1');
		//差
		Map<String, Object> row3 = ( Map<String, Object> ) evaluateService.getTotalTableInfo(currentOrg,'2');
		
		BigDecimal lastWeek = ((BigDecimal)row1.get("lastWeek")).add((BigDecimal)row2.get("lastWeek")).add((BigDecimal)row3.get("lastWeek"));
		BigDecimal lastMonth = ((BigDecimal)row1.get("lastMonth")).add((BigDecimal)row2.get("lastMonth")).add((BigDecimal)row3.get("lastMonth"));
		BigDecimal lastSixMonth = ((BigDecimal)row1.get("lastSixMonth")).add((BigDecimal)row2.get("lastSixMonth")).add((BigDecimal)row3.get("lastSixMonth"));
		BigDecimal beforSixMonth = ((BigDecimal)row1.get("beforSixMonth")).add((BigDecimal)row2.get("beforSixMonth")).add((BigDecimal)row3.get("beforSixMonth"));
		BigDecimal total =((BigDecimal)row1.get("total")).add((BigDecimal)row2.get("total")).add((BigDecimal)row3.get("total"));
		
		//总
		Map<String, Object> row4 = new HashMap<String, Object>();
		row4.put("lastWeek",lastWeek );
		row4.put("lastMonth",lastMonth );
		row4.put("lastSixMonth",lastSixMonth );
		row4.put("beforSixMonth",beforSixMonth );
		row4.put("total",total );
		
		model.put("row1", row1);
		model.put("row2", row2);
		model.put("row3", row3);
		model.put("row4", row4);
		model.put("quotaDetailList", quotaDetailList);
		
		return new ModelAndView("myEvaluateView",model);
	}
	
	/** 
	 * Description : 根据机构id取得评价信息
	 * Create Date: 2011-12-7上午11:56:19 by yucy  Modified Date: 2011-12-7上午11:56:19 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getEvaluateByOrgId")   
	public ModelAndView getEvaluateByOrgId(HttpServletRequest request) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();   
		List<Object[]> evaluateInfoList = evaluateService.getEvaluateByOrgId(request.getParameter("orgIds"));
		model.put("evaluateInfoList", evaluateInfoList);
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
}
