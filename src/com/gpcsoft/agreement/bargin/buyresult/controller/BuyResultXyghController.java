package com.gpcsoft.agreement.bargin.buyresult.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.agreement.bargin.bidding.domain.BiddingRecord;
import com.gpcsoft.agreement.bargin.bidding.domain.BiddingRecordDetail;
import com.gpcsoft.agreement.bargin.bidding.service.BiddingRecordDetailService;
import com.gpcsoft.agreement.bargin.bidding.service.BiddingRecordService;
import com.gpcsoft.agreement.bargin.buyresult.service.BuyResultServiceXygh;
import com.gpcsoft.agreement.bargin.require.domain.RequireItem;
import com.gpcsoft.agreement.bargin.require.service.RequireItemService;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.common.domain.EbuyMethodEnum;
import com.gpcsoft.epp.project.domain.Project;
import com.gpcsoft.epp.project.service.ProjectService;
import com.gpcsoft.epp.projrule.domain.ProjProcessRule;
import com.gpcsoft.epp.projrule.service.ProjProcessRuleService;
import com.gpcsoft.epp.signuprecord.domain.SignUprecord;
import com.gpcsoft.epp.signuprecord.service.SignUprecordService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.User;

/**
  *  @gpcsoft.view value="toConfirmResultBySupplierView"
  *  url="view/agreement/bargin/buyresult/a_confrim_result_bysupplier.jsp"
  *  
  *  @gpcsoft.view value="confirmResultView"
  *  url="view/agreement/bargin/buyresult/confrim_result_view.jsp"
  *  
  *  @gpcsoft.view value="confirmDetailView"
  *  url="view/agreement/bargin/buyresult/confrim_result_detail_view.jsp" 
  *  
  *  @gpcsoft.view value="toConfirmResultBySupplierBGNSView"
  *  url="view/agreement/bargin/buyresult/a_confrim_result_bysupplier_bgns.jsp"
  *  
  *  @gpcsoft.view value="toConfirmResultByGoodsView"
  *  url="view/agreement/bargin/buyresult/a_confrim_result_bygoods.jsp"
  *  
  *  @gpcsoft.view value="toResultByTurnView"
  *  url="view/agreement/bargin/buyresult/a_view_result_byturn.jsp"
  *  
  *  @gpcsoft.view value="toResultDetailView"
  *  url="view/agreement/bargin/buyresult/a_buy_result_detail.jsp"
  *  
  *  @gpcsoft.view value="toRecordItemByRecordView"
  *  url="view/agreement/bargin/buyresult/a_record_item_detail.jsp"
  *  
  *  @gpcsoft.view value="toChooseGoodsToOrderView"
  *  url="view/agreement/bargin/buyresult/a_record_readyfor_order.jsp"
  *  
  *  @gpcsoft.view value="toChooseGoodsToOrderViewTalkProject"
  *  url="view/agreement/bargin/buyresult/a_record_readyfor_order_talk_project.jsp"
  *  
  */

@SuppressWarnings("unchecked")
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={SignUprecord.class})
@RequestMapping("/BuyResultXyghController.do")//页面请求路径,可修改
public class BuyResultXyghController extends AnnotationMultiController {
	
	@Autowired(required=true) @Qualifier("buyResultServiceXyghImpl")
	private BuyResultServiceXygh buyResultServiceXygh;
	
	@Autowired(required=true) @Qualifier("projProcessRuleServiceImpl")
	private ProjProcessRuleService projProcessRuleServiceImpl;
	
	@Autowired(required=true) @Qualifier("biddingRecordServiceImpl")
	private BiddingRecordService biddingRecordService;
	
	@Autowired(required=true) @Qualifier("requireItemServiceImpl")
	private RequireItemService requireItemService;
	
	@Autowired(required=true) @Qualifier("projectServiceImpl")
	private ProjectService projectServiceImpl;
	
	@Autowired(required=true) @Qualifier("signUprecordServiceImpl")
	private SignUprecordService signUprecordService;
	
	@Autowired(required=true) @Qualifier("biddingRecordDetailServiceImpl")
	private BiddingRecordDetailService biddingRecordDetailServiceImpl;
	
