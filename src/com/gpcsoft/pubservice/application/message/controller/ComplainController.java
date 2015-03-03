package com.gpcsoft.pubservice.application.message.controller;

import java.util.Date;
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

import com.gpcsoft.agreement.bargin.bidding.domain.BiddingRecord;
import com.gpcsoft.agreement.bargin.bidding.service.BiddingRecordService;
import com.gpcsoft.bizplatform.organization.domain.OrgInfo;

import com.gpcsoft.core.Constants;
import com.gpcsoft.core.dao.hibernate.query.QueryObject;
import com.gpcsoft.core.dao.hibernate.query.QueryParam;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.pubservice.application.message.domain.Complain;
import com.gpcsoft.pubservice.application.message.service.ComplainService;
import com.gpcsoft.srplatform.auth.domain.User;

/**
  * @gpcsoft.view value="complainFormView"
  *  url="view/pubservice/application/message/complain_user_form.jsp"
  * @gpcsoft.view value="complainListView"
  *  url="view/pubservice/application/message/complain_user_list.jsp"  
  * @gpcsoft.view value="complainView"
  *  url="view/pubservice/application/message/complain_user_view.jsp" 
  * @gpcsoft.view value="complainedView"
  *  url="view/pubservice/application/message/complained_user_view.jsp" 
  * @gpcsoft.view value="complainDealFormView"
  *  url="view/pubservice/application/message/complain_form.jsp"
  * @gpcsoft.view value="complainDealListView"
  *  url="view/pubservice/application/message/complain_list.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={Complain.class})
@RequestMapping("/ComplainController.do")//页面请求路径,可修改
public class ComplainController extends AnnotationMultiController<Complain> {

	@Autowired(required=true) @Qualifier("complainServiceImpl")
	private ComplainService complainService;
	
	@Autowired(required=true) @Qualifier("biddingRecordServiceImpl")
	private BiddingRecordService biddingRecordService;
	
	/**
	 * 投诉举报页面
	 * @param objId
	 * @return
	 */
	@RequestMapping(params="method=toCreateComplain")
	public ModelAndView toCreateComplain(String projectId ,String beComplain,String beCompanyId) throws Exception{		
		Map<String, Object> model = new HashMap<String, Object>();
		User user = AuthenticationHelper.getCurrentUser(true);		
		model.put("replyEmail", user.getEmp().getEmail());
		//if(null == beComplain || beComplain.equals("")){//议价项目取投诉人为机构管理员
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("projectId", projectId);
			param.put("supplierId", beCompanyId);
			BiddingRecord br = biddingRecordService.getMinBiddingRecordByProjectAndSupplier(param);			

			if(br!=null && br.getCreateUser() != null){				
				model.put("beComplain", br.getCreateUser().getObjId());				
				model.put("beComplainName", br.getCreateUser().getEmp().getName());
			}
		//}
		
		return new ModelAndView("complainFormView",model);
		
	}
	
	
	/**
	 * 保存投诉举报信息
	 * @param complain
	 * @param request
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params="method=mySave")
	public ModelAndView mySave(Complain complain,HttpServletRequest request,SessionStatus status) throws Exception{
		complain.setObjId(null); //确保为临时对象
		complain.setComplainant(AuthenticationHelper.getCurrentUser(true));	
		if(null != AuthenticationHelper.getCurrentUser(true).getOrgInfo()){
			OrgInfo oi = (OrgInfo) AuthenticationHelper.getCurrentUser(true).getOrgInfo();		
			complain.setComplainantName(oi.getOrgName());
		}
		
		complain.setComplainTime(new Date());
		return this.save(complain, request, status);
			
	}
	
	
	/**
	 * 管理员处理投诉举报查询列表
	 * @param request
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params="method=listDeal")
	public ModelAndView listDeal(HttpServletRequest request) throws Exception{

		Map<String, Object> model = new HashMap<String, Object>();

		String currentTabID = request.getParameter("currentTabID");
		
		
		model.put("currentTabID", currentTabID);
		return new ModelAndView("complainDealListView",model);		
	}
	
	
	/**
	 * 用户的查看页面
	 * @param objId
	 * @param currentTabID
	 * @return
	 */
	@RequestMapping(params="method=toView")
	public ModelAndView toView(String objId,String currentTabID){		
		Map<String, Object> model = new HashMap<String, Object>();
		Complain complain = complainService.get(objId);
		model.put("complain", complain);	
		model.put("currentTabID", currentTabID);
		
		if(null !=currentTabID && (currentTabID.equals("tabs_telled")|| currentTabID.equals("tabs_complained"))){//我收到的投诉举报
			return new ModelAndView("complainedView",model);
		}
		else{//查看我做出的投诉举报
			return new ModelAndView("complainView",model);
		}	
	}
	
	/**
	 * 管理员处理投诉举报
	 * @param request
	 * @param status
	 * @return
	 * @throws Exception
	 */	
	@RequestMapping(params="method=toDeal")
	public ModelAndView toDeal(HttpServletRequest request) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		String objId = request.getParameter("objId");
		String currentTabID = request.getParameter("currentTabID");
		Complain complain = complainService.get(objId);
		model.put("complain", complain);
		model.put("currentTabID", currentTabID);
		return new ModelAndView("complainDealFormView", model);
	}

	
	@RequestMapping(params="method=saveDeal")
	public ModelAndView saveDeal(Complain complain,HttpServletRequest request,SessionStatus status) throws Exception{

		Map<String, Object> model = new HashMap<String, Object>();
		complainService.saveDeal(complain);	
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW,model);
		
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public QueryObject onFind(QueryObject query,HttpServletRequest request) throws Exception {
		User user = AuthenticationHelper.getCurrentUser(true);
			QueryParam qp = query.getQueryParam()==null?new QueryParam():query.getQueryParam();
			String tellType = request.getParameter("tellType");			
			if(null != tellType){
				if(tellType.equals("telled")){//被投诉 beComplain
					//qp.and(new QueryParam("beComplain","=",user.getObjId()));
					qp.and(new QueryParam("beCompanyId","=",user.getOrgInfo().getObjId()));
					
				}
				else if(tellType.equals("tell")){ //我做出的投诉 complainant 
					qp.and(new QueryParam("complainant.objId","=",user.getObjId()));
				}
			}	
			
			query.setQueryParam(qp);
		return query;
	}
	
	@Override
	 protected Map<String, String> getEnumColumns() {
	       Map<String, String> enumMap=new HashMap<String, String>();
	       enumMap.put("isDispose", "isDispose");//第一个参数是属性，第二参数是枚举类中定义名
       
	       return enumMap;
	 }

}
