package com.gpcsoft.agreement.bargin.bidding.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.agreement.bargin.bidding.domain.BiddingRecord;
import com.gpcsoft.agreement.bargin.bidding.domain.BiddingRecordItem;
import com.gpcsoft.agreement.bargin.bidding.service.BiddingRecordService;
import com.gpcsoft.bizplatform.base.common.util.StringUtils;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.utils.JsonUtils;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;

/**
 * @gpcsoft.view value="toBiddingRecordDetailView"
 *  url="view/agreement/bargin/project/talk/bargain_record_detail.jsp" 
 */


@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={BiddingRecord.class})
@RequestMapping("/BiddingRecordController.do")//页面请求路径,可修改
public class BiddingRecordController extends AnnotationMultiController<BiddingRecord> {
	
	@Autowired(required=true) @Qualifier("biddingRecordServiceImpl")
	private BiddingRecordService biddingRecordServiceImpl;
	
	@RequestMapping(params = "method=saveBiddingRecord")
	public ModelAndView saveBiddingRecord(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>(); 
		String biddingRecordStr = request.getParameter("biddingRecordStr");
		
		biddingRecordStr = StringUtils.ascii2Native(biddingRecordStr); 
		
		//转换报价和报价条目商品参数集合
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("biddingRecordItemSet", BiddingRecordItem.class);
		biddingRecordStr = biddingRecordStr.replaceAll("\"null\"","\"\"");
		BiddingRecord biddingRecord = JsonUtils.json2Bean(biddingRecordStr, BiddingRecord.class, map);
		biddingRecord.setSupplier((OrgInfo)AuthenticationHelper.getCurrentUser(true).getOrgInfo());
		
		//处理jsonBean转换时objId为空值的情况
		Set<BiddingRecordItem> biddingRecordItemSet = biddingRecord.getBiddingRecordItemSet();
		for (BiddingRecordItem biddingItem : biddingRecordItemSet) {
			if(StringUtils.hasLength(biddingItem.getServiceContent())) {
				biddingItem.setServiceContent(biddingItem.getServiceContent());
			}
			if(biddingItem != null) {
				if(biddingItem.getObjId() != null && "".equals(biddingItem.getObjId())){
					biddingItem.setObjId(null);
					biddingItem.setCreateTime(new Date());
				}
				if(biddingItem.getGoods() != null && "".equals(biddingItem.getGoods().getObjId())){
					biddingItem.setGoods(null);
				}
			}
		}
		
		biddingRecordServiceImpl.save(biddingRecord);
		
		model.put(Constants.JSON_RESULT,"success");
		
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  跳转到详情
	 * Create Date: 2010-11-30下午04:48:32 by yucy  Modified Date: 2010-11-30下午04:48:32 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toBiddingRecordDetail")
	public ModelAndView toBiddingRecordDetail(HttpServletRequest request,String biddingRecordId) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>(); 
		BiddingRecord biddingRecord = biddingRecordServiceImpl.get(biddingRecordId);
		model.put("biddingRecord", biddingRecord);
		return new ModelAndView("toBiddingRecordDetailView", model);
	}
}
