<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>TCL持续掀起国内智能手机攻势- 【阳光易购】</title>
<c:import url="/view/srplatform/common/init.jsp">
	<c:param name="isLastLoadPage">
		true
	</c:param>
</c:import>

<!--CSS-->
<link rel="stylesheet" type="text/css" href="/view/resource/skin/thems/default/web.css" />
<link rel="stylesheet" type="text/css" href="/view/resource/skin/skin07/css/seller.css" />
<link rel="stylesheet" type="text/css" href="/view/resource/skin/thems/default/listDetail.css" />
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
		<div id="contentSub" class="index3pa">
			<%@ include file="/view/staticpags/load/left_cms.jsp" %>
		</div>
		<div id="contentMain" class="index3pa">
			<div id="conTitle">
				<div class="navCurrent ">

	<a href="/view/srplatform/portal/index.jsp" id="/view/srplatform/portal/index.jsp" title="首页" target="_self">首页</a>
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/supplierzx.jsp" title="供应商资讯" class="cmsHref_self">供应商资讯</a>
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081304e848f01307700b496422d.jsp" title="TCL持续掀起国内智能手机攻势" class="cmsHref_self">TCL持续掀起国内智能手机攻势</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>TCL持续掀起国内智能手机攻势</h1>
					<div class="source">
						<span>发布时间：2011-06-10</span>
						<span>发布人：-  </span>
					</div>
					<p><p>6月10日消息，TCL通讯打造Android智能手机的战略越来越明显，继日前刚刚推出千元以下Android3G智能手机A906之后，TCL通讯又推出一款中高端Android3G智能手机A990，以便进一步拓展智能手机市场。</p>
<p>作为TCL通讯最新的智能互联网手机，TCL为该款手机取名&ldquo;光芒&rdquo;，其搭配3.5英寸电容触摸屏，作为一款标杆级Android智能手机，除了靓丽的外表，其基于谷歌Android2.2系统，配备高达600MHz的CPU以及512RAM+512ROM内存，提供流畅的智能手机操控体验。同时，该手机支持多点触控，500万像素的主摄像头，搭配30万像素前置摄像头。最高下载速度达7.2Mbps的WCDMA网络，搭配支持自建AP的WLAN网络功能。</p>
<p>该手机为TCL通讯移动互联网手机战略的具体体现。在日前出席成都研发中心落成仪式时，TCL集团高级副总裁、TCL通讯CEO郭爱平在讲话中表示，2011年，TCL通讯将定位于移动互联网手机，并全面发力Android智能终端，在全互联网智能时代抓住机遇赢得重生。</p>
<p>据悉，根据计划，2011年TCL通讯全面切入Android操作系统，计划年内将于全球推出数十款基于EDGE、3G等不同网络制式、高中低不同价格段的智能手机。</p>
</p>
				</div>
			</div>
		</div>
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

<script>
	// 为左边栏设定选中样式,判断用于解决点击左边栏后样式被覆盖问题
	var id = $("#channelId").val();
	if(null != $('.subnav>li[id='+id+']').html()) {
		$('.subnav>li').removeClass('selected');
		$('.subnav>li[id='+id+']').addClass('selected');
	}
</script>