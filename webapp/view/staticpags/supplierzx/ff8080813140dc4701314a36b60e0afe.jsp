<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>奇瑞汽车巴西工厂开工奠基 建成后年产能达15万辆- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813140dc4701314a36b60e0afe.jsp" title="奇瑞汽车巴西工厂开工奠基 建成后年产能达15万辆" class="cmsHref_self">奇瑞汽车巴西工厂开工奠基 建成后年产能达15万辆</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>奇瑞汽车巴西工厂开工奠基 建成后年产能达15万辆</h1>
					<div class="source">
						<span>发布时间：2011-07-21</span>
						<span>发布人：-新华网  </span>
					</div>
					<p><P>7月20日电 记者20日从奇瑞汽车公司获悉，奇瑞公司总投资4亿美元的巴西工厂项目，于7月19日在巴西圣保罗州的雅卡雷伊市，举行了开工奠基仪式。</P>
<P>据奇瑞公司新闻发言人金弋波介绍，该项目由奇瑞公司独资建设，总投资为4亿美元，工厂占地总面积达到100万平方米，共分两期投入，项目一期投入为1.4亿美元，将建成涂装、焊装和总装3条生产线，预计于2013年9月份建成投产，年产能达到5万辆/年。二期将增加一条冲压生产线，建成后最终产能将达到15万辆/年。</P>
<P>金弋波表示，近年来，随着经济的发展，巴西汽车市场增长迅速，2010年巴西市场汽车总销量达到330万辆，超过德国位居世界第四位，且未来市场潜力巨大。奇瑞巴西工厂所在的雅卡雷伊市，位于巴西东南部的中心，是巴西的主要汽车消费市场之一，有着便利的物流运输条件及完整的汽车工业供应链体系，而且当地政府对于投资建厂给予了一定的优惠政策。</P>
<P>据悉，自2009年进入巴西市场以来，奇瑞销量快速增长，今年上半年奇瑞汽车出口巴西累计达18000辆，同比增长321%，已占到奇瑞出口总量超过25%的份额。</P>
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