	/** 
	 * Description :  跳转到确认结果页面
	 * Create Date: 2011-5-31下午03:37:28 by yucy  Modified Date: 2011-5-31下午03:37:28 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=toConfirmResultView")
	public ModelAndView toConfirmResultView(String projectId, HttpServletRequest request) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		
		//项目需求信息
		model.put("requireItemList", requireItemService.getRequireItemsByProjId(projectId));
		
		//获取最低报价
		model.put("biddingRecordObjectList",biddingRecordDetailServiceImpl.getMinRecordAndDetail(projectId));
		
		return new ModelAndView("confirmResultView",model);
	}
	
	/** 
	 * Description :  跳转到确认条目页面
	 * Create Date: 2011-6-1上午11:22:08 by yucy  Modified Date: 2011-6-1上午11:22:08 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=toConfirmDetailView")
	public ModelAndView toConfirmDetailView(String projectId, String dealSupplier, String lostSupplier,  HttpServletRequest request) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		
		//List<Object[]> biddingRecordObjectList = biddingRecordDetailServiceImpl.getMinRecord(projectId);
		
		
		
		
		List<Object[]> biddingRecordObjectList = biddingRecordDetailServiceImpl.getMinOrSelectRecordDetail(projectId,dealSupplier);
		
		
		//项目需求信息
		List<RequireItem> requireItemList =  requireItemService.getRequireItemsByProjId(projectId);
		model.put("requireItemList", requireItemList);
		
		model.put("biddingRecordObjectList", biddingRecordObjectList);

		return new ModelAndView("confirmDetailView",model);
	}

	/** 
	 * Description :  跳转到确认成交结果页面(供应商纬度)
	 * Create Date: 2010-10-14上午10:38:07 by yucy  Modified Date: 2010-10-14上午10:38:07 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toConfirmResultBySupplierView")
	public ModelAndView toConfirmResultBySupplierView(HttpServletRequest request) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		
		//获取项目 信息
		Project project = projectServiceImpl.get(request.getParameter("projectId"));
		model.put("projectHall_Supplier", project);

		//项目规则信息
		List<ProjProcessRule> projruleList = projProcessRuleServiceImpl.getProjProcessRuleByProjectId( request.getParameter("projectId"));
		model.put("projruleList", projruleList);
		
		//是否可单个报价
		model.put("singlePrice", isSinglePrice(projruleList));
		
		//项目最高报价、最低报价、平均报价和报价供应商
		List<Object> maxMinAvgPrice = biddingRecordService.getMaxMinAvgPriceByProjId(project.getObjId());
		float maxPrice = 0;
		float minPrice = 0;
		float avgPrice = 0;
		String maxSupplier = "";
		String minSupplier = "";
		
		if(maxMinAvgPrice != null && maxMinAvgPrice.size() != 0) {
			Object[] p = (Object[])maxMinAvgPrice.get(0);
			if(p != null && p.length != 0) {
				maxPrice = ((BigDecimal)p[0]).floatValue();
				minPrice = ((BigDecimal)p[1]).floatValue();
				avgPrice = ((BigDecimal)p[2]).floatValue();
				maxSupplier = ((String)p[3]).toString();
				minSupplier = ((String)p[4]).toString();
			}
		}
		
		model.put("maxPrice", maxPrice==0?"":maxPrice);
		model.put("minPrice", minPrice==0?"":minPrice);
		model.put("avgPrice", avgPrice==0?"":avgPrice);
		model.put("maxPrice_Supplier", maxSupplier);
		model.put("minPrice_Supplier", minSupplier);
		
		
		//该项目需求条目是否有商品
		List<RequireItem> requireItemList =  requireItemService.getRequireItemsByProjId(request.getParameter("projectId"));
		String hasGoods = requireItemList.get(0).getGoods()==null?"0":"1";
		model.put("hasGoods", hasGoods );
		
		//取竞价/议价记录
		List<BiddingRecord> biddingRecordList = null;
		if(EbuyMethodEnum.COMPETITION.equals(project.getEbuyMethod())){
			 biddingRecordList =  biddingRecordService.getMinBiddingRecordByProjectId(request.getParameter("projectId"),null);
		}else if(EbuyMethodEnum.TALK.equals(project.getEbuyMethod())){
			 biddingRecordList =  biddingRecordService.getMinBiddingRecordByTalkProjectId(request.getParameter("projectId"),null);
		}
		
		model.put("biddingRecordList", biddingRecordList);
		ModelAndView mv = null;
		if("0".equals(isSinglePrice(projruleList))&&"1".equals(hasGoods)){
			model.put("requireItemList", requireItemList);
			mv = new ModelAndView("toConfirmResultBySupplierBGNSView",model);
		}else{
			mv = new ModelAndView("toConfirmResultBySupplierView",model);
		}
		return mv;
	}
	
	/** 
	 * Description :  跳转到结果以生成订单
	 * Create Date: 2010-10-22上午10:45:11 by yucy  Modified Date: 2010-10-22上午10:45:11 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toChooseGoodsToOrderView")
	public ModelAndView toChooseGoodsToOrderView(HttpServletRequest request) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("supplierId",request.getParameter("supplierId"));
		param.put("projectId",request.getParameter("projectId"));

		//BiddingRecord biddingRecord =   biddingRecordService.getMinBiddingRecordByProjectAndSupplier(param);
		
		List<BiddingRecordDetail> dealDetailList =  biddingRecordDetailServiceImpl.getDealRecordDetail(param);
		
		
		//model.put("biddingRecord", biddingRecord);
		
		model.put("dealDetailList", dealDetailList);

		return new ModelAndView("toChooseGoodsToOrderView",model);
	}
	
	/** 
	 * Description :  跳转到结果以生成订单    这个方法只有议价在用（老的报价记录）
	 * Create Date: 2010-10-22上午10:45:11 by yucy  Modified Date: 2010-10-22上午10:45:11 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toChooseGoodsToOrderViewTalkProject")
	public ModelAndView toChooseGoodsToOrderViewTalkProject(HttpServletRequest request) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("supplierId",request.getParameter("supplierId"));
		param.put("projectId",request.getParameter("projectId"));
		BiddingRecord biddingRecord =   biddingRecordService.getMinBiddingRecordByProjectAndSupplier(param);
		model.put("biddingRecord", biddingRecord);
		return new ModelAndView("toChooseGoodsToOrderViewTalkProject",model);
	}
	
	
	/**
	 * 判断是否可单个报价
	 * Description :  
	 * Create Date: 2010-10-21下午01:48:20 by sunl  Modified Date: 2010-10-21下午01:48:20 by sunl
	 * @param   
	 * @return  
	 * @Exception
	 */
	public String isSinglePrice(List<ProjProcessRule> projruleList) throws Exception {
		String singlePrice = "0";
		for (ProjProcessRule projProcessRule : projruleList) {
			//可单个报价
			if(projProcessRule.getCode().equals("singlePrice") &&projProcessRule.getResValue()!=null&& projProcessRule.getResValue().equals("1")) {
				singlePrice = "1";
			}
		}
		return singlePrice;
	}
	
