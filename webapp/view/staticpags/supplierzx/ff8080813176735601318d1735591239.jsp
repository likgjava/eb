<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>丰田第一财季营业亏损千亿 仍上调全年利润预期- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813176735601318d1735591239.jsp" title="丰田第一财季营业亏损千亿 仍上调全年利润预期" class="cmsHref_self">丰田第一财季营业亏损千亿 仍上调全年利润预期</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>丰田第一财季营业亏损千亿 仍上调全年利润预期</h1>
					<div class="source">
						<span>发布时间：2011-08-03</span>
						<span>发布人：-中国新闻网  </span>
					</div>
					<p><P>8月2日电 据日本共同社网站报道，丰田汽车2日披露的2011财年第一财季季报显示，受东日本大地震导致生产大幅下滑的影响，营业利润从上年同期的盈利2116亿日元降为亏损1079亿日元(约合人民币90亿元)。</P>
<P>中新网汽车频道从日本共同社网站获悉，丰田汽车第一财季销售收入为3.4410万亿日元，比上年同期下降29.4%，净利润锐降99.4%至11亿日元。不过，丰田同时将2011财年全年营业利润预期从原先的3000亿日元上调至4500亿日元，理由是在东日本大地震中遭受重创的零部件供应链已经恢复。</P>
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