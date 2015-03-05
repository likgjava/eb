<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>东南亚快消巨头加入中国凉茶大战- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813118359201312b60c89f2438.jsp" title="东南亚快消巨头加入中国凉茶大战" class="cmsHref_self">东南亚快消巨头加入中国凉茶大战</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>东南亚快消巨头加入中国凉茶大战</h1>
					<div class="source">
						<span>发布时间：2011-07-15</span>
						<span>发布人：-金羊网-羊城晚报  </span>
					</div>
					<p><P>据了解，清酷的母公司在近三五年内会投资10亿元开拓中国市场，包括请代言人以及投资生产线等。</P>
<P>“我们很尊重王老吉，因为它开辟了中国的凉茶市场。我们进入中国市场，并不会跟王老吉硬碰硬的，它是红的，我们就是绿的。”</P>
<P>总在言语之间不经意提起“王老吉”的，便是已经悄悄在华南地区上市三个月的“新贵”———清酷凉茶。其总经理吴子枫和品牌代言人容祖儿昨天下午首次揭开面纱，正式发力暑期饮料市场。</P>
<P>“其实我们要面对的不只是凉茶类的对手，”吴子枫非常坦白：“饮料市场群雄并起，我们还要直面果汁、饮用水、茶饮料等其他品类的竞争，这个市场从来不乏新进者，单是今年4、5月，市场上已经有数款新品上市，产品涉及茶饮料、乳酸饮料等领域，这些产品都背靠国际排名靠前的饮料巨头，各占山头。”</P>
<P>清酷背后的靠山———Enesis(恩尼世)集团，是东南亚知名的快消品企业，其研发的清热饮料已经占领了东南亚市场，2002年进军香港地区，“到了2008年我们已经成为清热产品中销量最高的品牌，市场占有率达到70%。”吴子枫透露。这也是清酷目前只进军华南市场的原因。</P>
<P>据悉，清酷产品目前已深入广、深、莞近7万个传统渠道，在珠三角80%现代渠道实现上架。“将来的发展以华南为主，一步一步来，”吴子枫毫不掩饰地说：“等华南市场成功之后再走向全国。”</P>
<P>在王老吉开拓了十多年的凉茶市场之后才开始小试牛刀，清酷凉茶似乎来得有些晚。恩尼世集团有关负责人坦言：“当年进入印尼的时候，行业内的人说我们来迟了，也不看好我们。4个月之后，我们产品销量大好，一年之后我们已经成为印尼市场的第一。香港市场也是如此。所以，中国市场来迟了，不要紧。这就是我们的挑战。”他表示：“迟也有迟的好处，有人替我们开路，市场变大了，我们要怎么进军，还有得参考。”</P>
<P>据了解，清酷的母公司在近三五年内会投资10亿元开拓中国市场，包括请代言人以及投资生产线等。</P>
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