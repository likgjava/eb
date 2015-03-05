<%@ page pageEncoding="utf-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<jsp:directive.page import="com.gpcsoft.core.utils.DateUtil" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="my">
<head>
<title>采购执行交易系统</title>
<c:import url="/view/srplatform/common/init_es.jsp">
	<c:param name="isLastLoadPage">
		true
	</c:param>
</c:import>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/portal/index_es.js" ></script>

<%
	
	int year = DateUtil.getYear();
	int month = DateUtil.getMonth();
	int date = DateUtil.getDate();
	int hour = DateUtil.getHour();
	int minute = DateUtil.getMinute();
	int second = DateUtil.getSecond();
%>
<!-- 显示系统时间 -->
<script>
	var gpcsoftDate = new Date("<%=year%>","<%=month%>","<%=date%>","<%=hour%>","<%=minute%>","<%=second%>");//设置当前服务器时
</script>
</head>
<body>
<input type="hidden" name="absolutedPath" value="<%=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/"%>" id="absolutedPath"/>
 <input type="hidden" name="initPath" value="<%=request.getContextPath()%>" id="initPath"/>
<!--页面容器-->
<noscript>
  <div class="userTips">
    <p>尊敬的用户，您好！您使用的浏览器不支持或禁用JavaScript脚本。请使用支持JavaScript脚本的浏览器或启用浏览器JavaScript脚本功能。</p>
    <p>如有任何疑问或需要帮助，请<a href="http://www.gpcsoft.com" target="_blank" title="珠海政采软件技术有限公司 技术支持">与我们联系</a>。</p>
  </div>
</noscript>
<!--浏览器检测 结束-->
<!--页面容器 开始-->
<style>
#dhtmlScheduler span {
    background: url("<%=request.getContextPath()%>/view/resource/skin/sysicon/16/application_view_detail.png") no-repeat scroll 0 0 transparent;
    vertical-align: middle;
}
</style>
<div id="sysContainer">
  <div id="sysBranding">
    <h1><span class="webName"><fmt:message key="srplatform.name" /><!--系统名称--></span></h1>
    <p><span class="versionSys"><fmt:message key="srplatform.versionSys" /><!--系统版本--></span> <span class="dataSys"><fmt:message key="srplatform.dataSys" /><!--系统发布时间--></span></p>
    <p class="sysSlogan">专注·所以专业 极致·因此共赢<!--系统口号--></p>
  </div>
  <div id="navMain">
    <ul>
      <!--主导航菜单-->
    </ul>
  </div>  
  <div id="loginInfo">
  	<!--登录信息-->
    <p><span class="currentUser" id="currentUser"><!--户信息--></span><!--<span class="hello">问候语</span>--></p>
    <p class="dateTime"><span class="nowDate"><!--当前日期--></span><span class="nowTime" id="nowTime"><!--当前时间--></span></p>
	<p>
        <a title="修改密码" id="updateMyPsw" class="sysicon siApp_resetPwd"><span>修改密码</span></a>
        <a title="查看个人信息" id="updateMyInfo" class="sysicon siInfo"><span>个人信息</span></a>
    </p>
  </div>
  <div id="sysTools">
    <!--系统工具-->
    <ul>
    	<li id="navSubControl" title="边栏菜单控制 快捷键 F9" ><span>侧栏</span></li>
      <li id="screenControl" title="界面显示控制 快捷键 F8" style="display: none"><span>全屏</span></li>
    	<li id="myDesktop" title="转到我的桌面"><span>桌面</span></li>
    	<li id="goToIndex" title="转到系统首页"><span>首页</span></li>
    	<authz:authorize ifAnyGranted="xejy"><li id="goToXYCG" title="转到小额采购平台"><span>电子采购室</span></li></authz:authorize>
      <li id="refreshBtn" title="刷新当前页 可使用快捷键 F5" style="display: none"><span>刷新</span></li>
      <li id="exitBtn" title="退出系统"><span>退出</span></li>
    </ul>
  </div>
  <div id="sysContent">
    <!--系统内容-->
    <div id="contentSub"><!--辅助内容--></div>
    <div id="contentMain">
    	<!--主内容-->
      <div id="conTitle" class="hidden">
      	<div class="navCurrent"><!--面包屑导航条--></div>
        <h3><span><!--功能点标题--></span></h3>
      </div>
      <div id="conBody"><!--功能页内容--></div>
    </div>
    <div id="contentSupp"><!--补充内容--></div>
  </div>
  <div id="sysInfo">
    <!--系统信息-->
    <p>&copy; 2007-2010 0.52 版权所有</p>
  </div>
</div>
<!--页面容器 结束-->
<input type="hidden" id="returnUrl" />
<div id="extraDiv">
	<!--扩展用容器，用于与内容无关的装饰性扩展-->
	<div id="extraDiv1"></div>
  <div id="extraDiv2"></div>
  <div id="extraDiv3"></div>
  <div id="extraDiv4"></div>
  <div id="extraDiv5"></div>
  <div id="extraDiv6"></div>
</div>
</body>
</html>

