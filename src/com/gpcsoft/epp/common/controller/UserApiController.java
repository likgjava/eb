
package com.gpcsoft.epp.common.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.bizplatform.agency.domain.Agency;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryObjectBase;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.dao.hibernate.query.QueryWebUtils;
import com.gpcsoft.core.extra.json.FrameJsonView;
import com.gpcsoft.core.utils.HqlResultConvertUtils;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.bid.domain.BidPackage;
import com.gpcsoft.epp.bid.service.BidPackageService;
import com.gpcsoft.epp.common.domain.SeparationEnum;
import com.gpcsoft.epp.common.service.UserApiService;
import com.gpcsoft.epp.signuprecord.domain.SignUprecord;
import com.gpcsoft.epp.signuprecord.service.SignUprecordService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.Department;
import com.gpcsoft.srplatform.auth.domain.Employee;
import com.gpcsoft.srplatform.auth.domain.User;

/**
 * @gpcsoft.view value="assignBlockTradeAgent"
 * url="view/es/planform/project/assignBlockTradeAgent.jsp"
 * 
 * 
 * @gpcsoft.view value="toAgentInfo"
 * url="view/es/planform/taskplan/agentInfo.jsp"
 * 
 */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={OrgInfo.class})
@RequestMapping("/UserApiController.do")//页面请求路径,可修改
public class UserApiController extends AnnotationMultiController<OrgInfo> {
	
	@Autowired(required=true) @Qualifier("userApiServiceImpl")
	private UserApiService userApiService;

	@Autowired(required=true) @Qualifier("signUprecordServiceImpl")
	private SignUprecordService signUprecordService;
	
	@Autowired(required=true) @Qualifier("bidPackageServiceImpl")
	private BidPackageService bidPackageService;
	
	/** 
	 * Description : 根据查询条件获得代理机构信息
	 * Create Date: 2010-5-5下午03:59:36 by wangsw  
	 * Modified Date: 2010-5-5下午03:59:36 by wangsw
	 * @Exception   
	 */
	@RequestMapping(params = "method=getAgentList")   
	public ModelAndView getAgentList(HttpServletRequest request,HttpServletResponse response) throws Exception { 
		Map<String, Object> model = new HashMap<String, Object>();  
		QueryObject queryObject = new QueryObjectBase();
		List<OrgInfo> agentList=userApiService.getAllAgents(queryObject);
		model.put("agentList", agentList);
		return new ModelAndView(Constants.JSON_VIEW, model);
    }
	
