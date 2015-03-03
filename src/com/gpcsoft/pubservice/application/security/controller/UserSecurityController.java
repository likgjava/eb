package com.gpcsoft.pubservice.application.security.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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

import com.gpcsoft.bizplatform.base.common.util.StringUtils;
import com.gpcsoft.core.Constants;
import com.gpcsoft.core.web.controller.AnnotationMultiController;
import com.gpcsoft.plugin.acegi.AuthenticationHelper;
import com.gpcsoft.pubservice.application.security.domain.UserSecurity;
import com.gpcsoft.pubservice.application.security.service.UserSecurityService;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.auth.service.UserService;
import com.gpcsoft.srplatform.baseData.domain.Dictionary;
import com.gpcsoft.srplatform.baseData.service.DictionaryService;

/** 
  *  Comments: <strong>UserSecurityController</strong>            		
  *	 <br/>		        																							
  *  <br/>CopyRright (c)2008-xxxx:   珠海政采软件技术有限公司    		
  *  <br/>Project:   pubservice                    					          
  *  <br/>Module ID: 公共服务平台     		
  *  <br/>Create Date：2010-9-2 上午09:39:16 by zhangyd    					                            
  *  <br/>Modified Date:  2010-9-2 上午09:39:16 by zhangyd                                   
  * @gpcsoft.view value="userSecurityFormView"
  *  url="view/pubservice/application/security/user_security_form.jsp"
  * @gpcsoft.view value="userSecurityListView"
  *  url="view/pubservice/application/security/user_security_list.jsp"
  * @gpcsoft.view value="userSecurityUpdateView"
  *  url="view/pubservice/application/security/user_security_update.jsp"
  * @gpcsoft.view value="userSecurityStep1View"
  *  url="view/pubservice/application/security/security_step1.jsp"
  * @gpcsoft.view value="userSecuritySettingView"
  *  url="view/pubservice/application/security/security_setting.jsp"
  * @gpcsoft.view value="userSecurityFinishView"
  *  url="view/pubservice/application/security/security_finish.jsp"
  *<p>@since 0.5
  *   @version: 0.5 
  */ 
@Controller//标识为控制器
@Scope("request")
@SessionAttributes(types={UserSecurity.class})
@RequestMapping("/UserSecurityController.do")//页面请求路径,可修改
public class UserSecurityController extends AnnotationMultiController<UserSecurity> {

	@Autowired(required=true) @Qualifier("userSecurityServiceImpl")
	private UserSecurityService userSecurityService;
	
	@Autowired(required=true) @Qualifier("dictionaryServiceImpl")
	private DictionaryService dictionaryService;
	
	@Autowired(required=true) @Qualifier("userServiceImpl")
	private UserService userService;
	
