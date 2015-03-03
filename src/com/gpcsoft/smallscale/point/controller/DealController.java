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
import com.gpcsoft.smallscale.point.domain.Deal;
import com.gpcsoft.smallscale.point.service.DealService;
import com.gpcsoft.smallscale.point.service.ExchangeService;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.auth.service.UserService;

/**
  * @gpcsoft.view value="dealFormView"
  *  url="view/smallscale/point/deal_form.jsp"
  *  
  * @gpcsoft.view value="dealTreeFormView"
  *  url="view/smallscale/point/deal_treeForm.jsp"
  *  
  * @gpcsoft.view value="dealListView"
  *  url="view/smallscale/point/deal_list.jsp"
  *  
  * @gpcsoft.view value="dealDetailView"
  *  url="view/smallscale/point/deal_letail.jsp"
  *  
  *  @gpcsoft.view value="exchangeManagerView"
  *  url="view/smallscale/point/exchange_manager.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={Deal.class})
@RequestMapping("/DealController.do")//页面请求路径,可修改
public class DealController extends AnnotationMultiController<Deal> {
	@Autowired(required=true) @Qualifier("dealServiceImpl")
	private DealService dealService;
	
	 @Autowired(required=true) @Qualifier("userServiceImpl")
	 private UserService userService;
	 
	 @Autowired(required=true) @Qualifier("exchangeServiceImpl")
	 private ExchangeService exchangeService;
	
	@Override
	@SuppressWarnings({ "unchecked", "deprecation" })
	protected QueryObject onFind(QueryObject query, HttpServletRequest request)
			throws Exception {
		User user=AuthenticationHelper.getCurrentUser(true);
		String userIdes = request.getParameter("userIdes");
		if("userIdes".equals(userIdes)){//当前用户下的交易积分
			QueryParam param = new QueryParam();
			//以下两个条件是或的关系，默认是且的关系
			param.or(new QueryParam("formUser",QueryParam.OPERATOR_EQ,user));
			param.or(new QueryParam("toUser",QueryParam.OPERATOR_EQ,user));
			
			query.getQueryParam().and(param);
		}
		return super.onFind(query, request);
	}
	
	/**
	 * 根据id查询一条交易积分的详细记录
	 * @param objId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params="method=queryDealById")
	public ModelAndView queryDealById(String objId) throws Exception{
		Map<String,Object> map=new HashMap<String,Object>();
		Deal deal=dealService.get(objId);
		map.put("deal", deal);
		return new ModelAndView("dealDetailView",map);
	}
	
	/**
	 * 跳转到交易页面中
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params="method=toDeal")
	public ModelAndView toDeal()throws Exception{
		Map<String,Object> map=new HashMap<String,Object>();
		User user=AuthenticationHelper.getCurrentUser(true);
		long nonce=exchangeService.queryLeaveIntegral(user); //查询用户的当前有效总积分
		map.put("nonce", nonce);
		return new ModelAndView("dealFormView",map);
	}

	/**
	 * 保存交易积分
	 * @param deal
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(params="method=dealSave")
	public ModelAndView dealSave(Deal deal, HttpServletRequest request,SessionStatus status) throws Exception {
		String userid=request.getParameter("userIdes");
		User user=userService.getUserByUsName(userid);
		deal.setToUser(user);  //送给那个用户积分
		deal.setFormUser(AuthenticationHelper.getCurrentUser(true));   //谁送的，当前用户
		deal.setDealDate(new Date());
		return super.save(deal, request, status);
	}
	
	
}
