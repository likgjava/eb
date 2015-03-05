<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>需求增加原材料涨价 光盘片成本上升- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081321f7bae01322280bd0f0054.jsp" title="需求增加原材料涨价 光盘片成本上升" class="cmsHref_self">需求增加原材料涨价 光盘片成本上升</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>需求增加原材料涨价 光盘片成本上升</h1>
					<div class="source">
						<span>发布时间：2011-09-01</span>
						<span>发布人：-  </span>
					</div>
					<p><P>稀土产量从去年开始降低，出口数量逐月递减，并采取配额制度，稀土价格飙涨。由于稀土金属元素是光盘片制造的重要材料，受制于稀土材料供给减少价格飙涨，光盘片制造商面临成本上升的压力，可能会导致光盘片的出厂价格上涨，影响零售渠道价格上调，具体幅度视稀土材料的涨幅决定。</P>
<P>由于光盘对稀土依赖程度高，作为关键材料的稀土，它可以增加光盘片的磁性，对提高光盘的存储密度起着至关重要的作用。光盘片生产商除了稀土价格飙涨，还受到我国人民币升值影响，进一步推动光盘片原料成本的上涨，可能会导致大批中小型厂商陆续淘汰而退出市场。</P>
<P>其次是深圳大运会带动光盘需求量，消化市场大量光盘片库存，加之中秋以及国庆等节日，光盘片需求增加，助涨光盘片价格。目前尚不知道光盘片价格的具体涨幅，ZOL消费存储频道将会密切关注光盘片价格走势，敬请大家关注ZOL光盘片价格报表。</P>
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