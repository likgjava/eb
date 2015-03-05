<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>三星本季度或超诺基亚成最大智能手机厂商- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813078a95601308bbf6a5912fb.jsp" title="三星本季度或超诺基亚成最大智能手机厂商" class="cmsHref_self">三星本季度或超诺基亚成最大智能手机厂商</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>三星本季度或超诺基亚成最大智能手机厂商</h1>
					<div class="source">
						<span>发布时间：2011-06-14</span>
						<span>发布人：-  </span>
					</div>
					<p><p>北京时间6月13日晚间消息，野村证券(Nomura)周一表示，三星本季度将超越诺基亚成为全球最大智能手机厂商，从而结束诺基亚长达14年的垄断地位。</p>
<p>野村控股同时指出，到下个季度苹果也将超越诺基亚跻身第二，而诺基亚只能屈居第三。野村证券分析师在一份调研笔记中称：&ldquo;1996年诺基亚推出了Communicator，此后便一直主导全球智能手机市场。但14年之后，诺基亚即将让出自己的宝座。&rdquo;</p>
<p>在智能手机市场，诺基亚的发展速度明显落后于苹果iPhone和谷歌Android手机。在低端市场，诺基亚又面临着亚洲竞争对手的强劲挑战。</p>
<p>但整体而言，诺基亚的手机出货量仍高于三星，主要是由于诺基亚在传统手机市场仍有较强优势，在新兴市场也拥有庞大的销售网络。</p>
<p>诺基亚今年2月宣布，将放弃Symbian系统，转而使用微软的WindowsPhone平台。5月31日，诺基亚又发布盈利预警，称2011财年第二财季营收将无法达到此前预期。</p>
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