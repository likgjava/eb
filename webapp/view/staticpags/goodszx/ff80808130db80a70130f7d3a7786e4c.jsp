<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>增加供应 国内汽柴油批发价下调- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff80808130db80a70130f7d3a7786e4c.jsp" title="增加供应 国内汽柴油批发价下调" class="cmsHref_self">增加供应 国内汽柴油批发价下调</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>增加供应 国内汽柴油批发价下调</h1>
					<div class="source">
						<span>发布时间：2011-07-05</span>
						<span>发布人：-四川新闻网-西南商报  </span>
					</div>
					<p><P>7月1日起，我国汽油、柴油、航空煤油和燃料油的进口关税开始下调，其中柴油、航空煤油开始以零关税进口。业内人士表示，面对7月国内炼油厂检修高峰，以及夏天汽油旺季，此举无疑将提高贸易商进口积极性，保障国内供应，并平抑国内汽柴油价格。</P>
<P>据财政部规定，7月1日起，车用汽油及航空汽油进口关税由此前的6%下调至1%，柴油、航空煤油均将以零关税进口，5-7号燃料油暂定进口关税由此前的3%降为1%。大宗产品电子商务平台金银岛分析师靳婷分析，此前业界预期的7-8月检修期将引起油荒的预测，将会缓解，甚至消散。值得关注的是，在此项政策开始前，政策的释放效应已提前打压国内汽柴油的批发价。</P>
<P>自2010年第四季度发生油荒至今，国内油品资源供应局势一直处于紧平衡状态，主营炼厂开工率水平保持在历史高位，并开始削减出口量，同时增加进口。</P>
<P>靳婷表示，事实上，海关数据显示，我国进口量一直不高，主要原因还是来自于利润亏损的限制。5月我国成品油进口量339万吨，环比上升仅17万吨，而关税下调的措施，必将刺激贸易商的进口动力，增加国内成品油供应，进而增加国内供应，平抑市场价格。</P>
<P>值得关注的是，关税下调的政策，已影响国内成品油批发价格的下调。记者采访了解到，就在上周，全国汽油市场下跌范围扩大，其中价格持稳地区占据64%，下跌地区扩大至36%，华东、华北、华南、华中等多个地区都出现了价格调整;柴油市场下跌地区超过半数，没有上涨地区。</P>
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