<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>美零部件供应商天纳克公布股份回购方案- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff808081302107010130249f1af60134.jsp" title="美零部件供应商天纳克公布股份回购方案" class="cmsHref_self">美零部件供应商天纳克公布股份回购方案</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>美零部件供应商天纳克公布股份回购方案</h1>
					<div class="source">
						<span>发布时间：2011-05-25</span>
						<span>发布人：-  </span>
					</div>
					<p><p>美国汽车零部件供应商天纳克公司(Tenneco Inc.)日前表示，该公司董事会已经批准股份回购方案，授权该公司于今后12个月内回购最高达40万股发行在外的普通股股份。</p>
<p>该公司表示，根据其长期薪酬计划，该公司于2011年向员工发行了限制性股票和股票期权，而此次该公司提出股份回购方案是为了抵消发行这些限制性股票和股票期权对该公司股份造成的摊薄影响。</p>
<p>据报道，该公司计划使用经营活动所得现金，以公开市场交易或私下协商交易的方式回购这些股份。该公司称，此次回购方案无需在具体时间或特定情况下执行。</p>
<p>天纳克公司称，该公司一直致力于降低负债水平以及提高公司财务活动的灵活性。据悉，截至2010年年底，天纳克的净负债从2009年底的10.5亿美元降至9.9亿美元。</p>
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