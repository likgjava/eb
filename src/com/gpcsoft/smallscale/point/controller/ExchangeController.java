package com.gpcsoft.smallscale.point.controller;

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

import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.smallscale.point.domain.Exchange;
import com.gpcsoft.smallscale.point.service.ExchangeService;
import com.gpcsoft.srplatform.auth.domain.User;

/**
  * @gpcsoft.view value="exchangeFormView"
  *  url="view/smallscale/point/exchange_form.jsp"
  *  
  * @gpcsoft.view value="exchangeTreeFormView"
  *  url="view/smallscale/point/exchange_treeForm.jsp"
  *  
  * @gpcsoft.view value="exchangeListView"
  *  url="view/smallscale/point/exchange_list.jsp"
  *  
  * @gpcsoft.view value="exchangeDetailView"
  *  url="view/smallscale/point/exchange_detail.jsp"
  *  
  * @gpcsoft.view value="exchangeManagerView"
  *  url="view/smallscale/point/exchange_manager.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={Exchange.class})
@RequestMapping("/ExchangeController.do")//页面请求路径,可修改
public class ExchangeController extends AnnotationMultiController<Exchange> {

	@Autowired(required=true) @Qualifier("exchangeServiceImpl")
	private ExchangeService exchangeService;
	/**
	 * 跳转到积分管理
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params="method=manager")
	public ModelAndView manager() throws Exception{
		Map<String,Object> map=new HashMap<String,Object>();
		User user=AuthenticationHelper.getCurrentUser(true);
		long nonce=exchangeService.queryLeaveIntegral(user); //查询用户的当前有效总积分
		long history=exchangeService.queryHistoryTotalLeave(user); //查询用户的历史总积分
		map.put("history", history);
		map.put("nonce",nonce);
		return new ModelAndView("exchangeManagerView",map);
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	protected QueryObject onFind(QueryObject query, HttpServletRequest request)
			throws Exception {
		User user=AuthenticationHelper.getCurrentUser(true);
		String userIdes = request.getParameter("userIdes");
		if("userIdes".equals(userIdes)){//当前用户下的积分管理
			query.getQueryParam().and(new QueryParam("userId",QueryParam.OPERATOR_EQ,user));
		}
		return super.onFind(query, request);
	}
	
	/**
	 * 根据id查询一条积分的详细记录
	 * @param objId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params="method=queryExchangeById")
	public ModelAndView queryExchangeById(String objId) throws Exception{
		Map<String,Object> map=new HashMap<String,Object>();
		Exchange exchange=exchangeService.get(objId);
		map.put("exchange", exchange);
		return new ModelAndView("exchangeDetailView",map);
	}
}
