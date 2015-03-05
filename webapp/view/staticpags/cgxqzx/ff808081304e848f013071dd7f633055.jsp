<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>苹果超过惠普成第一大芯片采购商- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081304e848f013071dd7f633055.jsp" title="苹果超过惠普成第一大芯片采购商" class="cmsHref_self">苹果超过惠普成第一大芯片采购商</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>苹果超过惠普成第一大芯片采购商</h1>
					<div class="source">
						<span>发布时间：2011-06-09</span>
						<span>发布人：-  </span>
					</div>
					<p><p>北京时间6月9日消息，由于iPhone和iPad的需求强劲，PC销量却渐渐低迷，苹果超过惠普成为最大的电子芯片采购商。</p>
<p>IHS ISuppli表示，2010年，苹果采购了价值175亿美元的芯片，上一年为97亿美元，同比增长79.6%。去年苹果的半导体开支比惠普多24亿美元，今年将超过惠普75亿美元。</p>
<p>2010年，苹果半导体开支中的61%是为iPhone和iPad采购芯片，惠普开支中82%是为台式机、笔记本和服务器采购芯片。</p>
<p>苹果还采购了大量的NAND闪存，它也用于iPod中，2010年苹果是第一大NAND闪存采购商。</p>
<p>在周二的WWDC大会上，苹果声称自2010年4月iPad推出以来，已经售出2500万台。而惠普则在5月17日说，上季度的PC销售下滑了23%，并将年度销售额预期下调10亿美元。</p>
<p>ISuppli解释说：&ldquo;苹果的长处在于硬件销售线和媒体生态系统，每台设备都可以通过iTunes和iOS连接，并可以和所有苹果产品协作。&rdquo;结果，用户每买一台设备，获得的额外价值也更多，这使得用户很难离开苹果王国。换句话说，通过一个共同的生态系统，苹果每款设备都卖得比对手好。</p>
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