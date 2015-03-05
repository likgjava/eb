<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>我国决心打破铁矿石垄断成全球头号供应国- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/goodszx/ff8080813090cbdd013090f08fdc00f4.jsp" title="我国决心打破铁矿石垄断成全球头号供应国" class="cmsHref_self">我国决心打破铁矿石垄断成全球头号供应国</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f7274030138" />
				<div class="frameNews">
					<h1>我国决心打破铁矿石垄断成全球头号供应国</h1>
					<div class="source">
						<span>发布时间：2011-06-15</span>
						<span>发布人：-  </span>
					</div>
					<p><p>中国目前在稀土生产与太阳能电池板领域的主导地位反映了北京方面的一大决心：成为相关领域的全球领头羊。它的下一个目标、也是外企特别关注的领域，那就是：战略意义重大、但科技含量不高的铁矿石。</p>
<p>任何对钢铁行业有所了解的人都知道，现在的铁矿石生产由必和必拓、淡水河谷与力拓等海外企业主导。每年中国钢铁企业谈判新的供应合同时，海外主导地位都让北京痛苦不已。为结束这种状况，北京下定决心大幅增加国内铁矿石生产，成全球头号供应国。</p>
<p>据当地媒体报道，中国拟到2015年打破海外矿企的垄断地位，措施之一就是实现国产矿年产达15亿吨---比目前产量增加近50%。同时，北京方面还在推动国内矿商2015年前实现海外铁矿石的权益矿达2亿吨。专家分析称如果顺利达标，到2015年中国对外矿依存程度将从去年的63%降至42%。</p>
<p>那么，它到底行得通吗?答案是&quot;YES&quot;，这显然能够削弱国际矿业巨擘影响力。中国拥有丰富的国内资源来达到这一目标，而且过往案例证明对于钢铁等关键行业，自足问题胜过任何经济问题。而且，海外与国内地方政府应该都不会带来任何阻力。一句话：中国计划降低对外矿的依存程度，成功几率几乎100%。</p>
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