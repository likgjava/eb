<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>地震影响持续 丰田30种零部件供应短缺- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/supplierzx.jsp" title="供应商资讯" class="cmsHref_self">供应商资讯</a>
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff80808130bf34b60130bf516fd8009c.jsp" title="地震影响持续 丰田30种零部件供应短缺" class="cmsHref_self">地震影响持续 丰田30种零部件供应短缺</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>地震影响持续 丰田30种零部件供应短缺</h1>
					<div class="source">
						<span>发布时间：2011-06-24</span>
						<span>发布人：-环球网汽车  </span>
					</div>
					<p><P>据外媒autonews报道，丰田公司近日表示，受311日本特大地震影响，仍有30种零部件短缺，丰田目前正与零件制造商通力合作，恢复供应。</P>
<P>在6月17日的年度股东大会上，丰田高级董事YasumoriIhara称，地震使659家供应商受挫，其中二、三级供应商居多。目前公司已组成150人的团队以恢复零部件供应，并竭力减少紧缺零部件的使用数量，如半导体和橡胶材料等。</P>
<P>受零部件供应和日元走强的影响，公司上周预测今年丰田的销售额将下降31%，仅为2800亿日元(约35亿美元)。此外日元升值将减少丰田出口，或被通用取代其海外市场的老大地位。而丰田新车的发行也会延迟。</P>
<P>尽管如此，预计丰田在北美市场和全球市场的生产将在9月和10月全面恢复。公司将继续实施丰田在发达国家和新兴市场的长期发展战略，包括在前景广阔的新兴市场重点发展下一代混合动力车、燃料电池车、电动车等。</P>
<P>据称，去年，丰田本土产量占据全球产量的45%。在日元走强和供应不足导致了国内生产成本走高的情况下，丰田公司可能重构产业布局，开始生产外迁，并提高厂商的生产能力。不管怎样，有一点可以确信，丰田将始终致力于以质量和科技引领全球。</P>
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