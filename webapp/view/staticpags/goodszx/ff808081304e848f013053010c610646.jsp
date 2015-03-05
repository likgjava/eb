<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>亚洲需求预计将推动金价继续上涨- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff808081304e848f013053010c610646.jsp" title="亚洲需求预计将推动金价继续上涨" class="cmsHref_self">亚洲需求预计将推动金价继续上涨</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>亚洲需求预计将推动金价继续上涨</h1>
					<div class="source">
						<span>发布时间：2011-06-03</span>
						<span>发布人：-  </span>
					</div>
					<p><p>总部位于香港的矿业公司国际资源集团有限公司副主席Owen Hegarty表示，亚洲各国央行预计将在外汇储备中增加金条和金币，因此金价有望继续攀升。</p>
<p>Hegarty周四接受采访称，中国、印度、日本等国央行已在过去几年中从黄金净卖家转变成了净买家。随着全球通货膨胀担忧日益升温，这一趋势可能还将持续下去。</p>
<p>Hegarty称，黄金是大宗商品，是货币，是保值品，也是对冲通胀、地缘政治和金融不确定性风险的工具。</p>
<p>传统上，印度、中国等亚洲国家的黄金储备与欧盟国家相比相对较少。但近年来，这些国家的央行不断购入黄金，其国内寻求抗通胀投资的个人和机构投资者也蜂拥而至。</p>
<p>中国央行公布的官方数据显示，截至3月底中国央行黄金储备达1054吨，为2009年4月份以来一直保持的水平。但市场观察人士则认为，央行未完全披露其黄金头寸。</p>
<p>中国许多学者和决策者近年来主张，中国政府应增加黄金和其他贵金属储备，以实现美元资产以外的资产多元化。</p>
<p>但包括中国央行副行长易纲在内的其他一些人士却反复表示，中国增加黄金储备会难免会对市场金价产生重大影响。</p>
<p>不管央行怎么做，中国的私人投资者却是一直在加紧购买黄金的。据世界黄金协会的数据显示，今年第一季度，包括私人投资在内的中国黄金投资需求总量增长了一倍多，至90.9吨，中国由此成为全球最大的黄金买家。</p>
<p>世界黄金协会称，目前全球黄金投资需求的25%来自中国，23%来自印度。</p>
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