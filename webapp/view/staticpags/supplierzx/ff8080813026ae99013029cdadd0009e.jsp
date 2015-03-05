<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>供应商爆发劳资纠纷 韩国汽车陷全面停产- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813026ae99013029cdadd0009e.jsp" title="供应商爆发劳资纠纷 韩国汽车陷全面停产" class="cmsHref_self">供应商爆发劳资纠纷 韩国汽车陷全面停产</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>供应商爆发劳资纠纷 韩国汽车陷全面停产</h1>
					<div class="source">
						<span>发布时间：2011-05-26</span>
						<span>发布人：-  </span>
					</div>
					<p><p>由于国内零部件供应商爆发劳资纠纷，韩国汽车企业在5月可能会面临全面停产的危机。昨日，韩国车企呼吁一家发动机零部件公司的罢工应立即结束。</p>
<p>从上周三开始，Yoosung Enterprise的工人就开始了罢工，并占据公司厂房，导致企业无法生产。据悉，这家公司为韩国车企提供约80%的活塞环，此外还生产空气压缩机、阀门等发动机零件。</p>
<p>由于供应不足，起亚汽车旗下一家工厂的嘉华车型上周已停产。而从周一开始，现代、起亚的索纳塔、伊兰特和K5等都陷入了停产。现代汽车的声明说，如果罢工持续，5月份将损失约5万辆轿车产能。另外，通用韩国、雷诺三星、双龙汽车也受到了停产影响。</p>
<p>据韩国媒体报道，有关零部件工厂从今年1月开始就爆发了劳资纠纷，工人要求重新排班和检讨薪酬制度。记者了解到，现代、起亚向中国出口的汽车包括索纳塔、胜达、霸锐等20多个车型，暂时未清楚罢工导致的停产对国内市场是否有影响。</p>
<p>自日本3月发生大地震以来，韩系车成了&ldquo;受益者&rdquo;。4月的统计显示，韩国车企的全球汽车销量达58.2万辆，同比增幅达39.1%，早前更有呼声称现代汽车的全球销量排名今年可能将上升到第三位。</p>
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