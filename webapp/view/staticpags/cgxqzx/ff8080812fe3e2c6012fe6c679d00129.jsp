<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>韩企将组团来渝采购汽车零部件- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812fe3e2c6012fe6c679d00129.jsp" title="韩企将组团来渝采购汽车零部件" class="cmsHref_self">韩企将组团来渝采购汽车零部件</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>韩企将组团来渝采购汽车零部件</h1>
					<div class="source">
						<span>发布时间：2011-05-13</span>
						<span>发布人：-  </span>
					</div>
					<p><p>重庆汽车产业所蕴藏的巨大机遇，吸引着韩国汽车企业。本月，韩国汽车工业协会将率领12家韩企访问重庆，并在渝洽会期间，与重庆企业对接。这是记者从昨日召开的渝洽会新闻通气会上获悉的。</p>
<p>重庆是我国发展电动汽车最早的城市之一，混合动力汽车技术在全国处于技术领先水平，不仅是我国第一批电动汽车示范城市的13个城市之一，还建立了以整车为龙头的重庆节能与新能源汽车产业联盟。去年，全市汽车产量达229.7万辆，汽车工业产值已突破3000亿元。</p>
<p>此前，韩国汽车企业每年都会来渝考察，其中韩泰、浦项等知名企业已在重庆&ldquo;扎根&rdquo;。本次来渝的企业包括世界500强韩国现代汽车、现代摩比斯、东西设备及发动机部件有限公司、Semyung工业、Yura公司、INFAC公司、GEN公司、Koreahitek公司、太阳金属工业有限公司、Hanshin工业等。届时，除了举办&ldquo;2011中韩汽车产业(重庆)对接洽谈会&rdquo;，这些企业还会抛出大量采购订单。</p>
<p>此外，中国(重庆)新能源汽车摩托车产业国际合作论坛，也将于20日在重庆国际会议展览中心举行。据了解，此次论坛以&ldquo;搭建新能源汽车、摩托车产业国际合作平台，推进技术交流、产业发展，共享新能源动力革命新机遇&rdquo;为主题，分为主题大会、高层圆桌座谈会和两江新区参观考察及洽谈等环节。</p>
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