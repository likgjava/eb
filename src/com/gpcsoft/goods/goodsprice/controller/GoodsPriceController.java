package com.gpcsoft.goods.goodsprice.controller;

import java.math.BigDecimal;
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

import com.gpcsoft.agreement.management.service.AgreementGoodsService;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.goods.goods.domain.Goods;
import com.gpcsoft.goods.goods.enumeration.GoodsEnum;
import com.gpcsoft.goods.goods.service.GoodsService;
import com.gpcsoft.goods.goodsprice.domain.GoodsPrice;
import com.gpcsoft.goods.goodsprice.domain.GoodsPriceSupplier;
import com.gpcsoft.goods.goodsprice.service.GoodsPriceService;
import com.gpcsoft.goods.goodsprice.service.GoodsPriceSupplierService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;

/**
  * @gpcsoft.view value="toCreatePriceView"
  *  url="view/goods/goodsprice/price_goods_form.jsp"
  *  
  * @gpcsoft.view value="toAddPriceView"
  *  url="view/goods/goodsprice/goods_price_create.jsp"
  *  
  * @gpcsoft.view value="toUpdatePriceView"
  *  url="view/goods/goodsprice/goods_price_update.jsp"
  *  
  *  @gpcsoft.view value="toAddPriceXEJYView"
  *  url="view/goods/goodsprice/s_goods_price_create.jsp"
  *  
  * @gpcsoft.view value="toUpdatePriceXEJYView"
  *  url="view/goods/goodsprice/s_goods_price_update.jsp"
  *  
  * @gpcsoft.view value="toAuditPriceListView"
  *  url="view/goods/goodsprice/goods_price_aud_list.jsp"
  *  
  * @gpcsoft.view value="toAuditPriceView"
  *  url="view/goods/goodsprice/goods_price_aud_form.jsp"
  *  
  * @gpcsoft.view value="toPriceDetailView"
  *  url="view/goods/goodsprice/goods_price_detail.jsp"
  *  
  * @gpcsoft.view value="toManagePriceListView"
  *  url="view/goods/goodsprice/goods_price_manage_list.jsp"
  *  
  * @gpcsoft.view value="toManagePriceDetailView"
  *  url="view/goods/goodsprice/goods_price_manage_detail.jsp"
  *  
  *  @gpcsoft.view value="toGoodsPriceShowListView"
  *  url="view/goods/showgoods/goods_price_list.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={GoodsPrice.class})
@RequestMapping("/GoodsPriceController.do")//页面请求路径,可修改
public class GoodsPriceController extends AnnotationMultiController<GoodsPrice> {

	@Autowired(required=true) @Qualifier("goodsPriceServiceImpl")
	private GoodsPriceService goodsPriceService;
	
	@Autowired(required=true) @Qualifier("goodsServiceImpl")
	private GoodsService goodsService;
	
	@Autowired(required=true) @Qualifier("goodsPriceSupplierServiceImpl")
	private GoodsPriceSupplierService goodsPriceSupplierService;
	
	@Autowired(required=true) @Qualifier("agreementGoodsServiceImpl")
	private AgreementGoodsService agreementGoodsService;
	
	/** 
	 * Description :跳转行情页面
	 * Create Date: 2010-4-29上午10:12:13 by yucy  Modified Date: 2010-4-29上午10:12:13 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toCreatePrice")   
	public ModelAndView toCreatePrice(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   
		Map<String, Object> param = new HashMap<String, Object>();   

		Goods goods = goodsService.get(request.getParameter("goodsId"));
		model.put("goods", goods);
		
		//是否有供应商行情
		param.put("goodsId", request.getParameter("goodsId"));
		GoodsPriceSupplier goodsPriceSupplier = goodsPriceSupplierService.getgoodsPriceSupplierBySupplier(param);
		
		model.put("supplierId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId());

		if(null!=goodsPriceSupplier){
			model.put("goodsPriceSupplierId", goodsPriceSupplier.getObjId());
		}
		return new ModelAndView("toCreatePriceView", model);
    }
	
	/** 
	 * Description :跳转新增行情页面
	 * Create Date: 2010-4-29上午10:12:13 by yucy  Modified Date: 2010-4-29上午10:12:13 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toAddPrice")   
	public ModelAndView toAddPrice(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   

		Goods goods = goodsService.get(request.getParameter("goodsId"));
		GoodsPrice goodsPrice = new GoodsPrice();
		
		model.put("goodsPrice", goodsPrice);
		model.put("goods", goods);
		
		ModelAndView mv  = null;
		if("XEJY".equals(request.getParameter("appType"))){
			mv = new ModelAndView("toAddPriceXEJYView", model);
		}else {
			//取出相关协议信息
			Map<String, Object> param = new HashMap<String, Object>();   
			param.put("goodsId",request.getParameter("goodsId") );
			param.put("orgId",AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId() );
			model.put("agreementGoods", agreementGoodsService.getAgreementGoodsByParam(param));
			mv = new ModelAndView("toAddPriceView", model);
		}
		return mv;
    }
	
	/** 
	 * Description :跳转修改行情页面
	 * Create Date: 2010-4-29上午10:12:13 by yucy  Modified Date: 2010-4-29上午10:12:13 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toUpdatePrice")   
	public ModelAndView toUpdatePrice(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   
		Goods goods = goodsService.get(request.getParameter("goodsId"));
		GoodsPrice goodsPrice =  goodsPriceService.get(request.getParameter("priceId"));
		model.put("goodsPrice", goodsPrice);
		model.put("goods", goods);
		
		ModelAndView mv  = null;
		if("XEJY".equals(request.getParameter("appType"))){
			mv = new ModelAndView("toUpdatePriceXEJYView", model);
		}else {
			mv = new ModelAndView("toUpdatePriceView", model);
		}
		return mv;
    }
	
	/** 
	 * Description :   查看
	 * Create Date: 2010-4-16下午05:24:08 by yucy  Modified Date: 2010-4-16下午05:24:08 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toPriceDetail")   
	public ModelAndView toPriceDetail(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   
		GoodsPrice goodsPrice =  goodsPriceService.get(request.getParameter("priceId"));
		Goods goods = goodsService.get(request.getParameter("goodsId"));
		model.put("goods", goods);
		model.put("goodsPrice", goodsPrice);
		return new ModelAndView("toPriceDetailView", model);
    }
	
	/** 
	 * Description :  跳转审核行情列表
	 * Create Date: 2010-4-16上午10:46:25 by yucy  Modified Date: 2010-4-16上午10:46:25 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toAuditPriceList")   
	public ModelAndView toAuditPriceList(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   
		return new ModelAndView("toAuditPriceListView", model);
    }
	
	/** 
	 * Description :  跳转审核行情表单
	 * Create Date: 2010-4-16上午10:46:25 by yucy  Modified Date: 2010-4-16上午10:46:25 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toAuditPrice")   
	public ModelAndView toAuditPrice(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   
		GoodsPrice  goodsPrice = goodsPriceService.get(request.getParameter("priceId"));
		model.put("goodsPrice", goodsPrice);
		return new ModelAndView("toAuditPriceView", model);
    }
	
	/** 
	 * Description :  跳转管理行情列表
	 * Create Date: 2010-4-16上午10:46:25 by yucy  Modified Date: 2010-4-16上午10:46:25 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toManagePriceList")   
	public ModelAndView toManagePriceList(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   
		return new ModelAndView("toManagePriceListView", model);
    }
	
	/** 
	 * Description :跳转管理行情详情页面
	 * Create Date: 2010-4-29上午10:12:13 by yucy  Modified Date: 2010-4-29上午10:12:13 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toManagePriceDetail")   
	public ModelAndView toManagePriceDetail(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   
		Goods goods = goodsService.get(request.getParameter("goodsId"));
		model.put("goods", goods);
		model.put("supplierId", request.getParameter("supplierId"));
		return new ModelAndView("toManagePriceDetailView", model);
    }

	
	/** 
	 * Description :  审核
	 * Create Date: 2010-4-16下午02:25:16 by yucy  Modified Date: 2010-4-16下午02:25:16 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=updateAuditStatus")   
	public ModelAndView updateAuditStatus(HttpServletRequest request,GoodsPrice goodPrice) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   
		Map<String, Object> param = new HashMap<String, Object>();   
		
		param.put("auditType", request.getParameter("auditType"));
		param.put("goodsPrice", goodPrice);
		
		Boolean  result = goodsPriceService.updateAuditStatus(param);
		if(result){
			model.put(Constants.SUCCESS, true);
		}else {
			model.put(Constants.FAILURE, true);
		}
		return new ModelAndView(Constants.JSON_VIEW, model);
    }
	
	/** 
	 * Description :  保存行情
	 * Create Date: 2010-4-15下午01:27:15 by yucy  Modified Date: 2010-4-15下午01:27:15 by yucy
	 * @param   
	 * @return  
	 * @throws Exception 
	 * @Exception   
	 */
	@RequestMapping(params="method=savePrice")
	public ModelAndView savePrice(HttpServletRequest request,GoodsPrice goodsPrice ,SessionStatus status) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		
		param.put("goodsPrice", goodsPrice);
		param.put("OptFitPriceJson", request.getParameter("OptFitPriceJson"));
		param.put("goodsId", request.getParameter("goodsId"));
		param.put("opType", request.getParameter("opType"));

		Boolean relsut = goodsPriceService.savePrice(param);
		if(relsut){
			model.put(Constants.SUCCESS, true);
		}else{
			model.put(Constants.FAILURE, true);
		}
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW,param);
	}
	
	/** 
	 * Description :  保存会员价
	 * Create Date: 2010-4-15下午01:27:15 by yucy  Modified Date: 2010-4-15下午01:27:15 by yucy
	 * @param   
	 * @return  
	 * @throws Exception 
	 * @Exception   
	 */
	@RequestMapping(params="method=saveMemberPrice")
	public ModelAndView saveMemberPrice(HttpServletRequest request,String goodsPriceId,
			BigDecimal memberPrice,SessionStatus status) throws Exception {
		
		GoodsPrice goodsPrice = goodsPriceService.get(goodsPriceId);
		goodsPrice.setMemberPrice(memberPrice);
		
		//删除会员价
		if(memberPrice.intValue() == 0) {
			goodsPrice.setMemberPrice(null);
		}
		goodsPriceService.save(goodsPrice);
		
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/** 
	 * Description :  禁用行情
	 * Create Date: 2010-4-16下午02:25:16 by yucy  Modified Date: 2010-4-16下午02:25:16 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=updateStopStatus")   
	public ModelAndView updateStopStatus(HttpServletRequest request,GoodsPrice goodPrice) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   
		Map<String, Object> param = new HashMap<String, Object>();   
		param.put("priceId", request.getParameter("priceId"));
		Boolean  result = goodsPriceService.updateStopStatus(param);
		if(result){
			model.put(Constants.SUCCESS, true);
		}else {
			model.put(Constants.FAILURE, true);
		}
		return new ModelAndView(Constants.JSON_VIEW, model);
    }
	
	
	
	/** 
	 * Description :  删除行情
	 * Create Date: 2010-4-17上午09:55:03 by yucy  Modified Date: 2010-4-17上午09:55:03 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=deletePrice")   
	public ModelAndView deletePrice(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();   
		Map<String, Object> param = new HashMap<String, Object>();   
		param.put("priceIds", request.getParameter("priceIds"));
		Boolean  result = goodsPriceService.deletePrice(param);
		if(result){
			model.put(Constants.SUCCESS, true);
		}else {
			model.put(Constants.FAILURE, true);
		}
		return new ModelAndView(Constants.JSON_VIEW, model);
    }
	
	/** 
	 * Description :  根据商品获得提供行情的供应商
	 * Create Date: 2010-10-27下午05:17:37 by liangxj  Modified Date: 2010-10-27下午05:17:37 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getProvideSupplierByGoods")   
    public ModelAndView getProvideSupplierByGoods(HttpServletRequest request, String goodsIds) throws Exception{
    	Map<String,Object> model = new HashMap<String,Object>();
    	goodsIds = request.getParameter("goodsIds");
    	model.put("result", goodsPriceService.getProvideSupplierByGoods(goodsIds));
    	return new ModelAndView("jsonView", model);
    }
	
	/** 
	 * Description :  行情参数
	 * Create Date: 2010-4-17上午09:55:03 by yucy  Modified Date: 2010-4-17上午09:55:03 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected QueryObject onFind(QueryObject query, HttpServletRequest request) throws Exception {
		if(GoodsEnum.USE_VALID.equals(request.getParameter("useStatus"))){
			//query.getQueryParam().and(new QueryParam("efctDate","<=",new Date())); 
			//query.getQueryParam().and(new QueryParam("endDate",">=",new Date()));
		}
		if(GoodsEnum.USE_INVALID.equals(request.getParameter("useStatus"))){
			query.getQueryParam().removeAndParams( new QueryParam( "useStatus",GoodsEnum.USE_INVALID) );
			
			QueryParam or = new QueryParam();
			or.or(new QueryParam( "useStatus",GoodsEnum.USE_INVALID));
			//or.or(new QueryParam("efctDate",">",new Date()));
			//or.or(new QueryParam("endDate","<",new Date()));
			query.getQueryParam().and(or);
		}
		return query;
	}
}
