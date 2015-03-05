<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>达芬奇事件余震不断 官网隐藏公共关系邮箱- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813140dc4701314f5d1be91238.jsp" title="达芬奇事件余震不断 官网隐藏公共关系邮箱" class="cmsHref_self">达芬奇事件余震不断 官网隐藏公共关系邮箱</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>达芬奇事件余震不断 官网隐藏公共关系邮箱</h1>
					<div class="source">
						<span>发布时间：2011-07-22</span>
						<span>发布人：-中国广播网  </span>
					</div>
					<p><P>7月21日消息 据中国之声《新闻晚高峰》报道，在向消费者首次公开道歉后，昨日，达芬奇家居有限公司在官网上“悄悄”挂出一个邮箱，表示消费者可将投诉邮件发送到该邮箱，公司将有专人安排处理。这个被其称为“公共关系”的邮箱，并没有醒目地张贴在达芬奇家居官网的首页，而是“隐藏”在资讯快报的栏目里面，只有点击进去才能看到。</P>
<CENTER><IMG border=1 alt=达芬奇首页截图，邮箱的确不好找(资料图片) src="http://i3.sinaimg.cn/cj/2011/0721/U5809P31DT20110721172356.jpg" width=550 height=275></CENTER>
<P align=center>达芬奇首页截图，邮箱的确不好找(资料图片)</P>
<P>该邮箱主要用于接受媒体垂询或采访预约，同时表示消费者的相关疑问或投诉，也可以邮件形式发至这个邮箱，公司将有专人统一安排处理，但仍未对退换货制定统一细则。而达芬奇家居的官方微博上，干脆只是贴出了邮箱，具体用途都没有说明。</P>
<P>在北京，达芬奇家具的销售人员明确表示，愿意为消费者负责但并不接受无条件退货。所谓为消费者负责，是指购买达芬奇家具的消费者如果觉得家具质量有问题，可以在出具权威机构的家具检测鉴定报告后，到达芬奇家具北京店进行赔付或退换货。但如果无法出具权威检测报告并不能证明达芬奇家具有质量问题，专卖店是不接受无理由无条件退货的：顾客个人心里面觉得东西有问题，其实东西是没有问题的。我们说是意大利进口的就是进口的……这样看来，你说或不说，怒火不怒，进口就在那里，不退不换…</P>
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