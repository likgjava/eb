package com.gpcsoft.smallscale.business.controller;

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

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.smallscale.business.domain.ForumTopic;
import com.gpcsoft.smallscale.business.service.CommunityService;
import com.gpcsoft.smallscale.business.service.ForumTopicService;
import com.gpcsoft.srplatform.auth.domain.User;

/**
  * @gpcsoft.view value="forumTopicFormView"
  *  url="view/smallscale/business/forum_topic_add.jsp"
  *  
  *  @gpcsoft.view value="forumTopicShowView"
  *  url="view/smallscale/business/forum_topic_reply.jsp"
  *  
  *  @gpcsoft.view value="forumTopicInfo"
  *  url="view/smallscale/business/forum_topic_detail.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={ForumTopic.class})
@RequestMapping("/ForumTopicController.do")//页面请求路径,可修改
public class ForumTopicController extends AnnotationMultiController<ForumTopic> {

	@Autowired(required=true) @Qualifier("forumTopicServiceImpl")
	private ForumTopicService forumTopicService;
	
	@Autowired(required=true) @Qualifier("communityServiceImpl")
	private CommunityService communityService;

	/**
	 * Description :  跳转到 新增/修改 帖子主题页面
	 * Create Date: 2011-2-17下午05:03:34 by dongcl  Modified Date: 2011-2-17下午05:03:34 by dongcl
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params = "method=toAddTopicView")
	public ModelAndView toAddTopicView(String communityId,String objId) throws Exception{
		Map<String, Object> model = new HashMap<String, Object>();		
		ForumTopic forumTopic = new ForumTopic();
		if(null != objId && !"".equals(objId)){
			forumTopic=forumTopicService.get(objId);
		}
		if(null != communityId && !"".equals(communityId)){
			forumTopic.setCommunity(communityService.get(communityId));
		}
		model.put("forumTopic", forumTopic);
		return new ModelAndView("forumTopicFormView",model);
	}
	
	/**
	 * Description :  保存帖子主题
	 * Create Date: 2011-2-17下午05:03:34 by dongcl  Modified Date: 2011-2-17下午05:03:34 by dongcl
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params = "method=saveTopic")
	public ModelAndView saveTopic(HttpServletRequest request, ForumTopic forumTopic,SessionStatus status) throws Exception{
		User user = AuthenticationHelper.getCurrentUser(true);		
		if(user.getOrgInfo()!=null){
			OrgInfo orginfo = (OrgInfo)user.getOrgInfo();
			forumTopic.setOrginfo(orginfo);
		}
		return super.save(forumTopic, request, status);
		
	}
	
	/** 
	 * Description :  是否置顶(isTop),是否显示(isShow),是否精华(isElite)
	 * Create Date: 2011-2-25下午14:00:27 by zhongq  Modified Date: 2011-2-25下午14:00:27 by zhongq
	 * @param   setType
	 * @return  
	 * @throws Exception 
	 * @Exception   
	 */
	@RequestMapping(params="method=updateTopOrShowOrElite")
	public ModelAndView updateTopOrShowOrElite(String objId,String isStatusVale,String setType) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		
		param.put("forumTopicrId", objId);//主题Id
		param.put("isStatusVale", isStatusVale);//当前状态值
		param.put("setType", setType);//操作类型
		
		Boolean relsut = forumTopicService.updateTopOrShowOrElite(param);
		if(relsut){
			model.put(Constants.JSON_RESULT, "success");
		}
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	/**
	 * Description :  删除帖子主题,并删除此主题下的所有的回复帖子
	 * Create Date: 2011-3-8下午04:41:55 by zhaojf  Modified Date: 2011-3-8下午04:41:55 by zhaojf
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params="method=removeTopicAndReply")
	public ModelAndView removeTopicAndReply(String objId) throws Exception{
		forumTopicService.removeTopicAndReply(objId);
		return new ModelAndView(Constants.JSON_VIEW);
	}
}
