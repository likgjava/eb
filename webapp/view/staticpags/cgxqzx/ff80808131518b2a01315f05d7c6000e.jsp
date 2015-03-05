<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>传苹果欲向LG采购55英寸OLED面板- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff80808131518b2a01315f05d7c6000e.jsp" title="传苹果欲向LG采购55英寸OLED面板" class="cmsHref_self">传苹果欲向LG采购55英寸OLED面板</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>传苹果欲向LG采购55英寸OLED面板</h1>
					<div class="source">
						<span>发布时间：2011-07-25</span>
						<span>发布人：-新浪科技  </span>
					</div>
					<p><P>北京时间7月25日早间消息，有传闻称，苹果已经与Mac电脑显示屏的供应商LG Display进行接触，讨论采购55英寸OLED面板的可能性。</P>
<P>消息称，这一OLED面板有可能被用在电视机产品中，以实现音乐、视频和电视节目通过IP网络的发布。LG Display上周五表示，将于2012年底开始OLED电视机的限量生产。</P>
<P>此前已有许多业内人士猜测，苹果将推出电视机产品。Piper Jaffray分析师吉恩·蒙斯特(Gene Munster)预计，苹果将在未来一年中推出一款这样的产品。</P>
<P>在所有显示屏厂商中，苹果与LG的关系最为密切。LG目前为iPod Touch和iPhone生产Retina显示屏。苹果曾于2009年向LG投资5亿美元，确保显示屏的生产。苹果随后获得了27英寸面板的暂时性独家使用权。</P>
<P>索尼目前也生产OLED显示屏，但索尼与苹果的关系并不是很好。另一家主要OLED面板厂商三星正与苹果发生专利权纠纷。业内人士认为，如果苹果推出电视机，那么苹果的产品中将会出现一些能够改变行业的独家创新。</P>
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