<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>国际能源署下调全球原油需求预期- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812fe3e2c6012fe6cd23150177.jsp" title="国际能源署下调全球原油需求预期" class="cmsHref_self">国际能源署下调全球原油需求预期</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>国际能源署下调全球原油需求预期</h1>
					<div class="source">
						<span>发布时间：2011-05-13</span>
						<span>发布人：-  </span>
					</div>
					<p><p>北京时间5月13日凌晨消息，国际能源署(IEA)周四公布月度原油报告称，3月份的初步数据表明全球原油需求的年增长率自2009年夏季以来第一次下降至接近于零的水平，原因是过高的价格正在抑制原油消费，尤其是在美国市场上更是如此。</p>
<p>国际能源署在今天公布的报告中指出：&ldquo;虽然3月份的初步数据很可能是受到了日本大地震和复活节假期的影响，但每加仑4美元(约合每升1.06美元)的汽油价格很可能会带来一个&lsquo;贫血的&rsquo;美国驾驶季节。我们对原油需求的预期发生了重要的变化，预计整个2011年北美市场上的原油需求都会走软。&rdquo;</p>
<p>根据国际能源署目前的预期，2011年全球原油日均需求量将为8920万桶，比此前预期少19万桶。国际能源署还称，4月份全球原油日均供应量减少5万桶，至8750万桶，其中来自于欧佩克(OPEC)成员国的供应量继续呈现出下行趋势。</p>
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