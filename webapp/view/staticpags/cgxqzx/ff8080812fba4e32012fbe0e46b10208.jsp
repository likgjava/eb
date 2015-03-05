<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>澳大利亚3月零售销售意外月降0.5%- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/cgxqzx/ff8080812fba4e32012fbe0e46b10208.jsp" title="澳大利亚3月零售销售意外月降0.5%" class="cmsHref_self">澳大利亚3月零售销售意外月降0.5%</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f72d3b7013c" />
				<div class="frameNews">
					<h1>澳大利亚3月零售销售意外月降0.5%</h1>
					<div class="source">
						<span>发布时间：2011-05-05</span>
						<span>发布人：-财讯  </span>
					</div>
					<p><p>　　澳大利亚统计局公布，3月零售销售经季调意外月降0.5%，至204.6亿澳元，但年比上升，其趋势估值月升0.1%，而一季度零售销售季比持平。</p>
<p>　　澳大利亚统计局(Australian Bureau of Statistics)5月5日公布，澳大利亚3月零售销售经季调月降0.5%，从2月的205.5亿澳元下降至204.6亿澳元，但高于去年同期的200亿澳元。澳大利亚2月零售销售月升0.8%。</p>
<p>　　该报告公布前，经济学家的平均预期为3月零售销售月升0.5%.</p>
<p>　　该局表示，澳大利亚3月零售销售趋势估值月升0.1%，至204.4亿澳元。趋势估值是对经季节性因素调整后数据进一步修正的结果。</p>
<p>　　数据还显示，以环比物量计算，澳大利亚2011年第一季度零售销售季比持平，相比之下，经济学家的预期为季升0.7%。</p>
<p>　　以数量计算，澳大利亚一季度零售营业额下降至597.8亿澳元，而2010年四季度为598.4亿澳元。</p>
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