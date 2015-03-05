<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>传AMD或将停止开发闪龙(Sempron)芯片- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130c14db40130ce9d68a40152.jsp" title="传AMD或将停止开发闪龙(Sempron)芯片" class="cmsHref_self">传AMD或将停止开发闪龙(Sempron)芯片</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>传AMD或将停止开发闪龙(Sempron)芯片</h1>
					<div class="source">
						<span>发布时间：2011-06-27</span>
						<span>发布人：-  </span>
					</div>
					<p><P>据国外媒体报道，据市场传言称，AMD打算停止开发闪龙(Sempron)系列芯片，它在5月份发布的闪龙芯片可能是这个产品系列的最后一款产品。</P>
<P>实际上，闪龙系列芯片自2004年起就上市了，从那时到现在已经服役了相当长的一段时间。如果当时就知道它能够服役这么长的时间，许多业内人士也会感到惊奇的。</P>
<P>闪龙一词起源于拉丁语“simper”，它的含义是“总是”。</P>
<P>闪龙芯片一共经历了3代不同的微结构，首先是AthlonXps，闪龙在AMD发布Athlon643800+和FX-53时就上市了。随后它被基于K8的处理器所替代，K8处理器在功能上有很多不足，比如二级缓存较小、不支持64位技术等。</P>
<P>第三代微结构是在2009年7月发布的，在5月份以前，这一代产品只包括3款单核和1款双核SKU。5月份，AMD推出了最新的闪龙130处理器。</P>
<P>闪龙130处理器配备了1个CPU核心，时钟频率仅2.6GHz，二级缓存为512KB。它与K10芯片使用的是一样的AMD64和虚拟化功能。</P>
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