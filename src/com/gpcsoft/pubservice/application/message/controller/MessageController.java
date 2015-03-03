package com.gpcsoft.pubservice.application.message.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gpcsoft.bizplatform.organization.enumeration.OrganizationEnum;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.ModelAndView;
import com.gpcsoft.core.Constants;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.pubservice.application.message.service.MessageService;
import com.gpcsoft.pubservice.application.message.domain.Message;
import com.gpcsoft.srplatform.auth.domain.Role;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.auth.manager.RoleManager;
import com.gpcsoft.srplatform.auth.service.RoleService;
import com.gpcsoft.srplatform.auth.service.UserService;

/**
  * @gpcsoft.view value="messageFormView"
  *  url="view/pubservice/application/message/message_form.jsp"
  *  
  * @gpcsoft.view value="messageTreeFormView"
  *  url="view/pubservice/application/message/message_tree_form.jsp"
  *  
  * @gpcsoft.view value="messageListView"
  *  url="view/pubservice/application/message/message_list.jsp"
  *  
  * @gpcsoft.view value="messageDetailView"
  *  url="view/pubservice/application/message/message_detail.jsp"
  *  
  * @gpcsoft.view value="toCreateMessageView"
  *  url="view/pubservice/application/message/message_form.jsp"
  *  
  * @gpcsoft.view value="toMessageDetailView"
  *  url="view/pubservice/application/message/message_detail.jsp"
  *  
  * @gpcsoft.view value="toMessageListView"
  *  url="view/pubservice/application/message/message_list.jsp"
  *  
  * @gpcsoft.view value="notReadMessageTipView"
  *  url="view/srplatform/portal/desk/not_read_message_tip.jsp"
  */
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={Message.class})
@RequestMapping("/MessageController.do")//页面请求路径,可修改
public class MessageController extends AnnotationMultiController<Message> 
{
	@Autowired(required=true) @Qualifier("messageServiceImpl")
	private MessageService messageService;
	
	@Autowired(required=true) @Qualifier("userServiceImpl")
	private UserService userService;
	
	@Autowired(required=true) @Qualifier("roleServiceImpl")
	private RoleService roleService;
	
	@Autowired(required=true) @Qualifier("roleManagerImpl")
	private RoleManager roleManagerImpl;

