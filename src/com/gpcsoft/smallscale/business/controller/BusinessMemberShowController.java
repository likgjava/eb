package com.gpcsoft.smallscale.business.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.bizplatform.base.common.util.StringUtils;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.smallscale.business.domain.BusinessMember;
import com.gpcsoft.smallscale.business.domain.Community;
import com.gpcsoft.smallscale.business.service.BusinessMemberService;
import com.gpcsoft.smallscale.business.service.CommunityService;

/**
 * @gpcsoft.view value="showBusinessMemberListView"
 *  url="view/smallscale/business/show_business_member_list.jsp"
 *  
 * @gpcsoft.view value="businessMemberForListView"
 *  url="view/smallscale/business/business_member_list_div_1.jsp"
 *  
 * @gpcsoft.view value="recommendMenberView"
 *  url="view/srplatform/portal/include/recommend_bizmember_list.jsp"
 *  
 * @gpcsoft.view value="shangQuanIndexView"
 *  url="view/smallscale/business/shangquan.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@RequestMapping("/BusinessMemberShowController.do")//页面请求路径,可修改
public class BusinessMemberShowController extends AnnotationMultiController<GpcBaseObject> {
	
	@Autowired(required=true) @Qualifier("businessMemberServiceImpl")
	private BusinessMemberService businessMemberService;
	
	@Autowired(required=true) @Qualifier("communityServiceImpl")
	private CommunityService communityService;
	
	/** 
	 * Description :  根据参数，获得首页所需的商圈会员数据
	 * Create Date: 2011-2-17下午01:51:50 by liangxj  Modified Date: 2011-2-17下午01:51:50 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=getBusinessMemberForIndex")   
	public ModelAndView getBusinessMemberForIndex(HttpServletRequest request) throws Exception { 
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> paramsMap = new HashMap<String, Object>();//paramsMap这个是查询条件
		
    	//获取推荐会员信息
    	Page<BusinessMember> page = prePage(request);//预分页,算出当前页和大小等	
    	Page<BusinessMember> pageData = businessMemberService.getBusinessMemberListForShow(page,paramsMap);
    	model.put("recommendMenberList",pageData.getData());

		return new ModelAndView("recommendMenberView", model);
	}
	
	/** 
	 * Description :  跳转到陶朱公商圈首页页面
	 * Create Date: 2011-3-17下午05:01:27 by likg  Modified Date: 2011-3-17下午05:01:27 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toShangQuanIndexView")   
	public ModelAndView toShangQuanIndexView(HttpServletRequest request) throws Exception { 
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		
		int rp = 5;
		if(request.getParameter("rp") != null ){
			rp = Integer.parseInt(request.getParameter("rp"));
		}
		
		//获取最新加入会员
		Page<BusinessMember> page = new Page<BusinessMember>(1, rp, rp, new ArrayList<BusinessMember>());
		Page<BusinessMember> pageData = businessMemberService.getBusinessMemberListForShow(page, paramsMap);
		model.put("newMemberList", pageData.getData());
		
		//获取推荐社区信息
		paramsMap.clear();
		paramsMap.put("isRecommended", "1");
		Page<Community> page2 = new Page<Community>(1L, 18L, 18, new ArrayList<Community>());
		Page<Community> pageData2 = communityService.getCommunityListForShow(page2, paramsMap);
		model.put("recommendCommunityList", pageData2.getData());
		
		return new ModelAndView("shangQuanIndexView", model);
	}
	
	/** 
	 * Description :  跳转到社区和商圈会员列表展示页面
	 * Create Date: 2011-3-18上午09:17:24 by likg  Modified Date: 2011-3-18上午09:17:24 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params="method=toShowBusinessMemberListView")
	public ModelAndView toShowBusinessMemberListView(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
    	Map<String, Object> paramsMap = new HashMap<String, Object>();
    	
    	//获取社区列表信息
		Page<Community> page1 = new Page<Community>(1L, 50L, 50, new ArrayList<Community>());
		Page<Community> pageData1 = communityService.getCommunityListForShow(page1, paramsMap);
		model.put("communityList", pageData1.getData());
		
    	//取商圈会员信息
    	Page<BusinessMember> page2 = prePage(request);
    	Page<BusinessMember> pageData2 = businessMemberService.getBusinessMemberListForShow(page2, paramsMap);
    	endPage(model, pageData2, request);
    	model.put("PAGERESULT",pageData2);
    	
		return new ModelAndView("showBusinessMemberListView", model);
	}
	
	/** 
	 * Description :  获取商圈会员展示的列表数据
	 * Create Date: 2011-3-18下午04:59:58 by likg  Modified Date: 2011-3-18下午04:59:58 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=getBusinessMemberForListView")   
	public ModelAndView getBusinessMemberForListView(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		
		String communityId = request.getParameter("communityId");
		if(StringUtils.hasLength(communityId)){
			Community community = communityService.get(communityId);
			paramsMap.put("categoryCode", community.getTenderCategorys().subSequence(0, community.getTenderCategorys().indexOf("#")));
		}
		
    	//排序
    	paramsMap.put("createTimeSort", request.getParameter("createTimeSort"));
    	
    	//高级搜索条件
    	String orgName = request.getParameter("orgName");//机构名称
    	String keyWord = request.getParameter("keyWord");//关键字
    	paramsMap.put("orgName", StringUtils.replaceIngoreStr(StringUtils.ascii2Native(orgName)));
    	paramsMap.put("keyWord", StringUtils.replaceIngoreStr(StringUtils.ascii2Native(keyWord)));
    	
    	//取商圈会员信息
    	Page<BusinessMember> page2 = prePage(request);
    	Page<BusinessMember> pageData2 = businessMemberService.getBusinessMemberListForShow(page2, paramsMap);
    	endPage(model, pageData2, request);
    	model.put("PAGERESULT",pageData2);
    	
		return new ModelAndView("businessMemberForListView", model);
    }
	
	/** 
	 * Description :  获取商圈会员列表数据
	 * Create Date: 2011-3-22上午11:44:51 by likg  Modified Date: 2011-3-22上午11:44:51 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=getBusinessMemberList")
	@SuppressWarnings("unchecked")
	public ModelAndView getBusinessMemberList(HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		
		paramsMap.put("order", request.getParameter("order")==null?"":request.getParameter("order"));//排序字段
		paramsMap.put("order_flag", request.getParameter("order_flag")==null?"":request.getParameter("order_flag"));//排序方式
		
		//获取社区基本信息
		String communityId = request.getParameter("communityId");
		Community community = communityService.get(communityId);
		
		//获取商圈会员信息
		Page page = prePage(request);
		paramsMap.put("categoryCode", community.getTenderCategorys().subSequence(0, community.getTenderCategorys().indexOf("#")));
		Page<BusinessMember> pageData = businessMemberService.getBusinessMemberListForShow(page, paramsMap);
		endPage(model, pageData, request);
	    
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
}
