<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>纺织企业利润增长有持续回落表现- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813192fe7c01319778d43a0330.jsp" title="纺织企业利润增长有持续回落表现" class="cmsHref_self">纺织企业利润增长有持续回落表现</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>纺织企业利润增长有持续回落表现</h1>
					<div class="source">
						<span>发布时间：2011-08-05</span>
						<span>发布人：-  </span>
					</div>
					<p><P>在内销旺盛、结构调整加快等利好作用下，今年以来中国纺织企业的盈利能力较上年同期有所提高，然而受成本上涨等因素影响，利润增长却又有持续回落表现。当前，中国纺织业运行正面临三大因素的困扰。</P>
<P>记者今日从中国纺织工业协会了解到，今年上半年中国纺织行业企业运行基本平稳，但劳动力成本维持高位，劳动力资源紧缺加剧，原材料、燃料动力成本继续上涨，国际市场需求不旺，纺织企业生产的经营压力增大，特别是那些棉纺织行业和中小型企业。</P>
<P>据介绍，目前有三种问题正影响着中国纺织业的运行。首先是，棉价的快速波动令中国纺织企业愁眉不展。近年来棉花价格高涨是原料成本压力上升的关键因素。从2009年下半年始，中国棉价一路高涨，尽管自今年3月中旬以来，中国棉价一直处于下跌通道，但迄今仍居于高位。</P>
<P>其次，今年以来世界经济复苏进程出现了一些动荡，在国际市场需求增长不确定性增加的大背景下，各国争夺国际市场的竞争也日趋激烈，而中国纺织业所面临的原材料价格上涨，劳动力成本上升尤为明显，这使得新兴的发展中国家对中国的传统纺织服装行业直接构成竞争。</P>
<P>最后，资金环境趋紧，加息带来融资的困难。今年中国纺织企业(尤其是中小企业)的资金环境更趋紧张，无论是正常的生产经营，还是扩大规模、改造升级等，资金的缺乏都令纺织企业难展欢颜。</P>
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