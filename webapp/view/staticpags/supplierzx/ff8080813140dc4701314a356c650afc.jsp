<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>必和必拓铁矿等多种商品产量大幅增长- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813140dc4701314a356c650afc.jsp" title="必和必拓铁矿等多种商品产量大幅增长" class="cmsHref_self">必和必拓铁矿等多种商品产量大幅增长</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>必和必拓铁矿等多种商品产量大幅增长</h1>
					<div class="source">
						<span>发布时间：2011-07-21</span>
						<span>发布人：-新华网  </span>
					</div>
					<p><P>7月20日电 国际矿业巨头必和必拓20日公布2010至2011财年生产报告。这份报告显示，必和必拓铁矿等多种商品的产量均较前一财年大幅增长。</P>
<P>报告显示，在截至2011年6月30日的上一财年中，必和必拓的铁矿产量为1.34406亿吨，较前一财年增长8%;石油类产品产量为1.5938亿吨，较前一财年增长1%;铜产量为113.94万吨，较前一财年增长6%。</P>
<P>此外，数据显示，同期必和必拓动力煤产量为6950万吨，较前一财年增长5%;冶金煤产量为3267.8万吨，较前一财年下降13%。</P>
<P>报告把总体产能维持增长的原因归结为新项目启动、基础设施改善和企业并购活动等因素。</P>
<P>必和必拓2010年下半年净利润为105.2亿美元，同比大增72%。在此背景下，必和必拓今年以来先后宣布提高股息，回购公司股票，加快收购矿业资产等措施。</P>
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