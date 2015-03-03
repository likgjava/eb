package com.gpcsoft.agreement.bargin.bidding.controller;

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

import com.gpcsoft.agreement.bargin.bidding.domain.BiddingRecordHistory;
import com.gpcsoft.agreement.bargin.bidding.service.BiddingRecordHistoryService;
import com.gpcsoft.agreement.bargin.require.service.RequireItemService;
import com.gpcsoft.core.web.controller.AnnotationMultiController;

/**
 * @gpcsoft.view value="toHistoryByDetailView"
 * url="view/agreement/bargin/project/project_record_history_supplier.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={BiddingRecordHistory.class})
@RequestMapping("/BiddingRecordHistoryController.do")//页面请求路径,可修改
public class BiddingRecordHistoryController extends AnnotationMultiController<BiddingRecordHistory> {

	@Autowired(required=true) @Qualifier("biddingRecordHistoryServiceImpl")
	private BiddingRecordHistoryService biddingRecordHistoryService;
	
	@Autowired(required=true) @Qualifier("requireItemServiceImpl")
	private RequireItemService requireItemServiceImpl;
	
	/** 
	 * Description :  详情所有的历史
	 * Create Date: 2011-5-30上午09:23:00 by yucy  Modified Date: 2011-5-30上午09:23:00 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toHistoryByDetail")
	ModelAndView toHistoryByDetail(String detailId,String requireId, HttpServletRequest request) throws Exception{
		Map<String , Object> model = new HashMap<String, Object>();
		
		model.put("requireItem", requireItemServiceImpl.get(requireId));
		model.put("biddingRecordObjectList",  biddingRecordHistoryService.getHistoryInfoByDetail(detailId));
		return new ModelAndView("toHistoryByDetailView",model);
	}
}
