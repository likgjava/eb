package com.gpcsoft.plugin.acegi;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.Authentication;
import org.springframework.security.AuthenticationException;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.ui.WebAuthenticationDetails;
import org.springframework.security.ui.webapp.AuthenticationProcessingFilter;
import org.springframework.security.userdetails.UsernameNotFoundException;

import com.gpcsoft.core.exception.BusinessException;
import com.gpcsoft.core.exception.ServiceException;
import com.gpcsoft.core.utils.DateUtil;
import com.gpcsoft.srplatform.auth.domain.User;
import com.gpcsoft.srplatform.common.exceptions.MaxErrorLogException;
import com.gpcsoft.srplatform.common.exceptions.RandErrorException;
import com.gpcsoft.srplatform.common.exceptions.UserPassWordErrorException;
import com.gpcsoft.srplatform.common.utils.SysInfo;
import com.gpcsoft.srplatform.logs.domain.LoginLogs;
import com.gpcsoft.srplatform.logs.service.LoginLogsService;

/**
 * 在用户登录时做处理，统计尝试登陆次数以及记录登录日志
 * @author xiaojf
 */
public class FrameAuthenticationProcessingFilter extends AuthenticationProcessingFilter {
	
	@Autowired(required=false)
	private LoginLogsService loginLogsService;
	
	//返回跳转路径
	private String defaultLoginUrl;
	private String changeUsrPassword;

	public String getChangeUsrPassword() {
		return changeUsrPassword;
	}


	public void setChangeUsrPassword(String changeUsrPassword) {
		this.changeUsrPassword = changeUsrPassword;
	}


	public String getDefaultLoginUrl() {
		return defaultLoginUrl;
	}


	public void setDefaultLoginUrl(String defaultLoginUrl) {
		this.defaultLoginUrl = defaultLoginUrl;
	}

	public static final String GCSOFT_SECURITY_CONTEXT_KEY = "GCSOFT_SECURITY_CONTEXT";

	
	public int getMaxLogNumber() {
		return SysInfo.getInstance().getMaxLogNumber();
	}

	public int getResetTime() {
		Integer resetTime=SysInfo.getInstance().getResetTime();
		if(resetTime==-1)
			return resetTime=resetTime*60;
		else
			return resetTime;
	}

	public void afterPropertiesSet() throws Exception {
		 super.afterPropertiesSet();
	 }

	/**
	 * 控制错误登录次数
	 * 登录前判断用户的已尝试登录次数，如果超过最大登录次数，抛出异常，否则提示用户剩余登录次数
	 * 
	 * @param request HttpServletRequest 可以获得登录的ip，获得登录的客户端session
	 * @param response HttpServletResponse
	 * @param failed AuthenticationException
	 * 
	 * @exception MaxErrorLogException 超过最大尝试数,IP被锁定
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void onUnsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws  UsernameNotFoundException, DataAccessException,UserPassWordErrorException,MaxErrorLogException {
		String ip = request.getRemoteAddr();
		//存放用户的登录信息，包括已尝试登录次数，最后尝试时间，最大登录次数，客户端ip
		Map logInfoMap = new HashMap();
    	logger.info("进入onUnsuccessfulAuthentication");
    	HttpSession session = request.getSession(true);
    	int logNumber =0;
		if(session.getAttribute(ip)!=null)
			logNumber =(Integer)(((Map)request.getSession().getAttribute(ip)).get("logNumber"));
		logNumber++;
		logInfoMap.put("logNumber", logNumber);
		logInfoMap.put("lastTryTime",new Date());
		logInfoMap.put("maxLogNumber",this.getMaxLogNumber());
		logInfoMap.put("ip",ip);
		session.setAttribute(ip, logInfoMap);
		int remainNum = this.getMaxLogNumber()-logNumber;
		//保存最大尝试次数和剩余次数
		request.setAttribute("maxLogNumber",this.getMaxLogNumber());
		request.setAttribute("remainNum",remainNum);
		logger.info("用户("+ip+")已尝试"+logNumber+"次，还有"+(this.getMaxLogNumber()-logNumber)+"次机会");
	}
	
	/** 
	 * 清除错误登录信息
	 * 从session中取得用户的登录信息，如果最后尝试时间加上重置时间大于当前时间，则把错误登录信息清除，允许重新登陆
	 * 
	 * @param request HttpServletRequest 可以获得登录的ip，获得登录的客户端session
	 * @param response HttpServletResponse
	 * 
	 * @throws AuthenticationException , IOException
	 * 
	 * @see org.springframework.security.ui.AbstractProcessingFilter#onPreAuthentication(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void onPreAuthentication(HttpServletRequest request, HttpServletResponse response)throws AuthenticationException, IOException {
		logger.debug("进入onPreAuthentication");
			/*
			 * 增加验证码认证    
			 */
			if(SysInfo.getInstance().isOpenValidateCode()){
				String rand = request.getParameter("j_rand")==null?"":request.getParameter("j_rand");
				if(request.getAttribute("isAttribute") != null) {
					rand = (String)request.getAttribute("j_rand");
				}
				
				String session_rand = request.getSession().getAttribute("j_rand") == null ? "": (String)request.getSession().getAttribute("j_rand");
				if(!rand.equals(session_rand)){
					throw new RandErrorException(messages.getMessage(
							"srplatform.auth.user.login_rand_error",
							"验证码错误!"));
				}
			}
			
