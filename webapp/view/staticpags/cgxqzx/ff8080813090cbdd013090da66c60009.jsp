<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>3d电视半年降价三成- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813090cbdd013090da66c60009.jsp" title="3d电视半年降价三成" class="cmsHref_self">3d电视半年降价三成</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>3d电视半年降价三成</h1>
					<div class="source">
						<span>发布时间：2011-06-15</span>
						<span>发布人：-  </span>
					</div>
					<p><p>昨日(14日)，我市家电卖场和多家彩电品牌厂商处信息显示，3D电视价格和普通LED电视差距由数倍缩小到1.2倍。</p>
<p>&ldquo;3D彩电价相比年初降价20~40%。&rdquo;苏宁彩电销售负责人罗峰称，主要降价的是国产品牌。一款46英寸的国产3D彩电价格已降到4900元/台，相对于同样大小的LED彩电，差距已缩小到几百元。</p>
<p>去年初3D电视才推出时，售价集中为1.5~2万元。但昨日，记者在苏宁、国美等家电卖场看到，一台国产42英寸3D彩电，价格为3999元。&ldquo;现在3D电视的价格仅是普通2D彩电的1.2~1.3倍。&rdquo;罗峰介绍，相对于LED彩电，消费者只需添数百、上千元就可以买到3D电视。</p>
<p>&ldquo;我才去了璧山县青杠镇，做一场活动3D电视就卖出几十台。&rdquo;康佳彩电重庆分公司市场部经理冯元说。他最近忙着在各县做活动，推广3D彩电。他表示，区县市场未来也将成为3D电视争夺的主战场。</p>
<p>长虹电视重庆分公司总经理罗健称，为争夺3D电视市场，在年内，该公司还将在重庆的县级市场设置3D电视体验店，让消费者体验。</p>
<p>厂商下力之下，3D彩电已渐成为市场主流产品。创维重庆方面的统计显示，年初其3D电视仅占到其销量的7~8%，现在为18%。国美重庆分公司彩电负责人袁跃认为，到今年国庆后，3D电视将占到整个彩电销售的四成左右。&ldquo;在我们所销售的电视中，现在的产品20%是3D彩电。&rdquo;TCL电视重庆分公司总经理贺玉权说。</p>
<p>&ldquo;虽然彩电可将一般的电视频道变为3D，但目前并未有电视台开播3D频道。&rdquo;袁跃认为，虽然3D电视市场发展很快，但是受片源等局限，难以在短期内成为市场主流。</p>
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