package com.gpcsoft.agreement.bargin.bidding.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.agreement.bargin.bidding.domain.BiddingRecordDetail;
import com.gpcsoft.agreement.bargin.bidding.service.BiddingRecordDetailService;
import com.gpcsoft.agreement.bargin.project.domain.BargainTurn;
import com.gpcsoft.agreement.bargin.project.service.BargainTurnService;
import com.gpcsoft.agreement.bargin.require.service.RequireItemService;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.utils.JsonUtils;
import com.gpcsoft.core.utils.StringUtils;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.srplatform.auth.domain.User;

/**
 * 
 *  @gpcsoft.view value="loadRecordByRequireView"
 *  url="view/agreement/bargin/project/project_record_by_require.jsp"
 *  
 *  @gpcsoft.view value="loadRecordByRequireForLoadView"
 *  url="view/agreement/bargin/project/project_record_by_require_div.jsp"
 *  
 *  @gpcsoft.view value="recordDetailBySupplierAndRequireView"
 *  url="view/agreement/bargin/project/project_record_by_require_supplier.jsp"
 *  
 *  @gpcsoft.view value="biddingRecordDetailListView"
 *  url="view/agreement/bargin/project/biddingrecord/bidding_record_list.jsp"
 */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={BiddingRecordDetail.class})
@RequestMapping("/BiddingRecordDetailController.do")//页面请求路径,可修改
public class BiddingRecordDetailController extends AnnotationMultiController<BiddingRecordDetail> {

	@Autowired(required=true) @Qualifier("biddingRecordDetailServiceImpl")
	private BiddingRecordDetailService biddingRecordDetailService;
	
	@Autowired(required=true) @Qualifier("requireItemServiceImpl")
	private RequireItemService requireItemServiceImpl;
	
	@Autowired(required=true) @Qualifier("bargainTurnServiceImpl")
	private BargainTurnService bargainTurnService;
	