        	String ip = request.getRemoteAddr(); //获得客户端ip
        	HttpSession session = request.getSession(true);
        	Map logInfoMap;

    		logInfoMap = (Map)session.getAttribute(ip);
	        if(logInfoMap!=null){
	        	Date lastLoginTime = (Date)logInfoMap.get("lastTryTime");
	        	if(lastLoginTime.getTime()+getResetTime()*1000<new Date().getTime()){
	        		session.removeAttribute(ip);
	        		logger.info("超过重置时间，session中用户错误登录信息清除成功");
	        		return;
	        	}else{
	        		int logNumber =(Integer)(logInfoMap.get("logNumber"));
	        		if(logNumber>=this.getMaxLogNumber()){
	        			logger.error("超过最大尝试数,IP被锁定！");
	        			throw new MaxErrorLogException("超过最大尝试数,IP被锁定！");
	        		}
	        	}
		    }
	}
	
	/**
	 * 登录成功前做一些处理
	 * 设定用户登陆时间，指定登录处理的Controller，记录登录日志，并将日志存放在session中
	 * 日志包括：用户ip、登录时间、用户对象
	 * 
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @param authResult Authentication
	 * 
	 * @exception ServiceException
	 * @exception BusinessException
	 * @throws IOException
	 * 
	 * @see org.springframework.security.ui.AbstractProcessingFilter#onSuccessfulAuthentication(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.Authentication)
	 */
	@Override
	protected void onSuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, Authentication authResult) throws IOException {
		logger.debug("进入onSuccessfulAuthentication");
		super.onSuccessfulAuthentication(request, response, authResult);
		request.getSession(true).removeAttribute(request.getRemoteAddr());   //移除客户端ip
		User user = (User)authResult.getPrincipal();
		
		//给用户设定 sessionId
		WebAuthenticationDetails details=(WebAuthenticationDetails) authResult.getDetails();
		String sessionId=details.getSessionId();
		user.setSessionId(sessionId);
		//设定用户登陆时间
		Date loginTime = new Date();
		user.setLoginTime(DateUtil.format(loginTime, DateUtil.hour24HMSPattern));
		
		user.setIp(request.getRemoteHost());
		
		request.getSession(true).setAttribute(FrameAuthenticationProcessingFilter.GCSOFT_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
		
		//记录登录日志
		LoginLogs log = new LoginLogs();

		log.setLgnFromIp(request.getRemoteAddr());
		log.setLgnInTime(loginTime);
		log.setUser(user);
		
		//用户对象存在且有效，未修改过密码
		if(user != null && user.getUsrChangePwd() == false){
			request.getSession().setAttribute("defaultLoginUrl", changeUsrPassword);
		}else{
			request.getSession().setAttribute("defaultLoginUrl", defaultLoginUrl);
		}
		try {
			log=loginLogsService.save(log); //将登陆日志放到数据库中
			request.getSession(true).setAttribute("LOGIN_LOGS", log); //将登陆日志放到session中
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * 放置user到session里
	 */
	@Override
	protected void successfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, Authentication authResult)
			throws IOException, ServletException {
		User user = (User)authResult.getPrincipal();
		request.getSession().setAttribute("currentUser", user);
		super.successfulAuthentication(request, response, authResult);
	}

}