	/** 
	 * Description :  跳转到供应商轮次报价详情
	 * Create Date: 2010-10-14下午03:17:43 by yucy  Modified Date: 2010-10-14下午03:17:43 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toResultByTurnView")
	public ModelAndView toResultByTurnView(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("projectId", request.getParameter("projectId"));
		param.put("supplierId", request.getParameter("supplierId"));
		param.put("turnId", request.getParameter("turnId"));
		
		Map<String, Object> supplierBargainByTurnMap =  buyResultServiceXygh.getSupplierBargainByTurn(param);
		model.put("tabResult", supplierBargainByTurnMap.get("tabResult"));
		model.put("firstResult", supplierBargainByTurnMap.get("firstResult"));
		
		OrgInfo orgInfo = new OrgInfo();
		orgInfo.setObjId(request.getParameter("supplierId"));
		User user  = new User();
		user.setOrgInfo(orgInfo);
		SignUprecord signUprecord = signUprecordService.getSignUprecordBySupplierId(request.getParameter("projectId"),  user);
		model.put("signUprecord", signUprecord);
		
		return new ModelAndView("toResultByTurnView",model);
	}
	
	/**
	 * Description :  取得单轮的数据
	 * Create Date: 2010-10-18上午10:48:54 by yucy  Modified Date: 2010-10-18上午10:48:54 by yucy
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params = "method=getSingleTurnRecordDate")
	public ModelAndView getSingleTurnRecordDate(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		
		param.put("turnId", request.getParameter("turnId"));
		param.put("projectId", request.getParameter("projectId"));
		param.put("supplierId", request.getParameter("supplierId"));
		
		List<Object[]> list =  buyResultServiceXygh.getSingleTurnRecordDate(param);
		model.put("list", list);
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	/** 
	 * Description :  跳转到供应商报价详情含商品信息
	 * Create Date: 2010-10-14下午03:17:43 by yucy  Modified Date: 2010-10-14下午03:17:43 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toRecordDetailView")
	public ModelAndView toRecordDetailView(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("recordId", request.getParameter("recordId"));
		return new ModelAndView("toRecordDetailView",model);
	}
	
	/** 
	 * Description :  跳转到查看成交结果
	 * Create Date: 2010-10-15下午02:11:51 by yucy  Modified Date: 2010-10-15下午02:11:51 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toResultDetailView")
	public ModelAndView toResultDetailView(HttpServletRequest request) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		
		//查看角色
		param.put("userType", request.getParameter("userType")) ;
		

		//取得查看成交结果数据
		param.put("projectId", request.getParameter("projectId"));
		Map<String, Object> buyResultData =  buyResultServiceXygh.getBuyResultData(param) ;
		
		model.put("buyResult", buyResultData.get("buyResult"));
		model.put("buyWinnerList", buyResultData.get("buyWinnerList"));
		
		//是否可单个报价
		List<ProjProcessRule> projruleList = projProcessRuleServiceImpl.getProjProcessRuleByProjectId( request.getParameter("projectId"));
		model.put("singlePrice", isSinglePrice(projruleList));
		
		//取订单数据
		if("supplier".equals(request.getParameter("userType"))){
			//加供应商过滤
			model.put("supplierId",AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId());
		}
		
		return new ModelAndView("toResultDetailView",model);
	}
	
	/**
	 * Description :  跳转到议价记录明细
	 * Create Date: 2010-10-18上午11:17:42 by yucy  Modified Date: 2010-10-18上午11:17:42 by yucy
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params = "method=toRecordItemByRecordView")
	public ModelAndView toRecordItemByRecordView(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("recordId", request.getParameter("recordId"));
		model.put("record", biddingRecordService.get(request.getParameter("recordId")));
		model.put("recordItemList", buyResultServiceXygh.getRecordItemsByRecord(param));
		return new ModelAndView("toRecordItemByRecordView",model);
	}
	
	/** 
	 * Description :  保存成交结果
	 * Create Date: 2010-10-14下午04:45:11 by yucy  Modified Date: 2010-10-14下午04:45:11 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveBuyResult")
	public ModelAndView saveBuyResult(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		
		param.put("projectId",request.getParameter("projectId"));
		param.put("supplierPrice",request.getParameter("supplierPrice"));
		param.put("confirmType", request.getParameter("confirmType"));
		param.put("opinion", request.getParameter("opinion"));
		
		param.put("lostSupplier", request.getParameter("lostSupplier"));
		param.put("dealSupplier", request.getParameter("dealSupplier"));
		
		Boolean result = buyResultServiceXygh.saveBuyResult(param);
		
		if(result){
			model.put(Constants.SUCCESS, true);
		}else{
			model.put(Constants.FAILURE, true);
		}
		
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	/** 
	 * Description :  保存成交结果和中标条目
	 * Create Date: 2011-6-13下午03:03:28 by yucy  Modified Date: 2011-6-13下午03:03:28 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveBuyResultAndResultItem")
	public ModelAndView saveBuyResultAndResultItem(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		
		param.put("projectId",request.getParameter("projectId"));
		param.put("supplierPrice",request.getParameter("supplierPrice"));
		param.put("confirmType", request.getParameter("confirmType"));
		param.put("opinion", request.getParameter("opinion"));
		
		
		
		param.put("lostSupplier", request.getParameter("lostSupplier"));
		
		param.put("dealSupplier", request.getParameter("dealSupplier"));
		
		param.put("detailIds",request.getParameter("detailIds"));

		param.put("opinion",request.getParameter("opinion"));
		
		
		Boolean result = buyResultServiceXygh.saveBuyResult(param);
		
		if(result){
			model.put(Constants.SUCCESS, true);
		}else{
			model.put(Constants.FAILURE, true);
		}
		
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	
	
}
