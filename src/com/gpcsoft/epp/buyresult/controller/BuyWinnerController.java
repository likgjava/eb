package com.gpcsoft.epp.buyresult.controller;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.epp.buyresult.domain.BuyWinner;
import com.gpcsoft.epp.buyresult.service.BuyWinnerService;
import com.gpcsoft.epp.noticemanage.domain.Notice;
import com.gpcsoft.epp.noticemanage.domain.NoticeStatusEnum;
import com.gpcsoft.epp.noticemanage.service.NoticeService;

/**
  * @gpcsoft.view value="buyWinnerFormView"
  *  url="view/es/planform/buyresult/buyWinnerForm.jsp"
  * @gpcsoft.view value="buyWinnerTreeFormView"
  *  url="view/es/planform/buyresult/buyWinnerTreeForm.jsp"
  * @gpcsoft.view value="buyWinnerListView"
  *  url="view/es/planform/buyresult/buyWinnerList.jsp"
  * @gpcsoft.view value="buyWinnerDetailView"
  *  url="view/es/planform/buyresult/buyWinnerDetail.jsp"
  * @gpcsoft.view value="listBuyWinnerView"
  *  url="view/es/planform/buyresult/buy_winner_list.jsp"
  * @gpcsoft.view value="listBuyWinner"
  *  url="view/es/planform/buyresult/buy_winner_list_view.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={BuyWinner.class})
@RequestMapping("/BuyWinnerController.do")//页面请求路径,可修改
public class BuyWinnerController extends AnnotationMultiController<BuyWinner> {
	
	@Autowired(required=true) @Qualifier("noticeServiceImpl")
	private NoticeService noticeService;
	
	@Autowired(required=true) @Qualifier("buyWinnerServiceImpl")
	private BuyWinnerService buyWinnerService;
	
	/**
	 * Description :跳转到起草通知书列表页面  
	 * Create Date: 2010-9-28下午01:33:38 by liuke  Modified Date: 2010-9-28下午01:33:38 by liuke
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params = "method=toListBuyResult")
	public ModelAndView toListBuyResult(HttpServletRequest request)throws Exception {
		logger.debug("\nes BuyWinnerController||toListBuyResult\n");
		String resultId = request.getParameter("resultId");
		List<BuyWinner> winners = buyWinnerService.getListByResultId(resultId);
		String subProjectId = request.getParameter("subProjectId");
		Map<String,String> isSendMap = new HashMap<String, String>();
		for(BuyWinner winner  :winners){
			Notice notice = noticeService.getNoticeBySelllerIdAndSubProjectId(winner.getSelllerId(),subProjectId);//供应商id和标段id确定一个通知书
		    if(notice!=null && NoticeStatusEnum.SENDSTATUS_YES.equals(notice.getSendStatus())){
		    	isSendMap.put(winner.getSelllerId(), "已发送");
		    }else{
		    	isSendMap.put(winner.getSelllerId(), "未发送");
		    }
		}
		Map<String ,Object> model = new HashMap<String ,Object>();	
		model.put("isSendMap", isSendMap);
		model.put("winners", winners);
		model.put("subProjectId", subProjectId);
		return new ModelAndView("listBuyWinnerView", model);
	}
	
	
	/** 
	 * FuncName : toViewListBuyResult
	 * Description :  跳转到查看通知书列表页面  
	 * Create Date: 2011-11-15下午01:33:12 by yangx  
	 * Modified Date: 2011-11-15下午01:33:12 by yangx
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toViewListBuyResult")
	public ModelAndView toViewListBuyResult(HttpServletRequest request)throws Exception {
		logger.debug("\nes BuyWinnerController||toViewListBuyResult\n");
		String resultId = request.getParameter("resultId");
		List<BuyWinner> winners = buyWinnerService.getListByResultId(resultId);
		String subProjectId = request.getParameter("subProjectId");
		Map<String,String> isSendMap = new HashMap<String, String>();
		for(BuyWinner winner  :winners){
			Notice notice = noticeService.getNoticeBySelllerIdAndSubProjectId(winner.getSelllerId(),subProjectId);//供应商id和标段id确定一个通知书
		    if(notice!=null && NoticeStatusEnum.SENDSTATUS_YES.equals(notice.getSendStatus())){
		    	isSendMap.put(winner.getSelllerId(), "已发送");
		    }else{
		    	isSendMap.put(winner.getSelllerId(), "未发送");
		    }
		}
		Map<String ,Object> model = new HashMap<String ,Object>();	
		model.put("isSendMap", isSendMap);
		model.put("winners", winners);
		model.put("subProjectId", subProjectId);
		return new ModelAndView("listBuyWinner", model);
	}
}
