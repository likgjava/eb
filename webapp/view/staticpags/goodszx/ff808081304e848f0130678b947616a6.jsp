<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>现货市场趋紧 螺纹或将再创新高- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081304e848f0130678b947616a6.jsp" title="现货市场趋紧 螺纹或将再创新高" class="cmsHref_self">现货市场趋紧 螺纹或将再创新高</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>现货市场趋紧 螺纹或将再创新高</h1>
					<div class="source">
						<span>发布时间：2011-06-07</span>
						<span>发布人：-  </span>
					</div>
					<p><p>国内现货钢材市场已明显出现分化，各品种涨跌不一。特别是板材，由于下游汽车、家电、造船等行业在需求上有弱势表现，商家看跌的预期已在加重。据国内知名钢铁资讯机构&ldquo;我的钢铁&rdquo;提供的最新市场报告，进入6月，传统的钢材销售&ldquo;淡季&rdquo;即将到来，将进一步压缩钢价上冲的空间。</p>
<p>钢材产量及库存压力较大，保障房建设对需求的拉动仍有待观察。中国钢铁工业协会最新统计数据显示，5月中旬76家会员企业粗钢日均产量为166.22万吨，现货市场价格坚挺令钢铁企业生产积极性仍然旺盛。库存方面，截至5月30日，国内主要城市的螺纹钢库存总量为562.255万吨，较前一周减少21.264万吨，与4月份相比则减少47.807万吨。虽然市场库存总体处于下滑的态势之中，但库存较往年相比依然维持在相对的高位，去库存化有望继续进行。</p>
<p>国内建筑钢市场在盘整中略有走强，尤以北方地区的涨幅最为明显，大部分南方地区&ldquo;低价资源&rdquo;的价格也开始抬升。部分主导钢厂已出台6月上旬的指导价格，多数以谨慎上调为主，并未超出市场的承受能力。建筑钢的库存水平保持在下降通道内，现货经销商的销售压力并不大。考虑到&ldquo;即将进入需求淡季&rdquo;，商家的订货意愿也普遍不强。</p>
<p>受到整体宏观政策调控及基本面供应过剩的影响，钢价上涨的动能明显不足，但考虑到钢厂的生产成本维持在高位，现货价格的下调幅度有限。目前尚需需求的进一步释放尤其是保障性住房的需求，才能有效地缓解当前供大于求的局面。螺纹钢价格处于滞涨更难跌的阶段，商品的价值投资洼地已经开始出现，4800元/吨之下具有较强的投资价值，而目前行情的上涨仍需等待需求的有效释放。</p>
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