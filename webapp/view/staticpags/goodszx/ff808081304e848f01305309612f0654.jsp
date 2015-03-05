<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>供应吃紧担忧 美玉米延续升势- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081304e848f01305309612f0654.jsp" title="供应吃紧担忧 美玉米延续升势" class="cmsHref_self">供应吃紧担忧 美玉米延续升势</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>供应吃紧担忧 美玉米延续升势</h1>
					<div class="source">
						<span>发布时间：2011-06-03</span>
						<span>发布人：-  </span>
					</div>
					<p><p>因美国玉米播种面积或少于预期，进一步加重对本已吃紧的供应问题的担忧，芝加哥期货交易所(CBOT)玉米期货收涨1.1%，交投最活跃的CBOT 7月玉米期货收涨8美分，涨幅1.1%，报收于7.66 1/2美元/蒲式耳。</p>
<p>交易商表示，密苏里河洪水导致播种面积减少，加之因天气潮湿，担忧美国玉米种植带东部的全部农田无法都进行播种，为玉米期货提供了支持。分析师指出，农户似乎愈发不可能实现创记录的产量，以恢复料降至15年低点的库存。此外，2日的交易还缩小了陈作物和新作物之前的价差。CBOT 12月玉米期货收盘跃升22美分，报收于6.95美元/蒲式耳。</p>
<p>交易商表示，美国大平原北部和中西部的东部的农户一直无法播种玉米，玉米播种期早于大豆，这可能导致农户闲置土地以获取作物保险，而非改种大豆。交易商表示，商品基金预计买入了10,000手玉米合约。</p>
<p>大连玉米期货2日止跌回稳。主力1201合约收盘价2,417元/吨，上涨3元/吨。受担忧糟糕春季天气将导致美国中西部的东部播种面积和产量减少支持美盘走势，且随着长江中下游地区大范围干旱，使得粮食价格出现了普涨行情，但国内食品价格明显上涨使投资者担忧货币政策进一步收紧，令期价涨势受限。隔夜美玉米继续收高，预计近期连玉米将会小幅走高。</p>
<p>当前玉米现货市场受到陈粮销售基本结束，当前只有贸易商手里有少量库存的影响，玉米购销活动已经变得非常清淡，而需求厂家基本上已经结束大量买货，当前主要是以消化库存为主，因此短期来看，现货市场中玉米价格涨势将会放缓，但是在今年干旱，农产品价格普涨的大环境之下，市场出现回落的可能性依旧不大。</p>
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