<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>中国进口需求下降 棉价下跌趋势难改- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813140dc4701314a2ac0180af0.jsp" title="中国进口需求下降 棉价下跌趋势难改" class="cmsHref_self">中国进口需求下降 棉价下跌趋势难改</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>中国进口需求下降 棉价下跌趋势难改</h1>
					<div class="source">
						<span>发布时间：2011-07-21</span>
						<span>发布人：-  </span>
					</div>
					<p><P>7月19日，美元指数下跌，原油等商品普遍上涨，棉花期货也顺势对前期的大幅下跌进行修正。</P>
<P>从目前情况看，美国得州的罕见干旱天气对市场的影响正在减弱，与6月3日的高点相比，12月合约已经累计下跌了40美分，使得买家观望兴趣渐浓。</P>
<P>从市场基本面情况看，由于中国新棉长势良好和纺织厂需求的减弱，因此给下年度的市场笼罩了一层看跌氛围。根据国家棉花市场监测系统(NCMMS)的数据，2011/12年度中国棉花产量提升至740万吨，同比增长18.6%，较今年3月份的预测增长6.2%，并超过了USDA目前的718.5万吨的预测。而NCMMS对中国消费量的预测仅为992.8万吨，为过去四个月以来的最低数值，并较USDA的预测低19.6万吨。</P>
<P>对比两个机构的数字不难发现，NCMMS公布的产需状况比USDA更为宽松。同时，中国库存量的上升将在很大程度上影响该国的棉花进口数量，因此将对国际棉价构成冲击。</P>
<P>从技术图形看，12月合约近期支撑为100.11美分，上升阻力仍为103.45美分。</P>
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