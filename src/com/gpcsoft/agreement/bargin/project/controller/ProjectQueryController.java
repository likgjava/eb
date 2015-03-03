package com.gpcsoft.agreement.bargin.project.controller;

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

import com.gpcsoft.agreement.bargin.project.domain.ProjectQueryDto;
import com.gpcsoft.agreement.bargin.project.service.BargainProjectService;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.core.web.controller.AnnotationMultiController;


/**
 * 
  */

@SuppressWarnings("unchecked")
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={ProjectQueryDto.class})
@RequestMapping("/ProjectQueryController.do")//页面请求路径,可修改
public class ProjectQueryController extends AnnotationMultiController {
	
	@Autowired(required=true) @Qualifier("bargainProjectServiceImpl")
	private BargainProjectService bargainProjectService;
	
	/** 
	 * Description :  项目查询列表数据(采购人)
	 * Create Date: 2011-6-29下午01:46:52 by yucy  Modified Date: 2011-6-29下午01:46:52 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getProjectList")   
	public ModelAndView getProjectList(HttpServletRequest request ) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		
		
		String orgId = request.getParameter("orgId");
		param.put("orgId", orgId);

		//搜索条件
		String projName = request.getParameter("projName");
		param.put("projName", projName);
		
		String projCode = request.getParameter("projCode");
		param.put("projCode", projCode);
		
		String supplierName = request.getParameter("supplierName");
		param.put("supplierName", supplierName);
		
		String startTime = request.getParameter("startTime");
		param.put("startTime", startTime);
		
		String endTime = request.getParameter("endTime");
		param.put("endTime", endTime);
		
		//排序条件
		
		//进行状态
		String  status = request.getParameter("status");
		param.put("status", status);
		
		//预分页,算出当前页和大小等		
		Page<Object> page = prePage(request);	
		
		//page.setStart(30);
		
		//正在进行的项目
		if("bargaining".equals(status)){
			page = bargainProjectService.getProjectList(page,param);
		}else{
			page = bargainProjectService.getProjectList(page,param);
		}
		
		endPage(model, page, request);
		
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	/** 
	 * Description :  项目查询列表数据(供应商)
	 * Create Date: 2011-6-29下午01:46:52 by yucy  Modified Date: 2011-6-29下午01:46:52 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getMyProjectList")   
	public ModelAndView getMyProjectList(HttpServletRequest request ) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();

		//搜索条件
		String projName = request.getParameter("projName");
		param.put("projName", projName);
		
		String projCode = request.getParameter("projCode");
		param.put("projCode", projCode);
		
		String buyerName = request.getParameter("buyerName");
		param.put("buyerName", buyerName);
		
		String startTime = request.getParameter("startTime");
		param.put("startTime", startTime);
		
		String endTime = request.getParameter("endTime");
		param.put("endTime", endTime);
		
		//排序条件
		
		//进行状态
		String  status = request.getParameter("status");
		param.put("status", status);
		
		//预分页,算出当前页和大小等		
		Page<Object> page = prePage(request);	
		
		//正在进行的项目
		if("bargaining".equals(status)){
			page = bargainProjectService.getMyProjectList(page,param);
		}else{
			page = bargainProjectService.getMyProjectList(page,param);
		}
		
		endPage(model, page, request);
		
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	/**
	 * Description :  我与客户的交易项目记录
	 * Create Date: 2011-7-7下午02:56:14 by zhaojf  Modified Date: 2011-7-7下午02:56:14 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=getExchangeProjectRecord")
	public ModelAndView getExchangeProjectRecord(HttpServletRequest request) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		
		String supplierOrgId = request.getParameter("supplierId");//供货商机构id
		String projectCreateUserId = request.getParameter("createUserId");//项目创建人用户Id
		
		param.put("supplierOrgId", supplierOrgId);
		param.put("projectCreateUserId", projectCreateUserId);
		
		Page<ProjectQueryDto> page = prePage(request);
		Page<ProjectQueryDto> pageList = bargainProjectService.getExchangeProjectRecord(page, param);
		endPage(model, pageList, request);		
		
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
}
