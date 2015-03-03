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
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.core.Constants;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.smallscale.business.domain.Community;
import com.gpcsoft.smallscale.business.service.BusinessMemberService;
import com.gpcsoft.smallscale.business.service.CommunityService;

/**
  * @gpcsoft.view value="communityRecommendView"
  *  url="view/srplatform/portal/include/recommend_bizarea_list.jsp"
  *  
  * @gpcsoft.view value="communityIndexView"
  *  url="view/smallscale/business/show_community_index.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@RequestMapping("/CommunityShowController.do")//页面请求路径,可修改
public class CommunityShowController extends AnnotationMultiController<GpcBaseObject> {

	@Autowired(required=true) @Qualifier("communityServiceImpl")
	private CommunityService communityService;
	
	@Autowired(required=true) @Qualifier("businessMemberServiceImpl")
	private BusinessMemberService businessMemberService;
	
	/** 
	 * Description :  取得社区数据
	 * Create Date: 2010-7-26下午03:26:13 by yucy  Modified Date: 2010-7-26下午03:26:13 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getNewAndSpecialAndRecommended")   
	public ModelAndView getNewAndSpecialAndRecommended(HttpServletRequest request, SessionStatus status) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();   
		Map <String,Object> result =  communityService.getNewAndSpecialAndRecommended(param);
		return new ModelAndView(Constants.JSON_VIEW,result);
	}
	
	/** 
	 * Description :  跳转到展会信息列表
	 * Create Date: 2010-7-19下午06:29:00 by sunl  Modified Date: 2010-7-19下午06:29:00 by sunl
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toZhanhuiList")
    public ModelAndView toZhanhuiList(HttpServletRequest request) throws Exception {
		Map<String,Object> model = new HashMap<String,Object>();
		
		String viewName = request.getParameter("viewName");
		String record_id = request.getParameter("record_id");
		
		model.put("record_id", record_id);
		return new ModelAndView(viewName);
    }
	
	/** 
	 * Description :  根据参数，获得首页所需的社区数据
	 * Create Date: 2011-2-17下午01:51:50 by liangxj  Modified Date: 2011-2-17下午01:51:50 by liangxj
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=getCommunityForIndex")   
	public ModelAndView getCommunityForIndex(HttpServletRequest request) throws Exception { 
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> paramsMap = new HashMap<String, Object>();//paramsMap这个是查询条件
		
		paramsMap.put("isSpecial", request.getParameter("isSpecial"));  //是否是特色
    	paramsMap.put("isRecommended",request.getParameter("isRecommended"));  //是否是推荐
    	
    	//获取推荐社区信息
    	Page<Community> page = prePage(request);//预分页,算出当前页和大小等	
    	Page<Community> pageData = communityService.getCommunityListForShow(page, paramsMap);
    	model.put("communityList", pageData.getData());

		return new ModelAndView("communityRecommendView", model);
	}
	
	/** 
	 * Description :  跳转到社区首页视图
	 * Create Date: 2011-3-19上午12:12:24 by likg  Modified Date: 2011-3-19上午12:12:24 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=toCommunityIndexView")   
	public ModelAndView toCommunityIndexView(HttpServletRequest request) throws Exception { 
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		
		//获取社区基本信息
		String communityId = request.getParameter("communityId");
		Community community = communityService.get(communityId);
		model.put("community", community);
		
		//获取社区商圈会员数量
		paramsMap.put("categoryCode", community.getTenderCategorys().subSequence(0, community.getTenderCategorys().indexOf("#")));
		model.put("businessMemberNum", businessMemberService.getBusinessMemberNum(paramsMap));
		
		//获取推荐社区信息
		paramsMap.clear();
		paramsMap.put("isRecommended", "1");
		Page<Community> page = new Page<Community>(1L, 5L, 5, new ArrayList<Community>());
		Page<Community> pageData = communityService.getCommunityListForShow(page, paramsMap);
		model.put("recommendCommunityList", pageData.getData());
		
		model.put("topicId", request.getParameter("topicId"));
		
		return new ModelAndView("communityIndexView", model);
	}
}
