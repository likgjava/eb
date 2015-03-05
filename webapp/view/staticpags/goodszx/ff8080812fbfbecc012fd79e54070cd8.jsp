<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>台湾电子产品与机械出口创历年单月新高- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/goodszx.jsp" title="供货资讯" class="cmsHref_self">供货资讯</a>
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080812fbfbecc012fd79e54070cd8.jsp" title="台湾电子产品与机械出口创历年单月新高" class="cmsHref_self">台湾电子产品与机械出口创历年单月新高</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>台湾电子产品与机械出口创历年单月新高</h1>
					<div class="source">
						<span>发布时间：2011-05-10</span>
						<span>发布人：-  </span>
					</div>
					<p><p>据台湾媒体报道，在全球对新科技产品需求强劲，油价与原物料价格上涨带动下，4月出口总值273.2亿美元，是历年单月新高。其中电子产品与机械出口创历年单月新高。</p>
<p>据报道，4月对日本出口金额为历年单月第3高，进口为近9个月来新低。是否因为日本强震造成转单效应，&ldquo;财政部统计长&rdquo;林丽贞说，看不出有转单效应，对日出口创新高，主要还是全球需求强劲的影响。</p>
<p>据了解，&ldquo;财政部&rdquo;9日公布4月出口273.2亿美元，比3月增加0.3%，进口243.6亿美元，比3月减少4.4%，出口创历年单月新高，进口为第3高(仅次于3月及2008年8月)。4月出超29.6亿美元。</p>
<p>林丽贞说，累计1到4月出口1011.2亿美元，比去年同期增加20.8%，1到4月进口936.2亿美元，年增22.8%，出、进口都创历年同期新高。</p>
<p>在出口主要货品方面，林丽贞说，与去年4月相比，前10大出口货品，除光学器材出口减少3.9%外，其余均呈2位数成长，其中信息与通信产品增71.3%、矿产品(油品)增67.9%、机械增42.6%等增幅较大。林丽贞说，4月电子产品出口73.3亿美元，机械出口18.1亿美元都创下历年单月新高。</p>
<p>她表示，累计1到4月前10大出口货品中，电子产品、基本金属及其制品、塑橡胶及其制品、化学品、机械、矿产品、交通运输设备等7项创历年同期新高。</p>
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