<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>化工市场陷入低迷 液氯、硝酸逆市上涨- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808131e0e1bd0131ef12a78f0040.jsp" title="化工市场陷入低迷 液氯、硝酸逆市上涨" class="cmsHref_self">化工市场陷入低迷 液氯、硝酸逆市上涨</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>化工市场陷入低迷 液氯、硝酸逆市上涨</h1>
					<div class="source">
						<span>发布时间：2011-08-22</span>
						<span>发布人：-  </span>
					</div>
					<p><p>国际油价上升乏力，作为下游的化工市场上周也呈跌多涨少局面。在生意社监测的68个化工品中，上涨产品13个，环比减少3个，占比19.1%;价格持稳的31个，较上周增加5个，占比45.6%;下跌产品24个，较上周减少2个，占比35.3%。其中，列涨幅榜与跌幅榜第一的分别是液氯和HFC- 
22。</p>
<p>上周，液氯价格继续小幅上涨，周初出厂均价751元/吨，周末升至821元/吨，周涨幅9.32%，但市场成交整体仍处低迷。</p>
<p>至于硝酸上周也继续小幅上涨，均价从周初的2225元/吨涨到周末的2308元/吨，涨幅为3.73%。据介绍，硝酸价格上涨的原因是上游原料液氨价格维持高位，对硝酸市场形成有力支撑;而下游苯胺、化肥等对硝酸需求相对平稳。此外，部分硝酸生产厂家近期检修，导致市场现货供应略有紧张。分析师高子斋认为，下周硝酸市场供应情况将有所改观，下游需求相对稳定，硝酸后市震荡盘稳为主。</p>
<p>值得注意的是，氟化工产业链上周继续下行。其中，HFC-22大幅下跌，周初报价还有18000元/吨，到周末时报价仅为15000元/吨，跌幅16.67%。同时，HFC-134a的周跌幅也达到9.0%。</p>
<p>分析人士对此解释，作为上游的氢氟酸供应充足且行情走软，导致HFC-22的成本支撑不足;而厂家库存充足，下游PTFE及空调工厂的制冷剂需求不佳，更引发整个氟化工产业链上下游产品不同程度走软。</p>
<p>“总体上，上周国内化工市场上涨乏力，整体进入深度整理期。”生意社化工分社社长张明认为。</p> 
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