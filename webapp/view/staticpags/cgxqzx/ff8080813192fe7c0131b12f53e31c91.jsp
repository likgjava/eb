<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>中国在国际市场的大豆采购愈加活跃- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/cgxqzx.jsp" title="采购焦点资讯" class="cmsHref_self">采购焦点资讯</a>
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813192fe7c0131b12f53e31c91.jsp" title="中国在国际市场的大豆采购愈加活跃" class="cmsHref_self">中国在国际市场的大豆采购愈加活跃</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>中国在国际市场的大豆采购愈加活跃</h1>
					<div class="source">
						<span>发布时间：2011-08-10</span>
						<span>发布人：-  </span>
					</div>
					<p><P>8月9日消息，总部设在德国汉堡的行业期刊《油世界》周二称，中国似乎将从国际市场买入更多大豆，此前该国进口曾陷入很长一段时间的疲软期，而美国料将从中受益。</P>
<P>《油世界》表示，2010/11年度的前四至五个月，中国大豆进口迅猛增长，导致国内港口大豆库存增至纪录水准。这使得1月/6月间美国、巴西和阿根廷等主要出口国向中国的大豆出口下滑。</P>
<P>“但目前有迹象显示中国采购活动增加，”油世界表示。</P>
<P>《油世界》补充称，受此影响，今年8月美国、巴西和阿根廷大豆出口总量料将触及665万吨，较7月份增加约100万吨。</P>
<P>这家位于德国汉堡的行业期刊称，美国8月大豆出口将从7月份的72万吨增至97万吨，但仍低于2010年8月的159万吨。</P>
<P>美国农业部(USDA)8月4日宣布，私人出口商报告向中国出口销售174,000吨美国大豆，2011/12市场年度交货。大豆2011/12市场年度将从9月1日开始。</P>
<P>美国出口走强将抵消内需疲软的负面影响。</P>
<P>《油世界》警告称，美国牲畜饲养业的豆粕需求疲软，意味着8月大豆压榨量可能降至340万吨，7月份为346万吨，2010年8月为349万吨。</P>
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