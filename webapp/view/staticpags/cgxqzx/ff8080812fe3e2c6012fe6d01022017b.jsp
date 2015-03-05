<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>苹果供应链紧张 影响iPad和iPhone销售- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812fe3e2c6012fe6d01022017b.jsp" title="苹果供应链紧张 影响iPad和iPhone销售" class="cmsHref_self">苹果供应链紧张 影响iPad和iPhone销售</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>苹果供应链紧张 影响iPad和iPhone销售</h1>
					<div class="source">
						<span>发布时间：2011-05-13</span>
						<span>发布人：-  </span>
					</div>
					<p><p>北京时间5月13日，据国外媒体报道，iPad和iPhone在全球范围内处于热销状态，因此苹果供应链紧张的新闻让许多消费者感到难过，甚至有一点焦急，尤其是那些耐心等待了很长时间的消费者。</p>
<p>来自DigiTimes网站的报道称，苹果产品的制造商富士康公司位于中国成都的工厂正在面临劳动力和原材料的短缺。报道中还说，由于上游零件制造商资源的短缺，iPad 2和iPhone 4的销售在第二季度会受到一定程度的影响。但富士康公司对此报道回应称，公司只是强调了所面临的压力，但是仍然会全力满足客户的需求。</p>
<p>日本遭受的地震和海啸对于设备零件的供应影响很大。iPad2的发售又给这些零件制造商增加了压力。目前苹果在线商店订购iPad的等待时间在两周到四周之间，曾有谣言称会缩短为一到两周，但从目前的情况来看，还有会新的变化。</p>
<p>苹果公司提供的数据显示，已经有1900万部iPad和1.08亿部iPone售出，同时还有6000万台iPod touch。</p>
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