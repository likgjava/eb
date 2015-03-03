package com.gpcsoft.smallscale.pointmall.controller;

import java.util.Map;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.ModelAndView;
import com.gpcsoft.core.Constants;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.smallscale.point.service.ExchangeService;
import com.gpcsoft.smallscale.pointmall.service.GiftExchangeRuleService;
import com.gpcsoft.smallscale.pointmall.service.GiftService;
import com.gpcsoft.smallscale.pointmall.service.VirtualGiftRecordService;
import com.gpcsoft.smallscale.pointmall.domain.Gift;
import com.gpcsoft.smallscale.pointmall.domain.GiftExchangeRule;
import com.gpcsoft.smallscale.pointmall.domain.VirtualGiftRecord;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.auth.manager.RoleManager;

/**
 * @gpcsoft.view value="virtualGiftDealFormView"
 *  url="view/smallscale/pointmall/virtual_gift_deal_form.jsp"
 * @gpcsoft.view value="virtualGiftDealListView"
 *  url="view/smallscale/pointmall/virtual_gift_deal_list.jsp"
 * @gpcsoft.view value="virtualGiftFormView"
 *  url="view/smallscale/pointmall/virtual_gift_form.jsp"
 * @gpcsoft.view value="virtualGiftRecordListView"
 *  url="view/smallscale/pointmall/virtual_gift_record_list.jsp"
 *  
 */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={VirtualGiftRecord.class})
@RequestMapping("/VirtualGiftRecordController.do")//页面请求路径,可修改
public class VirtualGiftRecordController extends AnnotationMultiController<VirtualGiftRecord> {

	@Autowired(required=true) @Qualifier("virtualGiftRecordServiceImpl")
	private VirtualGiftRecordService virtualGiftRecordService;
	
	@Autowired(required=true) @Qualifier("roleManagerImpl")
	private RoleManager roleManagerImpl;
	
	@Autowired(required=true) @Qualifier("giftServiceImpl")
	private GiftService giftService;
	
	@Autowired(required=true) @Qualifier("giftExchangeRuleServiceImpl")
	private GiftExchangeRuleService giftExchangeRuleService;
	
	@Autowired(required=true) @Qualifier("exchangeServiceImpl")
	private ExchangeService exchangeService;
	
	
	/**
	 * 兑换页面
	 * @param request
	 * @param giftId
	 * @param count
	 * @param eruleId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = { "method=toExchangeView" })
	public ModelAndView toExchangeView(HttpServletRequest request,String giftId,String count, String eruleId) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		User user = AuthenticationHelper.getCurrentUser(true);
		
		
		
		Gift gift = giftService.get(giftId);
		GiftExchangeRule erule = giftExchangeRuleService.get(eruleId);
		
		//取得用户有效积分
		long hasScore = exchangeService.queryLeaveIntegral(user);
		
		model.put("gift", gift);
		model.put("erule", erule);		
		model.put("user", user);
		model.put("giftId", giftId);
		model.put("count", count);
		model.put("eruleId", eruleId);
		model.put("hasScore", hasScore);
		return new ModelAndView("virtualGiftFormView", model);
	}
	
	/**
	 * 礼品兑换保存
	 * @param request
	 * @param eruleId
	 * @param giftCount
	 * @param email
	 * @param status
	 * @return
	 */
	@RequestMapping(params = { "method=saveExchange" })
	public ModelAndView saveExchange(HttpServletRequest request,String giftId,String eruleId,Integer giftCount,String email,SessionStatus status){
		Map<String, Object> model = new HashMap<String, Object>();
		
		virtualGiftRecordService.save(giftId,eruleId, giftCount,email);
		status.setComplete();		
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/**
	 * 礼品兑换列表
	 * @param request
	 * @return
	 */
	@RequestMapping(params = { "method=toRecordList" })
	public ModelAndView toRecordList(HttpServletRequest request){
		Map<String, Object> model = new HashMap<String, Object>();

		return new ModelAndView("virtualGiftRecordListView", model);
	}
	
	/**
	 * 礼品兑换处理列表
	 * @param request
	 * @return
	 */
	@RequestMapping(params = { "method=toDealList" })
	public ModelAndView toDealList(HttpServletRequest request){
		Map<String, Object> model = new HashMap<String, Object>();

		return new ModelAndView("virtualGiftDealListView", model);
	}
	
	/**
	 * 转到礼品兑换处理/查看 页面
	 * @param request
	 * @param objId
	 * @param type
	 * @return
	 */
	@RequestMapping(params = { "method=toDealView" })
	public ModelAndView toDealView(HttpServletRequest request,String objId,String type){
		Map<String, Object> model = new HashMap<String, Object>();
		
		VirtualGiftRecord virtualGiftRecord = virtualGiftRecordService.get(objId);
		model.put("giftRecord", virtualGiftRecord);		
		model.put("type", type);

		return new ModelAndView("virtualGiftDealFormView", model);
	}
	
	/**
	 * 礼品兑换处理、收货确认保存
	 * @param request
	 * @param virtualGiftRecord
	 * @param dealStatus
	 * @param status
	 * @return
	 */
	@RequestMapping(params = { "method=saveDeal" })
	public ModelAndView saveDeal(HttpServletRequest request,VirtualGiftRecord virtualGiftRecord,String dealStatus,SessionStatus status){
		Map<String, Object> model = new HashMap<String, Object>();
		virtualGiftRecord.setDealStatus(dealStatus);
		virtualGiftRecordService.saveDeal(virtualGiftRecord);
		status.setComplete();		
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public QueryObject onFind(QueryObject query,HttpServletRequest request) throws Exception {
		User user = AuthenticationHelper.getCurrentUser(true);
		QueryParam qp = query.getQueryParam()==null?new QueryParam():query.getQueryParam();
		
		//不是管理员角色
		if(!roleManagerImpl.isHasThisRole(user.getObjId(), OrganizationEnum.ROLE_MANAGER)){
			qp.and(new QueryParam("createUser.objId","=",user.getObjId()));
		}
		
		query.setQueryParam(qp);
		return query;
	}
	
	

}
