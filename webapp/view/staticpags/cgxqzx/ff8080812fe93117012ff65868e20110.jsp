<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>PCB厂：“电荒”恐会影响印刷电路板供应- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812fe93117012ff65868e20110.jsp" title="PCB厂：“电荒”恐会影响印刷电路板供应" class="cmsHref_self">PCB厂：“电荒”恐会影响印刷电路板供应</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>PCB厂：“电荒”恐会影响印刷电路板供应</h1>
					<div class="source">
						<span>发布时间：2011-05-16</span>
						<span>发布人：-  </span>
					</div>
					<p><p>由于日本地震和海啸的影响,许多与电子系统\半导体\PCB产业相关的领域受到一定程度的影响。因此，在市场需求旺盛的情况下，很多PCB厂商担心由于&ldquo;电荒&rdquo;会影响印刷电路板的供应问题。</p>
<p>近日，深圳沙井地区一度出现停电现象，据附近居民反映，停电频率为隔天停一次，深圳沙井还是PCB厂云集较密的地带之一，有部分企业因用电问题已是开工两天休息一天，停电不仅给居民生活带来了巨大的不便，还对PCB厂产能供应造成较大的影响。</p>
<p>因此，当问及PCB厂商是否担心&ldquo;电荒&rdquo;对PCB产能造成负面影响时，大部分经销商表示，&ldquo;目前还没听到风声&rdquo;。只是希望，相关部门能尽快解决用电问题。</p>
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