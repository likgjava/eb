<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>厦沙高速公路三明段开工 预投资71.57亿元- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/cgxqzx.jsp" title="采购焦点资讯" class="cmsHref_self">采购焦点资讯</a>
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812f9cf3c5012fb385d73c0512.jsp" title="厦沙高速公路三明段开工 预投资71.57亿元" class="cmsHref_self">厦沙高速公路三明段开工 预投资71.57亿元</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>厦沙高速公路三明段开工 预投资71.57亿元</h1>
					<div class="source">
						<span>发布时间：2011-05-03</span>
						<span>发布人：-  </span>
					</div>
					<p><p>4月27日，厦门至沙县高速公路三明段在尤溪举行开工仪式。厦沙高速公路三明境内长80.965公里，投资估算71.57亿元。尤溪县境内全长约58.6公里，设尤溪西和坂面两个互通口。</p>
<p>&nbsp;厦沙高速公路为福建省高速公路网&ldquo;三纵八横三环三十三连&rdquo;中主骨架网三纵八横的第六横，是厦门港通往三明、江西南昌的便捷通道。起于厦门集美田厝，途经同安、安溪、永春、德化、尤溪、沙县，终于沙县际口，与第三横福银线相接，规划线路全长240公里。</p>
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