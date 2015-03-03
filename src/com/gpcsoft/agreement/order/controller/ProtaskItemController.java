package com.gpcsoft.agreement.order.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.agreement.order.domain.Procurementtask;
import com.gpcsoft.agreement.order.domain.ProtaskItem;
import com.gpcsoft.agreement.order.service.ProcurementtaskService;
import com.gpcsoft.agreement.order.service.ProtaskItemService;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;

/**
  * @gpcsoft.view value="protaskItemFormView"
  *  url="view/agreement/order/protaskItemForm.jsp"
  * @gpcsoft.view value="protaskItemTreeFormView"
  *  url="view/agreement/order/protaskItemTreeForm.jsp"
  * @gpcsoft.view value="protaskItemListView"
  *  url="view/agreement/order/protaskItemList.jsp"
  * @gpcsoft.view value="protaskItemDetailView"
  *  url="view/agreement/order/protaskItemDetail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={ProtaskItem.class})
@RequestMapping("/ProtaskItemController.do")//页面请求路径,可修改
public class ProtaskItemController extends AnnotationMultiController<ProtaskItem> {

	@Autowired(required=true) @Qualifier("protaskItemServiceImpl")
	private ProtaskItemService protaskItemService;
	
	@Autowired(required=true) @Qualifier("procurementtaskServiceImpl")
	private ProcurementtaskService procurementtaskService;

	
	/** 
	 * Description :  将变量放进session中
	 * Create Date: 2010-5-7下午03:16:21 by liangxj  Modified Date: 2010-5-7下午03:16:21 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=setIdInSession")
	public ModelAndView setIdInSession(String objId,HttpSession session) throws Exception {
        session.setAttribute("protaskItemObjId", objId);
		Map<String, Object> model = new HashMap<String, Object>();
		return new ModelAndView(Constants.JSON_VIEW,model);
    }
	
	/** 
	 * Description :  删除
	 * Create Date: 2010-11-9下午10:45:03 by yucy  Modified Date: 2010-11-9下午10:45:03 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=removePlan")
	public ModelAndView removePlan(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		protaskItemService.remove(request.getParameter("itemId"),ProtaskItem.class );
		
		Procurementtask procurementtask =procurementtaskService.get(request.getParameter("planId"));
		procurementtask.setSumQty(new BigDecimal(request.getParameter("sumQty")));
		procurementtask.setSumTotal(new BigDecimal(request.getParameter("sumTotal")));
		procurementtaskService.save(procurementtask);
		
		return new ModelAndView(Constants.JSON_VIEW,model);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected QueryObject onFind(QueryObject query, HttpServletRequest request)throws Exception {
		//挑选任务书
		if("true".equals(request.getParameter("tochoose"))){
			query.getQueryParam().and(new QueryParam("procurementtask.buyer.objId",AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId()));
		}
		return query;
	}
}
