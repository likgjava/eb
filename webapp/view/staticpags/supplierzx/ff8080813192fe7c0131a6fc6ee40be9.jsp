<%@ page pageEncoding="UTF-8"%>
<%@ include file="/view/srplatform/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link href="view/srplatform/portal/logo.ico" type="image/ico" rel="shortcut icon"/> 
<title>美的“跨界”应对“整体家居”时代- 【阳光易购】</title>
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
	<a href="javascript:void(0)" id="/view/staticpags/supplierzx/ff8080813192fe7c0131a6fc6ee40be9.jsp" title="美的“跨界”应对“整体家居”时代" class="cmsHref_self">美的“跨界”应对“整体家居”时代</a>
		
				</div>
			</div>
 
			<div id="conBody">
				<input type="hidden"  id="channelId" value="2c9087922e6f5c1a012e6f71f9460134" />
				<div class="frameNews">
					<h1>美的“跨界”应对“整体家居”时代</h1>
					<div class="source">
						<span>发布时间：2011-08-08</span>
						<span>发布人：-  </span>
					</div>
					<p><P>眼下，家电企业的“跨界”之风正劲。整体卫浴、整体厨房和刚刚刮起的衣柜定制风潮，将早几年白色家电企业跨界进入黑电领域的“小手笔”远远地甩在了脑后。此时，面对家电产品薄如刀刃的利润，国内各家电壁挂炉企业都在争相上演一出出转行创业的大戏。</P>
<P>在上个月，美的品牌整体家居在某展会上华丽亮相。在美的整体家居展厅现场展示了橱柜、衣柜、衣帽间、书柜、浴室柜等板式家具，美的全系列家电产品包括厨房电器、卫浴电器、微波电器、洗涤电器、净水设备、电工照明、太阳能以及供暖系统，这种“一站式”的服务平台，给观众带来了新鲜的便捷体验。</P>
<P>白色家电到并不熟悉的整体卫浴市场，美的的大胆尝试引来众多媒体纷纷撰文。这家白电巨头的华丽变身是否会给整个行业带来哪些影响?美的的下一步动作又将如何演绎?这些问题都成为了话题焦点。</P>
<P>对此，美的整体家居集成有限公司的策划推广经理沈雪峰表示，美的此次大举进军整体家居行业，是契合家居“一体化”趋势做出的重大战略举措。目前，我国的橱柜、厨房电器、衣柜、卫浴等产业正在走向“一体化”，消费者崇尚家居统一风格的需求导向，促进了家居壁挂炉产业融合的必然趋势。以美的为首的家电企业开始整合资源，组建集成家居系统，主动应对“整体家居”时代。</P>
<P>沈雪峰透露，凭借美的集团在家电领域的深厚积累，美的已经取得了在整体厨房的成功实践，下一步目标是整体卫浴，而美的整体卫浴是以集成吊顶为牵引，纳入浴室柜、壁挂炉、热水器等，将冷热水供给、梳妆洗涤、收纳储藏、照明换气、风暖灯暖等整合为一体，为客户提供时尚美观的一站式整体卫浴解决方案。目前正在进行的是整体吊顶，其他的部分会分步骤分阶段地实施。</P>
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