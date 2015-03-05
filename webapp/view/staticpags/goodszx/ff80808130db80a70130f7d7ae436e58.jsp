<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>供应紧张 期棉价格受减税影响持续下跌- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808130db80a70130f7d7ae436e58.jsp" title="供应紧张 期棉价格受减税影响持续下跌" class="cmsHref_self">供应紧张 期棉价格受减税影响持续下跌</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>供应紧张 期棉价格受减税影响持续下跌</h1>
					<div class="source">
						<span>发布时间：2011-07-05</span>
						<span>发布人：-中财网  </span>
					</div>
					<p><P>近期郑州商品交易所棉花期货价格持续走低，交投最活跃的棉花1201合约最低探至22155元/吨，创出了该合约自今年1月18日上市以来的最低价。截至昨日收盘，棉花价格出现小幅反弹，目前报22480元/吨。</P>
<P>1月份交割的棉花期货，在去年11月9日最高曾达34250元/吨，目前棉花价格较那时的最高价格已经下跌超过1/3。分析人士表示，国内棉花价格的跌幅已经远远大于国际棉花价格的跌幅，主要是由于前期我国棉花供应紧张，棉花价格涨幅过大所致。同时，财政部决定自7月1日起，下调混纺布、亚麻、纱线等纺织原料进口关税，针对化纤、混纺布、棉布等纺织原材料及制品的关税下调幅度达到了50%，其中，混纺布进口税由之前的12%下调至6%。这一举措大幅缓解了我国棉花的供求矛盾，也导致了棉花期货价格的持续下跌。</P>
<P>对于以棉花为原材料的生产企业来说，棉花价格下降肯定是一件好事。有分析师表示，如果棉花价格能够继续下跌一定的空间，A股市场的纺织类上市公司的业绩将会有所提升，其股价也会有所表现。</P>
<P>但是有分析师表示，近期河南南阳市卧龙棉花产区降雨较少，棉田旱情严重，棉苗整体长势偏差，棉花减产已成定局。同时国际市场上希腊债务危机得以缓解，投资者对于商品的信心开始恢复，这些因素都对棉花价格的进一步下跌构成阻力。有分析师表示，纺织企业希望见到的棉花价格走低可能难以实现。</P>
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