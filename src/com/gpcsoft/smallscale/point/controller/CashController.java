package com.gpcsoft.smallscale.point.controller;

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

import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.smallscale.point.domain.Cash;
import com.gpcsoft.smallscale.point.service.CashService;
import com.gpcsoft.smallscale.point.service.ExchangeService;
import com.gpcsoft.srplatform.auth.domain.User;

/**
  * @gpcsoft.view value="cashFormView"
  *  url="view/smallscale/point/cash_form.jsp"
  *  
  * @gpcsoft.view value="cashTreeFormView"
  *  url="view/smallscale/point/cash_treeForm.jsp"
  *  
  * @gpcsoft.view value="cashListView"
  *  url="view/smallscale/point/cash_list.jsp"
  *  
  * @gpcsoft.view value="cashDetailView"
  *  url="view/smallscale/point/cash_detail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={Cash.class})
@RequestMapping("/CashController.do")//页面请求路径,可修改
public class CashController extends AnnotationMultiController<Cash> {

	@Autowired(required=true) @Qualifier("cashServiceImpl")
	private CashService cashService;

	@Autowired(required=true) @Qualifier("exchangeServiceImpl")
	private ExchangeService exchangeService;
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	protected QueryObject onFind(QueryObject query, HttpServletRequest request)
			throws Exception {
		User user=AuthenticationHelper.getCurrentUser(true);
		String userIdes = request.getParameter("userIdes");
		if("userIdes".equals(userIdes)){//当前用户下的兑现积分
			query.getQueryParam().and(new QueryParam("userId",QueryParam.OPERATOR_EQ,user));
		}
		return super.onFind(query, request);
	}
	
	/**
	 * 根据id查询一条兑现积分的详细记录
	 * @param objId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params="method=queryCashById")
	public ModelAndView queryCashById(String objId) throws Exception{
		Map<String,Object> map=new HashMap<String,Object>();
		Cash cash=cashService.get(objId);
	    map.put("cash", cash);
		return new ModelAndView("cashDetailView",map);
	}
	
	/**
	 * 跳转到兑现页面中
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params="method=toCash")
	public ModelAndView toCash()throws Exception{
		Map<String,Object> map=new HashMap<String,Object>();
		User user=AuthenticationHelper.getCurrentUser(true);
		long nonce=exchangeService.queryLeaveIntegral(user); //查询用户的当前有效总积分
		map.put("nonce", nonce);
		return new ModelAndView("cashFormView",map);
	}
	
	/**
	 * 保存兑现积分
	 * @param deal
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params="method=cashSave")
	public ModelAndView cashSave(Cash cash, HttpServletRequest request,SessionStatus status) throws Exception {
		cash.setCashDate(new Date());
		cash.setUserId(AuthenticationHelper.getCurrentUser(true));
		
		/**
		 * 积分兑现的钱的处理-------空，到时候再做   BigDecimal money=cash.getCashMoney();
		 */
		
		return super.save(cash, request, status);
	}
}
