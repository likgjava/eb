<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>随着前期热点的散去，大连商品交易所焦炭期价走势渐趋平静，呈现振荡走势。笔者认为在成本支撑及需求减弱的共同作用下 连焦有望延续振荡走势- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813045f6fc013048b93ad100ce.jsp" title="随着前期热点的散去，大连商品交易所焦炭期价走势渐趋平静，呈现振荡走势。笔者认为在成本支撑及需求减弱的共同作用下 连焦有望延续振荡走势" class="cmsHref_self">随着前期热点的散去，大连商品交易所焦炭期价走势渐趋平静，呈现振荡走势。笔者认为在成本支撑及需求减弱的共同作用下 连焦有望延续振荡走势</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>随着前期热点的散去，大连商品交易所焦炭期价走势渐趋平静，呈现振荡走势。笔者认为在成本支撑及需求减弱的共同作用下 连焦有望延续振荡走势</h1>
					<div class="source">
						<span>发布时间：2011-06-01</span>
						<span>发布人：-  </span>
					</div>
					<p><p>成品油供应已到紧要关口。国家发改委公告预警指出，二季度是国内成品油传统消费旺季，再加上部分地区电力供应紧张，预计国内成油品供应形势不容乐观。</p>
<p>发改委同时公告指出，由于国内成品油与原油比价偏低，一季度行业实现利润149亿元，同比下降16.2%;亏损企业数量增加11%，亏损企业亏损额约60亿元，为去年同期的4.4倍。发改委表示，4月7日上调成品油价格(破解油价困境)，企业经营压力有所缓解，但相当一部分企业仍处于亏损状态。</p>
<p>◎ 国际油价目前在每桶100美元左右，从高点已有一定的回落。如果原油价格在一定程度上继续下降将有利于中国石化、中国石油两大集团提高业绩，据测算，国际原油价格平均下跌10美元，中国石油的每股收益将增加0.15元左右。中国石化原油自给率仅为25%左右，原油价格波动对公司业绩影响相对较大，原油价格平均下跌10美元，中国石化的每股业绩将增加0.22元左右。今年中国石化购买海外原油大约10亿桶，每下跌10美元，下半年将对中国石化产生25亿美元的影响。</p>
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