	/** 
	 * Description :  保存新建的系统站内信内容
	 * Create Date: 2010-9-27上午08:18:16 by likg  Modified Date: 2010-9-27上午08:18:16 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=saveSystemMessage")
	public ModelAndView saveSystemMessage(Message message, String receivers, String receiverType_objIds) throws Exception
	{
		Map<String, Object> model = new HashMap<String, Object>();
		
		if("to_all".equals(receivers)) // 发送到所有人
		{
			List<User> users = userService.getAll();
			
			if(users!=null && users.size()!=0)
			{
				String[] objIds = new String[users.size()];
				for(int i=0; i<objIds.length; i++)
				{
					objIds[i] = users.get(i).getObjId();
				}
				
				messageService.saveSystemMessage(message, objIds, receivers, null);
			}
		}
		else if("to_role".equals(receivers)) // 发送到指定角色的用户
		{
			StringBuilder hql = new StringBuilder();
			hql.append("select distinct r from Role r join fetch r.users where r.objId in (");
			
			String[] receiverRoleObjIds = receiverType_objIds.split(",");
			for(String str : receiverRoleObjIds)
			{
				hql.append("'" + str + "',");
			}
			hql.deleteCharAt(hql.length()-1).append(")");
			
			List<Role> roles = roleService.findByHql(hql.toString(), new Object[]{});
			Set<User> allUsers = new HashSet<User>();
			for(Role role : roles)
			{
				List<User> users = new ArrayList<User>(role.getUsers());
				if(users!=null && users.size()!=0)
				{
					String[] objIds = new String[users.size()];
					for(int i=0; i<objIds.length; i++)
					{
						User user = users.get(i);
						if(!allUsers.contains(user))
						{
							objIds[i] = user.getObjId();
						}
					}
					messageService.saveSystemMessage(message, objIds, receivers, role.getChName());
					allUsers.addAll(role.getUsers());
				}
			}
		}
		else if("to_user".equals(receivers)) // 发送到指定的用户
		{
			String[] receiverObjIds = receiverType_objIds.split(",");
			messageService.saveSystemMessage(message, receiverObjIds, receivers, null);
		}
		
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  保存新建的私人站内信内容
	 * Create Date: 2010-9-27上午08:18:16 by likg  Modified Date: 2010-9-27上午08:18:16 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=saveMessage")
	public ModelAndView saveMessage(Message message) throws Exception
	{
		Map<String, Object> model = new HashMap<String, Object>();
		
		//群发私人站内信
		if(message.getReceiver()!=null && !"".equals(message.getReceiver()))
		{
			String[] receiverObjIds = message.getReceiver().split(",");
			messageService.saveMessageBatch(message, receiverObjIds);
		}
		else //一发私人站内信
		{
			String hql = "from User u where u.usName = ?";
			List<User> users = userService.findByHql(hql, new Object[]{message.getReceiverName()});
			if(users!=null && users.size()>0)
			{
				message.setReceiver(users.get(0).getObjId());
				messageService.saveMessage(message);
			}
		}
		
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  跳转到新建站内信视图
	 * Create Date: 2010-9-27上午08:18:16 by likg  Modified Date: 2010-9-27上午08:18:16 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=toCreateMessageView")
	public ModelAndView toCreateMessageView(String objId, String type) throws Exception
	{
		Map<String, Object> model = new HashMap<String, Object>();
		Message message = new Message();
		
		model.put("messageType", type);
		model.put("message", message);
		model.put("success", true);
		return new ModelAndView("toCreateMessageView", model);
	}
	
	/** 
	 * Description :  返回站内信详细内容视图
	 * Create Date: 2010-9-27上午08:18:16 by likg  Modified Date: 2010-9-27上午08:18:16 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=toMessageDetailView")
	public ModelAndView toMessageDetailView(String objId) throws Exception
	{
		Map<String, Object> model = new HashMap<String, Object>();
		
		Message message = null;
		String view = "";
		
		if(objId!=null && !"".equals(objId))
		{
			message = messageService.get(objId);
			
			//修改阅读状态，标记为已读
			if(!message.getIsRead())
			{
				message.setIsRead(true);
				message.setReader(new Date());
				messageService.save(message);
			}
			
			model.put("message", message);
			view = "toMessageDetailView";
		}
		else
		{
			view = "messageListView";
		}
		
		return new ModelAndView(view, model);
	}
	
	/** 
	 * Description :  跳转到站内信列表视图
	 * Create Date: 2010-9-27上午08:18:16 by likg  Modified Date: 2010-9-27上午08:18:16 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=toMessageListView")
	public ModelAndView toMessageListView(HttpSession session) throws Exception
	{
		Map<String, Object> model = new HashMap<String, Object>();
		
		//把用户名保存到session中
		session.setAttribute("username", AuthenticationHelper.getCurrentUser(true).getUsername());
		model.put("currentUserId", AuthenticationHelper.getCurrentUser(true).getObjId());
		
		User user = AuthenticationHelper.getCurrentUser(true);
		//判断当前用户是否为manager
		if(roleManagerImpl.isHasThisRole(user.getObjId(), OrganizationEnum.ROLE_MANAGER))
		{
			session.setAttribute("isManager", true);
		}
		else
		{
			session.setAttribute("isManager", false);
		}
		
		return new ModelAndView("toMessageListView", model);
	}
	
	/** 
	 * Description :  获得当前用户的【未读的和未提示的】站内信数目
	 * Create Date: 2010-9-30上午08:18:16 by likg  Modified Date: 2010-9-30上午08:18:16 by likg
	 * @param
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=getMessageNum")
	public ModelAndView getMessageNum() throws Exception
	{
		Map<String, Object> model = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		
		Map<String, Long> messageNumMap = messageService.getMessageNum(AuthenticationHelper.getCurrentUser(true).getObjId(), param);
		model.put("notReadMessageNum", messageNumMap.get("notReadMessageNum"));
		model.put("notTipMessageNum", messageNumMap.get("notTipMessageNum"));
		
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
	
	/** 
	 * Description :  获得当前用户的未提示的站内信
	 * Create Date: 2010-11-19上午09:09:07 by likg  Modified Date: 2010-11-19上午09:09:07 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=getNotTipMessage")
	public ModelAndView getNotTipMessage() throws Exception
	{
		Map<String, Object> model = new HashMap<String, Object>();
		
		List<Message> notTipMessage = messageService.getNotTipMessage(AuthenticationHelper.getCurrentUser(true).getObjId());
		messageService.updateMessageTipStatus(AuthenticationHelper.getCurrentUser(true).getObjId());
		model.put("notTipMessage", notTipMessage);
		model.put("notTipMessageNum", (notTipMessage==null ? 0 : notTipMessage.size()));
		
		return new ModelAndView("notReadMessageTipView", model);
	}
	
	/** 
	 * Description :  修改站内信的阅读状态
	 * Create Date: 2010-12-8下午05:42:36 by likg  Modified Date: 2010-12-8下午05:42:36 by likg
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=updateMessage")
	public ModelAndView updateMessage(String objId) throws Exception
	{
		Map<String, Object> model = new HashMap<String, Object>();
		
		Message message = messageService.get(objId);
		message.setIsRead(true);
		message.setIsTip(true);
		messageService.save(message);
		
		return new ModelAndView(Constants.JSON_VIEW, model);
	}
}
