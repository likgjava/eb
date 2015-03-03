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
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.gpcsoft.bizplatform.organization.domain.OrgInfo;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.model.GpcBaseObject;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.smallscale.business.domain.ForumReply;
import com.gpcsoft.smallscale.business.service.ForumReplyService;
import com.gpcsoft.srplatform.auth.domain.Attachment;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.auth.service.AttachmentService;
import com.gpcsoft.srplatform.common.utils.AttachmentUtil;

/**
 *  @gpcsoft.view value="toTopicReplyDetailView"
 *  url="view/smallscale/business/forum_topic_reply_detail.jsp"
 *  @gpcsoft.view value="toTopicReplyDiv1View"
 *  url="view/smallscale/business/forum_topic_reply_div_1.jsp"
 */

@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={ForumReply.class})
@RequestMapping("/ForumReplyController.do")//页面请求路径,可修改
public class ForumReplyController extends AnnotationMultiController<ForumReply> {

	@Autowired(required=true) @Qualifier("forumReplyServiceImpl")
	private ForumReplyService forumReplyService;
	
	@Autowired(required=true) @Qualifier("attachmentServiceImpl")
	private AttachmentService attachmentService;
	
	/**
	 * Description :  发表帖子评论
	 * Create Date: 2011-2-17下午05:03:34 by dongcl  Modified Date: 2011-2-17下午05:03:34 by dongcl
	 * @param   
	 * @return  
	 * @Exception
	 */
	@RequestMapping(params = "method=saveTopicReply")
	public ModelAndView saveTopicReply(HttpServletRequest request, ForumReply forumReply,SessionStatus status) throws Exception{	
		//处理附件
		CommonsMultipartFile file = null;
		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)request;
			file = (CommonsMultipartFile)multipartRequest.getFile("attachmentFile");
			if(file!=null && file.getSize()!=0){
				Object o=AttachmentUtil.uploadFile(request,"attachmentFile");
				if(o instanceof GpcBaseObject){
					Attachment attachment = (Attachment)o;
					attachmentService.saveAttachment((Attachment)o);
					forumReply.setAttachment(attachment.getObjId());
				}
			}
		}catch(Exception de) {
			de.printStackTrace();
		 }
		
		//设置组织机构
		User user = AuthenticationHelper.getCurrentUser(true);
		if(user.getOrgInfo()!=null){
			OrgInfo orginfo = (OrgInfo)user.getOrgInfo();
			forumReply.setOrginfo(orginfo);
			forumReply.setRateName(orginfo.getOrgName());
		}
		
		forumReplyService.save(forumReply);
		status.setComplete();
		return new ModelAndView(Constants.JSON_VIEW);
		
	}

    /**
     * Description :  更改帖子的显示状态(true：显示,false：不显示)
     * Create Date: 2011-3-8下午02:31:41 by zhaojf  Modified Date: 2011-3-8下午02:31:41 by zhaojf
     * @param   
     * @return  
     * @Exception
     */
    @RequestMapping(params="method=isUnfurlTopicReply")
    public ModelAndView isUnfurlTopicReply(String objId,String isShow) throws Exception{
    	 Map<String, Object> model = new HashMap<String, Object>();
    	 Map<String, Object> param = new HashMap<String, Object>();
    	 param.put("objId", objId);
    	 param.put("isShow", isShow);
    	 Boolean result = forumReplyService.isUnfurlTopicReply(param);
    	 if(result){
    		 model.put(Constants.JSON_RESULT, "success");
    	 }
    	return new ModelAndView(Constants.JSON_VIEW,model);
    }
    
    /**
     * Description :  帖子批量删除
     * Create Date: 2011-3-8下午04:16:22 by zhaojf  Modified Date: 2011-3-8下午04:16:22 by zhaojf
     * @param   
     * @return  
     * @Exception
     */
    @RequestMapping(params="method=removeForumReplyArray")
    public ModelAndView removeForumReplyArray(String forumReplyIds) throws Exception{
    	String[] forumIds = forumReplyIds.split(",");
    	forumReplyService.remove(forumIds, ForumReply.class);
    	return new ModelAndView(Constants.JSON_VIEW);
    }

}
