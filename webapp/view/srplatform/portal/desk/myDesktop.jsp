<%@ page pageEncoding="UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%><%@page import="com.gpcsoft.core.utils.DateUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>阳光易购采购交易平台</title>
<c:import url="/view/srplatform/common/init.jsp">
	<c:param name="isLastLoadPage">
		true
	</c:param>
</c:import>
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
<script type="text/javascript" src='<%=request.getContextPath()%>/view/srplatform/portal/desk/myDesktop.js'></script>
</head>
<body>
<%
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
<input type="hidden" id="roleType" value="<%=request.getSession().getAttribute("roleStr")%>" />
<input type="hidden" id="returnUrl" value="" />
<input type="hidden" id="hrefUrl" value=""/>
<input type="hidden" id="currentTab" value="0"/>
<!-- 供应商、采购人状态 -->
<input type="hidden" id="currSuppStatus" value="${currSuppStatus}"/>
<input type="hidden" id="currBuyStatus" value="${currBuyStatus}"/>

<!--页面容器 开始-->
<div id="container"> 
  <!--头部容器 开始-->
  <div class="header"> 
    <!--网站导航 开始-->
    <div class="siteNav">
      <p class="userInfo">
	  	<span class="currentUser" id="loginUser">您好，<a href="javascript:void(0);" id="${user.objId}">${user.usName}</a></span>&nbsp;<span id="logout"><a href="javascript:common.unlogin()">[退出]</a></span>
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
    <div class="branding"><a href="<%=request.getContextPath()%>/IndexViewController.do?method=index"><img style="width: 200px; height: 58px;" src="<%=request.getContextPath()%>/view/resource/skin/thems/default/img/logo.gif"/></a></div>
    <!--网站基础信息 结束--> 
    <!--主导航菜单 开始-->
    <div id="navMain" class="sysNavMain">
      <ul>
      </ul>
    </div>
    <!--主导航菜单 结束--> 
  </div>
  <!--头部容器 结束--> 
	<!--主要内容容器 开始-->
	<div class="page" style="min-height:500px!important;">
		<div class="gridBox">
			<!-- 三级菜单 -->
			<div class="grid16_3 hidden" id="menuList">
				<div class="menuList"><ul></ul></div>
			</div>

			<div class="grid16_16" id="conBody"></div>
    	</div>
	</div>
	<!--主要内容容器 结束--> 
  
  <!--底部容器 开始-->
  <div class="footer">
    <%@ include file="/view/srplatform/portal/include/foot.jsp" %>
  </div>
  <!--底部容器 结束--> 
</div>
<!--页面容器 结束--> 
<!--扩展用容器，用于与内容无关的装饰性扩展-->
<div id="extraDiv">
  <div id="extraDiv1"></div>
  <div id="extraDiv2"></div>
  <div id="extraDiv3"></div>
  <div id="extraDiv4"></div>
  <div id="extraDiv5"></div>
  <div id="extraDiv6"></div>
</div>
</body>
</html>
