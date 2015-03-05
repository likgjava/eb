<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>中国电信用户逾亿 签400亿终端采购大单- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff808081309cd86d0130aa7c687e0019.jsp" title="中国电信用户逾亿 签400亿终端采购大单" class="cmsHref_self">中国电信用户逾亿 签400亿终端采购大单</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>中国电信用户逾亿 签400亿终端采购大单</h1>
					<div class="source">
						<span>发布时间：2011-06-20</span>
						<span>发布人：-  </span>
					</div>
					<p><P>出于对未来市场的看好，中国电信(微博)携手代理商启动了数量高达4000万部的3G终端采购，总金额高达400亿元。</P>
<P>中国电信集团消息称，在17日的采购交易会上，中国电信联合中邮普泰、普天太力、天音通信、酷人通讯等四家代理商与众多CDMA终端厂商签署了采购合同，预计总金额为400亿元。</P>
<P>截至今年4月底，中国电信用户数达1.03亿户，成为世界上最大的CDMA运营商。今年年初，中国电信定下全年销售CDMA终端6000万部的目标。</P>
<P>前四个月，内地市场CDMA终端销售1798万部，比去年同期增长32%。中国电信副总经理杨小伟今天表示，今年6000万的销售目标有望达到。</P>
<P>此外，中国电信对社会渠道的重视也得到体现。在今天签订的采购合同中，四大代理商采购约2790万部，占70%份额，中国电信自主采购1200万部，占30%份额。</P>
<P>中国电信此前也通过终端采购也解决CDMA终端瓶颈。2009年中国电信3G网络商用时，曾连续进行过120万部、400万部终端采购，其总额不足100亿元。此次一举签订400亿元的采购大单，解决厂商销量之忧。在交易会上，摩托罗拉、华为等国内外厂商一举推出50余款全新智能手机。</P>
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