	/** 
	 * Description :  添加密保
	 * Create Date: 2010-9-3上午10:17:00 by zhangyd  Modified Date: 2010-9-3上午10:17:00 by zhangyd
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=addSecurity")
	public ModelAndView addSecurity(HttpServletRequest request){
		Map<String, Object> model = new HashMap<String, Object>(); 
		User user=AuthenticationHelper.getCurrentUser(true);
		List<UserSecurity> userSecurityList=new ArrayList<UserSecurity>();
		for(int i=1;i<=3;i++){
			Dictionary dictionary=dictionaryService.get(request.getParameter("dictionary"+i));
			UserSecurity userSecurity=new UserSecurity();
			userSecurity.setQuestion(dictionary);
			userSecurity.setUser(user);
			userSecurity.setAnswer(request.getParameter("answer"+i));
			userSecurity.setCreateTime(new Date());
			userSecurityList.add(userSecurity);
		}
		userSecurityService.save(userSecurityList);
		model.put(Constants.JSON_RESULT,"success");
		return new ModelAndView(Constants.JSON_VIEW, model);
		
	}
	
	/** 
	 * Description :  验证用户是否已填写过密保资料
	 * Create Date: 2010-9-3上午10:18:02 by zhangyd  Modified Date: 2010-9-3上午10:18:02 by zhangyd
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=checkSecurity")
	public ModelAndView checkSecurity(HttpServletRequest request){
		Map<String, Object> model = new HashMap<String, Object>(); 
		String userId=request.getParameter("userId");
		List<UserSecurity> userSecurityList=userSecurityService.getSecurityByUserId(userId);
		if(userSecurityList.size()!=0){
			model.put("userSecurityList", userSecurityList);
			model.put(Constants.JSON_RESULT,"success");
		}else{
			model.put(Constants.JSON_RESULT,"fail");
		}
	    return new ModelAndView(Constants.JSON_VIEW, model);
		
	}
	
	/** 
	 * Description :  列出密保问题
	 * Create Date: 2010-9-3上午10:19:43 by zhangyd  Modified Date: 2010-9-3上午10:19:43 by zhangyd
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=toSecurityForm")
	public ModelAndView toSecurityForm(HttpServletRequest request){
		Map<String, Object> model = new HashMap<String, Object>(); 
		User user=AuthenticationHelper.getCurrentUser(true);
		List<Dictionary> dictionaryList=new ArrayList<Dictionary>();
		dictionaryList=dictionaryService.getDictionaryByDictType("security");//通过数据字典类型取得数据字典值402886dc2acaeb8b012acc5dd6600038
		model.put("dictionaryList",dictionaryList);
		model.put("user",user);
	    return new ModelAndView("userSecurityFormView", model);
		
	}
	
	/** 
	 * Description :  返回用户的密保资料
	 * Create Date: 2010-9-3上午10:20:47 by zhangyd  Modified Date: 2010-9-3上午10:20:47 by zhangyd
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=listQuestions")
	public ModelAndView listQuestions(HttpServletRequest request,String userId){
		List<UserSecurity> userSecurityList=userSecurityService.getSecurityByUserId(userId);
		Map<String,Object> model = new HashMap<String,Object>();
		model.put("userSecurityList", userSecurityList);
		model.put("userId", userId);
		return new ModelAndView("userSecuritySettingView",model);
	}
	
	/** 
	 * Description :  验证密保问题答案
	 * Create Date: 2010-9-3下午01:25:06 by zhangyd  Modified Date: 2010-9-3下午01:25:06 by zhangyd
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(params="method=validateAnswer")
	public ModelAndView validateAnswer(HttpServletRequest request,String userId,String answer){
		List<UserSecurity> userSecurityList=userSecurityService.getSecurityByUserId(userId);//查出用户填写的所有密保信息
		Map<String,Object> model = new HashMap<String,Object>();
		String questionId = request.getParameter("questionId");
		answer = StringUtils.ascii2Native(answer);
		boolean flag=false;
		for (Iterator iterator = userSecurityList.iterator(); iterator.hasNext();) {
			UserSecurity userSecurity = (UserSecurity) iterator.next();
			if(userSecurity.getQuestion().getObjId().equals(questionId)&& userSecurity.getAnswer().equals(answer)){//找到其中有相等的即验证完成
				flag=true;
				break;
			}
		}
		if(flag){
			model.put(Constants.JSON_RESULT, "success");
		}else{
			model.put(Constants.JSON_RESULT, "failure");
		}
		model.put("userId", userId);
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
	
	/** 
	 * Description :  把密保资料发送给用户
	 * Create Date: 2010-9-3下午01:58:25 by zhangyd  Modified Date: 2010-9-3下午01:58:25 by zhangyd
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params="method=sendEmailToUser")
	public ModelAndView sendEmailToUser(HttpServletRequest request,String userId)throws Exception{
		userService.updateResetPassword(userId);//重设密码并向用户注册邮箱发送邮件
		Map<String,Object> model = new HashMap<String,Object>();
        model.put("flag", "yes");
        return new ModelAndView("jsonView", model);
	}
	
	/** 
	 * Description :  找回密码过程跳转
	 * Create Date: 2010-9-3上午10:06:07 by zhangyd  Modified Date: 2010-9-3上午10:06:07 by zhangyd
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=getPassword")
	 public ModelAndView getPassword( HttpServletRequest request,String step,String userId) throws Exception {
			Map<String, Object> model = new HashMap<String, Object>(); 
			String usName=request.getParameter("userName");
			User user=userSecurityService.getUserByUsName(usName);//根据用户名查找是否由此用户
			List<UserSecurity> userSecurityList=null;
			String viewName=null;//跳转路径
			if(StringUtils.hasLength(step)){
				if(step.equals("1")){//to验证用户名
					viewName="userSecurityStep1View";
				}
				if(step.equals("2")){//to填写信息
					if(user!=null){//用户存在
						userSecurityList=userSecurityService.getSecurityByUserId(user.getObjId());//查看此用户是否填写过密保
						if(userSecurityList.size()>0){
							model.put("user", user);
							model.put(Constants.JSON_RESULT,"success");
						}else{
							model.put("user", user);
							model.put(Constants.JSON_RESULT,"noData");
						}
					}else{//用户不存在
						model.put(Constants.JSON_RESULT,"fail");
					}
					viewName=Constants.JSON_VIEW;
				}
				if(step.equals("3")){//to提交完成
					model.put("userId", userId);
					viewName="userSecurityFinishView";
				}
			}
			if(model!=null){
				return new ModelAndView(viewName,model);
			}else{
				return new ModelAndView(viewName);
			}
	 }
	
	/** 
	 * Description : 验证邮箱 
	 * Create Date: 2010-11-16下午07:09:44 by yucy  Modified Date: 2010-11-16下午07:09:44 by yucy
	 * @param   
	 * @return  
	 * @Exception   
	 */
	@RequestMapping(params = "method=checkEmail")
	public ModelAndView checkEmail( HttpServletRequest request) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>(); 
		String email =  userService.get(request.getParameter("userId")).getEmp().getEmail();
		if(email!=null&&email.equals(request.getParameter("email"))){
			userService.updateResetPassword(request.getParameter("userId"));//重设密码并向用户注册邮箱发送邮件
			model.put(Constants.SUCCESS, true);
		}else{
			model.put(Constants.FAILURE, true);
		}
		return new ModelAndView(Constants.JSON_VIEW,model);
	}
}
