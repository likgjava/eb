<%@ page contentType="text/html;charset=UTF-8"%><%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@page import="com.gpcsoft.core.utils.DateUtil"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>阳光易购采购交易平台</title>

<c:import url="/view/srplatform/common/init.jsp">
	<c:param name="isLastLoadPage">
		true
	</c:param>
</c:import>

<!--JS-->
<script type="text/javascript" src='<%=request.getContextPath()%>/view/srplatform/portal/include/common.js'></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/login/login.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/view/srplatform/login/sha1.js"></script>
</head>
<body>
<div id="container">
	<input type="hidden" id="initPath" value="<%=request.getContextPath()%>" />
	<!--头部开始 -->
	<div class="header">
		<%@ include file="/view/srplatform/portal/include/main_header_index.jsp" %>
	</div>
	<!--CSS-->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/view/resource/skin/thems/default/login.css" />
	<!--头部结束-->
	<!--主要内容 开始-->
	<div id="sysContent" class="page">
		<div id="contentSub"></div>
		<div id="contentMain">
			<div id="userLogin">
				<ul><li><a href="#commonLogin"><span>普通登录</span></a></li></ul>
				<div id="commonLogin">
				<form id="loginForm">
					<ul class="loginBox">
						<li><label for="j_username">用户名：</label> <input type="text" name="j_username" value="" id="j_username" /> <span></span></li>
						<li><label for="j_password">密 码：</label> <input type="password" id="j_password" value="" name="j_password" /> <span></span></li>
						<li>
							<label for="j_rand">验证码：</label>
							<input size="12" type="text" id="j_rand" value="" name="j_rand" />
							<span></span> <img id="randImg" title="点击换一张" border="0" src="<%=request.getContextPath()%>/RandImageController.do?method=getRandImage"></img>
						</li>
						<li class="hidden"><span id="ErrorMsg"></span></li>
					</ul>
					<div class="conOperation">
						<button type="button" id="login" class="enterBtn"><span>登录</span></button>
						<a href="javascript:void(0);" id="registration" class="reg"><span>注册</span></a>
						<a href="javascript:void(0);" id="getPassWord"><span>忘记密码？</span></a>
					</div>
					<div class="securityRemind">
						<h3><span>安全须知</span></h3>
						<ul>
							<li>请不要在网吧等场所的公用计算机上使用<span class="webName"></span>。</li>
							<li>请不要通过不明网站、电子邮件或论坛中的网页链接登录<span class="webName"></span>。</li>
							<li>使用完毕或暂离机器时请勿忘退出<span class="webName"></span>。</li>
						</ul>
					</div>
				</form>
				</div>
			</div>
		</div>
		<div id="contentSupp"></div>
	</div>
	<!--主要内容 结束-->
	<!-- 脚开始 -->
	<div class="footer">
		<%@ include file="/view/srplatform/portal/include/foot.jsp" %>
	</div>
	<!-- 脚结束 -->
	<!--在线客服开始-->
    <%@ include file="/view/srplatform/portal/include/online_customer_service.jsp" %>
    <!--在线客服结束-->
</div>
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