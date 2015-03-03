package com.gpcsoft.agreement.bargin.bidding.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.agreement.bargin.bidding.domain.BiddingEliminateSupplier;
import com.gpcsoft.agreement.bargin.bidding.service.BiddingEliminateSupplierService;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.web.controller.AnnotationMultiController;

@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={BiddingEliminateSupplier.class})
@RequestMapping("/BiddingEliminateSupplierController.do")//页面请求路径,可修改
public class BiddingEliminateSupplierController extends AnnotationMultiController<BiddingEliminateSupplier> {
	
	@Autowired(required=true) @Qualifier("biddingEliminateSupplierServiceImpl")
	private BiddingEliminateSupplierService biddingEliminateSupplierServiceImpl;
	
	/**
	 * 剔除供应商
	 * Description :  
	 * Create Date: 2010-10-21下午01:48:20 by sunl  Modified Date: 2010-10-21下午01:48:20 by sunl
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params = "method=endSupplier")   
	public ModelAndView endSupplier(HttpServletRequest request) throws Exception {
		
		String endSupplierListStr = request.getParameter("endSupplierListStr");
		biddingEliminateSupplierServiceImpl.deleteSupplier( endSupplierListStr );
		return new ModelAndView(Constants.JSON_VIEW);
	}
}
