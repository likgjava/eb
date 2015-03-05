<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>深采购订单指数优于省平均水平- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080813026ae99013029c170120015.jsp" title="深采购订单指数优于省平均水平" class="cmsHref_self">深采购订单指数优于省平均水平</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>深采购订单指数优于省平均水平</h1>
					<div class="source">
						<span>发布时间：2011-05-26</span>
						<span>发布人：-  </span>
					</div>
					<p><p>网商创业者论坛昨日在深圳举办，上千名网商代表齐聚深圳共论网上创业发展。论坛上发布了阿里研究中心通过对2009年相关数据的采集、计算和分析，形成的《2010年度网商发展指数报告》，记者从报告中获悉，广东省网商发展指数为70.91，位居全国第一。</p>
<p>报告显示，截至2009年年底，全国网商发展指数为30.30。网商发展指数排名前十的省市依次为：广东、浙江、上海、北京、江苏、福建、山东、天津、四川、河北。值得注意的是，按照国家统计局发布的数字，2009年，我国GDP总量前十位的省市分别为：广东、江苏、山东、浙江、河南、河北、辽宁、上海、四川、湖南。网商发展指数排名同GDP排名相比较，前十强的重合度为70%。</p>
<p>与此同时，论坛上还发布了《深圳出口报告》，根据阿里巴巴平台数据显示，在电子商务的带动下，海外对深圳采购订单指数处于回升状态，而且好于全国和广东的平均水平。</p>
<p>报告同时显示未来2~3个月，深圳出口形势前景良好，增速在30%以上。其中，安防产品、钟表珠宝及汽摩配件的出口增长比较快，相反，包装印刷、通讯器材和礼品工艺品的增长则比较缓慢。</p>
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