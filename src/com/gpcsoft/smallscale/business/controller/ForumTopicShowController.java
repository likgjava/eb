package com.gpcsoft.smallscale.business.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.core.Constants;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.utils.Page;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.pubservice.application.service.service.ServiceSubscribeService;
import com.gpcsoft.smallscale.business.domain.Community;
import com.gpcsoft.smallscale.business.domain.ForumTopic;
import com.gpcsoft.smallscale.business.service.CommunityService;
import com.gpcsoft.smallscale.business.service.ForumReplyService;
import com.gpcsoft.smallscale.business.service.ForumTopicService;
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.service.AttachmentService;

/**
  * @gpcsoft.view value="toCommunityTopicInfoView"
  *  url="view/smallscale/business/community_topic_info.jsp"
  * @gpcsoft.view value="shangquanIndexTopicView"
  *  url="view/smallscale/business/shangquan_indexTopic_div.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@RequestMapping("/ForumTopicShowController.do")//页面请求路径,可修改
public class ForumTopicShowController extends AnnotationMultiController<GpcBaseObject> {

	@Autowired(required=true) @Qualifier("forumTopicServiceImpl")
	private ForumTopicService forumTopicService;
	
	@Autowired(required=true) @Qualifier("forumReplyServiceImpl")
	private ForumReplyService forumReplyService;
	
	@Autowired(required=true) @Qualifier("communityServiceImpl")
	private CommunityService communityService;
	
	@Autowired(required=true) @Qualifier("attachmentServiceImpl")
	private AttachmentService attachmentService;
	
	@Autowired(required=true)  @Qualifier("serviceSubscribeServiceImpl")
	private ServiceSubscribeService serviceSubscribeService;
	
	/**
	 * Description :  获取社区主题列表数据
	 * Create Date: 2011-4-15下午04:24:40 by zhaojf  Modified Date: 2011-4-15下午04:24:40 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params="method=getCommunityTopicList")
	public ModelAndView getCommunityTopicList(HttpServletRequest request) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		
		String communityId = request.getParameter("communityId");
		paramsMap.put("communityId", communityId);
		
		paramsMap.put("order", "order");
		
		//获取商圈会员信息
		Page page = prePage(request);
		Page<ForumTopic> pageData = forumTopicService.getForumTopicList(page, paramsMap);
		endPage(model, pageData, request);
		
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	/**
	 * Description :  跳转到社区
	 * Create Date: 2011-3-9下午02:24:51 by zhaojf  Modified Date: 2011-3-9下午02:24:51 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=toCommunityTopicInfo")
	public ModelAndView toCommunityTopicInfo(String objId) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		Community community = new Community();
		community = communityService.get(objId);
		model.put("community", community);
		return new ModelAndView("toCommunityTopicInfoView",model);
	}
	
	/**
	 * Description :  主题的回复数、发布机构的发帖数、发布机构发布的精英主题
	 * Create Date: 2011-3-11下午04:37:51 by zhaojf  Modified Date: 2011-3-11下午04:37:51 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=countReplyTopicNum")
	public ModelAndView countReplyTopicNum(String objId) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		model = forumTopicService.getReplyAndOrgReplyAndOrgTop(forumTopicService.get(objId));
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	/**
	 * Description :  商圈首页显示的符合条件的最新的主题
	 * Create Date: 2011-3-18下午02:23:03 by zhaojf  Modified Date: 2011-3-18下午02:23:03 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=getIndexTopicList")
	public ModelAndView getIndexTopicList(HttpServletRequest request) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		
		int rp = 5;
		if(request.getParameter("rp") != null ){
			rp = Integer.parseInt(request.getParameter("rp"));
		}
		//过滤条件
		paramsMap.put("isElite", "true");//精华主题
		
		Page<ForumTopic> page = new Page<ForumTopic>(1L, 5L, rp, new ArrayList<ForumTopic>());
    	Page<ForumTopic> pageData = forumTopicService.getForumTopicList(page, paramsMap);
    	model.put("newEliteTopicList", pageData);
		return new ModelAndView("shangquanIndexTopicView",model);
	}
	
	/**
	 * Description :  查看帖子主题、帖子展示
	 * Create Date: 2011-3-8下午01:33:11 by zhaojf  Modified Date: 2011-3-8下午01:33:11 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params = "method=toTopicShow")
	public ModelAndView toTopicShow(String objId,String skipType) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();
		String returnStr = "forumTopicInfo";//帖子主题
		
		ForumTopic forumTopic = forumTopicService.get(objId);
		
		if("unfurlTopic".equals(skipType)){
			returnStr = "forumTopicShowView";//帖子展示
			model = forumTopicService.getReplyAndOrgReplyAndOrgTop(forumTopic);
		}
		
		model.put("forumTopic", forumTopic);
		
		List<Attachment> attachmentList = new ArrayList<Attachment>();
		if(forumTopic.getAttachment() != null)
			attachmentList = attachmentService.getAttachmentsByRealID(forumTopic.getAttachment());
		model.put("attachmentList", attachmentList);
		model.put("attachmentListNum", attachmentList.size());
		
		return new ModelAndView(returnStr,model);
	}
		
	/** 
     * Description :  帖子评论列表
     * Create Date: 2011-2-17下午05:03:34 by dongcl  Modified Date: 2011-2-17下午05:03:34 by dongcl
     * @param   
     * @return  
     * @Exception   
     */
    @SuppressWarnings({ "unchecked" })
	@RequestMapping(params = "method=toTopicReplyList")
    public ModelAndView toTopicReplyList(HttpServletRequest request) throws Exception {
    	Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> paramsMap = new HashMap<String, Object>();//paramsMap这个是查询条件
		
		//当前主题Id
		paramsMap.put("topicId", request.getParameter("topicId"));
		
		//取回帖列表
    	Page<Object> page = prePage(request);//预分页,算出当前页和大小等	
    	Page<Object> pageData = forumReplyService.getTopicReplyListForShow(page, paramsMap);
    	endPage(model, pageData, request);
    	model.put("PAGERESULT",pageData);
    	
    	return new ModelAndView("toTopicReplyDiv1View",model);
    }
    
    /**
     * Description :  查看帖子
     * Create Date: 2011-3-8下午03:14:52 by zhaojf  Modified Date: 2011-3-8下午03:14:52 by zhaojf
     * @param   
     * @return  
     * @Exception
     */
    @RequestMapping(params="method=toTopicReplyDetail")
    public ModelAndView toTopicReplyDetail(String objId) throws Exception{
    	Map<String, Object> model = new HashMap<String, Object>();
    	model.put("forumReply", forumReplyService.get(objId));
    	return new ModelAndView("toTopicReplyDetailView",model);
    }
	    
    /**
     * Description :  判断当前机构是否拥有'商圈会员'角色
     * Create Date: 2011-3-22上午11:04:19 by zhaojf  Modified Date: 2011-3-22上午11:04:19 by zhaojf
     * @param   
     * @return  
     * @Exception
     */
    @RequestMapping(params="method=getCurrentUserIsBizMember")
    public ModelAndView getCurrentUserIsBizMember() throws Exception{
    	Map<String, Object> model = new HashMap<String, Object>();
    	
    	if(AuthenticationHelper.getCurrentUser() != null  && AuthenticationHelper.getCurrentUser().getOrgInfo()!=null) {
    		model.put("isBizMember", serviceSubscribeService.isShangQuanHuiYuan((AuthenticationHelper.getCurrentUser(true).getOrgInfo()).getObjId()));
    	}else{
    		model.put("isBizMember",false);
    	}
    	return new ModelAndView(Constants.JSON_VIEW,model);
    }

}
