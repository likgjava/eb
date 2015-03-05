<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>广告服务- 中国权威的电子采购与招标第三方公共服务平台</title>
<c:import url="/view/srplatform/common/init.jsp">
	<c:param name="isLastLoadPage">
		true
	</c:param>
</c:import>

<!--CSS-->
<link rel="stylesheet" type="text/css" href="/view/resource/skin/thems/default/web.css" />
<link rel="stylesheet" type="text/css" href="/view/resource/skin/skin07/css/seller.css" />
<link rel="stylesheet" type="text/css" href="/view/resource/skin/thems/default/listDetail.css" />
<link type="text/css" rel="stylesheet" href="/cms/resbase/foot/help.css" />
<!--JS-->
<script type="text/javascript" src='/view/srplatform/portal/include/common.js'></script>
</head>

<body>

<div id="container">
	<!-- 头部开始 -->
	<div class="header">
		<%@ include file="/view/srplatform/portal/include/main_header_index.jsp" %>
	</div>
	<!-- 头部结束-->
    <!--主要内容 开始-->
	<div id="sysContent" class="page">
		<div class="bottom_menu">
		<div class="menu">
			<h2>广告服务</h2>
			<ul>
				<li><a href="#ad_area">广告位置</a></li>
				<li><a href="#contact">联系方式</a></li>
				
			</ul>
		</div><!--.menu-->
		
	
		<div class="content">
			<ul class="breadcrumb">
				<li><a href="">首页</a></li>
				<li><span class="song">&gt;</span>广告服务</li>
			</ul>
			<div class="clearfloat"></div>
			<h1 id="gys">广告服务</h1>
			<h3 id="ad_area">广告位置</h3>
			<p><img src="http://www.ebid360.com/cms/resbase/foot/ad.jpg" /></p>
			<div class="fanhui"><a href="#gys">返回顶部</a></div>
			<h3 id="contact">联系方式</h3>
			<ul class="contact_info" style="font-weight:normal;">
				<li>总机：(86-10)88354986</li>
				<li>广告部经理：(86-10)88354986-331 </li>
				<li>广告部直客：(86-10)88354986-333</li>
				<li>传真：(86-10)88357792</li> 
				<li>E-mail：ad@ebid360.com</li>
				<li>地址：北京市海淀区首体南路22号国兴大厦11层 邮编：100044 </li>
			</ul>
    
		
		</div><!--.content-->
	</div><!--.bottom_menu-->

	</div>
	<!--主要内容 结束-->
	<!-- 脚开始 -->
	<div class="footer">
	    <%@ include file="/view/srplatform/portal/include/foot.jsp" %>
	</div>
	<!-- 脚结束 -->
</div>
</body>

</html>
