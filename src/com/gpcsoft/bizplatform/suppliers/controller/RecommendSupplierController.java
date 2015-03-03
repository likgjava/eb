package com.gpcsoft.bizplatform.suppliers.controller;

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

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.bizplatform.suppliers.domain.RecommendSupplier;
import com.gpcsoft.bizplatform.suppliers.service.RecommendSupplierService;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.utils.HqlResultConvertUtils;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.core.web.controller.AnnotationMultiController;

/**
  * @gpcsoft.view value="recommendSupplierView"
  *  url="view/bizplatform/suppliers/recommend/recommend_supplier.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={RecommendSupplier.class})
@RequestMapping("/RecommendSupplierController.do")//页面请求路径,可修改
public class RecommendSupplierController extends AnnotationMultiController<RecommendSupplier> 
{
	@Autowired(required=true) @Qualifier("recommendSupplierServiceImpl")
	private RecommendSupplierService recommendSupplierService;
	
	//@Autowired(required=true) @Qualifier("evaluateServiceImpl")
	//private EvaluateService evaluateService;

	/** 
	 * Description :  跳转到供应商推荐视图
	 * Create Date: 2010-10-14 下午 16:30:16 by likg  Modified Date: 2010-10-14 下午 16:30:16 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=toRecommendSupplierView")
	public ModelAndView toRecommendSupplierView() throws Exception
	{
		Map<String, Object> model = new HashMap<String, Object>();
		return new ModelAndView("recommendSupplierView", model);
	}
	
	/** 
	 * Description :  推荐供应商
	 * Create Date: 2010-10-14 下午 16:30:16 by likg  Modified Date: 2010-10-14 下午 16:30:16 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=recommendSupplier")
	public ModelAndView recommendSupplier(String supplierIds, String recommendReason) throws Exception
	{
		Map<String, Object> model = new HashMap<String, Object>();
		
		recommendSupplierService.recommendSupplier(supplierIds, recommendReason);
		model.put("success", true);
		
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  获取所有的未推荐供应商
	 * Create Date: 2010-10-14 下午 16:30:16 by likg  Modified Date: 2010-10-14 下午 16:30:16 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=listNoRecommendSupplier")
	@SuppressWarnings("unchecked")
	public ModelAndView listNoRecommendSupplier(HttpServletRequest request) throws Exception
	{
		Map<String, Object> param = new HashMap<String, Object>();
		Map<String, Object> model = new HashMap<String, Object>();
		
		//搜索条件
		param.put("unitScape", request.getParameter("unitScape")==null?"":request.getParameter("unitScape"));
		param.put("entPrpt", request.getParameter("entPrpt")==null?"":request.getParameter("entPrpt"));
		param.put("orgName", request.getParameter("orgName")==null?"":request.getParameter("orgName"));
		param.put("order", request.getParameter("order")==null?"":request.getParameter("order"));//排序
		param.put("order_flag", request.getParameter("order_flag")==null?"":request.getParameter("order_flag"));//排序
		
		Page page = prePage(request);	//预分页,算出当前页和大小等
	    Page pageData = recommendSupplierService.listNoRecommendSupplier(param, page);
	    
	   
		String alias=request.getParameter("alias");
		String queryColumns = makeQueryColumns(request);	//接收前台的指定列名
		pageData.setData(HqlResultConvertUtils.hqlResultConvert(pageData.getData(), queryColumns.split(","), alias==null?null:alias.split(","), OrgInfo.class, getEnumColumns()));
		
		endPage(model, pageData, request);
	    
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  删除推荐供应商
	 * Create Date: 2010-10-13 下午 16:30:16 by likg  Modified Date: 2010-10-13 下午 16:30:16 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=deleteRecommedSupplier")
	public ModelAndView deleteRecommedSupplier(String objId) throws Exception
	{
		Map<String, Object> model = new HashMap<String, Object>();
		Boolean result = false;
		
		if(objId!=null && !"".equals(objId))
		{
			result = recommendSupplierService.deleteRecommedSupplier(objId.split(","));
		}
		
		if(result)
		{
			model.put("success", true);
		}
		
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  修改推荐供应商的排序序号
	 * Create Date: 2010-10-15上午10:40:08 by likg  Modified Date: 2010-10-15上午10:40:08 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=updateSort")
	public ModelAndView updateSort(HttpServletRequest request) throws Exception
	{
		String sourceObjId = request.getParameter("sourceObjId");
		String isToUp = request.getParameter("isToUp");
		
		recommendSupplierService.updateSort(sourceObjId, ("true".equals(isToUp) ? true : false));
		
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
}