	/** 
	 * Description :  根据需求获得供应商报价页面
	 * Create Date: 2010-10-26下午11:53:04 by yucy  Modified Date: 2010-10-26下午11:53:04 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=loadRecordByRequire")   
	ModelAndView loadRecordByRequire (String requireId ,HttpServletRequest request) throws Exception{
		Map<String ,Object> model = new HashMap<String , Object>();
		
		Map<String ,Object> param = new HashMap<String , Object>();

		model.put("requireItem", requireItemServiceImpl.get(requireId));
		model.put("biddingRecordObjectList", biddingRecordDetailService.loadRecordByRequire(requireId , param));
		model.put("currentOrgId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId());
		return new ModelAndView ("loadRecordByRequireView", model);
	}
	
	/** 
	 * Description :  根据需求获得供应商报价排序(DivForLoad)
	 * Create Date: 2010-10-26下午11:53:04 by yucy  Modified Date: 2010-10-26下午11:53:04 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=loadRecordByRequireForLoad")   
	ModelAndView loadRecordByRequireForLoad (String requireId ,HttpServletRequest request) throws Exception{
		Map<String ,Object> model = new HashMap<String , Object>();
		
		Map<String ,Object> param = new HashMap<String , Object>();

		//排序字段
    	String sort = request.getParameter("order");
    	if(StringUtils.hasLength(sort)) {
    		param.put("order", sort.replace("__", " "));
    	}
		
		model.put("requireItem", requireItemServiceImpl.get(requireId));
		model.put("biddingRecordObjectList", biddingRecordDetailService.loadRecordByRequire(requireId ,param ));
		return new ModelAndView ("loadRecordByRequireForLoadView", model);
	}
	
	/** 
	 * Description :  跳转到报价详情
	 * Create Date: 2011-5-20下午01:34:58 by yucy  Modified Date: 2011-5-20下午01:34:58 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=toRecordDetailBySupplierAndRequire")
	ModelAndView toRecordDetailBySupplierAndRequire(HttpServletRequest request) throws Exception{
		Map<String ,Object> model = new HashMap<String , Object>();
		//需求id
		String requireId = request.getParameter("requireId");
		model.put("requireItem", requireItemServiceImpl.get(requireId));
		
		//供应商id
		String supplierId = request.getParameter("supplierId");
		
		model.put("biddingRecordObjectList", biddingRecordDetailService.getDetailBySupplierAndRequire(supplierId,requireId));
		model.put("currentOrgId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId());
		return new ModelAndView("recordDetailBySupplierAndRequireView" ,model);
	}
	
	/** 
	 * Description :  更改选中状态
	 * Create Date: 2011-5-17下午05:28:54 by yucy  Modified Date: 2011-5-17下午05:28:54 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=updateRecordChooseStatus")
	ModelAndView updateRecordChooseStatus(HttpServletRequest request) throws Exception{
		
		Map<String , Object > result = new HashMap<String, Object>();
		
		//要更改的id和值
		String changedIdandValue = request.getParameter("changedIdandValue");
		
		//需求id
		String requireId = request.getParameter("requireId");

		result.put("resultList",  biddingRecordDetailService.updateRecordChooseStatus(changedIdandValue,requireId));
		
		return new ModelAndView(Constants.JSON_VIEW ,result);
	}
	
	/** 
	 * Description :  上下移动时修改原行和目标行的排序
	 * Create Date: 2011-5-23上午10:45:15 by yucy  Modified Date: 2011-5-23上午10:45:15 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveSort")
	public ModelAndView saveSort(HttpServletRequest request,String[] orderArray) throws Exception{
		biddingRecordDetailService.saveSort( orderArray );
		return new ModelAndView(Constants.JSON_VIEW );
	}
	
	/** 
	 * Description :  保存报价信息[保存报价和保存历史]
	 * Create Date: 2011-5-26下午12:49:57 by sunl  Modified Date: 2011-5-26下午12:49:57 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveBargain")
	public ModelAndView saveBargain(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>(); 
		String bagainStr = request.getParameter("bagainStr");
		String bargainTurnId = request.getParameter("bargainTurnId");//轮次ID
		bagainStr = StringUtils.ascii2Native(bagainStr); 
		User user = AuthenticationHelper.getCurrentUser(true);
		
		BiddingRecordDetail biddingRecord = JsonUtils.json2Bean(bagainStr, BiddingRecordDetail.class);
		biddingRecord.setBarginTime(new Date());
		biddingRecord.setCreateTime(new Date());
		biddingRecord.setCreateUser(user);
		biddingRecord.setSupplier((OrgInfo)user.getOrgInfo());
		
		BargainTurn currentTurn = bargainTurnService.get(bargainTurnId);
		
		biddingRecordDetailService.saveBargain(biddingRecord,currentTurn);
		
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  重新报价[更新报价和更新历史报价]
	 * Create Date: 2011-5-26下午12:49:57 by sunl  Modified Date: 2011-5-26下午12:49:57 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=updateBargain")
	public ModelAndView updateBargain(HttpServletRequest request) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>(); 
		
		params.put("bargainDetailId", request.getParameter("bargainDetailId"));
		params.put("turnId", request.getParameter("turnId"));
		params.put("newPrice", request.getParameter("newPrice"));
		params.put("newTotal", request.getParameter("newTotal"));
		
		biddingRecordDetailService.updateBargain(params);
		
		return new ModelAndView(Constants.JSON_VIEW);
	}
	
	/** 
	 * Description :  获取供应商报价排名显示FusionChart的XML数据
	 * Create Date: 2011-7-1上午09:42:38 by likg  Modified Date: 2011-7-1上午09:42:38 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getBiddingRankChartXml")
	public void getBiddingRankChartXml(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,Object> param = new HashMap<String, Object>();
		String projId = request.getParameter("projId"); //项目Id
		String requireItemId = request.getParameter("requireItemId"); //需求条目Id
		String supplierId = request.getParameter("supplierId"); //当前供应商机构Id
		String bargainTurnId = request.getParameter("bargainTurnId"); //报价轮次Id

		param.put("projId", projId);
		param.put("supplierId", supplierId);
		param.put("bargainTurnId", bargainTurnId);
		param.put("requireItemId", requireItemId);
		
		String result = biddingRecordDetailService.getBiddingRankChartXml(param);
		response.getWriter().write(result);
		response.getWriter().close();
	}
	
	/** 
	 * Description :  获取供应商报价排名数据
	 * Create Date: 2011-7-1上午09:42:38 by likg  Modified Date: 2011-7-1上午09:42:38 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getBiddingRankData")
	public ModelAndView getBiddingRankData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String,Object> param = new HashMap<String, Object>();
		String projId = request.getParameter("projId"); //项目Id
		String requireItemId = request.getParameter("requireItemId"); //需求条目Id
		String bargainTurnId = request.getParameter("bargainTurnId"); //报价轮次Id
		
		//获取供应商报价排名数据
		param.put("projId", projId);
		param.put("bargainTurnId", bargainTurnId);
		param.put("requireItemId", requireItemId);
		model = biddingRecordDetailService.getBiddingRankData(param);
		
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  跳转到供应商报价记录列表页面
	 * Create Date: 2011-7-1上午09:42:38 by likg  Modified Date: 2011-7-1上午09:42:38 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toBiddingRecordDetailListView")
	public ModelAndView toBiddingRecordDetailListView(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		//获取当前供应商的机构id
		model.put("supplierId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId());
		
		return new ModelAndView("biddingRecordDetailListView", model);
	}
	
}
