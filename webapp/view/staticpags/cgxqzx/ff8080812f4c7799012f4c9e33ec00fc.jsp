<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>年内将开始配件采购 iPad 3将支持4G网络- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812f4c7799012f4c9e33ec00fc.jsp" title="年内将开始配件采购 iPad 3将支持4G网络" class="cmsHref_self">年内将开始配件采购 iPad 3将支持4G网络</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>年内将开始配件采购 iPad 3将支持4G网络</h1>
					<div class="source">
						<span>发布时间：2011-04-13</span>
						<span>发布人：-  </span>
					</div>
					<p><p>据台湾地区媒体报道，苹果已经对当地的配件供应商完成了认证，并将于今年年内开始下单采购iPad 3的零部件。</p>
<p>报道称，为解决日本地震导致原材料供应链受阻的问题，苹果先下手为强，力保旺季销售不受影响。此外，苹果将在iPad 3中增加4G网络支持，这包括HSPA+、LTE等。这也将作为苹果的重磅手段之一，借以打击其他竞争对手。</p>
<p>根据此前报道，iPad 3平板将采用带有FFS (fringe-field switching) 技术的IPS面板，在阳光下可提供更宽角度视角和更清晰的视觉体验。</p>
<p>此外，iPad 3平板的屏幕分辨率可以达到2048&times;1536。这一分辨率本来计划在iPad 2上实现，但由于出产率始终无法达到要求而废止。 <br />
&nbsp;</p>
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