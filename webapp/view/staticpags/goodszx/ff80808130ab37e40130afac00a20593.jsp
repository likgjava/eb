<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>美国棉花出口需求疲软 新棉长势不佳- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808130ab37e40130afac00a20593.jsp" title="美国棉花出口需求疲软 新棉长势不佳" class="cmsHref_self">美国棉花出口需求疲软 新棉长势不佳</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>美国棉花出口需求疲软 新棉长势不佳</h1>
					<div class="source">
						<span>发布时间：2011-06-21</span>
						<span>发布人：-  </span>
					</div>
					<p><P>2011年6月10日—16日，美国国内七大市场标准级现货平均价格为150.43美分/磅，较前周下跌1.51美分/磅，较去年同期上涨74.11美分/磅。当周，美国国内七大现货市场累计成交28534包。2011年6月17日—23日，AWP价格为123.43美分。</P>
<P>成交情况</P>
<P>当周，美国国内现货市场需求十分清淡，价格下跌，国外需求疲弱，皮马棉需求不错，价格稳定，国外对新花的询价稀少。</P>
<P>天气情况和收获进度</P>
<P>当周，美国东南地区雨后旱情依旧，干热天气继续威胁新棉生长，许多新苗根系发育不良;三角洲北部的暴风雨和冰雹导致数百英亩棉田受损;三角洲南部的降雨对缓解旱情基本没有帮助，高温天气对新棉生长不利;干热天气导致得州新棉生长缓慢，得州西部干热多风，新棉出苗后生长受阻;西部沙漠地区也是干热多风，苗情非常差;圣约金地区气温高，新棉生长加快;远西部地区气候温暖，苗情正常或良好，圣约金皮马棉长势良好。</P>
<P>国内外纺织厂需求情况</P>
<P>当周，美国纺织厂采购了今年8-9月船期的5级棉，纺织厂对新花的需求很好，主要是今年四季度到明年一季度的4级棉。纺织厂对今年剩余时间内的采购依然谨慎，并借ICE期货下跌之机对前期的订货进行了定价。</P>
<P>美棉出口询价稀少，各类低等级和低价品种需求最好，国外工厂继续关注低价纱线和其他非美棉品种。</P>
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