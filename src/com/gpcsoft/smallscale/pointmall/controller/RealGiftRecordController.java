package com.gpcsoft.smallscale.pointmall.controller;


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

import com.gpcsoft.core.Constants;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.smallscale.point.service.ExchangeService;
import com.gpcsoft.smallscale.pointmall.domain.Gift;
import com.gpcsoft.smallscale.pointmall.domain.GiftExchangeRule;
import com.gpcsoft.smallscale.pointmall.domain.RealGiftRecord;
import com.gpcsoft.smallscale.pointmall.service.GiftExchangeRuleService;
import com.gpcsoft.smallscale.pointmall.service.GiftService;
import com.gpcsoft.smallscale.pointmall.service.RealGiftRecordService;
import com.gpcsoft.srplatform.auth.domain.User;


/**
  * @gpcsoft.view value="realGiftFormView"
  *  url="view/smallscale/pointmall/real_gift_form.jsp"
  * @gpcsoft.view value="realGiftRecordListView"
  *  url="view/smallscale/pointmall/real_gift_record_list.jsp"
  * @gpcsoft.view value="realGiftDealListView"
  *  url="view/smallscale/pointmall/real_gift_deal_list.jsp"
  * @gpcsoft.view value="realGiftRecordDealView"
  *  url="view/smallscale/pointmall/real_deal_form.jsp"
  *  
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={RealGiftRecord.class})
@RequestMapping("/RealGiftRecordController.do")//页面请求路径,可修改
public class RealGiftRecordController extends AnnotationMultiController<RealGiftRecord> {

	@Autowired(required=true) @Qualifier("realGiftRecordServiceImpl")
	private RealGiftRecordService realGiftRecordService;
	
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
		return new ModelAndView("realGiftFormView", model);
	}
	
	
	/**
	 * 兑换提交
	 * @param request
	 * @param realGiftRecord
	 * @param giftId
	 * @param eruleId
	 * @param status
	 * @return
	 */
	@RequestMapping(params = { "method=saveExchange" })
	public ModelAndView saveExchange(HttpServletRequest request,RealGiftRecord realGiftRecord,String giftId,String eruleId, SessionStatus status)  {
		Map<String, Object> model = new HashMap<String, Object>();			
		
		realGiftRecordService.saveRecord(realGiftRecord,giftId,eruleId);
		
		status.setComplete();		
		
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/**
	 * 待处理兑换列表
	 * @param request
	 * @param realGiftRecord
	 * @param giftId
	 * @param eruleId
	 * @param status
	 * @return
	 */
	@RequestMapping(params = { "method=toDealList" })
	public ModelAndView toDealList(HttpServletRequest request, SessionStatus status)  {
		Map<String, Object> model = new HashMap<String, Object>();

		return new ModelAndView("realGiftDealListView", model);
	}
	
	
	/**
	 * 用户兑换列表
	 * @param request
	 * @param realGiftRecord
	 * @param giftId
	 * @param eruleId
	 * @param status
	 * @return
	 */
	@RequestMapping(params = { "method=toExchangeList" })
	public ModelAndView toExchangeList(HttpServletRequest request)  {
		Map<String, Object> model = new HashMap<String, Object>();
		
		return new ModelAndView("realGiftRecordListView", model);
	}
	
	
	/**
	 * 处理页面
	 * @param request
	 * @param giftId
	 * @param count
	 * @param eruleId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = { "method=toDealView" })
	public ModelAndView toDealView(HttpServletRequest request,String objId,String type) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
	
		
		RealGiftRecord giftRecord = realGiftRecordService.get(objId);
		
//		if(giftRecord.getDealStatus().equals(SmallscaleEnum.DEAL_STATUS_OUTSTOCK)){//缺货,设置兑换数量为0
//			giftRecord.setGiftCount(0);
//		}
		
		model.put("giftRecord", giftRecord);
		
		
		model.put("type", type);
		
		return new ModelAndView("realGiftRecordDealView", model);
	}
	
	
	/**
	 * 处理兑换保存
	 * @param request
	 * @param realGiftRecord
	 * @param status
	 * @return
	 */
	@RequestMapping(params = { "method=dealExchange" })
	public ModelAndView dealExchange(HttpServletRequest request,RealGiftRecord realGiftRecord,String dealStatus,SessionStatus status)  {
		Map<String, Object> model = new HashMap<String, Object>();		
		
		realGiftRecord.setDealStatus(dealStatus);
		realGiftRecordService.saveDealRecord(realGiftRecord);
		
		status.setComplete();
	
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public QueryObject onFind(QueryObject query,HttpServletRequest request) throws Exception {
		User user = AuthenticationHelper.getCurrentUser(true);
			QueryParam qp = query.getQueryParam()==null?new QueryParam():query.getQueryParam();
			
			String isAdmin = request.getParameter("isAdmin");

			if(null != isAdmin && isAdmin.equals("true")){//礼品供应商
				request.setAttribute("isAdmin", isAdmin);
				qp.and(new QueryParam("gift.giftSupplier.supplierId","=",user.getOrgInfo().getObjId()));
			}
			else{
				qp.and(new QueryParam("createUser.objId","=",user.getObjId()));
			}
			
			query.setQueryParam(qp);
		return query;
	}
	
//	@Override
//	protected Map<String, String> getEnumColumns() {
//		Map<String, String> enumMap = new HashMap<String, String>();
//		enumMap.put("fpostType", "com.gpcsoft.smallscale.pointmall.domain.Rule.fpostType");// 第一个参数是属性，第二参数是枚举类中定义名
//
//
//		return enumMap;
//	}

}
