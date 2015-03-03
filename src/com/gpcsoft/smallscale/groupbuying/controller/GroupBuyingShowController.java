package com.gpcsoft.smallscale.groupbuying.controller;

import java.util.ArrayList;
import java.util.Date;
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

import com.gpcsoft.core.Constants;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.smallscale.groupbuying.domain.GroupBuyer;
import com.gpcsoft.smallscale.groupbuying.domain.GroupBuying;
import com.gpcsoft.smallscale.groupbuying.domain.GroupBuyingClass;
import com.gpcsoft.smallscale.groupbuying.service.GroupBuyerService;
import com.gpcsoft.smallscale.groupbuying.service.GroupBuyingClassService;
import com.gpcsoft.smallscale.groupbuying.service.GroupBuyingService;

/**
  * @gpcsoft.view value="showGroupBuyingIndexView"
  * url="view/smallscale/groupbuying/show_group_buying_index.jsp"
  * 
  *  @gpcsoft.view value="groupBuyingPayView"
  *  url="view/smallscale/groupbuying/group_buying_pay.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={GroupBuying.class})
@RequestMapping("/GroupBuyingShowController.do")
public class GroupBuyingShowController extends AnnotationMultiController<GroupBuying> {

	@Autowired(required=true) @Qualifier("groupBuyingServiceImpl")
	private GroupBuyingService groupBuyingService;
	
	@Autowired(required=true) @Qualifier("groupBuyingClassServiceImpl")
	private GroupBuyingClassService groupBuyingClassService;
	
	@Autowired(required=true) @Qualifier("groupBuyerServiceImpl")
	private GroupBuyerService groupBuyerService;
	
	/** 
	 * Description :  跳转到团购首页页面
	 * Create Date: 2011-6-21上午11:16:04 by likg  Modified Date: 2011-6-21上午11:16:04 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toShowGroupBuyingIndexView")
	public ModelAndView toShowGroupBuyingIndexView(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		List<List<GroupBuying>> groupBuyingListList = new ArrayList<List<GroupBuying>>();
		List<GroupBuyingClass> groupBuyingClassList = new ArrayList<GroupBuyingClass>();
		
		//获取团购分类信息
//		QueryObject<GroupBuyingClass> query = new QueryObjectBase<GroupBuyingClass>();
//		query.setEntityClass(GroupBuyingClass.class);
//		query.getQueryProjections().setOrderProperty("sort");
//		query.getQueryProjections().setDescFlag(false);
//		List<GroupBuyingClass> ggcList = groupBuyingClassService.findByQuery(query);
		
		
		List<GroupBuyingClass> ggcList = groupBuyingClassService.getBuyingClassList();
		
		//获取团购信息
		for(GroupBuyingClass groupBuyingClass : ggcList) {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("goodsClassId", groupBuyingClass.getGoodsClass().getObjId());
			Page<GroupBuying> page = new Page<GroupBuying>(1L, 30L, 30, new ArrayList<GroupBuying>());
			List<GroupBuying> groupBuyingList = groupBuyingService.getGroupBuyingList(page, param).getData();
			if(groupBuyingList!=null && groupBuyingList.size()>0) {
				groupBuyingListList.add(groupBuyingList);
				groupBuyingClassList.add(groupBuyingClass);
			}
		}
		model.put("groupBuyingListList", groupBuyingListList);
		model.put("groupBuyingClassList", groupBuyingClassList);
		
		return new ModelAndView("showGroupBuyingIndexView", model);
	}
	
	/** 
	 * Description :  提交团购信息
	 * Create Date: 2011-6-22下午02:13:54 by likg  Modified Date: 2011-6-22下午02:13:54 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=updateStatus")
	public ModelAndView updateStatus(String objId, String useStatus, HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		//修改团购信息使用状态
		groupBuyingService.updateStatus(objId, useStatus);
		
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  跳转到团购支付页面
	 * Create Date: 2011-6-24上午09:36:46 by likg  Modified Date: 2011-6-24上午09:36:46 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toGroupBuyingPayView")
    public ModelAndView toGroupBuyingPayView(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		//获取团购采购人信息
		String groupBuyerId = request.getParameter("groupBuyerId");
		GroupBuyer groupBuyer = groupBuyerService.get(groupBuyerId);
		GroupBuying groupBuying = groupBuyer.getGroupBuying();
		if(groupBuying.getEndTime().getTime() - new Date().getTime() < 0) {
			model.put("hasEnd", true);
		}
		model.put("groupBuyer", groupBuyer);
		
		//订单金额
		String v_amount = groupBuyer.getAmount().multiply(groupBuyer.getGroupBuying().getGroupPrice()).toString();
		model.put("v_amount", v_amount);
		
		//商户返回调用业务方法
		model.put("v_back_req_method", "toGroupBuyingPayResult");

		return new ModelAndView("groupBuyingPayView", model);
    }
	
}
