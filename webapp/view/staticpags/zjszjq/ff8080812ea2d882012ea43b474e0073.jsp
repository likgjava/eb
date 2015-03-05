<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>议价应从报价较高或较低的厂商开始？- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/pubservice/help/help_index.jsp" title="客服中心" class="cmsHref_self">客服中心</a>
	<a href="javascript:void(0)" id="/view/staticpags/zjszjq/zjszjq.jsp" title="招竞实战技巧" class="cmsHref_self">招竞实战技巧</a>
	<a href="javascript:void(0)" id="/view/staticpags/zjszjq/ff8080812ea2d882012ea43b474e0073.jsp" title="议价应从报价较高或较低的厂商开始？" class="cmsHref_self">议价应从报价较高或较低的厂商开始？</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f6ee4840128" />
				<div class="frameNews">
					<h1>议价应从报价较高或较低的厂商开始？</h1>
					<div class="source">
						<span>发布时间：2011-03-11</span>
						<span>发布人：-  </span>
					</div>
						<p><img src="/cms/newscontent/contentupload/image/2011-03/a214d464_21e2_4944_a332_aca910d45bdb.jpg"></p>
					<p><p>　　经过报价分析与审查，将3～7个厂商的报价按高低次序排列(比价)，先找比价结果排行第三低者来议价，探知其降低的限度后，再找第二者来议价。经过这两次议价，&ldquo;底价&rdquo;就可能浮现出来。若此底价比报价最低者还低，表示第三、第二低者承做的意愿相当高，则可再找当初报价最低者来议价，以前述第三、第二低者降价后的底价，要求最低者降至底价以下来承做，若当初报价最低者不愿降价，则可交予第二或第三低者按议价后的最低价格成交。</p>
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