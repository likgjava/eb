<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>中国需求或继续推涨坚果价格- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808130db80a7013102100956070e.jsp" title="中国需求或继续推涨坚果价格" class="cmsHref_self">中国需求或继续推涨坚果价格</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>中国需求或继续推涨坚果价格</h1>
					<div class="source">
						<span>发布时间：2011-07-07</span>
						<span>发布人：-东方早报  </span>
					</div>
					<p><P>尽管严峻的通胀形势仍未缓解，但这并未影响中国的中产阶层对健康的关注。近段时间以来，全球坚果价格急剧上升。有分析人士认为，中国坚果消费的高速增长是坚果价格飙升的主要原因之一，而来自中国的旺盛需求或进一步推升国际坚果价格。</P>
<P>有统计数据显示，目前，腰果、核桃和山核桃的价格都处于创纪录高位。腰果仁的价格目前为每磅4.55美元，较一年前高出逾60%，带皮核桃的价格和山核桃仁的价格分别上涨43%和38%。</P>
<P>据英国《金融时报》报道，中国越来越多地从种植者那里直接购买坚果，这加剧了坚果价格的上涨。总部位于伦敦的坚果交易商CG Hacking董事总经理、国际坚果和干果基金会主席吉尔斯·海克英表示：“中国的坚果消费增长非常迅速，过去12个月，坚果价格一直飙升。”</P>
<P>与此同时，一位美国坚果业业内人士表示，中国的需求已开始影响那些在产品中使用坚果的食品制造商，而这种影响可能会从今年秋天开始变得更为明显。该业内人士称，以核桃为例，去年，美国加州对中国的出口增长了一倍，从2009年的4200万磅增至9800万磅。曾经是核桃净出口国的中国现在变成了净进口国，同时，核桃生产商发现，过去3年，核桃价格几乎翻了一番。</P>
<P>值得注意的是，坚果加工经销商香港鸿海贸易公司的郑鸿基表示，一种坚果价格的大幅上涨将促使中国进口商和购买者转向另一种坚果。他表示，比多数坚果价格都低的杏仁可能会取代价格飙升的腰果。</P>
<P>然而，坚果出口商表示，根据人均计算，中国的坚果消费仍低于发达国家，因此还有进一步增长的空间。加州蓝钻杏仁全球销售主管沃伦·科恩表示：“如果你逛一下中国的食品节，你会发现仍然有很多人甚至都不知道杏仁是什么，这令人鼓舞。”</P>
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