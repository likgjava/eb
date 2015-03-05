<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>传苹果选定LG三星夏普为iPad3显示屏供应商- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808131f586b50131f945f5ba0040.jsp" title="传苹果选定LG三星夏普为iPad3显示屏供应商" class="cmsHref_self">传苹果选定LG三星夏普为iPad3显示屏供应商</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>传苹果选定LG三星夏普为iPad3显示屏供应商</h1>
					<div class="source">
						<span>发布时间：2011-08-24</span>
						<span>发布人：-  </span>
					</div>
					<p><p>8月24日消息，据国外媒体报道，苹果本周继续调整第三代iPad的组件供应商，其中包括了3家重要的LCD厂商和2家背光组件供应商。</p>
<p>据知情人士透露，苹果已经选定LG Display、三星电子和夏普等3家厂商为iPad3的LCD显示屏供应商，其中LG是最大的显示屏供应商。</p>
<p>之前有消息称，由于三星和LG面临生产难题，苹果已经被迫放弃了今年开始量产iPad3的激进计划。</p>
<p>据说苹果已经要求供应商提高第四季度新款iPad的产量和出品率，为明年年初正式发布iPad3做准备。</p>
<p>除了上述3家显示屏供应商之外，据说苹果还联系了台湾的瑞仪光电为其背光组件主供应商。据说LG所需的半数背光组件和三星所需的全部背光组件也是由瑞仪光电提供的。而夏普使用的背光组件除了由瑞仪光电提供之外，还有一部分来自日本一家背光组件供应商。</p> 
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