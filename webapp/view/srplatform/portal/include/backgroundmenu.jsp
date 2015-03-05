<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%><%@page import="com.gpcsoft.core.utils.DateUtil"%><%@page import="com.gpcsoft.srplatform.auth.domain.User"%><%@page import="com.gpcsoft.core.utils.DateUtil"%><%@page import="com.gpcsoft.plugin.acegi.AuthenticationHelper"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>阳光易购采购交易平台</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/plug-in/extjs/resources/css/ext-all.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/thems/default/sys.css"></link>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/thems/default/main.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/skin07/css/index.css"/>
<!--[if IE 6]>
<script type="text/javascript" src='<%=request.getContextPath()%>/view/resource/skin/thems/default/iepngfix/iepngfix_tilebg.js'></script>
<link href="<%=request.getContextPath()%>/view/resource/skin/thems/default/ie6.css" rel="stylesheet" type="text/css" />
<![endif]-->

<script type="text/javascript" src='<%=request.getContextPath()%>/view/resource/plug-in/extjs/ext-all.js'></script>
<script type="text/javascript" src='<%=request.getContextPath()%>/view/resource/scripts/util/menu.js'></script>
<script type="text/javascript" src='<%=request.getContextPath()%>/view/srplatform/portal/include/common.js'></script>
<script type="text/javascript" src='<%=request.getContextPath()%>/view/srplatform/portal/include/backgroundmenu.js'></script>
</head>
<body>
<%
	User user = AuthenticationHelper.getCurrentUser();
	int year = DateUtil.getYear();
	int month = DateUtil.getMonth() - 1;
	int date = DateUtil.getDate();
	int hour = DateUtil.getHour();
	int minute = DateUtil.getMinute();
	int second = DateUtil.getSecond();
%>


<script>
	var gpcsoftDate = new Date("<%=year%>","<%=month%>","<%=date%>","<%=hour%>","<%=minute%>","<%=second%>");//设定 当前 服 器时间 
</script>
<input type="hidden" id="initPath" value="<%=request.getContextPath()%>" />
<input type="hidden" id="ssousername" value="<%=session.getAttribute("ssousername") %>" />
<input type="hidden" id="roleType" value="<%=request.getAttribute("roleStr")%>" />
<input type="hidden" id="returnUrl" value="" />
<input type="hidden" id="hrefUrl" value=""/>
<input type="hidden" id="currentTab" value="0"/>
<!-- 供应商、采购人状态 -->
<input type="hidden" id="currSuppStatus" value="${currSuppStatus}"/>
<input type="hidden" id="currBuyStatus" value="${currBuyStatus}"/>


<!--网站导航 开始-->
<div class="siteNav">
  <p class="userInfo">
<span class="currentUser" id="loginUser">您好，<a href="javascript:void(0);" id="<%=user.getObjId()%>"><%=user.getUsName()%></a></span>&nbsp;<span id="logout"><a href="javascript:common.unlogin()">[退出]</a></span>
<span><a href="javascript:void(0);" id="stationMessage" onclick="myDesktop.showStationMessage()">站内信<span id="notReadMessageNum" num="0"></span></a></span><!--户信息-->
<span id="security" class="hidden"><span class="modifier">为了你的密码安全，请填写[</span><a href="javascript:void(0);" onclick="common.addSecurityQuestions();return false;">密保资料</a>]</span>
 </p>
  <ul class="quickMenu">
<li ><a class="ebidHome" href="<%=request.getContextPath()%>/IndexViewController.do?method=index">返回首页</a></li>
<authz:authorize ifAnyGranted="ztb">
<li ><a class="dzcghome" id="numberAreaZT" href="javascript:void(0);">电子招标室</a></li>
</authz:authorize>
<li><a href="#" title="客户意见" class="opinion">反馈意见</a></li>
 <%@ include file="/view/srplatform/portal/include/header_tool.jsp" %>
 </ul>
</div>
<!--网站导航 结束--> 
<!--网站基础信息 开始-->
<div class="branding"><a href="<%=request.getContextPath()%>/IndexViewController.do?method=index"><img src="<%=request.getContextPath()%>/view/resource/skin/thems/default/img/logo.gif" style="width: 200px; height: 58px;"/></a></div>
<!--网站基础信息 结束--> 
<!--主导航菜单 开始-->
<div id="navMain" class="sysNavMain">
  <ul>
  </ul>
</div>
<!--主导航菜单 结束--> 

</body>
</html>