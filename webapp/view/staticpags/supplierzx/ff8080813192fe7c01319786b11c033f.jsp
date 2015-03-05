<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>LG、西门子、三星等获美国新能效星标签- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813192fe7c01319786b11c033f.jsp" title="LG、西门子、三星等获美国新能效星标签" class="cmsHref_self">LG、西门子、三星等获美国新能效星标签</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>LG、西门子、三星等获美国新能效星标签</h1>
					<div class="source">
						<span>发布时间：2011-08-05</span>
						<span>发布人：-  </span>
					</div>
					<p><P>美国新一批“最佳能效”先锋评比结果出炉，新一批最佳能效产品包括来自Frigidaire,Crosley,Kenmore,LG,伊莱克斯和三星的15款洗衣机;来自三星、松下、LG和百思买自主品牌的18款电视机;Rheem提供的4款中央空调和热力泵。伊莱克斯、西门子、LG和三星也成为美国家电市场上少有的每个种类产品都获得了最新能效星标签的品牌。</P>
<P>“最佳能效”先锋评比项目是由美国环保署和美国能源署共同推进的，是美国“能效星”计划的一个环节，他们将之作为激励制造商提高产品能效，为过客提供更多购买选择的认证。它的目的是帮助生产商吸引具有环保倾向和喜欢采用最新产品的顾客们。</P>
<P>最佳能效产品选择的是美国市场上所有同类型产品的前5%，产品要得到最佳能效认定必须符合能效星计划的要求，并经过环保署的认证。比如，对于体积≤2.5立方英尺的洗衣机来讲，能效要求包括：修正能量因数≥2.3，水因数≤4.5;对于体积≥2.5立方英尺的洗衣机来讲，其能效要求为修正能量因数≥3.0，水因数≤3.3。</P>
<P>美国环保署和能源署还将在近期宣布最佳能效冰箱及冷柜，以及他们对于最佳能效性炉具和地热式热力泵的评选要求。环保署今年下半年将要执行囊括更多品种产品的评选程序。</P>
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