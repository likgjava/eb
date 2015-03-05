<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>需求回暖支撑糖价- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813045f6fc013048b9ec8300d0.jsp" title="需求回暖支撑糖价" class="cmsHref_self">需求回暖支撑糖价</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>需求回暖支撑糖价</h1>
					<div class="source">
						<span>发布时间：2011-06-01</span>
						<span>发布人：-  </span>
					</div>
					<p><p>纽约原糖期货在5月初下探20.40美分/磅的低点获得有效支撑后，走出了一波幅度近10%的反弹行情。受其带动，郑糖短期行情亦呈现出较为强劲的上行态势。整体上看，市场热点的转换给处于弱势中的糖价带来支持，而且随着期价的上行市场多头信心也得到了较好的恢复。在消费旺季到来的预期心理支持下，预计短期糖价仍将易涨难跌。</p>
<p>国际方面，前期对供应增加的担忧，以及国际政治、经济、包括自然灾害等恐慌因素的影响有所弱化。目前市场更倾向于关注巴西生产迟滞和港口问题。此外，市场需求回暖的关注度也明显加强。据了解，目前巴西食糖生产晚于往年同期，单产下降、糖厂推迟开榨以及大量的甘蔗被转化成酒精是主要原因，并且市场预期未来数周巴西的食糖生产还难以明显好转。同时，由于对去年巴西港口问题导致出口迟滞带动国际糖价大幅上涨的经历印象深刻，市场对巴西港口的问题较为敏感。从目前情况看，今年巴西出口延误的问题虽然没有去年那么严重，但也存在着迟滞的现象。另外，国际市场近阶段需求出现明显回暖的迹象，这些都支持近期国际原糖价格。</p>
<p>国内方面，本榨季国内食糖产量基本确定，供需仍然存在较大缺口，将对国内糖价构成较强劲支撑。此外，消费旺季即将带来的影响在心理层面上的支撑作用也不容忽视。目前全国食糖生产已结束，产量在1045万吨左右，本榨季总供应量达到1210万吨左右。如果按照市场预计的1400万吨的需求量计算，供需缺口大概在190万吨。这是目前国内食糖现货价格始终处于高位的根本原因所在。</p>
<p>目前市场关注的焦点是国储拍卖的影响。2010/2011榨季以来，国储糖已分4次累计投放储备糖77万多吨，加上这次的25万吨，共计102万吨，上榨季同期为122万多吨。从季节性因素来看，端午节后直到中秋节前，是现货企业用糖和备货的高峰期，这一阶段国内糖价一般处于高位运行的概率较大。国储选择在目前这个节点进行拍卖，主要目的还是为了保证供应和稳定价格。目前国内市场食糖现货价格一直维持在7000元/吨上下，而6500元/吨的拍卖均价也基本符合市场预期，短期对郑糖盘面的利空作用将不会太大。</p>
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