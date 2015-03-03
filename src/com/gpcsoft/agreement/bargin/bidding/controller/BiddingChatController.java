package com.gpcsoft.agreement.bargin.bidding.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.agreement.bargin.bidding.domain.BiddingChat;
import com.gpcsoft.agreement.bargin.bidding.service.BiddingChatService;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.utils.JsonUtils;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.epp.signuprecord.domain.SignUprecord;
import com.gpcsoft.epp.signuprecord.service.SignUprecordService;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;

/**
  *  @gpcsoft.view value="toConfirmResultBySupplierView"
  *  url="view/agreement/bargin/buyresult/a_confrim_result_bysupplier.jsp"
  *  @gpcsoft.view value="toChartFormView"
  *  url="view/agreement/bargin/project/hall/chat_form.jsp"
  *  
  *  @gpcsoft.view value="toChatRecordDetailView"
  *  url="view/agreement/bargin/bidding/chat_list.jsp"
  */

@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={BiddingChat.class})
@RequestMapping("/BiddingChatController.do")//页面请求路径,可修改
public class BiddingChatController extends AnnotationMultiController<BiddingChat> {
	
	@Autowired(required=true) @Qualifier("biddingChatServiceImpl")
	private BiddingChatService biddingChatService;
	@Autowired(required=true) @Qualifier("signUprecordServiceImpl")
	private SignUprecordService signUprecordService;
	
	/** 
	 * Description :  跳转到聊天记录查看
	 * Create Date: 2010-10-27下午05:34:59 by yucy  Modified Date: 2010-10-27下午05:34:59 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toChatRecordDetailView")   
	public ModelAndView toChatRecordDetailView(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object> ();
		Map<String, Object> params = new HashMap<String, Object> ();
		
		params.put("projId", request.getParameter("projId"));
		params.put("turnId", request.getParameter("turnId"));
		params.put("orgInfoId", request.getParameter("orgInfoId"));
		
		List<BiddingChat> biddingChatList =  biddingChatService.findChatContent(params);
		model.put("biddingChatList", biddingChatList);
		return new ModelAndView ("toChatRecordDetailView",model);
	}
	
	/** 
	 * Description :  获取未读的聊天内容(异步方式只取接受人为当前人的 增加 sayType )
	 * Create Date: 2010-10-24下午01:36:56 by liangxj  Modified Date: 2010-10-24下午01:36:56 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=findNotReadChatContent")   
	public ModelAndView findNotReadChatContent(HttpServletRequest request) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("projId",request.getParameter("projId"));  //项目id
		params.put("orgInfoId", AuthenticationHelper.getCurrentUser().getOrgInfo().getObjId()+","+request.getParameter("orgInfoId"));  //发送人和接受人
		params.put("sayType",request.getParameter("sayType"));  //发送人或接受人 say or  receive
		params.put("readStatus",false);   //阅读状态为未读
		
		//获取未读的聊天信息
		Map<String, Object> model = new HashMap<String, Object>();
		List<BiddingChat> biddingChatList=biddingChatService.findChatContent(params);
		
		//新报名的供应商列表
		List<SignUprecord> supplierList = signUprecordService.getSignupRecordList(request.getParameter("projId"));
		List<SignUprecord> newsupplierList = new ArrayList<SignUprecord>();
		String oldSupplierList = request.getParameter("oldSupplierList");
		for (SignUprecord signUprecord : supplierList) {
			if(oldSupplierList!=null && oldSupplierList.indexOf(signUprecord.getSupplier().getObjId()) == -1) {
				newsupplierList.add(signUprecord);
			}
		}
		
		model.put("newSupplierList", newsupplierList);
		
		//更新阅读状态
		biddingChatService.updateChatRecord(params);
		
		model.put("chatList", biddingChatList);
		
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	/** 
	 * Description :  跳转到聊天页面
	 * Create Date: 2010-10-24下午02:30:42 by liangxj  Modified Date: 2010-10-24下午02:30:42 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toChatForm")   
	public ModelAndView toChatForm(HttpServletRequest request) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		OrgInfo orgInfo = (OrgInfo)AuthenticationHelper.getCurrentUser().getOrgInfo();
		params.put("projId",request.getParameter("projId"));  //项目id
		params.put("turnId",request.getParameter("turnId"));  //轮次id
		params.put("orgInfoId", orgInfo.getObjId()+","+request.getParameter("orgInfoId"));  //发送人和接受人

		
		//查询
		List<BiddingChat> biddingChatList=biddingChatService.findChatContent(params);
		
		//更新阅读状态
		biddingChatService.updateChatRecord(params);
		
		//往页面上传递参数
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("projId", request.getParameter("projId"));
		model.put("turnId", request.getParameter("turnId"));
		model.put("chatList", biddingChatList);
		model.put("orgInfoId", request.getParameter("orgInfoId"));
		model.put("myOrgInfoId", orgInfo.getObjId());
		model.put("myOrgInfoName", orgInfo.getOrgName());
		
		//是采购人
		if(StringUtils.hasLength(orgInfo.getBuyerId())) {
			model.put("chatRole", "buyer");
		}else {//是供应商
			model.put("chatRole", "supplier");
		}
		
		return new ModelAndView("toChartFormView",model);
	}
	
	/** 
	 * Description :  发送聊天消息(竞价)
	 * Create Date: 2010-10-22下午06:47:15 by sunl  Modified Date: 2010-10-22下午06:47:15 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=saveBiddingChat")   
	public ModelAndView saveBiddingChat(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		
		if(AuthenticationHelper.getCurrentUser() == null) {
			model.put(Constants.JSON_RESULT, "session已失效,请重新登录!");
		}else {
			String biddingChatStr = request.getParameter("biddingChatStr");
			BiddingChat biddingChat = JsonUtils.json2Bean(biddingChatStr, BiddingChat.class);
			
			biddingChat.setReadStatus(false);  //阅读状态为未读
			
			biddingChatService.saveByObject(biddingChat);
			biddingChat.setProject(null);biddingChat.setCreateUser(null);//防止json溢出 by yucy(字符串太长包含太多信息会导致错误)
			model.put("biddingChat", biddingChat);
		}
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	/** 
	 * Description :  查找历史聊天记录
	 * Create Date: 2010-11-10下午05:04:52 by likg  Modified Date: 2010-11-10下午05:04:52 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=findHistoryChatContent")   
	public ModelAndView findHistoryChatContent(HttpServletRequest request) throws Exception 
	{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("projId",request.getParameter("projId"));  //项目id
		params.put("orgInfoId", AuthenticationHelper.getCurrentUser(true).getOrgInfo().getObjId()+","+request.getParameter("orgInfoId"));  //发送人和接受人
		
		Map<String, Object> model = new HashMap<String, Object>();
		List<BiddingChat> historyBiddingChatList = biddingChatService.findChatContent(params);
		
		//更新阅读状态
		biddingChatService.updateChatRecord(params);
		
		model.put("chatList", historyBiddingChatList);
		
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
}
