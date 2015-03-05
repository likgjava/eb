<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>今年棉价累计下跌近两成 囤货商损失惨重- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081304e848f0130679c2de616b2.jsp" title="今年棉价累计下跌近两成 囤货商损失惨重" class="cmsHref_self">今年棉价累计下跌近两成 囤货商损失惨重</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>今年棉价累计下跌近两成 囤货商损失惨重</h1>
					<div class="source">
						<span>发布时间：2011-06-07</span>
						<span>发布人：-  </span>
					</div>
					<p><p>据中国棉花协会最新数据显示，今年以来，棉价一改去年大涨的走势，开始持续走下坡路，据最新监测数据，6月3日，中国棉花价格指数仍在下滑。昨日有分析师指出，去年以来棉花价格的大起大落，对棉花囤货商打击很大。</p>
<p>暴涨后持续下跌</p>
<p>昨日北京东方艾格农业咨询有限公司分析师马文峰介绍，今年以来，棉价一直在下跌，目前的价格和高点相比已经下跌20%，但仍处于高位。</p>
<p>据记者了解，去年下半年以来，棉花价格节节攀升，去年10月、11月，国内328级棉花(标准棉花)价格接连创下历史新高，去年比前年涨幅达到40%。而当时节节攀高的棉花价格直接波及到下游的服装市场，去年年底记者调查，商场新款服装普遍有两到三成的涨幅。</p>
<p>囤货商损失惨重</p>
<p>昨日马文峰表示，国内棉价的持续下跌对棉花囤货商打击很大。</p>
<p>中国棉花协会网站最新发布的分析称，当前多数棉商手里都有不少棉花存货，上百吨、上千吨的企业不在少数。随着当前棉花价格企稳，纺织企业库存见底，棉企积极出货，双方成交量有所提升。此外，当前行业下游比较悲观，预计到下半年订单来临将有所好转。</p>
<p>中国储备棉管理总公司近日也在国资委网站撰文称，虽然近期天气因素为期棉市场提供了些许支撑，但在纺织产销形势未见明显回暖之前，国内棉价弱势运行的可能性较大。</p>
<p>中储棉指出，人民币对美元汇率连创新高，令纺织企业的出口环境每况愈下;另一方面，国内纱线价格仍未摆脱下跌通道。</p>
<p>对于棉花的走势，昨日马文峰表示，受国内需求不足及出口量下滑的影响，预计棉价的下跌还将持续，可能要延续至八九月份。</p>
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