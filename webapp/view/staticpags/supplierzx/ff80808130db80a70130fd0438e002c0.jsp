<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>iPhone美国市场份额超黑莓 占8.7%份额- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130db80a70130fd0438e002c0.jsp" title="iPhone美国市场份额超黑莓 占8.7%份额" class="cmsHref_self">iPhone美国市场份额超黑莓 占8.7%份额</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>iPhone美国市场份额超黑莓 占8.7%份额</h1>
					<div class="source">
						<span>发布时间：2011-07-06</span>
						<span>发布人：-腾讯科技  </span>
					</div>
					<p><P>北京时间7月6日消息，据国外媒体报道，美国市场研究公司comScore周二发布的最新统计数据显示，在截至今年5月的前三个月期间，苹果iPhone在美国手机市场的份额已超过RIM黑莓(BlackBerry)。</P>
<P>comScore称，在截至今年5月的前三个月期间，以手机用户量为标准，iPhone美国市场份额已由此前三个月的7.5%增至8.7%;同期黑莓相应市场份额则由此前三个月的8.6%降至8.1%。</P>
<P>如果将常规手机和智能手机视为整体，三星为美国第一大手机厂商，市场份额为24.8%，与此前三个月持平。LG第二，市场份额由此前三个月的20.9%增至21.1%。摩托罗拉第三，市场份额由此前三个月的16.1%降至15.1%。</P>
<P>以智能手机平台为标准，则谷歌Android平台居首，在截至今年5月的前三个月期间美国市场份额为38.1%，高于于此前三个月的33.0%。苹果iOS排名第二，市场份额由此前三个月的25.2%增至26.6%;RIM排名第三，市场份额由此前三个月的28.9%降至24.7%。微软则由此前三个月的7.7%降至5.8%，Palm也由此前三个月的2.8%降至2.4%。</P>
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