	/**
	 * 
	 * Description :得到供应商列表  
	 * Create Date: 2010-8-17上午09:55:30 by liuke  Modified Date: 2010-8-17上午09:55:30 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params = "method=getSupplierList")   
	public ModelAndView getSupplierList(HttpServletRequest request,HttpServletResponse response) throws Exception { 
		Map<String, Object> model = new HashMap<String, Object>();  
		QueryObject queryObject = new QueryObjectBase();
		List<OrgInfo> agentList=userApiService.getAllAgents(queryObject);
		model.put("agentList", agentList);
		return new ModelAndView(Constants.JSON_VIEW, model);
    }
	
	/** 
	 * Description : 根据查询条件获得对应的人员信息
	 * Create Date: 2010-5-5下午03:59:36 by wangsw  Modified Date: 2010-5-5下午03:59:36 by wangsw
	 * @Exception   
	 */
	@RequestMapping(params = "method=getEmpList")   
	public ModelAndView getEmpList(String queryColumns, HttpServletRequest request,HttpServletResponse response) throws Exception { 
		Map<String, Object> model = new HashMap<String, Object>();
		Assert.notNull(queryColumns, "queryColumns不能为空!");
		QueryObject query = QueryWebUtils.getQuery(request, Employee.class);
		query.setEntityClass(Employee.class);
		initQueryColums(request, query);//初始化查询条件
		List<Object[]> objectList = userApiService.findByQuery(query);
		List<Employee> employeeList = new ArrayList<Employee>();
		for(Object[] objects:objectList){
			Employee employee = new Employee();
			employee.setObjId(objects[0]+"");
			employee.setName(objects[1]+"");
			employeeList.add(employee);
		}
		model.put("empList", employeeList);
		return new ModelAndView(Constants.JSON_VIEW, model);
    }
	/** 
	 * Description : 根据查询条件获得对应的人员信息
	 * Create Date: 2010-5-5下午03:59:36 by wangsw  Modified Date: 2010-5-5下午03:59:36 by wangsw
	 * @Exception   
	 */
	@SuppressWarnings("unchecked") 
	@RequestMapping(params = "method=getEmpListByCurUser")   
	public ModelAndView getEmpListByCurUser(String queryColumns, HttpServletRequest request,HttpServletResponse response) throws Exception { 
		logger.debug("\n es= UserApiController||getEmpListByCurUser\n");
		Map<String, Object> model = new HashMap<String, Object>();
		Assert.notNull(queryColumns, "queryColumns不能为空!");
		QueryObject query = QueryWebUtils.getQuery(request, Employee.class);
		initQueryColums(request, query);//初始化查询条件
		User user=AuthenticationHelper.getCurrentUser();
		Department department = userApiService.getDepartmentByUserId(user.getObjId());
		List<Employee> empList = null;
		if (null == department) {
			empList = new ArrayList<Employee>(0);
		}else{
			query.getQueryParam().and(new QueryParam("department.objId",QueryParam.OPERATOR_EQ,department.getObjId()));
			empList=userApiService.findByQuery(query);
		}
		model.put("empList", empList);
		return new ModelAndView(Constants.JSON_VIEW, model);
    }
	/** 
	 * Description : 根据公司id获得对应的人员信息
	 * Create Date: 2010-5-5下午03:59:36 by wangsw  Modified Date: 2010-5-5下午03:59:36 by wangsw
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=getEmpListByCompany")   
	public ModelAndView getEmpListByCompany(String queryColumns, HttpServletRequest request,HttpServletResponse response) throws Exception { 
		logger.debug("\n es= UserApiController||getEmpListByCompany\n");
		Page page = prePage(request);
		QueryObject query = QueryWebUtils.getQuery(request, Employee.class);
		Map model = new HashMap();
		Page<Employee> pages = userApiService.searchEmployee(query, page);
		model.put("page", pages);
		endPage(model, page, request);
		return new ModelAndView(Constants.JSON_VIEW, model);
    }
	
	/** 
	 * Description :  根据公司Id获取员工列表
	 * Create Date: 2010-10-22下午04:11:03 by yangx  Modified Date: 2010-10-22下午04:11:03 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=getEmpListByCompanyId")   
	public ModelAndView getEmpListByCompanyId(HttpServletRequest request) throws Exception {
		String companyId = request.getParameter("companyId");
		String empIds = request.getParameter("empIds");
		List<Employee> empList = userApiService.getEmpListByCompanyId(companyId,empIds);
		Map model = new HashMap();
		model.put(Constants.JSON_RESULT, empList);
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  查询业务处室
	 * Create Date: 2010-11-16上午10:14:08 by yangx  Modified Date: 2010-11-16上午10:14:08 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=getDepartmentByQueryObject")   
	public ModelAndView getDepartmentByQueryObject(String queryColumns, String includedProperties, HttpServletRequest request) throws Exception {
		Assert.notNull(queryColumns, "queryColumns不能为空!");
		QueryObject<Department> query = QueryWebUtils.getQuery(request,Department.class);
		initQueryColums(request, query);//初始化查询条件
		String orgId = SeparationEnum.QD_CZJID;//财政局
		String not_depId = SeparationEnum.QD_CGBID;//要过滤掉的ID[采购办]
		query.getQueryParam().and(new QueryParam("parent.objId",QueryParam.OPERATOR_EQ,orgId));
		query.getQueryParam().and(new QueryParam("objId",QueryParam.OPERATOR_NE,not_depId));
		List<Department> departmentList= userApiService.getDepartmentByQueryObject(query);
		departmentList=HqlResultConvertUtils.hqlResultConvert(departmentList, queryColumns.split(","), null,Department.class, getEnumColumns());
		Map model = new HashMap();
		model.put(Constants.JSON_RESULT, departmentList);  
		if (includedProperties!=null) {
			model.put(FrameJsonView.INCLUDED_PROPERTIES, includedProperties.split(","));//输出的结果当中包含的GpcObject	  Collection属性
		}
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  跳转到设置大宗代理机构页面
	 * Create Date: 2010-11-23上午10:17:13 by yangx  Modified Date: 2010-11-23上午10:17:13 by yangx
	 * @param   request
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=toBlockTradeAgent")
	public ModelAndView toBlockTradeAgent(HttpServletRequest request)throws Exception {
		String pageNum=request.getParameter(Page.PAGE_NAME);
		String rp=request.getParameter(Page.PAGE_SIZE_NAME);
		Page page=new Page();
		if(null != pageNum && "0".equals(pageNum)){
			pageNum = "1";
		}
		int curpage = Integer.parseInt((pageNum == null|| "".equals(rp)) ? "1":pageNum);		//得到当前页数   
		int limit = Integer.parseInt((rp == null || "".equals(rp)) ? "5":rp);					//得到当前页大小  
		int start = (curpage==1?0:Page.getStartOfPage(curpage, limit));
		page.setStart(start);
		page.setPageSize(limit);
		page.setStartRowNum(start);
		page = userApiService.getAllAgentForBlockTrade(page);
		Map<String, Object> model = new HashMap<String, Object>();
		super.endPage(model, page, request);
		model.put("PAGERESULT", page);
		model.put("pageSearchUrl", "UserApiController.do?method=toBlockTradeAgent");//设置分页对应的请求地址
		return new ModelAndView("assignBlockTradeAgent", model);
	}
	
	/** 
	 * Description :  保存设置的大宗代理机构
	 * Create Date: 2010-11-23下午01:02:10 by yangx  Modified Date: 2010-11-23下午01:02:10 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=submitAgentForBlockTrade")
	public ModelAndView submitAgentForBlockTrade(HttpServletRequest request,SessionStatus status)throws Exception {
		String orgInfoId = request.getParameter("orgInfoId");
		String orgName = request.getParameter("orgName");
		userApiService.saveAgentForBlockTrade(orgName,orgInfoId);
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/** 
	 * Description :  删除设置的大宗代理机构
	 * Create Date: 2010-11-23下午02:25:31 by yangx  Modified Date: 2010-11-23下午02:25:31 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=removeAgentForBlockTrade")
	public ModelAndView removeAgentForBlockTrade(HttpServletRequest request,SessionStatus status)throws Exception {
		String orgInfoIds = request.getParameter("orgInfoIds");
		userApiService.removeAgentForBlockTrade(orgInfoIds);
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/** 
	 * Description :  查询代理机构
	 * Create Date: 2010-11-23下午04:55:42 by yangx  Modified Date: 2010-11-23下午04:55:42 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=getOrgInfoForAgent")   
	public ModelAndView getOrgInfoForAgent(HttpServletRequest request) throws Exception {
		String orgIds = request.getParameter("orgInfoIds");
		List<OrgInfo> orgInfoList= userApiService.getOrgInfoForAgent(orgIds);
		Map model = new HashMap();
		model.put(Constants.JSON_RESULT, orgInfoList);  
		return new ModelAndView(Constants.JSON_VIEW, model);
	}	
	
	/** 
	 * Description :  从设置的大宗代理机构文件中查询代理机构
	 * Create Date: 2010-11-24上午11:46:14 by yangx  Modified Date: 2010-11-24上午11:46:14 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=getAgentForFile")   
	public ModelAndView getAgentForFile(HttpServletRequest request) throws Exception {
		List<OrgInfo> orgInfoList= userApiService.getAgentForFile();
		Map model = new HashMap();
		model.put(Constants.JSON_RESULT, orgInfoList);  
		return new ModelAndView(Constants.JSON_VIEW, model);
	}	
	
	/** 
	 * Description : 根据代理机构ID查询代理机构信息
	 * Create Date: 2010-11-24上午11:46:14 by liuke  Modified Date: 2010-11-24上午11:46:14 by liuke
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=getAgentMessageByObjId")   
	public ModelAndView getAgentMessageByObjId(HttpServletRequest request) throws Exception {
		String objId = request.getParameter("objId");
		Agency  agent = userApiService.getAgentMessageByObjId(objId);
		Map model = new HashMap();
		model.put("agent", agent);  
		return new ModelAndView("toAgentInfo", model);
	}	
	
	/**
	 * FuncName: getOrginfoByQueryObject
	 * Description :  录入保证金界面自动填充：查询供应商的处理方式
	 * @param 
	 * @param queryColumns
	 * @param includedProperties
	 * @param request
	 * @return
	 * @throws Exception ModelAndView
	 * @author: shenjz
	 * @Create Date:2011-11-8  下午05:10:31
	 * @Modifier: shenjz
	 * @Modified Date:2011-11-8  下午05:10:31
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=getOrginfoByQueryObject")   
	public ModelAndView getOrginfoByQueryObject(String queryColumns, String includedProperties, HttpServletRequest request) throws Exception{
		Map model = new HashMap();
		StringBuffer sb = new StringBuffer("");
		StringBuffer sb2 = new StringBuffer("");
		String alreadSupplierIds = request.getParameter("alreadSupplierIds");
		String[]alreadSupplierId = alreadSupplierIds.split(",");
		if(alreadSupplierId!=null){
			for (int i = 0; i < alreadSupplierId.length; i++) {
				sb2.append("'");
				sb2.append(alreadSupplierId[i]);
				sb2.append("',");
			}
		}
		String orgName = request.getParameter("orgName");
		List<BidPackage> bidPackageList = bidPackageService.getAllBidPackageByProjId(request.getParameter("projectId"));
		List<SignUprecord> signUprecordList = null;
		if(bidPackageList!=null&&!bidPackageList.isEmpty()){
			for (Iterator iterator = bidPackageList.iterator(); iterator
					.hasNext();) {
				BidPackage bidPackage = (BidPackage) iterator.next();
				String supplierId = bidPackage.getBid().getSupplier().getObjId();
				if(alreadSupplierIds.indexOf(supplierId)==-1){
					sb.append("'");
					sb.append(bidPackage.getBid().getSupplier().getObjId());
					sb.append("',");
				}
			}
		}else {
			signUprecordList = signUprecordService.getSignUprecordByprojectId(request.getParameter("projectId"));
			if(signUprecordList!=null&&!signUprecordList.isEmpty()){
				for (Iterator iterator = signUprecordList.iterator(); iterator
						.hasNext();) {
					SignUprecord signUprecord = (SignUprecord) iterator.next();
					String supplierId = signUprecord.getSupplier().getObjId();
					if(alreadSupplierIds.indexOf(supplierId)==-1){
						sb.append("'");
						sb.append(supplierId);
						sb.append("',");
					}
				}
			}
		}
		if (((bidPackageList!=null&&bidPackageList.size()>0)||(signUprecordList!=null&&signUprecordList.size()>0))&&"".equals(sb.toString())) {
			model.put(Constants.JSON_RESULT, "");			
		}else{
			String sbStr = sb.toString();
			if(sbStr.lastIndexOf(",")!=-1){
				sbStr =	sbStr.substring(0, sbStr.lastIndexOf(","));
			}
			String sb2Str = sb2.toString();
			if(sb2Str.lastIndexOf(",")!=-1){
				sb2Str = sb2Str.substring(0, sb2Str.lastIndexOf(","));
			}
			List  orgInfoList = userApiService.getSuppliers2(sbStr, orgName,sb2Str);
			model.put(Constants.JSON_RESULT, orgInfoList);
		}
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
}
