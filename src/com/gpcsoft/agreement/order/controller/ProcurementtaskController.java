package com.gpcsoft.agreement.order.controller;

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
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.agreement.order.domain.Order;
import com.gpcsoft.agreement.order.domain.Procurementtask;
import com.gpcsoft.agreement.order.service.OrderService;
import com.gpcsoft.agreement.order.service.ProcurementtaskService;
import com.gpcsoft.bizplatform.base.exception.CustomerException;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.context.FrameBeanFactory;
import com.gpcsoft.core.context.FrameMessageResource;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.extra.json.FrameJsonView;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.core.utils.StringUtils;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.common.utils.UploadFileResult;
import com.gpcsoft.epp.common.utils.UploadFileUtil;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.common.assist.service.FreeMarkerService;

/**
  * @gpcsoft.view value="toProcurementtaskListView"
  *  url="view/agreement/bargin/purchaseplan/purchase_plan_list.jsp"
  *  
  * @gpcsoft.view value="toCreateOrUpdateView"
  *  url="view/agreement/bargin/purchaseplan/purchase_plan_form.jsp"
  *  
  * @gpcsoft.view value="toPlanDetailView"
  *  url="view/agreement/bargin/purchaseplan/purchase_plan_detail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={Procurementtask.class})
@RequestMapping("/ProcurementtaskController.do")//页面请求路径,可修改
public class ProcurementtaskController extends AnnotationMultiController<Procurementtask> {
 
	@Autowired(required=true) @Qualifier("procurementtaskServiceImpl")
	private ProcurementtaskService procurementtaskService;
	
	@Autowired(required=true)
	@Qualifier("freeMarkerServiceImpl")
	private FreeMarkerService freeMarkerService;
	/** 
	 * Description :跳转到任务书
	 * Create Date: 2010-4-29上午10:12:13 by yucy  Modified Date: 2010-4-29上午10:12:13 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toProcurementtaskList")   
	public ModelAndView toProcurementtaskList(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   
		return new ModelAndView("toProcurementtaskListView", model);
    }
	
	/** 
	 * Description :  跳转到录入采购计划
	 * Create Date: 2010-11-9上午09:46:04 by yucy  Modified Date: 2010-11-9上午09:46:04 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toCreateOrUpdateView")   
	public ModelAndView toCreateOrUpdateView(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   
		if(StringUtils.hasLength(request.getParameter("objId"))){
			Procurementtask procurementtask = procurementtaskService.get(request.getParameter("objId"));
			model.put("procurementtask", procurementtask);
		}else{
			Procurementtask procurementtask = new Procurementtask();
			model.put("procurementtask", procurementtask);
		}
		return new ModelAndView("toCreateOrUpdateView",model);
    }
	
	/** 
	 * Description :  跳转采购计划详情
	 * Create Date: 2010-11-9上午09:46:04 by yucy  Modified Date: 2010-11-9上午09:46:04 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toPlanDetailView")   
	public ModelAndView toPlanDetailView(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   
		Procurementtask procurementtask = procurementtaskService.get(request.getParameter("objId"));
		model.put("procurementtask", procurementtask);
		return new ModelAndView("toPlanDetailView",model);
    }
	
	
	
	/** 
	 * Description : 相关订单列表
	 * Create Date: 2010-4-12下午12:16:49 by yucy  Modified Date: 2010-4-12下午12:16:49 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params="method=listOrder")
	public ModelAndView listOrder(HttpServletRequest request) throws Exception {
        OrderService orderService = (OrderService)FrameBeanFactory.getBean("orderServiceImpl");
		//HQL方式的后台分页写法,  支持dataTables、FlexGrid这两种分页控件通用
		Map<String, Object> model = new HashMap<String, Object>();
		Page<Order> page = prePage(request);//预分页,算出当前页和大小等		
		Page pageData = (Page) orderService.listOrderByTask(page,request);
		model.put(FrameJsonView.INCLUDED_PROPERTIES, new String[]{});//FrameJsonView.INCLUDED_PROPERTIES  表示输出的时候包括复杂属性
		endPage(model, pageData, request);
		return new ModelAndView(Constants.JSON_VIEW,model);
    } 
	
	/** 
	 * Description :  
	 * Create Date: 2010-4-13下午03:28:21 by yucy  Modified Date: 2010-4-13下午03:28:21 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params="method=listOrderByTaskItem")
	public ModelAndView listOrderByTaskItem(HttpServletRequest request) throws Exception {
        OrderService orderService = (OrderService)FrameBeanFactory.getBean("orderServiceImpl");
		//HQL方式的后台分页写法,  支持dataTables、FlexGrid这两种分页控件通用
		Map<String, Object> model = new HashMap<String, Object>();
		Page<Order> page = prePage(request);//预分页,算出当前页和大小等		
		Page pageData = (Page) orderService.listOrderByTaskItem(page,request);
		
		model.put(FrameJsonView.INCLUDED_PROPERTIES, new String[]{"supplier.orgName","contract.contractNo"});//FrameJsonView.INCLUDED_PROPERTIES  表示输出的时候包括复杂属性
		endPage(model, pageData, request);
		return new ModelAndView(Constants.JSON_VIEW,model);
    }
	
	@SuppressWarnings("unchecked")
	@Override // by yucy
	protected QueryObject onFind(QueryObject query, HttpServletRequest request) throws Exception {
		query.getQueryParam().and(new QueryParam("buyer.objId",AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId()));
		return query;
	}
	
	/** 
	 * Description :  保存采购计划
	 * Create Date: 2010-11-9上午09:46:04 by yucy  Modified Date: 2010-11-9上午09:46:04 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=createOrUpdatePlan")   
	public ModelAndView createOrUpdatePlan(HttpServletRequest request ,Procurementtask plan,SessionStatus status) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   
		Map<String, Object> param = new HashMap<String, Object>();   
		
		param.put("plan", plan);
		param.put("itemStr", request.getParameter("itemStr"));
		param.put("saveType", request.getParameter("saveType"));//保存类型  save or submit

		procurementtaskService.createOrUpdatePlan(param);
		
		status.setComplete();
		
		return new ModelAndView(Constants.JSON_VIEW,model);
    }
	
	/** 
	 * Description :  打印任务书
	 * Create Date: 2010-12-13下午04:24:04 by liangxj  Modified Date: 2010-12-13下午04:24:04 by liangxj
	 * @param   taskTemp 任务书模板 objId 任务书id
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params={"method=printProtask"})
	public ModelAndView printProtask(HttpServletRequest request, String objId, String taskTemp) throws Exception
	{
	    Procurementtask procurementtask = (Procurementtask)this.procurementtaskService.get(objId);

	    Map<String,Procurementtask> templateMap = new HashMap<String,Procurementtask>();
	    templateMap.put("procurementtask", procurementtask);

	    Map<String,String> model = new HashMap<String,String>();
	    
	    //加载任务书模板
		String template = "procurementtask.ftl";
		if(taskTemp != null)
			template = taskTemp;
		   
	    model.put("content", this.freeMarkerService.getFreeMarkerContent(template, templateMap));
	    return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description : 导入采购计划 
	 * Create Date: 2011-12-9上午10:13:23 by yucy  Modified Date: 2011-12-9上午10:13:23 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params={"method=importPlan"})
	public ModelAndView importPlan(HttpServletRequest request ) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   
		if(!(request instanceof MultipartHttpServletRequest)) throw new IllegalArgumentException(((FrameMessageResource)FrameBeanFactory.getBean(FrameMessageResource.BEAN_NAME)).getMessage("srplatform.auth.attachment.file_can_not_empty"));
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		CommonsMultipartFile file=null;
		String viewName = "";
		if (multipartRequest.getFileNames().hasNext()) {
			viewName =  (String) multipartRequest.getFileNames().next();
			file = (CommonsMultipartFile)multipartRequest.getFile(viewName);//取上传文件路径
		}
		if (file == null) throw new IllegalArgumentException(((FrameMessageResource)FrameBeanFactory.getBean(FrameMessageResource.BEAN_NAME)).getMessage("srplatform.auth.attachment.file_can_not_empty"));
        String postfix=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
        try {
	        if("XML".indexOf(postfix.toUpperCase())>=0){
	        	procurementtaskService.saveImportPlanXML( new UploadFileUtil().getDataObject(request, viewName, new String[]{UploadFileResult.FILE_TYPE_XML }) );
	        }else if("XLS".indexOf(postfix.toUpperCase())>=0){
	        	procurementtaskService.saveImportPlanExcel(  new UploadFileUtil().getDataObject(request, viewName, new String[]{UploadFileResult.FILE_TYPE_EXCEL_MS })  );
	        }else if("XLSX".indexOf(postfix.toUpperCase())>=0){
	        	throw new CustomerException("目前尚未支持office excel2007版本！");
	        }else{
	        	throw new CustomerException("非法格式！");
	        }
        	model.put(Constants.JSON_RESULT,StringUtils.string2ASCII("导入成功！") );
        } catch (Exception e) {
        	model.put(Constants.JSON_RESULT, StringUtils.string2ASCII(e.getMessage() ));
		}
	    return new ModelAndView(Constants.JSON_VIEW, model);
	  }
}
